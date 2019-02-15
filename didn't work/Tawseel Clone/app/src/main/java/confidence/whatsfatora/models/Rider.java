package com.tawseel.tawseel.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;
import java.util.HashMap;

public class Rider
  implements Parcelable
{
  public static final Parcelable.Creator<Rider> CREATOR = new Rider.1();
  public String accountStatus;
  public String appVersion;
  public Double createdAt;
  public String email;
  public String name;
  public Double updatedAt;
  
  public Rider() {}
  
  protected Rider(Parcel paramParcel)
  {
    this.accountStatus = paramParcel.readString();
    this.appVersion = paramParcel.readString();
    this.email = paramParcel.readString();
    this.name = paramParcel.readString();
    this.createdAt = Double.valueOf(paramParcel.readDouble());
    this.updatedAt = Double.valueOf(paramParcel.readDouble());
  }
  
  public Rider(HashMap paramHashMap)
  {
    if (paramHashMap != null) {}
    for (;;)
    {
      try
      {
        if (paramHashMap.get("accountStatus") != null)
        {
          str = paramHashMap.get("accountStatus").toString();
          this.accountStatus = str;
          if (paramHashMap.get("appVersion") == null) {
            break label227;
          }
          str = paramHashMap.get("appVersion").toString();
          this.appVersion = str;
          if (paramHashMap.get("createdAt") == null) {
            break label234;
          }
          d1 = ((Number)paramHashMap.get("createdAt")).doubleValue();
          this.createdAt = Double.valueOf(d1);
          d1 = d2;
          if (paramHashMap.get("updatedAt") != null) {
            d1 = ((Number)paramHashMap.get("updatedAt")).doubleValue();
          }
          this.updatedAt = Double.valueOf(d1);
          if (paramHashMap.get("name") == null) {
            break label239;
          }
          str = paramHashMap.get("name").toString();
          this.name = str;
          if (paramHashMap.get("email") != null)
          {
            paramHashMap = paramHashMap.get("email").toString();
            this.email = paramHashMap;
            return;
          }
          paramHashMap = "";
          continue;
          return;
        }
      }
      catch (Exception paramHashMap)
      {
        if (paramHashMap.getMessage() != null) {
          Log.e("Rider Object Exception", "Rider:exception " + paramHashMap.getMessage());
        }
      }
      String str = "";
      continue;
      label227:
      str = "";
      continue;
      label234:
      double d1 = 0.0D;
      continue;
      label239:
      str = "";
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    double d2 = 0.0D;
    String str;
    if (this.accountStatus != null)
    {
      str = this.accountStatus;
      paramParcel.writeString(str);
      if (this.appVersion == null) {
        break label130;
      }
      str = this.appVersion;
      label35:
      paramParcel.writeString(str);
      if (this.email == null) {
        break label137;
      }
      str = this.email;
      label54:
      paramParcel.writeString(str);
      if (this.name == null) {
        break label144;
      }
      str = this.name;
      label73:
      paramParcel.writeString(str);
      if (this.createdAt == null) {
        break label151;
      }
    }
    label130:
    label137:
    label144:
    label151:
    for (double d1 = this.createdAt.doubleValue();; d1 = 0.0D)
    {
      paramParcel.writeDouble(d1);
      d1 = d2;
      if (this.updatedAt != null) {
        d1 = this.updatedAt.doubleValue();
      }
      paramParcel.writeDouble(d1);
      return;
      str = "";
      break;
      str = "";
      break label35;
      str = "";
      break label54;
      str = "";
      break label73;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\models\Rider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */