package com.bumptech.glide.load.resource.gifbitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.model.ImageVideoWrapper;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.provider.DataLoadProvider;
import java.io.File;
import java.io.InputStream;

public class ImageVideoGifDrawableLoadProvider
  implements DataLoadProvider<ImageVideoWrapper, GifBitmapWrapper>
{
  private final ResourceDecoder<File, GifBitmapWrapper> cacheDecoder;
  private final ResourceEncoder<GifBitmapWrapper> encoder;
  private final ResourceDecoder<ImageVideoWrapper, GifBitmapWrapper> sourceDecoder;
  private final Encoder<ImageVideoWrapper> sourceEncoder;
  
  public ImageVideoGifDrawableLoadProvider(DataLoadProvider<ImageVideoWrapper, Bitmap> paramDataLoadProvider, DataLoadProvider<InputStream, GifDrawable> paramDataLoadProvider1, BitmapPool paramBitmapPool)
  {
    paramBitmapPool = new GifBitmapWrapperResourceDecoder(paramDataLoadProvider.getSourceDecoder(), paramDataLoadProvider1.getSourceDecoder(), paramBitmapPool);
    this.cacheDecoder = new FileToStreamDecoder(new GifBitmapWrapperStreamResourceDecoder(paramBitmapPool));
    this.sourceDecoder = paramBitmapPool;
    this.encoder = new GifBitmapWrapperResourceEncoder(paramDataLoadProvider.getEncoder(), paramDataLoadProvider1.getEncoder());
    this.sourceEncoder = paramDataLoadProvider.getSourceEncoder();
  }
  
  public ResourceDecoder<File, GifBitmapWrapper> getCacheDecoder()
  {
    return this.cacheDecoder;
  }
  
  public ResourceEncoder<GifBitmapWrapper> getEncoder()
  {
    return this.encoder;
  }
  
  public ResourceDecoder<ImageVideoWrapper, GifBitmapWrapper> getSourceDecoder()
  {
    return this.sourceDecoder;
  }
  
  public Encoder<ImageVideoWrapper> getSourceEncoder()
  {
    return this.sourceEncoder;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\gifbitmap\ImageVideoGifDrawableLoadProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */