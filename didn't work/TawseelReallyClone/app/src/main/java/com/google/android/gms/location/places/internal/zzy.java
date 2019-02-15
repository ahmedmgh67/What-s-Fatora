package com.google.android.gms.location.places.internal;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class zzy
  extends zzab
  implements Place
{
  private final String zzblg = zzN("place_id", "");
  
  public zzy(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
  }
  
  private List<String> zzIg()
  {
    return zzb("place_attributions", Collections.emptyList());
  }
  
  private PlaceEntity zzIq()
  {
    PlaceEntity localPlaceEntity = new PlaceEntity.zza().zzfb(getAddress().toString()).zzF(zzIg()).zzeZ(getId()).zzaD(zzIh()).zza(getLatLng()).zzg(zzIe()).zzfa(getName().toString()).zzfc(getPhoneNumber().toString()).zzkJ(getPriceLevel()).zzh(getRating()).zzE(getPlaceTypes()).zza(getViewport()).zzt(getWebsiteUri()).zza(zzIi()).zzIn();
    localPlaceEntity.setLocale(getLocale());
    return localPlaceEntity;
  }
  
  public CharSequence getAddress()
  {
    return zzN("place_address", "");
  }
  
  public CharSequence getAttributions()
  {
    return zzf.zzo(zzIg());
  }
  
  public String getId()
  {
    return this.zzblg;
  }
  
  public LatLng getLatLng()
  {
    return (LatLng)zza("place_lat_lng", LatLng.CREATOR);
  }
  
  public Locale getLocale()
  {
    String str = zzN("place_locale_language", "");
    if (!TextUtils.isEmpty(str)) {
      return new Locale(str, zzN("place_locale_country", ""));
    }
    str = zzN("place_locale", "");
    if (!TextUtils.isEmpty(str)) {
      return new Locale(str);
    }
    return Locale.getDefault();
  }
  
  public CharSequence getName()
  {
    return zzN("place_name", "");
  }
  
  public CharSequence getPhoneNumber()
  {
    return zzN("place_phone_number", "");
  }
  
  public List<Integer> getPlaceTypes()
  {
    return zza("place_types", Collections.emptyList());
  }
  
  public int getPriceLevel()
  {
    return zzy("place_price_level", -1);
  }
  
  public float getRating()
  {
    return zzb("place_rating", -1.0F);
  }
  
  public LatLngBounds getViewport()
  {
    return (LatLngBounds)zza("place_viewport", LatLngBounds.CREATOR);
  }
  
  public Uri getWebsiteUri()
  {
    String str = zzN("place_website_uri", null);
    if (str == null) {
      return null;
    }
    return Uri.parse(str);
  }
  
  public float zzIe()
  {
    return zzb("place_level_number", 0.0F);
  }
  
  public boolean zzIh()
  {
    return zzk("place_is_permanently_closed", false);
  }
  
  zzu zzIi()
  {
    return (zzu)zza("place_opening_hours", zzu.CREATOR);
  }
  
  public Place zzIm()
  {
    return zzIq();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\internal\zzy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */