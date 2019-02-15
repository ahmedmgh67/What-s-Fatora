package com.google.i18n.phonenumbers.internal;

import com.google.i18n.phonenumbers.nano.Phonemetadata.PhoneNumberDesc;

public abstract interface MatcherApi
{
  public abstract boolean matchesNationalNumber(String paramString, Phonemetadata.PhoneNumberDesc paramPhoneNumberDesc, boolean paramBoolean);
  
  public abstract boolean matchesPossibleNumber(String paramString, Phonemetadata.PhoneNumberDesc paramPhoneNumberDesc);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\i18n\phonenumbers\internal\MatcherApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */