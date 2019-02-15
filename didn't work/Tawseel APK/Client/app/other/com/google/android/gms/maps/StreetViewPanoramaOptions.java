package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

public final class StreetViewPanoramaOptions
  extends com.google.android.gms.common.internal.safeparcel.zza
  implements ReflectedParcelable
{
  public static final Parcelable.Creator<StreetViewPanoramaOptions> CREATOR = new zzb();
  private final int mVersionCode;
  private Boolean zzbnB;
  private Boolean zzbnH = Boolean.valueOf(true);
  private StreetViewPanoramaCamera zzbol;
  private String zzbom;
  private LatLng zzbon;
  private Integer zzboo;
  private Boolean zzbop = Boolean.valueOf(true);
  private Boolean zzboq = Boolean.valueOf(true);
  private Boolean zzbor = Boolean.valueOf(true);
  
  public StreetViewPanoramaOptions()
  {
    this.mVersionCode = 1;
  }
  
  StreetViewPanoramaOptions(int paramInt, StreetViewPanoramaCamera paramStreetViewPanoramaCamera, String paramString, LatLng paramLatLng, Integer paramInteger, byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5)
  {
    this.mVersionCode = paramInt;
    this.zzbol = paramStreetViewPanoramaCamera;
    this.zzbon = paramLatLng;
    this.zzboo = paramInteger;
    this.zzbom = paramString;
    this.zzbop = com.google.android.gms.maps.internal.zza.zza(paramByte1);
    this.zzbnH = com.google.android.gms.maps.internal.zza.zza(paramByte2);
    this.zzboq = com.google.android.gms.maps.internal.zza.zza(paramByte3);
    this.zzbor = com.google.android.gms.maps.internal.zza.zza(paramByte4);
    this.zzbnB = com.google.android.gms.maps.internal.zza.zza(paramByte5);
  }
  
  public Boolean getPanningGesturesEnabled()
  {
    return this.zzboq;
  }
  
  public String getPanoramaId()
  {
    return this.zzbom;
  }
  
  public LatLng getPosition()
  {
    return this.zzbon;
  }
  
  public Integer getRadius()
  {
    return this.zzboo;
  }
  
  public Boolean getStreetNamesEnabled()
  {
    return this.zzbor;
  }
  
  public StreetViewPanoramaCamera getStreetViewPanoramaCamera()
  {
    return this.zzbol;
  }
  
  public Boolean getUseViewLifecycleInFragment()
  {
    return this.zzbnB;
  }
  
  public Boolean getUserNavigationEnabled()
  {
    return this.zzbop;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public Boolean getZoomGesturesEnabled()
  {
    return this.zzbnH;
  }
  
  public StreetViewPanoramaOptions panningGesturesEnabled(boolean paramBoolean)
  {
    this.zzboq = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public StreetViewPanoramaOptions panoramaCamera(StreetViewPanoramaCamera paramStreetViewPanoramaCamera)
  {
    this.zzbol = paramStreetViewPanoramaCamera;
    return this;
  }
  
  public StreetViewPanoramaOptions panoramaId(String paramString)
  {
    this.zzbom = paramString;
    return this;
  }
  
  public StreetViewPanoramaOptions position(LatLng paramLatLng)
  {
    this.zzbon = paramLatLng;
    return this;
  }
  
  public StreetViewPanoramaOptions position(LatLng paramLatLng, Integer paramInteger)
  {
    this.zzbon = paramLatLng;
    this.zzboo = paramInteger;
    return this;
  }
  
  public StreetViewPanoramaOptions streetNamesEnabled(boolean paramBoolean)
  {
    this.zzbor = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public StreetViewPanoramaOptions useViewLifecycleInFragment(boolean paramBoolean)
  {
    this.zzbnB = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public StreetViewPanoramaOptions userNavigationEnabled(boolean paramBoolean)
  {
    this.zzbop = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb.zza(this, paramParcel, paramInt);
  }
  
  public StreetViewPanoramaOptions zoomGesturesEnabled(boolean paramBoolean)
  {
    this.zzbnH = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  byte zzIB()
  {
    return com.google.android.gms.maps.internal.zza.zze(this.zzbnB);
  }
  
  byte zzIF()
  {
    return com.google.android.gms.maps.internal.zza.zze(this.zzbnH);
  }
  
  byte zzIN()
  {
    return com.google.android.gms.maps.internal.zza.zze(this.zzbop);
  }
  
  byte zzIO()
  {
    return com.google.android.gms.maps.internal.zza.zze(this.zzboq);
  }
  
  byte zzIP()
  {
    return com.google.android.gms.maps.internal.zza.zze(this.zzbor);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\maps\StreetViewPanoramaOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */