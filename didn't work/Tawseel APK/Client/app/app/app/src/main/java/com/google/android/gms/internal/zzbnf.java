package com.google.android.gms.internal;

public class zzbnf
  extends zzbng
{
  private final zzbma zzceB;
  
  public zzbnf(zzbnh paramzzbnh, zzbmj paramzzbmj, zzbma paramzzbma)
  {
    super(zzbng.zza.zzceF, paramzzbnh, paramzzbmj);
    this.zzceB = paramzzbma;
  }
  
  public String toString()
  {
    return String.format("Merge { path=%s, source=%s, children=%s }", new Object[] { zzVc(), zzXO(), this.zzceB });
  }
  
  public zzbma zzXN()
  {
    return this.zzceB;
  }
  
  public zzbng zzc(zzbos paramzzbos)
  {
    if (this.zzbXY.isEmpty())
    {
      paramzzbos = this.zzceB.zzg(new zzbmj(new zzbos[] { paramzzbos }));
      if (!paramzzbos.isEmpty()) {}
    }
    while (!this.zzbXY.zzXi().equals(paramzzbos))
    {
      return null;
      if (paramzzbos.zzWF() != null) {
        return new zzbni(this.zzceD, zzbmj.zzXf(), paramzzbos.zzWF());
      }
      return new zzbnf(this.zzceD, zzbmj.zzXf(), paramzzbos);
    }
    return new zzbnf(this.zzceD, this.zzbXY.zzXj(), this.zzceB);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbnf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */