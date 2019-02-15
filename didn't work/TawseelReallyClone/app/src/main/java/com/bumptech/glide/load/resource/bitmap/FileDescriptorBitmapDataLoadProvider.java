package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.NullEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.bumptech.glide.provider.DataLoadProvider;
import java.io.File;

public class FileDescriptorBitmapDataLoadProvider
  implements DataLoadProvider<ParcelFileDescriptor, Bitmap>
{
  private final ResourceDecoder<File, Bitmap> cacheDecoder;
  private final BitmapEncoder encoder;
  private final FileDescriptorBitmapDecoder sourceDecoder;
  private final Encoder<ParcelFileDescriptor> sourceEncoder;
  
  public FileDescriptorBitmapDataLoadProvider(BitmapPool paramBitmapPool, DecodeFormat paramDecodeFormat)
  {
    this.cacheDecoder = new FileToStreamDecoder(new StreamBitmapDecoder(paramBitmapPool, paramDecodeFormat));
    this.sourceDecoder = new FileDescriptorBitmapDecoder(paramBitmapPool, paramDecodeFormat);
    this.encoder = new BitmapEncoder();
    this.sourceEncoder = NullEncoder.get();
  }
  
  public ResourceDecoder<File, Bitmap> getCacheDecoder()
  {
    return this.cacheDecoder;
  }
  
  public ResourceEncoder<Bitmap> getEncoder()
  {
    return this.encoder;
  }
  
  public ResourceDecoder<ParcelFileDescriptor, Bitmap> getSourceDecoder()
  {
    return this.sourceDecoder;
  }
  
  public Encoder<ParcelFileDescriptor> getSourceEncoder()
  {
    return this.sourceEncoder;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\bitmap\FileDescriptorBitmapDataLoadProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */