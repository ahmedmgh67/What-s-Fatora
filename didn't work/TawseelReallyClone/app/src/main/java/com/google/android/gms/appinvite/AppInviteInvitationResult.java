package com.google.android.gms.appinvite;

import android.content.Intent;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public abstract interface AppInviteInvitationResult
  extends Result
{
  public abstract Intent getInvitationIntent();
  
  public abstract Status getStatus();
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\appinvite\AppInviteInvitationResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */