package com.directions.route;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

public class RouteException
  extends Exception
{
  private static final String TAG = "RouteException";
  private final String KEY_MESSAGE = "error_message";
  private final String KEY_STATUS = "status";
  private String message;
  private String statusCode;
  
  public RouteException(String paramString)
  {
    this.message = paramString;
  }
  
  public RouteException(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null)
    {
      this.statusCode = "";
      this.message = "Parsing error";
      return;
    }
    try
    {
      this.statusCode = paramJSONObject.getString("status");
      this.message = paramJSONObject.getString("error_message");
      return;
    }
    catch (JSONException paramJSONObject)
    {
      Log.e("RouteException", "JSONException while parsing RouteException argument. Msg: " + paramJSONObject.getMessage());
    }
  }
  
  public String getMessage()
  {
    return this.message;
  }
  
  public String getStatusCode()
  {
    return this.statusCode;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\directions\route\RouteException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */