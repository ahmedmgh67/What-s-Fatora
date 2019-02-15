package com.google.android.gms.internal;

import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

public abstract class zzaag
  implements Releasable, Result
{
  protected final Status zzahq;
  protected final DataHolder zzazI;
  
  protected zzaag(DataHolder paramDataHolder, Status paramStatus)
  {
    this.zzahq = paramStatus;
    this.zzazI = paramDataHolder;
  }
  
  public Status getStatus()
  {
    return this.zzahq;
  }
  
  public void release()
  {
    if (this.zzazI != null) {
      this.zzazI.close();
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzaag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */