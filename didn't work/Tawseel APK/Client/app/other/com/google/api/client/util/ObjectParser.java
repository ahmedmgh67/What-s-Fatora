package com.google.api.client.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

public abstract interface ObjectParser
{
  public abstract <T> T parseAndClose(InputStream paramInputStream, Charset paramCharset, Class<T> paramClass)
    throws IOException;
  
  public abstract Object parseAndClose(InputStream paramInputStream, Charset paramCharset, Type paramType)
    throws IOException;
  
  public abstract <T> T parseAndClose(Reader paramReader, Class<T> paramClass)
    throws IOException;
  
  public abstract Object parseAndClose(Reader paramReader, Type paramType)
    throws IOException;
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\ObjectParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */