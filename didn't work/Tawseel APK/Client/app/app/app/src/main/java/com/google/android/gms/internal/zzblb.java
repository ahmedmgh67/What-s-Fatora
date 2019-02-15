package com.google.android.gms.internal;

import java.util.AbstractMap.SimpleEntry;
import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Stack;

public class zzblb<K, V>
  implements Iterator<Map.Entry<K, V>>
{
  private final Stack<zzblh<K, V>> zzbYM = new Stack();
  private final boolean zzbYN;
  
  zzblb(zzblf<K, V> paramzzblf, K paramK, Comparator<K> paramComparator, boolean paramBoolean)
  {
    this.zzbYN = paramBoolean;
    for (;;)
    {
      if (!paramzzblf.isEmpty())
      {
        int i;
        if (paramK != null) {
          if (paramBoolean) {
            i = paramComparator.compare(paramK, paramzzblf.getKey());
          }
        }
        for (;;)
        {
          if (i < 0)
          {
            if (paramBoolean)
            {
              paramzzblf = paramzzblf.zzVs();
              break;
              i = paramComparator.compare(paramzzblf.getKey(), paramK);
              continue;
              i = 1;
              continue;
            }
            paramzzblf = paramzzblf.zzVt();
            break;
          }
        }
        if (i == 0) {
          this.zzbYM.push((zzblh)paramzzblf);
        }
      }
      else
      {
        return;
      }
      this.zzbYM.push((zzblh)paramzzblf);
      if (paramBoolean) {
        paramzzblf = paramzzblf.zzVt();
      } else {
        paramzzblf = paramzzblf.zzVs();
      }
    }
  }
  
  public boolean hasNext()
  {
    return this.zzbYM.size() > 0;
  }
  
  public Map.Entry<K, V> next()
  {
    try
    {
      Object localObject = (zzblh)this.zzbYM.pop();
      AbstractMap.SimpleEntry localSimpleEntry = new AbstractMap.SimpleEntry(((zzblh)localObject).getKey(), ((zzblh)localObject).getValue());
      if (this.zzbYN) {
        for (localObject = ((zzblh)localObject).zzVs(); !((zzblf)localObject).isEmpty(); localObject = ((zzblf)localObject).zzVt()) {
          this.zzbYM.push((zzblh)localObject);
        }
      }
      for (localObject = ((zzblh)localObject).zzVt(); !((zzblf)localObject).isEmpty(); localObject = ((zzblf)localObject).zzVs()) {
        this.zzbYM.push((zzblh)localObject);
      }
      return localSimpleEntry;
    }
    catch (EmptyStackException localEmptyStackException)
    {
      throw new NoSuchElementException();
    }
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException("remove called on immutable collection");
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzblb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */