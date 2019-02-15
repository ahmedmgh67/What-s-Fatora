package com.google.android.gms.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class zzbpb<T extends zzbpb>
  implements zzbpe
{
  protected final zzbpe zzcgQ;
  private String zzcgR;
  
  static
  {
    if (!zzbpb.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  zzbpb(zzbpe paramzzbpe)
  {
    this.zzcgQ = paramzzbpe;
  }
  
  private static int zza(zzbpc paramzzbpc, zzbow paramzzbow)
  {
    return Double.valueOf(((Long)paramzzbpc.getValue()).longValue()).compareTo((Double)paramzzbow.getValue());
  }
  
  public int getChildCount()
  {
    return 0;
  }
  
  public Object getValue(boolean paramBoolean)
  {
    if ((!paramBoolean) || (this.zzcgQ.isEmpty())) {
      return getValue();
    }
    HashMap localHashMap = new HashMap();
    localHashMap.put(".value", getValue());
    localHashMap.put(".priority", this.zzcgQ.getValue());
    return localHashMap;
  }
  
  public boolean isEmpty()
  {
    return false;
  }
  
  public Iterator<zzbpd> iterator()
  {
    return Collections.emptyList().iterator();
  }
  
  public String toString()
  {
    String str = getValue(true).toString();
    if (str.length() <= 100) {
      return str;
    }
    return String.valueOf(str.substring(0, 100)).concat("...");
  }
  
  public zzbpe zzO(zzbmj paramzzbmj)
  {
    if (paramzzbmj.isEmpty()) {
      return this;
    }
    if (paramzzbmj.zzXi().zzZa()) {
      return this.zzcgQ;
    }
    return zzbox.zzZp();
  }
  
  public Iterator<zzbpd> zzVl()
  {
    return Collections.emptyList().iterator();
  }
  
  protected abstract zza zzYV();
  
  public String zzZc()
  {
    if (this.zzcgR == null) {
      this.zzcgR = zzbqg.zzji(zza(zzbpe.zza.zzchv));
    }
    return this.zzcgR;
  }
  
  public boolean zzZd()
  {
    return true;
  }
  
  public zzbpe zzZe()
  {
    return this.zzcgQ;
  }
  
  protected abstract int zza(T paramT);
  
  protected String zzb(zzbpe.zza paramzza)
  {
    switch (1.zzchm[paramzza.ordinal()])
    {
    default: 
      paramzza = String.valueOf(paramzza);
      throw new IllegalArgumentException(String.valueOf(paramzza).length() + 22 + "Unknown hash version: " + paramzza);
    }
    if (this.zzcgQ.isEmpty()) {
      return "";
    }
    paramzza = String.valueOf(this.zzcgQ.zza(paramzza));
    return String.valueOf(paramzza).length() + 10 + "priority:" + paramzza + ":";
  }
  
  protected int zzc(zzbpb<?> paramzzbpb)
  {
    zza localzza1 = zzYV();
    zza localzza2 = paramzzbpb.zzYV();
    if (localzza1.equals(localzza2)) {
      return zza(paramzzbpb);
    }
    return localzza1.compareTo(localzza2);
  }
  
  public zzbpe zze(zzbos paramzzbos, zzbpe paramzzbpe)
  {
    Object localObject;
    if (paramzzbos.zzZa()) {
      localObject = zzg(paramzzbpe);
    }
    do
    {
      return (zzbpe)localObject;
      localObject = this;
    } while (paramzzbpe.isEmpty());
    return zzbox.zzZp().zze(paramzzbos, paramzzbpe).zzg(this.zzcgQ);
  }
  
  public int zzh(zzbpe paramzzbpe)
  {
    if (paramzzbpe.isEmpty()) {
      return 1;
    }
    if ((paramzzbpe instanceof zzbot)) {
      return -1;
    }
    assert (paramzzbpe.zzZd()) : "Node is not leaf node!";
    if (((this instanceof zzbpc)) && ((paramzzbpe instanceof zzbow))) {
      return zza((zzbpc)this, (zzbow)paramzzbpe);
    }
    if (((this instanceof zzbow)) && ((paramzzbpe instanceof zzbpc))) {
      return zza((zzbpc)paramzzbpe, (zzbow)this) * -1;
    }
    return zzc((zzbpb)paramzzbpe);
  }
  
  public boolean zzk(zzbos paramzzbos)
  {
    return false;
  }
  
  public zzbos zzl(zzbos paramzzbos)
  {
    return null;
  }
  
  public zzbpe zzl(zzbmj paramzzbmj, zzbpe paramzzbpe)
  {
    zzbos localzzbos = paramzzbmj.zzXi();
    if (localzzbos == null) {
      return paramzzbpe;
    }
    if ((paramzzbpe.isEmpty()) && (!localzzbos.zzZa())) {
      return this;
    }
    assert ((!paramzzbmj.zzXi().zzZa()) || (paramzzbmj.size() == 1));
    return zze(localzzbos, zzbox.zzZp().zzl(paramzzbmj.zzXj(), paramzzbpe));
  }
  
  public zzbpe zzm(zzbos paramzzbos)
  {
    if (paramzzbos.zzZa()) {
      return this.zzcgQ;
    }
    return zzbox.zzZp();
  }
  
  protected static enum zza
  {
    private zza() {}
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbpb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */