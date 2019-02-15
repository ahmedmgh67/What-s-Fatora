package com.google.android.gms.common.data;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzac;

public abstract class zzc
{
  protected int zzaCm;
  private int zzaCn;
  protected final DataHolder zzazI;
  
  public zzc(DataHolder paramDataHolder, int paramInt)
  {
    this.zzazI = ((DataHolder)zzac.zzw(paramDataHolder));
    zzcA(paramInt);
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ((paramObject instanceof zzc))
    {
      paramObject = (zzc)paramObject;
      bool1 = bool2;
      if (zzaa.equal(Integer.valueOf(((zzc)paramObject).zzaCm), Integer.valueOf(this.zzaCm)))
      {
        bool1 = bool2;
        if (zzaa.equal(Integer.valueOf(((zzc)paramObject).zzaCn), Integer.valueOf(this.zzaCn)))
        {
          bool1 = bool2;
          if (((zzc)paramObject).zzazI == this.zzazI) {
            bool1 = true;
          }
        }
      }
    }
    return bool1;
  }
  
  protected boolean getBoolean(String paramString)
  {
    return this.zzazI.zze(paramString, this.zzaCm, this.zzaCn);
  }
  
  protected byte[] getByteArray(String paramString)
  {
    return this.zzazI.zzg(paramString, this.zzaCm, this.zzaCn);
  }
  
  protected float getFloat(String paramString)
  {
    return this.zzazI.zzf(paramString, this.zzaCm, this.zzaCn);
  }
  
  protected int getInteger(String paramString)
  {
    return this.zzazI.zzc(paramString, this.zzaCm, this.zzaCn);
  }
  
  protected long getLong(String paramString)
  {
    return this.zzazI.zzb(paramString, this.zzaCm, this.zzaCn);
  }
  
  protected String getString(String paramString)
  {
    return this.zzazI.zzd(paramString, this.zzaCm, this.zzaCn);
  }
  
  public int hashCode()
  {
    return zzaa.hashCode(new Object[] { Integer.valueOf(this.zzaCm), Integer.valueOf(this.zzaCn), this.zzazI });
  }
  
  public boolean isDataValid()
  {
    return !this.zzazI.isClosed();
  }
  
  protected void zza(String paramString, CharArrayBuffer paramCharArrayBuffer)
  {
    this.zzazI.zza(paramString, this.zzaCm, this.zzaCn, paramCharArrayBuffer);
  }
  
  protected void zzcA(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < this.zzazI.getCount())) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zzar(bool);
      this.zzaCm = paramInt;
      this.zzaCn = this.zzazI.zzcC(this.zzaCm);
      return;
    }
  }
  
  public boolean zzdj(String paramString)
  {
    return this.zzazI.zzdj(paramString);
  }
  
  protected Uri zzdk(String paramString)
  {
    return this.zzazI.zzh(paramString, this.zzaCm, this.zzaCn);
  }
  
  protected boolean zzdl(String paramString)
  {
    return this.zzazI.zzi(paramString, this.zzaCm, this.zzaCn);
  }
  
  protected int zzwB()
  {
    return this.zzaCm;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\common\data\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */