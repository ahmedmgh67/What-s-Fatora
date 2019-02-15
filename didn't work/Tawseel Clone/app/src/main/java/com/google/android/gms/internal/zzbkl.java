package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzac;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;
import java.util.ArrayList;
import java.util.List;

public class zzbkl
{
  private Context mContext;
  private SharedPreferences zzAN;
  private zzbrl zzbVW;
  private String zzbWT;
  private zzbrw zzbWU;
  
  public zzbkl(@NonNull Context paramContext, @NonNull String paramString, @NonNull zzbrl paramzzbrl)
  {
    zzac.zzw(paramContext);
    this.zzbWT = zzac.zzdv(paramString);
    this.mContext = paramContext.getApplicationContext();
    paramContext = String.format("com.google.firebase.auth.api.Store.%s", new Object[] { this.zzbWT });
    this.zzbVW = ((zzbrl)zzac.zzw(paramzzbrl));
    this.zzbWU = new zzbrw();
    this.zzAN = this.mContext.getSharedPreferences(paramContext, 0);
  }
  
  private zzbkh zza(@NonNull zzbru paramzzbru)
  {
    String str1 = paramzzbru.zzjS("cachedTokenState").zzabu();
    String str2 = paramzzbru.zzjS("applicationName").zzabu();
    boolean bool = paramzzbru.zzjS("anonymous").getAsBoolean();
    Object localObject = paramzzbru.zzjS("version");
    if ((localObject != null) && (!((zzbrr)localObject).zzaby())) {}
    for (localObject = ((zzbrr)localObject).zzabu();; localObject = "2")
    {
      paramzzbru = paramzzbru.zzjT("userInfos");
      int j = paramzzbru.size();
      ArrayList localArrayList = new ArrayList(j);
      int i = 0;
      while (i < j)
      {
        localArrayList.add((zzbkf)this.zzbVW.zza(paramzzbru.zzqc(i), zzbkf.class));
        i += 1;
      }
      paramzzbru = new zzbkh(FirebaseApp.getInstance(str2), localArrayList);
      if (!TextUtils.isEmpty(str1)) {
        paramzzbru.zza((zzbjp)this.zzbVW.zzf(str1, zzbjp.class));
      }
      ((zzbkh)paramzzbru.zzaT(bool)).zziA((String)localObject);
      return paramzzbru;
    }
  }
  
  @Nullable
  private String zzi(@NonNull FirebaseUser paramFirebaseUser)
  {
    zzbru localzzbru = new zzbru();
    if (zzbkh.class.isAssignableFrom(paramFirebaseUser.getClass()))
    {
      paramFirebaseUser = (zzbkh)paramFirebaseUser;
      localzzbru.zzaB("cachedTokenState", paramFirebaseUser.zzTX());
      localzzbru.zzaB("applicationName", paramFirebaseUser.zzTV().getName());
      localzzbru.zzaB("type", "com.google.firebase.auth.internal.DefaultFirebaseUser");
      if (paramFirebaseUser.zzUG() != null)
      {
        zzbro localzzbro = new zzbro();
        List localList = paramFirebaseUser.zzUG();
        int i = 0;
        while (i < localList.size())
        {
          zzbkf localzzbkf = (zzbkf)localList.get(i);
          localzzbro.zzc(zziB(this.zzbVW.zzaI(localzzbkf)));
          i += 1;
        }
        localzzbru.zza("userInfos", localzzbro);
      }
      localzzbru.zzb("anonymous", Boolean.valueOf(paramFirebaseUser.isAnonymous()));
      localzzbru.zzaB("version", "2");
      return localzzbru.toString();
    }
    return null;
  }
  
  private static zzbrr zziB(String paramString)
  {
    return new zzbrw().zzjU(paramString);
  }
  
  public void clear(String paramString)
  {
    this.zzAN.edit().remove(paramString).apply();
  }
  
  @Nullable
  public String get(String paramString)
  {
    return this.zzAN.getString(paramString, null);
  }
  
  @Nullable
  public FirebaseUser zzUI()
  {
    Object localObject = get("com.google.firebase.auth.FIREBASE_USER");
    if (TextUtils.isEmpty((CharSequence)localObject)) {}
    for (;;)
    {
      return null;
      try
      {
        localObject = this.zzbWU.zzjU((String)localObject).zzabz();
        if ((((zzbru)localObject).has("type")) && ("com.google.firebase.auth.internal.DefaultFirebaseUser".equalsIgnoreCase(((zzbru)localObject).zzjS("type").zzabu())))
        {
          localObject = zza((zzbru)localObject);
          return (FirebaseUser)localObject;
        }
      }
      catch (zzbsa localzzbsa) {}
    }
    return null;
  }
  
  public void zzUJ()
  {
    clear("com.google.firebase.auth.FIREBASE_USER");
  }
  
  public void zza(@NonNull FirebaseUser paramFirebaseUser, @NonNull zzbjp paramzzbjp)
  {
    zzac.zzw(paramFirebaseUser);
    zzac.zzw(paramzzbjp);
    zzp(String.format("com.google.firebase.auth.GET_TOKEN_RESPONSE.%s", new Object[] { paramFirebaseUser.getUid() }), paramzzbjp);
  }
  
  public void zzak(String paramString1, String paramString2)
  {
    this.zzAN.edit().putString(paramString1, paramString2).apply();
  }
  
  @Nullable
  public <T> T zze(String paramString, Class<T> paramClass)
  {
    paramString = get(paramString);
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    return (T)this.zzbVW.zzf(paramString, paramClass);
  }
  
  public void zzf(@NonNull FirebaseUser paramFirebaseUser)
  {
    zzac.zzw(paramFirebaseUser);
    paramFirebaseUser = zzi(paramFirebaseUser);
    if (!TextUtils.isEmpty(paramFirebaseUser)) {
      zzak("com.google.firebase.auth.FIREBASE_USER", paramFirebaseUser);
    }
  }
  
  public zzbjp zzg(@NonNull FirebaseUser paramFirebaseUser)
  {
    zzac.zzw(paramFirebaseUser);
    return (zzbjp)zze(String.format("com.google.firebase.auth.GET_TOKEN_RESPONSE.%s", new Object[] { paramFirebaseUser.getUid() }), zzbjp.class);
  }
  
  public void zzh(@NonNull FirebaseUser paramFirebaseUser)
  {
    zzac.zzw(paramFirebaseUser);
    clear(String.format("com.google.firebase.auth.GET_TOKEN_RESPONSE.%s", new Object[] { paramFirebaseUser.getUid() }));
  }
  
  public void zzp(String paramString, Object paramObject)
  {
    this.zzAN.edit().putString(paramString, this.zzbVW.zzaI(paramObject)).apply();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbkl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */