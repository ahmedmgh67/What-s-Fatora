package com.bumptech.glide.load.resource.file;

import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import java.io.File;

public class FileDecoder
  implements ResourceDecoder<File, File>
{
  public Resource<File> decode(File paramFile, int paramInt1, int paramInt2)
  {
    return new FileResource(paramFile);
  }
  
  public String getId()
  {
    return "";
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\file\FileDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */