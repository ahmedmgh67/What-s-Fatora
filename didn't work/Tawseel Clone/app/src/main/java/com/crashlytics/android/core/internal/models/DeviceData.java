package com.crashlytics.android.core.internal.models;

public class DeviceData
{
  public final long availableInternalStorage;
  public final long availablePhysicalMemory;
  public final int batteryCapacity;
  public final int batteryVelocity;
  public final int orientation;
  public final boolean proximity;
  public final long totalInternalStorage;
  public final long totalPhysicalMemory;
  
  public DeviceData(int paramInt1, long paramLong1, long paramLong2, long paramLong3, long paramLong4, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    this.orientation = paramInt1;
    this.totalPhysicalMemory = paramLong1;
    this.totalInternalStorage = paramLong2;
    this.availablePhysicalMemory = paramLong3;
    this.availableInternalStorage = paramLong4;
    this.batteryCapacity = paramInt2;
    this.batteryVelocity = paramInt3;
    this.proximity = paramBoolean;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\crashlytics\android\core\internal\models\DeviceData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */