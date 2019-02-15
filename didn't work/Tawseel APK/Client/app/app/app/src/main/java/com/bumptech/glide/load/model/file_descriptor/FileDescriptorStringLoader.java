package com.bumptech.glide.load.model.file_descriptor;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.StringLoader;

public class FileDescriptorStringLoader
  extends StringLoader<ParcelFileDescriptor>
  implements FileDescriptorModelLoader<String>
{
  public FileDescriptorStringLoader(Context paramContext)
  {
    this(Glide.buildFileDescriptorModelLoader(Uri.class, paramContext));
  }
  
  public FileDescriptorStringLoader(ModelLoader<Uri, ParcelFileDescriptor> paramModelLoader)
  {
    super(paramModelLoader);
  }
  
  public static class Factory
    implements ModelLoaderFactory<String, ParcelFileDescriptor>
  {
    public ModelLoader<String, ParcelFileDescriptor> build(Context paramContext, GenericLoaderFactory paramGenericLoaderFactory)
    {
      return new FileDescriptorStringLoader(paramGenericLoaderFactory.buildModelLoader(Uri.class, ParcelFileDescriptor.class));
    }
    
    public void teardown() {}
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\model\file_descriptor\FileDescriptorStringLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */