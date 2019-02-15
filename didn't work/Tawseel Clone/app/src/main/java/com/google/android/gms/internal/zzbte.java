package com.google.android.gms.internal;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public final class zzbte
  extends zzbsd<Time>
{
  public static final zzbse zzcnX = new zzbse()
  {
    public <T> zzbsd<T> zza(zzbrl paramAnonymouszzbrl, zzbth<T> paramAnonymouszzbth)
    {
      if (paramAnonymouszzbth.zzacb() == Time.class) {
        return new zzbte();
      }
      return null;
    }
  };
  private final DateFormat zzcox = new SimpleDateFormat("hh:mm:ss a");
  
  public void zza(zzbtk paramzzbtk, Time paramTime)
    throws IOException
  {
    if (paramTime == null) {}
    for (paramTime = null;; paramTime = this.zzcox.format(paramTime)) {
      try
      {
        paramzzbtk.zzjX(paramTime);
        return;
      }
      finally {}
    }
  }
  
  /* Error */
  public Time zzn(zzbti paramzzbti)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokevirtual 65	com/google/android/gms/internal/zzbti:zzabQ	()Lcom/google/android/gms/internal/zzbtj;
    //   6: getstatic 71	com/google/android/gms/internal/zzbtj:zzcqa	Lcom/google/android/gms/internal/zzbtj;
    //   9: if_acmpne +13 -> 22
    //   12: aload_1
    //   13: invokevirtual 74	com/google/android/gms/internal/zzbti:nextNull	()V
    //   16: aconst_null
    //   17: astore_1
    //   18: aload_0
    //   19: monitorexit
    //   20: aload_1
    //   21: areturn
    //   22: new 35	java/sql/Time
    //   25: dup
    //   26: aload_0
    //   27: getfield 29	com/google/android/gms/internal/zzbte:zzcox	Ljava/text/DateFormat;
    //   30: aload_1
    //   31: invokevirtual 78	com/google/android/gms/internal/zzbti:nextString	()Ljava/lang/String;
    //   34: invokevirtual 82	java/text/DateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   37: invokevirtual 88	java/util/Date:getTime	()J
    //   40: invokespecial 91	java/sql/Time:<init>	(J)V
    //   43: astore_1
    //   44: goto -26 -> 18
    //   47: astore_1
    //   48: new 93	com/google/android/gms/internal/zzbsa
    //   51: dup
    //   52: aload_1
    //   53: invokespecial 96	com/google/android/gms/internal/zzbsa:<init>	(Ljava/lang/Throwable;)V
    //   56: athrow
    //   57: astore_1
    //   58: aload_0
    //   59: monitorexit
    //   60: aload_1
    //   61: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	62	0	this	zzbte
    //   0	62	1	paramzzbti	zzbti
    // Exception table:
    //   from	to	target	type
    //   22	44	47	java/text/ParseException
    //   2	16	57	finally
    //   22	44	57	finally
    //   48	57	57	finally
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbte.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */