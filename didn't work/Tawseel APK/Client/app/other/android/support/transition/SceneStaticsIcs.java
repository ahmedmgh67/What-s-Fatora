package android.support.transition;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.RequiresApi;
import android.view.ViewGroup;

@TargetApi(14)
@RequiresApi(14)
class SceneStaticsIcs
  extends SceneStaticsImpl
{
  public SceneImpl getSceneForLayout(ViewGroup paramViewGroup, int paramInt, Context paramContext)
  {
    SceneIcs localSceneIcs = new SceneIcs();
    localSceneIcs.mScene = ScenePort.getSceneForLayout(paramViewGroup, paramInt, paramContext);
    return localSceneIcs;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\android\support\transition\SceneStaticsIcs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */