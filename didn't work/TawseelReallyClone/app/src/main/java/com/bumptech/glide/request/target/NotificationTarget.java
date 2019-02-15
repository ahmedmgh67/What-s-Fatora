package com.bumptech.glide.request.target;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import com.bumptech.glide.request.animation.GlideAnimation;

public class NotificationTarget
  extends SimpleTarget<Bitmap>
{
  private final Context context;
  private final Notification notification;
  private final int notificationId;
  private final RemoteViews remoteViews;
  private final int viewId;
  
  public NotificationTarget(Context paramContext, RemoteViews paramRemoteViews, int paramInt1, int paramInt2, int paramInt3, Notification paramNotification, int paramInt4)
  {
    super(paramInt2, paramInt3);
    if (paramContext == null) {
      throw new NullPointerException("Context must not be null!");
    }
    if (paramNotification == null) {
      throw new NullPointerException("Notification object can not be null!");
    }
    if (paramRemoteViews == null) {
      throw new NullPointerException("RemoteViews object can not be null!");
    }
    this.context = paramContext;
    this.viewId = paramInt1;
    this.notification = paramNotification;
    this.notificationId = paramInt4;
    this.remoteViews = paramRemoteViews;
  }
  
  public NotificationTarget(Context paramContext, RemoteViews paramRemoteViews, int paramInt1, Notification paramNotification, int paramInt2)
  {
    this(paramContext, paramRemoteViews, paramInt1, Integer.MIN_VALUE, Integer.MIN_VALUE, paramNotification, paramInt2);
  }
  
  private void update()
  {
    ((NotificationManager)this.context.getSystemService("notification")).notify(this.notificationId, this.notification);
  }
  
  public void onResourceReady(Bitmap paramBitmap, GlideAnimation<? super Bitmap> paramGlideAnimation)
  {
    this.remoteViews.setImageViewBitmap(this.viewId, paramBitmap);
    update();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\request\target\NotificationTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */