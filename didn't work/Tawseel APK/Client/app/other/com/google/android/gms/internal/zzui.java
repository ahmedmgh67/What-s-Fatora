package com.google.android.gms.internal;

import android.content.Intent;
import com.google.android.gms.appinvite.AppInviteInvitationResult;
import com.google.android.gms.common.api.Status;

public class zzui
  implements AppInviteInvitationResult
{
  private final Status zzahq;
  private final Intent zzahr;
  
  public zzui(Status paramStatus, Intent paramIntent)
  {
    this.zzahq = paramStatus;
    this.zzahr = paramIntent;
  }
  
  public Intent getInvitationIntent()
  {
    return this.zzahr;
  }
  
  public Status getStatus()
  {
    return this.zzahq;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzui.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */