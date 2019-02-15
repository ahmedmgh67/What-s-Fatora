package com.bumptech.glide.load.engine.cache;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.Resource;

public class MemoryCacheAdapter
  implements MemoryCache
{
  private MemoryCache.ResourceRemovedListener listener;
  
  public void clearMemory() {}
  
  public int getCurrentSize()
  {
    return 0;
  }
  
  public int getMaxSize()
  {
    return 0;
  }
  
  public Resource<?> put(Key paramKey, Resource<?> paramResource)
  {
    this.listener.onResourceRemoved(paramResource);
    return null;
  }
  
  public Resource<?> remove(Key paramKey)
  {
    return null;
  }
  
  public void setResourceRemovedListener(MemoryCache.ResourceRemovedListener paramResourceRemovedListener)
  {
    this.listener = paramResourceRemovedListener;
  }
  
  public void setSizeMultiplier(float paramFloat) {}
  
  public void trimMemory(int paramInt) {}
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\engine\cache\MemoryCacheAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */