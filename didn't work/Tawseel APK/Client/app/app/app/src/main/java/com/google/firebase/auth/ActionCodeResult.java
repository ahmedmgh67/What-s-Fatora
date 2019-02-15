package com.google.firebase.auth;

import android.support.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract interface ActionCodeResult
{
  public static final int EMAIL = 0;
  public static final int ERROR = 3;
  public static final int FROM_EMAIL = 1;
  public static final int PASSWORD_RESET = 0;
  public static final int RECOVER_EMAIL = 2;
  public static final int VERIFY_EMAIL = 1;
  
  @Nullable
  public abstract String getData(int paramInt);
  
  public abstract int getOperation();
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ActionDataKey {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Operation {}
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\auth\ActionCodeResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */