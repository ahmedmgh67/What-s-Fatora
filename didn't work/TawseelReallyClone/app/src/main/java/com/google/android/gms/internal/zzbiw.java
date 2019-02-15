package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzl;
import com.google.android.gms.dynamite.DynamiteModule;

public class zzbiw
  extends zzl<zzbjb>
  implements zzbiv
{
  private static zzace zzahA = new zzace("FirebaseAuth", new String[] { "FirebaseAuth:" });
  private final Context mContext;
  private final zzbjc.zza zzbVU;
  
  public zzbiw(Context paramContext, Looper paramLooper, zzg paramzzg, zzbjc.zza paramzza, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    super(paramContext, paramLooper, 112, paramzzg, paramConnectionCallbacks, paramOnConnectionFailedListener);
    this.mContext = ((Context)zzac.zzw(paramContext));
    this.zzbVU = paramzza;
  }
  
  protected String zzeu()
  {
    return "com.google.firebase.auth.api.gms.service.START";
  }
  
  protected String zzev()
  {
    return "com.google.firebase.auth.api.internal.IFirebaseAuthService";
  }
  
  protected zzbjb zzfC(IBinder paramIBinder)
  {
    return zzbjb.zza.zzfE(paramIBinder);
  }
  
  protected Bundle zzql()
  {
    Bundle localBundle2 = super.zzql();
    Bundle localBundle1 = localBundle2;
    if (localBundle2 == null) {
      localBundle1 = new Bundle();
    }
    if (this.zzbVU != null) {
      localBundle1.putString("com.google.firebase.auth.API_KEY", this.zzbVU.getApiKey());
    }
    return localBundle1;
  }
  
  public boolean zzuI()
  {
    return DynamiteModule.zzA(this.mContext, "com.google.firebase.auth") == 0;
  }
  
  protected String zzwP()
  {
    String str2 = zzbji.getProperty("firebear.preference");
    String str1 = str2;
    if (TextUtils.isEmpty(str2)) {
      str1 = "default";
    }
    switch (str1.hashCode())
    {
    default: 
      label48:
      i = -1;
      switch (i)
      {
      default: 
        label50:
        str1 = "default";
      }
      switch (str1.hashCode())
      {
      }
      break;
    }
    label96:
    for (int i = -1;; i = 0) {
      switch (i)
      {
      default: 
        zzahA.zza("Loading module via default loading order.", new Object[0]);
        i = DynamiteModule.zzA(this.mContext, "com.google.firebase.auth");
        if (DynamiteModule.zzB(this.mContext, "com.google.android.gms.firebase_auth") < i) {
          break label228;
        }
        zzahA.zza("Loading remote module.", new Object[0]);
        return "com.google.android.gms";
        if (!str1.equals("local")) {
          break label48;
        }
        i = 0;
        break label50;
        if (!str1.equals("default")) {
          break label48;
        }
        i = 1;
        break label50;
        if (!str1.equals("local")) {
          break label96;
        }
      }
    }
    zzahA.zza("Loading fallback module override.", new Object[0]);
    return this.mContext.getPackageName();
    label228:
    zzahA.zza("Loading fallback module.", new Object[0]);
    return this.mContext.getPackageName();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbiw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */