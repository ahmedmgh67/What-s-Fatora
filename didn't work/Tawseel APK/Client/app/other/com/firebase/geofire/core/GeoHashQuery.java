package com.firebase.geofire.core;

import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.util.Base32Utils;
import com.firebase.geofire.util.GeoUtils;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GeoHashQuery
{
  private final String endValue;
  private final String startValue;
  
  public GeoHashQuery(String paramString1, String paramString2)
  {
    this.startValue = paramString1;
    this.endValue = paramString2;
  }
  
  private boolean isPrefix(GeoHashQuery paramGeoHashQuery)
  {
    return (paramGeoHashQuery.endValue.compareTo(this.startValue) >= 0) && (paramGeoHashQuery.startValue.compareTo(this.startValue) < 0) && (paramGeoHashQuery.endValue.compareTo(this.endValue) < 0);
  }
  
  private boolean isSuperQuery(GeoHashQuery paramGeoHashQuery)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramGeoHashQuery.startValue.compareTo(this.startValue) <= 0)
    {
      bool1 = bool2;
      if (paramGeoHashQuery.endValue.compareTo(this.endValue) >= 0) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static Set<GeoHashQuery> queriesAtLocation(GeoLocation paramGeoLocation, double paramDouble)
  {
    int i = Math.max(1, Utils.bitsForBoundingBox(paramGeoLocation, paramDouble));
    int j = (int)Math.ceil(i / 5);
    double d1 = paramGeoLocation.latitude;
    double d2 = paramGeoLocation.longitude;
    double d4 = paramDouble / 110574.0D;
    double d3 = Math.min(90.0D, d1 + d4);
    d4 = Math.max(-90.0D, d1 - d4);
    paramDouble = Math.max(GeoUtils.distanceToLongitudeDegrees(paramDouble, d3), GeoUtils.distanceToLongitudeDegrees(paramDouble, d4));
    HashSet localHashSet = new HashSet();
    paramGeoLocation = new GeoHash(d1, d2, j);
    Object localObject1 = new GeoHash(d1, GeoUtils.wrapLongitude(d2 - paramDouble), j);
    Object localObject2 = new GeoHash(d1, GeoUtils.wrapLongitude(d2 + paramDouble), j);
    Object localObject3 = new GeoHash(d3, d2, j);
    Object localObject4 = new GeoHash(d3, GeoUtils.wrapLongitude(d2 - paramDouble), j);
    Object localObject5 = new GeoHash(d3, GeoUtils.wrapLongitude(d2 + paramDouble), j);
    GeoHash localGeoHash1 = new GeoHash(d4, d2, j);
    GeoHash localGeoHash2 = new GeoHash(d4, GeoUtils.wrapLongitude(d2 - paramDouble), j);
    GeoHash localGeoHash3 = new GeoHash(d4, GeoUtils.wrapLongitude(d2 + paramDouble), j);
    localHashSet.add(queryForGeoHash(paramGeoLocation, i));
    localHashSet.add(queryForGeoHash((GeoHash)localObject2, i));
    localHashSet.add(queryForGeoHash((GeoHash)localObject1, i));
    localHashSet.add(queryForGeoHash((GeoHash)localObject3, i));
    localHashSet.add(queryForGeoHash((GeoHash)localObject5, i));
    localHashSet.add(queryForGeoHash((GeoHash)localObject4, i));
    localHashSet.add(queryForGeoHash(localGeoHash1, i));
    localHashSet.add(queryForGeoHash(localGeoHash3, i));
    localHashSet.add(queryForGeoHash(localGeoHash2, i));
    for (;;)
    {
      paramGeoLocation = null;
      localObject1 = null;
      localObject4 = localHashSet.iterator();
      for (;;)
      {
        if (!((Iterator)localObject4).hasNext()) {
          break label478;
        }
        localObject3 = (GeoHashQuery)((Iterator)localObject4).next();
        localObject5 = localHashSet.iterator();
        if (((Iterator)localObject5).hasNext())
        {
          localObject2 = (GeoHashQuery)((Iterator)localObject5).next();
          if ((localObject3 == localObject2) || (!((GeoHashQuery)localObject3).canJoinWith((GeoHashQuery)localObject2))) {
            break;
          }
          paramGeoLocation = (GeoLocation)localObject3;
          localObject1 = localObject2;
        }
      }
      label478:
      if ((paramGeoLocation != null) && (localObject1 != null))
      {
        localHashSet.remove(paramGeoLocation);
        localHashSet.remove(localObject1);
        localHashSet.add(paramGeoLocation.joinWith((GeoHashQuery)localObject1));
      }
      for (i = 1; i == 0; i = 0) {
        return localHashSet;
      }
    }
  }
  
  public static GeoHashQuery queryForGeoHash(GeoHash paramGeoHash, int paramInt)
  {
    paramGeoHash = paramGeoHash.getGeoHashString();
    int i = (int)Math.ceil(paramInt / 5.0D);
    if (paramGeoHash.length() < i) {
      return new GeoHashQuery(paramGeoHash, paramGeoHash + "~");
    }
    String str = paramGeoHash.substring(0, i);
    paramGeoHash = str.substring(0, str.length() - 1);
    i = Base32Utils.base32CharToValue(str.charAt(str.length() - 1));
    paramInt = 5 - (paramInt - paramGeoHash.length() * 5);
    i = i >> paramInt << paramInt;
    paramInt = i + (1 << paramInt);
    str = paramGeoHash + Base32Utils.valueToBase32Char(i);
    if (paramInt > 31) {}
    for (paramGeoHash = paramGeoHash + "~";; paramGeoHash = paramGeoHash + Base32Utils.valueToBase32Char(paramInt)) {
      return new GeoHashQuery(str, paramGeoHash);
    }
  }
  
  public boolean canJoinWith(GeoHashQuery paramGeoHashQuery)
  {
    return (isPrefix(paramGeoHashQuery)) || (paramGeoHashQuery.isPrefix(this)) || (isSuperQuery(paramGeoHashQuery)) || (paramGeoHashQuery.isSuperQuery(this));
  }
  
  public boolean containsGeoHash(GeoHash paramGeoHash)
  {
    paramGeoHash = paramGeoHash.getGeoHashString();
    return (this.startValue.compareTo(paramGeoHash) <= 0) && (this.endValue.compareTo(paramGeoHash) > 0);
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
      paramObject = (GeoHashQuery)paramObject;
      if (!this.endValue.equals(((GeoHashQuery)paramObject).endValue)) {
        return false;
      }
    } while (this.startValue.equals(((GeoHashQuery)paramObject).startValue));
    return false;
  }
  
  public String getEndValue()
  {
    return this.endValue;
  }
  
  public String getStartValue()
  {
    return this.startValue;
  }
  
  public int hashCode()
  {
    return this.startValue.hashCode() * 31 + this.endValue.hashCode();
  }
  
  public GeoHashQuery joinWith(GeoHashQuery paramGeoHashQuery)
  {
    GeoHashQuery localGeoHashQuery;
    if (paramGeoHashQuery.isPrefix(this)) {
      localGeoHashQuery = new GeoHashQuery(this.startValue, paramGeoHashQuery.endValue);
    }
    do
    {
      return localGeoHashQuery;
      if (isPrefix(paramGeoHashQuery)) {
        return new GeoHashQuery(paramGeoHashQuery.startValue, this.endValue);
      }
      localGeoHashQuery = paramGeoHashQuery;
    } while (isSuperQuery(paramGeoHashQuery));
    if (paramGeoHashQuery.isSuperQuery(this)) {
      return this;
    }
    throw new IllegalArgumentException("Can't join these 2 queries: " + this + ", " + paramGeoHashQuery);
  }
  
  public String toString()
  {
    return "GeoHashQuery{startValue='" + this.startValue + '\'' + ", endValue='" + this.endValue + '\'' + '}';
  }
  
  public static class Utils
  {
    public static int bitsForBoundingBox(GeoLocation paramGeoLocation, double paramDouble)
    {
      double d2 = GeoUtils.distanceToLatitudeDegrees(paramDouble);
      double d1 = Math.min(90.0D, paramGeoLocation.latitude + d2);
      d2 = Math.max(-90.0D, paramGeoLocation.latitude - d2);
      return Math.min((int)Math.floor(bitsLatitude(paramDouble)) * 2, Math.min((int)Math.floor(bitsLongitude(paramDouble, d1)) * 2 - 1, (int)Math.floor(bitsLongitude(paramDouble, d2)) * 2 - 1));
    }
    
    public static double bitsLatitude(double paramDouble)
    {
      return Math.min(Math.log(2.000393E7D / paramDouble) / Math.log(2.0D), 110.0D);
    }
    
    public static double bitsLongitude(double paramDouble1, double paramDouble2)
    {
      double d = 1.0D;
      paramDouble2 = GeoUtils.distanceToLongitudeDegrees(paramDouble1, paramDouble2);
      paramDouble1 = d;
      if (Math.abs(paramDouble2) > 0.0D) {
        paramDouble1 = Math.max(1.0D, Math.log(360.0D / paramDouble2) / Math.log(2.0D));
      }
      return paramDouble1;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\firebase\geofire\core\GeoHashQuery.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */