package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.Key;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

class OriginalKey
  implements Key
{
  private final String id;
  private final Key signature;
  
  public OriginalKey(String paramString, Key paramKey)
  {
    this.id = paramString;
    this.signature = paramKey;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (getClass() != paramObject.getClass())) {
        return false;
      }
      paramObject = (OriginalKey)paramObject;
      if (!this.id.equals(((OriginalKey)paramObject).id)) {
        return false;
      }
    } while (this.signature.equals(((OriginalKey)paramObject).signature));
    return false;
  }
  
  public int hashCode()
  {
    return this.id.hashCode() * 31 + this.signature.hashCode();
  }
  
  public void updateDiskCacheKey(MessageDigest paramMessageDigest)
    throws UnsupportedEncodingException
  {
    paramMessageDigest.update(this.id.getBytes("UTF-8"));
    this.signature.updateDiskCacheKey(paramMessageDigest);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\engine\OriginalKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */