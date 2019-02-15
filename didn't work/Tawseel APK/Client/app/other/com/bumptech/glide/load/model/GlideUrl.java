package com.bumptech.glide.load.model;

import android.net.Uri;
import android.text.TextUtils;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class GlideUrl
{
  private static final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%";
  private final Headers headers;
  private String safeStringUrl;
  private URL safeUrl;
  private final String stringUrl;
  private final URL url;
  
  public GlideUrl(String paramString)
  {
    this(paramString, Headers.DEFAULT);
  }
  
  public GlideUrl(String paramString, Headers paramHeaders)
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("String url must not be empty or null: " + paramString);
    }
    if (paramHeaders == null) {
      throw new IllegalArgumentException("Headers must not be null");
    }
    this.stringUrl = paramString;
    this.url = null;
    this.headers = paramHeaders;
  }
  
  public GlideUrl(URL paramURL)
  {
    this(paramURL, Headers.DEFAULT);
  }
  
  public GlideUrl(URL paramURL, Headers paramHeaders)
  {
    if (paramURL == null) {
      throw new IllegalArgumentException("URL must not be null!");
    }
    if (paramHeaders == null) {
      throw new IllegalArgumentException("Headers must not be null");
    }
    this.url = paramURL;
    this.stringUrl = null;
    this.headers = paramHeaders;
  }
  
  private String getSafeStringUrl()
  {
    if (TextUtils.isEmpty(this.safeStringUrl))
    {
      String str2 = this.stringUrl;
      String str1 = str2;
      if (TextUtils.isEmpty(str2)) {
        str1 = this.url.toString();
      }
      this.safeStringUrl = Uri.encode(str1, "@#&=*+-_.,:!?()/~'%");
    }
    return this.safeStringUrl;
  }
  
  private URL getSafeUrl()
    throws MalformedURLException
  {
    if (this.safeUrl == null) {
      this.safeUrl = new URL(getSafeStringUrl());
    }
    return this.safeUrl;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ((paramObject instanceof GlideUrl))
    {
      paramObject = (GlideUrl)paramObject;
      bool1 = bool2;
      if (getCacheKey().equals(((GlideUrl)paramObject).getCacheKey()))
      {
        bool1 = bool2;
        if (this.headers.equals(((GlideUrl)paramObject).headers)) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public String getCacheKey()
  {
    if (this.stringUrl != null) {
      return this.stringUrl;
    }
    return this.url.toString();
  }
  
  public Map<String, String> getHeaders()
  {
    return this.headers.getHeaders();
  }
  
  public int hashCode()
  {
    return getCacheKey().hashCode() * 31 + this.headers.hashCode();
  }
  
  public String toString()
  {
    return getCacheKey() + '\n' + this.headers.toString();
  }
  
  public String toStringUrl()
  {
    return getSafeStringUrl();
  }
  
  public URL toURL()
    throws MalformedURLException
  {
    return getSafeUrl();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\model\GlideUrl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */