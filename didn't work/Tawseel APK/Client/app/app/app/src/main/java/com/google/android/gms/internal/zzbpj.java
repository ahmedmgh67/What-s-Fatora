package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class zzbpj
{
  private final zzbmj zzchA;
  private final zzbmj zzchB;
  private final zzbpe zzchC;
  
  static
  {
    if (!zzbpj.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public zzbpj(zzblt paramzzblt)
  {
    Object localObject1 = paramzzblt.zzWm();
    if (localObject1 != null) {}
    for (localObject1 = new zzbmj((List)localObject1);; localObject1 = null)
    {
      this.zzchA = ((zzbmj)localObject1);
      List localList = paramzzblt.zzWn();
      localObject1 = localObject2;
      if (localList != null) {
        localObject1 = new zzbmj(localList);
      }
      this.zzchB = ((zzbmj)localObject1);
      this.zzchC = zzbpf.zzar(paramzzblt.zzWo());
      return;
    }
  }
  
  private zzbpe zzb(zzbmj paramzzbmj, zzbpe paramzzbpe1, zzbpe paramzzbpe2)
  {
    int m = 1;
    int i;
    int j;
    label23:
    int k;
    if (this.zzchA == null)
    {
      i = 1;
      if (this.zzchB != null) {
        break label92;
      }
      j = -1;
      if ((this.zzchA == null) || (!paramzzbmj.zzi(this.zzchA))) {
        break label105;
      }
      k = 1;
      label44:
      if ((this.zzchB == null) || (!paramzzbmj.zzi(this.zzchB))) {
        break label111;
      }
      label62:
      if ((i <= 0) || (j >= 0) || (m != 0)) {
        break label117;
      }
    }
    label92:
    label105:
    label111:
    label117:
    while ((i > 0) && (m != 0) && (paramzzbpe2.zzZd()))
    {
      return paramzzbpe2;
      i = paramzzbmj.zzj(this.zzchA);
      break;
      j = paramzzbmj.zzj(this.zzchB);
      break label23;
      k = 0;
      break label44;
      m = 0;
      break label62;
    }
    if ((i > 0) && (j == 0))
    {
      assert (m != 0);
      assert (!paramzzbpe2.zzZd());
      if (paramzzbpe1.zzZd()) {
        return zzbox.zzZp();
      }
      return paramzzbpe1;
    }
    Object localObject1;
    if ((k != 0) || (m != 0))
    {
      localObject1 = new HashSet();
      Object localObject2 = paramzzbpe1.iterator();
      while (((Iterator)localObject2).hasNext()) {
        ((Set)localObject1).add(((zzbpd)((Iterator)localObject2).next()).zzZz());
      }
      localObject2 = paramzzbpe2.iterator();
      while (((Iterator)localObject2).hasNext()) {
        ((Set)localObject1).add(((zzbpd)((Iterator)localObject2).next()).zzZz());
      }
      localObject2 = new ArrayList(((Set)localObject1).size() + 1);
      ((List)localObject2).addAll((Collection)localObject1);
      if ((!paramzzbpe2.zzZe().isEmpty()) || (!paramzzbpe1.zzZe().isEmpty())) {
        ((List)localObject2).add(zzbos.zzYY());
      }
      localObject2 = ((List)localObject2).iterator();
      localObject1 = paramzzbpe1;
      if (((Iterator)localObject2).hasNext())
      {
        zzbos localzzbos = (zzbos)((Iterator)localObject2).next();
        zzbpe localzzbpe1 = paramzzbpe1.zzm(localzzbos);
        zzbpe localzzbpe2 = zzb(paramzzbmj.zza(localzzbos), paramzzbpe1.zzm(localzzbos), paramzzbpe2.zzm(localzzbos));
        if (localzzbpe2 == localzzbpe1) {
          break label497;
        }
        localObject1 = ((zzbpe)localObject1).zze(localzzbos, localzzbpe2);
      }
    }
    label497:
    for (;;)
    {
      break;
      return (zzbpe)localObject1;
      assert ((j > 0) || (i <= 0));
      return paramzzbpe1;
    }
  }
  
  public String toString()
  {
    String str1 = String.valueOf(this.zzchA);
    String str2 = String.valueOf(this.zzchB);
    String str3 = String.valueOf(this.zzchC);
    return String.valueOf(str1).length() + 55 + String.valueOf(str2).length() + String.valueOf(str3).length() + "RangeMerge{optExclusiveStart=" + str1 + ", optInclusiveEnd=" + str2 + ", snap=" + str3 + "}";
  }
  
  public zzbpe zzr(zzbpe paramzzbpe)
  {
    return zzb(zzbmj.zzXf(), paramzzbpe, this.zzchC);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbpj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */