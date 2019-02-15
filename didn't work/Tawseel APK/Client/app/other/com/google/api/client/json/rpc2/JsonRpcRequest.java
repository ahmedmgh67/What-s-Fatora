package com.google.api.client.json.rpc2;

import com.google.api.client.util.Beta;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;

@Beta
public class JsonRpcRequest
  extends GenericData
{
  @Key
  private Object id;
  @Key
  private final String jsonrpc = "2.0";
  @Key
  private String method;
  @Key
  private Object params;
  
  public JsonRpcRequest clone()
  {
    return (JsonRpcRequest)super.clone();
  }
  
  public Object getId()
  {
    return this.id;
  }
  
  public String getMethod()
  {
    return this.method;
  }
  
  public Object getParameters()
  {
    return this.params;
  }
  
  public String getVersion()
  {
    return "2.0";
  }
  
  public JsonRpcRequest set(String paramString, Object paramObject)
  {
    return (JsonRpcRequest)super.set(paramString, paramObject);
  }
  
  public void setId(Object paramObject)
  {
    this.id = paramObject;
  }
  
  public void setMethod(String paramString)
  {
    this.method = paramString;
  }
  
  public void setParameters(Object paramObject)
  {
    this.params = paramObject;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\json\rpc2\JsonRpcRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */