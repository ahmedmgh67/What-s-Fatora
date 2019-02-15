package com.bumptech.glide.load.resource.bitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.util.DisplayMetrics;
import android.view.Gravity;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;

public class GlideBitmapDrawable
  extends GlideDrawable
{
  private boolean applyGravity;
  private final Rect destRect = new Rect();
  private int height;
  private boolean mutated;
  private BitmapState state;
  private int width;
  
  public GlideBitmapDrawable(Resources paramResources, Bitmap paramBitmap)
  {
    this(paramResources, new BitmapState(paramBitmap));
  }
  
  GlideBitmapDrawable(Resources paramResources, BitmapState paramBitmapState)
  {
    if (paramBitmapState == null) {
      throw new NullPointerException("BitmapState must not be null");
    }
    this.state = paramBitmapState;
    int i;
    if (paramResources != null)
    {
      i = paramResources.getDisplayMetrics().densityDpi;
      if (i == 0)
      {
        i = 160;
        paramBitmapState.targetDensity = i;
      }
    }
    for (;;)
    {
      this.width = paramBitmapState.bitmap.getScaledWidth(i);
      this.height = paramBitmapState.bitmap.getScaledHeight(i);
      return;
      break;
      i = paramBitmapState.targetDensity;
    }
  }
  
  public void draw(Canvas paramCanvas)
  {
    if (this.applyGravity)
    {
      Gravity.apply(119, this.width, this.height, getBounds(), this.destRect);
      this.applyGravity = false;
    }
    paramCanvas.drawBitmap(this.state.bitmap, null, this.destRect, this.state.paint);
  }
  
  public Bitmap getBitmap()
  {
    return this.state.bitmap;
  }
  
  public Drawable.ConstantState getConstantState()
  {
    return this.state;
  }
  
  public int getIntrinsicHeight()
  {
    return this.height;
  }
  
  public int getIntrinsicWidth()
  {
    return this.width;
  }
  
  public int getOpacity()
  {
    Bitmap localBitmap = this.state.bitmap;
    if ((localBitmap == null) || (localBitmap.hasAlpha()) || (this.state.paint.getAlpha() < 255)) {
      return -3;
    }
    return -1;
  }
  
  public boolean isAnimated()
  {
    return false;
  }
  
  public boolean isRunning()
  {
    return false;
  }
  
  public Drawable mutate()
  {
    if ((!this.mutated) && (super.mutate() == this))
    {
      this.state = new BitmapState(this.state);
      this.mutated = true;
    }
    return this;
  }
  
  protected void onBoundsChange(Rect paramRect)
  {
    super.onBoundsChange(paramRect);
    this.applyGravity = true;
  }
  
  public void setAlpha(int paramInt)
  {
    if (this.state.paint.getAlpha() != paramInt)
    {
      this.state.setAlpha(paramInt);
      invalidateSelf();
    }
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    this.state.setColorFilter(paramColorFilter);
    invalidateSelf();
  }
  
  public void setLoopCount(int paramInt) {}
  
  public void start() {}
  
  public void stop() {}
  
  static class BitmapState
    extends Drawable.ConstantState
  {
    private static final Paint DEFAULT_PAINT = new Paint(6);
    private static final int DEFAULT_PAINT_FLAGS = 6;
    private static final int GRAVITY = 119;
    final Bitmap bitmap;
    Paint paint = DEFAULT_PAINT;
    int targetDensity;
    
    public BitmapState(Bitmap paramBitmap)
    {
      this.bitmap = paramBitmap;
    }
    
    BitmapState(BitmapState paramBitmapState)
    {
      this(paramBitmapState.bitmap);
      this.targetDensity = paramBitmapState.targetDensity;
    }
    
    public int getChangingConfigurations()
    {
      return 0;
    }
    
    void mutatePaint()
    {
      if (DEFAULT_PAINT == this.paint) {
        this.paint = new Paint(6);
      }
    }
    
    public Drawable newDrawable()
    {
      return new GlideBitmapDrawable(null, this);
    }
    
    public Drawable newDrawable(Resources paramResources)
    {
      return new GlideBitmapDrawable(paramResources, this);
    }
    
    void setAlpha(int paramInt)
    {
      mutatePaint();
      this.paint.setAlpha(paramInt);
    }
    
    void setColorFilter(ColorFilter paramColorFilter)
    {
      mutatePaint();
      this.paint.setColorFilter(paramColorFilter);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\bitmap\GlideBitmapDrawable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */