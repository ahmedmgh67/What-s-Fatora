package com.google.maps.android.heatmaps;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import android.support.v4.util.LongSparseArray;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Tile;
import com.google.android.gms.maps.model.TileProvider;
import com.google.maps.android.geometry.Bounds;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.quadtree.PointQuadTree;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class HeatmapTileProvider
  implements TileProvider
{
  public static final Gradient DEFAULT_GRADIENT = new Gradient(DEFAULT_GRADIENT_COLORS, DEFAULT_GRADIENT_START_POINTS);
  private static final int[] DEFAULT_GRADIENT_COLORS = { Color.rgb(102, 225, 0), Color.rgb(255, 0, 0) };
  private static final float[] DEFAULT_GRADIENT_START_POINTS = { 0.2F, 1.0F };
  private static final int DEFAULT_MAX_ZOOM = 11;
  private static final int DEFAULT_MIN_ZOOM = 5;
  public static final double DEFAULT_OPACITY = 0.7D;
  public static final int DEFAULT_RADIUS = 20;
  private static final int MAX_RADIUS = 50;
  private static final int MAX_ZOOM_LEVEL = 22;
  private static final int MIN_RADIUS = 10;
  private static final int SCREEN_SIZE = 1280;
  private static final int TILE_DIM = 512;
  static final double WORLD_WIDTH = 1.0D;
  private Bounds mBounds;
  private int[] mColorMap;
  private Collection<WeightedLatLng> mData;
  private Gradient mGradient;
  private double[] mKernel;
  private double[] mMaxIntensity;
  private double mOpacity;
  private int mRadius;
  private PointQuadTree<WeightedLatLng> mTree;
  
  private HeatmapTileProvider(Builder paramBuilder)
  {
    this.mData = paramBuilder.data;
    this.mRadius = paramBuilder.radius;
    this.mGradient = paramBuilder.gradient;
    this.mOpacity = paramBuilder.opacity;
    this.mKernel = generateKernel(this.mRadius, this.mRadius / 3.0D);
    setGradient(this.mGradient);
    setWeightedData(this.mData);
  }
  
  static Bitmap colorize(double[][] paramArrayOfDouble, int[] paramArrayOfInt, double paramDouble)
  {
    int k = paramArrayOfInt[(paramArrayOfInt.length - 1)];
    paramDouble = (paramArrayOfInt.length - 1) / paramDouble;
    int m = paramArrayOfDouble.length;
    int[] arrayOfInt = new int[m * m];
    int i = 0;
    while (i < m)
    {
      int j = 0;
      if (j < m)
      {
        double d = paramArrayOfDouble[j][i];
        int n = i * m + j;
        int i1 = (int)(d * paramDouble);
        if (d != 0.0D) {
          if (i1 < paramArrayOfInt.length) {
            arrayOfInt[n] = paramArrayOfInt[i1];
          }
        }
        for (;;)
        {
          j += 1;
          break;
          arrayOfInt[n] = k;
          continue;
          arrayOfInt[n] = 0;
        }
      }
      i += 1;
    }
    paramArrayOfDouble = Bitmap.createBitmap(m, m, Bitmap.Config.ARGB_8888);
    paramArrayOfDouble.setPixels(arrayOfInt, 0, m, 0, 0, m, m);
    return paramArrayOfDouble;
  }
  
  private static Tile convertBitmap(Bitmap paramBitmap)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localByteArrayOutputStream);
    return new Tile(512, 512, localByteArrayOutputStream.toByteArray());
  }
  
  static double[][] convolve(double[][] paramArrayOfDouble, double[] paramArrayOfDouble1)
  {
    int i = (int)Math.floor(paramArrayOfDouble1.length / 2.0D);
    int i2 = paramArrayOfDouble.length;
    int i3 = i2 - i * 2;
    int k = i + i3 - 1;
    double[][] arrayOfDouble = (double[][])Array.newInstance(Double.TYPE, new int[] { i2, i2 });
    int m = 0;
    int n;
    double d;
    int i1;
    int j;
    double[] arrayOfDouble1;
    while (m < i2)
    {
      n = 0;
      while (n < i2)
      {
        d = paramArrayOfDouble[m][n];
        if (d != 0.0D)
        {
          if (k < m + i)
          {
            i1 = k;
            if (i <= m - i) {
              break label179;
            }
            j = i;
          }
          for (;;)
          {
            if (j >= i1 + 1) {
              break label189;
            }
            arrayOfDouble1 = arrayOfDouble[j];
            arrayOfDouble1[n] += paramArrayOfDouble1[(j - (m - i))] * d;
            j += 1;
            continue;
            i1 = m + i;
            break;
            label179:
            j = m - i;
          }
        }
        label189:
        n += 1;
      }
      m += 1;
    }
    paramArrayOfDouble = (double[][])Array.newInstance(Double.TYPE, new int[] { i3, i3 });
    m = i;
    while (m < k + 1)
    {
      n = 0;
      while (n < i2)
      {
        d = arrayOfDouble[m][n];
        if (d != 0.0D)
        {
          if (k < n + i)
          {
            i1 = k;
            if (i <= n - i) {
              break label363;
            }
            j = i;
          }
          for (;;)
          {
            if (j >= i1 + 1) {
              break label373;
            }
            arrayOfDouble1 = paramArrayOfDouble[(m - i)];
            i3 = j - i;
            arrayOfDouble1[i3] += paramArrayOfDouble1[(j - (n - i))] * d;
            j += 1;
            continue;
            i1 = n + i;
            break;
            label363:
            j = n - i;
          }
        }
        label373:
        n += 1;
      }
      m += 1;
    }
    return paramArrayOfDouble;
  }
  
  static double[] generateKernel(int paramInt, double paramDouble)
  {
    double[] arrayOfDouble = new double[paramInt * 2 + 1];
    int i = -paramInt;
    while (i <= paramInt)
    {
      arrayOfDouble[(i + paramInt)] = Math.exp(-i * i / (2.0D * paramDouble * paramDouble));
      i += 1;
    }
    return arrayOfDouble;
  }
  
  static Bounds getBounds(Collection<WeightedLatLng> paramCollection)
  {
    paramCollection = paramCollection.iterator();
    WeightedLatLng localWeightedLatLng = (WeightedLatLng)paramCollection.next();
    double d7 = localWeightedLatLng.getPoint().x;
    double d3 = localWeightedLatLng.getPoint().x;
    double d2 = localWeightedLatLng.getPoint().y;
    double d1 = localWeightedLatLng.getPoint().y;
    while (paramCollection.hasNext())
    {
      localWeightedLatLng = (WeightedLatLng)paramCollection.next();
      double d8 = localWeightedLatLng.getPoint().x;
      double d4 = localWeightedLatLng.getPoint().y;
      double d5 = d7;
      if (d8 < d7) {
        d5 = d8;
      }
      double d6 = d3;
      if (d8 > d3) {
        d6 = d8;
      }
      d8 = d2;
      if (d4 < d2) {
        d8 = d4;
      }
      d7 = d5;
      d3 = d6;
      d2 = d8;
      if (d4 > d1)
      {
        d1 = d4;
        d7 = d5;
        d3 = d6;
        d2 = d8;
      }
    }
    return new Bounds(d7, d3, d2, d1);
  }
  
  private double[] getMaxIntensities(int paramInt)
  {
    double[] arrayOfDouble = new double[22];
    int i = 5;
    while (i < 11)
    {
      arrayOfDouble[i] = getMaxValue(this.mData, this.mBounds, paramInt, (int)(1280.0D * Math.pow(2.0D, i - 3)));
      if (i == 5)
      {
        int j = 0;
        while (j < i)
        {
          arrayOfDouble[j] = arrayOfDouble[i];
          j += 1;
        }
      }
      i += 1;
    }
    paramInt = 11;
    while (paramInt < 22)
    {
      arrayOfDouble[paramInt] = arrayOfDouble[10];
      paramInt += 1;
    }
    return arrayOfDouble;
  }
  
  static double getMaxValue(Collection<WeightedLatLng> paramCollection, Bounds paramBounds, int paramInt1, int paramInt2)
  {
    double d2 = paramBounds.minX;
    double d1 = paramBounds.maxX;
    double d3 = paramBounds.minY;
    double d4 = paramBounds.maxY;
    if (d1 - d2 > d4 - d3) {}
    for (d1 -= d2;; d1 = d4 - d3)
    {
      d4 = (int)(paramInt2 / (paramInt1 * 2) + 0.5D) / d1;
      LongSparseArray localLongSparseArray = new LongSparseArray();
      d1 = 0.0D;
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        WeightedLatLng localWeightedLatLng = (WeightedLatLng)localIterator.next();
        double d5 = localWeightedLatLng.getPoint().x;
        double d6 = localWeightedLatLng.getPoint().y;
        paramInt1 = (int)((d5 - d2) * d4);
        paramInt2 = (int)((d6 - d3) * d4);
        paramBounds = (LongSparseArray)localLongSparseArray.get(paramInt1);
        paramCollection = paramBounds;
        if (paramBounds == null)
        {
          paramCollection = new LongSparseArray();
          localLongSparseArray.put(paramInt1, paramCollection);
        }
        Double localDouble = (Double)paramCollection.get(paramInt2);
        paramBounds = localDouble;
        if (localDouble == null) {
          paramBounds = Double.valueOf(0.0D);
        }
        paramBounds = Double.valueOf(paramBounds.doubleValue() + localWeightedLatLng.getIntensity());
        paramCollection.put(paramInt2, paramBounds);
        if (paramBounds.doubleValue() > d1) {
          d1 = paramBounds.doubleValue();
        }
      }
    }
    return d1;
  }
  
  private static Collection<WeightedLatLng> wrapData(Collection<LatLng> paramCollection)
  {
    ArrayList localArrayList = new ArrayList();
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext()) {
      localArrayList.add(new WeightedLatLng((LatLng)paramCollection.next()));
    }
    return localArrayList;
  }
  
  public Tile getTile(int paramInt1, int paramInt2, int paramInt3)
  {
    double d1 = 1.0D / Math.pow(2.0D, paramInt3);
    double d2 = this.mRadius * d1 / 512.0D;
    double d3 = (d1 + 2.0D * d2) / (this.mRadius * 2 + 512);
    double d4 = paramInt1 * d1 - d2;
    double d5 = (paramInt1 + 1) * d1 + d2;
    double d6 = paramInt2 * d1 - d2;
    double d7 = (paramInt2 + 1) * d1 + d2;
    d1 = 0.0D;
    Object localObject1 = new ArrayList();
    if (d4 < 0.0D)
    {
      localObject1 = new Bounds(1.0D + d4, 1.0D, d6, d7);
      d1 = -1.0D;
      localObject1 = this.mTree.search((Bounds)localObject1);
    }
    for (;;)
    {
      localObject2 = new Bounds(d4, d5, d6, d7);
      if (((Bounds)localObject2).intersects(new Bounds(this.mBounds.minX - d2, this.mBounds.maxX + d2, this.mBounds.minY - d2, this.mBounds.maxY + d2))) {
        break;
      }
      return TileProvider.NO_TILE;
      if (d5 > 1.0D)
      {
        localObject1 = new Bounds(0.0D, d5 - 1.0D, d6, d7);
        d1 = 1.0D;
        localObject1 = this.mTree.search((Bounds)localObject1);
      }
    }
    Object localObject3 = this.mTree.search((Bounds)localObject2);
    if (((Collection)localObject3).isEmpty()) {
      return TileProvider.NO_TILE;
    }
    paramInt1 = this.mRadius;
    paramInt2 = this.mRadius;
    Object localObject2 = (double[][])Array.newInstance(Double.TYPE, new int[] { paramInt1 * 2 + 512, paramInt2 * 2 + 512 });
    localObject3 = ((Collection)localObject3).iterator();
    Object localObject4;
    while (((Iterator)localObject3).hasNext())
    {
      localObject4 = (WeightedLatLng)((Iterator)localObject3).next();
      Point localPoint = ((WeightedLatLng)localObject4).getPoint();
      paramInt1 = (int)((localPoint.x - d4) / d3);
      paramInt2 = (int)((localPoint.y - d6) / d3);
      localPoint = localObject2[paramInt1];
      localPoint[paramInt2] += ((WeightedLatLng)localObject4).getIntensity();
    }
    localObject1 = ((Collection)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject3 = (WeightedLatLng)((Iterator)localObject1).next();
      localObject4 = ((WeightedLatLng)localObject3).getPoint();
      paramInt1 = (int)((((Point)localObject4).x + d1 - d4) / d3);
      paramInt2 = (int)((((Point)localObject4).y - d6) / d3);
      localObject4 = localObject2[paramInt1];
      localObject4[paramInt2] += ((WeightedLatLng)localObject3).getIntensity();
    }
    return convertBitmap(colorize(convolve((double[][])localObject2, this.mKernel), this.mColorMap, this.mMaxIntensity[paramInt3]));
  }
  
  public void setData(Collection<LatLng> paramCollection)
  {
    setWeightedData(wrapData(paramCollection));
  }
  
  public void setGradient(Gradient paramGradient)
  {
    this.mGradient = paramGradient;
    this.mColorMap = paramGradient.generateColorMap(this.mOpacity);
  }
  
  public void setOpacity(double paramDouble)
  {
    this.mOpacity = paramDouble;
    setGradient(this.mGradient);
  }
  
  public void setRadius(int paramInt)
  {
    this.mRadius = paramInt;
    this.mKernel = generateKernel(this.mRadius, this.mRadius / 3.0D);
    this.mMaxIntensity = getMaxIntensities(this.mRadius);
  }
  
  public void setWeightedData(Collection<WeightedLatLng> paramCollection)
  {
    this.mData = paramCollection;
    if (this.mData.isEmpty()) {
      throw new IllegalArgumentException("No input points.");
    }
    this.mBounds = getBounds(this.mData);
    this.mTree = new PointQuadTree(this.mBounds);
    paramCollection = this.mData.iterator();
    while (paramCollection.hasNext())
    {
      WeightedLatLng localWeightedLatLng = (WeightedLatLng)paramCollection.next();
      this.mTree.add(localWeightedLatLng);
    }
    this.mMaxIntensity = getMaxIntensities(this.mRadius);
  }
  
  public static class Builder
  {
    private Collection<WeightedLatLng> data;
    private Gradient gradient = HeatmapTileProvider.DEFAULT_GRADIENT;
    private double opacity = 0.7D;
    private int radius = 20;
    
    public HeatmapTileProvider build()
    {
      if (this.data == null) {
        throw new IllegalStateException("No input data: you must use either .data or .weightedData before building");
      }
      return new HeatmapTileProvider(this, null);
    }
    
    public Builder data(Collection<LatLng> paramCollection)
    {
      return weightedData(HeatmapTileProvider.wrapData(paramCollection));
    }
    
    public Builder gradient(Gradient paramGradient)
    {
      this.gradient = paramGradient;
      return this;
    }
    
    public Builder opacity(double paramDouble)
    {
      this.opacity = paramDouble;
      if ((this.opacity < 0.0D) || (this.opacity > 1.0D)) {
        throw new IllegalArgumentException("Opacity must be in range [0, 1]");
      }
      return this;
    }
    
    public Builder radius(int paramInt)
    {
      this.radius = paramInt;
      if ((this.radius < 10) || (this.radius > 50)) {
        throw new IllegalArgumentException("Radius not within bounds.");
      }
      return this;
    }
    
    public Builder weightedData(Collection<WeightedLatLng> paramCollection)
    {
      this.data = paramCollection;
      if (this.data.isEmpty()) {
        throw new IllegalArgumentException("No input points.");
      }
      return this;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\maps\android\heatmaps\HeatmapTileProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */