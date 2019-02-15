package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class zzbpf
{
  public static int zza(zzbos paramzzbos1, zzbpe paramzzbpe1, zzbos paramzzbos2, zzbpe paramzzbpe2)
  {
    int i = paramzzbpe1.compareTo(paramzzbpe2);
    if (i != 0) {
      return i;
    }
    return paramzzbos1.zzi(paramzzbos2);
  }
  
  public static zzbpe zza(Object paramObject, zzbpe paramzzbpe)
    throws DatabaseException
  {
    Object localObject1 = paramzzbpe;
    Object localObject2;
    Object localObject3;
    try
    {
      if (!(paramObject instanceof Map)) {
        break label504;
      }
      localObject2 = (Map)paramObject;
      if (((Map)localObject2).containsKey(".priority")) {
        paramzzbpe = zzbpi.zzas(((Map)localObject2).get(".priority"));
      }
      localObject1 = paramzzbpe;
      if (!((Map)localObject2).containsKey(".value")) {
        break label504;
      }
      paramObject = ((Map)localObject2).get(".value");
      if (paramObject == null) {
        return zzbox.zzZp();
      }
      if ((paramObject instanceof String)) {
        return new zzbpk((String)paramObject, paramzzbpe);
      }
      if ((paramObject instanceof Long)) {
        return new zzbpc((Long)paramObject, paramzzbpe);
      }
      if ((paramObject instanceof Integer)) {
        return new zzbpc(Long.valueOf(((Integer)paramObject).intValue()), paramzzbpe);
      }
      if ((paramObject instanceof Double)) {
        return new zzbow((Double)paramObject, paramzzbpe);
      }
      if ((paramObject instanceof Boolean)) {
        return new zzbor((Boolean)paramObject, paramzzbpe);
      }
      if ((!(paramObject instanceof Map)) && (!(paramObject instanceof List))) {
        break label454;
      }
      if (!(paramObject instanceof Map)) {
        break label350;
      }
      localObject1 = (Map)paramObject;
      if (((Map)localObject1).containsKey(".sv")) {
        return new zzbov((Map)localObject1, paramzzbpe);
      }
      paramObject = new HashMap(((Map)localObject1).size());
      localObject2 = ((Map)localObject1).keySet().iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (String)((Iterator)localObject2).next();
        if (!((String)localObject3).startsWith("."))
        {
          zzbpe localzzbpe = zzar(((Map)localObject1).get(localObject3));
          if (!localzzbpe.isEmpty()) {
            ((Map)paramObject).put(zzbos.zzjb((String)localObject3), localzzbpe);
          }
        }
      }
      if (!((Map)paramObject).isEmpty()) {
        break label438;
      }
    }
    catch (ClassCastException paramObject)
    {
      throw new DatabaseException("Failed to parse node", (Throwable)paramObject);
    }
    label337:
    return zzbox.zzZp();
    label350:
    localObject1 = (List)paramObject;
    paramObject = new HashMap(((List)localObject1).size());
    int i = 0;
    for (;;)
    {
      if (i < ((List)localObject1).size())
      {
        localObject2 = 11 + i;
        localObject3 = zzar(((List)localObject1).get(i));
        if (!((zzbpe)localObject3).isEmpty())
        {
          ((Map)paramObject).put(zzbos.zzjb((String)localObject2), localObject3);
          break label509;
          label438:
          return new zzbot(zzbla.zza.zzb((Map)paramObject, zzbot.zzcgP), paramzzbpe);
          label454:
          paramObject = String.valueOf(paramObject.getClass().toString());
          if (((String)paramObject).length() != 0) {}
          for (paramObject = "Failed to parse node with class ".concat((String)paramObject);; paramObject = new String("Failed to parse node with class ")) {
            throw new DatabaseException((String)paramObject);
          }
        }
      }
      else
      {
        break label337;
        label504:
        paramzzbpe = (zzbpe)localObject1;
        break;
      }
      label509:
      i += 1;
    }
  }
  
  public static zzbpe zzar(Object paramObject)
    throws DatabaseException
  {
    return zza(paramObject, zzbpi.zzZB());
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbpf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */