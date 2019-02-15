package com.directions.route;

import java.util.ArrayList;

public abstract interface RoutingListener
{
  public abstract void onRoutingCancelled();
  
  public abstract void onRoutingFailure(RouteException paramRouteException);
  
  public abstract void onRoutingStart();
  
  public abstract void onRoutingSuccess(ArrayList<Route> paramArrayList, int paramInt);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\directions\route\RoutingListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */