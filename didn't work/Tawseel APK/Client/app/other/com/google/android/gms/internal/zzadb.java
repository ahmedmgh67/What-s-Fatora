package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

public class zzadb
  extends zza
{
  public static final Parcelable.Creator<zzadb> CREATOR = new zzadc();
  private final String mName;
  private final String mValue;
  private final int mVersionCode;
  
  zzadb(int paramInt, String paramString1, String paramString2)
  {
    this.mVersionCode = paramInt;
    this.mName = paramString1;
    this.mValue = paramString2;
  }
  
  public zzadb(String paramString1, String paramString2)
  {
    this(1, paramString1, paramString2);
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public String getValue()
  {
    return this.mValue;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzadc.zza(this, paramParcel, paramInt);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzadb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */