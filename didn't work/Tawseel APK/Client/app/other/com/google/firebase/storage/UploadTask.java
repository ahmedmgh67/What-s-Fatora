package com.google.firebase.storage;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzbqw;
import com.google.android.gms.internal.zzbrb;
import com.google.android.gms.internal.zzbre;
import com.google.android.gms.internal.zzbrf;
import com.google.firebase.FirebaseApp;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONException;
import org.json.JSONObject;

public class UploadTask
  extends StorageTask<TaskSnapshot>
{
  private volatile int mResultCode = 0;
  private final Uri mUri;
  private volatile Exception zzbLK = null;
  private final byte[] zzbyp;
  private volatile StorageMetadata zzckN;
  private final StorageReference zzcki;
  private zzbqw zzckk;
  private final byte[] zzclJ = new byte[262144];
  private final long zzclK;
  private final AtomicLong zzclL = new AtomicLong(0L);
  private InputStream zzclM;
  private boolean zzclN;
  private volatile Uri zzclO = null;
  private volatile Exception zzclP = null;
  private volatile String zzclQ;
  
  /* Error */
  UploadTask(StorageReference paramStorageReference, StorageMetadata paramStorageMetadata, Uri paramUri1, Uri paramUri2)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 48	com/google/firebase/storage/StorageTask:<init>	()V
    //   4: aload_0
    //   5: ldc 49
    //   7: newarray <illegal type>
    //   9: putfield 51	com/google/firebase/storage/UploadTask:zzclJ	[B
    //   12: aload_0
    //   13: new 53	java/util/concurrent/atomic/AtomicLong
    //   16: dup
    //   17: lconst_0
    //   18: invokespecial 56	java/util/concurrent/atomic/AtomicLong:<init>	(J)V
    //   21: putfield 58	com/google/firebase/storage/UploadTask:zzclL	Ljava/util/concurrent/atomic/AtomicLong;
    //   24: aload_0
    //   25: aconst_null
    //   26: putfield 60	com/google/firebase/storage/UploadTask:zzclO	Landroid/net/Uri;
    //   29: aload_0
    //   30: aconst_null
    //   31: putfield 62	com/google/firebase/storage/UploadTask:zzbLK	Ljava/lang/Exception;
    //   34: aload_0
    //   35: aconst_null
    //   36: putfield 64	com/google/firebase/storage/UploadTask:zzclP	Ljava/lang/Exception;
    //   39: aload_0
    //   40: iconst_0
    //   41: putfield 66	com/google/firebase/storage/UploadTask:mResultCode	I
    //   44: aload_1
    //   45: invokestatic 72	com/google/android/gms/common/internal/zzac:zzw	(Ljava/lang/Object;)Ljava/lang/Object;
    //   48: pop
    //   49: aload_3
    //   50: invokestatic 72	com/google/android/gms/common/internal/zzac:zzw	(Ljava/lang/Object;)Ljava/lang/Object;
    //   53: pop
    //   54: aload_0
    //   55: aconst_null
    //   56: putfield 74	com/google/firebase/storage/UploadTask:zzbyp	[B
    //   59: aload_0
    //   60: aload_1
    //   61: putfield 76	com/google/firebase/storage/UploadTask:zzcki	Lcom/google/firebase/storage/StorageReference;
    //   64: aload_0
    //   65: aload_2
    //   66: putfield 78	com/google/firebase/storage/UploadTask:zzckN	Lcom/google/firebase/storage/StorageMetadata;
    //   69: aload_0
    //   70: aload_3
    //   71: putfield 80	com/google/firebase/storage/UploadTask:mUri	Landroid/net/Uri;
    //   74: aload_0
    //   75: new 82	com/google/android/gms/internal/zzbqw
    //   78: dup
    //   79: aload_0
    //   80: getfield 76	com/google/firebase/storage/UploadTask:zzcki	Lcom/google/firebase/storage/StorageReference;
    //   83: invokevirtual 88	com/google/firebase/storage/StorageReference:getApp	()Lcom/google/firebase/FirebaseApp;
    //   86: aload_0
    //   87: getfield 76	com/google/firebase/storage/UploadTask:zzcki	Lcom/google/firebase/storage/StorageReference;
    //   90: invokevirtual 92	com/google/firebase/storage/StorageReference:getStorage	()Lcom/google/firebase/storage/FirebaseStorage;
    //   93: invokevirtual 98	com/google/firebase/storage/FirebaseStorage:getMaxUploadRetryTimeMillis	()J
    //   96: invokespecial 101	com/google/android/gms/internal/zzbqw:<init>	(Lcom/google/firebase/FirebaseApp;J)V
    //   99: putfield 103	com/google/firebase/storage/UploadTask:zzckk	Lcom/google/android/gms/internal/zzbqw;
    //   102: ldc2_w 104
    //   105: lstore 10
    //   107: lload 10
    //   109: lstore 8
    //   111: aload_0
    //   112: getfield 76	com/google/firebase/storage/UploadTask:zzcki	Lcom/google/firebase/storage/StorageReference;
    //   115: invokevirtual 92	com/google/firebase/storage/StorageReference:getStorage	()Lcom/google/firebase/storage/FirebaseStorage;
    //   118: invokevirtual 106	com/google/firebase/storage/FirebaseStorage:getApp	()Lcom/google/firebase/FirebaseApp;
    //   121: invokevirtual 112	com/google/firebase/FirebaseApp:getApplicationContext	()Landroid/content/Context;
    //   124: invokevirtual 118	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   127: astore_2
    //   128: lload 10
    //   130: lstore 12
    //   132: lload 10
    //   134: lstore 6
    //   136: lload 10
    //   138: lstore 8
    //   140: aload_2
    //   141: aload_0
    //   142: getfield 80	com/google/firebase/storage/UploadTask:mUri	Landroid/net/Uri;
    //   145: ldc 120
    //   147: invokevirtual 126	android/content/ContentResolver:openFileDescriptor	(Landroid/net/Uri;Ljava/lang/String;)Landroid/os/ParcelFileDescriptor;
    //   150: astore_1
    //   151: lload 10
    //   153: lstore 6
    //   155: aload_1
    //   156: ifnull +41 -> 197
    //   159: lload 10
    //   161: lstore 12
    //   163: lload 10
    //   165: lstore 6
    //   167: lload 10
    //   169: lstore 8
    //   171: aload_1
    //   172: invokevirtual 131	android/os/ParcelFileDescriptor:getStatSize	()J
    //   175: lstore 10
    //   177: lload 10
    //   179: lstore 12
    //   181: lload 10
    //   183: lstore 6
    //   185: lload 10
    //   187: lstore 8
    //   189: aload_1
    //   190: invokevirtual 134	android/os/ParcelFileDescriptor:close	()V
    //   193: lload 10
    //   195: lstore 6
    //   197: lload 6
    //   199: lstore 8
    //   201: aload_2
    //   202: aload_0
    //   203: getfield 80	com/google/firebase/storage/UploadTask:mUri	Landroid/net/Uri;
    //   206: invokevirtual 138	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   209: astore_1
    //   210: aload_1
    //   211: ifnull +255 -> 466
    //   214: lload 6
    //   216: lstore 8
    //   218: lload 6
    //   220: ldc2_w 104
    //   223: lcmp
    //   224: ifne +27 -> 251
    //   227: lload 6
    //   229: lstore 10
    //   231: aload_1
    //   232: invokevirtual 144	java/io/InputStream:available	()I
    //   235: istore 5
    //   237: lload 6
    //   239: lstore 8
    //   241: iload 5
    //   243: ifle +8 -> 251
    //   246: iload 5
    //   248: i2l
    //   249: lstore 8
    //   251: lload 8
    //   253: lstore 10
    //   255: new 146	java/io/BufferedInputStream
    //   258: dup
    //   259: aload_1
    //   260: invokespecial 149	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   263: astore_2
    //   264: aload_2
    //   265: astore_1
    //   266: lload 8
    //   268: lstore 6
    //   270: aload_0
    //   271: lload 6
    //   273: putfield 151	com/google/firebase/storage/UploadTask:zzclK	J
    //   276: aload_0
    //   277: aload_1
    //   278: putfield 153	com/google/firebase/storage/UploadTask:zzclM	Ljava/io/InputStream;
    //   281: aload_0
    //   282: iconst_1
    //   283: putfield 155	com/google/firebase/storage/UploadTask:zzclN	Z
    //   286: aload_0
    //   287: aload 4
    //   289: putfield 60	com/google/firebase/storage/UploadTask:zzclO	Landroid/net/Uri;
    //   292: return
    //   293: astore_1
    //   294: lload 12
    //   296: lstore 6
    //   298: ldc -99
    //   300: ldc -97
    //   302: aload_1
    //   303: invokestatic 165	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   306: pop
    //   307: ldc2_w 104
    //   310: lstore 6
    //   312: goto -115 -> 197
    //   315: astore_1
    //   316: lload 6
    //   318: lstore 8
    //   320: aload_0
    //   321: getfield 80	com/google/firebase/storage/UploadTask:mUri	Landroid/net/Uri;
    //   324: invokevirtual 171	android/net/Uri:toString	()Ljava/lang/String;
    //   327: invokestatic 177	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   330: astore_1
    //   331: lload 6
    //   333: lstore 8
    //   335: aload_1
    //   336: invokevirtual 180	java/lang/String:length	()I
    //   339: ifeq +75 -> 414
    //   342: lload 6
    //   344: lstore 8
    //   346: ldc -74
    //   348: aload_1
    //   349: invokevirtual 186	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
    //   352: astore_1
    //   353: lload 6
    //   355: lstore 8
    //   357: ldc -99
    //   359: aload_1
    //   360: invokestatic 189	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   363: pop
    //   364: goto -167 -> 197
    //   367: astore_2
    //   368: lload 8
    //   370: lstore 6
    //   372: aconst_null
    //   373: astore_1
    //   374: aload_0
    //   375: getfield 80	com/google/firebase/storage/UploadTask:mUri	Landroid/net/Uri;
    //   378: invokevirtual 171	android/net/Uri:toString	()Ljava/lang/String;
    //   381: invokestatic 177	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   384: astore_3
    //   385: aload_3
    //   386: invokevirtual 180	java/lang/String:length	()I
    //   389: ifeq +42 -> 431
    //   392: ldc -65
    //   394: aload_3
    //   395: invokevirtual 186	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
    //   398: astore_3
    //   399: ldc -99
    //   401: aload_3
    //   402: invokestatic 194	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   405: pop
    //   406: aload_0
    //   407: aload_2
    //   408: putfield 62	com/google/firebase/storage/UploadTask:zzbLK	Ljava/lang/Exception;
    //   411: goto -141 -> 270
    //   414: lload 6
    //   416: lstore 8
    //   418: new 173	java/lang/String
    //   421: dup
    //   422: ldc -74
    //   424: invokespecial 197	java/lang/String:<init>	(Ljava/lang/String;)V
    //   427: astore_1
    //   428: goto -75 -> 353
    //   431: new 173	java/lang/String
    //   434: dup
    //   435: ldc -65
    //   437: invokespecial 197	java/lang/String:<init>	(Ljava/lang/String;)V
    //   440: astore_3
    //   441: goto -42 -> 399
    //   444: astore_2
    //   445: lload 6
    //   447: lstore 8
    //   449: goto -198 -> 251
    //   452: astore_2
    //   453: aconst_null
    //   454: astore_1
    //   455: goto -81 -> 374
    //   458: astore_2
    //   459: lload 10
    //   461: lstore 6
    //   463: goto -89 -> 374
    //   466: goto -196 -> 270
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	469	0	this	UploadTask
    //   0	469	1	paramStorageReference	StorageReference
    //   0	469	2	paramStorageMetadata	StorageMetadata
    //   0	469	3	paramUri1	Uri
    //   0	469	4	paramUri2	Uri
    //   235	12	5	i	int
    //   134	328	6	l1	long
    //   109	339	8	l2	long
    //   105	355	10	l3	long
    //   130	165	12	l4	long
    // Exception table:
    //   from	to	target	type
    //   140	151	293	java/lang/NullPointerException
    //   171	177	293	java/lang/NullPointerException
    //   189	193	293	java/lang/NullPointerException
    //   140	151	315	java/io/IOException
    //   171	177	315	java/io/IOException
    //   189	193	315	java/io/IOException
    //   111	128	367	java/io/FileNotFoundException
    //   140	151	367	java/io/FileNotFoundException
    //   171	177	367	java/io/FileNotFoundException
    //   189	193	367	java/io/FileNotFoundException
    //   201	210	367	java/io/FileNotFoundException
    //   320	331	367	java/io/FileNotFoundException
    //   335	342	367	java/io/FileNotFoundException
    //   346	353	367	java/io/FileNotFoundException
    //   357	364	367	java/io/FileNotFoundException
    //   418	428	367	java/io/FileNotFoundException
    //   231	237	444	java/io/IOException
    //   298	307	452	java/io/FileNotFoundException
    //   231	237	458	java/io/FileNotFoundException
    //   255	264	458	java/io/FileNotFoundException
  }
  
  UploadTask(StorageReference paramStorageReference, StorageMetadata paramStorageMetadata, InputStream paramInputStream)
  {
    zzac.zzw(paramStorageReference);
    zzac.zzw(paramInputStream);
    this.zzclK = -1L;
    this.zzbyp = null;
    this.zzcki = paramStorageReference;
    this.zzckN = paramStorageMetadata;
    this.zzclM = new BufferedInputStream(paramInputStream, 262144);
    this.zzclN = false;
    this.mUri = null;
    this.zzckk = new zzbqw(this.zzcki.getApp(), this.zzcki.getStorage().getMaxUploadRetryTimeMillis());
  }
  
  UploadTask(StorageReference paramStorageReference, StorageMetadata paramStorageMetadata, byte[] paramArrayOfByte)
  {
    zzac.zzw(paramStorageReference);
    zzac.zzw(paramArrayOfByte);
    this.zzbyp = paramArrayOfByte;
    this.zzclK = this.zzbyp.length;
    this.zzcki = paramStorageReference;
    this.zzckN = paramStorageMetadata;
    this.mUri = null;
    this.zzclM = new BufferedInputStream(new ByteArrayInputStream(this.zzbyp), 262144);
    this.zzclN = true;
    this.zzckk = new zzbqw(this.zzcki.getApp(), this.zzcki.getStorage().getMaxUploadRetryTimeMillis());
  }
  
  private boolean zza(zzbrf paramzzbrf)
  {
    paramzzbrf.zza(zzbrb.zzi(this.zzcki.getApp()), this.zzcki.getApp().getApplicationContext());
    return zzc(paramzzbrf);
  }
  
  private void zzaba()
  {
    Object localObject3 = null;
    if (this.zzckN != null) {}
    for (Object localObject2 = this.zzckN.getContentType();; localObject2 = null)
    {
      Object localObject1 = localObject2;
      if (this.mUri != null)
      {
        localObject1 = localObject2;
        if (TextUtils.isEmpty((CharSequence)localObject2)) {
          localObject1 = this.zzcki.getStorage().getApp().getApplicationContext().getContentResolver().getType(this.mUri);
        }
      }
      localObject2 = localObject1;
      if (TextUtils.isEmpty((CharSequence)localObject1)) {
        localObject2 = "application/octet-stream";
      }
      try
      {
        zzbre localzzbre = this.zzcki.zzaaN();
        Uri localUri = this.zzcki.zzaaO();
        localObject1 = localObject3;
        if (this.zzckN != null) {
          localObject1 = this.zzckN.zzaaM();
        }
        localObject1 = localzzbre.zza(localUri, (JSONObject)localObject1, (String)localObject2);
        if (!zzb((zzbrf)localObject1)) {
          return;
        }
      }
      catch (RemoteException localRemoteException)
      {
        String str;
        do
        {
          Log.e("UploadTask", "Unable to create a network request from metadata", localRemoteException);
          this.zzbLK = localRemoteException;
          return;
          str = localRemoteException.zzjP("X-Goog-Upload-URL");
        } while (TextUtils.isEmpty(str));
        this.zzclO = Uri.parse(str);
        return;
      }
      catch (JSONException localJSONException)
      {
        for (;;) {}
      }
    }
  }
  
  private boolean zzabb()
  {
    if (zzaaQ() == 128) {}
    do
    {
      return false;
      if (Thread.interrupted())
      {
        this.zzbLK = new InterruptedException();
        zzf(64, false);
        return false;
      }
      if (zzaaQ() == 32)
      {
        zzf(256, false);
        return false;
      }
      if (zzaaQ() == 8)
      {
        zzf(16, false);
        return false;
      }
    } while (!zzabc());
    if (this.zzclO == null)
    {
      if (this.zzbLK == null) {
        this.zzbLK = new IllegalStateException("Unable to obtain an upload URL.");
      }
      zzf(64, false);
      return false;
    }
    if (this.zzbLK != null)
    {
      zzf(64, false);
      return false;
    }
    if ((this.zzclP != null) || (this.mResultCode < 200) || (this.mResultCode >= 300)) {}
    for (int i = 1;; i = 0)
    {
      if ((i == 0) || (zzbd(true))) {
        break label203;
      }
      if (!zzabc()) {
        break;
      }
      zzf(64, false);
      return false;
    }
    label203:
    return true;
  }
  
  private boolean zzabc()
  {
    if ("final".equals(this.zzclQ))
    {
      if (this.zzbLK == null) {
        this.zzbLK = new IOException("The server has terminated the upload session");
      }
      zzf(64, false);
      return false;
    }
    return true;
  }
  
  private void zzabd()
  {
    this.zzclM.mark(this.zzclJ.length + 1);
    int i;
    Object localObject;
    do
    {
      for (;;)
      {
        try
        {
          i = this.zzclM.read(this.zzclJ);
          zzbre localzzbre;
          String str;
          byte[] arrayOfByte;
          long l;
          boolean bool = false;
        }
        catch (IOException localIOException1)
        {
          try
          {
            localzzbre = this.zzcki.zzaaN();
            localObject = this.zzcki.zzaaO();
            str = this.zzclO.toString();
            arrayOfByte = this.zzclJ;
            l = this.zzclL.get();
            if (i != 262144L)
            {
              bool = true;
              localObject = localzzbre.zza((Uri)localObject, str, arrayOfByte, l, i, bool);
              if (zza((zzbrf)localObject)) {
                break;
              }
            }
          }
          catch (RemoteException localRemoteException1)
          {
            Log.e("UploadTask", "Unable to create chunk upload request", localRemoteException1);
            this.zzbLK = localRemoteException1;
            return;
          }
          try
          {
            this.zzclM.reset();
            return;
          }
          catch (IOException localIOException2)
          {
            Log.w("UploadTask", "Unable to reset the stream for error recovery.", localIOException2);
            this.zzbLK = localIOException2;
            return;
          }
          localIOException1 = localIOException1;
          Log.e("UploadTask", "Unable to read bytes for uploading", localIOException1);
          this.zzbLK = localIOException1;
          return;
        }
      }
      if (i != -1) {
        this.zzclL.getAndAdd(i);
      }
    } while (i == 262144L);
    try
    {
      this.zzckN = new StorageMetadata.Builder(((zzbrf)localObject).zzabq(), this.zzcki).build();
      zzf(4, false);
      zzf(128, false);
      return;
    }
    catch (RemoteException localRemoteException2)
    {
      localObject = String.valueOf(((zzbrf)localObject).zzabk());
      if (((String)localObject).length() != 0) {}
      for (localObject = "Unable to parse resulting metadata from upload:".concat((String)localObject);; localObject = new String("Unable to parse resulting metadata from upload:"))
      {
        Log.e("UploadTask", (String)localObject, localRemoteException2);
        this.zzbLK = localRemoteException2;
        return;
      }
    }
    catch (JSONException localJSONException)
    {
      for (;;) {}
    }
  }
  
  private boolean zzb(zzbrf paramzzbrf)
  {
    this.zzckk.zzd(paramzzbrf);
    return zzc(paramzzbrf);
  }
  
  private boolean zzbd(boolean paramBoolean)
  {
    try
    {
      zzbrf localzzbrf = this.zzcki.zzaaN().zzb(this.zzcki.zzaaO(), this.zzclO.toString());
      if ("final".equals(this.zzclQ)) {
        return false;
      }
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("UploadTask", "Unable to recover status during resumable upload", localRemoteException);
      this.zzbLK = localRemoteException;
      return false;
    }
    if (paramBoolean)
    {
      if (!zzb(localRemoteException)) {
        return false;
      }
    }
    else if (!zza(localRemoteException)) {
      return false;
    }
    if ("final".equals(localRemoteException.zzjP("X-Goog-Upload-Status")))
    {
      this.zzbLK = new IOException("The server has terminated the upload session");
      return false;
    }
    String str = localRemoteException.zzjP("X-Goog-Upload-Size-Received");
    if (!TextUtils.isEmpty(str)) {}
    long l2;
    for (long l1 = Long.parseLong(str);; l1 = 0L)
    {
      l2 = this.zzclL.get();
      if (l2 <= l1) {
        break;
      }
      this.zzbLK = new IOException("Unexpected error. The server lost a chunk update.");
      return false;
    }
    if (l2 < l1) {
      try
      {
        if (this.zzclM.skip(l1 - l2) != l1 - l2)
        {
          this.zzbLK = new IOException("Unexpected end of stream encountered.");
          return false;
        }
        if (!this.zzclL.compareAndSet(l2, l1))
        {
          Log.e("UploadTask", "Somehow, the uploaded bytes changed during an uploaded.  This should nothappen");
          this.zzbLK = new IllegalStateException("uploaded bytes changed unexpectedly.");
          return false;
        }
      }
      catch (IOException localIOException)
      {
        Log.e("UploadTask", "Unable to recover position in Stream during resumable upload", localIOException);
        this.zzbLK = localIOException;
        return false;
      }
    }
    return true;
  }
  
  private boolean zzc(zzbrf paramzzbrf)
  {
    int j = paramzzbrf.getResultCode();
    int i = j;
    if (this.zzckk.zzqa(j)) {
      i = -2;
    }
    this.mResultCode = i;
    this.zzclP = paramzzbrf.getException();
    this.zzclQ = paramzzbrf.zzjP("X-Goog-Upload-Status");
    return (zzpW(this.mResultCode)) && (this.zzclP == null);
  }
  
  private boolean zzpW(int paramInt)
  {
    return (paramInt == 308) || ((paramInt >= 200) && (paramInt < 300));
  }
  
  StorageReference getStorage()
  {
    return this.zzcki;
  }
  
  long getTotalByteCount()
  {
    return this.zzclK;
  }
  
  protected void onCanceled()
  {
    this.zzckk.cancel();
    if (this.zzclO != null) {}
    for (;;)
    {
      try
      {
        final zzbrf localzzbrf = this.zzcki.zzaaN().zza(this.zzcki.zzaaO(), this.zzclO.toString());
        if (localzzbrf != null) {
          zzd.zzaaW().zzt(new Runnable()
          {
            public void run()
            {
              localzzbrf.zza(zzbrb.zzi(UploadTask.zza(UploadTask.this).getApp()), UploadTask.zza(UploadTask.this).getApp().getApplicationContext());
            }
          });
        }
        this.zzbLK = StorageException.fromErrorStatus(Status.zzayl);
        super.onCanceled();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("UploadTask", "Unable to create chunk upload request", localRemoteException);
      }
      Object localObject = null;
    }
  }
  
  protected void resetState()
  {
    this.zzbLK = null;
    this.zzclP = null;
    this.mResultCode = 0;
    this.zzclQ = null;
  }
  
  void run()
  {
    this.zzckk.reset();
    if (!zzf(4, false)) {
      Log.d("UploadTask", "The upload cannot continue as it is not in a valid state.");
    }
    do
    {
      do
      {
        return;
        if (this.zzcki.getParent() == null) {
          this.zzbLK = new IllegalArgumentException("Cannot upload to getRoot. You should upload to a storage location such as .getReference('image.png').putFile...");
        }
      } while (this.zzbLK != null);
      if (this.zzclO == null) {
        zzaba();
      }
      for (;;)
      {
        boolean bool1 = zzabb();
        while (bool1)
        {
          zzabd();
          boolean bool2 = zzabb();
          bool1 = bool2;
          if (bool2)
          {
            zzf(4, false);
            bool1 = bool2;
          }
        }
        zzbd(false);
      }
    } while (!this.zzclN);
    try
    {
      this.zzclM.close();
      return;
    }
    catch (IOException localIOException)
    {
      Log.e("UploadTask", "Unable to close stream.", localIOException);
    }
  }
  
  protected void schedule()
  {
    zzd.zzaaW().zzu(zzTj());
  }
  
  @NonNull
  TaskSnapshot zzabe()
  {
    if (this.zzbLK != null) {}
    for (Exception localException = this.zzbLK;; localException = this.zzclP) {
      return new TaskSnapshot(StorageException.fromExceptionAndHttpCode(localException, this.mResultCode), this.zzclL.get(), this.zzclO, this.zzckN);
    }
  }
  
  public class TaskSnapshot
    extends StorageTask<TaskSnapshot>.SnapshotBase
  {
    private final StorageMetadata zzckN;
    private final Uri zzclO;
    private final long zzclT;
    
    TaskSnapshot(Exception paramException, long paramLong, Uri paramUri, StorageMetadata paramStorageMetadata)
    {
      super(paramException);
      this.zzclT = paramLong;
      this.zzclO = paramUri;
      this.zzckN = paramStorageMetadata;
    }
    
    public long getBytesTransferred()
    {
      return this.zzclT;
    }
    
    @Nullable
    public Uri getDownloadUrl()
    {
      StorageMetadata localStorageMetadata = getMetadata();
      if (localStorageMetadata != null) {
        return localStorageMetadata.getDownloadUrl();
      }
      return null;
    }
    
    @Nullable
    public StorageMetadata getMetadata()
    {
      return this.zzckN;
    }
    
    public long getTotalByteCount()
    {
      return UploadTask.this.getTotalByteCount();
    }
    
    @Nullable
    public Uri getUploadSessionUri()
    {
      return this.zzclO;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\storage\UploadTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */