package com.google.android.gms.internal;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.util.Log;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseApp.zzb;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.connection.idl.zzc;
import com.google.firebase.database.connection.idl.zze;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;

public class zzbkx
  implements zzbmk
{
  private final FirebaseApp zzbYm;
  private final Set<String> zzbYr = new HashSet();
  private final Context zzqs;
  
  public zzbkx(FirebaseApp paramFirebaseApp)
  {
    this.zzbYm = paramFirebaseApp;
    if (this.zzbYm == null)
    {
      Log.e("FirebaseDatabase", "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
      Log.e("FirebaseDatabase", "ERROR: You must call FirebaseApp.initializeApp() before using Firebase Database.");
      Log.e("FirebaseDatabase", "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
      throw new RuntimeException("You need to call FirebaseApp.initializeApp() before using Firebase Database.");
    }
    this.zzqs = this.zzbYm.getApplicationContext();
  }
  
  public zzblr zza(final zzbmc paramzzbmc, zzbln paramzzbln, zzblp paramzzblp, zzblr.zza paramzza)
  {
    paramzzbmc = new zzc(paramzzblp, paramzzbmc.zzWu(), null, paramzzbmc.zzVK(), FirebaseDatabase.getSdkVersion(), paramzzbmc.zzjQ());
    paramzzbmc = zze.zza(this.zzqs, paramzzbmc, paramzzbln, paramzza);
    this.zzbYm.zza(new FirebaseApp.zzb()
    {
      public void zzaQ(boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean)
        {
          paramzzbmc.interrupt("app_in_background");
          return;
        }
        paramzzbmc.resume("app_in_background");
      }
    });
    return paramzzbmc;
  }
  
  public zzbly zza(ScheduledExecutorService paramScheduledExecutorService)
  {
    return new zzbkv(this.zzbYm, paramScheduledExecutorService);
  }
  
  public zzbmg zza(zzbmc paramzzbmc)
  {
    return new zzbkw();
  }
  
  public zzbnn zza(zzbmc paramzzbmc, String paramString)
  {
    String str = paramzzbmc.zzWS();
    paramString = String.valueOf(paramString).length() + 1 + String.valueOf(str).length() + paramString + "_" + str;
    if (this.zzbYr.contains(paramString)) {
      throw new DatabaseException(String.valueOf(str).length() + 47 + "SessionPersistenceKey '" + str + "' has already been used.");
    }
    this.zzbYr.add(paramString);
    return new zzbnk(paramzzbmc, new zzbky(this.zzqs, paramzzbmc, paramString), new zzbnl(paramzzbmc.zzWP()));
  }
  
  public zzboq zza(zzbmc paramzzbmc, zzboq.zza paramzza, List<String> paramList)
  {
    return new zzbon(paramzza, paramList);
  }
  
  public zzbmo zzb(zzbmc paramzzbmc)
  {
    new zzbqa()
    {
      public void zzj(final Throwable paramAnonymousThrowable)
      {
        final String str = zzbqa.zzl(paramAnonymousThrowable);
        this.zzbYs.zzd(str, paramAnonymousThrowable);
        new Handler(zzbkx.zza(zzbkx.this).getMainLooper()).post(new Runnable()
        {
          public void run()
          {
            throw new RuntimeException(str, paramAnonymousThrowable);
          }
        });
        zzVJ().shutdownNow();
      }
    };
  }
  
  public String zzc(zzbmc paramzzbmc)
  {
    int i = Build.VERSION.SDK_INT;
    return 19 + i + "/Android";
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbkx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */