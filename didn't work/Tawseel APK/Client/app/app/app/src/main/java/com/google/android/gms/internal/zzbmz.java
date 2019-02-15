package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.zza;

public class zzbmz
  extends zzbme
{
  private final zzbml zzbXR;
  private final zzboe zzcbz;
  private final ValueEventListener zzcen;
  
  public zzbmz(zzbml paramzzbml, ValueEventListener paramValueEventListener, zzboe paramzzboe)
  {
    this.zzbXR = paramzzbml;
    this.zzcen = paramValueEventListener;
    this.zzcbz = paramzzboe;
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof zzbmz)) && (((zzbmz)paramObject).zzcen.equals(this.zzcen)) && (((zzbmz)paramObject).zzbXR.equals(this.zzbXR)) && (((zzbmz)paramObject).zzcbz.equals(this.zzcbz));
  }
  
  public int hashCode()
  {
    return (this.zzcen.hashCode() * 31 + this.zzbXR.hashCode()) * 31 + this.zzcbz.hashCode();
  }
  
  public String toString()
  {
    return "ValueEventRegistration";
  }
  
  public zzboe zzWD()
  {
    return this.zzcbz;
  }
  
  public zzbme zza(zzboe paramzzboe)
  {
    return new zzbmz(this.zzbXR, this.zzcen, paramzzboe);
  }
  
  public zzbnz zza(zzbny paramzzbny, zzboe paramzzboe)
  {
    paramzzbny = zza.zza(zza.zza(this.zzbXR, paramzzboe.zzVc()), paramzzbny.zzYi());
    return new zzbnz(zzboa.zza.zzcfQ, this, paramzzbny, null);
  }
  
  public void zza(zzbnz paramzzbnz)
  {
    if (zzXb()) {
      return;
    }
    this.zzcen.onDataChange(paramzzbnz.zzYo());
  }
  
  public void zza(DatabaseError paramDatabaseError)
  {
    this.zzcen.onCancelled(paramDatabaseError);
  }
  
  public boolean zza(zzboa.zza paramzza)
  {
    return paramzza == zzboa.zza.zzcfQ;
  }
  
  public boolean zzc(zzbme paramzzbme)
  {
    return ((paramzzbme instanceof zzbmz)) && (((zzbmz)paramzzbme).zzcen.equals(this.zzcen));
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbmz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */