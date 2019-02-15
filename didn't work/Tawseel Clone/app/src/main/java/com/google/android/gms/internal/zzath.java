package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzf.zzb;
import com.google.android.gms.common.internal.zzf.zzc;

public class zzath
  extends zzf<zzate>
{
  public zzath(Context paramContext, Looper paramLooper, zzf.zzb paramzzb, zzf.zzc paramzzc)
  {
    super(paramContext, paramLooper, 93, paramzzb, paramzzc, null);
  }
  
  public zzate zzes(IBinder paramIBinder)
  {
    return zzate.zza.zzer(paramIBinder);
  }
  
  @NonNull
  protected String zzeu()
  {
    return "com.google.android.gms.measurement.START";
  }
  
  @NonNull
  protected String zzev()
  {
    return "com.google.android.gms.measurement.internal.IMeasurementService";
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */