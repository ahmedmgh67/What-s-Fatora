package com.google.android.gms.internal;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class zzblc<T>
  implements Iterable<T>
{
  private final zzbla<T, Void> zzbYO;
  
  private zzblc(zzbla<T, Void> paramzzbla)
  {
    this.zzbYO = paramzzbla;
  }
  
  public zzblc(List<T> paramList, Comparator<T> paramComparator)
  {
    this.zzbYO = zzbla.zza.zzb(paramList, Collections.emptyMap(), zzbla.zza.zzVm(), paramComparator);
  }
  
  public Iterator<T> iterator()
  {
    return new zza(this.zzbYO.iterator());
  }
  
  public Iterator<T> zzVl()
  {
    return new zza(this.zzbYO.zzVl());
  }
  
  public T zzVn()
  {
    return (T)this.zzbYO.zzVj();
  }
  
  public T zzVo()
  {
    return (T)this.zzbYO.zzVk();
  }
  
  public zzblc<T> zzaj(T paramT)
  {
    paramT = this.zzbYO.zzae(paramT);
    if (paramT == this.zzbYO) {
      return this;
    }
    return new zzblc(paramT);
  }
  
  public zzblc<T> zzak(T paramT)
  {
    return new zzblc(this.zzbYO.zzj(paramT, null));
  }
  
  public T zzal(T paramT)
  {
    return (T)this.zzbYO.zzaf(paramT);
  }
  
  private static class zza<T>
    implements Iterator<T>
  {
    final Iterator<Map.Entry<T, Void>> zzbYP;
    
    public zza(Iterator<Map.Entry<T, Void>> paramIterator)
    {
      this.zzbYP = paramIterator;
    }
    
    public boolean hasNext()
    {
      return this.zzbYP.hasNext();
    }
    
    public T next()
    {
      return (T)((Map.Entry)this.zzbYP.next()).getKey();
    }
    
    public void remove()
    {
      this.zzbYP.remove();
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzblc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */