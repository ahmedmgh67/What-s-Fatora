package com.google.android.gms.location.places.internal;

import android.support.annotation.Nullable;
import android.text.style.CharacterStyle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.location.places.AutocompletePrediction;
import java.util.Collections;
import java.util.List;

public class zzc
  extends zzab
  implements AutocompletePrediction
{
  public zzc(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
  }
  
  private String zzHX()
  {
    return zzN("ap_description", "");
  }
  
  private String zzHY()
  {
    return zzN("ap_primary_text", "");
  }
  
  private String zzHZ()
  {
    return zzN("ap_secondary_text", "");
  }
  
  private List<zza.zza> zzIa()
  {
    return zza("ap_matched_subscriptions", zza.zza.CREATOR, Collections.emptyList());
  }
  
  private List<zza.zza> zzIb()
  {
    return zza("ap_primary_text_matched", zza.zza.CREATOR, Collections.emptyList());
  }
  
  private List<zza.zza> zzIc()
  {
    return zza("ap_secondary_text_matched", zza.zza.CREATOR, Collections.emptyList());
  }
  
  public CharSequence getFullText(@Nullable CharacterStyle paramCharacterStyle)
  {
    return zzf.zza(zzHX(), zzIa(), paramCharacterStyle);
  }
  
  public String getPlaceId()
  {
    return zzN("ap_place_id", null);
  }
  
  public List<Integer> getPlaceTypes()
  {
    return zza("ap_place_types", Collections.emptyList());
  }
  
  public CharSequence getPrimaryText(@Nullable CharacterStyle paramCharacterStyle)
  {
    return zzf.zza(zzHY(), zzIb(), paramCharacterStyle);
  }
  
  public CharSequence getSecondaryText(@Nullable CharacterStyle paramCharacterStyle)
  {
    return zzf.zza(zzHZ(), zzIc(), paramCharacterStyle);
  }
  
  public AutocompletePrediction zzHV()
  {
    return zza.zza(getPlaceId(), getPlaceTypes(), zzHW(), zzHX(), zzIa(), zzHY(), zzIb(), zzHZ(), zzIc());
  }
  
  public int zzHW()
  {
    return zzy("ap_personalization_type", 6);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\internal\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */