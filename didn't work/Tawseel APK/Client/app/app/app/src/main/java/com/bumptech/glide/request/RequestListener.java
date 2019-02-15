package com.bumptech.glide.request;

import com.bumptech.glide.request.target.Target;

public abstract interface RequestListener<T, R>
{
  public abstract boolean onException(Exception paramException, T paramT, Target<R> paramTarget, boolean paramBoolean);
  
  public abstract boolean onResourceReady(R paramR, T paramT, Target<R> paramTarget, boolean paramBoolean1, boolean paramBoolean2);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\request\RequestListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */