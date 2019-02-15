package com.bumptech.glide.load.engine.executor;

import android.os.Process;
import android.util.Log;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class FifoPriorityThreadPoolExecutor
  extends ThreadPoolExecutor
{
  private static final String TAG = "PriorityExecutor";
  private final AtomicInteger ordering = new AtomicInteger();
  private final UncaughtThrowableStrategy uncaughtThrowableStrategy;
  
  public FifoPriorityThreadPoolExecutor(int paramInt)
  {
    this(paramInt, UncaughtThrowableStrategy.LOG);
  }
  
  public FifoPriorityThreadPoolExecutor(int paramInt1, int paramInt2, long paramLong, TimeUnit paramTimeUnit, ThreadFactory paramThreadFactory, UncaughtThrowableStrategy paramUncaughtThrowableStrategy)
  {
    super(paramInt1, paramInt2, paramLong, paramTimeUnit, new PriorityBlockingQueue(), paramThreadFactory);
    this.uncaughtThrowableStrategy = paramUncaughtThrowableStrategy;
  }
  
  public FifoPriorityThreadPoolExecutor(int paramInt, UncaughtThrowableStrategy paramUncaughtThrowableStrategy)
  {
    this(paramInt, paramInt, 0L, TimeUnit.MILLISECONDS, new DefaultThreadFactory(), paramUncaughtThrowableStrategy);
  }
  
  protected void afterExecute(Runnable paramRunnable, Throwable paramThrowable)
  {
    super.afterExecute(paramRunnable, paramThrowable);
    if ((paramThrowable == null) && ((paramRunnable instanceof Future)))
    {
      paramRunnable = (Future)paramRunnable;
      if ((!paramRunnable.isDone()) || (paramRunnable.isCancelled())) {}
    }
    try
    {
      paramRunnable.get();
      return;
    }
    catch (InterruptedException paramRunnable)
    {
      this.uncaughtThrowableStrategy.handle(paramRunnable);
      return;
    }
    catch (ExecutionException paramRunnable)
    {
      this.uncaughtThrowableStrategy.handle(paramRunnable);
    }
  }
  
  protected <T> RunnableFuture<T> newTaskFor(Runnable paramRunnable, T paramT)
  {
    return new LoadTask(paramRunnable, paramT, this.ordering.getAndIncrement());
  }
  
  public static class DefaultThreadFactory
    implements ThreadFactory
  {
    int threadNum = 0;
    
    public Thread newThread(Runnable paramRunnable)
    {
      paramRunnable = new Thread(paramRunnable, "fifo-pool-thread-" + this.threadNum)
      {
        public void run()
        {
          Process.setThreadPriority(10);
          super.run();
        }
      };
      this.threadNum += 1;
      return paramRunnable;
    }
  }
  
  static class LoadTask<T>
    extends FutureTask<T>
    implements Comparable<LoadTask<?>>
  {
    private final int order;
    private final int priority;
    
    public LoadTask(Runnable paramRunnable, T paramT, int paramInt)
    {
      super(paramT);
      if (!(paramRunnable instanceof Prioritized)) {
        throw new IllegalArgumentException("FifoPriorityThreadPoolExecutor must be given Runnables that implement Prioritized");
      }
      this.priority = ((Prioritized)paramRunnable).getPriority();
      this.order = paramInt;
    }
    
    public int compareTo(LoadTask<?> paramLoadTask)
    {
      int j = this.priority - paramLoadTask.priority;
      int i = j;
      if (j == 0) {
        i = this.order - paramLoadTask.order;
      }
      return i;
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      if ((paramObject instanceof LoadTask))
      {
        paramObject = (LoadTask)paramObject;
        bool1 = bool2;
        if (this.order == ((LoadTask)paramObject).order)
        {
          bool1 = bool2;
          if (this.priority == ((LoadTask)paramObject).priority) {
            bool1 = true;
          }
        }
      }
      return bool1;
    }
    
    public int hashCode()
    {
      return this.priority * 31 + this.order;
    }
  }
  
  public static enum UncaughtThrowableStrategy
  {
    IGNORE,  LOG,  THROW;
    
    private UncaughtThrowableStrategy() {}
    
    protected void handle(Throwable paramThrowable) {}
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\engine\executor\FifoPriorityThreadPoolExecutor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */