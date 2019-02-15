package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public final class PolylineOptions
  extends zza
{
  public static final Parcelable.Creator<PolylineOptions> CREATOR = new zzj();
  private int mColor = -16777216;
  private final int mVersionCode;
  private float zzboJ = 0.0F;
  private boolean zzboK = true;
  private boolean zzboL = false;
  private float zzboP = 10.0F;
  private final List<LatLng> zzbpk;
  private boolean zzbpm = false;
  
  public PolylineOptions()
  {
    this.mVersionCode = 1;
    this.zzbpk = new ArrayList();
  }
  
  PolylineOptions(int paramInt1, List paramList, float paramFloat1, int paramInt2, float paramFloat2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    this.mVersionCode = paramInt1;
    this.zzbpk = paramList;
    this.zzboP = paramFloat1;
    this.mColor = paramInt2;
    this.zzboJ = paramFloat2;
    this.zzboK = paramBoolean1;
    this.zzbpm = paramBoolean2;
    this.zzboL = paramBoolean3;
  }
  
  public PolylineOptions add(LatLng paramLatLng)
  {
    this.zzbpk.add(paramLatLng);
    return this;
  }
  
  public PolylineOptions add(LatLng... paramVarArgs)
  {
    this.zzbpk.addAll(Arrays.asList(paramVarArgs));
    return this;
  }
  
  public PolylineOptions addAll(Iterable<LatLng> paramIterable)
  {
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext())
    {
      LatLng localLatLng = (LatLng)paramIterable.next();
      this.zzbpk.add(localLatLng);
    }
    return this;
  }
  
  public PolylineOptions clickable(boolean paramBoolean)
  {
    this.zzboL = paramBoolean;
    return this;
  }
  
  public PolylineOptions color(int paramInt)
  {
    this.mColor = paramInt;
    return this;
  }
  
  public PolylineOptions geodesic(boolean paramBoolean)
  {
    this.zzbpm = paramBoolean;
    return this;
  }
  
  public int getColor()
  {
    return this.mColor;
  }
  
  public List<LatLng> getPoints()
  {
    return this.zzbpk;
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
  
  public boolean isClickable()
  {
    return this.zzboL;
  }
  
  public boolean isGeodesic()
  {
    return this.zzbpm;
  }
  
  public boolean isVisible()
  {
    return this.zzboK;
  }
  
  public PolylineOptions visible(boolean paramBoolean)
  {
    this.zzboK = paramBoolean;
    return this;
  }
  
  public PolylineOptions width(float paramFloat)
  {
    this.zzboP = paramFloat;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzj.zza(this, paramParcel, paramInt);
  }
  
  public PolylineOptions zIndex(float paramFloat)
  {
    this.zzboJ = paramFloat;
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\maps\model\PolylineOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */