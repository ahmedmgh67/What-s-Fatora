package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzd;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.location.places.PlacesStatusCodes;

@Deprecated
public final class zzasl
  extends zzd<zzask>
  implements Result
{
  private final Status zzahq;
  
  public zzasl(DataHolder paramDataHolder)
  {
    this(paramDataHolder, PlacesStatusCodes.zzdn(paramDataHolder.getStatusCode()));
  }
  
  private zzasl(DataHolder paramDataHolder, Status paramStatus)
  {
    super(paramDataHolder, zzask.CREATOR);
    if ((paramDataHolder == null) || (paramDataHolder.getStatusCode() == paramStatus.getStatusCode())) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zzas(bool);
      this.zzahq = paramStatus;
      return;
    }
  }
  
  public Status getStatus()
  {
    return this.zzahq;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzasl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */