package com.bumptech.glide.load.data;

import com.bumptech.glide.Priority;

public abstract interface DataFetcher<T>
{
  public abstract void cancel();
  
  public abstract void cleanup();
  
  public abstract String getId();
  
  public abstract T loadData(Priority paramPriority)
    throws Exception;
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\data\DataFetcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */