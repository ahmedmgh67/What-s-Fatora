package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zzd.zza;

public final class MarkerOptions
  extends zza
{
  public static final Parcelable.Creator<MarkerOptions> CREATOR = new zzg();
  private float mAlpha = 1.0F;
  private final int mVersionCode;
  private String zzalD;
  private float zzboJ;
  private boolean zzboK = true;
  private float zzboS = 0.5F;
  private float zzboT = 1.0F;
  private LatLng zzbon;
  private String zzbpc;
  private BitmapDescriptor zzbpd;
  private boolean zzbpe;
  private boolean zzbpf = false;
  private float zzbpg = 0.0F;
  private float zzbph = 0.5F;
  private float zzbpi = 0.0F;
  
  public MarkerOptions()
  {
    this.mVersionCode = 1;
  }
  
  MarkerOptions(int paramInt, LatLng paramLatLng, String paramString1, String paramString2, IBinder paramIBinder, float paramFloat1, float paramFloat2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7)
  {
    this.mVersionCode = paramInt;
    this.zzbon = paramLatLng;
    this.zzalD = paramString1;
    this.zzbpc = paramString2;
    if (paramIBinder == null) {}
    for (paramLatLng = null;; paramLatLng = new BitmapDescriptor(zzd.zza.zzcd(paramIBinder)))
    {
      this.zzbpd = paramLatLng;
      this.zzboS = paramFloat1;
      this.zzboT = paramFloat2;
      this.zzbpe = paramBoolean1;
      this.zzboK = paramBoolean2;
      this.zzbpf = paramBoolean3;
      this.zzbpg = paramFloat3;
      this.zzbph = paramFloat4;
      this.zzbpi = paramFloat5;
      this.mAlpha = paramFloat6;
      this.zzboJ = paramFloat7;
      return;
    }
  }
  
  public MarkerOptions alpha(float paramFloat)
  {
    this.mAlpha = paramFloat;
    return this;
  }
  
  public MarkerOptions anchor(float paramFloat1, float paramFloat2)
  {
    this.zzboS = paramFloat1;
    this.zzboT = paramFloat2;
    return this;
  }
  
  public MarkerOptions draggable(boolean paramBoolean)
  {
    this.zzbpe = paramBoolean;
    return this;
  }
  
  public MarkerOptions flat(boolean paramBoolean)
  {
    this.zzbpf = paramBoolean;
    return this;
  }
  
  public float getAlpha()
  {
    return this.mAlpha;
  }
  
  public float getAnchorU()
  {
    return this.zzboS;
  }
  
  public float getAnchorV()
  {
    return this.zzboT;
  }
  
  public BitmapDescriptor getIcon()
  {
    return this.zzbpd;
  }
  
  public float getInfoWindowAnchorU()
  {
    return this.zzbph;
  }
  
  public float getInfoWindowAnchorV()
  {
    return this.zzbpi;
  }
  
  public LatLng getPosition()
  {
    return this.zzbon;
  }
  
  public float getRotation()
  {
    return this.zzbpg;
  }
  
  public String getSnippet()
  {
    return this.zzbpc;
  }
  
  public String getTitle()
  {
    return this.zzalD;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public float getZIndex()
  {
    return this.zzboJ;
  }
  
  public MarkerOptions icon(@Nullable BitmapDescriptor paramBitmapDescriptor)
  {
    this.zzbpd = paramBitmapDescriptor;
    return this;
  }
  
  public MarkerOptions infoWindowAnchor(float paramFloat1, float paramFloat2)
  {
    this.zzbph = paramFloat1;
    this.zzbpi = paramFloat2;
    return this;
  }
  
  public boolean isDraggable()
  {
    return this.zzbpe;
  }
  
  public boolean isFlat()
  {
    return this.zzbpf;
  }
  
  public boolean isVisible()
  {
    return this.zzboK;
  }
  
  public MarkerOptions position(@NonNull LatLng paramLatLng)
  {
    if (paramLatLng == null) {
      throw new IllegalArgumentException("latlng cannot be null - a position is required.");
    }
    this.zzbon = paramLatLng;
    return this;
  }
  
  public MarkerOptions rotation(float paramFloat)
  {
    this.zzbpg = paramFloat;
    return this;
  }
  
  public MarkerOptions snippet(@Nullable String paramString)
  {
    this.zzbpc = paramString;
    return this;
  }
  
  public MarkerOptions title(@Nullable String paramString)
  {
    this.zzalD = paramString;
    return this;
  }
  
  public MarkerOptions visible(boolean paramBoolean)
  {
    this.zzboK = paramBoolean;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzg.zza(this, paramParcel, paramInt);
  }
  
  public MarkerOptions zIndex(float paramFloat)
  {
    this.zzboJ = paramFloat;
    return this;
  }
  
  IBinder zzIV()
  {
    if (this.zzbpd == null) {
      return null;
    }
    return this.zzbpd.zzIy().asBinder();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\maps\model\MarkerOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */