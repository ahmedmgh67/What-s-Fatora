package com.crashlytics.android.core;

import java.io.File;
import java.util.Map;

abstract interface Report
{
  public abstract Map<String, String> getCustomHeaders();
  
  public abstract File getFile();
  
  public abstract String getFileName();
  
  public abstract File[] getFiles();
  
  public abstract String getIdentifier();
  
  public abstract void remove();
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\crashlytics\android\core\Report.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */