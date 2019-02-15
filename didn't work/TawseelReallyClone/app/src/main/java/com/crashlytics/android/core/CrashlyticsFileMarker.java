package com.crashlytics.android.core;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.persistence.FileStore;
import java.io.File;
import java.io.IOException;

class CrashlyticsFileMarker
{
  private final FileStore fileStore;
  private final String markerName;
  
  public CrashlyticsFileMarker(String paramString, FileStore paramFileStore)
  {
    this.markerName = paramString;
    this.fileStore = paramFileStore;
  }
  
  private File getMarkerFile()
  {
    return new File(this.fileStore.getFilesDir(), this.markerName);
  }
  
  public boolean create()
  {
    try
    {
      boolean bool = getMarkerFile().createNewFile();
      return bool;
    }
    catch (IOException localIOException)
    {
      Fabric.getLogger().e("CrashlyticsCore", "Error creating marker: " + this.markerName, localIOException);
    }
    return false;
  }
  
  public boolean isPresent()
  {
    return getMarkerFile().exists();
  }
  
  public boolean remove()
  {
    return getMarkerFile().delete();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\crashlytics\android\core\CrashlyticsFileMarker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */