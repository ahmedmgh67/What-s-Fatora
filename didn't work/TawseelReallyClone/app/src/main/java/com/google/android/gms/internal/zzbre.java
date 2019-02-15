package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.zza;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.json.JSONObject;

public class zzbre
{
  private static final Object zzcmb = new Object();
  static volatile zzbre zzcmc;
  private Context mContext;
  private FirebaseApp zzciR;
  private zzbrd zzcmd;
  
  protected zzbre(@NonNull FirebaseApp paramFirebaseApp)
    throws RemoteException
  {
    this.mContext = paramFirebaseApp.getApplicationContext();
    this.zzciR = paramFirebaseApp;
    try
    {
      this.zzcmd = zzbrd.zza.zzfM(DynamiteModule.zza(this.mContext, DynamiteModule.zzaQw, "com.google.android.gms.firebasestorage").zzdX("com.google.firebase.storage.network.NetworkRequestFactoryImpl"));
      if (this.zzcmd == null)
      {
        Log.e("NetworkRqFactoryProxy", "Unable to load Firebase Storage Network layer.");
        throw new RemoteException();
      }
    }
    catch (DynamiteModule.zza paramFirebaseApp)
    {
      Log.e("NetworkRqFactoryProxy", "NetworkRequestFactoryProxy failed with a RemoteException:", paramFirebaseApp);
      throw new RemoteException();
    }
  }
  
  private zzbrf zze(zzbrf paramzzbrf)
  {
    paramzzbrf.zzay("x-firebase-gmpid", this.zzciR.getOptions().getApplicationId());
    return paramzzbrf;
  }
  
  public static zzbre zzj(@NonNull FirebaseApp paramFirebaseApp)
    throws RemoteException
  {
    if (zzcmc == null) {}
    synchronized (zzcmb)
    {
      if (zzcmc == null) {
        zzcmc = new zzbre(paramFirebaseApp);
      }
      return zzcmc;
    }
  }
  
  @Nullable
  public String zzA(Uri paramUri)
  {
    try
    {
      paramUri = this.zzcmd.zzA(paramUri);
      return paramUri;
    }
    catch (RemoteException paramUri)
    {
      Log.e("NetworkRqFactoryProxy", "getDefaultURL failed with a RemoteException:", paramUri);
    }
    return null;
  }
  
  @NonNull
  public zzbrf zzC(Uri paramUri)
    throws RemoteException
  {
    return zze(new zzbrf(this.zzcmd.zza(paramUri, zze.zzA(this.mContext))));
  }
  
  @NonNull
  public zzbrf zzD(Uri paramUri)
    throws RemoteException
  {
    return zze(new zzbrf(this.zzcmd.zzb(paramUri, zze.zzA(this.mContext))));
  }
  
  @NonNull
  public zzbrf zza(Uri paramUri, long paramLong)
    throws RemoteException
  {
    return zze(new zzbrf(this.zzcmd.zza(paramUri, zze.zzA(this.mContext), paramLong)));
  }
  
  @Nullable
  public zzbrf zza(Uri paramUri, String paramString)
    throws RemoteException
  {
    return zze(new zzbrf(this.zzcmd.zzb(paramUri, zze.zzA(this.mContext), paramString)));
  }
  
  @NonNull
  public zzbrf zza(Uri paramUri, String paramString, byte[] paramArrayOfByte, long paramLong, int paramInt, boolean paramBoolean)
    throws RemoteException
  {
    return zze(new zzbrf(this.zzcmd.zza(paramUri, zze.zzA(this.mContext), paramString, zze.zzA(paramArrayOfByte), paramLong, paramInt, paramBoolean)));
  }
  
  @NonNull
  public zzbrf zza(Uri paramUri, JSONObject paramJSONObject)
    throws RemoteException
  {
    return zze(new zzbrf(this.zzcmd.zza(paramUri, zze.zzA(this.mContext), zze.zzA(paramJSONObject))));
  }
  
  @NonNull
  public zzbrf zza(Uri paramUri, JSONObject paramJSONObject, String paramString)
    throws RemoteException
  {
    return zze(new zzbrf(this.zzcmd.zza(paramUri, zze.zzA(this.mContext), zze.zzA(paramJSONObject), paramString)));
  }
  
  @Nullable
  public String zzabp()
  {
    try
    {
      String str = this.zzcmd.zzabp();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("NetworkRqFactoryProxy", "getBackendAuthority failed with a RemoteException:", localRemoteException);
    }
    return null;
  }
  
  @NonNull
  public zzbrf zzb(Uri paramUri, String paramString)
    throws RemoteException
  {
    return zze(new zzbrf(this.zzcmd.zzc(paramUri, zze.zzA(this.mContext), paramString)));
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbre.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */