package com.bumptech.glide.request;

import com.bumptech.glide.load.engine.Resource;

public abstract interface ResourceCallback
{
  public abstract void onException(Exception paramException);
  
  public abstract void onResourceReady(Resource<?> paramResource);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\request\ResourceCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */