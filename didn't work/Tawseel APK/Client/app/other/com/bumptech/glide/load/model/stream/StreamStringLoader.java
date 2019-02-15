package com.bumptech.glide.load.model.stream;

import android.content.Context;
import android.net.Uri;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.StringLoader;
import java.io.InputStream;

public class StreamStringLoader
  extends StringLoader<InputStream>
  implements StreamModelLoader<String>
{
  public StreamStringLoader(Context paramContext)
  {
    this(Glide.buildStreamModelLoader(Uri.class, paramContext));
  }
  
  public StreamStringLoader(ModelLoader<Uri, InputStream> paramModelLoader)
  {
    super(paramModelLoader);
  }
  
  public static class Factory
    implements ModelLoaderFactory<String, InputStream>
  {
    public ModelLoader<String, InputStream> build(Context paramContext, GenericLoaderFactory paramGenericLoaderFactory)
    {
      return new StreamStringLoader(paramGenericLoaderFactory.buildModelLoader(Uri.class, InputStream.class));
    }
    
    public void teardown() {}
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\model\stream\StreamStringLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */