package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class zzbnc
  implements zzbmf
{
  private static zzbnc zzcey;
  final HashMap<zzbme, List<zzbme>> zzcex = new HashMap();
  
  static
  {
    if (!zzbnc.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      zzcey = new zzbnc();
      return;
    }
  }
  
  public static zzbnc zzXK()
  {
    return zzcey;
  }
  
  private void zzj(zzbme paramzzbme)
  {
    int j = 0;
    label129:
    label239:
    label244:
    label249:
    for (;;)
    {
      Object localObject;
      synchronized (this.zzcex)
      {
        localObject = (List)this.zzcex.get(paramzzbme);
        if (localObject == null) {
          break label244;
        }
        i = 0;
        if (i >= ((List)localObject).size()) {
          break label239;
        }
        if (((List)localObject).get(i) == paramzzbme)
        {
          int k = 1;
          ((List)localObject).remove(i);
          i = k;
          if (!((List)localObject).isEmpty()) {
            break label249;
          }
          this.zzcex.remove(paramzzbme);
          break label249;
          if (($assertionsDisabled) || (i != 0) || (!paramzzbme.zzXc())) {
            break label129;
          }
          throw new AssertionError();
        }
      }
      i += 1;
      continue;
      List localList;
      if (!paramzzbme.zzWD().isDefault())
      {
        localObject = paramzzbme.zza(zzboe.zzN(paramzzbme.zzWD().zzVc()));
        localList = (List)this.zzcex.get(localObject);
        if (localList != null) {
          i = j;
        }
      }
      for (;;)
      {
        if (i < localList.size())
        {
          if (localList.get(i) == paramzzbme) {
            localList.remove(i);
          }
        }
        else
        {
          if (localList.isEmpty()) {
            this.zzcex.remove(localObject);
          }
          return;
        }
        i += 1;
      }
      int i = 0;
      continue;
      i = 0;
    }
  }
  
  public void zzd(zzbme paramzzbme)
  {
    zzj(paramzzbme);
  }
  
  public void zzi(zzbme paramzzbme)
  {
    synchronized (this.zzcex)
    {
      List localList = (List)this.zzcex.get(paramzzbme);
      Object localObject = localList;
      if (localList == null)
      {
        localObject = new ArrayList();
        this.zzcex.put(paramzzbme, localObject);
      }
      ((List)localObject).add(paramzzbme);
      if (!paramzzbme.zzWD().isDefault())
      {
        zzbme localzzbme = paramzzbme.zza(zzboe.zzN(paramzzbme.zzWD().zzVc()));
        localList = (List)this.zzcex.get(localzzbme);
        localObject = localList;
        if (localList == null)
        {
          localObject = new ArrayList();
          this.zzcex.put(localzzbme, localObject);
        }
        ((List)localObject).add(paramzzbme);
      }
      paramzzbme.zzba(true);
      paramzzbme.zza(this);
      return;
    }
  }
  
  public void zzk(zzbme paramzzbme)
  {
    for (;;)
    {
      int i;
      synchronized (this.zzcex)
      {
        List localList = (List)this.zzcex.get(paramzzbme);
        if ((localList != null) && (!localList.isEmpty())) {
          if (paramzzbme.zzWD().isDefault())
          {
            paramzzbme = new HashSet();
            i = localList.size() - 1;
            if (i >= 0)
            {
              zzbme localzzbme = (zzbme)localList.get(i);
              if (paramzzbme.contains(localzzbme.zzWD())) {
                break label132;
              }
              paramzzbme.add(localzzbme.zzWD());
              localzzbme.zzXa();
              break label132;
            }
          }
          else
          {
            ((zzbme)localList.get(0)).zzXa();
          }
        }
        return;
      }
      label132:
      i -= 1;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbnc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */