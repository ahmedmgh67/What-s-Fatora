package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.support.annotation.Nullable;

public abstract interface TintableCompoundButton
{
  @Nullable
  public abstract ColorStateList getSupportButtonTintList();
  
  @Nullable
  public abstract PorterDuff.Mode getSupportButtonTintMode();
  
  public abstract void setSupportButtonTintList(@Nullable ColorStateList paramColorStateList);
  
  public abstract void setSupportButtonTintMode(@Nullable PorterDuff.Mode paramMode);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\android\support\v4\widget\TintableCompoundButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */