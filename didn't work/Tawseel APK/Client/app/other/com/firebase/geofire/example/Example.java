package com.firebase.geofire.example;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import java.io.PrintStream;

public class Example
{
  public static void main(String[] paramArrayOfString)
    throws InterruptedException
  {
    new GeoFire(FirebaseDatabase.getInstance().getReferenceFromUrl("https://geofire-v3.firebaseio.com/geofire")).queryAtLocation(new GeoLocation(37.7D, -122.4D), 10.0D).addGeoQueryEventListener(new GeoQueryEventListener()
    {
      public void onGeoQueryError(DatabaseError paramAnonymousDatabaseError)
      {
        System.err.println("There was an error querying locations: " + paramAnonymousDatabaseError.getMessage());
      }
      
      public void onGeoQueryReady()
      {
        System.out.println("All initial key entered events have been fired!");
      }
      
      public void onKeyEntered(String paramAnonymousString, GeoLocation paramAnonymousGeoLocation)
      {
        System.out.println(String.format("%s entered at [%f, %f]", new Object[] { paramAnonymousString, Double.valueOf(paramAnonymousGeoLocation.latitude), Double.valueOf(paramAnonymousGeoLocation.longitude) }));
      }
      
      public void onKeyExited(String paramAnonymousString)
      {
        System.out.println(String.format("%s exited", new Object[] { paramAnonymousString }));
      }
      
      public void onKeyMoved(String paramAnonymousString, GeoLocation paramAnonymousGeoLocation)
      {
        System.out.println(String.format("%s moved to [%f, %f]", new Object[] { paramAnonymousString, Double.valueOf(paramAnonymousGeoLocation.latitude), Double.valueOf(paramAnonymousGeoLocation.longitude) }));
      }
    });
    Thread.sleep(60000L);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\firebase\geofire\example\Example.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */