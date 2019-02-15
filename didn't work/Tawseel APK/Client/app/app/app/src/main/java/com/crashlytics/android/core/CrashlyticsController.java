package com.crashlytics.android.core;

import android.app.Activity;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.core.internal.models.SessionEventData;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.Crash.FatalException;
import io.fabric.sdk.android.services.common.Crash.LoggedException;
import io.fabric.sdk.android.services.common.DeliveryMechanism;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import io.fabric.sdk.android.services.persistence.FileStore;
import io.fabric.sdk.android.services.settings.AppSettingsData;
import io.fabric.sdk.android.services.settings.FeaturesSettingsData;
import io.fabric.sdk.android.services.settings.PromptSettingsData;
import io.fabric.sdk.android.services.settings.SessionSettingsData;
import io.fabric.sdk.android.services.settings.Settings;
import io.fabric.sdk.android.services.settings.SettingsData;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CrashlyticsController
{
  private static final int ANALYZER_VERSION = 1;
  static final FilenameFilter ANY_SESSION_FILENAME_FILTER;
  private static final String COLLECT_CUSTOM_KEYS = "com.crashlytics.CollectCustomKeys";
  private static final String CRASHLYTICS_API_ENDPOINT = "com.crashlytics.ApiEndpoint";
  private static final String EVENT_TYPE_CRASH = "crash";
  private static final String EVENT_TYPE_LOGGED = "error";
  static final String FATAL_SESSION_DIR = "fatal-sessions";
  private static final String GENERATOR_FORMAT = "Crashlytics Android SDK/%s";
  private static final String[] INITIAL_SESSION_PART_TAGS = { "SessionUser", "SessionApp", "SessionOS", "SessionDevice" };
  static final String INVALID_CLS_CACHE_DIR = "invalidClsFiles";
  static final Comparator<File> LARGEST_FILE_NAME_FIRST;
  static final int MAX_INVALID_SESSIONS = 4;
  private static final int MAX_LOCAL_LOGGED_EXCEPTIONS = 64;
  static final int MAX_OPEN_SESSIONS = 8;
  static final int MAX_STACK_SIZE = 1024;
  static final String NONFATAL_SESSION_DIR = "nonfatal-sessions";
  static final int NUM_STACK_REPETITIONS_ALLOWED = 10;
  private static final Map<String, String> SEND_AT_CRASHTIME_HEADER;
  static final String SESSION_APP_TAG = "SessionApp";
  static final String SESSION_BEGIN_TAG = "BeginSession";
  static final String SESSION_DEVICE_TAG = "SessionDevice";
  static final String SESSION_EVENT_MISSING_BINARY_IMGS_TAG = "SessionMissingBinaryImages";
  static final String SESSION_FATAL_TAG = "SessionCrash";
  static final FilenameFilter SESSION_FILE_FILTER = new FilenameFilter()
  {
    public boolean accept(File paramAnonymousFile, String paramAnonymousString)
    {
      return (paramAnonymousString.length() == ".cls".length() + 35) && (paramAnonymousString.endsWith(".cls"));
    }
  };
  private static final Pattern SESSION_FILE_PATTERN;
  private static final int SESSION_ID_LENGTH = 35;
  static final String SESSION_NON_FATAL_TAG = "SessionEvent";
  static final String SESSION_OS_TAG = "SessionOS";
  static final String SESSION_USER_TAG = "SessionUser";
  private static final boolean SHOULD_PROMPT_BEFORE_SENDING_REPORTS_DEFAULT = false;
  static final Comparator<File> SMALLEST_FILE_NAME_FIRST;
  private final AppData appData;
  private final CrashlyticsBackgroundWorker backgroundWorker;
  private CrashlyticsUncaughtExceptionHandler crashHandler;
  private final CrashlyticsCore crashlyticsCore;
  private final DevicePowerStateListener devicePowerStateListener;
  private final AtomicInteger eventCounter = new AtomicInteger(0);
  private final FileStore fileStore;
  private final ReportUploader.HandlingExceptionCheck handlingExceptionCheck;
  private final HttpRequestFactory httpRequestFactory;
  private final IdManager idManager;
  private final LogFileDirectoryProvider logFileDirectoryProvider;
  private final LogFileManager logFileManager;
  private final PreferenceManager preferenceManager;
  private final ReportUploader.ReportFilesProvider reportFilesProvider;
  private final StackTraceTrimmingStrategy stackTraceTrimmingStrategy;
  private final String unityVersion;
  
  static
  {
    LARGEST_FILE_NAME_FIRST = new Comparator()
    {
      public int compare(File paramAnonymousFile1, File paramAnonymousFile2)
      {
        return paramAnonymousFile2.getName().compareTo(paramAnonymousFile1.getName());
      }
    };
    SMALLEST_FILE_NAME_FIRST = new Comparator()
    {
      public int compare(File paramAnonymousFile1, File paramAnonymousFile2)
      {
        return paramAnonymousFile1.getName().compareTo(paramAnonymousFile2.getName());
      }
    };
    ANY_SESSION_FILENAME_FILTER = new FilenameFilter()
    {
      public boolean accept(File paramAnonymousFile, String paramAnonymousString)
      {
        return CrashlyticsController.SESSION_FILE_PATTERN.matcher(paramAnonymousString).matches();
      }
    };
    SESSION_FILE_PATTERN = Pattern.compile("([\\d|A-Z|a-z]{12}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{12}).+");
    SEND_AT_CRASHTIME_HEADER = Collections.singletonMap("X-CRASHLYTICS-SEND-FLAGS", "1");
  }
  
  CrashlyticsController(CrashlyticsCore paramCrashlyticsCore, CrashlyticsBackgroundWorker paramCrashlyticsBackgroundWorker, HttpRequestFactory paramHttpRequestFactory, IdManager paramIdManager, PreferenceManager paramPreferenceManager, FileStore paramFileStore, AppData paramAppData, UnityVersionProvider paramUnityVersionProvider)
  {
    this.crashlyticsCore = paramCrashlyticsCore;
    this.backgroundWorker = paramCrashlyticsBackgroundWorker;
    this.httpRequestFactory = paramHttpRequestFactory;
    this.idManager = paramIdManager;
    this.preferenceManager = paramPreferenceManager;
    this.fileStore = paramFileStore;
    this.appData = paramAppData;
    this.unityVersion = paramUnityVersionProvider.getUnityVersion();
    paramCrashlyticsCore = paramCrashlyticsCore.getContext();
    this.logFileDirectoryProvider = new LogFileDirectoryProvider(paramFileStore);
    this.logFileManager = new LogFileManager(paramCrashlyticsCore, this.logFileDirectoryProvider);
    this.reportFilesProvider = new ReportUploaderFilesProvider(null);
    this.handlingExceptionCheck = new ReportUploaderHandlingExceptionCheck(null);
    this.devicePowerStateListener = new DevicePowerStateListener(paramCrashlyticsCore);
    this.stackTraceTrimmingStrategy = new MiddleOutFallbackStrategy(1024, new StackTraceTrimmingStrategy[] { new RemoveRepeatsStrategy(10) });
  }
  
  private void closeOpenSessions(File[] paramArrayOfFile, int paramInt1, int paramInt2)
  {
    Fabric.getLogger().d("CrashlyticsCore", "Closing open sessions.");
    while (paramInt1 < paramArrayOfFile.length)
    {
      File localFile = paramArrayOfFile[paramInt1];
      String str = getSessionIdFromSessionFile(localFile);
      Fabric.getLogger().d("CrashlyticsCore", "Closing session: " + str);
      writeSessionPartsToSessionFile(localFile, str, paramInt2);
      paramInt1 += 1;
    }
  }
  
  private void closeWithoutRenamingOrLog(ClsFileOutputStream paramClsFileOutputStream)
  {
    if (paramClsFileOutputStream == null) {
      return;
    }
    try
    {
      paramClsFileOutputStream.closeInProgressStream();
      return;
    }
    catch (IOException paramClsFileOutputStream)
    {
      Fabric.getLogger().e("CrashlyticsCore", "Error closing session file stream in the presence of an exception", paramClsFileOutputStream);
    }
  }
  
  private static void copyToCodedOutputStream(InputStream paramInputStream, CodedOutputStream paramCodedOutputStream, int paramInt)
    throws IOException
  {
    byte[] arrayOfByte = new byte[paramInt];
    paramInt = 0;
    while (paramInt < arrayOfByte.length)
    {
      int i = paramInputStream.read(arrayOfByte, paramInt, arrayOfByte.length - paramInt);
      if (i < 0) {
        break;
      }
      paramInt += i;
    }
    paramCodedOutputStream.writeRawBytes(arrayOfByte);
  }
  
  private void deleteSessionPartFilesFor(String paramString)
  {
    paramString = listSessionPartFilesFor(paramString);
    int j = paramString.length;
    int i = 0;
    while (i < j)
    {
      paramString[i].delete();
      i += 1;
    }
  }
  
  private void doCloseSessions(SessionSettingsData paramSessionSettingsData, boolean paramBoolean)
    throws Exception
  {
    if (paramBoolean) {}
    File[] arrayOfFile;
    for (int i = 1;; i = 0)
    {
      trimOpenSessions(i + 8);
      arrayOfFile = listSortedSessionBeginFiles();
      if (arrayOfFile.length > i) {
        break;
      }
      Fabric.getLogger().d("CrashlyticsCore", "No open sessions to be closed.");
      return;
    }
    writeSessionUser(getSessionIdFromSessionFile(arrayOfFile[i]));
    if (paramSessionSettingsData == null)
    {
      Fabric.getLogger().d("CrashlyticsCore", "Unable to close session. Settings are not loaded.");
      return;
    }
    closeOpenSessions(arrayOfFile, i, paramSessionSettingsData.maxCustomExceptionEvents);
  }
  
  private void doOpenSession()
    throws Exception
  {
    Date localDate = new Date();
    String str = new CLSUUID(this.idManager).toString();
    Fabric.getLogger().d("CrashlyticsCore", "Opening a new session with ID " + str);
    writeBeginSession(str, localDate);
    writeSessionApp(str);
    writeSessionOS(str);
    writeSessionDevice(str);
    this.logFileManager.setCurrentSession(str);
  }
  
  /* Error */
  private void doWriteExternalCrashEvent(SessionEventData paramSessionEventData)
    throws IOException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 10
    //   3: aconst_null
    //   4: astore 6
    //   6: aconst_null
    //   7: astore 11
    //   9: aconst_null
    //   10: astore 9
    //   12: aconst_null
    //   13: astore 8
    //   15: aconst_null
    //   16: astore 7
    //   18: aload 11
    //   20: astore_3
    //   21: aload 10
    //   23: astore 4
    //   25: aload_0
    //   26: invokespecial 485	com/crashlytics/android/core/CrashlyticsController:getPreviousSessionId	()Ljava/lang/String;
    //   29: astore 12
    //   31: aload 12
    //   33: ifnonnull +40 -> 73
    //   36: aload 11
    //   38: astore_3
    //   39: aload 10
    //   41: astore 4
    //   43: invokestatic 365	io/fabric/sdk/android/Fabric:getLogger	()Lio/fabric/sdk/android/Logger;
    //   46: ldc_w 367
    //   49: ldc_w 487
    //   52: aconst_null
    //   53: invokeinterface 408 4 0
    //   58: aconst_null
    //   59: ldc_w 489
    //   62: invokestatic 495	io/fabric/sdk/android/services/common/CommonUtils:flushOrLog	(Ljava/io/Flushable;Ljava/lang/String;)V
    //   65: aconst_null
    //   66: ldc_w 497
    //   69: invokestatic 501	io/fabric/sdk/android/services/common/CommonUtils:closeOrLog	(Ljava/io/Closeable;Ljava/lang/String;)V
    //   72: return
    //   73: aload 11
    //   75: astore_3
    //   76: aload 10
    //   78: astore 4
    //   80: aload 12
    //   82: getstatic 507	java/util/Locale:US	Ljava/util/Locale;
    //   85: ldc_w 509
    //   88: iconst_2
    //   89: anewarray 4	java/lang/Object
    //   92: dup
    //   93: iconst_0
    //   94: aload_1
    //   95: getfield 515	com/crashlytics/android/core/internal/models/SessionEventData:signal	Lcom/crashlytics/android/core/internal/models/SignalData;
    //   98: getfield 520	com/crashlytics/android/core/internal/models/SignalData:code	Ljava/lang/String;
    //   101: aastore
    //   102: dup
    //   103: iconst_1
    //   104: aload_1
    //   105: getfield 515	com/crashlytics/android/core/internal/models/SessionEventData:signal	Lcom/crashlytics/android/core/internal/models/SignalData;
    //   108: getfield 523	com/crashlytics/android/core/internal/models/SignalData:name	Ljava/lang/String;
    //   111: aastore
    //   112: invokestatic 527	java/lang/String:format	(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   115: invokestatic 530	com/crashlytics/android/core/CrashlyticsController:recordFatalExceptionAnswersEvent	(Ljava/lang/String;Ljava/lang/String;)V
    //   118: aload 11
    //   120: astore_3
    //   121: aload 10
    //   123: astore 4
    //   125: aload_1
    //   126: getfield 534	com/crashlytics/android/core/internal/models/SessionEventData:binaryImages	[Lcom/crashlytics/android/core/internal/models/BinaryImageData;
    //   129: ifnull +154 -> 283
    //   132: aload 11
    //   134: astore_3
    //   135: aload 10
    //   137: astore 4
    //   139: aload_1
    //   140: getfield 534	com/crashlytics/android/core/internal/models/SessionEventData:binaryImages	[Lcom/crashlytics/android/core/internal/models/BinaryImageData;
    //   143: arraylength
    //   144: ifle +139 -> 283
    //   147: iconst_1
    //   148: istore_2
    //   149: goto +229 -> 378
    //   152: aload 11
    //   154: astore_3
    //   155: aload 10
    //   157: astore 4
    //   159: new 399	com/crashlytics/android/core/ClsFileOutputStream
    //   162: dup
    //   163: aload_0
    //   164: invokevirtual 538	com/crashlytics/android/core/CrashlyticsController:getFilesDir	()Ljava/io/File;
    //   167: new 381	java/lang/StringBuilder
    //   170: dup
    //   171: invokespecial 382	java/lang/StringBuilder:<init>	()V
    //   174: aload 12
    //   176: invokevirtual 388	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   179: aload 5
    //   181: invokevirtual 388	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   184: invokevirtual 391	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   187: invokespecial 541	com/crashlytics/android/core/ClsFileOutputStream:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   190: astore 5
    //   192: aload 9
    //   194: astore_3
    //   195: aload 8
    //   197: astore 4
    //   199: aload 5
    //   201: invokestatic 545	com/crashlytics/android/core/CodedOutputStream:newInstance	(Ljava/io/OutputStream;)Lcom/crashlytics/android/core/CodedOutputStream;
    //   204: astore 6
    //   206: aload 6
    //   208: astore_3
    //   209: aload 6
    //   211: astore 4
    //   213: new 547	com/crashlytics/android/core/MetaDataStore
    //   216: dup
    //   217: aload_0
    //   218: invokevirtual 538	com/crashlytics/android/core/CrashlyticsController:getFilesDir	()Ljava/io/File;
    //   221: invokespecial 550	com/crashlytics/android/core/MetaDataStore:<init>	(Ljava/io/File;)V
    //   224: aload 12
    //   226: invokevirtual 554	com/crashlytics/android/core/MetaDataStore:readKeyData	(Ljava/lang/String;)Ljava/util/Map;
    //   229: astore 7
    //   231: aload 6
    //   233: astore_3
    //   234: aload 6
    //   236: astore 4
    //   238: aload_1
    //   239: new 264	com/crashlytics/android/core/LogFileManager
    //   242: dup
    //   243: aload_0
    //   244: getfield 231	com/crashlytics/android/core/CrashlyticsController:crashlyticsCore	Lcom/crashlytics/android/core/CrashlyticsCore;
    //   247: invokevirtual 257	com/crashlytics/android/core/CrashlyticsCore:getContext	()Landroid/content/Context;
    //   250: aload_0
    //   251: getfield 262	com/crashlytics/android/core/CrashlyticsController:logFileDirectoryProvider	Lcom/crashlytics/android/core/CrashlyticsController$LogFileDirectoryProvider;
    //   254: aload 12
    //   256: invokespecial 557	com/crashlytics/android/core/LogFileManager:<init>	(Landroid/content/Context;Lcom/crashlytics/android/core/LogFileManager$DirectoryProvider;Ljava/lang/String;)V
    //   259: aload 7
    //   261: aload 6
    //   263: invokestatic 563	com/crashlytics/android/core/NativeCrashWriter:writeNativeCrash	(Lcom/crashlytics/android/core/internal/models/SessionEventData;Lcom/crashlytics/android/core/LogFileManager;Ljava/util/Map;Lcom/crashlytics/android/core/CodedOutputStream;)V
    //   266: aload 6
    //   268: ldc_w 489
    //   271: invokestatic 495	io/fabric/sdk/android/services/common/CommonUtils:flushOrLog	(Ljava/io/Flushable;Ljava/lang/String;)V
    //   274: aload 5
    //   276: ldc_w 497
    //   279: invokestatic 501	io/fabric/sdk/android/services/common/CommonUtils:closeOrLog	(Ljava/io/Closeable;Ljava/lang/String;)V
    //   282: return
    //   283: iconst_0
    //   284: istore_2
    //   285: goto +93 -> 378
    //   288: ldc 125
    //   290: astore 5
    //   292: goto -140 -> 152
    //   295: astore_1
    //   296: aload 6
    //   298: astore 5
    //   300: aload_1
    //   301: astore 6
    //   303: aload 7
    //   305: astore_1
    //   306: aload_1
    //   307: astore_3
    //   308: aload 5
    //   310: astore 4
    //   312: invokestatic 365	io/fabric/sdk/android/Fabric:getLogger	()Lio/fabric/sdk/android/Logger;
    //   315: ldc_w 367
    //   318: ldc_w 565
    //   321: aload 6
    //   323: invokeinterface 408 4 0
    //   328: aload_1
    //   329: ldc_w 489
    //   332: invokestatic 495	io/fabric/sdk/android/services/common/CommonUtils:flushOrLog	(Ljava/io/Flushable;Ljava/lang/String;)V
    //   335: aload 5
    //   337: ldc_w 497
    //   340: invokestatic 501	io/fabric/sdk/android/services/common/CommonUtils:closeOrLog	(Ljava/io/Closeable;Ljava/lang/String;)V
    //   343: return
    //   344: astore_1
    //   345: aload_3
    //   346: ldc_w 489
    //   349: invokestatic 495	io/fabric/sdk/android/services/common/CommonUtils:flushOrLog	(Ljava/io/Flushable;Ljava/lang/String;)V
    //   352: aload 4
    //   354: ldc_w 497
    //   357: invokestatic 501	io/fabric/sdk/android/services/common/CommonUtils:closeOrLog	(Ljava/io/Closeable;Ljava/lang/String;)V
    //   360: aload_1
    //   361: athrow
    //   362: astore_1
    //   363: aload 5
    //   365: astore 4
    //   367: goto -22 -> 345
    //   370: astore 6
    //   372: aload 4
    //   374: astore_1
    //   375: goto -69 -> 306
    //   378: iload_2
    //   379: ifeq -91 -> 288
    //   382: ldc -128
    //   384: astore 5
    //   386: goto -234 -> 152
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	389	0	this	CrashlyticsController
    //   0	389	1	paramSessionEventData	SessionEventData
    //   148	231	2	i	int
    //   20	326	3	localObject1	Object
    //   23	350	4	localObject2	Object
    //   179	206	5	localObject3	Object
    //   4	318	6	localObject4	Object
    //   370	1	6	localException	Exception
    //   16	288	7	localMap	Map
    //   13	183	8	localObject5	Object
    //   10	183	9	localObject6	Object
    //   1	155	10	localObject7	Object
    //   7	146	11	localObject8	Object
    //   29	226	12	str	String
    // Exception table:
    //   from	to	target	type
    //   25	31	295	java/lang/Exception
    //   43	58	295	java/lang/Exception
    //   80	118	295	java/lang/Exception
    //   125	132	295	java/lang/Exception
    //   139	147	295	java/lang/Exception
    //   159	192	295	java/lang/Exception
    //   25	31	344	finally
    //   43	58	344	finally
    //   80	118	344	finally
    //   125	132	344	finally
    //   139	147	344	finally
    //   159	192	344	finally
    //   312	328	344	finally
    //   199	206	362	finally
    //   213	231	362	finally
    //   238	266	362	finally
    //   199	206	370	java/lang/Exception
    //   213	231	370	java/lang/Exception
    //   238	266	370	java/lang/Exception
  }
  
  /* Error */
  private void doWriteNonFatal(Date paramDate, Thread paramThread, Throwable paramThrowable)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 303	com/crashlytics/android/core/CrashlyticsController:getCurrentSessionId	()Ljava/lang/String;
    //   4: astore 12
    //   6: aload 12
    //   8: ifnonnull +19 -> 27
    //   11: invokestatic 365	io/fabric/sdk/android/Fabric:getLogger	()Lio/fabric/sdk/android/Logger;
    //   14: ldc_w 367
    //   17: ldc_w 567
    //   20: aconst_null
    //   21: invokeinterface 408 4 0
    //   26: return
    //   27: aload 12
    //   29: aload_3
    //   30: invokevirtual 571	java/lang/Object:getClass	()Ljava/lang/Class;
    //   33: invokevirtual 576	java/lang/Class:getName	()Ljava/lang/String;
    //   36: invokestatic 579	com/crashlytics/android/core/CrashlyticsController:recordLoggedExceptionAnswersEvent	(Ljava/lang/String;Ljava/lang/String;)V
    //   39: aconst_null
    //   40: astore 6
    //   42: aconst_null
    //   43: astore 8
    //   45: aconst_null
    //   46: astore 11
    //   48: aconst_null
    //   49: astore 10
    //   51: aconst_null
    //   52: astore 9
    //   54: aconst_null
    //   55: astore 7
    //   57: aload 11
    //   59: astore 4
    //   61: aload 6
    //   63: astore 5
    //   65: invokestatic 365	io/fabric/sdk/android/Fabric:getLogger	()Lio/fabric/sdk/android/Logger;
    //   68: ldc_w 367
    //   71: new 381	java/lang/StringBuilder
    //   74: dup
    //   75: invokespecial 382	java/lang/StringBuilder:<init>	()V
    //   78: ldc_w 581
    //   81: invokevirtual 388	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: aload_3
    //   85: invokevirtual 584	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   88: ldc_w 586
    //   91: invokevirtual 388	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: aload_2
    //   95: invokevirtual 589	java/lang/Thread:getName	()Ljava/lang/String;
    //   98: invokevirtual 388	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   101: invokevirtual 391	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   104: invokeinterface 375 3 0
    //   109: aload 11
    //   111: astore 4
    //   113: aload 6
    //   115: astore 5
    //   117: aload_0
    //   118: getfield 229	com/crashlytics/android/core/CrashlyticsController:eventCounter	Ljava/util/concurrent/atomic/AtomicInteger;
    //   121: invokevirtual 593	java/util/concurrent/atomic/AtomicInteger:getAndIncrement	()I
    //   124: invokestatic 597	io/fabric/sdk/android/services/common/CommonUtils:padWithZerosToMaxIntWidth	(I)Ljava/lang/String;
    //   127: astore 13
    //   129: aload 11
    //   131: astore 4
    //   133: aload 6
    //   135: astore 5
    //   137: new 381	java/lang/StringBuilder
    //   140: dup
    //   141: invokespecial 382	java/lang/StringBuilder:<init>	()V
    //   144: aload 12
    //   146: invokevirtual 388	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   149: ldc -120
    //   151: invokevirtual 388	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   154: aload 13
    //   156: invokevirtual 388	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   159: invokevirtual 391	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   162: astore 13
    //   164: aload 11
    //   166: astore 4
    //   168: aload 6
    //   170: astore 5
    //   172: new 399	com/crashlytics/android/core/ClsFileOutputStream
    //   175: dup
    //   176: aload_0
    //   177: invokevirtual 538	com/crashlytics/android/core/CrashlyticsController:getFilesDir	()Ljava/io/File;
    //   180: aload 13
    //   182: invokespecial 541	com/crashlytics/android/core/ClsFileOutputStream:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   185: astore 6
    //   187: aload 10
    //   189: astore 4
    //   191: aload 9
    //   193: astore 5
    //   195: aload 6
    //   197: invokestatic 545	com/crashlytics/android/core/CodedOutputStream:newInstance	(Ljava/io/OutputStream;)Lcom/crashlytics/android/core/CodedOutputStream;
    //   200: astore 7
    //   202: aload 7
    //   204: astore 4
    //   206: aload 7
    //   208: astore 5
    //   210: aload_0
    //   211: aload 7
    //   213: aload_1
    //   214: aload_2
    //   215: aload_3
    //   216: ldc 83
    //   218: iconst_0
    //   219: invokespecial 601	com/crashlytics/android/core/CrashlyticsController:writeSessionEvent	(Lcom/crashlytics/android/core/CodedOutputStream;Ljava/util/Date;Ljava/lang/Thread;Ljava/lang/Throwable;Ljava/lang/String;Z)V
    //   222: aload 7
    //   224: ldc_w 603
    //   227: invokestatic 495	io/fabric/sdk/android/services/common/CommonUtils:flushOrLog	(Ljava/io/Flushable;Ljava/lang/String;)V
    //   230: aload 6
    //   232: ldc_w 605
    //   235: invokestatic 501	io/fabric/sdk/android/services/common/CommonUtils:closeOrLog	(Ljava/io/Closeable;Ljava/lang/String;)V
    //   238: aload_0
    //   239: aload 12
    //   241: bipush 64
    //   243: invokespecial 609	com/crashlytics/android/core/CrashlyticsController:trimSessionEventFiles	(Ljava/lang/String;I)V
    //   246: return
    //   247: astore_1
    //   248: invokestatic 365	io/fabric/sdk/android/Fabric:getLogger	()Lio/fabric/sdk/android/Logger;
    //   251: ldc_w 367
    //   254: ldc_w 611
    //   257: aload_1
    //   258: invokeinterface 408 4 0
    //   263: return
    //   264: astore_3
    //   265: aload 8
    //   267: astore_2
    //   268: aload 7
    //   270: astore_1
    //   271: aload_1
    //   272: astore 4
    //   274: aload_2
    //   275: astore 5
    //   277: invokestatic 365	io/fabric/sdk/android/Fabric:getLogger	()Lio/fabric/sdk/android/Logger;
    //   280: ldc_w 367
    //   283: ldc_w 613
    //   286: aload_3
    //   287: invokeinterface 408 4 0
    //   292: aload_1
    //   293: ldc_w 603
    //   296: invokestatic 495	io/fabric/sdk/android/services/common/CommonUtils:flushOrLog	(Ljava/io/Flushable;Ljava/lang/String;)V
    //   299: aload_2
    //   300: ldc_w 605
    //   303: invokestatic 501	io/fabric/sdk/android/services/common/CommonUtils:closeOrLog	(Ljava/io/Closeable;Ljava/lang/String;)V
    //   306: goto -68 -> 238
    //   309: astore_1
    //   310: aload 4
    //   312: ldc_w 603
    //   315: invokestatic 495	io/fabric/sdk/android/services/common/CommonUtils:flushOrLog	(Ljava/io/Flushable;Ljava/lang/String;)V
    //   318: aload 5
    //   320: ldc_w 605
    //   323: invokestatic 501	io/fabric/sdk/android/services/common/CommonUtils:closeOrLog	(Ljava/io/Closeable;Ljava/lang/String;)V
    //   326: aload_1
    //   327: athrow
    //   328: astore_1
    //   329: aload 6
    //   331: astore 5
    //   333: goto -23 -> 310
    //   336: astore_3
    //   337: aload 6
    //   339: astore_2
    //   340: aload 5
    //   342: astore_1
    //   343: goto -72 -> 271
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	346	0	this	CrashlyticsController
    //   0	346	1	paramDate	Date
    //   0	346	2	paramThread	Thread
    //   0	346	3	paramThrowable	Throwable
    //   59	252	4	localObject1	Object
    //   63	278	5	localObject2	Object
    //   40	298	6	localClsFileOutputStream	ClsFileOutputStream
    //   55	214	7	localCodedOutputStream	CodedOutputStream
    //   43	223	8	localObject3	Object
    //   52	140	9	localObject4	Object
    //   49	139	10	localObject5	Object
    //   46	119	11	localObject6	Object
    //   4	236	12	str1	String
    //   127	54	13	str2	String
    // Exception table:
    //   from	to	target	type
    //   238	246	247	java/lang/Exception
    //   65	109	264	java/lang/Exception
    //   117	129	264	java/lang/Exception
    //   137	164	264	java/lang/Exception
    //   172	187	264	java/lang/Exception
    //   65	109	309	finally
    //   117	129	309	finally
    //   137	164	309	finally
    //   172	187	309	finally
    //   277	292	309	finally
    //   195	202	328	finally
    //   210	222	328	finally
    //   195	202	336	java/lang/Exception
    //   210	222	336	java/lang/Exception
  }
  
  private File[] ensureFileArrayNotNull(File[] paramArrayOfFile)
  {
    File[] arrayOfFile = paramArrayOfFile;
    if (paramArrayOfFile == null) {
      arrayOfFile = new File[0];
    }
    return arrayOfFile;
  }
  
  private CreateReportSpiCall getCreateReportSpiCall(String paramString)
  {
    String str = CommonUtils.getStringsFileValue(this.crashlyticsCore.getContext(), "com.crashlytics.ApiEndpoint");
    return new DefaultCreateReportSpiCall(this.crashlyticsCore, str, paramString, this.httpRequestFactory);
  }
  
  private String getCurrentSessionId()
  {
    File[] arrayOfFile = listSortedSessionBeginFiles();
    if (arrayOfFile.length > 0) {
      return getSessionIdFromSessionFile(arrayOfFile[0]);
    }
    return null;
  }
  
  private String getPreviousSessionId()
  {
    File[] arrayOfFile = listSortedSessionBeginFiles();
    if (arrayOfFile.length > 1) {
      return getSessionIdFromSessionFile(arrayOfFile[1]);
    }
    return null;
  }
  
  static String getSessionIdFromSessionFile(File paramFile)
  {
    return paramFile.getName().substring(0, 35);
  }
  
  private File[] getTrimmedNonFatalFiles(String paramString, File[] paramArrayOfFile, int paramInt)
  {
    File[] arrayOfFile = paramArrayOfFile;
    if (paramArrayOfFile.length > paramInt)
    {
      Fabric.getLogger().d("CrashlyticsCore", String.format(Locale.US, "Trimming down to %d logged exceptions.", new Object[] { Integer.valueOf(paramInt) }));
      trimSessionEventFiles(paramString, paramInt);
      arrayOfFile = listFilesMatching(new FileNameContainsFilter(paramString + "SessionEvent"));
    }
    return arrayOfFile;
  }
  
  private UserMetaData getUserMetaData(String paramString)
  {
    if (isHandlingException()) {
      return new UserMetaData(this.crashlyticsCore.getUserIdentifier(), this.crashlyticsCore.getUserName(), this.crashlyticsCore.getUserEmail());
    }
    return new MetaDataStore(getFilesDir()).readUserData(paramString);
  }
  
  private File[] listFiles(File paramFile)
  {
    return ensureFileArrayNotNull(paramFile.listFiles());
  }
  
  private File[] listFilesMatching(File paramFile, FilenameFilter paramFilenameFilter)
  {
    return ensureFileArrayNotNull(paramFile.listFiles(paramFilenameFilter));
  }
  
  private File[] listFilesMatching(FilenameFilter paramFilenameFilter)
  {
    return listFilesMatching(getFilesDir(), paramFilenameFilter);
  }
  
  private File[] listSessionPartFilesFor(String paramString)
  {
    return listFilesMatching(new SessionPartFileFilter(paramString));
  }
  
  private File[] listSortedSessionBeginFiles()
  {
    File[] arrayOfFile = listSessionBeginFiles();
    Arrays.sort(arrayOfFile, LARGEST_FILE_NAME_FIRST);
    return arrayOfFile;
  }
  
  private static void recordFatalExceptionAnswersEvent(String paramString1, String paramString2)
  {
    Answers localAnswers = (Answers)Fabric.getKit(Answers.class);
    if (localAnswers == null)
    {
      Fabric.getLogger().d("CrashlyticsCore", "Answers is not available");
      return;
    }
    localAnswers.onException(new Crash.FatalException(paramString1, paramString2));
  }
  
  private static void recordLoggedExceptionAnswersEvent(String paramString1, String paramString2)
  {
    Answers localAnswers = (Answers)Fabric.getKit(Answers.class);
    if (localAnswers == null)
    {
      Fabric.getLogger().d("CrashlyticsCore", "Answers is not available");
      return;
    }
    localAnswers.onException(new Crash.LoggedException(paramString1, paramString2));
  }
  
  private void retainSessions(File[] paramArrayOfFile, Set<String> paramSet)
  {
    int j = paramArrayOfFile.length;
    int i = 0;
    for (;;)
    {
      File localFile;
      String str;
      Matcher localMatcher;
      if (i < j)
      {
        localFile = paramArrayOfFile[i];
        str = localFile.getName();
        localMatcher = SESSION_FILE_PATTERN.matcher(str);
        if (!localMatcher.matches())
        {
          Fabric.getLogger().d("CrashlyticsCore", "Deleting unknown file: " + str);
          localFile.delete();
        }
      }
      else
      {
        return;
      }
      if (!paramSet.contains(localMatcher.group(1)))
      {
        Fabric.getLogger().d("CrashlyticsCore", "Trimming session file: " + str);
        localFile.delete();
      }
      i += 1;
    }
  }
  
  private void sendSessionReports(SettingsData paramSettingsData)
  {
    if (paramSettingsData == null) {
      Fabric.getLogger().w("CrashlyticsCore", "Cannot send reports. Settings are unavailable.");
    }
    for (;;)
    {
      return;
      Context localContext = this.crashlyticsCore.getContext();
      paramSettingsData = getCreateReportSpiCall(paramSettingsData.appData.reportsUrl);
      paramSettingsData = new ReportUploader(this.appData.apiKey, paramSettingsData, this.reportFilesProvider, this.handlingExceptionCheck);
      File[] arrayOfFile = listCompleteSessionFiles();
      int j = arrayOfFile.length;
      int i = 0;
      while (i < j)
      {
        SessionReport localSessionReport = new SessionReport(arrayOfFile[i], SEND_AT_CRASHTIME_HEADER);
        this.backgroundWorker.submit(new SendReportRunnable(localContext, localSessionReport, paramSettingsData));
        i += 1;
      }
    }
  }
  
  private boolean shouldPromptUserBeforeSendingCrashReports(SettingsData paramSettingsData)
  {
    if (paramSettingsData == null) {}
    while ((!paramSettingsData.featuresData.promptEnabled) || (this.preferenceManager.shouldAlwaysSendReports())) {
      return false;
    }
    return true;
  }
  
  /* Error */
  private void synthesizeSessionFile(File paramFile1, String paramString, File[] paramArrayOfFile, File paramFile2)
  {
    // Byte code:
    //   0: aload 4
    //   2: ifnull +274 -> 276
    //   5: iconst_1
    //   6: istore 5
    //   8: iload 5
    //   10: ifeq +272 -> 282
    //   13: aload_0
    //   14: invokevirtual 797	com/crashlytics/android/core/CrashlyticsController:getFatalSessionFilesDir	()Ljava/io/File;
    //   17: astore 8
    //   19: aload 8
    //   21: invokevirtual 800	java/io/File:exists	()Z
    //   24: ifne +9 -> 33
    //   27: aload 8
    //   29: invokevirtual 803	java/io/File:mkdirs	()Z
    //   32: pop
    //   33: aconst_null
    //   34: astore 7
    //   36: aconst_null
    //   37: astore 10
    //   39: aconst_null
    //   40: astore 6
    //   42: aconst_null
    //   43: astore 12
    //   45: aconst_null
    //   46: astore 11
    //   48: aconst_null
    //   49: astore 9
    //   51: new 399	com/crashlytics/android/core/ClsFileOutputStream
    //   54: dup
    //   55: aload 8
    //   57: aload_2
    //   58: invokespecial 541	com/crashlytics/android/core/ClsFileOutputStream:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   61: astore 8
    //   63: aload 12
    //   65: astore 6
    //   67: aload 11
    //   69: astore 7
    //   71: aload 8
    //   73: invokestatic 545	com/crashlytics/android/core/CodedOutputStream:newInstance	(Ljava/io/OutputStream;)Lcom/crashlytics/android/core/CodedOutputStream;
    //   76: astore 9
    //   78: aload 9
    //   80: astore 6
    //   82: aload 9
    //   84: astore 7
    //   86: invokestatic 365	io/fabric/sdk/android/Fabric:getLogger	()Lio/fabric/sdk/android/Logger;
    //   89: ldc_w 367
    //   92: new 381	java/lang/StringBuilder
    //   95: dup
    //   96: invokespecial 382	java/lang/StringBuilder:<init>	()V
    //   99: ldc_w 805
    //   102: invokevirtual 388	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   105: aload_2
    //   106: invokevirtual 388	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: invokevirtual 391	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   112: invokeinterface 375 3 0
    //   117: aload 9
    //   119: astore 6
    //   121: aload 9
    //   123: astore 7
    //   125: aload 9
    //   127: aload_1
    //   128: invokestatic 809	com/crashlytics/android/core/CrashlyticsController:writeToCosFromFile	(Lcom/crashlytics/android/core/CodedOutputStream;Ljava/io/File;)V
    //   131: aload 9
    //   133: astore 6
    //   135: aload 9
    //   137: astore 7
    //   139: aload 9
    //   141: iconst_4
    //   142: new 457	java/util/Date
    //   145: dup
    //   146: invokespecial 458	java/util/Date:<init>	()V
    //   149: invokevirtual 813	java/util/Date:getTime	()J
    //   152: ldc2_w 814
    //   155: ldiv
    //   156: invokevirtual 819	com/crashlytics/android/core/CodedOutputStream:writeUInt64	(IJ)V
    //   159: aload 9
    //   161: astore 6
    //   163: aload 9
    //   165: astore 7
    //   167: aload 9
    //   169: iconst_5
    //   170: iload 5
    //   172: invokevirtual 823	com/crashlytics/android/core/CodedOutputStream:writeBool	(IZ)V
    //   175: aload 9
    //   177: astore 6
    //   179: aload 9
    //   181: astore 7
    //   183: aload 9
    //   185: bipush 11
    //   187: iconst_1
    //   188: invokevirtual 827	com/crashlytics/android/core/CodedOutputStream:writeUInt32	(II)V
    //   191: aload 9
    //   193: astore 6
    //   195: aload 9
    //   197: astore 7
    //   199: aload 9
    //   201: bipush 12
    //   203: iconst_3
    //   204: invokevirtual 830	com/crashlytics/android/core/CodedOutputStream:writeEnum	(II)V
    //   207: aload 9
    //   209: astore 6
    //   211: aload 9
    //   213: astore 7
    //   215: aload_0
    //   216: aload 9
    //   218: aload_2
    //   219: invokespecial 834	com/crashlytics/android/core/CrashlyticsController:writeInitialPartsTo	(Lcom/crashlytics/android/core/CodedOutputStream;Ljava/lang/String;)V
    //   222: aload 9
    //   224: astore 6
    //   226: aload 9
    //   228: astore 7
    //   230: aload 9
    //   232: aload_3
    //   233: aload_2
    //   234: invokestatic 838	com/crashlytics/android/core/CrashlyticsController:writeNonFatalEventsTo	(Lcom/crashlytics/android/core/CodedOutputStream;[Ljava/io/File;Ljava/lang/String;)V
    //   237: iload 5
    //   239: ifeq +18 -> 257
    //   242: aload 9
    //   244: astore 6
    //   246: aload 9
    //   248: astore 7
    //   250: aload 9
    //   252: aload 4
    //   254: invokestatic 809	com/crashlytics/android/core/CrashlyticsController:writeToCosFromFile	(Lcom/crashlytics/android/core/CodedOutputStream;Ljava/io/File;)V
    //   257: aload 9
    //   259: ldc_w 840
    //   262: invokestatic 495	io/fabric/sdk/android/services/common/CommonUtils:flushOrLog	(Ljava/io/Flushable;Ljava/lang/String;)V
    //   265: iconst_0
    //   266: ifeq +25 -> 291
    //   269: aload_0
    //   270: aload 8
    //   272: invokespecial 842	com/crashlytics/android/core/CrashlyticsController:closeWithoutRenamingOrLog	(Lcom/crashlytics/android/core/ClsFileOutputStream;)V
    //   275: return
    //   276: iconst_0
    //   277: istore 5
    //   279: goto -271 -> 8
    //   282: aload_0
    //   283: invokevirtual 845	com/crashlytics/android/core/CrashlyticsController:getNonFatalSessionFilesDir	()Ljava/io/File;
    //   286: astore 8
    //   288: goto -269 -> 19
    //   291: aload 8
    //   293: ldc_w 847
    //   296: invokestatic 501	io/fabric/sdk/android/services/common/CommonUtils:closeOrLog	(Ljava/io/Closeable;Ljava/lang/String;)V
    //   299: return
    //   300: astore 4
    //   302: aload 10
    //   304: astore_3
    //   305: aload 9
    //   307: astore_1
    //   308: aload_1
    //   309: astore 6
    //   311: aload_3
    //   312: astore 7
    //   314: invokestatic 365	io/fabric/sdk/android/Fabric:getLogger	()Lio/fabric/sdk/android/Logger;
    //   317: ldc_w 367
    //   320: new 381	java/lang/StringBuilder
    //   323: dup
    //   324: invokespecial 382	java/lang/StringBuilder:<init>	()V
    //   327: ldc_w 849
    //   330: invokevirtual 388	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   333: aload_2
    //   334: invokevirtual 388	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   337: invokevirtual 391	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   340: aload 4
    //   342: invokeinterface 408 4 0
    //   347: aload_1
    //   348: ldc_w 840
    //   351: invokestatic 495	io/fabric/sdk/android/services/common/CommonUtils:flushOrLog	(Ljava/io/Flushable;Ljava/lang/String;)V
    //   354: iconst_1
    //   355: ifeq +9 -> 364
    //   358: aload_0
    //   359: aload_3
    //   360: invokespecial 842	com/crashlytics/android/core/CrashlyticsController:closeWithoutRenamingOrLog	(Lcom/crashlytics/android/core/ClsFileOutputStream;)V
    //   363: return
    //   364: aload_3
    //   365: ldc_w 847
    //   368: invokestatic 501	io/fabric/sdk/android/services/common/CommonUtils:closeOrLog	(Ljava/io/Closeable;Ljava/lang/String;)V
    //   371: return
    //   372: astore_1
    //   373: aload 6
    //   375: ldc_w 840
    //   378: invokestatic 495	io/fabric/sdk/android/services/common/CommonUtils:flushOrLog	(Ljava/io/Flushable;Ljava/lang/String;)V
    //   381: iconst_0
    //   382: ifeq +11 -> 393
    //   385: aload_0
    //   386: aload 7
    //   388: invokespecial 842	com/crashlytics/android/core/CrashlyticsController:closeWithoutRenamingOrLog	(Lcom/crashlytics/android/core/ClsFileOutputStream;)V
    //   391: aload_1
    //   392: athrow
    //   393: aload 7
    //   395: ldc_w 847
    //   398: invokestatic 501	io/fabric/sdk/android/services/common/CommonUtils:closeOrLog	(Ljava/io/Closeable;Ljava/lang/String;)V
    //   401: goto -10 -> 391
    //   404: astore_1
    //   405: aload 8
    //   407: astore 7
    //   409: goto -36 -> 373
    //   412: astore 4
    //   414: aload 8
    //   416: astore_3
    //   417: aload 7
    //   419: astore_1
    //   420: goto -112 -> 308
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	423	0	this	CrashlyticsController
    //   0	423	1	paramFile1	File
    //   0	423	2	paramString	String
    //   0	423	3	paramArrayOfFile	File[]
    //   0	423	4	paramFile2	File
    //   6	272	5	bool	boolean
    //   40	334	6	localObject1	Object
    //   34	384	7	localObject2	Object
    //   17	398	8	localObject3	Object
    //   49	257	9	localCodedOutputStream	CodedOutputStream
    //   37	266	10	localObject4	Object
    //   46	22	11	localObject5	Object
    //   43	21	12	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   51	63	300	java/lang/Exception
    //   51	63	372	finally
    //   314	347	372	finally
    //   71	78	404	finally
    //   86	117	404	finally
    //   125	131	404	finally
    //   139	159	404	finally
    //   167	175	404	finally
    //   183	191	404	finally
    //   199	207	404	finally
    //   215	222	404	finally
    //   230	237	404	finally
    //   250	257	404	finally
    //   71	78	412	java/lang/Exception
    //   86	117	412	java/lang/Exception
    //   125	131	412	java/lang/Exception
    //   139	159	412	java/lang/Exception
    //   167	175	412	java/lang/Exception
    //   183	191	412	java/lang/Exception
    //   199	207	412	java/lang/Exception
    //   215	222	412	java/lang/Exception
    //   230	237	412	java/lang/Exception
    //   250	257	412	java/lang/Exception
  }
  
  private void trimInvalidSessionFiles()
  {
    File localFile = getInvalidFilesDir();
    if (!localFile.exists()) {
      return;
    }
    File[] arrayOfFile = listFilesMatching(localFile, new InvalidPartFileFilter());
    Arrays.sort(arrayOfFile, Collections.reverseOrder());
    HashSet localHashSet = new HashSet();
    int i = 0;
    while ((i < arrayOfFile.length) && (localHashSet.size() < 4))
    {
      localHashSet.add(getSessionIdFromSessionFile(arrayOfFile[i]));
      i += 1;
    }
    retainSessions(listFiles(localFile), localHashSet);
  }
  
  private void trimOpenSessions(int paramInt)
  {
    HashSet localHashSet = new HashSet();
    File[] arrayOfFile = listSortedSessionBeginFiles();
    int i = Math.min(paramInt, arrayOfFile.length);
    paramInt = 0;
    while (paramInt < i)
    {
      localHashSet.add(getSessionIdFromSessionFile(arrayOfFile[paramInt]));
      paramInt += 1;
    }
    this.logFileManager.discardOldLogFiles(localHashSet);
    retainSessions(listFilesMatching(new AnySessionPartFileFilter(null)), localHashSet);
  }
  
  private void trimSessionEventFiles(String paramString, int paramInt)
  {
    Utils.capFileCount(getFilesDir(), new FileNameContainsFilter(paramString + "SessionEvent"), paramInt, SMALLEST_FILE_NAME_FIRST);
  }
  
  /* Error */
  private void writeBeginSession(String paramString, Date paramDate)
    throws Exception
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 6
    //   6: aconst_null
    //   7: astore_3
    //   8: new 399	com/crashlytics/android/core/ClsFileOutputStream
    //   11: dup
    //   12: aload_0
    //   13: invokevirtual 538	com/crashlytics/android/core/CrashlyticsController:getFilesDir	()Ljava/io/File;
    //   16: new 381	java/lang/StringBuilder
    //   19: dup
    //   20: invokespecial 382	java/lang/StringBuilder:<init>	()V
    //   23: aload_1
    //   24: invokevirtual 388	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: ldc 119
    //   29: invokevirtual 388	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: invokevirtual 391	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   35: invokespecial 541	com/crashlytics/android/core/ClsFileOutputStream:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   38: astore 4
    //   40: aload 6
    //   42: astore_3
    //   43: aload 4
    //   45: invokestatic 545	com/crashlytics/android/core/CodedOutputStream:newInstance	(Ljava/io/OutputStream;)Lcom/crashlytics/android/core/CodedOutputStream;
    //   48: astore 5
    //   50: aload 5
    //   52: astore_3
    //   53: aload 5
    //   55: aload_1
    //   56: getstatic 507	java/util/Locale:US	Ljava/util/Locale;
    //   59: ldc 89
    //   61: iconst_1
    //   62: anewarray 4	java/lang/Object
    //   65: dup
    //   66: iconst_0
    //   67: aload_0
    //   68: getfield 231	com/crashlytics/android/core/CrashlyticsController:crashlyticsCore	Lcom/crashlytics/android/core/CrashlyticsCore;
    //   71: invokevirtual 893	com/crashlytics/android/core/CrashlyticsCore:getVersion	()Ljava/lang/String;
    //   74: aastore
    //   75: invokestatic 527	java/lang/String:format	(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   78: aload_2
    //   79: invokevirtual 813	java/util/Date:getTime	()J
    //   82: ldc2_w 814
    //   85: ldiv
    //   86: invokestatic 898	com/crashlytics/android/core/SessionProtobufHelper:writeBeginSession	(Lcom/crashlytics/android/core/CodedOutputStream;Ljava/lang/String;Ljava/lang/String;J)V
    //   89: aload 5
    //   91: ldc_w 489
    //   94: invokestatic 495	io/fabric/sdk/android/services/common/CommonUtils:flushOrLog	(Ljava/io/Flushable;Ljava/lang/String;)V
    //   97: aload 4
    //   99: ldc_w 900
    //   102: invokestatic 501	io/fabric/sdk/android/services/common/CommonUtils:closeOrLog	(Ljava/io/Closeable;Ljava/lang/String;)V
    //   105: return
    //   106: astore_2
    //   107: aload 5
    //   109: astore_1
    //   110: aload_3
    //   111: ldc_w 489
    //   114: invokestatic 495	io/fabric/sdk/android/services/common/CommonUtils:flushOrLog	(Ljava/io/Flushable;Ljava/lang/String;)V
    //   117: aload_1
    //   118: ldc_w 900
    //   121: invokestatic 501	io/fabric/sdk/android/services/common/CommonUtils:closeOrLog	(Ljava/io/Closeable;Ljava/lang/String;)V
    //   124: aload_2
    //   125: athrow
    //   126: astore_2
    //   127: aload 4
    //   129: astore_1
    //   130: goto -20 -> 110
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	133	0	this	CrashlyticsController
    //   0	133	1	paramString	String
    //   0	133	2	paramDate	Date
    //   7	104	3	localObject1	Object
    //   38	90	4	localClsFileOutputStream	ClsFileOutputStream
    //   1	107	5	localCodedOutputStream	CodedOutputStream
    //   4	37	6	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   8	40	106	finally
    //   43	50	126	finally
    //   53	89	126	finally
  }
  
  /* Error */
  private void writeFatal(Date paramDate, Thread paramThread, Throwable paramThrowable)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore 8
    //   6: aconst_null
    //   7: astore 11
    //   9: aconst_null
    //   10: astore 10
    //   12: aconst_null
    //   13: astore 9
    //   15: aconst_null
    //   16: astore 7
    //   18: aload 11
    //   20: astore 4
    //   22: aload 6
    //   24: astore 5
    //   26: aload_0
    //   27: invokespecial 303	com/crashlytics/android/core/CrashlyticsController:getCurrentSessionId	()Ljava/lang/String;
    //   30: astore 12
    //   32: aload 12
    //   34: ifnonnull +41 -> 75
    //   37: aload 11
    //   39: astore 4
    //   41: aload 6
    //   43: astore 5
    //   45: invokestatic 365	io/fabric/sdk/android/Fabric:getLogger	()Lio/fabric/sdk/android/Logger;
    //   48: ldc_w 367
    //   51: ldc_w 902
    //   54: aconst_null
    //   55: invokeinterface 408 4 0
    //   60: aconst_null
    //   61: ldc_w 489
    //   64: invokestatic 495	io/fabric/sdk/android/services/common/CommonUtils:flushOrLog	(Ljava/io/Flushable;Ljava/lang/String;)V
    //   67: aconst_null
    //   68: ldc_w 497
    //   71: invokestatic 501	io/fabric/sdk/android/services/common/CommonUtils:closeOrLog	(Ljava/io/Closeable;Ljava/lang/String;)V
    //   74: return
    //   75: aload 11
    //   77: astore 4
    //   79: aload 6
    //   81: astore 5
    //   83: aload 12
    //   85: aload_3
    //   86: invokevirtual 571	java/lang/Object:getClass	()Ljava/lang/Class;
    //   89: invokevirtual 576	java/lang/Class:getName	()Ljava/lang/String;
    //   92: invokestatic 530	com/crashlytics/android/core/CrashlyticsController:recordFatalExceptionAnswersEvent	(Ljava/lang/String;Ljava/lang/String;)V
    //   95: aload 11
    //   97: astore 4
    //   99: aload 6
    //   101: astore 5
    //   103: new 399	com/crashlytics/android/core/ClsFileOutputStream
    //   106: dup
    //   107: aload_0
    //   108: invokevirtual 538	com/crashlytics/android/core/CrashlyticsController:getFilesDir	()Ljava/io/File;
    //   111: new 381	java/lang/StringBuilder
    //   114: dup
    //   115: invokespecial 382	java/lang/StringBuilder:<init>	()V
    //   118: aload 12
    //   120: invokevirtual 388	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   123: ldc -128
    //   125: invokevirtual 388	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   128: invokevirtual 391	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   131: invokespecial 541	com/crashlytics/android/core/ClsFileOutputStream:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   134: astore 6
    //   136: aload 10
    //   138: astore 4
    //   140: aload 9
    //   142: astore 5
    //   144: aload 6
    //   146: invokestatic 545	com/crashlytics/android/core/CodedOutputStream:newInstance	(Ljava/io/OutputStream;)Lcom/crashlytics/android/core/CodedOutputStream;
    //   149: astore 7
    //   151: aload 7
    //   153: astore 4
    //   155: aload 7
    //   157: astore 5
    //   159: aload_0
    //   160: aload 7
    //   162: aload_1
    //   163: aload_2
    //   164: aload_3
    //   165: ldc 80
    //   167: iconst_1
    //   168: invokespecial 601	com/crashlytics/android/core/CrashlyticsController:writeSessionEvent	(Lcom/crashlytics/android/core/CodedOutputStream;Ljava/util/Date;Ljava/lang/Thread;Ljava/lang/Throwable;Ljava/lang/String;Z)V
    //   171: aload 7
    //   173: ldc_w 489
    //   176: invokestatic 495	io/fabric/sdk/android/services/common/CommonUtils:flushOrLog	(Ljava/io/Flushable;Ljava/lang/String;)V
    //   179: aload 6
    //   181: ldc_w 497
    //   184: invokestatic 501	io/fabric/sdk/android/services/common/CommonUtils:closeOrLog	(Ljava/io/Closeable;Ljava/lang/String;)V
    //   187: return
    //   188: astore_3
    //   189: aload 8
    //   191: astore_2
    //   192: aload 7
    //   194: astore_1
    //   195: aload_1
    //   196: astore 4
    //   198: aload_2
    //   199: astore 5
    //   201: invokestatic 365	io/fabric/sdk/android/Fabric:getLogger	()Lio/fabric/sdk/android/Logger;
    //   204: ldc_w 367
    //   207: ldc_w 904
    //   210: aload_3
    //   211: invokeinterface 408 4 0
    //   216: aload_1
    //   217: ldc_w 489
    //   220: invokestatic 495	io/fabric/sdk/android/services/common/CommonUtils:flushOrLog	(Ljava/io/Flushable;Ljava/lang/String;)V
    //   223: aload_2
    //   224: ldc_w 497
    //   227: invokestatic 501	io/fabric/sdk/android/services/common/CommonUtils:closeOrLog	(Ljava/io/Closeable;Ljava/lang/String;)V
    //   230: return
    //   231: astore_1
    //   232: aload 4
    //   234: ldc_w 489
    //   237: invokestatic 495	io/fabric/sdk/android/services/common/CommonUtils:flushOrLog	(Ljava/io/Flushable;Ljava/lang/String;)V
    //   240: aload 5
    //   242: ldc_w 497
    //   245: invokestatic 501	io/fabric/sdk/android/services/common/CommonUtils:closeOrLog	(Ljava/io/Closeable;Ljava/lang/String;)V
    //   248: aload_1
    //   249: athrow
    //   250: astore_1
    //   251: aload 6
    //   253: astore 5
    //   255: goto -23 -> 232
    //   258: astore_3
    //   259: aload 6
    //   261: astore_2
    //   262: aload 5
    //   264: astore_1
    //   265: goto -70 -> 195
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	268	0	this	CrashlyticsController
    //   0	268	1	paramDate	Date
    //   0	268	2	paramThread	Thread
    //   0	268	3	paramThrowable	Throwable
    //   20	213	4	localObject1	Object
    //   24	239	5	localObject2	Object
    //   1	259	6	localClsFileOutputStream	ClsFileOutputStream
    //   16	177	7	localCodedOutputStream	CodedOutputStream
    //   4	186	8	localObject3	Object
    //   13	128	9	localObject4	Object
    //   10	127	10	localObject5	Object
    //   7	89	11	localObject6	Object
    //   30	89	12	str	String
    // Exception table:
    //   from	to	target	type
    //   26	32	188	java/lang/Exception
    //   45	60	188	java/lang/Exception
    //   83	95	188	java/lang/Exception
    //   103	136	188	java/lang/Exception
    //   26	32	231	finally
    //   45	60	231	finally
    //   83	95	231	finally
    //   103	136	231	finally
    //   201	216	231	finally
    //   144	151	250	finally
    //   159	171	250	finally
    //   144	151	258	java/lang/Exception
    //   159	171	258	java/lang/Exception
  }
  
  private void writeInitialPartsTo(CodedOutputStream paramCodedOutputStream, String paramString)
    throws IOException
  {
    String[] arrayOfString = INITIAL_SESSION_PART_TAGS;
    int j = arrayOfString.length;
    int i = 0;
    if (i < j)
    {
      String str = arrayOfString[i];
      File[] arrayOfFile = listFilesMatching(new FileNameContainsFilter(paramString + str));
      if (arrayOfFile.length == 0) {
        Fabric.getLogger().e("CrashlyticsCore", "Can't find " + str + " data for session ID " + paramString, null);
      }
      for (;;)
      {
        i += 1;
        break;
        Fabric.getLogger().d("CrashlyticsCore", "Collecting " + str + " data for session ID " + paramString);
        writeToCosFromFile(paramCodedOutputStream, arrayOfFile[0]);
      }
    }
  }
  
  private static void writeNonFatalEventsTo(CodedOutputStream paramCodedOutputStream, File[] paramArrayOfFile, String paramString)
  {
    int i = 0;
    Arrays.sort(paramArrayOfFile, CommonUtils.FILE_MODIFIED_COMPARATOR);
    int j = paramArrayOfFile.length;
    for (;;)
    {
      if (i < j)
      {
        File localFile = paramArrayOfFile[i];
        try
        {
          Fabric.getLogger().d("CrashlyticsCore", String.format(Locale.US, "Found Non Fatal for session ID %s in %s ", new Object[] { paramString, localFile.getName() }));
          writeToCosFromFile(paramCodedOutputStream, localFile);
          i += 1;
        }
        catch (Exception localException)
        {
          for (;;)
          {
            Fabric.getLogger().e("CrashlyticsCore", "Error writting non-fatal to session.", localException);
          }
        }
      }
    }
  }
  
  private void writeSessionApp(String paramString)
    throws Exception
  {
    Object localObject1 = null;
    String str2 = null;
    String str1 = null;
    try
    {
      localObject3 = new ClsFileOutputStream(getFilesDir(), paramString + "SessionApp");
      paramString = str2;
      String str3;
      String str4;
      int i;
      CommonUtils.flushOrLog(paramString, "Failed to flush to session app file.");
    }
    finally
    {
      try
      {
        localObject1 = CodedOutputStream.newInstance((OutputStream)localObject3);
        paramString = (String)localObject1;
        str1 = this.idManager.getAppIdentifier();
        paramString = (String)localObject1;
        str2 = this.appData.versionCode;
        paramString = (String)localObject1;
        str3 = this.appData.versionName;
        paramString = (String)localObject1;
        str4 = this.idManager.getAppInstallIdentifier();
        paramString = (String)localObject1;
        i = DeliveryMechanism.determineFrom(this.appData.installerPackageName).getId();
        paramString = (String)localObject1;
        SessionProtobufHelper.writeSessionApp((CodedOutputStream)localObject1, str1, this.appData.apiKey, str2, str3, str4, i, this.unityVersion);
        CommonUtils.flushOrLog((Flushable)localObject1, "Failed to flush to session app file.");
        CommonUtils.closeOrLog((Closeable)localObject3, "Failed to close session app file.");
        return;
      }
      finally
      {
        Object localObject3;
        for (;;) {}
      }
      paramString = finally;
      localObject3 = localObject1;
      localObject1 = paramString;
      paramString = str1;
    }
    CommonUtils.closeOrLog((Closeable)localObject3, "Failed to close session app file.");
    throw ((Throwable)localObject1);
  }
  
  private void writeSessionDevice(String paramString)
    throws Exception
  {
    Object localObject1 = null;
    String str = null;
    Context localContext = null;
    try
    {
      localObject3 = new ClsFileOutputStream(getFilesDir(), paramString + "SessionDevice");
      paramString = str;
      Object localObject4;
      int i;
      int j;
      long l1;
      long l2;
      long l3;
      boolean bool;
      int k;
      CommonUtils.flushOrLog(paramString, "Failed to flush session device info.");
    }
    finally
    {
      try
      {
        localObject1 = CodedOutputStream.newInstance((OutputStream)localObject3);
        paramString = (String)localObject1;
        localContext = this.crashlyticsCore.getContext();
        paramString = (String)localObject1;
        localObject4 = new StatFs(Environment.getDataDirectory().getPath());
        paramString = (String)localObject1;
        str = this.idManager.getDeviceUUID();
        paramString = (String)localObject1;
        i = CommonUtils.getCpuArchitectureInt();
        paramString = (String)localObject1;
        j = Runtime.getRuntime().availableProcessors();
        paramString = (String)localObject1;
        l1 = CommonUtils.getTotalRamInBytes();
        paramString = (String)localObject1;
        l2 = ((StatFs)localObject4).getBlockCount();
        paramString = (String)localObject1;
        l3 = ((StatFs)localObject4).getBlockSize();
        paramString = (String)localObject1;
        bool = CommonUtils.isEmulator(localContext);
        paramString = (String)localObject1;
        localObject4 = this.idManager.getDeviceIdentifiers();
        paramString = (String)localObject1;
        k = CommonUtils.getDeviceState(localContext);
        paramString = (String)localObject1;
        SessionProtobufHelper.writeSessionDevice((CodedOutputStream)localObject1, str, i, Build.MODEL, j, l1, l2 * l3, bool, (Map)localObject4, k, Build.MANUFACTURER, Build.PRODUCT);
        CommonUtils.flushOrLog((Flushable)localObject1, "Failed to flush session device info.");
        CommonUtils.closeOrLog((Closeable)localObject3, "Failed to close session device file.");
        return;
      }
      finally
      {
        Object localObject3;
        for (;;) {}
      }
      paramString = finally;
      localObject3 = localObject1;
      localObject1 = paramString;
      paramString = localContext;
    }
    CommonUtils.closeOrLog((Closeable)localObject3, "Failed to close session device file.");
    throw ((Throwable)localObject1);
  }
  
  private void writeSessionEvent(CodedOutputStream paramCodedOutputStream, Date paramDate, Thread paramThread, Throwable paramThrowable, String paramString, boolean paramBoolean)
    throws Exception
  {
    TrimmedThrowableData localTrimmedThrowableData = new TrimmedThrowableData(paramThrowable, this.stackTraceTrimmingStrategy);
    Object localObject = this.crashlyticsCore.getContext();
    long l1 = paramDate.getTime() / 1000L;
    Float localFloat = CommonUtils.getBatteryLevel((Context)localObject);
    int j = CommonUtils.getBatteryVelocity((Context)localObject, this.devicePowerStateListener.isPowerConnected());
    boolean bool = CommonUtils.getProximitySensorEnabled((Context)localObject);
    int k = ((Context)localObject).getResources().getConfiguration().orientation;
    long l2 = CommonUtils.getTotalRamInBytes();
    long l3 = CommonUtils.calculateFreeRamInBytes((Context)localObject);
    long l4 = CommonUtils.calculateUsedDiskSpaceInBytes(Environment.getDataDirectory().getPath());
    ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = CommonUtils.getAppProcessInfo(((Context)localObject).getPackageName(), (Context)localObject);
    LinkedList localLinkedList = new LinkedList();
    StackTraceElement[] arrayOfStackTraceElement = localTrimmedThrowableData.stacktrace;
    String str1 = this.appData.buildId;
    String str2 = this.idManager.getAppIdentifier();
    if (paramBoolean)
    {
      paramThrowable = Thread.getAllStackTraces();
      paramDate = new Thread[paramThrowable.size()];
      int i = 0;
      Iterator localIterator = paramThrowable.entrySet().iterator();
      for (;;)
      {
        paramThrowable = paramDate;
        if (!localIterator.hasNext()) {
          break;
        }
        paramThrowable = (Map.Entry)localIterator.next();
        paramDate[i] = ((Thread)paramThrowable.getKey());
        localLinkedList.add(this.stackTraceTrimmingStrategy.getTrimmedStackTrace((StackTraceElement[])paramThrowable.getValue()));
        i += 1;
      }
    }
    paramThrowable = new Thread[0];
    if (!CommonUtils.getBooleanResourceValue((Context)localObject, "com.crashlytics.CollectCustomKeys", true)) {
      paramDate = new TreeMap();
    }
    for (;;)
    {
      SessionProtobufHelper.writeSessionEvent(paramCodedOutputStream, l1, paramString, localTrimmedThrowableData, paramThread, arrayOfStackTraceElement, paramThrowable, localLinkedList, paramDate, this.logFileManager, localRunningAppProcessInfo, k, str2, str1, localFloat, j, bool, l2 - l3, l4);
      return;
      localObject = this.crashlyticsCore.getAttributes();
      paramDate = (Date)localObject;
      if (localObject != null)
      {
        paramDate = (Date)localObject;
        if (((Map)localObject).size() > 1) {
          paramDate = new TreeMap((Map)localObject);
        }
      }
    }
  }
  
  /* Error */
  private void writeSessionOS(String paramString)
    throws Exception
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore 5
    //   5: aconst_null
    //   6: astore 4
    //   8: new 399	com/crashlytics/android/core/ClsFileOutputStream
    //   11: dup
    //   12: aload_0
    //   13: invokevirtual 538	com/crashlytics/android/core/CrashlyticsController:getFilesDir	()Ljava/io/File;
    //   16: new 381	java/lang/StringBuilder
    //   19: dup
    //   20: invokespecial 382	java/lang/StringBuilder:<init>	()V
    //   23: aload_1
    //   24: invokevirtual 388	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: ldc -117
    //   29: invokevirtual 388	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: invokevirtual 391	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   35: invokespecial 541	com/crashlytics/android/core/ClsFileOutputStream:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   38: astore_3
    //   39: aload 5
    //   41: astore_1
    //   42: aload_3
    //   43: invokestatic 545	com/crashlytics/android/core/CodedOutputStream:newInstance	(Ljava/io/OutputStream;)Lcom/crashlytics/android/core/CodedOutputStream;
    //   46: astore_2
    //   47: aload_2
    //   48: astore_1
    //   49: aload_2
    //   50: aload_0
    //   51: getfield 231	com/crashlytics/android/core/CrashlyticsController:crashlyticsCore	Lcom/crashlytics/android/core/CrashlyticsCore;
    //   54: invokevirtual 257	com/crashlytics/android/core/CrashlyticsCore:getContext	()Landroid/content/Context;
    //   57: invokestatic 1134	io/fabric/sdk/android/services/common/CommonUtils:isRooted	(Landroid/content/Context;)Z
    //   60: invokestatic 1137	com/crashlytics/android/core/SessionProtobufHelper:writeSessionOS	(Lcom/crashlytics/android/core/CodedOutputStream;Z)V
    //   63: aload_2
    //   64: ldc_w 1139
    //   67: invokestatic 495	io/fabric/sdk/android/services/common/CommonUtils:flushOrLog	(Ljava/io/Flushable;Ljava/lang/String;)V
    //   70: aload_3
    //   71: ldc_w 1141
    //   74: invokestatic 501	io/fabric/sdk/android/services/common/CommonUtils:closeOrLog	(Ljava/io/Closeable;Ljava/lang/String;)V
    //   77: return
    //   78: astore_3
    //   79: aload 4
    //   81: astore_1
    //   82: aload_1
    //   83: ldc_w 1139
    //   86: invokestatic 495	io/fabric/sdk/android/services/common/CommonUtils:flushOrLog	(Ljava/io/Flushable;Ljava/lang/String;)V
    //   89: aload_2
    //   90: ldc_w 1141
    //   93: invokestatic 501	io/fabric/sdk/android/services/common/CommonUtils:closeOrLog	(Ljava/io/Closeable;Ljava/lang/String;)V
    //   96: aload_3
    //   97: athrow
    //   98: astore 4
    //   100: aload_3
    //   101: astore_2
    //   102: aload 4
    //   104: astore_3
    //   105: goto -23 -> 82
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	108	0	this	CrashlyticsController
    //   0	108	1	paramString	String
    //   1	101	2	localObject1	Object
    //   38	33	3	localClsFileOutputStream	ClsFileOutputStream
    //   78	23	3	localObject2	Object
    //   104	1	3	localObject3	Object
    //   6	74	4	localObject4	Object
    //   98	5	4	localObject5	Object
    //   3	37	5	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   8	39	78	finally
    //   42	47	98	finally
    //   49	63	98	finally
  }
  
  private void writeSessionPartsToSessionFile(File paramFile, String paramString, int paramInt)
  {
    Fabric.getLogger().d("CrashlyticsCore", "Collecting session parts for ID " + paramString);
    Object localObject = listFilesMatching(new FileNameContainsFilter(paramString + "SessionCrash"));
    boolean bool1;
    boolean bool2;
    if ((localObject != null) && (localObject.length > 0))
    {
      bool1 = true;
      Fabric.getLogger().d("CrashlyticsCore", String.format(Locale.US, "Session %s has fatal exception: %s", new Object[] { paramString, Boolean.valueOf(bool1) }));
      File[] arrayOfFile = listFilesMatching(new FileNameContainsFilter(paramString + "SessionEvent"));
      if ((arrayOfFile == null) || (arrayOfFile.length <= 0)) {
        break label279;
      }
      bool2 = true;
      label159:
      Fabric.getLogger().d("CrashlyticsCore", String.format(Locale.US, "Session %s has non-fatal exceptions: %s", new Object[] { paramString, Boolean.valueOf(bool2) }));
      if ((!bool1) && (!bool2)) {
        break label291;
      }
      arrayOfFile = getTrimmedNonFatalFiles(paramString, arrayOfFile, paramInt);
      if (!bool1) {
        break label285;
      }
      localObject = localObject[0];
      label226:
      synthesizeSessionFile(paramFile, paramString, arrayOfFile, (File)localObject);
    }
    for (;;)
    {
      Fabric.getLogger().d("CrashlyticsCore", "Removing session part files for ID " + paramString);
      deleteSessionPartFilesFor(paramString);
      return;
      bool1 = false;
      break;
      label279:
      bool2 = false;
      break label159;
      label285:
      localObject = null;
      break label226;
      label291:
      Fabric.getLogger().d("CrashlyticsCore", "No events present for session ID " + paramString);
    }
  }
  
  /* Error */
  private void writeSessionUser(String paramString)
    throws Exception
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 6
    //   6: aconst_null
    //   7: astore_3
    //   8: new 399	com/crashlytics/android/core/ClsFileOutputStream
    //   11: dup
    //   12: aload_0
    //   13: invokevirtual 538	com/crashlytics/android/core/CrashlyticsController:getFilesDir	()Ljava/io/File;
    //   16: new 381	java/lang/StringBuilder
    //   19: dup
    //   20: invokespecial 382	java/lang/StringBuilder:<init>	()V
    //   23: aload_1
    //   24: invokevirtual 388	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: ldc -114
    //   29: invokevirtual 388	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: invokevirtual 391	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   35: invokespecial 541	com/crashlytics/android/core/ClsFileOutputStream:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   38: astore 4
    //   40: aload 6
    //   42: astore_3
    //   43: aload 4
    //   45: invokestatic 545	com/crashlytics/android/core/CodedOutputStream:newInstance	(Ljava/io/OutputStream;)Lcom/crashlytics/android/core/CodedOutputStream;
    //   48: astore 5
    //   50: aload 5
    //   52: astore_3
    //   53: aload_0
    //   54: aload_1
    //   55: invokespecial 1164	com/crashlytics/android/core/CrashlyticsController:getUserMetaData	(Ljava/lang/String;)Lcom/crashlytics/android/core/UserMetaData;
    //   58: astore_1
    //   59: aload 5
    //   61: astore_3
    //   62: aload_1
    //   63: invokevirtual 1167	com/crashlytics/android/core/UserMetaData:isEmpty	()Z
    //   66: istore_2
    //   67: iload_2
    //   68: ifeq +20 -> 88
    //   71: aload 5
    //   73: ldc_w 1169
    //   76: invokestatic 495	io/fabric/sdk/android/services/common/CommonUtils:flushOrLog	(Ljava/io/Flushable;Ljava/lang/String;)V
    //   79: aload 4
    //   81: ldc_w 1171
    //   84: invokestatic 501	io/fabric/sdk/android/services/common/CommonUtils:closeOrLog	(Ljava/io/Closeable;Ljava/lang/String;)V
    //   87: return
    //   88: aload 5
    //   90: astore_3
    //   91: aload 5
    //   93: aload_1
    //   94: getfield 1174	com/crashlytics/android/core/UserMetaData:id	Ljava/lang/String;
    //   97: aload_1
    //   98: getfield 1175	com/crashlytics/android/core/UserMetaData:name	Ljava/lang/String;
    //   101: aload_1
    //   102: getfield 1178	com/crashlytics/android/core/UserMetaData:email	Ljava/lang/String;
    //   105: invokestatic 1181	com/crashlytics/android/core/SessionProtobufHelper:writeSessionUser	(Lcom/crashlytics/android/core/CodedOutputStream;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   108: aload 5
    //   110: ldc_w 1169
    //   113: invokestatic 495	io/fabric/sdk/android/services/common/CommonUtils:flushOrLog	(Ljava/io/Flushable;Ljava/lang/String;)V
    //   116: aload 4
    //   118: ldc_w 1171
    //   121: invokestatic 501	io/fabric/sdk/android/services/common/CommonUtils:closeOrLog	(Ljava/io/Closeable;Ljava/lang/String;)V
    //   124: return
    //   125: astore 4
    //   127: aload 5
    //   129: astore_1
    //   130: aload_3
    //   131: ldc_w 1169
    //   134: invokestatic 495	io/fabric/sdk/android/services/common/CommonUtils:flushOrLog	(Ljava/io/Flushable;Ljava/lang/String;)V
    //   137: aload_1
    //   138: ldc_w 1171
    //   141: invokestatic 501	io/fabric/sdk/android/services/common/CommonUtils:closeOrLog	(Ljava/io/Closeable;Ljava/lang/String;)V
    //   144: aload 4
    //   146: athrow
    //   147: astore 5
    //   149: aload 4
    //   151: astore_1
    //   152: aload 5
    //   154: astore 4
    //   156: goto -26 -> 130
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	159	0	this	CrashlyticsController
    //   0	159	1	paramString	String
    //   66	2	2	bool	boolean
    //   7	124	3	localObject1	Object
    //   38	79	4	localClsFileOutputStream	ClsFileOutputStream
    //   125	25	4	localObject2	Object
    //   154	1	4	localObject3	Object
    //   1	127	5	localCodedOutputStream	CodedOutputStream
    //   147	6	5	localObject4	Object
    //   4	37	6	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   8	40	125	finally
    //   43	50	147	finally
    //   53	59	147	finally
    //   62	67	147	finally
    //   91	108	147	finally
  }
  
  private static void writeToCosFromFile(CodedOutputStream paramCodedOutputStream, File paramFile)
    throws IOException
  {
    if (!paramFile.exists())
    {
      Fabric.getLogger().e("CrashlyticsCore", "Tried to include a file that doesn't exist: " + paramFile.getName(), null);
      return;
    }
    Object localObject = null;
    try
    {
      localFileInputStream = new FileInputStream(paramFile);
      CommonUtils.closeOrLog(paramCodedOutputStream, "Failed to close file input stream.");
    }
    finally
    {
      try
      {
        copyToCodedOutputStream(localFileInputStream, paramCodedOutputStream, (int)paramFile.length());
        CommonUtils.closeOrLog(localFileInputStream, "Failed to close file input stream.");
        return;
      }
      finally
      {
        FileInputStream localFileInputStream;
        paramCodedOutputStream = localFileInputStream;
      }
      paramFile = finally;
      paramCodedOutputStream = (CodedOutputStream)localObject;
    }
    throw paramFile;
  }
  
  void cacheKeyData(final Map<String, String> paramMap)
  {
    this.backgroundWorker.submit(new Callable()
    {
      public Void call()
        throws Exception
      {
        String str = CrashlyticsController.this.getCurrentSessionId();
        new MetaDataStore(CrashlyticsController.this.getFilesDir()).writeKeyData(str, paramMap);
        return null;
      }
    });
  }
  
  void cacheUserData(final String paramString1, final String paramString2, final String paramString3)
  {
    this.backgroundWorker.submit(new Callable()
    {
      public Void call()
        throws Exception
      {
        String str = CrashlyticsController.this.getCurrentSessionId();
        new MetaDataStore(CrashlyticsController.this.getFilesDir()).writeUserData(str, new UserMetaData(paramString1, paramString2, paramString3));
        return null;
      }
    });
  }
  
  void cleanInvalidTempFiles()
  {
    this.backgroundWorker.submit(new Runnable()
    {
      public void run()
      {
        CrashlyticsController.this.doCleanInvalidTempFiles(CrashlyticsController.this.listFilesMatching(new CrashlyticsController.InvalidPartFileFilter()));
      }
    });
  }
  
  void doCleanInvalidTempFiles(File[] paramArrayOfFile)
  {
    int j = 0;
    final Object localObject = new HashSet();
    int k = paramArrayOfFile.length;
    int i = 0;
    File localFile;
    while (i < k)
    {
      localFile = paramArrayOfFile[i];
      Fabric.getLogger().d("CrashlyticsCore", "Found invalid session part file: " + localFile);
      ((Set)localObject).add(getSessionIdFromSessionFile(localFile));
      i += 1;
    }
    if (((Set)localObject).isEmpty()) {
      return;
    }
    paramArrayOfFile = getInvalidFilesDir();
    if (!paramArrayOfFile.exists()) {
      paramArrayOfFile.mkdir();
    }
    localObject = listFilesMatching(new FilenameFilter()
    {
      public boolean accept(File paramAnonymousFile, String paramAnonymousString)
      {
        if (paramAnonymousString.length() < 35) {
          return false;
        }
        return localObject.contains(paramAnonymousString.substring(0, 35));
      }
    });
    k = localObject.length;
    i = j;
    while (i < k)
    {
      localFile = localObject[i];
      Fabric.getLogger().d("CrashlyticsCore", "Moving session file: " + localFile);
      if (!localFile.renameTo(new File(paramArrayOfFile, localFile.getName())))
      {
        Fabric.getLogger().d("CrashlyticsCore", "Could not move session file. Deleting " + localFile);
        localFile.delete();
      }
      i += 1;
    }
    trimInvalidSessionFiles();
  }
  
  void doCloseSessions(SessionSettingsData paramSessionSettingsData)
    throws Exception
  {
    doCloseSessions(paramSessionSettingsData, false);
  }
  
  void enableExceptionHandling(Thread.UncaughtExceptionHandler paramUncaughtExceptionHandler)
  {
    openSession();
    this.crashHandler = new CrashlyticsUncaughtExceptionHandler(new CrashlyticsUncaughtExceptionHandler.CrashListener()
    {
      public void onUncaughtException(Thread paramAnonymousThread, Throwable paramAnonymousThrowable)
      {
        CrashlyticsController.this.handleUncaughtException(paramAnonymousThread, paramAnonymousThrowable);
      }
    }, paramUncaughtExceptionHandler);
    Thread.setDefaultUncaughtExceptionHandler(this.crashHandler);
  }
  
  boolean finalizeSessions(final SessionSettingsData paramSessionSettingsData)
  {
    ((Boolean)this.backgroundWorker.submitAndWait(new Callable()
    {
      public Boolean call()
        throws Exception
      {
        if (CrashlyticsController.this.isHandlingException())
        {
          Fabric.getLogger().d("CrashlyticsCore", "Skipping session finalization because a crash has already occurred.");
          return Boolean.FALSE;
        }
        Fabric.getLogger().d("CrashlyticsCore", "Finalizing previously open sessions.");
        CrashlyticsController.this.doCloseSessions(paramSessionSettingsData, true);
        Fabric.getLogger().d("CrashlyticsCore", "Closed all previously open sessions");
        return Boolean.TRUE;
      }
    })).booleanValue();
  }
  
  File getFatalSessionFilesDir()
  {
    return new File(getFilesDir(), "fatal-sessions");
  }
  
  File getFilesDir()
  {
    return this.fileStore.getFilesDir();
  }
  
  File getInvalidFilesDir()
  {
    return new File(getFilesDir(), "invalidClsFiles");
  }
  
  File getNonFatalSessionFilesDir()
  {
    return new File(getFilesDir(), "nonfatal-sessions");
  }
  
  void handleUncaughtException(final Thread paramThread, final Throwable paramThrowable)
  {
    try
    {
      Fabric.getLogger().d("CrashlyticsCore", "Crashlytics is handling uncaught exception \"" + paramThrowable + "\" from thread " + paramThread.getName());
      this.devicePowerStateListener.dispose();
      final Date localDate = new Date();
      this.backgroundWorker.submitAndWait(new Callable()
      {
        public Void call()
          throws Exception
        {
          CrashlyticsController.this.crashlyticsCore.createCrashMarker();
          CrashlyticsController.this.writeFatal(localDate, paramThread, paramThrowable);
          SettingsData localSettingsData = Settings.getInstance().awaitSettingsData();
          if (localSettingsData != null) {}
          for (SessionSettingsData localSessionSettingsData = localSettingsData.sessionData;; localSessionSettingsData = null)
          {
            CrashlyticsController.this.doCloseSessions(localSessionSettingsData);
            CrashlyticsController.this.doOpenSession();
            if (localSessionSettingsData != null) {
              CrashlyticsController.this.trimSessionFiles(localSessionSettingsData.maxCompleteSessionsCount);
            }
            if (!CrashlyticsController.this.shouldPromptUserBeforeSendingCrashReports(localSettingsData)) {
              CrashlyticsController.this.sendSessionReports(localSettingsData);
            }
            return null;
          }
        }
      });
      return;
    }
    finally
    {
      paramThread = finally;
      throw paramThread;
    }
  }
  
  boolean hasOpenSession()
  {
    return listSessionBeginFiles().length > 0;
  }
  
  boolean isHandlingException()
  {
    return (this.crashHandler != null) && (this.crashHandler.isHandlingException());
  }
  
  File[] listCompleteSessionFiles()
  {
    LinkedList localLinkedList = new LinkedList();
    Collections.addAll(localLinkedList, listFilesMatching(getFatalSessionFilesDir(), SESSION_FILE_FILTER));
    Collections.addAll(localLinkedList, listFilesMatching(getNonFatalSessionFilesDir(), SESSION_FILE_FILTER));
    Collections.addAll(localLinkedList, listFilesMatching(getFilesDir(), SESSION_FILE_FILTER));
    return (File[])localLinkedList.toArray(new File[localLinkedList.size()]);
  }
  
  File[] listSessionBeginFiles()
  {
    return listFilesMatching(new FileNameContainsFilter("BeginSession"));
  }
  
  void openSession()
  {
    this.backgroundWorker.submit(new Callable()
    {
      public Void call()
        throws Exception
      {
        CrashlyticsController.this.doOpenSession();
        return null;
      }
    });
  }
  
  void submitAllReports(float paramFloat, SettingsData paramSettingsData)
  {
    if (paramSettingsData == null)
    {
      Fabric.getLogger().w("CrashlyticsCore", "Could not send reports. Settings are not available.");
      return;
    }
    CreateReportSpiCall localCreateReportSpiCall = getCreateReportSpiCall(paramSettingsData.appData.reportsUrl);
    if (shouldPromptUserBeforeSendingCrashReports(paramSettingsData)) {}
    for (paramSettingsData = new PrivacyDialogCheck(this.crashlyticsCore, this.preferenceManager, paramSettingsData.promptData);; paramSettingsData = new ReportUploader.AlwaysSendCheck())
    {
      new ReportUploader(this.appData.apiKey, localCreateReportSpiCall, this.reportFilesProvider, this.handlingExceptionCheck).uploadReports(paramFloat, paramSettingsData);
      return;
    }
  }
  
  void trimSessionFiles(int paramInt)
  {
    paramInt -= Utils.capFileCount(getFatalSessionFilesDir(), paramInt, SMALLEST_FILE_NAME_FIRST);
    int i = Utils.capFileCount(getNonFatalSessionFilesDir(), paramInt, SMALLEST_FILE_NAME_FIRST);
    Utils.capFileCount(getFilesDir(), SESSION_FILE_FILTER, paramInt - i, SMALLEST_FILE_NAME_FIRST);
  }
  
  void writeExternalCrashEvent(final SessionEventData paramSessionEventData)
  {
    this.backgroundWorker.submit(new Callable()
    {
      public Void call()
        throws Exception
      {
        if (!CrashlyticsController.this.isHandlingException()) {
          CrashlyticsController.this.doWriteExternalCrashEvent(paramSessionEventData);
        }
        return null;
      }
    });
  }
  
  void writeNonFatalException(final Thread paramThread, final Throwable paramThrowable)
  {
    final Date localDate = new Date();
    this.backgroundWorker.submit(new Runnable()
    {
      public void run()
      {
        if (!CrashlyticsController.this.isHandlingException()) {
          CrashlyticsController.this.doWriteNonFatal(localDate, paramThread, paramThrowable);
        }
      }
    });
  }
  
  void writeToLog(final long paramLong, String paramString)
  {
    this.backgroundWorker.submit(new Callable()
    {
      public Void call()
        throws Exception
      {
        if (!CrashlyticsController.this.isHandlingException()) {
          CrashlyticsController.this.logFileManager.writeToLog(paramLong, this.val$msg);
        }
        return null;
      }
    });
  }
  
  private static class AnySessionPartFileFilter
    implements FilenameFilter
  {
    public boolean accept(File paramFile, String paramString)
    {
      return (!CrashlyticsController.SESSION_FILE_FILTER.accept(paramFile, paramString)) && (CrashlyticsController.SESSION_FILE_PATTERN.matcher(paramString).matches());
    }
  }
  
  static class FileNameContainsFilter
    implements FilenameFilter
  {
    private final String string;
    
    public FileNameContainsFilter(String paramString)
    {
      this.string = paramString;
    }
    
    public boolean accept(File paramFile, String paramString)
    {
      return (paramString.contains(this.string)) && (!paramString.endsWith(".cls_temp"));
    }
  }
  
  static class InvalidPartFileFilter
    implements FilenameFilter
  {
    public boolean accept(File paramFile, String paramString)
    {
      return (ClsFileOutputStream.TEMP_FILENAME_FILTER.accept(paramFile, paramString)) || (paramString.contains("SessionMissingBinaryImages"));
    }
  }
  
  private static final class LogFileDirectoryProvider
    implements LogFileManager.DirectoryProvider
  {
    private static final String LOG_FILES_DIR = "log-files";
    private final FileStore rootFileStore;
    
    public LogFileDirectoryProvider(FileStore paramFileStore)
    {
      this.rootFileStore = paramFileStore;
    }
    
    public File getLogFileDir()
    {
      File localFile = new File(this.rootFileStore.getFilesDir(), "log-files");
      if (!localFile.exists()) {
        localFile.mkdirs();
      }
      return localFile;
    }
  }
  
  private static final class PrivacyDialogCheck
    implements ReportUploader.SendCheck
  {
    private final Kit kit;
    private final PreferenceManager preferenceManager;
    private final PromptSettingsData promptData;
    
    public PrivacyDialogCheck(Kit paramKit, PreferenceManager paramPreferenceManager, PromptSettingsData paramPromptSettingsData)
    {
      this.kit = paramKit;
      this.preferenceManager = paramPreferenceManager;
      this.promptData = paramPromptSettingsData;
    }
    
    public boolean canSendReports()
    {
      Activity localActivity = this.kit.getFabric().getCurrentActivity();
      if ((localActivity == null) || (localActivity.isFinishing())) {
        return true;
      }
      final Object localObject = new CrashPromptDialog.AlwaysSendCallback()
      {
        public void sendUserReportsWithoutPrompting(boolean paramAnonymousBoolean)
        {
          CrashlyticsController.PrivacyDialogCheck.this.preferenceManager.setShouldAlwaysSendReports(paramAnonymousBoolean);
        }
      };
      localObject = CrashPromptDialog.create(localActivity, this.promptData, (CrashPromptDialog.AlwaysSendCallback)localObject);
      localActivity.runOnUiThread(new Runnable()
      {
        public void run()
        {
          localObject.show();
        }
      });
      Fabric.getLogger().d("CrashlyticsCore", "Waiting for user opt-in.");
      ((CrashPromptDialog)localObject).await();
      return ((CrashPromptDialog)localObject).getOptIn();
    }
  }
  
  private final class ReportUploaderFilesProvider
    implements ReportUploader.ReportFilesProvider
  {
    private ReportUploaderFilesProvider() {}
    
    public File[] getCompleteSessionFiles()
    {
      return CrashlyticsController.this.listCompleteSessionFiles();
    }
    
    public File[] getInvalidSessionFiles()
    {
      return CrashlyticsController.this.getInvalidFilesDir().listFiles();
    }
  }
  
  private final class ReportUploaderHandlingExceptionCheck
    implements ReportUploader.HandlingExceptionCheck
  {
    private ReportUploaderHandlingExceptionCheck() {}
    
    public boolean isHandlingException()
    {
      return CrashlyticsController.this.isHandlingException();
    }
  }
  
  private static final class SendReportRunnable
    implements Runnable
  {
    private final Context context;
    private final Report report;
    private final ReportUploader reportUploader;
    
    public SendReportRunnable(Context paramContext, Report paramReport, ReportUploader paramReportUploader)
    {
      this.context = paramContext;
      this.report = paramReport;
      this.reportUploader = paramReportUploader;
    }
    
    public void run()
    {
      if (!CommonUtils.canTryConnection(this.context)) {
        return;
      }
      Fabric.getLogger().d("CrashlyticsCore", "Attempting to send crash report at time of crash...");
      this.reportUploader.forceUpload(this.report);
    }
  }
  
  static class SessionPartFileFilter
    implements FilenameFilter
  {
    private final String sessionId;
    
    public SessionPartFileFilter(String paramString)
    {
      this.sessionId = paramString;
    }
    
    public boolean accept(File paramFile, String paramString)
    {
      if (paramString.equals(this.sessionId + ".cls")) {}
      while ((!paramString.contains(this.sessionId)) || (paramString.endsWith(".cls_temp"))) {
        return false;
      }
      return true;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\crashlytics\android\core\CrashlyticsController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */