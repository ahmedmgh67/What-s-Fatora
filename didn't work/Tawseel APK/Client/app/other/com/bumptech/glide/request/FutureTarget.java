package com.bumptech.glide.request;

import com.bumptech.glide.request.target.Target;
import java.util.concurrent.Future;

public abstract interface FutureTarget<R>
  extends Future<R>, Target<R>
{
  public abstract void clear();
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\request\FutureTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */