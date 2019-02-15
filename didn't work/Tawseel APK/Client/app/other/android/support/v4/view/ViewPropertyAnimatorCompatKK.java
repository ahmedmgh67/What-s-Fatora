package android.support.v4.view;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewPropertyAnimator;

@TargetApi(19)
@RequiresApi(19)
class ViewPropertyAnimatorCompatKK
{
  public static void setUpdateListener(final View paramView, ViewPropertyAnimatorUpdateListener paramViewPropertyAnimatorUpdateListener)
  {
    ValueAnimator.AnimatorUpdateListener local1 = null;
    if (paramViewPropertyAnimatorUpdateListener != null) {
      local1 = new ValueAnimator.AnimatorUpdateListener()
      {
        public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
        {
          this.val$listener.onAnimationUpdate(paramView);
        }
      };
    }
    paramView.animate().setUpdateListener(local1);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\android\support\v4\view\ViewPropertyAnimatorCompatKK.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */