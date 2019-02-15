package com.bumptech.glide.load.model.stream;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.data.MediaStoreThumbFetcher;
import com.bumptech.glide.load.model.ModelLoader;
import java.io.InputStream;

public class MediaStoreStreamLoader
  implements ModelLoader<Uri, InputStream>
{
  private final Context context;
  private final ModelLoader<Uri, InputStream> uriLoader;
  
  public MediaStoreStreamLoader(Context paramContext, ModelLoader<Uri, InputStream> paramModelLoader)
  {
    this.context = paramContext;
    this.uriLoader = paramModelLoader;
  }
  
  public DataFetcher<InputStream> getResourceFetcher(Uri paramUri, int paramInt1, int paramInt2)
  {
    return new MediaStoreThumbFetcher(this.context, paramUri, this.uriLoader.getResourceFetcher(paramUri, paramInt1, paramInt2), paramInt1, paramInt2);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\model\stream\MediaStoreStreamLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */