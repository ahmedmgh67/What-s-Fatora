package com.google.maps.android.clustering;

import com.google.android.gms.maps.model.LatLng;
import java.util.Collection;

public abstract interface Cluster<T extends ClusterItem>
{
  public abstract Collection<T> getItems();
  
  public abstract LatLng getPosition();
  
  public abstract int getSize();
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\maps\android\clustering\Cluster.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */