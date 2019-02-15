package com.google.android.gms.internal;

import android.app.Activity;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class zzbqv
{
  private static final zzbqv zzclU = new zzbqv();
  private final Map<Object, zza> zzclV = new HashMap();
  private final Object zzclW = new Object();
  
  @NonNull
  public static zzbqv zzabf()
  {
    return zzclU;
  }
  
  public void zza(@NonNull Activity paramActivity, @NonNull Object paramObject, @NonNull Runnable paramRunnable)
  {
    synchronized (this.zzclW)
    {
      paramRunnable = new zza(paramActivity, paramRunnable, paramObject);
      zzb.zzx(paramActivity).zza(paramRunnable);
      this.zzclV.put(paramObject, paramRunnable);
      return;
    }
  }
  
  public void zzaH(@NonNull Object paramObject)
  {
    synchronized (this.zzclW)
    {
      paramObject = (zza)this.zzclV.get(paramObject);
      if (paramObject != null) {
        zzb.zzx(((zza)paramObject).getActivity()).zzb((zza)paramObject);
      }
      return;
    }
  }
  
  private static class zza
  {
    @NonNull
    private final Activity mActivity;
    @NonNull
    private final Object zzclX;
    @NonNull
    private final Runnable zzv;
    
    public zza(@NonNull Activity paramActivity, @NonNull Runnable paramRunnable, @NonNull Object paramObject)
    {
      this.mActivity = paramActivity;
      this.zzv = paramRunnable;
      this.zzclX = paramObject;
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof zza)) {}
      do
      {
        return false;
        paramObject = (zza)paramObject;
      } while ((!((zza)paramObject).zzclX.equals(this.zzclX)) || (((zza)paramObject).zzv != this.zzv) || (((zza)paramObject).mActivity != this.mActivity));
      return true;
    }
    
    @NonNull
    public Activity getActivity()
    {
      return this.mActivity;
    }
    
    public int hashCode()
    {
      return this.zzclX.hashCode();
    }
    
    @NonNull
    public Runnable zzTj()
    {
      return this.zzv;
    }
    
    @NonNull
    public Object zzabg()
    {
      return this.zzclX;
    }
  }
  
  private static class zzb
    extends zzaaw
  {
    private final List<zzbqv.zza> mListeners = new ArrayList();
    
    private zzb(zzaax paramzzaax)
    {
      super();
      this.zzaBs.zza("StorageOnStopCallback", this);
    }
    
    public static zzb zzx(Activity paramActivity)
    {
      zzaax localzzaax = zzc(new zzaav(paramActivity));
      zzb localzzb = (zzb)localzzaax.zza("StorageOnStopCallback", zzb.class);
      paramActivity = localzzb;
      if (localzzb == null) {
        paramActivity = new zzb(localzzaax);
      }
      return paramActivity;
    }
    
    @MainThread
    public void onStop()
    {
      synchronized (this.mListeners)
      {
        Object localObject2 = new ArrayList(this.mListeners);
        this.mListeners.clear();
        ??? = ((ArrayList)localObject2).iterator();
        while (((Iterator)???).hasNext())
        {
          localObject2 = (zzbqv.zza)((Iterator)???).next();
          if (localObject2 != null)
          {
            Log.d("StorageOnStopCallback", "removing subscription from activity.");
            ((zzbqv.zza)localObject2).zzTj().run();
            zzbqv.zzabf().zzaH(((zzbqv.zza)localObject2).zzabg());
          }
        }
      }
    }
    
    public void zza(zzbqv.zza paramzza)
    {
      synchronized (this.mListeners)
      {
        this.mListeners.add(paramzza);
        return;
      }
    }
    
    public void zzb(zzbqv.zza paramzza)
    {
      synchronized (this.mListeners)
      {
        this.mListeners.remove(paramzza);
        return;
      }
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbqv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */