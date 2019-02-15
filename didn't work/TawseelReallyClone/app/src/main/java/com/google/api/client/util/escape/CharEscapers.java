package com.google.api.client.util.escape;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public final class CharEscapers
{
  private static final Escaper URI_ESCAPER = new PercentEscaper("-_.*", true);
  private static final Escaper URI_PATH_ESCAPER = new PercentEscaper("-_.!~*'()@:$&,;=", false);
  private static final Escaper URI_QUERY_STRING_ESCAPER = new PercentEscaper("-_.!~*'()@:$,;/?:", false);
  private static final Escaper URI_RESERVED_ESCAPER = new PercentEscaper("-_.!~*'()@:$&,;=+/?", false);
  private static final Escaper URI_USERINFO_ESCAPER = new PercentEscaper("-_.!~*'():$&,;=", false);
  
  public static String decodeUri(String paramString)
  {
    try
    {
      paramString = URLDecoder.decode(paramString, "UTF-8");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
      throw new RuntimeException(paramString);
    }
  }
  
  public static String escapeUri(String paramString)
  {
    return URI_ESCAPER.escape(paramString);
  }
  
  public static String escapeUriPath(String paramString)
  {
    return URI_PATH_ESCAPER.escape(paramString);
  }
  
  public static String escapeUriPathWithoutReserved(String paramString)
  {
    return URI_RESERVED_ESCAPER.escape(paramString);
  }
  
  public static String escapeUriQuery(String paramString)
  {
    return URI_QUERY_STRING_ESCAPER.escape(paramString);
  }
  
  public static String escapeUriUserInfo(String paramString)
  {
    return URI_USERINFO_ESCAPER.escape(paramString);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\escape\CharEscapers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */