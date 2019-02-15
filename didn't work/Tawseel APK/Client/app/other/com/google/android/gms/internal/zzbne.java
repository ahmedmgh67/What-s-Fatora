package com.google.android.gms.internal;

public class zzbne
  extends zzbng
{
  static
  {
    if (!zzbne.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public zzbne(zzbnh paramzzbnh, zzbmj paramzzbmj)
  {
    super(zzbng.zza.zzceH, paramzzbnh, paramzzbmj);
    assert (!paramzzbnh.zzXQ()) : "Can't have a listen complete from a user source";
  }
  
  public String toString()
  {
    return String.format("ListenComplete { path=%s, source=%s }", new Object[] { zzVc(), zzXO() });
  }
  
  public zzbng zzc(zzbos paramzzbos)
  {
    if (this.zzbXY.isEmpty()) {
      return new zzbne(this.zzceD, zzbmj.zzXf());
    }
    return new zzbne(this.zzceD, this.zzbXY.zzXj());
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbne.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */