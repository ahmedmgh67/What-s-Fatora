package com.google.android.gms.internal;

abstract class zzats
  extends zzatr
{
  private boolean zzacO;
  
  zzats(zzatp paramzzatp)
  {
    super(paramzzatp);
    this.zzbpw.zzb(this);
  }
  
  public final void initialize()
  {
    if (this.zzacO) {
      throw new IllegalStateException("Can't initialize twice");
    }
    zzmr();
    this.zzbpw.zzLK();
    this.zzacO = true;
  }
  
  boolean isInitialized()
  {
    return this.zzacO;
  }
  
  protected abstract void zzmr();
  
  protected void zznA()
  {
    if (!isInitialized()) {
      throw new IllegalStateException("Not initialized");
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzats.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */