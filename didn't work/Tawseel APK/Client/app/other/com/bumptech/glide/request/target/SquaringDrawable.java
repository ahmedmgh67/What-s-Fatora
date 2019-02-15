package com.bumptech.glide.request.target;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;

public class SquaringDrawable
  extends GlideDrawable
{
  private boolean mutated;
  private State state;
  private GlideDrawable wrapped;
  
  public SquaringDrawable(GlideDrawable paramGlideDrawable, int paramInt)
  {
    this(new State(paramGlideDrawable.getConstantState(), paramInt), paramGlideDrawable, null);
  }
  
  SquaringDrawable(State paramState, GlideDrawable paramGlideDrawable, Resources paramResources)
  {
    this.state = paramState;
    if (paramGlideDrawable == null)
    {
      if (paramResources != null)
      {
        this.wrapped = ((GlideDrawable)paramState.wrapped.newDrawable(paramResources));
        return;
      }
      this.wrapped = ((GlideDrawable)paramState.wrapped.newDrawable());
      return;
    }
    this.wrapped = paramGlideDrawable;
  }
  
  public void clearColorFilter()
  {
    this.wrapped.clearColorFilter();
  }
  
  public void draw(Canvas paramCanvas)
  {
    this.wrapped.draw(paramCanvas);
  }
  
  @TargetApi(19)
  public int getAlpha()
  {
    return this.wrapped.getAlpha();
  }
  
  @TargetApi(11)
  public Drawable.Callback getCallback()
  {
    return this.wrapped.getCallback();
  }
  
  public int getChangingConfigurations()
  {
    return this.wrapped.getChangingConfigurations();
  }
  
  public Drawable.ConstantState getConstantState()
  {
    return this.state;
  }
  
  public Drawable getCurrent()
  {
    return this.wrapped.getCurrent();
  }
  
  public int getIntrinsicHeight()
  {
    return this.state.side;
  }
  
  public int getIntrinsicWidth()
  {
    return this.state.side;
  }
  
  public int getMinimumHeight()
  {
    return this.wrapped.getMinimumHeight();
  }
  
  public int getMinimumWidth()
  {
    return this.wrapped.getMinimumWidth();
  }
  
  public int getOpacity()
  {
    return this.wrapped.getOpacity();
  }
  
  public boolean getPadding(Rect paramRect)
  {
    return this.wrapped.getPadding(paramRect);
  }
  
  public void invalidateSelf()
  {
    super.invalidateSelf();
    this.wrapped.invalidateSelf();
  }
  
  public boolean isAnimated()
  {
    return this.wrapped.isAnimated();
  }
  
  public boolean isRunning()
  {
    return this.wrapped.isRunning();
  }
  
  public Drawable mutate()
  {
    if ((!this.mutated) && (super.mutate() == this))
    {
      this.wrapped = ((GlideDrawable)this.wrapped.mutate());
      this.state = new State(this.state);
      this.mutated = true;
    }
    return this;
  }
  
  public void scheduleSelf(Runnable paramRunnable, long paramLong)
  {
    super.scheduleSelf(paramRunnable, paramLong);
    this.wrapped.scheduleSelf(paramRunnable, paramLong);
  }
  
  public void setAlpha(int paramInt)
  {
    this.wrapped.setAlpha(paramInt);
  }
  
  public void setBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.setBounds(paramInt1, paramInt2, paramInt3, paramInt4);
    this.wrapped.setBounds(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void setBounds(Rect paramRect)
  {
    super.setBounds(paramRect);
    this.wrapped.setBounds(paramRect);
  }
  
  public void setChangingConfigurations(int paramInt)
  {
    this.wrapped.setChangingConfigurations(paramInt);
  }
  
  public void setColorFilter(int paramInt, PorterDuff.Mode paramMode)
  {
    this.wrapped.setColorFilter(paramInt, paramMode);
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    this.wrapped.setColorFilter(paramColorFilter);
  }
  
  public void setDither(boolean paramBoolean)
  {
    this.wrapped.setDither(paramBoolean);
  }
  
  public void setFilterBitmap(boolean paramBoolean)
  {
    this.wrapped.setFilterBitmap(paramBoolean);
  }
  
  public void setLoopCount(int paramInt)
  {
    this.wrapped.setLoopCount(paramInt);
  }
  
  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2)
  {
    return this.wrapped.setVisible(paramBoolean1, paramBoolean2);
  }
  
  public void start()
  {
    this.wrapped.start();
  }
  
  public void stop()
  {
    this.wrapped.stop();
  }
  
  public void unscheduleSelf(Runnable paramRunnable)
  {
    super.unscheduleSelf(paramRunnable);
    this.wrapped.unscheduleSelf(paramRunnable);
  }
  
  static class State
    extends Drawable.ConstantState
  {
    private final int side;
    private final Drawable.ConstantState wrapped;
    
    State(Drawable.ConstantState paramConstantState, int paramInt)
    {
      this.wrapped = paramConstantState;
      this.side = paramInt;
    }
    
    State(State paramState)
    {
      this(paramState.wrapped, paramState.side);
    }
    
    public int getChangingConfigurations()
    {
      return 0;
    }
    
    public Drawable newDrawable()
    {
      return newDrawable(null);
    }
    
    public Drawable newDrawable(Resources paramResources)
    {
      return new SquaringDrawable(this, null, paramResources);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\request\target\SquaringDrawable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */