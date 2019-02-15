package com.google.android.gms.internal;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

public class zzbnz
  implements zzboa
{
  private final zzbme zzcfE;
  private final zzboa.zza zzcfG;
  private final DataSnapshot zzcfK;
  private final String zzcfL;
  
  public zzbnz(zzboa.zza paramzza, zzbme paramzzbme, DataSnapshot paramDataSnapshot, String paramString)
  {
    this.zzcfG = paramzza;
    this.zzcfE = paramzzbme;
    this.zzcfK = paramDataSnapshot;
    this.zzcfL = paramString;
  }
  
  public String toString()
  {
    if (this.zzcfG == zzboa.zza.zzcfQ)
    {
      str1 = String.valueOf(zzVc());
      str2 = String.valueOf(this.zzcfG);
      str3 = String.valueOf(this.zzcfK.getValue(true));
      return String.valueOf(str1).length() + 4 + String.valueOf(str2).length() + String.valueOf(str3).length() + str1 + ": " + str2 + ": " + str3;
    }
    String str1 = String.valueOf(zzVc());
    String str2 = String.valueOf(this.zzcfG);
    String str3 = String.valueOf(this.zzcfK.getKey());
    String str4 = String.valueOf(this.zzcfK.getValue(true));
    return String.valueOf(str1).length() + 10 + String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(str4).length() + str1 + ": " + str2 + ": { " + str3 + ": " + str4 + " }";
  }
  
  public zzbmj zzVc()
  {
    zzbmj localzzbmj = this.zzcfK.getRef().zzVc();
    if (this.zzcfG == zzboa.zza.zzcfQ) {
      return localzzbmj;
    }
    return localzzbmj.zzXk();
  }
  
  public void zzYj()
  {
    this.zzcfE.zza(this);
  }
  
  public zzboa.zza zzYl()
  {
    return this.zzcfG;
  }
  
  public DataSnapshot zzYo()
  {
    return this.zzcfK;
  }
  
  public String zzYp()
  {
    return this.zzcfL;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbnz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */