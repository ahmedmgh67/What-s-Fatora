package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.SystemClock;
import com.google.android.gms.common.util.zzs;

public final class zzabt
  extends Drawable
  implements Drawable.Callback
{
  private int mFrom;
  private boolean zzaCZ = true;
  private int zzaDe = 0;
  private int zzaDf;
  private int zzaDg = 255;
  private int zzaDh;
  private int zzaDi = 0;
  private boolean zzaDj;
  private zzb zzaDk;
  private Drawable zzaDl;
  private Drawable zzaDm;
  private boolean zzaDn;
  private boolean zzaDo;
  private boolean zzaDp;
  private int zzaDq;
  private long zzaed;
  
  public zzabt(Drawable paramDrawable1, Drawable paramDrawable2)
  {
    this(null);
    Object localObject = paramDrawable1;
    if (paramDrawable1 == null) {
      localObject = zza.zzwN();
    }
    this.zzaDl = ((Drawable)localObject);
    ((Drawable)localObject).setCallback(this);
    paramDrawable1 = this.zzaDk;
    paramDrawable1.zzaDt |= ((Drawable)localObject).getChangingConfigurations();
    paramDrawable1 = paramDrawable2;
    if (paramDrawable2 == null) {
      paramDrawable1 = zza.zzwN();
    }
    this.zzaDm = paramDrawable1;
    paramDrawable1.setCallback(this);
    paramDrawable2 = this.zzaDk;
    paramDrawable2.zzaDt |= paramDrawable1.getChangingConfigurations();
  }
  
  zzabt(zzb paramzzb)
  {
    this.zzaDk = new zzb(paramzzb);
  }
  
  public boolean canConstantState()
  {
    if (!this.zzaDn) {
      if ((this.zzaDl.getConstantState() == null) || (this.zzaDm.getConstantState() == null)) {
        break label44;
      }
    }
    label44:
    for (boolean bool = true;; bool = false)
    {
      this.zzaDo = bool;
      this.zzaDn = true;
      return this.zzaDo;
    }
  }
  
  public void draw(Canvas paramCanvas)
  {
    int j = 1;
    int i = 1;
    int k = 0;
    switch (this.zzaDe)
    {
    }
    boolean bool;
    Drawable localDrawable1;
    Drawable localDrawable2;
    do
    {
      for (;;)
      {
        j = this.zzaDi;
        bool = this.zzaCZ;
        localDrawable1 = this.zzaDl;
        localDrawable2 = this.zzaDm;
        if (i == 0) {
          break;
        }
        if ((!bool) || (j == 0)) {
          localDrawable1.draw(paramCanvas);
        }
        if (j == this.zzaDg)
        {
          localDrawable2.setAlpha(this.zzaDg);
          localDrawable2.draw(paramCanvas);
        }
        return;
        this.zzaed = SystemClock.uptimeMillis();
        this.zzaDe = 2;
        i = k;
      }
    } while (this.zzaed < 0L);
    float f = (float)(SystemClock.uptimeMillis() - this.zzaed) / this.zzaDh;
    if (f >= 1.0F) {}
    for (i = j;; i = 0)
    {
      if (i != 0) {
        this.zzaDe = 0;
      }
      this.zzaDi = ((int)(Math.min(f, 1.0F) * (this.zzaDf + 0) + 0.0F));
      break;
    }
    if (bool) {
      localDrawable1.setAlpha(this.zzaDg - j);
    }
    localDrawable1.draw(paramCanvas);
    if (bool) {
      localDrawable1.setAlpha(this.zzaDg);
    }
    if (j > 0)
    {
      localDrawable2.setAlpha(j);
      localDrawable2.draw(paramCanvas);
      localDrawable2.setAlpha(this.zzaDg);
    }
    invalidateSelf();
  }
  
  public int getChangingConfigurations()
  {
    return super.getChangingConfigurations() | this.zzaDk.mChangingConfigurations | this.zzaDk.zzaDt;
  }
  
  public Drawable.ConstantState getConstantState()
  {
    if (canConstantState())
    {
      this.zzaDk.mChangingConfigurations = getChangingConfigurations();
      return this.zzaDk;
    }
    return null;
  }
  
  public int getIntrinsicHeight()
  {
    return Math.max(this.zzaDl.getIntrinsicHeight(), this.zzaDm.getIntrinsicHeight());
  }
  
  public int getIntrinsicWidth()
  {
    return Math.max(this.zzaDl.getIntrinsicWidth(), this.zzaDm.getIntrinsicWidth());
  }
  
  public int getOpacity()
  {
    if (!this.zzaDp)
    {
      this.zzaDq = Drawable.resolveOpacity(this.zzaDl.getOpacity(), this.zzaDm.getOpacity());
      this.zzaDp = true;
    }
    return this.zzaDq;
  }
  
  @TargetApi(11)
  public void invalidateDrawable(Drawable paramDrawable)
  {
    if (zzs.zzyx())
    {
      paramDrawable = getCallback();
      if (paramDrawable != null) {
        paramDrawable.invalidateDrawable(this);
      }
    }
  }
  
  public Drawable mutate()
  {
    if ((!this.zzaDj) && (super.mutate() == this))
    {
      if (!canConstantState()) {
        throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
      }
      this.zzaDl.mutate();
      this.zzaDm.mutate();
      this.zzaDj = true;
    }
    return this;
  }
  
  protected void onBoundsChange(Rect paramRect)
  {
    this.zzaDl.setBounds(paramRect);
    this.zzaDm.setBounds(paramRect);
  }
  
  @TargetApi(11)
  public void scheduleDrawable(Drawable paramDrawable, Runnable paramRunnable, long paramLong)
  {
    if (zzs.zzyx())
    {
      paramDrawable = getCallback();
      if (paramDrawable != null) {
        paramDrawable.scheduleDrawable(this, paramRunnable, paramLong);
      }
    }
  }
  
  public void setAlpha(int paramInt)
  {
    if (this.zzaDi == this.zzaDg) {
      this.zzaDi = paramInt;
    }
    this.zzaDg = paramInt;
    invalidateSelf();
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    this.zzaDl.setColorFilter(paramColorFilter);
    this.zzaDm.setColorFilter(paramColorFilter);
  }
  
  public void startTransition(int paramInt)
  {
    this.mFrom = 0;
    this.zzaDf = this.zzaDg;
    this.zzaDi = 0;
    this.zzaDh = paramInt;
    this.zzaDe = 1;
    invalidateSelf();
  }
  
  @TargetApi(11)
  public void unscheduleDrawable(Drawable paramDrawable, Runnable paramRunnable)
  {
    if (zzs.zzyx())
    {
      paramDrawable = getCallback();
      if (paramDrawable != null) {
        paramDrawable.unscheduleDrawable(this, paramRunnable);
      }
    }
  }
  
  public Drawable zzwM()
  {
    return this.zzaDm;
  }
  
  private static final class zza
    extends Drawable
  {
    private static final zza zzaDr = new zza();
    private static final zza zzaDs = new zza(null);
    
    public void draw(Canvas paramCanvas) {}
    
    public Drawable.ConstantState getConstantState()
    {
      return zzaDs;
    }
    
    public int getOpacity()
    {
      return -2;
    }
    
    public void setAlpha(int paramInt) {}
    
    public void setColorFilter(ColorFilter paramColorFilter) {}
    
    private static final class zza
      extends Drawable.ConstantState
    {
      public int getChangingConfigurations()
      {
        return 0;
      }
      
      public Drawable newDrawable()
      {
        return zzabt.zza.zzwN();
      }
    }
  }
  
  static final class zzb
    extends Drawable.ConstantState
  {
    int mChangingConfigurations;
    int zzaDt;
    
    zzb(zzb paramzzb)
    {
      if (paramzzb != null)
      {
        this.mChangingConfigurations = paramzzb.mChangingConfigurations;
        this.zzaDt = paramzzb.zzaDt;
      }
    }
    
    public int getChangingConfigurations()
    {
      return this.mChangingConfigurations;
    }
    
    public Drawable newDrawable()
    {
      return new zzabt(this);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzabt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */