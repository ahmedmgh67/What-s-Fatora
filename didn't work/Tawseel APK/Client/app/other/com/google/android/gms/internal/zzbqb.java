package com.google.android.gms.internal;

import java.util.Iterator;

public class zzbqb
{
  static
  {
    if (!zzbqb.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  private static long zzd(zzbpb<?> paramzzbpb)
  {
    long l = 8L;
    if ((paramzzbpb instanceof zzbow)) {}
    while (paramzzbpb.zzZe().isEmpty())
    {
      return l;
      if (!(paramzzbpb instanceof zzbpc)) {
        if ((paramzzbpb instanceof zzbor))
        {
          l = 4L;
        }
        else if ((paramzzbpb instanceof zzbpk))
        {
          l = ((String)paramzzbpb.getValue()).length() + 2;
        }
        else
        {
          paramzzbpb = String.valueOf(paramzzbpb.getClass());
          throw new IllegalArgumentException(String.valueOf(paramzzbpb).length() + 24 + "Unknown leaf node type: " + paramzzbpb);
        }
      }
    }
    return zzd((zzbpb)paramzzbpb.zzZe()) + (24L + l);
  }
  
  public static long zzt(zzbpe paramzzbpe)
  {
    long l2;
    if (paramzzbpe.isEmpty()) {
      l2 = 4L;
    }
    long l1;
    do
    {
      return l2;
      if (paramzzbpe.zzZd()) {
        return zzd((zzbpb)paramzzbpe);
      }
      if ((!$assertionsDisabled) && (!(paramzzbpe instanceof zzbot)))
      {
        paramzzbpe = String.valueOf(paramzzbpe.getClass());
        throw new AssertionError(String.valueOf(paramzzbpe).length() + 22 + "Unexpected node type: " + paramzzbpe);
      }
      Iterator localIterator = paramzzbpe.iterator();
      zzbpd localzzbpd;
      for (l1 = 1L; localIterator.hasNext(); l1 = zzt(localzzbpd.zzUY()) + (l1 + l2 + 4L))
      {
        localzzbpd = (zzbpd)localIterator.next();
        l2 = localzzbpd.zzZz().asString().length();
      }
      l2 = l1;
    } while (paramzzbpe.zzZe().isEmpty());
    return l1 + 12L + zzd((zzbpb)paramzzbpe.zzZe());
  }
  
  public static int zzu(zzbpe paramzzbpe)
  {
    if (paramzzbpe.isEmpty()) {
      return 0;
    }
    if (paramzzbpe.zzZd()) {
      return 1;
    }
    if ((!$assertionsDisabled) && (!(paramzzbpe instanceof zzbot)))
    {
      paramzzbpe = String.valueOf(paramzzbpe.getClass());
      throw new AssertionError(String.valueOf(paramzzbpe).length() + 22 + "Unexpected node type: " + paramzzbpe);
    }
    paramzzbpe = paramzzbpe.iterator();
    for (int i = 0; paramzzbpe.hasNext(); i = zzu(((zzbpd)paramzzbpe.next()).zzUY()) + i) {}
    return i;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbqb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */