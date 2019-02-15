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

public final class PlaceFilter
  extends zza
{
  public static final Parcelable.Creator<PlaceFilter> CREATOR = new zzi();
  private static final PlaceFilter zzbkX = new PlaceFilter();
  final int mVersionCode;
  final List<String> zzbkH;
  final List<Integer> zzbkI;
  final List<zzp> zzbkJ;
  private final Set<String> zzbkM;
  private final Set<Integer> zzbkN;
  private final Set<zzp> zzbkO;
  final boolean zzbkY;
  
  public PlaceFilter()
  {
    this(false, null);
  }
  
  PlaceFilter(int paramInt, @Nullable List<Integer> paramList, boolean paramBoolean, @Nullable List<String> paramList1, @Nullable List<zzp> paramList2)
  {
    this.mVersionCode = paramInt;
    if (paramList == null)
    {
      paramList = Collections.emptyList();
      this.zzbkI = paramList;
      this.zzbkY = paramBoolean;
      if (paramList2 != null) {
        break label97;
      }
      paramList = Collections.emptyList();
      label36:
      this.zzbkJ = paramList;
      if (paramList1 != null) {
        break label106;
      }
    }
    label97:
    label106:
    for (paramList = Collections.emptyList();; paramList = Collections.unmodifiableList(paramList1))
    {
      this.zzbkH = paramList;
      this.zzbkN = zzD(this.zzbkI);
      this.zzbkO = zzD(this.zzbkJ);
      this.zzbkM = zzD(this.zzbkH);
      return;
      paramList = Collections.unmodifiableList(paramList);
      break;
      paramList = Collections.unmodifiableList(paramList2);
      break label36;
    }
  }
  
  public PlaceFilter(@Nullable Collection<Integer> paramCollection, boolean paramBoolean, @Nullable Collection<String> paramCollection1, @Nullable Collection<zzp> paramCollection2)
  {
    this(0, zzk(paramCollection), paramBoolean, zzk(paramCollection1), zzk(paramCollection2));
  }
  
  public PlaceFilter(boolean paramBoolean, @Nullable Collection<String> paramCollection)
  {
    this(null, paramBoolean, paramCollection, null);
  }
  
  @Deprecated
  public static PlaceFilter zzHR()
  {
    return new zza(null).zzHS();
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof PlaceFilter)) {
        return false;
      }
      paramObject = (PlaceFilter)paramObject;
    } while ((this.zzbkN.equals(((PlaceFilter)paramObject).zzbkN)) && (this.zzbkY == ((PlaceFilter)paramObject).zzbkY) && (this.zzbkO.equals(((PlaceFilter)paramObject).zzbkO)) && (this.zzbkM.equals(((PlaceFilter)paramObject).zzbkM)));
    return false;
  }
  
  public Set<String> getPlaceIds()
  {
    return this.zzbkM;
  }
  
  public int hashCode()
  {
    return zzaa.hashCode(new Object[] { this.zzbkN, Boolean.valueOf(this.zzbkY), this.zzbkO, this.zzbkM });
  }
  
  public boolean isRestrictedToPlacesOpenNow()
  {
    return this.zzbkY;
  }
  
  public String toString()
  {
    zzaa.zza localzza = zzaa.zzv(this);
    if (!this.zzbkN.isEmpty()) {
      localzza.zzg("types", this.zzbkN);
    }
    localzza.zzg("requireOpenNow", Boolean.valueOf(this.zzbkY));
    if (!this.zzbkM.isEmpty()) {
      localzza.zzg("placeIds", this.zzbkM);
    }
    if (!this.zzbkO.isEmpty()) {
      localzza.zzg("requestedUserDataTypes", this.zzbkO);
    }
    return localzza.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzi.zza(this, paramParcel, paramInt);
  }
  
  public Set<Integer> zzHQ()
  {
    return this.zzbkN;
  }
  
  @Deprecated
  public static final class zza
  {
    private boolean zzbkY = false;
    private Collection<Integer> zzbkZ = null;
    private Collection<zzp> zzbla = null;
    private String[] zzblb = null;
    
    public PlaceFilter zzHS()
    {
      return new PlaceFilter(null, false, null, null);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\PlaceFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */