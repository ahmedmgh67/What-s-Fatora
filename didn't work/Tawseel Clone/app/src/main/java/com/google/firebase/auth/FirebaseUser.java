package com.google.firebase.auth;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzbjp;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import java.util.List;

public abstract class FirebaseUser
  implements UserInfo
{
  private FirebaseAuth zzTZ()
  {
    return FirebaseAuth.getInstance(zzTV());
  }
  
  @NonNull
  public Task<Void> delete()
  {
    return zzTZ().zzc(this);
  }
  
  @Nullable
  public abstract String getDisplayName();
  
  @Nullable
  public abstract String getEmail();
  
  @Nullable
  public abstract Uri getPhotoUrl();
  
  @NonNull
  public abstract List<? extends UserInfo> getProviderData();
  
  @NonNull
  public abstract String getProviderId();
  
  @Nullable
  public abstract List<String> getProviders();
  
  @NonNull
  public Task<GetTokenResult> getToken(boolean paramBoolean)
  {
    return zzTZ().zza(this, paramBoolean);
  }
  
  @NonNull
  public abstract String getUid();
  
  public abstract boolean isAnonymous();
  
  @NonNull
  public Task<AuthResult> linkWithCredential(@NonNull AuthCredential paramAuthCredential)
  {
    zzac.zzw(paramAuthCredential);
    return zzTZ().zzb(this, paramAuthCredential);
  }
  
  public Task<Void> reauthenticate(@NonNull AuthCredential paramAuthCredential)
  {
    zzac.zzw(paramAuthCredential);
    return zzTZ().zza(this, paramAuthCredential);
  }
  
  @NonNull
  public Task<Void> reload()
  {
    return zzTZ().zzb(this);
  }
  
  @NonNull
  public Task<Void> sendEmailVerification()
  {
    zzTZ().zza(this, false).continueWithTask(new Continuation()
    {
      public Task<Void> zze(@NonNull Task<GetTokenResult> paramAnonymousTask)
        throws Exception
      {
        paramAnonymousTask = (GetTokenResult)paramAnonymousTask.getResult();
        return FirebaseUser.zzd(FirebaseUser.this).zzix(paramAnonymousTask.getToken());
      }
    });
  }
  
  public Task<AuthResult> unlink(@NonNull String paramString)
  {
    zzac.zzdv(paramString);
    return zzTZ().zza(this, paramString);
  }
  
  @NonNull
  public Task<Void> updateEmail(@NonNull String paramString)
  {
    zzac.zzdv(paramString);
    return zzTZ().zzb(this, paramString);
  }
  
  @NonNull
  public Task<Void> updatePassword(@NonNull String paramString)
  {
    zzac.zzdv(paramString);
    return zzTZ().zzc(this, paramString);
  }
  
  @NonNull
  public Task<Void> updateProfile(@NonNull UserProfileChangeRequest paramUserProfileChangeRequest)
  {
    zzac.zzw(paramUserProfileChangeRequest);
    return zzTZ().zza(this, paramUserProfileChangeRequest);
  }
  
  @NonNull
  public abstract FirebaseUser zzR(@NonNull List<? extends UserInfo> paramList);
  
  @NonNull
  public abstract FirebaseApp zzTV();
  
  @NonNull
  public abstract zzbjp zzTW();
  
  @NonNull
  public abstract String zzTX();
  
  @NonNull
  public abstract String zzTY();
  
  public abstract void zza(@NonNull zzbjp paramzzbjp);
  
  public abstract FirebaseUser zzaT(boolean paramBoolean);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\auth\FirebaseUser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */