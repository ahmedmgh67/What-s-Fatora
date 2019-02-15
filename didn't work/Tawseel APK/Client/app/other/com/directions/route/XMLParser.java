package com.directions.route;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class XMLParser
{
  protected static final String MARKER = "marker";
  protected static final String MARKERS = "markers";
  protected URL feedUrl;
  
  protected XMLParser(String paramString)
  {
    try
    {
      this.feedUrl = new URL(paramString);
      return;
    }
    catch (MalformedURLException paramString)
    {
      Log.e("Routing Error", paramString.getMessage());
    }
  }
  
  protected InputStream getInputStream()
  {
    try
    {
      InputStream localInputStream = this.feedUrl.openConnection().getInputStream();
      return localInputStream;
    }
    catch (IOException localIOException)
    {
      Log.e("Routing Error", localIOException.getMessage());
    }
    return null;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\directions\route\XMLParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */