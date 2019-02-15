package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class zzbou
{
  private final List<zzbmj> zzbZh;
  private final List<String> zzbZi;
  
  private zzbou(List<zzbmj> paramList, List<String> paramList1)
  {
    if (paramList.size() != paramList1.size() - 1) {
      throw new IllegalArgumentException("Number of posts need to be n-1 for n hashes in CompoundHash");
    }
    this.zzbZh = paramList;
    this.zzbZi = paramList1;
  }
  
  public static zzbou zza(zzbpe paramzzbpe, zzc paramzzc)
  {
    if (paramzzbpe.isEmpty()) {
      return new zzbou(Collections.emptyList(), Collections.singletonList(""));
    }
    paramzzc = new zza(paramzzc);
    zza(paramzzbpe, paramzzc);
    zza.zza(paramzzc);
    return new zzbou(zza.zzb(paramzzc), zza.zzc(paramzzc));
  }
  
  private static void zza(zzbpe paramzzbpe, zza paramzza)
  {
    if (paramzzbpe.zzZd())
    {
      zza.zza(paramzza, (zzbpb)paramzzbpe);
      return;
    }
    if (paramzzbpe.isEmpty()) {
      throw new IllegalArgumentException("Can't calculate hash on empty node!");
    }
    if (!(paramzzbpe instanceof zzbot))
    {
      paramzzbpe = String.valueOf(paramzzbpe);
      throw new IllegalStateException(String.valueOf(paramzzbpe).length() + 33 + "Expected children node, but got: " + paramzzbpe);
    }
    ((zzbot)paramzzbpe).zza(new zzbot.zza()
    {
      public void zzb(zzbos paramAnonymouszzbos, zzbpe paramAnonymouszzbpe)
      {
        zzbou.zza.zza(zzbou.this, paramAnonymouszzbos);
        zzbou.zzb(paramAnonymouszzbpe, zzbou.this);
        zzbou.zza.zzd(zzbou.this);
      }
    }, true);
  }
  
  public static zzbou zzi(zzbpe paramzzbpe)
  {
    return zza(paramzzbpe, new zzb(paramzzbpe));
  }
  
  public List<zzbmj> zzVF()
  {
    return Collections.unmodifiableList(this.zzbZh);
  }
  
  public List<String> zzVG()
  {
    return Collections.unmodifiableList(this.zzbZi);
  }
  
  static class zza
  {
    private StringBuilder zzcgW = null;
    private Stack<zzbos> zzcgX = new Stack();
    private int zzcgY = -1;
    private int zzcgZ;
    private boolean zzcha = true;
    private final List<zzbmj> zzchb = new ArrayList();
    private final List<String> zzchc = new ArrayList();
    private final zzbou.zzc zzchd;
    
    public zza(zzbou.zzc paramzzc)
    {
      this.zzchd = paramzzc;
    }
    
    private void zzZl()
    {
      if (!zzZi())
      {
        this.zzcgW = new StringBuilder();
        this.zzcgW.append("(");
        Iterator localIterator = zzpQ(this.zzcgZ).iterator();
        while (localIterator.hasNext())
        {
          zzbos localzzbos = (zzbos)localIterator.next();
          zza(this.zzcgW, localzzbos);
          this.zzcgW.append(":(");
        }
        this.zzcha = false;
      }
    }
    
    private void zzZm()
    {
      this.zzcgZ -= 1;
      if (zzZi()) {
        this.zzcgW.append(")");
      }
      this.zzcha = true;
    }
    
    private void zzZn()
    {
      if (this.zzcgZ == 0) {}
      for (boolean bool = true;; bool = false)
      {
        zzbqg.zzb(bool, "Can't finish hashing in the middle processing a child");
        if (zzZi()) {
          zzZo();
        }
        this.zzchc.add("");
        return;
      }
    }
    
    private void zzZo()
    {
      zzbqg.zzb(zzZi(), "Can't end range without starting a range!");
      int i = 0;
      while (i < this.zzcgZ)
      {
        this.zzcgW.append(")");
        i += 1;
      }
      this.zzcgW.append(")");
      zzbmj localzzbmj = zzpQ(this.zzcgY);
      String str = zzbqg.zzji(this.zzcgW.toString());
      this.zzchc.add(str);
      this.zzchb.add(localzzbmj);
      this.zzcgW = null;
    }
    
    private void zza(StringBuilder paramStringBuilder, zzbos paramzzbos)
    {
      paramStringBuilder.append(zzbqg.zzjj(paramzzbos.asString()));
    }
    
    private void zzb(zzbpb<?> paramzzbpb)
    {
      zzZl();
      this.zzcgY = this.zzcgZ;
      this.zzcgW.append(paramzzbpb.zza(zzbpe.zza.zzchw));
      this.zzcha = true;
      if (this.zzchd.zze(this)) {
        zzZo();
      }
    }
    
    private void zzn(zzbos paramzzbos)
    {
      zzZl();
      if (this.zzcha) {
        this.zzcgW.append(",");
      }
      zza(this.zzcgW, paramzzbos);
      this.zzcgW.append(":(");
      if (this.zzcgZ == this.zzcgX.size()) {
        this.zzcgX.add(paramzzbos);
      }
      for (;;)
      {
        this.zzcgZ += 1;
        this.zzcha = false;
        return;
        this.zzcgX.set(this.zzcgZ, paramzzbos);
      }
    }
    
    private zzbmj zzpQ(int paramInt)
    {
      zzbos[] arrayOfzzbos = new zzbos[paramInt];
      int i = 0;
      while (i < paramInt)
      {
        arrayOfzzbos[i] = ((zzbos)this.zzcgX.get(i));
        i += 1;
      }
      return new zzbmj(arrayOfzzbos);
    }
    
    public boolean zzZi()
    {
      return this.zzcgW != null;
    }
    
    public int zzZj()
    {
      return this.zzcgW.length();
    }
    
    public zzbmj zzZk()
    {
      return zzpQ(this.zzcgZ);
    }
  }
  
  private static class zzb
    implements zzbou.zzc
  {
    private final long zzche;
    
    public zzb(zzbpe paramzzbpe)
    {
      this.zzche = Math.max(512L, Math.sqrt(zzbqb.zzt(paramzzbpe) * 100L));
    }
    
    public boolean zze(zzbou.zza paramzza)
    {
      return (paramzza.zzZj() > this.zzche) && ((paramzza.zzZk().isEmpty()) || (!paramzza.zzZk().zzXl().equals(zzbos.zzYY())));
    }
  }
  
  public static abstract interface zzc
  {
    public abstract boolean zze(zzbou.zza paramzza);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbou.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */