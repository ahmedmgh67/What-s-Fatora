package com.bumptech.glide.request.target;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.WindowManager;
import com.bumptech.glide.request.Request;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class ViewTarget<T extends View, Z>
  extends BaseTarget<Z>
{
  private static final String TAG = "ViewTarget";
  private static boolean isTagUsedAtLeastOnce = false;
  private static Integer tagId = null;
  private final SizeDeterminer sizeDeterminer;
  protected final T view;
  
  public ViewTarget(T paramT)
  {
    if (paramT == null) {
      throw new NullPointerException("View must not be null!");
    }
    this.view = paramT;
    this.sizeDeterminer = new SizeDeterminer(paramT);
  }
  
  private Object getTag()
  {
    if (tagId == null) {
      return this.view.getTag();
    }
    return this.view.getTag(tagId.intValue());
  }
  
  private void setTag(Object paramObject)
  {
    if (tagId == null)
    {
      isTagUsedAtLeastOnce = true;
      this.view.setTag(paramObject);
      return;
    }
    this.view.setTag(tagId.intValue(), paramObject);
  }
  
  public static void setTagId(int paramInt)
  {
    if ((tagId != null) || (isTagUsedAtLeastOnce)) {
      throw new IllegalArgumentException("You cannot set the tag id more than once or change the tag id after the first request has been made");
    }
    tagId = Integer.valueOf(paramInt);
  }
  
  public Request getRequest()
  {
    Object localObject = getTag();
    Request localRequest = null;
    if (localObject != null)
    {
      if ((localObject instanceof Request)) {
        localRequest = (Request)localObject;
      }
    }
    else {
      return localRequest;
    }
    throw new IllegalArgumentException("You must not call setTag() on a view Glide is targeting");
  }
  
  public void getSize(SizeReadyCallback paramSizeReadyCallback)
  {
    this.sizeDeterminer.getSize(paramSizeReadyCallback);
  }
  
  public T getView()
  {
    return this.view;
  }
  
  public void setRequest(Request paramRequest)
  {
    setTag(paramRequest);
  }
  
  public String toString()
  {
    return "Target for: " + this.view;
  }
  
  private static class SizeDeterminer
  {
    private static final int PENDING_SIZE = 0;
    private final List<SizeReadyCallback> cbs = new ArrayList();
    private Point displayDimens;
    private SizeDeterminerLayoutListener layoutListener;
    private final View view;
    
    public SizeDeterminer(View paramView)
    {
      this.view = paramView;
    }
    
    private void checkCurrentDimens()
    {
      if (this.cbs.isEmpty()) {}
      int i;
      int j;
      do
      {
        return;
        i = getViewWidthOrParam();
        j = getViewHeightOrParam();
      } while ((!isSizeValid(i)) || (!isSizeValid(j)));
      notifyCbs(i, j);
      ViewTreeObserver localViewTreeObserver = this.view.getViewTreeObserver();
      if (localViewTreeObserver.isAlive()) {
        localViewTreeObserver.removeOnPreDrawListener(this.layoutListener);
      }
      this.layoutListener = null;
    }
    
    @TargetApi(13)
    private Point getDisplayDimens()
    {
      if (this.displayDimens != null) {
        return this.displayDimens;
      }
      Display localDisplay = ((WindowManager)this.view.getContext().getSystemService("window")).getDefaultDisplay();
      if (Build.VERSION.SDK_INT >= 13)
      {
        this.displayDimens = new Point();
        localDisplay.getSize(this.displayDimens);
      }
      for (;;)
      {
        return this.displayDimens;
        this.displayDimens = new Point(localDisplay.getWidth(), localDisplay.getHeight());
      }
    }
    
    private int getSizeForParam(int paramInt, boolean paramBoolean)
    {
      if (paramInt == -2)
      {
        Point localPoint = getDisplayDimens();
        if (paramBoolean) {
          return localPoint.y;
        }
        return localPoint.x;
      }
      return paramInt;
    }
    
    private int getViewHeightOrParam()
    {
      ViewGroup.LayoutParams localLayoutParams = this.view.getLayoutParams();
      if (isSizeValid(this.view.getHeight())) {
        return this.view.getHeight();
      }
      if (localLayoutParams != null) {
        return getSizeForParam(localLayoutParams.height, true);
      }
      return 0;
    }
    
    private int getViewWidthOrParam()
    {
      int i = 0;
      ViewGroup.LayoutParams localLayoutParams = this.view.getLayoutParams();
      if (isSizeValid(this.view.getWidth())) {
        i = this.view.getWidth();
      }
      while (localLayoutParams == null) {
        return i;
      }
      return getSizeForParam(localLayoutParams.width, false);
    }
    
    private boolean isSizeValid(int paramInt)
    {
      return (paramInt > 0) || (paramInt == -2);
    }
    
    private void notifyCbs(int paramInt1, int paramInt2)
    {
      Iterator localIterator = this.cbs.iterator();
      while (localIterator.hasNext()) {
        ((SizeReadyCallback)localIterator.next()).onSizeReady(paramInt1, paramInt2);
      }
      this.cbs.clear();
    }
    
    public void getSize(SizeReadyCallback paramSizeReadyCallback)
    {
      int i = getViewWidthOrParam();
      int j = getViewHeightOrParam();
      if ((isSizeValid(i)) && (isSizeValid(j))) {
        paramSizeReadyCallback.onSizeReady(i, j);
      }
      do
      {
        return;
        if (!this.cbs.contains(paramSizeReadyCallback)) {
          this.cbs.add(paramSizeReadyCallback);
        }
      } while (this.layoutListener != null);
      paramSizeReadyCallback = this.view.getViewTreeObserver();
      this.layoutListener = new SizeDeterminerLayoutListener(this);
      paramSizeReadyCallback.addOnPreDrawListener(this.layoutListener);
    }
    
    private static class SizeDeterminerLayoutListener
      implements ViewTreeObserver.OnPreDrawListener
    {
      private final WeakReference<ViewTarget.SizeDeterminer> sizeDeterminerRef;
      
      public SizeDeterminerLayoutListener(ViewTarget.SizeDeterminer paramSizeDeterminer)
      {
        this.sizeDeterminerRef = new WeakReference(paramSizeDeterminer);
      }
      
      public boolean onPreDraw()
      {
        if (Log.isLoggable("ViewTarget", 2)) {
          Log.v("ViewTarget", "OnGlobalLayoutListener called listener=" + this);
        }
        ViewTarget.SizeDeterminer localSizeDeterminer = (ViewTarget.SizeDeterminer)this.sizeDeterminerRef.get();
        if (localSizeDeterminer != null) {
          localSizeDeterminer.checkCurrentDimens();
        }
        return true;
      }
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\request\target\ViewTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */