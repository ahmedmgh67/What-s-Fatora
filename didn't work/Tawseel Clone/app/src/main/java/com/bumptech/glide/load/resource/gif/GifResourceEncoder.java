package com.bumptech.glide.load.resource.gif;

import android.graphics.Bitmap;
import android.util.Log;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.gifdecoder.GifDecoder.BitmapProvider;
import com.bumptech.glide.gifdecoder.GifHeader;
import com.bumptech.glide.gifdecoder.GifHeaderParser;
import com.bumptech.glide.gifencoder.AnimatedGifEncoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.UnitTransformation;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.util.LogTime;
import java.io.IOException;
import java.io.OutputStream;

public class GifResourceEncoder
  implements ResourceEncoder<GifDrawable>
{
  private static final Factory FACTORY = new Factory();
  private static final String TAG = "GifEncoder";
  private final BitmapPool bitmapPool;
  private final Factory factory;
  private final GifDecoder.BitmapProvider provider;
  
  public GifResourceEncoder(BitmapPool paramBitmapPool)
  {
    this(paramBitmapPool, FACTORY);
  }
  
  GifResourceEncoder(BitmapPool paramBitmapPool, Factory paramFactory)
  {
    this.bitmapPool = paramBitmapPool;
    this.provider = new GifBitmapProvider(paramBitmapPool);
    this.factory = paramFactory;
  }
  
  private GifDecoder decodeHeaders(byte[] paramArrayOfByte)
  {
    Object localObject = this.factory.buildParser();
    ((GifHeaderParser)localObject).setData(paramArrayOfByte);
    localObject = ((GifHeaderParser)localObject).parseHeader();
    GifDecoder localGifDecoder = this.factory.buildDecoder(this.provider);
    localGifDecoder.setData((GifHeader)localObject, paramArrayOfByte);
    localGifDecoder.advance();
    return localGifDecoder;
  }
  
  private Resource<Bitmap> getTransformedFrame(Bitmap paramBitmap, Transformation<Bitmap> paramTransformation, GifDrawable paramGifDrawable)
  {
    paramBitmap = this.factory.buildFrameResource(paramBitmap, this.bitmapPool);
    paramTransformation = paramTransformation.transform(paramBitmap, paramGifDrawable.getIntrinsicWidth(), paramGifDrawable.getIntrinsicHeight());
    if (!paramBitmap.equals(paramTransformation)) {
      paramBitmap.recycle();
    }
    return paramTransformation;
  }
  
  private boolean writeDataDirect(byte[] paramArrayOfByte, OutputStream paramOutputStream)
  {
    try
    {
      paramOutputStream.write(paramArrayOfByte);
      return true;
    }
    catch (IOException paramArrayOfByte)
    {
      if (Log.isLoggable("GifEncoder", 3)) {
        Log.d("GifEncoder", "Failed to write data to output stream in GifResourceEncoder", paramArrayOfByte);
      }
    }
    return false;
  }
  
  public boolean encode(Resource<GifDrawable> paramResource, OutputStream paramOutputStream)
  {
    long l = LogTime.getLogTime();
    paramResource = (GifDrawable)paramResource.get();
    Transformation localTransformation = paramResource.getFrameTransformation();
    boolean bool1;
    if ((localTransformation instanceof UnitTransformation)) {
      bool1 = writeDataDirect(paramResource.getData(), paramOutputStream);
    }
    GifDecoder localGifDecoder;
    boolean bool2;
    do
    {
      return bool1;
      localGifDecoder = decodeHeaders(paramResource.getData());
      AnimatedGifEncoder localAnimatedGifEncoder = this.factory.buildEncoder();
      if (!localAnimatedGifEncoder.start(paramOutputStream)) {
        return false;
      }
      int i = 0;
      while (i < localGifDecoder.getFrameCount())
      {
        paramOutputStream = getTransformedFrame(localGifDecoder.getNextFrame(), localTransformation, paramResource);
        try
        {
          bool1 = localAnimatedGifEncoder.addFrame((Bitmap)paramOutputStream.get());
          if (!bool1) {
            return false;
          }
          localAnimatedGifEncoder.setDelay(localGifDecoder.getDelay(localGifDecoder.getCurrentFrameIndex()));
          localGifDecoder.advance();
          paramOutputStream.recycle();
          i += 1;
        }
        finally
        {
          paramOutputStream.recycle();
        }
      }
      bool2 = localAnimatedGifEncoder.finish();
      bool1 = bool2;
    } while (!Log.isLoggable("GifEncoder", 2));
    Log.v("GifEncoder", "Encoded gif with " + localGifDecoder.getFrameCount() + " frames and " + paramResource.getData().length + " bytes in " + LogTime.getElapsedMillis(l) + " ms");
    return bool2;
  }
  
  public String getId()
  {
    return "";
  }
  
  static class Factory
  {
    public GifDecoder buildDecoder(GifDecoder.BitmapProvider paramBitmapProvider)
    {
      return new GifDecoder(paramBitmapProvider);
    }
    
    public AnimatedGifEncoder buildEncoder()
    {
      return new AnimatedGifEncoder();
    }
    
    public Resource<Bitmap> buildFrameResource(Bitmap paramBitmap, BitmapPool paramBitmapPool)
    {
      return new BitmapResource(paramBitmap, paramBitmapPool);
    }
    
    public GifHeaderParser buildParser()
    {
      return new GifHeaderParser();
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\gif\GifResourceEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */