package com.google.android.gms.internal;

public class zzblg<K, V>
  extends zzblh<K, V>
{
  zzblg(K paramK, V paramV)
  {
    super(paramK, paramV, zzble.zzVr(), zzble.zzVr());
  }
  
  zzblg(K paramK, V paramV, zzblf<K, V> paramzzblf1, zzblf<K, V> paramzzblf2)
  {
    super(paramK, paramV, paramzzblf1, paramzzblf2);
  }
  
  protected zzblf.zza zzVp()
  {
    return zzblf.zza.zzbYR;
  }
  
  public boolean zzVq()
  {
    return true;
  }
  
  protected zzblh<K, V> zza(K paramK, V paramV, zzblf<K, V> paramzzblf1, zzblf<K, V> paramzzblf2)
  {
    Object localObject = paramK;
    if (paramK == null) {
      localObject = getKey();
    }
    paramK = paramV;
    if (paramV == null) {
      paramK = getValue();
    }
    paramV = paramzzblf1;
    if (paramzzblf1 == null) {
      paramV = zzVs();
    }
    paramzzblf1 = paramzzblf2;
    if (paramzzblf2 == null) {
      paramzzblf1 = zzVt();
    }
    return new zzblg(localObject, paramK, paramV, paramzzblf1);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzblg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */