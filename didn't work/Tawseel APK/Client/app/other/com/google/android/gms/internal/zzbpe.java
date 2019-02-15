package com.google.android.gms.internal;

import java.util.Iterator;

public abstract interface zzbpe
  extends Comparable<zzbpe>, Iterable<zzbpd>
{
  public static final zzbot zzchu = new zzbot()
  {
    public boolean equals(Object paramAnonymousObject)
    {
      return paramAnonymousObject == this;
    }
    
    public boolean isEmpty()
    {
      return false;
    }
    
    public String toString()
    {
      return "<Max Node>";
    }
    
    public zzbpe zzZe()
    {
      return this;
    }
    
    public int zzh(zzbpe paramAnonymouszzbpe)
    {
      if (paramAnonymouszzbpe == this) {
        return 0;
      }
      return 1;
    }
    
    public boolean zzk(zzbos paramAnonymouszzbos)
    {
      return false;
    }
    
    public zzbpe zzm(zzbos paramAnonymouszzbos)
    {
      if (paramAnonymouszzbos.zzZa()) {
        return zzZe();
      }
      return zzbox.zzZp();
    }
  };
  
  public abstract int getChildCount();
  
  public abstract Object getValue();
  
  public abstract Object getValue(boolean paramBoolean);
  
  public abstract boolean isEmpty();
  
  public abstract zzbpe zzO(zzbmj paramzzbmj);
  
  public abstract Iterator<zzbpd> zzVl();
  
  public abstract String zzZc();
  
  public abstract boolean zzZd();
  
  public abstract zzbpe zzZe();
  
  public abstract String zza(zza paramzza);
  
  public abstract zzbpe zze(zzbos paramzzbos, zzbpe paramzzbpe);
  
  public abstract zzbpe zzg(zzbpe paramzzbpe);
  
  public abstract boolean zzk(zzbos paramzzbos);
  
  public abstract zzbos zzl(zzbos paramzzbos);
  
  public abstract zzbpe zzl(zzbmj paramzzbmj, zzbpe paramzzbpe);
  
  public abstract zzbpe zzm(zzbos paramzzbos);
  
  public static enum zza
  {
    private zza() {}
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbpe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */