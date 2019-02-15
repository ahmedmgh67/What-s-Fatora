package android.support.transition;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.RequiresApi;
import android.transition.Scene;
import android.view.ViewGroup;

@TargetApi(21)
@RequiresApi(21)
class SceneStaticsApi21
  extends SceneStaticsImpl
{
  public SceneImpl getSceneForLayout(ViewGroup paramViewGroup, int paramInt, Context paramContext)
  {
    SceneApi21 localSceneApi21 = new SceneApi21();
    localSceneApi21.mScene = Scene.getSceneForLayout(paramViewGroup, paramInt, paramContext);
    return localSceneApi21;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\android\support\transition\SceneStaticsApi21.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */