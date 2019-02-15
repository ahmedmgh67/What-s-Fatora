package com.google.i18n.phonenumbers;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class RegexCache
{
  private LRUCache<String, Pattern> cache;
  
  public RegexCache(int paramInt)
  {
    this.cache = new LRUCache(paramInt);
  }
  
  boolean containsRegex(String paramString)
  {
    return this.cache.containsKey(paramString);
  }
  
  public Pattern getPatternForRegex(String paramString)
  {
    Pattern localPattern2 = (Pattern)this.cache.get(paramString);
    Pattern localPattern1 = localPattern2;
    if (localPattern2 == null)
    {
      localPattern1 = Pattern.compile(paramString);
      this.cache.put(paramString, localPattern1);
    }
    return localPattern1;
  }
  
  private static class LRUCache<K, V>
  {
    private LinkedHashMap<K, V> map;
    private int size;
    
    public LRUCache(int paramInt)
    {
      this.size = paramInt;
      this.map = new LinkedHashMap(paramInt * 4 / 3 + 1, 0.75F, true)
      {
        protected boolean removeEldestEntry(Map.Entry<K, V> paramAnonymousEntry)
        {
          return size() > RegexCache.LRUCache.this.size;
        }
      };
    }
    
    public boolean containsKey(K paramK)
    {
      try
      {
        boolean bool = this.map.containsKey(paramK);
        return bool;
      }
      finally
      {
        paramK = finally;
        throw paramK;
      }
    }
    
    public V get(K paramK)
    {
      try
      {
        paramK = this.map.get(paramK);
        return paramK;
      }
      finally
      {
        paramK = finally;
        throw paramK;
      }
    }
    
    public void put(K paramK, V paramV)
    {
      try
      {
        this.map.put(paramK, paramV);
        return;
      }
      finally
      {
        paramK = finally;
        throw paramK;
      }
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\i18n\phonenumbers\RegexCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */