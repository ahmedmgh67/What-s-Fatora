package com.google.maps.android.clustering.algo;

import android.support.v4.util.LongSparseArray;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.projection.SphericalMercatorProjection;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GridBasedAlgorithm<T extends ClusterItem>
  implements Algorithm<T>
{
  private static final int GRID_SIZE = 100;
  private final Set<T> mItems = Collections.synchronizedSet(new HashSet());
  
  private static long getCoord(long paramLong, double paramDouble1, double paramDouble2)
  {
    return (paramLong * Math.floor(paramDouble1) + Math.floor(paramDouble2));
  }
  
  public void addItem(T paramT)
  {
    this.mItems.add(paramT);
  }
  
  public void addItems(Collection<T> paramCollection)
  {
    this.mItems.addAll(paramCollection);
  }
  
  public void clearItems()
  {
    this.mItems.clear();
  }
  
  public Set<? extends Cluster<T>> getClusters(double paramDouble)
  {
    long l1 = Math.ceil(256.0D * Math.pow(2.0D, paramDouble) / 100.0D);
    SphericalMercatorProjection localSphericalMercatorProjection = new SphericalMercatorProjection(l1);
    HashSet localHashSet = new HashSet();
    LongSparseArray localLongSparseArray = new LongSparseArray();
    synchronized (this.mItems)
    {
      Iterator localIterator = this.mItems.iterator();
      if (localIterator.hasNext())
      {
        ClusterItem localClusterItem = (ClusterItem)localIterator.next();
        com.google.maps.android.projection.Point localPoint = localSphericalMercatorProjection.toPoint(localClusterItem.getPosition());
        long l2 = getCoord(l1, localPoint.x, localPoint.y);
        StaticCluster localStaticCluster2 = (StaticCluster)localLongSparseArray.get(l2);
        StaticCluster localStaticCluster1 = localStaticCluster2;
        if (localStaticCluster2 == null)
        {
          localStaticCluster1 = new StaticCluster(localSphericalMercatorProjection.toLatLng(new com.google.maps.android.geometry.Point(Math.floor(localPoint.x) + 0.5D, Math.floor(localPoint.y) + 0.5D)));
          localLongSparseArray.put(l2, localStaticCluster1);
          localHashSet.add(localStaticCluster1);
        }
        localStaticCluster1.add(localClusterItem);
      }
    }
    return localHashSet;
  }
  
  public Collection<T> getItems()
  {
    return this.mItems;
  }
  
  public void removeItem(T paramT)
  {
    this.mItems.remove(paramT);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\maps\android\clustering\algo\GridBasedAlgorithm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */