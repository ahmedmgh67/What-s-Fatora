package com.bumptech.glide.load.model;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.net.Uri;
import android.util.Log;
import com.bumptech.glide.load.data.DataFetcher;

public class ResourceLoader<T>
  implements ModelLoader<Integer, T>
{
  private static final String TAG = "ResourceLoader";
  private final Resources resources;
  private final ModelLoader<Uri, T> uriLoader;
  
  public ResourceLoader(Context paramContext, ModelLoader<Uri, T> paramModelLoader)
  {
    this(paramContext.getResources(), paramModelLoader);
  }
  
  public ResourceLoader(Resources paramResources, ModelLoader<Uri, T> paramModelLoader)
  {
    this.resources = paramResources;
    this.uriLoader = paramModelLoader;
  }
  
  public DataFetcher<T> getResourceFetcher(Integer paramInteger, int paramInt1, int paramInt2)
  {
    Object localObject2 = null;
    try
    {
      localObject1 = Uri.parse("android.resource://" + this.resources.getResourcePackageName(paramInteger.intValue()) + '/' + this.resources.getResourceTypeName(paramInteger.intValue()) + '/' + this.resources.getResourceEntryName(paramInteger.intValue()));
      if (localObject1 != null) {
        return this.uriLoader.getResourceFetcher(localObject1, paramInt1, paramInt2);
      }
    }
    catch (Resources.NotFoundException localNotFoundException)
    {
      for (;;)
      {
        Object localObject1 = localObject2;
        if (Log.isLoggable("ResourceLoader", 5))
        {
          Log.w("ResourceLoader", "Received invalid resource id: " + paramInteger, localNotFoundException);
          localObject1 = localObject2;
        }
      }
    }
    return null;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\model\ResourceLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */