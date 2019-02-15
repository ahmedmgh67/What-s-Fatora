package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

public class zzbjv
  extends zza
{
  public static final Parcelable.Creator<zzbjv> CREATOR = new zzbjw();
  @zzbjd
  public final int mVersionCode;
  @zzbsg("email")
  private String zzaiW;
  @zzbsg("newEmail")
  private String zzbWB;
  @zzbsg("requestType")
  private String zzbWC;
  
  public zzbjv()
  {
    this.mVersionCode = 1;
  }
  
  zzbjv(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    this.mVersionCode = paramInt;
    this.zzaiW = paramString1;
    this.zzbWB = paramString2;
    this.zzbWC = paramString3;
  }
  
  public String getEmail()
  {
    return this.zzaiW;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzbjw.zza(this, paramParcel, paramInt);
  }
  
  public String zzUy()
  {
    return this.zzbWB;
  }
  
  public String zzUz()
  {
    return this.zzbWC;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbjv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */