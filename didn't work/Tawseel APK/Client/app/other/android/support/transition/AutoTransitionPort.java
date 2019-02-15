package android.support.transition;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;

@TargetApi(14)
@RequiresApi(14)
class AutoTransitionPort
  extends TransitionSetPort
{
  public AutoTransitionPort()
  {
    setOrdering(1);
    addTransition(new FadePort(2)).addTransition(new ChangeBoundsPort()).addTransition(new FadePort(1));
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\android\support\transition\AutoTransitionPort.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */