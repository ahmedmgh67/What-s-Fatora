package com.google.android.gms.internal;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.List;

class zzbpm
{
  static zzb zzb(byte paramByte)
  {
    if (paramByte == 2) {
      return new zza();
    }
    return new zzc();
  }
  
  static class zza
    implements zzbpm.zzb
  {
    private List<byte[]> zzchE = new ArrayList();
    private int zzchF = 0;
    
    public boolean zzU(byte[] paramArrayOfByte)
    {
      this.zzchE.add(paramArrayOfByte);
      this.zzchF += paramArrayOfByte.length;
      return true;
    }
    
    public zzbps zzZD()
    {
      byte[] arrayOfByte1 = new byte[this.zzchF];
      int i = 0;
      int j = 0;
      while (i < this.zzchE.size())
      {
        byte[] arrayOfByte2 = (byte[])this.zzchE.get(i);
        System.arraycopy(arrayOfByte2, 0, arrayOfByte1, j, arrayOfByte2.length);
        j += arrayOfByte2.length;
        i += 1;
      }
      return new zzbps(arrayOfByte1);
    }
  }
  
  static abstract interface zzb
  {
    public abstract boolean zzU(byte[] paramArrayOfByte);
    
    public abstract zzbps zzZD();
  }
  
  static class zzc
    implements zzbpm.zzb
  {
    private static ThreadLocal<CharsetDecoder> zzchG = new ThreadLocal()
    {
      protected CharsetDecoder zzZE()
      {
        CharsetDecoder localCharsetDecoder = Charset.forName("UTF8").newDecoder();
        localCharsetDecoder.onMalformedInput(CodingErrorAction.REPORT);
        localCharsetDecoder.onUnmappableCharacter(CodingErrorAction.REPORT);
        return localCharsetDecoder;
      }
    };
    private static ThreadLocal<CharsetEncoder> zzchH = new ThreadLocal()
    {
      protected CharsetEncoder zzZF()
      {
        CharsetEncoder localCharsetEncoder = Charset.forName("UTF8").newEncoder();
        localCharsetEncoder.onMalformedInput(CodingErrorAction.REPORT);
        localCharsetEncoder.onUnmappableCharacter(CodingErrorAction.REPORT);
        return localCharsetEncoder;
      }
    };
    private StringBuilder zzchI = new StringBuilder();
    
    private String zzV(byte[] paramArrayOfByte)
    {
      try
      {
        paramArrayOfByte = ByteBuffer.wrap(paramArrayOfByte);
        paramArrayOfByte = ((CharsetDecoder)zzchG.get()).decode(paramArrayOfByte).toString();
        return paramArrayOfByte;
      }
      catch (CharacterCodingException paramArrayOfByte) {}
      return null;
    }
    
    public boolean zzU(byte[] paramArrayOfByte)
    {
      paramArrayOfByte = zzV(paramArrayOfByte);
      if (paramArrayOfByte != null)
      {
        this.zzchI.append(paramArrayOfByte);
        return true;
      }
      return false;
    }
    
    public zzbps zzZD()
    {
      return new zzbps(this.zzchI.toString());
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbpm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */