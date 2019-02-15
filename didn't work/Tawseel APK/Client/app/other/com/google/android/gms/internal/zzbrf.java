package com.google.android.gms.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.storage.StorageException;
import java.io.InputStream;
import java.net.SocketException;
import org.json.JSONObject;

public class zzbrf
{
  private Exception zzbLK;
  private zzbrc zzcme;
  private int zzcmf;
  private Exception zzcmg;
  
  static
  {
    if (!zzbrf.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public zzbrf(@NonNull zzbrc paramzzbrc)
  {
    this.zzcme = paramzzbrc;
  }
  
  private boolean zzce(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((paramContext == null) || (!paramContext.isConnected()))
    {
      this.zzcmf = -2;
      this.zzcmg = new SocketException("Network subsystem is unavailable");
      return false;
    }
    return true;
  }
  
  @Nullable
  public Exception getException()
  {
    try
    {
      if (this.zzcmg != null) {
        return this.zzcmg;
      }
      if (this.zzbLK != null) {
        return this.zzbLK;
      }
      Exception localException = (Exception)zze.zzE(this.zzcme.zzabm());
      return localException;
    }
    catch (RemoteException localRemoteException)
    {
      this.zzbLK = localRemoteException;
      Log.e("NetworkRequestProxy", "getException failed with a RemoteException:", localRemoteException);
    }
    return null;
  }
  
  public int getResultCode()
  {
    try
    {
      if (this.zzcmf != 0) {
        return this.zzcmf;
      }
      int i = this.zzcme.getResultCode();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      this.zzbLK = localRemoteException;
      Log.e("NetworkRequestProxy", "getResultCode failed with a RemoteException:", localRemoteException);
    }
    return 0;
  }
  
  @Nullable
  public InputStream getStream()
  {
    try
    {
      InputStream localInputStream = (InputStream)zze.zzE(this.zzcme.zzabi());
      return localInputStream;
    }
    catch (RemoteException localRemoteException)
    {
      this.zzbLK = localRemoteException;
      Log.e("NetworkRequestProxy", "getStream failed with a RemoteException:", localRemoteException);
    }
    return null;
  }
  
  public void reset()
  {
    try
    {
      this.zzcmf = 0;
      this.zzcmg = null;
      this.zzcme.reset();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      this.zzbLK = localRemoteException;
      Log.e("NetworkRequestProxy", "reset failed with a RemoteException:", localRemoteException);
    }
  }
  
  public <TResult> void zza(TaskCompletionSource<TResult> paramTaskCompletionSource, TResult paramTResult)
  {
    Exception localException = getException();
    if ((zzabn()) && (localException == null))
    {
      paramTaskCompletionSource.setResult(paramTResult);
      return;
    }
    paramTResult = StorageException.fromExceptionAndHttpCode(localException, getResultCode());
    assert (paramTResult != null);
    paramTaskCompletionSource.setException(paramTResult);
  }
  
  public void zza(@Nullable String paramString, @NonNull Context paramContext)
  {
    try
    {
      if (!zzce(paramContext)) {
        return;
      }
      this.zzcme.zzjN(paramString);
      return;
    }
    catch (RemoteException paramString)
    {
      this.zzbLK = paramString;
      Log.e("NetworkRequestProxy", "performRequest failed with a RemoteException:", paramString);
    }
  }
  
  public void zzabh()
  {
    try
    {
      this.zzcme.zzabh();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      this.zzbLK = localRemoteException;
      Log.e("NetworkRequestProxy", "performRequestEnd failed with a RemoteException:", localRemoteException);
    }
  }
  
  @Nullable
  public String zzabk()
  {
    try
    {
      this.zzcme.zzabk();
      return null;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        this.zzbLK = localRemoteException;
        Log.e("NetworkRequestProxy", "getRawResult failed with a RemoteException:", localRemoteException);
      }
    }
  }
  
  public boolean zzabn()
  {
    try
    {
      if (this.zzcmf != -2)
      {
        if (this.zzcmg != null) {
          return false;
        }
        boolean bool = this.zzcme.zzabn();
        return bool;
      }
    }
    catch (RemoteException localRemoteException)
    {
      this.zzbLK = localRemoteException;
      Log.e("NetworkRequestProxy", "isResultSuccess failed with a RemoteException:", localRemoteException);
    }
    return false;
  }
  
  public int zzabo()
  {
    try
    {
      int i = this.zzcme.zzabo();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      this.zzbLK = localRemoteException;
      Log.e("NetworkRequestProxy", "getResultingContentLength failed with a RemoteException:", localRemoteException);
    }
    return 0;
  }
  
  @NonNull
  public JSONObject zzabq()
    throws RemoteException
  {
    return (JSONObject)zze.zzE(this.zzcme.zzabj());
  }
  
  public void zzay(String paramString1, String paramString2)
  {
    try
    {
      this.zzcme.zzay(paramString1, paramString2);
      return;
    }
    catch (RemoteException paramString2)
    {
      paramString1 = String.valueOf(paramString1);
      if (paramString1.length() == 0) {}
    }
    for (paramString1 = "Caught remote exception setting custom header:".concat(paramString1);; paramString1 = new String("Caught remote exception setting custom header:"))
    {
      Log.e("NetworkRequestProxy", paramString1, paramString2);
      return;
    }
  }
  
  public void zzjO(@Nullable String paramString)
  {
    try
    {
      this.zzcme.zzjO(paramString);
      return;
    }
    catch (RemoteException paramString)
    {
      this.zzbLK = paramString;
      Log.e("NetworkRequestProxy", "performRequestStart failed with a RemoteException:", paramString);
    }
  }
  
  @Nullable
  public String zzjP(String paramString)
  {
    try
    {
      String str = this.zzcme.zzjP(paramString);
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      paramString = String.valueOf(paramString);
      if (paramString.length() == 0) {}
    }
    for (paramString = "getResultString failed with a RemoteException:".concat(paramString);; paramString = new String("getResultString failed with a RemoteException:"))
    {
      Log.e("NetworkRequestProxy", paramString, localRemoteException);
      return null;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbrf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */