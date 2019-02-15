package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzac;
import com.google.firebase.auth.ProviderQueryResult;
import java.util.List;

public class zzbki
  implements ProviderQueryResult
{
  private List<String> zzbWP;
  
  public zzbki(@NonNull zzbjj paramzzbjj)
  {
    zzac.zzw(paramzzbjj);
    this.zzbWP = paramzzbjj.getAllProviders();
  }
  
  @Nullable
  public List<String> getProviders()
  {
    return this.zzbWP;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbki.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */