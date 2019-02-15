package com.google.maps.android.heatmaps;

import android.graphics.Color;
import java.util.HashMap;

public class Gradient
{
  private static final int DEFAULT_COLOR_MAP_SIZE = 1000;
  public final int mColorMapSize;
  public int[] mColors;
  public float[] mStartPoints;
  
  public Gradient(int[] paramArrayOfInt, float[] paramArrayOfFloat)
  {
    this(paramArrayOfInt, paramArrayOfFloat, 1000);
  }
  
  public Gradient(int[] paramArrayOfInt, float[] paramArrayOfFloat, int paramInt)
  {
    if (paramArrayOfInt.length != paramArrayOfFloat.length) {
      throw new IllegalArgumentException("colors and startPoints should be same length");
    }
    if (paramArrayOfInt.length == 0) {
      throw new IllegalArgumentException("No colors have been defined");
    }
    int i = 1;
    while (i < paramArrayOfFloat.length)
    {
      if (paramArrayOfFloat[i] <= paramArrayOfFloat[(i - 1)]) {
        throw new IllegalArgumentException("startPoints should be in increasing order");
      }
      i += 1;
    }
    this.mColorMapSize = paramInt;
    this.mColors = new int[paramArrayOfInt.length];
    this.mStartPoints = new float[paramArrayOfFloat.length];
    System.arraycopy(paramArrayOfInt, 0, this.mColors, 0, paramArrayOfInt.length);
    System.arraycopy(paramArrayOfFloat, 0, this.mStartPoints, 0, paramArrayOfFloat.length);
  }
  
  private HashMap<Integer, ColorInterval> generateColorIntervals()
  {
    HashMap localHashMap = new HashMap();
    if (this.mStartPoints[0] != 0.0F)
    {
      i = Color.argb(0, Color.red(this.mColors[0]), Color.green(this.mColors[0]), Color.blue(this.mColors[0]));
      int j = this.mColors[0];
      float f = this.mColorMapSize;
      localHashMap.put(Integer.valueOf(0), new ColorInterval(i, j, this.mStartPoints[0] * f, null));
    }
    int i = 1;
    while (i < this.mColors.length)
    {
      localHashMap.put(Integer.valueOf((int)(this.mColorMapSize * this.mStartPoints[(i - 1)])), new ColorInterval(this.mColors[(i - 1)], this.mColors[i], this.mColorMapSize * (this.mStartPoints[i] - this.mStartPoints[(i - 1)]), null));
      i += 1;
    }
    if (this.mStartPoints[(this.mStartPoints.length - 1)] != 1.0F)
    {
      i = this.mStartPoints.length - 1;
      localHashMap.put(Integer.valueOf((int)(this.mColorMapSize * this.mStartPoints[i])), new ColorInterval(this.mColors[i], this.mColors[i], this.mColorMapSize * (1.0F - this.mStartPoints[i]), null));
    }
    return localHashMap;
  }
  
  static int interpolateColor(int paramInt1, int paramInt2, float paramFloat)
  {
    int i = (int)((Color.alpha(paramInt2) - Color.alpha(paramInt1)) * paramFloat + Color.alpha(paramInt1));
    float[] arrayOfFloat1 = new float[3];
    Color.RGBToHSV(Color.red(paramInt1), Color.green(paramInt1), Color.blue(paramInt1), arrayOfFloat1);
    float[] arrayOfFloat2 = new float[3];
    Color.RGBToHSV(Color.red(paramInt2), Color.green(paramInt2), Color.blue(paramInt2), arrayOfFloat2);
    if (arrayOfFloat1[0] - arrayOfFloat2[0] > 180.0F) {
      arrayOfFloat2[0] += 360.0F;
    }
    float[] arrayOfFloat3;
    for (;;)
    {
      arrayOfFloat3 = new float[3];
      paramInt1 = 0;
      while (paramInt1 < 3)
      {
        arrayOfFloat3[paramInt1] = ((arrayOfFloat2[paramInt1] - arrayOfFloat1[paramInt1]) * paramFloat + arrayOfFloat1[paramInt1]);
        paramInt1 += 1;
      }
      if (arrayOfFloat2[0] - arrayOfFloat1[0] > 180.0F) {
        arrayOfFloat1[0] += 360.0F;
      }
    }
    return Color.HSVToColor(i, arrayOfFloat3);
  }
  
  int[] generateColorMap(double paramDouble)
  {
    HashMap localHashMap = generateColorIntervals();
    int[] arrayOfInt = new int[this.mColorMapSize];
    ColorInterval localColorInterval = (ColorInterval)localHashMap.get(Integer.valueOf(0));
    int j = 0;
    int i = 0;
    while (i < this.mColorMapSize)
    {
      if (localHashMap.containsKey(Integer.valueOf(i)))
      {
        localColorInterval = (ColorInterval)localHashMap.get(Integer.valueOf(i));
        j = i;
      }
      float f = (i - j) / localColorInterval.duration;
      arrayOfInt[i] = interpolateColor(localColorInterval.color1, localColorInterval.color2, f);
      i += 1;
    }
    if (paramDouble != 1.0D)
    {
      i = 0;
      while (i < this.mColorMapSize)
      {
        j = arrayOfInt[i];
        arrayOfInt[i] = Color.argb((int)(Color.alpha(j) * paramDouble), Color.red(j), Color.green(j), Color.blue(j));
        i += 1;
      }
    }
    return arrayOfInt;
  }
  
  private class ColorInterval
  {
    private final int color1;
    private final int color2;
    private final float duration;
    
    private ColorInterval(int paramInt1, int paramInt2, float paramFloat)
    {
      this.color1 = paramInt1;
      this.color2 = paramInt2;
      this.duration = paramFloat;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\maps\android\heatmaps\Gradient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */