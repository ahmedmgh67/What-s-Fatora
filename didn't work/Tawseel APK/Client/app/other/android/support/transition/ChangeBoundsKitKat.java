package android.support.transition;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.transition.ChangeBounds;

@TargetApi(19)
@RequiresApi(19)
class ChangeBoundsKitKat
  extends TransitionKitKat
  implements ChangeBoundsInterface
{
  public ChangeBoundsKitKat(TransitionInterface paramTransitionInterface)
  {
    init(paramTransitionInterface, new ChangeBounds());
  }
  
  public void setResizeClip(boolean paramBoolean)
  {
    ((ChangeBounds)this.mTransition).setResizeClip(paramBoolean);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\android\support\transition\ChangeBoundsKitKat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */