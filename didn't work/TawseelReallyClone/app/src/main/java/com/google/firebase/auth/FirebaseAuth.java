package com.google.firebase.auth;

import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzbiu;
import com.google.android.gms.internal.zzbix;
import com.google.android.gms.internal.zzbiz;
import com.google.android.gms.internal.zzbjc;
import com.google.android.gms.internal.zzbjc.zza;
import com.google.android.gms.internal.zzbjc.zza.zza;
import com.google.android.gms.internal.zzbjp;
import com.google.android.gms.internal.zzbkb;
import com.google.android.gms.internal.zzbke;
import com.google.android.gms.internal.zzbkg;
import com.google.android.gms.internal.zzbkh;
import com.google.android.gms.internal.zzbkk;
import com.google.android.gms.internal.zzbkl;
import com.google.android.gms.internal.zzbkm;
import com.google.android.gms.internal.zzbql;
import com.google.android.gms.internal.zzbqm;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class FirebaseAuth
  implements zzbql
{
  private static FirebaseAuth zzbVC;
  private static Map<String, FirebaseAuth> zzbha = new ArrayMap();
  private List<AuthStateListener> mListeners;
  private zzbkl zzbVA;
  private zzbkm zzbVB;
  private FirebaseApp zzbVx;
  private zzbiu zzbVy;
  private FirebaseUser zzbVz;
  
  public FirebaseAuth(FirebaseApp paramFirebaseApp)
  {
    this(paramFirebaseApp, zzb(paramFirebaseApp), new zzbkl(paramFirebaseApp.getApplicationContext(), paramFirebaseApp.zzTu(), zzbiz.zzUg()));
  }
  
  FirebaseAuth(FirebaseApp paramFirebaseApp, zzbiu paramzzbiu, zzbkl paramzzbkl)
  {
    this.zzbVx = ((FirebaseApp)zzac.zzw(paramFirebaseApp));
    this.zzbVy = ((zzbiu)zzac.zzw(paramzzbiu));
    this.zzbVA = ((zzbkl)zzac.zzw(paramzzbkl));
    this.mListeners = new CopyOnWriteArrayList();
    this.zzbVB = zzbkm.zzUK();
    zzTT();
  }
  
  public static FirebaseAuth getInstance()
  {
    return zzc(FirebaseApp.getInstance());
  }
  
  @Keep
  public static FirebaseAuth getInstance(@NonNull FirebaseApp paramFirebaseApp)
  {
    return zzc(paramFirebaseApp);
  }
  
  static zzbiu zzb(FirebaseApp paramFirebaseApp)
  {
    zzbjc.zza localzza = new zzbjc.zza.zza(paramFirebaseApp.getOptions().getApiKey()).zzUj();
    return zzbjc.zza(paramFirebaseApp.getApplicationContext(), localzza);
  }
  
  private static FirebaseAuth zzc(@NonNull FirebaseApp paramFirebaseApp)
  {
    return zzd(paramFirebaseApp);
  }
  
  private static FirebaseAuth zzd(@NonNull FirebaseApp paramFirebaseApp)
  {
    try
    {
      FirebaseAuth localFirebaseAuth = (FirebaseAuth)zzbha.get(paramFirebaseApp.zzTu());
      Object localObject = localFirebaseAuth;
      if (localFirebaseAuth == null)
      {
        localObject = new zzbkg(paramFirebaseApp);
        paramFirebaseApp.zza((zzbql)localObject);
        if (zzbVC == null) {
          zzbVC = (FirebaseAuth)localObject;
        }
        zzbha.put(paramFirebaseApp.zzTu(), localObject);
      }
      return (FirebaseAuth)localObject;
    }
    finally {}
  }
  
  public void addAuthStateListener(@NonNull final AuthStateListener paramAuthStateListener)
  {
    this.mListeners.add(paramAuthStateListener);
    this.zzbVB.execute(new Runnable()
    {
      public void run()
      {
        paramAuthStateListener.onAuthStateChanged(FirebaseAuth.this);
      }
    });
  }
  
  @NonNull
  public Task<Void> applyActionCode(@NonNull String paramString)
  {
    zzac.zzdv(paramString);
    return this.zzbVy.zze(this.zzbVx, paramString);
  }
  
  @NonNull
  public Task<ActionCodeResult> checkActionCode(@NonNull String paramString)
  {
    zzac.zzdv(paramString);
    return this.zzbVy.zzd(this.zzbVx, paramString);
  }
  
  @NonNull
  public Task<Void> confirmPasswordReset(@NonNull String paramString1, @NonNull String paramString2)
  {
    zzac.zzdv(paramString1);
    zzac.zzdv(paramString2);
    return this.zzbVy.zza(this.zzbVx, paramString1, paramString2);
  }
  
  @NonNull
  public Task<AuthResult> createUserWithEmailAndPassword(@NonNull String paramString1, @NonNull String paramString2)
  {
    zzac.zzdv(paramString1);
    zzac.zzdv(paramString2);
    return this.zzbVy.zza(this.zzbVx, paramString1, paramString2, new zza());
  }
  
  @NonNull
  public Task<ProviderQueryResult> fetchProvidersForEmail(@NonNull String paramString)
  {
    zzac.zzdv(paramString);
    return this.zzbVy.zza(this.zzbVx, paramString);
  }
  
  @Nullable
  public FirebaseUser getCurrentUser()
  {
    return this.zzbVz;
  }
  
  public void removeAuthStateListener(@NonNull AuthStateListener paramAuthStateListener)
  {
    this.mListeners.remove(paramAuthStateListener);
  }
  
  @NonNull
  public Task<Void> sendPasswordResetEmail(@NonNull String paramString)
  {
    zzac.zzdv(paramString);
    return this.zzbVy.zzb(this.zzbVx, paramString);
  }
  
  @NonNull
  public Task<AuthResult> signInAnonymously()
  {
    if ((this.zzbVz != null) && (this.zzbVz.isAnonymous())) {
      return Tasks.forResult(new zzbke((zzbkh)this.zzbVz));
    }
    return this.zzbVy.zza(this.zzbVx, new zza());
  }
  
  @NonNull
  public Task<AuthResult> signInWithCredential(@NonNull AuthCredential paramAuthCredential)
  {
    zzac.zzw(paramAuthCredential);
    if (EmailAuthCredential.class.isAssignableFrom(paramAuthCredential.getClass()))
    {
      paramAuthCredential = (EmailAuthCredential)paramAuthCredential;
      return this.zzbVy.zzb(this.zzbVx, paramAuthCredential.getEmail(), paramAuthCredential.getPassword(), new zza());
    }
    return this.zzbVy.zza(this.zzbVx, paramAuthCredential, new zza());
  }
  
  @NonNull
  public Task<AuthResult> signInWithCustomToken(@NonNull String paramString)
  {
    zzac.zzdv(paramString);
    return this.zzbVy.zza(this.zzbVx, paramString, new zza());
  }
  
  @NonNull
  public Task<AuthResult> signInWithEmailAndPassword(@NonNull String paramString1, @NonNull String paramString2)
  {
    zzac.zzdv(paramString1);
    zzac.zzdv(paramString2);
    return this.zzbVy.zzb(this.zzbVx, paramString1, paramString2, new zza());
  }
  
  public void signOut()
  {
    zzTS();
  }
  
  @NonNull
  public Task<String> verifyPasswordResetCode(@NonNull String paramString)
  {
    zzac.zzdv(paramString);
    return this.zzbVy.zzf(this.zzbVx, paramString);
  }
  
  public void zzTS()
  {
    if (this.zzbVz != null)
    {
      this.zzbVA.zzh(this.zzbVz);
      this.zzbVz = null;
    }
    this.zzbVA.zzUJ();
    zza(null);
  }
  
  protected void zzTT()
  {
    this.zzbVz = this.zzbVA.zzUI();
    if (this.zzbVz != null)
    {
      zzbjp localzzbjp = this.zzbVA.zzg(this.zzbVz);
      if (localzzbjp != null) {
        zza(this.zzbVz, localzzbjp, false);
      }
    }
  }
  
  @NonNull
  public Task<Void> zza(@NonNull FirebaseUser paramFirebaseUser, @NonNull AuthCredential paramAuthCredential)
  {
    zzac.zzw(paramFirebaseUser);
    zzac.zzw(paramAuthCredential);
    if (EmailAuthCredential.class.isAssignableFrom(paramAuthCredential.getClass()))
    {
      paramAuthCredential = (EmailAuthCredential)paramAuthCredential;
      return this.zzbVy.zza(this.zzbVx, paramFirebaseUser, paramAuthCredential.getEmail(), paramAuthCredential.getPassword(), new zza());
    }
    return this.zzbVy.zza(this.zzbVx, paramFirebaseUser, paramAuthCredential, new zza());
  }
  
  @NonNull
  public Task<Void> zza(@NonNull FirebaseUser paramFirebaseUser, @NonNull UserProfileChangeRequest paramUserProfileChangeRequest)
  {
    zzac.zzw(paramFirebaseUser);
    zzac.zzw(paramUserProfileChangeRequest);
    return this.zzbVy.zza(this.zzbVx, paramFirebaseUser, paramUserProfileChangeRequest, new zza());
  }
  
  @NonNull
  public Task<AuthResult> zza(@NonNull FirebaseUser paramFirebaseUser, @NonNull String paramString)
  {
    zzac.zzdv(paramString);
    zzac.zzw(paramFirebaseUser);
    return this.zzbVy.zzd(this.zzbVx, paramFirebaseUser, paramString, new zza());
  }
  
  @NonNull
  public Task<GetTokenResult> zza(@Nullable FirebaseUser paramFirebaseUser, boolean paramBoolean)
  {
    if (paramFirebaseUser == null) {
      return Tasks.forException(zzbix.zzcb(new Status(17495)));
    }
    zzbjp localzzbjp = this.zzbVz.zzTW();
    if ((localzzbjp.isValid()) && (!paramBoolean)) {
      return Tasks.forResult(new GetTokenResult(localzzbjp.getAccessToken()));
    }
    this.zzbVy.zza(this.zzbVx, paramFirebaseUser, localzzbjp.zzUs(), new zzbkb()
    {
      public void zza(@NonNull zzbjp paramAnonymouszzbjp, @NonNull FirebaseUser paramAnonymousFirebaseUser)
      {
        FirebaseAuth.this.zza(paramAnonymousFirebaseUser, paramAnonymouszzbjp, true);
      }
    });
  }
  
  public void zza(@Nullable final FirebaseUser paramFirebaseUser)
  {
    if (paramFirebaseUser != null)
    {
      String str = String.valueOf(paramFirebaseUser.getUid());
      Log.d("FirebaseAuth", String.valueOf(str).length() + 36 + "Notifying listeners about user ( " + str + " ).");
      if (paramFirebaseUser == null) {
        break label103;
      }
    }
    label103:
    for (paramFirebaseUser = paramFirebaseUser.zzTY();; paramFirebaseUser = null)
    {
      paramFirebaseUser = new zzbqm(paramFirebaseUser);
      this.zzbVB.execute(new Runnable()
      {
        public void run()
        {
          FirebaseAuth.zza(FirebaseAuth.this).zza(paramFirebaseUser);
          Iterator localIterator = FirebaseAuth.zzb(FirebaseAuth.this).iterator();
          while (localIterator.hasNext()) {
            ((FirebaseAuth.AuthStateListener)localIterator.next()).onAuthStateChanged(FirebaseAuth.this);
          }
        }
      });
      return;
      Log.d("FirebaseAuth", "Notifying listeners about a sign-out event.");
      break;
    }
  }
  
  public void zza(@NonNull FirebaseUser paramFirebaseUser, @NonNull zzbjp paramzzbjp, boolean paramBoolean)
  {
    int k = 1;
    zzac.zzw(paramFirebaseUser);
    zzac.zzw(paramzzbjp);
    int i;
    if (this.zzbVz == null)
    {
      i = k;
      if (i != 0)
      {
        if (this.zzbVz != null) {
          this.zzbVz.zza(paramzzbjp);
        }
        zza(paramFirebaseUser, paramBoolean, false);
        zza(this.zzbVz);
      }
      if (paramBoolean) {
        this.zzbVA.zza(paramFirebaseUser, paramzzbjp);
      }
      return;
    }
    if (!this.zzbVz.zzTW().getAccessToken().equals(paramzzbjp.getAccessToken())) {}
    for (int j = 1;; j = 0)
    {
      i = k;
      if (!this.zzbVz.getUid().equals(paramFirebaseUser.getUid())) {
        break;
      }
      i = k;
      if (j != 0) {
        break;
      }
      i = 0;
      break;
    }
  }
  
  public void zza(@NonNull FirebaseUser paramFirebaseUser, boolean paramBoolean1, boolean paramBoolean2)
  {
    zzac.zzw(paramFirebaseUser);
    if (this.zzbVz == null) {
      this.zzbVz = paramFirebaseUser;
    }
    for (;;)
    {
      if (paramBoolean1) {
        this.zzbVA.zzf(this.zzbVz);
      }
      if (paramBoolean2) {
        zza(this.zzbVz);
      }
      return;
      this.zzbVz.zzaT(paramFirebaseUser.isAnonymous());
      this.zzbVz.zzR(paramFirebaseUser.getProviderData());
    }
  }
  
  @NonNull
  public Task<GetTokenResult> zzaS(boolean paramBoolean)
  {
    return zza(this.zzbVz, paramBoolean);
  }
  
  @NonNull
  public Task<Void> zzb(@NonNull FirebaseUser paramFirebaseUser)
  {
    zzac.zzw(paramFirebaseUser);
    return this.zzbVy.zzb(this.zzbVx, paramFirebaseUser, new zza());
  }
  
  @NonNull
  public Task<AuthResult> zzb(@NonNull FirebaseUser paramFirebaseUser, @NonNull AuthCredential paramAuthCredential)
  {
    zzac.zzw(paramAuthCredential);
    zzac.zzw(paramFirebaseUser);
    return this.zzbVy.zzb(this.zzbVx, paramFirebaseUser, paramAuthCredential, new zza());
  }
  
  @NonNull
  public Task<Void> zzb(@NonNull FirebaseUser paramFirebaseUser, @NonNull String paramString)
  {
    zzac.zzw(paramFirebaseUser);
    zzac.zzdv(paramString);
    return this.zzbVy.zzb(this.zzbVx, paramFirebaseUser, paramString, new zza());
  }
  
  @NonNull
  public Task<Void> zzc(@NonNull final FirebaseUser paramFirebaseUser)
  {
    zzac.zzw(paramFirebaseUser);
    this.zzbVy.zza(paramFirebaseUser, new zzbkk()
    {
      public void zzTU()
      {
        if (FirebaseAuth.zzc(FirebaseAuth.this).getUid().equalsIgnoreCase(paramFirebaseUser.getUid())) {
          FirebaseAuth.this.zzTS();
        }
      }
    });
  }
  
  @NonNull
  public Task<Void> zzc(@NonNull FirebaseUser paramFirebaseUser, @NonNull String paramString)
  {
    zzac.zzw(paramFirebaseUser);
    zzac.zzdv(paramString);
    return this.zzbVy.zzc(this.zzbVx, paramFirebaseUser, paramString, new zza());
  }
  
  @NonNull
  public Task<Void> zzix(@NonNull String paramString)
  {
    zzac.zzdv(paramString);
    return this.zzbVy.zzc(this.zzbVx, paramString);
  }
  
  public static abstract interface AuthStateListener
  {
    public abstract void onAuthStateChanged(@NonNull FirebaseAuth paramFirebaseAuth);
  }
  
  class zza
    implements zzbkb
  {
    zza() {}
    
    public void zza(@NonNull zzbjp paramzzbjp, @NonNull FirebaseUser paramFirebaseUser)
    {
      zzac.zzw(paramzzbjp);
      zzac.zzw(paramFirebaseUser);
      paramFirebaseUser.zza(paramzzbjp);
      FirebaseAuth.this.zza(paramFirebaseUser, paramzzbjp, true);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\auth\FirebaseAuth.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */