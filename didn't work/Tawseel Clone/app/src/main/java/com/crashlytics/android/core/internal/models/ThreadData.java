package com.crashlytics.android.core.internal.models;

public class ThreadData
{
  public static final int IMPORTANCE_CRASHED_THREAD = 4;
  public final FrameData[] frames;
  public final int importance;
  public final String name;
  
  public ThreadData(int paramInt, FrameData[] paramArrayOfFrameData)
  {
    this(null, paramInt, paramArrayOfFrameData);
  }
  
  public ThreadData(String paramString, int paramInt, FrameData[] paramArrayOfFrameData)
  {
    this.name = paramString;
    this.importance = paramInt;
    this.frames = paramArrayOfFrameData;
  }
  
  public static final class FrameData
  {
    public final long address;
    public final String file;
    public final int importance;
    public final long offset;
    public final String symbol;
    
    public FrameData(long paramLong, int paramInt)
    {
      this(paramLong, "", paramInt);
    }
    
    public FrameData(long paramLong, String paramString, int paramInt)
    {
      this(paramLong, paramString, "", 0L, paramInt);
    }
    
    public FrameData(long paramLong1, String paramString1, String paramString2, long paramLong2, int paramInt)
    {
      this.address = paramLong1;
      this.symbol = paramString1;
      this.file = paramString2;
      this.offset = paramLong2;
      this.importance = paramInt;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\crashlytics\android\core\internal\models\ThreadData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */