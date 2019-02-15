package com.directions.route;

import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Routing
  extends AbstractRouting
{
  private final boolean alternativeRoutes;
  private final int avoidKinds;
  private final String key;
  private final String language;
  private final boolean optimize;
  private final AbstractRouting.TravelMode travelMode;
  private final List<LatLng> waypoints;
  
  private Routing(Builder paramBuilder)
  {
    super(paramBuilder.listener);
    this.travelMode = paramBuilder.travelMode;
    this.waypoints = paramBuilder.waypoints;
    this.avoidKinds = paramBuilder.avoidKinds;
    this.optimize = paramBuilder.optimize;
    this.alternativeRoutes = paramBuilder.alternativeRoutes;
    this.language = paramBuilder.language;
    this.key = paramBuilder.key;
  }
  
  protected String constructURL()
  {
    StringBuilder localStringBuilder = new StringBuilder("https://maps.googleapis.com/maps/api/directions/json?");
    LatLng localLatLng = (LatLng)this.waypoints.get(0);
    localStringBuilder.append("origin=");
    localStringBuilder.append(localLatLng.latitude);
    localStringBuilder.append(',');
    localStringBuilder.append(localLatLng.longitude);
    localLatLng = (LatLng)this.waypoints.get(this.waypoints.size() - 1);
    localStringBuilder.append("&destination=");
    localStringBuilder.append(localLatLng.latitude);
    localStringBuilder.append(',');
    localStringBuilder.append(localLatLng.longitude);
    localStringBuilder.append("&mode=");
    localStringBuilder.append(this.travelMode.getValue());
    if (this.waypoints.size() > 2)
    {
      localStringBuilder.append("&waypoints=");
      if (this.optimize) {
        localStringBuilder.append("optimize:true|");
      }
      int i = 1;
      while (i < this.waypoints.size() - 1)
      {
        localLatLng = (LatLng)this.waypoints.get(i);
        localStringBuilder.append("via:");
        localStringBuilder.append(localLatLng.latitude);
        localStringBuilder.append(",");
        localStringBuilder.append(localLatLng.longitude);
        localStringBuilder.append("|");
        i += 1;
      }
    }
    if (this.avoidKinds > 0)
    {
      localStringBuilder.append("&avoid=");
      localStringBuilder.append(AbstractRouting.AvoidKind.getRequestParam(this.avoidKinds));
    }
    if (this.alternativeRoutes) {
      localStringBuilder.append("&alternatives=true");
    }
    localStringBuilder.append("&sensor=true");
    if (this.language != null) {
      localStringBuilder.append("&language=").append(this.language);
    }
    if (this.key != null) {
      localStringBuilder.append("&key=").append(this.key);
    }
    return localStringBuilder.toString();
  }
  
  public static class Builder
  {
    private boolean alternativeRoutes = false;
    private int avoidKinds = 0;
    private String key = null;
    private String language = null;
    private RoutingListener listener = null;
    private boolean optimize = false;
    private AbstractRouting.TravelMode travelMode = AbstractRouting.TravelMode.DRIVING;
    private List<LatLng> waypoints = new ArrayList();
    
    public Builder alternativeRoutes(boolean paramBoolean)
    {
      this.alternativeRoutes = paramBoolean;
      return this;
    }
    
    public Builder avoid(AbstractRouting.AvoidKind... paramVarArgs)
    {
      int j = paramVarArgs.length;
      int i = 0;
      while (i < j)
      {
        AbstractRouting.AvoidKind localAvoidKind = paramVarArgs[i];
        this.avoidKinds |= localAvoidKind.getBitValue();
        i += 1;
      }
      return this;
    }
    
    public Routing build()
    {
      if (this.waypoints.size() < 2) {
        throw new IllegalArgumentException("Must supply at least two waypoints to route between.");
      }
      if ((this.waypoints.size() <= 2) && (this.optimize)) {
        throw new IllegalArgumentException("You need at least three waypoints to enable optimize");
      }
      return new Routing(this, null);
    }
    
    public Builder key(String paramString)
    {
      this.key = paramString;
      return this;
    }
    
    public Builder language(String paramString)
    {
      this.language = paramString;
      return this;
    }
    
    public Builder optimize(boolean paramBoolean)
    {
      this.optimize = paramBoolean;
      return this;
    }
    
    public Builder travelMode(AbstractRouting.TravelMode paramTravelMode)
    {
      this.travelMode = paramTravelMode;
      return this;
    }
    
    public Builder waypoints(List<LatLng> paramList)
    {
      this.waypoints = new ArrayList(paramList);
      return this;
    }
    
    public Builder waypoints(LatLng... paramVarArgs)
    {
      this.waypoints.clear();
      Collections.addAll(this.waypoints, paramVarArgs);
      return this;
    }
    
    public Builder withListener(RoutingListener paramRoutingListener)
    {
      this.listener = paramRoutingListener;
      return this;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\directions\route\Routing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */