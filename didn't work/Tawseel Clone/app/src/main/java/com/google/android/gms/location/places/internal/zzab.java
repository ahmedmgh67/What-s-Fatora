package com.google.android.gms.location.places.internal;

import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.internal.zzbis;
import com.google.android.gms.internal.zzbus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class zzab
  extends zzc
{
  public zzab(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
  }
  
  protected String zzN(String paramString1, String paramString2)
  {
    String str = paramString2;
    if (zzdj(paramString1))
    {
      str = paramString2;
      if (!zzdl(paramString1)) {
        str = getString(paramString1);
      }
    }
    return str;
  }
  
  protected <E extends SafeParcelable> E zza(String paramString, Parcelable.Creator<E> paramCreator)
  {
    paramString = zzc(paramString, null);
    if (paramString == null) {
      return null;
    }
    return zzd.zza(paramString, paramCreator);
  }
  
  protected <E extends SafeParcelable> List<E> zza(String paramString, Parcelable.Creator<E> paramCreator, List<E> paramList)
  {
    paramString = zzc(paramString, null);
    if (paramString == null) {}
    do
    {
      for (;;)
      {
        return paramList;
        try
        {
          Object localObject = zzbis.zzS(paramString);
          if (((zzbis)localObject).zzbUx != null)
          {
            paramString = new ArrayList(((zzbis)localObject).zzbUx.length);
            localObject = ((zzbis)localObject).zzbUx;
            int j = localObject.length;
            int i = 0;
            while (i < j)
            {
              paramString.add(zzd.zza(localObject[i], paramCreator));
              i += 1;
            }
            return paramString;
          }
        }
        catch (zzbus paramString) {}
      }
    } while (!Log.isLoggable("SafeDataBufferRef", 6));
    Log.e("SafeDataBufferRef", "Cannot parse byte[]", paramString);
    return paramList;
  }
  
  protected List<Integer> zza(String paramString, List<Integer> paramList)
  {
    paramString = zzc(paramString, null);
    if (paramString == null) {}
    do
    {
      for (;;)
      {
        return paramList;
        try
        {
          paramString = zzbis.zzS(paramString);
          if (paramString.zzbUw != null)
          {
            ArrayList localArrayList = new ArrayList(paramString.zzbUw.length);
            int i = 0;
            while (i < paramString.zzbUw.length)
            {
              localArrayList.add(Integer.valueOf(paramString.zzbUw[i]));
              i += 1;
            }
            return localArrayList;
          }
        }
        catch (zzbus paramString) {}
      }
    } while (!Log.isLoggable("SafeDataBufferRef", 6));
    Log.e("SafeDataBufferRef", "Cannot parse byte[]", paramString);
    return paramList;
  }
  
  protected float zzb(String paramString, float paramFloat)
  {
    float f = paramFloat;
    if (zzdj(paramString))
    {
      f = paramFloat;
      if (!zzdl(paramString)) {
        f = getFloat(paramString);
      }
    }
    return f;
  }
  
  protected List<String> zzb(String paramString, List<String> paramList)
  {
    paramString = zzc(paramString, null);
    if (paramString == null) {}
    do
    {
      for (;;)
      {
        return paramList;
        try
        {
          paramString = zzbis.zzS(paramString);
          if (paramString.zzbUv != null)
          {
            paramString = Arrays.asList(paramString.zzbUv);
            return paramString;
          }
        }
        catch (zzbus paramString) {}
      }
    } while (!Log.isLoggable("SafeDataBufferRef", 6));
    Log.e("SafeDataBufferRef", "Cannot parse byte[]", paramString);
    return paramList;
  }
  
  protected byte[] zzc(String paramString, byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = paramArrayOfByte;
    if (zzdj(paramString))
    {
      arrayOfByte = paramArrayOfByte;
      if (!zzdl(paramString)) {
        arrayOfByte = getByteArray(paramString);
      }
    }
    return arrayOfByte;
  }
  
  protected boolean zzk(String paramString, boolean paramBoolean)
  {
    boolean bool = paramBoolean;
    if (zzdj(paramString))
    {
      bool = paramBoolean;
      if (!zzdl(paramString)) {
        bool = getBoolean(paramString);
      }
    }
    return bool;
  }
  
  protected int zzy(String paramString, int paramInt)
  {
    int i = paramInt;
    if (zzdj(paramString))
    {
      i = paramInt;
      if (!zzdl(paramString)) {
        i = getInteger(paramString);
      }
    }
    return i;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\internal\zzab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */