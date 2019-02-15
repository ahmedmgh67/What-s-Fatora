package com.bumptech.glide.load.resource.drawable;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;

public abstract class GlideDrawable
  extends Drawable
  implements Animatable
{
  public static final int LOOP_FOREVER = -1;
  public static final int LOOP_INTRINSIC = 0;
  
  public abstract boolean isAnimated();
  
  public abstract void setLoopCount(int paramInt);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\drawable\GlideDrawable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */