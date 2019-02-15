package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.bumptech.glide.provider.DataLoadProvider;
import java.io.File;
import java.io.InputStream;

public class StreamBitmapDataLoadProvider
  implements DataLoadProvider<InputStream, Bitmap>
{
  private final FileToStreamDecoder<Bitmap> cacheDecoder;
  private final StreamBitmapDecoder decoder;
  private final BitmapEncoder encoder;
  private final StreamEncoder sourceEncoder = new StreamEncoder();
  
  public StreamBitmapDataLoadProvider(BitmapPool paramBitmapPool, DecodeFormat paramDecodeFormat)
  {
    this.decoder = new StreamBitmapDecoder(paramBitmapPool, paramDecodeFormat);
    this.encoder = new BitmapEncoder();
    this.cacheDecoder = new FileToStreamDecoder(this.decoder);
  }
  
  public ResourceDecoder<File, Bitmap> getCacheDecoder()
  {
    return this.cacheDecoder;
  }
  
  public ResourceEncoder<Bitmap> getEncoder()
  {
    return this.encoder;
  }
  
  public ResourceDecoder<InputStream, Bitmap> getSourceDecoder()
  {
    return this.decoder;
  }
  
  public Encoder<InputStream> getSourceEncoder()
  {
    return this.sourceEncoder;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\bitmap\StreamBitmapDataLoadProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */