package com.google.api.client.http;

import com.google.api.client.util.Data;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Types;
import com.google.api.client.util.escape.CharEscapers;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class UrlEncodedContent
  extends AbstractHttpContent
{
  private Object data;
  
  public UrlEncodedContent(Object paramObject)
  {
    super(UrlEncodedParser.MEDIA_TYPE);
    setData(paramObject);
  }
  
  private static boolean appendParam(boolean paramBoolean, Writer paramWriter, String paramString, Object paramObject)
    throws IOException
  {
    boolean bool = paramBoolean;
    if (paramObject != null)
    {
      if (Data.isNull(paramObject)) {
        bool = paramBoolean;
      }
    }
    else {
      return bool;
    }
    if (paramBoolean)
    {
      paramBoolean = false;
      label26:
      paramWriter.write(paramString);
      if (!(paramObject instanceof Enum)) {
        break label86;
      }
    }
    label86:
    for (paramString = FieldInfo.of((Enum)paramObject).getName();; paramString = paramObject.toString())
    {
      paramString = CharEscapers.escapeUri(paramString);
      bool = paramBoolean;
      if (paramString.length() == 0) {
        break;
      }
      paramWriter.write("=");
      paramWriter.write(paramString);
      return paramBoolean;
      paramWriter.write("&");
      break label26;
    }
  }
  
  public static UrlEncodedContent getContent(HttpRequest paramHttpRequest)
  {
    Object localObject = paramHttpRequest.getContent();
    if (localObject != null) {
      return (UrlEncodedContent)localObject;
    }
    localObject = new UrlEncodedContent(new HashMap());
    paramHttpRequest.setContent((HttpContent)localObject);
    return (UrlEncodedContent)localObject;
  }
  
  public final Object getData()
  {
    return this.data;
  }
  
  public UrlEncodedContent setData(Object paramObject)
  {
    this.data = Preconditions.checkNotNull(paramObject);
    return this;
  }
  
  public UrlEncodedContent setMediaType(HttpMediaType paramHttpMediaType)
  {
    super.setMediaType(paramHttpMediaType);
    return this;
  }
  
  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    paramOutputStream = new BufferedWriter(new OutputStreamWriter(paramOutputStream, getCharset()));
    boolean bool1 = true;
    Iterator localIterator = Data.mapOf(this.data).entrySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject2 = (Map.Entry)localIterator.next();
      Object localObject1 = ((Map.Entry)localObject2).getValue();
      if (localObject1 != null)
      {
        localObject2 = CharEscapers.escapeUri((String)((Map.Entry)localObject2).getKey());
        Class localClass = localObject1.getClass();
        if (((localObject1 instanceof Iterable)) || (localClass.isArray()))
        {
          localObject1 = Types.iterableOf(localObject1).iterator();
          for (boolean bool2 = bool1;; bool2 = appendParam(bool2, paramOutputStream, (String)localObject2, ((Iterator)localObject1).next()))
          {
            bool1 = bool2;
            if (!((Iterator)localObject1).hasNext()) {
              break;
            }
          }
        }
        bool1 = appendParam(bool1, paramOutputStream, (String)localObject2, localObject1);
      }
    }
    paramOutputStream.flush();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\UrlEncodedContent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */