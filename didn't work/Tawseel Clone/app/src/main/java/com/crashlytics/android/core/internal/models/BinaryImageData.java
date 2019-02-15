package com.crashlytics.android.core.internal.models;

public class BinaryImageData
{
  public final long baseAddress;
  public final String id;
  public final String path;
  public final long size;
  
  public BinaryImageData(long paramLong1, long paramLong2, String paramString1, String paramString2)
  {
    this.baseAddress = paramLong1;
    this.size = paramLong2;
    this.path = paramString1;
    this.id = paramString2;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\crashlytics\android\core\internal\models\BinaryImageData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */