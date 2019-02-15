package com.google.android.gms.common.internal;

import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class zzaa
{
  public static boolean equal(@Nullable Object paramObject1, @Nullable Object paramObject2)
  {
    return (paramObject1 == paramObject2) || ((paramObject1 != null) && (paramObject1.equals(paramObject2)));
  }
  
  public static int hashCode(Object... paramVarArgs)
  {
    return Arrays.hashCode(paramVarArgs);
  }
  
  public static zza zzv(Object paramObject)
  {
    return new zza(paramObject, null);
  }
  
  public static final class zza
  {
    private final Object zzXN;
    private final List<String> zzaEY;
    
    private zza(Object paramObject)
    {
      this.zzXN = zzac.zzw(paramObject);
      this.zzaEY = new ArrayList();
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder(100).append(this.zzXN.getClass().getSimpleName()).append('{');
      int j = this.zzaEY.size();
      int i = 0;
      while (i < j)
      {
        localStringBuilder.append((String)this.zzaEY.get(i));
        if (i < j - 1) {
          localStringBuilder.append(", ");
        }
        i += 1;
      }
      return '}';
    }
    
    public zza zzg(String paramString, Object paramObject)
    {
      List localList = this.zzaEY;
      paramString = (String)zzac.zzw(paramString);
      paramObject = String.valueOf(String.valueOf(paramObject));
      localList.add(String.valueOf(paramString).length() + 1 + String.valueOf(paramObject).length() + paramString + "=" + (String)paramObject);
      return this;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\common\internal\zzaa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */