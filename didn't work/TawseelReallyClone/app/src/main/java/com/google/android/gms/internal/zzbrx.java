package com.google.android.gms.internal;

import java.math.BigInteger;

public final class zzbrx
  extends zzbrr
{
  private static final Class<?>[] zzcmN = { Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class };
  private Object value;
  
  public zzbrx(Boolean paramBoolean)
  {
    setValue(paramBoolean);
  }
  
  public zzbrx(Number paramNumber)
  {
    setValue(paramNumber);
  }
  
  zzbrx(Object paramObject)
  {
    setValue(paramObject);
  }
  
  public zzbrx(String paramString)
  {
    setValue(paramString);
  }
  
  private static boolean zza(zzbrx paramzzbrx)
  {
    if ((paramzzbrx.value instanceof Number))
    {
      paramzzbrx = (Number)paramzzbrx.value;
      return ((paramzzbrx instanceof BigInteger)) || ((paramzzbrx instanceof Long)) || ((paramzzbrx instanceof Integer)) || ((paramzzbrx instanceof Short)) || ((paramzzbrx instanceof Byte));
    }
    return false;
  }
  
  private static boolean zzaK(Object paramObject)
  {
    if ((paramObject instanceof String)) {
      return true;
    }
    paramObject = paramObject.getClass();
    Class[] arrayOfClass = zzcmN;
    int j = arrayOfClass.length;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label45;
      }
      if (arrayOfClass[i].isAssignableFrom((Class)paramObject)) {
        break;
      }
      i += 1;
    }
    label45:
    return false;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    if (this == paramObject) {}
    do
    {
      do
      {
        return true;
        if ((paramObject == null) || (getClass() != paramObject.getClass())) {
          return false;
        }
        paramObject = (zzbrx)paramObject;
        if (this.value != null) {
          break;
        }
      } while (((zzbrx)paramObject).value == null);
      return false;
      if ((!zza(this)) || (!zza((zzbrx)paramObject))) {
        break;
      }
    } while (zzabt().longValue() == ((zzbrx)paramObject).zzabt().longValue());
    return false;
    if (((this.value instanceof Number)) && ((((zzbrx)paramObject).value instanceof Number)))
    {
      double d1 = zzabt().doubleValue();
      double d2 = ((zzbrx)paramObject).zzabt().doubleValue();
      boolean bool1;
      if (d1 != d2)
      {
        bool1 = bool2;
        if (Double.isNaN(d1))
        {
          bool1 = bool2;
          if (!Double.isNaN(d2)) {}
        }
      }
      else
      {
        bool1 = true;
      }
      return bool1;
    }
    return this.value.equals(((zzbrx)paramObject).value);
  }
  
  public boolean getAsBoolean()
  {
    if (zzabD()) {
      return zzabC().booleanValue();
    }
    return Boolean.parseBoolean(zzabu());
  }
  
  public double getAsDouble()
  {
    if (zzabE()) {
      return zzabt().doubleValue();
    }
    return Double.parseDouble(zzabu());
  }
  
  public int getAsInt()
  {
    if (zzabE()) {
      return zzabt().intValue();
    }
    return Integer.parseInt(zzabu());
  }
  
  public long getAsLong()
  {
    if (zzabE()) {
      return zzabt().longValue();
    }
    return Long.parseLong(zzabu());
  }
  
  public int hashCode()
  {
    if (this.value == null) {
      return 31;
    }
    long l;
    if (zza(this))
    {
      l = zzabt().longValue();
      return (int)(l ^ l >>> 32);
    }
    if ((this.value instanceof Number))
    {
      l = Double.doubleToLongBits(zzabt().doubleValue());
      return (int)(l ^ l >>> 32);
    }
    return this.value.hashCode();
  }
  
  void setValue(Object paramObject)
  {
    if ((paramObject instanceof Character))
    {
      this.value = String.valueOf(((Character)paramObject).charValue());
      return;
    }
    if (((paramObject instanceof Number)) || (zzaK(paramObject))) {}
    for (boolean bool = true;; bool = false)
    {
      zzbsj.zzas(bool);
      this.value = paramObject;
      return;
    }
  }
  
  Boolean zzabC()
  {
    return (Boolean)this.value;
  }
  
  public boolean zzabD()
  {
    return this.value instanceof Boolean;
  }
  
  public boolean zzabE()
  {
    return this.value instanceof Number;
  }
  
  public boolean zzabF()
  {
    return this.value instanceof String;
  }
  
  public Number zzabt()
  {
    if ((this.value instanceof String)) {
      return new zzbso((String)this.value);
    }
    return (Number)this.value;
  }
  
  public String zzabu()
  {
    if (zzabE()) {
      return zzabt().toString();
    }
    if (zzabD()) {
      return zzabC().toString();
    }
    return (String)this.value;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbrx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */