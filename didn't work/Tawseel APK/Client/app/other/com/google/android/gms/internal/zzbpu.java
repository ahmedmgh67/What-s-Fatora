package com.google.android.gms.internal;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;

class zzbpu
{
  private final Random zzcbl = new Random();
  private final Thread zzchS = zzbpo.getThreadFactory().newThread(new Runnable()
  {
    public void run()
    {
      zzbpu.zza(zzbpu.this);
    }
  });
  private zzbpo zzcij;
  private volatile boolean zzcim = false;
  private BlockingQueue<ByteBuffer> zzcin;
  private boolean zzcio = false;
  private WritableByteChannel zzcip;
  
  zzbpu(zzbpo paramzzbpo, String paramString, int paramInt)
  {
    zzbpo.zzZG().zza(zzZN(), String.valueOf(paramString).length() + 18 + paramString + "Writer-" + paramInt);
    this.zzcij = paramzzbpo;
    this.zzcin = new LinkedBlockingQueue();
  }
  
  private byte[] zzZR()
  {
    byte[] arrayOfByte = new byte[4];
    this.zzcbl.nextBytes(arrayOfByte);
    return arrayOfByte;
  }
  
  private void zzZS()
    throws InterruptedException, IOException
  {
    ByteBuffer localByteBuffer = (ByteBuffer)this.zzcin.take();
    this.zzcip.write(localByteBuffer);
  }
  
  private void zzZU()
  {
    try
    {
      while ((!this.zzcim) && (!Thread.interrupted())) {
        zzZS();
      }
      int i;
      return;
    }
    catch (IOException localIOException)
    {
      zzc(new zzbpq("IO Exception", localIOException));
      for (;;)
      {
        return;
        i = 0;
        while (i < this.zzcin.size())
        {
          zzZS();
          i += 1;
        }
      }
    }
    catch (InterruptedException localInterruptedException) {}
  }
  
  private ByteBuffer zza(byte paramByte, boolean paramBoolean, byte[] paramArrayOfByte)
    throws IOException
  {
    int i = 2;
    if (paramBoolean) {
      i = 6;
    }
    byte b = paramArrayOfByte.length;
    ByteBuffer localByteBuffer;
    if (b < 126)
    {
      localByteBuffer = ByteBuffer.allocate(i + paramArrayOfByte.length);
      localByteBuffer.put((byte)(paramByte | 0xFFFFFF80));
      if (b >= 126) {
        break label145;
      }
      if (!paramBoolean) {
        break label232;
      }
    }
    label145:
    label226:
    label232:
    for (paramByte = b | 0x80;; paramByte = b)
    {
      localByteBuffer.put((byte)paramByte);
      if (paramBoolean)
      {
        byte[] arrayOfByte = zzZR();
        localByteBuffer.put(arrayOfByte);
        paramByte = 0;
        for (;;)
        {
          if (paramByte < paramArrayOfByte.length)
          {
            localByteBuffer.put((byte)(paramArrayOfByte[paramByte] ^ arrayOfByte[(paramByte % 4)]));
            paramByte += 1;
            continue;
            if (b <= 65535)
            {
              i += 2;
              break;
            }
            i += 8;
            break;
            if (b <= 65535) {
              if (!paramBoolean) {
                break label226;
              }
            }
          }
        }
      }
      for (paramByte = 254;; paramByte = 126)
      {
        localByteBuffer.put((byte)paramByte);
        localByteBuffer.putShort((short)b);
        break;
        paramByte = Byte.MAX_VALUE;
        if (paramBoolean) {
          paramByte = 255;
        }
        localByteBuffer.put((byte)paramByte);
        localByteBuffer.putInt(0);
        localByteBuffer.putInt(b);
        break;
        localByteBuffer.flip();
        return localByteBuffer;
      }
    }
  }
  
  private void zzc(zzbpq paramzzbpq)
  {
    this.zzcij.zzb(paramzzbpq);
  }
  
  Thread zzZN()
  {
    return this.zzchS;
  }
  
  void zzZT()
  {
    this.zzcim = true;
  }
  
  void zzb(byte paramByte, boolean paramBoolean, byte[] paramArrayOfByte)
    throws IOException
  {
    try
    {
      paramArrayOfByte = zza(paramByte, paramBoolean, paramArrayOfByte);
      if ((this.zzcim) && ((this.zzcio) || (paramByte != 8))) {
        throw new zzbpq("Shouldn't be sending");
      }
    }
    finally {}
    if (paramByte == 8) {
      this.zzcio = true;
    }
    this.zzcin.add(paramArrayOfByte);
  }
  
  void zzb(OutputStream paramOutputStream)
  {
    this.zzcip = Channels.newChannel(paramOutputStream);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbpu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */