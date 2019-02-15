package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.style.CharacterStyle;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzaa.zza;
import com.google.android.gms.location.places.AutocompletePrediction;
import java.util.Collections;
import java.util.List;

public class zza
  extends com.google.android.gms.common.internal.safeparcel.zza
  implements AutocompletePrediction
{
  public static final Parcelable.Creator<zza> CREATOR = new zzb();
  private static final List<zza> zzblC = Collections.emptyList();
  final int mVersionCode;
  final List<Integer> zzbkA;
  final String zzblD;
  final List<zza> zzblE;
  final int zzblF;
  final String zzblG;
  final List<zza> zzblH;
  final String zzblI;
  final List<zza> zzblJ;
  final String zzblg;
  
  zza(int paramInt1, String paramString1, List<Integer> paramList, int paramInt2, String paramString2, List<zza> paramList1, String paramString3, List<zza> paramList2, String paramString4, List<zza> paramList3)
  {
    this.mVersionCode = paramInt1;
    this.zzblg = paramString1;
    this.zzbkA = paramList;
    this.zzblF = paramInt2;
    this.zzblD = paramString2;
    this.zzblE = paramList1;
    this.zzblG = paramString3;
    this.zzblH = paramList2;
    this.zzblI = paramString4;
    this.zzblJ = paramList3;
  }
  
  public static zza zza(String paramString1, List<Integer> paramList, int paramInt, String paramString2, List<zza> paramList1, String paramString3, List<zza> paramList2, String paramString4, List<zza> paramList3)
  {
    return new zza(0, paramString1, paramList, paramInt, (String)com.google.android.gms.common.internal.zzac.zzw(paramString2), paramList1, paramString3, paramList2, paramString4, paramList3);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof zza)) {
        return false;
      }
      paramObject = (zza)paramObject;
    } while ((zzaa.equal(this.zzblg, ((zza)paramObject).zzblg)) && (zzaa.equal(this.zzbkA, ((zza)paramObject).zzbkA)) && (zzaa.equal(Integer.valueOf(this.zzblF), Integer.valueOf(((zza)paramObject).zzblF))) && (zzaa.equal(this.zzblD, ((zza)paramObject).zzblD)) && (zzaa.equal(this.zzblE, ((zza)paramObject).zzblE)) && (zzaa.equal(this.zzblG, ((zza)paramObject).zzblG)) && (zzaa.equal(this.zzblH, ((zza)paramObject).zzblH)) && (zzaa.equal(this.zzblI, ((zza)paramObject).zzblI)) && (zzaa.equal(this.zzblJ, ((zza)paramObject).zzblJ)));
    return false;
  }
  
  public CharSequence getFullText(@Nullable CharacterStyle paramCharacterStyle)
  {
    return zzf.zza(this.zzblD, this.zzblE, paramCharacterStyle);
  }
  
  @Nullable
  public String getPlaceId()
  {
    return this.zzblg;
  }
  
  public List<Integer> getPlaceTypes()
  {
    return this.zzbkA;
  }
  
  public CharSequence getPrimaryText(@Nullable CharacterStyle paramCharacterStyle)
  {
    return zzf.zza(this.zzblG, this.zzblH, paramCharacterStyle);
  }
  
  public CharSequence getSecondaryText(@Nullable CharacterStyle paramCharacterStyle)
  {
    return zzf.zza(this.zzblI, this.zzblJ, paramCharacterStyle);
  }
  
  public int hashCode()
  {
    return zzaa.hashCode(new Object[] { this.zzblg, this.zzbkA, Integer.valueOf(this.zzblF), this.zzblD, this.zzblE, this.zzblG, this.zzblH, this.zzblI, this.zzblJ });
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public String toString()
  {
    return zzaa.zzv(this).zzg("placeId", this.zzblg).zzg("placeTypes", this.zzbkA).zzg("fullText", this.zzblD).zzg("fullTextMatchedSubstrings", this.zzblE).zzg("primaryText", this.zzblG).zzg("primaryTextMatchedSubstrings", this.zzblH).zzg("secondaryText", this.zzblI).zzg("secondaryTextMatchedSubstrings", this.zzblJ).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb.zza(this, paramParcel, paramInt);
  }
  
  public AutocompletePrediction zzHV()
  {
    return this;
  }
  
  public static class zza
    extends com.google.android.gms.common.internal.safeparcel.zza
  {
    public static final Parcelable.Creator<zza> CREATOR = new zzac();
    final int mLength;
    final int mOffset;
    final int mVersionCode;
    
    public zza(int paramInt1, int paramInt2, int paramInt3)
    {
      this.mVersionCode = paramInt1;
      this.mOffset = paramInt2;
      this.mLength = paramInt3;
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if (!(paramObject instanceof zza)) {
          return false;
        }
        paramObject = (zza)paramObject;
      } while ((zzaa.equal(Integer.valueOf(this.mOffset), Integer.valueOf(((zza)paramObject).mOffset))) && (zzaa.equal(Integer.valueOf(this.mLength), Integer.valueOf(((zza)paramObject).mLength))));
      return false;
    }
    
    public int getLength()
    {
      return this.mLength;
    }
    
    public int getOffset()
    {
      return this.mOffset;
    }
    
    public int hashCode()
    {
      return zzaa.hashCode(new Object[] { Integer.valueOf(this.mOffset), Integer.valueOf(this.mLength) });
    }
    
    public String toString()
    {
      return zzaa.zzv(this).zzg("offset", Integer.valueOf(this.mOffset)).zzg("length", Integer.valueOf(this.mLength)).toString();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      zzac.zza(this, paramParcel, paramInt);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\internal\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */