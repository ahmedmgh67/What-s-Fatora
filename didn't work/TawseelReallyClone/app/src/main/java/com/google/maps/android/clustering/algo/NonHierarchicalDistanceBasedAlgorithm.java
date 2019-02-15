package com.google.maps.android.clustering.algo;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.geometry.Bounds;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.projection.SphericalMercatorProjection;
import com.google.maps.android.quadtree.PointQuadTree;
import com.google.maps.android.quadtree.PointQuadTree.Item;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NonHierarchicalDistanceBasedAlgorithm<T extends ClusterItem>
  implements Algorithm<T>
{
  public static final int MAX_DISTANCE_AT_ZOOM = 100;
  private static final SphericalMercatorProjection PROJECTION = new SphericalMercatorProjection(1.0D);
  private final Collection<QuadItem<T>> mItems = new ArrayList();
  private final PointQuadTree<QuadItem<T>> mQuadTree = new PointQuadTree(0.0D, 1.0D, 0.0D, 1.0D);
  
  private Bounds createBoundsFromSpan(Point paramPoint, double paramDouble)
  {
    paramDouble /= 2.0D;
    return new Bounds(paramPoint.x - paramDouble, paramPoint.x + paramDouble, paramPoint.y - paramDouble, paramPoint.y + paramDouble);
  }
  
  private double distanceSquared(Point paramPoint1, Point paramPoint2)
  {
    return (paramPoint1.x - paramPoint2.x) * (paramPoint1.x - paramPoint2.x) + (paramPoint1.y - paramPoint2.y) * (paramPoint1.y - paramPoint2.y);
  }
  
  public void addItem(T arg1)
  {
    QuadItem localQuadItem = new QuadItem(???, null);
    synchronized (this.mQuadTree)
    {
      this.mItems.add(localQuadItem);
      this.mQuadTree.add(localQuadItem);
      return;
    }
  }
  
  public void addItems(Collection<T> paramCollection)
  {
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext()) {
      addItem((ClusterItem)paramCollection.next());
    }
  }
  
  public void clearItems()
  {
    synchronized (this.mQuadTree)
    {
      this.mItems.clear();
      this.mQuadTree.clear();
      return;
    }
  }
  
  public Set<? extends Cluster<T>> getClusters(double paramDouble)
  {
    paramDouble = 100.0D / Math.pow(2.0D, (int)paramDouble) / 256.0D;
    HashSet localHashSet1 = new HashSet();
    HashSet localHashSet2 = new HashSet();
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    for (;;)
    {
      QuadItem localQuadItem1;
      Object localObject2;
      synchronized (this.mQuadTree)
      {
        Iterator localIterator1 = this.mItems.iterator();
        if (!localIterator1.hasNext()) {
          break;
        }
        localQuadItem1 = (QuadItem)localIterator1.next();
        if (localHashSet1.contains(localQuadItem1)) {
          continue;
        }
        localObject2 = createBoundsFromSpan(localQuadItem1.getPoint(), paramDouble);
        localObject2 = this.mQuadTree.search((Bounds)localObject2);
        if (((Collection)localObject2).size() == 1)
        {
          localHashSet2.add(localQuadItem1);
          localHashSet1.add(localQuadItem1);
          localHashMap1.put(localQuadItem1, Double.valueOf(0.0D));
        }
      }
      StaticCluster localStaticCluster = new StaticCluster(localQuadItem1.mClusterItem.getPosition());
      localHashSet2.add(localStaticCluster);
      Iterator localIterator2 = ((Collection)localObject2).iterator();
      while (localIterator2.hasNext())
      {
        QuadItem localQuadItem2 = (QuadItem)localIterator2.next();
        Double localDouble = (Double)localHashMap1.get(localQuadItem2);
        double d = distanceSquared(localQuadItem2.getPoint(), localQuadItem1.getPoint());
        if (localDouble != null)
        {
          if (localDouble.doubleValue() >= d) {
            ((StaticCluster)localHashMap2.get(localQuadItem2)).remove(localQuadItem2.mClusterItem);
          }
        }
        else
        {
          localHashMap1.put(localQuadItem2, Double.valueOf(d));
          localStaticCluster.add(localQuadItem2.mClusterItem);
          localHashMap2.put(localQuadItem2, localStaticCluster);
        }
      }
      ((Set)localObject1).addAll((Collection)localObject2);
    }
    return localHashSet2;
  }
  
  public Collection<T> getItems()
  {
    ArrayList localArrayList = new ArrayList();
    synchronized (this.mQuadTree)
    {
      Iterator localIterator = this.mItems.iterator();
      if (localIterator.hasNext()) {
        localArrayList.add(((QuadItem)localIterator.next()).mClusterItem);
      }
    }
    return localCollection;
  }
  
  public void removeItem(T paramT)
  {
    throw new UnsupportedOperationException("NonHierarchicalDistanceBasedAlgorithm.remove not implemented");
  }
  
  private static class QuadItem<T extends ClusterItem>
    implements PointQuadTree.Item, Cluster<T>
  {
    private final T mClusterItem;
    private final Point mPoint;
    private final LatLng mPosition;
    private Set<T> singletonSet;
    
    private QuadItem(T paramT)
    {
      this.mClusterItem = paramT;
      this.mPosition = paramT.getPosition();
      this.mPoint = NonHierarchicalDistanceBasedAlgorithm.PROJECTION.toPoint(this.mPosition);
      this.singletonSet = Collections.singleton(this.mClusterItem);
    }
    
    public Set<T> getItems()
    {
      return this.singletonSet;
    }
    
    public Point getPoint()
    {
      return this.mPoint;
    }
    
    public LatLng getPosition()
    {
      return this.mPosition;
    }
    
    public int getSize()
    {
      return 1;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\maps\android\clustering\algo\NonHierarchicalDistanceBasedAlgorithm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */