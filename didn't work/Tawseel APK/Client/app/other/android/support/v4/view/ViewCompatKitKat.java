package android.support.v4.view;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.view.View;

@TargetApi(19)
@RequiresApi(19)
class ViewCompatKitKat
{
  public static int getAccessibilityLiveRegion(View paramView)
  {
    return paramView.getAccessibilityLiveRegion();
  }
  
  public static boolean isAttachedToWindow(View paramView)
  {
    return paramView.isAttachedToWindow();
  }
  
  public static boolean isLaidOut(View paramView)
  {
    return paramView.isLaidOut();
  }
  
  public static boolean isLayoutDirectionResolved(View paramView)
  {
    return paramView.isLayoutDirectionResolved();
  }
  
  public static void setAccessibilityLiveRegion(View paramView, int paramInt)
  {
    paramView.setAccessibilityLiveRegion(paramInt);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\android\support\v4\view\ViewCompatKitKat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */