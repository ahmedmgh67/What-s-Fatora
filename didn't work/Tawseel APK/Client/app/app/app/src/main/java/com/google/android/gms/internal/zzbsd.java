package com.google.android.gms.internal;

import java.io.IOException;

public abstract class zzbsd<T>
{
  public abstract void zza(zzbtk paramzzbtk, T paramT)
    throws IOException;
  
  public final zzbrr zzaL(T paramT)
  {
    try
    {
      zzbsz localzzbsz = new zzbsz();
      zza(localzzbsz, paramT);
      paramT = localzzbsz.zzabU();
      return paramT;
    }
    catch (IOException paramT)
    {
      throw new zzbrs(paramT);
    }
  }
  
  public abstract T zzb(zzbti paramzzbti)
    throws IOException;
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbsd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */