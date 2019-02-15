package com.google.api.client.http;

import com.google.api.client.util.Preconditions;
import com.google.api.client.util.StreamingContent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class MultipartContent
  extends AbstractHttpContent
{
  static final String NEWLINE = "\r\n";
  private static final String TWO_DASHES = "--";
  private ArrayList<Part> parts = new ArrayList();
  
  public MultipartContent()
  {
    super(new HttpMediaType("multipart/related").setParameter("boundary", "__END_OF_PART__"));
  }
  
  public MultipartContent addPart(Part paramPart)
  {
    this.parts.add(Preconditions.checkNotNull(paramPart));
    return this;
  }
  
  public final String getBoundary()
  {
    return getMediaType().getParameter("boundary");
  }
  
  public final Collection<Part> getParts()
  {
    return Collections.unmodifiableCollection(this.parts);
  }
  
  public boolean retrySupported()
  {
    Iterator localIterator = this.parts.iterator();
    while (localIterator.hasNext()) {
      if (!((Part)localIterator.next()).content.retrySupported()) {
        return false;
      }
    }
    return true;
  }
  
  public MultipartContent setBoundary(String paramString)
  {
    getMediaType().setParameter("boundary", (String)Preconditions.checkNotNull(paramString));
    return this;
  }
  
  public MultipartContent setContentParts(Collection<? extends HttpContent> paramCollection)
  {
    this.parts = new ArrayList(paramCollection.size());
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext()) {
      addPart(new Part((HttpContent)paramCollection.next()));
    }
    return this;
  }
  
  public MultipartContent setMediaType(HttpMediaType paramHttpMediaType)
  {
    super.setMediaType(paramHttpMediaType);
    return this;
  }
  
  public MultipartContent setParts(Collection<Part> paramCollection)
  {
    this.parts = new ArrayList(paramCollection);
    return this;
  }
  
  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    OutputStreamWriter localOutputStreamWriter = new OutputStreamWriter(paramOutputStream, getCharset());
    String str = getBoundary();
    Iterator localIterator = this.parts.iterator();
    if (localIterator.hasNext())
    {
      Part localPart = (Part)localIterator.next();
      HttpHeaders localHttpHeaders = new HttpHeaders().setAcceptEncoding(null);
      if (localPart.headers != null) {
        localHttpHeaders.fromHttpHeaders(localPart.headers);
      }
      localHttpHeaders.setContentEncoding(null).setUserAgent(null).setContentType(null).setContentLength(null).set("Content-Transfer-Encoding", null);
      Object localObject1 = localPart.content;
      Object localObject2 = null;
      long l;
      if (localObject1 != null)
      {
        localHttpHeaders.set("Content-Transfer-Encoding", Arrays.asList(new String[] { "binary" }));
        localHttpHeaders.setContentType(((HttpContent)localObject1).getType());
        localObject2 = localPart.encoding;
        if (localObject2 != null) {
          break label266;
        }
        l = ((HttpContent)localObject1).getLength();
      }
      for (;;)
      {
        localObject2 = localObject1;
        if (l != -1L)
        {
          localHttpHeaders.setContentLength(Long.valueOf(l));
          localObject2 = localObject1;
        }
        localOutputStreamWriter.write("--");
        localOutputStreamWriter.write(str);
        localOutputStreamWriter.write("\r\n");
        HttpHeaders.serializeHeadersForMultipartRequests(localHttpHeaders, null, null, localOutputStreamWriter);
        if (localObject2 != null)
        {
          localOutputStreamWriter.write("\r\n");
          localOutputStreamWriter.flush();
          ((StreamingContent)localObject2).writeTo(paramOutputStream);
        }
        localOutputStreamWriter.write("\r\n");
        break;
        label266:
        localHttpHeaders.setContentEncoding(((HttpEncoding)localObject2).getName());
        localObject2 = new HttpEncodingStreamingContent((StreamingContent)localObject1, (HttpEncoding)localObject2);
        l = AbstractHttpContent.computeLength((HttpContent)localObject1);
        localObject1 = localObject2;
      }
    }
    localOutputStreamWriter.write("--");
    localOutputStreamWriter.write(str);
    localOutputStreamWriter.write("--");
    localOutputStreamWriter.write("\r\n");
    localOutputStreamWriter.flush();
  }
  
  public static final class Part
  {
    HttpContent content;
    HttpEncoding encoding;
    HttpHeaders headers;
    
    public Part()
    {
      this(null);
    }
    
    public Part(HttpContent paramHttpContent)
    {
      this(null, paramHttpContent);
    }
    
    public Part(HttpHeaders paramHttpHeaders, HttpContent paramHttpContent)
    {
      setHeaders(paramHttpHeaders);
      setContent(paramHttpContent);
    }
    
    public HttpContent getContent()
    {
      return this.content;
    }
    
    public HttpEncoding getEncoding()
    {
      return this.encoding;
    }
    
    public HttpHeaders getHeaders()
    {
      return this.headers;
    }
    
    public Part setContent(HttpContent paramHttpContent)
    {
      this.content = paramHttpContent;
      return this;
    }
    
    public Part setEncoding(HttpEncoding paramHttpEncoding)
    {
      this.encoding = paramHttpEncoding;
      return this;
    }
    
    public Part setHeaders(HttpHeaders paramHttpHeaders)
    {
      this.headers = paramHttpHeaders;
      return this;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\MultipartContent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */