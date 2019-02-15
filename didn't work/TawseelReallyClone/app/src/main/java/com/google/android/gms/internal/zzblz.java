package com.google.android.gms.internal;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.zza;

public class zzblz
  extends zzbme
{
  private final zzbml zzbXR;
  private final ChildEventListener zzcby;
  private final zzboe zzcbz;
  
  public zzblz(zzbml paramzzbml, ChildEventListener paramChildEventListener, zzboe paramzzboe)
  {
    this.zzbXR = paramzzbml;
    this.zzcby = paramChildEventListener;
    this.zzcbz = paramzzboe;
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof zzblz)) && (((zzblz)paramObject).zzcby.equals(this.zzcby)) && (((zzblz)paramObject).zzbXR.equals(this.zzbXR)) && (((zzblz)paramObject).zzcbz.equals(this.zzcbz));
  }
  
  public int hashCode()
  {
    return (this.zzcby.hashCode() * 31 + this.zzbXR.hashCode()) * 31 + this.zzcbz.hashCode();
  }
  
  public String toString()
  {
    return "ChildEventRegistration";
  }
  
  public zzboe zzWD()
  {
    return this.zzcbz;
  }
  
  public zzbme zza(zzboe paramzzboe)
  {
    return new zzblz(this.zzbXR, this.zzcby, paramzzboe);
  }
  
  public zzbnz zza(zzbny paramzzbny, zzboe paramzzboe)
  {
    DataSnapshot localDataSnapshot = zza.zza(zza.zza(this.zzbXR, paramzzboe.zzVc().zza(paramzzbny.zzYk())), paramzzbny.zzYi());
    if (paramzzbny.zzYm() != null) {}
    for (paramzzboe = paramzzbny.zzYm().asString();; paramzzboe = null) {
      return new zzbnz(paramzzbny.zzYl(), this, localDataSnapshot, paramzzboe);
    }
  }
  
  public void zza(zzbnz paramzzbnz)
  {
    if (zzXb()) {
      return;
    }
    switch (1.zzcbA[paramzzbnz.zzYl().ordinal()])
    {
    default: 
      return;
    case 1: 
      this.zzcby.onChildAdded(paramzzbnz.zzYo(), paramzzbnz.zzYp());
      return;
    case 2: 
      this.zzcby.onChildChanged(paramzzbnz.zzYo(), paramzzbnz.zzYp());
      return;
    case 3: 
      this.zzcby.onChildMoved(paramzzbnz.zzYo(), paramzzbnz.zzYp());
      return;
    }
    this.zzcby.onChildRemoved(paramzzbnz.zzYo());
  }
  
  public void zza(DatabaseError paramDatabaseError)
  {
    this.zzcby.onCancelled(paramDatabaseError);
  }
  
  public boolean zza(zzboa.zza paramzza)
  {
    return paramzza != zzboa.zza.zzcfQ;
  }
  
  public boolean zzc(zzbme paramzzbme)
  {
    return ((paramzzbme instanceof zzblz)) && (((zzblz)paramzzbme).zzcby.equals(this.zzcby));
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzblz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */