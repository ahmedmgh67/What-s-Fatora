package com.google.android.gms.common.api;

import android.support.annotation.NonNull;

public abstract class TransformedResult<R extends Result>
{
  public abstract void andFinally(@NonNull ResultCallbacks<? super R> paramResultCallbacks);
  
  @NonNull
  public abstract <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> paramResultTransform);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\common\api\TransformedResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */