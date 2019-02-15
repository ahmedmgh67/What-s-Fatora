package com.google.android.gms.location.places;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzaa.zza;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AddPlaceRequest
  extends zza
{
  public static final Parcelable.Creator<AddPlaceRequest> CREATOR = new zzb();
  private final String mName;
  final int mVersionCode;
  private final String zzaQS;
  private final List<Integer> zzbkA;
  private final String zzbkB;
  private final Uri zzbkC;
  private final LatLng zzbkz;
  
  AddPlaceRequest(int paramInt, String paramString1, LatLng paramLatLng, String paramString2, List<Integer> paramList, String paramString3, Uri paramUri)
  {
    this.mVersionCode = paramInt;
    this.mName = zzac.zzdv(paramString1);
    this.zzbkz = ((LatLng)zzac.zzw(paramLatLng));
    this.zzaQS = zzac.zzdv(paramString2);
    this.zzbkA = new ArrayList((Collection)zzac.zzw(paramList));
    if (!this.zzbkA.isEmpty()) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      zzac.zzb(bool1, "At least one place type should be provided.");
      if (TextUtils.isEmpty(paramString3))
      {
        bool1 = bool2;
        if (paramUri == null) {}
      }
      else
      {
        bool1 = true;
      }
      zzac.zzb(bool1, "One of phone number or URI should be provided.");
      this.zzbkB = paramString3;
      this.zzbkC = paramUri;
      return;
    }
  }
  
  public AddPlaceRequest(String paramString1, LatLng paramLatLng, String paramString2, List<Integer> paramList, Uri paramUri)
  {
    this(paramString1, paramLatLng, paramString2, paramList, null, (Uri)zzac.zzw(paramUri));
  }
  
  public AddPlaceRequest(String paramString1, LatLng paramLatLng, String paramString2, List<Integer> paramList, String paramString3)
  {
    this(paramString1, paramLatLng, paramString2, paramList, zzac.zzdv(paramString3), null);
  }
  
  public AddPlaceRequest(String paramString1, LatLng paramLatLng, String paramString2, List<Integer> paramList, String paramString3, Uri paramUri)
  {
    this(0, paramString1, paramLatLng, paramString2, paramList, paramString3, paramUri);
  }
  
  public String getAddress()
  {
    return this.zzaQS;
  }
  
  public LatLng getLatLng()
  {
    return this.zzbkz;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  @Nullable
  public String getPhoneNumber()
  {
    return this.zzbkB;
  }
  
  public List<Integer> getPlaceTypes()
  {
    return this.zzbkA;
  }
  
  @Nullable
  public Uri getWebsiteUri()
  {
    return this.zzbkC;
  }
  
  public String toString()
  {
    return zzaa.zzv(this).zzg("name", this.mName).zzg("latLng", this.zzbkz).zzg("address", this.zzaQS).zzg("placeTypes", this.zzbkA).zzg("phoneNumer", this.zzbkB).zzg("websiteUri", this.zzbkC).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb.zza(this, paramParcel, paramInt);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\AddPlaceRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */