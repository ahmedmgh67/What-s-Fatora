package com.google.android.gms.internal;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class zzbox
  extends zzbot
  implements zzbpe
{
  private static final zzbox zzchh = new zzbox();
  
  public static zzbox zzZp()
  {
    return zzchh;
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof zzbox)) {
      return true;
    }
    if (((paramObject instanceof zzbpe)) && (((zzbpe)paramObject).isEmpty()) && (zzZe().equals(((zzbpe)paramObject).zzZe()))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int getChildCount()
  {
    return 0;
  }
  
  public Object getValue()
  {
    return null;
  }
  
  public Object getValue(boolean paramBoolean)
  {
    return null;
  }
  
  public int hashCode()
  {
    return 0;
  }
  
  public boolean isEmpty()
  {
    return true;
  }
  
  public Iterator<zzbpd> iterator()
  {
    return Collections.emptyList().iterator();
  }
  
  public String toString()
  {
    return "<Empty Node>";
  }
  
  public zzbpe zzO(zzbmj paramzzbmj)
  {
    return this;
  }
  
  public Iterator<zzbpd> zzVl()
  {
    return Collections.emptyList().iterator();
  }
  
  public String zzZc()
  {
    return "";
  }
  
  public boolean zzZd()
  {
    return false;
  }
  
  public zzbpe zzZe()
  {
    return this;
  }
  
  public String zza(zzbpe.zza paramzza)
  {
    return "";
  }
  
  public zzbpe zze(zzbos paramzzbos, zzbpe paramzzbpe)
  {
    if (paramzzbpe.isEmpty()) {}
    while (paramzzbos.zzZa()) {
      return this;
    }
    return new zzbot().zze(paramzzbos, paramzzbpe);
  }
  
  public int zzh(zzbpe paramzzbpe)
  {
    if (paramzzbpe.isEmpty()) {
      return 0;
    }
    return -1;
  }
  
  public boolean zzk(zzbos paramzzbos)
  {
    return false;
  }
  
  public zzbos zzl(zzbos paramzzbos)
  {
    return null;
  }
  
  public zzbox zzl(zzbpe paramzzbpe)
  {
    return this;
  }
  
  public zzbpe zzl(zzbmj paramzzbmj, zzbpe paramzzbpe)
  {
    if (paramzzbmj.isEmpty()) {
      return paramzzbpe;
    }
    zzbos localzzbos = paramzzbmj.zzXi();
    return zze(localzzbos, zzm(localzzbos).zzl(paramzzbmj.zzXj(), paramzzbpe));
  }
  
  public zzbpe zzm(zzbos paramzzbos)
  {
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */