package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

public class zzbnm
  implements zzbnn
{
  private boolean zzbYy = false;
  
  private void zzVi()
  {
    zzbqg.zzb(this.zzbYy, "Transaction expected to already be in progress.");
  }
  
  public List<zzbmx> zzVe()
  {
    return Collections.emptyList();
  }
  
  public void zzVh()
  {
    zzVi();
  }
  
  public void zza(zzbmj paramzzbmj, zzbma paramzzbma, long paramLong)
  {
    zzVi();
  }
  
  public void zza(zzbmj paramzzbmj, zzbpe paramzzbpe, long paramLong)
  {
    zzVi();
  }
  
  public void zza(zzboe paramzzboe, zzbpe paramzzbpe)
  {
    zzVi();
  }
  
  public void zza(zzboe paramzzboe, Set<zzbos> paramSet)
  {
    zzVi();
  }
  
  public void zza(zzboe paramzzboe, Set<zzbos> paramSet1, Set<zzbos> paramSet2)
  {
    zzVi();
  }
  
  public void zzaA(long paramLong)
  {
    zzVi();
  }
  
  public void zzc(zzbmj paramzzbmj, zzbma paramzzbma)
  {
    zzVi();
  }
  
  public void zzd(zzbmj paramzzbmj, zzbma paramzzbma)
  {
    zzVi();
  }
  
  public zzbnw zzf(zzboe paramzzboe)
  {
    return new zzbnw(zzboz.zza(zzbox.zzZp(), paramzzboe.zzYz()), false, false);
  }
  
  public <T> T zzf(Callable<T> paramCallable)
  {
    if (!this.zzbYy) {}
    for (boolean bool = true;; bool = false)
    {
      zzbqg.zzb(bool, "runInTransaction called when an existing transaction is already in progress.");
      this.zzbYy = true;
      try
      {
        paramCallable = paramCallable.call();
        return paramCallable;
      }
      catch (Throwable paramCallable)
      {
        throw new RuntimeException(paramCallable);
      }
      finally
      {
        this.zzbYy = false;
      }
    }
  }
  
  public void zzg(zzboe paramzzboe)
  {
    zzVi();
  }
  
  public void zzh(zzboe paramzzboe)
  {
    zzVi();
  }
  
  public void zzi(zzboe paramzzboe)
  {
    zzVi();
  }
  
  public void zzk(zzbmj paramzzbmj, zzbpe paramzzbpe)
  {
    zzVi();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbnm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */