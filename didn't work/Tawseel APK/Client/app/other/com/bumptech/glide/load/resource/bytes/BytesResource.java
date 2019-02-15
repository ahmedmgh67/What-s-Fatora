package com.bumptech.glide.load.resource.bytes;

import com.bumptech.glide.load.engine.Resource;

public class BytesResource
  implements Resource<byte[]>
{
  private final byte[] bytes;
  
  public BytesResource(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      throw new NullPointerException("Bytes must not be null");
    }
    this.bytes = paramArrayOfByte;
  }
  
  public byte[] get()
  {
    return this.bytes;
  }
  
  public int getSize()
  {
    return this.bytes.length;
  }
  
  public void recycle() {}
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\bytes\BytesResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */