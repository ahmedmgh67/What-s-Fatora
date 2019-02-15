package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.zzac;

public class BooleanResult
  implements Result
{
  private final Status zzahq;
  private final boolean zzaxF;
  
  public BooleanResult(Status paramStatus, boolean paramBoolean)
  {
    this.zzahq = ((Status)zzac.zzb(paramStatus, "Status must not be null"));
    this.zzaxF = paramBoolean;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (!(paramObject instanceof BooleanResult)) {
        return false;
      }
      paramObject = (BooleanResult)paramObject;
    } while ((this.zzahq.equals(((BooleanResult)paramObject).zzahq)) && (this.zzaxF == ((BooleanResult)paramObject).zzaxF));
    return false;
  }
  
  public Status getStatus()
  {
    return this.zzahq;
  }
  
  public boolean getValue()
  {
    return this.zzaxF;
  }
  
  public final int hashCode()
  {
    int j = this.zzahq.hashCode();
    if (this.zzaxF) {}
    for (int i = 1;; i = 0) {
      return i + (j + 527) * 31;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\common\api\BooleanResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */