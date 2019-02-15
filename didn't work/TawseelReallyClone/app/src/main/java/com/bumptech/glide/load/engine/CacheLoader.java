package com.bumptech.glide.load.engine;

import android.util.Log;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.cache.DiskCache;
import java.io.File;
import java.io.IOException;

class CacheLoader
{
  private static final String TAG = "CacheLoader";
  private final DiskCache diskCache;
  
  public CacheLoader(DiskCache paramDiskCache)
  {
    this.diskCache = paramDiskCache;
  }
  
  public <Z> Resource<Z> load(Key paramKey, ResourceDecoder<File, Z> paramResourceDecoder, int paramInt1, int paramInt2)
  {
    File localFile = this.diskCache.get(paramKey);
    Object localObject;
    if (localFile == null) {
      localObject = null;
    }
    for (;;)
    {
      return (Resource<Z>)localObject;
      localObject = null;
      try
      {
        paramResourceDecoder = paramResourceDecoder.decode(localFile, paramInt1, paramInt2);
        localObject = paramResourceDecoder;
        if (paramResourceDecoder != null) {
          continue;
        }
        if (Log.isLoggable("CacheLoader", 3)) {
          Log.d("CacheLoader", "Failed to decode image from cache or not present in cache");
        }
        this.diskCache.delete(paramKey);
        return paramResourceDecoder;
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          paramResourceDecoder = (ResourceDecoder<File, Z>)localObject;
          if (Log.isLoggable("CacheLoader", 3))
          {
            Log.d("CacheLoader", "Exception decoding image from cache", localIOException);
            paramResourceDecoder = (ResourceDecoder<File, Z>)localObject;
          }
        }
      }
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\engine\CacheLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */