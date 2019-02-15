package com.firebase.geofire.core;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleFuture<V>
  implements Future<V>
{
  private boolean isSet = false;
  private final Lock lock = new ReentrantLock();
  private V object = null;
  private final Condition setCondition = this.lock.newCondition();
  
  public boolean cancel(boolean paramBoolean)
  {
    return false;
  }
  
  public V get()
    throws InterruptedException, ExecutionException
  {
    this.lock.lock();
    try
    {
      while (!this.isSet) {
        this.setCondition.await();
      }
      localObject2 = this.object;
    }
    finally
    {
      this.lock.unlock();
    }
    Object localObject2;
    this.lock.unlock();
    return (V)localObject2;
  }
  
  public V get(long paramLong, TimeUnit paramTimeUnit)
    throws InterruptedException, ExecutionException, TimeoutException
  {
    this.lock.lock();
    try
    {
      while (!this.isSet) {
        if (!this.setCondition.await(paramLong, paramTimeUnit)) {
          throw new TimeoutException();
        }
      }
    }
    finally
    {
      this.lock.unlock();
    }
    paramTimeUnit = this.object;
    this.lock.unlock();
    return paramTimeUnit;
  }
  
  public boolean isCancelled()
  {
    return false;
  }
  
  public boolean isDone()
  {
    this.lock.lock();
    try
    {
      boolean bool = this.isSet;
      return bool;
    }
    finally
    {
      this.lock.unlock();
    }
  }
  
  /* Error */
  public void put(V paramV)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 29	com/firebase/geofire/core/SimpleFuture:lock	Ljava/util/concurrent/locks/Lock;
    //   6: invokeinterface 48 1 0
    //   11: aload_0
    //   12: aload_1
    //   13: putfield 22	com/firebase/geofire/core/SimpleFuture:object	Ljava/lang/Object;
    //   16: aload_0
    //   17: iconst_1
    //   18: putfield 24	com/firebase/geofire/core/SimpleFuture:isSet	Z
    //   21: aload_0
    //   22: getfield 37	com/firebase/geofire/core/SimpleFuture:setCondition	Ljava/util/concurrent/locks/Condition;
    //   25: invokeinterface 75 1 0
    //   30: aload_0
    //   31: getfield 29	com/firebase/geofire/core/SimpleFuture:lock	Ljava/util/concurrent/locks/Lock;
    //   34: invokeinterface 56 1 0
    //   39: aload_0
    //   40: monitorexit
    //   41: return
    //   42: astore_1
    //   43: aload_0
    //   44: getfield 29	com/firebase/geofire/core/SimpleFuture:lock	Ljava/util/concurrent/locks/Lock;
    //   47: invokeinterface 56 1 0
    //   52: aload_1
    //   53: athrow
    //   54: astore_1
    //   55: aload_0
    //   56: monitorexit
    //   57: aload_1
    //   58: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	59	0	this	SimpleFuture
    //   0	59	1	paramV	V
    // Exception table:
    //   from	to	target	type
    //   11	30	42	finally
    //   2	11	54	finally
    //   30	39	54	finally
    //   43	54	54	finally
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\firebase\geofire\core\SimpleFuture.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */