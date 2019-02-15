package android.support.transition;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Map;

@TargetApi(14)
@RequiresApi(14)
abstract class VisibilityPort
  extends TransitionPort
{
  private static final String PROPNAME_PARENT = "android:visibility:parent";
  private static final String PROPNAME_VISIBILITY = "android:visibility:visibility";
  private static final String[] sTransitionProperties = { "android:visibility:visibility", "android:visibility:parent" };
  
  private void captureValues(TransitionValues paramTransitionValues)
  {
    int i = paramTransitionValues.view.getVisibility();
    paramTransitionValues.values.put("android:visibility:visibility", Integer.valueOf(i));
    paramTransitionValues.values.put("android:visibility:parent", paramTransitionValues.view.getParent());
  }
  
  private VisibilityInfo getVisibilityChangeInfo(TransitionValues paramTransitionValues1, TransitionValues paramTransitionValues2)
  {
    VisibilityInfo localVisibilityInfo = new VisibilityInfo();
    localVisibilityInfo.visibilityChange = false;
    localVisibilityInfo.fadeIn = false;
    if (paramTransitionValues1 != null)
    {
      localVisibilityInfo.startVisibility = ((Integer)paramTransitionValues1.values.get("android:visibility:visibility")).intValue();
      localVisibilityInfo.startParent = ((ViewGroup)paramTransitionValues1.values.get("android:visibility:parent"));
      if (paramTransitionValues2 == null) {
        break label149;
      }
      localVisibilityInfo.endVisibility = ((Integer)paramTransitionValues2.values.get("android:visibility:visibility")).intValue();
      localVisibilityInfo.endParent = ((ViewGroup)paramTransitionValues2.values.get("android:visibility:parent"));
      label104:
      if ((paramTransitionValues1 == null) || (paramTransitionValues2 == null)) {
        break label190;
      }
      if ((localVisibilityInfo.startVisibility != localVisibilityInfo.endVisibility) || (localVisibilityInfo.startParent != localVisibilityInfo.endParent)) {
        break label162;
      }
    }
    label149:
    label162:
    label190:
    do
    {
      return localVisibilityInfo;
      localVisibilityInfo.startVisibility = -1;
      localVisibilityInfo.startParent = null;
      break;
      localVisibilityInfo.endVisibility = -1;
      localVisibilityInfo.endParent = null;
      break label104;
      if (localVisibilityInfo.startVisibility != localVisibilityInfo.endVisibility) {
        if (localVisibilityInfo.startVisibility == 0)
        {
          localVisibilityInfo.fadeIn = false;
          localVisibilityInfo.visibilityChange = true;
        }
      }
      while (paramTransitionValues1 == null)
      {
        localVisibilityInfo.fadeIn = true;
        localVisibilityInfo.visibilityChange = true;
        return localVisibilityInfo;
        if (localVisibilityInfo.endVisibility == 0)
        {
          localVisibilityInfo.fadeIn = true;
          localVisibilityInfo.visibilityChange = true;
          continue;
          if (localVisibilityInfo.startParent != localVisibilityInfo.endParent) {
            if (localVisibilityInfo.endParent == null)
            {
              localVisibilityInfo.fadeIn = false;
              localVisibilityInfo.visibilityChange = true;
            }
            else if (localVisibilityInfo.startParent == null)
            {
              localVisibilityInfo.fadeIn = true;
              localVisibilityInfo.visibilityChange = true;
            }
          }
        }
      }
    } while (paramTransitionValues2 != null);
    localVisibilityInfo.fadeIn = false;
    localVisibilityInfo.visibilityChange = true;
    return localVisibilityInfo;
  }
  
  public void captureEndValues(TransitionValues paramTransitionValues)
  {
    captureValues(paramTransitionValues);
  }
  
  public void captureStartValues(TransitionValues paramTransitionValues)
  {
    captureValues(paramTransitionValues);
  }
  
  public Animator createAnimator(ViewGroup paramViewGroup, TransitionValues paramTransitionValues1, TransitionValues paramTransitionValues2)
  {
    int j = -1;
    Object localObject2 = null;
    VisibilityInfo localVisibilityInfo = getVisibilityChangeInfo(paramTransitionValues1, paramTransitionValues2);
    Object localObject1 = localObject2;
    View localView;
    if (localVisibilityInfo.visibilityChange)
    {
      i = 0;
      if ((this.mTargets.size() > 0) || (this.mTargetIds.size() > 0))
      {
        if (paramTransitionValues1 == null) {
          break label175;
        }
        localObject1 = paramTransitionValues1.view;
        if (paramTransitionValues2 == null) {
          break label181;
        }
        localView = paramTransitionValues2.view;
        label69:
        if (localObject1 == null) {
          break label187;
        }
        i = ((View)localObject1).getId();
        label81:
        if (localView != null) {
          j = localView.getId();
        }
        if ((!isValidTarget((View)localObject1, i)) && (!isValidTarget(localView, j))) {
          break label193;
        }
      }
    }
    label175:
    label181:
    label187:
    label193:
    for (int i = 1;; i = 0)
    {
      if ((i == 0) && (localVisibilityInfo.startParent == null))
      {
        localObject1 = localObject2;
        if (localVisibilityInfo.endParent == null) {}
      }
      else
      {
        if (!localVisibilityInfo.fadeIn) {
          break label199;
        }
        localObject1 = onAppear(paramViewGroup, paramTransitionValues1, localVisibilityInfo.startVisibility, paramTransitionValues2, localVisibilityInfo.endVisibility);
      }
      return (Animator)localObject1;
      localObject1 = null;
      break;
      localView = null;
      break label69;
      i = -1;
      break label81;
    }
    label199:
    return onDisappear(paramViewGroup, paramTransitionValues1, localVisibilityInfo.startVisibility, paramTransitionValues2, localVisibilityInfo.endVisibility);
  }
  
  public String[] getTransitionProperties()
  {
    return sTransitionProperties;
  }
  
  public boolean isVisible(TransitionValues paramTransitionValues)
  {
    if (paramTransitionValues == null) {
      return false;
    }
    int i = ((Integer)paramTransitionValues.values.get("android:visibility:visibility")).intValue();
    paramTransitionValues = (View)paramTransitionValues.values.get("android:visibility:parent");
    if ((i == 0) && (paramTransitionValues != null)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public Animator onAppear(ViewGroup paramViewGroup, TransitionValues paramTransitionValues1, int paramInt1, TransitionValues paramTransitionValues2, int paramInt2)
  {
    return null;
  }
  
  public Animator onDisappear(ViewGroup paramViewGroup, TransitionValues paramTransitionValues1, int paramInt1, TransitionValues paramTransitionValues2, int paramInt2)
  {
    return null;
  }
  
  private static class VisibilityInfo
  {
    ViewGroup endParent;
    int endVisibility;
    boolean fadeIn;
    ViewGroup startParent;
    int startVisibility;
    boolean visibilityChange;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\android\support\transition\VisibilityPort.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */