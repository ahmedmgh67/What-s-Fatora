package com.google.i18n.phonenumbers.internal;

import com.google.i18n.phonenumbers.RegexCache;
import com.google.i18n.phonenumbers.nano.Phonemetadata.PhoneNumberDesc;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RegexBasedMatcher
  implements MatcherApi
{
  private final RegexCache regexCache = new RegexCache(100);
  
  public static MatcherApi create()
  {
    return new RegexBasedMatcher();
  }
  
  public boolean matchesNationalNumber(String paramString, Phonemetadata.PhoneNumberDesc paramPhoneNumberDesc, boolean paramBoolean)
  {
    paramString = this.regexCache.getPatternForRegex(paramPhoneNumberDesc.nationalNumberPattern).matcher(paramString);
    return (paramString.matches()) || ((paramBoolean) && (paramString.lookingAt()));
  }
  
  public boolean matchesPossibleNumber(String paramString, Phonemetadata.PhoneNumberDesc paramPhoneNumberDesc)
  {
    return this.regexCache.getPatternForRegex(paramPhoneNumberDesc.possibleNumberPattern).matcher(paramString).matches();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\i18n\phonenumbers\internal\RegexBasedMatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */