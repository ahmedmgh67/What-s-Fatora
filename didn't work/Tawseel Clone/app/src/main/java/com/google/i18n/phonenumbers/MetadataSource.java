package com.google.i18n.phonenumbers;

import com.google.i18n.phonenumbers.nano.Phonemetadata.PhoneMetadata;

abstract interface MetadataSource
{
  public abstract Phonemetadata.PhoneMetadata getMetadataForNonGeographicalRegion(int paramInt);
  
  public abstract Phonemetadata.PhoneMetadata getMetadataForRegion(String paramString);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\i18n\phonenumbers\MetadataSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */