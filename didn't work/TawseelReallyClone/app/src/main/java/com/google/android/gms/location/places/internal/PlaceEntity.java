package com.google.android.gms.location.places.internal;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzaa.zza;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public final class PlaceEntity
  extends zza
  implements ReflectedParcelable, Place
{
  public static final Parcelable.Creator<PlaceEntity> CREATOR = new zzo();
  private final String mName;
  final int mVersionCode;
  private final String zzGu;
  private final String zzaQS;
  private final List<Integer> zzbkA;
  private final String zzbkB;
  private final Uri zzbkC;
  private final LatLng zzbkz;
  private final Bundle zzblT;
  @Deprecated
  private final zzs zzblU;
  private final float zzblV;
  private final LatLngBounds zzblW;
  private final String zzblX;
  private final boolean zzblY;
  private final float zzblZ;
  private Locale zzblw;
  private final int zzbma;
  private final List<Integer> zzbmb;
  private final String zzbmc;
  private final List<String> zzbmd;
  private final zzu zzbme;
  private final Map<Integer, String> zzbmf;
  private final TimeZone zzbmg;
  
  PlaceEntity(int paramInt1, String paramString1, List<Integer> paramList1, List<Integer> paramList2, Bundle paramBundle, String paramString2, String paramString3, String paramString4, String paramString5, List<String> paramList, LatLng paramLatLng, float paramFloat1, LatLngBounds paramLatLngBounds, String paramString6, Uri paramUri, boolean paramBoolean, float paramFloat2, int paramInt2, zzs paramzzs, zzu paramzzu)
  {
    this.mVersionCode = paramInt1;
    this.zzGu = paramString1;
    this.zzbkA = Collections.unmodifiableList(paramList1);
    this.zzbmb = paramList2;
    if (paramBundle != null)
    {
      this.zzblT = paramBundle;
      this.mName = paramString2;
      this.zzaQS = paramString3;
      this.zzbkB = paramString4;
      this.zzbmc = paramString5;
      if (paramList == null) {
        break label176;
      }
      label68:
      this.zzbmd = paramList;
      this.zzbkz = paramLatLng;
      this.zzblV = paramFloat1;
      this.zzblW = paramLatLngBounds;
      if (paramString6 == null) {
        break label184;
      }
    }
    for (;;)
    {
      this.zzblX = paramString6;
      this.zzbkC = paramUri;
      this.zzblY = paramBoolean;
      this.zzblZ = paramFloat2;
      this.zzbma = paramInt2;
      this.zzbmf = Collections.unmodifiableMap(new HashMap());
      this.zzbmg = null;
      this.zzblw = null;
      this.zzblU = paramzzs;
      this.zzbme = paramzzu;
      return;
      paramBundle = new Bundle();
      break;
      label176:
      paramList = Collections.emptyList();
      break label68;
      label184:
      paramString6 = "UTC";
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof PlaceEntity)) {
        return false;
      }
      paramObject = (PlaceEntity)paramObject;
    } while ((this.zzGu.equals(((PlaceEntity)paramObject).zzGu)) && (zzaa.equal(this.zzblw, ((PlaceEntity)paramObject).zzblw)));
    return false;
  }
  
  public String getAddress()
  {
    return this.zzaQS;
  }
  
  public CharSequence getAttributions()
  {
    return zzf.zzo(this.zzbmd);
  }
  
  public String getId()
  {
    return this.zzGu;
  }
  
  public LatLng getLatLng()
  {
    return this.zzbkz;
  }
  
  public Locale getLocale()
  {
    return this.zzblw;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public String getPhoneNumber()
  {
    return this.zzbkB;
  }
  
  public List<Integer> getPlaceTypes()
  {
    return this.zzbkA;
  }
  
  public int getPriceLevel()
  {
    return this.zzbma;
  }
  
  public float getRating()
  {
    return this.zzblZ;
  }
  
  public LatLngBounds getViewport()
  {
    return this.zzblW;
  }
  
  public Uri getWebsiteUri()
  {
    return this.zzbkC;
  }
  
  public int hashCode()
  {
    return zzaa.hashCode(new Object[] { this.zzGu, this.zzblw });
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public void setLocale(Locale paramLocale)
  {
    this.zzblw = paramLocale;
  }
  
  @SuppressLint({"DefaultLocale"})
  public String toString()
  {
    return zzaa.zzv(this).zzg("id", this.zzGu).zzg("placeTypes", this.zzbkA).zzg("locale", this.zzblw).zzg("name", this.mName).zzg("address", this.zzaQS).zzg("phoneNumber", this.zzbkB).zzg("latlng", this.zzbkz).zzg("viewport", this.zzblW).zzg("websiteUri", this.zzbkC).zzg("isPermanentlyClosed", Boolean.valueOf(this.zzblY)).zzg("priceLevel", Integer.valueOf(this.zzbma)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzo.zza(this, paramParcel, paramInt);
  }
  
  public List<Integer> zzId()
  {
    return this.zzbmb;
  }
  
  public float zzIe()
  {
    return this.zzblV;
  }
  
  public String zzIf()
  {
    return this.zzbmc;
  }
  
  public List<String> zzIg()
  {
    return this.zzbmd;
  }
  
  public boolean zzIh()
  {
    return this.zzblY;
  }
  
  public zzu zzIi()
  {
    return this.zzbme;
  }
  
  public Bundle zzIj()
  {
    return this.zzblT;
  }
  
  public String zzIk()
  {
    return this.zzblX;
  }
  
  @Deprecated
  public zzs zzIl()
  {
    return this.zzblU;
  }
  
  public Place zzIm()
  {
    return this;
  }
  
  public static class zza
  {
    private String mName;
    private int mVersionCode = 0;
    private String zzGu;
    private String zzaQS;
    private String zzbkB;
    private Uri zzbkC;
    private LatLng zzbkz;
    private float zzblV;
    private LatLngBounds zzblW;
    private boolean zzblY;
    private float zzblZ;
    private int zzbma;
    private List<String> zzbmd;
    private zzu zzbme;
    private List<Integer> zzbmh;
    
    public zza zzE(List<Integer> paramList)
    {
      this.zzbmh = paramList;
      return this;
    }
    
    public zza zzF(List<String> paramList)
    {
      this.zzbmd = paramList;
      return this;
    }
    
    public PlaceEntity zzIn()
    {
      return new PlaceEntity(0, this.zzGu, this.zzbmh, Collections.emptyList(), null, this.mName, this.zzaQS, this.zzbkB, null, this.zzbmd, this.zzbkz, this.zzblV, this.zzblW, null, this.zzbkC, this.zzblY, this.zzblZ, this.zzbma, zzs.zza(this.mName, this.zzaQS, this.zzbkB, null, this.zzbmd), this.zzbme);
    }
    
    public zza zza(zzu paramzzu)
    {
      this.zzbme = paramzzu;
      return this;
    }
    
    public zza zza(LatLng paramLatLng)
    {
      this.zzbkz = paramLatLng;
      return this;
    }
    
    public zza zza(LatLngBounds paramLatLngBounds)
    {
      this.zzblW = paramLatLngBounds;
      return this;
    }
    
    public zza zzaD(boolean paramBoolean)
    {
      this.zzblY = paramBoolean;
      return this;
    }
    
    public zza zzeZ(String paramString)
    {
      this.zzGu = paramString;
      return this;
    }
    
    public zza zzfa(String paramString)
    {
      this.mName = paramString;
      return this;
    }
    
    public zza zzfb(String paramString)
    {
      this.zzaQS = paramString;
      return this;
    }
    
    public zza zzfc(String paramString)
    {
      this.zzbkB = paramString;
      return this;
    }
    
    public zza zzg(float paramFloat)
    {
      this.zzblV = paramFloat;
      return this;
    }
    
    public zza zzh(float paramFloat)
    {
      this.zzblZ = paramFloat;
      return this;
    }
    
    public zza zzkJ(int paramInt)
    {
      this.zzbma = paramInt;
      return this;
    }
    
    public zza zzt(Uri paramUri)
    {
      this.zzbkC = paramUri;
      return this;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\internal\PlaceEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */