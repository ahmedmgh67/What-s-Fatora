package com.google.android.gms.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.zzc;

public class zzaaq<O extends Api.ApiOptions>
  extends zzaah
{
  private final zzc<O> zzaBl;
  
  public zzaaq(zzc<O> paramzzc)
  {
    super("Method is not supported by connectionless client. APIs supporting connectionless client must not call this method.");
    this.zzaBl = paramzzc;
  }
  
  public Looper getLooper()
  {
    return this.zzaBl.getLooper();
  }
  
  public <A extends Api.zzb, R extends Result, T extends zzzv.zza<R, A>> T zza(@NonNull T paramT)
  {
    return this.zzaBl.doRead(paramT);
  }
  
  public void zza(zzabp paramzzabp) {}
  
  public <A extends Api.zzb, T extends zzzv.zza<? extends Result, A>> T zzb(@NonNull T paramT)
  {
    return this.zzaBl.doWrite(paramT);
  }
  
  public void zzb(zzabp paramzzabp) {}
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzaaq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */