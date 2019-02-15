package com.google.maps.android.geometry;

public class Bounds
{
  public final double maxX;
  public final double maxY;
  public final double midX;
  public final double midY;
  public final double minX;
  public final double minY;
  
  public Bounds(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    this.minX = paramDouble1;
    this.minY = paramDouble3;
    this.maxX = paramDouble2;
    this.maxY = paramDouble4;
    this.midX = ((paramDouble1 + paramDouble2) / 2.0D);
    this.midY = ((paramDouble3 + paramDouble4) / 2.0D);
  }
  
  public boolean contains(double paramDouble1, double paramDouble2)
  {
    return (this.minX <= paramDouble1) && (paramDouble1 <= this.maxX) && (this.minY <= paramDouble2) && (paramDouble2 <= this.maxY);
  }
  
  public boolean contains(Bounds paramBounds)
  {
    return (paramBounds.minX >= this.minX) && (paramBounds.maxX <= this.maxX) && (paramBounds.minY >= this.minY) && (paramBounds.maxY <= this.maxY);
  }
  
  public boolean contains(Point paramPoint)
  {
    return contains(paramPoint.x, paramPoint.y);
  }
  
  public boolean intersects(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    return (paramDouble1 < this.maxX) && (this.minX < paramDouble2) && (paramDouble3 < this.maxY) && (this.minY < paramDouble4);
  }
  
  public boolean intersects(Bounds paramBounds)
  {
    return intersects(paramBounds.minX, paramBounds.maxX, paramBounds.minY, paramBounds.maxY);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\maps\android\geometry\Bounds.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */