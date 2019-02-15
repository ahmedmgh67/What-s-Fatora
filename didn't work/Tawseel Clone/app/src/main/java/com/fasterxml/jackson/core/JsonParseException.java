package com.fasterxml.jackson.core;

public class JsonParseException
  extends JsonProcessingException
{
  private static final long serialVersionUID = 2L;
  protected JsonParser _processor;
  
  public JsonParseException(JsonParser paramJsonParser, String paramString) {}
  
  public JsonParseException(JsonParser paramJsonParser, String paramString, JsonLocation paramJsonLocation)
  {
    super(paramString, paramJsonLocation);
    this._processor = paramJsonParser;
  }
  
  public JsonParseException(JsonParser paramJsonParser, String paramString, JsonLocation paramJsonLocation, Throwable paramThrowable)
  {
    super(paramString, paramJsonLocation, paramThrowable);
    this._processor = paramJsonParser;
  }
  
  public JsonParseException(JsonParser paramJsonParser, String paramString, Throwable paramThrowable) {}
  
  @Deprecated
  public JsonParseException(String paramString, JsonLocation paramJsonLocation)
  {
    super(paramString, paramJsonLocation);
  }
  
  @Deprecated
  public JsonParseException(String paramString, JsonLocation paramJsonLocation, Throwable paramThrowable)
  {
    super(paramString, paramJsonLocation, paramThrowable);
  }
  
  public JsonParser getProcessor()
  {
    return this._processor;
  }
  
  public JsonParseException withParser(JsonParser paramJsonParser)
  {
    this._processor = paramJsonParser;
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\core\JsonParseException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */