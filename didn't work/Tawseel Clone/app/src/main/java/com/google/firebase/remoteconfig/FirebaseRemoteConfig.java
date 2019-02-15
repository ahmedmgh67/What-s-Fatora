package com.google.firebase.remoteconfig;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzacz.zza.zza;
import com.google.android.gms.internal.zzacz.zzb;
import com.google.android.gms.internal.zzadf;
import com.google.android.gms.internal.zzbqo;
import com.google.android.gms.internal.zzbqq;
import com.google.android.gms.internal.zzbqs;
import com.google.android.gms.internal.zzbqt;
import com.google.android.gms.internal.zzbqu.zza;
import com.google.android.gms.internal.zzbqu.zzb;
import com.google.android.gms.internal.zzbqu.zzc;
import com.google.android.gms.internal.zzbqu.zzd;
import com.google.android.gms.internal.zzbqu.zze;
import com.google.android.gms.internal.zzbqu.zzf;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseApp;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirebaseRemoteConfig
{
  public static final boolean DEFAULT_VALUE_FOR_BOOLEAN = false;
  public static final byte[] DEFAULT_VALUE_FOR_BYTE_ARRAY = new byte[0];
  public static final double DEFAULT_VALUE_FOR_DOUBLE = 0.0D;
  public static final long DEFAULT_VALUE_FOR_LONG = 0L;
  public static final String DEFAULT_VALUE_FOR_STRING = "";
  public static final int LAST_FETCH_STATUS_FAILURE = 1;
  public static final int LAST_FETCH_STATUS_NO_FETCH_YET = 0;
  public static final int LAST_FETCH_STATUS_SUCCESS = -1;
  public static final int LAST_FETCH_STATUS_THROTTLED = 2;
  public static final int VALUE_SOURCE_DEFAULT = 1;
  public static final int VALUE_SOURCE_REMOTE = 2;
  public static final int VALUE_SOURCE_STATIC = 0;
  private static FirebaseRemoteConfig zzcjz;
  private final Context mContext;
  private zzbqq zzcjA;
  private zzbqq zzcjB;
  private zzbqq zzcjC;
  private zzbqt zzcjD;
  private final ReadWriteLock zzcjE = new ReentrantReadWriteLock(true);
  
  FirebaseRemoteConfig(Context paramContext)
  {
    this(paramContext, null, null, null, null);
  }
  
  private FirebaseRemoteConfig(Context paramContext, zzbqq paramzzbqq1, zzbqq paramzzbqq2, zzbqq paramzzbqq3, zzbqt paramzzbqt)
  {
    this.mContext = paramContext;
    if (paramzzbqt != null) {}
    for (this.zzcjD = paramzzbqt;; this.zzcjD = new zzbqt())
    {
      this.zzcjD.zzaS(zzcc(this.mContext));
      if (paramzzbqq1 != null) {
        this.zzcjA = paramzzbqq1;
      }
      if (paramzzbqq2 != null) {
        this.zzcjB = paramzzbqq2;
      }
      if (paramzzbqq3 != null) {
        this.zzcjC = paramzzbqq3;
      }
      return;
    }
  }
  
  public static FirebaseRemoteConfig getInstance()
  {
    if (zzcjz == null)
    {
      FirebaseApp localFirebaseApp = FirebaseApp.getInstance();
      if (localFirebaseApp == null) {
        throw new IllegalStateException("FirebaseApp has not been initialized.");
      }
      return zzcb(localFirebaseApp.getApplicationContext());
    }
    return zzcjz;
  }
  
  private static zzbqq zza(zzbqu.zza paramzza)
  {
    if (paramzza == null) {
      return null;
    }
    HashMap localHashMap1 = new HashMap();
    zzbqu.zzd[] arrayOfzzd = paramzza.zzcjU;
    int k = arrayOfzzd.length;
    int i = 0;
    while (i < k)
    {
      Object localObject1 = arrayOfzzd[i];
      String str = ((zzbqu.zzd)localObject1).zzaFs;
      HashMap localHashMap2 = new HashMap();
      localObject1 = ((zzbqu.zzd)localObject1).zzcka;
      int m = localObject1.length;
      int j = 0;
      while (j < m)
      {
        Object localObject2 = localObject1[j];
        localHashMap2.put(((zzbqu.zzb)localObject2).zzaA, ((zzbqu.zzb)localObject2).zzcjW);
        j += 1;
      }
      localHashMap1.put(str, localHashMap2);
      i += 1;
    }
    return new zzbqq(localHashMap1, paramzza.timestamp);
  }
  
  private static zzbqt zza(zzbqu.zzc paramzzc)
  {
    if (paramzzc == null) {
      return null;
    }
    zzbqt localzzbqt = new zzbqt();
    localzzbqt.zzpV(paramzzc.zzcjX);
    localzzbqt.zzbc(paramzzc.zzcjY);
    return localzzbqt;
  }
  
  private static Map<String, zzbqo> zza(zzbqu.zzf[] paramArrayOfzzf)
  {
    HashMap localHashMap = new HashMap();
    if (paramArrayOfzzf == null) {}
    for (;;)
    {
      return localHashMap;
      int j = paramArrayOfzzf.length;
      int i = 0;
      while (i < j)
      {
        zzbqu.zzf localzzf = paramArrayOfzzf[i];
        localHashMap.put(localzzf.zzaFs, new zzbqo(localzzf.resourceId, localzzf.zzckh));
        i += 1;
      }
    }
  }
  
  /* Error */
  private void zzaas()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 68	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzcjE	Ljava/util/concurrent/locks/ReadWriteLock;
    //   4: invokeinterface 194 1 0
    //   9: invokeinterface 199 1 0
    //   14: new 201	com/google/android/gms/internal/zzbqp
    //   17: dup
    //   18: aload_0
    //   19: getfield 70	com/google/firebase/remoteconfig/FirebaseRemoteConfig:mContext	Landroid/content/Context;
    //   22: aload_0
    //   23: getfield 84	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzcjA	Lcom/google/android/gms/internal/zzbqq;
    //   26: aload_0
    //   27: getfield 86	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzcjB	Lcom/google/android/gms/internal/zzbqq;
    //   30: aload_0
    //   31: getfield 88	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzcjC	Lcom/google/android/gms/internal/zzbqq;
    //   34: aload_0
    //   35: getfield 72	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzcjD	Lcom/google/android/gms/internal/zzbqt;
    //   38: invokespecial 202	com/google/android/gms/internal/zzbqp:<init>	(Landroid/content/Context;Lcom/google/android/gms/internal/zzbqq;Lcom/google/android/gms/internal/zzbqq;Lcom/google/android/gms/internal/zzbqq;Lcom/google/android/gms/internal/zzbqt;)V
    //   41: astore_1
    //   42: getstatic 207	android/os/Build$VERSION:SDK_INT	I
    //   45: bipush 11
    //   47: if_icmplt +27 -> 74
    //   50: getstatic 213	android/os/AsyncTask:SERIAL_EXECUTOR	Ljava/util/concurrent/Executor;
    //   53: aload_1
    //   54: invokeinterface 219 2 0
    //   59: aload_0
    //   60: getfield 68	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzcjE	Ljava/util/concurrent/locks/ReadWriteLock;
    //   63: invokeinterface 194 1 0
    //   68: invokeinterface 222 1 0
    //   73: return
    //   74: new 8	com/google/firebase/remoteconfig/FirebaseRemoteConfig$zza
    //   77: dup
    //   78: invokespecial 223	com/google/firebase/remoteconfig/FirebaseRemoteConfig$zza:<init>	()V
    //   81: aload_1
    //   82: invokevirtual 224	com/google/firebase/remoteconfig/FirebaseRemoteConfig$zza:execute	(Ljava/lang/Runnable;)V
    //   85: goto -26 -> 59
    //   88: astore_1
    //   89: aload_0
    //   90: getfield 68	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzcjE	Ljava/util/concurrent/locks/ReadWriteLock;
    //   93: invokeinterface 194 1 0
    //   98: invokeinterface 222 1 0
    //   103: aload_1
    //   104: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	105	0	this	FirebaseRemoteConfig
    //   41	41	1	localzzbqp	com.google.android.gms.internal.zzbqp
    //   88	16	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   14	59	88	finally
    //   74	85	88	finally
  }
  
  private static long zzb(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    byte[] arrayOfByte = new byte['က'];
    int i;
    for (long l = 0L;; l += i)
    {
      i = paramInputStream.read(arrayOfByte);
      if (i == -1) {
        return l;
      }
      paramOutputStream.write(arrayOfByte, 0, i);
    }
  }
  
  private void zzc(Map<String, Object> paramMap, String paramString, boolean paramBoolean)
  {
    if (paramString == null) {
      return;
    }
    int i;
    HashMap localHashMap;
    Iterator localIterator;
    if ((paramMap == null) || (paramMap.isEmpty()))
    {
      i = 1;
      localHashMap = new HashMap();
      if (i == 0) {
        localIterator = paramMap.keySet().iterator();
      }
    }
    else
    {
      for (;;)
      {
        if (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          Object localObject = paramMap.get(str);
          if ((localObject instanceof String))
          {
            localHashMap.put(str, ((String)localObject).getBytes(zzbqs.UTF_8));
            continue;
            i = 0;
            break;
          }
          if ((localObject instanceof Long)) {
            localHashMap.put(str, ((Long)localObject).toString().getBytes(zzbqs.UTF_8));
          } else if ((localObject instanceof Integer)) {
            localHashMap.put(str, ((Integer)localObject).toString().getBytes(zzbqs.UTF_8));
          } else if ((localObject instanceof Double)) {
            localHashMap.put(str, ((Double)localObject).toString().getBytes(zzbqs.UTF_8));
          } else if ((localObject instanceof Float)) {
            localHashMap.put(str, ((Float)localObject).toString().getBytes(zzbqs.UTF_8));
          } else if ((localObject instanceof byte[])) {
            localHashMap.put(str, (byte[])localObject);
          } else if ((localObject instanceof Boolean)) {
            localHashMap.put(str, ((Boolean)localObject).toString().getBytes(zzbqs.UTF_8));
          } else {
            throw new IllegalArgumentException("The type of a default value needs to beone of String, Long, Double, Boolean, or byte[].");
          }
        }
      }
    }
    this.zzcjE.writeLock().lock();
    if (i != 0) {}
    for (;;)
    {
      try
      {
        if (this.zzcjC != null)
        {
          boolean bool = this.zzcjC.zzjG(paramString);
          if (bool) {}
        }
        else
        {
          return;
        }
        this.zzcjC.zzj(null, paramString);
        this.zzcjC.setTimestamp(System.currentTimeMillis());
        if (paramBoolean) {
          this.zzcjD.zzjH(paramString);
        }
        zzaas();
        return;
      }
      finally
      {
        this.zzcjE.writeLock().unlock();
      }
      if (this.zzcjC == null) {
        this.zzcjC = new zzbqq(new HashMap(), System.currentTimeMillis());
      }
      this.zzcjC.zzj(localHashMap, paramString);
      this.zzcjC.setTimestamp(System.currentTimeMillis());
    }
  }
  
  public static FirebaseRemoteConfig zzcb(Context paramContext)
  {
    zzbqu.zze localzze;
    if (zzcjz == null)
    {
      localzze = zzcd(paramContext);
      if (localzze != null) {
        break label50;
      }
      if (Log.isLoggable("FirebaseRemoteConfig", 3)) {
        Log.d("FirebaseRemoteConfig", "No persisted config was found. Initializing from scratch.");
      }
    }
    label50:
    zzbqq localzzbqq1;
    zzbqq localzzbqq2;
    zzbqq localzzbqq3;
    zzbqt localzzbqt;
    for (zzcjz = new FirebaseRemoteConfig(paramContext);; zzcjz = new FirebaseRemoteConfig(paramContext, localzzbqq1, localzzbqq2, localzzbqq3, localzzbqt))
    {
      return zzcjz;
      if (Log.isLoggable("FirebaseRemoteConfig", 3)) {
        Log.d("FirebaseRemoteConfig", "Initializing from persisted config.");
      }
      localzzbqq1 = zza(localzze.zzckb);
      localzzbqq2 = zza(localzze.zzckc);
      localzzbqq3 = zza(localzze.zzckd);
      localzzbqt = zza(localzze.zzcke);
      if (localzzbqt != null) {
        localzzbqt.zzaH(zza(localzze.zzckf));
      }
    }
  }
  
  private long zzcc(Context paramContext)
  {
    try
    {
      long l = this.mContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).lastUpdateTime;
      return l;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      paramContext = String.valueOf(paramContext.getPackageName());
      Log.e("FirebaseRemoteConfig", String.valueOf(paramContext).length() + 25 + "Package [" + paramContext + "] was not found!");
    }
    return 0L;
  }
  
  /* Error */
  private static zzbqu.zze zzcd(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: aload_0
    //   7: ldc_w 434
    //   10: invokevirtual 438	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   13: astore_1
    //   14: aload_1
    //   15: astore_0
    //   16: aload_1
    //   17: invokestatic 442	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzl	(Ljava/io/InputStream;)[B
    //   20: invokestatic 448	com/google/android/gms/internal/zzbul:zzad	([B)Lcom/google/android/gms/internal/zzbul;
    //   23: astore_3
    //   24: aload_1
    //   25: astore_0
    //   26: new 356	com/google/android/gms/internal/zzbqu$zze
    //   29: dup
    //   30: invokespecial 449	com/google/android/gms/internal/zzbqu$zze:<init>	()V
    //   33: astore_2
    //   34: aload_1
    //   35: astore_0
    //   36: aload_2
    //   37: aload_3
    //   38: invokevirtual 452	com/google/android/gms/internal/zzbqu$zze:zzb	(Lcom/google/android/gms/internal/zzbul;)Lcom/google/android/gms/internal/zzbut;
    //   41: pop
    //   42: aload_1
    //   43: ifnull +7 -> 50
    //   46: aload_1
    //   47: invokevirtual 457	java/io/FileInputStream:close	()V
    //   50: aload_2
    //   51: areturn
    //   52: astore_0
    //   53: ldc_w 338
    //   56: ldc_w 459
    //   59: aload_0
    //   60: invokestatic 462	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   63: pop
    //   64: goto -14 -> 50
    //   67: astore_2
    //   68: aconst_null
    //   69: astore_1
    //   70: aload_1
    //   71: astore_0
    //   72: ldc_w 338
    //   75: iconst_3
    //   76: invokestatic 344	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   79: ifeq +16 -> 95
    //   82: aload_1
    //   83: astore_0
    //   84: ldc_w 338
    //   87: ldc_w 464
    //   90: aload_2
    //   91: invokestatic 466	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   94: pop
    //   95: aload_1
    //   96: ifnull -92 -> 4
    //   99: aload_1
    //   100: invokevirtual 457	java/io/FileInputStream:close	()V
    //   103: aconst_null
    //   104: areturn
    //   105: astore_0
    //   106: ldc_w 338
    //   109: ldc_w 459
    //   112: aload_0
    //   113: invokestatic 462	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   116: pop
    //   117: aconst_null
    //   118: areturn
    //   119: astore_2
    //   120: aconst_null
    //   121: astore_1
    //   122: aload_1
    //   123: astore_0
    //   124: ldc_w 338
    //   127: ldc_w 468
    //   130: aload_2
    //   131: invokestatic 462	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   134: pop
    //   135: aload_1
    //   136: ifnull -132 -> 4
    //   139: aload_1
    //   140: invokevirtual 457	java/io/FileInputStream:close	()V
    //   143: aconst_null
    //   144: areturn
    //   145: astore_0
    //   146: ldc_w 338
    //   149: ldc_w 459
    //   152: aload_0
    //   153: invokestatic 462	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   156: pop
    //   157: aconst_null
    //   158: areturn
    //   159: astore_1
    //   160: aconst_null
    //   161: astore_0
    //   162: aload_0
    //   163: ifnull +7 -> 170
    //   166: aload_0
    //   167: invokevirtual 457	java/io/FileInputStream:close	()V
    //   170: aload_1
    //   171: athrow
    //   172: astore_0
    //   173: ldc_w 338
    //   176: ldc_w 459
    //   179: aload_0
    //   180: invokestatic 462	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   183: pop
    //   184: goto -14 -> 170
    //   187: astore_1
    //   188: goto -26 -> 162
    //   191: astore_2
    //   192: goto -70 -> 122
    //   195: astore_2
    //   196: goto -126 -> 70
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	199	0	paramContext	Context
    //   13	127	1	localFileInputStream	java.io.FileInputStream
    //   159	12	1	localObject1	Object
    //   187	1	1	localObject2	Object
    //   33	18	2	localzze	zzbqu.zze
    //   67	24	2	localFileNotFoundException1	java.io.FileNotFoundException
    //   119	12	2	localIOException1	IOException
    //   191	1	2	localIOException2	IOException
    //   195	1	2	localFileNotFoundException2	java.io.FileNotFoundException
    //   23	15	3	localzzbul	com.google.android.gms.internal.zzbul
    // Exception table:
    //   from	to	target	type
    //   46	50	52	java/io/IOException
    //   6	14	67	java/io/FileNotFoundException
    //   99	103	105	java/io/IOException
    //   6	14	119	java/io/IOException
    //   139	143	145	java/io/IOException
    //   6	14	159	finally
    //   166	170	172	java/io/IOException
    //   16	24	187	finally
    //   26	34	187	finally
    //   36	42	187	finally
    //   72	82	187	finally
    //   84	95	187	finally
    //   124	135	187	finally
    //   16	24	191	java/io/IOException
    //   26	34	191	java/io/IOException
    //   36	42	191	java/io/IOException
    //   16	24	195	java/io/FileNotFoundException
    //   26	34	195	java/io/FileNotFoundException
    //   36	42	195	java/io/FileNotFoundException
  }
  
  private static byte[] zzl(InputStream paramInputStream)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    zzb(paramInputStream, localByteArrayOutputStream);
    return localByteArrayOutputStream.toByteArray();
  }
  
  public boolean activateFetched()
  {
    this.zzcjE.writeLock().lock();
    try
    {
      zzbqq localzzbqq = this.zzcjA;
      if (localzzbqq == null) {
        return false;
      }
      if (this.zzcjB != null)
      {
        l1 = this.zzcjB.getTimestamp();
        long l2 = this.zzcjA.getTimestamp();
        if (l1 >= l2) {
          return false;
        }
      }
      long l1 = this.zzcjA.getTimestamp();
      this.zzcjB = this.zzcjA;
      this.zzcjB.setTimestamp(System.currentTimeMillis());
      this.zzcjA = new zzbqq(null, l1);
      zzaas();
      return true;
    }
    finally
    {
      this.zzcjE.writeLock().unlock();
    }
  }
  
  public Task<Void> fetch()
  {
    return fetch(43200L);
  }
  
  public Task<Void> fetch(long paramLong)
  {
    final TaskCompletionSource localTaskCompletionSource = new TaskCompletionSource();
    this.zzcjE.readLock().lock();
    try
    {
      zzacz.zza.zza localzza = new zzacz.zza.zza();
      localzza.zzJ(paramLong);
      if (this.zzcjD.isDeveloperModeEnabled()) {
        localzza.zzG("_rcn_developer", "true");
      }
      localzza.zzdl(10200);
      new zzadf(this.mContext).zza(localzza.zzyT()).setResultCallback(new ResultCallback()
      {
        public void zza(@NonNull zzacz.zzb paramAnonymouszzb)
        {
          FirebaseRemoteConfig.this.zza(localTaskCompletionSource, paramAnonymouszzb);
        }
      });
      return localTaskCompletionSource.getTask();
    }
    finally
    {
      this.zzcjE.readLock().unlock();
    }
  }
  
  public boolean getBoolean(String paramString)
  {
    return getBoolean(paramString, "configns:firebase");
  }
  
  public boolean getBoolean(String paramString1, String paramString2)
  {
    if (paramString2 == null) {
      return false;
    }
    this.zzcjE.readLock().lock();
    try
    {
      boolean bool;
      if ((this.zzcjB != null) && (this.zzcjB.zzav(paramString1, paramString2)))
      {
        String str = new String(this.zzcjB.zzaw(paramString1, paramString2), zzbqs.UTF_8);
        bool = zzbqs.zzaHr.matcher(str).matches();
        if (bool) {
          return true;
        }
        bool = zzbqs.zzaHs.matcher(str).matches();
        if (bool) {
          return false;
        }
      }
      if ((this.zzcjC != null) && (this.zzcjC.zzav(paramString1, paramString2)))
      {
        paramString1 = new String(this.zzcjC.zzaw(paramString1, paramString2), zzbqs.UTF_8);
        bool = zzbqs.zzaHr.matcher(paramString1).matches();
        if (bool) {
          return true;
        }
        bool = zzbqs.zzaHs.matcher(paramString1).matches();
        if (bool) {
          return false;
        }
      }
      return false;
    }
    finally
    {
      this.zzcjE.readLock().unlock();
    }
  }
  
  public byte[] getByteArray(String paramString)
  {
    return getByteArray(paramString, "configns:firebase");
  }
  
  public byte[] getByteArray(String paramString1, String paramString2)
  {
    if (paramString2 == null) {
      return DEFAULT_VALUE_FOR_BYTE_ARRAY;
    }
    this.zzcjE.readLock().lock();
    try
    {
      if ((this.zzcjB != null) && (this.zzcjB.zzav(paramString1, paramString2)))
      {
        paramString1 = this.zzcjB.zzaw(paramString1, paramString2);
        return paramString1;
      }
      if ((this.zzcjC != null) && (this.zzcjC.zzav(paramString1, paramString2)))
      {
        paramString1 = this.zzcjC.zzaw(paramString1, paramString2);
        return paramString1;
      }
      paramString1 = DEFAULT_VALUE_FOR_BYTE_ARRAY;
      return paramString1;
    }
    finally
    {
      this.zzcjE.readLock().unlock();
    }
  }
  
  public double getDouble(String paramString)
  {
    return getDouble(paramString, "configns:firebase");
  }
  
  public double getDouble(String paramString1, String paramString2)
  {
    if (paramString2 == null) {
      return 0.0D;
    }
    this.zzcjE.readLock().lock();
    try
    {
      double d;
      if ((this.zzcjB != null) && (this.zzcjB.zzav(paramString1, paramString2)))
      {
        String str = new String(this.zzcjB.zzaw(paramString1, paramString2), zzbqs.UTF_8);
        try
        {
          d = Double.valueOf(str).doubleValue();
          return d;
        }
        catch (NumberFormatException localNumberFormatException) {}
      }
      if ((this.zzcjC != null) && (this.zzcjC.zzav(paramString1, paramString2)))
      {
        paramString1 = new String(this.zzcjC.zzaw(paramString1, paramString2), zzbqs.UTF_8);
        try
        {
          d = Double.valueOf(paramString1).doubleValue();
          return d;
        }
        catch (NumberFormatException paramString1) {}
      }
      return 0.0D;
    }
    finally
    {
      this.zzcjE.readLock().unlock();
    }
  }
  
  /* Error */
  public FirebaseRemoteConfigInfo getInfo()
  {
    // Byte code:
    //   0: new 593	com/google/android/gms/internal/zzbqr
    //   3: dup
    //   4: invokespecial 594	com/google/android/gms/internal/zzbqr:<init>	()V
    //   7: astore_3
    //   8: aload_0
    //   9: getfield 68	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzcjE	Ljava/util/concurrent/locks/ReadWriteLock;
    //   12: invokeinterface 194 1 0
    //   17: invokeinterface 199 1 0
    //   22: aload_0
    //   23: getfield 84	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzcjA	Lcom/google/android/gms/internal/zzbqq;
    //   26: ifnonnull +63 -> 89
    //   29: ldc2_w 595
    //   32: lstore_1
    //   33: aload_3
    //   34: lload_1
    //   35: invokevirtual 599	com/google/android/gms/internal/zzbqr:zzaR	(J)V
    //   38: aload_3
    //   39: aload_0
    //   40: getfield 72	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzcjD	Lcom/google/android/gms/internal/zzbqt;
    //   43: invokevirtual 602	com/google/android/gms/internal/zzbqt:getLastFetchStatus	()I
    //   46: invokevirtual 603	com/google/android/gms/internal/zzbqr:zzpV	(I)V
    //   49: aload_3
    //   50: new 605	com/google/firebase/remoteconfig/FirebaseRemoteConfigSettings$Builder
    //   53: dup
    //   54: invokespecial 606	com/google/firebase/remoteconfig/FirebaseRemoteConfigSettings$Builder:<init>	()V
    //   57: aload_0
    //   58: getfield 72	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzcjD	Lcom/google/android/gms/internal/zzbqt;
    //   61: invokevirtual 502	com/google/android/gms/internal/zzbqt:isDeveloperModeEnabled	()Z
    //   64: invokevirtual 610	com/google/firebase/remoteconfig/FirebaseRemoteConfigSettings$Builder:setDeveloperModeEnabled	(Z)Lcom/google/firebase/remoteconfig/FirebaseRemoteConfigSettings$Builder;
    //   67: invokevirtual 614	com/google/firebase/remoteconfig/FirebaseRemoteConfigSettings$Builder:build	()Lcom/google/firebase/remoteconfig/FirebaseRemoteConfigSettings;
    //   70: invokevirtual 618	com/google/android/gms/internal/zzbqr:setConfigSettings	(Lcom/google/firebase/remoteconfig/FirebaseRemoteConfigSettings;)V
    //   73: aload_0
    //   74: getfield 68	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzcjE	Ljava/util/concurrent/locks/ReadWriteLock;
    //   77: invokeinterface 194 1 0
    //   82: invokeinterface 222 1 0
    //   87: aload_3
    //   88: areturn
    //   89: aload_0
    //   90: getfield 84	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzcjA	Lcom/google/android/gms/internal/zzbqq;
    //   93: invokevirtual 481	com/google/android/gms/internal/zzbqq:getTimestamp	()J
    //   96: lstore_1
    //   97: goto -64 -> 33
    //   100: astore_3
    //   101: aload_0
    //   102: getfield 68	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzcjE	Ljava/util/concurrent/locks/ReadWriteLock;
    //   105: invokeinterface 194 1 0
    //   110: invokeinterface 222 1 0
    //   115: aload_3
    //   116: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	117	0	this	FirebaseRemoteConfig
    //   32	65	1	l	long
    //   7	81	3	localzzbqr	com.google.android.gms.internal.zzbqr
    //   100	16	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   22	29	100	finally
    //   33	73	100	finally
    //   89	97	100	finally
  }
  
  public Set<String> getKeysByPrefix(String paramString)
  {
    return getKeysByPrefix(paramString, "configns:firebase");
  }
  
  public Set<String> getKeysByPrefix(String paramString1, String paramString2)
  {
    this.zzcjE.readLock().lock();
    try
    {
      if (this.zzcjB == null)
      {
        paramString1 = new TreeSet();
        return paramString1;
      }
      paramString1 = this.zzcjB.zzax(paramString1, paramString2);
      return paramString1;
    }
    finally
    {
      this.zzcjE.readLock().unlock();
    }
  }
  
  public long getLong(String paramString)
  {
    return getLong(paramString, "configns:firebase");
  }
  
  public long getLong(String paramString1, String paramString2)
  {
    if (paramString2 == null) {
      return 0L;
    }
    this.zzcjE.readLock().lock();
    try
    {
      long l;
      if ((this.zzcjB != null) && (this.zzcjB.zzav(paramString1, paramString2)))
      {
        String str = new String(this.zzcjB.zzaw(paramString1, paramString2), zzbqs.UTF_8);
        try
        {
          l = Long.valueOf(str).longValue();
          return l;
        }
        catch (NumberFormatException localNumberFormatException) {}
      }
      if ((this.zzcjC != null) && (this.zzcjC.zzav(paramString1, paramString2)))
      {
        paramString1 = new String(this.zzcjC.zzaw(paramString1, paramString2), zzbqs.UTF_8);
        try
        {
          l = Long.valueOf(paramString1).longValue();
          return l;
        }
        catch (NumberFormatException paramString1) {}
      }
      return 0L;
    }
    finally
    {
      this.zzcjE.readLock().unlock();
    }
  }
  
  public String getString(String paramString)
  {
    return getString(paramString, "configns:firebase");
  }
  
  public String getString(String paramString1, String paramString2)
  {
    if (paramString2 == null) {
      return "";
    }
    this.zzcjE.readLock().lock();
    try
    {
      if ((this.zzcjB != null) && (this.zzcjB.zzav(paramString1, paramString2)))
      {
        paramString1 = new String(this.zzcjB.zzaw(paramString1, paramString2), zzbqs.UTF_8);
        return paramString1;
      }
      if ((this.zzcjC != null) && (this.zzcjC.zzav(paramString1, paramString2)))
      {
        paramString1 = new String(this.zzcjC.zzaw(paramString1, paramString2), zzbqs.UTF_8);
        return paramString1;
      }
      return "";
    }
    finally
    {
      this.zzcjE.readLock().unlock();
    }
  }
  
  public FirebaseRemoteConfigValue getValue(String paramString)
  {
    return getValue(paramString, "configns:firebase");
  }
  
  public FirebaseRemoteConfigValue getValue(String paramString1, String paramString2)
  {
    if (paramString2 == null) {
      return new zzbqs(DEFAULT_VALUE_FOR_BYTE_ARRAY, 0);
    }
    this.zzcjE.readLock().lock();
    try
    {
      if ((this.zzcjB != null) && (this.zzcjB.zzav(paramString1, paramString2)))
      {
        paramString1 = new zzbqs(this.zzcjB.zzaw(paramString1, paramString2), 2);
        return paramString1;
      }
      if ((this.zzcjC != null) && (this.zzcjC.zzav(paramString1, paramString2)))
      {
        paramString1 = new zzbqs(this.zzcjC.zzaw(paramString1, paramString2), 1);
        return paramString1;
      }
      paramString1 = new zzbqs(DEFAULT_VALUE_FOR_BYTE_ARRAY, 0);
      return paramString1;
    }
    finally
    {
      this.zzcjE.readLock().unlock();
    }
  }
  
  /* Error */
  public void setConfigSettings(FirebaseRemoteConfigSettings paramFirebaseRemoteConfigSettings)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 68	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzcjE	Ljava/util/concurrent/locks/ReadWriteLock;
    //   4: invokeinterface 309 1 0
    //   9: invokeinterface 199 1 0
    //   14: aload_0
    //   15: getfield 72	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzcjD	Lcom/google/android/gms/internal/zzbqt;
    //   18: invokevirtual 502	com/google/android/gms/internal/zzbqt:isDeveloperModeEnabled	()Z
    //   21: istore_3
    //   22: aload_1
    //   23: ifnonnull +37 -> 60
    //   26: iconst_0
    //   27: istore_2
    //   28: aload_0
    //   29: getfield 72	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzcjD	Lcom/google/android/gms/internal/zzbqt;
    //   32: iload_2
    //   33: invokevirtual 170	com/google/android/gms/internal/zzbqt:zzbc	(Z)V
    //   36: iload_3
    //   37: iload_2
    //   38: if_icmpeq +7 -> 45
    //   41: aload_0
    //   42: invokespecial 331	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzaas	()V
    //   45: aload_0
    //   46: getfield 68	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzcjE	Ljava/util/concurrent/locks/ReadWriteLock;
    //   49: invokeinterface 309 1 0
    //   54: invokeinterface 222 1 0
    //   59: return
    //   60: aload_1
    //   61: invokevirtual 658	com/google/firebase/remoteconfig/FirebaseRemoteConfigSettings:isDeveloperModeEnabled	()Z
    //   64: istore_2
    //   65: goto -37 -> 28
    //   68: astore_1
    //   69: aload_0
    //   70: getfield 68	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzcjE	Ljava/util/concurrent/locks/ReadWriteLock;
    //   73: invokeinterface 309 1 0
    //   78: invokeinterface 222 1 0
    //   83: aload_1
    //   84: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	85	0	this	FirebaseRemoteConfig
    //   0	85	1	paramFirebaseRemoteConfigSettings	FirebaseRemoteConfigSettings
    //   27	38	2	bool1	boolean
    //   21	18	3	bool2	boolean
    // Exception table:
    //   from	to	target	type
    //   14	22	68	finally
    //   28	36	68	finally
    //   41	45	68	finally
    //   60	65	68	finally
  }
  
  public void setDefaults(int paramInt)
  {
    setDefaults(paramInt, "configns:firebase");
  }
  
  /* Error */
  public void setDefaults(int paramInt, String paramString)
  {
    // Byte code:
    //   0: aload_2
    //   1: ifnonnull +24 -> 25
    //   4: ldc_w 338
    //   7: iconst_3
    //   8: invokestatic 344	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   11: ifeq +13 -> 24
    //   14: ldc_w 338
    //   17: ldc_w 666
    //   20: invokestatic 350	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   23: pop
    //   24: return
    //   25: aload_0
    //   26: getfield 68	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzcjE	Ljava/util/concurrent/locks/ReadWriteLock;
    //   29: invokeinterface 194 1 0
    //   34: invokeinterface 199 1 0
    //   39: aload_0
    //   40: getfield 72	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzcjD	Lcom/google/android/gms/internal/zzbqt;
    //   43: ifnull +107 -> 150
    //   46: aload_0
    //   47: getfield 72	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzcjD	Lcom/google/android/gms/internal/zzbqt;
    //   50: invokevirtual 670	com/google/android/gms/internal/zzbqt:zzaay	()Ljava/util/Map;
    //   53: ifnull +97 -> 150
    //   56: aload_0
    //   57: getfield 72	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzcjD	Lcom/google/android/gms/internal/zzbqt;
    //   60: invokevirtual 670	com/google/android/gms/internal/zzbqt:zzaay	()Ljava/util/Map;
    //   63: aload_2
    //   64: invokeinterface 272 2 0
    //   69: ifnull +81 -> 150
    //   72: aload_0
    //   73: getfield 72	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzcjD	Lcom/google/android/gms/internal/zzbqt;
    //   76: invokevirtual 670	com/google/android/gms/internal/zzbqt:zzaay	()Ljava/util/Map;
    //   79: aload_2
    //   80: invokeinterface 272 2 0
    //   85: checkcast 176	com/google/android/gms/internal/zzbqo
    //   88: astore 4
    //   90: iload_1
    //   91: aload 4
    //   93: invokevirtual 673	com/google/android/gms/internal/zzbqo:zzaat	()I
    //   96: if_icmpne +54 -> 150
    //   99: aload_0
    //   100: getfield 72	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzcjD	Lcom/google/android/gms/internal/zzbqt;
    //   103: invokevirtual 676	com/google/android/gms/internal/zzbqt:zzaaz	()J
    //   106: aload 4
    //   108: invokevirtual 679	com/google/android/gms/internal/zzbqo:zzaau	()J
    //   111: lcmp
    //   112: ifne +38 -> 150
    //   115: ldc_w 338
    //   118: iconst_3
    //   119: invokestatic 344	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   122: ifeq +13 -> 135
    //   125: ldc_w 338
    //   128: ldc_w 681
    //   131: invokestatic 350	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   134: pop
    //   135: aload_0
    //   136: getfield 68	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzcjE	Ljava/util/concurrent/locks/ReadWriteLock;
    //   139: invokeinterface 194 1 0
    //   144: invokeinterface 222 1 0
    //   149: return
    //   150: aload_0
    //   151: getfield 68	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzcjE	Ljava/util/concurrent/locks/ReadWriteLock;
    //   154: invokeinterface 194 1 0
    //   159: invokeinterface 222 1 0
    //   164: new 116	java/util/HashMap
    //   167: dup
    //   168: invokespecial 117	java/util/HashMap:<init>	()V
    //   171: astore 10
    //   173: aload_0
    //   174: getfield 70	com/google/firebase/remoteconfig/FirebaseRemoteConfig:mContext	Landroid/content/Context;
    //   177: invokevirtual 685	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   180: iload_1
    //   181: invokevirtual 691	android/content/res/Resources:getXml	(I)Landroid/content/res/XmlResourceParser;
    //   184: astore 11
    //   186: aload 11
    //   188: invokeinterface 696 1 0
    //   193: istore_3
    //   194: aconst_null
    //   195: astore 4
    //   197: aconst_null
    //   198: astore 9
    //   200: aconst_null
    //   201: astore 5
    //   203: iload_3
    //   204: iconst_1
    //   205: if_icmpeq +232 -> 437
    //   208: iload_3
    //   209: iconst_2
    //   210: if_icmpne +60 -> 270
    //   213: aload 11
    //   215: invokeinterface 699 1 0
    //   220: astore 8
    //   222: aload 4
    //   224: astore 7
    //   226: aload 5
    //   228: astore 6
    //   230: aload 11
    //   232: invokeinterface 701 1 0
    //   237: istore_3
    //   238: aload 6
    //   240: astore 5
    //   242: aload 7
    //   244: astore 4
    //   246: aload 8
    //   248: astore 9
    //   250: goto -47 -> 203
    //   253: astore_2
    //   254: aload_0
    //   255: getfield 68	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzcjE	Ljava/util/concurrent/locks/ReadWriteLock;
    //   258: invokeinterface 194 1 0
    //   263: invokeinterface 222 1 0
    //   268: aload_2
    //   269: athrow
    //   270: iload_3
    //   271: iconst_3
    //   272: if_icmpne +74 -> 346
    //   275: aload 5
    //   277: astore 6
    //   279: aload 4
    //   281: astore 7
    //   283: ldc_w 703
    //   286: aload 11
    //   288: invokeinterface 699 1 0
    //   293: invokevirtual 707	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   296: ifeq +190 -> 486
    //   299: aload 5
    //   301: astore 6
    //   303: aload 4
    //   305: astore 7
    //   307: aload 4
    //   309: ifnull +177 -> 486
    //   312: aload 5
    //   314: astore 6
    //   316: aload 4
    //   318: astore 7
    //   320: aload 5
    //   322: ifnull +164 -> 486
    //   325: aload 10
    //   327: aload 4
    //   329: aload 5
    //   331: invokeinterface 146 3 0
    //   336: pop
    //   337: aconst_null
    //   338: astore 6
    //   340: aconst_null
    //   341: astore 7
    //   343: goto +143 -> 486
    //   346: aload 5
    //   348: astore 6
    //   350: aload 4
    //   352: astore 7
    //   354: aload 9
    //   356: astore 8
    //   358: iload_3
    //   359: iconst_4
    //   360: if_icmpne -130 -> 230
    //   363: ldc_w 709
    //   366: aload 9
    //   368: invokevirtual 707	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   371: ifeq +23 -> 394
    //   374: aload 11
    //   376: invokeinterface 712 1 0
    //   381: astore 7
    //   383: aload 5
    //   385: astore 6
    //   387: aload 9
    //   389: astore 8
    //   391: goto -161 -> 230
    //   394: aload 5
    //   396: astore 6
    //   398: aload 4
    //   400: astore 7
    //   402: aload 9
    //   404: astore 8
    //   406: ldc_w 714
    //   409: aload 9
    //   411: invokevirtual 707	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   414: ifeq -184 -> 230
    //   417: aload 11
    //   419: invokeinterface 712 1 0
    //   424: astore 6
    //   426: aload 4
    //   428: astore 7
    //   430: aload 9
    //   432: astore 8
    //   434: goto -204 -> 230
    //   437: new 176	com/google/android/gms/internal/zzbqo
    //   440: dup
    //   441: iload_1
    //   442: aload_0
    //   443: getfield 72	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzcjD	Lcom/google/android/gms/internal/zzbqt;
    //   446: invokevirtual 676	com/google/android/gms/internal/zzbqt:zzaaz	()J
    //   449: invokespecial 185	com/google/android/gms/internal/zzbqo:<init>	(IJ)V
    //   452: astore 4
    //   454: aload_0
    //   455: getfield 72	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzcjD	Lcom/google/android/gms/internal/zzbqt;
    //   458: aload_2
    //   459: aload 4
    //   461: invokevirtual 717	com/google/android/gms/internal/zzbqt:zza	(Ljava/lang/String;Lcom/google/android/gms/internal/zzbqo;)V
    //   464: aload_0
    //   465: aload 10
    //   467: aload_2
    //   468: iconst_0
    //   469: invokespecial 719	com/google/firebase/remoteconfig/FirebaseRemoteConfig:zzc	(Ljava/util/Map;Ljava/lang/String;Z)V
    //   472: return
    //   473: astore_2
    //   474: ldc_w 338
    //   477: ldc_w 721
    //   480: aload_2
    //   481: invokestatic 462	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   484: pop
    //   485: return
    //   486: aconst_null
    //   487: astore 8
    //   489: goto -259 -> 230
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	492	0	this	FirebaseRemoteConfig
    //   0	492	1	paramInt	int
    //   0	492	2	paramString	String
    //   193	168	3	i	int
    //   88	372	4	localObject1	Object
    //   201	194	5	localObject2	Object
    //   228	197	6	localObject3	Object
    //   224	205	7	localObject4	Object
    //   220	268	8	localObject5	Object
    //   198	233	9	localObject6	Object
    //   171	295	10	localHashMap	HashMap
    //   184	234	11	localXmlResourceParser	android.content.res.XmlResourceParser
    // Exception table:
    //   from	to	target	type
    //   39	135	253	finally
    //   173	194	473	java/lang/Exception
    //   213	222	473	java/lang/Exception
    //   230	238	473	java/lang/Exception
    //   283	299	473	java/lang/Exception
    //   325	337	473	java/lang/Exception
    //   363	383	473	java/lang/Exception
    //   406	426	473	java/lang/Exception
    //   437	472	473	java/lang/Exception
  }
  
  public void setDefaults(Map<String, Object> paramMap)
  {
    setDefaults(paramMap, "configns:firebase");
  }
  
  public void setDefaults(Map<String, Object> paramMap, String paramString)
  {
    zzc(paramMap, paramString, true);
  }
  
  @VisibleForTesting
  void zza(TaskCompletionSource<Void> paramTaskCompletionSource, zzacz.zzb paramzzb)
  {
    if ((paramzzb == null) || (paramzzb.getStatus() == null))
    {
      this.zzcjD.zzpV(1);
      paramTaskCompletionSource.setException(new FirebaseRemoteConfigFetchException());
      zzaas();
      return;
    }
    int i = paramzzb.getStatus().getStatusCode();
    this.zzcjE.writeLock().lock();
    switch (i)
    {
    }
    for (;;)
    {
      try
      {
        if (paramzzb.getStatus().isSuccess()) {
          Log.w("FirebaseRemoteConfig", 45 + "Unknown (successful) status code: " + i);
        }
        this.zzcjD.zzpV(1);
        paramTaskCompletionSource.setException(new FirebaseRemoteConfigFetchException());
        zzaas();
        return;
      }
      finally
      {
        this.zzcjE.writeLock().unlock();
      }
      this.zzcjD.zzpV(1);
      paramTaskCompletionSource.setException(new FirebaseRemoteConfigFetchException());
      zzaas();
      continue;
      this.zzcjD.zzpV(2);
      paramTaskCompletionSource.setException(new FirebaseRemoteConfigFetchThrottledException(paramzzb.getThrottleEndTimeMillis()));
      zzaas();
      continue;
      this.zzcjD.zzpV(-1);
      String str1;
      HashMap localHashMap2;
      Iterator localIterator2;
      String str2;
      if ((this.zzcjA != null) && (!this.zzcjA.zzaaw()))
      {
        localMap = paramzzb.zzyU();
        localHashMap1 = new HashMap();
        localIterator1 = localMap.keySet().iterator();
        while (localIterator1.hasNext())
        {
          str1 = (String)localIterator1.next();
          localHashMap2 = new HashMap();
          localIterator2 = ((Set)localMap.get(str1)).iterator();
          while (localIterator2.hasNext())
          {
            str2 = (String)localIterator2.next();
            localHashMap2.put(str2, paramzzb.zza(str2, null, str1));
          }
          localHashMap1.put(str1, localHashMap2);
        }
        this.zzcjA = new zzbqq(localHashMap1, this.zzcjA.getTimestamp());
      }
      paramTaskCompletionSource.setResult(null);
      zzaas();
      continue;
      Map localMap = paramzzb.zzyU();
      HashMap localHashMap1 = new HashMap();
      Iterator localIterator1 = localMap.keySet().iterator();
      while (localIterator1.hasNext())
      {
        str1 = (String)localIterator1.next();
        localHashMap2 = new HashMap();
        localIterator2 = ((Set)localMap.get(str1)).iterator();
        while (localIterator2.hasNext())
        {
          str2 = (String)localIterator2.next();
          localHashMap2.put(str2, paramzzb.zza(str2, null, str1));
        }
        localHashMap1.put(str1, localHashMap2);
      }
      this.zzcjA = new zzbqq(localHashMap1, System.currentTimeMillis());
      this.zzcjD.zzpV(-1);
      paramTaskCompletionSource.setResult(null);
      zzaas();
    }
  }
  
  static class zza
    implements Executor
  {
    public void execute(Runnable paramRunnable)
    {
      new Thread(paramRunnable).start();
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\remoteconfig\FirebaseRemoteConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */