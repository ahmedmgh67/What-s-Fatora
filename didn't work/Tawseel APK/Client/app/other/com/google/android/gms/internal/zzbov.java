package com.google.android.gms.internal;

import java.util.Map;

public class zzbov
  extends zzbpb<zzbov>
{
  private Map<Object, Object> zzchf;
  
  static
  {
    if (!zzbov.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public zzbov(Map<Object, Object> paramMap, zzbpe paramzzbpe)
  {
    super(paramzzbpe);
    this.zzchf = paramMap;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof zzbov)) {}
    do
    {
      return false;
      paramObject = (zzbov)paramObject;
    } while ((!this.zzchf.equals(((zzbov)paramObject).zzchf)) || (!this.zzcgQ.equals(((zzbov)paramObject).zzcgQ)));
    return true;
  }
  
  public Object getValue()
  {
    return this.zzchf;
  }
  
  public int hashCode()
  {
    return this.zzchf.hashCode() + this.zzcgQ.hashCode();
  }
  
  protected zzbpb.zza zzYV()
  {
    return zzbpb.zza.zzchn;
  }
  
  public String zza(zzbpe.zza paramzza)
  {
    paramzza = String.valueOf(zzb(paramzza));
    String str = String.valueOf(this.zzchf);
    return String.valueOf(paramzza).length() + 14 + String.valueOf(str).length() + paramzza + "deferredValue:" + str;
  }
  
  public zzbov zzj(zzbpe paramzzbpe)
  {
    assert (zzbpi.zzq(paramzzbpe));
    return new zzbov(this.zzchf, paramzzbpe);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbov.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */