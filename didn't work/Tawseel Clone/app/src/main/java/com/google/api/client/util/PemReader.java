package com.google.api.client.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Beta
public final class PemReader
{
  private static final Pattern BEGIN_PATTERN = Pattern.compile("-----BEGIN ([A-Z ]+)-----");
  private static final Pattern END_PATTERN = Pattern.compile("-----END ([A-Z ]+)-----");
  private BufferedReader reader;
  
  public PemReader(Reader paramReader)
  {
    this.reader = new BufferedReader(paramReader);
  }
  
  public static Section readFirstSectionAndClose(Reader paramReader)
    throws IOException
  {
    return readFirstSectionAndClose(paramReader, null);
  }
  
  public static Section readFirstSectionAndClose(Reader paramReader, String paramString)
    throws IOException
  {
    paramReader = new PemReader(paramReader);
    try
    {
      paramString = paramReader.readNextSection(paramString);
      return paramString;
    }
    finally
    {
      paramReader.close();
    }
  }
  
  public void close()
    throws IOException
  {
    this.reader.close();
  }
  
  public Section readNextSection()
    throws IOException
  {
    return readNextSection(null);
  }
  
  public Section readNextSection(String paramString)
    throws IOException
  {
    Object localObject1 = null;
    StringBuilder localStringBuilder = null;
    for (;;)
    {
      Object localObject2 = this.reader.readLine();
      if (localObject2 == null)
      {
        if (localObject1 == null) {}
        for (boolean bool = true;; bool = false)
        {
          Preconditions.checkArgument(bool, "missing end tag (%s)", new Object[] { localObject1 });
          return null;
        }
      }
      if (localStringBuilder == null)
      {
        localObject2 = BEGIN_PATTERN.matcher((CharSequence)localObject2);
        if (((Matcher)localObject2).matches())
        {
          localObject2 = ((Matcher)localObject2).group(1);
          if ((paramString == null) || (((String)localObject2).equals(paramString)))
          {
            localStringBuilder = new StringBuilder();
            localObject1 = localObject2;
          }
        }
      }
      else
      {
        Matcher localMatcher = END_PATTERN.matcher((CharSequence)localObject2);
        if (localMatcher.matches())
        {
          paramString = localMatcher.group(1);
          Preconditions.checkArgument(paramString.equals(localObject1), "end tag (%s) doesn't match begin tag (%s)", new Object[] { paramString, localObject1 });
          return new Section((String)localObject1, Base64.decodeBase64(localStringBuilder.toString()));
        }
        localStringBuilder.append((String)localObject2);
      }
    }
  }
  
  public static final class Section
  {
    private final byte[] base64decodedBytes;
    private final String title;
    
    Section(String paramString, byte[] paramArrayOfByte)
    {
      this.title = ((String)Preconditions.checkNotNull(paramString));
      this.base64decodedBytes = ((byte[])Preconditions.checkNotNull(paramArrayOfByte));
    }
    
    public byte[] getBase64DecodedBytes()
    {
      return this.base64decodedBytes;
    }
    
    public String getTitle()
    {
      return this.title;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\PemReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */