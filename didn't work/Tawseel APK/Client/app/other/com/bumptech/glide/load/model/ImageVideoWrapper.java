package com.bumptech.glide.load.model;

import android.os.ParcelFileDescriptor;
import java.io.InputStream;

public class ImageVideoWrapper
{
  private final ParcelFileDescriptor fileDescriptor;
  private final InputStream streamData;
  
  public ImageVideoWrapper(InputStream paramInputStream, ParcelFileDescriptor paramParcelFileDescriptor)
  {
    this.streamData = paramInputStream;
    this.fileDescriptor = paramParcelFileDescriptor;
  }
  
  public ParcelFileDescriptor getFileDescriptor()
  {
    return this.fileDescriptor;
  }
  
  public InputStream getStream()
  {
    return this.streamData;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\model\ImageVideoWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */