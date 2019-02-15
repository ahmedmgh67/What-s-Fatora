package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

public abstract interface zzbqu
{
  public static final class zza
    extends zzbun<zza>
  {
    public long timestamp;
    public zzbqu.zzd[] zzcjU;
    
    public zza()
    {
      zzaaA();
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
              return bool1;
              bool1 = bool2;
            } while (!(paramObject instanceof zza));
            paramObject = (zza)paramObject;
            bool1 = bool2;
          } while (!zzbur.equals(this.zzcjU, ((zza)paramObject).zzcjU));
          bool1 = bool2;
        } while (this.timestamp != ((zza)paramObject).timestamp);
        if ((this.zzcrX != null) && (!this.zzcrX.isEmpty())) {
          break label93;
        }
        if (((zza)paramObject).zzcrX == null) {
          break;
        }
        bool1 = bool2;
      } while (!((zza)paramObject).zzcrX.isEmpty());
      return true;
      label93:
      return this.zzcrX.equals(((zza)paramObject).zzcrX);
    }
    
    public int hashCode()
    {
      int j = getClass().getName().hashCode();
      int k = zzbur.hashCode(this.zzcjU);
      int m = (int)(this.timestamp ^ this.timestamp >>> 32);
      if ((this.zzcrX == null) || (this.zzcrX.isEmpty())) {}
      for (int i = 0;; i = this.zzcrX.hashCode()) {
        return i + (((j + 527) * 31 + k) * 31 + m) * 31;
      }
    }
    
    public void zza(zzbum paramzzbum)
      throws IOException
    {
      if ((this.zzcjU != null) && (this.zzcjU.length > 0))
      {
        int i = 0;
        while (i < this.zzcjU.length)
        {
          zzbqu.zzd localzzd = this.zzcjU[i];
          if (localzzd != null) {
            paramzzbum.zza(1, localzzd);
          }
          i += 1;
        }
      }
      if (this.timestamp != 0L) {
        paramzzbum.zzc(2, this.timestamp);
      }
      super.zza(paramzzbum);
    }
    
    public zza zzaaA()
    {
      this.zzcjU = zzbqu.zzd.zzaaE();
      this.timestamp = 0L;
      this.zzcrX = null;
      this.zzcsg = -1;
      return this;
    }
    
    public zza zzac(zzbul paramzzbul)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzbul.zzacu();
        switch (i)
        {
        default: 
          if (super.zza(paramzzbul, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          int j = zzbuw.zzc(paramzzbul, 10);
          if (this.zzcjU == null) {}
          zzbqu.zzd[] arrayOfzzd;
          for (i = 0;; i = this.zzcjU.length)
          {
            arrayOfzzd = new zzbqu.zzd[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzcjU, 0, arrayOfzzd, 0, i);
              j = i;
            }
            while (j < arrayOfzzd.length - 1)
            {
              arrayOfzzd[j] = new zzbqu.zzd();
              paramzzbul.zza(arrayOfzzd[j]);
              paramzzbul.zzacu();
              j += 1;
            }
          }
          arrayOfzzd[j] = new zzbqu.zzd();
          paramzzbul.zza(arrayOfzzd[j]);
          this.zzcjU = arrayOfzzd;
          break;
        case 17: 
          this.timestamp = paramzzbul.zzacz();
        }
      }
    }
    
    protected int zzv()
    {
      int i = super.zzv();
      int j = i;
      if (this.zzcjU != null)
      {
        j = i;
        if (this.zzcjU.length > 0)
        {
          int k = 0;
          for (;;)
          {
            j = i;
            if (k >= this.zzcjU.length) {
              break;
            }
            zzbqu.zzd localzzd = this.zzcjU[k];
            j = i;
            if (localzzd != null) {
              j = i + zzbum.zzc(1, localzzd);
            }
            k += 1;
            i = j;
          }
        }
      }
      i = j;
      if (this.timestamp != 0L) {
        i = j + zzbum.zzg(2, this.timestamp);
      }
      return i;
    }
  }
  
  public static final class zzb
    extends zzbun<zzb>
  {
    private static volatile zzb[] zzcjV;
    public String zzaA;
    public byte[] zzcjW;
    
    public zzb()
    {
      zzaaC();
    }
    
    public static zzb[] zzaaB()
    {
      if (zzcjV == null) {}
      synchronized (zzbur.zzcsf)
      {
        if (zzcjV == null) {
          zzcjV = new zzb[0];
        }
        return zzcjV;
      }
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
          return bool1;
          bool1 = bool2;
        } while (!(paramObject instanceof zzb));
        paramObject = (zzb)paramObject;
        if (this.zzaA != null) {
          break;
        }
        bool1 = bool2;
      } while (((zzb)paramObject).zzaA != null);
      while (this.zzaA.equals(((zzb)paramObject).zzaA))
      {
        bool1 = bool2;
        if (!Arrays.equals(this.zzcjW, ((zzb)paramObject).zzcjW)) {
          break;
        }
        if ((this.zzcrX != null) && (!this.zzcrX.isEmpty())) {
          break label111;
        }
        if (((zzb)paramObject).zzcrX != null)
        {
          bool1 = bool2;
          if (!((zzb)paramObject).zzcrX.isEmpty()) {
            break;
          }
        }
        return true;
      }
      return false;
      label111:
      return this.zzcrX.equals(((zzb)paramObject).zzcrX);
    }
    
    public int hashCode()
    {
      int k = 0;
      int m = getClass().getName().hashCode();
      int i;
      int n;
      if (this.zzaA == null)
      {
        i = 0;
        n = Arrays.hashCode(this.zzcjW);
        j = k;
        if (this.zzcrX != null) {
          if (!this.zzcrX.isEmpty()) {
            break label87;
          }
        }
      }
      label87:
      for (int j = k;; j = this.zzcrX.hashCode())
      {
        return ((i + (m + 527) * 31) * 31 + n) * 31 + j;
        i = this.zzaA.hashCode();
        break;
      }
    }
    
    public void zza(zzbum paramzzbum)
      throws IOException
    {
      if ((this.zzaA != null) && (!this.zzaA.equals(""))) {
        paramzzbum.zzq(1, this.zzaA);
      }
      if (!Arrays.equals(this.zzcjW, zzbuw.zzcsp)) {
        paramzzbum.zzb(2, this.zzcjW);
      }
      super.zza(paramzzbum);
    }
    
    public zzb zzaaC()
    {
      this.zzaA = "";
      this.zzcjW = zzbuw.zzcsp;
      this.zzcrX = null;
      this.zzcsg = -1;
      return this;
    }
    
    public zzb zzad(zzbul paramzzbul)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzbul.zzacu();
        switch (i)
        {
        default: 
          if (super.zza(paramzzbul, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          this.zzaA = paramzzbul.readString();
          break;
        case 18: 
          this.zzcjW = paramzzbul.readBytes();
        }
      }
    }
    
    protected int zzv()
    {
      int j = super.zzv();
      int i = j;
      if (this.zzaA != null)
      {
        i = j;
        if (!this.zzaA.equals("")) {
          i = j + zzbum.zzr(1, this.zzaA);
        }
      }
      j = i;
      if (!Arrays.equals(this.zzcjW, zzbuw.zzcsp)) {
        j = i + zzbum.zzc(2, this.zzcjW);
      }
      return j;
    }
  }
  
  public static final class zzc
    extends zzbun<zzc>
  {
    public int zzcjX;
    public boolean zzcjY;
    
    public zzc()
    {
      zzaaD();
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
              return bool1;
              bool1 = bool2;
            } while (!(paramObject instanceof zzc));
            paramObject = (zzc)paramObject;
            bool1 = bool2;
          } while (this.zzcjX != ((zzc)paramObject).zzcjX);
          bool1 = bool2;
        } while (this.zzcjY != ((zzc)paramObject).zzcjY);
        if ((this.zzcrX != null) && (!this.zzcrX.isEmpty())) {
          break label89;
        }
        if (((zzc)paramObject).zzcrX == null) {
          break;
        }
        bool1 = bool2;
      } while (!((zzc)paramObject).zzcrX.isEmpty());
      return true;
      label89:
      return this.zzcrX.equals(((zzc)paramObject).zzcrX);
    }
    
    public int hashCode()
    {
      int k = getClass().getName().hashCode();
      int m = this.zzcjX;
      int i;
      if (this.zzcjY)
      {
        i = 1231;
        if ((this.zzcrX != null) && (!this.zzcrX.isEmpty())) {
          break label76;
        }
      }
      label76:
      for (int j = 0;; j = this.zzcrX.hashCode())
      {
        return j + (i + ((k + 527) * 31 + m) * 31) * 31;
        i = 1237;
        break;
      }
    }
    
    public void zza(zzbum paramzzbum)
      throws IOException
    {
      if (this.zzcjX != 0) {
        paramzzbum.zzF(1, this.zzcjX);
      }
      if (this.zzcjY) {
        paramzzbum.zzg(2, this.zzcjY);
      }
      super.zza(paramzzbum);
    }
    
    public zzc zzaaD()
    {
      this.zzcjX = 0;
      this.zzcjY = false;
      this.zzcrX = null;
      this.zzcsg = -1;
      return this;
    }
    
    public zzc zzae(zzbul paramzzbul)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzbul.zzacu();
        switch (i)
        {
        default: 
          if (super.zza(paramzzbul, i)) {}
          break;
        case 0: 
          return this;
        case 8: 
          this.zzcjX = paramzzbul.zzacy();
          break;
        case 16: 
          this.zzcjY = paramzzbul.zzacA();
        }
      }
    }
    
    protected int zzv()
    {
      int j = super.zzv();
      int i = j;
      if (this.zzcjX != 0) {
        i = j + zzbum.zzH(1, this.zzcjX);
      }
      j = i;
      if (this.zzcjY) {
        j = i + zzbum.zzh(2, this.zzcjY);
      }
      return j;
    }
  }
  
  public static final class zzd
    extends zzbun<zzd>
  {
    private static volatile zzd[] zzcjZ;
    public String zzaFs;
    public zzbqu.zzb[] zzcka;
    
    public zzd()
    {
      zzaaF();
    }
    
    public static zzd[] zzaaE()
    {
      if (zzcjZ == null) {}
      synchronized (zzbur.zzcsf)
      {
        if (zzcjZ == null) {
          zzcjZ = new zzd[0];
        }
        return zzcjZ;
      }
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
          return bool1;
          bool1 = bool2;
        } while (!(paramObject instanceof zzd));
        paramObject = (zzd)paramObject;
        if (this.zzaFs != null) {
          break;
        }
        bool1 = bool2;
      } while (((zzd)paramObject).zzaFs != null);
      while (this.zzaFs.equals(((zzd)paramObject).zzaFs))
      {
        bool1 = bool2;
        if (!zzbur.equals(this.zzcka, ((zzd)paramObject).zzcka)) {
          break;
        }
        if ((this.zzcrX != null) && (!this.zzcrX.isEmpty())) {
          break label111;
        }
        if (((zzd)paramObject).zzcrX != null)
        {
          bool1 = bool2;
          if (!((zzd)paramObject).zzcrX.isEmpty()) {
            break;
          }
        }
        return true;
      }
      return false;
      label111:
      return this.zzcrX.equals(((zzd)paramObject).zzcrX);
    }
    
    public int hashCode()
    {
      int k = 0;
      int m = getClass().getName().hashCode();
      int i;
      int n;
      if (this.zzaFs == null)
      {
        i = 0;
        n = zzbur.hashCode(this.zzcka);
        j = k;
        if (this.zzcrX != null) {
          if (!this.zzcrX.isEmpty()) {
            break label87;
          }
        }
      }
      label87:
      for (int j = k;; j = this.zzcrX.hashCode())
      {
        return ((i + (m + 527) * 31) * 31 + n) * 31 + j;
        i = this.zzaFs.hashCode();
        break;
      }
    }
    
    public void zza(zzbum paramzzbum)
      throws IOException
    {
      if ((this.zzaFs != null) && (!this.zzaFs.equals(""))) {
        paramzzbum.zzq(1, this.zzaFs);
      }
      if ((this.zzcka != null) && (this.zzcka.length > 0))
      {
        int i = 0;
        while (i < this.zzcka.length)
        {
          zzbqu.zzb localzzb = this.zzcka[i];
          if (localzzb != null) {
            paramzzbum.zza(2, localzzb);
          }
          i += 1;
        }
      }
      super.zza(paramzzbum);
    }
    
    public zzd zzaaF()
    {
      this.zzaFs = "";
      this.zzcka = zzbqu.zzb.zzaaB();
      this.zzcrX = null;
      this.zzcsg = -1;
      return this;
    }
    
    public zzd zzaf(zzbul paramzzbul)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzbul.zzacu();
        switch (i)
        {
        default: 
          if (super.zza(paramzzbul, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          this.zzaFs = paramzzbul.readString();
          break;
        case 18: 
          int j = zzbuw.zzc(paramzzbul, 18);
          if (this.zzcka == null) {}
          zzbqu.zzb[] arrayOfzzb;
          for (i = 0;; i = this.zzcka.length)
          {
            arrayOfzzb = new zzbqu.zzb[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzcka, 0, arrayOfzzb, 0, i);
              j = i;
            }
            while (j < arrayOfzzb.length - 1)
            {
              arrayOfzzb[j] = new zzbqu.zzb();
              paramzzbul.zza(arrayOfzzb[j]);
              paramzzbul.zzacu();
              j += 1;
            }
          }
          arrayOfzzb[j] = new zzbqu.zzb();
          paramzzbul.zza(arrayOfzzb[j]);
          this.zzcka = arrayOfzzb;
        }
      }
    }
    
    protected int zzv()
    {
      int j = super.zzv();
      int i = j;
      if (this.zzaFs != null)
      {
        i = j;
        if (!this.zzaFs.equals("")) {
          i = j + zzbum.zzr(1, this.zzaFs);
        }
      }
      j = i;
      if (this.zzcka != null)
      {
        j = i;
        if (this.zzcka.length > 0)
        {
          j = 0;
          while (j < this.zzcka.length)
          {
            zzbqu.zzb localzzb = this.zzcka[j];
            int k = i;
            if (localzzb != null) {
              k = i + zzbum.zzc(2, localzzb);
            }
            j += 1;
            i = k;
          }
          j = i;
        }
      }
      return j;
    }
  }
  
  public static final class zze
    extends zzbun<zze>
  {
    public zzbqu.zza zzckb;
    public zzbqu.zza zzckc;
    public zzbqu.zza zzckd;
    public zzbqu.zzc zzcke;
    public zzbqu.zzf[] zzckf;
    
    public zze()
    {
      zzaaG();
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1;
      if (paramObject == this) {
        bool1 = true;
      }
      label41:
      label57:
      label73:
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
              } while (!(paramObject instanceof zze));
              paramObject = (zze)paramObject;
              if (this.zzckb != null) {
                break;
              }
              bool1 = bool2;
            } while (((zze)paramObject).zzckb != null);
            if (this.zzckc != null) {
              break label159;
            }
            bool1 = bool2;
          } while (((zze)paramObject).zzckc != null);
          if (this.zzckd != null) {
            break label175;
          }
          bool1 = bool2;
        } while (((zze)paramObject).zzckd != null);
        if (this.zzcke != null) {
          break label191;
        }
        bool1 = bool2;
      } while (((zze)paramObject).zzcke != null);
      label159:
      label175:
      label191:
      while (this.zzcke.equals(((zze)paramObject).zzcke))
      {
        bool1 = bool2;
        if (!zzbur.equals(this.zzckf, ((zze)paramObject).zzckf)) {
          break;
        }
        if ((this.zzcrX != null) && (!this.zzcrX.isEmpty())) {
          break label207;
        }
        if (((zze)paramObject).zzcrX != null)
        {
          bool1 = bool2;
          if (!((zze)paramObject).zzcrX.isEmpty()) {
            break;
          }
        }
        return true;
        if (this.zzckb.equals(((zze)paramObject).zzckb)) {
          break label41;
        }
        return false;
        if (this.zzckc.equals(((zze)paramObject).zzckc)) {
          break label57;
        }
        return false;
        if (this.zzckd.equals(((zze)paramObject).zzckd)) {
          break label73;
        }
        return false;
      }
      return false;
      label207:
      return this.zzcrX.equals(((zze)paramObject).zzcrX);
    }
    
    public int hashCode()
    {
      int i1 = 0;
      int i2 = getClass().getName().hashCode();
      int i;
      int j;
      label33:
      int k;
      label42:
      int m;
      label52:
      int i3;
      if (this.zzckb == null)
      {
        i = 0;
        if (this.zzckc != null) {
          break label137;
        }
        j = 0;
        if (this.zzckd != null) {
          break label148;
        }
        k = 0;
        if (this.zzcke != null) {
          break label159;
        }
        m = 0;
        i3 = zzbur.hashCode(this.zzckf);
        n = i1;
        if (this.zzcrX != null) {
          if (!this.zzcrX.isEmpty()) {
            break label171;
          }
        }
      }
      label137:
      label148:
      label159:
      label171:
      for (int n = i1;; n = this.zzcrX.hashCode())
      {
        return ((m + (k + (j + (i + (i2 + 527) * 31) * 31) * 31) * 31) * 31 + i3) * 31 + n;
        i = this.zzckb.hashCode();
        break;
        j = this.zzckc.hashCode();
        break label33;
        k = this.zzckd.hashCode();
        break label42;
        m = this.zzcke.hashCode();
        break label52;
      }
    }
    
    public void zza(zzbum paramzzbum)
      throws IOException
    {
      if (this.zzckb != null) {
        paramzzbum.zza(1, this.zzckb);
      }
      if (this.zzckc != null) {
        paramzzbum.zza(2, this.zzckc);
      }
      if (this.zzckd != null) {
        paramzzbum.zza(3, this.zzckd);
      }
      if (this.zzcke != null) {
        paramzzbum.zza(4, this.zzcke);
      }
      if ((this.zzckf != null) && (this.zzckf.length > 0))
      {
        int i = 0;
        while (i < this.zzckf.length)
        {
          zzbqu.zzf localzzf = this.zzckf[i];
          if (localzzf != null) {
            paramzzbum.zza(5, localzzf);
          }
          i += 1;
        }
      }
      super.zza(paramzzbum);
    }
    
    public zze zzaaG()
    {
      this.zzckb = null;
      this.zzckc = null;
      this.zzckd = null;
      this.zzcke = null;
      this.zzckf = zzbqu.zzf.zzaaH();
      this.zzcrX = null;
      this.zzcsg = -1;
      return this;
    }
    
    public zze zzag(zzbul paramzzbul)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzbul.zzacu();
        switch (i)
        {
        default: 
          if (super.zza(paramzzbul, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          if (this.zzckb == null) {
            this.zzckb = new zzbqu.zza();
          }
          paramzzbul.zza(this.zzckb);
          break;
        case 18: 
          if (this.zzckc == null) {
            this.zzckc = new zzbqu.zza();
          }
          paramzzbul.zza(this.zzckc);
          break;
        case 26: 
          if (this.zzckd == null) {
            this.zzckd = new zzbqu.zza();
          }
          paramzzbul.zza(this.zzckd);
          break;
        case 34: 
          if (this.zzcke == null) {
            this.zzcke = new zzbqu.zzc();
          }
          paramzzbul.zza(this.zzcke);
          break;
        case 42: 
          int j = zzbuw.zzc(paramzzbul, 42);
          if (this.zzckf == null) {}
          zzbqu.zzf[] arrayOfzzf;
          for (i = 0;; i = this.zzckf.length)
          {
            arrayOfzzf = new zzbqu.zzf[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.zzckf, 0, arrayOfzzf, 0, i);
              j = i;
            }
            while (j < arrayOfzzf.length - 1)
            {
              arrayOfzzf[j] = new zzbqu.zzf();
              paramzzbul.zza(arrayOfzzf[j]);
              paramzzbul.zzacu();
              j += 1;
            }
          }
          arrayOfzzf[j] = new zzbqu.zzf();
          paramzzbul.zza(arrayOfzzf[j]);
          this.zzckf = arrayOfzzf;
        }
      }
    }
    
    protected int zzv()
    {
      int j = super.zzv();
      int i = j;
      if (this.zzckb != null) {
        i = j + zzbum.zzc(1, this.zzckb);
      }
      j = i;
      if (this.zzckc != null) {
        j = i + zzbum.zzc(2, this.zzckc);
      }
      int k = j;
      if (this.zzckd != null) {
        k = j + zzbum.zzc(3, this.zzckd);
      }
      i = k;
      if (this.zzcke != null) {
        i = k + zzbum.zzc(4, this.zzcke);
      }
      j = i;
      if (this.zzckf != null)
      {
        j = i;
        if (this.zzckf.length > 0)
        {
          j = 0;
          while (j < this.zzckf.length)
          {
            zzbqu.zzf localzzf = this.zzckf[j];
            k = i;
            if (localzzf != null) {
              k = i + zzbum.zzc(5, localzzf);
            }
            j += 1;
            i = k;
          }
          j = i;
        }
      }
      return j;
    }
  }
  
  public static final class zzf
    extends zzbun<zzf>
  {
    private static volatile zzf[] zzckg;
    public int resourceId;
    public String zzaFs;
    public long zzckh;
    
    public zzf()
    {
      zzaaI();
    }
    
    public static zzf[] zzaaH()
    {
      if (zzckg == null) {}
      synchronized (zzbur.zzcsf)
      {
        if (zzckg == null) {
          zzckg = new zzf[0];
        }
        return zzckg;
      }
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
              return bool1;
              bool1 = bool2;
            } while (!(paramObject instanceof zzf));
            paramObject = (zzf)paramObject;
            bool1 = bool2;
          } while (this.resourceId != ((zzf)paramObject).resourceId);
          bool1 = bool2;
        } while (this.zzckh != ((zzf)paramObject).zzckh);
        if (this.zzaFs != null) {
          break;
        }
        bool1 = bool2;
      } while (((zzf)paramObject).zzaFs != null);
      for (;;)
      {
        if ((this.zzcrX == null) || (this.zzcrX.isEmpty()))
        {
          if (((zzf)paramObject).zzcrX != null)
          {
            bool1 = bool2;
            if (!((zzf)paramObject).zzcrX.isEmpty()) {
              break;
            }
          }
          return true;
          if (!this.zzaFs.equals(((zzf)paramObject).zzaFs)) {
            return false;
          }
        }
      }
      return this.zzcrX.equals(((zzf)paramObject).zzcrX);
    }
    
    public int hashCode()
    {
      int k = 0;
      int m = getClass().getName().hashCode();
      int n = this.resourceId;
      int i1 = (int)(this.zzckh ^ this.zzckh >>> 32);
      int i;
      if (this.zzaFs == null)
      {
        i = 0;
        j = k;
        if (this.zzcrX != null) {
          if (!this.zzcrX.isEmpty()) {
            break label105;
          }
        }
      }
      label105:
      for (int j = k;; j = this.zzcrX.hashCode())
      {
        return (i + (((m + 527) * 31 + n) * 31 + i1) * 31) * 31 + j;
        i = this.zzaFs.hashCode();
        break;
      }
    }
    
    public void zza(zzbum paramzzbum)
      throws IOException
    {
      if (this.resourceId != 0) {
        paramzzbum.zzF(1, this.resourceId);
      }
      if (this.zzckh != 0L) {
        paramzzbum.zzc(2, this.zzckh);
      }
      if ((this.zzaFs != null) && (!this.zzaFs.equals(""))) {
        paramzzbum.zzq(3, this.zzaFs);
      }
      super.zza(paramzzbum);
    }
    
    public zzf zzaaI()
    {
      this.resourceId = 0;
      this.zzckh = 0L;
      this.zzaFs = "";
      this.zzcrX = null;
      this.zzcsg = -1;
      return this;
    }
    
    public zzf zzah(zzbul paramzzbul)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzbul.zzacu();
        switch (i)
        {
        default: 
          if (super.zza(paramzzbul, i)) {}
          break;
        case 0: 
          return this;
        case 8: 
          this.resourceId = paramzzbul.zzacy();
          break;
        case 17: 
          this.zzckh = paramzzbul.zzacz();
          break;
        case 26: 
          this.zzaFs = paramzzbul.readString();
        }
      }
    }
    
    protected int zzv()
    {
      int j = super.zzv();
      int i = j;
      if (this.resourceId != 0) {
        i = j + zzbum.zzH(1, this.resourceId);
      }
      j = i;
      if (this.zzckh != 0L) {
        j = i + zzbum.zzg(2, this.zzckh);
      }
      i = j;
      if (this.zzaFs != null)
      {
        i = j;
        if (!this.zzaFs.equals("")) {
          i = j + zzbum.zzr(3, this.zzaFs);
        }
      }
      return i;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbqu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */