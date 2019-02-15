package com.bumptech.glide.load;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public abstract interface Key
{
  public static final String STRING_CHARSET_NAME = "UTF-8";
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
  
  public abstract void updateDiskCacheKey(MessageDigest paramMessageDigest)
    throws UnsupportedEncodingException;
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\Key.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */