package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzh;
import com.google.firebase.FirebaseApp;
import java.util.Random;

public class zzbqw
{
  static zze zzaiV = zzh.zzyv();
  private static Random zzbQY = new Random();
  static zzbqy zzclY = new zzbqz();
  private volatile boolean zzJ;
  private FirebaseApp zzciR;
  private long zzclZ;
  
  public zzbqw(FirebaseApp paramFirebaseApp, long paramLong)
  {
    this.zzciR = paramFirebaseApp;
    this.zzclZ = paramLong;
  }
  
  public void cancel()
  {
    this.zzJ = true;
  }
  
  public void reset()
  {
    this.zzJ = false;
  }
  
  public void zza(@NonNull zzbrf paramzzbrf, boolean paramBoolean)
  {
    zzac.zzw(paramzzbrf);
    long l1 = zzaiV.elapsedRealtime();
    long l2 = this.zzclZ;
    int j;
    if (paramBoolean)
    {
      paramzzbrf.zza(zzbrb.zzi(this.zzciR), this.zzciR.getApplicationContext());
      j = 1000;
    }
    for (;;)
    {
      if ((zzaiV.elapsedRealtime() + j <= l1 + l2) && (!paramzzbrf.zzabn()) && (zzqa(paramzzbrf.getResultCode()))) {}
      int i;
      for (;;)
      {
        try
        {
          zzclY.zzqb(zzbQY.nextInt(250) + j);
          i = j;
          if (j < 30000)
          {
            if (paramzzbrf.getResultCode() == -2) {
              break label178;
            }
            i = j * 2;
            Log.w("ExponenentialBackoff", "network error occurred, backing off/sleeping.");
          }
          if (!this.zzJ) {
            break label193;
          }
          return;
        }
        catch (InterruptedException paramzzbrf)
        {
          Log.w("ExponenentialBackoff", "thread interrupted during exponential backoff.");
          Thread.currentThread().interrupt();
          return;
        }
        paramzzbrf.zzjO(zzbrb.zzi(this.zzciR));
        break;
        label178:
        Log.w("ExponenentialBackoff", "network unavailable, sleeping.");
        i = 1000;
      }
      label193:
      paramzzbrf.reset();
      if (paramBoolean)
      {
        paramzzbrf.zza(zzbrb.zzi(this.zzciR), this.zzciR.getApplicationContext());
        j = i;
      }
      else
      {
        paramzzbrf.zzjO(zzbrb.zzi(this.zzciR));
        j = i;
      }
    }
  }
  
  public void zzd(@NonNull zzbrf paramzzbrf)
  {
    zza(paramzzbrf, true);
  }
  
  public boolean zzqa(int paramInt)
  {
    return ((paramInt >= 500) && (paramInt < 600)) || (paramInt == -2) || (paramInt == 429) || (paramInt == 408);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbqw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */