package com.google.api.client.googleapis.media;

import com.google.api.client.http.HttpIOExceptionHandler;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpUnsuccessfulResponseHandler;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Beta
class MediaUploadErrorHandler
  implements HttpUnsuccessfulResponseHandler, HttpIOExceptionHandler
{
  static final Logger LOGGER = Logger.getLogger(MediaUploadErrorHandler.class.getName());
  private final HttpIOExceptionHandler originalIOExceptionHandler;
  private final HttpUnsuccessfulResponseHandler originalUnsuccessfulHandler;
  private final MediaHttpUploader uploader;
  
  public MediaUploadErrorHandler(MediaHttpUploader paramMediaHttpUploader, HttpRequest paramHttpRequest)
  {
    this.uploader = ((MediaHttpUploader)Preconditions.checkNotNull(paramMediaHttpUploader));
    this.originalIOExceptionHandler = paramHttpRequest.getIOExceptionHandler();
    this.originalUnsuccessfulHandler = paramHttpRequest.getUnsuccessfulResponseHandler();
    paramHttpRequest.setIOExceptionHandler(this);
    paramHttpRequest.setUnsuccessfulResponseHandler(this);
  }
  
  public boolean handleIOException(HttpRequest paramHttpRequest, boolean paramBoolean)
    throws IOException
  {
    if ((this.originalIOExceptionHandler != null) && (this.originalIOExceptionHandler.handleIOException(paramHttpRequest, paramBoolean))) {}
    for (paramBoolean = true;; paramBoolean = false)
    {
      if (paramBoolean) {}
      try
      {
        this.uploader.serverErrorCallback();
        return paramBoolean;
      }
      catch (IOException paramHttpRequest)
      {
        LOGGER.log(Level.WARNING, "exception thrown while calling server callback", paramHttpRequest);
      }
    }
    return paramBoolean;
  }
  
  public boolean handleResponse(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, boolean paramBoolean)
    throws IOException
  {
    if ((this.originalUnsuccessfulHandler != null) && (this.originalUnsuccessfulHandler.handleResponse(paramHttpRequest, paramHttpResponse, paramBoolean))) {}
    for (boolean bool = true;; bool = false)
    {
      if ((bool) && (paramBoolean) && (paramHttpResponse.getStatusCode() / 100 == 5)) {}
      try
      {
        this.uploader.serverErrorCallback();
        return bool;
      }
      catch (IOException paramHttpRequest)
      {
        LOGGER.log(Level.WARNING, "exception thrown while calling server callback", paramHttpRequest);
      }
    }
    return bool;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\media\MediaUploadErrorHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */