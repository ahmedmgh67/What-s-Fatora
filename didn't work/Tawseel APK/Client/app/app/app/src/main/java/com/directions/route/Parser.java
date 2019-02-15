package com.directions.route;

import java.util.List;

public abstract interface Parser
{
  public abstract List<Route> parse()
    throws RouteException;
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\directions\route\Parser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */