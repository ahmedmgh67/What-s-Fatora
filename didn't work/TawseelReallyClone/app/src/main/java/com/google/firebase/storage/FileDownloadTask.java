package com.google.firebase.storage;

import android.net.Uri;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.internal.zzbqw;
import com.google.android.gms.internal.zzbre;
import com.google.android.gms.internal.zzbrf;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileDownloadTask
  extends StorageTask<TaskSnapshot>
{
  private int mResultCode;
  private long zzaKG = -1L;
  private volatile Exception zzbLK = null;
  private StorageReference zzcki;
  private zzbqw zzckk;
  private final Uri zzckl;
  private long zzckm;
  private String zzckn = null;
  private long zzcko = 0L;
  
  FileDownloadTask(@NonNull StorageReference paramStorageReference, @NonNull Uri paramUri)
  {
    this.zzcki = paramStorageReference;
    this.zzckl = paramUri;
    this.zzckk = new zzbqw(this.zzcki.getStorage().getApp(), this.zzcki.getStorage().getMaxDownloadRetryTimeMillis());
  }
  
  private boolean zzpW(int paramInt)
  {
    return (paramInt == 308) || ((paramInt >= 200) && (paramInt < 300));
  }
  
  @NonNull
  StorageReference getStorage()
  {
    return this.zzcki;
  }
  
  long getTotalBytes()
  {
    return this.zzaKG;
  }
  
  protected void onCanceled()
  {
    this.zzckk.cancel();
  }
  
  void run()
  {
    if (!zzf(4, false)) {}
    for (;;)
    {
      return;
      this.zzckk.reset();
      zzbrf localzzbrf;
      Object localObject2;
      for (;;)
      {
        try
        {
          localzzbrf = this.zzcki.zzaaN().zza(this.zzcki.zzaaO(), this.zzcko);
          this.zzckk.zza(localzzbrf, false);
          this.mResultCode = localzzbrf.getResultCode();
          if (localzzbrf.getException() != null)
          {
            Object localObject1 = localzzbrf.getException();
            this.zzbLK = ((Exception)localObject1);
            if ((!zzpW(this.mResultCode)) || (this.zzbLK != null) || (zzaaQ() != 4)) {
              break label213;
            }
            i = 1;
            if (i == 0) {
              break label323;
            }
            this.zzaKG = localzzbrf.zzabo();
            localObject1 = localzzbrf.zzjP("ETag");
            if ((TextUtils.isEmpty((CharSequence)localObject1)) || (this.zzckn == null) || (this.zzckn.equals(localObject1))) {
              break;
            }
            Log.w("FileDownloadTask", "The file at the server has changed.  Restarting from the beginning.");
            this.zzcko = 0L;
            this.zzckn = null;
            localzzbrf.zzabh();
            schedule();
            return;
          }
        }
        catch (RemoteException localRemoteException)
        {
          Log.e("FileDownloadTask", "Unable to create firebase storage network request.", localRemoteException);
          this.zzbLK = localRemoteException;
          zzf(64, false);
          return;
        }
        localObject2 = this.zzbLK;
        continue;
        label213:
        i = 0;
      }
      this.zzckn = ((String)localObject2);
      InputStream localInputStream = localzzbrf.getStream();
      Object localObject4;
      if (localInputStream != null)
      {
        try
        {
          localObject4 = new File(this.zzckl.getPath());
          if (((File)localObject4).exists()) {
            break label414;
          }
          if (this.zzcko <= 0L) {
            break label376;
          }
          localObject2 = String.valueOf(((File)localObject4).getAbsolutePath());
          if (((String)localObject2).length() == 0) {
            break label363;
          }
          localObject2 = "The file downloading to has been deleted:".concat((String)localObject2);
          Log.e("FileDownloadTask", (String)localObject2);
          throw new IllegalStateException("expected a file to resume from.");
        }
        catch (Exception localException)
        {
          Log.e("FileDownloadTask", "Exception occurred during file download", localException);
          this.zzbLK = localException;
        }
        label323:
        localzzbrf.zzabh();
        if ((i == 0) || (this.zzbLK != null) || (zzaaQ() != 4)) {
          break label582;
        }
      }
      label363:
      label376:
      label407:
      label414:
      label446:
      label540:
      label553:
      label582:
      for (int i = 1;; i = 0)
      {
        if (i == 0) {
          break label587;
        }
        zzf(128, false);
        return;
        localObject3 = new String("The file downloading to has been deleted:");
        break;
        if (!((File)localObject4).createNewFile())
        {
          localObject3 = String.valueOf(((File)localObject4).getAbsolutePath());
          if (((String)localObject3).length() != 0)
          {
            localObject3 = "unable to create file:".concat((String)localObject3);
            Log.w("FileDownloadTask", (String)localObject3);
          }
        }
        else
        {
          if (this.zzcko <= 0L) {
            break label553;
          }
          localObject3 = String.valueOf(((File)localObject4).getAbsolutePath());
          if (((String)localObject3).length() == 0) {
            break label540;
          }
          localObject3 = "Resuming download file ".concat((String)localObject3);
          Log.d("FileDownloadTask", (String)localObject3);
        }
        for (localObject3 = new FileOutputStream((File)localObject4, true);; localObject3 = new FileOutputStream((File)localObject4))
        {
          localObject4 = new byte[262144];
          do
          {
            int j = localInputStream.read((byte[])localObject4);
            if (j == -1) {
              break;
            }
            ((OutputStream)localObject3).write((byte[])localObject4, 0, j);
            this.zzckm += j;
          } while (zzf(4, false));
          ((OutputStream)localObject3).flush();
          ((OutputStream)localObject3).close();
          localInputStream.close();
          break;
          localObject3 = new String("unable to create file:");
          break label407;
          localObject3 = new String("Resuming download file ");
          break label446;
        }
        this.zzbLK = new IllegalStateException("Unable to open Firebase Storage stream.");
        break label323;
      }
      label587:
      Object localObject3 = new File(this.zzckl.getPath());
      if (((File)localObject3).exists()) {}
      for (this.zzcko = ((File)localObject3).length(); zzaaQ() == 8; this.zzcko = 0L)
      {
        zzf(16, false);
        return;
      }
      if (zzaaQ() == 32) {}
      for (i = 256; !zzf(i, false); i = 64)
      {
        i = zzaaQ();
        Log.w("FileDownloadTask", 62 + "Unable to change download task to final state from " + i);
        return;
      }
    }
  }
  
  protected void schedule()
  {
    zzd.zzaaW().zzv(zzTj());
  }
  
  @NonNull
  TaskSnapshot zzaaJ()
  {
    return new TaskSnapshot(StorageException.fromExceptionAndHttpCode(this.zzbLK, this.mResultCode), this.zzckm);
  }
  
  public class TaskSnapshot
    extends StorageTask<TaskSnapshot>.SnapshotBase
  {
    private final long zzckm;
    
    TaskSnapshot(Exception paramException, long paramLong)
    {
      super(paramException);
      this.zzckm = paramLong;
    }
    
    public long getBytesTransferred()
    {
      return this.zzckm;
    }
    
    public long getTotalByteCount()
    {
      return FileDownloadTask.this.getTotalBytes();
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\storage\FileDownloadTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */