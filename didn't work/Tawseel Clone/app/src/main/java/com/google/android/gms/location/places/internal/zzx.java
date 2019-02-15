package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoResult;

public class zzx
  extends zzab
  implements PlacePhotoMetadata
{
  private final String zzbmy = getString("photo_fife_url");
  
  public zzx(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
  }
  
  public CharSequence getAttributions()
  {
    return zzN("photo_attributions", null);
  }
  
  public int getMaxHeight()
  {
    return zzy("photo_max_height", 0);
  }
  
  public int getMaxWidth()
  {
    return zzy("photo_max_width", 0);
  }
  
  public PendingResult<PlacePhotoResult> getPhoto(GoogleApiClient paramGoogleApiClient)
  {
    return getScaledPhoto(paramGoogleApiClient, getMaxWidth(), getMaxHeight());
  }
  
  public PendingResult<PlacePhotoResult> getScaledPhoto(GoogleApiClient paramGoogleApiClient, int paramInt1, int paramInt2)
  {
    return ((PlacePhotoMetadata)freeze()).getScaledPhoto(paramGoogleApiClient, paramInt1, paramInt2);
  }
  
  public PlacePhotoMetadata zzIp()
  {
    return new zzw(this.zzbmy, getMaxWidth(), getMaxHeight(), getAttributions(), this.zzaCm);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\internal\zzx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */