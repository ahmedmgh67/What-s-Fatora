package com.bumptech.glide.load.model;

import com.bumptech.glide.load.data.DataFetcher;
import java.net.URL;

public class UrlLoader<T>
  implements ModelLoader<URL, T>
{
  private final ModelLoader<GlideUrl, T> glideUrlLoader;
  
  public UrlLoader(ModelLoader<GlideUrl, T> paramModelLoader)
  {
    this.glideUrlLoader = paramModelLoader;
  }
  
  public DataFetcher<T> getResourceFetcher(URL paramURL, int paramInt1, int paramInt2)
  {
    return this.glideUrlLoader.getResourceFetcher(new GlideUrl(paramURL), paramInt1, paramInt2);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\model\UrlLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */