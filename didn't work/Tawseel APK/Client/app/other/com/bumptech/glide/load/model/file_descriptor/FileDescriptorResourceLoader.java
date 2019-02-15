package com.bumptech.glide.load.model.file_descriptor;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.ResourceLoader;

public class FileDescriptorResourceLoader
  extends ResourceLoader<ParcelFileDescriptor>
  implements FileDescriptorModelLoader<Integer>
{
  public FileDescriptorResourceLoader(Context paramContext)
  {
    this(paramContext, Glide.buildFileDescriptorModelLoader(Uri.class, paramContext));
  }
  
  public FileDescriptorResourceLoader(Context paramContext, ModelLoader<Uri, ParcelFileDescriptor> paramModelLoader)
  {
    super(paramContext, paramModelLoader);
  }
  
  public static class Factory
    implements ModelLoaderFactory<Integer, ParcelFileDescriptor>
  {
    public ModelLoader<Integer, ParcelFileDescriptor> build(Context paramContext, GenericLoaderFactory paramGenericLoaderFactory)
    {
      return new FileDescriptorResourceLoader(paramContext, paramGenericLoaderFactory.buildModelLoader(Uri.class, ParcelFileDescriptor.class));
    }
    
    public void teardown() {}
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\model\file_descriptor\FileDescriptorResourceLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */