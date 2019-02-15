package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.util.ByteBufferBackedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class ByteBufferSerializer
  extends StdScalarSerializer<ByteBuffer>
{
  public ByteBufferSerializer()
  {
    super(ByteBuffer.class);
  }
  
  public void serialize(ByteBuffer paramByteBuffer, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    if (paramByteBuffer.hasArray())
    {
      paramJsonGenerator.writeBinary(paramByteBuffer.array(), 0, paramByteBuffer.limit());
      return;
    }
    paramByteBuffer = paramByteBuffer.asReadOnlyBuffer();
    if (paramByteBuffer.position() > 0) {
      paramByteBuffer.rewind();
    }
    paramSerializerProvider = new ByteBufferBackedInputStream(paramByteBuffer);
    paramJsonGenerator.writeBinary(paramSerializerProvider, paramByteBuffer.remaining());
    paramSerializerProvider.close();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\ser\std\ByteBufferSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */