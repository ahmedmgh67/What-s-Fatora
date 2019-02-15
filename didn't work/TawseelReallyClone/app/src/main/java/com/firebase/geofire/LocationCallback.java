package com.firebase.geofire;

import com.google.firebase.database.DatabaseError;

public abstract interface LocationCallback
{
  public abstract void onCancelled(DatabaseError paramDatabaseError);
  
  public abstract void onLocationResult(String paramString, GeoLocation paramGeoLocation);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\firebase\geofire\LocationCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */