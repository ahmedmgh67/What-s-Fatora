package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.LongSparseArray;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TargetApi(14)
@RequiresApi(14)
abstract class TransitionPort
  implements Cloneable
{
  static final boolean DBG = false;
  private static final String LOG_TAG = "Transition";
  private static ThreadLocal<ArrayMap<Animator, AnimationInfo>> sRunningAnimators = new ThreadLocal();
  ArrayList<Animator> mAnimators = new ArrayList();
  boolean mCanRemoveViews = false;
  ArrayList<Animator> mCurrentAnimators = new ArrayList();
  long mDuration = -1L;
  private TransitionValuesMaps mEndValues = new TransitionValuesMaps();
  private boolean mEnded = false;
  TimeInterpolator mInterpolator = null;
  ArrayList<TransitionListener> mListeners = null;
  private String mName = getClass().getName();
  int mNumInstances = 0;
  TransitionSetPort mParent = null;
  boolean mPaused = false;
  ViewGroup mSceneRoot = null;
  long mStartDelay = -1L;
  private TransitionValuesMaps mStartValues = new TransitionValuesMaps();
  ArrayList<View> mTargetChildExcludes = null;
  ArrayList<View> mTargetExcludes = null;
  ArrayList<Integer> mTargetIdChildExcludes = null;
  ArrayList<Integer> mTargetIdExcludes = null;
  ArrayList<Integer> mTargetIds = new ArrayList();
  ArrayList<Class> mTargetTypeChildExcludes = null;
  ArrayList<Class> mTargetTypeExcludes = null;
  ArrayList<View> mTargets = new ArrayList();
  
  private void captureHierarchy(View paramView, boolean paramBoolean)
  {
    if (paramView == null) {}
    for (;;)
    {
      return;
      int i = 0;
      if ((paramView.getParent() instanceof ListView)) {
        i = 1;
      }
      if ((i == 0) || (((ListView)paramView.getParent()).getAdapter().hasStableIds()))
      {
        int j = -1;
        long l = -1L;
        if (i == 0) {
          j = paramView.getId();
        }
        for (;;)
        {
          if (((this.mTargetIdExcludes != null) && (this.mTargetIdExcludes.contains(Integer.valueOf(j)))) || ((this.mTargetExcludes != null) && (this.mTargetExcludes.contains(paramView)))) {
            break label180;
          }
          if ((this.mTargetTypeExcludes == null) || (paramView == null)) {
            break label182;
          }
          int m = this.mTargetTypeExcludes.size();
          int k = 0;
          for (;;)
          {
            if (k >= m) {
              break label182;
            }
            if (((Class)this.mTargetTypeExcludes.get(k)).isInstance(paramView)) {
              break;
            }
            k += 1;
          }
          localObject = (ListView)paramView.getParent();
          l = ((ListView)localObject).getItemIdAtPosition(((ListView)localObject).getPositionForView(paramView));
        }
        label180:
        continue;
        label182:
        Object localObject = new TransitionValues();
        ((TransitionValues)localObject).view = paramView;
        if (paramBoolean)
        {
          captureStartValues((TransitionValues)localObject);
          label207:
          if (!paramBoolean) {
            break label374;
          }
          if (i != 0) {
            break label357;
          }
          this.mStartValues.viewValues.put(paramView, localObject);
          if (j >= 0) {
            this.mStartValues.idValues.put(j, localObject);
          }
        }
        for (;;)
        {
          if ((!(paramView instanceof ViewGroup)) || ((this.mTargetIdChildExcludes != null) && (this.mTargetIdChildExcludes.contains(Integer.valueOf(j)))) || ((this.mTargetChildExcludes != null) && (this.mTargetChildExcludes.contains(paramView)))) {
            break label429;
          }
          if ((this.mTargetTypeChildExcludes == null) || (paramView == null)) {
            break label431;
          }
          j = this.mTargetTypeChildExcludes.size();
          i = 0;
          for (;;)
          {
            if (i >= j) {
              break label431;
            }
            if (((Class)this.mTargetTypeChildExcludes.get(i)).isInstance(paramView)) {
              break;
            }
            i += 1;
          }
          captureEndValues((TransitionValues)localObject);
          break label207;
          label357:
          this.mStartValues.itemIdValues.put(l, localObject);
          continue;
          label374:
          if (i == 0)
          {
            this.mEndValues.viewValues.put(paramView, localObject);
            if (j >= 0) {
              this.mEndValues.idValues.put(j, localObject);
            }
          }
          else
          {
            this.mEndValues.itemIdValues.put(l, localObject);
          }
        }
        label429:
        continue;
        label431:
        paramView = (ViewGroup)paramView;
        i = 0;
        while (i < paramView.getChildCount())
        {
          captureHierarchy(paramView.getChildAt(i), paramBoolean);
          i += 1;
        }
      }
    }
  }
  
  private ArrayList<Integer> excludeId(ArrayList<Integer> paramArrayList, int paramInt, boolean paramBoolean)
  {
    Object localObject = paramArrayList;
    if (paramInt > 0)
    {
      if (paramBoolean) {
        localObject = ArrayListManager.add(paramArrayList, Integer.valueOf(paramInt));
      }
    }
    else {
      return (ArrayList<Integer>)localObject;
    }
    return ArrayListManager.remove(paramArrayList, Integer.valueOf(paramInt));
  }
  
  private ArrayList<Class> excludeType(ArrayList<Class> paramArrayList, Class paramClass, boolean paramBoolean)
  {
    Object localObject = paramArrayList;
    if (paramClass != null)
    {
      if (paramBoolean) {
        localObject = ArrayListManager.add(paramArrayList, paramClass);
      }
    }
    else {
      return (ArrayList<Class>)localObject;
    }
    return ArrayListManager.remove(paramArrayList, paramClass);
  }
  
  private ArrayList<View> excludeView(ArrayList<View> paramArrayList, View paramView, boolean paramBoolean)
  {
    Object localObject = paramArrayList;
    if (paramView != null)
    {
      if (paramBoolean) {
        localObject = ArrayListManager.add(paramArrayList, paramView);
      }
    }
    else {
      return (ArrayList<View>)localObject;
    }
    return ArrayListManager.remove(paramArrayList, paramView);
  }
  
  private static ArrayMap<Animator, AnimationInfo> getRunningAnimators()
  {
    ArrayMap localArrayMap2 = (ArrayMap)sRunningAnimators.get();
    ArrayMap localArrayMap1 = localArrayMap2;
    if (localArrayMap2 == null)
    {
      localArrayMap1 = new ArrayMap();
      sRunningAnimators.set(localArrayMap1);
    }
    return localArrayMap1;
  }
  
  private void runAnimator(Animator paramAnimator, final ArrayMap<Animator, AnimationInfo> paramArrayMap)
  {
    if (paramAnimator != null)
    {
      paramAnimator.addListener(new AnimatorListenerAdapter()
      {
        public void onAnimationEnd(Animator paramAnonymousAnimator)
        {
          paramArrayMap.remove(paramAnonymousAnimator);
          TransitionPort.this.mCurrentAnimators.remove(paramAnonymousAnimator);
        }
        
        public void onAnimationStart(Animator paramAnonymousAnimator)
        {
          TransitionPort.this.mCurrentAnimators.add(paramAnonymousAnimator);
        }
      });
      animate(paramAnimator);
    }
  }
  
  public TransitionPort addListener(TransitionListener paramTransitionListener)
  {
    if (this.mListeners == null) {
      this.mListeners = new ArrayList();
    }
    this.mListeners.add(paramTransitionListener);
    return this;
  }
  
  public TransitionPort addTarget(int paramInt)
  {
    if (paramInt > 0) {
      this.mTargetIds.add(Integer.valueOf(paramInt));
    }
    return this;
  }
  
  public TransitionPort addTarget(View paramView)
  {
    this.mTargets.add(paramView);
    return this;
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  protected void animate(Animator paramAnimator)
  {
    if (paramAnimator == null)
    {
      end();
      return;
    }
    if (getDuration() >= 0L) {
      paramAnimator.setDuration(getDuration());
    }
    if (getStartDelay() >= 0L) {
      paramAnimator.setStartDelay(getStartDelay());
    }
    if (getInterpolator() != null) {
      paramAnimator.setInterpolator(getInterpolator());
    }
    paramAnimator.addListener(new AnimatorListenerAdapter()
    {
      public void onAnimationEnd(Animator paramAnonymousAnimator)
      {
        TransitionPort.this.end();
        paramAnonymousAnimator.removeListener(this);
      }
    });
    paramAnimator.start();
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  protected void cancel()
  {
    int i = this.mCurrentAnimators.size() - 1;
    while (i >= 0)
    {
      ((Animator)this.mCurrentAnimators.get(i)).cancel();
      i -= 1;
    }
    if ((this.mListeners != null) && (this.mListeners.size() > 0))
    {
      ArrayList localArrayList = (ArrayList)this.mListeners.clone();
      int j = localArrayList.size();
      i = 0;
      while (i < j)
      {
        ((TransitionListener)localArrayList.get(i)).onTransitionCancel(this);
        i += 1;
      }
    }
  }
  
  public abstract void captureEndValues(TransitionValues paramTransitionValues);
  
  public abstract void captureStartValues(TransitionValues paramTransitionValues);
  
  void captureValues(ViewGroup paramViewGroup, boolean paramBoolean)
  {
    clearValues(paramBoolean);
    if ((this.mTargetIds.size() > 0) || (this.mTargets.size() > 0))
    {
      int i;
      Object localObject;
      if (this.mTargetIds.size() > 0)
      {
        i = 0;
        if (i < this.mTargetIds.size())
        {
          int j = ((Integer)this.mTargetIds.get(i)).intValue();
          localObject = paramViewGroup.findViewById(j);
          TransitionValues localTransitionValues;
          if (localObject != null)
          {
            localTransitionValues = new TransitionValues();
            localTransitionValues.view = ((View)localObject);
            if (!paramBoolean) {
              break label148;
            }
            captureStartValues(localTransitionValues);
            label103:
            if (!paramBoolean) {
              break label157;
            }
            this.mStartValues.viewValues.put(localObject, localTransitionValues);
            if (j >= 0) {
              this.mStartValues.idValues.put(j, localTransitionValues);
            }
          }
          for (;;)
          {
            i += 1;
            break;
            label148:
            captureEndValues(localTransitionValues);
            break label103;
            label157:
            this.mEndValues.viewValues.put(localObject, localTransitionValues);
            if (j >= 0) {
              this.mEndValues.idValues.put(j, localTransitionValues);
            }
          }
        }
      }
      if (this.mTargets.size() > 0)
      {
        i = 0;
        if (i < this.mTargets.size())
        {
          paramViewGroup = (View)this.mTargets.get(i);
          if (paramViewGroup != null)
          {
            localObject = new TransitionValues();
            ((TransitionValues)localObject).view = paramViewGroup;
            if (!paramBoolean) {
              break label283;
            }
            captureStartValues((TransitionValues)localObject);
            label258:
            if (!paramBoolean) {
              break label292;
            }
            this.mStartValues.viewValues.put(paramViewGroup, localObject);
          }
          for (;;)
          {
            i += 1;
            break;
            label283:
            captureEndValues((TransitionValues)localObject);
            break label258;
            label292:
            this.mEndValues.viewValues.put(paramViewGroup, localObject);
          }
        }
      }
    }
    else
    {
      captureHierarchy(paramViewGroup, paramBoolean);
    }
  }
  
  void clearValues(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.mStartValues.viewValues.clear();
      this.mStartValues.idValues.clear();
      this.mStartValues.itemIdValues.clear();
      return;
    }
    this.mEndValues.viewValues.clear();
    this.mEndValues.idValues.clear();
    this.mEndValues.itemIdValues.clear();
  }
  
  public TransitionPort clone()
  {
    Object localObject = null;
    try
    {
      TransitionPort localTransitionPort = (TransitionPort)super.clone();
      localObject = localTransitionPort;
      localTransitionPort.mAnimators = new ArrayList();
      localObject = localTransitionPort;
      localTransitionPort.mStartValues = new TransitionValuesMaps();
      localObject = localTransitionPort;
      localTransitionPort.mEndValues = new TransitionValuesMaps();
      return localTransitionPort;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException) {}
    return (TransitionPort)localObject;
  }
  
  public Animator createAnimator(ViewGroup paramViewGroup, TransitionValues paramTransitionValues1, TransitionValues paramTransitionValues2)
  {
    return null;
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  protected void createAnimators(ViewGroup paramViewGroup, TransitionValuesMaps paramTransitionValuesMaps1, TransitionValuesMaps paramTransitionValuesMaps2)
  {
    Object localObject7 = new ArrayMap(paramTransitionValuesMaps2.viewValues);
    Object localObject6 = new SparseArray(paramTransitionValuesMaps2.idValues.size());
    int i = 0;
    while (i < paramTransitionValuesMaps2.idValues.size())
    {
      ((SparseArray)localObject6).put(paramTransitionValuesMaps2.idValues.keyAt(i), paramTransitionValuesMaps2.idValues.valueAt(i));
      i += 1;
    }
    Object localObject5 = new LongSparseArray(paramTransitionValuesMaps2.itemIdValues.size());
    i = 0;
    while (i < paramTransitionValuesMaps2.itemIdValues.size())
    {
      ((LongSparseArray)localObject5).put(paramTransitionValuesMaps2.itemIdValues.keyAt(i), paramTransitionValuesMaps2.itemIdValues.valueAt(i));
      i += 1;
    }
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    Iterator localIterator1 = paramTransitionValuesMaps1.viewValues.keySet().iterator();
    Object localObject1;
    label251:
    label340:
    Object localObject4;
    Object localObject3;
    long l;
    while (localIterator1.hasNext())
    {
      View localView = (View)localIterator1.next();
      localObject1 = null;
      i = 0;
      if ((localView.getParent() instanceof ListView)) {
        i = 1;
      }
      if (i == 0)
      {
        i = localView.getId();
        if (paramTransitionValuesMaps1.viewValues.get(localView) != null)
        {
          localObject2 = (TransitionValues)paramTransitionValuesMaps1.viewValues.get(localView);
          if (paramTransitionValuesMaps2.viewValues.get(localView) == null) {
            break label340;
          }
          localObject1 = (TransitionValues)paramTransitionValuesMaps2.viewValues.get(localView);
          ((ArrayMap)localObject7).remove(localView);
        }
        for (;;)
        {
          ((SparseArray)localObject6).remove(i);
          if (!isValidTarget(localView, i)) {
            break;
          }
          localArrayList1.add(localObject2);
          localArrayList2.add(localObject1);
          break;
          localObject2 = (TransitionValues)paramTransitionValuesMaps1.idValues.get(i);
          break label251;
          if (i != -1)
          {
            localObject4 = (TransitionValues)paramTransitionValuesMaps2.idValues.get(i);
            localObject3 = null;
            Iterator localIterator2 = ((ArrayMap)localObject7).keySet().iterator();
            while (localIterator2.hasNext())
            {
              localObject1 = (View)localIterator2.next();
              if (((View)localObject1).getId() == i) {
                localObject3 = localObject1;
              }
            }
            localObject1 = localObject4;
            if (localObject3 != null)
            {
              ((ArrayMap)localObject7).remove(localObject3);
              localObject1 = localObject4;
            }
          }
        }
      }
      localObject1 = (ListView)localView.getParent();
      if (((ListView)localObject1).getAdapter().hasStableIds())
      {
        l = ((ListView)localObject1).getItemIdAtPosition(((ListView)localObject1).getPositionForView(localView));
        localObject1 = (TransitionValues)paramTransitionValuesMaps1.itemIdValues.get(l);
        ((LongSparseArray)localObject5).remove(l);
        localArrayList1.add(localObject1);
        localArrayList2.add(null);
      }
    }
    int j = paramTransitionValuesMaps1.itemIdValues.size();
    i = 0;
    while (i < j)
    {
      l = paramTransitionValuesMaps1.itemIdValues.keyAt(i);
      if (isValidTarget(null, l))
      {
        localObject1 = (TransitionValues)paramTransitionValuesMaps1.itemIdValues.get(l);
        localObject2 = (TransitionValues)paramTransitionValuesMaps2.itemIdValues.get(l);
        ((LongSparseArray)localObject5).remove(l);
        localArrayList1.add(localObject1);
        localArrayList2.add(localObject2);
      }
      i += 1;
    }
    Object localObject2 = ((ArrayMap)localObject7).keySet().iterator();
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (View)((Iterator)localObject2).next();
      i = ((View)localObject3).getId();
      if (isValidTarget((View)localObject3, i))
      {
        if (paramTransitionValuesMaps1.viewValues.get(localObject3) != null) {}
        for (localObject1 = (TransitionValues)paramTransitionValuesMaps1.viewValues.get(localObject3);; localObject1 = (TransitionValues)paramTransitionValuesMaps1.idValues.get(i))
        {
          localObject3 = (TransitionValues)((ArrayMap)localObject7).get(localObject3);
          ((SparseArray)localObject6).remove(i);
          localArrayList1.add(localObject1);
          localArrayList2.add(localObject3);
          break;
        }
      }
    }
    j = ((SparseArray)localObject6).size();
    i = 0;
    int k;
    while (i < j)
    {
      k = ((SparseArray)localObject6).keyAt(i);
      if (isValidTarget(null, k))
      {
        localObject1 = (TransitionValues)paramTransitionValuesMaps1.idValues.get(k);
        localObject2 = (TransitionValues)((SparseArray)localObject6).get(k);
        localArrayList1.add(localObject1);
        localArrayList2.add(localObject2);
      }
      i += 1;
    }
    j = ((LongSparseArray)localObject5).size();
    i = 0;
    while (i < j)
    {
      l = ((LongSparseArray)localObject5).keyAt(i);
      localObject1 = (TransitionValues)paramTransitionValuesMaps1.itemIdValues.get(l);
      localObject2 = (TransitionValues)((LongSparseArray)localObject5).get(l);
      localArrayList1.add(localObject1);
      localArrayList2.add(localObject2);
      i += 1;
    }
    localObject6 = getRunningAnimators();
    i = 0;
    if (i < localArrayList1.size())
    {
      paramTransitionValuesMaps1 = (TransitionValues)localArrayList1.get(i);
      localObject1 = (TransitionValues)localArrayList2.get(i);
      if (((paramTransitionValuesMaps1 != null) || (localObject1 != null)) && ((paramTransitionValuesMaps1 == null) || (!paramTransitionValuesMaps1.equals(localObject1))))
      {
        localObject3 = createAnimator(paramViewGroup, paramTransitionValuesMaps1, (TransitionValues)localObject1);
        if (localObject3 != null)
        {
          localObject5 = null;
          if (localObject1 == null) {
            break label1316;
          }
          localObject4 = ((TransitionValues)localObject1).view;
          localObject7 = getTransitionProperties();
          localObject1 = localObject3;
          paramTransitionValuesMaps1 = (TransitionValuesMaps)localObject5;
          localObject2 = localObject4;
          if (localObject4 != null)
          {
            localObject1 = localObject3;
            paramTransitionValuesMaps1 = (TransitionValuesMaps)localObject5;
            localObject2 = localObject4;
            if (localObject7 != null)
            {
              localObject1 = localObject3;
              paramTransitionValuesMaps1 = (TransitionValuesMaps)localObject5;
              localObject2 = localObject4;
              if (localObject7.length > 0)
              {
                localObject5 = new TransitionValues();
                ((TransitionValues)localObject5).view = ((View)localObject4);
                paramTransitionValuesMaps1 = (TransitionValues)paramTransitionValuesMaps2.viewValues.get(localObject4);
                if (paramTransitionValuesMaps1 != null)
                {
                  j = 0;
                  while (j < localObject7.length)
                  {
                    ((TransitionValues)localObject5).values.put(localObject7[j], paramTransitionValuesMaps1.values.get(localObject7[j]));
                    j += 1;
                  }
                }
                k = ((ArrayMap)localObject6).size();
                j = 0;
                label1154:
                localObject1 = localObject3;
                paramTransitionValuesMaps1 = (TransitionValuesMaps)localObject5;
                localObject2 = localObject4;
                if (j < k)
                {
                  paramTransitionValuesMaps1 = (AnimationInfo)((ArrayMap)localObject6).get((Animator)((ArrayMap)localObject6).keyAt(j));
                  if ((paramTransitionValuesMaps1.values == null) || (paramTransitionValuesMaps1.view != localObject4) || (((paramTransitionValuesMaps1.name != null) || (getName() != null)) && ((!paramTransitionValuesMaps1.name.equals(getName())) || (!paramTransitionValuesMaps1.values.equals(localObject5))))) {
                    break label1307;
                  }
                  localObject1 = null;
                  localObject2 = localObject4;
                }
              }
            }
          }
        }
      }
      for (paramTransitionValuesMaps1 = (TransitionValuesMaps)localObject5;; paramTransitionValuesMaps1 = (TransitionValuesMaps)localObject5)
      {
        if (localObject1 != null)
        {
          ((ArrayMap)localObject6).put(localObject1, new AnimationInfo((View)localObject2, getName(), WindowIdPort.getWindowId(paramViewGroup), paramTransitionValuesMaps1));
          this.mAnimators.add(localObject1);
        }
        i += 1;
        break;
        label1307:
        j += 1;
        break label1154;
        label1316:
        localObject2 = paramTransitionValuesMaps1.view;
        localObject1 = localObject3;
      }
    }
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  protected void end()
  {
    this.mNumInstances -= 1;
    if (this.mNumInstances == 0)
    {
      Object localObject;
      if ((this.mListeners != null) && (this.mListeners.size() > 0))
      {
        localObject = (ArrayList)this.mListeners.clone();
        int j = ((ArrayList)localObject).size();
        i = 0;
        while (i < j)
        {
          ((TransitionListener)((ArrayList)localObject).get(i)).onTransitionEnd(this);
          i += 1;
        }
      }
      int i = 0;
      while (i < this.mStartValues.itemIdValues.size())
      {
        localObject = ((TransitionValues)this.mStartValues.itemIdValues.valueAt(i)).view;
        i += 1;
      }
      i = 0;
      while (i < this.mEndValues.itemIdValues.size())
      {
        localObject = ((TransitionValues)this.mEndValues.itemIdValues.valueAt(i)).view;
        i += 1;
      }
      this.mEnded = true;
    }
  }
  
  public TransitionPort excludeChildren(int paramInt, boolean paramBoolean)
  {
    this.mTargetIdChildExcludes = excludeId(this.mTargetIdChildExcludes, paramInt, paramBoolean);
    return this;
  }
  
  public TransitionPort excludeChildren(View paramView, boolean paramBoolean)
  {
    this.mTargetChildExcludes = excludeView(this.mTargetChildExcludes, paramView, paramBoolean);
    return this;
  }
  
  public TransitionPort excludeChildren(Class paramClass, boolean paramBoolean)
  {
    this.mTargetTypeChildExcludes = excludeType(this.mTargetTypeChildExcludes, paramClass, paramBoolean);
    return this;
  }
  
  public TransitionPort excludeTarget(int paramInt, boolean paramBoolean)
  {
    this.mTargetIdExcludes = excludeId(this.mTargetIdExcludes, paramInt, paramBoolean);
    return this;
  }
  
  public TransitionPort excludeTarget(View paramView, boolean paramBoolean)
  {
    this.mTargetExcludes = excludeView(this.mTargetExcludes, paramView, paramBoolean);
    return this;
  }
  
  public TransitionPort excludeTarget(Class paramClass, boolean paramBoolean)
  {
    this.mTargetTypeExcludes = excludeType(this.mTargetTypeExcludes, paramClass, paramBoolean);
    return this;
  }
  
  public long getDuration()
  {
    return this.mDuration;
  }
  
  public TimeInterpolator getInterpolator()
  {
    return this.mInterpolator;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public long getStartDelay()
  {
    return this.mStartDelay;
  }
  
  public List<Integer> getTargetIds()
  {
    return this.mTargetIds;
  }
  
  public List<View> getTargets()
  {
    return this.mTargets;
  }
  
  public String[] getTransitionProperties()
  {
    return null;
  }
  
  public TransitionValues getTransitionValues(View paramView, boolean paramBoolean)
  {
    Object localObject;
    if (this.mParent != null)
    {
      localObject = this.mParent.getTransitionValues(paramView, paramBoolean);
      return (TransitionValues)localObject;
    }
    if (paramBoolean) {}
    for (TransitionValuesMaps localTransitionValuesMaps = this.mStartValues;; localTransitionValuesMaps = this.mEndValues)
    {
      TransitionValues localTransitionValues = (TransitionValues)localTransitionValuesMaps.viewValues.get(paramView);
      localObject = localTransitionValues;
      if (localTransitionValues != null) {
        break;
      }
      int i = paramView.getId();
      if (i >= 0) {
        localTransitionValues = (TransitionValues)localTransitionValuesMaps.idValues.get(i);
      }
      localObject = localTransitionValues;
      if (localTransitionValues != null) {
        break;
      }
      localObject = localTransitionValues;
      if (!(paramView.getParent() instanceof ListView)) {
        break;
      }
      localObject = (ListView)paramView.getParent();
      long l = ((ListView)localObject).getItemIdAtPosition(((ListView)localObject).getPositionForView(paramView));
      return (TransitionValues)localTransitionValuesMaps.itemIdValues.get(l);
    }
  }
  
  boolean isValidTarget(View paramView, long paramLong)
  {
    if ((this.mTargetIdExcludes != null) && (this.mTargetIdExcludes.contains(Integer.valueOf((int)paramLong)))) {
      return false;
    }
    if ((this.mTargetExcludes != null) && (this.mTargetExcludes.contains(paramView))) {
      return false;
    }
    int i;
    if ((this.mTargetTypeExcludes != null) && (paramView != null))
    {
      int j = this.mTargetTypeExcludes.size();
      i = 0;
      while (i < j)
      {
        if (((Class)this.mTargetTypeExcludes.get(i)).isInstance(paramView)) {
          return false;
        }
        i += 1;
      }
    }
    if ((this.mTargetIds.size() == 0) && (this.mTargets.size() == 0)) {
      return true;
    }
    if (this.mTargetIds.size() > 0)
    {
      i = 0;
      while (i < this.mTargetIds.size())
      {
        if (((Integer)this.mTargetIds.get(i)).intValue() == paramLong) {
          return true;
        }
        i += 1;
      }
    }
    if ((paramView != null) && (this.mTargets.size() > 0))
    {
      i = 0;
      while (i < this.mTargets.size())
      {
        if (this.mTargets.get(i) == paramView) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void pause(View paramView)
  {
    if (!this.mEnded)
    {
      ArrayMap localArrayMap = getRunningAnimators();
      int i = localArrayMap.size();
      paramView = WindowIdPort.getWindowId(paramView);
      i -= 1;
      while (i >= 0)
      {
        AnimationInfo localAnimationInfo = (AnimationInfo)localArrayMap.valueAt(i);
        if ((localAnimationInfo.view != null) && (paramView.equals(localAnimationInfo.windowId))) {
          ((Animator)localArrayMap.keyAt(i)).cancel();
        }
        i -= 1;
      }
      if ((this.mListeners != null) && (this.mListeners.size() > 0))
      {
        paramView = (ArrayList)this.mListeners.clone();
        int j = paramView.size();
        i = 0;
        while (i < j)
        {
          ((TransitionListener)paramView.get(i)).onTransitionPause(this);
          i += 1;
        }
      }
      this.mPaused = true;
    }
  }
  
  void playTransition(ViewGroup paramViewGroup)
  {
    ArrayMap localArrayMap = getRunningAnimators();
    int i = localArrayMap.size() - 1;
    if (i >= 0)
    {
      Animator localAnimator = (Animator)localArrayMap.keyAt(i);
      Object localObject1;
      if (localAnimator != null)
      {
        localObject1 = (AnimationInfo)localArrayMap.get(localAnimator);
        if ((localObject1 != null) && (((AnimationInfo)localObject1).view != null) && (((AnimationInfo)localObject1).view.getContext() == paramViewGroup.getContext()))
        {
          int k = 0;
          TransitionValues localTransitionValues = ((AnimationInfo)localObject1).values;
          Object localObject3 = ((AnimationInfo)localObject1).view;
          if (this.mEndValues.viewValues == null) {
            break label286;
          }
          localObject1 = (TransitionValues)this.mEndValues.viewValues.get(localObject3);
          label117:
          Object localObject2 = localObject1;
          if (localObject1 == null) {
            localObject2 = (TransitionValues)this.mEndValues.idValues.get(((View)localObject3).getId());
          }
          int j = k;
          if (localTransitionValues != null)
          {
            j = k;
            if (localObject2 != null)
            {
              localObject1 = localTransitionValues.values.keySet().iterator();
              Object localObject4;
              do
              {
                j = k;
                if (!((Iterator)localObject1).hasNext()) {
                  break;
                }
                localObject4 = (String)((Iterator)localObject1).next();
                localObject3 = localTransitionValues.values.get(localObject4);
                localObject4 = ((TransitionValues)localObject2).values.get(localObject4);
              } while ((localObject3 == null) || (localObject4 == null) || (localObject3.equals(localObject4)));
              j = 1;
            }
          }
          if (j != 0)
          {
            if ((!localAnimator.isRunning()) && (!localAnimator.isStarted())) {
              break label292;
            }
            localAnimator.cancel();
          }
        }
      }
      for (;;)
      {
        i -= 1;
        break;
        label286:
        localObject1 = null;
        break label117;
        label292:
        localArrayMap.remove(localAnimator);
      }
    }
    createAnimators(paramViewGroup, this.mStartValues, this.mEndValues);
    runAnimators();
  }
  
  public TransitionPort removeListener(TransitionListener paramTransitionListener)
  {
    if (this.mListeners == null) {}
    do
    {
      return this;
      this.mListeners.remove(paramTransitionListener);
    } while (this.mListeners.size() != 0);
    this.mListeners = null;
    return this;
  }
  
  public TransitionPort removeTarget(int paramInt)
  {
    if (paramInt > 0) {
      this.mTargetIds.remove(Integer.valueOf(paramInt));
    }
    return this;
  }
  
  public TransitionPort removeTarget(View paramView)
  {
    if (paramView != null) {
      this.mTargets.remove(paramView);
    }
    return this;
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void resume(View paramView)
  {
    if (this.mPaused)
    {
      if (!this.mEnded)
      {
        ArrayMap localArrayMap = getRunningAnimators();
        int i = localArrayMap.size();
        paramView = WindowIdPort.getWindowId(paramView);
        i -= 1;
        while (i >= 0)
        {
          AnimationInfo localAnimationInfo = (AnimationInfo)localArrayMap.valueAt(i);
          if ((localAnimationInfo.view != null) && (paramView.equals(localAnimationInfo.windowId))) {
            ((Animator)localArrayMap.keyAt(i)).end();
          }
          i -= 1;
        }
        if ((this.mListeners != null) && (this.mListeners.size() > 0))
        {
          paramView = (ArrayList)this.mListeners.clone();
          int j = paramView.size();
          i = 0;
          while (i < j)
          {
            ((TransitionListener)paramView.get(i)).onTransitionResume(this);
            i += 1;
          }
        }
      }
      this.mPaused = false;
    }
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  protected void runAnimators()
  {
    start();
    ArrayMap localArrayMap = getRunningAnimators();
    Iterator localIterator = this.mAnimators.iterator();
    while (localIterator.hasNext())
    {
      Animator localAnimator = (Animator)localIterator.next();
      if (localArrayMap.containsKey(localAnimator))
      {
        start();
        runAnimator(localAnimator, localArrayMap);
      }
    }
    this.mAnimators.clear();
    end();
  }
  
  void setCanRemoveViews(boolean paramBoolean)
  {
    this.mCanRemoveViews = paramBoolean;
  }
  
  public TransitionPort setDuration(long paramLong)
  {
    this.mDuration = paramLong;
    return this;
  }
  
  public TransitionPort setInterpolator(TimeInterpolator paramTimeInterpolator)
  {
    this.mInterpolator = paramTimeInterpolator;
    return this;
  }
  
  TransitionPort setSceneRoot(ViewGroup paramViewGroup)
  {
    this.mSceneRoot = paramViewGroup;
    return this;
  }
  
  public TransitionPort setStartDelay(long paramLong)
  {
    this.mStartDelay = paramLong;
    return this;
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  protected void start()
  {
    if (this.mNumInstances == 0)
    {
      if ((this.mListeners != null) && (this.mListeners.size() > 0))
      {
        ArrayList localArrayList = (ArrayList)this.mListeners.clone();
        int j = localArrayList.size();
        int i = 0;
        while (i < j)
        {
          ((TransitionListener)localArrayList.get(i)).onTransitionStart(this);
          i += 1;
        }
      }
      this.mEnded = false;
    }
    this.mNumInstances += 1;
  }
  
  public String toString()
  {
    return toString("");
  }
  
  String toString(String paramString)
  {
    String str = paramString + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + ": ";
    paramString = str;
    if (this.mDuration != -1L) {
      paramString = str + "dur(" + this.mDuration + ") ";
    }
    str = paramString;
    if (this.mStartDelay != -1L) {
      str = paramString + "dly(" + this.mStartDelay + ") ";
    }
    paramString = str;
    if (this.mInterpolator != null) {
      paramString = str + "interp(" + this.mInterpolator + ") ";
    }
    if (this.mTargetIds.size() <= 0)
    {
      str = paramString;
      if (this.mTargets.size() <= 0) {}
    }
    else
    {
      str = paramString + "tgts(";
      paramString = str;
      int i;
      if (this.mTargetIds.size() > 0)
      {
        i = 0;
        for (;;)
        {
          paramString = str;
          if (i >= this.mTargetIds.size()) {
            break;
          }
          paramString = str;
          if (i > 0) {
            paramString = str + ", ";
          }
          str = paramString + this.mTargetIds.get(i);
          i += 1;
        }
      }
      str = paramString;
      if (this.mTargets.size() > 0)
      {
        i = 0;
        for (;;)
        {
          str = paramString;
          if (i >= this.mTargets.size()) {
            break;
          }
          str = paramString;
          if (i > 0) {
            str = paramString + ", ";
          }
          paramString = str + this.mTargets.get(i);
          i += 1;
        }
      }
      str = str + ")";
    }
    return str;
  }
  
  private static class AnimationInfo
  {
    String name;
    TransitionValues values;
    View view;
    WindowIdPort windowId;
    
    AnimationInfo(View paramView, String paramString, WindowIdPort paramWindowIdPort, TransitionValues paramTransitionValues)
    {
      this.view = paramView;
      this.name = paramString;
      this.values = paramTransitionValues;
      this.windowId = paramWindowIdPort;
    }
  }
  
  private static class ArrayListManager
  {
    static <T> ArrayList<T> add(ArrayList<T> paramArrayList, T paramT)
    {
      Object localObject = paramArrayList;
      if (paramArrayList == null) {
        localObject = new ArrayList();
      }
      if (!((ArrayList)localObject).contains(paramT)) {
        ((ArrayList)localObject).add(paramT);
      }
      return (ArrayList<T>)localObject;
    }
    
    static <T> ArrayList<T> remove(ArrayList<T> paramArrayList, T paramT)
    {
      ArrayList<T> localArrayList = paramArrayList;
      if (paramArrayList != null)
      {
        paramArrayList.remove(paramT);
        localArrayList = paramArrayList;
        if (paramArrayList.isEmpty()) {
          localArrayList = null;
        }
      }
      return localArrayList;
    }
  }
  
  public static abstract interface TransitionListener
  {
    public abstract void onTransitionCancel(TransitionPort paramTransitionPort);
    
    public abstract void onTransitionEnd(TransitionPort paramTransitionPort);
    
    public abstract void onTransitionPause(TransitionPort paramTransitionPort);
    
    public abstract void onTransitionResume(TransitionPort paramTransitionPort);
    
    public abstract void onTransitionStart(TransitionPort paramTransitionPort);
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static class TransitionListenerAdapter
    implements TransitionPort.TransitionListener
  {
    public void onTransitionCancel(TransitionPort paramTransitionPort) {}
    
    public void onTransitionEnd(TransitionPort paramTransitionPort) {}
    
    public void onTransitionPause(TransitionPort paramTransitionPort) {}
    
    public void onTransitionResume(TransitionPort paramTransitionPort) {}
    
    public void onTransitionStart(TransitionPort paramTransitionPort) {}
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\android\support\transition\TransitionPort.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */