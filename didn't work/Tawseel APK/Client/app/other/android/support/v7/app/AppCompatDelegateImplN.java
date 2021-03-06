package android.support.v7.app;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.RequiresApi;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.Window;
import android.view.Window.Callback;
import java.util.List;

@TargetApi(24)
@RequiresApi(24)
class AppCompatDelegateImplN
  extends AppCompatDelegateImplV23
{
  AppCompatDelegateImplN(Context paramContext, Window paramWindow, AppCompatCallback paramAppCompatCallback)
  {
    super(paramContext, paramWindow, paramAppCompatCallback);
  }
  
  Window.Callback wrapWindowCallback(Window.Callback paramCallback)
  {
    return new AppCompatWindowCallbackN(paramCallback);
  }
  
  class AppCompatWindowCallbackN
    extends AppCompatDelegateImplV23.AppCompatWindowCallbackV23
  {
    AppCompatWindowCallbackN(Window.Callback paramCallback)
    {
      super(paramCallback);
    }
    
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> paramList, Menu paramMenu, int paramInt)
    {
      AppCompatDelegateImplV9.PanelFeatureState localPanelFeatureState = AppCompatDelegateImplN.this.getPanelState(0, true);
      if ((localPanelFeatureState != null) && (localPanelFeatureState.menu != null))
      {
        super.onProvideKeyboardShortcuts(paramList, localPanelFeatureState.menu, paramInt);
        return;
      }
      super.onProvideKeyboardShortcuts(paramList, paramMenu, paramInt);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\android\support\v7\app\AppCompatDelegateImplN.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */