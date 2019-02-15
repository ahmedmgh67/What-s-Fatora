package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

 enum zzbmh
  implements zzbmk
{
  static ThreadFactory zzcbX;
  static final zzbmv zzcbY = new zzbmv()
  {
    public void zza(Thread paramAnonymousThread, String paramAnonymousString) {}
    
    public void zza(Thread paramAnonymousThread, Thread.UncaughtExceptionHandler paramAnonymousUncaughtExceptionHandler)
    {
      paramAnonymousThread.setUncaughtExceptionHandler(paramAnonymousUncaughtExceptionHandler);
    }
    
    public void zza(Thread paramAnonymousThread, boolean paramAnonymousBoolean) {}
  };
  
  private zzbmh() {}
  
  public static boolean isActive()
  {
    return zzXd() != null;
  }
  
  private static ThreadFactory zzXd()
  {
    if (zzcbX == null) {}
    try
    {
      Class localClass = Class.forName("com.google.appengine.api.ThreadManager");
      if (localClass != null) {
        zzcbX = (ThreadFactory)localClass.getMethod("backgroundThreadFactory", new Class[0]).invoke(null, new Object[0]);
      }
      return zzcbX;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      return null;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new RuntimeException(localInvocationTargetException);
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      throw new RuntimeException(localNoSuchMethodException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new RuntimeException(localIllegalAccessException);
    }
  }
  
  public void initialize()
  {
    zzbpo.zza(zzcbX, new zzbpn()
    {
      public void zza(Thread paramAnonymousThread, String paramAnonymousString)
      {
        zzbmh.zzcbY.zza(paramAnonymousThread, paramAnonymousString);
      }
    });
  }
  
  public zzblr zza(zzbmc paramzzbmc, zzbln paramzzbln, zzblp paramzzblp, zzblr.zza paramzza)
  {
    return new zzbls(paramzzbmc.zzWO(), paramzzblp, paramzza);
  }
  
  public zzbly zza(ScheduledExecutorService paramScheduledExecutorService)
  {
    throw new RuntimeException("Authentication is not implemented yet");
  }
  
  public zzbmg zza(zzbmc paramzzbmc)
  {
    return new zzbmw(zzXd(), zzcbY);
  }
  
  public zzbnn zza(zzbmc paramzzbmc, String paramString)
  {
    return null;
  }
  
  public zzboq zza(zzbmc paramzzbmc, zzboq.zza paramzza, List<String> paramList)
  {
    return new zzboo(paramzza, paramList);
  }
  
  public zzbmo zzb(zzbmc paramzzbmc)
  {
    new zzbqa()
    {
      protected ThreadFactory getThreadFactory()
      {
        return zzbmh.zzcbX;
      }
      
      protected zzbmv zzXe()
      {
        return zzbmh.zzcbY;
      }
      
      public void zzj(Throwable paramAnonymousThrowable)
      {
        this.zzbYs.zzd(zzbqa.zzl(paramAnonymousThrowable), paramAnonymousThrowable);
      }
    };
  }
  
  public String zzc(zzbmc paramzzbmc)
  {
    paramzzbmc = System.getProperty("java.specification.version", "Unknown");
    return String.valueOf(paramzzbmc).length() + 1 + String.valueOf("AppEngine").length() + paramzzbmc + "/" + "AppEngine";
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbmh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */