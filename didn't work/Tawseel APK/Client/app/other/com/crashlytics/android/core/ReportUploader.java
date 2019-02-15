package com.crashlytics.android.core;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.common.BackgroundPriorityRunnable;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class ReportUploader
{
  static final Map<String, String> HEADER_INVALID_CLS_FILE = Collections.singletonMap("X-CRASHLYTICS-INVALID-SESSION", "1");
  private static final short[] RETRY_INTERVALS = { 10, 20, 30, 60, 120, 300 };
  private final String apiKey;
  private final CreateReportSpiCall createReportCall;
  private final Object fileAccessLock = new Object();
  private final HandlingExceptionCheck handlingExceptionCheck;
  private final ReportFilesProvider reportFilesProvider;
  private Thread uploadThread;
  
  public ReportUploader(String paramString, CreateReportSpiCall paramCreateReportSpiCall, ReportFilesProvider paramReportFilesProvider, HandlingExceptionCheck paramHandlingExceptionCheck)
  {
    if (paramCreateReportSpiCall == null) {
      throw new IllegalArgumentException("createReportCall must not be null.");
    }
    this.createReportCall = paramCreateReportSpiCall;
    this.apiKey = paramString;
    this.reportFilesProvider = paramReportFilesProvider;
    this.handlingExceptionCheck = paramHandlingExceptionCheck;
  }
  
  List<Report> findReports()
  {
    Fabric.getLogger().d("CrashlyticsCore", "Checking for crash reports...");
    int j;
    int i;
    Object localObject4;
    synchronized (this.fileAccessLock)
    {
      localObject3 = this.reportFilesProvider.getCompleteSessionFiles();
      File[] arrayOfFile = this.reportFilesProvider.getInvalidSessionFiles();
      ??? = new LinkedList();
      if (localObject3 != null)
      {
        j = localObject3.length;
        i = 0;
        if (i < j)
        {
          localObject4 = localObject3[i];
          Fabric.getLogger().d("CrashlyticsCore", "Found crash report " + ((File)localObject4).getPath());
          ((List)???).add(new SessionReport((File)localObject4));
          i += 1;
        }
      }
    }
    Object localObject3 = new HashMap();
    Object localObject5;
    if (localObject2 != null)
    {
      j = localObject2.length;
      i = 0;
      while (i < j)
      {
        localObject4 = localObject2[i];
        localObject5 = CrashlyticsController.getSessionIdFromSessionFile((File)localObject4);
        if (!((Map)localObject3).containsKey(localObject5)) {
          ((Map)localObject3).put(localObject5, new LinkedList());
        }
        ((List)((Map)localObject3).get(localObject5)).add(localObject4);
        i += 1;
      }
    }
    Iterator localIterator = ((Map)localObject3).keySet().iterator();
    while (localIterator.hasNext())
    {
      localObject4 = (String)localIterator.next();
      Fabric.getLogger().d("CrashlyticsCore", "Found invalid session: " + (String)localObject4);
      localObject5 = (List)((Map)localObject3).get(localObject4);
      ((List)???).add(new InvalidSessionReport((String)localObject4, (File[])((List)localObject5).toArray(new File[((List)localObject5).size()])));
    }
    if (((List)???).isEmpty()) {
      Fabric.getLogger().d("CrashlyticsCore", "No reports found.");
    }
    return (List<Report>)???;
  }
  
  boolean forceUpload(Report paramReport)
  {
    boolean bool2 = false;
    synchronized (this.fileAccessLock)
    {
      try
      {
        localObject1 = new CreateReportRequest(this.apiKey, paramReport);
        boolean bool3 = this.createReportCall.invoke((CreateReportRequest)localObject1);
        Logger localLogger = Fabric.getLogger();
        StringBuilder localStringBuilder = new StringBuilder().append("Crashlytics report upload ");
        if (!bool3) {
          break label114;
        }
        localObject1 = "complete: ";
        localLogger.i("CrashlyticsCore", (String)localObject1 + paramReport.getIdentifier());
        bool1 = bool2;
        if (bool3)
        {
          paramReport.remove();
          bool1 = true;
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          Object localObject1;
          label114:
          Fabric.getLogger().e("CrashlyticsCore", "Error occurred sending report " + paramReport, localException);
          boolean bool1 = bool2;
        }
      }
      return bool1;
      localObject1 = "FAILED: ";
    }
  }
  
  boolean isUploading()
  {
    return this.uploadThread != null;
  }
  
  /* Error */
  public void uploadReports(float paramFloat, SendCheck paramSendCheck)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 84	com/crashlytics/android/core/ReportUploader:uploadThread	Ljava/lang/Thread;
    //   6: ifnull +19 -> 25
    //   9: invokestatic 96	io/fabric/sdk/android/Fabric:getLogger	()Lio/fabric/sdk/android/Logger;
    //   12: ldc 98
    //   14: ldc_w 259
    //   17: invokeinterface 106 3 0
    //   22: aload_0
    //   23: monitorexit
    //   24: return
    //   25: aload_0
    //   26: new 261	java/lang/Thread
    //   29: dup
    //   30: new 18	com/crashlytics/android/core/ReportUploader$Worker
    //   33: dup
    //   34: aload_0
    //   35: fload_1
    //   36: aload_2
    //   37: invokespecial 264	com/crashlytics/android/core/ReportUploader$Worker:<init>	(Lcom/crashlytics/android/core/ReportUploader;FLcom/crashlytics/android/core/ReportUploader$SendCheck;)V
    //   40: ldc_w 266
    //   43: invokespecial 269	java/lang/Thread:<init>	(Ljava/lang/Runnable;Ljava/lang/String;)V
    //   46: putfield 84	com/crashlytics/android/core/ReportUploader:uploadThread	Ljava/lang/Thread;
    //   49: aload_0
    //   50: getfield 84	com/crashlytics/android/core/ReportUploader:uploadThread	Ljava/lang/Thread;
    //   53: invokevirtual 272	java/lang/Thread:start	()V
    //   56: goto -34 -> 22
    //   59: astore_2
    //   60: aload_0
    //   61: monitorexit
    //   62: aload_2
    //   63: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	64	0	this	ReportUploader
    //   0	64	1	paramFloat	float
    //   0	64	2	paramSendCheck	SendCheck
    // Exception table:
    //   from	to	target	type
    //   2	22	59	finally
    //   25	56	59	finally
  }
  
  static final class AlwaysSendCheck
    implements ReportUploader.SendCheck
  {
    public boolean canSendReports()
    {
      return true;
    }
  }
  
  static abstract interface HandlingExceptionCheck
  {
    public abstract boolean isHandlingException();
  }
  
  static abstract interface ReportFilesProvider
  {
    public abstract File[] getCompleteSessionFiles();
    
    public abstract File[] getInvalidSessionFiles();
  }
  
  static abstract interface SendCheck
  {
    public abstract boolean canSendReports();
  }
  
  private class Worker
    extends BackgroundPriorityRunnable
  {
    private final float delay;
    private final ReportUploader.SendCheck sendCheck;
    
    Worker(float paramFloat, ReportUploader.SendCheck paramSendCheck)
    {
      this.delay = paramFloat;
      this.sendCheck = paramSendCheck;
    }
    
    private void attemptUploadWithRetry()
    {
      Fabric.getLogger().d("CrashlyticsCore", "Starting report processing in " + this.delay + " second(s)...");
      if (this.delay > 0.0F) {}
      List localList;
      break label192;
      label81:
      Object localObject1;
      for (;;)
      {
        try
        {
          Thread.sleep((this.delay * 1000.0F));
          localList = ReportUploader.this.findReports();
          if (ReportUploader.this.handlingExceptionCheck.isHandlingException()) {
            return;
          }
        }
        catch (InterruptedException localInterruptedException1)
        {
          Thread.currentThread().interrupt();
          return;
        }
        if ((localInterruptedException1.isEmpty()) || (this.sendCheck.canSendReports())) {
          break;
        }
        Fabric.getLogger().d("CrashlyticsCore", "User declined to send. Removing " + localInterruptedException1.size() + " Report(s).");
        localObject1 = localInterruptedException1.iterator();
        while (((Iterator)localObject1).hasNext()) {
          ((Report)((Iterator)localObject1).next()).remove();
        }
      }
      int i = 0;
      for (;;)
      {
        label192:
        if ((((List)localObject1).isEmpty()) || (ReportUploader.this.handlingExceptionCheck.isHandlingException())) {
          break label81;
        }
        Fabric.getLogger().d("CrashlyticsCore", "Attempting to send " + ((List)localObject1).size() + " report(s)");
        localObject1 = ((List)localObject1).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (Report)((Iterator)localObject1).next();
          ReportUploader.this.forceUpload((Report)localObject2);
        }
        Object localObject2 = ReportUploader.this.findReports();
        localObject1 = localObject2;
        if (((List)localObject2).isEmpty()) {
          break;
        }
        long l = ReportUploader.RETRY_INTERVALS[Math.min(i, ReportUploader.RETRY_INTERVALS.length - 1)];
        Fabric.getLogger().d("CrashlyticsCore", "Report submisson: scheduling delayed retry in " + l + " seconds");
        try
        {
          Thread.sleep(1000L * l);
          i += 1;
          localObject1 = localObject2;
        }
        catch (InterruptedException localInterruptedException2)
        {
          Thread.currentThread().interrupt();
        }
      }
    }
    
    public void onRun()
    {
      try
      {
        attemptUploadWithRetry();
        ReportUploader.access$002(ReportUploader.this, null);
        return;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          Fabric.getLogger().e("CrashlyticsCore", "An unexpected error occurred while attempting to upload crash reports.", localException);
        }
      }
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\crashlytics\android\core\ReportUploader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */