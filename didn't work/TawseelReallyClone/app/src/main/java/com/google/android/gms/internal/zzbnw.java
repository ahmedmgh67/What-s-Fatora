package com.google.android.gms.internal;

public class zzbnw
{
  private final zzboz zzcfB;
  private final boolean zzcfC;
  private final boolean zzcfD;
  
  public zzbnw(zzboz paramzzboz, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.zzcfB = paramzzboz;
    this.zzcfC = paramBoolean1;
    this.zzcfD = paramBoolean2;
  }
  
  public boolean zzM(zzbmj paramzzbmj)
  {
    if (paramzzbmj.isEmpty()) {
      return (zzYg()) && (!this.zzcfD);
    }
    return zzf(paramzzbmj.zzXi());
  }
  
  public zzbpe zzUY()
  {
    return this.zzcfB.zzUY();
  }
  
  public boolean zzYg()
  {
    return this.zzcfC;
  }
  
  public boolean zzYh()
  {
    return this.zzcfD;
  }
  
  public zzboz zzYi()
  {
    return this.zzcfB;
  }
  
  public boolean zzf(zzbos paramzzbos)
  {
    return ((zzYg()) && (!this.zzcfD)) || (this.zzcfB.zzUY().zzk(paramzzbos));
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbnw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */