package com.google.android.gms.maps;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import com.google.android.gms.R.styleable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLngBounds;

public final class GoogleMapOptions
  extends com.google.android.gms.common.internal.safeparcel.zza
  implements ReflectedParcelable
{
  public static final Parcelable.Creator<GoogleMapOptions> CREATOR = new zza();
  private final int mVersionCode;
  private Boolean zzbnA;
  private Boolean zzbnB;
  private int zzbnC = -1;
  private CameraPosition zzbnD;
  private Boolean zzbnE;
  private Boolean zzbnF;
  private Boolean zzbnG;
  private Boolean zzbnH;
  private Boolean zzbnI;
  private Boolean zzbnJ;
  private Boolean zzbnK;
  private Boolean zzbnL;
  private Boolean zzbnM;
  private Float zzbnN = null;
  private Float zzbnO = null;
  private LatLngBounds zzbnP = null;
  
  public GoogleMapOptions()
  {
    this.mVersionCode = 1;
  }
  
  GoogleMapOptions(int paramInt1, byte paramByte1, byte paramByte2, int paramInt2, CameraPosition paramCameraPosition, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, byte paramByte7, byte paramByte8, byte paramByte9, byte paramByte10, byte paramByte11, Float paramFloat1, Float paramFloat2, LatLngBounds paramLatLngBounds)
  {
    this.mVersionCode = paramInt1;
    this.zzbnA = com.google.android.gms.maps.internal.zza.zza(paramByte1);
    this.zzbnB = com.google.android.gms.maps.internal.zza.zza(paramByte2);
    this.zzbnC = paramInt2;
    this.zzbnD = paramCameraPosition;
    this.zzbnE = com.google.android.gms.maps.internal.zza.zza(paramByte3);
    this.zzbnF = com.google.android.gms.maps.internal.zza.zza(paramByte4);
    this.zzbnG = com.google.android.gms.maps.internal.zza.zza(paramByte5);
    this.zzbnH = com.google.android.gms.maps.internal.zza.zza(paramByte6);
    this.zzbnI = com.google.android.gms.maps.internal.zza.zza(paramByte7);
    this.zzbnJ = com.google.android.gms.maps.internal.zza.zza(paramByte8);
    this.zzbnK = com.google.android.gms.maps.internal.zza.zza(paramByte9);
    this.zzbnL = com.google.android.gms.maps.internal.zza.zza(paramByte10);
    this.zzbnM = com.google.android.gms.maps.internal.zza.zza(paramByte11);
    this.zzbnN = paramFloat1;
    this.zzbnO = paramFloat2;
    this.zzbnP = paramLatLngBounds;
  }
  
  public static GoogleMapOptions createFromAttributes(Context paramContext, AttributeSet paramAttributeSet)
  {
    if (paramAttributeSet == null) {
      return null;
    }
    TypedArray localTypedArray = paramContext.getResources().obtainAttributes(paramAttributeSet, R.styleable.MapAttrs);
    GoogleMapOptions localGoogleMapOptions = new GoogleMapOptions();
    if (localTypedArray.hasValue(R.styleable.MapAttrs_mapType)) {
      localGoogleMapOptions.mapType(localTypedArray.getInt(R.styleable.MapAttrs_mapType, -1));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_zOrderOnTop)) {
      localGoogleMapOptions.zOrderOnTop(localTypedArray.getBoolean(R.styleable.MapAttrs_zOrderOnTop, false));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_useViewLifecycle)) {
      localGoogleMapOptions.useViewLifecycleInFragment(localTypedArray.getBoolean(R.styleable.MapAttrs_useViewLifecycle, false));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_uiCompass)) {
      localGoogleMapOptions.compassEnabled(localTypedArray.getBoolean(R.styleable.MapAttrs_uiCompass, true));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_uiRotateGestures)) {
      localGoogleMapOptions.rotateGesturesEnabled(localTypedArray.getBoolean(R.styleable.MapAttrs_uiRotateGestures, true));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_uiScrollGestures)) {
      localGoogleMapOptions.scrollGesturesEnabled(localTypedArray.getBoolean(R.styleable.MapAttrs_uiScrollGestures, true));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_uiTiltGestures)) {
      localGoogleMapOptions.tiltGesturesEnabled(localTypedArray.getBoolean(R.styleable.MapAttrs_uiTiltGestures, true));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_uiZoomGestures)) {
      localGoogleMapOptions.zoomGesturesEnabled(localTypedArray.getBoolean(R.styleable.MapAttrs_uiZoomGestures, true));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_uiZoomControls)) {
      localGoogleMapOptions.zoomControlsEnabled(localTypedArray.getBoolean(R.styleable.MapAttrs_uiZoomControls, true));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_liteMode)) {
      localGoogleMapOptions.liteMode(localTypedArray.getBoolean(R.styleable.MapAttrs_liteMode, false));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_uiMapToolbar)) {
      localGoogleMapOptions.mapToolbarEnabled(localTypedArray.getBoolean(R.styleable.MapAttrs_uiMapToolbar, true));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_ambientEnabled)) {
      localGoogleMapOptions.ambientEnabled(localTypedArray.getBoolean(R.styleable.MapAttrs_ambientEnabled, false));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_cameraMinZoomPreference)) {
      localGoogleMapOptions.minZoomPreference(localTypedArray.getFloat(R.styleable.MapAttrs_cameraMinZoomPreference, Float.NEGATIVE_INFINITY));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_cameraMinZoomPreference)) {
      localGoogleMapOptions.maxZoomPreference(localTypedArray.getFloat(R.styleable.MapAttrs_cameraMaxZoomPreference, Float.POSITIVE_INFINITY));
    }
    localGoogleMapOptions.latLngBoundsForCameraTarget(LatLngBounds.createFromAttributes(paramContext, paramAttributeSet));
    localGoogleMapOptions.camera(CameraPosition.createFromAttributes(paramContext, paramAttributeSet));
    localTypedArray.recycle();
    return localGoogleMapOptions;
  }
  
  public GoogleMapOptions ambientEnabled(boolean paramBoolean)
  {
    this.zzbnM = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public GoogleMapOptions camera(CameraPosition paramCameraPosition)
  {
    this.zzbnD = paramCameraPosition;
    return this;
  }
  
  public GoogleMapOptions compassEnabled(boolean paramBoolean)
  {
    this.zzbnF = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public Boolean getAmbientEnabled()
  {
    return this.zzbnM;
  }
  
  public CameraPosition getCamera()
  {
    return this.zzbnD;
  }
  
  public Boolean getCompassEnabled()
  {
    return this.zzbnF;
  }
  
  public LatLngBounds getLatLngBoundsForCameraTarget()
  {
    return this.zzbnP;
  }
  
  public Boolean getLiteMode()
  {
    return this.zzbnK;
  }
  
  public Boolean getMapToolbarEnabled()
  {
    return this.zzbnL;
  }
  
  public int getMapType()
  {
    return this.zzbnC;
  }
  
  public Float getMaxZoomPreference()
  {
    return this.zzbnO;
  }
  
  public Float getMinZoomPreference()
  {
    return this.zzbnN;
  }
  
  public Boolean getRotateGesturesEnabled()
  {
    return this.zzbnJ;
  }
  
  public Boolean getScrollGesturesEnabled()
  {
    return this.zzbnG;
  }
  
  public Boolean getTiltGesturesEnabled()
  {
    return this.zzbnI;
  }
  
  public Boolean getUseViewLifecycleInFragment()
  {
    return this.zzbnB;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public Boolean getZOrderOnTop()
  {
    return this.zzbnA;
  }
  
  public Boolean getZoomControlsEnabled()
  {
    return this.zzbnE;
  }
  
  public Boolean getZoomGesturesEnabled()
  {
    return this.zzbnH;
  }
  
  public GoogleMapOptions latLngBoundsForCameraTarget(LatLngBounds paramLatLngBounds)
  {
    this.zzbnP = paramLatLngBounds;
    return this;
  }
  
  public GoogleMapOptions liteMode(boolean paramBoolean)
  {
    this.zzbnK = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public GoogleMapOptions mapToolbarEnabled(boolean paramBoolean)
  {
    this.zzbnL = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public GoogleMapOptions mapType(int paramInt)
  {
    this.zzbnC = paramInt;
    return this;
  }
  
  public GoogleMapOptions maxZoomPreference(float paramFloat)
  {
    this.zzbnO = Float.valueOf(paramFloat);
    return this;
  }
  
  public GoogleMapOptions minZoomPreference(float paramFloat)
  {
    this.zzbnN = Float.valueOf(paramFloat);
    return this;
  }
  
  public GoogleMapOptions rotateGesturesEnabled(boolean paramBoolean)
  {
    this.zzbnJ = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public GoogleMapOptions scrollGesturesEnabled(boolean paramBoolean)
  {
    this.zzbnG = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public GoogleMapOptions tiltGesturesEnabled(boolean paramBoolean)
  {
    this.zzbnI = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public GoogleMapOptions useViewLifecycleInFragment(boolean paramBoolean)
  {
    this.zzbnB = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza.zza(this, paramParcel, paramInt);
  }
  
  public GoogleMapOptions zOrderOnTop(boolean paramBoolean)
  {
    this.zzbnA = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public GoogleMapOptions zoomControlsEnabled(boolean paramBoolean)
  {
    this.zzbnE = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public GoogleMapOptions zoomGesturesEnabled(boolean paramBoolean)
  {
    this.zzbnH = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  byte zzIA()
  {
    return com.google.android.gms.maps.internal.zza.zze(this.zzbnA);
  }
  
  byte zzIB()
  {
    return com.google.android.gms.maps.internal.zza.zze(this.zzbnB);
  }
  
  byte zzIC()
  {
    return com.google.android.gms.maps.internal.zza.zze(this.zzbnE);
  }
  
  byte zzID()
  {
    return com.google.android.gms.maps.internal.zza.zze(this.zzbnF);
  }
  
  byte zzIE()
  {
    return com.google.android.gms.maps.internal.zza.zze(this.zzbnG);
  }
  
  byte zzIF()
  {
    return com.google.android.gms.maps.internal.zza.zze(this.zzbnH);
  }
  
  byte zzIG()
  {
    return com.google.android.gms.maps.internal.zza.zze(this.zzbnI);
  }
  
  byte zzIH()
  {
    return com.google.android.gms.maps.internal.zza.zze(this.zzbnJ);
  }
  
  byte zzII()
  {
    return com.google.android.gms.maps.internal.zza.zze(this.zzbnK);
  }
  
  byte zzIJ()
  {
    return com.google.android.gms.maps.internal.zza.zze(this.zzbnL);
  }
  
  byte zzIK()
  {
    return com.google.android.gms.maps.internal.zza.zze(this.zzbnM);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\maps\GoogleMapOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */