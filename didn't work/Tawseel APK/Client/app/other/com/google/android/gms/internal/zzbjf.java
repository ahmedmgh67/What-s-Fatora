package com.google.android.gms.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzac;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;

abstract class zzbjf<SuccessT, CallbackT>
{
  private boolean zzbLI;
  protected final int zzbVZ;
  protected FirebaseApp zzbVx;
  protected final zza zzbWa = new zza(null);
  protected FirebaseUser zzbWb;
  protected zzbjb zzbWc;
  protected CallbackT zzbWd;
  protected zzbje<SuccessT> zzbWe;
  protected zzbjp zzbWf;
  protected zzbjl zzbWg;
  protected zzbjj zzbWh;
  protected zzbjv zzbWi;
  protected String zzbWj;
  boolean zzbWk;
  SuccessT zzbWl;
  Status zzbWm;
  
  public zzbjf(int paramInt)
  {
    this.zzbVZ = paramInt;
  }
  
  private void zzUl()
  {
    zzUe();
    zzac.zza(this.zzbLI, "no success or failure set on method implementation");
  }
  
  protected abstract void dispatch()
    throws RemoteException;
  
  public abstract void zzUe();
  
  public void zzUk()
  {
    zzac(null);
  }
  
  public zzbjf<SuccessT, CallbackT> zza(zzbje<SuccessT> paramzzbje)
  {
    this.zzbWe = paramzzbje;
    return this;
  }
  
  public void zza(zzbjb paramzzbjb)
    throws RemoteException
  {
    this.zzbWc = paramzzbjb;
    dispatch();
  }
  
  public zzbjf<SuccessT, CallbackT> zzab(CallbackT paramCallbackT)
  {
    this.zzbWd = zzac.zzb(paramCallbackT, "external callback cannot be null");
    return this;
  }
  
  public void zzac(SuccessT paramSuccessT)
  {
    this.zzbLI = true;
    this.zzbWk = true;
    this.zzbWl = paramSuccessT;
    this.zzbWe.zza(paramSuccessT, null);
  }
  
  public void zzcc(Status paramStatus)
  {
    this.zzbLI = true;
    this.zzbWk = false;
    this.zzbWm = paramStatus;
    this.zzbWe.zza(null, paramStatus);
  }
  
  public zzbjf<SuccessT, CallbackT> zze(FirebaseApp paramFirebaseApp)
  {
    this.zzbVx = ((FirebaseApp)zzac.zzb(paramFirebaseApp, "firebaseApp cannot be null"));
    return this;
  }
  
  public zzbjf<SuccessT, CallbackT> zze(FirebaseUser paramFirebaseUser)
  {
    this.zzbWb = ((FirebaseUser)zzac.zzb(paramFirebaseUser, "firebaseUser cannot be null"));
    return this;
  }
  
  private class zza
    extends zzbja.zza
  {
    private zza() {}
    
    public void onFailure(@NonNull Status paramStatus)
      throws RemoteException
    {
      zzbjf.this.zzcc(paramStatus);
    }
    
    public void zzUh()
      throws RemoteException
    {
      if (zzbjf.this.zzbVZ == 5) {}
      for (boolean bool = true;; bool = false)
      {
        int i = zzbjf.this.zzbVZ;
        zzac.zza(bool, 36 + "Unexpected response type " + i);
        zzbjf.zzb(zzbjf.this);
        return;
      }
    }
    
    public void zzUi()
      throws RemoteException
    {
      if (zzbjf.this.zzbVZ == 6) {}
      for (boolean bool = true;; bool = false)
      {
        int i = zzbjf.this.zzbVZ;
        zzac.zza(bool, 36 + "Unexpected response type " + i);
        zzbjf.zzb(zzbjf.this);
        return;
      }
    }
    
    public void zza(@NonNull zzbjj paramzzbjj)
      throws RemoteException
    {
      if (zzbjf.this.zzbVZ == 3) {}
      for (boolean bool = true;; bool = false)
      {
        int i = zzbjf.this.zzbVZ;
        zzac.zza(bool, 36 + "Unexpected response type " + i);
        zzbjf.this.zzbWh = paramzzbjj;
        zzbjf.zzb(zzbjf.this);
        return;
      }
    }
    
    public void zza(@NonNull zzbjp paramzzbjp, @NonNull zzbjl paramzzbjl)
      throws RemoteException
    {
      if (zzbjf.this.zzbVZ == 2) {}
      for (boolean bool = true;; bool = false)
      {
        int i = zzbjf.this.zzbVZ;
        zzac.zza(bool, 37 + "Unexpected response type: " + i);
        zzbjf.this.zzbWf = paramzzbjp;
        zzbjf.this.zzbWg = paramzzbjl;
        zzbjf.zzb(zzbjf.this);
        return;
      }
    }
    
    public void zza(@Nullable zzbjv paramzzbjv)
      throws RemoteException
    {
      if (zzbjf.this.zzbVZ == 4) {}
      for (boolean bool = true;; bool = false)
      {
        int i = zzbjf.this.zzbVZ;
        zzac.zza(bool, 36 + "Unexpected response type " + i);
        zzbjf.this.zzbWi = paramzzbjv;
        zzbjf.zzb(zzbjf.this);
        return;
      }
    }
    
    public void zzb(@NonNull zzbjp paramzzbjp)
      throws RemoteException
    {
      boolean bool = true;
      if (zzbjf.this.zzbVZ == 1) {}
      for (;;)
      {
        int i = zzbjf.this.zzbVZ;
        zzac.zza(bool, 37 + "Unexpected response type: " + i);
        zzbjf.this.zzbWf = paramzzbjp;
        zzbjf.zzb(zzbjf.this);
        return;
        bool = false;
      }
    }
    
    public void zziy(@NonNull String paramString)
      throws RemoteException
    {
      if (zzbjf.this.zzbVZ == 7) {}
      for (boolean bool = true;; bool = false)
      {
        int i = zzbjf.this.zzbVZ;
        zzac.zza(bool, 36 + "Unexpected response type " + i);
        zzbjf.this.zzbWj = paramString;
        zzbjf.zzb(zzbjf.this);
        return;
      }
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbjf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */