package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.firebase.auth.ActionCodeResult;

public class zzbkd
  implements ActionCodeResult
{
  private final String zzaiW;
  private final String zzbWI;
  private final int zzbkr;
  
  public zzbkd(@NonNull zzbjv paramzzbjv)
  {
    if (TextUtils.isEmpty(paramzzbjv.zzUy())) {}
    for (this.zzaiW = paramzzbjv.getEmail();; this.zzaiW = paramzzbjv.zzUy())
    {
      this.zzbWI = paramzzbjv.getEmail();
      if (TextUtils.isEmpty(paramzzbjv.zzUz())) {
        break label111;
      }
      if (!paramzzbjv.zzUz().equals("PASSWORD_RESET")) {
        break;
      }
      this.zzbkr = 0;
      return;
    }
    if (paramzzbjv.zzUz().equals("VERIFY_EMAIL"))
    {
      this.zzbkr = 1;
      return;
    }
    if (paramzzbjv.zzUz().equals("RECOVER_EMAIL"))
    {
      this.zzbkr = 2;
      return;
    }
    this.zzbkr = 3;
    return;
    label111:
    this.zzbkr = 3;
  }
  
  @Nullable
  public String getData(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 0: 
      return this.zzaiW;
    }
    return this.zzbWI;
  }
  
  public int getOperation()
  {
    return this.zzbkr;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbkd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */