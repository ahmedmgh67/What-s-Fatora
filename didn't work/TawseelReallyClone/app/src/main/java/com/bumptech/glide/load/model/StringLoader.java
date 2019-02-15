package com.bumptech.glide.load.model;

import android.net.Uri;
import android.text.TextUtils;
import com.bumptech.glide.load.data.DataFetcher;
import java.io.File;

public class StringLoader<T>
  implements ModelLoader<String, T>
{
  private final ModelLoader<Uri, T> uriLoader;
  
  public StringLoader(ModelLoader<Uri, T> paramModelLoader)
  {
    this.uriLoader = paramModelLoader;
  }
  
  private static Uri toFileUri(String paramString)
  {
    return Uri.fromFile(new File(paramString));
  }
  
  public DataFetcher<T> getResourceFetcher(String paramString, int paramInt1, int paramInt2)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    Object localObject;
    if (paramString.startsWith("/")) {
      localObject = toFileUri(paramString);
    }
    for (;;)
    {
      return this.uriLoader.getResourceFetcher(localObject, paramInt1, paramInt2);
      Uri localUri = Uri.parse(paramString);
      localObject = localUri;
      if (localUri.getScheme() == null) {
        localObject = toFileUri(paramString);
      }
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\model\StringLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */