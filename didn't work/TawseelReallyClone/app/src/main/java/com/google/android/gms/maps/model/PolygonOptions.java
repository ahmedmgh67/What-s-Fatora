package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public final class PolygonOptions
  extends zza
{
  public static final Parcelable.Creator<PolygonOptions> CREATOR = new zzi();
  private int mFillColor = 0;
  private int mStrokeColor = -16777216;
  private float mStrokeWidth = 10.0F;
  private final int mVersionCode;
  private float zzboJ = 0.0F;
  private boolean zzboK = true;
  private boolean zzboL = false;
  private final List<LatLng> zzbpk;
  private final List<List<LatLng>> zzbpl;
  private boolean zzbpm = false;
  
  public PolygonOptions()
  {
    this.mVersionCode = 1;
    this.zzbpk = new ArrayList();
    this.zzbpl = new ArrayList();
  }
  
  PolygonOptions(int paramInt1, List<LatLng> paramList, List paramList1, float paramFloat1, int paramInt2, int paramInt3, float paramFloat2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    this.mVersionCode = paramInt1;
    this.zzbpk = paramList;
    this.zzbpl = paramList1;
    this.mStrokeWidth = paramFloat1;
    this.mStrokeColor = paramInt2;
    this.mFillColor = paramInt3;
    this.zzboJ = paramFloat2;
    this.zzboK = paramBoolean1;
    this.zzbpm = paramBoolean2;
    this.zzboL = paramBoolean3;
  }
  
  public PolygonOptions add(LatLng paramLatLng)
  {
    this.zzbpk.add(paramLatLng);
    return this;
  }
  
  public PolygonOptions add(LatLng... paramVarArgs)
  {
    this.zzbpk.addAll(Arrays.asList(paramVarArgs));
    return this;
  }
  
  public PolygonOptions addAll(Iterable<LatLng> paramIterable)
  {
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext())
    {
      LatLng localLatLng = (LatLng)paramIterable.next();
      this.zzbpk.add(localLatLng);
    }
    return this;
  }
  
  public PolygonOptions addHole(Iterable<LatLng> paramIterable)
  {
    ArrayList localArrayList = new ArrayList();
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext()) {
      localArrayList.add((LatLng)paramIterable.next());
    }
    this.zzbpl.add(localArrayList);
    return this;
  }
  
  public PolygonOptions clickable(boolean paramBoolean)
  {
    this.zzboL = paramBoolean;
    return this;
  }
  
  public PolygonOptions fillColor(int paramInt)
  {
    this.mFillColor = paramInt;
    return this;
  }
  
  public PolygonOptions geodesic(boolean paramBoolean)
  {
    this.zzbpm = paramBoolean;
    return this;
  }
  
  public int getFillColor()
  {
    return this.mFillColor;
  }
  
  public List<List<LatLng>> getHoles()
  {
    return this.zzbpl;
  }
  
  public List<LatLng> getPoints()
  {
    return this.zzbpk;
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
  
  public boolean isGeodesic()
  {
    return this.zzbpm;
  }
  
  public boolean isVisible()
  {
    return this.zzboK;
  }
  
  public PolygonOptions strokeColor(int paramInt)
  {
    this.mStrokeColor = paramInt;
    return this;
  }
  
  public PolygonOptions strokeWidth(float paramFloat)
  {
    this.mStrokeWidth = paramFloat;
    return this;
  }
  
  public PolygonOptions visible(boolean paramBoolean)
  {
    this.zzboK = paramBoolean;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzi.zza(this, paramParcel, paramInt);
  }
  
  public PolygonOptions zIndex(float paramFloat)
  {
    this.zzboJ = paramFloat;
    return this;
  }
  
  List zzIW()
  {
    return this.zzbpl;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\maps\model\PolygonOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */