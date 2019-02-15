package com.firebase.geofire.core;

import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.util.Base32Utils;

public class GeoHash
{
  private static final int DEFAULT_PRECISION = 10;
  public static final int MAX_PRECISION = 22;
  public static final int MAX_PRECISION_BITS = 110;
  private final String geoHash;
  
  public GeoHash(double paramDouble1, double paramDouble2)
  {
    this(paramDouble1, paramDouble2, 10);
  }
  
  public GeoHash(double paramDouble1, double paramDouble2, int paramInt)
  {
    if (paramInt < 1) {
      throw new IllegalArgumentException("Precision of GeoHash must be larger than zero!");
    }
    if (paramInt > 22) {
      throw new IllegalArgumentException("Precision of a GeoHash must be less than 23!");
    }
    if (!GeoLocation.coordinatesValid(paramDouble1, paramDouble2)) {
      throw new IllegalArgumentException(String.format("Not valid location coordinates: [%f, %f]", new Object[] { Double.valueOf(paramDouble1), Double.valueOf(paramDouble2) }));
    }
    char[] arrayOfChar = new char[paramInt];
    int j = 0;
    while (j < paramInt)
    {
      int i = 0;
      int k = 0;
      if (k < 5)
      {
        int m;
        label119:
        double d1;
        label127:
        double[] arrayOfDouble;
        label149:
        double d2;
        if ((j * 5 + k) % 2 == 0)
        {
          m = 1;
          if (m == 0) {
            break label201;
          }
          d1 = paramDouble2;
          if (m == 0) {
            break label207;
          }
          arrayOfDouble = new double[] { -180.0D, 180.0D };
          d2 = (arrayOfDouble[0] + arrayOfDouble[1]) / 2.0D;
          if (d1 <= d2) {
            break label227;
          }
          i = (i << 1) + 1;
          arrayOfDouble[0] = d2;
        }
        for (;;)
        {
          k += 1;
          break;
          m = 0;
          break label119;
          label201:
          d1 = paramDouble1;
          break label127;
          label207:
          arrayOfDouble = new double[] { -90.0D, 90.0D };
          break label149;
          label227:
          i <<= 1;
          arrayOfDouble[1] = d2;
        }
      }
      arrayOfChar[j] = Base32Utils.valueToBase32Char(i);
      j += 1;
    }
    this.geoHash = new String(arrayOfChar);
  }
  
  public GeoHash(GeoLocation paramGeoLocation)
  {
    this(paramGeoLocation.latitude, paramGeoLocation.longitude, 10);
  }
  
  public GeoHash(String paramString)
  {
    if ((paramString.length() == 0) || (!Base32Utils.isValidBase32String(paramString))) {
      throw new IllegalArgumentException("Not a valid geoHash: " + paramString);
    }
    this.geoHash = paramString;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if ((paramObject == null) || (getClass() != paramObject.getClass())) {
      return false;
    }
    paramObject = (GeoHash)paramObject;
    return this.geoHash.equals(((GeoHash)paramObject).geoHash);
  }
  
  public String getGeoHashString()
  {
    return this.geoHash;
  }
  
  public int hashCode()
  {
    return this.geoHash.hashCode();
  }
  
  public String toString()
  {
    return "GeoHash{geoHash='" + this.geoHash + '\'' + '}';
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\firebase\geofire\core\GeoHash.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */