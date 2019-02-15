package com.google.firebase.storage;

import android.net.Uri;
import android.net.Uri.Builder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzbqx;
import com.google.android.gms.internal.zzbre;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseApp;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class StorageReference
{
  private final Uri zzckP;
  private final FirebaseStorage zzckQ;
  
  static
  {
    if (!StorageReference.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  StorageReference(@NonNull Uri paramUri, @NonNull FirebaseStorage paramFirebaseStorage)
  {
    if (paramUri != null)
    {
      bool1 = true;
      zzac.zzb(bool1, "storageUri cannot be null");
      if (paramFirebaseStorage == null) {
        break label48;
      }
    }
    label48:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      zzac.zzb(bool1, "FirebaseApp cannot be null");
      this.zzckP = paramUri;
      this.zzckQ = paramFirebaseStorage;
      return;
      bool1 = false;
      break;
    }
  }
  
  @NonNull
  public StorageReference child(@NonNull String paramString)
  {
    if (!TextUtils.isEmpty(paramString)) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zzb(bool, "childName cannot be null or empty");
      paramString = zzbqx.zzjL(paramString);
      try
      {
        Uri localUri = this.zzckP.buildUpon().appendEncodedPath(zzbqx.zzjJ(paramString)).build();
        return new StorageReference(localUri, this.zzckQ);
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        paramString = String.valueOf(paramString);
        if (paramString.length() == 0) {
          break;
        }
      }
    }
    for (paramString = "Unable to create a valid default Uri. ".concat(paramString);; paramString = new String("Unable to create a valid default Uri. "))
    {
      Log.e("StorageReference", paramString, localUnsupportedEncodingException);
      throw new IllegalArgumentException("childName");
    }
  }
  
  public Task<Void> delete()
  {
    TaskCompletionSource localTaskCompletionSource = new TaskCompletionSource();
    zzd.zzaaW().zzt(new zza(this, localTaskCompletionSource));
    return localTaskCompletionSource.getTask();
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof StorageReference)) {
      return false;
    }
    return ((StorageReference)paramObject).toString().equals(toString());
  }
  
  @NonNull
  public List<FileDownloadTask> getActiveDownloadTasks()
  {
    return zzc.zzaaV().zzb(this);
  }
  
  @NonNull
  public List<UploadTask> getActiveUploadTasks()
  {
    return zzc.zzaaV().zza(this);
  }
  
  @NonNull
  FirebaseApp getApp()
  {
    return getStorage().getApp();
  }
  
  @NonNull
  public String getBucket()
  {
    return this.zzckP.getAuthority();
  }
  
  @NonNull
  public Task<byte[]> getBytes(final long paramLong)
  {
    final TaskCompletionSource localTaskCompletionSource = new TaskCompletionSource();
    StreamDownloadTask localStreamDownloadTask = new StreamDownloadTask(this);
    ((StorageTask)localStreamDownloadTask.zza(new StreamDownloadTask.StreamProcessor()
    {
      public void doInBackground(StreamDownloadTask.TaskSnapshot paramAnonymousTaskSnapshot, InputStream paramAnonymousInputStream)
        throws IOException
      {
        int i = 0;
        for (;;)
        {
          byte[] arrayOfByte;
          int j;
          try
          {
            paramAnonymousTaskSnapshot = new ByteArrayOutputStream();
            arrayOfByte = new byte['䀀'];
            j = paramAnonymousInputStream.read(arrayOfByte, 0, 16384);
            if (j == -1) {
              break;
            }
            i += j;
            if (i > paramLong)
            {
              Log.e("StorageReference", "the maximum allowed buffer size was exceeded.");
              throw new IndexOutOfBoundsException("the maximum allowed buffer size was exceeded.");
            }
          }
          finally
          {
            paramAnonymousInputStream.close();
          }
          paramAnonymousTaskSnapshot.write(arrayOfByte, 0, j);
        }
        paramAnonymousTaskSnapshot.flush();
        this.zzckS.setResult(paramAnonymousTaskSnapshot.toByteArray());
        paramAnonymousInputStream.close();
      }
    }).addOnSuccessListener(new OnSuccessListener()
    {
      public void zza(StreamDownloadTask.TaskSnapshot paramAnonymousTaskSnapshot)
      {
        if (!localTaskCompletionSource.getTask().isComplete())
        {
          Log.e("StorageReference", "getBytes 'succeeded', but failed to set a Result.");
          localTaskCompletionSource.setException(StorageException.fromErrorStatus(Status.zzayj));
        }
      }
    })).addOnFailureListener(new OnFailureListener()
    {
      static
      {
        if (!StorageReference.class.desiredAssertionStatus()) {}
        for (boolean bool = true;; bool = false)
        {
          $assertionsDisabled = bool;
          return;
        }
      }
      
      public void onFailure(@NonNull Exception paramAnonymousException)
      {
        paramAnonymousException = StorageException.fromExceptionAndHttpCode(paramAnonymousException, 0);
        assert (paramAnonymousException != null);
        localTaskCompletionSource.setException(paramAnonymousException);
      }
    });
    localStreamDownloadTask.zzaaP();
    return localTaskCompletionSource.getTask();
  }
  
  @NonNull
  public Task<Uri> getDownloadUrl()
  {
    final TaskCompletionSource localTaskCompletionSource = new TaskCompletionSource();
    Task localTask = getMetadata();
    localTask.addOnSuccessListener(new OnSuccessListener()
    {
      public void zzb(StorageMetadata paramAnonymousStorageMetadata)
      {
        localTaskCompletionSource.setResult(paramAnonymousStorageMetadata.getDownloadUrl());
      }
    });
    localTask.addOnFailureListener(new OnFailureListener()
    {
      public void onFailure(@NonNull Exception paramAnonymousException)
      {
        localTaskCompletionSource.setException(paramAnonymousException);
      }
    });
    return localTaskCompletionSource.getTask();
  }
  
  @NonNull
  public FileDownloadTask getFile(@NonNull Uri paramUri)
  {
    paramUri = new FileDownloadTask(this, paramUri);
    paramUri.zzaaP();
    return paramUri;
  }
  
  @NonNull
  public FileDownloadTask getFile(@NonNull File paramFile)
  {
    return getFile(Uri.fromFile(paramFile));
  }
  
  @NonNull
  public Task<StorageMetadata> getMetadata()
  {
    TaskCompletionSource localTaskCompletionSource = new TaskCompletionSource();
    zzd.zzaaW().zzt(new zzb(this, localTaskCompletionSource));
    return localTaskCompletionSource.getTask();
  }
  
  @NonNull
  public String getName()
  {
    String str2 = this.zzckP.getPath();
    assert (str2 != null);
    int i = str2.lastIndexOf('/');
    String str1 = str2;
    if (i != -1) {
      str1 = str2.substring(i + 1);
    }
    return str1;
  }
  
  @Nullable
  public StorageReference getParent()
  {
    String str = this.zzckP.getPath();
    if ((str == null) || (str.equals("/"))) {
      return null;
    }
    int i = str.lastIndexOf('/');
    if (i == -1) {}
    for (str = "/";; str = str.substring(0, i)) {
      return new StorageReference(this.zzckP.buildUpon().path(str).build(), this.zzckQ);
    }
  }
  
  @NonNull
  public String getPath()
  {
    String str = this.zzckP.getPath();
    assert (str != null);
    return str;
  }
  
  @NonNull
  public StorageReference getRoot()
  {
    return new StorageReference(this.zzckP.buildUpon().path("").build(), this.zzckQ);
  }
  
  @NonNull
  public FirebaseStorage getStorage()
  {
    return this.zzckQ;
  }
  
  @NonNull
  public StreamDownloadTask getStream()
  {
    StreamDownloadTask localStreamDownloadTask = new StreamDownloadTask(this);
    localStreamDownloadTask.zzaaP();
    return localStreamDownloadTask;
  }
  
  @NonNull
  public StreamDownloadTask getStream(@NonNull StreamDownloadTask.StreamProcessor paramStreamProcessor)
  {
    StreamDownloadTask localStreamDownloadTask = new StreamDownloadTask(this);
    localStreamDownloadTask.zza(paramStreamProcessor);
    localStreamDownloadTask.zzaaP();
    return localStreamDownloadTask;
  }
  
  public int hashCode()
  {
    return toString().hashCode();
  }
  
  @NonNull
  public UploadTask putBytes(@NonNull byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte != null) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zzb(bool, "bytes cannot be null");
      paramArrayOfByte = new UploadTask(this, null, paramArrayOfByte);
      paramArrayOfByte.zzaaP();
      return paramArrayOfByte;
    }
  }
  
  @NonNull
  public UploadTask putBytes(@NonNull byte[] paramArrayOfByte, @NonNull StorageMetadata paramStorageMetadata)
  {
    boolean bool2 = true;
    if (paramArrayOfByte != null)
    {
      bool1 = true;
      zzac.zzb(bool1, "bytes cannot be null");
      if (paramStorageMetadata == null) {
        break label53;
      }
    }
    label53:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      zzac.zzb(bool1, "metadata cannot be null");
      paramArrayOfByte = new UploadTask(this, paramStorageMetadata, paramArrayOfByte);
      paramArrayOfByte.zzaaP();
      return paramArrayOfByte;
      bool1 = false;
      break;
    }
  }
  
  @NonNull
  public UploadTask putFile(@NonNull Uri paramUri)
  {
    if (paramUri != null) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zzb(bool, "uri cannot be null");
      paramUri = new UploadTask(this, null, paramUri, null);
      paramUri.zzaaP();
      return paramUri;
    }
  }
  
  @NonNull
  public UploadTask putFile(@NonNull Uri paramUri, @NonNull StorageMetadata paramStorageMetadata)
  {
    boolean bool2 = true;
    if (paramUri != null)
    {
      bool1 = true;
      zzac.zzb(bool1, "uri cannot be null");
      if (paramStorageMetadata == null) {
        break label54;
      }
    }
    label54:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      zzac.zzb(bool1, "metadata cannot be null");
      paramUri = new UploadTask(this, paramStorageMetadata, paramUri, null);
      paramUri.zzaaP();
      return paramUri;
      bool1 = false;
      break;
    }
  }
  
  @NonNull
  public UploadTask putFile(@NonNull Uri paramUri1, @Nullable StorageMetadata paramStorageMetadata, @Nullable Uri paramUri2)
  {
    boolean bool2 = true;
    if (paramUri1 != null)
    {
      bool1 = true;
      zzac.zzb(bool1, "uri cannot be null");
      if (paramStorageMetadata == null) {
        break label59;
      }
    }
    label59:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      zzac.zzb(bool1, "metadata cannot be null");
      paramUri1 = new UploadTask(this, paramStorageMetadata, paramUri1, paramUri2);
      paramUri1.zzaaP();
      return paramUri1;
      bool1 = false;
      break;
    }
  }
  
  @NonNull
  public UploadTask putStream(@NonNull InputStream paramInputStream)
  {
    if (paramInputStream != null) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zzb(bool, "stream cannot be null");
      paramInputStream = new UploadTask(this, null, paramInputStream);
      paramInputStream.zzaaP();
      return paramInputStream;
    }
  }
  
  @NonNull
  public UploadTask putStream(@NonNull InputStream paramInputStream, @NonNull StorageMetadata paramStorageMetadata)
  {
    boolean bool2 = true;
    if (paramInputStream != null)
    {
      bool1 = true;
      zzac.zzb(bool1, "stream cannot be null");
      if (paramStorageMetadata == null) {
        break label53;
      }
    }
    label53:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      zzac.zzb(bool1, "metadata cannot be null");
      paramInputStream = new UploadTask(this, paramStorageMetadata, paramInputStream);
      paramInputStream.zzaaP();
      return paramInputStream;
      bool1 = false;
      break;
    }
  }
  
  public String toString()
  {
    String str1 = String.valueOf(this.zzckP.getAuthority());
    String str2 = String.valueOf(this.zzckP.getPath());
    return String.valueOf(str1).length() + 5 + String.valueOf(str2).length() + "gs://" + str1 + str2;
  }
  
  @NonNull
  public Task<StorageMetadata> updateMetadata(@NonNull StorageMetadata paramStorageMetadata)
  {
    zzac.zzw(paramStorageMetadata);
    TaskCompletionSource localTaskCompletionSource = new TaskCompletionSource();
    zzd.zzaaW().zzt(new zzf(this, localTaskCompletionSource, paramStorageMetadata));
    return localTaskCompletionSource.getTask();
  }
  
  @NonNull
  zzbre zzaaN()
    throws RemoteException
  {
    return zzbre.zzj(getApp());
  }
  
  @NonNull
  Uri zzaaO()
  {
    return this.zzckP;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\storage\StorageReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */