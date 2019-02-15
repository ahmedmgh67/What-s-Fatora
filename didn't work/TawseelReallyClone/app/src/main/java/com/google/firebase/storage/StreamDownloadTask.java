package com.google.firebase.storage;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzbqw;
import com.google.android.gms.internal.zzbrf;
import java.io.IOException;
import java.io.InputStream;

public class StreamDownloadTask
  extends StorageTask<TaskSnapshot>
{
  private volatile int mResultCode = 0;
  private long zzaKG;
  private volatile Exception zzbLK = null;
  private InputStream zzbSc;
  private StorageReference zzcki;
  private zzbqw zzckk;
  private long zzckm;
  private StreamProcessor zzcls;
  private long zzclt;
  private zzbrf zzclu;
  
  StreamDownloadTask(@NonNull StorageReference paramStorageReference)
  {
    this.zzcki = paramStorageReference;
    this.zzckk = new zzbqw(this.zzcki.getApp(), this.zzcki.getStorage().getMaxDownloadRetryTimeMillis());
  }
  
  private void zzaT(long paramLong)
  {
    this.zzckm += paramLong;
    if (this.zzclt + 262144L <= this.zzckm) {
      zzf(4, false);
    }
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
  
  protected void onProgress()
  {
    this.zzclt = this.zzckm;
  }
  
  public boolean pause()
  {
    throw new UnsupportedOperationException("this operation is not supported on StreamDownloadTask.");
  }
  
  public boolean resume()
  {
    throw new UnsupportedOperationException("this operation is not supported on StreamDownloadTask.");
  }
  
  /* Error */
  void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 67	com/google/firebase/storage/StreamDownloadTask:zzckk	Lcom/google/android/gms/internal/zzbqw;
    //   4: invokevirtual 121	com/google/android/gms/internal/zzbqw:reset	()V
    //   7: aload_0
    //   8: getfield 40	com/google/firebase/storage/StreamDownloadTask:zzbLK	Ljava/lang/Exception;
    //   11: ifnull +12 -> 23
    //   14: aload_0
    //   15: bipush 64
    //   17: iconst_0
    //   18: invokevirtual 87	com/google/firebase/storage/StreamDownloadTask:zzf	(IZ)Z
    //   21: pop
    //   22: return
    //   23: aload_0
    //   24: iconst_4
    //   25: iconst_0
    //   26: invokevirtual 87	com/google/firebase/storage/StreamDownloadTask:zzf	(IZ)Z
    //   29: ifeq -7 -> 22
    //   32: aload_0
    //   33: aload_0
    //   34: getfield 44	com/google/firebase/storage/StreamDownloadTask:zzcki	Lcom/google/firebase/storage/StorageReference;
    //   37: invokevirtual 125	com/google/firebase/storage/StorageReference:zzaaN	()Lcom/google/android/gms/internal/zzbre;
    //   40: aload_0
    //   41: getfield 44	com/google/firebase/storage/StreamDownloadTask:zzcki	Lcom/google/firebase/storage/StorageReference;
    //   44: invokevirtual 129	com/google/firebase/storage/StorageReference:zzaaO	()Landroid/net/Uri;
    //   47: lconst_0
    //   48: invokevirtual 134	com/google/android/gms/internal/zzbre:zza	(Landroid/net/Uri;J)Lcom/google/android/gms/internal/zzbrf;
    //   51: putfield 72	com/google/firebase/storage/StreamDownloadTask:zzclu	Lcom/google/android/gms/internal/zzbrf;
    //   54: aload_0
    //   55: getfield 67	com/google/firebase/storage/StreamDownloadTask:zzckk	Lcom/google/android/gms/internal/zzbqw;
    //   58: aload_0
    //   59: getfield 72	com/google/firebase/storage/StreamDownloadTask:zzclu	Lcom/google/android/gms/internal/zzbrf;
    //   62: iconst_0
    //   63: invokevirtual 137	com/google/android/gms/internal/zzbqw:zza	(Lcom/google/android/gms/internal/zzbrf;Z)V
    //   66: aload_0
    //   67: aload_0
    //   68: getfield 72	com/google/firebase/storage/StreamDownloadTask:zzclu	Lcom/google/android/gms/internal/zzbrf;
    //   71: invokevirtual 143	com/google/android/gms/internal/zzbrf:getResultCode	()I
    //   74: putfield 42	com/google/firebase/storage/StreamDownloadTask:mResultCode	I
    //   77: aload_0
    //   78: getfield 72	com/google/firebase/storage/StreamDownloadTask:zzclu	Lcom/google/android/gms/internal/zzbrf;
    //   81: invokevirtual 147	com/google/android/gms/internal/zzbrf:getException	()Ljava/lang/Exception;
    //   84: ifnull +192 -> 276
    //   87: aload_0
    //   88: getfield 72	com/google/firebase/storage/StreamDownloadTask:zzclu	Lcom/google/android/gms/internal/zzbrf;
    //   91: invokevirtual 147	com/google/android/gms/internal/zzbrf:getException	()Ljava/lang/Exception;
    //   94: astore_2
    //   95: aload_0
    //   96: aload_2
    //   97: putfield 40	com/google/firebase/storage/StreamDownloadTask:zzbLK	Ljava/lang/Exception;
    //   100: aload_0
    //   101: aload_0
    //   102: getfield 42	com/google/firebase/storage/StreamDownloadTask:mResultCode	I
    //   105: invokespecial 149	com/google/firebase/storage/StreamDownloadTask:zzpW	(I)Z
    //   108: ifeq +176 -> 284
    //   111: aload_0
    //   112: getfield 40	com/google/firebase/storage/StreamDownloadTask:zzbLK	Ljava/lang/Exception;
    //   115: ifnonnull +169 -> 284
    //   118: aload_0
    //   119: invokevirtual 152	com/google/firebase/storage/StreamDownloadTask:zzaaQ	()I
    //   122: iconst_4
    //   123: if_icmpne +161 -> 284
    //   126: iconst_1
    //   127: istore_1
    //   128: iload_1
    //   129: ifeq +67 -> 196
    //   132: aload_0
    //   133: aload_0
    //   134: getfield 72	com/google/firebase/storage/StreamDownloadTask:zzclu	Lcom/google/android/gms/internal/zzbrf;
    //   137: invokevirtual 155	com/google/android/gms/internal/zzbrf:zzabo	()I
    //   140: i2l
    //   141: putfield 98	com/google/firebase/storage/StreamDownloadTask:zzaKG	J
    //   144: aload_0
    //   145: getfield 72	com/google/firebase/storage/StreamDownloadTask:zzclu	Lcom/google/android/gms/internal/zzbrf;
    //   148: invokevirtual 159	com/google/android/gms/internal/zzbrf:getStream	()Ljava/io/InputStream;
    //   151: astore_2
    //   152: aload_2
    //   153: ifnull +154 -> 307
    //   156: aload_0
    //   157: new 13	com/google/firebase/storage/StreamDownloadTask$zza
    //   160: dup
    //   161: aload_0
    //   162: aload_2
    //   163: invokespecial 162	com/google/firebase/storage/StreamDownloadTask$zza:<init>	(Lcom/google/firebase/storage/StreamDownloadTask;Ljava/io/InputStream;)V
    //   166: putfield 91	com/google/firebase/storage/StreamDownloadTask:zzbSc	Ljava/io/InputStream;
    //   169: aload_0
    //   170: getfield 164	com/google/firebase/storage/StreamDownloadTask:zzcls	Lcom/google/firebase/storage/StreamDownloadTask$StreamProcessor;
    //   173: ifnull +23 -> 196
    //   176: aload_0
    //   177: getfield 164	com/google/firebase/storage/StreamDownloadTask:zzcls	Lcom/google/firebase/storage/StreamDownloadTask$StreamProcessor;
    //   180: aload_0
    //   181: invokevirtual 168	com/google/firebase/storage/StreamDownloadTask:zzaaS	()Lcom/google/firebase/storage/StorageTask$ProvideError;
    //   184: checkcast 10	com/google/firebase/storage/StreamDownloadTask$TaskSnapshot
    //   187: aload_0
    //   188: getfield 91	com/google/firebase/storage/StreamDownloadTask:zzbSc	Ljava/io/InputStream;
    //   191: invokeinterface 172 3 0
    //   196: aload_0
    //   197: getfield 91	com/google/firebase/storage/StreamDownloadTask:zzbSc	Ljava/io/InputStream;
    //   200: ifnonnull +10 -> 210
    //   203: aload_0
    //   204: getfield 72	com/google/firebase/storage/StreamDownloadTask:zzclu	Lcom/google/android/gms/internal/zzbrf;
    //   207: invokevirtual 175	com/google/android/gms/internal/zzbrf:zzabh	()V
    //   210: iload_1
    //   211: ifeq +112 -> 323
    //   214: aload_0
    //   215: getfield 40	com/google/firebase/storage/StreamDownloadTask:zzbLK	Ljava/lang/Exception;
    //   218: ifnonnull +105 -> 323
    //   221: aload_0
    //   222: invokevirtual 152	com/google/firebase/storage/StreamDownloadTask:zzaaQ	()I
    //   225: iconst_4
    //   226: if_icmpne +97 -> 323
    //   229: iconst_1
    //   230: istore_1
    //   231: iload_1
    //   232: ifeq +96 -> 328
    //   235: aload_0
    //   236: iconst_4
    //   237: iconst_0
    //   238: invokevirtual 87	com/google/firebase/storage/StreamDownloadTask:zzf	(IZ)Z
    //   241: pop
    //   242: aload_0
    //   243: sipush 128
    //   246: iconst_0
    //   247: invokevirtual 87	com/google/firebase/storage/StreamDownloadTask:zzf	(IZ)Z
    //   250: pop
    //   251: return
    //   252: astore_2
    //   253: ldc -79
    //   255: ldc -77
    //   257: aload_2
    //   258: invokestatic 185	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   261: pop
    //   262: aload_0
    //   263: aload_2
    //   264: putfield 40	com/google/firebase/storage/StreamDownloadTask:zzbLK	Ljava/lang/Exception;
    //   267: aload_0
    //   268: bipush 64
    //   270: iconst_0
    //   271: invokevirtual 87	com/google/firebase/storage/StreamDownloadTask:zzf	(IZ)Z
    //   274: pop
    //   275: return
    //   276: aload_0
    //   277: getfield 40	com/google/firebase/storage/StreamDownloadTask:zzbLK	Ljava/lang/Exception;
    //   280: astore_2
    //   281: goto -186 -> 95
    //   284: iconst_0
    //   285: istore_1
    //   286: goto -158 -> 128
    //   289: astore_2
    //   290: ldc -79
    //   292: ldc -69
    //   294: aload_2
    //   295: invokestatic 190	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   298: pop
    //   299: aload_0
    //   300: aload_2
    //   301: putfield 40	com/google/firebase/storage/StreamDownloadTask:zzbLK	Ljava/lang/Exception;
    //   304: goto -108 -> 196
    //   307: aload_0
    //   308: new 192	java/io/IOException
    //   311: dup
    //   312: ldc -62
    //   314: invokespecial 195	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   317: putfield 40	com/google/firebase/storage/StreamDownloadTask:zzbLK	Ljava/lang/Exception;
    //   320: goto -124 -> 196
    //   323: iconst_0
    //   324: istore_1
    //   325: goto -94 -> 231
    //   328: aload_0
    //   329: invokevirtual 152	com/google/firebase/storage/StreamDownloadTask:zzaaQ	()I
    //   332: bipush 32
    //   334: if_icmpne +49 -> 383
    //   337: sipush 256
    //   340: istore_1
    //   341: aload_0
    //   342: iload_1
    //   343: iconst_0
    //   344: invokevirtual 87	com/google/firebase/storage/StreamDownloadTask:zzf	(IZ)Z
    //   347: ifne -325 -> 22
    //   350: aload_0
    //   351: invokevirtual 152	com/google/firebase/storage/StreamDownloadTask:zzaaQ	()I
    //   354: istore_1
    //   355: ldc -79
    //   357: new 197	java/lang/StringBuilder
    //   360: dup
    //   361: bipush 62
    //   363: invokespecial 200	java/lang/StringBuilder:<init>	(I)V
    //   366: ldc -54
    //   368: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   371: iload_1
    //   372: invokevirtual 209	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   375: invokevirtual 213	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   378: invokestatic 216	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   381: pop
    //   382: return
    //   383: bipush 64
    //   385: istore_1
    //   386: goto -45 -> 341
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	389	0	this	StreamDownloadTask
    //   127	259	1	i	int
    //   94	69	2	localObject	Object
    //   252	12	2	localRemoteException	android.os.RemoteException
    //   280	1	2	localException1	Exception
    //   289	12	2	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   32	54	252	android/os/RemoteException
    //   176	196	289	java/lang/Exception
  }
  
  protected void schedule()
  {
    zzd.zzaaW().zzv(zzTj());
  }
  
  StreamDownloadTask zza(@NonNull StreamProcessor paramStreamProcessor)
  {
    zzac.zzw(paramStreamProcessor);
    if (this.zzcls == null) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zzar(bool);
      this.zzcls = paramStreamProcessor;
      return this;
    }
  }
  
  @NonNull
  TaskSnapshot zzaaX()
  {
    return new TaskSnapshot(StorageException.fromExceptionAndHttpCode(this.zzbLK, this.mResultCode), this.zzclt);
  }
  
  public static abstract interface StreamProcessor
  {
    public abstract void doInBackground(StreamDownloadTask.TaskSnapshot paramTaskSnapshot, InputStream paramInputStream)
      throws IOException;
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
    
    public InputStream getStream()
    {
      return StreamDownloadTask.zzb(StreamDownloadTask.this);
    }
    
    public long getTotalByteCount()
    {
      return StreamDownloadTask.this.getTotalBytes();
    }
  }
  
  private static class zza
    extends InputStream
  {
    private StreamDownloadTask zzclv;
    private InputStream zzclw;
    private int zzclx;
    
    public zza(@NonNull StreamDownloadTask paramStreamDownloadTask, @NonNull InputStream paramInputStream)
    {
      this.zzclv = paramStreamDownloadTask;
      this.zzclw = paramInputStream;
    }
    
    private void zzaaY()
      throws IOException
    {
      if (this.zzclv.zzaaQ() == 32) {
        throw StorageException.zzckv;
      }
    }
    
    public int available()
      throws IOException
    {
      zzaaY();
      return this.zzclw.available();
    }
    
    public void close()
      throws IOException
    {
      this.zzclw.close();
      if (StreamDownloadTask.zza(this.zzclv) != null) {
        StreamDownloadTask.zza(this.zzclv).zzabh();
      }
      zzaaY();
    }
    
    public void mark(int paramInt)
    {
      this.zzclx = paramInt;
      this.zzclw.mark(paramInt);
    }
    
    public boolean markSupported()
    {
      return this.zzclw.markSupported();
    }
    
    public int read()
      throws IOException
    {
      zzaaY();
      int i = this.zzclw.read();
      if (i != -1) {
        StreamDownloadTask.zza(this.zzclv, 1L);
      }
      return i;
    }
    
    public int read(@NonNull byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      zzaaY();
      int i = 0;
      int j = i;
      int m = paramInt2;
      int k = paramInt1;
      int n;
      if (paramInt2 > 262144L)
      {
        n = this.zzclw.read(paramArrayOfByte, paramInt1, 262144);
        if (n == -1) {
          if (i != 0) {}
        }
      }
      do
      {
        return -1;
        return i;
        j = i + n;
        k = paramInt1 + n;
        m = paramInt2 - n;
        StreamDownloadTask.zza(this.zzclv, n);
        zzaaY();
        i = j;
        paramInt2 = m;
        paramInt1 = k;
        if (n >= 262144L) {
          break;
        }
        paramInt1 = j;
        if (m <= 0) {
          return paramInt1;
        }
        paramInt2 = this.zzclw.read(paramArrayOfByte, k, m);
        if (paramInt2 != -1) {
          break label142;
        }
      } while (j == 0);
      return j;
      label142:
      paramInt1 = j + paramInt2;
      StreamDownloadTask.zza(this.zzclv, paramInt2);
      return paramInt1;
    }
    
    public void reset()
      throws IOException
    {
      try
      {
        zzaaY();
        StreamDownloadTask.zza(this.zzclv, -this.zzclx);
        this.zzclw.reset();
        return;
      }
      finally
      {
        localObject = finally;
        throw ((Throwable)localObject);
      }
    }
    
    public long skip(long paramLong)
      throws IOException
    {
      zzaaY();
      int i = 0;
      while (paramLong > 262144L)
      {
        long l = this.zzclw.skip(262144L);
        i = (int)(i + l);
        if (l < 262144L)
        {
          StreamDownloadTask.zza(this.zzclv, l);
          return i;
        }
        StreamDownloadTask.zza(this.zzclv, 262144L);
        paramLong -= 262144L;
        zzaaY();
      }
      paramLong = this.zzclw.skip(paramLong);
      i = (int)(i + paramLong);
      StreamDownloadTask.zza(this.zzclv, paramLong);
      return i;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\storage\StreamDownloadTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */