package com.google.firebase.database.connection.idl;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.internal.zzbln;
import com.google.android.gms.internal.zzblq;
import com.google.android.gms.internal.zzblr;
import com.google.android.gms.internal.zzblr.zza;
import com.google.android.gms.internal.zzblu;
import java.util.List;
import java.util.Map;

public class zze
  implements zzblr
{
  private final zzk zzcaV;
  
  private zze(zzk paramzzk)
  {
    this.zzcaV = paramzzk;
  }
  
  public static zze zza(Context paramContext, zzc paramzzc, zzbln paramzzbln, zzblr.zza paramzza)
  {
    return new zze(IPersistentConnectionImpl.loadDynamic(paramContext, paramzzc, paramzzbln.zzVI(), paramzzbln.zzVJ(), paramzza));
  }
  
  private static zzm zza(zzblu paramzzblu)
  {
    new zzm.zza()
    {
      public void zzan(String paramAnonymousString1, String paramAnonymousString2)
        throws RemoteException
      {
        zze.this.zzan(paramAnonymousString1, paramAnonymousString2);
      }
    };
  }
  
  public void initialize()
  {
    try
    {
      this.zzcaV.initialize();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeException(localRemoteException);
    }
  }
  
  public void interrupt(String paramString)
  {
    try
    {
      this.zzcaV.interrupt(paramString);
      return;
    }
    catch (RemoteException paramString)
    {
      throw new RuntimeException(paramString);
    }
  }
  
  public boolean isInterrupted(String paramString)
  {
    try
    {
      boolean bool = this.zzcaV.isInterrupted(paramString);
      return bool;
    }
    catch (RemoteException paramString)
    {
      throw new RuntimeException(paramString);
    }
  }
  
  public void purgeOutstandingWrites()
  {
    try
    {
      this.zzcaV.purgeOutstandingWrites();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeException(localRemoteException);
    }
  }
  
  public void refreshAuthToken()
  {
    try
    {
      this.zzcaV.refreshAuthToken();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeException(localRemoteException);
    }
  }
  
  public void resume(String paramString)
  {
    try
    {
      this.zzcaV.resume(paramString);
      return;
    }
    catch (RemoteException paramString)
    {
      throw new RuntimeException(paramString);
    }
  }
  
  public void shutdown()
  {
    try
    {
      this.zzcaV.shutdown();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeException(localRemoteException);
    }
  }
  
  public void zza(List<String> paramList, zzblu paramzzblu)
  {
    try
    {
      this.zzcaV.onDisconnectCancel(paramList, zza(paramzzblu));
      return;
    }
    catch (RemoteException paramList)
    {
      throw new RuntimeException(paramList);
    }
  }
  
  public void zza(List<String> paramList, Object paramObject, zzblu paramzzblu)
  {
    try
    {
      this.zzcaV.put(paramList, com.google.android.gms.dynamic.zze.zzA(paramObject), zza(paramzzblu));
      return;
    }
    catch (RemoteException paramList)
    {
      throw new RuntimeException(paramList);
    }
  }
  
  public void zza(List<String> paramList, Object paramObject, String paramString, zzblu paramzzblu)
  {
    try
    {
      this.zzcaV.compareAndPut(paramList, com.google.android.gms.dynamic.zze.zzA(paramObject), paramString, zza(paramzzblu));
      return;
    }
    catch (RemoteException paramList)
    {
      throw new RuntimeException(paramList);
    }
  }
  
  public void zza(List<String> paramList, Map<String, Object> paramMap)
  {
    try
    {
      this.zzcaV.unlisten(paramList, com.google.android.gms.dynamic.zze.zzA(paramMap));
      return;
    }
    catch (RemoteException paramList)
    {
      throw new RuntimeException(paramList);
    }
  }
  
  public void zza(List<String> paramList, Map<String, Object> paramMap, final zzblq paramzzblq, Long paramLong, zzblu paramzzblu)
  {
    paramzzblq = new zzj.zza()
    {
      public String zzVM()
      {
        return paramzzblq.zzVM();
      }
      
      public boolean zzVN()
      {
        return paramzzblq.zzVN();
      }
      
      public zza zzWw()
      {
        return zza.zza(paramzzblq.zzVO());
      }
    };
    if (paramLong != null) {}
    for (;;)
    {
      try
      {
        l = paramLong.longValue();
        this.zzcaV.listen(paramList, com.google.android.gms.dynamic.zze.zzA(paramMap), paramzzblq, l, zza(paramzzblu));
        return;
      }
      catch (RemoteException paramList)
      {
        long l;
        throw new RuntimeException(paramList);
      }
      l = -1L;
    }
  }
  
  public void zza(List<String> paramList, Map<String, Object> paramMap, zzblu paramzzblu)
  {
    try
    {
      this.zzcaV.merge(paramList, com.google.android.gms.dynamic.zze.zzA(paramMap), zza(paramzzblu));
      return;
    }
    catch (RemoteException paramList)
    {
      throw new RuntimeException(paramList);
    }
  }
  
  public void zzb(List<String> paramList, Object paramObject, zzblu paramzzblu)
  {
    try
    {
      this.zzcaV.onDisconnectPut(paramList, com.google.android.gms.dynamic.zze.zzA(paramObject), zza(paramzzblu));
      return;
    }
    catch (RemoteException paramList)
    {
      throw new RuntimeException(paramList);
    }
  }
  
  public void zzb(List<String> paramList, Map<String, Object> paramMap, zzblu paramzzblu)
  {
    try
    {
      this.zzcaV.onDisconnectMerge(paramList, com.google.android.gms.dynamic.zze.zzA(paramMap), zza(paramzzblu));
      return;
    }
    catch (RemoteException paramList)
    {
      throw new RuntimeException(paramList);
    }
  }
  
  public void zziO(String paramString)
  {
    try
    {
      this.zzcaV.refreshAuthToken2(paramString);
      return;
    }
    catch (RemoteException paramString)
    {
      throw new RuntimeException(paramString);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\database\connection\idl\zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */