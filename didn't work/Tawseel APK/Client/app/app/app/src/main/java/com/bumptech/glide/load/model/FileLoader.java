package com.bumptech.glide.load.model;

import android.net.Uri;
import com.bumptech.glide.load.data.DataFetcher;
import java.io.File;

public class FileLoader<T>
  implements ModelLoader<File, T>
{
  private final ModelLoader<Uri, T> uriLoader;
  
  public FileLoader(ModelLoader<Uri, T> paramModelLoader)
  {
    this.uriLoader = paramModelLoader;
  }
  
  public DataFetcher<T> getResourceFetcher(File paramFile, int paramInt1, int paramInt2)
  {
    return this.uriLoader.getResourceFetcher(Uri.fromFile(paramFile), paramInt1, paramInt2);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\model\FileLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */