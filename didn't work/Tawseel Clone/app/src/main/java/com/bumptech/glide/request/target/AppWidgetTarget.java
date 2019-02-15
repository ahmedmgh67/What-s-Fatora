package com.bumptech.glide.request.target;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import com.bumptech.glide.request.animation.GlideAnimation;

public class AppWidgetTarget
  extends SimpleTarget<Bitmap>
{
  private final ComponentName componentName;
  private final Context context;
  private final RemoteViews remoteViews;
  private final int viewId;
  private final int[] widgetIds;
  
  public AppWidgetTarget(Context paramContext, RemoteViews paramRemoteViews, int paramInt1, int paramInt2, int paramInt3, ComponentName paramComponentName)
  {
    super(paramInt2, paramInt3);
    if (paramContext == null) {
      throw new NullPointerException("Context can not be null!");
    }
    if (paramComponentName == null) {
      throw new NullPointerException("ComponentName can not be null!");
    }
    if (paramRemoteViews == null) {
      throw new NullPointerException("RemoteViews object can not be null!");
    }
    this.context = paramContext;
    this.remoteViews = paramRemoteViews;
    this.viewId = paramInt1;
    this.componentName = paramComponentName;
    this.widgetIds = null;
  }
  
  public AppWidgetTarget(Context paramContext, RemoteViews paramRemoteViews, int paramInt1, int paramInt2, int paramInt3, int... paramVarArgs)
  {
    super(paramInt2, paramInt3);
    if (paramContext == null) {
      throw new NullPointerException("Context can not be null!");
    }
    if (paramVarArgs == null) {
      throw new NullPointerException("WidgetIds can not be null!");
    }
    if (paramVarArgs.length == 0) {
      throw new IllegalArgumentException("WidgetIds must have length > 0");
    }
    if (paramRemoteViews == null) {
      throw new NullPointerException("RemoteViews object can not be null!");
    }
    this.context = paramContext;
    this.remoteViews = paramRemoteViews;
    this.viewId = paramInt1;
    this.widgetIds = paramVarArgs;
    this.componentName = null;
  }
  
  public AppWidgetTarget(Context paramContext, RemoteViews paramRemoteViews, int paramInt, ComponentName paramComponentName)
  {
    this(paramContext, paramRemoteViews, paramInt, Integer.MIN_VALUE, Integer.MIN_VALUE, paramComponentName);
  }
  
  public AppWidgetTarget(Context paramContext, RemoteViews paramRemoteViews, int paramInt, int... paramVarArgs)
  {
    this(paramContext, paramRemoteViews, paramInt, Integer.MIN_VALUE, Integer.MIN_VALUE, paramVarArgs);
  }
  
  private void update()
  {
    AppWidgetManager localAppWidgetManager = AppWidgetManager.getInstance(this.context);
    if (this.componentName != null)
    {
      localAppWidgetManager.updateAppWidget(this.componentName, this.remoteViews);
      return;
    }
    localAppWidgetManager.updateAppWidget(this.widgetIds, this.remoteViews);
  }
  
  public void onResourceReady(Bitmap paramBitmap, GlideAnimation<? super Bitmap> paramGlideAnimation)
  {
    this.remoteViews.setImageViewBitmap(this.viewId, paramBitmap);
    update();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\request\target\AppWidgetTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */