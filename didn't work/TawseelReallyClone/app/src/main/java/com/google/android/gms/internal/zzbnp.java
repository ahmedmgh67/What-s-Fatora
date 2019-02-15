package com.google.android.gms.internal;

public class zzbnp
{
  private static final zzbnt<Boolean> zzceZ = new zzbnt()
  {
    public boolean zzf(Boolean paramAnonymousBoolean)
    {
      return !paramAnonymousBoolean.booleanValue();
    }
  };
  private static final zzbnt<Boolean> zzcfa = new zzbnt()
  {
    public boolean zzf(Boolean paramAnonymousBoolean)
    {
      return paramAnonymousBoolean.booleanValue();
    }
  };
  private static final zzbns<Boolean> zzcfb = new zzbns(Boolean.valueOf(true));
  private static final zzbns<Boolean> zzcfc = new zzbns(Boolean.valueOf(false));
  private final zzbns<Boolean> zzceY;
  
  public zzbnp()
  {
    this.zzceY = zzbns.zzYd();
  }
  
  private zzbnp(zzbns<Boolean> paramzzbns)
  {
    this.zzceY = paramzzbns;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof zzbnp)) {
        return false;
      }
      paramObject = (zzbnp)paramObject;
    } while (this.zzceY.equals(((zzbnp)paramObject).zzceY));
    return false;
  }
  
  public int hashCode()
  {
    return this.zzceY.hashCode();
  }
  
  public String toString()
  {
    String str = String.valueOf(this.zzceY.toString());
    return String.valueOf(str).length() + 14 + "{PruneForest:" + str + "}";
  }
  
  public boolean zzXY()
  {
    return this.zzceY.zzb(zzcfa);
  }
  
  public <T> T zza(T paramT, final zzbns.zza<Void, T> paramzza)
  {
    (T)this.zzceY.zzb(paramT, new zzbns.zza()
    {
      public T zza(zzbmj paramAnonymouszzbmj, Boolean paramAnonymousBoolean, T paramAnonymousT)
      {
        Object localObject = paramAnonymousT;
        if (!paramAnonymousBoolean.booleanValue()) {
          localObject = paramzza.zza(paramAnonymouszzbmj, null, paramAnonymousT);
        }
        return (T)localObject;
      }
    });
  }
  
  public zzbnp zzd(zzbos paramzzbos)
  {
    paramzzbos = this.zzceY.zze(paramzzbos);
    if (paramzzbos == null) {
      paramzzbos = new zzbns((Boolean)this.zzceY.getValue());
    }
    for (;;)
    {
      return new zzbnp(paramzzbos);
      if ((paramzzbos.getValue() == null) && (this.zzceY.getValue() != null)) {
        paramzzbos = paramzzbos.zzb(zzbmj.zzXf(), (Boolean)this.zzceY.getValue());
      }
    }
  }
  
  public boolean zzw(zzbmj paramzzbmj)
  {
    paramzzbmj = (Boolean)this.zzceY.zzH(paramzzbmj);
    return (paramzzbmj != null) && (paramzzbmj.booleanValue());
  }
  
  public boolean zzx(zzbmj paramzzbmj)
  {
    paramzzbmj = (Boolean)this.zzceY.zzH(paramzzbmj);
    return (paramzzbmj != null) && (!paramzzbmj.booleanValue());
  }
  
  public zzbnp zzy(zzbmj paramzzbmj)
  {
    if (this.zzceY.zzb(paramzzbmj, zzceZ) != null) {
      throw new IllegalArgumentException("Can't prune path that was kept previously!");
    }
    if (this.zzceY.zzb(paramzzbmj, zzcfa) != null) {
      return this;
    }
    return new zzbnp(this.zzceY.zza(paramzzbmj, zzcfb));
  }
  
  public zzbnp zzz(zzbmj paramzzbmj)
  {
    if (this.zzceY.zzb(paramzzbmj, zzceZ) != null) {
      return this;
    }
    return new zzbnp(this.zzceY.zza(paramzzbmj, zzcfc));
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbnp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */