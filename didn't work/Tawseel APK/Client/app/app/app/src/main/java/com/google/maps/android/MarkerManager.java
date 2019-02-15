package com.google.maps.android;

import android.view.View;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MarkerManager
  implements GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener, GoogleMap.InfoWindowAdapter
{
  private final Map<Marker, Collection> mAllMarkers = new HashMap();
  private final GoogleMap mMap;
  private final Map<String, Collection> mNamedCollections = new HashMap();
  
  public MarkerManager(GoogleMap paramGoogleMap)
  {
    this.mMap = paramGoogleMap;
  }
  
  public Collection getCollection(String paramString)
  {
    return (Collection)this.mNamedCollections.get(paramString);
  }
  
  public View getInfoContents(Marker paramMarker)
  {
    Collection localCollection = (Collection)this.mAllMarkers.get(paramMarker);
    if ((localCollection != null) && (localCollection.mInfoWindowAdapter != null)) {
      return localCollection.mInfoWindowAdapter.getInfoContents(paramMarker);
    }
    return null;
  }
  
  public View getInfoWindow(Marker paramMarker)
  {
    Collection localCollection = (Collection)this.mAllMarkers.get(paramMarker);
    if ((localCollection != null) && (localCollection.mInfoWindowAdapter != null)) {
      return localCollection.mInfoWindowAdapter.getInfoWindow(paramMarker);
    }
    return null;
  }
  
  public Collection newCollection()
  {
    return new Collection();
  }
  
  public Collection newCollection(String paramString)
  {
    if (this.mNamedCollections.get(paramString) != null) {
      throw new IllegalArgumentException("collection id is not unique: " + paramString);
    }
    Collection localCollection = new Collection();
    this.mNamedCollections.put(paramString, localCollection);
    return localCollection;
  }
  
  public void onInfoWindowClick(Marker paramMarker)
  {
    Collection localCollection = (Collection)this.mAllMarkers.get(paramMarker);
    if ((localCollection != null) && (localCollection.mInfoWindowClickListener != null)) {
      localCollection.mInfoWindowClickListener.onInfoWindowClick(paramMarker);
    }
  }
  
  public boolean onMarkerClick(Marker paramMarker)
  {
    Collection localCollection = (Collection)this.mAllMarkers.get(paramMarker);
    if ((localCollection != null) && (localCollection.mMarkerClickListener != null)) {
      return localCollection.mMarkerClickListener.onMarkerClick(paramMarker);
    }
    return false;
  }
  
  public void onMarkerDrag(Marker paramMarker)
  {
    Collection localCollection = (Collection)this.mAllMarkers.get(paramMarker);
    if ((localCollection != null) && (localCollection.mMarkerDragListener != null)) {
      localCollection.mMarkerDragListener.onMarkerDrag(paramMarker);
    }
  }
  
  public void onMarkerDragEnd(Marker paramMarker)
  {
    Collection localCollection = (Collection)this.mAllMarkers.get(paramMarker);
    if ((localCollection != null) && (localCollection.mMarkerDragListener != null)) {
      localCollection.mMarkerDragListener.onMarkerDragEnd(paramMarker);
    }
  }
  
  public void onMarkerDragStart(Marker paramMarker)
  {
    Collection localCollection = (Collection)this.mAllMarkers.get(paramMarker);
    if ((localCollection != null) && (localCollection.mMarkerDragListener != null)) {
      localCollection.mMarkerDragListener.onMarkerDragStart(paramMarker);
    }
  }
  
  public boolean remove(Marker paramMarker)
  {
    Collection localCollection = (Collection)this.mAllMarkers.get(paramMarker);
    return (localCollection != null) && (localCollection.remove(paramMarker));
  }
  
  public class Collection
  {
    private GoogleMap.InfoWindowAdapter mInfoWindowAdapter;
    private GoogleMap.OnInfoWindowClickListener mInfoWindowClickListener;
    private GoogleMap.OnMarkerClickListener mMarkerClickListener;
    private GoogleMap.OnMarkerDragListener mMarkerDragListener;
    private final Set<Marker> mMarkers = new HashSet();
    
    public Collection() {}
    
    public Marker addMarker(MarkerOptions paramMarkerOptions)
    {
      paramMarkerOptions = MarkerManager.this.mMap.addMarker(paramMarkerOptions);
      this.mMarkers.add(paramMarkerOptions);
      MarkerManager.this.mAllMarkers.put(paramMarkerOptions, this);
      return paramMarkerOptions;
    }
    
    public void clear()
    {
      Iterator localIterator = this.mMarkers.iterator();
      while (localIterator.hasNext())
      {
        Marker localMarker = (Marker)localIterator.next();
        localMarker.remove();
        MarkerManager.this.mAllMarkers.remove(localMarker);
      }
      this.mMarkers.clear();
    }
    
    public Collection<Marker> getMarkers()
    {
      return Collections.unmodifiableCollection(this.mMarkers);
    }
    
    public boolean remove(Marker paramMarker)
    {
      if (this.mMarkers.remove(paramMarker))
      {
        MarkerManager.this.mAllMarkers.remove(paramMarker);
        paramMarker.remove();
        return true;
      }
      return false;
    }
    
    public void setOnInfoWindowAdapter(GoogleMap.InfoWindowAdapter paramInfoWindowAdapter)
    {
      this.mInfoWindowAdapter = paramInfoWindowAdapter;
    }
    
    public void setOnInfoWindowClickListener(GoogleMap.OnInfoWindowClickListener paramOnInfoWindowClickListener)
    {
      this.mInfoWindowClickListener = paramOnInfoWindowClickListener;
    }
    
    public void setOnMarkerClickListener(GoogleMap.OnMarkerClickListener paramOnMarkerClickListener)
    {
      this.mMarkerClickListener = paramOnMarkerClickListener;
    }
    
    public void setOnMarkerDragListener(GoogleMap.OnMarkerDragListener paramOnMarkerDragListener)
    {
      this.mMarkerDragListener = paramOnMarkerDragListener;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\maps\android\MarkerManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */