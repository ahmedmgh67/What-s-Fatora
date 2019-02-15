package com.crashlytics.android.core;

import android.content.Context;
import android.util.Log;
import com.crashlytics.android.core.internal.CrashEventDataProvider;
import com.crashlytics.android.core.internal.models.SessionEventData;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.common.ApiKey;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.ExecutorUtils;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.concurrency.DependsOn;
import io.fabric.sdk.android.services.concurrency.Priority;
import io.fabric.sdk.android.services.concurrency.PriorityCallable;
import io.fabric.sdk.android.services.concurrency.Task;
import io.fabric.sdk.android.services.concurrency.UnmetDependencyException;
import io.fabric.sdk.android.services.network.DefaultHttpRequestFactory;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import io.fabric.sdk.android.services.persistence.FileStoreImpl;
import io.fabric.sdk.android.services.persistence.PreferenceStoreImpl;
import io.fabric.sdk.android.services.settings.FeaturesSettingsData;
import io.fabric.sdk.android.services.settings.Settings;
import io.fabric.sdk.android.services.settings.SettingsData;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.net.ssl.HttpsURLConnection;

@DependsOn({CrashEventDataProvider.class})
public class CrashlyticsCore
  extends Kit<Void>
{
  static final float CLS_DEFAULT_PROCESS_DELAY = 1.0F;
  static final String CRASHLYTICS_REQUIRE_BUILD_ID = "com.crashlytics.RequireBuildId";
  static final boolean CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT = true;
  static final String CRASH_MARKER_FILE_NAME = "crash_marker";
  static final int DEFAULT_MAIN_HANDLER_TIMEOUT_SEC = 4;
  private static final String INITIALIZATION_MARKER_FILE_NAME = "initialization_marker";
  static final int MAX_ATTRIBUTES = 64;
  static final int MAX_ATTRIBUTE_SIZE = 1024;
  private static final String MISSING_BUILD_ID_MSG = "This app relies on Crashlytics. Please sign up for access at https://fabric.io/sign_up,\ninstall an Android build tool and ask a team member to invite you to this app's organization.";
  private static final String PREFERENCE_STORE_NAME = "com.crashlytics.android.core.CrashlyticsCore";
  public static final String TAG = "CrashlyticsCore";
  private final ConcurrentHashMap<String, String> attributes;
  private CrashlyticsBackgroundWorker backgroundWorker;
  private CrashlyticsController controller;
  private CrashlyticsFileMarker crashMarker;
  private float delay;
  private boolean disabled;
  private CrashEventDataProvider externalCrashEventDataProvider;
  private HttpRequestFactory httpRequestFactory;
  private CrashlyticsFileMarker initializationMarker;
  private CrashlyticsListener listener;
  private final PinningInfoProvider pinningInfo;
  private final long startTime;
  private String userEmail = null;
  private String userId = null;
  private String userName = null;
  
  public CrashlyticsCore()
  {
    this(1.0F, null, null, false);
  }
  
  CrashlyticsCore(float paramFloat, CrashlyticsListener paramCrashlyticsListener, PinningInfoProvider paramPinningInfoProvider, boolean paramBoolean)
  {
    this(paramFloat, paramCrashlyticsListener, paramPinningInfoProvider, paramBoolean, ExecutorUtils.buildSingleThreadExecutorService("Crashlytics Exception Handler"));
  }
  
  CrashlyticsCore(float paramFloat, CrashlyticsListener paramCrashlyticsListener, PinningInfoProvider paramPinningInfoProvider, boolean paramBoolean, ExecutorService paramExecutorService)
  {
    this.delay = paramFloat;
    if (paramCrashlyticsListener != null) {}
    for (;;)
    {
      this.listener = paramCrashlyticsListener;
      this.pinningInfo = paramPinningInfoProvider;
      this.disabled = paramBoolean;
      this.backgroundWorker = new CrashlyticsBackgroundWorker(paramExecutorService);
      this.attributes = new ConcurrentHashMap();
      this.startTime = System.currentTimeMillis();
      return;
      paramCrashlyticsListener = new NoOpListener(null);
    }
  }
  
  private void checkForPreviousCrash()
  {
    Boolean localBoolean = (Boolean)this.backgroundWorker.submitAndWait(new CrashMarkerCheck(this.crashMarker));
    if (!Boolean.TRUE.equals(localBoolean)) {
      return;
    }
    try
    {
      this.listener.crashlyticsDidDetectCrashDuringPreviousExecution();
      return;
    }
    catch (Exception localException)
    {
      Fabric.getLogger().e("CrashlyticsCore", "Exception thrown by CrashlyticsListener while notifying of previous crash.", localException);
    }
  }
  
  private void doLog(int paramInt, String paramString1, String paramString2)
  {
    if (this.disabled) {}
    while (!ensureFabricWithCalled("prior to logging messages.")) {
      return;
    }
    long l1 = System.currentTimeMillis();
    long l2 = this.startTime;
    this.controller.writeToLog(l1 - l2, formatLogMessage(paramInt, paramString1, paramString2));
  }
  
  private static boolean ensureFabricWithCalled(String paramString)
  {
    CrashlyticsCore localCrashlyticsCore = getInstance();
    if ((localCrashlyticsCore == null) || (localCrashlyticsCore.controller == null))
    {
      Fabric.getLogger().e("CrashlyticsCore", "Crashlytics must be initialized by calling Fabric.with(Context) " + paramString, null);
      return false;
    }
    return true;
  }
  
  private void finishInitSynchronously()
  {
    Object localObject = new PriorityCallable()
    {
      public Void call()
        throws Exception
      {
        return CrashlyticsCore.this.doInBackground();
      }
      
      public Priority getPriority()
      {
        return Priority.IMMEDIATE;
      }
    };
    Iterator localIterator = getDependencies().iterator();
    while (localIterator.hasNext()) {
      ((PriorityCallable)localObject).addDependency((Task)localIterator.next());
    }
    localObject = getFabric().getExecutorService().submit((Callable)localObject);
    Fabric.getLogger().d("CrashlyticsCore", "Crashlytics detected incomplete initialization on previous app launch. Will initialize synchronously.");
    try
    {
      ((Future)localObject).get(4L, TimeUnit.SECONDS);
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      Fabric.getLogger().e("CrashlyticsCore", "Crashlytics was interrupted during initialization.", localInterruptedException);
      return;
    }
    catch (ExecutionException localExecutionException)
    {
      Fabric.getLogger().e("CrashlyticsCore", "Problem encountered during Crashlytics initialization.", localExecutionException);
      return;
    }
    catch (TimeoutException localTimeoutException)
    {
      Fabric.getLogger().e("CrashlyticsCore", "Crashlytics timed out during initialization.", localTimeoutException);
    }
  }
  
  private static String formatLogMessage(int paramInt, String paramString1, String paramString2)
  {
    return CommonUtils.logPriorityToString(paramInt) + "/" + paramString1 + " " + paramString2;
  }
  
  public static CrashlyticsCore getInstance()
  {
    return (CrashlyticsCore)Fabric.getKit(CrashlyticsCore.class);
  }
  
  static boolean isBuildIdValid(String paramString, boolean paramBoolean)
  {
    if (!paramBoolean) {
      Fabric.getLogger().d("CrashlyticsCore", "Configured not to require a build ID.");
    }
    while (!CommonUtils.isNullOrEmpty(paramString)) {
      return true;
    }
    Log.e("CrashlyticsCore", ".");
    Log.e("CrashlyticsCore", ".     |  | ");
    Log.e("CrashlyticsCore", ".     |  |");
    Log.e("CrashlyticsCore", ".     |  |");
    Log.e("CrashlyticsCore", ".   \\ |  | /");
    Log.e("CrashlyticsCore", ".    \\    /");
    Log.e("CrashlyticsCore", ".     \\  /");
    Log.e("CrashlyticsCore", ".      \\/");
    Log.e("CrashlyticsCore", ".");
    Log.e("CrashlyticsCore", "This app relies on Crashlytics. Please sign up for access at https://fabric.io/sign_up,\ninstall an Android build tool and ask a team member to invite you to this app's organization.");
    Log.e("CrashlyticsCore", ".");
    Log.e("CrashlyticsCore", ".      /\\");
    Log.e("CrashlyticsCore", ".     /  \\");
    Log.e("CrashlyticsCore", ".    /    \\");
    Log.e("CrashlyticsCore", ".   / |  | \\");
    Log.e("CrashlyticsCore", ".     |  |");
    Log.e("CrashlyticsCore", ".     |  |");
    Log.e("CrashlyticsCore", ".     |  |");
    Log.e("CrashlyticsCore", ".");
    return false;
  }
  
  private static String sanitizeAttribute(String paramString)
  {
    String str = paramString;
    if (paramString != null)
    {
      paramString = paramString.trim();
      str = paramString;
      if (paramString.length() > 1024) {
        str = paramString.substring(0, 1024);
      }
    }
    return str;
  }
  
  public void crash()
  {
    new CrashTest().indexOutOfBounds();
  }
  
  void createCrashMarker()
  {
    this.crashMarker.create();
  }
  
  boolean didPreviousInitializationFail()
  {
    ((Boolean)this.backgroundWorker.submitAndWait(new Callable()
    {
      public Boolean call()
        throws Exception
      {
        return Boolean.valueOf(CrashlyticsCore.this.initializationMarker.isPresent());
      }
    })).booleanValue();
  }
  
  protected Void doInBackground()
  {
    markInitializationStarted();
    Object localObject1 = getExternalCrashEventData();
    if (localObject1 != null) {
      this.controller.writeExternalCrashEvent((SessionEventData)localObject1);
    }
    this.controller.cleanInvalidTempFiles();
    try
    {
      localObject1 = Settings.getInstance().awaitSettingsData();
      if (localObject1 == null)
      {
        Fabric.getLogger().w("CrashlyticsCore", "Received null settings, skipping report submission!");
        return null;
      }
      if (!((SettingsData)localObject1).featuresData.collectReports)
      {
        Fabric.getLogger().d("CrashlyticsCore", "Collection of crash reports disabled in Crashlytics settings.");
        return null;
      }
      if (!this.controller.finalizeSessions(((SettingsData)localObject1).sessionData)) {
        Fabric.getLogger().d("CrashlyticsCore", "Could not finalize previous sessions.");
      }
      this.controller.submitAllReports(this.delay, (SettingsData)localObject1);
      return null;
    }
    catch (Exception localException)
    {
      Fabric.getLogger().e("CrashlyticsCore", "Crashlytics encountered a problem during asynchronous initialization.", localException);
      return null;
    }
    finally
    {
      markInitializationComplete();
    }
  }
  
  Map<String, String> getAttributes()
  {
    return Collections.unmodifiableMap(this.attributes);
  }
  
  CrashlyticsController getController()
  {
    return this.controller;
  }
  
  SessionEventData getExternalCrashEventData()
  {
    SessionEventData localSessionEventData = null;
    if (this.externalCrashEventDataProvider != null) {
      localSessionEventData = this.externalCrashEventDataProvider.getCrashEventData();
    }
    return localSessionEventData;
  }
  
  public String getIdentifier()
  {
    return "com.crashlytics.sdk.android.crashlytics-core";
  }
  
  public PinningInfoProvider getPinningInfoProvider()
  {
    if (!this.disabled) {
      return this.pinningInfo;
    }
    return null;
  }
  
  String getUserEmail()
  {
    if (getIdManager().canCollectUserIds()) {
      return this.userEmail;
    }
    return null;
  }
  
  String getUserIdentifier()
  {
    if (getIdManager().canCollectUserIds()) {
      return this.userId;
    }
    return null;
  }
  
  String getUserName()
  {
    if (getIdManager().canCollectUserIds()) {
      return this.userName;
    }
    return null;
  }
  
  public String getVersion()
  {
    return "2.3.17.dev";
  }
  
  boolean internalVerifyPinning(URL paramURL)
  {
    boolean bool = false;
    if (getPinningInfoProvider() != null)
    {
      paramURL = this.httpRequestFactory.buildHttpRequest(HttpMethod.GET, paramURL.toString());
      ((HttpsURLConnection)paramURL.getConnection()).setInstanceFollowRedirects(false);
      paramURL.code();
      bool = true;
    }
    return bool;
  }
  
  public void log(int paramInt, String paramString1, String paramString2)
  {
    doLog(paramInt, paramString1, paramString2);
    Fabric.getLogger().log(paramInt, "" + paramString1, "" + paramString2, true);
  }
  
  public void log(String paramString)
  {
    doLog(3, "CrashlyticsCore", paramString);
  }
  
  public void logException(Throwable paramThrowable)
  {
    if (this.disabled) {}
    while (!ensureFabricWithCalled("prior to logging exceptions.")) {
      return;
    }
    if (paramThrowable == null)
    {
      Fabric.getLogger().log(5, "CrashlyticsCore", "Crashlytics is ignoring a request to log a null exception.");
      return;
    }
    this.controller.writeNonFatalException(Thread.currentThread(), paramThrowable);
  }
  
  void markInitializationComplete()
  {
    this.backgroundWorker.submit(new Callable()
    {
      public Boolean call()
        throws Exception
      {
        try
        {
          boolean bool = CrashlyticsCore.this.initializationMarker.remove();
          Fabric.getLogger().d("CrashlyticsCore", "Initialization marker file removed: " + bool);
          return Boolean.valueOf(bool);
        }
        catch (Exception localException)
        {
          Fabric.getLogger().e("CrashlyticsCore", "Problem encountered deleting Crashlytics initialization marker.", localException);
        }
        return Boolean.valueOf(false);
      }
    });
  }
  
  void markInitializationStarted()
  {
    this.backgroundWorker.submitAndWait(new Callable()
    {
      public Void call()
        throws Exception
      {
        CrashlyticsCore.this.initializationMarker.create();
        Fabric.getLogger().d("CrashlyticsCore", "Initialization marker file created.");
        return null;
      }
    });
  }
  
  protected boolean onPreExecute()
  {
    return onPreExecute(super.getContext());
  }
  
  boolean onPreExecute(Context paramContext)
  {
    if (this.disabled) {
      return false;
    }
    Object localObject2 = new ApiKey().getValue(paramContext);
    if (localObject2 == null) {
      return false;
    }
    Object localObject3 = CommonUtils.resolveBuildId(paramContext);
    if (!isBuildIdValid((String)localObject3, CommonUtils.getBooleanResourceValue(paramContext, "com.crashlytics.RequireBuildId", true))) {
      throw new UnmetDependencyException("This app relies on Crashlytics. Please sign up for access at https://fabric.io/sign_up,\ninstall an Android build tool and ask a team member to invite you to this app's organization.");
    }
    try
    {
      Fabric.getLogger().i("CrashlyticsCore", "Initializing Crashlytics " + getVersion());
      FileStoreImpl localFileStoreImpl = new FileStoreImpl(this);
      this.crashMarker = new CrashlyticsFileMarker("crash_marker", localFileStoreImpl);
      this.initializationMarker = new CrashlyticsFileMarker("initialization_marker", localFileStoreImpl);
      PreferenceManager localPreferenceManager = PreferenceManager.create(new PreferenceStoreImpl(getContext(), "com.crashlytics.android.core.CrashlyticsCore"), this);
      if (this.pinningInfo != null) {}
      for (Object localObject1 = new CrashlyticsPinningInfoProvider(this.pinningInfo);; localObject1 = null)
      {
        this.httpRequestFactory = new DefaultHttpRequestFactory(Fabric.getLogger());
        this.httpRequestFactory.setPinningInfoProvider((io.fabric.sdk.android.services.network.PinningInfoProvider)localObject1);
        localObject1 = getIdManager();
        localObject2 = AppData.create(paramContext, (IdManager)localObject1, (String)localObject2, (String)localObject3);
        localObject3 = new ManifestUnityVersionProvider(paramContext, ((AppData)localObject2).packageName);
        Fabric.getLogger().d("CrashlyticsCore", "Installer package name is: " + ((AppData)localObject2).installerPackageName);
        this.controller = new CrashlyticsController(this, this.backgroundWorker, this.httpRequestFactory, (IdManager)localObject1, localPreferenceManager, localFileStoreImpl, (AppData)localObject2, (UnityVersionProvider)localObject3);
        boolean bool = didPreviousInitializationFail();
        checkForPreviousCrash();
        this.controller.enableExceptionHandling(Thread.getDefaultUncaughtExceptionHandler());
        if ((!bool) || (!CommonUtils.canTryConnection(paramContext))) {
          break;
        }
        Fabric.getLogger().d("CrashlyticsCore", "Crashlytics did not finish previous background initialization. Initializing synchronously.");
        finishInitSynchronously();
        return false;
      }
      Fabric.getLogger().d("CrashlyticsCore", "Exception handling initialization successful");
    }
    catch (Exception paramContext)
    {
      Fabric.getLogger().e("CrashlyticsCore", "Crashlytics was not started due to an exception during initialization", paramContext);
      this.controller = null;
      return false;
    }
    return true;
  }
  
  public void setBool(String paramString, boolean paramBoolean)
  {
    setString(paramString, Boolean.toString(paramBoolean));
  }
  
  public void setDouble(String paramString, double paramDouble)
  {
    setString(paramString, Double.toString(paramDouble));
  }
  
  void setExternalCrashEventDataProvider(CrashEventDataProvider paramCrashEventDataProvider)
  {
    this.externalCrashEventDataProvider = paramCrashEventDataProvider;
  }
  
  public void setFloat(String paramString, float paramFloat)
  {
    setString(paramString, Float.toString(paramFloat));
  }
  
  public void setInt(String paramString, int paramInt)
  {
    setString(paramString, Integer.toString(paramInt));
  }
  
  @Deprecated
  public void setListener(CrashlyticsListener paramCrashlyticsListener)
  {
    try
    {
      Fabric.getLogger().w("CrashlyticsCore", "Use of setListener is deprecated.");
      if (paramCrashlyticsListener == null) {
        throw new IllegalArgumentException("listener must not be null.");
      }
    }
    finally {}
    this.listener = paramCrashlyticsListener;
  }
  
  public void setLong(String paramString, long paramLong)
  {
    setString(paramString, Long.toString(paramLong));
  }
  
  public void setString(String paramString1, String paramString2)
  {
    if (this.disabled) {}
    while (!ensureFabricWithCalled("prior to setting keys.")) {
      return;
    }
    if (paramString1 == null)
    {
      paramString1 = getContext();
      if ((paramString1 != null) && (CommonUtils.isAppDebuggable(paramString1))) {
        throw new IllegalArgumentException("Custom attribute key must not be null.");
      }
      Fabric.getLogger().e("CrashlyticsCore", "Attempting to set custom attribute with null key, ignoring.", null);
      return;
    }
    String str = sanitizeAttribute(paramString1);
    if ((this.attributes.size() >= 64) && (!this.attributes.containsKey(str)))
    {
      Fabric.getLogger().d("CrashlyticsCore", "Exceeded maximum number of custom attributes (64)");
      return;
    }
    if (paramString2 == null) {}
    for (paramString1 = "";; paramString1 = sanitizeAttribute(paramString2))
    {
      this.attributes.put(str, paramString1);
      this.controller.cacheKeyData(this.attributes);
      return;
    }
  }
  
  public void setUserEmail(String paramString)
  {
    if (this.disabled) {}
    while (!ensureFabricWithCalled("prior to setting user data.")) {
      return;
    }
    this.userEmail = sanitizeAttribute(paramString);
    this.controller.cacheUserData(this.userId, this.userName, this.userEmail);
  }
  
  public void setUserIdentifier(String paramString)
  {
    if (this.disabled) {}
    while (!ensureFabricWithCalled("prior to setting user data.")) {
      return;
    }
    this.userId = sanitizeAttribute(paramString);
    this.controller.cacheUserData(this.userId, this.userName, this.userEmail);
  }
  
  public void setUserName(String paramString)
  {
    if (this.disabled) {}
    while (!ensureFabricWithCalled("prior to setting user data.")) {
      return;
    }
    this.userName = sanitizeAttribute(paramString);
    this.controller.cacheUserData(this.userId, this.userName, this.userEmail);
  }
  
  public boolean verifyPinning(URL paramURL)
  {
    try
    {
      boolean bool = internalVerifyPinning(paramURL);
      return bool;
    }
    catch (Exception paramURL)
    {
      Fabric.getLogger().e("CrashlyticsCore", "Could not verify SSL pinning", paramURL);
    }
    return false;
  }
  
  public static class Builder
  {
    private float delay = -1.0F;
    private boolean disabled = false;
    private CrashlyticsListener listener;
    private PinningInfoProvider pinningInfoProvider;
    
    public CrashlyticsCore build()
    {
      if (this.delay < 0.0F) {
        this.delay = 1.0F;
      }
      return new CrashlyticsCore(this.delay, this.listener, this.pinningInfoProvider, this.disabled);
    }
    
    public Builder delay(float paramFloat)
    {
      if (paramFloat <= 0.0F) {
        throw new IllegalArgumentException("delay must be greater than 0");
      }
      if (this.delay > 0.0F) {
        throw new IllegalStateException("delay already set.");
      }
      this.delay = paramFloat;
      return this;
    }
    
    public Builder disabled(boolean paramBoolean)
    {
      this.disabled = paramBoolean;
      return this;
    }
    
    public Builder listener(CrashlyticsListener paramCrashlyticsListener)
    {
      if (paramCrashlyticsListener == null) {
        throw new IllegalArgumentException("listener must not be null.");
      }
      if (this.listener != null) {
        throw new IllegalStateException("listener already set.");
      }
      this.listener = paramCrashlyticsListener;
      return this;
    }
    
    @Deprecated
    public Builder pinningInfo(PinningInfoProvider paramPinningInfoProvider)
    {
      if (paramPinningInfoProvider == null) {
        throw new IllegalArgumentException("pinningInfoProvider must not be null.");
      }
      if (this.pinningInfoProvider != null) {
        throw new IllegalStateException("pinningInfoProvider already set.");
      }
      this.pinningInfoProvider = paramPinningInfoProvider;
      return this;
    }
  }
  
  private static final class CrashMarkerCheck
    implements Callable<Boolean>
  {
    private final CrashlyticsFileMarker crashMarker;
    
    public CrashMarkerCheck(CrashlyticsFileMarker paramCrashlyticsFileMarker)
    {
      this.crashMarker = paramCrashlyticsFileMarker;
    }
    
    public Boolean call()
      throws Exception
    {
      if (!this.crashMarker.isPresent()) {
        return Boolean.FALSE;
      }
      Fabric.getLogger().d("CrashlyticsCore", "Found previous crash marker.");
      this.crashMarker.remove();
      return Boolean.TRUE;
    }
  }
  
  private static final class NoOpListener
    implements CrashlyticsListener
  {
    public void crashlyticsDidDetectCrashDuringPreviousExecution() {}
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\crashlytics\android\core\CrashlyticsCore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */