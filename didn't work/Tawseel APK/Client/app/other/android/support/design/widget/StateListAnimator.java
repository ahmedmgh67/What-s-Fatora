package android.support.design.widget;

import android.util.StateSet;
import java.util.ArrayList;

final class StateListAnimator
{
  private final ValueAnimatorCompat.AnimatorListener mAnimationListener = new ValueAnimatorCompat.AnimatorListenerAdapter()
  {
    public void onAnimationEnd(ValueAnimatorCompat paramAnonymousValueAnimatorCompat)
    {
      if (StateListAnimator.this.mRunningAnimator == paramAnonymousValueAnimatorCompat) {
        StateListAnimator.this.mRunningAnimator = null;
      }
    }
  };
  private Tuple mLastMatch = null;
  ValueAnimatorCompat mRunningAnimator = null;
  private final ArrayList<Tuple> mTuples = new ArrayList();
  
  private void cancel()
  {
    if (this.mRunningAnimator != null)
    {
      this.mRunningAnimator.cancel();
      this.mRunningAnimator = null;
    }
  }
  
  private void start(Tuple paramTuple)
  {
    this.mRunningAnimator = paramTuple.mAnimator;
    this.mRunningAnimator.start();
  }
  
  public void addState(int[] paramArrayOfInt, ValueAnimatorCompat paramValueAnimatorCompat)
  {
    paramArrayOfInt = new Tuple(paramArrayOfInt, paramValueAnimatorCompat);
    paramValueAnimatorCompat.addListener(this.mAnimationListener);
    this.mTuples.add(paramArrayOfInt);
  }
  
  public void jumpToCurrentState()
  {
    if (this.mRunningAnimator != null)
    {
      this.mRunningAnimator.end();
      this.mRunningAnimator = null;
    }
  }
  
  void setState(int[] paramArrayOfInt)
  {
    Object localObject2 = null;
    int j = this.mTuples.size();
    int i = 0;
    Object localObject1 = localObject2;
    if (i < j)
    {
      localObject1 = (Tuple)this.mTuples.get(i);
      if (!StateSet.stateSetMatches(((Tuple)localObject1).mSpecs, paramArrayOfInt)) {}
    }
    else
    {
      if (localObject1 != this.mLastMatch) {
        break label64;
      }
    }
    label64:
    do
    {
      return;
      i += 1;
      break;
      if (this.mLastMatch != null) {
        cancel();
      }
      this.mLastMatch = ((Tuple)localObject1);
    } while (localObject1 == null);
    start((Tuple)localObject1);
  }
  
  static class Tuple
  {
    final ValueAnimatorCompat mAnimator;
    final int[] mSpecs;
    
    Tuple(int[] paramArrayOfInt, ValueAnimatorCompat paramValueAnimatorCompat)
    {
      this.mSpecs = paramArrayOfInt;
      this.mAnimator = paramValueAnimatorCompat;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\android\support\design\widget\StateListAnimator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */