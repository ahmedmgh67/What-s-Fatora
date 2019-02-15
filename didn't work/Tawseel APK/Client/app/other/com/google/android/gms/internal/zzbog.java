package com.google.android.gms.internal;

public class zzbog
{
  private final zzbnw zzcgp;
  private final zzbnw zzcgq;
  
  public zzbog(zzbnw paramzzbnw1, zzbnw paramzzbnw2)
  {
    this.zzcgp = paramzzbnw1;
    this.zzcgq = paramzzbnw2;
  }
  
  public zzbnw zzYK()
  {
    return this.zzcgp;
  }
  
  public zzbpe zzYL()
  {
    if (this.zzcgp.zzYg()) {
      return this.zzcgp.zzUY();
    }
    return null;
  }
  
  public zzbnw zzYM()
  {
    return this.zzcgq;
  }
  
  public zzbpe zzYN()
  {
    if (this.zzcgq.zzYg()) {
      return this.zzcgq.zzUY();
    }
    return null;
  }
  
  public zzbog zza(zzboz paramzzboz, boolean paramBoolean1, boolean paramBoolean2)
  {
    return new zzbog(new zzbnw(paramzzboz, paramBoolean1, paramBoolean2), this.zzcgq);
  }
  
  public zzbog zzb(zzboz paramzzboz, boolean paramBoolean1, boolean paramBoolean2)
  {
    return new zzbog(this.zzcgp, new zzbnw(paramzzboz, paramBoolean1, paramBoolean2));
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */