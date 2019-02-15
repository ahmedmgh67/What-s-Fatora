package com.google.api.client.googleapis.notifications;

import com.google.api.client.util.Beta;
import java.io.IOException;
import java.io.Serializable;

@Beta
public abstract interface UnparsedNotificationCallback
  extends Serializable
{
  public abstract void onNotification(StoredChannel paramStoredChannel, UnparsedNotification paramUnparsedNotification)
    throws IOException;
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\notifications\UnparsedNotificationCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */