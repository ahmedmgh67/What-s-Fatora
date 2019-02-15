package com.google.android.gms.location.places;

import android.support.annotation.Nullable;
import android.text.style.CharacterStyle;
import com.google.android.gms.common.data.Freezable;
import java.util.List;

public abstract interface AutocompletePrediction
  extends Freezable<AutocompletePrediction>
{
  public abstract CharSequence getFullText(@Nullable CharacterStyle paramCharacterStyle);
  
  @Nullable
  public abstract String getPlaceId();
  
  @Nullable
  public abstract List<Integer> getPlaceTypes();
  
  public abstract CharSequence getPrimaryText(@Nullable CharacterStyle paramCharacterStyle);
  
  public abstract CharSequence getSecondaryText(@Nullable CharacterStyle paramCharacterStyle);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\AutocompletePrediction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */