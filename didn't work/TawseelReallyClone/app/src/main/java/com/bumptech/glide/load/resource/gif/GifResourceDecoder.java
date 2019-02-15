package com.bumptech.glide.load.resource.gif;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import com.bumptech.glide.Glide;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.gifdecoder.GifDecoder.BitmapProvider;
import com.bumptech.glide.gifdecoder.GifHeader;
import com.bumptech.glide.gifdecoder.GifHeaderParser;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.UnitTransformation;
import com.bumptech.glide.util.Util;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;

public class GifResourceDecoder
  implements ResourceDecoder<InputStream, GifDrawable>
{
  private static final GifDecoderPool DECODER_POOL = new GifDecoderPool();
  private static final GifHeaderParserPool PARSER_POOL = new GifHeaderParserPool();
  private static final String TAG = "GifResourceDecoder";
  private final BitmapPool bitmapPool;
  private final Context context;
  private final GifDecoderPool decoderPool;
  private final GifHeaderParserPool parserPool;
  private final GifBitmapProvider provider;
  
  public GifResourceDecoder(Context paramContext)
  {
    this(paramContext, Glide.get(paramContext).getBitmapPool());
  }
  
  public GifResourceDecoder(Context paramContext, BitmapPool paramBitmapPool)
  {
    this(paramContext, paramBitmapPool, PARSER_POOL, DECODER_POOL);
  }
  
  GifResourceDecoder(Context paramContext, BitmapPool paramBitmapPool, GifHeaderParserPool paramGifHeaderParserPool, GifDecoderPool paramGifDecoderPool)
  {
    this.context = paramContext;
    this.bitmapPool = paramBitmapPool;
    this.decoderPool = paramGifDecoderPool;
    this.provider = new GifBitmapProvider(paramBitmapPool);
    this.parserPool = paramGifHeaderParserPool;
  }
  
  private GifDrawableResource decode(byte[] paramArrayOfByte, int paramInt1, int paramInt2, GifHeaderParser paramGifHeaderParser, GifDecoder paramGifDecoder)
  {
    paramGifHeaderParser = paramGifHeaderParser.parseHeader();
    if ((paramGifHeaderParser.getNumFrames() <= 0) || (paramGifHeaderParser.getStatus() != 0)) {}
    do
    {
      return null;
      paramGifDecoder = decodeFirstFrame(paramGifDecoder, paramGifHeaderParser, paramArrayOfByte);
    } while (paramGifDecoder == null);
    UnitTransformation localUnitTransformation = UnitTransformation.get();
    return new GifDrawableResource(new GifDrawable(this.context, this.provider, this.bitmapPool, localUnitTransformation, paramInt1, paramInt2, paramGifHeaderParser, paramArrayOfByte, paramGifDecoder));
  }
  
  private Bitmap decodeFirstFrame(GifDecoder paramGifDecoder, GifHeader paramGifHeader, byte[] paramArrayOfByte)
  {
    paramGifDecoder.setData(paramGifHeader, paramArrayOfByte);
    paramGifDecoder.advance();
    return paramGifDecoder.getNextFrame();
  }
  
  private static byte[] inputStreamToBytes(InputStream paramInputStream)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(16384);
    try
    {
      byte[] arrayOfByte = new byte['䀀'];
      for (;;)
      {
        int i = paramInputStream.read(arrayOfByte);
        if (i == -1) {
          break;
        }
        localByteArrayOutputStream.write(arrayOfByte, 0, i);
      }
      return localByteArrayOutputStream.toByteArray();
    }
    catch (IOException paramInputStream)
    {
      Log.w("GifResourceDecoder", "Error reading data from stream", paramInputStream);
    }
    for (;;)
    {
      localByteArrayOutputStream.flush();
    }
  }
  
  public GifDrawableResource decode(InputStream paramInputStream, int paramInt1, int paramInt2)
  {
    Object localObject1 = inputStreamToBytes(paramInputStream);
    paramInputStream = this.parserPool.obtain((byte[])localObject1);
    GifDecoder localGifDecoder = this.decoderPool.obtain(this.provider);
    try
    {
      localObject1 = decode((byte[])localObject1, paramInt1, paramInt2, paramInputStream, localGifDecoder);
      return (GifDrawableResource)localObject1;
    }
    finally
    {
      this.parserPool.release(paramInputStream);
      this.decoderPool.release(localGifDecoder);
    }
  }
  
  public String getId()
  {
    return "";
  }
  
  static class GifDecoderPool
  {
    private final Queue<GifDecoder> pool = Util.createQueue(0);
    
    public GifDecoder obtain(GifDecoder.BitmapProvider paramBitmapProvider)
    {
      try
      {
        GifDecoder localGifDecoder2 = (GifDecoder)this.pool.poll();
        GifDecoder localGifDecoder1 = localGifDecoder2;
        if (localGifDecoder2 == null) {
          localGifDecoder1 = new GifDecoder(paramBitmapProvider);
        }
        return localGifDecoder1;
      }
      finally {}
    }
    
    public void release(GifDecoder paramGifDecoder)
    {
      try
      {
        paramGifDecoder.clear();
        this.pool.offer(paramGifDecoder);
        return;
      }
      finally
      {
        paramGifDecoder = finally;
        throw paramGifDecoder;
      }
    }
  }
  
  static class GifHeaderParserPool
  {
    private final Queue<GifHeaderParser> pool = Util.createQueue(0);
    
    public GifHeaderParser obtain(byte[] paramArrayOfByte)
    {
      try
      {
        GifHeaderParser localGifHeaderParser2 = (GifHeaderParser)this.pool.poll();
        GifHeaderParser localGifHeaderParser1 = localGifHeaderParser2;
        if (localGifHeaderParser2 == null) {
          localGifHeaderParser1 = new GifHeaderParser();
        }
        paramArrayOfByte = localGifHeaderParser1.setData(paramArrayOfByte);
        return paramArrayOfByte;
      }
      finally {}
    }
    
    public void release(GifHeaderParser paramGifHeaderParser)
    {
      try
      {
        paramGifHeaderParser.clear();
        this.pool.offer(paramGifHeaderParser);
        return;
      }
      finally
      {
        paramGifHeaderParser = finally;
        throw paramGifHeaderParser;
      }
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\gif\GifResourceDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */