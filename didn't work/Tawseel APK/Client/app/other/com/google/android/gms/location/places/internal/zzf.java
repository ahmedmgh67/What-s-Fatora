package com.google.android.gms.location.places.internal;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class zzf
{
  public static CharSequence zza(String paramString, List<zza.zza> paramList, CharacterStyle paramCharacterStyle)
  {
    if (paramCharacterStyle == null) {
      return paramString;
    }
    paramString = new SpannableString(paramString);
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      zza.zza localzza = (zza.zza)paramList.next();
      CharacterStyle localCharacterStyle = CharacterStyle.wrap(paramCharacterStyle);
      int i = localzza.getOffset();
      int j = localzza.getOffset();
      paramString.setSpan(localCharacterStyle, i, localzza.getLength() + j, 0);
    }
    return paramString;
  }
  
  public static String zzo(Collection<String> paramCollection)
  {
    if ((paramCollection == null) || (paramCollection.isEmpty())) {
      return null;
    }
    return TextUtils.join(", ", paramCollection);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\internal\zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */