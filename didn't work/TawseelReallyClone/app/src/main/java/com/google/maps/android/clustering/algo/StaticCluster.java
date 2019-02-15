package com.google.maps.android.clustering.algo;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StaticCluster<T extends ClusterItem>
  implements Cluster<T>
{
  private final LatLng mCenter;
  private final List<T> mItems = new ArrayList();
  
  public StaticCluster(LatLng paramLatLng)
  {
    this.mCenter = paramLatLng;
  }
  
  public boolean add(T paramT)
  {
    return this.mItems.add(paramT);
  }
  
  public Collection<T> getItems()
  {
    return this.mItems;
  }
  
  public LatLng getPosition()
  {
    return this.mCenter;
  }
  
  public int getSize()
  {
    return this.mItems.size();
  }
  
  public boolean remove(T paramT)
  {
    return this.mItems.remove(paramT);
  }
  
  public String toString()
  {
    return "StaticCluster{mCenter=" + this.mCenter + ", mItems.size=" + this.mItems.size() + '}';
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\maps\android\clustering\algo\StaticCluster.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */