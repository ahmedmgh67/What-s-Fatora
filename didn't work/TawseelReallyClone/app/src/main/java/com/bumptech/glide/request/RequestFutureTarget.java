package com.bumptech.glide.request;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.util.Util;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class RequestFutureTarget<T, R>
  implements FutureTarget<R>, Runnable
{
  private static final Waiter DEFAULT_WAITER = new Waiter();
  private final boolean assertBackgroundThread;
  private Exception exception;
  private boolean exceptionReceived;
  private final int height;
  private boolean isCancelled;
  private final Handler mainHandler;
  private Request request;
  private R resource;
  private boolean resultReceived;
  private final Waiter waiter;
  private final int width;
  
  public RequestFutureTarget(Handler paramHandler, int paramInt1, int paramInt2)
  {
    this(paramHandler, paramInt1, paramInt2, true, DEFAULT_WAITER);
  }
  
  RequestFutureTarget(Handler paramHandler, int paramInt1, int paramInt2, boolean paramBoolean, Waiter paramWaiter)
  {
    this.mainHandler = paramHandler;
    this.width = paramInt1;
    this.height = paramInt2;
    this.assertBackgroundThread = paramBoolean;
    this.waiter = paramWaiter;
  }
  
  private R doGet(Long paramLong)
    throws ExecutionException, InterruptedException, TimeoutException
  {
    try
    {
      if (this.assertBackgroundThread) {
        Util.assertBackgroundThread();
      }
      if (this.isCancelled) {
        throw new CancellationException();
      }
    }
    finally {}
    if (this.exceptionReceived) {
      throw new ExecutionException(this.exception);
    }
    if (this.resultReceived) {}
    for (paramLong = this.resource;; paramLong = this.resource)
    {
      return paramLong;
      if (paramLong == null) {
        this.waiter.waitForTimeout(this, 0L);
      }
      while (Thread.interrupted())
      {
        throw new InterruptedException();
        if (paramLong.longValue() > 0L) {
          this.waiter.waitForTimeout(this, paramLong.longValue());
        }
      }
      if (this.exceptionReceived) {
        throw new ExecutionException(this.exception);
      }
      if (this.isCancelled) {
        throw new CancellationException();
      }
      if (!this.resultReceived) {
        throw new TimeoutException();
      }
    }
  }
  
  public boolean cancel(boolean paramBoolean)
  {
    boolean bool1 = true;
    boolean bool2 = true;
    for (;;)
    {
      try
      {
        boolean bool3 = this.isCancelled;
        if (bool3) {
          return bool2;
        }
        if (!isDone())
        {
          bool2 = bool1;
          if (bool1)
          {
            this.isCancelled = true;
            if (paramBoolean) {
              clear();
            }
            this.waiter.notifyAll(this);
            bool2 = bool1;
          }
        }
        else
        {
          bool1 = false;
        }
      }
      finally {}
    }
  }
  
  public void clear()
  {
    this.mainHandler.post(this);
  }
  
  public R get()
    throws InterruptedException, ExecutionException
  {
    try
    {
      Object localObject = doGet(null);
      return (R)localObject;
    }
    catch (TimeoutException localTimeoutException)
    {
      throw new AssertionError(localTimeoutException);
    }
  }
  
  public R get(long paramLong, TimeUnit paramTimeUnit)
    throws InterruptedException, ExecutionException, TimeoutException
  {
    return (R)doGet(Long.valueOf(paramTimeUnit.toMillis(paramLong)));
  }
  
  public Request getRequest()
  {
    return this.request;
  }
  
  public void getSize(SizeReadyCallback paramSizeReadyCallback)
  {
    paramSizeReadyCallback.onSizeReady(this.width, this.height);
  }
  
  public boolean isCancelled()
  {
    try
    {
      boolean bool = this.isCancelled;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  public boolean isDone()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 69	com/bumptech/glide/request/RequestFutureTarget:isCancelled	Z
    //   6: ifne +12 -> 18
    //   9: aload_0
    //   10: getfield 81	com/bumptech/glide/request/RequestFutureTarget:resultReceived	Z
    //   13: istore_1
    //   14: iload_1
    //   15: ifeq +9 -> 24
    //   18: iconst_1
    //   19: istore_1
    //   20: aload_0
    //   21: monitorexit
    //   22: iload_1
    //   23: ireturn
    //   24: iconst_0
    //   25: istore_1
    //   26: goto -6 -> 20
    //   29: astore_2
    //   30: aload_0
    //   31: monitorexit
    //   32: aload_2
    //   33: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	34	0	this	RequestFutureTarget
    //   13	13	1	bool	boolean
    //   29	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	14	29	finally
  }
  
  public void onDestroy() {}
  
  public void onLoadCleared(Drawable paramDrawable) {}
  
  public void onLoadFailed(Exception paramException, Drawable paramDrawable)
  {
    try
    {
      this.exceptionReceived = true;
      this.exception = paramException;
      this.waiter.notifyAll(this);
      return;
    }
    finally
    {
      paramException = finally;
      throw paramException;
    }
  }
  
  public void onLoadStarted(Drawable paramDrawable) {}
  
  public void onResourceReady(R paramR, GlideAnimation<? super R> paramGlideAnimation)
  {
    try
    {
      this.resultReceived = true;
      this.resource = paramR;
      this.waiter.notifyAll(this);
      return;
    }
    finally
    {
      paramR = finally;
      throw paramR;
    }
  }
  
  public void onStart() {}
  
  public void onStop() {}
  
  public void run()
  {
    if (this.request != null)
    {
      this.request.clear();
      cancel(false);
    }
  }
  
  public void setRequest(Request paramRequest)
  {
    this.request = paramRequest;
  }
  
  static class Waiter
  {
    public void notifyAll(Object paramObject)
    {
      paramObject.notifyAll();
    }
    
    public void waitForTimeout(Object paramObject, long paramLong)
      throws InterruptedException
    {
      paramObject.wait(paramLong);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\request\RequestFutureTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */