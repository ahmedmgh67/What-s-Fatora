package com.firebase.geofire;

import com.firebase.geofire.core.GeoHash;
import com.firebase.geofire.core.GeoHashQuery;
import com.firebase.geofire.util.GeoUtils;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class GeoQuery
{
  private GeoLocation center;
  private final ChildEventListener childEventLister = new ChildEventListener()
  {
    public void onCancelled(DatabaseError paramAnonymousDatabaseError) {}
    
    public void onChildAdded(DataSnapshot paramAnonymousDataSnapshot, String arg2)
    {
      synchronized (GeoQuery.this)
      {
        GeoQuery.this.childAdded(paramAnonymousDataSnapshot);
        return;
      }
    }
    
    public void onChildChanged(DataSnapshot paramAnonymousDataSnapshot, String arg2)
    {
      synchronized (GeoQuery.this)
      {
        GeoQuery.this.childChanged(paramAnonymousDataSnapshot);
        return;
      }
    }
    
    public void onChildMoved(DataSnapshot paramAnonymousDataSnapshot, String paramAnonymousString) {}
    
    public void onChildRemoved(DataSnapshot paramAnonymousDataSnapshot)
    {
      synchronized (GeoQuery.this)
      {
        GeoQuery.this.childRemoved(paramAnonymousDataSnapshot);
        return;
      }
    }
  };
  private final Set<GeoQueryEventListener> eventListeners = new HashSet();
  private final Map<GeoHashQuery, Query> firebaseQueries = new HashMap();
  private final GeoFire geoFire;
  private final Map<String, LocationInfo> locationInfos = new HashMap();
  private final Set<GeoHashQuery> outstandingQueries = new HashSet();
  private Set<GeoHashQuery> queries;
  private double radius;
  
  GeoQuery(GeoFire paramGeoFire, GeoLocation paramGeoLocation, double paramDouble)
  {
    this.geoFire = paramGeoFire;
    this.center = paramGeoLocation;
    this.radius = (1000.0D * paramDouble);
  }
  
  private void addValueToReadyListener(Query paramQuery, final GeoHashQuery paramGeoHashQuery)
  {
    paramQuery.addListenerForSingleValueEvent(new ValueEventListener()
    {
      public void onCancelled(final DatabaseError paramAnonymousDatabaseError)
      {
        synchronized (GeoQuery.this)
        {
          Iterator localIterator = GeoQuery.this.eventListeners.iterator();
          if (localIterator.hasNext())
          {
            final GeoQueryEventListener localGeoQueryEventListener = (GeoQueryEventListener)localIterator.next();
            GeoQuery.this.geoFire.raiseEvent(new Runnable()
            {
              public void run()
              {
                localGeoQueryEventListener.onGeoQueryError(paramAnonymousDatabaseError);
              }
            });
          }
        }
      }
      
      public void onDataChange(DataSnapshot arg1)
      {
        synchronized (GeoQuery.this)
        {
          GeoQuery.this.outstandingQueries.remove(paramGeoHashQuery);
          GeoQuery.this.checkAndFireReady();
          return;
        }
      }
    });
  }
  
  private boolean canFireReady()
  {
    return this.outstandingQueries.isEmpty();
  }
  
  private void checkAndFireReady()
  {
    if (canFireReady())
    {
      Iterator localIterator = this.eventListeners.iterator();
      while (localIterator.hasNext())
      {
        final GeoQueryEventListener localGeoQueryEventListener = (GeoQueryEventListener)localIterator.next();
        this.geoFire.raiseEvent(new Runnable()
        {
          public void run()
          {
            localGeoQueryEventListener.onGeoQueryReady();
          }
        });
      }
    }
  }
  
  private void childAdded(DataSnapshot paramDataSnapshot)
  {
    GeoLocation localGeoLocation = GeoFire.getLocationValue(paramDataSnapshot);
    if (localGeoLocation != null) {
      updateLocationInfo(paramDataSnapshot.getKey(), localGeoLocation);
    }
  }
  
  private void childChanged(DataSnapshot paramDataSnapshot)
  {
    GeoLocation localGeoLocation = GeoFire.getLocationValue(paramDataSnapshot);
    if (localGeoLocation != null) {
      updateLocationInfo(paramDataSnapshot.getKey(), localGeoLocation);
    }
  }
  
  private void childRemoved(final DataSnapshot paramDataSnapshot)
  {
    paramDataSnapshot = paramDataSnapshot.getKey();
    if ((LocationInfo)this.locationInfos.get(paramDataSnapshot) != null) {
      this.geoFire.getDatabaseRefForKey(paramDataSnapshot).addListenerForSingleValueEvent(new ValueEventListener()
      {
        public void onCancelled(DatabaseError paramAnonymousDatabaseError) {}
        
        public void onDataChange(DataSnapshot paramAnonymousDataSnapshot)
        {
          for (;;)
          {
            synchronized (GeoQuery.this)
            {
              paramAnonymousDataSnapshot = GeoFire.getLocationValue(paramAnonymousDataSnapshot);
              if (paramAnonymousDataSnapshot != null)
              {
                paramAnonymousDataSnapshot = new GeoHash(paramAnonymousDataSnapshot);
                if ((paramAnonymousDataSnapshot != null) && (GeoQuery.this.geoHashQueriesContainGeoHash(paramAnonymousDataSnapshot))) {
                  break;
                }
                paramAnonymousDataSnapshot = (GeoQuery.LocationInfo)GeoQuery.this.locationInfos.get(paramDataSnapshot);
                GeoQuery.this.locationInfos.remove(paramDataSnapshot);
                if ((paramAnonymousDataSnapshot == null) || (!paramAnonymousDataSnapshot.inGeoQuery)) {
                  break;
                }
                paramAnonymousDataSnapshot = GeoQuery.this.eventListeners.iterator();
                if (!paramAnonymousDataSnapshot.hasNext()) {
                  break;
                }
                final GeoQueryEventListener localGeoQueryEventListener = (GeoQueryEventListener)paramAnonymousDataSnapshot.next();
                GeoQuery.this.geoFire.raiseEvent(new Runnable()
                {
                  public void run()
                  {
                    localGeoQueryEventListener.onKeyExited(GeoQuery.7.this.val$key);
                  }
                });
              }
            }
            paramAnonymousDataSnapshot = null;
          }
        }
      });
    }
  }
  
  private boolean geoHashQueriesContainGeoHash(GeoHash paramGeoHash)
  {
    if (this.queries == null) {}
    Iterator localIterator;
    do
    {
      while (!localIterator.hasNext())
      {
        return false;
        localIterator = this.queries.iterator();
      }
    } while (!((GeoHashQuery)localIterator.next()).containsGeoHash(paramGeoHash));
    return true;
  }
  
  private boolean hasListeners()
  {
    return !this.eventListeners.isEmpty();
  }
  
  private boolean locationIsInQuery(GeoLocation paramGeoLocation)
  {
    return GeoUtils.distance(paramGeoLocation, this.center) <= this.radius;
  }
  
  private void reset()
  {
    Iterator localIterator = this.firebaseQueries.entrySet().iterator();
    while (localIterator.hasNext()) {
      ((Query)((Map.Entry)localIterator.next()).getValue()).removeEventListener(this.childEventLister);
    }
    this.outstandingQueries.clear();
    this.firebaseQueries.clear();
    this.queries = null;
    this.locationInfos.clear();
  }
  
  private void setupQueries()
  {
    if (this.queries == null) {}
    Object localObject3;
    Object localObject4;
    for (Object localObject1 = new HashSet();; localObject1 = this.queries)
    {
      localObject2 = GeoHashQuery.queriesAtLocation(this.center, this.radius);
      this.queries = ((Set)localObject2);
      localObject3 = ((Set)localObject1).iterator();
      while (((Iterator)localObject3).hasNext())
      {
        localObject4 = (GeoHashQuery)((Iterator)localObject3).next();
        if (!((Set)localObject2).contains(localObject4))
        {
          ((Query)this.firebaseQueries.get(localObject4)).removeEventListener(this.childEventLister);
          this.firebaseQueries.remove(localObject4);
          this.outstandingQueries.remove(localObject4);
        }
      }
    }
    Object localObject2 = ((Set)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (GeoHashQuery)((Iterator)localObject2).next();
      if (!((Set)localObject1).contains(localObject3))
      {
        this.outstandingQueries.add(localObject3);
        localObject4 = this.geoFire.getDatabaseReference().orderByChild("g").startAt(((GeoHashQuery)localObject3).getStartValue()).endAt(((GeoHashQuery)localObject3).getEndValue());
        ((Query)localObject4).addChildEventListener(this.childEventLister);
        addValueToReadyListener((Query)localObject4, (GeoHashQuery)localObject3);
        this.firebaseQueries.put(localObject3, localObject4);
      }
    }
    localObject1 = this.locationInfos.entrySet().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (Map.Entry)((Iterator)localObject1).next();
      localObject3 = (LocationInfo)((Map.Entry)localObject2).getValue();
      updateLocationInfo((String)((Map.Entry)localObject2).getKey(), ((LocationInfo)localObject3).location);
    }
    localObject1 = this.locationInfos.entrySet().iterator();
    while (((Iterator)localObject1).hasNext()) {
      if (!geoHashQueriesContainGeoHash(((LocationInfo)((Map.Entry)((Iterator)localObject1).next()).getValue()).geoHash)) {
        ((Iterator)localObject1).remove();
      }
    }
    checkAndFireReady();
  }
  
  private void updateLocationInfo(final String paramString, final GeoLocation paramGeoLocation)
  {
    int k = 1;
    Object localObject = (LocationInfo)this.locationInfos.get(paramString);
    int i;
    int j;
    if (localObject == null)
    {
      i = 1;
      if ((localObject == null) || (((LocationInfo)localObject).location.equals(paramGeoLocation))) {
        break label139;
      }
      j = 1;
      label45:
      if ((localObject == null) || (!((LocationInfo)localObject).inGeoQuery)) {
        break label145;
      }
    }
    boolean bool;
    final GeoQueryEventListener localGeoQueryEventListener;
    for (;;)
    {
      bool = locationIsInQuery(paramGeoLocation);
      if (((i == 0) && (k != 0)) || (!bool)) {
        break label151;
      }
      localObject = this.eventListeners.iterator();
      while (((Iterator)localObject).hasNext())
      {
        localGeoQueryEventListener = (GeoQueryEventListener)((Iterator)localObject).next();
        this.geoFire.raiseEvent(new Runnable()
        {
          public void run()
          {
            localGeoQueryEventListener.onKeyEntered(paramString, paramGeoLocation);
          }
        });
      }
      i = 0;
      break;
      label139:
      j = 0;
      break label45;
      label145:
      k = 0;
    }
    label151:
    if ((i == 0) && (j != 0) && (bool)) {
      localObject = this.eventListeners.iterator();
    }
    while (((Iterator)localObject).hasNext())
    {
      localGeoQueryEventListener = (GeoQueryEventListener)((Iterator)localObject).next();
      this.geoFire.raiseEvent(new Runnable()
      {
        public void run()
        {
          localGeoQueryEventListener.onKeyMoved(paramString, paramGeoLocation);
        }
      });
      continue;
      if ((k != 0) && (!bool))
      {
        localObject = this.eventListeners.iterator();
        while (((Iterator)localObject).hasNext())
        {
          localGeoQueryEventListener = (GeoQueryEventListener)((Iterator)localObject).next();
          this.geoFire.raiseEvent(new Runnable()
          {
            public void run()
            {
              localGeoQueryEventListener.onKeyExited(paramString);
            }
          });
        }
      }
    }
    paramGeoLocation = new LocationInfo(paramGeoLocation, locationIsInQuery(paramGeoLocation));
    this.locationInfos.put(paramString, paramGeoLocation);
  }
  
  public void addGeoQueryEventListener(final GeoQueryEventListener paramGeoQueryEventListener)
  {
    try
    {
      if (this.eventListeners.contains(paramGeoQueryEventListener)) {
        throw new IllegalArgumentException("Added the same listener twice to a GeoQuery!");
      }
    }
    finally {}
    this.eventListeners.add(paramGeoQueryEventListener);
    if (this.queries == null) {
      setupQueries();
    }
    for (;;)
    {
      return;
      Iterator localIterator = this.locationInfos.entrySet().iterator();
      while (localIterator.hasNext())
      {
        final Object localObject = (Map.Entry)localIterator.next();
        final String str = (String)((Map.Entry)localObject).getKey();
        localObject = (LocationInfo)((Map.Entry)localObject).getValue();
        if (((LocationInfo)localObject).inGeoQuery) {
          this.geoFire.raiseEvent(new Runnable()
          {
            public void run()
            {
              paramGeoQueryEventListener.onKeyEntered(str, localObject.location);
            }
          });
        }
      }
      if (canFireReady()) {
        this.geoFire.raiseEvent(new Runnable()
        {
          public void run()
          {
            paramGeoQueryEventListener.onGeoQueryReady();
          }
        });
      }
    }
  }
  
  public GeoLocation getCenter()
  {
    try
    {
      GeoLocation localGeoLocation = this.center;
      return localGeoLocation;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public double getRadius()
  {
    try
    {
      double d = this.radius;
      d /= 1000.0D;
      return d;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void removeAllListeners()
  {
    try
    {
      this.eventListeners.clear();
      reset();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void removeGeoQueryEventListener(GeoQueryEventListener paramGeoQueryEventListener)
  {
    try
    {
      if (!this.eventListeners.contains(paramGeoQueryEventListener)) {
        throw new IllegalArgumentException("Trying to remove listener that was removed or not added!");
      }
    }
    finally {}
    this.eventListeners.remove(paramGeoQueryEventListener);
    if (!hasListeners()) {
      reset();
    }
  }
  
  public void setCenter(GeoLocation paramGeoLocation)
  {
    try
    {
      this.center = paramGeoLocation;
      if (hasListeners()) {
        setupQueries();
      }
      return;
    }
    finally
    {
      paramGeoLocation = finally;
      throw paramGeoLocation;
    }
  }
  
  public void setLocation(GeoLocation paramGeoLocation, double paramDouble)
  {
    try
    {
      this.center = paramGeoLocation;
      this.radius = (1000.0D * paramDouble);
      if (hasListeners()) {
        setupQueries();
      }
      return;
    }
    finally
    {
      paramGeoLocation = finally;
      throw paramGeoLocation;
    }
  }
  
  public void setRadius(double paramDouble)
  {
    try
    {
      this.radius = (1000.0D * paramDouble);
      if (hasListeners()) {
        setupQueries();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private static class LocationInfo
  {
    final GeoHash geoHash;
    final boolean inGeoQuery;
    final GeoLocation location;
    
    public LocationInfo(GeoLocation paramGeoLocation, boolean paramBoolean)
    {
      this.location = paramGeoLocation;
      this.inGeoQuery = paramBoolean;
      this.geoHash = new GeoHash(paramGeoLocation);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\firebase\geofire\GeoQuery.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */