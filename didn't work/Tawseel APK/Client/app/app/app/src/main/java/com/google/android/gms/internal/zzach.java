package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.SparseArray;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public final class zzach
  extends zza
  implements zzack.zzb<String, Integer>
{
  public static final Parcelable.Creator<zzach> CREATOR = new zzaci();
  final int mVersionCode;
  private final HashMap<String, Integer> zzaFv;
  private final SparseArray<String> zzaFw;
  private final ArrayList<zza> zzaFx;
  
  public zzach()
  {
    this.mVersionCode = 1;
    this.zzaFv = new HashMap();
    this.zzaFw = new SparseArray();
    this.zzaFx = null;
  }
  
  zzach(int paramInt, ArrayList<zza> paramArrayList)
  {
    this.mVersionCode = paramInt;
    this.zzaFv = new HashMap();
    this.zzaFw = new SparseArray();
    this.zzaFx = null;
    zzh(paramArrayList);
  }
  
  private void zzh(ArrayList<zza> paramArrayList)
  {
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      zza localzza = (zza)paramArrayList.next();
      zzj(localzza.zzaFy, localzza.zzaFz);
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzaci.zza(this, paramParcel, paramInt);
  }
  
  public String zzd(Integer paramInteger)
  {
    String str = (String)this.zzaFw.get(paramInteger.intValue());
    paramInteger = str;
    if (str == null)
    {
      paramInteger = str;
      if (this.zzaFv.containsKey("gms_unknown")) {
        paramInteger = "gms_unknown";
      }
    }
    return paramInteger;
  }
  
  public zzach zzj(String paramString, int paramInt)
  {
    this.zzaFv.put(paramString, Integer.valueOf(paramInt));
    this.zzaFw.put(paramInt, paramString);
    return this;
  }
  
  ArrayList<zza> zzxJ()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.zzaFv.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localArrayList.add(new zza(str, ((Integer)this.zzaFv.get(str)).intValue()));
    }
    return localArrayList;
  }
  
  public static final class zza
    extends zza
  {
    public static final Parcelable.Creator<zza> CREATOR = new zzacj();
    final int versionCode;
    final String zzaFy;
    final int zzaFz;
    
    zza(int paramInt1, String paramString, int paramInt2)
    {
      this.versionCode = paramInt1;
      this.zzaFy = paramString;
      this.zzaFz = paramInt2;
    }
    
    zza(String paramString, int paramInt)
    {
      this.versionCode = 1;
      this.zzaFy = paramString;
      this.zzaFz = paramInt;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      zzacj.zza(this, paramParcel, paramInt);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzach.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */