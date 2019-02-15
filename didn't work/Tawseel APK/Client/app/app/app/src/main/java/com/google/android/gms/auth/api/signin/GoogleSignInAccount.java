package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzh;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleSignInAccount
  extends com.google.android.gms.common.internal.safeparcel.zza
  implements ReflectedParcelable
{
  public static final Parcelable.Creator<GoogleSignInAccount> CREATOR = new zza();
  public static zze zzaiV = zzh.zzyv();
  private static Comparator<Scope> zzajc = new Comparator()
  {
    public int zza(Scope paramAnonymousScope1, Scope paramAnonymousScope2)
    {
      return paramAnonymousScope1.zzuS().compareTo(paramAnonymousScope2.zzuS());
    }
  };
  final int versionCode;
  private String zzGu;
  List<Scope> zzahM;
  private String zzaiW;
  private String zzaiX;
  private Uri zzaiY;
  private String zzaiZ;
  private String zzaik;
  private String zzail;
  private String zzaix;
  private long zzaja;
  private String zzajb;
  
  GoogleSignInAccount(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, Uri paramUri, String paramString5, long paramLong, String paramString6, List<Scope> paramList, String paramString7, String paramString8)
  {
    this.versionCode = paramInt;
    this.zzGu = paramString1;
    this.zzaix = paramString2;
    this.zzaiW = paramString3;
    this.zzaiX = paramString4;
    this.zzaiY = paramUri;
    this.zzaiZ = paramString5;
    this.zzaja = paramLong;
    this.zzajb = paramString6;
    this.zzahM = paramList;
    this.zzaik = paramString7;
    this.zzail = paramString8;
  }
  
  public static GoogleSignInAccount zza(@Nullable String paramString1, @Nullable String paramString2, @Nullable String paramString3, @Nullable String paramString4, @Nullable String paramString5, @Nullable String paramString6, @Nullable Uri paramUri, @Nullable Long paramLong, @NonNull String paramString7, @NonNull Set<Scope> paramSet)
  {
    Long localLong = paramLong;
    if (paramLong == null) {
      localLong = Long.valueOf(zzaiV.currentTimeMillis() / 1000L);
    }
    return new GoogleSignInAccount(3, paramString1, paramString2, paramString3, paramString4, paramUri, null, localLong.longValue(), zzac.zzdv(paramString7), new ArrayList((Collection)zzac.zzw(paramSet)), paramString5, paramString6);
  }
  
  @Nullable
  public static GoogleSignInAccount zzcu(@Nullable String paramString)
    throws JSONException
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    JSONObject localJSONObject = new JSONObject(paramString);
    paramString = localJSONObject.optString("photoUrl", null);
    if (!TextUtils.isEmpty(paramString)) {}
    for (paramString = Uri.parse(paramString);; paramString = null)
    {
      long l = Long.parseLong(localJSONObject.getString("expirationTime"));
      HashSet localHashSet = new HashSet();
      JSONArray localJSONArray = localJSONObject.getJSONArray("grantedScopes");
      int j = localJSONArray.length();
      int i = 0;
      while (i < j)
      {
        localHashSet.add(new Scope(localJSONArray.getString(i)));
        i += 1;
      }
      return zza(localJSONObject.optString("id"), localJSONObject.optString("tokenId", null), localJSONObject.optString("email", null), localJSONObject.optString("displayName", null), localJSONObject.optString("givenName", null), localJSONObject.optString("familyName", null), paramString, Long.valueOf(l), localJSONObject.getString("obfuscatedIdentifier"), localHashSet).zzcv(localJSONObject.optString("serverAuthCode", null));
    }
  }
  
  private JSONObject zzqI()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      if (getId() != null) {
        localJSONObject.put("id", getId());
      }
      if (getIdToken() != null) {
        localJSONObject.put("tokenId", getIdToken());
      }
      if (getEmail() != null) {
        localJSONObject.put("email", getEmail());
      }
      if (getDisplayName() != null) {
        localJSONObject.put("displayName", getDisplayName());
      }
      if (getGivenName() != null) {
        localJSONObject.put("givenName", getGivenName());
      }
      if (getFamilyName() != null) {
        localJSONObject.put("familyName", getFamilyName());
      }
      if (getPhotoUrl() != null) {
        localJSONObject.put("photoUrl", getPhotoUrl().toString());
      }
      if (getServerAuthCode() != null) {
        localJSONObject.put("serverAuthCode", getServerAuthCode());
      }
      localJSONObject.put("expirationTime", this.zzaja);
      localJSONObject.put("obfuscatedIdentifier", zzqF());
      JSONArray localJSONArray = new JSONArray();
      Collections.sort(this.zzahM, zzajc);
      Iterator localIterator = this.zzahM.iterator();
      while (localIterator.hasNext()) {
        localJSONArray.put(((Scope)localIterator.next()).zzuS());
      }
      localJSONException.put("grantedScopes", localJSONArray);
    }
    catch (JSONException localJSONException)
    {
      throw new RuntimeException(localJSONException);
    }
    return localJSONException;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof GoogleSignInAccount)) {
      return false;
    }
    return ((GoogleSignInAccount)paramObject).zzqG().equals(zzqG());
  }
  
  @Nullable
  public Account getAccount()
  {
    if (this.zzaiW == null) {
      return null;
    }
    return new Account(this.zzaiW, "com.google");
  }
  
  @Nullable
  public String getDisplayName()
  {
    return this.zzaiX;
  }
  
  @Nullable
  public String getEmail()
  {
    return this.zzaiW;
  }
  
  @Nullable
  public String getFamilyName()
  {
    return this.zzail;
  }
  
  @Nullable
  public String getGivenName()
  {
    return this.zzaik;
  }
  
  @NonNull
  public Set<Scope> getGrantedScopes()
  {
    return new HashSet(this.zzahM);
  }
  
  @Nullable
  public String getId()
  {
    return this.zzGu;
  }
  
  @Nullable
  public String getIdToken()
  {
    return this.zzaix;
  }
  
  @Nullable
  public Uri getPhotoUrl()
  {
    return this.zzaiY;
  }
  
  @Nullable
  public String getServerAuthCode()
  {
    return this.zzaiZ;
  }
  
  public int hashCode()
  {
    return zzqG().hashCode();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza.zza(this, paramParcel, paramInt);
  }
  
  public boolean zza()
  {
    return zzaiV.currentTimeMillis() / 1000L >= this.zzaja - 300L;
  }
  
  public GoogleSignInAccount zzcv(String paramString)
  {
    this.zzaiZ = paramString;
    return this;
  }
  
  public long zzqE()
  {
    return this.zzaja;
  }
  
  @NonNull
  public String zzqF()
  {
    return this.zzajb;
  }
  
  public String zzqG()
  {
    return zzqI().toString();
  }
  
  public String zzqH()
  {
    JSONObject localJSONObject = zzqI();
    localJSONObject.remove("serverAuthCode");
    return localJSONObject.toString();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\auth\api\signin\GoogleSignInAccount.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */