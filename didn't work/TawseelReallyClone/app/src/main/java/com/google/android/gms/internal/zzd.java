package com.google.android.gms.internal;

public class zzd
  implements zzo
{
  private int zzn;
  private int zzo;
  private final int zzp;
  private final float zzq;
  
  public zzd()
  {
    this(2500, 1, 1.0F);
  }
  
  public zzd(int paramInt1, int paramInt2, float paramFloat)
  {
    this.zzn = paramInt1;
    this.zzp = paramInt2;
    this.zzq = paramFloat;
  }
  
  public void zza(zzr paramzzr)
    throws zzr
  {
    this.zzo += 1;
    this.zzn = ((int)(this.zzn + this.zzn * this.zzq));
    if (!zze()) {
      throw paramzzr;
    }
  }
  
  public int zzc()
  {
    return this.zzn;
  }
  
  public int zzd()
  {
    return this.zzo;
  }
  
  protected boolean zze()
  {
    return this.zzo <= this.zzp;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */