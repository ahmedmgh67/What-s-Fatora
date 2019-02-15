package com.crashlytics.android.core.internal.models;

public class SignalData
{
  public final String code;
  public final long faultAddress;
  public final String name;
  
  public SignalData(String paramString1, String paramString2, long paramLong)
  {
    this.name = paramString1;
    this.code = paramString2;
    this.faultAddress = paramLong;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\crashlytics\android\core\internal\models\SignalData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */