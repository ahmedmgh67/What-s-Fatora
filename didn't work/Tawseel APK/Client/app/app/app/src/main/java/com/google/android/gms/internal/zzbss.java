package com.google.android.gms.internal;

import java.io.EOFException;
import java.io.IOException;
import java.io.Writer;

public final class zzbss
{
  public static Writer zza(Appendable paramAppendable)
  {
    if ((paramAppendable instanceof Writer)) {
      return (Writer)paramAppendable;
    }
    return new zza(paramAppendable, null);
  }
  
  public static void zzb(zzbrr paramzzbrr, zzbtk paramzzbtk)
    throws IOException
  {
    zzbtg.zzcpp.zza(paramzzbtk, paramzzbrr);
  }
  
  public static zzbrr zzh(zzbti paramzzbti)
    throws zzbrv
  {
    int i = 1;
    try
    {
      paramzzbti.zzabQ();
      i = 0;
      paramzzbti = (zzbrr)zzbtg.zzcpp.zzb(paramzzbti);
      return paramzzbti;
    }
    catch (EOFException paramzzbti)
    {
      if (i != 0) {
        return zzbrt.zzcmL;
      }
      throw new zzbsa(paramzzbti);
    }
    catch (zzbtl paramzzbti)
    {
      throw new zzbsa(paramzzbti);
    }
    catch (IOException paramzzbti)
    {
      throw new zzbrs(paramzzbti);
    }
    catch (NumberFormatException paramzzbti)
    {
      throw new zzbsa(paramzzbti);
    }
  }
  
  private static final class zza
    extends Writer
  {
    private final Appendable zzcnQ;
    private final zza zzcnR = new zza();
    
    private zza(Appendable paramAppendable)
    {
      this.zzcnQ = paramAppendable;
    }
    
    public void close() {}
    
    public void flush() {}
    
    public void write(int paramInt)
      throws IOException
    {
      this.zzcnQ.append((char)paramInt);
    }
    
    public void write(char[] paramArrayOfChar, int paramInt1, int paramInt2)
      throws IOException
    {
      this.zzcnR.zzcnS = paramArrayOfChar;
      this.zzcnQ.append(this.zzcnR, paramInt1, paramInt1 + paramInt2);
    }
    
    static class zza
      implements CharSequence
    {
      char[] zzcnS;
      
      public char charAt(int paramInt)
      {
        return this.zzcnS[paramInt];
      }
      
      public int length()
      {
        return this.zzcnS.length;
      }
      
      public CharSequence subSequence(int paramInt1, int paramInt2)
      {
        return new String(this.zzcnS, paramInt1, paramInt2 - paramInt1);
      }
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbss.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */