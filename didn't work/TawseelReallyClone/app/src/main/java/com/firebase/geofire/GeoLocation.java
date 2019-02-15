package com.firebase.geofire;

public final class GeoLocation
{
  public final double latitude;
  public final double longitude;
  
  public GeoLocation(double paramDouble1, double paramDouble2)
  {
    if (!coordinatesValid(paramDouble1, paramDouble2)) {
      throw new IllegalArgumentException("Not a valid geo location: " + paramDouble1 + ", " + paramDouble2);
    }
    this.latitude = paramDouble1;
    this.longitude = paramDouble2;
  }
  
  public static boolean coordinatesValid(double paramDouble1, double paramDouble2)
  {
    return (paramDouble1 >= -90.0D) && (paramDouble1 <= 90.0D) && (paramDouble2 >= -180.0D) && (paramDouble2 <= 180.0D);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (getClass() != paramObject.getClass())) {
        return false;
      }
      paramObject = (GeoLocation)paramObject;
      if (Double.compare(((GeoLocation)paramObject).latitude, this.latitude) != 0) {
        return false;
      }
    } while (Double.compare(((GeoLocation)paramObject).longitude, this.longitude) == 0);
    return false;
  }
  
  public int hashCode()
  {
    long l = Double.doubleToLongBits(this.latitude);
    int i = (int)(l >>> 32 ^ l);
    l = Double.doubleToLongBits(this.longitude);
    return i * 31 + (int)(l >>> 32 ^ l);
  }
  
  public String toString()
  {
    return "GeoLocation(" + this.latitude + ", " + this.longitude + ")";
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\firebase\geofire\GeoLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */