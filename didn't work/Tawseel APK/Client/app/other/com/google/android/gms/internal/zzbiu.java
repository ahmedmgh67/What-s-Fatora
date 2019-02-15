package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zzc;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.ActionCodeResult;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.ProviderQueryResult;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.zza;
import java.util.ArrayList;
import java.util.List;

public class zzbiu
  extends zzc<zzbjc.zza>
{
  zzbiu(@NonNull Context paramContext, @NonNull zzbjc.zza paramzza)
  {
    super(paramContext, zzbjc.zzbVY, paramzza, new zza());
  }
  
  private <ResultT, CallbackT> zzj<ResultT, CallbackT> zza(zzbjf<ResultT, CallbackT> paramzzbjf)
  {
    return new zzj(paramzzbjf);
  }
  
  @NonNull
  private static zzbkh zza(@NonNull FirebaseApp paramFirebaseApp, @NonNull zzbjl paramzzbjl)
  {
    return zza(paramFirebaseApp, paramzzbjl, false);
  }
  
  @NonNull
  private static zzbkh zza(@NonNull FirebaseApp paramFirebaseApp, @NonNull zzbjl paramzzbjl, boolean paramBoolean)
  {
    zzac.zzw(paramFirebaseApp);
    zzac.zzw(paramzzbjl);
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new zzbkf(paramzzbjl, "firebase"));
    paramzzbjl = paramzzbjl.zzUp();
    if ((paramzzbjl != null) && (!paramzzbjl.isEmpty()))
    {
      int i = 0;
      while (i < paramzzbjl.size())
      {
        localArrayList.add(new zzbkf((zzbjr)paramzzbjl.get(i)));
        i += 1;
      }
    }
    paramFirebaseApp = new zzbkh(paramFirebaseApp, localArrayList);
    paramFirebaseApp.zzaT(paramBoolean);
    return paramFirebaseApp;
  }
  
  @NonNull
  private Task<AuthResult> zza(@NonNull FirebaseApp paramFirebaseApp, @NonNull AuthCredential paramAuthCredential, @NonNull FirebaseUser paramFirebaseUser, @NonNull zzbkb paramzzbkb)
  {
    zzac.zzw(paramFirebaseApp);
    zzac.zzw(paramAuthCredential);
    zzac.zzw(paramFirebaseUser);
    zzac.zzw(paramzzbkb);
    List localList = paramFirebaseUser.getProviders();
    if ((localList != null) && (localList.contains(paramAuthCredential.getProvider()))) {
      return Tasks.forException(zzbix.zzcb(new Status(17015)));
    }
    return doWrite(zza(new zzi(paramAuthCredential).zze(paramFirebaseApp).zze(paramFirebaseUser).zzab(paramzzbkb)));
  }
  
  @NonNull
  private Task<AuthResult> zza(@NonNull FirebaseApp paramFirebaseApp, @NonNull EmailAuthCredential paramEmailAuthCredential, @NonNull FirebaseUser paramFirebaseUser, @NonNull zzbkb paramzzbkb)
  {
    return doWrite(zza(new zzh(paramEmailAuthCredential).zze(paramFirebaseApp).zze(paramFirebaseUser).zzab(paramzzbkb)));
  }
  
  @NonNull
  private Task<AuthResult> zza(@NonNull FirebaseApp paramFirebaseApp, @NonNull FirebaseUser paramFirebaseUser, @NonNull zzbkb paramzzbkb)
  {
    return doWrite(zza(new zzt().zze(paramFirebaseApp).zze(paramFirebaseUser).zzab(paramzzbkb)));
  }
  
  @NonNull
  private Task<AuthResult> zza(@NonNull FirebaseApp paramFirebaseApp, @NonNull String paramString, @NonNull FirebaseUser paramFirebaseUser, @NonNull zzbkb paramzzbkb)
  {
    return doWrite(zza(new zzu(paramString).zze(paramFirebaseApp).zze(paramFirebaseUser).zzab(paramzzbkb)));
  }
  
  public Task<AuthResult> zza(@NonNull FirebaseApp paramFirebaseApp, @NonNull zzbkb paramzzbkb)
  {
    return doWrite(zza(new zzp().zze(paramFirebaseApp).zzab(paramzzbkb)));
  }
  
  public Task<AuthResult> zza(@NonNull FirebaseApp paramFirebaseApp, @NonNull AuthCredential paramAuthCredential, @NonNull zzbkb paramzzbkb)
  {
    return doWrite(zza(new zzq(paramAuthCredential).zze(paramFirebaseApp).zzab(paramzzbkb)));
  }
  
  public Task<Void> zza(@NonNull FirebaseApp paramFirebaseApp, @NonNull FirebaseUser paramFirebaseUser, @NonNull AuthCredential paramAuthCredential, @NonNull zzbkb paramzzbkb)
  {
    return doWrite(zza(new zzk(paramAuthCredential).zze(paramFirebaseApp).zze(paramFirebaseUser).zzab(paramzzbkb)));
  }
  
  public Task<Void> zza(@NonNull FirebaseApp paramFirebaseApp, @NonNull FirebaseUser paramFirebaseUser, @NonNull UserProfileChangeRequest paramUserProfileChangeRequest, @NonNull zzbkb paramzzbkb)
  {
    return doWrite(zza(new zzx(paramUserProfileChangeRequest).zze(paramFirebaseApp).zze(paramFirebaseUser).zzab(paramzzbkb)));
  }
  
  public Task<GetTokenResult> zza(@NonNull FirebaseApp paramFirebaseApp, @NonNull FirebaseUser paramFirebaseUser, @NonNull String paramString, @NonNull zzbkb paramzzbkb)
  {
    return doRead(zza(new zzg(paramString).zze(paramFirebaseApp).zze(paramFirebaseUser).zzab(paramzzbkb)));
  }
  
  public Task<Void> zza(@NonNull FirebaseApp paramFirebaseApp, @NonNull FirebaseUser paramFirebaseUser, @NonNull String paramString1, @NonNull String paramString2, @NonNull zzbkb paramzzbkb)
  {
    return doWrite(zza(new zzl(paramString1, paramString2).zze(paramFirebaseApp).zze(paramFirebaseUser).zzab(paramzzbkb)));
  }
  
  public Task<ProviderQueryResult> zza(@NonNull FirebaseApp paramFirebaseApp, @NonNull String paramString)
  {
    return doRead(zza(new zzf(paramString).zze(paramFirebaseApp)));
  }
  
  public Task<AuthResult> zza(@NonNull FirebaseApp paramFirebaseApp, @NonNull String paramString, @NonNull zzbkb paramzzbkb)
  {
    return doWrite(zza(new zzr(paramString).zze(paramFirebaseApp).zzab(paramzzbkb)));
  }
  
  public Task<Void> zza(@NonNull FirebaseApp paramFirebaseApp, @NonNull String paramString1, @NonNull String paramString2)
  {
    return doWrite(zza(new zzc(paramString1, paramString2).zze(paramFirebaseApp)));
  }
  
  public Task<AuthResult> zza(@NonNull FirebaseApp paramFirebaseApp, @NonNull String paramString1, @NonNull String paramString2, @NonNull zzbkb paramzzbkb)
  {
    return doWrite(zza(new zzd(paramString1, paramString2).zze(paramFirebaseApp).zzab(paramzzbkb)));
  }
  
  @NonNull
  public Task<Void> zza(@NonNull FirebaseUser paramFirebaseUser, @NonNull zzbkk paramzzbkk)
  {
    return doWrite(zza(new zze().zze(paramFirebaseUser).zzab(paramzzbkk)));
  }
  
  @NonNull
  public Task<Void> zzb(@NonNull FirebaseApp paramFirebaseApp, @NonNull FirebaseUser paramFirebaseUser, @NonNull zzbkb paramzzbkb)
  {
    return doRead(zza(new zzm().zze(paramFirebaseApp).zze(paramFirebaseUser).zzab(paramzzbkb)));
  }
  
  public Task<AuthResult> zzb(@NonNull FirebaseApp paramFirebaseApp, @NonNull FirebaseUser paramFirebaseUser, @NonNull AuthCredential paramAuthCredential, @NonNull zzbkb paramzzbkb)
  {
    zzac.zzw(paramFirebaseApp);
    zzac.zzw(paramAuthCredential);
    zzac.zzw(paramFirebaseUser);
    zzac.zzw(paramzzbkb);
    if (EmailAuthCredential.class.isAssignableFrom(paramAuthCredential.getClass())) {
      return zza(paramFirebaseApp, (EmailAuthCredential)paramAuthCredential, paramFirebaseUser, paramzzbkb);
    }
    return zza(paramFirebaseApp, paramAuthCredential, paramFirebaseUser, paramzzbkb);
  }
  
  public Task<Void> zzb(@NonNull FirebaseApp paramFirebaseApp, @NonNull FirebaseUser paramFirebaseUser, @NonNull String paramString, @NonNull zzbkb paramzzbkb)
  {
    return doWrite(zza(new zzv(paramString).zze(paramFirebaseApp).zze(paramFirebaseUser).zzab(paramzzbkb)));
  }
  
  public Task<Void> zzb(@NonNull FirebaseApp paramFirebaseApp, @NonNull String paramString)
  {
    return doWrite(zza(new zzo(paramString).zze(paramFirebaseApp)));
  }
  
  public Task<AuthResult> zzb(@NonNull FirebaseApp paramFirebaseApp, @NonNull String paramString1, @NonNull String paramString2, @NonNull zzbkb paramzzbkb)
  {
    return doWrite(zza(new zzs(paramString1, paramString2).zze(paramFirebaseApp).zzab(paramzzbkb)));
  }
  
  public Task<Void> zzc(@NonNull FirebaseApp paramFirebaseApp, @NonNull FirebaseUser paramFirebaseUser, @NonNull String paramString, @NonNull zzbkb paramzzbkb)
  {
    return doWrite(zza(new zzw(paramString).zze(paramFirebaseApp).zze(paramFirebaseUser).zzab(paramzzbkb)));
  }
  
  public Task<Void> zzc(@NonNull FirebaseApp paramFirebaseApp, @NonNull String paramString)
  {
    return doWrite(zza(new zzn(paramString).zze(paramFirebaseApp)));
  }
  
  public Task<AuthResult> zzd(@NonNull FirebaseApp paramFirebaseApp, @NonNull FirebaseUser paramFirebaseUser, @NonNull String paramString, @NonNull zzbkb paramzzbkb)
  {
    zzac.zzw(paramFirebaseApp);
    zzac.zzdv(paramString);
    zzac.zzw(paramFirebaseUser);
    zzac.zzw(paramzzbkb);
    List localList = paramFirebaseUser.getProviders();
    if (((localList != null) && (!localList.contains(paramString))) || (paramFirebaseUser.isAnonymous())) {
      return Tasks.forException(zzbix.zzcb(new Status(17016, paramString)));
    }
    int i = -1;
    switch (paramString.hashCode())
    {
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        return zza(paramFirebaseApp, paramString, paramFirebaseUser, paramzzbkb);
        if (paramString.equals("password")) {
          i = 0;
        }
        break;
      }
    }
    return zza(paramFirebaseApp, paramFirebaseUser, paramzzbkb);
  }
  
  public Task<ActionCodeResult> zzd(@NonNull FirebaseApp paramFirebaseApp, @NonNull String paramString)
  {
    return doWrite(zza(new zzb(paramString).zze(paramFirebaseApp)));
  }
  
  public Task<Void> zze(@NonNull FirebaseApp paramFirebaseApp, @NonNull String paramString)
  {
    return doWrite(zza(new zza(paramString).zze(paramFirebaseApp)));
  }
  
  public Task<String> zzf(@NonNull FirebaseApp paramFirebaseApp, @NonNull String paramString)
  {
    return doWrite(zza(new zzy(paramString).zze(paramFirebaseApp)));
  }
  
  static final class zza
    extends zzbjf<Void, zzbkb>
  {
    @NonNull
    private final String zzaZp;
    
    public zza(@NonNull String paramString)
    {
      super();
      this.zzaZp = zzac.zzh(paramString, "code cannot be null or empty");
    }
    
    public void dispatch()
      throws RemoteException
    {
      this.zzbWc.zzj(this.zzaZp, this.zzbWa);
    }
    
    public void zzUe()
    {
      zzUk();
    }
  }
  
  static final class zzb
    extends zzbjf<ActionCodeResult, zzbkb>
  {
    @NonNull
    private final String zzaZp;
    
    public zzb(@NonNull String paramString)
    {
      super();
      this.zzaZp = zzac.zzh(paramString, "code cannot be null or empty");
    }
    
    public void dispatch()
      throws RemoteException
    {
      this.zzbWc.zzi(this.zzaZp, this.zzbWa);
    }
    
    public void zzUe()
    {
      zzac(new zzbkd(this.zzbWi));
    }
  }
  
  static final class zzc
    extends zzbjf<Void, zzbkb>
  {
    @NonNull
    private final String zzaZp;
    @NonNull
    private final String zzbVN;
    
    public zzc(@NonNull String paramString1, @NonNull String paramString2)
    {
      super();
      this.zzaZp = zzac.zzh(paramString1, "code cannot be null or empty");
      this.zzbVN = zzac.zzh(paramString2, "new password cannot be null or empty");
    }
    
    public void dispatch()
      throws RemoteException
    {
      this.zzbWc.zzf(this.zzaZp, this.zzbVN, this.zzbWa);
    }
    
    public void zzUe()
    {
      zzUk();
    }
  }
  
  static final class zzd
    extends zzbjf<AuthResult, zzbkb>
  {
    @NonNull
    private String zzaiW;
    @NonNull
    private String zzaig;
    
    public zzd(@NonNull String paramString1, @NonNull String paramString2)
    {
      super();
      this.zzaiW = zzac.zzh(paramString1, "email cannot be null or empty");
      this.zzaig = zzac.zzh(paramString2, "password cannot be null or empty");
    }
    
    public void dispatch()
      throws RemoteException
    {
      this.zzbWc.zzc(this.zzaiW, this.zzaig, this.zzbWa);
    }
    
    public void zzUe()
    {
      zzbkh localzzbkh = zzbiu.zzb(this.zzbVx, this.zzbWg);
      ((zzbkb)this.zzbWd).zza(this.zzbWf, localzzbkh);
      zzac(new zzbke(localzzbkh));
    }
  }
  
  static final class zze
    extends zzbjf<Void, zzbkk>
  {
    public zze()
    {
      super();
    }
    
    public void dispatch()
      throws RemoteException
    {
      this.zzbWc.zzg(this.zzbWb.zzTX(), this.zzbWa);
    }
    
    public void zzUe()
    {
      ((zzbkk)this.zzbWd).zzTU();
      zzac(null);
    }
  }
  
  static final class zzf
    extends zzbjf<ProviderQueryResult, zzbkb>
  {
    @NonNull
    private final String zzaiW;
    
    public zzf(@NonNull String paramString)
    {
      super();
      this.zzaiW = zzac.zzh(paramString, "email cannot be null or empty");
    }
    
    public void dispatch()
      throws RemoteException
    {
      this.zzbWc.zzc(this.zzaiW, this.zzbWa);
    }
    
    public void zzUe()
    {
      zzac(new zzbki(this.zzbWh));
    }
  }
  
  static final class zzg
    extends zzbjf<GetTokenResult, zzbkb>
  {
    @NonNull
    private final String zzbVO;
    
    public zzg(@NonNull String paramString)
    {
      super();
      this.zzbVO = zzac.zzh(paramString, "refresh token cannot be null");
    }
    
    public void dispatch()
      throws RemoteException
    {
      this.zzbWc.zza(this.zzbVO, this.zzbWa);
    }
    
    public void zzUe()
    {
      this.zzbWf.zziz(this.zzbVO);
      ((zzbkb)this.zzbWd).zza(this.zzbWf, this.zzbWb);
      zzac(new GetTokenResult(this.zzbWf.getAccessToken()));
    }
  }
  
  static final class zzh
    extends zzbjf<AuthResult, zzbkb>
  {
    @NonNull
    private final EmailAuthCredential zzbVP;
    
    public zzh(@NonNull EmailAuthCredential paramEmailAuthCredential)
    {
      super();
      this.zzbVP = ((EmailAuthCredential)zzac.zzb(paramEmailAuthCredential, "credential cannot be null"));
    }
    
    public void dispatch()
      throws RemoteException
    {
      this.zzbWc.zza(this.zzbVP.getEmail(), this.zzbVP.getPassword(), this.zzbWb.zzTX(), this.zzbWa);
    }
    
    public void zzUe()
    {
      zzbkh localzzbkh = zzbiu.zzb(this.zzbVx, this.zzbWg);
      ((zzbkb)this.zzbWd).zza(this.zzbWf, localzzbkh);
      zzac(new zzbke(localzzbkh));
    }
  }
  
  static final class zzi
    extends zzbjf<AuthResult, zzbkb>
  {
    @NonNull
    private final zzbjz zzbVQ;
    
    public zzi(@NonNull AuthCredential paramAuthCredential)
    {
      super();
      zzac.zzb(paramAuthCredential, "credential cannot be null");
      this.zzbVQ = zzbkc.zza(paramAuthCredential);
    }
    
    public void dispatch()
      throws RemoteException
    {
      this.zzbWc.zza(this.zzbWb.zzTX(), this.zzbVQ, this.zzbWa);
    }
    
    public void zzUe()
    {
      zzbkh localzzbkh = zzbiu.zzb(this.zzbVx, this.zzbWg);
      ((zzbkb)this.zzbWd).zza(this.zzbWf, localzzbkh);
      zzac(new zzbke(localzzbkh));
    }
  }
  
  private static class zzj<ResultT, CallbackT>
    extends zzabn<zzbiv, ResultT>
    implements zzbje<ResultT>
  {
    private TaskCompletionSource<ResultT> zzayo;
    private zzbjf<ResultT, CallbackT> zzbVR;
    
    public zzj(zzbjf<ResultT, CallbackT> paramzzbjf)
    {
      this.zzbVR = paramzzbjf;
      this.zzbVR.zza(this);
    }
    
    protected void zza(zzbiv paramzzbiv, TaskCompletionSource<ResultT> paramTaskCompletionSource)
      throws RemoteException
    {
      this.zzayo = paramTaskCompletionSource;
      this.zzbVR.zza(paramzzbiv.zzUf());
    }
    
    public final void zza(ResultT paramResultT, Status paramStatus)
    {
      zzac.zzb(this.zzayo, "doExecute must be called before onComplete");
      if (paramStatus != null)
      {
        this.zzayo.setException(zzbix.zzcb(paramStatus));
        return;
      }
      this.zzayo.setResult(paramResultT);
    }
  }
  
  static final class zzk
    extends zzbjf<Void, zzbkb>
  {
    @NonNull
    private final zzbjz zzbVQ;
    
    public zzk(@NonNull AuthCredential paramAuthCredential)
    {
      super();
      zzac.zzb(paramAuthCredential, "credential cannot be null");
      this.zzbVQ = zzbkc.zza(paramAuthCredential);
    }
    
    public void dispatch()
      throws RemoteException
    {
      this.zzbWc.zza(this.zzbVQ, this.zzbWa);
    }
    
    public void zzUe()
    {
      zzbkh localzzbkh = zzbiu.zzb(this.zzbVx, this.zzbWg);
      if (this.zzbWb.getUid().equalsIgnoreCase(localzzbkh.getUid()))
      {
        ((zzbkb)this.zzbWd).zza(this.zzbWf, localzzbkh);
        zzUk();
        return;
      }
      zzcc(zzbkj.zzUH());
    }
  }
  
  static final class zzl
    extends zzbjf<Void, zzbkb>
  {
    @NonNull
    private final String zzaiW;
    @NonNull
    private final String zzaig;
    
    public zzl(@NonNull String paramString1, @NonNull String paramString2)
    {
      super();
      this.zzaiW = zzac.zzh(paramString1, "email cannot be null or empty");
      this.zzaig = zzac.zzh(paramString2, "password cannot be null or empty");
    }
    
    public void dispatch()
      throws RemoteException
    {
      this.zzbWc.zzd(this.zzaiW, this.zzaig, this.zzbWa);
    }
    
    public void zzUe()
    {
      zzbkh localzzbkh = zzbiu.zzb(this.zzbVx, this.zzbWg);
      if (this.zzbWb.getUid().equalsIgnoreCase(localzzbkh.getUid()))
      {
        ((zzbkb)this.zzbWd).zza(this.zzbWf, localzzbkh);
        zzUk();
        return;
      }
      zzcc(zzbkj.zzUH());
    }
  }
  
  static final class zzm
    extends zzbjf<Void, zzbkb>
  {
    public zzm()
    {
      super();
    }
    
    public void dispatch()
      throws RemoteException
    {
      this.zzbWc.zzf(this.zzbWb.zzTX(), this.zzbWa);
    }
    
    public void zzUe()
    {
      zzbkh localzzbkh = zzbiu.zzb(this.zzbVx, this.zzbWg, this.zzbWb.isAnonymous());
      ((zzbkb)this.zzbWd).zza(this.zzbWf, localzzbkh);
      zzac(null);
    }
  }
  
  static final class zzn
    extends zzbjf<Void, zzbkb>
  {
    @NonNull
    private String zzahI;
    
    public zzn(@NonNull String paramString)
    {
      super();
      this.zzahI = zzac.zzh(paramString, "token cannot be null or empty");
    }
    
    public void dispatch()
      throws RemoteException
    {
      this.zzbWc.zzh(this.zzahI, this.zzbWa);
    }
    
    public void zzUe()
    {
      zzUk();
    }
  }
  
  static final class zzo
    extends zzbjf<Void, zzbkb>
  {
    @NonNull
    private String zzaiW;
    
    public zzo(@NonNull String paramString)
    {
      super();
      this.zzaiW = zzac.zzh(paramString, "email cannot be null or empty");
    }
    
    public void dispatch()
      throws RemoteException
    {
      this.zzbWc.zzd(this.zzaiW, this.zzbWa);
    }
    
    public void zzUe()
    {
      zzUk();
    }
  }
  
  static final class zzp
    extends zzbjf<AuthResult, zzbkb>
  {
    public zzp()
    {
      super();
    }
    
    public void dispatch()
      throws RemoteException
    {
      this.zzbWc.zza(this.zzbWa);
    }
    
    public void zzUe()
    {
      zzbkh localzzbkh = zzbiu.zzb(this.zzbVx, this.zzbWg, true);
      ((zzbkb)this.zzbWd).zza(this.zzbWf, localzzbkh);
      zzac(new zzbke(localzzbkh));
    }
  }
  
  static final class zzq
    extends zzbjf<AuthResult, zzbkb>
  {
    @NonNull
    private final zzbjz zzbVQ;
    
    public zzq(@NonNull AuthCredential paramAuthCredential)
    {
      super();
      zzac.zzb(paramAuthCredential, "credential cannot be null");
      this.zzbVQ = zzbkc.zza(paramAuthCredential);
    }
    
    public void dispatch()
      throws RemoteException
    {
      this.zzbWc.zza(this.zzbVQ, this.zzbWa);
    }
    
    public void zzUe()
    {
      zzbkh localzzbkh = zzbiu.zzb(this.zzbVx, this.zzbWg);
      ((zzbkb)this.zzbWd).zza(this.zzbWf, localzzbkh);
      zzac(new zzbke(localzzbkh));
    }
  }
  
  static final class zzr
    extends zzbjf<AuthResult, zzbkb>
  {
    @NonNull
    private final String zzahI;
    
    public zzr(@NonNull String paramString)
    {
      super();
      this.zzahI = zzac.zzh(paramString, "token cannot be null or empty");
    }
    
    public void dispatch()
      throws RemoteException
    {
      this.zzbWc.zzb(this.zzahI, this.zzbWa);
    }
    
    public void zzUe()
    {
      zzbkh localzzbkh = zzbiu.zzb(this.zzbVx, this.zzbWg);
      ((zzbkb)this.zzbWd).zza(this.zzbWf, localzzbkh);
      zzac(new zzbke(localzzbkh));
    }
  }
  
  static final class zzs
    extends zzbjf<AuthResult, zzbkb>
  {
    @NonNull
    private String zzaiW;
    @NonNull
    private String zzaig;
    
    public zzs(String paramString1, String paramString2)
    {
      super();
      this.zzaiW = zzac.zzh(paramString1, "email cannot be null or empty");
      this.zzaig = zzac.zzh(paramString2, "password cannot be null or empty");
    }
    
    public void dispatch()
      throws RemoteException
    {
      this.zzbWc.zzd(this.zzaiW, this.zzaig, this.zzbWa);
    }
    
    public void zzUe()
    {
      zzbkh localzzbkh = zzbiu.zzb(this.zzbVx, this.zzbWg);
      ((zzbkb)this.zzbWd).zza(this.zzbWf, localzzbkh);
      zzac(new zzbke(localzzbkh));
    }
  }
  
  static final class zzt
    extends zzbjf<AuthResult, zzbkb>
  {
    public zzt()
    {
      super();
    }
    
    public void dispatch()
      throws RemoteException
    {
      this.zzbWc.zze(this.zzbWb.zzTX(), this.zzbWa);
    }
    
    public void zzUe()
    {
      zzbkh localzzbkh = zzbiu.zzb(this.zzbVx, this.zzbWg);
      ((zzbkb)this.zzbWd).zza(this.zzbWf, localzzbkh);
      zzac(new zzbke(localzzbkh));
    }
  }
  
  static final class zzu
    extends zzbjf<AuthResult, zzbkb>
  {
    @NonNull
    private String zzbVS;
    
    public zzu(@NonNull String paramString)
    {
      super();
      this.zzbVS = zzac.zzh(paramString, "provider cannot be null or empty");
    }
    
    public void dispatch()
      throws RemoteException
    {
      this.zzbWc.zze(this.zzbVS, this.zzbWb.zzTX(), this.zzbWa);
    }
    
    public void zzUe()
    {
      zzbkh localzzbkh = zzbiu.zzb(this.zzbVx, this.zzbWg);
      ((zzbkb)this.zzbWd).zza(this.zzbWf, localzzbkh);
      zzac(new zzbke(localzzbkh));
    }
  }
  
  static final class zzv
    extends zzbjf<Void, zzbkb>
  {
    @NonNull
    private final String zzaiW;
    
    public zzv(String paramString)
    {
      super();
      this.zzaiW = zzac.zzh(paramString, "email cannot be null or empty");
    }
    
    public void dispatch()
      throws RemoteException
    {
      this.zzbWc.zza(this.zzbWb.zzTX(), this.zzaiW, this.zzbWa);
    }
    
    public void zzUe()
    {
      ((zzbkb)this.zzbWd).zza(this.zzbWf, zzbiu.zzb(this.zzbVx, this.zzbWg));
      zzUk();
    }
  }
  
  static final class zzw
    extends zzbjf<Void, zzbkb>
  {
    @NonNull
    private final String zzaig;
    
    public zzw(@NonNull String paramString)
    {
      super();
      this.zzaig = zzac.zzh(paramString, "password cannot be null or empty");
    }
    
    public void dispatch()
      throws RemoteException
    {
      this.zzbWc.zzb(this.zzbWb.zzTX(), this.zzaig, this.zzbWa);
    }
    
    public void zzUe()
    {
      ((zzbkb)this.zzbWd).zza(this.zzbWf, zzbiu.zzb(this.zzbVx, this.zzbWg));
      zzUk();
    }
  }
  
  static final class zzx
    extends zzbjf<Void, zzbkb>
  {
    @NonNull
    private final UserProfileChangeRequest zzbVT;
    
    public zzx(UserProfileChangeRequest paramUserProfileChangeRequest)
    {
      super();
      this.zzbVT = ((UserProfileChangeRequest)zzac.zzb(paramUserProfileChangeRequest, "request cannot be null"));
    }
    
    public void dispatch()
      throws RemoteException
    {
      this.zzbWc.zza(this.zzbWb.zzTX(), this.zzbVT, this.zzbWa);
    }
    
    public void zzUe()
    {
      ((zzbkb)this.zzbWd).zza(this.zzbWf, zzbiu.zzb(this.zzbVx, this.zzbWg));
      zzUk();
    }
  }
  
  static final class zzy
    extends zzbjf<String, zzbkb>
  {
    @NonNull
    private final String zzaZp;
    
    public zzy(@NonNull String paramString)
    {
      super();
      this.zzaZp = zzac.zzh(paramString, "code cannot be null or empty");
    }
    
    public void dispatch()
      throws RemoteException
    {
      this.zzbWc.zzi(this.zzaZp, this.zzbWa);
    }
    
    public void zzUe()
    {
      if (new zzbkd(this.zzbWi).getOperation() != 0)
      {
        zzcc(new Status(17499));
        return;
      }
      zzac(this.zzbWi.getEmail());
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbiu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */