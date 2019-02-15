package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzaa.zza;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zzc;
import com.google.android.gms.common.util.zzp;
import com.google.android.gms.common.util.zzq;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class zzack
{
  private void zza(StringBuilder paramStringBuilder, zza paramzza, Object paramObject)
  {
    if (paramzza.zzxL() == 11)
    {
      paramStringBuilder.append(((zzack)paramzza.zzxR().cast(paramObject)).toString());
      return;
    }
    if (paramzza.zzxL() == 7)
    {
      paramStringBuilder.append("\"");
      paramStringBuilder.append(zzp.zzdC((String)paramObject));
      paramStringBuilder.append("\"");
      return;
    }
    paramStringBuilder.append(paramObject);
  }
  
  private void zza(StringBuilder paramStringBuilder, zza paramzza, ArrayList<Object> paramArrayList)
  {
    paramStringBuilder.append("[");
    int i = 0;
    int j = paramArrayList.size();
    while (i < j)
    {
      if (i > 0) {
        paramStringBuilder.append(",");
      }
      Object localObject = paramArrayList.get(i);
      if (localObject != null) {
        zza(paramStringBuilder, paramzza, localObject);
      }
      i += 1;
    }
    paramStringBuilder.append("]");
  }
  
  public String toString()
  {
    Map localMap = zzxK();
    StringBuilder localStringBuilder = new StringBuilder(100);
    Iterator localIterator = localMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      zza localzza = (zza)localMap.get(str);
      if (zza(localzza))
      {
        Object localObject = zza(localzza, zzb(localzza));
        if (localStringBuilder.length() == 0) {
          localStringBuilder.append("{");
        }
        for (;;)
        {
          localStringBuilder.append("\"").append(str).append("\":");
          if (localObject != null) {
            break label139;
          }
          localStringBuilder.append("null");
          break;
          localStringBuilder.append(",");
        }
        label139:
        switch (localzza.zzxN())
        {
        default: 
          if (localzza.zzxM()) {
            zza(localStringBuilder, localzza, (ArrayList)localObject);
          }
          break;
        case 8: 
          localStringBuilder.append("\"").append(zzc.zzq((byte[])localObject)).append("\"");
          break;
        case 9: 
          localStringBuilder.append("\"").append(zzc.zzr((byte[])localObject)).append("\"");
          break;
        case 10: 
          zzq.zza(localStringBuilder, (HashMap)localObject);
          continue;
          zza(localStringBuilder, localzza, localObject);
        }
      }
    }
    if (localStringBuilder.length() > 0) {
      localStringBuilder.append("}");
    }
    for (;;)
    {
      return localStringBuilder.toString();
      localStringBuilder.append("{}");
    }
  }
  
  protected <O, I> I zza(zza<I, O> paramzza, Object paramObject)
  {
    Object localObject = paramObject;
    if (zza.zzc(paramzza) != null) {
      localObject = paramzza.convertBack(paramObject);
    }
    return (I)localObject;
  }
  
  protected boolean zza(zza paramzza)
  {
    if (paramzza.zzxN() == 11)
    {
      if (paramzza.zzxO()) {
        return zzdz(paramzza.zzxP());
      }
      return zzdy(paramzza.zzxP());
    }
    return zzdx(paramzza.zzxP());
  }
  
  protected Object zzb(zza paramzza)
  {
    String str = paramzza.zzxP();
    if (paramzza.zzxR() != null)
    {
      zzdw(paramzza.zzxP());
      zzac.zza(true, "Concrete field shouldn't be value object: %s", new Object[] { paramzza.zzxP() });
      paramzza.zzxO();
      try
      {
        char c = Character.toUpperCase(str.charAt(0));
        paramzza = String.valueOf(str.substring(1));
        paramzza = String.valueOf(paramzza).length() + 4 + "get" + c + paramzza;
        paramzza = getClass().getMethod(paramzza, new Class[0]).invoke(this, new Object[0]);
        return paramzza;
      }
      catch (Exception paramzza)
      {
        throw new RuntimeException(paramzza);
      }
    }
    return zzdw(paramzza.zzxP());
  }
  
  protected abstract Object zzdw(String paramString);
  
  protected abstract boolean zzdx(String paramString);
  
  protected boolean zzdy(String paramString)
  {
    throw new UnsupportedOperationException("Concrete types not supported");
  }
  
  protected boolean zzdz(String paramString)
  {
    throw new UnsupportedOperationException("Concrete type arrays not supported");
  }
  
  public abstract Map<String, zza<?, ?>> zzxK();
  
  public static class zza<I, O>
    extends zza
  {
    public static final zzacm CREATOR = new zzacm();
    private final int mVersionCode;
    protected final int zzaFA;
    protected final boolean zzaFB;
    protected final int zzaFC;
    protected final boolean zzaFD;
    protected final String zzaFE;
    protected final int zzaFF;
    protected final Class<? extends zzack> zzaFG;
    protected final String zzaFH;
    private zzaco zzaFI;
    private zzack.zzb<I, O> zzaFJ;
    
    zza(int paramInt1, int paramInt2, boolean paramBoolean1, int paramInt3, boolean paramBoolean2, String paramString1, int paramInt4, String paramString2, zzacf paramzzacf)
    {
      this.mVersionCode = paramInt1;
      this.zzaFA = paramInt2;
      this.zzaFB = paramBoolean1;
      this.zzaFC = paramInt3;
      this.zzaFD = paramBoolean2;
      this.zzaFE = paramString1;
      this.zzaFF = paramInt4;
      if (paramString2 == null) {
        this.zzaFG = null;
      }
      for (this.zzaFH = null; paramzzacf == null; this.zzaFH = paramString2)
      {
        this.zzaFJ = null;
        return;
        this.zzaFG = zzacr.class;
      }
      this.zzaFJ = paramzzacf.zzxI();
    }
    
    protected zza(int paramInt1, boolean paramBoolean1, int paramInt2, boolean paramBoolean2, String paramString, int paramInt3, Class<? extends zzack> paramClass, zzack.zzb<I, O> paramzzb)
    {
      this.mVersionCode = 1;
      this.zzaFA = paramInt1;
      this.zzaFB = paramBoolean1;
      this.zzaFC = paramInt2;
      this.zzaFD = paramBoolean2;
      this.zzaFE = paramString;
      this.zzaFF = paramInt3;
      this.zzaFG = paramClass;
      if (paramClass == null) {}
      for (this.zzaFH = null;; this.zzaFH = paramClass.getCanonicalName())
      {
        this.zzaFJ = paramzzb;
        return;
      }
    }
    
    public static zza zza(String paramString, int paramInt, zzack.zzb<?, ?> paramzzb, boolean paramBoolean)
    {
      return new zza(7, paramBoolean, 0, false, paramString, paramInt, null, paramzzb);
    }
    
    public static <T extends zzack> zza<T, T> zza(String paramString, int paramInt, Class<T> paramClass)
    {
      return new zza(11, false, 11, false, paramString, paramInt, paramClass, null);
    }
    
    public static <T extends zzack> zza<ArrayList<T>, ArrayList<T>> zzb(String paramString, int paramInt, Class<T> paramClass)
    {
      return new zza(11, true, 11, true, paramString, paramInt, paramClass, null);
    }
    
    public static zza<Integer, Integer> zzk(String paramString, int paramInt)
    {
      return new zza(0, false, 0, false, paramString, paramInt, null, null);
    }
    
    public static zza<Boolean, Boolean> zzl(String paramString, int paramInt)
    {
      return new zza(6, false, 6, false, paramString, paramInt, null, null);
    }
    
    public static zza<String, String> zzm(String paramString, int paramInt)
    {
      return new zza(7, false, 7, false, paramString, paramInt, null, null);
    }
    
    public I convertBack(O paramO)
    {
      return (I)this.zzaFJ.convertBack(paramO);
    }
    
    public int getVersionCode()
    {
      return this.mVersionCode;
    }
    
    public String toString()
    {
      zzaa.zza localzza = zzaa.zzv(this).zzg("versionCode", Integer.valueOf(this.mVersionCode)).zzg("typeIn", Integer.valueOf(this.zzaFA)).zzg("typeInArray", Boolean.valueOf(this.zzaFB)).zzg("typeOut", Integer.valueOf(this.zzaFC)).zzg("typeOutArray", Boolean.valueOf(this.zzaFD)).zzg("outputFieldName", this.zzaFE).zzg("safeParcelFieldId", Integer.valueOf(this.zzaFF)).zzg("concreteTypeName", zzxS());
      Class localClass = zzxR();
      if (localClass != null) {
        localzza.zzg("concreteType.class", localClass.getCanonicalName());
      }
      if (this.zzaFJ != null) {
        localzza.zzg("converterName", this.zzaFJ.getClass().getCanonicalName());
      }
      return localzza.toString();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      zzacm.zza(this, paramParcel, paramInt);
    }
    
    public void zza(zzaco paramzzaco)
    {
      this.zzaFI = paramzzaco;
    }
    
    public int zzxL()
    {
      return this.zzaFA;
    }
    
    public boolean zzxM()
    {
      return this.zzaFB;
    }
    
    public int zzxN()
    {
      return this.zzaFC;
    }
    
    public boolean zzxO()
    {
      return this.zzaFD;
    }
    
    public String zzxP()
    {
      return this.zzaFE;
    }
    
    public int zzxQ()
    {
      return this.zzaFF;
    }
    
    public Class<? extends zzack> zzxR()
    {
      return this.zzaFG;
    }
    
    String zzxS()
    {
      if (this.zzaFH == null) {
        return null;
      }
      return this.zzaFH;
    }
    
    public boolean zzxT()
    {
      return this.zzaFJ != null;
    }
    
    zzacf zzxU()
    {
      if (this.zzaFJ == null) {
        return null;
      }
      return zzacf.zza(this.zzaFJ);
    }
    
    public Map<String, zza<?, ?>> zzxV()
    {
      zzac.zzw(this.zzaFH);
      zzac.zzw(this.zzaFI);
      return this.zzaFI.zzdA(this.zzaFH);
    }
  }
  
  public static abstract interface zzb<I, O>
  {
    public abstract I convertBack(O paramO);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */