package com.google.android.gms.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public final class zzbsl
{
  private final Map<Type, zzbrn<?>> zzcmG;
  
  public zzbsl(Map<Type, zzbrn<?>> paramMap)
  {
    this.zzcmG = paramMap;
  }
  
  private <T> zzbsq<T> zzc(final Type paramType, Class<? super T> paramClass)
  {
    if (Collection.class.isAssignableFrom(paramClass))
    {
      if (SortedSet.class.isAssignableFrom(paramClass)) {
        new zzbsq()
        {
          public T zzabJ()
          {
            return new TreeSet();
          }
        };
      }
      if (EnumSet.class.isAssignableFrom(paramClass)) {
        new zzbsq()
        {
          public T zzabJ()
          {
            if ((paramType instanceof ParameterizedType))
            {
              localObject = ((ParameterizedType)paramType).getActualTypeArguments()[0];
              if ((localObject instanceof Class)) {
                return EnumSet.noneOf((Class)localObject);
              }
              localObject = String.valueOf(paramType.toString());
              if (((String)localObject).length() != 0) {}
              for (localObject = "Invalid EnumSet type: ".concat((String)localObject);; localObject = new String("Invalid EnumSet type: ")) {
                throw new zzbrs((String)localObject);
              }
            }
            Object localObject = String.valueOf(paramType.toString());
            if (((String)localObject).length() != 0) {}
            for (localObject = "Invalid EnumSet type: ".concat((String)localObject);; localObject = new String("Invalid EnumSet type: ")) {
              throw new zzbrs((String)localObject);
            }
          }
        };
      }
      if (Set.class.isAssignableFrom(paramClass)) {
        new zzbsq()
        {
          public T zzabJ()
          {
            return new LinkedHashSet();
          }
        };
      }
      if (Queue.class.isAssignableFrom(paramClass)) {
        new zzbsq()
        {
          public T zzabJ()
          {
            return new LinkedList();
          }
        };
      }
      new zzbsq()
      {
        public T zzabJ()
        {
          return new ArrayList();
        }
      };
    }
    if (Map.class.isAssignableFrom(paramClass))
    {
      if (SortedMap.class.isAssignableFrom(paramClass)) {
        new zzbsq()
        {
          public T zzabJ()
          {
            return new TreeMap();
          }
        };
      }
      if (((paramType instanceof ParameterizedType)) && (!String.class.isAssignableFrom(zzbth.zzl(((ParameterizedType)paramType).getActualTypeArguments()[0]).zzacb()))) {
        new zzbsq()
        {
          public T zzabJ()
          {
            return new LinkedHashMap();
          }
        };
      }
      new zzbsq()
      {
        public T zzabJ()
        {
          return new zzbsp();
        }
      };
    }
    return null;
  }
  
  private <T> zzbsq<T> zzd(final Type paramType, final Class<? super T> paramClass)
  {
    new zzbsq()
    {
      private final zzbst zzcnj = zzbst.zzabO();
      
      public T zzabJ()
      {
        try
        {
          Object localObject = this.zzcnj.zze(paramClass);
          return (T)localObject;
        }
        catch (Exception localException)
        {
          String str = String.valueOf(paramType);
          throw new RuntimeException(String.valueOf(str).length() + 116 + "Unable to invoke no-args constructor for " + str + ". " + "Register an InstanceCreator with Gson for this type may fix this problem.", localException);
        }
      }
    };
  }
  
  private <T> zzbsq<T> zzk(final Class<? super T> paramClass)
  {
    try
    {
      paramClass = paramClass.getDeclaredConstructor(new Class[0]);
      if (!paramClass.isAccessible()) {
        paramClass.setAccessible(true);
      }
      paramClass = new zzbsq()
      {
        public T zzabJ()
        {
          try
          {
            Object localObject = paramClass.newInstance(null);
            return (T)localObject;
          }
          catch (InstantiationException localInstantiationException)
          {
            str = String.valueOf(paramClass);
            throw new RuntimeException(String.valueOf(str).length() + 30 + "Failed to invoke " + str + " with no args", localInstantiationException);
          }
          catch (InvocationTargetException localInvocationTargetException)
          {
            String str = String.valueOf(paramClass);
            throw new RuntimeException(String.valueOf(str).length() + 30 + "Failed to invoke " + str + " with no args", localInvocationTargetException.getTargetException());
          }
          catch (IllegalAccessException localIllegalAccessException)
          {
            throw new AssertionError(localIllegalAccessException);
          }
        }
      };
      return paramClass;
    }
    catch (NoSuchMethodException paramClass) {}
    return null;
  }
  
  public String toString()
  {
    return this.zzcmG.toString();
  }
  
  public <T> zzbsq<T> zzb(final zzbth<T> paramzzbth)
  {
    final Type localType = paramzzbth.zzacc();
    Class localClass = paramzzbth.zzacb();
    paramzzbth = (zzbrn)this.zzcmG.get(localType);
    if (paramzzbth != null) {
      paramzzbth = new zzbsq()
      {
        public T zzabJ()
        {
          return (T)paramzzbth.zza(localType);
        }
      };
    }
    zzbsq localzzbsq;
    do
    {
      do
      {
        return paramzzbth;
        paramzzbth = (zzbrn)this.zzcmG.get(localClass);
        if (paramzzbth != null) {
          new zzbsq()
          {
            public T zzabJ()
            {
              return (T)paramzzbth.zza(localType);
            }
          };
        }
        localzzbsq = zzk(localClass);
        paramzzbth = localzzbsq;
      } while (localzzbsq != null);
      localzzbsq = zzc(localType, localClass);
      paramzzbth = localzzbsq;
    } while (localzzbsq != null);
    return zzd(localType, localClass);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbsl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */