package com.google.maps.android.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.maps.android.R.id;
import com.google.maps.android.R.layout;
import com.google.maps.android.R.style;

public class IconGenerator
{
  public static final int STYLE_BLUE = 4;
  public static final int STYLE_DEFAULT = 1;
  public static final int STYLE_GREEN = 5;
  public static final int STYLE_ORANGE = 7;
  public static final int STYLE_PURPLE = 6;
  public static final int STYLE_RED = 3;
  public static final int STYLE_WHITE = 2;
  private float mAnchorU = 0.5F;
  private float mAnchorV = 1.0F;
  private BubbleDrawable mBackground;
  private ViewGroup mContainer;
  private View mContentView;
  private final Context mContext;
  private int mRotation;
  private RotationLayout mRotationLayout;
  private TextView mTextView;
  
  public IconGenerator(Context paramContext)
  {
    this.mContext = paramContext;
    this.mBackground = new BubbleDrawable(this.mContext.getResources());
    this.mContainer = ((ViewGroup)LayoutInflater.from(this.mContext).inflate(R.layout.text_bubble, null));
    this.mRotationLayout = ((RotationLayout)this.mContainer.getChildAt(0));
    paramContext = (TextView)this.mRotationLayout.findViewById(R.id.text);
    this.mTextView = paramContext;
    this.mContentView = paramContext;
    setStyle(1);
  }
  
  private static int getStyleColor(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return -1;
    case 3: 
      return -3407872;
    case 4: 
      return -16737844;
    case 5: 
      return -10053376;
    case 6: 
      return -6736948;
    }
    return 34816;
  }
  
  private static int getTextStyle(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return R.style.Bubble_TextAppearance_Dark;
    }
    return R.style.Bubble_TextAppearance_Light;
  }
  
  private float rotateAnchor(float paramFloat1, float paramFloat2)
  {
    float f = paramFloat1;
    switch (this.mRotation)
    {
    default: 
      throw new IllegalStateException();
    case 1: 
      f = 1.0F - paramFloat2;
    case 0: 
      return f;
    case 2: 
      return 1.0F - paramFloat1;
    }
    return paramFloat2;
  }
  
  public float getAnchorU()
  {
    return rotateAnchor(this.mAnchorU, this.mAnchorV);
  }
  
  public float getAnchorV()
  {
    return rotateAnchor(this.mAnchorV, this.mAnchorU);
  }
  
  public Bitmap makeIcon()
  {
    int i = View.MeasureSpec.makeMeasureSpec(0, 0);
    this.mContainer.measure(i, i);
    int j = this.mContainer.getMeasuredWidth();
    i = this.mContainer.getMeasuredHeight();
    this.mContainer.layout(0, 0, j, i);
    if ((this.mRotation == 1) || (this.mRotation == 3))
    {
      i = this.mContainer.getMeasuredWidth();
      j = this.mContainer.getMeasuredHeight();
    }
    Bitmap localBitmap = Bitmap.createBitmap(j, i, Bitmap.Config.ARGB_8888);
    localBitmap.eraseColor(0);
    Canvas localCanvas = new Canvas(localBitmap);
    if (this.mRotation == 0) {}
    for (;;)
    {
      this.mContainer.draw(localCanvas);
      return localBitmap;
      if (this.mRotation == 1)
      {
        localCanvas.translate(j, 0.0F);
        localCanvas.rotate(90.0F);
      }
      else if (this.mRotation == 2)
      {
        localCanvas.rotate(180.0F, j / 2, i / 2);
      }
      else
      {
        localCanvas.translate(0.0F, i);
        localCanvas.rotate(270.0F);
      }
    }
  }
  
  public Bitmap makeIcon(String paramString)
  {
    if (this.mTextView != null) {
      this.mTextView.setText(paramString);
    }
    return makeIcon();
  }
  
  public void setBackground(Drawable paramDrawable)
  {
    this.mContainer.setBackgroundDrawable(paramDrawable);
    if (paramDrawable != null)
    {
      Rect localRect = new Rect();
      paramDrawable.getPadding(localRect);
      this.mContainer.setPadding(localRect.left, localRect.top, localRect.right, localRect.bottom);
      return;
    }
    this.mContainer.setPadding(0, 0, 0, 0);
  }
  
  public void setColor(int paramInt)
  {
    this.mBackground.setColor(paramInt);
    setBackground(this.mBackground);
  }
  
  public void setContentPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mContentView.setPadding(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void setContentRotation(int paramInt)
  {
    this.mRotationLayout.setViewRotation(paramInt);
  }
  
  public void setContentView(View paramView)
  {
    this.mRotationLayout.removeAllViews();
    this.mRotationLayout.addView(paramView);
    this.mContentView = paramView;
    paramView = this.mRotationLayout.findViewById(R.id.text);
    if ((paramView instanceof TextView)) {}
    for (paramView = (TextView)paramView;; paramView = null)
    {
      this.mTextView = paramView;
      return;
    }
  }
  
  public void setRotation(int paramInt)
  {
    this.mRotation = ((paramInt + 360) % 360 / 90);
  }
  
  public void setStyle(int paramInt)
  {
    setColor(getStyleColor(paramInt));
    setTextAppearance(this.mContext, getTextStyle(paramInt));
  }
  
  public void setTextAppearance(int paramInt)
  {
    setTextAppearance(this.mContext, paramInt);
  }
  
  public void setTextAppearance(Context paramContext, int paramInt)
  {
    if (this.mTextView != null) {
      this.mTextView.setTextAppearance(paramContext, paramInt);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\maps\android\ui\IconGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */