package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseException;

public class zzbpi
{
  public static zzbpe zzZB()
  {
    return zzbox.zzZp();
  }
  
  public static zzbpe zzas(Object paramObject)
  {
    zzbpe localzzbpe = zzbpf.zzar(paramObject);
    paramObject = localzzbpe;
    if ((localzzbpe instanceof zzbpc)) {
      paramObject = new zzbow(Double.valueOf(((Long)localzzbpe.getValue()).longValue()), zzZB());
    }
    if (!zzq((zzbpe)paramObject)) {
      throw new DatabaseException("Invalid Firebase Database priority (must be a string, double, ServerValue, or null)");
    }
    return (zzbpe)paramObject;
  }
  
  public static boolean zzq(zzbpe paramzzbpe)
  {
    return (paramzzbpe.zzZe().isEmpty()) && ((paramzzbpe.isEmpty()) || ((paramzzbpe instanceof zzbow)) || ((paramzzbpe instanceof zzbpk)) || ((paramzzbpe instanceof zzbov)));
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbpi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */