package com.bumptech.glide.load.model.stream;

import android.content.Context;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.data.HttpUrlFetcher;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelCache;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import java.io.InputStream;

public class HttpUrlGlideUrlLoader
  implements ModelLoader<GlideUrl, InputStream>
{
  private final ModelCache<GlideUrl, GlideUrl> modelCache;
  
  public HttpUrlGlideUrlLoader()
  {
    this(null);
  }
  
  public HttpUrlGlideUrlLoader(ModelCache<GlideUrl, GlideUrl> paramModelCache)
  {
    this.modelCache = paramModelCache;
  }
  
  public DataFetcher<InputStream> getResourceFetcher(GlideUrl paramGlideUrl, int paramInt1, int paramInt2)
  {
    Object localObject = paramGlideUrl;
    if (this.modelCache != null)
    {
      GlideUrl localGlideUrl = (GlideUrl)this.modelCache.get(paramGlideUrl, 0, 0);
      localObject = localGlideUrl;
      if (localGlideUrl == null)
      {
        this.modelCache.put(paramGlideUrl, 0, 0, paramGlideUrl);
        localObject = paramGlideUrl;
      }
    }
    return new HttpUrlFetcher((GlideUrl)localObject);
  }
  
  public static class Factory
    implements ModelLoaderFactory<GlideUrl, InputStream>
  {
    private final ModelCache<GlideUrl, GlideUrl> modelCache = new ModelCache(500);
    
    public ModelLoader<GlideUrl, InputStream> build(Context paramContext, GenericLoaderFactory paramGenericLoaderFactory)
    {
      return new HttpUrlGlideUrlLoader(this.modelCache);
    }
    
    public void teardown() {}
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\model\stream\HttpUrlGlideUrlLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */