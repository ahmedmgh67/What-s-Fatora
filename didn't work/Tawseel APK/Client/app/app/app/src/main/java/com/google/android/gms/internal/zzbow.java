package com.google.android.gms.internal;

public class zzbow
  extends zzbpb<zzbow>
{
  private final Double zzchg;
  
  static
  {
    if (!zzbow.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public zzbow(Double paramDouble, zzbpe paramzzbpe)
  {
    super(paramzzbpe);
    this.zzchg = paramDouble;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof zzbow)) {}
    do
    {
      return false;
      paramObject = (zzbow)paramObject;
    } while ((!this.zzchg.equals(((zzbow)paramObject).zzchg)) || (!this.zzcgQ.equals(((zzbow)paramObject).zzcgQ)));
    return true;
  }
  
  public Object getValue()
  {
    return this.zzchg;
  }
  
  public int hashCode()
  {
    return this.zzchg.hashCode() + this.zzcgQ.hashCode();
  }
  
  protected zzbpb.zza zzYV()
  {
    return zzbpb.zza.zzchp;
  }
  
  protected int zza(zzbow paramzzbow)
  {
    return this.zzchg.compareTo(paramzzbow.zzchg);
  }
  
  public String zza(zzbpe.zza paramzza)
  {
    paramzza = String.valueOf(String.valueOf(zzb(paramzza)).concat("number:"));
    String str = String.valueOf(zzbqg.zzl(this.zzchg.doubleValue()));
    if (str.length() != 0) {
      return paramzza.concat(str);
    }
    return new String(paramzza);
  }
  
  public zzbow zzk(zzbpe paramzzbpe)
  {
    assert (zzbpi.zzq(paramzzbpe));
    return new zzbow(this.zzchg, paramzzbpe);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */