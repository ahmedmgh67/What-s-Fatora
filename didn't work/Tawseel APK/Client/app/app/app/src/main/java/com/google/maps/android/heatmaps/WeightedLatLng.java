package com.google.maps.android.heatmaps;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.projection.SphericalMercatorProjection;
import com.google.maps.android.quadtree.PointQuadTree.Item;

public class WeightedLatLng
  implements PointQuadTree.Item
{
  public static final double DEFAULT_INTENSITY = 1.0D;
  private static final SphericalMercatorProjection sProjection = new SphericalMercatorProjection(1.0D);
  private double mIntensity;
  private Point mPoint;
  
  public WeightedLatLng(LatLng paramLatLng)
  {
    this(paramLatLng, 1.0D);
  }
  
  public WeightedLatLng(LatLng paramLatLng, double paramDouble)
  {
    this.mPoint = sProjection.toPoint(paramLatLng);
    if (paramDouble >= 0.0D)
    {
      this.mIntensity = paramDouble;
      return;
    }
    this.mIntensity = 1.0D;
  }
  
  public double getIntensity()
  {
    return this.mIntensity;
  }
  
  public Point getPoint()
  {
    return this.mPoint;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\maps\android\heatmaps\WeightedLatLng.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */