package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zzf;
import java.util.Set;

public final class zzp
  extends zza
{
  public static final Parcelable.Creator<zzp> CREATOR = new zzq();
  public static final Set<zzp> zzblA;
  public static final zzp zzblx = zzx("test_type", 1);
  public static final zzp zzbly = zzx("labeled_place", 6);
  public static final zzp zzblz = zzx("here_content", 7);
  final int mVersionCode;
  final String zzTW;
  final int zzblB;
  
  static
  {
    zzblA = zzf.zza(zzblx, zzbly, zzblz);
  }
  
  zzp(int paramInt1, String paramString, int paramInt2)
  {
    zzac.zzdv(paramString);
    this.mVersionCode = paramInt1;
    this.zzTW = paramString;
    this.zzblB = paramInt2;
  }
  
  private static zzp zzx(String paramString, int paramInt)
  {
    return new zzp(0, paramString, paramInt);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof zzp)) {
        return false;
      }
      paramObject = (zzp)paramObject;
    } while ((this.zzTW.equals(((zzp)paramObject).zzTW)) && (this.zzblB == ((zzp)paramObject).zzblB));
    return false;
  }
  
  public int hashCode()
  {
    return this.zzTW.hashCode();
  }
  
  public String toString()
  {
    return this.zzTW;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzq.zza(this, paramParcel, paramInt);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\zzp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */