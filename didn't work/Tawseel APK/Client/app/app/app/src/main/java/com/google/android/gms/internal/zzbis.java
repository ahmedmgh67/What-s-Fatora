package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbis
  extends zzbun<zzbis>
{
  public String[] zzbUv;
  public int[] zzbUw;
  public byte[][] zzbUx;
  
  public zzbis()
  {
    zzTr();
  }
  
  public static zzbis zzS(byte[] paramArrayOfByte)
    throws zzbus
  {
    return (zzbis)zzbut.zza(new zzbis(), paramArrayOfByte);
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1;
    if (paramObject == this) {
      bool1 = true;
    }
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              return bool1;
              bool1 = bool2;
            } while (!(paramObject instanceof zzbis));
            paramObject = (zzbis)paramObject;
            bool1 = bool2;
          } while (!zzbur.equals(this.zzbUv, ((zzbis)paramObject).zzbUv));
          bool1 = bool2;
        } while (!zzbur.equals(this.zzbUw, ((zzbis)paramObject).zzbUw));
        bool1 = bool2;
      } while (!zzbur.zza(this.zzbUx, ((zzbis)paramObject).zzbUx));
      if ((this.zzcrX != null) && (!this.zzcrX.isEmpty())) {
        break label111;
      }
      if (((zzbis)paramObject).zzcrX == null) {
        break;
      }
      bool1 = bool2;
    } while (!((zzbis)paramObject).zzcrX.isEmpty());
    return true;
    label111:
    return this.zzcrX.equals(((zzbis)paramObject).zzcrX);
  }
  
  public int hashCode()
  {
    int j = getClass().getName().hashCode();
    int k = zzbur.hashCode(this.zzbUv);
    int m = zzbur.hashCode(this.zzbUw);
    int n = zzbur.zzb(this.zzbUx);
    if ((this.zzcrX == null) || (this.zzcrX.isEmpty())) {}
    for (int i = 0;; i = this.zzcrX.hashCode()) {
      return i + ((((j + 527) * 31 + k) * 31 + m) * 31 + n) * 31;
    }
  }
  
  public zzbis zzTr()
  {
    this.zzbUv = zzbuw.zzcsn;
    this.zzbUw = zzbuw.zzcsi;
    this.zzbUx = zzbuw.zzcso;
    this.zzcrX = null;
    this.zzcsg = -1;
    return this;
  }
  
  public void zza(zzbum paramzzbum)
    throws IOException
  {
    int j = 0;
    int i;
    Object localObject;
    if ((this.zzbUv != null) && (this.zzbUv.length > 0))
    {
      i = 0;
      while (i < this.zzbUv.length)
      {
        localObject = this.zzbUv[i];
        if (localObject != null) {
          paramzzbum.zzq(1, (String)localObject);
        }
        i += 1;
      }
    }
    if ((this.zzbUw != null) && (this.zzbUw.length > 0))
    {
      i = 0;
      while (i < this.zzbUw.length)
      {
        paramzzbum.zzF(2, this.zzbUw[i]);
        i += 1;
      }
    }
    if ((this.zzbUx != null) && (this.zzbUx.length > 0))
    {
      i = j;
      while (i < this.zzbUx.length)
      {
        localObject = this.zzbUx[i];
        if (localObject != null) {
          paramzzbum.zzb(3, (byte[])localObject);
        }
        i += 1;
      }
    }
    super.zza(paramzzbum);
  }
  
  public zzbis zzab(zzbul paramzzbul)
    throws IOException
  {
    for (;;)
    {
      int i = paramzzbul.zzacu();
      int j;
      Object localObject;
      switch (i)
      {
      default: 
        if (super.zza(paramzzbul, i)) {}
        break;
      case 0: 
        return this;
      case 10: 
        j = zzbuw.zzc(paramzzbul, 10);
        if (this.zzbUv == null) {}
        for (i = 0;; i = this.zzbUv.length)
        {
          localObject = new String[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(this.zzbUv, 0, localObject, 0, i);
            j = i;
          }
          while (j < localObject.length - 1)
          {
            localObject[j] = paramzzbul.readString();
            paramzzbul.zzacu();
            j += 1;
          }
        }
        localObject[j] = paramzzbul.readString();
        this.zzbUv = ((String[])localObject);
        break;
      case 16: 
        j = zzbuw.zzc(paramzzbul, 16);
        if (this.zzbUw == null) {}
        for (i = 0;; i = this.zzbUw.length)
        {
          localObject = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(this.zzbUw, 0, localObject, 0, i);
            j = i;
          }
          while (j < localObject.length - 1)
          {
            localObject[j] = paramzzbul.zzacy();
            paramzzbul.zzacu();
            j += 1;
          }
        }
        localObject[j] = paramzzbul.zzacy();
        this.zzbUw = ((int[])localObject);
        break;
      case 18: 
        int k = paramzzbul.zzqj(paramzzbul.zzacD());
        i = paramzzbul.getPosition();
        j = 0;
        while (paramzzbul.zzacI() > 0)
        {
          paramzzbul.zzacy();
          j += 1;
        }
        paramzzbul.zzql(i);
        if (this.zzbUw == null) {}
        for (i = 0;; i = this.zzbUw.length)
        {
          localObject = new int[j + i];
          j = i;
          if (i != 0)
          {
            System.arraycopy(this.zzbUw, 0, localObject, 0, i);
            j = i;
          }
          while (j < localObject.length)
          {
            localObject[j] = paramzzbul.zzacy();
            j += 1;
          }
        }
        this.zzbUw = ((int[])localObject);
        paramzzbul.zzqk(k);
        break;
      case 26: 
        j = zzbuw.zzc(paramzzbul, 26);
        if (this.zzbUx == null) {}
        for (i = 0;; i = this.zzbUx.length)
        {
          localObject = new byte[j + i][];
          j = i;
          if (i != 0)
          {
            System.arraycopy(this.zzbUx, 0, localObject, 0, i);
            j = i;
          }
          while (j < localObject.length - 1)
          {
            localObject[j] = paramzzbul.readBytes();
            paramzzbul.zzacu();
            j += 1;
          }
        }
        localObject[j] = paramzzbul.readBytes();
        this.zzbUx = ((byte[][])localObject);
      }
    }
  }
  
  protected int zzv()
  {
    int i1 = 0;
    int i2 = super.zzv();
    int i;
    int k;
    Object localObject;
    int n;
    int m;
    if ((this.zzbUv != null) && (this.zzbUv.length > 0))
    {
      i = 0;
      j = 0;
      for (k = 0; i < this.zzbUv.length; k = m)
      {
        localObject = this.zzbUv[i];
        n = j;
        m = k;
        if (localObject != null)
        {
          m = k + 1;
          n = j + zzbum.zzkc((String)localObject);
        }
        i += 1;
        j = n;
      }
    }
    for (int j = i2 + j + k * 1;; j = i2)
    {
      i = j;
      if (this.zzbUw != null)
      {
        i = j;
        if (this.zzbUw.length > 0)
        {
          i = 0;
          k = 0;
          while (i < this.zzbUw.length)
          {
            k += zzbum.zzqp(this.zzbUw[i]);
            i += 1;
          }
          i = j + k + this.zzbUw.length * 1;
        }
      }
      j = i;
      if (this.zzbUx != null)
      {
        j = i;
        if (this.zzbUx.length > 0)
        {
          k = 0;
          m = 0;
          j = i1;
          while (j < this.zzbUx.length)
          {
            localObject = this.zzbUx[j];
            i1 = k;
            n = m;
            if (localObject != null)
            {
              n = m + 1;
              i1 = k + zzbum.zzag((byte[])localObject);
            }
            j += 1;
            k = i1;
            m = n;
          }
          j = i + k + m * 1;
        }
      }
      return j;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbis.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */