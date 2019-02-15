package com.google.android.gms.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzac;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

public class zzaba
{
  private final Set<zzaaz<?>> zzarH = Collections.newSetFromMap(new WeakHashMap());
  
  public static <L> zzaaz.zzb<L> zza(@NonNull L paramL, @NonNull String paramString)
  {
    zzac.zzb(paramL, "Listener must not be null");
    zzac.zzb(paramString, "Listener type must not be null");
    zzac.zzh(paramString, "Listener type must not be empty");
    return new zzaaz.zzb(paramL, paramString);
  }
  
  public static <L> zzaaz<L> zzb(@NonNull L paramL, @NonNull Looper paramLooper, @NonNull String paramString)
  {
    zzac.zzb(paramL, "Listener must not be null");
    zzac.zzb(paramLooper, "Looper must not be null");
    zzac.zzb(paramString, "Listener type must not be null");
    return new zzaaz(paramLooper, paramL, paramString);
  }
  
  public void release()
  {
    Iterator localIterator = this.zzarH.iterator();
    while (localIterator.hasNext()) {
      ((zzaaz)localIterator.next()).clear();
    }
    this.zzarH.clear();
  }
  
  public <L> zzaaz<L> zza(@NonNull L paramL, @NonNull Looper paramLooper, @NonNull String paramString)
  {
    paramL = zzb(paramL, paramLooper, paramString);
    this.zzarH.add(paramL);
    return paramL;
  }
  
  public <L> zzaaz<L> zzb(@NonNull L paramL, Looper paramLooper)
  {
    return zza(paramL, paramLooper, "NO_TYPE");
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzaba.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */