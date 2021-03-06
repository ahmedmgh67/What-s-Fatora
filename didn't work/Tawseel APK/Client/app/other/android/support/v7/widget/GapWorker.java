package android.support.v7.widget;

import android.support.annotation.Nullable;
import android.support.v4.os.TraceCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

final class GapWorker
  implements Runnable
{
  static final ThreadLocal<GapWorker> sGapWorker = new ThreadLocal();
  static Comparator<Task> sTaskComparator = new Comparator()
  {
    public int compare(GapWorker.Task paramAnonymousTask1, GapWorker.Task paramAnonymousTask2)
    {
      int k = -1;
      int j;
      if (paramAnonymousTask1.view == null)
      {
        i = 1;
        if (paramAnonymousTask2.view != null) {
          break label42;
        }
        j = 1;
      }
      for (;;)
      {
        if (i != j)
        {
          if (paramAnonymousTask1.view == null)
          {
            return 1;
            i = 0;
            break;
            label42:
            j = 0;
            continue;
          }
          return -1;
        }
      }
      if (paramAnonymousTask1.immediate != paramAnonymousTask2.immediate)
      {
        if (paramAnonymousTask1.immediate) {}
        for (i = k;; i = 1) {
          return i;
        }
      }
      int i = paramAnonymousTask2.viewVelocity - paramAnonymousTask1.viewVelocity;
      if (i != 0) {
        return i;
      }
      i = paramAnonymousTask1.distanceToItem - paramAnonymousTask2.distanceToItem;
      if (i != 0) {
        return i;
      }
      return 0;
    }
  };
  long mFrameIntervalNs;
  long mPostTimeNs;
  ArrayList<RecyclerView> mRecyclerViews = new ArrayList();
  private ArrayList<Task> mTasks = new ArrayList();
  
  private void buildTaskList()
  {
    int m = this.mRecyclerViews.size();
    int j = 0;
    int i = 0;
    Object localObject;
    while (i < m)
    {
      localObject = (RecyclerView)this.mRecyclerViews.get(i);
      ((RecyclerView)localObject).mPrefetchRegistry.collectPrefetchPositionsFromView((RecyclerView)localObject, false);
      j += ((RecyclerView)localObject).mPrefetchRegistry.mCount;
      i += 1;
    }
    this.mTasks.ensureCapacity(j);
    j = 0;
    i = 0;
    while (i < m)
    {
      RecyclerView localRecyclerView = (RecyclerView)this.mRecyclerViews.get(i);
      LayoutPrefetchRegistryImpl localLayoutPrefetchRegistryImpl = localRecyclerView.mPrefetchRegistry;
      int n = Math.abs(localLayoutPrefetchRegistryImpl.mPrefetchDx) + Math.abs(localLayoutPrefetchRegistryImpl.mPrefetchDy);
      int k = 0;
      if (k < localLayoutPrefetchRegistryImpl.mCount * 2)
      {
        label161:
        int i1;
        if (j >= this.mTasks.size())
        {
          localObject = new Task();
          this.mTasks.add(localObject);
          i1 = localLayoutPrefetchRegistryImpl.mPrefetchArray[(k + 1)];
          if (i1 > n) {
            break label249;
          }
        }
        label249:
        for (boolean bool = true;; bool = false)
        {
          ((Task)localObject).immediate = bool;
          ((Task)localObject).viewVelocity = n;
          ((Task)localObject).distanceToItem = i1;
          ((Task)localObject).view = localRecyclerView;
          ((Task)localObject).position = localLayoutPrefetchRegistryImpl.mPrefetchArray[k];
          j += 1;
          k += 2;
          break;
          localObject = (Task)this.mTasks.get(j);
          break label161;
        }
      }
      i += 1;
    }
    Collections.sort(this.mTasks, sTaskComparator);
  }
  
  private void flushTaskWithDeadline(Task paramTask, long paramLong)
  {
    if (paramTask.immediate) {}
    for (long l = Long.MAX_VALUE;; l = paramLong)
    {
      paramTask = prefetchPositionWithDeadline(paramTask.view, paramTask.position, l);
      if ((paramTask != null) && (paramTask.mNestedRecyclerView != null)) {
        prefetchInnerRecyclerViewWithDeadline((RecyclerView)paramTask.mNestedRecyclerView.get(), paramLong);
      }
      return;
    }
  }
  
  private void flushTasksWithDeadline(long paramLong)
  {
    int i = 0;
    for (;;)
    {
      Task localTask;
      if (i < this.mTasks.size())
      {
        localTask = (Task)this.mTasks.get(i);
        if (localTask.view != null) {}
      }
      else
      {
        return;
      }
      flushTaskWithDeadline(localTask, paramLong);
      localTask.clear();
      i += 1;
    }
  }
  
  static boolean isPrefetchPositionAttached(RecyclerView paramRecyclerView, int paramInt)
  {
    int j = paramRecyclerView.mChildHelper.getUnfilteredChildCount();
    int i = 0;
    while (i < j)
    {
      RecyclerView.ViewHolder localViewHolder = RecyclerView.getChildViewHolderInt(paramRecyclerView.mChildHelper.getUnfilteredChildAt(i));
      if ((localViewHolder.mPosition == paramInt) && (!localViewHolder.isInvalid())) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  private void prefetchInnerRecyclerViewWithDeadline(@Nullable RecyclerView paramRecyclerView, long paramLong)
  {
    if (paramRecyclerView == null) {}
    LayoutPrefetchRegistryImpl localLayoutPrefetchRegistryImpl;
    do
    {
      return;
      if ((paramRecyclerView.mDataSetHasChangedAfterLayout) && (paramRecyclerView.mChildHelper.getUnfilteredChildCount() != 0)) {
        paramRecyclerView.removeAndRecycleViews();
      }
      localLayoutPrefetchRegistryImpl = paramRecyclerView.mPrefetchRegistry;
      localLayoutPrefetchRegistryImpl.collectPrefetchPositionsFromView(paramRecyclerView, true);
    } while (localLayoutPrefetchRegistryImpl.mCount == 0);
    try
    {
      TraceCompat.beginSection("RV Nested Prefetch");
      paramRecyclerView.mState.prepareForNestedPrefetch(paramRecyclerView.mAdapter);
      int i = 0;
      while (i < localLayoutPrefetchRegistryImpl.mCount * 2)
      {
        prefetchPositionWithDeadline(paramRecyclerView, localLayoutPrefetchRegistryImpl.mPrefetchArray[i], paramLong);
        i += 2;
      }
      return;
    }
    finally
    {
      TraceCompat.endSection();
    }
  }
  
  private RecyclerView.ViewHolder prefetchPositionWithDeadline(RecyclerView paramRecyclerView, int paramInt, long paramLong)
  {
    if (isPrefetchPositionAttached(paramRecyclerView, paramInt)) {
      paramRecyclerView = null;
    }
    RecyclerView.Recycler localRecycler;
    RecyclerView.ViewHolder localViewHolder;
    do
    {
      return paramRecyclerView;
      localRecycler = paramRecyclerView.mRecycler;
      localViewHolder = localRecycler.tryGetViewHolderForPositionByDeadline(paramInt, false, paramLong);
      paramRecyclerView = localViewHolder;
    } while (localViewHolder == null);
    if (localViewHolder.isBound())
    {
      localRecycler.recycleView(localViewHolder.itemView);
      return localViewHolder;
    }
    localRecycler.addViewHolderToRecycledViewPool(localViewHolder, false);
    return localViewHolder;
  }
  
  public void add(RecyclerView paramRecyclerView)
  {
    this.mRecyclerViews.add(paramRecyclerView);
  }
  
  void postFromTraversal(RecyclerView paramRecyclerView, int paramInt1, int paramInt2)
  {
    if ((paramRecyclerView.isAttachedToWindow()) && (this.mPostTimeNs == 0L))
    {
      this.mPostTimeNs = paramRecyclerView.getNanoTime();
      paramRecyclerView.post(this);
    }
    paramRecyclerView.mPrefetchRegistry.setPrefetchVector(paramInt1, paramInt2);
  }
  
  void prefetch(long paramLong)
  {
    buildTaskList();
    flushTasksWithDeadline(paramLong);
  }
  
  public void remove(RecyclerView paramRecyclerView)
  {
    this.mRecyclerViews.remove(paramRecyclerView);
  }
  
  public void run()
  {
    try
    {
      TraceCompat.beginSection("RV Prefetch");
      boolean bool = this.mRecyclerViews.isEmpty();
      if (bool) {
        return;
      }
      long l = TimeUnit.MILLISECONDS.toNanos(((RecyclerView)this.mRecyclerViews.get(0)).getDrawingTime());
      if (l == 0L) {
        return;
      }
      prefetch(l + this.mFrameIntervalNs);
      return;
    }
    finally
    {
      this.mPostTimeNs = 0L;
      TraceCompat.endSection();
    }
  }
  
  static class LayoutPrefetchRegistryImpl
    implements RecyclerView.LayoutManager.LayoutPrefetchRegistry
  {
    int mCount;
    int[] mPrefetchArray;
    int mPrefetchDx;
    int mPrefetchDy;
    
    public void addPosition(int paramInt1, int paramInt2)
    {
      if (paramInt2 < 0) {
        throw new IllegalArgumentException("Pixel distance must be non-negative");
      }
      int i = this.mCount * 2;
      if (this.mPrefetchArray == null)
      {
        this.mPrefetchArray = new int[4];
        Arrays.fill(this.mPrefetchArray, -1);
      }
      for (;;)
      {
        this.mPrefetchArray[i] = paramInt1;
        this.mPrefetchArray[(i + 1)] = paramInt2;
        this.mCount += 1;
        return;
        if (i >= this.mPrefetchArray.length)
        {
          int[] arrayOfInt = this.mPrefetchArray;
          this.mPrefetchArray = new int[i * 2];
          System.arraycopy(arrayOfInt, 0, this.mPrefetchArray, 0, arrayOfInt.length);
        }
      }
    }
    
    void clearPrefetchPositions()
    {
      if (this.mPrefetchArray != null) {
        Arrays.fill(this.mPrefetchArray, -1);
      }
    }
    
    void collectPrefetchPositionsFromView(RecyclerView paramRecyclerView, boolean paramBoolean)
    {
      this.mCount = 0;
      if (this.mPrefetchArray != null) {
        Arrays.fill(this.mPrefetchArray, -1);
      }
      RecyclerView.LayoutManager localLayoutManager = paramRecyclerView.mLayout;
      if ((paramRecyclerView.mAdapter != null) && (localLayoutManager != null) && (localLayoutManager.isItemPrefetchEnabled()))
      {
        if (!paramBoolean) {
          break label101;
        }
        if (!paramRecyclerView.mAdapterHelper.hasPendingUpdates()) {
          localLayoutManager.collectInitialPrefetchPositions(paramRecyclerView.mAdapter.getItemCount(), this);
        }
      }
      for (;;)
      {
        if (this.mCount > localLayoutManager.mPrefetchMaxCountObserved)
        {
          localLayoutManager.mPrefetchMaxCountObserved = this.mCount;
          localLayoutManager.mPrefetchMaxObservedInInitialPrefetch = paramBoolean;
          paramRecyclerView.mRecycler.updateViewCacheSize();
        }
        return;
        label101:
        if (!paramRecyclerView.hasPendingAdapterUpdates()) {
          localLayoutManager.collectAdjacentPrefetchPositions(this.mPrefetchDx, this.mPrefetchDy, paramRecyclerView.mState, this);
        }
      }
    }
    
    boolean lastPrefetchIncludedPosition(int paramInt)
    {
      if (this.mPrefetchArray != null)
      {
        int j = this.mCount;
        int i = 0;
        while (i < j * 2)
        {
          if (this.mPrefetchArray[i] == paramInt) {
            return true;
          }
          i += 2;
        }
      }
      return false;
    }
    
    void setPrefetchVector(int paramInt1, int paramInt2)
    {
      this.mPrefetchDx = paramInt1;
      this.mPrefetchDy = paramInt2;
    }
  }
  
  static class Task
  {
    public int distanceToItem;
    public boolean immediate;
    public int position;
    public RecyclerView view;
    public int viewVelocity;
    
    public void clear()
    {
      this.immediate = false;
      this.viewVelocity = 0;
      this.distanceToItem = 0;
      this.view = null;
      this.position = 0;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\android\support\v7\widget\GapWorker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */