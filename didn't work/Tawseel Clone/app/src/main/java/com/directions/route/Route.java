package com.directions.route;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.LatLngBounds.Builder;
import com.google.android.gms.maps.model.PolylineOptions;
import java.util.ArrayList;
import java.util.List;

public class Route
{
  private String copyright;
  private String country;
  private String distanceText;
  private int distanceValue;
  private String durationText;
  private int durationValue;
  private String endAddressText;
  private LatLngBounds latLgnBounds;
  private int length;
  private String name;
  private final List<LatLng> points = new ArrayList();
  private PolylineOptions polyOptions;
  private String polyline;
  private List<Segment> segments = new ArrayList();
  private String warning;
  
  public void addPoint(LatLng paramLatLng)
  {
    this.points.add(paramLatLng);
  }
  
  public void addPoints(List<LatLng> paramList)
  {
    this.points.addAll(paramList);
  }
  
  public void addSegment(Segment paramSegment)
  {
    this.segments.add(paramSegment);
  }
  
  public String getCopyright()
  {
    return this.copyright;
  }
  
  public String getCountry()
  {
    return this.country;
  }
  
  public String getDistanceText()
  {
    return this.distanceText;
  }
  
  public int getDistanceValue()
  {
    return this.distanceValue;
  }
  
  public String getDurationText()
  {
    return this.durationText;
  }
  
  public int getDurationValue()
  {
    return this.durationValue;
  }
  
  public String getEndAddressText()
  {
    return this.endAddressText;
  }
  
  public LatLngBounds getLatLgnBounds()
  {
    return this.latLgnBounds;
  }
  
  public int getLength()
  {
    return this.length;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public List<LatLng> getPoints()
  {
    return this.points;
  }
  
  public PolylineOptions getPolyOptions()
  {
    return this.polyOptions;
  }
  
  public String getPolyline()
  {
    return this.polyline;
  }
  
  public List<Segment> getSegments()
  {
    return this.segments;
  }
  
  public String getWarning()
  {
    return this.warning;
  }
  
  public void setCopyright(String paramString)
  {
    this.copyright = paramString;
  }
  
  public void setCountry(String paramString)
  {
    this.country = paramString;
  }
  
  public void setDistanceText(String paramString)
  {
    this.distanceText = paramString;
  }
  
  public void setDistanceValue(int paramInt)
  {
    this.distanceValue = paramInt;
  }
  
  public void setDurationText(String paramString)
  {
    this.durationText = paramString;
  }
  
  public void setDurationValue(int paramInt)
  {
    this.durationValue = paramInt;
  }
  
  public void setEndAddressText(String paramString)
  {
    this.endAddressText = paramString;
  }
  
  public void setLatLgnBounds(LatLng paramLatLng1, LatLng paramLatLng2)
  {
    LatLngBounds.Builder localBuilder = new LatLngBounds.Builder();
    localBuilder.include(paramLatLng1);
    localBuilder.include(paramLatLng2);
    this.latLgnBounds = localBuilder.build();
  }
  
  public void setLength(int paramInt)
  {
    this.length = paramInt;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public void setPolyOptions(PolylineOptions paramPolylineOptions)
  {
    this.polyOptions = paramPolylineOptions;
  }
  
  public void setPolyline(String paramString)
  {
    this.polyline = paramString;
  }
  
  public void setSegments(List<Segment> paramList)
  {
    this.segments = paramList;
  }
  
  public void setWarning(String paramString)
  {
    this.warning = paramString;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\directions\route\Route.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */