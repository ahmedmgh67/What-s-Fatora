package com.firebase.geofire.util;

import com.firebase.geofire.GeoLocation;

public class GeoUtils
{
  public static double distance(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    double d = Math.toRadians(paramDouble1 - paramDouble3);
    paramDouble2 = Math.toRadians(paramDouble2 - paramDouble4);
    paramDouble1 = Math.sin(d / 2.0D) * Math.sin(d / 2.0D) + Math.cos(Math.toRadians(paramDouble1)) * Math.cos(Math.toRadians(paramDouble3)) * Math.sin(paramDouble2 / 2.0D) * Math.sin(paramDouble2 / 2.0D);
    return 1.27359893E7D * Math.atan2(Math.sqrt(paramDouble1), Math.sqrt(1.0D - paramDouble1));
  }
  
  public static double distance(GeoLocation paramGeoLocation1, GeoLocation paramGeoLocation2)
  {
    return distance(paramGeoLocation1.latitude, paramGeoLocation1.longitude, paramGeoLocation2.latitude, paramGeoLocation2.longitude);
  }
  
  public static double distanceToLatitudeDegrees(double paramDouble)
  {
    return paramDouble / 110574.0D;
  }
  
  public static double distanceToLongitudeDegrees(double paramDouble1, double paramDouble2)
  {
    paramDouble2 = Math.toRadians(paramDouble2);
    paramDouble2 = Math.cos(paramDouble2) * 6378137.0D * 3.141592653589793D / 180.0D * (1.0D / Math.sqrt(1.0D - 0.00669447819799D * Math.sin(paramDouble2) * Math.sin(paramDouble2)));
    if (paramDouble2 < 1.0E-12D)
    {
      paramDouble2 = paramDouble1;
      if (paramDouble1 > 0.0D) {
        paramDouble2 = 360.0D;
      }
      return paramDouble2;
    }
    return Math.min(360.0D, paramDouble1 / paramDouble2);
  }
  
  public static double wrapLongitude(double paramDouble)
  {
    if ((paramDouble >= -180.0D) && (paramDouble <= 180.0D)) {
      return paramDouble;
    }
    paramDouble += 180.0D;
    if (paramDouble > 0.0D) {
      return paramDouble % 360.0D - 180.0D;
    }
    return 180.0D - -paramDouble % 360.0D;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\firebase\geofire\util\GeoUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */