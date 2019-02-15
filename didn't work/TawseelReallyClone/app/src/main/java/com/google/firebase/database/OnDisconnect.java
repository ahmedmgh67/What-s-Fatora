package com.google.firebase.database;

import com.google.android.gms.internal.zzbmj;
import com.google.android.gms.internal.zzbml;
import com.google.android.gms.internal.zzbmy;
import com.google.android.gms.internal.zzbpe;
import com.google.android.gms.internal.zzbpf;
import com.google.android.gms.internal.zzbpi;
import com.google.android.gms.internal.zzbqd;
import com.google.android.gms.internal.zzbqg;
import com.google.android.gms.internal.zzbqh;
import com.google.android.gms.internal.zzbqi;
import com.google.android.gms.tasks.Task;
import java.util.Map;

public class OnDisconnect
{
  private zzbml zzbXR;
  private zzbmj zzbXY;
  
  OnDisconnect(zzbml paramzzbml, zzbmj paramzzbmj)
  {
    this.zzbXR = paramzzbml;
    this.zzbXY = paramzzbmj;
  }
  
  private Task<Void> zza(final DatabaseReference.CompletionListener paramCompletionListener)
  {
    paramCompletionListener = zzbqg.zzb(paramCompletionListener);
    this.zzbXR.zzs(new Runnable()
    {
      public void run()
      {
        OnDisconnect.zzb(OnDisconnect.this).zza(OnDisconnect.zza(OnDisconnect.this), (DatabaseReference.CompletionListener)paramCompletionListener.zzZZ());
      }
    });
    return (Task)paramCompletionListener.getFirst();
  }
  
  private Task<Void> zza(final Map<String, Object> paramMap, final DatabaseReference.CompletionListener paramCompletionListener)
  {
    final Map localMap = zzbqh.zzc(this.zzbXY, paramMap);
    paramCompletionListener = zzbqg.zzb(paramCompletionListener);
    this.zzbXR.zzs(new Runnable()
    {
      public void run()
      {
        OnDisconnect.zzb(OnDisconnect.this).zza(OnDisconnect.zza(OnDisconnect.this), localMap, (DatabaseReference.CompletionListener)paramCompletionListener.zzZZ(), paramMap);
      }
    });
    return (Task)paramCompletionListener.getFirst();
  }
  
  private Task<Void> zzb(final Object paramObject, final zzbpe paramzzbpe, DatabaseReference.CompletionListener paramCompletionListener)
  {
    zzbqh.zzQ(this.zzbXY);
    zzbmy.zza(this.zzbXY, paramObject);
    paramObject = zzbqi.zzaw(paramObject);
    zzbqh.zzav(paramObject);
    paramObject = zzbpf.zza(paramObject, paramzzbpe);
    paramzzbpe = zzbqg.zzb(paramCompletionListener);
    this.zzbXR.zzs(new Runnable()
    {
      public void run()
      {
        OnDisconnect.zzb(OnDisconnect.this).zzb(OnDisconnect.zza(OnDisconnect.this), paramObject, (DatabaseReference.CompletionListener)paramzzbpe.zzZZ());
      }
    });
    return (Task)paramzzbpe.getFirst();
  }
  
  public Task<Void> cancel()
  {
    return zza(null);
  }
  
  public void cancel(DatabaseReference.CompletionListener paramCompletionListener)
  {
    zza(paramCompletionListener);
  }
  
  public Task<Void> removeValue()
  {
    return setValue(null);
  }
  
  public void removeValue(DatabaseReference.CompletionListener paramCompletionListener)
  {
    setValue(null, paramCompletionListener);
  }
  
  public Task<Void> setValue(Object paramObject)
  {
    return zzb(paramObject, zzbpi.zzZB(), null);
  }
  
  public Task<Void> setValue(Object paramObject, double paramDouble)
  {
    return zzb(paramObject, zzbpi.zzas(Double.valueOf(paramDouble)), null);
  }
  
  public Task<Void> setValue(Object paramObject, String paramString)
  {
    return zzb(paramObject, zzbpi.zzas(paramString), null);
  }
  
  public void setValue(Object paramObject, double paramDouble, DatabaseReference.CompletionListener paramCompletionListener)
  {
    zzb(paramObject, zzbpi.zzas(Double.valueOf(paramDouble)), paramCompletionListener);
  }
  
  public void setValue(Object paramObject, DatabaseReference.CompletionListener paramCompletionListener)
  {
    zzb(paramObject, zzbpi.zzZB(), paramCompletionListener);
  }
  
  public void setValue(Object paramObject, String paramString, DatabaseReference.CompletionListener paramCompletionListener)
  {
    zzb(paramObject, zzbpi.zzas(paramString), paramCompletionListener);
  }
  
  public void setValue(Object paramObject, Map paramMap, DatabaseReference.CompletionListener paramCompletionListener)
  {
    zzb(paramObject, zzbpi.zzas(paramMap), paramCompletionListener);
  }
  
  public Task<Void> updateChildren(Map<String, Object> paramMap)
  {
    return zza(paramMap, null);
  }
  
  public void updateChildren(Map<String, Object> paramMap, DatabaseReference.CompletionListener paramCompletionListener)
  {
    zza(paramMap, paramCompletionListener);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\database\OnDisconnect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */