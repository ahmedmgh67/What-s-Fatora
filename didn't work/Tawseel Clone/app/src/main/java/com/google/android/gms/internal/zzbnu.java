package com.google.android.gms.internal;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class zzbnu<T>
{
  private zzbos zzcfw;
  private zzbnu<T> zzcfx;
  private zzbnv<T> zzcfy;
  
  static
  {
    if (!zzbnu.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public zzbnu()
  {
    this(null, null, new zzbnv());
  }
  
  public zzbnu(zzbos paramzzbos, zzbnu<T> paramzzbnu, zzbnv<T> paramzzbnv)
  {
    this.zzcfw = paramzzbos;
    this.zzcfx = paramzzbnu;
    this.zzcfy = paramzzbnv;
  }
  
  private void zzYf()
  {
    if (this.zzcfx != null) {
      this.zzcfx.zza(this.zzcfw, this);
    }
  }
  
  private void zza(zzbos paramzzbos, zzbnu<T> paramzzbnu)
  {
    boolean bool1 = paramzzbnu.isEmpty();
    boolean bool2 = this.zzcfy.zzcdw.containsKey(paramzzbos);
    if ((bool1) && (bool2))
    {
      this.zzcfy.zzcdw.remove(paramzzbos);
      zzYf();
    }
    while ((bool1) || (bool2)) {
      return;
    }
    this.zzcfy.zzcdw.put(paramzzbos, paramzzbnu.zzcfy);
    zzYf();
  }
  
  public T getValue()
  {
    return (T)this.zzcfy.value;
  }
  
  public boolean hasChildren()
  {
    return !this.zzcfy.zzcdw.isEmpty();
  }
  
  public boolean isEmpty()
  {
    return (this.zzcfy.value == null) && (this.zzcfy.zzcdw.isEmpty());
  }
  
  public void setValue(T paramT)
  {
    this.zzcfy.value = paramT;
    zzYf();
  }
  
  public String toString()
  {
    return toString("");
  }
  
  String toString(String paramString)
  {
    if (this.zzcfw == null) {}
    for (String str1 = "<anon>";; str1 = this.zzcfw.asString())
    {
      String str2 = String.valueOf(this.zzcfy.toString(String.valueOf(paramString).concat("\t")));
      return String.valueOf(paramString).length() + 1 + String.valueOf(str1).length() + String.valueOf(str2).length() + paramString + str1 + "\n" + str2;
    }
  }
  
  public zzbnu<T> zzL(zzbmj paramzzbmj)
  {
    Object localObject = paramzzbmj.zzXi();
    zzbmj localzzbmj = paramzzbmj;
    zzbnu localzzbnu = this;
    paramzzbmj = (zzbmj)localObject;
    if (paramzzbmj != null)
    {
      if (localzzbnu.zzcfy.zzcdw.containsKey(paramzzbmj)) {}
      for (localObject = (zzbnv)localzzbnu.zzcfy.zzcdw.get(paramzzbmj);; localObject = new zzbnv())
      {
        localzzbnu = new zzbnu(paramzzbmj, localzzbnu, (zzbnv)localObject);
        localzzbmj = localzzbmj.zzXj();
        paramzzbmj = localzzbmj.zzXi();
        break;
      }
    }
    return localzzbnu;
  }
  
  public zzbmj zzVc()
  {
    if (this.zzcfx != null)
    {
      assert (this.zzcfw != null);
      return this.zzcfx.zzVc().zza(this.zzcfw);
    }
    if (this.zzcfw != null) {
      return new zzbmj(new zzbos[] { this.zzcfw });
    }
    return zzbmj.zzXf();
  }
  
  public void zza(zzb<T> paramzzb)
  {
    zza(paramzzb, false, false);
  }
  
  public void zza(final zzb<T> paramzzb, boolean paramBoolean1, final boolean paramBoolean2)
  {
    if ((paramBoolean1) && (!paramBoolean2)) {
      paramzzb.zzd(this);
    }
    zzb(new zzb()
    {
      public void zzd(zzbnu<T> paramAnonymouszzbnu)
      {
        paramAnonymouszzbnu.zza(paramzzb, true, paramBoolean2);
      }
    });
    if ((paramBoolean1) && (paramBoolean2)) {
      paramzzb.zzd(this);
    }
  }
  
  public boolean zza(zza<T> paramzza)
  {
    return zza(paramzza, false);
  }
  
  public boolean zza(zza<T> paramzza, boolean paramBoolean)
  {
    zzbnu localzzbnu;
    if (paramBoolean) {
      localzzbnu = this;
    }
    while (localzzbnu != null)
    {
      paramzza.zze(localzzbnu);
      localzzbnu = localzzbnu.zzcfx;
      continue;
      localzzbnu = this.zzcfx;
    }
    return false;
  }
  
  public void zzb(zzb<T> paramzzb)
  {
    Object[] arrayOfObject = this.zzcfy.zzcdw.entrySet().toArray();
    int i = 0;
    while (i < arrayOfObject.length)
    {
      Map.Entry localEntry = (Map.Entry)arrayOfObject[i];
      paramzzb.zzd(new zzbnu((zzbos)localEntry.getKey(), this, (zzbnv)localEntry.getValue()));
      i += 1;
    }
  }
  
  public static abstract interface zza<T>
  {
    public abstract boolean zze(zzbnu<T> paramzzbnu);
  }
  
  public static abstract interface zzb<T>
  {
    public abstract void zzd(zzbnu<T> paramzzbnu);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbnu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */