package com.google.android.gms.location.places;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.location.places.internal.zzg;
import com.google.android.gms.location.places.internal.zzh;
import com.google.android.gms.location.places.internal.zzh.zza;
import com.google.android.gms.location.places.internal.zzm;
import com.google.android.gms.location.places.internal.zzn;
import com.google.android.gms.location.places.internal.zzn.zza;

public class Places
{
  public static final Api<PlacesOptions> GEO_DATA_API;
  public static final GeoDataApi GeoDataApi = new zzg();
  public static final Api<PlacesOptions> PLACE_DETECTION_API;
  public static final PlaceDetectionApi PlaceDetectionApi = new zzm();
  public static final Api.zzf<zzh> zzblm = new Api.zzf();
  public static final Api.zzf<zzn> zzbln = new Api.zzf();
  
  static
  {
    GEO_DATA_API = new Api("Places.GEO_DATA_API", new zzh.zza(), zzblm);
    PLACE_DETECTION_API = new Api("Places.PLACE_DETECTION_API", new zzn.zza(), zzbln);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\Places.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */