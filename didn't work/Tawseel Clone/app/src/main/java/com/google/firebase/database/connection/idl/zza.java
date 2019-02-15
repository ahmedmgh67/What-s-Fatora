package com.google.firebase.database.connection.idl;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzblk;
import com.google.android.gms.internal.zzblo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class zza
  extends com.google.android.gms.common.internal.safeparcel.zza
{
  public static final Parcelable.Creator<zza> CREATOR = new zzb();
  final int versionCode;
  final List<String> zzbZh;
  final List<String> zzbZi;
  
  public zza(int paramInt, List<String> paramList1, List<String> paramList2)
  {
    this.versionCode = paramInt;
    this.zzbZh = paramList1;
    this.zzbZi = paramList2;
  }
  
  public static zzblk zza(zza paramzza)
  {
    ArrayList localArrayList = new ArrayList(paramzza.zzbZh.size());
    Iterator localIterator = paramzza.zzbZh.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(zzblo.zziN((String)localIterator.next()));
    }
    return new zzblk(localArrayList, paramzza.zzbZi);
  }
  
  public static zza zza(zzblk paramzzblk)
  {
    Object localObject = paramzzblk.zzVF();
    ArrayList localArrayList = new ArrayList(((List)localObject).size());
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      localArrayList.add(zzblo.zzT((List)((Iterator)localObject).next()));
    }
    return new zza(1, localArrayList, paramzzblk.zzVG());
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb.zza(this, paramParcel, paramInt);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\database\connection\idl\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */