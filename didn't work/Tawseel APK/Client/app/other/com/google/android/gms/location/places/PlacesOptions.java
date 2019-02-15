package com.google.android.gms.location.places;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.internal.zzaa;
import java.util.Locale;

public final class PlacesOptions
  implements Api.ApiOptions.Optional
{
  @Nullable
  public final String zzaht = null;
  @Nullable
  public final String zzblt = null;
  @Nullable
  public final String zzblu = null;
  public final int zzblv = 0;
  @Nullable
  public final Locale zzblw = null;
  
  private PlacesOptions(Builder paramBuilder) {}
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ((paramObject instanceof PlacesOptions))
    {
      paramObject = (PlacesOptions)paramObject;
      bool1 = bool2;
      if (zzaa.equal(this.zzblt, ((PlacesOptions)paramObject).zzblt))
      {
        bool1 = bool2;
        if (zzaa.equal(this.zzblu, ((PlacesOptions)paramObject).zzblu))
        {
          bool1 = bool2;
          if (zzaa.equal(Integer.valueOf(this.zzblv), Integer.valueOf(((PlacesOptions)paramObject).zzblv)))
          {
            bool1 = bool2;
            if (zzaa.equal(this.zzaht, ((PlacesOptions)paramObject).zzaht))
            {
              bool1 = bool2;
              if (zzaa.equal(this.zzblw, ((PlacesOptions)paramObject).zzblw)) {
                bool1 = true;
              }
            }
          }
        }
      }
    }
    return bool1;
  }
  
  @Nullable
  public String getAccountName()
  {
    return this.zzaht;
  }
  
  @Nullable
  public Locale getLocale()
  {
    return this.zzblw;
  }
  
  public int hashCode()
  {
    return zzaa.hashCode(new Object[] { this.zzblt, this.zzblu, Integer.valueOf(this.zzblv), this.zzaht, this.zzblw });
  }
  
  public static class Builder
  {
    private int zzblv = 0;
    
    public PlacesOptions build()
    {
      return new PlacesOptions(this, null);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\PlacesOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */