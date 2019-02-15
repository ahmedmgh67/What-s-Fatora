package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.io.InputStream;

public class StreamBitmapDecoder
  implements ResourceDecoder<InputStream, Bitmap>
{
  private static final String ID = "StreamBitmapDecoder.com.bumptech.glide.load.resource.bitmap";
  private BitmapPool bitmapPool;
  private DecodeFormat decodeFormat;
  private final Downsampler downsampler;
  private String id;
  
  public StreamBitmapDecoder(Context paramContext)
  {
    this(Glide.get(paramContext).getBitmapPool());
  }
  
  public StreamBitmapDecoder(Context paramContext, DecodeFormat paramDecodeFormat)
  {
    this(Glide.get(paramContext).getBitmapPool(), paramDecodeFormat);
  }
  
  public StreamBitmapDecoder(BitmapPool paramBitmapPool)
  {
    this(paramBitmapPool, DecodeFormat.DEFAULT);
  }
  
  public StreamBitmapDecoder(BitmapPool paramBitmapPool, DecodeFormat paramDecodeFormat)
  {
    this(Downsampler.AT_LEAST, paramBitmapPool, paramDecodeFormat);
  }
  
  public StreamBitmapDecoder(Downsampler paramDownsampler, BitmapPool paramBitmapPool, DecodeFormat paramDecodeFormat)
  {
    this.downsampler = paramDownsampler;
    this.bitmapPool = paramBitmapPool;
    this.decodeFormat = paramDecodeFormat;
  }
  
  public Resource<Bitmap> decode(InputStream paramInputStream, int paramInt1, int paramInt2)
  {
    return BitmapResource.obtain(this.downsampler.decode(paramInputStream, this.bitmapPool, paramInt1, paramInt2, this.decodeFormat), this.bitmapPool);
  }
  
  public String getId()
  {
    if (this.id == null) {
      this.id = ("StreamBitmapDecoder.com.bumptech.glide.load.resource.bitmap" + this.downsampler.getId() + this.decodeFormat.name());
    }
    return this.id;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\bitmap\StreamBitmapDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */