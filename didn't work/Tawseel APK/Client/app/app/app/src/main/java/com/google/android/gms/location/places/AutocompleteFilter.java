package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzaa.zza;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class AutocompleteFilter
  extends zza
  implements ReflectedParcelable
{
  public static final Parcelable.Creator<AutocompleteFilter> CREATOR = new zzc();
  public static final int TYPE_FILTER_ADDRESS = 2;
  public static final int TYPE_FILTER_CITIES = 5;
  public static final int TYPE_FILTER_ESTABLISHMENT = 34;
  public static final int TYPE_FILTER_GEOCODE = 1007;
  public static final int TYPE_FILTER_NONE = 0;
  public static final int TYPE_FILTER_REGIONS = 4;
  final int mVersionCode;
  final boolean zzbkD;
  final List<Integer> zzbkE;
  final String zzbkF;
  final int zzbkG;
  
  AutocompleteFilter(int paramInt, boolean paramBoolean, List<Integer> paramList, String paramString)
  {
    this.mVersionCode = paramInt;
    this.zzbkE = paramList;
    this.zzbkG = zzl(paramList);
    this.zzbkF = paramString;
    if (this.mVersionCode < 1)
    {
      if (!paramBoolean) {}
      for (paramBoolean = bool;; paramBoolean = false)
      {
        this.zzbkD = paramBoolean;
        return;
      }
    }
    this.zzbkD = paramBoolean;
  }
  
  private static int zzl(@Nullable Collection<Integer> paramCollection)
  {
    if ((paramCollection == null) || (paramCollection.isEmpty())) {
      return 0;
    }
    return ((Integer)paramCollection.iterator().next()).intValue();
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof AutocompleteFilter)) {
        return false;
      }
      paramObject = (AutocompleteFilter)paramObject;
    } while ((this.zzbkG == ((AutocompleteFilter)paramObject).zzbkG) && (this.zzbkD == ((AutocompleteFilter)paramObject).zzbkD) && (this.zzbkF == ((AutocompleteFilter)paramObject).zzbkF));
    return false;
  }
  
  public int getTypeFilter()
  {
    return this.zzbkG;
  }
  
  public int hashCode()
  {
    return zzaa.hashCode(new Object[] { Boolean.valueOf(this.zzbkD), Integer.valueOf(this.zzbkG), this.zzbkF });
  }
  
  public String toString()
  {
    return zzaa.zzv(this).zzg("includeQueryPredictions", Boolean.valueOf(this.zzbkD)).zzg("typeFilter", Integer.valueOf(this.zzbkG)).zzg("country", this.zzbkF).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzc.zza(this, paramParcel, paramInt);
  }
  
  public static final class Builder
  {
    private boolean zzbkD = false;
    private String zzbkF = "";
    private int zzbkG = 0;
    
    public AutocompleteFilter build()
    {
      return new AutocompleteFilter(1, false, Arrays.asList(new Integer[] { Integer.valueOf(this.zzbkG) }), this.zzbkF);
    }
    
    public Builder setCountry(String paramString)
    {
      this.zzbkF = paramString;
      return this;
    }
    
    public Builder setTypeFilter(int paramInt)
    {
      this.zzbkG = paramInt;
      return this;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\AutocompleteFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */