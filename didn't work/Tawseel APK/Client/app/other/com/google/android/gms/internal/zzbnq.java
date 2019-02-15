package com.google.android.gms.internal;

public class zzbnq
{
  public final long id;
  public final zzboe zzcfe;
  public final long zzcff;
  public final boolean zzcfg;
  public final boolean zzcfh;
  
  public zzbnq(long paramLong1, zzboe paramzzboe, long paramLong2, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.id = paramLong1;
    if ((paramzzboe.zzYD()) && (!paramzzboe.isDefault())) {
      throw new IllegalArgumentException("Can't create TrackedQuery for a non-default query that loads all data");
    }
    this.zzcfe = paramzzboe;
    this.zzcff = paramLong2;
    this.zzcfg = paramBoolean1;
    this.zzcfh = paramBoolean2;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if ((paramObject == null) || (paramObject.getClass() != getClass())) {
        return false;
      }
      paramObject = (zzbnq)paramObject;
    } while ((this.id == ((zzbnq)paramObject).id) && (this.zzcfe.equals(((zzbnq)paramObject).zzcfe)) && (this.zzcff == ((zzbnq)paramObject).zzcff) && (this.zzcfg == ((zzbnq)paramObject).zzcfg) && (this.zzcfh == ((zzbnq)paramObject).zzcfh));
    return false;
  }
  
  public int hashCode()
  {
    return (((Long.valueOf(this.id).hashCode() * 31 + this.zzcfe.hashCode()) * 31 + Long.valueOf(this.zzcff).hashCode()) * 31 + Boolean.valueOf(this.zzcfg).hashCode()) * 31 + Boolean.valueOf(this.zzcfh).hashCode();
  }
  
  public String toString()
  {
    long l1 = this.id;
    String str = String.valueOf(this.zzcfe);
    long l2 = this.zzcff;
    boolean bool1 = this.zzcfg;
    boolean bool2 = this.zzcfh;
    return String.valueOf(str).length() + 109 + "TrackedQuery{id=" + l1 + ", querySpec=" + str + ", lastUse=" + l2 + ", complete=" + bool1 + ", active=" + bool2 + "}";
  }
  
  public zzbnq zzXZ()
  {
    return new zzbnq(this.id, this.zzcfe, this.zzcff, true, this.zzcfh);
  }
  
  public zzbnq zzaO(long paramLong)
  {
    return new zzbnq(this.id, this.zzcfe, paramLong, this.zzcfg, this.zzcfh);
  }
  
  public zzbnq zzbb(boolean paramBoolean)
  {
    return new zzbnq(this.id, this.zzcfe, this.zzcff, this.zzcfg, paramBoolean);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbnq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */