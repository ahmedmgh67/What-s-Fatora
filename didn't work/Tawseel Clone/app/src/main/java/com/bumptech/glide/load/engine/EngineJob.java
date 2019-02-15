package com.bumptech.glide.load.engine;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.request.ResourceCallback;
import com.bumptech.glide.util.Util;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

class EngineJob
  implements EngineRunnable.EngineRunnableManager
{
  private static final EngineResourceFactory DEFAULT_FACTORY = new EngineResourceFactory();
  private static final Handler MAIN_THREAD_HANDLER = new Handler(Looper.getMainLooper(), new MainThreadCallback(null));
  private static final int MSG_COMPLETE = 1;
  private static final int MSG_EXCEPTION = 2;
  private final List<ResourceCallback> cbs = new ArrayList();
  private final ExecutorService diskCacheService;
  private EngineResource<?> engineResource;
  private final EngineResourceFactory engineResourceFactory;
  private EngineRunnable engineRunnable;
  private Exception exception;
  private volatile Future<?> future;
  private boolean hasException;
  private boolean hasResource;
  private Set<ResourceCallback> ignoredCallbacks;
  private final boolean isCacheable;
  private boolean isCancelled;
  private final Key key;
  private final EngineJobListener listener;
  private Resource<?> resource;
  private final ExecutorService sourceService;
  
  public EngineJob(Key paramKey, ExecutorService paramExecutorService1, ExecutorService paramExecutorService2, boolean paramBoolean, EngineJobListener paramEngineJobListener)
  {
    this(paramKey, paramExecutorService1, paramExecutorService2, paramBoolean, paramEngineJobListener, DEFAULT_FACTORY);
  }
  
  public EngineJob(Key paramKey, ExecutorService paramExecutorService1, ExecutorService paramExecutorService2, boolean paramBoolean, EngineJobListener paramEngineJobListener, EngineResourceFactory paramEngineResourceFactory)
  {
    this.key = paramKey;
    this.diskCacheService = paramExecutorService1;
    this.sourceService = paramExecutorService2;
    this.isCacheable = paramBoolean;
    this.listener = paramEngineJobListener;
    this.engineResourceFactory = paramEngineResourceFactory;
  }
  
  private void addIgnoredCallback(ResourceCallback paramResourceCallback)
  {
    if (this.ignoredCallbacks == null) {
      this.ignoredCallbacks = new HashSet();
    }
    this.ignoredCallbacks.add(paramResourceCallback);
  }
  
  private void handleExceptionOnMainThread()
  {
    if (this.isCancelled) {}
    for (;;)
    {
      return;
      if (this.cbs.isEmpty()) {
        throw new IllegalStateException("Received an exception without any callbacks to notify");
      }
      this.hasException = true;
      this.listener.onEngineJobComplete(this.key, null);
      Iterator localIterator = this.cbs.iterator();
      while (localIterator.hasNext())
      {
        ResourceCallback localResourceCallback = (ResourceCallback)localIterator.next();
        if (!isInIgnoredCallbacks(localResourceCallback)) {
          localResourceCallback.onException(this.exception);
        }
      }
    }
  }
  
  private void handleResultOnMainThread()
  {
    if (this.isCancelled)
    {
      this.resource.recycle();
      return;
    }
    if (this.cbs.isEmpty()) {
      throw new IllegalStateException("Received a resource without any callbacks to notify");
    }
    this.engineResource = this.engineResourceFactory.build(this.resource, this.isCacheable);
    this.hasResource = true;
    this.engineResource.acquire();
    this.listener.onEngineJobComplete(this.key, this.engineResource);
    Iterator localIterator = this.cbs.iterator();
    while (localIterator.hasNext())
    {
      ResourceCallback localResourceCallback = (ResourceCallback)localIterator.next();
      if (!isInIgnoredCallbacks(localResourceCallback))
      {
        this.engineResource.acquire();
        localResourceCallback.onResourceReady(this.engineResource);
      }
    }
    this.engineResource.release();
  }
  
  private boolean isInIgnoredCallbacks(ResourceCallback paramResourceCallback)
  {
    return (this.ignoredCallbacks != null) && (this.ignoredCallbacks.contains(paramResourceCallback));
  }
  
  public void addCallback(ResourceCallback paramResourceCallback)
  {
    
    if (this.hasResource)
    {
      paramResourceCallback.onResourceReady(this.engineResource);
      return;
    }
    if (this.hasException)
    {
      paramResourceCallback.onException(this.exception);
      return;
    }
    this.cbs.add(paramResourceCallback);
  }
  
  void cancel()
  {
    if ((this.hasException) || (this.hasResource) || (this.isCancelled)) {
      return;
    }
    this.engineRunnable.cancel();
    Future localFuture = this.future;
    if (localFuture != null) {
      localFuture.cancel(true);
    }
    this.isCancelled = true;
    this.listener.onEngineJobCancelled(this, this.key);
  }
  
  boolean isCancelled()
  {
    return this.isCancelled;
  }
  
  public void onException(Exception paramException)
  {
    this.exception = paramException;
    MAIN_THREAD_HANDLER.obtainMessage(2, this).sendToTarget();
  }
  
  public void onResourceReady(Resource<?> paramResource)
  {
    this.resource = paramResource;
    MAIN_THREAD_HANDLER.obtainMessage(1, this).sendToTarget();
  }
  
  public void removeCallback(ResourceCallback paramResourceCallback)
  {
    
    if ((this.hasResource) || (this.hasException)) {
      addIgnoredCallback(paramResourceCallback);
    }
    do
    {
      return;
      this.cbs.remove(paramResourceCallback);
    } while (!this.cbs.isEmpty());
    cancel();
  }
  
  public void start(EngineRunnable paramEngineRunnable)
  {
    this.engineRunnable = paramEngineRunnable;
    this.future = this.diskCacheService.submit(paramEngineRunnable);
  }
  
  public void submitForSource(EngineRunnable paramEngineRunnable)
  {
    this.future = this.sourceService.submit(paramEngineRunnable);
  }
  
  static class EngineResourceFactory
  {
    public <R> EngineResource<R> build(Resource<R> paramResource, boolean paramBoolean)
    {
      return new EngineResource(paramResource, paramBoolean);
    }
  }
  
  private static class MainThreadCallback
    implements Handler.Callback
  {
    public boolean handleMessage(Message paramMessage)
    {
      if ((1 == paramMessage.what) || (2 == paramMessage.what))
      {
        EngineJob localEngineJob = (EngineJob)paramMessage.obj;
        if (1 == paramMessage.what)
        {
          localEngineJob.handleResultOnMainThread();
          return true;
        }
        localEngineJob.handleExceptionOnMainThread();
        return true;
      }
      return false;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\engine\EngineJob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */