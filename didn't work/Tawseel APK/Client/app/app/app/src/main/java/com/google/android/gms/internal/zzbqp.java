package com.google.android.gms.internal;

import android.content.Context;
import android.util.Log;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class zzbqp
  implements Runnable
{
  public final Context mContext;
  public final zzbqt zzcjD;
  public final zzbqq zzcjK;
  public final zzbqq zzcjL;
  public final zzbqq zzcjM;
  
  public zzbqp(Context paramContext, zzbqq paramzzbqq1, zzbqq paramzzbqq2, zzbqq paramzzbqq3, zzbqt paramzzbqt)
  {
    this.mContext = paramContext;
    this.zzcjK = paramzzbqq1;
    this.zzcjL = paramzzbqq2;
    this.zzcjM = paramzzbqq3;
    this.zzcjD = paramzzbqt;
  }
  
  private zzbqu.zza zza(zzbqq paramzzbqq)
  {
    zzbqu.zza localzza = new zzbqu.zza();
    if (paramzzbqq.zzaav() != null)
    {
      Map localMap = paramzzbqq.zzaav();
      ArrayList localArrayList1 = new ArrayList();
      Iterator localIterator1 = localMap.keySet().iterator();
      while (localIterator1.hasNext())
      {
        String str1 = (String)localIterator1.next();
        ArrayList localArrayList2 = new ArrayList();
        Object localObject = (Map)localMap.get(str1);
        Iterator localIterator2 = ((Map)localObject).keySet().iterator();
        while (localIterator2.hasNext())
        {
          String str2 = (String)localIterator2.next();
          zzbqu.zzb localzzb = new zzbqu.zzb();
          localzzb.zzaA = str2;
          localzzb.zzcjW = ((byte[])((Map)localObject).get(str2));
          localArrayList2.add(localzzb);
        }
        localObject = new zzbqu.zzd();
        ((zzbqu.zzd)localObject).zzaFs = str1;
        ((zzbqu.zzd)localObject).zzcka = ((zzbqu.zzb[])localArrayList2.toArray(new zzbqu.zzb[localArrayList2.size()]));
        localArrayList1.add(localObject);
      }
      localzza.zzcjU = ((zzbqu.zzd[])localArrayList1.toArray(new zzbqu.zzd[localArrayList1.size()]));
    }
    localzza.timestamp = paramzzbqq.getTimestamp();
    return localzza;
  }
  
  public void run()
  {
    Object localObject1 = new zzbqu.zze();
    if (this.zzcjK != null) {
      ((zzbqu.zze)localObject1).zzckb = zza(this.zzcjK);
    }
    if (this.zzcjL != null) {
      ((zzbqu.zze)localObject1).zzckc = zza(this.zzcjL);
    }
    if (this.zzcjM != null) {
      ((zzbqu.zze)localObject1).zzckd = zza(this.zzcjM);
    }
    Object localObject2;
    if (this.zzcjD != null)
    {
      localObject2 = new zzbqu.zzc();
      ((zzbqu.zzc)localObject2).zzcjX = this.zzcjD.getLastFetchStatus();
      ((zzbqu.zzc)localObject2).zzcjY = this.zzcjD.isDeveloperModeEnabled();
      ((zzbqu.zze)localObject1).zzcke = ((zzbqu.zzc)localObject2);
    }
    if ((this.zzcjD != null) && (this.zzcjD.zzaay() != null))
    {
      localObject2 = new ArrayList();
      Map localMap = this.zzcjD.zzaay();
      Iterator localIterator = localMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        if (localMap.get(str) != null)
        {
          zzbqu.zzf localzzf = new zzbqu.zzf();
          localzzf.zzaFs = str;
          localzzf.zzckh = ((zzbqo)localMap.get(str)).zzaau();
          localzzf.resourceId = ((zzbqo)localMap.get(str)).zzaat();
          ((List)localObject2).add(localzzf);
        }
      }
      ((zzbqu.zze)localObject1).zzckf = ((zzbqu.zzf[])((List)localObject2).toArray(new zzbqu.zzf[((List)localObject2).size()]));
    }
    localObject1 = zzbut.zzf((zzbut)localObject1);
    try
    {
      localObject2 = this.mContext.openFileOutput("persisted_config", 0);
      ((FileOutputStream)localObject2).write((byte[])localObject1);
      ((FileOutputStream)localObject2).close();
      return;
    }
    catch (IOException localIOException)
    {
      Log.e("AsyncPersisterTask", "Could not persist config.", localIOException);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbqp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */