package com.google.android.gms.internal;

public class zzbmu
{
  private final long zzcec;
  
  public zzbmu(long paramLong)
  {
    this.zzcec = paramLong;
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
      paramObject = (zzbmu)paramObject;
    } while (this.zzcec == ((zzbmu)paramObject).zzcec);
    return false;
  }
  
  public int hashCode()
  {
    return (int)(this.zzcec ^ this.zzcec >>> 32);
  }
  
  public String toString()
  {
    long l = this.zzcec;
    return 35 + "Tag{tagNumber=" + l + "}";
  }
  
  public long zzXB()
  {
    return this.zzcec;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbmu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */