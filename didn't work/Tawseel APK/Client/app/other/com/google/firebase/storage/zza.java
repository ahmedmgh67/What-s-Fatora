package com.google.firebase.storage;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzbqw;
import com.google.android.gms.internal.zzbre;
import com.google.android.gms.internal.zzbrf;
import com.google.android.gms.tasks.TaskCompletionSource;

class zza
  implements Runnable
{
  private StorageReference zzcki;
  private TaskCompletionSource<Void> zzckj;
  private zzbqw zzckk;
  
  public zza(@NonNull StorageReference paramStorageReference, @NonNull TaskCompletionSource<Void> paramTaskCompletionSource)
  {
    zzac.zzw(paramStorageReference);
    zzac.zzw(paramTaskCompletionSource);
    this.zzcki = paramStorageReference;
    this.zzckj = paramTaskCompletionSource;
    this.zzckk = new zzbqw(this.zzcki.getStorage().getApp(), this.zzcki.getStorage().getMaxOperationRetryTimeMillis());
  }
  
  public void run()
  {
    try
    {
      zzbrf localzzbrf = this.zzcki.zzaaN().zzC(this.zzcki.zzaaO());
      this.zzckk.zzd(localzzbrf);
      localzzbrf.zza(this.zzckj, null);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("DeleteStorageTask", "Unable to create Firebase Storage network request.", localRemoteException);
      this.zzckj.setException(StorageException.fromException(localRemoteException));
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\storage\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */