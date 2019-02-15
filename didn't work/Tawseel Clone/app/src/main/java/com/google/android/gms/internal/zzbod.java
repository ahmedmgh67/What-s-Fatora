package com.google.android.gms.internal;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class zzbod
{
  public static final zzbod zzcfX;
  private zzboy zzcfT = zzbph.zzZA();
  private Integer zzcfY;
  private zza zzcfZ;
  private zzbpe zzcga = null;
  private zzbos zzcgb = null;
  private zzbpe zzcgc = null;
  private zzbos zzcgd = null;
  private String zzcge = null;
  
  static
  {
    if (!zzbod.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      zzcfX = new zzbod();
      return;
    }
  }
  
  private zzbod zzYA()
  {
    zzbod localzzbod = new zzbod();
    localzzbod.zzcfY = this.zzcfY;
    localzzbod.zzcga = this.zzcga;
    localzzbod.zzcgb = this.zzcgb;
    localzzbod.zzcgc = this.zzcgc;
    localzzbod.zzcgd = this.zzcgd;
    localzzbod.zzcfZ = this.zzcfZ;
    localzzbod.zzcfT = this.zzcfT;
    return localzzbod;
  }
  
  public static zzbod zzaD(Map<String, Object> paramMap)
  {
    zzbod localzzbod = new zzbod();
    localzzbod.zzcfY = ((Integer)paramMap.get("l"));
    if (paramMap.containsKey("sp"))
    {
      localzzbod.zzcga = zze(zzbpf.zzar(paramMap.get("sp")));
      localObject = (String)paramMap.get("sn");
      if (localObject != null) {
        localzzbod.zzcgb = zzbos.zzjb((String)localObject);
      }
    }
    if (paramMap.containsKey("ep"))
    {
      localzzbod.zzcgc = zze(zzbpf.zzar(paramMap.get("ep")));
      localObject = (String)paramMap.get("en");
      if (localObject != null) {
        localzzbod.zzcgd = zzbos.zzjb((String)localObject);
      }
    }
    Object localObject = (String)paramMap.get("vf");
    if (localObject != null) {
      if (!((String)localObject).equals("l")) {
        break label189;
      }
    }
    label189:
    for (localObject = zza.zzcgg;; localObject = zza.zzcgh)
    {
      localzzbod.zzcfZ = ((zza)localObject);
      paramMap = (String)paramMap.get("i");
      if (paramMap != null) {
        localzzbod.zzcfT = zzboy.zzjc(paramMap);
      }
      return localzzbod;
    }
  }
  
  private static zzbpe zze(zzbpe paramzzbpe)
  {
    if (((paramzzbpe instanceof zzbpk)) || ((paramzzbpe instanceof zzbor)) || ((paramzzbpe instanceof zzbow)) || ((paramzzbpe instanceof zzbox))) {
      return paramzzbpe;
    }
    if ((paramzzbpe instanceof zzbpc)) {
      return new zzbow(Double.valueOf(((Long)paramzzbpe.getValue()).doubleValue()), zzbpi.zzZB());
    }
    paramzzbpe = String.valueOf(paramzzbpe.getValue());
    throw new IllegalStateException(String.valueOf(paramzzbpe).length() + 43 + "Unexpected value passed to normalizeValue: " + paramzzbpe);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (getClass() != paramObject.getClass())) {
        return false;
      }
      paramObject = (zzbod)paramObject;
      if (this.zzcfY != null)
      {
        if (this.zzcfY.equals(((zzbod)paramObject).zzcfY)) {}
      }
      else {
        while (((zzbod)paramObject).zzcfY != null) {
          return false;
        }
      }
      if (this.zzcfT != null)
      {
        if (this.zzcfT.equals(((zzbod)paramObject).zzcfT)) {}
      }
      else {
        while (((zzbod)paramObject).zzcfT != null) {
          return false;
        }
      }
      if (this.zzcgd != null)
      {
        if (this.zzcgd.equals(((zzbod)paramObject).zzcgd)) {}
      }
      else {
        while (((zzbod)paramObject).zzcgd != null) {
          return false;
        }
      }
      if (this.zzcgc != null)
      {
        if (this.zzcgc.equals(((zzbod)paramObject).zzcgc)) {}
      }
      else {
        while (((zzbod)paramObject).zzcgc != null) {
          return false;
        }
      }
      if (this.zzcgb != null)
      {
        if (this.zzcgb.equals(((zzbod)paramObject).zzcgb)) {}
      }
      else {
        while (((zzbod)paramObject).zzcgb != null) {
          return false;
        }
      }
      if (this.zzcga != null)
      {
        if (this.zzcga.equals(((zzbod)paramObject).zzcga)) {}
      }
      else {
        while (((zzbod)paramObject).zzcga != null) {
          return false;
        }
      }
    } while (zzYB() == ((zzbod)paramObject).zzYB());
    return false;
  }
  
  public int getLimit()
  {
    if (!zzYx()) {
      throw new IllegalArgumentException("Cannot get limit if limit has not been set");
    }
    return this.zzcfY.intValue();
  }
  
  public int hashCode()
  {
    int i2 = 0;
    int i;
    int j;
    label29:
    int k;
    label44:
    int m;
    label60:
    int n;
    if (this.zzcfY != null)
    {
      i = this.zzcfY.intValue();
      if (!zzYB()) {
        break label149;
      }
      j = 1231;
      if (this.zzcga == null) {
        break label156;
      }
      k = this.zzcga.hashCode();
      if (this.zzcgb == null) {
        break label161;
      }
      m = this.zzcgb.hashCode();
      if (this.zzcgc == null) {
        break label167;
      }
      n = this.zzcgc.hashCode();
      label76:
      if (this.zzcgd == null) {
        break label173;
      }
    }
    label149:
    label156:
    label161:
    label167:
    label173:
    for (int i1 = this.zzcgd.hashCode();; i1 = 0)
    {
      if (this.zzcfT != null) {
        i2 = this.zzcfT.hashCode();
      }
      return (i1 + (n + (m + (k + (j + i * 31) * 31) * 31) * 31) * 31) * 31 + i2;
      i = 0;
      break;
      j = 1237;
      break label29;
      k = 0;
      break label44;
      m = 0;
      break label60;
      n = 0;
      break label76;
    }
  }
  
  public boolean isDefault()
  {
    return (zzYD()) && (this.zzcfT.equals(zzbph.zzZA()));
  }
  
  public boolean isValid()
  {
    return (!zzYr()) || (!zzYu()) || (!zzYx()) || (zzYy());
  }
  
  public String toString()
  {
    return zzYC().toString();
  }
  
  public boolean zzYB()
  {
    if (this.zzcfZ != null) {
      return this.zzcfZ == zza.zzcgg;
    }
    return zzYr();
  }
  
  public Map<String, Object> zzYC()
  {
    HashMap localHashMap = new HashMap();
    if (zzYr())
    {
      localHashMap.put("sp", this.zzcga.getValue());
      if (this.zzcgb != null) {
        localHashMap.put("sn", this.zzcgb.asString());
      }
    }
    if (zzYu())
    {
      localHashMap.put("ep", this.zzcgc.getValue());
      if (this.zzcgd != null) {
        localHashMap.put("en", this.zzcgd.asString());
      }
    }
    zza localzza1;
    if (this.zzcfY != null)
    {
      localHashMap.put("l", this.zzcfY);
      zza localzza2 = this.zzcfZ;
      localzza1 = localzza2;
      if (localzza2 == null)
      {
        if (!zzYr()) {
          break label207;
        }
        localzza1 = zza.zzcgg;
      }
      switch (1.zzcgf[localzza1.ordinal()])
      {
      }
    }
    for (;;)
    {
      if (!this.zzcfT.equals(zzbph.zzZA())) {
        localHashMap.put("i", this.zzcfT.zzZs());
      }
      return localHashMap;
      label207:
      localzza1 = zza.zzcgh;
      break;
      localHashMap.put("vf", "l");
      continue;
      localHashMap.put("vf", "r");
    }
  }
  
  public boolean zzYD()
  {
    return (!zzYr()) && (!zzYu()) && (!zzYx());
  }
  
  public String zzYE()
  {
    if (this.zzcge == null) {}
    try
    {
      this.zzcge = zzbpx.zzaE(zzYC());
      return this.zzcge;
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(localIOException);
    }
  }
  
  public zzbol zzYF()
  {
    if (zzYD()) {
      return new zzboj(zzYz());
    }
    if (zzYx()) {
      return new zzbok(this);
    }
    return new zzbom(this);
  }
  
  public boolean zzYr()
  {
    return this.zzcga != null;
  }
  
  public zzbpe zzYs()
  {
    if (!zzYr()) {
      throw new IllegalArgumentException("Cannot get index start value if start has not been set");
    }
    return this.zzcga;
  }
  
  public zzbos zzYt()
  {
    if (!zzYr()) {
      throw new IllegalArgumentException("Cannot get index start name if start has not been set");
    }
    if (this.zzcgb != null) {
      return this.zzcgb;
    }
    return zzbos.zzYW();
  }
  
  public boolean zzYu()
  {
    return this.zzcgc != null;
  }
  
  public zzbpe zzYv()
  {
    if (!zzYu()) {
      throw new IllegalArgumentException("Cannot get index end value if start has not been set");
    }
    return this.zzcgc;
  }
  
  public zzbos zzYw()
  {
    if (!zzYu()) {
      throw new IllegalArgumentException("Cannot get index end name if start has not been set");
    }
    if (this.zzcgd != null) {
      return this.zzcgd;
    }
    return zzbos.zzYX();
  }
  
  public boolean zzYx()
  {
    return this.zzcfY != null;
  }
  
  public boolean zzYy()
  {
    return (zzYx()) && (this.zzcfZ != null);
  }
  
  public zzboy zzYz()
  {
    return this.zzcfT;
  }
  
  public zzbod zza(zzboy paramzzboy)
  {
    zzbod localzzbod = zzYA();
    localzzbod.zzcfT = paramzzboy;
    return localzzbod;
  }
  
  public zzbod zza(zzbpe paramzzbpe, zzbos paramzzbos)
  {
    assert ((paramzzbpe.zzZd()) || (paramzzbpe.isEmpty()));
    if (!(paramzzbpe instanceof zzbpc)) {}
    for (boolean bool = true;; bool = false)
    {
      zzbqg.zzaW(bool);
      zzbod localzzbod = zzYA();
      localzzbod.zzcga = paramzzbpe;
      localzzbod.zzcgb = paramzzbos;
      return localzzbod;
    }
  }
  
  public zzbod zzb(zzbpe paramzzbpe, zzbos paramzzbos)
  {
    assert ((paramzzbpe.zzZd()) || (paramzzbpe.isEmpty()));
    if (!(paramzzbpe instanceof zzbpc)) {}
    for (boolean bool = true;; bool = false)
    {
      zzbqg.zzaW(bool);
      zzbod localzzbod = zzYA();
      localzzbod.zzcgc = paramzzbpe;
      localzzbod.zzcgd = paramzzbos;
      return localzzbod;
    }
  }
  
  public zzbod zzpO(int paramInt)
  {
    zzbod localzzbod = zzYA();
    localzzbod.zzcfY = Integer.valueOf(paramInt);
    localzzbod.zzcfZ = zza.zzcgg;
    return localzzbod;
  }
  
  public zzbod zzpP(int paramInt)
  {
    zzbod localzzbod = zzYA();
    localzzbod.zzcfY = Integer.valueOf(paramInt);
    localzzbod.zzcfZ = zza.zzcgh;
    return localzzbod;
  }
  
  private static enum zza
  {
    private zza() {}
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbod.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */