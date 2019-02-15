package com.google.api.client.testing.http.javanet;

import com.google.api.client.util.Beta;
import com.google.api.client.util.Preconditions;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Beta
public class MockHttpURLConnection
  extends HttpURLConnection
{
  @Deprecated
  public static final byte[] ERROR_BUF = new byte[5];
  @Deprecated
  public static final byte[] INPUT_BUF = new byte[1];
  private boolean doOutputCalled;
  private InputStream errorStream = null;
  private Map<String, List<String>> headers = new LinkedHashMap();
  private InputStream inputStream = null;
  private OutputStream outputStream = new ByteArrayOutputStream(0);
  
  public MockHttpURLConnection(URL paramURL)
  {
    super(paramURL);
  }
  
  public MockHttpURLConnection addHeader(String paramString1, String paramString2)
  {
    Preconditions.checkNotNull(paramString1);
    Preconditions.checkNotNull(paramString2);
    if (this.headers.containsKey(paramString1))
    {
      ((List)this.headers.get(paramString1)).add(paramString2);
      return this;
    }
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramString2);
    this.headers.put(paramString1, localArrayList);
    return this;
  }
  
  public void connect()
    throws IOException
  {}
  
  public void disconnect() {}
  
  public final boolean doOutputCalled()
  {
    return this.doOutputCalled;
  }
  
  public InputStream getErrorStream()
  {
    return this.errorStream;
  }
  
  public String getHeaderField(String paramString)
  {
    paramString = (List)this.headers.get(paramString);
    if (paramString == null) {
      return null;
    }
    return (String)paramString.get(0);
  }
  
  public Map<String, List<String>> getHeaderFields()
  {
    return this.headers;
  }
  
  public InputStream getInputStream()
    throws IOException
  {
    if (this.responseCode < 400) {
      return this.inputStream;
    }
    throw new IOException();
  }
  
  public OutputStream getOutputStream()
    throws IOException
  {
    if (this.outputStream != null) {
      return this.outputStream;
    }
    return super.getOutputStream();
  }
  
  public int getResponseCode()
    throws IOException
  {
    return this.responseCode;
  }
  
  public void setDoOutput(boolean paramBoolean)
  {
    this.doOutputCalled = true;
  }
  
  public MockHttpURLConnection setErrorStream(InputStream paramInputStream)
  {
    Preconditions.checkNotNull(paramInputStream);
    if (this.errorStream == null) {
      this.errorStream = paramInputStream;
    }
    return this;
  }
  
  public MockHttpURLConnection setInputStream(InputStream paramInputStream)
  {
    Preconditions.checkNotNull(paramInputStream);
    if (this.inputStream == null) {
      this.inputStream = paramInputStream;
    }
    return this;
  }
  
  public MockHttpURLConnection setOutputStream(OutputStream paramOutputStream)
  {
    this.outputStream = paramOutputStream;
    return this;
  }
  
  public MockHttpURLConnection setResponseCode(int paramInt)
  {
    if (paramInt >= -1) {}
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkArgument(bool);
      this.responseCode = paramInt;
      return this;
    }
  }
  
  public boolean usingProxy()
  {
    return false;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\testing\http\javanet\MockHttpURLConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */