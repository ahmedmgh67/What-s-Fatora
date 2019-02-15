package com.directions.route;

import android.util.Log;
import com.google.android.gms.maps.model.LatLng;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleParser
  extends XMLParser
  implements Parser
{
  private String OK = "OK";
  private int distance;
  
  public GoogleParser(String paramString)
  {
    super(paramString);
  }
  
  private static String convertStreamToString(InputStream paramInputStream)
  {
    if (paramInputStream == null) {
      return null;
    }
    localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream));
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      for (;;)
      {
        String str = localBufferedReader.readLine();
        if (str == null) {
          break;
        }
        localStringBuilder.append(str);
      }
      try
      {
        paramInputStream.close();
        localBufferedReader.close();
        throw ((Throwable)localObject);
      }
      catch (IOException paramInputStream)
      {
        for (;;)
        {
          Log.e("Routing Error", paramInputStream.getMessage());
        }
      }
    }
    catch (IOException localIOException)
    {
      localIOException = localIOException;
      Log.e("Routing Error", localIOException.getMessage());
      try
      {
        paramInputStream.close();
        localBufferedReader.close();
        for (;;)
        {
          return localStringBuilder.toString();
          try
          {
            paramInputStream.close();
            localBufferedReader.close();
          }
          catch (IOException paramInputStream)
          {
            Log.e("Routing Error", paramInputStream.getMessage());
          }
        }
      }
      catch (IOException paramInputStream)
      {
        for (;;)
        {
          Log.e("Routing Error", paramInputStream.getMessage());
        }
      }
    }
    finally {}
  }
  
  private List<LatLng> decodePolyLine(String paramString)
  {
    int i2 = paramString.length();
    int i = 0;
    ArrayList localArrayList = new ArrayList();
    int k = 0;
    int j = 0;
    int m;
    int n;
    if (i < i2)
    {
      m = 0;
      n = 0;
    }
    for (int i1 = i;; i1 = i)
    {
      i = i1 + 1;
      i1 = paramString.charAt(i1) - '?';
      n |= (i1 & 0x1F) << m;
      m += 5;
      if (i1 < 32)
      {
        if ((n & 0x1) != 0)
        {
          m = n >> 1 ^ 0xFFFFFFFF;
          label94:
          i1 = k + m;
          k = 0;
          m = 0;
        }
        for (n = i;; n = i)
        {
          i = n + 1;
          n = paramString.charAt(n) - '?';
          m |= (n & 0x1F) << k;
          k += 5;
          if (n < 32)
          {
            if ((m & 0x1) != 0) {}
            for (k = m >> 1 ^ 0xFFFFFFFF;; k = m >> 1)
            {
              j += k;
              localArrayList.add(new LatLng(i1 / 100000.0D, j / 100000.0D));
              k = i1;
              break;
              m = n >> 1;
              break label94;
            }
            return localArrayList;
          }
        }
      }
    }
  }
  
  public ArrayList<Route> parse()
    throws RouteException
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = convertStreamToString(getInputStream());
    if (localObject1 == null) {
      throw new RouteException("Result is null");
    }
    try
    {
      localObject1 = new JSONObject((String)localObject1);
      if (!((JSONObject)localObject1).getString("status").equals(this.OK)) {
        throw new RouteException((JSONObject)localObject1);
      }
    }
    catch (JSONException localJSONException)
    {
      throw new RouteException("JSONException. Msg: " + localJSONException.getMessage());
    }
    localObject1 = ((JSONObject)localObject1).getJSONArray("routes");
    int i = 0;
    Route localRoute;
    Segment localSegment;
    JSONObject localJSONObject1;
    JSONObject localJSONObject2;
    Object localObject2;
    int k;
    if (i < ((JSONArray)localObject1).length())
    {
      localRoute = new Route();
      localSegment = new Segment();
      localJSONObject1 = ((JSONArray)localObject1).getJSONObject(i);
      localJSONObject2 = localJSONObject1.getJSONObject("bounds");
      localObject2 = localJSONObject2.getJSONObject("northeast");
      localJSONObject2 = localJSONObject2.getJSONObject("southwest");
      localRoute.setLatLgnBounds(new LatLng(((JSONObject)localObject2).getDouble("lat"), ((JSONObject)localObject2).getDouble("lng")), new LatLng(localJSONObject2.getDouble("lat"), localJSONObject2.getDouble("lng")));
      localJSONObject2 = localJSONObject1.getJSONArray("legs").getJSONObject(0);
      localObject2 = localJSONObject2.getJSONArray("steps");
      k = ((JSONArray)localObject2).length();
      localRoute.setName(localJSONObject2.getString("start_address") + " to " + localJSONObject2.getString("end_address"));
      localRoute.setCopyright(localJSONObject1.getString("copyrights"));
      localRoute.setDurationText(localJSONObject2.getJSONObject("duration").getString("text"));
      localRoute.setDurationValue(localJSONObject2.getJSONObject("duration").getInt("value"));
      localRoute.setDistanceText(localJSONObject2.getJSONObject("distance").getString("text"));
      localRoute.setDistanceValue(localJSONObject2.getJSONObject("distance").getInt("value"));
      localRoute.setEndAddressText(localJSONObject2.getString("end_address"));
      localRoute.setLength(localJSONObject2.getJSONObject("distance").getInt("value"));
      if (localJSONObject1.getJSONArray("warnings").isNull(0)) {
        break label605;
      }
      localRoute.setWarning(localJSONObject1.getJSONArray("warnings").getString(0));
    }
    for (;;)
    {
      int j;
      if (j < k)
      {
        localJSONObject1 = ((JSONArray)localObject2).getJSONObject(j);
        localJSONObject2 = localJSONObject1.getJSONObject("start_location");
        localSegment.setPoint(new LatLng(localJSONObject2.getDouble("lat"), localJSONObject2.getDouble("lng")));
        int m = localJSONObject1.getJSONObject("distance").getInt("value");
        this.distance += m;
        localSegment.setLength(m);
        localSegment.setDistance(this.distance / 1000.0D);
        localSegment.setInstruction(localJSONObject1.getString("html_instructions").replaceAll("<(.*?)*>", ""));
        localRoute.addPoints(decodePolyLine(localJSONObject1.getJSONObject("polyline").getString("points")));
        localRoute.addSegment(localSegment.copy());
        j += 1;
      }
      else
      {
        localJSONException.add(localRoute);
        i += 1;
        break;
        return localJSONException;
        label605:
        j = 0;
      }
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\directions\route\GoogleParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */