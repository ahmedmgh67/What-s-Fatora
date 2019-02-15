package com.crashlytics.android.core;

import android.content.Context;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.File;
import java.util.Set;

class LogFileManager
{
  private static final String COLLECT_CUSTOM_LOGS = "com.crashlytics.CollectCustomLogs";
  private static final String LOGFILE_EXT = ".temp";
  private static final String LOGFILE_PREFIX = "crashlytics-userlog-";
  static final int MAX_LOG_SIZE = 65536;
  private static final NoopLogStore NOOP_LOG_STORE = new NoopLogStore(null);
  private final Context context;
  private FileLogStore currentLog;
  private final DirectoryProvider directoryProvider;
  
  LogFileManager(Context paramContext, DirectoryProvider paramDirectoryProvider)
  {
    this(paramContext, paramDirectoryProvider, null);
  }
  
  LogFileManager(Context paramContext, DirectoryProvider paramDirectoryProvider, String paramString)
  {
    this.context = paramContext;
    this.directoryProvider = paramDirectoryProvider;
    this.currentLog = NOOP_LOG_STORE;
    setCurrentSession(paramString);
  }
  
  private String getSessionIdForFile(File paramFile)
  {
    paramFile = paramFile.getName();
    int i = paramFile.lastIndexOf(".temp");
    if (i == -1) {
      return paramFile;
    }
    return paramFile.substring("crashlytics-userlog-".length(), i);
  }
  
  private File getWorkingFileForSession(String paramString)
  {
    paramString = "crashlytics-userlog-" + paramString + ".temp";
    return new File(this.directoryProvider.getLogFileDir(), paramString);
  }
  
  void clearLog()
  {
    this.currentLog.deleteLogFile();
  }
  
  void discardOldLogFiles(Set<String> paramSet)
  {
    File[] arrayOfFile = this.directoryProvider.getLogFileDir().listFiles();
    if (arrayOfFile != null)
    {
      int j = arrayOfFile.length;
      int i = 0;
      while (i < j)
      {
        File localFile = arrayOfFile[i];
        if (!paramSet.contains(getSessionIdForFile(localFile))) {
          localFile.delete();
        }
        i += 1;
      }
    }
  }
  
  ByteString getByteStringForLog()
  {
    return this.currentLog.getLogAsByteString();
  }
  
  final void setCurrentSession(String paramString)
  {
    this.currentLog.closeLogFile();
    this.currentLog = NOOP_LOG_STORE;
    if (paramString == null) {
      return;
    }
    if (!CommonUtils.getBooleanResourceValue(this.context, "com.crashlytics.CollectCustomLogs", true))
    {
      Fabric.getLogger().d("CrashlyticsCore", "Preferences requested no custom logs. Aborting log file creation.");
      return;
    }
    setLogFile(getWorkingFileForSession(paramString), 65536);
  }
  
  void setLogFile(File paramFile, int paramInt)
  {
    this.currentLog = new QueueFileLogStore(paramFile, paramInt);
  }
  
  void writeToLog(long paramLong, String paramString)
  {
    this.currentLog.writeToLog(paramLong, paramString);
  }
  
  public static abstract interface DirectoryProvider
  {
    public abstract File getLogFileDir();
  }
  
  private static final class NoopLogStore
    implements FileLogStore
  {
    public void closeLogFile() {}
    
    public void deleteLogFile() {}
    
    public ByteString getLogAsByteString()
    {
      return null;
    }
    
    public void writeToLog(long paramLong, String paramString) {}
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\crashlytics\android\core\LogFileManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */