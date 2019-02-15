package com.bumptech.glide.load.data;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.bumptech.glide.Priority;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class LocalUriFetcher<T>
  implements DataFetcher<T>
{
  private static final String TAG = "LocalUriFetcher";
  private final Context context;
  private T data;
  private final Uri uri;
  
  public LocalUriFetcher(Context paramContext, Uri paramUri)
  {
    this.context = paramContext.getApplicationContext();
    this.uri = paramUri;
  }
  
  public void cancel() {}
  
  public void cleanup()
  {
    if (this.data != null) {}
    try
    {
      close(this.data);
      return;
    }
    catch (IOException localIOException)
    {
      while (!Log.isLoggable("LocalUriFetcher", 2)) {}
      Log.v("LocalUriFetcher", "failed to close data", localIOException);
    }
  }
  
  protected abstract void close(T paramT)
    throws IOException;
  
  public String getId()
  {
    return this.uri.toString();
  }
  
  public final T loadData(Priority paramPriority)
    throws Exception
  {
    paramPriority = this.context.getContentResolver();
    this.data = loadResource(this.uri, paramPriority);
    return (T)this.data;
  }
  
  protected abstract T loadResource(Uri paramUri, ContentResolver paramContentResolver)
    throws FileNotFoundException;
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\data\LocalUriFetcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */