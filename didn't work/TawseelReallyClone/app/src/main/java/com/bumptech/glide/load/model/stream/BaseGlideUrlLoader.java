package com.bumptech.glide.load.model.stream;

import android.content.Context;
import android.text.TextUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.Headers;
import com.bumptech.glide.load.model.ModelCache;
import com.bumptech.glide.load.model.ModelLoader;
import java.io.InputStream;

public abstract class BaseGlideUrlLoader<T>
  implements StreamModelLoader<T>
{
  private final ModelLoader<GlideUrl, InputStream> concreteLoader;
  private final ModelCache<T, GlideUrl> modelCache;
  
  public BaseGlideUrlLoader(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public BaseGlideUrlLoader(Context paramContext, ModelCache<T, GlideUrl> paramModelCache)
  {
    this(Glide.buildModelLoader(GlideUrl.class, InputStream.class, paramContext), paramModelCache);
  }
  
  public BaseGlideUrlLoader(ModelLoader<GlideUrl, InputStream> paramModelLoader)
  {
    this(paramModelLoader, null);
  }
  
  public BaseGlideUrlLoader(ModelLoader<GlideUrl, InputStream> paramModelLoader, ModelCache<T, GlideUrl> paramModelCache)
  {
    this.concreteLoader = paramModelLoader;
    this.modelCache = paramModelCache;
  }
  
  protected Headers getHeaders(T paramT, int paramInt1, int paramInt2)
  {
    return Headers.DEFAULT;
  }
  
  public DataFetcher<InputStream> getResourceFetcher(T paramT, int paramInt1, int paramInt2)
  {
    Object localObject1 = null;
    if (this.modelCache != null) {
      localObject1 = (GlideUrl)this.modelCache.get(paramT, paramInt1, paramInt2);
    }
    Object localObject2 = localObject1;
    if (localObject1 == null)
    {
      localObject1 = getUrl(paramT, paramInt1, paramInt2);
      if (TextUtils.isEmpty((CharSequence)localObject1)) {
        return null;
      }
      localObject1 = new GlideUrl((String)localObject1, getHeaders(paramT, paramInt1, paramInt2));
      localObject2 = localObject1;
      if (this.modelCache != null)
      {
        this.modelCache.put(paramT, paramInt1, paramInt2, localObject1);
        localObject2 = localObject1;
      }
    }
    return this.concreteLoader.getResourceFetcher(localObject2, paramInt1, paramInt2);
  }
  
  protected abstract String getUrl(T paramT, int paramInt1, int paramInt2);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\model\stream\BaseGlideUrlLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */