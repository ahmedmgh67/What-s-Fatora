package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzaa.zza;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class zzd
  extends zza
{
  public static final Parcelable.Creator<zzd> CREATOR = new zze();
  final int mVersionCode;
  final List<String> zzbkH;
  final List<Integer> zzbkI;
  final List<zzp> zzbkJ;
  final String zzbkK;
  final boolean zzbkL;
  private final Set<String> zzbkM;
  private final Set<Integer> zzbkN;
  private final Set<zzp> zzbkO;
  
  zzd(int paramInt, @Nullable List<String> paramList, @Nullable List<Integer> paramList1, @Nullable List<zzp> paramList2, @Nullable String paramString, boolean paramBoolean)
  {
    this.mVersionCode = paramInt;
    if (paramList1 == null)
    {
      paramList1 = Collections.emptyList();
      this.zzbkI = paramList1;
      if (paramList2 != null) {
        break label103;
      }
      paramList1 = Collections.emptyList();
      label31:
      this.zzbkJ = paramList1;
      if (paramList != null) {
        break label112;
      }
    }
    label103:
    label112:
    for (paramList = Collections.emptyList();; paramList = Collections.unmodifiableList(paramList))
    {
      this.zzbkH = paramList;
      this.zzbkN = zzD(this.zzbkI);
      this.zzbkO = zzD(this.zzbkJ);
      this.zzbkM = zzD(this.zzbkH);
      this.zzbkK = paramString;
      this.zzbkL = paramBoolean;
      return;
      paramList1 = Collections.unmodifiableList(paramList1);
      break;
      paramList1 = Collections.unmodifiableList(paramList2);
      break label31;
    }
  }
  
  public static zzd zzm(Collection<String> paramCollection)
  {
    if ((paramCollection == null) || (paramCollection.isEmpty())) {
      throw new IllegalArgumentException("NearbyAlertFilters must contain at least oneplace ID to match results with.");
    }
    return new zzd(0, zzk(paramCollection), null, null, null, false);
  }
  
  public static zzd zzn(Collection<Integer> paramCollection)
  {
    if ((paramCollection == null) || (paramCollection.isEmpty())) {
      throw new IllegalArgumentException("NearbyAlertFilters must contain at least oneplace type to match results with.");
    }
    return new zzd(0, null, zzk(paramCollection), null, null, false);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof zzd)) {
        return false;
      }
      paramObject = (zzd)paramObject;
      if ((this.zzbkK == null) && (((zzd)paramObject).zzbkK != null)) {
        return false;
      }
    } while ((this.zzbkN.equals(((zzd)paramObject).zzbkN)) && (this.zzbkO.equals(((zzd)paramObject).zzbkO)) && (this.zzbkM.equals(((zzd)paramObject).zzbkM)) && ((this.zzbkK == null) || (this.zzbkK.equals(((zzd)paramObject).zzbkK))) && (this.zzbkL == ((zzd)paramObject).zzHK()));
    return false;
  }
  
  public Set<String> getPlaceIds()
  {
    return this.zzbkM;
  }
  
  public int hashCode()
  {
    return zzaa.hashCode(new Object[] { this.zzbkN, this.zzbkO, this.zzbkM, this.zzbkK, Boolean.valueOf(this.zzbkL) });
  }
  
  public String toString()
  {
    zzaa.zza localzza = zzaa.zzv(this);
    if (!this.zzbkN.isEmpty()) {
      localzza.zzg("types", this.zzbkN);
    }
    if (!this.zzbkM.isEmpty()) {
      localzza.zzg("placeIds", this.zzbkM);
    }
    if (!this.zzbkO.isEmpty()) {
      localzza.zzg("requestedUserDataTypes", this.zzbkO);
    }
    if (this.zzbkK != null) {
      localzza.zzg("chainName", this.zzbkK);
    }
    localzza.zzg("Beacon required: ", Boolean.valueOf(this.zzbkL));
    return localzza.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zze.zza(this, paramParcel, paramInt);
  }
  
  public boolean zzHK()
  {
    return this.zzbkL;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */