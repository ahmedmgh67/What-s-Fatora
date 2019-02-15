package com.google.maps.android.clustering.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.MessageQueue.IdleHandler;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.DecelerateInterpolator;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.VisibleRegion;
import com.google.maps.android.MarkerManager;
import com.google.maps.android.MarkerManager.Collection;
import com.google.maps.android.R.id;
import com.google.maps.android.R.style;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.ClusterManager.OnClusterClickListener;
import com.google.maps.android.clustering.ClusterManager.OnClusterInfoWindowClickListener;
import com.google.maps.android.clustering.ClusterManager.OnClusterItemClickListener;
import com.google.maps.android.clustering.ClusterManager.OnClusterItemInfoWindowClickListener;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.projection.SphericalMercatorProjection;
import com.google.maps.android.ui.IconGenerator;
import com.google.maps.android.ui.SquareTextView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DefaultClusterRenderer<T extends ClusterItem>
  implements ClusterRenderer<T>
{
  private static final TimeInterpolator ANIMATION_INTERP;
  private static final int[] BUCKETS;
  private static final int MIN_CLUSTER_SIZE = 4;
  private static final boolean SHOULD_ANIMATE;
  private ClusterManager.OnClusterClickListener<T> mClickListener;
  private final ClusterManager<T> mClusterManager;
  private Map<Cluster<T>, Marker> mClusterToMarker = new HashMap();
  private Set<? extends Cluster<T>> mClusters;
  private ShapeDrawable mColoredCircleBackground;
  private final float mDensity;
  private final IconGenerator mIconGenerator;
  private SparseArray<BitmapDescriptor> mIcons = new SparseArray();
  private ClusterManager.OnClusterInfoWindowClickListener<T> mInfoWindowClickListener;
  private ClusterManager.OnClusterItemClickListener<T> mItemClickListener;
  private ClusterManager.OnClusterItemInfoWindowClickListener<T> mItemInfoWindowClickListener;
  private final GoogleMap mMap;
  private MarkerCache<T> mMarkerCache = new MarkerCache(null);
  private Map<Marker, Cluster<T>> mMarkerToCluster = new HashMap();
  private Set<MarkerWithPosition> mMarkers = Collections.newSetFromMap(new ConcurrentHashMap());
  private final DefaultClusterRenderer<T>.ViewModifier mViewModifier = new ViewModifier(null);
  private float mZoom;
  
  static
  {
    if (Build.VERSION.SDK_INT >= 11) {}
    for (boolean bool = true;; bool = false)
    {
      SHOULD_ANIMATE = bool;
      BUCKETS = new int[] { 10, 20, 50, 100, 200, 500, 1000 };
      ANIMATION_INTERP = new DecelerateInterpolator();
      return;
    }
  }
  
  public DefaultClusterRenderer(Context paramContext, GoogleMap paramGoogleMap, ClusterManager<T> paramClusterManager)
  {
    this.mMap = paramGoogleMap;
    this.mDensity = paramContext.getResources().getDisplayMetrics().density;
    this.mIconGenerator = new IconGenerator(paramContext);
    this.mIconGenerator.setContentView(makeSquareTextView(paramContext));
    this.mIconGenerator.setTextAppearance(R.style.ClusterIcon_TextAppearance);
    this.mIconGenerator.setBackground(makeClusterBackground());
    this.mClusterManager = paramClusterManager;
  }
  
  private static double distanceSquared(Point paramPoint1, Point paramPoint2)
  {
    return (paramPoint1.x - paramPoint2.x) * (paramPoint1.x - paramPoint2.x) + (paramPoint1.y - paramPoint2.y) * (paramPoint1.y - paramPoint2.y);
  }
  
  private static Point findClosestCluster(List<Point> paramList, Point paramPoint)
  {
    if ((paramList == null) || (paramList.isEmpty()))
    {
      localObject = null;
      return (Point)localObject;
    }
    double d1 = 10000.0D;
    Object localObject = null;
    Iterator localIterator = paramList.iterator();
    paramList = (List<Point>)localObject;
    for (;;)
    {
      localObject = paramList;
      if (!localIterator.hasNext()) {
        break;
      }
      localObject = (Point)localIterator.next();
      double d2 = distanceSquared((Point)localObject, paramPoint);
      if (d2 < d1)
      {
        paramList = (List<Point>)localObject;
        d1 = d2;
      }
    }
  }
  
  private int getColor(int paramInt)
  {
    float f = Math.min(paramInt, 300.0F);
    return Color.HSVToColor(new float[] { (300.0F - f) * (300.0F - f) / 90000.0F * 220.0F, 1.0F, 0.6F });
  }
  
  private LayerDrawable makeClusterBackground()
  {
    this.mColoredCircleBackground = new ShapeDrawable(new OvalShape());
    Object localObject = new ShapeDrawable(new OvalShape());
    ((ShapeDrawable)localObject).getPaint().setColor(-2130706433);
    localObject = new LayerDrawable(new Drawable[] { localObject, this.mColoredCircleBackground });
    int i = (int)(this.mDensity * 3.0F);
    ((LayerDrawable)localObject).setLayerInset(1, i, i, i, i);
    return (LayerDrawable)localObject;
  }
  
  private SquareTextView makeSquareTextView(Context paramContext)
  {
    paramContext = new SquareTextView(paramContext);
    paramContext.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
    paramContext.setId(R.id.text);
    int i = (int)(12.0F * this.mDensity);
    paramContext.setPadding(i, i, i, i);
    return paramContext;
  }
  
  protected int getBucket(Cluster<T> paramCluster)
  {
    int j = paramCluster.getSize();
    if (j <= BUCKETS[0]) {
      return j;
    }
    int i = 0;
    while (i < BUCKETS.length - 1)
    {
      if (j < BUCKETS[(i + 1)]) {
        return BUCKETS[i];
      }
      i += 1;
    }
    return BUCKETS[(BUCKETS.length - 1)];
  }
  
  public Cluster<T> getCluster(Marker paramMarker)
  {
    return (Cluster)this.mMarkerToCluster.get(paramMarker);
  }
  
  public T getClusterItem(Marker paramMarker)
  {
    return (ClusterItem)this.mMarkerCache.get(paramMarker);
  }
  
  protected String getClusterText(int paramInt)
  {
    if (paramInt < BUCKETS[0]) {
      return String.valueOf(paramInt);
    }
    return String.valueOf(paramInt) + "+";
  }
  
  public Marker getMarker(Cluster<T> paramCluster)
  {
    return (Marker)this.mClusterToMarker.get(paramCluster);
  }
  
  public Marker getMarker(T paramT)
  {
    return this.mMarkerCache.get(paramT);
  }
  
  public void onAdd()
  {
    this.mClusterManager.getMarkerCollection().setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
    {
      public boolean onMarkerClick(Marker paramAnonymousMarker)
      {
        return (DefaultClusterRenderer.this.mItemClickListener != null) && (DefaultClusterRenderer.this.mItemClickListener.onClusterItemClick((ClusterItem)DefaultClusterRenderer.this.mMarkerCache.get(paramAnonymousMarker)));
      }
    });
    this.mClusterManager.getMarkerCollection().setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener()
    {
      public void onInfoWindowClick(Marker paramAnonymousMarker)
      {
        if (DefaultClusterRenderer.this.mItemInfoWindowClickListener != null) {
          DefaultClusterRenderer.this.mItemInfoWindowClickListener.onClusterItemInfoWindowClick((ClusterItem)DefaultClusterRenderer.this.mMarkerCache.get(paramAnonymousMarker));
        }
      }
    });
    this.mClusterManager.getClusterMarkerCollection().setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
    {
      public boolean onMarkerClick(Marker paramAnonymousMarker)
      {
        return (DefaultClusterRenderer.this.mClickListener != null) && (DefaultClusterRenderer.this.mClickListener.onClusterClick((Cluster)DefaultClusterRenderer.this.mMarkerToCluster.get(paramAnonymousMarker)));
      }
    });
    this.mClusterManager.getClusterMarkerCollection().setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener()
    {
      public void onInfoWindowClick(Marker paramAnonymousMarker)
      {
        if (DefaultClusterRenderer.this.mInfoWindowClickListener != null) {
          DefaultClusterRenderer.this.mInfoWindowClickListener.onClusterInfoWindowClick((Cluster)DefaultClusterRenderer.this.mMarkerToCluster.get(paramAnonymousMarker));
        }
      }
    });
  }
  
  protected void onBeforeClusterItemRendered(T paramT, MarkerOptions paramMarkerOptions) {}
  
  protected void onBeforeClusterRendered(Cluster<T> paramCluster, MarkerOptions paramMarkerOptions)
  {
    int i = getBucket(paramCluster);
    BitmapDescriptor localBitmapDescriptor = (BitmapDescriptor)this.mIcons.get(i);
    paramCluster = localBitmapDescriptor;
    if (localBitmapDescriptor == null)
    {
      this.mColoredCircleBackground.getPaint().setColor(getColor(i));
      paramCluster = BitmapDescriptorFactory.fromBitmap(this.mIconGenerator.makeIcon(getClusterText(i)));
      this.mIcons.put(i, paramCluster);
    }
    paramMarkerOptions.icon(paramCluster);
  }
  
  protected void onClusterItemRendered(T paramT, Marker paramMarker) {}
  
  protected void onClusterRendered(Cluster<T> paramCluster, Marker paramMarker) {}
  
  public void onClustersChanged(Set<? extends Cluster<T>> paramSet)
  {
    this.mViewModifier.queue(paramSet);
  }
  
  public void onRemove()
  {
    this.mClusterManager.getMarkerCollection().setOnMarkerClickListener(null);
    this.mClusterManager.getClusterMarkerCollection().setOnMarkerClickListener(null);
  }
  
  public void setOnClusterClickListener(ClusterManager.OnClusterClickListener<T> paramOnClusterClickListener)
  {
    this.mClickListener = paramOnClusterClickListener;
  }
  
  public void setOnClusterInfoWindowClickListener(ClusterManager.OnClusterInfoWindowClickListener<T> paramOnClusterInfoWindowClickListener)
  {
    this.mInfoWindowClickListener = paramOnClusterInfoWindowClickListener;
  }
  
  public void setOnClusterItemClickListener(ClusterManager.OnClusterItemClickListener<T> paramOnClusterItemClickListener)
  {
    this.mItemClickListener = paramOnClusterItemClickListener;
  }
  
  public void setOnClusterItemInfoWindowClickListener(ClusterManager.OnClusterItemInfoWindowClickListener<T> paramOnClusterItemInfoWindowClickListener)
  {
    this.mItemInfoWindowClickListener = paramOnClusterItemInfoWindowClickListener;
  }
  
  protected boolean shouldRenderAsCluster(Cluster<T> paramCluster)
  {
    return paramCluster.getSize() > 4;
  }
  
  @TargetApi(12)
  private class AnimationTask
    extends AnimatorListenerAdapter
    implements ValueAnimator.AnimatorUpdateListener
  {
    private final LatLng from;
    private MarkerManager mMarkerManager;
    private boolean mRemoveOnComplete;
    private final Marker marker;
    private final DefaultClusterRenderer.MarkerWithPosition markerWithPosition;
    private final LatLng to;
    
    private AnimationTask(DefaultClusterRenderer.MarkerWithPosition paramMarkerWithPosition, LatLng paramLatLng1, LatLng paramLatLng2)
    {
      this.markerWithPosition = paramMarkerWithPosition;
      this.marker = DefaultClusterRenderer.MarkerWithPosition.access$1700(paramMarkerWithPosition);
      this.from = paramLatLng1;
      this.to = paramLatLng2;
    }
    
    public void onAnimationEnd(Animator paramAnimator)
    {
      if (this.mRemoveOnComplete)
      {
        paramAnimator = (Cluster)DefaultClusterRenderer.this.mMarkerToCluster.get(this.marker);
        DefaultClusterRenderer.this.mClusterToMarker.remove(paramAnimator);
        DefaultClusterRenderer.this.mMarkerCache.remove(this.marker);
        DefaultClusterRenderer.this.mMarkerToCluster.remove(this.marker);
        this.mMarkerManager.remove(this.marker);
      }
      DefaultClusterRenderer.MarkerWithPosition.access$1602(this.markerWithPosition, this.to);
    }
    
    public void onAnimationUpdate(ValueAnimator paramValueAnimator)
    {
      float f = paramValueAnimator.getAnimatedFraction();
      double d3 = this.to.latitude;
      double d4 = this.from.latitude;
      double d5 = f;
      double d6 = this.from.latitude;
      double d2 = this.to.longitude - this.from.longitude;
      double d1 = d2;
      if (Math.abs(d2) > 180.0D) {
        d1 = d2 - Math.signum(d2) * 360.0D;
      }
      paramValueAnimator = new LatLng((d3 - d4) * d5 + d6, f * d1 + this.from.longitude);
      this.marker.setPosition(paramValueAnimator);
    }
    
    public void perform()
    {
      ValueAnimator localValueAnimator = ValueAnimator.ofFloat(new float[] { 0.0F, 1.0F });
      localValueAnimator.setInterpolator(DefaultClusterRenderer.ANIMATION_INTERP);
      localValueAnimator.addUpdateListener(this);
      localValueAnimator.addListener(this);
      localValueAnimator.start();
    }
    
    public void removeOnAnimationComplete(MarkerManager paramMarkerManager)
    {
      this.mMarkerManager = paramMarkerManager;
      this.mRemoveOnComplete = true;
    }
  }
  
  private class CreateMarkerTask
  {
    private final LatLng animateFrom;
    private final Cluster<T> cluster;
    private final Set<DefaultClusterRenderer.MarkerWithPosition> newMarkers;
    
    public CreateMarkerTask(Set<DefaultClusterRenderer.MarkerWithPosition> paramSet, LatLng paramLatLng)
    {
      this.cluster = paramSet;
      this.newMarkers = paramLatLng;
      LatLng localLatLng;
      this.animateFrom = localLatLng;
    }
    
    private void perform(DefaultClusterRenderer<T>.MarkerModifier paramDefaultClusterRenderer)
    {
      label93:
      Object localObject2;
      if (!DefaultClusterRenderer.this.shouldRenderAsCluster(this.cluster))
      {
        Iterator localIterator = this.cluster.getItems().iterator();
        if (localIterator.hasNext())
        {
          ClusterItem localClusterItem = (ClusterItem)localIterator.next();
          localObject1 = DefaultClusterRenderer.this.mMarkerCache.get(localClusterItem);
          if (localObject1 == null)
          {
            localObject1 = new MarkerOptions();
            if (this.animateFrom != null)
            {
              ((MarkerOptions)localObject1).position(this.animateFrom);
              DefaultClusterRenderer.this.onBeforeClusterItemRendered(localClusterItem, (MarkerOptions)localObject1);
              Marker localMarker = DefaultClusterRenderer.this.mClusterManager.getMarkerCollection().addMarker((MarkerOptions)localObject1);
              DefaultClusterRenderer.MarkerWithPosition localMarkerWithPosition = new DefaultClusterRenderer.MarkerWithPosition(localMarker, null);
              DefaultClusterRenderer.this.mMarkerCache.put(localClusterItem, localMarker);
              localObject1 = localMarker;
              localObject2 = localMarkerWithPosition;
              if (this.animateFrom != null)
              {
                paramDefaultClusterRenderer.animate(localMarkerWithPosition, this.animateFrom, localClusterItem.getPosition());
                localObject2 = localMarkerWithPosition;
                localObject1 = localMarker;
              }
            }
          }
          for (;;)
          {
            DefaultClusterRenderer.this.onClusterItemRendered(localClusterItem, (Marker)localObject1);
            this.newMarkers.add(localObject2);
            break;
            ((MarkerOptions)localObject1).position(localClusterItem.getPosition());
            break label93;
            localObject2 = new DefaultClusterRenderer.MarkerWithPosition((Marker)localObject1, null);
          }
        }
      }
      else
      {
        localObject2 = new MarkerOptions();
        if (this.animateFrom != null) {
          break label386;
        }
      }
      label386:
      for (Object localObject1 = this.cluster.getPosition();; localObject1 = this.animateFrom)
      {
        localObject1 = ((MarkerOptions)localObject2).position((LatLng)localObject1);
        DefaultClusterRenderer.this.onBeforeClusterRendered(this.cluster, (MarkerOptions)localObject1);
        localObject1 = DefaultClusterRenderer.this.mClusterManager.getClusterMarkerCollection().addMarker((MarkerOptions)localObject1);
        DefaultClusterRenderer.this.mMarkerToCluster.put(localObject1, this.cluster);
        DefaultClusterRenderer.this.mClusterToMarker.put(this.cluster, localObject1);
        localObject2 = new DefaultClusterRenderer.MarkerWithPosition((Marker)localObject1, null);
        if (this.animateFrom != null) {
          paramDefaultClusterRenderer.animate((DefaultClusterRenderer.MarkerWithPosition)localObject2, this.animateFrom, this.cluster.getPosition());
        }
        DefaultClusterRenderer.this.onClusterRendered(this.cluster, (Marker)localObject1);
        this.newMarkers.add(localObject2);
        return;
      }
    }
  }
  
  private static class MarkerCache<T>
  {
    private Map<T, Marker> mCache = new HashMap();
    private Map<Marker, T> mCacheReverse = new HashMap();
    
    public Marker get(T paramT)
    {
      return (Marker)this.mCache.get(paramT);
    }
    
    public T get(Marker paramMarker)
    {
      return (T)this.mCacheReverse.get(paramMarker);
    }
    
    public void put(T paramT, Marker paramMarker)
    {
      this.mCache.put(paramT, paramMarker);
      this.mCacheReverse.put(paramMarker, paramT);
    }
    
    public void remove(Marker paramMarker)
    {
      Object localObject = this.mCacheReverse.get(paramMarker);
      this.mCacheReverse.remove(paramMarker);
      this.mCache.remove(localObject);
    }
  }
  
  @SuppressLint({"HandlerLeak"})
  private class MarkerModifier
    extends Handler
    implements MessageQueue.IdleHandler
  {
    private static final int BLANK = 0;
    private final Condition busyCondition = this.lock.newCondition();
    private final Lock lock = new ReentrantLock();
    private Queue<DefaultClusterRenderer<T>.AnimationTask> mAnimationTasks = new LinkedList();
    private Queue<DefaultClusterRenderer<T>.CreateMarkerTask> mCreateMarkerTasks = new LinkedList();
    private boolean mListenerAdded;
    private Queue<DefaultClusterRenderer<T>.CreateMarkerTask> mOnScreenCreateMarkerTasks = new LinkedList();
    private Queue<Marker> mOnScreenRemoveMarkerTasks = new LinkedList();
    private Queue<Marker> mRemoveMarkerTasks = new LinkedList();
    
    private MarkerModifier()
    {
      super();
    }
    
    private void performNextTask()
    {
      if (!this.mOnScreenRemoveMarkerTasks.isEmpty()) {
        removeMarker((Marker)this.mOnScreenRemoveMarkerTasks.poll());
      }
      do
      {
        return;
        if (!this.mAnimationTasks.isEmpty())
        {
          ((DefaultClusterRenderer.AnimationTask)this.mAnimationTasks.poll()).perform();
          return;
        }
        if (!this.mOnScreenCreateMarkerTasks.isEmpty())
        {
          ((DefaultClusterRenderer.CreateMarkerTask)this.mOnScreenCreateMarkerTasks.poll()).perform(this);
          return;
        }
        if (!this.mCreateMarkerTasks.isEmpty())
        {
          ((DefaultClusterRenderer.CreateMarkerTask)this.mCreateMarkerTasks.poll()).perform(this);
          return;
        }
      } while (this.mRemoveMarkerTasks.isEmpty());
      removeMarker((Marker)this.mRemoveMarkerTasks.poll());
    }
    
    private void removeMarker(Marker paramMarker)
    {
      Cluster localCluster = (Cluster)DefaultClusterRenderer.this.mMarkerToCluster.get(paramMarker);
      DefaultClusterRenderer.this.mClusterToMarker.remove(localCluster);
      DefaultClusterRenderer.this.mMarkerCache.remove(paramMarker);
      DefaultClusterRenderer.this.mMarkerToCluster.remove(paramMarker);
      DefaultClusterRenderer.this.mClusterManager.getMarkerManager().remove(paramMarker);
    }
    
    public void add(boolean paramBoolean, DefaultClusterRenderer<T>.CreateMarkerTask paramDefaultClusterRenderer)
    {
      this.lock.lock();
      sendEmptyMessage(0);
      if (paramBoolean) {
        this.mOnScreenCreateMarkerTasks.add(paramDefaultClusterRenderer);
      }
      for (;;)
      {
        this.lock.unlock();
        return;
        this.mCreateMarkerTasks.add(paramDefaultClusterRenderer);
      }
    }
    
    public void animate(DefaultClusterRenderer.MarkerWithPosition paramMarkerWithPosition, LatLng paramLatLng1, LatLng paramLatLng2)
    {
      this.lock.lock();
      this.mAnimationTasks.add(new DefaultClusterRenderer.AnimationTask(DefaultClusterRenderer.this, paramMarkerWithPosition, paramLatLng1, paramLatLng2, null));
      this.lock.unlock();
    }
    
    public void animateThenRemove(DefaultClusterRenderer.MarkerWithPosition paramMarkerWithPosition, LatLng paramLatLng1, LatLng paramLatLng2)
    {
      this.lock.lock();
      paramMarkerWithPosition = new DefaultClusterRenderer.AnimationTask(DefaultClusterRenderer.this, paramMarkerWithPosition, paramLatLng1, paramLatLng2, null);
      paramMarkerWithPosition.removeOnAnimationComplete(DefaultClusterRenderer.this.mClusterManager.getMarkerManager());
      this.mAnimationTasks.add(paramMarkerWithPosition);
      this.lock.unlock();
    }
    
    public void handleMessage(Message paramMessage)
    {
      if (!this.mListenerAdded)
      {
        Looper.myQueue().addIdleHandler(this);
        this.mListenerAdded = true;
      }
      removeMessages(0);
      this.lock.lock();
      int i = 0;
      for (;;)
      {
        if (i < 10) {}
        try
        {
          performNextTask();
          i += 1;
        }
        finally
        {
          this.lock.unlock();
        }
      }
      if (!isBusy())
      {
        this.mListenerAdded = false;
        Looper.myQueue().removeIdleHandler(this);
        this.busyCondition.signalAll();
      }
      for (;;)
      {
        this.lock.unlock();
        return;
        sendEmptyMessageDelayed(0, 10L);
      }
    }
    
    /* Error */
    public boolean isBusy()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 52	com/google/maps/android/clustering/view/DefaultClusterRenderer$MarkerModifier:lock	Ljava/util/concurrent/locks/Lock;
      //   4: invokeinterface 150 1 0
      //   9: aload_0
      //   10: getfield 65	com/google/maps/android/clustering/view/DefaultClusterRenderer$MarkerModifier:mCreateMarkerTasks	Ljava/util/Queue;
      //   13: invokeinterface 84 1 0
      //   18: ifeq +53 -> 71
      //   21: aload_0
      //   22: getfield 67	com/google/maps/android/clustering/view/DefaultClusterRenderer$MarkerModifier:mOnScreenCreateMarkerTasks	Ljava/util/Queue;
      //   25: invokeinterface 84 1 0
      //   30: ifeq +41 -> 71
      //   33: aload_0
      //   34: getfield 71	com/google/maps/android/clustering/view/DefaultClusterRenderer$MarkerModifier:mOnScreenRemoveMarkerTasks	Ljava/util/Queue;
      //   37: invokeinterface 84 1 0
      //   42: ifeq +29 -> 71
      //   45: aload_0
      //   46: getfield 69	com/google/maps/android/clustering/view/DefaultClusterRenderer$MarkerModifier:mRemoveMarkerTasks	Ljava/util/Queue;
      //   49: invokeinterface 84 1 0
      //   54: ifeq +17 -> 71
      //   57: aload_0
      //   58: getfield 73	com/google/maps/android/clustering/view/DefaultClusterRenderer$MarkerModifier:mAnimationTasks	Ljava/util/Queue;
      //   61: invokeinterface 84 1 0
      //   66: istore_1
      //   67: iload_1
      //   68: ifne +16 -> 84
      //   71: iconst_1
      //   72: istore_1
      //   73: aload_0
      //   74: getfield 52	com/google/maps/android/clustering/view/DefaultClusterRenderer$MarkerModifier:lock	Ljava/util/concurrent/locks/Lock;
      //   77: invokeinterface 160 1 0
      //   82: iload_1
      //   83: ireturn
      //   84: iconst_0
      //   85: istore_1
      //   86: goto -13 -> 73
      //   89: astore_2
      //   90: aload_0
      //   91: getfield 52	com/google/maps/android/clustering/view/DefaultClusterRenderer$MarkerModifier:lock	Ljava/util/concurrent/locks/Lock;
      //   94: invokeinterface 160 1 0
      //   99: aload_2
      //   100: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	101	0	this	MarkerModifier
      //   66	20	1	bool	boolean
      //   89	11	2	localObject	Object
      // Exception table:
      //   from	to	target	type
      //   0	67	89	finally
    }
    
    public boolean queueIdle()
    {
      sendEmptyMessage(0);
      return true;
    }
    
    public void remove(boolean paramBoolean, Marker paramMarker)
    {
      this.lock.lock();
      sendEmptyMessage(0);
      if (paramBoolean) {
        this.mOnScreenRemoveMarkerTasks.add(paramMarker);
      }
      for (;;)
      {
        this.lock.unlock();
        return;
        this.mRemoveMarkerTasks.add(paramMarker);
      }
    }
    
    public void waitUntilFree()
    {
      while (isBusy())
      {
        sendEmptyMessage(0);
        this.lock.lock();
        try
        {
          if (isBusy()) {
            this.busyCondition.await();
          }
          this.lock.unlock();
        }
        catch (InterruptedException localInterruptedException)
        {
          throw new RuntimeException(localInterruptedException);
        }
        finally
        {
          this.lock.unlock();
        }
      }
    }
  }
  
  private static class MarkerWithPosition
  {
    private final Marker marker;
    private LatLng position;
    
    private MarkerWithPosition(Marker paramMarker)
    {
      this.marker = paramMarker;
      this.position = paramMarker.getPosition();
    }
    
    public boolean equals(Object paramObject)
    {
      if ((paramObject instanceof MarkerWithPosition)) {
        return this.marker.equals(((MarkerWithPosition)paramObject).marker);
      }
      return false;
    }
    
    public int hashCode()
    {
      return this.marker.hashCode();
    }
  }
  
  private class RenderTask
    implements Runnable
  {
    final Set<? extends Cluster<T>> clusters;
    private Runnable mCallback;
    private float mMapZoom;
    private Projection mProjection;
    private SphericalMercatorProjection mSphericalMercatorProjection;
    
    private RenderTask()
    {
      Set localSet;
      this.clusters = localSet;
    }
    
    @SuppressLint({"NewApi"})
    public void run()
    {
      if (this.clusters.equals(DefaultClusterRenderer.this.mClusters))
      {
        this.mCallback.run();
        return;
      }
      DefaultClusterRenderer.MarkerModifier localMarkerModifier = new DefaultClusterRenderer.MarkerModifier(DefaultClusterRenderer.this, null);
      float f1 = this.mMapZoom;
      if (f1 > DefaultClusterRenderer.this.mZoom) {}
      float f2;
      Object localObject3;
      LatLngBounds localLatLngBounds;
      for (int i = 1;; i = 0)
      {
        f2 = DefaultClusterRenderer.this.mZoom;
        localObject3 = DefaultClusterRenderer.this.mMarkers;
        localLatLngBounds = this.mProjection.getVisibleRegion().latLngBounds;
        localObject2 = null;
        localObject1 = localObject2;
        if (DefaultClusterRenderer.this.mClusters == null) {
          break;
        }
        localObject1 = localObject2;
        if (!DefaultClusterRenderer.SHOULD_ANIMATE) {
          break;
        }
        localObject2 = new ArrayList();
        localObject4 = DefaultClusterRenderer.this.mClusters.iterator();
        for (;;)
        {
          localObject1 = localObject2;
          if (!((Iterator)localObject4).hasNext()) {
            break;
          }
          localObject1 = (Cluster)((Iterator)localObject4).next();
          if ((DefaultClusterRenderer.this.shouldRenderAsCluster((Cluster)localObject1)) && (localLatLngBounds.contains(((Cluster)localObject1).getPosition()))) {
            ((List)localObject2).add(this.mSphericalMercatorProjection.toPoint(((Cluster)localObject1).getPosition()));
          }
        }
      }
      Object localObject4 = Collections.newSetFromMap(new ConcurrentHashMap());
      Object localObject2 = this.clusters.iterator();
      Object localObject5;
      boolean bool;
      while (((Iterator)localObject2).hasNext())
      {
        localObject5 = (Cluster)((Iterator)localObject2).next();
        bool = localLatLngBounds.contains(((Cluster)localObject5).getPosition());
        if ((i != 0) && (bool) && (DefaultClusterRenderer.SHOULD_ANIMATE))
        {
          Object localObject6 = DefaultClusterRenderer.findClosestCluster((List)localObject1, this.mSphericalMercatorProjection.toPoint(((Cluster)localObject5).getPosition()));
          if (localObject6 != null)
          {
            localObject6 = this.mSphericalMercatorProjection.toLatLng((Point)localObject6);
            localMarkerModifier.add(true, new DefaultClusterRenderer.CreateMarkerTask(DefaultClusterRenderer.this, (Cluster)localObject5, (Set)localObject4, (LatLng)localObject6));
          }
          else
          {
            localMarkerModifier.add(true, new DefaultClusterRenderer.CreateMarkerTask(DefaultClusterRenderer.this, (Cluster)localObject5, (Set)localObject4, null));
          }
        }
        else
        {
          localMarkerModifier.add(bool, new DefaultClusterRenderer.CreateMarkerTask(DefaultClusterRenderer.this, (Cluster)localObject5, (Set)localObject4, null));
        }
      }
      localMarkerModifier.waitUntilFree();
      ((Set)localObject3).removeAll((Collection)localObject4);
      Object localObject1 = null;
      if (DefaultClusterRenderer.SHOULD_ANIMATE)
      {
        localObject2 = new ArrayList();
        localObject5 = this.clusters.iterator();
        for (;;)
        {
          localObject1 = localObject2;
          if (!((Iterator)localObject5).hasNext()) {
            break;
          }
          localObject1 = (Cluster)((Iterator)localObject5).next();
          if ((DefaultClusterRenderer.this.shouldRenderAsCluster((Cluster)localObject1)) && (localLatLngBounds.contains(((Cluster)localObject1).getPosition()))) {
            ((List)localObject2).add(this.mSphericalMercatorProjection.toPoint(((Cluster)localObject1).getPosition()));
          }
        }
      }
      localObject2 = ((Set)localObject3).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (DefaultClusterRenderer.MarkerWithPosition)((Iterator)localObject2).next();
        bool = localLatLngBounds.contains(((DefaultClusterRenderer.MarkerWithPosition)localObject3).position);
        if ((i == 0) && (f1 - f2 > -3.0F) && (bool) && (DefaultClusterRenderer.SHOULD_ANIMATE))
        {
          localObject5 = DefaultClusterRenderer.findClosestCluster((List)localObject1, this.mSphericalMercatorProjection.toPoint(((DefaultClusterRenderer.MarkerWithPosition)localObject3).position));
          if (localObject5 != null)
          {
            localObject5 = this.mSphericalMercatorProjection.toLatLng((Point)localObject5);
            localMarkerModifier.animateThenRemove((DefaultClusterRenderer.MarkerWithPosition)localObject3, ((DefaultClusterRenderer.MarkerWithPosition)localObject3).position, (LatLng)localObject5);
          }
          else
          {
            localMarkerModifier.remove(true, ((DefaultClusterRenderer.MarkerWithPosition)localObject3).marker);
          }
        }
        else
        {
          localMarkerModifier.remove(bool, ((DefaultClusterRenderer.MarkerWithPosition)localObject3).marker);
        }
      }
      localMarkerModifier.waitUntilFree();
      DefaultClusterRenderer.access$1302(DefaultClusterRenderer.this, (Set)localObject4);
      DefaultClusterRenderer.access$1102(DefaultClusterRenderer.this, this.clusters);
      DefaultClusterRenderer.access$1002(DefaultClusterRenderer.this, f1);
      this.mCallback.run();
    }
    
    public void setCallback(Runnable paramRunnable)
    {
      this.mCallback = paramRunnable;
    }
    
    public void setMapZoom(float paramFloat)
    {
      this.mMapZoom = paramFloat;
      this.mSphericalMercatorProjection = new SphericalMercatorProjection(256.0D * Math.pow(2.0D, Math.min(paramFloat, DefaultClusterRenderer.this.mZoom)));
    }
    
    public void setProjection(Projection paramProjection)
    {
      this.mProjection = paramProjection;
    }
  }
  
  @SuppressLint({"HandlerLeak"})
  private class ViewModifier
    extends Handler
  {
    private static final int RUN_TASK = 0;
    private static final int TASK_FINISHED = 1;
    private DefaultClusterRenderer<T>.RenderTask mNextClusters = null;
    private boolean mViewModificationInProgress = false;
    
    private ViewModifier() {}
    
    public void handleMessage(Message paramMessage)
    {
      if (paramMessage.what == 1)
      {
        this.mViewModificationInProgress = false;
        if (this.mNextClusters != null) {
          sendEmptyMessage(0);
        }
      }
      do
      {
        return;
        removeMessages(0);
      } while ((this.mViewModificationInProgress) || (this.mNextClusters == null));
      try
      {
        paramMessage = this.mNextClusters;
        this.mNextClusters = null;
        this.mViewModificationInProgress = true;
        paramMessage.setCallback(new Runnable()
        {
          public void run()
          {
            DefaultClusterRenderer.ViewModifier.this.sendEmptyMessage(1);
          }
        });
        paramMessage.setProjection(DefaultClusterRenderer.this.mMap.getProjection());
        paramMessage.setMapZoom(DefaultClusterRenderer.this.mMap.getCameraPosition().zoom);
        new Thread(paramMessage).start();
        return;
      }
      finally {}
    }
    
    public void queue(Set<? extends Cluster<T>> paramSet)
    {
      try
      {
        this.mNextClusters = new DefaultClusterRenderer.RenderTask(DefaultClusterRenderer.this, paramSet, null);
        sendEmptyMessage(0);
        return;
      }
      finally {}
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\maps\android\clustering\view\DefaultClusterRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */