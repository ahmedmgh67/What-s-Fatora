package android.support.v4.content.res;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.graphics.drawable.Drawable;
import android.support.annotation.RequiresApi;

@TargetApi(21)
@RequiresApi(21)
class ResourcesCompatApi21
{
  public static Drawable getDrawable(Resources paramResources, int paramInt, Resources.Theme paramTheme)
    throws Resources.NotFoundException
  {
    return paramResources.getDrawable(paramInt, paramTheme);
  }
  
  public static Drawable getDrawableForDensity(Resources paramResources, int paramInt1, int paramInt2, Resources.Theme paramTheme)
    throws Resources.NotFoundException
  {
    return paramResources.getDrawableForDensity(paramInt1, paramInt2, paramTheme);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\android\support\v4\content\res\ResourcesCompatApi21.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */