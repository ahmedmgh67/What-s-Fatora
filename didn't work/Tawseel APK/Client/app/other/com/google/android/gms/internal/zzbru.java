package com.google.android.gms.internal;

import java.util.Map.Entry;
import java.util.Set;

public final class zzbru
  extends zzbrr
{
  private final zzbsp<String, zzbrr> zzcmM = new zzbsp();
  
  private zzbrr zzaJ(Object paramObject)
  {
    if (paramObject == null) {
      return zzbrt.zzcmL;
    }
    return new zzbrx(paramObject);
  }
  
  public Set<Map.Entry<String, zzbrr>> entrySet()
  {
    return this.zzcmM.entrySet();
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof zzbru)) && (((zzbru)paramObject).zzcmM.equals(this.zzcmM)));
  }
  
  public boolean has(String paramString)
  {
    return this.zzcmM.containsKey(paramString);
  }
  
  public int hashCode()
  {
    return this.zzcmM.hashCode();
  }
  
  public void zza(String paramString, zzbrr paramzzbrr)
  {
    Object localObject = paramzzbrr;
    if (paramzzbrr == null) {
      localObject = zzbrt.zzcmL;
    }
    this.zzcmM.put(paramString, localObject);
  }
  
  public void zzaB(String paramString1, String paramString2)
  {
    zza(paramString1, zzaJ(paramString2));
  }
  
  public void zzb(String paramString, Boolean paramBoolean)
  {
    zza(paramString, zzaJ(paramBoolean));
  }
  
  public zzbrr zzjS(String paramString)
  {
    return (zzbrr)this.zzcmM.get(paramString);
  }
  
  public zzbro zzjT(String paramString)
  {
    return (zzbro)this.zzcmM.get(paramString);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbru.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */