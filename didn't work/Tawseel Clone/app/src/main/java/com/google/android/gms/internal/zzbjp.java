package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzh;

public class zzbjp
  extends zza
{
  public static final Parcelable.Creator<zzbjp> CREATOR = new zzbjq();
  @zzbjd
  public final int mVersionCode;
  @zzbsg("access_token")
  private String zzbBR;
  @zzbsg("refresh_token")
  private String zzbVO;
  @zzbsg("expires_in")
  private Long zzbWv;
  @zzbsg("token_type")
  private String zzbWw;
  @zzbsg("issued_at")
  private Long zzbWx;
  
  public zzbjp()
  {
    this.mVersionCode = 1;
    this.zzbWx = Long.valueOf(System.currentTimeMillis());
  }
  
  zzbjp(int paramInt, String paramString1, String paramString2, Long paramLong1, String paramString3, Long paramLong2)
  {
    this.mVersionCode = paramInt;
    this.zzbVO = paramString1;
    this.zzbBR = paramString2;
    this.zzbWv = paramLong1;
    this.zzbWw = paramString3;
    this.zzbWx = paramLong2;
  }
  
  public String getAccessToken()
  {
    return this.zzbBR;
  }
  
  public boolean isValid()
  {
    long l1 = this.zzbWx.longValue();
    long l2 = this.zzbWv.longValue();
    return zzh.zzyv().currentTimeMillis() + 300000L < l1 + l2 * 1000L;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzbjq.zza(this, paramParcel, paramInt);
  }
  
  public String zzUs()
  {
    return this.zzbVO;
  }
  
  public long zzUt()
  {
    if (this.zzbWv == null) {
      return 0L;
    }
    return this.zzbWv.longValue();
  }
  
  @Nullable
  public String zzUu()
  {
    return this.zzbWw;
  }
  
  public long zzUv()
  {
    return this.zzbWx.longValue();
  }
  
  public void zziz(@NonNull String paramString)
  {
    this.zzbVO = zzac.zzdv(paramString);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbjp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */