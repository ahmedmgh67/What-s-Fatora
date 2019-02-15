package com.google.android.gms.internal;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.Logger.Level;

public class zzbmd
  extends zzbmc
{
  public void setLogLevel(Logger.Level paramLevel)
  {
    for (;;)
    {
      try
      {
        zzWN();
        switch (1.zzcbS[paramLevel.ordinal()])
        {
        case 1: 
          paramLevel = String.valueOf(paramLevel);
          throw new IllegalArgumentException(String.valueOf(paramLevel).length() + 19 + "Unknown log level: " + paramLevel);
        }
      }
      finally {}
      this.zzcbO = zzboq.zza.zzcgF;
      for (;;)
      {
        return;
        this.zzcbO = zzboq.zza.zzcgG;
        continue;
        this.zzcbO = zzboq.zza.zzcgH;
        continue;
        this.zzcbO = zzboq.zza.zzcgI;
        continue;
        this.zzcbO = zzboq.zza.zzcgJ;
      }
    }
  }
  
  public void setPersistenceEnabled(boolean paramBoolean)
  {
    try
    {
      zzWN();
      this.zzbZx = paramBoolean;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void zzf(FirebaseApp paramFirebaseApp)
  {
    try
    {
      this.zzbYm = paramFirebaseApp;
      return;
    }
    finally
    {
      paramFirebaseApp = finally;
      throw paramFirebaseApp;
    }
  }
  
  public void zziZ(String paramString)
  {
    try
    {
      zzWN();
      if ((paramString == null) || (paramString.isEmpty())) {
        throw new IllegalArgumentException("Session identifier is not allowed to be empty or null!");
      }
    }
    finally {}
    this.zzcbN = paramString;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbmd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */