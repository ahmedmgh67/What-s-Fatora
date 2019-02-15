package com.google.i18n.phonenumbers;

import java.util.Arrays;

public final class PhoneNumberMatch
{
  private final Phonenumber.PhoneNumber number;
  private final String rawString;
  private final int start;
  
  PhoneNumberMatch(int paramInt, String paramString, Phonenumber.PhoneNumber paramPhoneNumber)
  {
    if (paramInt < 0) {
      throw new IllegalArgumentException("Start index must be >= 0.");
    }
    if ((paramString == null) || (paramPhoneNumber == null)) {
      throw new NullPointerException();
    }
    this.start = paramInt;
    this.rawString = paramString;
    this.number = paramPhoneNumber;
  }
  
  public int end()
  {
    return this.start + this.rawString.length();
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof PhoneNumberMatch)) {
        return false;
      }
      paramObject = (PhoneNumberMatch)paramObject;
    } while ((this.rawString.equals(((PhoneNumberMatch)paramObject).rawString)) && (this.start == ((PhoneNumberMatch)paramObject).start) && (this.number.equals(((PhoneNumberMatch)paramObject).number)));
    return false;
  }
  
  public int hashCode()
  {
    return Arrays.hashCode(new Object[] { Integer.valueOf(this.start), this.rawString, this.number });
  }
  
  public Phonenumber.PhoneNumber number()
  {
    return this.number;
  }
  
  public String rawString()
  {
    return this.rawString;
  }
  
  public int start()
  {
    return this.start;
  }
  
  public String toString()
  {
    int i = start();
    int j = end();
    String str = String.valueOf(String.valueOf(this.rawString));
    return str.length() + 43 + "PhoneNumberMatch [" + i + "," + j + ") " + str;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\i18n\phonenumbers\PhoneNumberMatch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */