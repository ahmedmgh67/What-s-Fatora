package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.util.List;

public class zzbjj
  extends zza
{
  public static final Parcelable.Creator<zzbjj> CREATOR = new zzbjk();
  @zzbjd
  public final int mVersionCode;
  @zzbsg("registered")
  private boolean zzafv;
  @zzbsg("authUri")
  private String zzbWo;
  @zzbsg("providerId")
  private String zzbWp;
  @zzbsg("forExistingProvider")
  private boolean zzbWq;
  @zzbsg("allProviders")
  private zzbjx zzbWr;
  
  public zzbjj()
  {
    this.mVersionCode = 1;
    this.zzbWr = zzbjx.zzUB();
  }
  
  zzbjj(int paramInt, String paramString1, boolean paramBoolean1, String paramString2, boolean paramBoolean2, zzbjx paramzzbjx)
  {
    this.mVersionCode = paramInt;
    this.zzbWo = paramString1;
    this.zzafv = paramBoolean1;
    this.zzbWp = paramString2;
    this.zzbWq = paramBoolean2;
    if (paramzzbjx == null) {}
    for (paramString1 = zzbjx.zzUB();; paramString1 = zzbjx.zza(paramzzbjx))
    {
      this.zzbWr = paramString1;
      return;
    }
  }
  
  @Nullable
  public List<String> getAllProviders()
  {
    return this.zzbWr.zzUA();
  }
  
  @Nullable
  public String getProviderId()
  {
    return this.zzbWp;
  }
  
  public boolean isRegistered()
  {
    return this.zzafv;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzbjk.zza(this, paramParcel, paramInt);
  }
  
  @Nullable
  public String zzUm()
  {
    return this.zzbWo;
  }
  
  public boolean zzUn()
  {
    return this.zzbWq;
  }
  
  public zzbjx zzUo()
  {
    return this.zzbWr;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbjj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */