package android.support.design.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.design.R.dimen;
import android.support.v4.util.Pools.Pool;
import android.support.v4.util.Pools.SynchronizedPool;
import android.support.v4.view.ViewCompat;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuView;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class BottomNavigationMenuView
  extends ViewGroup
  implements MenuView
{
  private static final Pools.Pool<BottomNavigationItemView> sItemPool = new Pools.SynchronizedPool(5);
  private int mActiveButton = 0;
  private final int mActiveItemMaxWidth;
  private final BottomNavigationAnimationHelperBase mAnimationHelper;
  private BottomNavigationItemView[] mButtons;
  private final int mInactiveItemMaxWidth;
  private final int mInactiveItemMinWidth;
  private int mItemBackgroundRes;
  private final int mItemHeight;
  private ColorStateList mItemIconTint;
  private ColorStateList mItemTextColor;
  private MenuBuilder mMenu;
  private final View.OnClickListener mOnClickListener;
  private BottomNavigationPresenter mPresenter;
  private boolean mShiftingMode = true;
  private int[] mTempChildWidths;
  
  public BottomNavigationMenuView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public BottomNavigationMenuView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    paramContext = getResources();
    this.mInactiveItemMaxWidth = paramContext.getDimensionPixelSize(R.dimen.design_bottom_navigation_item_max_width);
    this.mInactiveItemMinWidth = paramContext.getDimensionPixelSize(R.dimen.design_bottom_navigation_item_min_width);
    this.mActiveItemMaxWidth = paramContext.getDimensionPixelSize(R.dimen.design_bottom_navigation_active_item_max_width);
    this.mItemHeight = paramContext.getDimensionPixelSize(R.dimen.design_bottom_navigation_height);
    if (Build.VERSION.SDK_INT >= 14) {}
    for (this.mAnimationHelper = new BottomNavigationAnimationHelperIcs();; this.mAnimationHelper = new BottomNavigationAnimationHelperBase())
    {
      this.mOnClickListener = new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = (BottomNavigationItemView)paramAnonymousView;
          int i = paramAnonymousView.getItemPosition();
          if (!BottomNavigationMenuView.this.mMenu.performItemAction(paramAnonymousView.getItemData(), BottomNavigationMenuView.this.mPresenter, 0)) {
            BottomNavigationMenuView.this.activateNewButton(i);
          }
        }
      };
      this.mTempChildWidths = new int[5];
      return;
    }
  }
  
  private void activateNewButton(int paramInt)
  {
    if (this.mActiveButton == paramInt) {
      return;
    }
    this.mAnimationHelper.beginDelayedTransition(this);
    this.mMenu.getItem(paramInt).setChecked(true);
    this.mActiveButton = paramInt;
  }
  
  private BottomNavigationItemView getNewItem()
  {
    BottomNavigationItemView localBottomNavigationItemView2 = (BottomNavigationItemView)sItemPool.acquire();
    BottomNavigationItemView localBottomNavigationItemView1 = localBottomNavigationItemView2;
    if (localBottomNavigationItemView2 == null) {
      localBottomNavigationItemView1 = new BottomNavigationItemView(getContext());
    }
    return localBottomNavigationItemView1;
  }
  
  public void buildMenuView()
  {
    Object localObject1;
    int i;
    if (this.mButtons != null)
    {
      localObject1 = this.mButtons;
      int j = localObject1.length;
      i = 0;
      while (i < j)
      {
        Object localObject2 = localObject1[i];
        sItemPool.release(localObject2);
        i += 1;
      }
    }
    removeAllViews();
    if (this.mMenu.size() == 0) {
      return;
    }
    this.mButtons = new BottomNavigationItemView[this.mMenu.size()];
    if (this.mMenu.size() > 3) {}
    for (boolean bool = true;; bool = false)
    {
      this.mShiftingMode = bool;
      i = 0;
      while (i < this.mMenu.size())
      {
        this.mPresenter.setUpdateSuspended(true);
        this.mMenu.getItem(i).setCheckable(true);
        this.mPresenter.setUpdateSuspended(false);
        localObject1 = getNewItem();
        this.mButtons[i] = localObject1;
        ((BottomNavigationItemView)localObject1).setIconTintList(this.mItemIconTint);
        ((BottomNavigationItemView)localObject1).setTextColor(this.mItemTextColor);
        ((BottomNavigationItemView)localObject1).setItemBackground(this.mItemBackgroundRes);
        ((BottomNavigationItemView)localObject1).setShiftingMode(this.mShiftingMode);
        ((BottomNavigationItemView)localObject1).initialize((MenuItemImpl)this.mMenu.getItem(i), 0);
        ((BottomNavigationItemView)localObject1).setItemPosition(i);
        ((BottomNavigationItemView)localObject1).setOnClickListener(this.mOnClickListener);
        addView((View)localObject1);
        i += 1;
      }
    }
    this.mActiveButton = Math.min(this.mMenu.size() - 1, this.mActiveButton);
    this.mMenu.getItem(this.mActiveButton).setChecked(true);
  }
  
  @Nullable
  public ColorStateList getIconTintList()
  {
    return this.mItemIconTint;
  }
  
  public int getItemBackgroundRes()
  {
    return this.mItemBackgroundRes;
  }
  
  public ColorStateList getItemTextColor()
  {
    return this.mItemTextColor;
  }
  
  public int getWindowAnimations()
  {
    return 0;
  }
  
  public void initialize(MenuBuilder paramMenuBuilder)
  {
    this.mMenu = paramMenuBuilder;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = getChildCount();
    paramInt3 -= paramInt1;
    paramInt4 -= paramInt2;
    paramInt2 = 0;
    paramInt1 = 0;
    while (paramInt1 < i)
    {
      View localView = getChildAt(paramInt1);
      if (localView.getVisibility() == 8)
      {
        paramInt1 += 1;
      }
      else
      {
        if (ViewCompat.getLayoutDirection(this) == 1) {
          localView.layout(paramInt3 - paramInt2 - localView.getMeasuredWidth(), 0, paramInt3 - paramInt2, paramInt4);
        }
        for (;;)
        {
          paramInt2 += localView.getMeasuredWidth();
          break;
          localView.layout(paramInt2, 0, localView.getMeasuredWidth() + paramInt2, paramInt4);
        }
      }
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt2 = View.MeasureSpec.getSize(paramInt1);
    int j = getChildCount();
    int n = View.MeasureSpec.makeMeasureSpec(this.mItemHeight, 1073741824);
    int k;
    Object localObject;
    int i;
    if (this.mShiftingMode)
    {
      paramInt1 = j - 1;
      k = Math.min(paramInt2 - this.mInactiveItemMinWidth * paramInt1, this.mActiveItemMaxWidth);
      int m = Math.min((paramInt2 - k) / paramInt1, this.mInactiveItemMaxWidth);
      paramInt2 = paramInt2 - k - m * paramInt1;
      paramInt1 = 0;
      if (paramInt1 < j)
      {
        localObject = this.mTempChildWidths;
        if (paramInt1 == this.mActiveButton) {}
        for (i = k;; i = m)
        {
          localObject[paramInt1] = i;
          i = paramInt2;
          if (paramInt2 > 0)
          {
            localObject = this.mTempChildWidths;
            localObject[paramInt1] += 1;
            i = paramInt2 - 1;
          }
          paramInt1 += 1;
          paramInt2 = i;
          break;
        }
      }
    }
    else
    {
      if (j == 0) {}
      for (paramInt1 = 1;; paramInt1 = j)
      {
        k = Math.min(paramInt2 / paramInt1, this.mActiveItemMaxWidth);
        paramInt2 -= k * j;
        paramInt1 = 0;
        while (paramInt1 < j)
        {
          this.mTempChildWidths[paramInt1] = k;
          i = paramInt2;
          if (paramInt2 > 0)
          {
            localObject = this.mTempChildWidths;
            localObject[paramInt1] += 1;
            i = paramInt2 - 1;
          }
          paramInt1 += 1;
          paramInt2 = i;
        }
      }
    }
    paramInt2 = 0;
    paramInt1 = 0;
    if (paramInt1 < j)
    {
      localObject = getChildAt(paramInt1);
      if (((View)localObject).getVisibility() == 8) {}
      for (;;)
      {
        paramInt1 += 1;
        break;
        ((View)localObject).measure(View.MeasureSpec.makeMeasureSpec(this.mTempChildWidths[paramInt1], 1073741824), n);
        ((View)localObject).getLayoutParams().width = ((View)localObject).getMeasuredWidth();
        paramInt2 += ((View)localObject).getMeasuredWidth();
      }
    }
    setMeasuredDimension(ViewCompat.resolveSizeAndState(paramInt2, View.MeasureSpec.makeMeasureSpec(paramInt2, 1073741824), 0), ViewCompat.resolveSizeAndState(this.mItemHeight, n, 0));
  }
  
  public void setIconTintList(ColorStateList paramColorStateList)
  {
    this.mItemIconTint = paramColorStateList;
    if (this.mButtons == null) {}
    for (;;)
    {
      return;
      BottomNavigationItemView[] arrayOfBottomNavigationItemView = this.mButtons;
      int j = arrayOfBottomNavigationItemView.length;
      int i = 0;
      while (i < j)
      {
        arrayOfBottomNavigationItemView[i].setIconTintList(paramColorStateList);
        i += 1;
      }
    }
  }
  
  public void setItemBackgroundRes(int paramInt)
  {
    this.mItemBackgroundRes = paramInt;
    if (this.mButtons == null) {}
    for (;;)
    {
      return;
      BottomNavigationItemView[] arrayOfBottomNavigationItemView = this.mButtons;
      int j = arrayOfBottomNavigationItemView.length;
      int i = 0;
      while (i < j)
      {
        arrayOfBottomNavigationItemView[i].setItemBackground(paramInt);
        i += 1;
      }
    }
  }
  
  public void setItemTextColor(ColorStateList paramColorStateList)
  {
    this.mItemTextColor = paramColorStateList;
    if (this.mButtons == null) {}
    for (;;)
    {
      return;
      BottomNavigationItemView[] arrayOfBottomNavigationItemView = this.mButtons;
      int j = arrayOfBottomNavigationItemView.length;
      int i = 0;
      while (i < j)
      {
        arrayOfBottomNavigationItemView[i].setTextColor(paramColorStateList);
        i += 1;
      }
    }
  }
  
  public void setPresenter(BottomNavigationPresenter paramBottomNavigationPresenter)
  {
    this.mPresenter = paramBottomNavigationPresenter;
  }
  
  public void updateMenuView()
  {
    int j = this.mMenu.size();
    if (j != this.mButtons.length) {
      buildMenuView();
    }
    for (;;)
    {
      return;
      int i = 0;
      while (i < j)
      {
        this.mPresenter.setUpdateSuspended(true);
        if (this.mMenu.getItem(i).isChecked()) {
          this.mActiveButton = i;
        }
        this.mButtons[i].initialize((MenuItemImpl)this.mMenu.getItem(i), 0);
        this.mPresenter.setUpdateSuspended(false);
        i += 1;
      }
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\android\support\design\internal\BottomNavigationMenuView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */