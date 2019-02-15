package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.model.ImageVideoWrapper;
import java.io.IOException;
import java.io.InputStream;

public class ImageVideoBitmapDecoder
  implements ResourceDecoder<ImageVideoWrapper, Bitmap>
{
  private static final String TAG = "ImageVideoDecoder";
  private final ResourceDecoder<ParcelFileDescriptor, Bitmap> fileDescriptorDecoder;
  private final ResourceDecoder<InputStream, Bitmap> streamDecoder;
  
  public ImageVideoBitmapDecoder(ResourceDecoder<InputStream, Bitmap> paramResourceDecoder, ResourceDecoder<ParcelFileDescriptor, Bitmap> paramResourceDecoder1)
  {
    this.streamDecoder = paramResourceDecoder;
    this.fileDescriptorDecoder = paramResourceDecoder1;
  }
  
  public Resource<Bitmap> decode(ImageVideoWrapper paramImageVideoWrapper, int paramInt1, int paramInt2)
    throws IOException
  {
    Object localObject2 = null;
    InputStream localInputStream = paramImageVideoWrapper.getStream();
    Object localObject1 = localObject2;
    if (localInputStream != null) {}
    try
    {
      localObject1 = this.streamDecoder.decode(localInputStream, paramInt1, paramInt2);
      localObject2 = localObject1;
      if (localObject1 == null)
      {
        paramImageVideoWrapper = paramImageVideoWrapper.getFileDescriptor();
        localObject2 = localObject1;
        if (paramImageVideoWrapper != null) {
          localObject2 = this.fileDescriptorDecoder.decode(paramImageVideoWrapper, paramInt1, paramInt2);
        }
      }
      return (Resource<Bitmap>)localObject2;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localObject1 = localObject2;
        if (Log.isLoggable("ImageVideoDecoder", 2))
        {
          Log.v("ImageVideoDecoder", "Failed to load image from stream, trying FileDescriptor", localIOException);
          localObject1 = localObject2;
        }
      }
    }
  }
  
  public String getId()
  {
    return "ImageVideoBitmapDecoder.com.bumptech.glide.load.resource.bitmap";
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\bitmap\ImageVideoBitmapDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */