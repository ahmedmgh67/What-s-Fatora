package com.bumptech.glide.util;

import android.view.View;
import com.bumptech.glide.ListPreloader.PreloadSizeProvider;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.ViewTarget;
import java.util.Arrays;

public class ViewPreloadSizeProvider<T>
  implements ListPreloader.PreloadSizeProvider<T>, SizeReadyCallback
{
  private int[] size;
  private SizeViewTarget viewTarget;
  
  public ViewPreloadSizeProvider() {}
  
  public ViewPreloadSizeProvider(View paramView)
  {
    setView(paramView);
  }
  
  public int[] getPreloadSize(T paramT, int paramInt1, int paramInt2)
  {
    if (this.size == null) {
      return null;
    }
    return Arrays.copyOf(this.size, this.size.length);
  }
  
  public void onSizeReady(int paramInt1, int paramInt2)
  {
    this.size = new int[] { paramInt1, paramInt2 };
    this.viewTarget = null;
  }
  
  public void setView(View paramView)
  {
    if ((this.size != null) || (this.viewTarget != null)) {
      return;
    }
    this.viewTarget = new SizeViewTarget(paramView, this);
  }
  
  private static final class SizeViewTarget
    extends ViewTarget<View, Object>
  {
    public SizeViewTarget(View paramView, SizeReadyCallback paramSizeReadyCallback)
    {
      super();
      getSize(paramSizeReadyCallback);
    }
    
    public void onResourceReady(Object paramObject, GlideAnimation paramGlideAnimation) {}
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\util\ViewPreloadSizeProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */