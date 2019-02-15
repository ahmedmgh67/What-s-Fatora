package com.google.api.client.util;

import java.io.IOException;
import java.io.OutputStream;

public class ByteArrayStreamingContent
  implements StreamingContent
{
  private final byte[] byteArray;
  private final int length;
  private final int offset;
  
  public ByteArrayStreamingContent(byte[] paramArrayOfByte)
  {
    this(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public ByteArrayStreamingContent(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.byteArray = ((byte[])Preconditions.checkNotNull(paramArrayOfByte));
    if ((paramInt1 >= 0) && (paramInt2 >= 0) && (paramInt1 + paramInt2 <= paramArrayOfByte.length)) {}
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkArgument(bool);
      this.offset = paramInt1;
      this.length = paramInt2;
      return;
    }
  }
  
  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    paramOutputStream.write(this.byteArray, this.offset, this.length);
    paramOutputStream.flush();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\ByteArrayStreamingContent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */