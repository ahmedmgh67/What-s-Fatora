package com.bumptech.glide.load.resource.drawable;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import com.bumptech.glide.load.engine.Resource;

public abstract class DrawableResource<T extends Drawable>
  implements Resource<T>
{
  protected final T drawable;
  
  public DrawableResource(T paramT)
  {
    if (paramT == null) {
      throw new NullPointerException("Drawable must not be null!");
    }
    this.drawable = paramT;
  }
  
  public final T get()
  {
    return this.drawable.getConstantState().newDrawable();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\drawable\DrawableResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */