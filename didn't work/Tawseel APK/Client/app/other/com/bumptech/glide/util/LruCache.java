package com.bumptech.glide.util;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

public class LruCache<T, Y>
{
  private final LinkedHashMap<T, Y> cache = new LinkedHashMap(100, 0.75F, true);
  private int currentSize = 0;
  private final int initialMaxSize;
  private int maxSize;
  
  public LruCache(int paramInt)
  {
    this.initialMaxSize = paramInt;
    this.maxSize = paramInt;
  }
  
  private void evict()
  {
    trimToSize(this.maxSize);
  }
  
  public void clearMemory()
  {
    trimToSize(0);
  }
  
  public boolean contains(T paramT)
  {
    return this.cache.containsKey(paramT);
  }
  
  public Y get(T paramT)
  {
    return (Y)this.cache.get(paramT);
  }
  
  public int getCurrentSize()
  {
    return this.currentSize;
  }
  
  public int getMaxSize()
  {
    return this.maxSize;
  }
  
  protected int getSize(Y paramY)
  {
    return 1;
  }
  
  protected void onItemEvicted(T paramT, Y paramY) {}
  
  public Y put(T paramT, Y paramY)
  {
    if (getSize(paramY) >= this.maxSize)
    {
      onItemEvicted(paramT, paramY);
      return null;
    }
    paramT = this.cache.put(paramT, paramY);
    if (paramY != null) {
      this.currentSize += getSize(paramY);
    }
    if (paramT != null) {
      this.currentSize -= getSize(paramT);
    }
    evict();
    return paramT;
  }
  
  public Y remove(T paramT)
  {
    paramT = this.cache.remove(paramT);
    if (paramT != null) {
      this.currentSize -= getSize(paramT);
    }
    return paramT;
  }
  
  public void setSizeMultiplier(float paramFloat)
  {
    if (paramFloat < 0.0F) {
      throw new IllegalArgumentException("Multiplier must be >= 0");
    }
    this.maxSize = Math.round(this.initialMaxSize * paramFloat);
    evict();
  }
  
  protected void trimToSize(int paramInt)
  {
    while (this.currentSize > paramInt)
    {
      Object localObject2 = (Map.Entry)this.cache.entrySet().iterator().next();
      Object localObject1 = ((Map.Entry)localObject2).getValue();
      this.currentSize -= getSize(localObject1);
      localObject2 = ((Map.Entry)localObject2).getKey();
      this.cache.remove(localObject2);
      onItemEvicted(localObject2, localObject1);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\util\LruCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */