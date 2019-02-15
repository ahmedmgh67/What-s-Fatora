package com.crashlytics.android.core;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Logger;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class InvalidSessionReport
  implements Report
{
  private final Map<String, String> customHeaders;
  private final File[] files;
  private final String identifier;
  
  public InvalidSessionReport(String paramString, File[] paramArrayOfFile)
  {
    this.files = paramArrayOfFile;
    this.customHeaders = new HashMap(ReportUploader.HEADER_INVALID_CLS_FILE);
    this.identifier = paramString;
  }
  
  public Map<String, String> getCustomHeaders()
  {
    return Collections.unmodifiableMap(this.customHeaders);
  }
  
  public File getFile()
  {
    return this.files[0];
  }
  
  public String getFileName()
  {
    return this.files[0].getName();
  }
  
  public File[] getFiles()
  {
    return this.files;
  }
  
  public String getIdentifier()
  {
    return this.identifier;
  }
  
  public void remove()
  {
    File[] arrayOfFile = this.files;
    int j = arrayOfFile.length;
    int i = 0;
    while (i < j)
    {
      File localFile = arrayOfFile[i];
      Fabric.getLogger().d("CrashlyticsCore", "Removing invalid report file at " + localFile.getPath());
      localFile.delete();
      i += 1;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\crashlytics\android\core\InvalidSessionReport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */