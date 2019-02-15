package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzac;

public final class zzaaz<L>
{
  private volatile L mListener;
  private final zza zzaBy;
  private final zzb<L> zzaBz;
  
  zzaaz(@NonNull Looper paramLooper, @NonNull L paramL, @NonNull String paramString)
  {
    this.zzaBy = new zza(paramLooper);
    this.mListener = zzac.zzb(paramL, "Listener must not be null");
    this.zzaBz = new zzb(paramL, zzac.zzdv(paramString));
  }
  
  public void clear()
  {
    this.mListener = null;
  }
  
  public void zza(zzc<? super L> paramzzc)
  {
    zzac.zzb(paramzzc, "Notifier must not be null");
    paramzzc = this.zzaBy.obtainMessage(1, paramzzc);
    this.zzaBy.sendMessage(paramzzc);
  }
  
  void zzb(zzc<? super L> paramzzc)
  {
    Object localObject = this.mListener;
    if (localObject == null)
    {
      paramzzc.zzvy();
      return;
    }
    try
    {
      paramzzc.zzs(localObject);
      return;
    }
    catch (RuntimeException localRuntimeException)
    {
      paramzzc.zzvy();
      throw localRuntimeException;
    }
  }
  
  @NonNull
  public zzb<L> zzwp()
  {
    return this.zzaBz;
  }
  
  private final class zza
    extends Handler
  {
    public zza(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      boolean bool = true;
      if (paramMessage.what == 1) {}
      for (;;)
      {
        zzac.zzas(bool);
        zzaaz.this.zzb((zzaaz.zzc)paramMessage.obj);
        return;
        bool = false;
      }
    }
  }
  
  public static final class zzb<L>
  {
    private final L mListener;
    private final String zzaBB;
    
    zzb(L paramL, String paramString)
    {
      this.mListener = paramL;
      this.zzaBB = paramString;
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if (!(paramObject instanceof zzb)) {
          return false;
        }
        paramObject = (zzb)paramObject;
      } while ((this.mListener == ((zzb)paramObject).mListener) && (this.zzaBB.equals(((zzb)paramObject).zzaBB)));
      return false;
    }
    
    public int hashCode()
    {
      return System.identityHashCode(this.mListener) * 31 + this.zzaBB.hashCode();
    }
  }
  
  public static abstract interface zzc<L>
  {
    public abstract void zzs(L paramL);
    
    public abstract void zzvy();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzaaz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */