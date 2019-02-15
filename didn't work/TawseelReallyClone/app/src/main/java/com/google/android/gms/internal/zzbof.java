package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class zzbof
{
  private final zzboe zzcfS;
  private final zzboh zzcgj;
  private zzbog zzcgk;
  private final List<zzbme> zzcgl;
  private final zzbob zzcgm;
  
  static
  {
    if (!zzbof.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public zzbof(zzboe paramzzboe, zzbog paramzzbog)
  {
    this.zzcfS = paramzzboe;
    Object localObject = new zzboj(paramzzboe.zzYz());
    zzbol localzzbol = paramzzboe.zzYG().zzYF();
    this.zzcgj = new zzboh(localzzbol);
    zzbnw localzzbnw = paramzzbog.zzYM();
    paramzzbog = paramzzbog.zzYK();
    zzboz localzzboz2 = zzboz.zza(zzbox.zzZp(), paramzzboe.zzYz());
    zzboz localzzboz1 = ((zzboj)localObject).zza(localzzboz2, localzzbnw.zzYi(), null);
    localzzboz2 = localzzbol.zza(localzzboz2, paramzzbog.zzYi(), null);
    localObject = new zzbnw(localzzboz1, localzzbnw.zzYg(), ((zzboj)localObject).zzYQ());
    this.zzcgk = new zzbog(new zzbnw(localzzboz2, paramzzbog.zzYg(), localzzbol.zzYQ()), (zzbnw)localObject);
    this.zzcgl = new ArrayList();
    this.zzcgm = new zzbob(paramzzboe);
  }
  
  private List<zzbnz> zza(List<zzbny> paramList, zzboz paramzzboz, zzbme paramzzbme)
  {
    if (paramzzbme == null) {}
    for (paramzzbme = this.zzcgl;; paramzzbme = Arrays.asList(new zzbme[] { paramzzbme })) {
      return this.zzcgm.zza(paramList, paramzzboz, paramzzbme);
    }
  }
  
  public boolean isEmpty()
  {
    return this.zzcgl.isEmpty();
  }
  
  public zzboe zzYH()
  {
    return this.zzcfS;
  }
  
  public zzbpe zzYI()
  {
    return this.zzcgk.zzYM().zzUY();
  }
  
  public zzbpe zzYJ()
  {
    return this.zzcgk.zzYK().zzUY();
  }
  
  public List<zzboa> zza(zzbme paramzzbme, DatabaseError paramDatabaseError)
  {
    int i;
    int j;
    if (paramDatabaseError != null)
    {
      Object localObject = new ArrayList();
      assert (paramzzbme == null) : "A cancel should cancel all event registrations";
      zzbmj localzzbmj = this.zzcfS.zzVc();
      Iterator localIterator = this.zzcgl.iterator();
      while (localIterator.hasNext()) {
        ((List)localObject).add(new zzbnx((zzbme)localIterator.next(), paramDatabaseError, localzzbmj));
      }
      paramDatabaseError = (DatabaseError)localObject;
      if (paramzzbme == null) {
        break label204;
      }
      i = 0;
      j = -1;
      label106:
      if (i >= this.zzcgl.size()) {
        break label249;
      }
      localObject = (zzbme)this.zzcgl.get(i);
      if (!((zzbme)localObject).zzc(paramzzbme)) {
        break label197;
      }
      if (!((zzbme)localObject).zzXb()) {
        break label194;
      }
    }
    for (;;)
    {
      if (i != -1)
      {
        paramzzbme = (zzbme)this.zzcgl.get(i);
        this.zzcgl.remove(i);
        paramzzbme.zzXa();
      }
      return paramDatabaseError;
      paramDatabaseError = Collections.emptyList();
      break;
      label194:
      j = i;
      label197:
      i += 1;
      break label106;
      label204:
      paramzzbme = this.zzcgl.iterator();
      while (paramzzbme.hasNext()) {
        ((zzbme)paramzzbme.next()).zzXa();
      }
      this.zzcgl.clear();
      return paramDatabaseError;
      label249:
      i = j;
    }
  }
  
  public zza zzb(zzbng paramzzbng, zzbnb paramzzbnb, zzbpe paramzzbpe)
  {
    if ((paramzzbng.zzXP() == zzbng.zza.zzceF) && (paramzzbng.zzXO().zzXT() != null))
    {
      assert (this.zzcgk.zzYN() != null) : "We should always have a full cache before handling merges";
      assert (this.zzcgk.zzYL() != null) : "Missing event cache, even though we have a server cache";
    }
    zzbog localzzbog = this.zzcgk;
    paramzzbng = this.zzcgj.zza(localzzbog, paramzzbng, paramzzbnb, paramzzbpe);
    assert ((paramzzbng.zzcgk.zzYM().zzYg()) || (!localzzbog.zzYM().zzYg())) : "Once a server snap is complete, it should never go back";
    this.zzcgk = paramzzbng.zzcgk;
    return new zza(zza(paramzzbng.zzcgo, paramzzbng.zzcgk.zzYK().zzYi(), null), paramzzbng.zzcgo);
  }
  
  public void zzb(zzbme paramzzbme)
  {
    this.zzcgl.add(paramzzbme);
  }
  
  public List<zzbnz> zzl(zzbme paramzzbme)
  {
    zzbnw localzzbnw = this.zzcgk.zzYK();
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = localzzbnw.zzUY().iterator();
    while (localIterator.hasNext())
    {
      zzbpd localzzbpd = (zzbpd)localIterator.next();
      localArrayList.add(zzbny.zzc(localzzbpd.zzZz(), localzzbpd.zzUY()));
    }
    if (localzzbnw.zzYg()) {
      localArrayList.add(zzbny.zza(localzzbnw.zzYi()));
    }
    return zza(localArrayList, localzzbnw.zzYi(), paramzzbme);
  }
  
  public zzbpe zzs(zzbmj paramzzbmj)
  {
    zzbpe localzzbpe = this.zzcgk.zzYN();
    if ((localzzbpe != null) && ((this.zzcfS.zzYD()) || ((!paramzzbmj.isEmpty()) && (!localzzbpe.zzm(paramzzbmj.zzXi()).isEmpty())))) {
      return localzzbpe.zzO(paramzzbmj);
    }
    return null;
  }
  
  public static class zza
  {
    public final List<zzbnz> zzcgn;
    public final List<zzbny> zzcgo;
    
    public zza(List<zzbnz> paramList, List<zzbny> paramList1)
    {
      this.zzcgn = paramList;
      this.zzcgo = paramList1;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbof.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */