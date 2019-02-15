package com.google.firebase;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzabk;

public class zza
  implements zzabk
{
  public Exception zzz(Status paramStatus)
  {
    if (paramStatus.getStatusCode() == 8) {
      return new FirebaseException(paramStatus.zzuU());
    }
    return new FirebaseApiNotAvailableException(paramStatus.zzuU());
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */