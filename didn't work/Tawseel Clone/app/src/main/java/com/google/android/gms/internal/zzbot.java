package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class zzbot
  implements zzbpe
{
  public static Comparator<zzbos> zzcgP;
  private final zzbla<zzbos, zzbpe> zzcfq;
  private final zzbpe zzcgQ;
  private String zzcgR = null;
  
  static
  {
    if (!zzbot.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      zzcgP = new Comparator()
      {
        public int zza(zzbos paramAnonymouszzbos1, zzbos paramAnonymouszzbos2)
        {
          return paramAnonymouszzbos1.zzi(paramAnonymouszzbos2);
        }
      };
      return;
    }
  }
  
  protected zzbot()
  {
    this.zzcfq = zzbla.zza.zza(zzcgP);
    this.zzcgQ = zzbpi.zzZB();
  }
  
  protected zzbot(zzbla<zzbos, zzbpe> paramzzbla, zzbpe paramzzbpe)
  {
    if ((paramzzbla.isEmpty()) && (!paramzzbpe.isEmpty())) {
      throw new IllegalArgumentException("Can't create empty ChildrenNode with priority!");
    }
    this.zzcgQ = paramzzbpe;
    this.zzcfq = paramzzbla;
  }
  
  private static void zzb(StringBuilder paramStringBuilder, int paramInt)
  {
    int i = 0;
    while (i < paramInt)
    {
      paramStringBuilder.append(" ");
      i += 1;
    }
  }
  
  private void zzc(StringBuilder paramStringBuilder, int paramInt)
  {
    if ((this.zzcfq.isEmpty()) && (this.zzcgQ.isEmpty()))
    {
      paramStringBuilder.append("{ }");
      return;
    }
    paramStringBuilder.append("{\n");
    Iterator localIterator = this.zzcfq.iterator();
    if (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      zzb(paramStringBuilder, paramInt + 2);
      paramStringBuilder.append(((zzbos)localEntry.getKey()).asString());
      paramStringBuilder.append("=");
      if ((localEntry.getValue() instanceof zzbot)) {
        ((zzbot)localEntry.getValue()).zzc(paramStringBuilder, paramInt + 2);
      }
      for (;;)
      {
        paramStringBuilder.append("\n");
        break;
        paramStringBuilder.append(((zzbpe)localEntry.getValue()).toString());
      }
    }
    if (!this.zzcgQ.isEmpty())
    {
      zzb(paramStringBuilder, paramInt + 2);
      paramStringBuilder.append(".priority=");
      paramStringBuilder.append(this.zzcgQ.toString());
      paramStringBuilder.append("\n");
    }
    zzb(paramStringBuilder, paramInt);
    paramStringBuilder.append("}");
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {
      return false;
    }
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof zzbot)) {
      return false;
    }
    Object localObject = (zzbot)paramObject;
    if (!zzZe().equals(((zzbot)localObject).zzZe())) {
      return false;
    }
    if (this.zzcfq.size() != ((zzbot)localObject).zzcfq.size()) {
      return false;
    }
    paramObject = this.zzcfq.iterator();
    localObject = ((zzbot)localObject).zzcfq.iterator();
    while ((((Iterator)paramObject).hasNext()) && (((Iterator)localObject).hasNext()))
    {
      Map.Entry localEntry1 = (Map.Entry)((Iterator)paramObject).next();
      Map.Entry localEntry2 = (Map.Entry)((Iterator)localObject).next();
      if ((!((zzbos)localEntry1.getKey()).equals(localEntry2.getKey())) || (!((zzbpe)localEntry1.getValue()).equals(localEntry2.getValue()))) {
        return false;
      }
    }
    if ((((Iterator)paramObject).hasNext()) || (((Iterator)localObject).hasNext())) {
      throw new IllegalStateException("Something went wrong internally.");
    }
    return true;
  }
  
  public int getChildCount()
  {
    return this.zzcfq.size();
  }
  
  public Object getValue()
  {
    return getValue(false);
  }
  
  public Object getValue(boolean paramBoolean)
  {
    int m = 0;
    if (isEmpty())
    {
      localObject1 = null;
      return localObject1;
    }
    HashMap localHashMap = new HashMap();
    Object localObject1 = this.zzcfq.iterator();
    int j = 1;
    int i = 0;
    int k = 0;
    label41:
    Object localObject2;
    String str;
    int n;
    if (((Iterator)localObject1).hasNext())
    {
      localObject2 = (Map.Entry)((Iterator)localObject1).next();
      str = ((zzbos)((Map.Entry)localObject2).getKey()).asString();
      localHashMap.put(str, ((zzbpe)((Map.Entry)localObject2).getValue()).getValue(paramBoolean));
      n = k + 1;
      if (j == 0) {
        break label328;
      }
      if ((str.length() > 1) && (str.charAt(0) == '0'))
      {
        k = 0;
        j = i;
        i = k;
      }
    }
    for (;;)
    {
      k = j;
      j = i;
      i = k;
      k = n;
      break label41;
      localObject2 = zzbqg.zzjk(str);
      if ((localObject2 != null) && (((Integer)localObject2).intValue() >= 0))
      {
        if (((Integer)localObject2).intValue() > i)
        {
          k = ((Integer)localObject2).intValue();
          i = j;
          j = k;
        }
      }
      else
      {
        k = 0;
        j = i;
        i = k;
        continue;
        if ((!paramBoolean) && (j != 0) && (i < k * 2))
        {
          localObject2 = new ArrayList(i + 1);
          j = m;
          for (;;)
          {
            localObject1 = localObject2;
            if (j > i) {
              break;
            }
            ((List)localObject2).add(localHashMap.get(11 + j));
            j += 1;
          }
        }
        if ((paramBoolean) && (!this.zzcgQ.isEmpty())) {
          localHashMap.put(".priority", this.zzcgQ.getValue());
        }
        return localHashMap;
      }
      label328:
      k = i;
      i = j;
      j = k;
    }
  }
  
  public int hashCode()
  {
    Iterator localIterator = iterator();
    zzbpd localzzbpd;
    int j;
    for (int i = 0; localIterator.hasNext(); i = localzzbpd.zzUY().hashCode() + (i * 31 + j) * 17)
    {
      localzzbpd = (zzbpd)localIterator.next();
      j = localzzbpd.zzZz().hashCode();
    }
    return i;
  }
  
  public boolean isEmpty()
  {
    return this.zzcfq.isEmpty();
  }
  
  public Iterator<zzbpd> iterator()
  {
    return new zzb(this.zzcfq.iterator());
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    zzc(localStringBuilder, 0);
    return localStringBuilder.toString();
  }
  
  public zzbpe zzO(zzbmj paramzzbmj)
  {
    zzbos localzzbos = paramzzbmj.zzXi();
    if (localzzbos == null) {
      return this;
    }
    return zzm(localzzbos).zzO(paramzzbmj.zzXj());
  }
  
  public Iterator<zzbpd> zzVl()
  {
    return new zzb(this.zzcfq.zzVl());
  }
  
  public String zzZc()
  {
    if (this.zzcgR == null)
    {
      str = zza(zzbpe.zza.zzchv);
      if (!str.isEmpty()) {
        break label36;
      }
    }
    label36:
    for (String str = "";; str = zzbqg.zzji(str))
    {
      this.zzcgR = str;
      return this.zzcgR;
    }
  }
  
  public boolean zzZd()
  {
    return false;
  }
  
  public zzbpe zzZe()
  {
    return this.zzcgQ;
  }
  
  public zzbos zzZf()
  {
    return (zzbos)this.zzcfq.zzVj();
  }
  
  public zzbos zzZg()
  {
    return (zzbos)this.zzcfq.zzVk();
  }
  
  public String zza(zzbpe.zza paramzza)
  {
    if (paramzza != zzbpe.zza.zzchv) {
      throw new IllegalArgumentException("Hashes on children nodes only supported for V1");
    }
    paramzza = new StringBuilder();
    if (!this.zzcgQ.isEmpty())
    {
      paramzza.append("priority:");
      paramzza.append(this.zzcgQ.zza(zzbpe.zza.zzchv));
      paramzza.append(":");
    }
    Object localObject1 = new ArrayList();
    Object localObject2 = iterator();
    int i = 0;
    Object localObject3;
    if (((Iterator)localObject2).hasNext())
    {
      localObject3 = (zzbpd)((Iterator)localObject2).next();
      ((List)localObject1).add(localObject3);
      if ((i != 0) || (!((zzbpd)localObject3).zzUY().zzZe().isEmpty())) {}
      for (i = 1;; i = 0) {
        break;
      }
    }
    if (i != 0) {
      Collections.sort((List)localObject1, zzbph.zzZA());
    }
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (zzbpd)((Iterator)localObject1).next();
      localObject3 = ((zzbpd)localObject2).zzUY().zzZc();
      if (!((String)localObject3).equals(""))
      {
        paramzza.append(":");
        paramzza.append(((zzbpd)localObject2).zzZz().asString());
        paramzza.append(":");
        paramzza.append((String)localObject3);
      }
    }
    return paramzza.toString();
  }
  
  public void zza(zza paramzza)
  {
    zza(paramzza, false);
  }
  
  public void zza(final zza paramzza, boolean paramBoolean)
  {
    if ((!paramBoolean) || (zzZe().isEmpty()))
    {
      this.zzcfq.zza(paramzza);
      return;
    }
    this.zzcfq.zza(new zzblf.zzb()
    {
      boolean zzcgS = false;
      
      public void zzf(zzbos paramAnonymouszzbos, zzbpe paramAnonymouszzbpe)
      {
        if ((!this.zzcgS) && (paramAnonymouszzbos.zzi(zzbos.zzYY()) > 0))
        {
          this.zzcgS = true;
          paramzza.zzb(zzbos.zzYY(), zzbot.this.zzZe());
        }
        paramzza.zzb(paramAnonymouszzbos, paramAnonymouszzbpe);
      }
    });
  }
  
  public zzbpe zze(zzbos paramzzbos, zzbpe paramzzbpe)
  {
    if (paramzzbos.zzZa()) {
      return zzg(paramzzbpe);
    }
    Object localObject2 = this.zzcfq;
    Object localObject1 = localObject2;
    if (((zzbla)localObject2).containsKey(paramzzbos)) {
      localObject1 = ((zzbla)localObject2).zzae(paramzzbos);
    }
    localObject2 = localObject1;
    if (!paramzzbpe.isEmpty()) {
      localObject2 = ((zzbla)localObject1).zzj(paramzzbos, paramzzbpe);
    }
    if (((zzbla)localObject2).isEmpty()) {
      return zzbox.zzZp();
    }
    return new zzbot((zzbla)localObject2, this.zzcgQ);
  }
  
  public zzbpe zzg(zzbpe paramzzbpe)
  {
    if (this.zzcfq.isEmpty()) {
      return zzbox.zzZp();
    }
    return new zzbot(this.zzcfq, paramzzbpe);
  }
  
  public int zzh(zzbpe paramzzbpe)
  {
    if (isEmpty()) {
      if (!paramzzbpe.isEmpty()) {}
    }
    do
    {
      return 0;
      return -1;
      if (paramzzbpe.zzZd()) {
        return 1;
      }
      if (paramzzbpe.isEmpty()) {
        return 1;
      }
    } while (paramzzbpe != zzbpe.zzchu);
    return -1;
  }
  
  public boolean zzk(zzbos paramzzbos)
  {
    return !zzm(paramzzbos).isEmpty();
  }
  
  public zzbos zzl(zzbos paramzzbos)
  {
    return (zzbos)this.zzcfq.zzaf(paramzzbos);
  }
  
  public zzbpe zzl(zzbmj paramzzbmj, zzbpe paramzzbpe)
  {
    zzbos localzzbos = paramzzbmj.zzXi();
    if (localzzbos == null) {
      return paramzzbpe;
    }
    if (localzzbos.zzZa())
    {
      assert (zzbpi.zzq(paramzzbpe));
      return zzg(paramzzbpe);
    }
    return zze(localzzbos, zzm(localzzbos).zzl(paramzzbmj.zzXj(), paramzzbpe));
  }
  
  public zzbpe zzm(zzbos paramzzbos)
  {
    if ((paramzzbos.zzZa()) && (!this.zzcgQ.isEmpty())) {
      return this.zzcgQ;
    }
    if (this.zzcfq.containsKey(paramzzbos)) {
      return (zzbpe)this.zzcfq.get(paramzzbos);
    }
    return zzbox.zzZp();
  }
  
  public static abstract class zza
    extends zzblf.zzb<zzbos, zzbpe>
  {
    public abstract void zzb(zzbos paramzzbos, zzbpe paramzzbpe);
    
    public void zzf(zzbos paramzzbos, zzbpe paramzzbpe)
    {
      zzb(paramzzbos, paramzzbpe);
    }
  }
  
  private static class zzb
    implements Iterator<zzbpd>
  {
    private final Iterator<Map.Entry<zzbos, zzbpe>> zzbYP;
    
    public zzb(Iterator<Map.Entry<zzbos, zzbpe>> paramIterator)
    {
      this.zzbYP = paramIterator;
    }
    
    public boolean hasNext()
    {
      return this.zzbYP.hasNext();
    }
    
    public void remove()
    {
      this.zzbYP.remove();
    }
    
    public zzbpd zzZh()
    {
      Map.Entry localEntry = (Map.Entry)this.zzbYP.next();
      return new zzbpd((zzbos)localEntry.getKey(), (zzbpe)localEntry.getValue());
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */