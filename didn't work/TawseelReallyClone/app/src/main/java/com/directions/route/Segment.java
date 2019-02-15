package com.directions.route;

import com.google.android.gms.maps.model.LatLng;

public class Segment
{
  private double distance;
  private String instruction;
  private int length;
  private LatLng start;
  
  public Segment copy()
  {
    Segment localSegment = new Segment();
    localSegment.start = this.start;
    localSegment.instruction = this.instruction;
    localSegment.length = this.length;
    localSegment.distance = this.distance;
    return localSegment;
  }
  
  public double getDistance()
  {
    return this.distance;
  }
  
  public String getInstruction()
  {
    return this.instruction;
  }
  
  public int getLength()
  {
    return this.length;
  }
  
  public void setDistance(double paramDouble)
  {
    this.distance = paramDouble;
  }
  
  public void setInstruction(String paramString)
  {
    this.instruction = paramString;
  }
  
  public void setLength(int paramInt)
  {
    this.length = paramInt;
  }
  
  public void setPoint(LatLng paramLatLng)
  {
    this.start = paramLatLng;
  }
  
  public LatLng startPoint()
  {
    return this.start;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\directions\route\Segment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */