package com.google.android.gms.internal;

import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class zzbpo
{
  private static final AtomicInteger zzchJ = new AtomicInteger(0);
  private static final Charset zzchK = Charset.forName("UTF-8");
  private static ThreadFactory zzchT = Executors.defaultThreadFactory();
  private static zzbpn zzchU = new zzbpn()
  {
    public void zza(Thread paramAnonymousThread, String paramAnonymousString)
    {
      paramAnonymousThread.setName(paramAnonymousString);
    }
  };
  private final int zzayy = zzchJ.incrementAndGet();
  private volatile zza zzchL = zza.zzchX;
  private volatile Socket zzchM = null;
  private zzbpp zzchN = null;
  private final URI zzchO;
  private final zzbpt zzchP;
  private final zzbpu zzchQ;
  private final zzbpr zzchR;
  private final Thread zzchS = getThreadFactory().newThread(new Runnable()
  {
    public void run()
    {
      zzbpo.zza(zzbpo.this);
    }
  });
  
  public zzbpo(URI paramURI, String paramString, Map<String, String> paramMap)
  {
    this.zzchO = paramURI;
    this.zzchR = new zzbpr(paramURI, paramString, paramMap);
    this.zzchP = new zzbpt(this);
    this.zzchQ = new zzbpu(this, "TubeSock", this.zzayy);
  }
  
  private Socket createSocket()
  {
    Object localObject1 = this.zzchO.getScheme();
    String str3 = this.zzchO.getHost();
    int i = this.zzchO.getPort();
    if ((localObject1 != null) && (((String)localObject1).equals("ws")))
    {
      if (i != -1) {
        break label396;
      }
      i = 80;
    }
    label396:
    for (;;)
    {
      Object localObject3;
      try
      {
        localObject1 = new Socket(str3, i);
        return (Socket)localObject1;
      }
      catch (UnknownHostException localUnknownHostException1)
      {
        localObject1 = String.valueOf(str3);
        if (((String)localObject1).length() != 0)
        {
          localObject1 = "unknown host: ".concat((String)localObject1);
          throw new zzbpq((String)localObject1, localUnknownHostException1);
        }
        localObject1 = new String("unknown host: ");
        continue;
      }
      catch (IOException localIOException1)
      {
        localObject3 = String.valueOf(this.zzchO);
        throw new zzbpq(String.valueOf(localObject3).length() + 31 + "error while creating socket to " + (String)localObject3, localIOException1);
      }
      if ((localIOException1 != null) && (localIOException1.equals("wss")))
      {
        int j = i;
        if (i == -1) {
          j = 443;
        }
        try
        {
          localObject3 = (SSLSocket)SSLSocketFactory.getDefault().createSocket(str3, j);
          localObject2 = localObject3;
          if (HttpsURLConnection.getDefaultHostnameVerifier().verify(str3, ((SSLSocket)localObject3).getSession())) {
            continue;
          }
          localObject2 = String.valueOf(this.zzchO);
          throw new zzbpq(String.valueOf(localObject2).length() + 39 + "Error while verifying secure socket to " + (String)localObject2);
        }
        catch (UnknownHostException localUnknownHostException2)
        {
          Object localObject2 = String.valueOf(str3);
          if (((String)localObject2).length() != 0) {}
          for (localObject2 = "unknown host: ".concat((String)localObject2);; localObject2 = new String("unknown host: ")) {
            throw new zzbpq((String)localObject2, localUnknownHostException2);
          }
        }
        catch (IOException localIOException2)
        {
          String str2 = String.valueOf(this.zzchO);
          throw new zzbpq(String.valueOf(str2).length() + 38 + "error while creating secure socket to " + str2, localIOException2);
        }
      }
      else
      {
        String str1 = String.valueOf(localIOException2);
        if (str1.length() != 0) {}
        for (str1 = "unsupported protocol: ".concat(str1);; str1 = new String("unsupported protocol: ")) {
          throw new zzbpq(str1);
        }
      }
    }
  }
  
  static ThreadFactory getThreadFactory()
  {
    return zzchT;
  }
  
  static zzbpn zzZG()
  {
    return zzchU;
  }
  
  private void zzZJ()
  {
    try
    {
      localObject1 = this.zzchL;
      zza localzza = zza.zzcib;
      if (localObject1 != localzza) {
        break label19;
      }
    }
    finally
    {
      try
      {
        Object localObject1;
        label19:
        this.zzchM.close();
        this.zzchL = zza.zzcib;
        this.zzchN.onClose();
      }
      catch (IOException localIOException)
      {
        throw new RuntimeException(localIOException);
      }
      localObject2 = finally;
    }
    return;
    this.zzchP.zzZQ();
    this.zzchQ.zzZT();
    localObject1 = this.zzchM;
    if (localObject1 == null) {}
  }
  
  private void zzZK()
  {
    try
    {
      this.zzchL = zza.zzcia;
      this.zzchQ.zzZT();
      this.zzchQ.zzb((byte)8, true, new byte[0]);
      return;
    }
    catch (IOException localIOException)
    {
      this.zzchN.zza(new zzbpq("Failed to send close frame", localIOException));
    }
  }
  
  /* Error */
  private void zzZM()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 270	com/google/android/gms/internal/zzbpo:createSocket	()Ljava/net/Socket;
    //   4: astore 5
    //   6: aload_0
    //   7: monitorenter
    //   8: aload_0
    //   9: aload 5
    //   11: putfield 81	com/google/android/gms/internal/zzbpo:zzchM	Ljava/net/Socket;
    //   14: aload_0
    //   15: getfield 79	com/google/android/gms/internal/zzbpo:zzchL	Lcom/google/android/gms/internal/zzbpo$zza;
    //   18: astore 6
    //   20: getstatic 235	com/google/android/gms/internal/zzbpo$zza:zzcib	Lcom/google/android/gms/internal/zzbpo$zza;
    //   23: astore 7
    //   25: aload 6
    //   27: aload 7
    //   29: if_acmpne +59 -> 88
    //   32: aload_0
    //   33: getfield 81	com/google/android/gms/internal/zzbpo:zzchM	Ljava/net/Socket;
    //   36: invokevirtual 244	java/net/Socket:close	()V
    //   39: aload_0
    //   40: aconst_null
    //   41: putfield 81	com/google/android/gms/internal/zzbpo:zzchM	Ljava/net/Socket;
    //   44: aload_0
    //   45: monitorexit
    //   46: aload_0
    //   47: invokevirtual 271	com/google/android/gms/internal/zzbpo:close	()V
    //   50: return
    //   51: astore 5
    //   53: new 251	java/lang/RuntimeException
    //   56: dup
    //   57: aload 5
    //   59: invokespecial 254	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   62: athrow
    //   63: astore 5
    //   65: aload_0
    //   66: monitorexit
    //   67: aload 5
    //   69: athrow
    //   70: astore 5
    //   72: aload_0
    //   73: getfield 83	com/google/android/gms/internal/zzbpo:zzchN	Lcom/google/android/gms/internal/zzbpp;
    //   76: aload 5
    //   78: invokeinterface 267 2 0
    //   83: aload_0
    //   84: invokevirtual 271	com/google/android/gms/internal/zzbpo:close	()V
    //   87: return
    //   88: aload_0
    //   89: monitorexit
    //   90: new 273	java/io/DataInputStream
    //   93: dup
    //   94: aload 5
    //   96: invokevirtual 277	java/net/Socket:getInputStream	()Ljava/io/InputStream;
    //   99: invokespecial 280	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   102: astore 6
    //   104: aload 5
    //   106: invokevirtual 284	java/net/Socket:getOutputStream	()Ljava/io/OutputStream;
    //   109: astore 7
    //   111: aload 7
    //   113: aload_0
    //   114: getfield 111	com/google/android/gms/internal/zzbpo:zzchR	Lcom/google/android/gms/internal/zzbpr;
    //   117: invokevirtual 288	com/google/android/gms/internal/zzbpr:zzZO	()[B
    //   120: invokevirtual 294	java/io/OutputStream:write	([B)V
    //   123: sipush 1000
    //   126: newarray <illegal type>
    //   128: astore 5
    //   130: new 296	java/util/ArrayList
    //   133: dup
    //   134: invokespecial 297	java/util/ArrayList:<init>	()V
    //   137: astore 8
    //   139: iconst_0
    //   140: istore_2
    //   141: iconst_0
    //   142: istore_3
    //   143: iload_3
    //   144: ifne +264 -> 408
    //   147: aload 6
    //   149: invokevirtual 300	java/io/DataInputStream:read	()I
    //   152: istore 4
    //   154: iload 4
    //   156: iconst_m1
    //   157: if_icmpne +73 -> 230
    //   160: new 173	com/google/android/gms/internal/zzbpq
    //   163: dup
    //   164: ldc_w 302
    //   167: invokespecial 225	com/google/android/gms/internal/zzbpq:<init>	(Ljava/lang/String;)V
    //   170: athrow
    //   171: astore 6
    //   173: aload_0
    //   174: getfield 83	com/google/android/gms/internal/zzbpo:zzchN	Lcom/google/android/gms/internal/zzbpp;
    //   177: astore 7
    //   179: aload 6
    //   181: invokevirtual 305	java/io/IOException:getMessage	()Ljava/lang/String;
    //   184: invokestatic 162	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   187: astore 5
    //   189: aload 5
    //   191: invokevirtual 165	java/lang/String:length	()I
    //   194: ifeq +364 -> 558
    //   197: ldc_w 307
    //   200: aload 5
    //   202: invokevirtual 171	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
    //   205: astore 5
    //   207: aload 7
    //   209: new 173	com/google/android/gms/internal/zzbpq
    //   212: dup
    //   213: aload 5
    //   215: aload 6
    //   217: invokespecial 176	com/google/android/gms/internal/zzbpq:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   220: invokeinterface 267 2 0
    //   225: aload_0
    //   226: invokevirtual 271	com/google/android/gms/internal/zzbpo:close	()V
    //   229: return
    //   230: iload 4
    //   232: i2b
    //   233: istore_1
    //   234: aload 5
    //   236: iload_2
    //   237: iload_1
    //   238: bastore
    //   239: iload_2
    //   240: iconst_1
    //   241: iadd
    //   242: istore 4
    //   244: aload 5
    //   246: iload 4
    //   248: iconst_1
    //   249: isub
    //   250: baload
    //   251: bipush 10
    //   253: if_icmpne +75 -> 328
    //   256: aload 5
    //   258: iload 4
    //   260: iconst_2
    //   261: isub
    //   262: baload
    //   263: bipush 13
    //   265: if_icmpne +63 -> 328
    //   268: new 149	java/lang/String
    //   271: dup
    //   272: aload 5
    //   274: getstatic 59	com/google/android/gms/internal/zzbpo:zzchK	Ljava/nio/charset/Charset;
    //   277: invokespecial 310	java/lang/String:<init>	([BLjava/nio/charset/Charset;)V
    //   280: astore 5
    //   282: aload 5
    //   284: invokevirtual 313	java/lang/String:trim	()Ljava/lang/String;
    //   287: ldc_w 315
    //   290: invokevirtual 153	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   293: ifeq +19 -> 312
    //   296: iconst_1
    //   297: istore_2
    //   298: sipush 1000
    //   301: newarray <illegal type>
    //   303: astore 5
    //   305: iload_2
    //   306: istore_3
    //   307: iconst_0
    //   308: istore_2
    //   309: goto -166 -> 143
    //   312: aload 8
    //   314: aload 5
    //   316: invokevirtual 313	java/lang/String:trim	()Ljava/lang/String;
    //   319: invokevirtual 318	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   322: pop
    //   323: iload_3
    //   324: istore_2
    //   325: goto -27 -> 298
    //   328: iload 4
    //   330: istore_2
    //   331: iload 4
    //   333: sipush 1000
    //   336: if_icmpne -193 -> 143
    //   339: new 149	java/lang/String
    //   342: dup
    //   343: aload 5
    //   345: getstatic 59	com/google/android/gms/internal/zzbpo:zzchK	Ljava/nio/charset/Charset;
    //   348: invokespecial 310	java/lang/String:<init>	([BLjava/nio/charset/Charset;)V
    //   351: invokestatic 162	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   354: astore 5
    //   356: aload 5
    //   358: invokevirtual 165	java/lang/String:length	()I
    //   361: ifeq +32 -> 393
    //   364: ldc_w 320
    //   367: aload 5
    //   369: invokevirtual 171	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
    //   372: astore 5
    //   374: new 173	com/google/android/gms/internal/zzbpq
    //   377: dup
    //   378: aload 5
    //   380: invokespecial 225	com/google/android/gms/internal/zzbpq:<init>	(Ljava/lang/String;)V
    //   383: athrow
    //   384: astore 5
    //   386: aload_0
    //   387: invokevirtual 271	com/google/android/gms/internal/zzbpo:close	()V
    //   390: aload 5
    //   392: athrow
    //   393: new 149	java/lang/String
    //   396: dup
    //   397: ldc_w 320
    //   400: invokespecial 179	java/lang/String:<init>	(Ljava/lang/String;)V
    //   403: astore 5
    //   405: goto -31 -> 374
    //   408: aload_0
    //   409: getfield 111	com/google/android/gms/internal/zzbpo:zzchR	Lcom/google/android/gms/internal/zzbpr;
    //   412: aload 8
    //   414: iconst_0
    //   415: invokevirtual 324	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   418: checkcast 149	java/lang/String
    //   421: invokevirtual 327	com/google/android/gms/internal/zzbpr:zzjd	(Ljava/lang/String;)V
    //   424: aload 8
    //   426: iconst_0
    //   427: invokevirtual 330	java/util/ArrayList:remove	(I)Ljava/lang/Object;
    //   430: pop
    //   431: new 332	java/util/HashMap
    //   434: dup
    //   435: invokespecial 333	java/util/HashMap:<init>	()V
    //   438: astore 5
    //   440: aload 8
    //   442: invokevirtual 337	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   445: astore 8
    //   447: aload 8
    //   449: invokeinterface 343 1 0
    //   454: ifeq +39 -> 493
    //   457: aload 8
    //   459: invokeinterface 347 1 0
    //   464: checkcast 149	java/lang/String
    //   467: ldc_w 349
    //   470: iconst_2
    //   471: invokevirtual 353	java/lang/String:split	(Ljava/lang/String;I)[Ljava/lang/String;
    //   474: astore 9
    //   476: aload 5
    //   478: aload 9
    //   480: iconst_0
    //   481: aaload
    //   482: aload 9
    //   484: iconst_1
    //   485: aaload
    //   486: invokevirtual 357	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   489: pop
    //   490: goto -43 -> 447
    //   493: aload_0
    //   494: getfield 111	com/google/android/gms/internal/zzbpo:zzchR	Lcom/google/android/gms/internal/zzbpr;
    //   497: aload 5
    //   499: invokevirtual 361	com/google/android/gms/internal/zzbpr:zzd	(Ljava/util/HashMap;)V
    //   502: aload_0
    //   503: getfield 125	com/google/android/gms/internal/zzbpo:zzchQ	Lcom/google/android/gms/internal/zzbpu;
    //   506: aload 7
    //   508: invokevirtual 364	com/google/android/gms/internal/zzbpu:zzb	(Ljava/io/OutputStream;)V
    //   511: aload_0
    //   512: getfield 116	com/google/android/gms/internal/zzbpo:zzchP	Lcom/google/android/gms/internal/zzbpt;
    //   515: aload 6
    //   517: invokevirtual 367	com/google/android/gms/internal/zzbpt:zza	(Ljava/io/DataInputStream;)V
    //   520: aload_0
    //   521: getstatic 370	com/google/android/gms/internal/zzbpo$zza:zzchZ	Lcom/google/android/gms/internal/zzbpo$zza;
    //   524: putfield 79	com/google/android/gms/internal/zzbpo:zzchL	Lcom/google/android/gms/internal/zzbpo$zza;
    //   527: aload_0
    //   528: getfield 125	com/google/android/gms/internal/zzbpo:zzchQ	Lcom/google/android/gms/internal/zzbpu;
    //   531: invokevirtual 374	com/google/android/gms/internal/zzbpu:zzZN	()Ljava/lang/Thread;
    //   534: invokevirtual 379	java/lang/Thread:start	()V
    //   537: aload_0
    //   538: getfield 83	com/google/android/gms/internal/zzbpo:zzchN	Lcom/google/android/gms/internal/zzbpp;
    //   541: invokeinterface 382 1 0
    //   546: aload_0
    //   547: getfield 116	com/google/android/gms/internal/zzbpo:zzchP	Lcom/google/android/gms/internal/zzbpt;
    //   550: invokevirtual 385	com/google/android/gms/internal/zzbpt:run	()V
    //   553: aload_0
    //   554: invokevirtual 271	com/google/android/gms/internal/zzbpo:close	()V
    //   557: return
    //   558: new 149	java/lang/String
    //   561: dup
    //   562: ldc_w 307
    //   565: invokespecial 179	java/lang/String:<init>	(Ljava/lang/String;)V
    //   568: astore 5
    //   570: goto -363 -> 207
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	573	0	this	zzbpo
    //   233	5	1	i	int
    //   140	191	2	j	int
    //   142	182	3	k	int
    //   152	185	4	m	int
    //   4	6	5	localSocket	Socket
    //   51	7	5	localIOException1	IOException
    //   63	5	5	localObject1	Object
    //   70	35	5	localzzbpq	zzbpq
    //   128	251	5	localObject2	Object
    //   384	7	5	localObject3	Object
    //   403	166	5	localObject4	Object
    //   18	130	6	localObject5	Object
    //   171	345	6	localIOException2	IOException
    //   23	484	7	localObject6	Object
    //   137	321	8	localObject7	Object
    //   474	9	9	arrayOfString	String[]
    // Exception table:
    //   from	to	target	type
    //   32	39	51	java/io/IOException
    //   8	25	63	finally
    //   32	39	63	finally
    //   39	46	63	finally
    //   53	63	63	finally
    //   65	67	63	finally
    //   88	90	63	finally
    //   0	8	70	com/google/android/gms/internal/zzbpq
    //   67	70	70	com/google/android/gms/internal/zzbpq
    //   90	139	70	com/google/android/gms/internal/zzbpq
    //   147	154	70	com/google/android/gms/internal/zzbpq
    //   160	171	70	com/google/android/gms/internal/zzbpq
    //   268	296	70	com/google/android/gms/internal/zzbpq
    //   298	305	70	com/google/android/gms/internal/zzbpq
    //   312	323	70	com/google/android/gms/internal/zzbpq
    //   339	374	70	com/google/android/gms/internal/zzbpq
    //   374	384	70	com/google/android/gms/internal/zzbpq
    //   393	405	70	com/google/android/gms/internal/zzbpq
    //   408	447	70	com/google/android/gms/internal/zzbpq
    //   447	490	70	com/google/android/gms/internal/zzbpq
    //   493	553	70	com/google/android/gms/internal/zzbpq
    //   0	8	171	java/io/IOException
    //   67	70	171	java/io/IOException
    //   90	139	171	java/io/IOException
    //   147	154	171	java/io/IOException
    //   160	171	171	java/io/IOException
    //   268	296	171	java/io/IOException
    //   298	305	171	java/io/IOException
    //   312	323	171	java/io/IOException
    //   339	374	171	java/io/IOException
    //   374	384	171	java/io/IOException
    //   393	405	171	java/io/IOException
    //   408	447	171	java/io/IOException
    //   447	490	171	java/io/IOException
    //   493	553	171	java/io/IOException
    //   0	8	384	finally
    //   67	70	384	finally
    //   72	83	384	finally
    //   90	139	384	finally
    //   147	154	384	finally
    //   160	171	384	finally
    //   173	207	384	finally
    //   207	225	384	finally
    //   268	296	384	finally
    //   298	305	384	finally
    //   312	323	384	finally
    //   339	374	384	finally
    //   374	384	384	finally
    //   393	405	384	finally
    //   408	447	384	finally
    //   447	490	384	finally
    //   493	553	384	finally
    //   558	570	384	finally
  }
  
  /* Error */
  private void zza(byte paramByte, byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 79	com/google/android/gms/internal/zzbpo:zzchL	Lcom/google/android/gms/internal/zzbpo$zza;
    //   6: getstatic 370	com/google/android/gms/internal/zzbpo$zza:zzchZ	Lcom/google/android/gms/internal/zzbpo$zza;
    //   9: if_acmpeq +25 -> 34
    //   12: aload_0
    //   13: getfield 83	com/google/android/gms/internal/zzbpo:zzchN	Lcom/google/android/gms/internal/zzbpp;
    //   16: new 173	com/google/android/gms/internal/zzbpq
    //   19: dup
    //   20: ldc_w 388
    //   23: invokespecial 225	com/google/android/gms/internal/zzbpq:<init>	(Ljava/lang/String;)V
    //   26: invokeinterface 267 2 0
    //   31: aload_0
    //   32: monitorexit
    //   33: return
    //   34: aload_0
    //   35: getfield 125	com/google/android/gms/internal/zzbpo:zzchQ	Lcom/google/android/gms/internal/zzbpu;
    //   38: iload_1
    //   39: iconst_1
    //   40: aload_2
    //   41: invokevirtual 262	com/google/android/gms/internal/zzbpu:zzb	(BZ[B)V
    //   44: goto -13 -> 31
    //   47: astore_2
    //   48: aload_0
    //   49: getfield 83	com/google/android/gms/internal/zzbpo:zzchN	Lcom/google/android/gms/internal/zzbpp;
    //   52: new 173	com/google/android/gms/internal/zzbpq
    //   55: dup
    //   56: ldc_w 390
    //   59: aload_2
    //   60: invokespecial 176	com/google/android/gms/internal/zzbpq:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   63: invokeinterface 267 2 0
    //   68: aload_0
    //   69: invokevirtual 271	com/google/android/gms/internal/zzbpo:close	()V
    //   72: goto -41 -> 31
    //   75: astore_2
    //   76: aload_0
    //   77: monitorexit
    //   78: aload_2
    //   79: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	80	0	this	zzbpo
    //   0	80	1	paramByte	byte
    //   0	80	2	paramArrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   34	44	47	java/io/IOException
    //   2	31	75	finally
    //   34	44	75	finally
    //   48	72	75	finally
  }
  
  public static void zza(ThreadFactory paramThreadFactory, zzbpn paramzzbpn)
  {
    zzchT = paramThreadFactory;
    zzchU = paramzzbpn;
  }
  
  public void close()
  {
    for (;;)
    {
      try
      {
        int i = 3.zzchW[this.zzchL.ordinal()];
        switch (i)
        {
        case 4: 
        case 5: 
        default: 
          return;
        }
      }
      finally {}
      this.zzchL = zza.zzcib;
      continue;
      zzZJ();
      continue;
      zzZK();
    }
  }
  
  /* Error */
  public void connect()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 79	com/google/android/gms/internal/zzbpo:zzchL	Lcom/google/android/gms/internal/zzbpo$zza;
    //   6: getstatic 77	com/google/android/gms/internal/zzbpo$zza:zzchX	Lcom/google/android/gms/internal/zzbpo$zza;
    //   9: if_acmpeq +29 -> 38
    //   12: aload_0
    //   13: getfield 83	com/google/android/gms/internal/zzbpo:zzchN	Lcom/google/android/gms/internal/zzbpp;
    //   16: new 173	com/google/android/gms/internal/zzbpq
    //   19: dup
    //   20: ldc_w 407
    //   23: invokespecial 225	com/google/android/gms/internal/zzbpq:<init>	(Ljava/lang/String;)V
    //   26: invokeinterface 267 2 0
    //   31: aload_0
    //   32: invokevirtual 271	com/google/android/gms/internal/zzbpo:close	()V
    //   35: aload_0
    //   36: monitorexit
    //   37: return
    //   38: invokestatic 409	com/google/android/gms/internal/zzbpo:zzZG	()Lcom/google/android/gms/internal/zzbpn;
    //   41: astore_2
    //   42: aload_0
    //   43: invokevirtual 410	com/google/android/gms/internal/zzbpo:zzZN	()Ljava/lang/Thread;
    //   46: astore_3
    //   47: ldc_w 412
    //   50: invokestatic 162	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   53: astore 4
    //   55: aload_0
    //   56: getfield 89	com/google/android/gms/internal/zzbpo:zzayy	I
    //   59: istore_1
    //   60: aload_2
    //   61: aload_3
    //   62: new 181	java/lang/StringBuilder
    //   65: dup
    //   66: aload 4
    //   68: invokestatic 162	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   71: invokevirtual 165	java/lang/String:length	()I
    //   74: bipush 11
    //   76: iadd
    //   77: invokespecial 182	java/lang/StringBuilder:<init>	(I)V
    //   80: aload 4
    //   82: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   85: iload_1
    //   86: invokevirtual 415	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   89: invokevirtual 191	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   92: invokeinterface 420 3 0
    //   97: aload_0
    //   98: getstatic 423	com/google/android/gms/internal/zzbpo$zza:zzchY	Lcom/google/android/gms/internal/zzbpo$zza;
    //   101: putfield 79	com/google/android/gms/internal/zzbpo:zzchL	Lcom/google/android/gms/internal/zzbpo$zza;
    //   104: aload_0
    //   105: invokevirtual 410	com/google/android/gms/internal/zzbpo:zzZN	()Ljava/lang/Thread;
    //   108: invokevirtual 379	java/lang/Thread:start	()V
    //   111: goto -76 -> 35
    //   114: astore_2
    //   115: aload_0
    //   116: monitorexit
    //   117: aload_2
    //   118: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	119	0	this	zzbpo
    //   59	27	1	i	int
    //   41	20	2	localzzbpn	zzbpn
    //   114	4	2	localObject	Object
    //   46	16	3	localThread	Thread
    //   53	28	4	str	String
    // Exception table:
    //   from	to	target	type
    //   2	35	114	finally
    //   38	111	114	finally
  }
  
  void zzW(byte[] paramArrayOfByte)
  {
    try
    {
      zza((byte)10, paramArrayOfByte);
      return;
    }
    finally
    {
      paramArrayOfByte = finally;
      throw paramArrayOfByte;
    }
  }
  
  zzbpp zzZH()
  {
    return this.zzchN;
  }
  
  void zzZI()
  {
    zzZJ();
  }
  
  public void zzZL()
    throws InterruptedException
  {
    if (this.zzchQ.zzZN().getState() != Thread.State.NEW) {
      this.zzchQ.zzZN().join();
    }
    zzZN().join();
  }
  
  Thread zzZN()
  {
    return this.zzchS;
  }
  
  public void zza(zzbpp paramzzbpp)
  {
    this.zzchN = paramzzbpp;
  }
  
  void zzb(zzbpq paramzzbpq)
  {
    this.zzchN.zza(paramzzbpq);
    if (this.zzchL == zza.zzchZ) {
      close();
    }
    zzZJ();
  }
  
  public void zziT(String paramString)
  {
    try
    {
      zza((byte)1, paramString.getBytes(zzchK));
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  private static enum zza
  {
    private zza() {}
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbpo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */