package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zzd.zza;

public final class GroundOverlayOptions
  extends zza
{
  public static final Parcelable.Creator<GroundOverlayOptions> CREATOR = new zzc();
  public static final float NO_DIMENSION = -1.0F;
  private final int mVersionCode;
  private LatLngBounds zzbmR;
  private float zzboF;
  private float zzboJ;
  private boolean zzboK = true;
  private boolean zzboL = false;
  private BitmapDescriptor zzboN;
  private LatLng zzboO;
  private float zzboP;
  private float zzboQ;
  private float zzboR = 0.0F;
  private float zzboS = 0.5F;
  private float zzboT = 0.5F;
  
  public GroundOverlayOptions()
  {
    this.mVersionCode = 1;
  }
  
  GroundOverlayOptions(int paramInt, IBinder paramIBinder, LatLng paramLatLng, float paramFloat1, float paramFloat2, LatLngBounds paramLatLngBounds, float paramFloat3, float paramFloat4, boolean paramBoolean1, float paramFloat5, float paramFloat6, float paramFloat7, boolean paramBoolean2)
  {
    this.mVersionCode = paramInt;
    this.zzboN = new BitmapDescriptor(zzd.zza.zzcd(paramIBinder));
    this.zzboO = paramLatLng;
    this.zzboP = paramFloat1;
    this.zzboQ = paramFloat2;
    this.zzbmR = paramLatLngBounds;
    this.zzboF = paramFloat3;
    this.zzboJ = paramFloat4;
    this.zzboK = paramBoolean1;
    this.zzboR = paramFloat5;
    this.zzboS = paramFloat6;
    this.zzboT = paramFloat7;
    this.zzboL = paramBoolean2;
  }
  
  private GroundOverlayOptions zza(LatLng paramLatLng, float paramFloat1, float paramFloat2)
  {
    this.zzboO = paramLatLng;
    this.zzboP = paramFloat1;
    this.zzboQ = paramFloat2;
    return this;
  }
  
  public GroundOverlayOptions anchor(float paramFloat1, float paramFloat2)
  {
    this.zzboS = paramFloat1;
    this.zzboT = paramFloat2;
    return this;
  }
  
  public GroundOverlayOptions bearing(float paramFloat)
  {
    this.zzboF = ((paramFloat % 360.0F + 360.0F) % 360.0F);
    return this;
  }
  
  public GroundOverlayOptions clickable(boolean paramBoolean)
  {
    this.zzboL = paramBoolean;
    return this;
  }
  
  public float getAnchorU()
  {
    return this.zzboS;
  }
  
  public float getAnchorV()
  {
    return this.zzboT;
  }
  
  public float getBearing()
  {
    return this.zzboF;
  }
  
  public LatLngBounds getBounds()
  {
    return this.zzbmR;
  }
  
  public float getHeight()
  {
    return this.zzboQ;
  }
  
  public BitmapDescriptor getImage()
  {
    return this.zzboN;
  }
  
  public LatLng getLocation()
  {
    return this.zzboO;
  }
  
  public float getTransparency()
  {
    return this.zzboR;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public float getWidth()
  {
    return this.zzboP;
  }
  
  public float getZIndex()
  {
    return this.zzboJ;
  }
  
  public GroundOverlayOptions image(BitmapDescriptor paramBitmapDescriptor)
  {
    this.zzboN = paramBitmapDescriptor;
    return this;
  }
  
  public boolean isClickable()
  {
    return this.zzboL;
  }
  
  public boolean isVisible()
  {
    return this.zzboK;
  }
  
  public GroundOverlayOptions position(LatLng paramLatLng, float paramFloat)
  {
    boolean bool2 = true;
    if (this.zzbmR == null)
    {
      bool1 = true;
      zzac.zza(bool1, "Position has already been set using positionFromBounds");
      if (paramLatLng == null) {
        break label59;
      }
      bool1 = true;
      label24:
      zzac.zzb(bool1, "Location must be specified");
      if (paramFloat < 0.0F) {
        break label64;
      }
    }
    label59:
    label64:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      zzac.zzb(bool1, "Width must be non-negative");
      return zza(paramLatLng, paramFloat, -1.0F);
      bool1 = false;
      break;
      bool1 = false;
      break label24;
    }
  }
  
  public GroundOverlayOptions position(LatLng paramLatLng, float paramFloat1, float paramFloat2)
  {
    boolean bool2 = true;
    if (this.zzbmR == null)
    {
      bool1 = true;
      zzac.zza(bool1, "Position has already been set using positionFromBounds");
      if (paramLatLng == null) {
        break label81;
      }
      bool1 = true;
      label27:
      zzac.zzb(bool1, "Location must be specified");
      if (paramFloat1 < 0.0F) {
        break label87;
      }
      bool1 = true;
      label43:
      zzac.zzb(bool1, "Width must be non-negative");
      if (paramFloat2 < 0.0F) {
        break label93;
      }
    }
    label81:
    label87:
    label93:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      zzac.zzb(bool1, "Height must be non-negative");
      return zza(paramLatLng, paramFloat1, paramFloat2);
      bool1 = false;
      break;
      bool1 = false;
      break label27;
      bool1 = false;
      break label43;
    }
  }
  
  public GroundOverlayOptions positionFromBounds(LatLngBounds paramLatLngBounds)
  {
    if (this.zzboO == null) {}
    for (boolean bool = true;; bool = false)
    {
      String str = String.valueOf(this.zzboO);
      zzac.zza(bool, String.valueOf(str).length() + 46 + "Position has already been set using position: " + str);
      this.zzbmR = paramLatLngBounds;
      return this;
    }
  }
  
  public GroundOverlayOptions transparency(float paramFloat)
  {
    if ((paramFloat >= 0.0F) && (paramFloat <= 1.0F)) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zzb(bool, "Transparency must be in the range [0..1]");
      this.zzboR = paramFloat;
      return this;
    }
  }
  
  public GroundOverlayOptions visible(boolean paramBoolean)
  {
    this.zzboK = paramBoolean;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzc.zza(this, paramParcel, paramInt);
  }
  
  public GroundOverlayOptions zIndex(float paramFloat)
  {
    this.zzboJ = paramFloat;
    return this;
  }
  
  IBinder zzIT()
  {
    return this.zzboN.zzIy().asBinder();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\maps\model\GroundOverlayOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */