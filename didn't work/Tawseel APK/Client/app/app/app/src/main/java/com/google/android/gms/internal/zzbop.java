package com.google.android.gms.internal;

import java.io.PrintWriter;
import java.io.StringWriter;

public class zzbop
{
  private final String prefix;
  private final zzboq zzbZw;
  private final String zzcgE;
  
  public zzbop(zzboq paramzzboq, String paramString)
  {
    this(paramzzboq, paramString, null);
  }
  
  public zzbop(zzboq paramzzboq, String paramString1, String paramString2)
  {
    this.zzbZw = paramzzboq;
    this.zzcgE = paramString1;
    this.prefix = paramString2;
  }
  
  private long zzYU()
  {
    return System.currentTimeMillis();
  }
  
  private String zzj(String paramString, Object... paramVarArgs)
  {
    String str = paramString;
    if (paramVarArgs.length > 0) {
      str = String.format(paramString, paramVarArgs);
    }
    if (this.prefix == null) {
      return str;
    }
    paramString = this.prefix;
    return String.valueOf(paramString).length() + 3 + String.valueOf(str).length() + paramString + " - " + str;
  }
  
  private static String zzk(Throwable paramThrowable)
  {
    StringWriter localStringWriter = new StringWriter();
    paramThrowable.printStackTrace(new PrintWriter(localStringWriter));
    return localStringWriter.toString();
  }
  
  public void info(String paramString)
  {
    this.zzbZw.zzb(zzboq.zza.zzcgG, this.zzcgE, zzj(paramString, new Object[0]), zzYU());
  }
  
  public void warn(String paramString)
  {
    zze(paramString, null);
  }
  
  public boolean zzYT()
  {
    return this.zzbZw.zzWu().ordinal() <= zzboq.zza.zzcgF.ordinal();
  }
  
  public void zza(String paramString, Throwable paramThrowable, Object... paramVarArgs)
  {
    if (zzYT())
    {
      paramVarArgs = zzj(paramString, paramVarArgs);
      paramString = paramVarArgs;
      if (paramThrowable != null)
      {
        paramString = String.valueOf(zzk(paramThrowable));
        paramString = String.valueOf(paramVarArgs).length() + 1 + String.valueOf(paramString).length() + paramVarArgs + "\n" + paramString;
      }
      this.zzbZw.zzb(zzboq.zza.zzcgF, this.zzcgE, paramString, zzYU());
    }
  }
  
  public void zzd(String paramString, Throwable paramThrowable)
  {
    paramString = String.valueOf(zzj(paramString, new Object[0]));
    paramThrowable = String.valueOf(zzk(paramThrowable));
    paramString = String.valueOf(paramString).length() + 1 + String.valueOf(paramThrowable).length() + paramString + "\n" + paramThrowable;
    this.zzbZw.zzb(zzboq.zza.zzcgI, this.zzcgE, paramString, zzYU());
  }
  
  public void zze(String paramString, Throwable paramThrowable)
  {
    String str = zzj(paramString, new Object[0]);
    paramString = str;
    if (paramThrowable != null)
    {
      paramString = String.valueOf(zzk(paramThrowable));
      paramString = String.valueOf(str).length() + 1 + String.valueOf(paramString).length() + str + "\n" + paramString;
    }
    this.zzbZw.zzb(zzboq.zza.zzcgH, this.zzcgE, paramString, zzYU());
  }
  
  public void zzi(String paramString, Object... paramVarArgs)
  {
    zza(paramString, null, paramVarArgs);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */