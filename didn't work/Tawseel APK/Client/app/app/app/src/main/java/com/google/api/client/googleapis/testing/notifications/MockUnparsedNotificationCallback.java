package com.google.api.client.googleapis.testing.notifications;

import com.google.api.client.googleapis.notifications.StoredChannel;
import com.google.api.client.googleapis.notifications.UnparsedNotification;
import com.google.api.client.googleapis.notifications.UnparsedNotificationCallback;
import com.google.api.client.util.Beta;
import java.io.IOException;

@Beta
public class MockUnparsedNotificationCallback
  implements UnparsedNotificationCallback
{
  private static final long serialVersionUID = 0L;
  private boolean wasCalled;
  
  public void onNotification(StoredChannel paramStoredChannel, UnparsedNotification paramUnparsedNotification)
    throws IOException
  {
    this.wasCalled = true;
  }
  
  public boolean wasCalled()
  {
    return this.wasCalled;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\testing\notifications\MockUnparsedNotificationCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */