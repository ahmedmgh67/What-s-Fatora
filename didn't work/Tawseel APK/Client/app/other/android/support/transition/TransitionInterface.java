package android.support.transition;

import android.animation.Animator;
import android.view.ViewGroup;

abstract interface TransitionInterface
{
  public abstract void captureEndValues(TransitionValues paramTransitionValues);
  
  public abstract void captureStartValues(TransitionValues paramTransitionValues);
  
  public abstract Animator createAnimator(ViewGroup paramViewGroup, TransitionValues paramTransitionValues1, TransitionValues paramTransitionValues2);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\android\support\transition\TransitionInterface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */