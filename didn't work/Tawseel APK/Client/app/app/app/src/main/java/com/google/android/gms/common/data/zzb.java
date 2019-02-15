package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.zzac;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class zzb<T>
  implements Iterator<T>
{
  protected final DataBuffer<T> zzaCj;
  protected int zzaCk;
  
  public zzb(DataBuffer<T> paramDataBuffer)
  {
    this.zzaCj = ((DataBuffer)zzac.zzw(paramDataBuffer));
    this.zzaCk = -1;
  }
  
  public boolean hasNext()
  {
    return this.zzaCk < this.zzaCj.getCount() - 1;
  }
  
  public T next()
  {
    if (!hasNext())
    {
      i = this.zzaCk;
      throw new NoSuchElementException(46 + "Cannot advance the iterator beyond " + i);
    }
    DataBuffer localDataBuffer = this.zzaCj;
    int i = this.zzaCk + 1;
    this.zzaCk = i;
    return (T)localDataBuffer.get(i);
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\common\data\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */