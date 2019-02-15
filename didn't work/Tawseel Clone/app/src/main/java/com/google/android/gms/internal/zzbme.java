package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseError;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class zzbme
{
  private AtomicBoolean zzcbT = new AtomicBoolean(false);
  private zzbmf zzcbU;
  private boolean zzcbV = false;
  
  static
  {
    if (!zzbme.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public abstract zzboe zzWD();
  
  public void zzXa()
  {
    if ((this.zzcbT.compareAndSet(false, true)) && (this.zzcbU != null))
    {
      this.zzcbU.zzd(this);
      this.zzcbU = null;
    }
  }
  
  public boolean zzXb()
  {
    return this.zzcbT.get();
  }
  
  public boolean zzXc()
  {
    return this.zzcbV;
  }
  
  public abstract zzbme zza(zzboe paramzzboe);
  
  public abstract zzbnz zza(zzbny paramzzbny, zzboe paramzzboe);
  
  public void zza(zzbmf paramzzbmf)
  {
    assert (!zzXb());
    assert (this.zzcbU == null);
    this.zzcbU = paramzzbmf;
  }
  
  public abstract void zza(zzbnz paramzzbnz);
  
  public abstract void zza(DatabaseError paramDatabaseError);
  
  public abstract boolean zza(zzboa.zza paramzza);
  
  public void zzba(boolean paramBoolean)
  {
    this.zzcbV = paramBoolean;
  }
  
  public abstract boolean zzc(zzbme paramzzbme);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbme.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */