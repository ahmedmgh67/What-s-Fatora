package com.bumptech.glide.load.resource.gifbitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.model.ImageVideoWrapper;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.load.resource.bitmap.ImageHeaderParser;
import com.bumptech.glide.load.resource.bitmap.ImageHeaderParser.ImageType;
import com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.util.ByteArrayPool;
import java.io.IOException;
import java.io.InputStream;

public class GifBitmapWrapperResourceDecoder
  implements ResourceDecoder<ImageVideoWrapper, GifBitmapWrapper>
{
  private static final ImageTypeParser DEFAULT_PARSER = new ImageTypeParser();
  private static final BufferedStreamFactory DEFAULT_STREAM_FACTORY = new BufferedStreamFactory();
  static final int MARK_LIMIT_BYTES = 2048;
  private final ResourceDecoder<ImageVideoWrapper, Bitmap> bitmapDecoder;
  private final BitmapPool bitmapPool;
  private final ResourceDecoder<InputStream, GifDrawable> gifDecoder;
  private String id;
  private final ImageTypeParser parser;
  private final BufferedStreamFactory streamFactory;
  
  public GifBitmapWrapperResourceDecoder(ResourceDecoder<ImageVideoWrapper, Bitmap> paramResourceDecoder, ResourceDecoder<InputStream, GifDrawable> paramResourceDecoder1, BitmapPool paramBitmapPool)
  {
    this(paramResourceDecoder, paramResourceDecoder1, paramBitmapPool, DEFAULT_PARSER, DEFAULT_STREAM_FACTORY);
  }
  
  GifBitmapWrapperResourceDecoder(ResourceDecoder<ImageVideoWrapper, Bitmap> paramResourceDecoder, ResourceDecoder<InputStream, GifDrawable> paramResourceDecoder1, BitmapPool paramBitmapPool, ImageTypeParser paramImageTypeParser, BufferedStreamFactory paramBufferedStreamFactory)
  {
    this.bitmapDecoder = paramResourceDecoder;
    this.gifDecoder = paramResourceDecoder1;
    this.bitmapPool = paramBitmapPool;
    this.parser = paramImageTypeParser;
    this.streamFactory = paramBufferedStreamFactory;
  }
  
  private GifBitmapWrapper decode(ImageVideoWrapper paramImageVideoWrapper, int paramInt1, int paramInt2, byte[] paramArrayOfByte)
    throws IOException
  {
    if (paramImageVideoWrapper.getStream() != null) {
      return decodeStream(paramImageVideoWrapper, paramInt1, paramInt2, paramArrayOfByte);
    }
    return decodeBitmapWrapper(paramImageVideoWrapper, paramInt1, paramInt2);
  }
  
  private GifBitmapWrapper decodeBitmapWrapper(ImageVideoWrapper paramImageVideoWrapper, int paramInt1, int paramInt2)
    throws IOException
  {
    Object localObject = null;
    Resource localResource = this.bitmapDecoder.decode(paramImageVideoWrapper, paramInt1, paramInt2);
    paramImageVideoWrapper = (ImageVideoWrapper)localObject;
    if (localResource != null) {
      paramImageVideoWrapper = new GifBitmapWrapper(localResource, null);
    }
    return paramImageVideoWrapper;
  }
  
  private GifBitmapWrapper decodeGifWrapper(InputStream paramInputStream, int paramInt1, int paramInt2)
    throws IOException
  {
    Object localObject = null;
    Resource localResource = this.gifDecoder.decode(paramInputStream, paramInt1, paramInt2);
    paramInputStream = (InputStream)localObject;
    if (localResource != null)
    {
      paramInputStream = (GifDrawable)localResource.get();
      if (paramInputStream.getFrameCount() > 1) {
        paramInputStream = new GifBitmapWrapper(null, localResource);
      }
    }
    else
    {
      return paramInputStream;
    }
    return new GifBitmapWrapper(new BitmapResource(paramInputStream.getFirstFrame(), this.bitmapPool), null);
  }
  
  private GifBitmapWrapper decodeStream(ImageVideoWrapper paramImageVideoWrapper, int paramInt1, int paramInt2, byte[] paramArrayOfByte)
    throws IOException
  {
    InputStream localInputStream = this.streamFactory.build(paramImageVideoWrapper.getStream(), paramArrayOfByte);
    localInputStream.mark(2048);
    Object localObject = this.parser.parse(localInputStream);
    localInputStream.reset();
    paramArrayOfByte = null;
    if (localObject == ImageHeaderParser.ImageType.GIF) {
      paramArrayOfByte = decodeGifWrapper(localInputStream, paramInt1, paramInt2);
    }
    localObject = paramArrayOfByte;
    if (paramArrayOfByte == null) {
      localObject = decodeBitmapWrapper(new ImageVideoWrapper(localInputStream, paramImageVideoWrapper.getFileDescriptor()), paramInt1, paramInt2);
    }
    return (GifBitmapWrapper)localObject;
  }
  
  public Resource<GifBitmapWrapper> decode(ImageVideoWrapper paramImageVideoWrapper, int paramInt1, int paramInt2)
    throws IOException
  {
    ByteArrayPool localByteArrayPool = ByteArrayPool.get();
    byte[] arrayOfByte = localByteArrayPool.getBytes();
    try
    {
      paramImageVideoWrapper = decode(paramImageVideoWrapper, paramInt1, paramInt2, arrayOfByte);
      localByteArrayPool.releaseBytes(arrayOfByte);
      if (paramImageVideoWrapper != null) {
        return new GifBitmapWrapperResource(paramImageVideoWrapper);
      }
    }
    finally
    {
      localByteArrayPool.releaseBytes(arrayOfByte);
    }
    return null;
  }
  
  public String getId()
  {
    if (this.id == null) {
      this.id = (this.gifDecoder.getId() + this.bitmapDecoder.getId());
    }
    return this.id;
  }
  
  static class BufferedStreamFactory
  {
    public InputStream build(InputStream paramInputStream, byte[] paramArrayOfByte)
    {
      return new RecyclableBufferedInputStream(paramInputStream, paramArrayOfByte);
    }
  }
  
  static class ImageTypeParser
  {
    public ImageHeaderParser.ImageType parse(InputStream paramInputStream)
      throws IOException
    {
      return new ImageHeaderParser(paramInputStream).getType();
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\gifbitmap\GifBitmapWrapperResourceDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */