package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

public final class CircleOptions
  extends zza
{
  public static final Parcelable.Creator<CircleOptions> CREATOR = new zzb();
  private int mFillColor = 0;
  private int mStrokeColor = -16777216;
  private float mStrokeWidth = 10.0F;
  private final int mVersionCode;
  private LatLng zzboH = null;
  private double zzboI = 0.0D;
  private float zzboJ = 0.0F;
  private boolean zzboK = true;
  private boolean zzboL = false;
  
  public CircleOptions()
  {
    this.mVersionCode = 1;
  }
  
  CircleOptions(int paramInt1, LatLng paramLatLng, double paramDouble, float paramFloat1, int paramInt2, int paramInt3, float paramFloat2, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.mVersionCode = paramInt1;
    this.zzboH = paramLatLng;
    this.zzboI = paramDouble;
    this.mStrokeWidth = paramFloat1;
    this.mStrokeColor = paramInt2;
    this.mFillColor = paramInt3;
    this.zzboJ = paramFloat2;
    this.zzboK = paramBoolean1;
    this.zzboL = paramBoolean2;
  }
  
  public CircleOptions center(LatLng paramLatLng)
  {
    this.zzboH = paramLatLng;
    return this;
  }
  
  public CircleOptions clickable(boolean paramBoolean)
  {
    this.zzboL = paramBoolean;
    return this;
  }
  
  public CircleOptions fillColor(int paramInt)
  {
    this.mFillColor = paramInt;
    return this;
  }
  
  public LatLng getCenter()
  {
    return this.zzboH;
  }
  
  public int getFillColor()
  {
    return this.mFillColor;
  }
  
  public double getRadius()
  {
    return this.zzboI;
  }
  
  public int getStrokeColor()
  {
    return this.mStrokeColor;
  }
  
  public float getStrokeWidth()
  {
    return this.mStrokeWidth;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public float getZIndex()
  {
    return this.zzboJ;
  }
  
  public boolean isClickable()
  {
    return this.zzboL;
  }
  
  public boolean isVisible()
  {
    return this.zzboK;
  }
  
  public CircleOptions radius(double paramDouble)
  {
    this.zzboI = paramDouble;
    return this;
  }
  
  public CircleOptions strokeColor(int paramInt)
  {
    this.mStrokeColor = paramInt;
    return this;
  }
  
  public CircleOptions strokeWidth(float paramFloat)
  {
    this.mStrokeWidth = paramFloat;
    return this;
  }
  
  public CircleOptions visible(boolean paramBoolean)
  {
    this.zzboK = paramBoolean;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb.zza(this, paramParcel, paramInt);
  }
  
  public CircleOptions zIndex(float paramFloat)
  {
    this.zzboJ = paramFloat;
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\maps\model\CircleOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */