package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class zzbsm
  implements zzbse, Cloneable
{
  public static final zzbsm zzcnn = new zzbsm();
  private double zzcno = -1.0D;
  private int zzcnp = 136;
  private boolean zzcnq = true;
  private List<zzbrh> zzcnr = Collections.emptyList();
  private List<zzbrh> zzcns = Collections.emptyList();
  
  private boolean zza(zzbsh paramzzbsh)
  {
    return (paramzzbsh == null) || (paramzzbsh.zzabI() <= this.zzcno);
  }
  
  private boolean zza(zzbsh paramzzbsh, zzbsi paramzzbsi)
  {
    return (zza(paramzzbsh)) && (zza(paramzzbsi));
  }
  
  private boolean zza(zzbsi paramzzbsi)
  {
    return (paramzzbsi == null) || (paramzzbsi.zzabI() > this.zzcno);
  }
  
  private boolean zzl(Class<?> paramClass)
  {
    return (!Enum.class.isAssignableFrom(paramClass)) && ((paramClass.isAnonymousClass()) || (paramClass.isLocalClass()));
  }
  
  private boolean zzm(Class<?> paramClass)
  {
    return (paramClass.isMemberClass()) && (!zzn(paramClass));
  }
  
  private boolean zzn(Class<?> paramClass)
  {
    return (paramClass.getModifiers() & 0x8) != 0;
  }
  
  public <T> zzbsd<T> zza(final zzbrl paramzzbrl, final zzbth<T> paramzzbth)
  {
    Class localClass = paramzzbth.zzacb();
    final boolean bool1 = zza(localClass, true);
    final boolean bool2 = zza(localClass, false);
    if ((!bool1) && (!bool2)) {
      return null;
    }
    new zzbsd()
    {
      private zzbsd<T> zzcmC;
      
      private zzbsd<T> zzabG()
      {
        zzbsd localzzbsd = this.zzcmC;
        if (localzzbsd != null) {
          return localzzbsd;
        }
        localzzbsd = paramzzbrl.zza(zzbsm.this, paramzzbth);
        this.zzcmC = localzzbsd;
        return localzzbsd;
      }
      
      public void zza(zzbtk paramAnonymouszzbtk, T paramAnonymousT)
        throws IOException
      {
        if (bool1)
        {
          paramAnonymouszzbtk.zzaca();
          return;
        }
        zzabG().zza(paramAnonymouszzbtk, paramAnonymousT);
      }
      
      public T zzb(zzbti paramAnonymouszzbti)
        throws IOException
      {
        if (bool2)
        {
          paramAnonymouszzbti.skipValue();
          return null;
        }
        return (T)zzabG().zzb(paramAnonymouszzbti);
      }
    };
  }
  
  public zzbsm zza(zzbrh paramzzbrh, boolean paramBoolean1, boolean paramBoolean2)
  {
    zzbsm localzzbsm = zzabK();
    if (paramBoolean1)
    {
      localzzbsm.zzcnr = new ArrayList(this.zzcnr);
      localzzbsm.zzcnr.add(paramzzbrh);
    }
    if (paramBoolean2)
    {
      localzzbsm.zzcns = new ArrayList(this.zzcns);
      localzzbsm.zzcns.add(paramzzbrh);
    }
    return localzzbsm;
  }
  
  public boolean zza(Class<?> paramClass, boolean paramBoolean)
  {
    if ((this.zzcno != -1.0D) && (!zza((zzbsh)paramClass.getAnnotation(zzbsh.class), (zzbsi)paramClass.getAnnotation(zzbsi.class)))) {
      return true;
    }
    if ((!this.zzcnq) && (zzm(paramClass))) {
      return true;
    }
    if (zzl(paramClass)) {
      return true;
    }
    if (paramBoolean) {}
    for (Object localObject = this.zzcnr;; localObject = this.zzcns)
    {
      localObject = ((List)localObject).iterator();
      do
      {
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
      } while (!((zzbrh)((Iterator)localObject).next()).zzg(paramClass));
      return true;
    }
    return false;
  }
  
  public boolean zza(Field paramField, boolean paramBoolean)
  {
    if ((this.zzcnp & paramField.getModifiers()) != 0) {
      return true;
    }
    if ((this.zzcno != -1.0D) && (!zza((zzbsh)paramField.getAnnotation(zzbsh.class), (zzbsi)paramField.getAnnotation(zzbsi.class)))) {
      return true;
    }
    if (paramField.isSynthetic()) {
      return true;
    }
    if ((!this.zzcnq) && (zzm(paramField.getType()))) {
      return true;
    }
    if (zzl(paramField.getType())) {
      return true;
    }
    if (paramBoolean) {}
    for (Object localObject = this.zzcnr; !((List)localObject).isEmpty(); localObject = this.zzcns)
    {
      paramField = new zzbri(paramField);
      localObject = ((List)localObject).iterator();
      do
      {
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
      } while (!((zzbrh)((Iterator)localObject).next()).zza(paramField));
      return true;
    }
    return false;
  }
  
  protected zzbsm zzabK()
  {
    try
    {
      zzbsm localzzbsm = (zzbsm)super.clone();
      return localzzbsm;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new AssertionError();
    }
  }
  
  public zzbsm zzg(int... paramVarArgs)
  {
    int i = 0;
    zzbsm localzzbsm = zzabK();
    localzzbsm.zzcnp = 0;
    int j = paramVarArgs.length;
    while (i < j)
    {
      localzzbsm.zzcnp = (paramVarArgs[i] | localzzbsm.zzcnp);
      i += 1;
    }
    return localzzbsm;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbsm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */