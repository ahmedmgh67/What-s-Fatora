package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;

public abstract class zzaaf<L>
  implements zzaaz.zzc<L>
{
  private final DataHolder zzazI;
  
  protected zzaaf(DataHolder paramDataHolder)
  {
    this.zzazI = paramDataHolder;
  }
  
  protected abstract void zza(L paramL, DataHolder paramDataHolder);
  
  public final void zzs(L paramL)
  {
    zza(paramL, this.zzazI);
  }
  
  public void zzvy()
  {
    if (this.zzazI != null) {
      this.zzazI.close();
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzaaf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */