package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.internal.zzf;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzac;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleSignInOptions
  extends zza
  implements Api.ApiOptions.Optional, ReflectedParcelable
{
  public static final Parcelable.Creator<GoogleSignInOptions> CREATOR = new zzb();
  public static final GoogleSignInOptions DEFAULT_SIGN_IN;
  private static Comparator<Scope> zzajc = new Comparator()
  {
    public int zza(Scope paramAnonymousScope1, Scope paramAnonymousScope2)
    {
      return paramAnonymousScope1.zzuS().compareTo(paramAnonymousScope2.zzuS());
    }
  };
  public static final Scope zzajd = new Scope("profile");
  public static final Scope zzaje = new Scope("email");
  public static final Scope zzajf = new Scope("openid");
  final int versionCode;
  private Account zzagg;
  private final ArrayList<Scope> zzajg;
  private boolean zzajh;
  private final boolean zzaji;
  private final boolean zzajj;
  private String zzajk;
  private String zzajl;
  
  static
  {
    DEFAULT_SIGN_IN = new Builder().requestId().requestProfile().build();
  }
  
  GoogleSignInOptions(int paramInt, ArrayList<Scope> paramArrayList, Account paramAccount, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString1, String paramString2)
  {
    this.versionCode = paramInt;
    this.zzajg = paramArrayList;
    this.zzagg = paramAccount;
    this.zzajh = paramBoolean1;
    this.zzaji = paramBoolean2;
    this.zzajj = paramBoolean3;
    this.zzajk = paramString1;
    this.zzajl = paramString2;
  }
  
  private GoogleSignInOptions(Set<Scope> paramSet, Account paramAccount, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString1, String paramString2)
  {
    this(2, new ArrayList(paramSet), paramAccount, paramBoolean1, paramBoolean2, paramBoolean3, paramString1, paramString2);
  }
  
  @Nullable
  public static GoogleSignInOptions zzcw(@Nullable String paramString)
    throws JSONException
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    JSONObject localJSONObject = new JSONObject(paramString);
    HashSet localHashSet = new HashSet();
    paramString = localJSONObject.getJSONArray("scopes");
    int j = paramString.length();
    int i = 0;
    while (i < j)
    {
      localHashSet.add(new Scope(paramString.getString(i)));
      i += 1;
    }
    paramString = localJSONObject.optString("accountName", null);
    if (!TextUtils.isEmpty(paramString)) {}
    for (paramString = new Account(paramString, "com.google");; paramString = null) {
      return new GoogleSignInOptions(localHashSet, paramString, localJSONObject.getBoolean("idTokenRequested"), localJSONObject.getBoolean("serverAuthRequested"), localJSONObject.getBoolean("forceCodeForRefreshToken"), localJSONObject.optString("serverClientId", null), localJSONObject.optString("hostedDomain", null));
    }
  }
  
  private JSONObject zzqI()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      JSONArray localJSONArray = new JSONArray();
      Collections.sort(this.zzajg, zzajc);
      Iterator localIterator = this.zzajg.iterator();
      while (localIterator.hasNext()) {
        localJSONArray.put(((Scope)localIterator.next()).zzuS());
      }
      localJSONException.put("scopes", localJSONArray);
    }
    catch (JSONException localJSONException)
    {
      throw new RuntimeException(localJSONException);
    }
    if (this.zzagg != null) {
      localJSONException.put("accountName", this.zzagg.name);
    }
    localJSONException.put("idTokenRequested", this.zzajh);
    localJSONException.put("forceCodeForRefreshToken", this.zzajj);
    localJSONException.put("serverAuthRequested", this.zzaji);
    if (!TextUtils.isEmpty(this.zzajk)) {
      localJSONException.put("serverClientId", this.zzajk);
    }
    if (!TextUtils.isEmpty(this.zzajl)) {
      localJSONException.put("hostedDomain", this.zzajl);
    }
    return localJSONException;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {}
    for (;;)
    {
      return false;
      try
      {
        paramObject = (GoogleSignInOptions)paramObject;
        if ((this.zzajg.size() != ((GoogleSignInOptions)paramObject).zzqJ().size()) || (!this.zzajg.containsAll(((GoogleSignInOptions)paramObject).zzqJ()))) {
          continue;
        }
        if (this.zzagg == null)
        {
          if (((GoogleSignInOptions)paramObject).getAccount() != null) {
            continue;
          }
          label56:
          if (!TextUtils.isEmpty(this.zzajk)) {
            break label128;
          }
          if (!TextUtils.isEmpty(((GoogleSignInOptions)paramObject).zzqN())) {
            continue;
          }
        }
        while ((this.zzajj == ((GoogleSignInOptions)paramObject).zzqM()) && (this.zzajh == ((GoogleSignInOptions)paramObject).zzqK()) && (this.zzaji == ((GoogleSignInOptions)paramObject).zzqL()))
        {
          return true;
          if (!this.zzagg.equals(((GoogleSignInOptions)paramObject).getAccount())) {
            break;
          }
          break label56;
          label128:
          boolean bool = this.zzajk.equals(((GoogleSignInOptions)paramObject).zzqN());
          if (!bool) {
            break;
          }
        }
        return false;
      }
      catch (ClassCastException paramObject) {}
    }
  }
  
  public Account getAccount()
  {
    return this.zzagg;
  }
  
  public Scope[] getScopeArray()
  {
    return (Scope[])this.zzajg.toArray(new Scope[this.zzajg.size()]);
  }
  
  public int hashCode()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.zzajg.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(((Scope)localIterator.next()).zzuS());
    }
    Collections.sort(localArrayList);
    return new zzf().zzq(localArrayList).zzq(this.zzagg).zzq(this.zzajk).zzad(this.zzajj).zzad(this.zzajh).zzad(this.zzaji).zzqV();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb.zza(this, paramParcel, paramInt);
  }
  
  public String zzqG()
  {
    return zzqI().toString();
  }
  
  public ArrayList<Scope> zzqJ()
  {
    return new ArrayList(this.zzajg);
  }
  
  public boolean zzqK()
  {
    return this.zzajh;
  }
  
  public boolean zzqL()
  {
    return this.zzaji;
  }
  
  public boolean zzqM()
  {
    return this.zzajj;
  }
  
  public String zzqN()
  {
    return this.zzajk;
  }
  
  public String zzqO()
  {
    return this.zzajl;
  }
  
  public static final class Builder
  {
    private Account zzagg;
    private boolean zzajh;
    private boolean zzaji;
    private boolean zzajj;
    private String zzajk;
    private String zzajl;
    private Set<Scope> zzajm = new HashSet();
    
    public Builder() {}
    
    public Builder(@NonNull GoogleSignInOptions paramGoogleSignInOptions)
    {
      zzac.zzw(paramGoogleSignInOptions);
      this.zzajm = new HashSet(GoogleSignInOptions.zzb(paramGoogleSignInOptions));
      this.zzaji = GoogleSignInOptions.zzc(paramGoogleSignInOptions);
      this.zzajj = GoogleSignInOptions.zzd(paramGoogleSignInOptions);
      this.zzajh = GoogleSignInOptions.zze(paramGoogleSignInOptions);
      this.zzajk = GoogleSignInOptions.zzf(paramGoogleSignInOptions);
      this.zzagg = GoogleSignInOptions.zzg(paramGoogleSignInOptions);
      this.zzajl = GoogleSignInOptions.zzh(paramGoogleSignInOptions);
    }
    
    private String zzcx(String paramString)
    {
      zzac.zzdv(paramString);
      if ((this.zzajk == null) || (this.zzajk.equals(paramString))) {}
      for (boolean bool = true;; bool = false)
      {
        zzac.zzb(bool, "two different server client ids provided");
        return paramString;
      }
    }
    
    public GoogleSignInOptions build()
    {
      if ((this.zzajh) && ((this.zzagg == null) || (!this.zzajm.isEmpty()))) {
        requestId();
      }
      return new GoogleSignInOptions(this.zzajm, this.zzagg, this.zzajh, this.zzaji, this.zzajj, this.zzajk, this.zzajl, null);
    }
    
    public Builder requestEmail()
    {
      this.zzajm.add(GoogleSignInOptions.zzaje);
      return this;
    }
    
    public Builder requestId()
    {
      this.zzajm.add(GoogleSignInOptions.zzajf);
      return this;
    }
    
    public Builder requestIdToken(String paramString)
    {
      this.zzajh = true;
      this.zzajk = zzcx(paramString);
      return this;
    }
    
    public Builder requestProfile()
    {
      this.zzajm.add(GoogleSignInOptions.zzajd);
      return this;
    }
    
    public Builder requestScopes(Scope paramScope, Scope... paramVarArgs)
    {
      this.zzajm.add(paramScope);
      this.zzajm.addAll(Arrays.asList(paramVarArgs));
      return this;
    }
    
    public Builder requestServerAuthCode(String paramString)
    {
      return requestServerAuthCode(paramString, false);
    }
    
    public Builder requestServerAuthCode(String paramString, boolean paramBoolean)
    {
      this.zzaji = true;
      this.zzajk = zzcx(paramString);
      this.zzajj = paramBoolean;
      return this;
    }
    
    public Builder setAccountName(String paramString)
    {
      this.zzagg = new Account(zzac.zzdv(paramString), "com.google");
      return this;
    }
    
    public Builder setHostedDomain(String paramString)
    {
      this.zzajl = zzac.zzdv(paramString);
      return this;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\auth\api\signin\GoogleSignInOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */