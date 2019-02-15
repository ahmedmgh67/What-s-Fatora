package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.dynamic.zzg.zza;

public final class zzaj
  extends zzg<zzy>
{
  private static final zzaj zzaFl = new zzaj();
  
  private zzaj()
  {
    super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
  }
  
  public static View zzd(Context paramContext, int paramInt1, int paramInt2)
    throws zzg.zza
  {
    return zzaFl.zze(paramContext, paramInt1, paramInt2);
  }
  
  private View zze(Context paramContext, int paramInt1, int paramInt2)
    throws zzg.zza
  {
    try
    {
      zzah localzzah = new zzah(paramInt1, paramInt2, null);
      zzd localzzd = zze.zzA(paramContext);
      paramContext = (View)zze.zzE(((zzy)zzaT(paramContext)).zza(localzzd, localzzah));
      return paramContext;
    }
    catch (Exception paramContext)
    {
      throw new zzg.zza(64 + "Could not get button with size " + paramInt1 + " and color " + paramInt2, paramContext);
    }
  }
  
  public zzy zzby(IBinder paramIBinder)
  {
    return zzy.zza.zzbx(paramIBinder);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\common\internal\zzaj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */