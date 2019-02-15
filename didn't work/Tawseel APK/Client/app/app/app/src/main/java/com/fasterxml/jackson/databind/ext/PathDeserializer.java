package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathDeserializer
  extends StdScalarDeserializer<Path>
{
  private static final long serialVersionUID = 1L;
  
  public PathDeserializer()
  {
    super(Path.class);
  }
  
  public Path deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if ((localJsonToken != null) && (localJsonToken.isScalarValue())) {
      return Paths.get(paramJsonParser.getValueAsString(), new String[0]);
    }
    throw paramDeserializationContext.mappingException(Path.class, localJsonToken);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\ext\PathDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */