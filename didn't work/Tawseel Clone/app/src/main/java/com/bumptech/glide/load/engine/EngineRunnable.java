package com.bumptech.glide.load.engine;

import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.executor.Prioritized;
import com.bumptech.glide.request.ResourceCallback;

class EngineRunnable
  implements Runnable, Prioritized
{
  private static final String TAG = "EngineRunnable";
  private final DecodeJob<?, ?, ?> decodeJob;
  private volatile boolean isCancelled;
  private final EngineRunnableManager manager;
  private final Priority priority;
  private Stage stage;
  
  public EngineRunnable(EngineRunnableManager paramEngineRunnableManager, DecodeJob<?, ?, ?> paramDecodeJob, Priority paramPriority)
  {
    this.manager = paramEngineRunnableManager;
    this.decodeJob = paramDecodeJob;
    this.stage = Stage.CACHE;
    this.priority = paramPriority;
  }
  
  private Resource<?> decode()
    throws Exception
  {
    if (isDecodingFromCache()) {
      return decodeFromCache();
    }
    return decodeFromSource();
  }
  
  private Resource<?> decodeFromCache()
    throws Exception
  {
    Object localObject2 = null;
    try
    {
      localObject1 = this.decodeJob.decodeResultFromCache();
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = this.decodeJob.decodeSourceFromCache();
      }
      return (Resource<?>)localObject2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject1 = localObject2;
        if (Log.isLoggable("EngineRunnable", 3))
        {
          Log.d("EngineRunnable", "Exception decoding result from cache: " + localException);
          localObject1 = localObject2;
        }
      }
    }
  }
  
  private Resource<?> decodeFromSource()
    throws Exception
  {
    return this.decodeJob.decodeFromSource();
  }
  
  private boolean isDecodingFromCache()
  {
    return this.stage == Stage.CACHE;
  }
  
  private void onLoadComplete(Resource paramResource)
  {
    this.manager.onResourceReady(paramResource);
  }
  
  private void onLoadFailed(Exception paramException)
  {
    if (isDecodingFromCache())
    {
      this.stage = Stage.SOURCE;
      this.manager.submitForSource(this);
      return;
    }
    this.manager.onException(paramException);
  }
  
  public void cancel()
  {
    this.isCancelled = true;
    this.decodeJob.cancel();
  }
  
  public int getPriority()
  {
    return this.priority.ordinal();
  }
  
  public void run()
  {
    if (this.isCancelled) {}
    Object localObject2;
    do
    {
      return;
      Object localObject1 = null;
      localObject2 = null;
      try
      {
        Resource localResource = decode();
        localObject2 = localResource;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          if (Log.isLoggable("EngineRunnable", 2)) {
            Log.v("EngineRunnable", "Exception decoding", localException);
          }
        }
        if (localObject2 != null) {
          break label69;
        }
        onLoadFailed(localException);
        return;
        onLoadComplete((Resource)localObject2);
      }
      if (!this.isCancelled) {
        break label59;
      }
    } while (localObject2 == null);
    ((Resource)localObject2).recycle();
    return;
    label59:
    label69:
    return;
  }
  
  static abstract interface EngineRunnableManager
    extends ResourceCallback
  {
    public abstract void submitForSource(EngineRunnable paramEngineRunnable);
  }
  
  private static enum Stage
  {
    CACHE,  SOURCE;
    
    private Stage() {}
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\engine\EngineRunnable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */