package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class zzbtc
  implements zzbse
{
  private final zzbsm zzcmD;
  private final zzbrk zzcmF;
  private final zzbsl zzcmu;
  
  public zzbtc(zzbsl paramzzbsl, zzbrk paramzzbrk, zzbsm paramzzbsm)
  {
    this.zzcmu = paramzzbsl;
    this.zzcmF = paramzzbrk;
    this.zzcmD = paramzzbsm;
  }
  
  private zzbsd<?> zza(zzbrl paramzzbrl, Field paramField, zzbth<?> paramzzbth)
  {
    paramField = (zzbsf)paramField.getAnnotation(zzbsf.class);
    if (paramField != null)
    {
      paramField = zzbsx.zza(this.zzcmu, paramzzbrl, paramzzbth, paramField);
      if (paramField != null) {
        return paramField;
      }
    }
    return paramzzbrl.zza(paramzzbth);
  }
  
  private zzb zza(final zzbrl paramzzbrl, final Field paramField, String paramString, final zzbth<?> paramzzbth, boolean paramBoolean1, boolean paramBoolean2)
  {
    new zzb(paramString, paramBoolean1, paramBoolean2)
    {
      final zzbsd<?> zzcoo = zzbtc.zza(zzbtc.this, paramzzbrl, paramField, paramzzbth);
      
      void zza(zzbti paramAnonymouszzbti, Object paramAnonymousObject)
        throws IOException, IllegalAccessException
      {
        paramAnonymouszzbti = this.zzcoo.zzb(paramAnonymouszzbti);
        if ((paramAnonymouszzbti != null) || (!this.zzcos)) {
          paramField.set(paramAnonymousObject, paramAnonymouszzbti);
        }
      }
      
      void zza(zzbtk paramAnonymouszzbtk, Object paramAnonymousObject)
        throws IOException, IllegalAccessException
      {
        paramAnonymousObject = paramField.get(paramAnonymousObject);
        new zzbtf(paramzzbrl, this.zzcoo, paramzzbth.zzacc()).zza(paramAnonymouszzbtk, paramAnonymousObject);
      }
      
      public boolean zzaQ(Object paramAnonymousObject)
        throws IOException, IllegalAccessException
      {
        if (!this.zzcov) {}
        while (paramField.get(paramAnonymousObject) == paramAnonymousObject) {
          return false;
        }
        return true;
      }
    };
  }
  
  static List<String> zza(zzbrk paramzzbrk, Field paramField)
  {
    zzbsg localzzbsg = (zzbsg)paramField.getAnnotation(zzbsg.class);
    LinkedList localLinkedList = new LinkedList();
    if (localzzbsg == null) {
      localLinkedList.add(paramzzbrk.zzc(paramField));
    }
    for (;;)
    {
      return localLinkedList;
      localLinkedList.add(localzzbsg.value());
      paramzzbrk = localzzbsg.zzabH();
      int j = paramzzbrk.length;
      int i = 0;
      while (i < j)
      {
        localLinkedList.add(paramzzbrk[i]);
        i += 1;
      }
    }
  }
  
  private Map<String, zzb> zza(zzbrl paramzzbrl, zzbth<?> paramzzbth, Class<?> paramClass)
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    if (paramClass.isInterface()) {
      return localLinkedHashMap;
    }
    Type localType1 = paramzzbth.zzacc();
    Object localObject1 = paramClass;
    paramClass = paramzzbth;
    label94:
    int j;
    if (localObject1 != Object.class)
    {
      Field[] arrayOfField = ((Class)localObject1).getDeclaredFields();
      int k = arrayOfField.length;
      int i = 0;
      for (;;)
      {
        if (i < k)
        {
          Field localField = arrayOfField[i];
          boolean bool1 = zza(localField, true);
          boolean bool2 = zza(localField, false);
          if ((!bool1) && (!bool2))
          {
            i += 1;
          }
          else
          {
            localField.setAccessible(true);
            Type localType2 = zzbsk.zza(paramClass.zzacc(), (Class)localObject1, localField.getGenericType());
            List localList = zzd(localField);
            paramzzbth = null;
            j = 0;
            label138:
            if (j < localList.size())
            {
              Object localObject2 = (String)localList.get(j);
              if (j != 0) {
                bool1 = false;
              }
              localObject2 = (zzb)localLinkedHashMap.put(localObject2, zza(paramzzbrl, localField, (String)localObject2, zzbth.zzl(localType2), bool1, bool2));
              if (paramzzbth != null) {
                break label314;
              }
              paramzzbth = (zzbth<?>)localObject2;
            }
          }
        }
      }
    }
    label314:
    for (;;)
    {
      j += 1;
      break label138;
      if (paramzzbth == null) {
        break label94;
      }
      paramzzbrl = String.valueOf(localType1);
      paramzzbth = paramzzbth.name;
      throw new IllegalArgumentException(String.valueOf(paramzzbrl).length() + 37 + String.valueOf(paramzzbth).length() + paramzzbrl + " declares multiple JSON fields named " + paramzzbth);
      paramClass = zzbth.zzl(zzbsk.zza(paramClass.zzacc(), (Class)localObject1, ((Class)localObject1).getGenericSuperclass()));
      localObject1 = paramClass.zzacb();
      break;
      return localLinkedHashMap;
    }
  }
  
  static boolean zza(Field paramField, boolean paramBoolean, zzbsm paramzzbsm)
  {
    return (!paramzzbsm.zza(paramField.getType(), paramBoolean)) && (!paramzzbsm.zza(paramField, paramBoolean));
  }
  
  private List<String> zzd(Field paramField)
  {
    return zza(this.zzcmF, paramField);
  }
  
  public <T> zzbsd<T> zza(zzbrl paramzzbrl, zzbth<T> paramzzbth)
  {
    Class localClass = paramzzbth.zzacb();
    if (!Object.class.isAssignableFrom(localClass)) {
      return null;
    }
    return new zza(this.zzcmu.zzb(paramzzbth), zza(paramzzbrl, paramzzbth, localClass), null);
  }
  
  public boolean zza(Field paramField, boolean paramBoolean)
  {
    return zza(paramField, paramBoolean, this.zzcmD);
  }
  
  public static final class zza<T>
    extends zzbsd<T>
  {
    private final zzbsq<T> zzcob;
    private final Map<String, zzbtc.zzb> zzcou;
    
    private zza(zzbsq<T> paramzzbsq, Map<String, zzbtc.zzb> paramMap)
    {
      this.zzcob = paramzzbsq;
      this.zzcou = paramMap;
    }
    
    public void zza(zzbtk paramzzbtk, T paramT)
      throws IOException
    {
      if (paramT == null)
      {
        paramzzbtk.zzaca();
        return;
      }
      paramzzbtk.zzabY();
      try
      {
        Iterator localIterator = this.zzcou.values().iterator();
        while (localIterator.hasNext())
        {
          zzbtc.zzb localzzb = (zzbtc.zzb)localIterator.next();
          if (localzzb.zzaQ(paramT))
          {
            paramzzbtk.zzjW(localzzb.name);
            localzzb.zza(paramzzbtk, paramT);
          }
        }
        paramzzbtk.zzabZ();
      }
      catch (IllegalAccessException paramzzbtk)
      {
        throw new AssertionError();
      }
    }
    
    public T zzb(zzbti paramzzbti)
      throws IOException
    {
      if (paramzzbti.zzabQ() == zzbtj.zzcqa)
      {
        paramzzbti.nextNull();
        return null;
      }
      Object localObject1 = this.zzcob.zzabJ();
      try
      {
        paramzzbti.beginObject();
        for (;;)
        {
          if (!paramzzbti.hasNext()) {
            break label103;
          }
          localObject2 = paramzzbti.nextName();
          localObject2 = (zzbtc.zzb)this.zzcou.get(localObject2);
          if ((localObject2 != null) && (((zzbtc.zzb)localObject2).zzcow)) {
            break;
          }
          paramzzbti.skipValue();
        }
      }
      catch (IllegalStateException paramzzbti)
      {
        for (;;)
        {
          Object localObject2;
          throw new zzbsa(paramzzbti);
          ((zzbtc.zzb)localObject2).zza(paramzzbti, localObject1);
        }
      }
      catch (IllegalAccessException paramzzbti)
      {
        throw new AssertionError(paramzzbti);
      }
      label103:
      paramzzbti.endObject();
      return (T)localObject1;
    }
  }
  
  static abstract class zzb
  {
    final String name;
    final boolean zzcov;
    final boolean zzcow;
    
    protected zzb(String paramString, boolean paramBoolean1, boolean paramBoolean2)
    {
      this.name = paramString;
      this.zzcov = paramBoolean1;
      this.zzcow = paramBoolean2;
    }
    
    abstract void zza(zzbti paramzzbti, Object paramObject)
      throws IOException, IllegalAccessException;
    
    abstract void zza(zzbtk paramzzbtk, Object paramObject)
      throws IOException, IllegalAccessException;
    
    abstract boolean zzaQ(Object paramObject)
      throws IOException, IllegalAccessException;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbtc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */