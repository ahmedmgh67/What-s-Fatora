package com.bumptech.glide.load.data;

import android.text.TextUtils;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.util.ContentLengthInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HttpUrlFetcher
  implements DataFetcher<InputStream>
{
  private static final HttpUrlConnectionFactory DEFAULT_CONNECTION_FACTORY = new DefaultHttpUrlConnectionFactory(null);
  private static final int MAXIMUM_REDIRECTS = 5;
  private static final String TAG = "HttpUrlFetcher";
  private final HttpUrlConnectionFactory connectionFactory;
  private final GlideUrl glideUrl;
  private volatile boolean isCancelled;
  private InputStream stream;
  private HttpURLConnection urlConnection;
  
  public HttpUrlFetcher(GlideUrl paramGlideUrl)
  {
    this(paramGlideUrl, DEFAULT_CONNECTION_FACTORY);
  }
  
  HttpUrlFetcher(GlideUrl paramGlideUrl, HttpUrlConnectionFactory paramHttpUrlConnectionFactory)
  {
    this.glideUrl = paramGlideUrl;
    this.connectionFactory = paramHttpUrlConnectionFactory;
  }
  
  private InputStream getStreamForSuccessfulRequest(HttpURLConnection paramHttpURLConnection)
    throws IOException
  {
    int i;
    if (TextUtils.isEmpty(paramHttpURLConnection.getContentEncoding())) {
      i = paramHttpURLConnection.getContentLength();
    }
    for (this.stream = ContentLengthInputStream.obtain(paramHttpURLConnection.getInputStream(), i);; this.stream = paramHttpURLConnection.getInputStream())
    {
      return this.stream;
      if (Log.isLoggable("HttpUrlFetcher", 3)) {
        Log.d("HttpUrlFetcher", "Got non empty content encoding: " + paramHttpURLConnection.getContentEncoding());
      }
    }
  }
  
  private InputStream loadDataWithRedirects(URL paramURL1, int paramInt, URL paramURL2, Map<String, String> paramMap)
    throws IOException
  {
    if (paramInt >= 5) {
      throw new IOException("Too many (> 5) redirects!");
    }
    if (paramURL2 != null) {
      try
      {
        if (paramURL1.toURI().equals(paramURL2.toURI())) {
          throw new IOException("In re-direct loop");
        }
      }
      catch (URISyntaxException paramURL2) {}
    }
    this.urlConnection = this.connectionFactory.build(paramURL1);
    paramURL2 = paramMap.entrySet().iterator();
    while (paramURL2.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramURL2.next();
      this.urlConnection.addRequestProperty((String)localEntry.getKey(), (String)localEntry.getValue());
    }
    this.urlConnection.setConnectTimeout(2500);
    this.urlConnection.setReadTimeout(2500);
    this.urlConnection.setUseCaches(false);
    this.urlConnection.setDoInput(true);
    this.urlConnection.connect();
    if (this.isCancelled) {
      return null;
    }
    int i = this.urlConnection.getResponseCode();
    if (i / 100 == 2) {
      return getStreamForSuccessfulRequest(this.urlConnection);
    }
    if (i / 100 == 3)
    {
      paramURL2 = this.urlConnection.getHeaderField("Location");
      if (TextUtils.isEmpty(paramURL2)) {
        throw new IOException("Received empty or null redirect url");
      }
      return loadDataWithRedirects(new URL(paramURL1, paramURL2), paramInt + 1, paramURL1, paramMap);
    }
    if (i == -1) {
      throw new IOException("Unable to retrieve response code from HttpUrlConnection.");
    }
    throw new IOException("Request failed " + i + ": " + this.urlConnection.getResponseMessage());
  }
  
  public void cancel()
  {
    this.isCancelled = true;
  }
  
  public void cleanup()
  {
    if (this.stream != null) {}
    try
    {
      this.stream.close();
      if (this.urlConnection != null) {
        this.urlConnection.disconnect();
      }
      return;
    }
    catch (IOException localIOException)
    {
      for (;;) {}
    }
  }
  
  public String getId()
  {
    return this.glideUrl.getCacheKey();
  }
  
  public InputStream loadData(Priority paramPriority)
    throws Exception
  {
    return loadDataWithRedirects(this.glideUrl.toURL(), 0, null, this.glideUrl.getHeaders());
  }
  
  private static class DefaultHttpUrlConnectionFactory
    implements HttpUrlFetcher.HttpUrlConnectionFactory
  {
    public HttpURLConnection build(URL paramURL)
      throws IOException
    {
      return (HttpURLConnection)paramURL.openConnection();
    }
  }
  
  static abstract interface HttpUrlConnectionFactory
  {
    public abstract HttpURLConnection build(URL paramURL)
      throws IOException;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\data\HttpUrlFetcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */