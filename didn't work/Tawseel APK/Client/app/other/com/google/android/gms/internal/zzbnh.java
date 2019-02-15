package com.google.android.gms.internal;

public class zzbnh
{
  public static final zzbnh zzceJ;
  public static final zzbnh zzceK;
  private final zza zzceL;
  private final zzbod zzceM;
  private final boolean zzceN;
  
  static
  {
    if (!zzbnh.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      zzceJ = new zzbnh(zza.zzceO, null, false);
      zzceK = new zzbnh(zza.zzceP, null, false);
      return;
    }
  }
  
  public zzbnh(zza paramzza, zzbod paramzzbod, boolean paramBoolean)
  {
    this.zzceL = paramzza;
    this.zzceM = paramzzbod;
    this.zzceN = paramBoolean;
    assert ((!paramBoolean) || (zzXR()));
  }
  
  public static zzbnh zzc(zzbod paramzzbod)
  {
    return new zzbnh(zza.zzceP, paramzzbod, true);
  }
  
  public String toString()
  {
    String str1 = String.valueOf(this.zzceL);
    String str2 = String.valueOf(this.zzceM);
    boolean bool = this.zzceN;
    return String.valueOf(str1).length() + 52 + String.valueOf(str2).length() + "OperationSource{source=" + str1 + ", queryParams=" + str2 + ", tagged=" + bool + "}";
  }
  
  public boolean zzXQ()
  {
    return this.zzceL == zza.zzceO;
  }
  
  public boolean zzXR()
  {
    return this.zzceL == zza.zzceP;
  }
  
  public boolean zzXS()
  {
    return this.zzceN;
  }
  
  public zzbod zzXT()
  {
    return this.zzceM;
  }
  
  private static enum zza
  {
    private zza() {}
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbnh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */