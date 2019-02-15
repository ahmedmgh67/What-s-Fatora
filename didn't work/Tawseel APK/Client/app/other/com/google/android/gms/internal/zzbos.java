package com.google.android.gms.internal;

public class zzbos
  implements Comparable<zzbos>
{
  private static final zzbos zzcgL;
  private static final zzbos zzcgM;
  private static final zzbos zzcgN;
  private static final zzbos zzcgO;
  private final String zzaA;
  
  static
  {
    if (!zzbos.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      zzcgL = new zzbos("[MIN_KEY]");
      zzcgM = new zzbos("[MAX_KEY]");
      zzcgN = new zzbos(".priority");
      zzcgO = new zzbos(".info");
      return;
    }
  }
  
  private zzbos(String paramString)
  {
    this.zzaA = paramString;
  }
  
  public static zzbos zzYW()
  {
    return zzcgL;
  }
  
  public static zzbos zzYX()
  {
    return zzcgM;
  }
  
  public static zzbos zzYY()
  {
    return zzcgN;
  }
  
  public static zzbos zzYZ()
  {
    return zzcgO;
  }
  
  public static zzbos zzjb(String paramString)
  {
    Integer localInteger = zzbqg.zzjk(paramString);
    if (localInteger != null) {
      return new zza(paramString, localInteger.intValue());
    }
    if (paramString.equals(".priority")) {
      return zzcgN;
    }
    assert (!paramString.contains("/"));
    return new zzbos(paramString);
  }
  
  public String asString()
  {
    return this.zzaA;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof zzbos)) {
      return false;
    }
    if (this == paramObject) {
      return true;
    }
    paramObject = (zzbos)paramObject;
    return this.zzaA.equals(((zzbos)paramObject).zzaA);
  }
  
  public int hashCode()
  {
    return this.zzaA.hashCode();
  }
  
  protected int intValue()
  {
    return 0;
  }
  
  public String toString()
  {
    String str = this.zzaA;
    return String.valueOf(str).length() + 12 + "ChildKey(\"" + str + "\")";
  }
  
  public boolean zzZa()
  {
    return this == zzcgN;
  }
  
  protected boolean zzZb()
  {
    return false;
  }
  
  public int zzi(zzbos paramzzbos)
  {
    int j = -1;
    int i;
    if (this == paramzzbos) {
      i = 0;
    }
    do
    {
      do
      {
        do
        {
          do
          {
            return i;
            i = j;
          } while (this == zzcgL);
          i = j;
        } while (paramzzbos == zzcgM);
        if ((paramzzbos == zzcgL) || (this == zzcgM)) {
          return 1;
        }
        if (!zzZb()) {
          break;
        }
        i = j;
      } while (!paramzzbos.zzZb());
      j = zzbqg.zzD(intValue(), paramzzbos.intValue());
      i = j;
    } while (j != 0);
    return zzbqg.zzD(this.zzaA.length(), paramzzbos.zzaA.length());
    if (paramzzbos.zzZb()) {
      return 1;
    }
    return this.zzaA.compareTo(paramzzbos.zzaA);
  }
  
  private static class zza
    extends zzbos
  {
    private final int zzaFz;
    
    zza(String paramString, int paramInt)
    {
      super(null);
      this.zzaFz = paramInt;
    }
    
    protected int intValue()
    {
      return this.zzaFz;
    }
    
    public String toString()
    {
      String str = zzbos.zzj(this);
      return String.valueOf(str).length() + 20 + "IntegerChildName(\"" + str + "\")";
    }
    
    protected boolean zzZb()
    {
      return true;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbos.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */