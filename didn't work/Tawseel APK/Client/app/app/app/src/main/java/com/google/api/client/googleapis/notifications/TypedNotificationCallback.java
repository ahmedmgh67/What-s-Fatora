package com.google.api.client.googleapis.notifications;

import com.google.api.client.http.HttpMediaType;
import com.google.api.client.util.Beta;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.nio.charset.Charset;

@Beta
public abstract class TypedNotificationCallback<T>
  implements UnparsedNotificationCallback
{
  private static final long serialVersionUID = 1L;
  
  protected abstract Class<T> getDataClass()
    throws IOException;
  
  protected abstract ObjectParser getObjectParser()
    throws IOException;
  
  protected abstract void onNotification(StoredChannel paramStoredChannel, TypedNotification<T> paramTypedNotification)
    throws IOException;
  
  public final void onNotification(StoredChannel paramStoredChannel, UnparsedNotification paramUnparsedNotification)
    throws IOException
  {
    TypedNotification localTypedNotification = new TypedNotification(paramUnparsedNotification);
    Object localObject = paramUnparsedNotification.getContentType();
    if (localObject != null)
    {
      localObject = new HttpMediaType((String)localObject).getCharsetParameter();
      Class localClass = (Class)Preconditions.checkNotNull(getDataClass());
      localTypedNotification.setContent(getObjectParser().parseAndClose(paramUnparsedNotification.getContentStream(), (Charset)localObject, localClass));
    }
    onNotification(paramStoredChannel, localTypedNotification);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\notifications\TypedNotificationCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */