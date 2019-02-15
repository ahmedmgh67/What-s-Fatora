package com.google.maps.android.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class SquareTextView
  extends TextView
{
  private int mOffsetLeft = 0;
  private int mOffsetTop = 0;
  
  public SquareTextView(Context paramContext)
  {
    super(paramContext);
  }
  
  public SquareTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public SquareTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public void draw(Canvas paramCanvas)
  {
    paramCanvas.translate(this.mOffsetLeft / 2, this.mOffsetTop / 2);
    super.draw(paramCanvas);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    paramInt1 = getMeasuredWidth();
    paramInt2 = getMeasuredHeight();
    int i = Math.max(paramInt1, paramInt2);
    if (paramInt1 > paramInt2) {
      this.mOffsetTop = (paramInt1 - paramInt2);
    }
    for (this.mOffsetLeft = 0;; this.mOffsetLeft = (paramInt2 - paramInt1))
    {
      setMeasuredDimension(i, i);
      return;
      this.mOffsetTop = 0;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\maps\android\ui\SquareTextView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */