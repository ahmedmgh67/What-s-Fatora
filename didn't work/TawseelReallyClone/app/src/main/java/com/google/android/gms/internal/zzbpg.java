package com.google.android.gms.internal;

public class zzbpg
  extends zzboy
{
  private final zzbmj zzchy;
  
  public zzbpg(zzbmj paramzzbmj)
  {
    if ((paramzzbmj.size() == 1) && (paramzzbmj.zzXi().zzZa())) {
      throw new IllegalArgumentException("Can't create PathIndex with '.priority' as key. Please use PriorityIndex instead!");
    }
    this.zzchy = paramzzbmj;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (getClass() != paramObject.getClass())) {
        return false;
      }
      paramObject = (zzbpg)paramObject;
    } while (this.zzchy.equals(((zzbpg)paramObject).zzchy));
    return false;
  }
  
  public int hashCode()
  {
    return this.zzchy.hashCode();
  }
  
  public zzbpd zzZr()
  {
    zzbpe localzzbpe = zzbox.zzZp().zzl(this.zzchy, zzbpe.zzchu);
    return new zzbpd(zzbos.zzYX(), localzzbpe);
  }
  
  public String zzZs()
  {
    return this.zzchy.zzXg();
  }
  
  public int zza(zzbpd paramzzbpd1, zzbpd paramzzbpd2)
  {
    int j = paramzzbpd1.zzUY().zzO(this.zzchy).compareTo(paramzzbpd2.zzUY().zzO(this.zzchy));
    int i = j;
    if (j == 0) {
      i = paramzzbpd1.zzZz().zzi(paramzzbpd2.zzZz());
    }
    return i;
  }
  
  public zzbpd zzg(zzbos paramzzbos, zzbpe paramzzbpe)
  {
    return new zzbpd(paramzzbos, zzbox.zzZp().zzl(this.zzchy, paramzzbpe));
  }
  
  public boolean zzm(zzbpe paramzzbpe)
  {
    return !paramzzbpe.zzO(this.zzchy).isEmpty();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbpg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */