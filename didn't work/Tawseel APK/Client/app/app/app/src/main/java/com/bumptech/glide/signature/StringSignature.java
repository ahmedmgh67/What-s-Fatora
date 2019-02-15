package com.bumptech.glide.signature;

import com.bumptech.glide.load.Key;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class StringSignature
  implements Key
{
  private final String signature;
  
  public StringSignature(String paramString)
  {
    if (paramString == null) {
      throw new NullPointerException("Signature cannot be null!");
    }
    this.signature = paramString;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if ((paramObject == null) || (getClass() != paramObject.getClass())) {
      return false;
    }
    paramObject = (StringSignature)paramObject;
    return this.signature.equals(((StringSignature)paramObject).signature);
  }
  
  public int hashCode()
  {
    return this.signature.hashCode();
  }
  
  public String toString()
  {
    return "StringSignature{signature='" + this.signature + '\'' + '}';
  }
  
  public void updateDiskCacheKey(MessageDigest paramMessageDigest)
    throws UnsupportedEncodingException
  {
    paramMessageDigest.update(this.signature.getBytes("UTF-8"));
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\signature\StringSignature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */