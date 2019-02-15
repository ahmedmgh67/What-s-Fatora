package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.view.View;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzaxo;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class zzg
{
  private final Set<Scope> zzaEb;
  private final Map<Api<?>, zza> zzaEc;
  private final zzaxo zzaEd;
  private Integer zzaEe;
  private final Account zzagg;
  private final String zzahp;
  private final Set<Scope> zzaxN;
  private final int zzaxP;
  private final View zzaxQ;
  private final String zzaxR;
  
  public zzg(Account paramAccount, Set<Scope> paramSet, Map<Api<?>, zza> paramMap, int paramInt, View paramView, String paramString1, String paramString2, zzaxo paramzzaxo)
  {
    this.zzagg = paramAccount;
    if (paramSet == null) {}
    for (paramAccount = Collections.EMPTY_SET;; paramAccount = Collections.unmodifiableSet(paramSet))
    {
      this.zzaxN = paramAccount;
      paramAccount = paramMap;
      if (paramMap == null) {
        paramAccount = Collections.EMPTY_MAP;
      }
      this.zzaEc = paramAccount;
      this.zzaxQ = paramView;
      this.zzaxP = paramInt;
      this.zzahp = paramString1;
      this.zzaxR = paramString2;
      this.zzaEd = paramzzaxo;
      paramAccount = new HashSet(this.zzaxN);
      paramSet = this.zzaEc.values().iterator();
      while (paramSet.hasNext()) {
        paramAccount.addAll(((zza)paramSet.next()).zzajm);
      }
    }
    this.zzaEb = Collections.unmodifiableSet(paramAccount);
  }
  
  public static zzg zzaA(Context paramContext)
  {
    return new GoogleApiClient.Builder(paramContext).zzuP();
  }
  
  public Account getAccount()
  {
    return this.zzagg;
  }
  
  @Deprecated
  public String getAccountName()
  {
    if (this.zzagg != null) {
      return this.zzagg.name;
    }
    return null;
  }
  
  public Set<Scope> zzc(Api<?> paramApi)
  {
    paramApi = (zza)this.zzaEc.get(paramApi);
    if ((paramApi == null) || (paramApi.zzajm.isEmpty())) {
      return this.zzaxN;
    }
    HashSet localHashSet = new HashSet(this.zzaxN);
    localHashSet.addAll(paramApi.zzajm);
    return localHashSet;
  }
  
  public void zzc(Integer paramInteger)
  {
    this.zzaEe = paramInteger;
  }
  
  public Account zzwU()
  {
    if (this.zzagg != null) {
      return this.zzagg;
    }
    return new Account("<<default account>>", "com.google");
  }
  
  public int zzxd()
  {
    return this.zzaxP;
  }
  
  public Set<Scope> zzxe()
  {
    return this.zzaxN;
  }
  
  public Set<Scope> zzxf()
  {
    return this.zzaEb;
  }
  
  public Map<Api<?>, zza> zzxg()
  {
    return this.zzaEc;
  }
  
  public String zzxh()
  {
    return this.zzahp;
  }
  
  public String zzxi()
  {
    return this.zzaxR;
  }
  
  public View zzxj()
  {
    return this.zzaxQ;
  }
  
  public zzaxo zzxk()
  {
    return this.zzaEd;
  }
  
  public Integer zzxl()
  {
    return this.zzaEe;
  }
  
  public static final class zza
  {
    public final boolean zzaEf;
    public final Set<Scope> zzajm;
    
    public zza(Set<Scope> paramSet, boolean paramBoolean)
    {
      zzac.zzw(paramSet);
      this.zzajm = Collections.unmodifiableSet(paramSet);
      this.zzaEf = paramBoolean;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\common\internal\zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */