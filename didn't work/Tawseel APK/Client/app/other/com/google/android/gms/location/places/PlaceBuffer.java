package com.google.android.gms.location.places;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.location.places.internal.zzy;

public class PlaceBuffer
  extends AbstractDataBuffer<Place>
  implements Result
{
  private final Status zzahq;
  private final String zzbkW;
  
  public PlaceBuffer(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
    this.zzahq = PlacesStatusCodes.zzdn(paramDataHolder.getStatusCode());
    if ((paramDataHolder != null) && (paramDataHolder.zzwy() != null))
    {
      this.zzbkW = paramDataHolder.zzwy().getString("com.google.android.gms.location.places.PlaceBuffer.ATTRIBUTIONS_EXTRA_KEY");
      return;
    }
    this.zzbkW = null;
  }
  
  public Place get(int paramInt)
  {
    return new zzy(this.zzazI, paramInt);
  }
  
  @Nullable
  public CharSequence getAttributions()
  {
    return this.zzbkW;
  }
  
  public Status getStatus()
  {
    return this.zzahq;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\PlaceBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */