package com.google.firebase.database.connection.idl;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.zza;
import com.google.android.gms.internal.zzblk;
import com.google.android.gms.internal.zzblm;
import com.google.android.gms.internal.zzblm.zza;
import com.google.android.gms.internal.zzbln;
import com.google.android.gms.internal.zzblp;
import com.google.android.gms.internal.zzblq;
import com.google.android.gms.internal.zzblr;
import com.google.android.gms.internal.zzblr.zza;
import com.google.android.gms.internal.zzbls;
import com.google.android.gms.internal.zzblt;
import com.google.android.gms.internal.zzblu;
import com.google.android.gms.internal.zzbon;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

@DynamiteApi
public class IPersistentConnectionImpl
  extends zzk.zza
{
  private zzblr zzcaY;
  
  public static zzk loadDynamic(Context paramContext, zzc paramzzc, zzblm paramzzblm, ScheduledExecutorService paramScheduledExecutorService, zzblr.zza paramzza)
  {
    try
    {
      paramContext = zzk.zza.asInterface(DynamiteModule.zza(paramContext, DynamiteModule.zzaQz, "com.google.android.gms.firebase_database").zzdX("com.google.firebase.database.connection.idl.IPersistentConnectionImpl"));
      paramContext.setup(paramzzc, zza(paramzzblm), zze.zzA(paramScheduledExecutorService), zza(paramzza));
      return paramContext;
    }
    catch (DynamiteModule.zza paramContext)
    {
      throw new RuntimeException(paramContext);
    }
    catch (RemoteException paramContext)
    {
      throw new RuntimeException(paramContext);
    }
  }
  
  private static long zza(Long paramLong)
  {
    long l = -1L;
    if (paramLong != null)
    {
      if (paramLong.longValue() == -1L) {
        throw new IllegalArgumentException("Tag parameter clashed with NO_TAG value");
      }
      l = paramLong.longValue();
    }
    return l;
  }
  
  private static zzblm zza(zzh paramzzh)
  {
    new zzblm()
    {
      public void zza(boolean paramAnonymousBoolean, final zzblm.zza paramAnonymouszza)
      {
        try
        {
          IPersistentConnectionImpl.this.zza(paramAnonymousBoolean, new zzi.zza()
          {
            public void onError(String paramAnonymous2String)
              throws RemoteException
            {
              paramAnonymouszza.onError(paramAnonymous2String);
            }
            
            public void zziM(String paramAnonymous2String)
              throws RemoteException
            {
              paramAnonymouszza.zziM(paramAnonymous2String);
            }
          });
          return;
        }
        catch (RemoteException paramAnonymouszza)
        {
          throw new RuntimeException(paramAnonymouszza);
        }
      }
    };
  }
  
  private static zzblr.zza zza(zzl paramzzl)
  {
    new zzblr.zza()
    {
      public void onDisconnect()
      {
        try
        {
          IPersistentConnectionImpl.this.onDisconnect();
          return;
        }
        catch (RemoteException localRemoteException)
        {
          throw new RuntimeException(localRemoteException);
        }
      }
      
      public void zzVP()
      {
        try
        {
          IPersistentConnectionImpl.this.zzVP();
          return;
        }
        catch (RemoteException localRemoteException)
        {
          throw new RuntimeException(localRemoteException);
        }
      }
      
      public void zza(List<String> paramAnonymousList, Object paramAnonymousObject, boolean paramAnonymousBoolean, Long paramAnonymousLong)
      {
        try
        {
          IPersistentConnectionImpl.this.zza(paramAnonymousList, zze.zzA(paramAnonymousObject), paramAnonymousBoolean, IPersistentConnectionImpl.zzb(paramAnonymousLong));
          return;
        }
        catch (RemoteException paramAnonymousList)
        {
          throw new RuntimeException(paramAnonymousList);
        }
      }
      
      public void zza(List<String> paramAnonymousList, List<zzblt> paramAnonymousList1, Long paramAnonymousLong)
      {
        ArrayList localArrayList1 = new ArrayList(paramAnonymousList1.size());
        ArrayList localArrayList2 = new ArrayList(paramAnonymousList1.size());
        paramAnonymousList1 = paramAnonymousList1.iterator();
        while (paramAnonymousList1.hasNext())
        {
          zzblt localzzblt = (zzblt)paramAnonymousList1.next();
          localArrayList1.add(zzn.zza(localzzblt));
          localArrayList2.add(localzzblt.zzWo());
        }
        try
        {
          IPersistentConnectionImpl.this.zza(paramAnonymousList, localArrayList1, zze.zzA(localArrayList2), IPersistentConnectionImpl.zzb(paramAnonymousLong));
          return;
        }
        catch (RemoteException paramAnonymousList)
        {
          throw new RuntimeException(paramAnonymousList);
        }
      }
      
      public void zzaX(boolean paramAnonymousBoolean)
      {
        try
        {
          IPersistentConnectionImpl.this.zzaX(paramAnonymousBoolean);
          return;
        }
        catch (RemoteException localRemoteException)
        {
          throw new RuntimeException(localRemoteException);
        }
      }
      
      public void zzaw(Map<String, Object> paramAnonymousMap)
      {
        try
        {
          IPersistentConnectionImpl.this.zzP(zze.zzA(paramAnonymousMap));
          return;
        }
        catch (RemoteException paramAnonymousMap)
        {
          throw new RuntimeException(paramAnonymousMap);
        }
      }
    };
  }
  
  private static zzblu zza(zzm paramzzm)
  {
    new zzblu()
    {
      public void zzan(String paramAnonymousString1, String paramAnonymousString2)
      {
        try
        {
          IPersistentConnectionImpl.this.zzan(paramAnonymousString1, paramAnonymousString2);
          return;
        }
        catch (RemoteException paramAnonymousString1)
        {
          throw new RuntimeException(paramAnonymousString1);
        }
      }
    };
  }
  
  private static zzh zza(zzblm paramzzblm)
  {
    new zzh.zza()
    {
      public void zza(boolean paramAnonymousBoolean, final zzi paramAnonymouszzi)
        throws RemoteException
      {
        IPersistentConnectionImpl.this.zza(paramAnonymousBoolean, new zzblm.zza()
        {
          public void onError(String paramAnonymous2String)
          {
            try
            {
              paramAnonymouszzi.onError(paramAnonymous2String);
              return;
            }
            catch (RemoteException paramAnonymous2String)
            {
              throw new RuntimeException(paramAnonymous2String);
            }
          }
          
          public void zziM(String paramAnonymous2String)
          {
            try
            {
              paramAnonymouszzi.zziM(paramAnonymous2String);
              return;
            }
            catch (RemoteException paramAnonymous2String)
            {
              throw new RuntimeException(paramAnonymous2String);
            }
          }
        });
      }
    };
  }
  
  private static zzl zza(zzblr.zza paramzza)
  {
    new zzl.zza()
    {
      public void onDisconnect()
      {
        IPersistentConnectionImpl.this.onDisconnect();
      }
      
      public void zzP(zzd paramAnonymouszzd)
      {
        IPersistentConnectionImpl.this.zzaw((Map)zze.zzE(paramAnonymouszzd));
      }
      
      public void zzVP()
      {
        IPersistentConnectionImpl.this.zzVP();
      }
      
      public void zza(List<String> paramAnonymousList, zzd paramAnonymouszzd, boolean paramAnonymousBoolean, long paramAnonymousLong)
      {
        IPersistentConnectionImpl.this.zza(paramAnonymousList, zze.zzE(paramAnonymouszzd), paramAnonymousBoolean, IPersistentConnectionImpl.zzaH(paramAnonymousLong));
      }
      
      public void zza(List<String> paramAnonymousList, List<zzn> paramAnonymousList1, zzd paramAnonymouszzd, long paramAnonymousLong)
      {
        paramAnonymouszzd = (List)zze.zzE(paramAnonymouszzd);
        ArrayList localArrayList = new ArrayList(paramAnonymousList1.size());
        int i = 0;
        while (i < paramAnonymousList1.size())
        {
          localArrayList.add(zzn.zza((zzn)paramAnonymousList1.get(i), paramAnonymouszzd.get(i)));
          i += 1;
        }
        IPersistentConnectionImpl.this.zza(paramAnonymousList, localArrayList, IPersistentConnectionImpl.zzaH(paramAnonymousLong));
      }
      
      public void zzaX(boolean paramAnonymousBoolean)
      {
        IPersistentConnectionImpl.this.zzaX(paramAnonymousBoolean);
      }
    };
  }
  
  private static Long zzaG(long paramLong)
  {
    if (paramLong == -1L) {
      return null;
    }
    return Long.valueOf(paramLong);
  }
  
  public void compareAndPut(List<String> paramList, zzd paramzzd, String paramString, zzm paramzzm)
  {
    this.zzcaY.zza(paramList, zze.zzE(paramzzd), paramString, zza(paramzzm));
  }
  
  public void initialize()
  {
    this.zzcaY.initialize();
  }
  
  public void interrupt(String paramString)
  {
    this.zzcaY.interrupt(paramString);
  }
  
  public boolean isInterrupted(String paramString)
  {
    return this.zzcaY.isInterrupted(paramString);
  }
  
  public void listen(List<String> paramList, zzd paramzzd, final zzj paramzzj, long paramLong, zzm paramzzm)
  {
    Long localLong = zzaG(paramLong);
    paramzzd = (Map)zze.zzE(paramzzd);
    paramzzj = new zzblq()
    {
      public String zzVM()
      {
        try
        {
          String str = paramzzj.zzVM();
          return str;
        }
        catch (RemoteException localRemoteException)
        {
          throw new RuntimeException(localRemoteException);
        }
      }
      
      public boolean zzVN()
      {
        try
        {
          boolean bool = paramzzj.zzVN();
          return bool;
        }
        catch (RemoteException localRemoteException)
        {
          throw new RuntimeException(localRemoteException);
        }
      }
      
      public zzblk zzVO()
      {
        try
        {
          zzblk localzzblk = zza.zza(paramzzj.zzWw());
          return localzzblk;
        }
        catch (RemoteException localRemoteException)
        {
          throw new RuntimeException(localRemoteException);
        }
      }
    };
    this.zzcaY.zza(paramList, paramzzd, paramzzj, localLong, zza(paramzzm));
  }
  
  public void merge(List<String> paramList, zzd paramzzd, zzm paramzzm)
  {
    this.zzcaY.zza(paramList, (Map)zze.zzE(paramzzd), zza(paramzzm));
  }
  
  public void onDisconnectCancel(List<String> paramList, zzm paramzzm)
  {
    this.zzcaY.zza(paramList, zza(paramzzm));
  }
  
  public void onDisconnectMerge(List<String> paramList, zzd paramzzd, zzm paramzzm)
  {
    this.zzcaY.zzb(paramList, (Map)zze.zzE(paramzzd), zza(paramzzm));
  }
  
  public void onDisconnectPut(List<String> paramList, zzd paramzzd, zzm paramzzm)
  {
    this.zzcaY.zzb(paramList, zze.zzE(paramzzd), zza(paramzzm));
  }
  
  public void purgeOutstandingWrites()
  {
    this.zzcaY.purgeOutstandingWrites();
  }
  
  public void put(List<String> paramList, zzd paramzzd, zzm paramzzm)
  {
    this.zzcaY.zza(paramList, zze.zzE(paramzzd), zza(paramzzm));
  }
  
  public void refreshAuthToken()
  {
    this.zzcaY.refreshAuthToken();
  }
  
  public void refreshAuthToken2(String paramString)
  {
    this.zzcaY.zziO(paramString);
  }
  
  public void resume(String paramString)
  {
    this.zzcaY.resume(paramString);
  }
  
  public void setup(zzc paramzzc, zzh paramzzh, zzd paramzzd, zzl paramzzl)
  {
    zzblp localzzblp = zzf.zza(paramzzc.zzcaQ);
    paramzzd = (ScheduledExecutorService)zze.zzE(paramzzd);
    paramzzl = zza(paramzzl);
    this.zzcaY = new zzbls(new zzbln(new zzbon(paramzzc.zzWu(), paramzzc.zzWv()), zza(paramzzh), paramzzd, paramzzc.zzbZx, paramzzc.zzcaT, paramzzc.zzbZz), localzzblp, paramzzl);
  }
  
  public void shutdown()
  {
    this.zzcaY.shutdown();
  }
  
  public void unlisten(List<String> paramList, zzd paramzzd)
  {
    this.zzcaY.zza(paramList, (Map)zze.zzE(paramzzd));
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\database\connection\idl\IPersistentConnectionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */