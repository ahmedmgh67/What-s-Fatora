package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseError;

public class zzbnx
  implements zzboa
{
  private final zzbmj zzbXY;
  private final zzbme zzcfE;
  private final DatabaseError zzcfF;
  
  public zzbnx(zzbme paramzzbme, DatabaseError paramDatabaseError, zzbmj paramzzbmj)
  {
    this.zzcfE = paramzzbme;
    this.zzbXY = paramzzbmj;
    this.zzcfF = paramDatabaseError;
  }
  
  public String toString()
  {
    String str = String.valueOf(zzVc());
    return String.valueOf(str).length() + 7 + str + ":CANCEL";
  }
  
  public zzbmj zzVc()
  {
    return this.zzbXY;
  }
  
  public void zzYj()
  {
    this.zzcfE.zza(this.zzcfF);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbnx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */