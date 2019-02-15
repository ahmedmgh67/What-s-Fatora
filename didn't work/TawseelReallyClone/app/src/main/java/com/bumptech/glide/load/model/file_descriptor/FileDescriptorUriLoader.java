package com.bumptech.glide.load.model.file_descriptor;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.data.FileDescriptorAssetPathFetcher;
import com.bumptech.glide.load.data.FileDescriptorLocalUriFetcher;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.UriLoader;

public class FileDescriptorUriLoader
  extends UriLoader<ParcelFileDescriptor>
  implements FileDescriptorModelLoader<Uri>
{
  public FileDescriptorUriLoader(Context paramContext)
  {
    this(paramContext, Glide.buildFileDescriptorModelLoader(GlideUrl.class, paramContext));
  }
  
  public FileDescriptorUriLoader(Context paramContext, ModelLoader<GlideUrl, ParcelFileDescriptor> paramModelLoader)
  {
    super(paramContext, paramModelLoader);
  }
  
  protected DataFetcher<ParcelFileDescriptor> getAssetPathFetcher(Context paramContext, String paramString)
  {
    return new FileDescriptorAssetPathFetcher(paramContext.getApplicationContext().getAssets(), paramString);
  }
  
  protected DataFetcher<ParcelFileDescriptor> getLocalUriFetcher(Context paramContext, Uri paramUri)
  {
    return new FileDescriptorLocalUriFetcher(paramContext, paramUri);
  }
  
  public static class Factory
    implements ModelLoaderFactory<Uri, ParcelFileDescriptor>
  {
    public ModelLoader<Uri, ParcelFileDescriptor> build(Context paramContext, GenericLoaderFactory paramGenericLoaderFactory)
    {
      return new FileDescriptorUriLoader(paramContext, paramGenericLoaderFactory.buildModelLoader(GlideUrl.class, ParcelFileDescriptor.class));
    }
    
    public void teardown() {}
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\model\file_descriptor\FileDescriptorUriLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */