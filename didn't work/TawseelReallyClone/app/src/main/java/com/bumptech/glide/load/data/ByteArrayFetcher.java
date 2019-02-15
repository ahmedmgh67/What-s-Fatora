package com.bumptech.glide.load.data;

import com.bumptech.glide.Priority;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ByteArrayFetcher
  implements DataFetcher<InputStream>
{
  private final byte[] bytes;
  private final String id;
  
  public ByteArrayFetcher(byte[] paramArrayOfByte, String paramString)
  {
    this.bytes = paramArrayOfByte;
    this.id = paramString;
  }
  
  public void cancel() {}
  
  public void cleanup() {}
  
  public String getId()
  {
    return this.id;
  }
  
  public InputStream loadData(Priority paramPriority)
  {
    return new ByteArrayInputStream(this.bytes);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\data\ByteArrayFetcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */