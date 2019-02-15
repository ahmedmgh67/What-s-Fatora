package com.google.android.gms.internal;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;

class zzbpt
{
  private zzbpp zzchN = null;
  private DataInputStream zzcii = null;
  private zzbpo zzcij = null;
  private byte[] zzcik = new byte[112];
  private zzbpm.zzb zzcil;
  private volatile boolean zzcim = false;
  
  zzbpt(zzbpo paramzzbpo)
  {
    this.zzcij = paramzzbpo;
  }
  
  private int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    this.zzcii.readFully(paramArrayOfByte, paramInt1, paramInt2);
    return paramInt2;
  }
  
  private void zzX(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte.length <= 125)
    {
      this.zzcij.zzW(paramArrayOfByte);
      return;
    }
    throw new zzbpq("PING frame too long");
  }
  
  private void zza(boolean paramBoolean, byte paramByte, byte[] paramArrayOfByte)
  {
    if (paramByte == 9) {
      if (paramBoolean) {
        zzX(paramArrayOfByte);
      }
    }
    do
    {
      return;
      throw new zzbpq("PING must not fragment across frames");
      if ((this.zzcil != null) && (paramByte != 0)) {
        throw new zzbpq("Failed to continue outstanding frame");
      }
      if ((this.zzcil == null) && (paramByte == 0)) {
        throw new zzbpq("Received continuing frame, but there's nothing to continue");
      }
      if (this.zzcil == null) {
        this.zzcil = zzbpm.zzb(paramByte);
      }
      if (!this.zzcil.zzU(paramArrayOfByte)) {
        throw new zzbpq("Failed to decode frame");
      }
    } while (!paramBoolean);
    paramArrayOfByte = this.zzcil.zzZD();
    this.zzcil = null;
    this.zzchN.zza(paramArrayOfByte);
  }
  
  private void zzc(zzbpq paramzzbpq)
  {
    zzZQ();
    this.zzcij.zzb(paramzzbpq);
  }
  
  private long zze(byte[] paramArrayOfByte, int paramInt)
  {
    return (paramArrayOfByte[(paramInt + 0)] << 56) + ((paramArrayOfByte[(paramInt + 1)] & 0xFF) << 48) + ((paramArrayOfByte[(paramInt + 2)] & 0xFF) << 40) + ((paramArrayOfByte[(paramInt + 3)] & 0xFF) << 32) + ((paramArrayOfByte[(paramInt + 4)] & 0xFF) << 24) + ((paramArrayOfByte[(paramInt + 5)] & 0xFF) << 16) + ((paramArrayOfByte[(paramInt + 6)] & 0xFF) << 8) + ((paramArrayOfByte[(paramInt + 7)] & 0xFF) << 0);
  }
  
  void run()
  {
    this.zzchN = this.zzcij.zzZH();
    if (!this.zzcim) {}
    for (;;)
    {
      byte b;
      try
      {
        j = read(this.zzcik, 0, 1) + 0;
        if ((this.zzcik[0] & 0x80) == 0) {
          break label315;
        }
        bool = true;
        if ((this.zzcik[0] & 0x70) == 0) {
          break label321;
        }
        i = 1;
        if (i != 0) {
          throw new zzbpq("Invalid frame received");
        }
        b = (byte)(this.zzcik[0] & 0xF);
        i = j + read(this.zzcik, j, 1);
        j = this.zzcik[1];
        l = 0L;
        if (j < 126)
        {
          l = j;
          byte[] arrayOfByte = new byte[(int)l];
          read(arrayOfByte, 0, (int)l);
          if (b != 8) {
            break label326;
          }
          this.zzcij.zzZI();
        }
      }
      catch (IOException localIOException)
      {
        zzc(new zzbpq("IO Error", localIOException));
        break;
        if (j == 126)
        {
          read(this.zzcik, i, 2);
          l = (this.zzcik[2] & 0xFF) << 8 | this.zzcik[3] & 0xFF;
          continue;
        }
        if (j != 127) {
          continue;
        }
        int j = read(this.zzcik, i, 8);
        long l = zze(this.zzcik, j + i - 8);
        continue;
        zza(bool, b, localIOException);
      }
      catch (zzbpq localzzbpq)
      {
        zzc(localzzbpq);
        break;
        throw new zzbpq(24 + "Unsupported opcode: " + b);
        return;
      }
      catch (SocketTimeoutException localSocketTimeoutException) {}
      break;
      label315:
      boolean bool = false;
      continue;
      label321:
      int i = 0;
      continue;
      label326:
      if (b == 10) {
        break;
      }
      if ((b != 1) && (b != 2) && (b != 9)) {
        if (b != 0) {}
      }
    }
  }
  
  void zzZQ()
  {
    this.zzcim = true;
  }
  
  void zza(DataInputStream paramDataInputStream)
  {
    this.zzcii = paramDataInputStream;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbpt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */