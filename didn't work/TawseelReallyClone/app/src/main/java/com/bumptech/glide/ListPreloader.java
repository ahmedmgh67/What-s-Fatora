package com.bumptech.glide;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BaseTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.util.Util;
import java.util.List;
import java.util.Queue;

public class ListPreloader<T>
  implements AbsListView.OnScrollListener
{
  private boolean isIncreasing = true;
  private int lastEnd;
  private int lastFirstVisible;
  private int lastStart;
  private final int maxPreload;
  private final PreloadSizeProvider<T> preloadDimensionProvider;
  private final PreloadModelProvider<T> preloadModelProvider;
  private final PreloadTargetQueue preloadTargetQueue;
  private int totalItemCount;
  
  @Deprecated
  public ListPreloader(int paramInt)
  {
    this.preloadModelProvider = new PreloadModelProvider()
    {
      public List<T> getPreloadItems(int paramAnonymousInt)
      {
        return ListPreloader.this.getItems(paramAnonymousInt, paramAnonymousInt + 1);
      }
      
      public GenericRequestBuilder getPreloadRequestBuilder(T paramAnonymousT)
      {
        return ListPreloader.this.getRequestBuilder(paramAnonymousT);
      }
    };
    this.preloadDimensionProvider = new PreloadSizeProvider()
    {
      public int[] getPreloadSize(T paramAnonymousT, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        return ListPreloader.this.getDimensions(paramAnonymousT);
      }
    };
    this.maxPreload = paramInt;
    this.preloadTargetQueue = new PreloadTargetQueue(paramInt + 1);
  }
  
  public ListPreloader(PreloadModelProvider<T> paramPreloadModelProvider, PreloadSizeProvider<T> paramPreloadSizeProvider, int paramInt)
  {
    this.preloadModelProvider = paramPreloadModelProvider;
    this.preloadDimensionProvider = paramPreloadSizeProvider;
    this.maxPreload = paramInt;
    this.preloadTargetQueue = new PreloadTargetQueue(paramInt + 1);
  }
  
  private void cancelAll()
  {
    int i = 0;
    while (i < this.maxPreload)
    {
      Glide.clear(this.preloadTargetQueue.next(0, 0));
      i += 1;
    }
  }
  
  private void preload(int paramInt1, int paramInt2)
  {
    int i;
    if (paramInt1 < paramInt2) {
      i = Math.max(this.lastEnd, paramInt1);
    }
    for (int j = paramInt2;; j = Math.min(this.lastStart, paramInt1))
    {
      j = Math.min(this.totalItemCount, j);
      i = Math.min(this.totalItemCount, Math.max(0, i));
      if (paramInt1 >= paramInt2) {
        break;
      }
      paramInt1 = i;
      while (paramInt1 < j)
      {
        preloadAdapterPosition(this.preloadModelProvider.getPreloadItems(paramInt1), paramInt1, true);
        paramInt1 += 1;
      }
      i = paramInt2;
    }
    paramInt1 = j - 1;
    while (paramInt1 >= i)
    {
      preloadAdapterPosition(this.preloadModelProvider.getPreloadItems(paramInt1), paramInt1, false);
      paramInt1 -= 1;
    }
    this.lastStart = i;
    this.lastEnd = j;
  }
  
  private void preload(int paramInt, boolean paramBoolean)
  {
    if (this.isIncreasing != paramBoolean)
    {
      this.isIncreasing = paramBoolean;
      cancelAll();
    }
    if (paramBoolean) {}
    for (int i = this.maxPreload;; i = -this.maxPreload)
    {
      preload(paramInt, i + paramInt);
      return;
    }
  }
  
  private void preloadAdapterPosition(List<T> paramList, int paramInt, boolean paramBoolean)
  {
    int j = paramList.size();
    if (paramBoolean)
    {
      i = 0;
      while (i < j)
      {
        preloadItem(paramList.get(i), paramInt, i);
        i += 1;
      }
    }
    int i = j - 1;
    while (i >= 0)
    {
      preloadItem(paramList.get(i), paramInt, i);
      i -= 1;
    }
  }
  
  private void preloadItem(T paramT, int paramInt1, int paramInt2)
  {
    int[] arrayOfInt = this.preloadDimensionProvider.getPreloadSize(paramT, paramInt1, paramInt2);
    if (arrayOfInt != null) {
      this.preloadModelProvider.getPreloadRequestBuilder(paramT).into(this.preloadTargetQueue.next(arrayOfInt[0], arrayOfInt[1]));
    }
  }
  
  @Deprecated
  protected int[] getDimensions(T paramT)
  {
    throw new IllegalStateException("You must either provide a PreloadDimensionProvider or override getDimensions()");
  }
  
  @Deprecated
  protected List<T> getItems(int paramInt1, int paramInt2)
  {
    throw new IllegalStateException("You must either provide a PreloadModelProvider or override getItems()");
  }
  
  @Deprecated
  protected GenericRequestBuilder getRequestBuilder(T paramT)
  {
    throw new IllegalStateException("You must either provide a PreloadModelProvider, or override getRequestBuilder()");
  }
  
  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
  {
    this.totalItemCount = paramInt3;
    if (paramInt1 > this.lastFirstVisible) {
      preload(paramInt1 + paramInt2, true);
    }
    for (;;)
    {
      this.lastFirstVisible = paramInt1;
      return;
      if (paramInt1 < this.lastFirstVisible) {
        preload(paramInt1, false);
      }
    }
  }
  
  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt) {}
  
  public static abstract interface PreloadModelProvider<U>
  {
    public abstract List<U> getPreloadItems(int paramInt);
    
    public abstract GenericRequestBuilder getPreloadRequestBuilder(U paramU);
  }
  
  public static abstract interface PreloadSizeProvider<T>
  {
    public abstract int[] getPreloadSize(T paramT, int paramInt1, int paramInt2);
  }
  
  private static class PreloadTarget
    extends BaseTarget<Object>
  {
    private int photoHeight;
    private int photoWidth;
    
    public void getSize(SizeReadyCallback paramSizeReadyCallback)
    {
      paramSizeReadyCallback.onSizeReady(this.photoWidth, this.photoHeight);
    }
    
    public void onResourceReady(Object paramObject, GlideAnimation<? super Object> paramGlideAnimation) {}
  }
  
  private static final class PreloadTargetQueue
  {
    private final Queue<ListPreloader.PreloadTarget> queue;
    
    public PreloadTargetQueue(int paramInt)
    {
      this.queue = Util.createQueue(paramInt);
      int i = 0;
      while (i < paramInt)
      {
        this.queue.offer(new ListPreloader.PreloadTarget(null));
        i += 1;
      }
    }
    
    public ListPreloader.PreloadTarget next(int paramInt1, int paramInt2)
    {
      ListPreloader.PreloadTarget localPreloadTarget = (ListPreloader.PreloadTarget)this.queue.poll();
      this.queue.offer(localPreloadTarget);
      ListPreloader.PreloadTarget.access$102(localPreloadTarget, paramInt1);
      ListPreloader.PreloadTarget.access$202(localPreloadTarget, paramInt2);
      return localPreloadTarget;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\ListPreloader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */