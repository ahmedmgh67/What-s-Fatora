package com.google.android.gms.location.places;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzaa.zza;
import com.google.android.gms.location.places.internal.zzp;
import com.google.android.gms.location.places.internal.zzr;
import java.util.Comparator;

public class PlaceLikelihoodBuffer
  extends AbstractDataBuffer<PlaceLikelihood>
  implements Result
{
  private static final Comparator<zzp> zzblc = new Comparator()
  {
    public int zza(zzp paramAnonymouszzp1, zzp paramAnonymouszzp2)
    {
      return -Float.compare(paramAnonymouszzp1.getLikelihood(), paramAnonymouszzp2.getLikelihood());
    }
  };
  private final int zzAG;
  private final Status zzahq;
  private final String zzbkW;
  
  public PlaceLikelihoodBuffer(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder);
    this.zzahq = PlacesStatusCodes.zzdn(paramDataHolder.getStatusCode());
    this.zzAG = zza.zzkA(paramInt);
    if ((paramDataHolder != null) && (paramDataHolder.zzwy() != null))
    {
      this.zzbkW = paramDataHolder.zzwy().getString("com.google.android.gms.location.places.PlaceLikelihoodBuffer.ATTRIBUTIONS_EXTRA_KEY");
      return;
    }
    this.zzbkW = null;
  }
  
  public static int zzM(Bundle paramBundle)
  {
    return paramBundle.getInt("com.google.android.gms.location.places.PlaceLikelihoodBuffer.SOURCE_EXTRA_KEY");
  }
  
  public PlaceLikelihood get(int paramInt)
  {
    return new zzr(this.zzazI, paramInt);
  }
  
  @Nullable
  public CharSequence getAttributions()
  {
    return this.zzbkW;
  }
  
  public Status getStatus()
  {
    return this.zzahq;
  }
  
  public String toString()
  {
    return zzaa.zzv(this).zzg("status", getStatus()).zzg("attributions", this.zzbkW).toString();
  }
  
  public static class zza
  {
    static int zzkA(int paramInt)
    {
      switch (paramInt)
      {
      default: 
        throw new IllegalArgumentException(27 + "invalid source: " + paramInt);
      }
      return paramInt;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\PlaceLikelihoodBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */