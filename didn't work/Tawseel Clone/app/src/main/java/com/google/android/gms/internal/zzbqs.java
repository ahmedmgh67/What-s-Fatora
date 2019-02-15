package com.google.android.gms.internal;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class zzbqs
  implements FirebaseRemoteConfigValue
{
  public static final Charset UTF_8 = Charset.forName("UTF-8");
  public static final Pattern zzaHr = Pattern.compile("^(1|true|t|yes|y|on)$", 2);
  public static final Pattern zzaHs = Pattern.compile("^(0|false|f|no|n|off|)$", 2);
  private final int zzAG;
  private final byte[] zzcjR;
  
  public zzbqs(byte[] paramArrayOfByte, int paramInt)
  {
    this.zzcjR = paramArrayOfByte;
    this.zzAG = paramInt;
  }
  
  private void zzaax()
  {
    if (this.zzcjR == null) {
      throw new IllegalArgumentException("Value is null, and cannot be converted to the desired type.");
    }
  }
  
  public boolean asBoolean()
    throws IllegalArgumentException
  {
    if (this.zzAG == 0) {}
    String str;
    do
    {
      return false;
      str = asString();
      if (zzaHr.matcher(str).matches()) {
        return true;
      }
    } while (zzaHs.matcher(str).matches());
    throw new IllegalArgumentException(String.valueOf(str).length() + 45 + "[Value: " + str + "] cannot be interpreted as a boolean.");
  }
  
  public byte[] asByteArray()
  {
    if (this.zzAG == 0) {
      return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_BYTE_ARRAY;
    }
    return this.zzcjR;
  }
  
  public double asDouble()
  {
    if (this.zzAG == 0) {
      return 0.0D;
    }
    String str = asString();
    try
    {
      double d = Double.valueOf(str).doubleValue();
      return d;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      throw new IllegalArgumentException(String.valueOf(str).length() + 42 + "[Value: " + str + "] cannot be converted to a double.", localNumberFormatException);
    }
  }
  
  public long asLong()
  {
    if (this.zzAG == 0) {
      return 0L;
    }
    String str = asString();
    try
    {
      long l = Long.valueOf(str).longValue();
      return l;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      throw new IllegalArgumentException(String.valueOf(str).length() + 40 + "[Value: " + str + "] cannot be converted to a long.", localNumberFormatException);
    }
  }
  
  public String asString()
  {
    if (this.zzAG == 0) {
      return "";
    }
    zzaax();
    return new String(this.zzcjR, UTF_8);
  }
  
  public int getSource()
  {
    return this.zzAG;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbqs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */