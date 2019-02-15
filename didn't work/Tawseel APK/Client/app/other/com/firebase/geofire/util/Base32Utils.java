package com.firebase.geofire.util;

public class Base32Utils
{
  private static final String BASE32_CHARS = "0123456789bcdefghjkmnpqrstuvwxyz";
  public static final int BITS_PER_BASE32_CHAR = 5;
  
  public static int base32CharToValue(char paramChar)
  {
    int i = "0123456789bcdefghjkmnpqrstuvwxyz".indexOf(paramChar);
    if (i == -1) {
      throw new IllegalArgumentException("Not a valid base32 char: " + paramChar);
    }
    return i;
  }
  
  public static boolean isValidBase32String(String paramString)
  {
    return paramString.matches("^[0123456789bcdefghjkmnpqrstuvwxyz]*$");
  }
  
  public static char valueToBase32Char(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= "0123456789bcdefghjkmnpqrstuvwxyz".length())) {
      throw new IllegalArgumentException("Not a valid base32 value: " + paramInt);
    }
    return "0123456789bcdefghjkmnpqrstuvwxyz".charAt(paramInt);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\firebase\geofire\util\Base32Utils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */