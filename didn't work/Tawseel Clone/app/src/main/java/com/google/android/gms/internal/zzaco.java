package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzac;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class zzaco
  extends zza
{
  public static final Parcelable.Creator<zzaco> CREATOR = new zzacp();
  final int mVersionCode;
  private final HashMap<String, Map<String, zzack.zza<?, ?>>> zzaFK;
  private final ArrayList<zza> zzaFL;
  private final String zzaFM;
  
  zzaco(int paramInt, ArrayList<zza> paramArrayList, String paramString)
  {
    this.mVersionCode = paramInt;
    this.zzaFL = null;
    this.zzaFK = zzi(paramArrayList);
    this.zzaFM = ((String)zzac.zzw(paramString));
    zzxW();
  }
  
  private static HashMap<String, Map<String, zzack.zza<?, ?>>> zzi(ArrayList<zza> paramArrayList)
  {
    HashMap localHashMap = new HashMap();
    int j = paramArrayList.size();
    int i = 0;
    while (i < j)
    {
      zza localzza = (zza)paramArrayList.get(i);
      localHashMap.put(localzza.className, localzza.zzxZ());
      i += 1;
    }
    return localHashMap;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator1 = this.zzaFK.keySet().iterator();
    while (localIterator1.hasNext())
    {
      Object localObject = (String)localIterator1.next();
      localStringBuilder.append((String)localObject).append(":\n");
      localObject = (Map)this.zzaFK.get(localObject);
      Iterator localIterator2 = ((Map)localObject).keySet().iterator();
      while (localIterator2.hasNext())
      {
        String str = (String)localIterator2.next();
        localStringBuilder.append("  ").append(str).append(": ");
        localStringBuilder.append(((Map)localObject).get(str));
      }
    }
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzacp.zza(this, paramParcel, paramInt);
  }
  
  public Map<String, zzack.zza<?, ?>> zzdA(String paramString)
  {
    return (Map)this.zzaFK.get(paramString);
  }
  
  public void zzxW()
  {
    Iterator localIterator1 = this.zzaFK.keySet().iterator();
    while (localIterator1.hasNext())
    {
      Object localObject = (String)localIterator1.next();
      localObject = (Map)this.zzaFK.get(localObject);
      Iterator localIterator2 = ((Map)localObject).keySet().iterator();
      while (localIterator2.hasNext()) {
        ((zzack.zza)((Map)localObject).get((String)localIterator2.next())).zza(this);
      }
    }
  }
  
  ArrayList<zza> zzxX()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.zzaFK.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localArrayList.add(new zza(str, (Map)this.zzaFK.get(str)));
    }
    return localArrayList;
  }
  
  public String zzxY()
  {
    return this.zzaFM;
  }
  
  public static class zza
    extends zza
  {
    public static final Parcelable.Creator<zza> CREATOR = new zzacq();
    final String className;
    final int versionCode;
    final ArrayList<zzaco.zzb> zzaFN;
    
    zza(int paramInt, String paramString, ArrayList<zzaco.zzb> paramArrayList)
    {
      this.versionCode = paramInt;
      this.className = paramString;
      this.zzaFN = paramArrayList;
    }
    
    zza(String paramString, Map<String, zzack.zza<?, ?>> paramMap)
    {
      this.versionCode = 1;
      this.className = paramString;
      this.zzaFN = zzW(paramMap);
    }
    
    private static ArrayList<zzaco.zzb> zzW(Map<String, zzack.zza<?, ?>> paramMap)
    {
      if (paramMap == null) {
        return null;
      }
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = paramMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        localArrayList.add(new zzaco.zzb(str, (zzack.zza)paramMap.get(str)));
      }
      return localArrayList;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      zzacq.zza(this, paramParcel, paramInt);
    }
    
    HashMap<String, zzack.zza<?, ?>> zzxZ()
    {
      HashMap localHashMap = new HashMap();
      int j = this.zzaFN.size();
      int i = 0;
      while (i < j)
      {
        zzaco.zzb localzzb = (zzaco.zzb)this.zzaFN.get(i);
        localHashMap.put(localzzb.zzaA, localzzb.zzaFO);
        i += 1;
      }
      return localHashMap;
    }
  }
  
  public static class zzb
    extends zza
  {
    public static final Parcelable.Creator<zzb> CREATOR = new zzacn();
    final int versionCode;
    final String zzaA;
    final zzack.zza<?, ?> zzaFO;
    
    zzb(int paramInt, String paramString, zzack.zza<?, ?> paramzza)
    {
      this.versionCode = paramInt;
      this.zzaA = paramString;
      this.zzaFO = paramzza;
    }
    
    zzb(String paramString, zzack.zza<?, ?> paramzza)
    {
      this.versionCode = 1;
      this.zzaA = paramString;
      this.zzaFO = paramzza;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      zzacn.zza(this, paramParcel, paramInt);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzaco.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */