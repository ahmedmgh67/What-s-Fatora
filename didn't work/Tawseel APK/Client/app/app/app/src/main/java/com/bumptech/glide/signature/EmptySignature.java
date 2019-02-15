package com.bumptech.glide.signature;

import com.bumptech.glide.load.Key;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public final class EmptySignature
  implements Key
{
  private static final EmptySignature EMPTY_KEY = new EmptySignature();
  
  public static EmptySignature obtain()
  {
    return EMPTY_KEY;
  }
  
  public void updateDiskCacheKey(MessageDigest paramMessageDigest)
    throws UnsupportedEncodingException
  {}
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\signature\EmptySignature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */