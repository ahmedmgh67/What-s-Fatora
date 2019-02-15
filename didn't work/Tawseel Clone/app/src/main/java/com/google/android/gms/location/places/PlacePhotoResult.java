package com.google.android.gms.location.places;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzaa.zza;

public class PlacePhotoResult
  extends zza
  implements Result
{
  public static final Parcelable.Creator<PlacePhotoResult> CREATOR = new zzk();
  private final Bitmap mBitmap;
  final int mVersionCode;
  private final Status zzahq;
  final BitmapTeleporter zzblf;
  
  PlacePhotoResult(int paramInt, Status paramStatus, BitmapTeleporter paramBitmapTeleporter)
  {
    this.mVersionCode = paramInt;
    this.zzahq = paramStatus;
    this.zzblf = paramBitmapTeleporter;
    if (this.zzblf != null)
    {
      this.mBitmap = paramBitmapTeleporter.zzwz();
      return;
    }
    this.mBitmap = null;
  }
  
  public PlacePhotoResult(Status paramStatus, @Nullable BitmapTeleporter paramBitmapTeleporter)
  {
    this(0, paramStatus, paramBitmapTeleporter);
  }
  
  public Bitmap getBitmap()
  {
    return this.mBitmap;
  }
  
  public Status getStatus()
  {
    return this.zzahq;
  }
  
  public String toString()
  {
    return zzaa.zzv(this).zzg("status", this.zzahq).zzg("bitmap", this.mBitmap).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzk.zza(this, paramParcel, paramInt);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\PlacePhotoResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */