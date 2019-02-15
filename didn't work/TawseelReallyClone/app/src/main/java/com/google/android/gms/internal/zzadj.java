package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.util.List;

public class zzadj
  extends zza
{
  public static final Parcelable.Creator<zzadj> CREATOR = new zzadk();
  public final int mVersionCode;
  private final String zzQL;
  private final String zzaHA;
  private final String zzaHB;
  private final String zzaHC;
  private final List<String> zzaHD;
  private final int zzaHp;
  private final List<zzadb> zzaHq;
  private final long zzaHy;
  private final DataHolder zzaHz;
  
  zzadj(int paramInt1, String paramString1, long paramLong, DataHolder paramDataHolder, String paramString2, String paramString3, String paramString4, List<String> paramList, int paramInt2, List<zzadb> paramList1)
  {
    this.mVersionCode = paramInt1;
    this.zzQL = paramString1;
    this.zzaHy = paramLong;
    this.zzaHz = paramDataHolder;
    this.zzaHA = paramString2;
    this.zzaHB = paramString3;
    this.zzaHC = paramString4;
    this.zzaHD = paramList;
    this.zzaHp = paramInt2;
    this.zzaHq = paramList1;
  }
  
  public zzadj(String paramString1, long paramLong, DataHolder paramDataHolder, String paramString2, String paramString3, String paramString4, List<String> paramList, int paramInt, List<zzadb> paramList1)
  {
    this(2, paramString1, paramLong, paramDataHolder, paramString2, paramString3, paramString4, paramList, paramInt, paramList1);
  }
  
  public String getAppInstanceId()
  {
    return this.zzaHB;
  }
  
  public String getPackageName()
  {
    return this.zzQL;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzadk.zza(this, paramParcel, paramInt);
  }
  
  public int zzyS()
  {
    return this.zzaHp;
  }
  
  public long zzyV()
  {
    return this.zzaHy;
  }
  
  public DataHolder zzyW()
  {
    return this.zzaHz;
  }
  
  public String zzyX()
  {
    return this.zzaHA;
  }
  
  public String zzyY()
  {
    return this.zzaHC;
  }
  
  public List<String> zzyZ()
  {
    return this.zzaHD;
  }
  
  public List<zzadb> zzza()
  {
    return this.zzaHq;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzadj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */