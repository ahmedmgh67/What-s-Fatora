package com.google.maps.android.quadtree;

import com.google.maps.android.geometry.Bounds;
import com.google.maps.android.geometry.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class PointQuadTree<T extends Item>
{
  private static final int MAX_DEPTH = 40;
  private static final int MAX_ELEMENTS = 50;
  private final Bounds mBounds;
  private List<PointQuadTree<T>> mChildren = null;
  private final int mDepth;
  private List<T> mItems;
  
  public PointQuadTree(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    this(new Bounds(paramDouble1, paramDouble2, paramDouble3, paramDouble4));
  }
  
  private PointQuadTree(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, int paramInt)
  {
    this(new Bounds(paramDouble1, paramDouble2, paramDouble3, paramDouble4), paramInt);
  }
  
  public PointQuadTree(Bounds paramBounds)
  {
    this(paramBounds, 0);
  }
  
  private PointQuadTree(Bounds paramBounds, int paramInt)
  {
    this.mBounds = paramBounds;
    this.mDepth = paramInt;
  }
  
  private void insert(double paramDouble1, double paramDouble2, T paramT)
  {
    if (this.mChildren != null) {
      if (paramDouble2 < this.mBounds.midY) {
        if (paramDouble1 < this.mBounds.midX) {
          ((PointQuadTree)this.mChildren.get(0)).insert(paramDouble1, paramDouble2, paramT);
        }
      }
    }
    do
    {
      return;
      ((PointQuadTree)this.mChildren.get(1)).insert(paramDouble1, paramDouble2, paramT);
      return;
      if (paramDouble1 < this.mBounds.midX)
      {
        ((PointQuadTree)this.mChildren.get(2)).insert(paramDouble1, paramDouble2, paramT);
        return;
      }
      ((PointQuadTree)this.mChildren.get(3)).insert(paramDouble1, paramDouble2, paramT);
      return;
      if (this.mItems == null) {
        this.mItems = new ArrayList();
      }
      this.mItems.add(paramT);
    } while ((this.mItems.size() <= 50) || (this.mDepth >= 40));
    split();
  }
  
  private boolean remove(double paramDouble1, double paramDouble2, T paramT)
  {
    if (this.mChildren != null)
    {
      if (paramDouble2 < this.mBounds.midY)
      {
        if (paramDouble1 < this.mBounds.midX) {
          return ((PointQuadTree)this.mChildren.get(0)).remove(paramDouble1, paramDouble2, paramT);
        }
        return ((PointQuadTree)this.mChildren.get(1)).remove(paramDouble1, paramDouble2, paramT);
      }
      if (paramDouble1 < this.mBounds.midX) {
        return ((PointQuadTree)this.mChildren.get(2)).remove(paramDouble1, paramDouble2, paramT);
      }
      return ((PointQuadTree)this.mChildren.get(3)).remove(paramDouble1, paramDouble2, paramT);
    }
    return this.mItems.remove(paramT);
  }
  
  private void search(Bounds paramBounds, Collection<T> paramCollection)
  {
    if (!this.mBounds.intersects(paramBounds)) {}
    for (;;)
    {
      return;
      Iterator localIterator;
      if (this.mChildren != null)
      {
        localIterator = this.mChildren.iterator();
        while (localIterator.hasNext()) {
          ((PointQuadTree)localIterator.next()).search(paramBounds, paramCollection);
        }
      }
      else if (this.mItems != null)
      {
        if (paramBounds.contains(this.mBounds))
        {
          paramCollection.addAll(this.mItems);
          return;
        }
        localIterator = this.mItems.iterator();
        while (localIterator.hasNext())
        {
          Item localItem = (Item)localIterator.next();
          if (paramBounds.contains(localItem.getPoint())) {
            paramCollection.add(localItem);
          }
        }
      }
    }
  }
  
  private void split()
  {
    this.mChildren = new ArrayList(4);
    this.mChildren.add(new PointQuadTree(this.mBounds.minX, this.mBounds.midX, this.mBounds.minY, this.mBounds.midY, this.mDepth + 1));
    this.mChildren.add(new PointQuadTree(this.mBounds.midX, this.mBounds.maxX, this.mBounds.minY, this.mBounds.midY, this.mDepth + 1));
    this.mChildren.add(new PointQuadTree(this.mBounds.minX, this.mBounds.midX, this.mBounds.midY, this.mBounds.maxY, this.mDepth + 1));
    this.mChildren.add(new PointQuadTree(this.mBounds.midX, this.mBounds.maxX, this.mBounds.midY, this.mBounds.maxY, this.mDepth + 1));
    Object localObject = this.mItems;
    this.mItems = null;
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      Item localItem = (Item)((Iterator)localObject).next();
      insert(localItem.getPoint().x, localItem.getPoint().y, localItem);
    }
  }
  
  public void add(T paramT)
  {
    Point localPoint = paramT.getPoint();
    if (this.mBounds.contains(localPoint.x, localPoint.y)) {
      insert(localPoint.x, localPoint.y, paramT);
    }
  }
  
  public void clear()
  {
    this.mChildren = null;
    if (this.mItems != null) {
      this.mItems.clear();
    }
  }
  
  public boolean remove(T paramT)
  {
    Point localPoint = paramT.getPoint();
    if (this.mBounds.contains(localPoint.x, localPoint.y)) {
      return remove(localPoint.x, localPoint.y, paramT);
    }
    return false;
  }
  
  public Collection<T> search(Bounds paramBounds)
  {
    ArrayList localArrayList = new ArrayList();
    search(paramBounds, localArrayList);
    return localArrayList;
  }
  
  public static abstract interface Item
  {
    public abstract Point getPoint();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\maps\android\quadtree\PointQuadTree.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */