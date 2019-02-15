package com.google.android.gms.internal;

public class zzbni
  extends zzbng
{
  private final zzbpe zzceR;
  
  public zzbni(zzbnh paramzzbnh, zzbmj paramzzbmj, zzbpe paramzzbpe)
  {
    super(zzbng.zza.zzceE, paramzzbnh, paramzzbmj);
    this.zzceR = paramzzbpe;
  }
  
  public String toString()
  {
    return String.format("Overwrite { path=%s, source=%s, snapshot=%s }", new Object[] { zzVc(), zzXO(), this.zzceR });
  }
  
  public zzbpe zzXU()
  {
    return this.zzceR;
  }
  
  public zzbng zzc(zzbos paramzzbos)
  {
    if (this.zzbXY.isEmpty()) {
      return new zzbni(this.zzceD, zzbmj.zzXf(), this.zzceR.zzm(paramzzbos));
    }
    return new zzbni(this.zzceD, this.zzbXY.zzXj(), this.zzceR);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbni.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */