package com.firebase.geofire;

import com.firebase.geofire.core.GeoHash;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseReference.CompletionListener;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeoFire
{
  private final DatabaseReference databaseReference;
  private final EventRaiser eventRaiser;
  
  public GeoFire(DatabaseReference paramDatabaseReference)
  {
    this.databaseReference = paramDatabaseReference;
    try
    {
      paramDatabaseReference = new AndroidEventRaiser();
      this.eventRaiser = paramDatabaseReference;
      return;
    }
    catch (Throwable paramDatabaseReference)
    {
      for (;;)
      {
        paramDatabaseReference = new ThreadEventRaiser();
      }
    }
  }
  
  static GeoLocation getLocationValue(DataSnapshot paramDataSnapshot)
  {
    try
    {
      paramDataSnapshot = (List)((Map)paramDataSnapshot.getValue(new GenericTypeIndicator() {})).get("l");
      Number localNumber1 = (Number)paramDataSnapshot.get(0);
      Number localNumber2 = (Number)paramDataSnapshot.get(1);
      double d1 = localNumber1.doubleValue();
      double d2 = localNumber2.doubleValue();
      if ((paramDataSnapshot.size() == 2) && (GeoLocation.coordinatesValid(d1, d2)))
      {
        paramDataSnapshot = new GeoLocation(d1, d2);
        return paramDataSnapshot;
      }
      return null;
    }
    catch (NullPointerException paramDataSnapshot)
    {
      return null;
    }
    catch (ClassCastException paramDataSnapshot) {}
    return null;
  }
  
  DatabaseReference getDatabaseRefForKey(String paramString)
  {
    return this.databaseReference.child(paramString);
  }
  
  public DatabaseReference getDatabaseReference()
  {
    return this.databaseReference;
  }
  
  public void getLocation(String paramString, LocationCallback paramLocationCallback)
  {
    getDatabaseRefForKey(paramString).addListenerForSingleValueEvent(new LocationValueEventListener(paramLocationCallback));
  }
  
  public GeoQuery queryAtLocation(GeoLocation paramGeoLocation, double paramDouble)
  {
    return new GeoQuery(this, paramGeoLocation, paramDouble);
  }
  
  void raiseEvent(Runnable paramRunnable)
  {
    this.eventRaiser.raiseEvent(paramRunnable);
  }
  
  public void removeLocation(String paramString)
  {
    removeLocation(paramString, null);
  }
  
  public void removeLocation(final String paramString, final CompletionListener paramCompletionListener)
  {
    if (paramString == null) {
      throw new NullPointerException();
    }
    DatabaseReference localDatabaseReference = getDatabaseRefForKey(paramString);
    if (paramCompletionListener != null)
    {
      localDatabaseReference.setValue(null, new DatabaseReference.CompletionListener()
      {
        public void onComplete(DatabaseError paramAnonymousDatabaseError, DatabaseReference paramAnonymousDatabaseReference)
        {
          paramCompletionListener.onComplete(paramString, paramAnonymousDatabaseError);
        }
      });
      return;
    }
    localDatabaseReference.setValue(null);
  }
  
  public void setLocation(String paramString, GeoLocation paramGeoLocation)
  {
    setLocation(paramString, paramGeoLocation, null);
  }
  
  public void setLocation(final String paramString, GeoLocation paramGeoLocation, final CompletionListener paramCompletionListener)
  {
    if (paramString == null) {
      throw new NullPointerException();
    }
    DatabaseReference localDatabaseReference = getDatabaseRefForKey(paramString);
    GeoHash localGeoHash = new GeoHash(paramGeoLocation);
    HashMap localHashMap = new HashMap();
    localHashMap.put("g", localGeoHash.getGeoHashString());
    localHashMap.put("l", Arrays.asList(new Double[] { Double.valueOf(paramGeoLocation.latitude), Double.valueOf(paramGeoLocation.longitude) }));
    if (paramCompletionListener != null)
    {
      localDatabaseReference.setValue(localHashMap, localGeoHash.getGeoHashString(), new DatabaseReference.CompletionListener()
      {
        public void onComplete(DatabaseError paramAnonymousDatabaseError, DatabaseReference paramAnonymousDatabaseReference)
        {
          paramCompletionListener.onComplete(paramString, paramAnonymousDatabaseError);
        }
      });
      return;
    }
    localDatabaseReference.setValue(localHashMap, localGeoHash.getGeoHashString());
  }
  
  public static abstract interface CompletionListener
  {
    public abstract void onComplete(String paramString, DatabaseError paramDatabaseError);
  }
  
  private static class LocationValueEventListener
    implements ValueEventListener
  {
    private final LocationCallback callback;
    
    LocationValueEventListener(LocationCallback paramLocationCallback)
    {
      this.callback = paramLocationCallback;
    }
    
    public void onCancelled(DatabaseError paramDatabaseError)
    {
      this.callback.onCancelled(paramDatabaseError);
    }
    
    public void onDataChange(DataSnapshot paramDataSnapshot)
    {
      if (paramDataSnapshot.getValue() == null)
      {
        this.callback.onLocationResult(paramDataSnapshot.getKey(), null);
        return;
      }
      GeoLocation localGeoLocation = GeoFire.getLocationValue(paramDataSnapshot);
      if (localGeoLocation != null)
      {
        this.callback.onLocationResult(paramDataSnapshot.getKey(), localGeoLocation);
        return;
      }
      paramDataSnapshot = "GeoFire data has invalid format: " + paramDataSnapshot.getValue();
      this.callback.onCancelled(DatabaseError.fromException(new Throwable(paramDataSnapshot)));
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\firebase\geofire\GeoFire.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */