package com.google.firebase.storage;

import android.support.annotation.NonNull;
import com.google.android.gms.internal.zzbqw;
import com.google.android.gms.tasks.TaskCompletionSource;

class zzf
  implements Runnable
{
  private final StorageReference zzcki;
  private final TaskCompletionSource<StorageMetadata> zzckj;
  private zzbqw zzckk;
  private StorageMetadata zzcku = null;
  private final StorageMetadata zzclI;
  
  public zzf(@NonNull StorageReference paramStorageReference, @NonNull TaskCompletionSource<StorageMetadata> paramTaskCompletionSource, @NonNull StorageMetadata paramStorageMetadata)
  {
    this.zzcki = paramStorageReference;
    this.zzckj = paramTaskCompletionSource;
    this.zzclI = paramStorageMetadata;
    this.zzckk = new zzbqw(this.zzcki.getApp(), this.zzcki.getStorage().getMaxOperationRetryTimeMillis());
  }
  
  /* Error */
  public void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 26	com/google/firebase/storage/zzf:zzcki	Lcom/google/firebase/storage/StorageReference;
    //   4: invokevirtual 66	com/google/firebase/storage/StorageReference:zzaaN	()Lcom/google/android/gms/internal/zzbre;
    //   7: aload_0
    //   8: getfield 26	com/google/firebase/storage/zzf:zzcki	Lcom/google/firebase/storage/StorageReference;
    //   11: invokevirtual 70	com/google/firebase/storage/StorageReference:zzaaO	()Landroid/net/Uri;
    //   14: aload_0
    //   15: getfield 30	com/google/firebase/storage/zzf:zzclI	Lcom/google/firebase/storage/StorageMetadata;
    //   18: invokevirtual 76	com/google/firebase/storage/StorageMetadata:zzaaM	()Lorg/json/JSONObject;
    //   21: invokevirtual 82	com/google/android/gms/internal/zzbre:zza	(Landroid/net/Uri;Lorg/json/JSONObject;)Lcom/google/android/gms/internal/zzbrf;
    //   24: astore_2
    //   25: aload_0
    //   26: getfield 53	com/google/firebase/storage/zzf:zzckk	Lcom/google/android/gms/internal/zzbqw;
    //   29: aload_2
    //   30: invokevirtual 86	com/google/android/gms/internal/zzbqw:zzd	(Lcom/google/android/gms/internal/zzbrf;)V
    //   33: aload_2
    //   34: invokevirtual 92	com/google/android/gms/internal/zzbrf:zzabn	()Z
    //   37: ifeq +25 -> 62
    //   40: aload_0
    //   41: new 94	com/google/firebase/storage/StorageMetadata$Builder
    //   44: dup
    //   45: aload_2
    //   46: invokevirtual 97	com/google/android/gms/internal/zzbrf:zzabq	()Lorg/json/JSONObject;
    //   49: aload_0
    //   50: getfield 26	com/google/firebase/storage/zzf:zzcki	Lcom/google/firebase/storage/StorageReference;
    //   53: invokespecial 100	com/google/firebase/storage/StorageMetadata$Builder:<init>	(Lorg/json/JSONObject;Lcom/google/firebase/storage/StorageReference;)V
    //   56: invokevirtual 104	com/google/firebase/storage/StorageMetadata$Builder:build	()Lcom/google/firebase/storage/StorageMetadata;
    //   59: putfield 24	com/google/firebase/storage/zzf:zzcku	Lcom/google/firebase/storage/StorageMetadata;
    //   62: aload_0
    //   63: getfield 28	com/google/firebase/storage/zzf:zzckj	Lcom/google/android/gms/tasks/TaskCompletionSource;
    //   66: ifnull +15 -> 81
    //   69: aload_2
    //   70: aload_0
    //   71: getfield 28	com/google/firebase/storage/zzf:zzckj	Lcom/google/android/gms/tasks/TaskCompletionSource;
    //   74: aload_0
    //   75: getfield 24	com/google/firebase/storage/zzf:zzcku	Lcom/google/firebase/storage/StorageMetadata;
    //   78: invokevirtual 107	com/google/android/gms/internal/zzbrf:zza	(Lcom/google/android/gms/tasks/TaskCompletionSource;Ljava/lang/Object;)V
    //   81: return
    //   82: astore_1
    //   83: ldc 109
    //   85: ldc 111
    //   87: aload_1
    //   88: invokestatic 117	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   91: pop
    //   92: aload_0
    //   93: getfield 28	com/google/firebase/storage/zzf:zzckj	Lcom/google/android/gms/tasks/TaskCompletionSource;
    //   96: aload_1
    //   97: invokestatic 123	com/google/firebase/storage/StorageException:fromException	(Ljava/lang/Throwable;)Lcom/google/firebase/storage/StorageException;
    //   100: invokevirtual 129	com/google/android/gms/tasks/TaskCompletionSource:setException	(Ljava/lang/Exception;)V
    //   103: return
    //   104: astore_1
    //   105: aload_2
    //   106: invokevirtual 133	com/google/android/gms/internal/zzbrf:zzabk	()Ljava/lang/String;
    //   109: invokestatic 139	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   112: astore_2
    //   113: aload_2
    //   114: invokevirtual 143	java/lang/String:length	()I
    //   117: ifeq +30 -> 147
    //   120: ldc -111
    //   122: aload_2
    //   123: invokevirtual 149	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
    //   126: astore_2
    //   127: ldc 109
    //   129: aload_2
    //   130: aload_1
    //   131: invokestatic 117	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   134: pop
    //   135: aload_0
    //   136: getfield 28	com/google/firebase/storage/zzf:zzckj	Lcom/google/android/gms/tasks/TaskCompletionSource;
    //   139: aload_1
    //   140: invokestatic 123	com/google/firebase/storage/StorageException:fromException	(Ljava/lang/Throwable;)Lcom/google/firebase/storage/StorageException;
    //   143: invokevirtual 129	com/google/android/gms/tasks/TaskCompletionSource:setException	(Ljava/lang/Exception;)V
    //   146: return
    //   147: new 135	java/lang/String
    //   150: dup
    //   151: ldc -111
    //   153: invokespecial 152	java/lang/String:<init>	(Ljava/lang/String;)V
    //   156: astore_2
    //   157: goto -30 -> 127
    //   160: astore_1
    //   161: goto -56 -> 105
    //   164: astore_1
    //   165: goto -82 -> 83
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	168	0	this	zzf
    //   82	15	1	localRemoteException1	android.os.RemoteException
    //   104	36	1	localRemoteException2	android.os.RemoteException
    //   160	1	1	localJSONException1	org.json.JSONException
    //   164	1	1	localJSONException2	org.json.JSONException
    //   24	133	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   0	25	82	android/os/RemoteException
    //   40	62	104	android/os/RemoteException
    //   40	62	160	org/json/JSONException
    //   0	25	164	org/json/JSONException
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\storage\zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */