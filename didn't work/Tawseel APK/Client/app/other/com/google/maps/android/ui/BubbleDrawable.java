package com.google.maps.android.ui;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.google.maps.android.R.drawable;

class BubbleDrawable
  extends Drawable
{
  private int mColor = -1;
  private final Drawable mMask;
  private final Drawable mShadow;
  
  public BubbleDrawable(Resources paramResources)
  {
    this.mMask = paramResources.getDrawable(R.drawable.bubble_mask);
    this.mShadow = paramResources.getDrawable(R.drawable.bubble_shadow);
  }
  
  public void draw(Canvas paramCanvas)
  {
    this.mMask.draw(paramCanvas);
    paramCanvas.drawColor(this.mColor, PorterDuff.Mode.SRC_IN);
    this.mShadow.draw(paramCanvas);
  }
  
  public int getOpacity()
  {
    return -3;
  }
  
  public boolean getPadding(Rect paramRect)
  {
    return this.mMask.getPadding(paramRect);
  }
  
  public void setAlpha(int paramInt)
  {
    throw new UnsupportedOperationException();
  }
  
  public void setBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mMask.setBounds(paramInt1, paramInt2, paramInt3, paramInt4);
    this.mShadow.setBounds(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void setBounds(Rect paramRect)
  {
    this.mMask.setBounds(paramRect);
    this.mShadow.setBounds(paramRect);
  }
  
  public void setColor(int paramInt)
  {
    this.mColor = paramInt;
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\maps\android\ui\BubbleDrawable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */