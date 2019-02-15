package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.io.IOException;

public class VideoBitmapDecoder
  implements BitmapDecoder<ParcelFileDescriptor>
{
  private static final MediaMetadataRetrieverFactory DEFAULT_FACTORY = new MediaMetadataRetrieverFactory();
  private static final int NO_FRAME = -1;
  private MediaMetadataRetrieverFactory factory;
  private int frame;
  
  public VideoBitmapDecoder()
  {
    this(DEFAULT_FACTORY, -1);
  }
  
  public VideoBitmapDecoder(int paramInt)
  {
    this(DEFAULT_FACTORY, checkValidFrame(paramInt));
  }
  
  VideoBitmapDecoder(MediaMetadataRetrieverFactory paramMediaMetadataRetrieverFactory)
  {
    this(paramMediaMetadataRetrieverFactory, -1);
  }
  
  VideoBitmapDecoder(MediaMetadataRetrieverFactory paramMediaMetadataRetrieverFactory, int paramInt)
  {
    this.factory = paramMediaMetadataRetrieverFactory;
    this.frame = paramInt;
  }
  
  private static int checkValidFrame(int paramInt)
  {
    if (paramInt < 0) {
      throw new IllegalArgumentException("Requested frame must be non-negative");
    }
    return paramInt;
  }
  
  public Bitmap decode(ParcelFileDescriptor paramParcelFileDescriptor, BitmapPool paramBitmapPool, int paramInt1, int paramInt2, DecodeFormat paramDecodeFormat)
    throws IOException
  {
    paramDecodeFormat = this.factory.build();
    paramDecodeFormat.setDataSource(paramParcelFileDescriptor.getFileDescriptor());
    if (this.frame >= 0) {}
    for (paramBitmapPool = paramDecodeFormat.getFrameAtTime(this.frame);; paramBitmapPool = paramDecodeFormat.getFrameAtTime())
    {
      paramDecodeFormat.release();
      paramParcelFileDescriptor.close();
      return paramBitmapPool;
    }
  }
  
  public String getId()
  {
    return "VideoBitmapDecoder.com.bumptech.glide.load.resource.bitmap";
  }
  
  static class MediaMetadataRetrieverFactory
  {
    public MediaMetadataRetriever build()
    {
      return new MediaMetadataRetriever();
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\bitmap\VideoBitmapDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */