package com.bumptech.glide.load.model;

import java.util.Collections;
import java.util.Map;

public abstract interface Headers
{
  public static final Headers DEFAULT = new LazyHeaders.Builder().build();
  @Deprecated
  public static final Headers NONE = new Headers()
  {
    public Map<String, String> getHeaders()
    {
      return Collections.emptyMap();
    }
  };
  
  public abstract Map<String, String> getHeaders();
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\model\Headers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */