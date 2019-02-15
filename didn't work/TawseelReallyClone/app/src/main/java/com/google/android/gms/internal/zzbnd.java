package com.google.android.gms.internal;

public class zzbnd
  extends zzbng
{
  private final zzbns<Boolean> zzceA;
  private final boolean zzcez;
  
  public zzbnd(zzbmj paramzzbmj, zzbns<Boolean> paramzzbns, boolean paramBoolean)
  {
    super(zzbng.zza.zzceG, zzbnh.zzceJ, paramzzbmj);
    this.zzceA = paramzzbns;
    this.zzcez = paramBoolean;
  }
  
  public String toString()
  {
    return String.format("AckUserWrite { path=%s, revert=%s, affectedTree=%s }", new Object[] { zzVc(), Boolean.valueOf(this.zzcez), this.zzceA });
  }
  
  public zzbns<Boolean> zzXL()
  {
    return this.zzceA;
  }
  
  public boolean zzXM()
  {
    return this.zzcez;
  }
  
  public zzbng zzc(zzbos paramzzbos)
  {
    if (!this.zzbXY.isEmpty())
    {
      zzbqg.zzb(this.zzbXY.zzXi().equals(paramzzbos), "operationForChild called for unrelated child.");
      return new zzbnd(this.zzbXY.zzXj(), this.zzceA, this.zzcez);
    }
    if (this.zzceA.getValue() != null)
    {
      zzbqg.zzb(this.zzceA.zzYe().isEmpty(), "affectedTree should not have overlapping affected paths.");
      return this;
    }
    paramzzbos = this.zzceA.zzI(new zzbmj(new zzbos[] { paramzzbos }));
    return new zzbnd(zzbmj.zzXf(), paramzzbos, this.zzcez);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbnd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */