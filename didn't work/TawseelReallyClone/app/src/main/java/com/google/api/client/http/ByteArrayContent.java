package com.google.api.client.http;

import com.google.api.client.util.Preconditions;
import com.google.api.client.util.StringUtils;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public final class ByteArrayContent
  extends AbstractInputStreamContent
{
  private final byte[] byteArray;
  private final int length;
  private final int offset;
  
  public ByteArrayContent(String paramString, byte[] paramArrayOfByte)
  {
    this(paramString, paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public ByteArrayContent(String paramString, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    super(paramString);
    this.byteArray = ((byte[])Preconditions.checkNotNull(paramArrayOfByte));
    if ((paramInt1 >= 0) && (paramInt2 >= 0) && (paramInt1 + paramInt2 <= paramArrayOfByte.length)) {}
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkArgument(bool, "offset %s, length %s, array length %s", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramArrayOfByte.length) });
      this.offset = paramInt1;
      this.length = paramInt2;
      return;
    }
  }
  
  public static ByteArrayContent fromString(String paramString1, String paramString2)
  {
    return new ByteArrayContent(paramString1, StringUtils.getBytesUtf8(paramString2));
  }
  
  public InputStream getInputStream()
  {
    return new ByteArrayInputStream(this.byteArray, this.offset, this.length);
  }
  
  public long getLength()
  {
    return this.length;
  }
  
  public boolean retrySupported()
  {
    return true;
  }
  
  public ByteArrayContent setCloseInputStream(boolean paramBoolean)
  {
    return (ByteArrayContent)super.setCloseInputStream(paramBoolean);
  }
  
  public ByteArrayContent setType(String paramString)
  {
    return (ByteArrayContent)super.setType(paramString);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\ByteArrayContent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */