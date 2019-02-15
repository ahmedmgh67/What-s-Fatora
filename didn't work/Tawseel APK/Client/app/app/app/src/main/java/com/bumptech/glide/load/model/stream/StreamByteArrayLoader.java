package com.bumptech.glide.load.model.stream;

import android.content.Context;
import com.bumptech.glide.load.data.ByteArrayFetcher;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import java.io.InputStream;

public class StreamByteArrayLoader
  implements StreamModelLoader<byte[]>
{
  private final String id;
  
  public StreamByteArrayLoader()
  {
    this("");
  }
  
  @Deprecated
  public StreamByteArrayLoader(String paramString)
  {
    this.id = paramString;
  }
  
  public DataFetcher<InputStream> getResourceFetcher(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return new ByteArrayFetcher(paramArrayOfByte, this.id);
  }
  
  public static class Factory
    implements ModelLoaderFactory<byte[], InputStream>
  {
    public ModelLoader<byte[], InputStream> build(Context paramContext, GenericLoaderFactory paramGenericLoaderFactory)
    {
      return new StreamByteArrayLoader();
    }
    
    public void teardown() {}
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\model\stream\StreamByteArrayLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */