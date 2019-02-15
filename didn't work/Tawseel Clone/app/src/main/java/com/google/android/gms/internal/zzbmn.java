package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class zzbmn
{
  private static final zzbmn zzcdn = new zzbmn();
  private final Map<zzbmc, Map<String, zzbml>> zzcdo = new HashMap();
  
  public static zzbml zza(zzbmc paramzzbmc, zzbmm paramzzbmm, FirebaseDatabase paramFirebaseDatabase)
    throws DatabaseException
  {
    return zzcdn.zzb(paramzzbmc, paramzzbmm, paramFirebaseDatabase);
  }
  
  private zzbml zzb(zzbmc paramzzbmc, zzbmm paramzzbmm, FirebaseDatabase paramFirebaseDatabase)
    throws DatabaseException
  {
    paramzzbmc.zzWz();
    ??? = paramzzbmm.zzbZA;
    String str = paramzzbmm.zzaFs;
    str = String.valueOf(???).length() + 9 + String.valueOf(str).length() + "https://" + (String)??? + "/" + str;
    synchronized (this.zzcdo)
    {
      if (!this.zzcdo.containsKey(paramzzbmc))
      {
        localObject2 = new HashMap();
        this.zzcdo.put(paramzzbmc, localObject2);
      }
      Object localObject2 = (Map)this.zzcdo.get(paramzzbmc);
      if (!((Map)localObject2).containsKey(str))
      {
        paramzzbmc = new zzbml(paramzzbmm, paramzzbmc, paramFirebaseDatabase);
        ((Map)localObject2).put(str, paramzzbmc);
        return paramzzbmc;
      }
      throw new IllegalStateException("createLocalRepo() called for existing repo.");
    }
  }
  
  public static void zzd(zzbmc paramzzbmc)
  {
    zzcdn.zzf(paramzzbmc);
  }
  
  public static void zze(zzbmc paramzzbmc)
  {
    zzcdn.zzg(paramzzbmc);
  }
  
  private void zzf(final zzbmc paramzzbmc)
  {
    zzbmo localzzbmo = paramzzbmc.zzWR();
    if (localzzbmo != null) {
      localzzbmo.zzs(new Runnable()
      {
        public void run()
        {
          int i;
          synchronized (zzbmn.zza(zzbmn.this))
          {
            if (zzbmn.zza(zzbmn.this).containsKey(paramzzbmc))
            {
              Iterator localIterator = ((Map)zzbmn.zza(zzbmn.this).get(paramzzbmc)).values().iterator();
              i = 1;
              if (localIterator.hasNext())
              {
                zzbml localzzbml = (zzbml)localIterator.next();
                localzzbml.interrupt();
                if ((i == 0) || (localzzbml.zzXq())) {
                  break label125;
                }
                i = 1;
                break label122;
              }
              if (i != 0) {
                paramzzbmc.stop();
              }
            }
            return;
          }
          for (;;)
          {
            label122:
            break;
            label125:
            i = 0;
          }
        }
      });
    }
  }
  
  private void zzg(final zzbmc paramzzbmc)
  {
    zzbmo localzzbmo = paramzzbmc.zzWR();
    if (localzzbmo != null) {
      localzzbmo.zzs(new Runnable()
      {
        public void run()
        {
          synchronized (zzbmn.zza(zzbmn.this))
          {
            if (zzbmn.zza(zzbmn.this).containsKey(paramzzbmc))
            {
              Iterator localIterator = ((Map)zzbmn.zza(zzbmn.this).get(paramzzbmc)).values().iterator();
              if (localIterator.hasNext()) {
                ((zzbml)localIterator.next()).resume();
              }
            }
          }
        }
      });
    }
  }
  
  public static void zzk(zzbml paramzzbml)
  {
    paramzzbml.zzs(new Runnable()
    {
      public void run()
      {
        zzbmn.this.interrupt();
      }
    });
  }
  
  public static void zzl(zzbml paramzzbml)
  {
    paramzzbml.zzs(new Runnable()
    {
      public void run()
      {
        zzbmn.this.resume();
      }
    });
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbmn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */