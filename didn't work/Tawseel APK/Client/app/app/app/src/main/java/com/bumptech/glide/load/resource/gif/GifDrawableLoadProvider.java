package com.bumptech.glide.load.resource.gif;

import android.content.Context;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.bumptech.glide.provider.DataLoadProvider;
import java.io.File;
import java.io.InputStream;

public class GifDrawableLoadProvider
  implements DataLoadProvider<InputStream, GifDrawable>
{
  private final FileToStreamDecoder<GifDrawable> cacheDecoder;
  private final GifResourceDecoder decoder;
  private final GifResourceEncoder encoder;
  private final StreamEncoder sourceEncoder;
  
  public GifDrawableLoadProvider(Context paramContext, BitmapPool paramBitmapPool)
  {
    this.decoder = new GifResourceDecoder(paramContext, paramBitmapPool);
    this.cacheDecoder = new FileToStreamDecoder(this.decoder);
    this.encoder = new GifResourceEncoder(paramBitmapPool);
    this.sourceEncoder = new StreamEncoder();
  }
  
  public ResourceDecoder<File, GifDrawable> getCacheDecoder()
  {
    return this.cacheDecoder;
  }
  
  public ResourceEncoder<GifDrawable> getEncoder()
  {
    return this.encoder;
  }
  
  public ResourceDecoder<InputStream, GifDrawable> getSourceDecoder()
  {
    return this.decoder;
  }
  
  public Encoder<InputStream> getSourceEncoder()
  {
    return this.sourceEncoder;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\gif\GifDrawableLoadProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */