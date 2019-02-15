package com.bumptech.glide.load.engine.cache;

import com.bumptech.glide.load.Key;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

final class DiskCacheWriteLocker
{
  private final Map<Key, WriteLock> locks = new HashMap();
  private final WriteLockPool writeLockPool = new WriteLockPool(null);
  
  void acquire(Key paramKey)
  {
    try
    {
      WriteLock localWriteLock2 = (WriteLock)this.locks.get(paramKey);
      WriteLock localWriteLock1 = localWriteLock2;
      if (localWriteLock2 == null)
      {
        localWriteLock1 = this.writeLockPool.obtain();
        this.locks.put(paramKey, localWriteLock1);
      }
      localWriteLock1.interestedThreads += 1;
      localWriteLock1.lock.lock();
      return;
    }
    finally {}
  }
  
  void release(Key paramKey)
  {
    WriteLock localWriteLock1;
    for (;;)
    {
      try
      {
        localWriteLock1 = (WriteLock)this.locks.get(paramKey);
        if ((localWriteLock1 != null) && (localWriteLock1.interestedThreads > 0)) {
          break;
        }
        paramKey = new StringBuilder().append("Cannot release a lock that is not held, key: ").append(paramKey).append(", interestedThreads: ");
        if (localWriteLock1 == null)
        {
          i = 0;
          throw new IllegalArgumentException(i);
        }
      }
      finally {}
      i = localWriteLock1.interestedThreads;
    }
    int i = localWriteLock1.interestedThreads - 1;
    localWriteLock1.interestedThreads = i;
    if (i == 0)
    {
      WriteLock localWriteLock2 = (WriteLock)this.locks.remove(paramKey);
      if (!localWriteLock2.equals(localWriteLock1)) {
        throw new IllegalStateException("Removed the wrong lock, expected to remove: " + localWriteLock1 + ", but actually removed: " + localWriteLock2 + ", key: " + paramKey);
      }
      this.writeLockPool.offer(localWriteLock2);
    }
    localWriteLock1.lock.unlock();
  }
  
  private static class WriteLock
  {
    int interestedThreads;
    final Lock lock = new ReentrantLock();
  }
  
  private static class WriteLockPool
  {
    private static final int MAX_POOL_SIZE = 10;
    private final Queue<DiskCacheWriteLocker.WriteLock> pool = new ArrayDeque();
    
    DiskCacheWriteLocker.WriteLock obtain()
    {
      synchronized (this.pool)
      {
        DiskCacheWriteLocker.WriteLock localWriteLock = (DiskCacheWriteLocker.WriteLock)this.pool.poll();
        ??? = localWriteLock;
        if (localWriteLock == null) {
          ??? = new DiskCacheWriteLocker.WriteLock(null);
        }
        return (DiskCacheWriteLocker.WriteLock)???;
      }
    }
    
    void offer(DiskCacheWriteLocker.WriteLock paramWriteLock)
    {
      synchronized (this.pool)
      {
        if (this.pool.size() < 10) {
          this.pool.offer(paramWriteLock);
        }
        return;
      }
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\engine\cache\DiskCacheWriteLocker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */