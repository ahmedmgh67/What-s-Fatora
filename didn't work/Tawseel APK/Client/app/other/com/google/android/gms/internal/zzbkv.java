package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseApp.zza;
import com.google.firebase.auth.GetTokenResult;
import java.util.concurrent.ScheduledExecutorService;

public class zzbkv
  implements zzbly
{
  private final ScheduledExecutorService zzbYl;
  private final FirebaseApp zzbYm;
  
  public zzbkv(@NonNull FirebaseApp paramFirebaseApp, @NonNull ScheduledExecutorService paramScheduledExecutorService)
  {
    this.zzbYm = paramFirebaseApp;
    this.zzbYl = paramScheduledExecutorService;
  }
  
  private FirebaseApp.zza zzb(final zzbly.zzb paramzzb)
  {
    new FirebaseApp.zza()
    {
      public void zzb(@NonNull final zzbqm paramAnonymouszzbqm)
      {
        zzbkv.zza(zzbkv.this).execute(new Runnable()
        {
          public void run()
          {
            zzbkv.3.this.zzbYo.zziV(paramAnonymouszzbqm.getToken());
          }
        });
      }
    };
  }
  
  public void zza(zzbly.zzb paramzzb)
  {
    paramzzb = zzb(paramzzb);
    this.zzbYm.zza(paramzzb);
  }
  
  public void zza(boolean paramBoolean, @NonNull final zzbly.zza paramzza)
  {
    this.zzbYm.getToken(paramBoolean).addOnSuccessListener(this.zzbYl, new OnSuccessListener()
    {
      public void zza(GetTokenResult paramAnonymousGetTokenResult)
      {
        paramzza.zziM(paramAnonymousGetTokenResult.getToken());
      }
    }).addOnFailureListener(this.zzbYl, new OnFailureListener()
    {
      private boolean zza(Exception paramAnonymousException)
      {
        return ((paramAnonymousException instanceof FirebaseApiNotAvailableException)) || ((paramAnonymousException instanceof zzbqn));
      }
      
      public void onFailure(@NonNull Exception paramAnonymousException)
      {
        if (zza(paramAnonymousException))
        {
          paramzza.zziM(null);
          return;
        }
        paramzza.onError(paramAnonymousException.getMessage());
      }
    });
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbkv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */