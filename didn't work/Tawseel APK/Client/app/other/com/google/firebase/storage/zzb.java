package com.google.firebase.storage;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzbqw;
import com.google.android.gms.tasks.TaskCompletionSource;

class zzb
  implements Runnable
{
  private StorageReference zzcki;
  private TaskCompletionSource<StorageMetadata> zzckj;
  private zzbqw zzckk;
  private StorageMetadata zzcku;
  
  public zzb(@NonNull StorageReference paramStorageReference, @NonNull TaskCompletionSource<StorageMetadata> paramTaskCompletionSource)
  {
    zzac.zzw(paramStorageReference);
    zzac.zzw(paramTaskCompletionSource);
    this.zzcki = paramStorageReference;
    this.zzckj = paramTaskCompletionSource;
    this.zzckk = new zzbqw(this.zzcki.getApp(), this.zzcki.getStorage().getMaxOperationRetryTimeMillis());
  }
  
  /* Error */
  public void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 29	com/google/firebase/storage/zzb:zzcki	Lcom/google/firebase/storage/StorageReference;
    //   4: invokevirtual 67	com/google/firebase/storage/StorageReference:zzaaN	()Lcom/google/android/gms/internal/zzbre;
    //   7: aload_0
    //   8: getfield 29	com/google/firebase/storage/zzb:zzcki	Lcom/google/firebase/storage/StorageReference;
    //   11: invokevirtual 71	com/google/firebase/storage/StorageReference:zzaaO	()Landroid/net/Uri;
    //   14: invokevirtual 77	com/google/android/gms/internal/zzbre:zzD	(Landroid/net/Uri;)Lcom/google/android/gms/internal/zzbrf;
    //   17: astore_2
    //   18: aload_0
    //   19: getfield 54	com/google/firebase/storage/zzb:zzckk	Lcom/google/android/gms/internal/zzbqw;
    //   22: aload_2
    //   23: invokevirtual 81	com/google/android/gms/internal/zzbqw:zzd	(Lcom/google/android/gms/internal/zzbrf;)V
    //   26: aload_2
    //   27: invokevirtual 87	com/google/android/gms/internal/zzbrf:zzabn	()Z
    //   30: ifeq +25 -> 55
    //   33: aload_0
    //   34: new 89	com/google/firebase/storage/StorageMetadata$Builder
    //   37: dup
    //   38: aload_2
    //   39: invokevirtual 93	com/google/android/gms/internal/zzbrf:zzabq	()Lorg/json/JSONObject;
    //   42: aload_0
    //   43: getfield 29	com/google/firebase/storage/zzb:zzcki	Lcom/google/firebase/storage/StorageReference;
    //   46: invokespecial 96	com/google/firebase/storage/StorageMetadata$Builder:<init>	(Lorg/json/JSONObject;Lcom/google/firebase/storage/StorageReference;)V
    //   49: invokevirtual 100	com/google/firebase/storage/StorageMetadata$Builder:build	()Lcom/google/firebase/storage/StorageMetadata;
    //   52: putfield 102	com/google/firebase/storage/zzb:zzcku	Lcom/google/firebase/storage/StorageMetadata;
    //   55: aload_0
    //   56: getfield 31	com/google/firebase/storage/zzb:zzckj	Lcom/google/android/gms/tasks/TaskCompletionSource;
    //   59: ifnull +15 -> 74
    //   62: aload_2
    //   63: aload_0
    //   64: getfield 31	com/google/firebase/storage/zzb:zzckj	Lcom/google/android/gms/tasks/TaskCompletionSource;
    //   67: aload_0
    //   68: getfield 102	com/google/firebase/storage/zzb:zzcku	Lcom/google/firebase/storage/StorageMetadata;
    //   71: invokevirtual 106	com/google/android/gms/internal/zzbrf:zza	(Lcom/google/android/gms/tasks/TaskCompletionSource;Ljava/lang/Object;)V
    //   74: return
    //   75: astore_1
    //   76: ldc 108
    //   78: ldc 110
    //   80: aload_1
    //   81: invokestatic 116	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   84: pop
    //   85: aload_0
    //   86: getfield 31	com/google/firebase/storage/zzb:zzckj	Lcom/google/android/gms/tasks/TaskCompletionSource;
    //   89: aload_1
    //   90: invokestatic 122	com/google/firebase/storage/StorageException:fromException	(Ljava/lang/Throwable;)Lcom/google/firebase/storage/StorageException;
    //   93: invokevirtual 128	com/google/android/gms/tasks/TaskCompletionSource:setException	(Ljava/lang/Exception;)V
    //   96: return
    //   97: astore_1
    //   98: aload_2
    //   99: invokevirtual 132	com/google/android/gms/internal/zzbrf:zzabk	()Ljava/lang/String;
    //   102: invokestatic 138	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   105: astore_2
    //   106: aload_2
    //   107: invokevirtual 142	java/lang/String:length	()I
    //   110: ifeq +30 -> 140
    //   113: ldc -112
    //   115: aload_2
    //   116: invokevirtual 148	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
    //   119: astore_2
    //   120: ldc 108
    //   122: aload_2
    //   123: aload_1
    //   124: invokestatic 116	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   127: pop
    //   128: aload_0
    //   129: getfield 31	com/google/firebase/storage/zzb:zzckj	Lcom/google/android/gms/tasks/TaskCompletionSource;
    //   132: aload_1
    //   133: invokestatic 122	com/google/firebase/storage/StorageException:fromException	(Ljava/lang/Throwable;)Lcom/google/firebase/storage/StorageException;
    //   136: invokevirtual 128	com/google/android/gms/tasks/TaskCompletionSource:setException	(Ljava/lang/Exception;)V
    //   139: return
    //   140: new 134	java/lang/String
    //   143: dup
    //   144: ldc -112
    //   146: invokespecial 151	java/lang/String:<init>	(Ljava/lang/String;)V
    //   149: astore_2
    //   150: goto -30 -> 120
    //   153: astore_1
    //   154: goto -56 -> 98
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	157	0	this	zzb
    //   75	15	1	localRemoteException1	android.os.RemoteException
    //   97	36	1	localRemoteException2	android.os.RemoteException
    //   153	1	1	localJSONException	org.json.JSONException
    //   17	133	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   0	18	75	android/os/RemoteException
    //   33	55	97	android/os/RemoteException
    //   33	55	153	org/json/JSONException
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\storage\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */