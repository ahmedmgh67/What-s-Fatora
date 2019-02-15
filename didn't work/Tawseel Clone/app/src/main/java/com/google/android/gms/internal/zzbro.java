package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class zzbro
  extends zzbrr
  implements Iterable<zzbrr>
{
  private final List<zzbrr> zzbNt = new ArrayList();
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof zzbro)) && (((zzbro)paramObject).zzbNt.equals(this.zzbNt)));
  }
  
  public boolean getAsBoolean()
  {
    if (this.zzbNt.size() == 1) {
      return ((zzbrr)this.zzbNt.get(0)).getAsBoolean();
    }
    throw new IllegalStateException();
  }
  
  public double getAsDouble()
  {
    if (this.zzbNt.size() == 1) {
      return ((zzbrr)this.zzbNt.get(0)).getAsDouble();
    }
    throw new IllegalStateException();
  }
  
  public int getAsInt()
  {
    if (this.zzbNt.size() == 1) {
      return ((zzbrr)this.zzbNt.get(0)).getAsInt();
    }
    throw new IllegalStateException();
  }
  
  public long getAsLong()
  {
    if (this.zzbNt.size() == 1) {
      return ((zzbrr)this.zzbNt.get(0)).getAsLong();
    }
    throw new IllegalStateException();
  }
  
  public int hashCode()
  {
    return this.zzbNt.hashCode();
  }
  
  public Iterator<zzbrr> iterator()
  {
    return this.zzbNt.iterator();
  }
  
  public int size()
  {
    return this.zzbNt.size();
  }
  
  public Number zzabt()
  {
    if (this.zzbNt.size() == 1) {
      return ((zzbrr)this.zzbNt.get(0)).zzabt();
    }
    throw new IllegalStateException();
  }
  
  public String zzabu()
  {
    if (this.zzbNt.size() == 1) {
      return ((zzbrr)this.zzbNt.get(0)).zzabu();
    }
    throw new IllegalStateException();
  }
  
  public void zzc(zzbrr paramzzbrr)
  {
    Object localObject = paramzzbrr;
    if (paramzzbrr == null) {
      localObject = zzbrt.zzcmL;
    }
    this.zzbNt.add(localObject);
  }
  
  public zzbrr zzqc(int paramInt)
  {
    return (zzbrr)this.zzbNt.get(paramInt);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbro.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */