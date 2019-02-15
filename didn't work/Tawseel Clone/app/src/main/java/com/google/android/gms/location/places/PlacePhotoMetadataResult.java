package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.zza;

public class PlacePhotoMetadataResult
  extends zza
  implements Result
{
  public static final Parcelable.Creator<PlacePhotoMetadataResult> CREATOR = new zzj();
  final int mVersionCode;
  private final Status zzahq;
  final DataHolder zzbld;
  private final PlacePhotoMetadataBuffer zzble;
  
  PlacePhotoMetadataResult(int paramInt, Status paramStatus, DataHolder paramDataHolder)
  {
    this.mVersionCode = paramInt;
    this.zzahq = paramStatus;
    this.zzbld = paramDataHolder;
    if (paramDataHolder == null)
    {
      this.zzble = null;
      return;
    }
    this.zzble = new PlacePhotoMetadataBuffer(this.zzbld);
  }
  
  public PlacePhotoMetadataResult(Status paramStatus, DataHolder paramDataHolder)
  {
    this(0, paramStatus, paramDataHolder);
  }
  
  public PlacePhotoMetadataBuffer getPhotoMetadata()
  {
    return this.zzble;
  }
  
  public Status getStatus()
  {
    return this.zzahq;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzj.zza(this, paramParcel, paramInt);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\PlacePhotoMetadataResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */