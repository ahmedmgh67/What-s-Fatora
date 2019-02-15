package com.google.api.client.http.javanet;

import com.google.api.client.http.LowLevelHttpResponse;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

final class NetHttpResponse
  extends LowLevelHttpResponse
{
  private final HttpURLConnection connection;
  private final ArrayList<String> headerNames = new ArrayList();
  private final ArrayList<String> headerValues = new ArrayList();
  private final int responseCode;
  private final String responseMessage;
  
  NetHttpResponse(HttpURLConnection paramHttpURLConnection)
    throws IOException
  {
    this.connection = paramHttpURLConnection;
    int j = paramHttpURLConnection.getResponseCode();
    int i = j;
    if (j == -1) {
      i = 0;
    }
    this.responseCode = i;
    this.responseMessage = paramHttpURLConnection.getResponseMessage();
    ArrayList localArrayList1 = this.headerNames;
    ArrayList localArrayList2 = this.headerValues;
    paramHttpURLConnection = paramHttpURLConnection.getHeaderFields().entrySet().iterator();
    while (paramHttpURLConnection.hasNext())
    {
      Object localObject = (Map.Entry)paramHttpURLConnection.next();
      String str1 = (String)((Map.Entry)localObject).getKey();
      if (str1 != null)
      {
        localObject = ((List)((Map.Entry)localObject).getValue()).iterator();
        while (((Iterator)localObject).hasNext())
        {
          String str2 = (String)((Iterator)localObject).next();
          if (str2 != null)
          {
            localArrayList1.add(str1);
            localArrayList2.add(str2);
          }
        }
      }
    }
  }
  
  public void disconnect()
  {
    this.connection.disconnect();
  }
  
  public InputStream getContent()
    throws IOException
  {
    try
    {
      InputStream localInputStream1 = this.connection.getInputStream();
      if (localInputStream1 == null) {
        return null;
      }
    }
    catch (IOException localIOException)
    {
      InputStream localInputStream2;
      for (;;)
      {
        localInputStream2 = this.connection.getErrorStream();
      }
      return new SizeValidatingInputStream(localInputStream2);
    }
  }
  
  public String getContentEncoding()
  {
    return this.connection.getContentEncoding();
  }
  
  public long getContentLength()
  {
    String str = this.connection.getHeaderField("Content-Length");
    if (str == null) {
      return -1L;
    }
    return Long.parseLong(str);
  }
  
  public String getContentType()
  {
    return this.connection.getHeaderField("Content-Type");
  }
  
  public int getHeaderCount()
  {
    return this.headerNames.size();
  }
  
  public String getHeaderName(int paramInt)
  {
    return (String)this.headerNames.get(paramInt);
  }
  
  public String getHeaderValue(int paramInt)
  {
    return (String)this.headerValues.get(paramInt);
  }
  
  public String getReasonPhrase()
  {
    return this.responseMessage;
  }
  
  public int getStatusCode()
  {
    return this.responseCode;
  }
  
  public String getStatusLine()
  {
    String str = this.connection.getHeaderField(0);
    if ((str != null) && (str.startsWith("HTTP/1."))) {
      return str;
    }
    return null;
  }
  
  private final class SizeValidatingInputStream
    extends FilterInputStream
  {
    private long bytesRead = 0L;
    
    public SizeValidatingInputStream(InputStream paramInputStream)
    {
      super();
    }
    
    private void throwIfFalseEOF()
      throws IOException
    {
      long l = NetHttpResponse.this.getContentLength();
      if (l == -1L) {}
      while ((this.bytesRead == 0L) || (this.bytesRead >= l)) {
        return;
      }
      throw new IOException("Connection closed prematurely: bytesRead = " + this.bytesRead + ", Content-Length = " + l);
    }
    
    public int read()
      throws IOException
    {
      int i = this.in.read();
      if (i == -1)
      {
        throwIfFalseEOF();
        return i;
      }
      this.bytesRead += 1L;
      return i;
    }
    
    public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      paramInt1 = this.in.read(paramArrayOfByte, paramInt1, paramInt2);
      if (paramInt1 == -1)
      {
        throwIfFalseEOF();
        return paramInt1;
      }
      this.bytesRead += paramInt1;
      return paramInt1;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\javanet\NetHttpResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */