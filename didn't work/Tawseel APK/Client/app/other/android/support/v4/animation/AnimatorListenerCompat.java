package android.support.v4.animation;

import android.support.annotation.RestrictTo;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public abstract interface AnimatorListenerCompat
{
  public abstract void onAnimationCancel(ValueAnimatorCompat paramValueAnimatorCompat);
  
  public abstract void onAnimationEnd(ValueAnimatorCompat paramValueAnimatorCompat);
  
  public abstract void onAnimationRepeat(ValueAnimatorCompat paramValueAnimatorCompat);
  
  public abstract void onAnimationStart(ValueAnimatorCompat paramValueAnimatorCompat);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\android\support\v4\animation\AnimatorListenerCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */