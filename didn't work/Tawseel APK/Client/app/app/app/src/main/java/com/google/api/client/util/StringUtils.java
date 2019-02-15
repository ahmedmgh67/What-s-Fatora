package com.google.api.client.util;

public class StringUtils
{
  public static final String LINE_SEPARATOR = System.getProperty("line.separator");
  
  public static byte[] getBytesUtf8(String paramString)
  {
    return com.google.api.client.repackaged.org.apache.commons.codec.binary.StringUtils.getBytesUtf8(paramString);
  }
  
  public static String newStringUtf8(byte[] paramArrayOfByte)
  {
    return com.google.api.client.repackaged.org.apache.commons.codec.binary.StringUtils.newStringUtf8(paramArrayOfByte);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\StringUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */