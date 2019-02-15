package com.google.android.gms.internal;

public abstract interface zzbmv
{
  public static final zzbmv zzced = new zzbmv()
  {
    public void zza(Thread paramAnonymousThread, String paramAnonymousString)
    {
      paramAnonymousThread.setName(paramAnonymousString);
    }
    
    public void zza(Thread paramAnonymousThread, Thread.UncaughtExceptionHandler paramAnonymousUncaughtExceptionHandler)
    {
      paramAnonymousThread.setUncaughtExceptionHandler(paramAnonymousUncaughtExceptionHandler);
    }
    
    public void zza(Thread paramAnonymousThread, boolean paramAnonymousBoolean)
    {
      paramAnonymousThread.setDaemon(paramAnonymousBoolean);
    }
  };
  
  public abstract void zza(Thread paramThread, String paramString);
  
  public abstract void zza(Thread paramThread, Thread.UncaughtExceptionHandler paramUncaughtExceptionHandler);
  
  public abstract void zza(Thread paramThread, boolean paramBoolean);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbmv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */