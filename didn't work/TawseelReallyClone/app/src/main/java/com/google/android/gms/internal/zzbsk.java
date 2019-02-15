package com.google.android.gms.internal;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

public final class zzbsk
{
  static final Type[] zzcmZ = new Type[0];
  
  static boolean equal(Object paramObject1, Object paramObject2)
  {
    return (paramObject1 == paramObject2) || ((paramObject1 != null) && (paramObject1.equals(paramObject2)));
  }
  
  private static int zza(Object[] paramArrayOfObject, Object paramObject)
  {
    int i = 0;
    while (i < paramArrayOfObject.length)
    {
      if (paramObject.equals(paramArrayOfObject[i])) {
        return i;
      }
      i += 1;
    }
    throw new NoSuchElementException();
  }
  
  private static Class<?> zza(TypeVariable<?> paramTypeVariable)
  {
    paramTypeVariable = paramTypeVariable.getGenericDeclaration();
    if ((paramTypeVariable instanceof Class)) {
      return (Class)paramTypeVariable;
    }
    return null;
  }
  
  public static ParameterizedType zza(Type paramType1, Type paramType2, Type... paramVarArgs)
  {
    return new zzb(paramType1, paramType2, paramVarArgs);
  }
  
  public static Type zza(Type paramType, Class<?> paramClass)
  {
    paramClass = zzb(paramType, paramClass, Collection.class);
    paramType = paramClass;
    if ((paramClass instanceof WildcardType)) {
      paramType = ((WildcardType)paramClass).getUpperBounds()[0];
    }
    if ((paramType instanceof ParameterizedType)) {
      return ((ParameterizedType)paramType).getActualTypeArguments()[0];
    }
    return Object.class;
  }
  
  static Type zza(Type paramType, Class<?> paramClass1, Class<?> paramClass2)
  {
    if (paramClass2 == paramClass1) {
      return paramType;
    }
    if (paramClass2.isInterface())
    {
      paramType = paramClass1.getInterfaces();
      int i = 0;
      int j = paramType.length;
      while (i < j)
      {
        if (paramType[i] == paramClass2) {
          return paramClass1.getGenericInterfaces()[i];
        }
        if (paramClass2.isAssignableFrom(paramType[i])) {
          return zza(paramClass1.getGenericInterfaces()[i], paramType[i], paramClass2);
        }
        i += 1;
      }
    }
    if (!paramClass1.isInterface()) {
      while (paramClass1 != Object.class)
      {
        paramType = paramClass1.getSuperclass();
        if (paramType == paramClass2) {
          return paramClass1.getGenericSuperclass();
        }
        if (paramClass2.isAssignableFrom(paramType)) {
          return zza(paramClass1.getGenericSuperclass(), paramType, paramClass2);
        }
        paramClass1 = paramType;
      }
    }
    return paramClass2;
  }
  
  public static Type zza(Type paramType1, Class<?> paramClass, Type paramType2)
  {
    Object localObject1 = paramType2;
    if ((localObject1 instanceof TypeVariable))
    {
      localObject1 = (TypeVariable)localObject1;
      paramType2 = zza(paramType1, paramClass, (TypeVariable)localObject1);
      if (paramType2 != localObject1) {}
    }
    label90:
    label131:
    Object localObject3;
    label292:
    label361:
    do
    {
      do
      {
        Object localObject2;
        do
        {
          do
          {
            int i;
            do
            {
              do
              {
                do
                {
                  return paramType2;
                  localObject1 = paramType2;
                  break;
                  if ((!(localObject1 instanceof Class)) || (!((Class)localObject1).isArray())) {
                    break label90;
                  }
                  paramType2 = (Class)localObject1;
                  localObject1 = paramType2.getComponentType();
                  paramType1 = zza(paramType1, paramClass, (Type)localObject1);
                } while (localObject1 == paramType1);
                return zzb(paramType1);
                if (!(localObject1 instanceof GenericArrayType)) {
                  break label131;
                }
                paramType2 = (GenericArrayType)localObject1;
                localObject1 = paramType2.getGenericComponentType();
                paramType1 = zza(paramType1, paramClass, (Type)localObject1);
              } while (localObject1 == paramType1);
              return zzb(paramType1);
              if (!(localObject1 instanceof ParameterizedType)) {
                break label292;
              }
              localObject2 = (ParameterizedType)localObject1;
              paramType2 = ((ParameterizedType)localObject2).getOwnerType();
              localObject3 = zza(paramType1, paramClass, paramType2);
              if (localObject3 != paramType2) {}
              for (i = 1;; i = 0)
              {
                localObject1 = ((ParameterizedType)localObject2).getActualTypeArguments();
                int m = localObject1.length;
                int k = 0;
                while (k < m)
                {
                  Type localType = zza(paramType1, paramClass, localObject1[k]);
                  paramType2 = (Type)localObject1;
                  int j = i;
                  if (localType != localObject1[k])
                  {
                    paramType2 = (Type)localObject1;
                    j = i;
                    if (i == 0)
                    {
                      paramType2 = (Type[])((Type[])localObject1).clone();
                      j = 1;
                    }
                    paramType2[k] = localType;
                  }
                  k += 1;
                  localObject1 = paramType2;
                  i = j;
                }
              }
              paramType2 = (Type)localObject2;
            } while (i == 0);
            return zza((Type)localObject3, ((ParameterizedType)localObject2).getRawType(), (Type[])localObject1);
            paramType2 = (Type)localObject1;
          } while (!(localObject1 instanceof WildcardType));
          localObject1 = (WildcardType)localObject1;
          localObject2 = ((WildcardType)localObject1).getLowerBounds();
          localObject3 = ((WildcardType)localObject1).getUpperBounds();
          if (localObject2.length != 1) {
            break label361;
          }
          paramType1 = zza(paramType1, paramClass, localObject2[0]);
          paramType2 = (Type)localObject1;
        } while (paramType1 == localObject2[0]);
        return zzd(paramType1);
        paramType2 = (Type)localObject1;
      } while (localObject3.length != 1);
      paramType1 = zza(paramType1, paramClass, localObject3[0]);
      paramType2 = (Type)localObject1;
    } while (paramType1 == localObject3[0]);
    return zzc(paramType1);
  }
  
  static Type zza(Type paramType, Class<?> paramClass, TypeVariable<?> paramTypeVariable)
  {
    Class localClass = zza(paramTypeVariable);
    if (localClass == null) {}
    do
    {
      return paramTypeVariable;
      paramType = zza(paramType, paramClass, localClass);
    } while (!(paramType instanceof ParameterizedType));
    int i = zza(localClass.getTypeParameters(), paramTypeVariable);
    return ((ParameterizedType)paramType).getActualTypeArguments()[i];
  }
  
  public static boolean zza(Type paramType1, Type paramType2)
  {
    boolean bool4 = true;
    boolean bool5 = true;
    boolean bool2 = true;
    boolean bool3 = false;
    if (paramType1 == paramType2) {
      bool1 = true;
    }
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              return bool1;
              if ((paramType1 instanceof Class)) {
                return paramType1.equals(paramType2);
              }
              if (!(paramType1 instanceof ParameterizedType)) {
                break;
              }
              bool1 = bool3;
            } while (!(paramType2 instanceof ParameterizedType));
            paramType1 = (ParameterizedType)paramType1;
            paramType2 = (ParameterizedType)paramType2;
            if ((equal(paramType1.getOwnerType(), paramType2.getOwnerType())) && (paramType1.getRawType().equals(paramType2.getRawType())) && (Arrays.equals(paramType1.getActualTypeArguments(), paramType2.getActualTypeArguments()))) {}
            for (bool1 = bool2;; bool1 = false) {
              return bool1;
            }
            if (!(paramType1 instanceof GenericArrayType)) {
              break;
            }
            bool1 = bool3;
          } while (!(paramType2 instanceof GenericArrayType));
          paramType1 = (GenericArrayType)paramType1;
          paramType2 = (GenericArrayType)paramType2;
          return zza(paramType1.getGenericComponentType(), paramType2.getGenericComponentType());
          if (!(paramType1 instanceof WildcardType)) {
            break;
          }
          bool1 = bool3;
        } while (!(paramType2 instanceof WildcardType));
        paramType1 = (WildcardType)paramType1;
        paramType2 = (WildcardType)paramType2;
        if ((Arrays.equals(paramType1.getUpperBounds(), paramType2.getUpperBounds())) && (Arrays.equals(paramType1.getLowerBounds(), paramType2.getLowerBounds()))) {}
        for (bool1 = bool4;; bool1 = false) {
          return bool1;
        }
        bool1 = bool3;
      } while (!(paramType1 instanceof TypeVariable));
      bool1 = bool3;
    } while (!(paramType2 instanceof TypeVariable));
    paramType1 = (TypeVariable)paramType1;
    paramType2 = (TypeVariable)paramType2;
    if ((paramType1.getGenericDeclaration() == paramType2.getGenericDeclaration()) && (paramType1.getName().equals(paramType2.getName()))) {}
    for (boolean bool1 = bool5;; bool1 = false) {
      return bool1;
    }
  }
  
  private static int zzaM(Object paramObject)
  {
    if (paramObject != null) {
      return paramObject.hashCode();
    }
    return 0;
  }
  
  public static GenericArrayType zzb(Type paramType)
  {
    return new zza(paramType);
  }
  
  static Type zzb(Type paramType, Class<?> paramClass1, Class<?> paramClass2)
  {
    zzbsj.zzas(paramClass2.isAssignableFrom(paramClass1));
    return zza(paramType, paramClass1, zza(paramType, paramClass1, paramClass2));
  }
  
  public static Type[] zzb(Type paramType, Class<?> paramClass)
  {
    if (paramType == Properties.class) {
      return new Type[] { String.class, String.class };
    }
    paramType = zzb(paramType, paramClass, Map.class);
    if ((paramType instanceof ParameterizedType)) {
      return ((ParameterizedType)paramType).getActualTypeArguments();
    }
    return new Type[] { Object.class, Object.class };
  }
  
  public static WildcardType zzc(Type paramType)
  {
    Type[] arrayOfType = zzcmZ;
    return new zzc(new Type[] { paramType }, arrayOfType);
  }
  
  public static WildcardType zzd(Type paramType)
  {
    return new zzc(new Type[] { Object.class }, new Type[] { paramType });
  }
  
  public static Type zze(Type paramType)
  {
    if ((paramType instanceof Class))
    {
      paramType = (Class)paramType;
      if (paramType.isArray()) {
        paramType = new zza(zze(paramType.getComponentType()));
      }
      for (;;)
      {
        return (Type)paramType;
      }
    }
    if ((paramType instanceof ParameterizedType))
    {
      paramType = (ParameterizedType)paramType;
      return new zzb(paramType.getOwnerType(), paramType.getRawType(), paramType.getActualTypeArguments());
    }
    if ((paramType instanceof GenericArrayType)) {
      return new zza(((GenericArrayType)paramType).getGenericComponentType());
    }
    if ((paramType instanceof WildcardType))
    {
      paramType = (WildcardType)paramType;
      return new zzc(paramType.getUpperBounds(), paramType.getLowerBounds());
    }
    return paramType;
  }
  
  public static Class<?> zzf(Type paramType)
  {
    if ((paramType instanceof Class)) {
      return (Class)paramType;
    }
    if ((paramType instanceof ParameterizedType))
    {
      paramType = ((ParameterizedType)paramType).getRawType();
      zzbsj.zzas(paramType instanceof Class);
      return (Class)paramType;
    }
    if ((paramType instanceof GenericArrayType)) {
      return Array.newInstance(zzf(((GenericArrayType)paramType).getGenericComponentType()), 0).getClass();
    }
    if ((paramType instanceof TypeVariable)) {
      return Object.class;
    }
    if ((paramType instanceof WildcardType)) {
      return zzf(((WildcardType)paramType).getUpperBounds()[0]);
    }
    if (paramType == null) {}
    for (String str1 = "null";; str1 = paramType.getClass().getName())
    {
      String str2 = String.valueOf("Expected a Class, ParameterizedType, or GenericArrayType, but <");
      paramType = String.valueOf(paramType);
      throw new IllegalArgumentException(String.valueOf(str2).length() + 13 + String.valueOf(paramType).length() + String.valueOf(str1).length() + str2 + paramType + "> is of type " + str1);
    }
  }
  
  public static String zzg(Type paramType)
  {
    if ((paramType instanceof Class)) {
      return ((Class)paramType).getName();
    }
    return paramType.toString();
  }
  
  public static Type zzh(Type paramType)
  {
    if ((paramType instanceof GenericArrayType)) {
      return ((GenericArrayType)paramType).getGenericComponentType();
    }
    return ((Class)paramType).getComponentType();
  }
  
  private static void zzi(Type paramType)
  {
    if ((!(paramType instanceof Class)) || (!((Class)paramType).isPrimitive())) {}
    for (boolean bool = true;; bool = false)
    {
      zzbsj.zzas(bool);
      return;
    }
  }
  
  private static final class zza
    implements Serializable, GenericArrayType
  {
    private final Type zzcna;
    
    public zza(Type paramType)
    {
      this.zzcna = zzbsk.zze(paramType);
    }
    
    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof GenericArrayType)) && (zzbsk.zza(this, (GenericArrayType)paramObject));
    }
    
    public Type getGenericComponentType()
    {
      return this.zzcna;
    }
    
    public int hashCode()
    {
      return this.zzcna.hashCode();
    }
    
    public String toString()
    {
      return String.valueOf(zzbsk.zzg(this.zzcna)).concat("[]");
    }
  }
  
  private static final class zzb
    implements Serializable, ParameterizedType
  {
    private final Type zzcnb;
    private final Type zzcnc;
    private final Type[] zzcnd;
    
    public zzb(Type paramType1, Type paramType2, Type... paramVarArgs)
    {
      int i;
      boolean bool;
      if ((paramType2 instanceof Class))
      {
        Class localClass = (Class)paramType2;
        if ((Modifier.isStatic(localClass.getModifiers())) || (localClass.getEnclosingClass() == null))
        {
          i = 1;
          if ((paramType1 == null) && (i == 0)) {
            break label156;
          }
          bool = true;
          label54:
          zzbsj.zzas(bool);
        }
      }
      else
      {
        if (paramType1 != null) {
          break label162;
        }
      }
      label156:
      label162:
      for (paramType1 = null;; paramType1 = zzbsk.zze(paramType1))
      {
        this.zzcnb = paramType1;
        this.zzcnc = zzbsk.zze(paramType2);
        this.zzcnd = ((Type[])paramVarArgs.clone());
        i = j;
        while (i < this.zzcnd.length)
        {
          zzbsj.zzw(this.zzcnd[i]);
          zzbsk.zzj(this.zzcnd[i]);
          this.zzcnd[i] = zzbsk.zze(this.zzcnd[i]);
          i += 1;
        }
        i = 0;
        break;
        bool = false;
        break label54;
      }
    }
    
    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof ParameterizedType)) && (zzbsk.zza(this, (ParameterizedType)paramObject));
    }
    
    public Type[] getActualTypeArguments()
    {
      return (Type[])this.zzcnd.clone();
    }
    
    public Type getOwnerType()
    {
      return this.zzcnb;
    }
    
    public Type getRawType()
    {
      return this.zzcnc;
    }
    
    public int hashCode()
    {
      return Arrays.hashCode(this.zzcnd) ^ this.zzcnc.hashCode() ^ zzbsk.zzaN(this.zzcnb);
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder((this.zzcnd.length + 1) * 30);
      localStringBuilder.append(zzbsk.zzg(this.zzcnc));
      if (this.zzcnd.length == 0) {
        return localStringBuilder.toString();
      }
      localStringBuilder.append("<").append(zzbsk.zzg(this.zzcnd[0]));
      int i = 1;
      while (i < this.zzcnd.length)
      {
        localStringBuilder.append(", ").append(zzbsk.zzg(this.zzcnd[i]));
        i += 1;
      }
      return ">";
    }
  }
  
  private static final class zzc
    implements Serializable, WildcardType
  {
    private final Type zzcne;
    private final Type zzcnf;
    
    public zzc(Type[] paramArrayOfType1, Type[] paramArrayOfType2)
    {
      if (paramArrayOfType2.length <= 1)
      {
        bool1 = true;
        zzbsj.zzas(bool1);
        if (paramArrayOfType1.length != 1) {
          break label87;
        }
        bool1 = true;
        label27:
        zzbsj.zzas(bool1);
        if (paramArrayOfType2.length != 1) {
          break label97;
        }
        zzbsj.zzw(paramArrayOfType2[0]);
        zzbsk.zzj(paramArrayOfType2[0]);
        if (paramArrayOfType1[0] != Object.class) {
          break label92;
        }
      }
      label87:
      label92:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        zzbsj.zzas(bool1);
        this.zzcnf = zzbsk.zze(paramArrayOfType2[0]);
        this.zzcne = Object.class;
        return;
        bool1 = false;
        break;
        bool1 = false;
        break label27;
      }
      label97:
      zzbsj.zzw(paramArrayOfType1[0]);
      zzbsk.zzj(paramArrayOfType1[0]);
      this.zzcnf = null;
      this.zzcne = zzbsk.zze(paramArrayOfType1[0]);
    }
    
    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof WildcardType)) && (zzbsk.zza(this, (WildcardType)paramObject));
    }
    
    public Type[] getLowerBounds()
    {
      if (this.zzcnf != null) {
        return new Type[] { this.zzcnf };
      }
      return zzbsk.zzcmZ;
    }
    
    public Type[] getUpperBounds()
    {
      return new Type[] { this.zzcne };
    }
    
    public int hashCode()
    {
      if (this.zzcnf != null) {}
      for (int i = this.zzcnf.hashCode() + 31;; i = 1) {
        return i ^ this.zzcne.hashCode() + 31;
      }
    }
    
    public String toString()
    {
      if (this.zzcnf != null)
      {
        str = String.valueOf(zzbsk.zzg(this.zzcnf));
        if (str.length() != 0) {
          return "? super ".concat(str);
        }
        return new String("? super ");
      }
      if (this.zzcne == Object.class) {
        return "?";
      }
      String str = String.valueOf(zzbsk.zzg(this.zzcne));
      if (str.length() != 0) {
        return "? extends ".concat(str);
      }
      return new String("? extends ");
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbsk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */