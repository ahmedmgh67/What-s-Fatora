package com.google.android.gms.internal;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.internal.zzac;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class zzbkh
  extends FirebaseUser
{
  private zzbrl zzbVW;
  private zzbjp zzbWK;
  private zzbkf zzbWL;
  private String zzbWM;
  private String zzbWN;
  private List<zzbkf> zzbWO;
  private List<String> zzbWP;
  private Map<String, zzbkf> zzbWQ;
  private String zzbWR;
  private boolean zzbWS;
  
  public zzbkh(@NonNull FirebaseApp paramFirebaseApp, @NonNull List<? extends UserInfo> paramList)
  {
    zzac.zzw(paramFirebaseApp);
    this.zzbWM = paramFirebaseApp.getName();
    this.zzbVW = zzbiz.zzUg();
    this.zzbWN = "com.google.firebase.auth.internal.DefaultFirebaseUser";
    this.zzbWR = "2";
    zzR(paramList);
  }
  
  @Nullable
  public String getDisplayName()
  {
    return this.zzbWL.getDisplayName();
  }
  
  @Nullable
  public String getEmail()
  {
    return this.zzbWL.getEmail();
  }
  
  @Nullable
  public Uri getPhotoUrl()
  {
    return this.zzbWL.getPhotoUrl();
  }
  
  @NonNull
  public List<? extends UserInfo> getProviderData()
  {
    return this.zzbWO;
  }
  
  @NonNull
  public String getProviderId()
  {
    return this.zzbWL.getProviderId();
  }
  
  @Nullable
  public List<String> getProviders()
  {
    return this.zzbWP;
  }
  
  @NonNull
  public String getUid()
  {
    return this.zzbWL.getUid();
  }
  
  public boolean isAnonymous()
  {
    return this.zzbWS;
  }
  
  public boolean isEmailVerified()
  {
    return this.zzbWL.isEmailVerified();
  }
  
  @NonNull
  public FirebaseUser zzR(@NonNull List<? extends UserInfo> paramList)
  {
    zzac.zzw(paramList);
    this.zzbWO = new ArrayList(paramList.size());
    this.zzbWP = new ArrayList(paramList.size());
    this.zzbWQ = new ArrayMap();
    int i = 0;
    if (i < paramList.size())
    {
      UserInfo localUserInfo = (UserInfo)paramList.get(i);
      if (localUserInfo.getProviderId().equals("firebase")) {
        this.zzbWL = ((zzbkf)localUserInfo);
      }
      for (;;)
      {
        this.zzbWO.add((zzbkf)localUserInfo);
        this.zzbWQ.put(localUserInfo.getProviderId(), (zzbkf)localUserInfo);
        i += 1;
        break;
        this.zzbWP.add(localUserInfo.getProviderId());
      }
    }
    if (this.zzbWL == null) {
      this.zzbWL = ((zzbkf)this.zzbWO.get(0));
    }
    return this;
  }
  
  @NonNull
  public FirebaseApp zzTV()
  {
    return FirebaseApp.getInstance(this.zzbWM);
  }
  
  @NonNull
  public zzbjp zzTW()
  {
    return this.zzbWK;
  }
  
  @NonNull
  public String zzTX()
  {
    return this.zzbVW.zzaI(this.zzbWK);
  }
  
  @NonNull
  public String zzTY()
  {
    return zzTW().getAccessToken();
  }
  
  public List<zzbkf> zzUG()
  {
    return this.zzbWO;
  }
  
  public void zza(@NonNull zzbjp paramzzbjp)
  {
    this.zzbWK = ((zzbjp)zzac.zzw(paramzzbjp));
  }
  
  public zzbkh zzaU(boolean paramBoolean)
  {
    this.zzbWS = paramBoolean;
    return this;
  }
  
  public zzbkh zziA(@NonNull String paramString)
  {
    this.zzbWR = paramString;
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbkh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */