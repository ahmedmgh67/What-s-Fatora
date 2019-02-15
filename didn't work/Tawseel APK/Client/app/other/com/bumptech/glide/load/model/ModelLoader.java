package com.bumptech.glide.load.model;

import com.bumptech.glide.load.data.DataFetcher;

public abstract interface ModelLoader<T, Y>
{
  public abstract DataFetcher<Y> getResourceFetcher(T paramT, int paramInt1, int paramInt2);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\model\ModelLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */