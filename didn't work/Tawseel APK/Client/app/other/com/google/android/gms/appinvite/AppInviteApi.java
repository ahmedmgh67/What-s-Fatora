package com.google.android.gms.appinvite;

import android.app.Activity;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

public abstract interface AppInviteApi
{
  public abstract PendingResult<Status> convertInvitation(@NonNull GoogleApiClient paramGoogleApiClient, String paramString);
  
  public abstract PendingResult<AppInviteInvitationResult> getInvitation(@NonNull GoogleApiClient paramGoogleApiClient, Activity paramActivity, boolean paramBoolean);
  
  @Deprecated
  public abstract PendingResult<Status> updateInvitationOnInstall(@NonNull GoogleApiClient paramGoogleApiClient, String paramString);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\appinvite\AppInviteApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */