package com.google.android.gms.internal;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzbrm
{
  private zzbsm zzcmD = zzbsm.zzcnn;
  private zzbsb zzcmE = zzbsb.zzcmO;
  private zzbrk zzcmF = zzbrj.zzcml;
  private final Map<Type, zzbrn<?>> zzcmG = new HashMap();
  private final List<zzbse> zzcmH = new ArrayList();
  private int zzcmI = 2;
  private int zzcmJ = 2;
  private boolean zzcmK = true;
  private final List<zzbse> zzcmt = new ArrayList();
  
  private void zza(String paramString, int paramInt1, int paramInt2, List<zzbse> paramList)
  {
    if ((paramString != null) && (!"".equals(paramString.trim()))) {}
    for (paramString = new zzbrg(paramString);; paramString = new zzbrg(paramInt1, paramInt2))
    {
      paramList.add(zzbsc.zza(zzbth.zzq(java.util.Date.class), paramString));
      paramList.add(zzbsc.zza(zzbth.zzq(Timestamp.class), paramString));
      paramList.add(zzbsc.zza(zzbth.zzq(java.sql.Date.class), paramString));
      do
      {
        return;
      } while ((paramInt1 == 2) || (paramInt2 == 2));
    }
  }
  
  public zzbrm zza(Type paramType, Object paramObject)
  {
    if (((paramObject instanceof zzbrz)) || ((paramObject instanceof zzbrq)) || ((paramObject instanceof zzbrn)) || ((paramObject instanceof zzbsd))) {}
    for (boolean bool = true;; bool = false)
    {
      zzbsj.zzas(bool);
      if ((paramObject instanceof zzbrn)) {
        this.zzcmG.put(paramType, (zzbrn)paramObject);
      }
      if (((paramObject instanceof zzbrz)) || ((paramObject instanceof zzbrq)))
      {
        zzbth localzzbth = zzbth.zzl(paramType);
        this.zzcmt.add(zzbsc.zzb(localzzbth, paramObject));
      }
      if ((paramObject instanceof zzbsd)) {
        this.zzcmt.add(zzbtg.zza(zzbth.zzl(paramType), (zzbsd)paramObject));
      }
      return this;
    }
  }
  
  public zzbrm zza(zzbrh... paramVarArgs)
  {
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      zzbrh localzzbrh = paramVarArgs[i];
      this.zzcmD = this.zzcmD.zza(localzzbrh, true, true);
      i += 1;
    }
    return this;
  }
  
  public zzbrm zzabr()
  {
    this.zzcmK = false;
    return this;
  }
  
  public zzbrl zzabs()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(this.zzcmt);
    Collections.reverse(localArrayList);
    localArrayList.addAll(this.zzcmH);
    zza(null, this.zzcmI, this.zzcmJ, localArrayList);
    return new zzbrl(this.zzcmD, this.zzcmF, this.zzcmG, false, false, false, this.zzcmK, false, false, this.zzcmE, localArrayList);
  }
  
  public zzbrm zzf(int... paramVarArgs)
  {
    this.zzcmD = this.zzcmD.zzg(paramVarArgs);
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbrm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */