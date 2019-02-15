package com.bumptech.glide.signature;

import com.bumptech.glide.load.Key;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

public class MediaStoreSignature
  implements Key
{
  private final long dateModified;
  private final String mimeType;
  private final int orientation;
  
  public MediaStoreSignature(String paramString, long paramLong, int paramInt)
  {
    this.mimeType = paramString;
    this.dateModified = paramLong;
    this.orientation = paramInt;
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
      paramObject = (MediaStoreSignature)paramObject;
      if (this.dateModified != ((MediaStoreSignature)paramObject).dateModified) {
        return false;
      }
      if (this.orientation != ((MediaStoreSignature)paramObject).orientation) {
        return false;
      }
      if (this.mimeType == null) {
        break;
      }
    } while (this.mimeType.equals(((MediaStoreSignature)paramObject).mimeType));
    for (;;)
    {
      return false;
      if (((MediaStoreSignature)paramObject).mimeType == null) {
        break;
      }
    }
  }
  
  public int hashCode()
  {
    if (this.mimeType != null) {}
    for (int i = this.mimeType.hashCode();; i = 0) {
      return (i * 31 + (int)(this.dateModified ^ this.dateModified >>> 32)) * 31 + this.orientation;
    }
  }
  
  public void updateDiskCacheKey(MessageDigest paramMessageDigest)
    throws UnsupportedEncodingException
  {
    paramMessageDigest.update(ByteBuffer.allocate(12).putLong(this.dateModified).putInt(this.orientation).array());
    paramMessageDigest.update(this.mimeType.getBytes("UTF-8"));
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\signature\MediaStoreSignature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */