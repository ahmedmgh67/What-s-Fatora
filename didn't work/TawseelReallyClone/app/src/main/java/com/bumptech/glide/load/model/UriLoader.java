package com.bumptech.glide.load.model;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.load.data.DataFetcher;

public abstract class UriLoader<T>
  implements ModelLoader<Uri, T>
{
  private final Context context;
  private final ModelLoader<GlideUrl, T> urlLoader;
  
  public UriLoader(Context paramContext, ModelLoader<GlideUrl, T> paramModelLoader)
  {
    this.context = paramContext;
    this.urlLoader = paramModelLoader;
  }
  
  private static boolean isLocalUri(String paramString)
  {
    return ("file".equals(paramString)) || ("content".equals(paramString)) || ("android.resource".equals(paramString));
  }
  
  protected abstract DataFetcher<T> getAssetPathFetcher(Context paramContext, String paramString);
  
  protected abstract DataFetcher<T> getLocalUriFetcher(Context paramContext, Uri paramUri);
  
  public final DataFetcher<T> getResourceFetcher(Uri paramUri, int paramInt1, int paramInt2)
  {
    String str = paramUri.getScheme();
    Object localObject2 = null;
    Object localObject1;
    if (isLocalUri(str)) {
      if (AssetUriParser.isAssetUri(paramUri))
      {
        paramUri = AssetUriParser.toAssetPath(paramUri);
        localObject1 = getAssetPathFetcher(this.context, paramUri);
      }
    }
    do
    {
      do
      {
        return (DataFetcher<T>)localObject1;
        return getLocalUriFetcher(this.context, paramUri);
        localObject1 = localObject2;
      } while (this.urlLoader == null);
      if ("http".equals(str)) {
        break;
      }
      localObject1 = localObject2;
    } while (!"https".equals(str));
    return this.urlLoader.getResourceFetcher(new GlideUrl(paramUri.toString()), paramInt1, paramInt2);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\model\UriLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */