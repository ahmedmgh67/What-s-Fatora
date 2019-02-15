package com.bumptech.glide.load.engine.cache;

import android.util.Log;
import com.bumptech.glide.disklrucache.DiskLruCache;
import com.bumptech.glide.disklrucache.DiskLruCache.Value;
import com.bumptech.glide.load.Key;
import java.io.File;
import java.io.IOException;

public class DiskLruCacheWrapper
  implements DiskCache
{
  private static final int APP_VERSION = 1;
  private static final String TAG = "DiskLruCacheWrapper";
  private static final int VALUE_COUNT = 1;
  private static DiskLruCacheWrapper wrapper = null;
  private final File directory;
  private DiskLruCache diskLruCache;
  private final int maxSize;
  private final SafeKeyGenerator safeKeyGenerator;
  private final DiskCacheWriteLocker writeLocker = new DiskCacheWriteLocker();
  
  protected DiskLruCacheWrapper(File paramFile, int paramInt)
  {
    this.directory = paramFile;
    this.maxSize = paramInt;
    this.safeKeyGenerator = new SafeKeyGenerator();
  }
  
  public static DiskCache get(File paramFile, int paramInt)
  {
    try
    {
      if (wrapper == null) {
        wrapper = new DiskLruCacheWrapper(paramFile, paramInt);
      }
      paramFile = wrapper;
      return paramFile;
    }
    finally {}
  }
  
  private DiskLruCache getDiskCache()
    throws IOException
  {
    try
    {
      if (this.diskLruCache == null) {
        this.diskLruCache = DiskLruCache.open(this.directory, 1, 1, this.maxSize);
      }
      DiskLruCache localDiskLruCache = this.diskLruCache;
      return localDiskLruCache;
    }
    finally {}
  }
  
  private void resetDiskCache()
  {
    try
    {
      this.diskLruCache = null;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void clear()
  {
    try
    {
      getDiskCache().delete();
      resetDiskCache();
      return;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        if (Log.isLoggable("DiskLruCacheWrapper", 5)) {
          Log.w("DiskLruCacheWrapper", "Unable to clear disk cache", localIOException);
        }
      }
    }
    finally {}
  }
  
  public void delete(Key paramKey)
  {
    paramKey = this.safeKeyGenerator.getSafeKey(paramKey);
    try
    {
      getDiskCache().remove(paramKey);
      return;
    }
    catch (IOException paramKey)
    {
      while (!Log.isLoggable("DiskLruCacheWrapper", 5)) {}
      Log.w("DiskLruCacheWrapper", "Unable to delete from disk cache", paramKey);
    }
  }
  
  public File get(Key paramKey)
  {
    paramKey = this.safeKeyGenerator.getSafeKey(paramKey);
    Object localObject = null;
    try
    {
      DiskLruCache.Value localValue = getDiskCache().get(paramKey);
      paramKey = (Key)localObject;
      if (localValue != null) {
        paramKey = localValue.getFile(0);
      }
    }
    catch (IOException localIOException)
    {
      do
      {
        paramKey = (Key)localObject;
      } while (!Log.isLoggable("DiskLruCacheWrapper", 5));
      Log.w("DiskLruCacheWrapper", "Unable to get from disk cache", localIOException);
    }
    return paramKey;
    return null;
  }
  
  /* Error */
  public void put(Key paramKey, DiskCache.Writer paramWriter)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 48	com/bumptech/glide/load/engine/cache/DiskLruCacheWrapper:safeKeyGenerator	Lcom/bumptech/glide/load/engine/cache/SafeKeyGenerator;
    //   4: aload_1
    //   5: invokevirtual 91	com/bumptech/glide/load/engine/cache/SafeKeyGenerator:getSafeKey	(Lcom/bumptech/glide/load/Key;)Ljava/lang/String;
    //   8: astore_3
    //   9: aload_0
    //   10: getfield 39	com/bumptech/glide/load/engine/cache/DiskLruCacheWrapper:writeLocker	Lcom/bumptech/glide/load/engine/cache/DiskCacheWriteLocker;
    //   13: aload_1
    //   14: invokevirtual 114	com/bumptech/glide/load/engine/cache/DiskCacheWriteLocker:acquire	(Lcom/bumptech/glide/load/Key;)V
    //   17: aload_0
    //   18: invokespecial 69	com/bumptech/glide/load/engine/cache/DiskLruCacheWrapper:getDiskCache	()Lcom/bumptech/glide/disklrucache/DiskLruCache;
    //   21: aload_3
    //   22: invokevirtual 118	com/bumptech/glide/disklrucache/DiskLruCache:edit	(Ljava/lang/String;)Lcom/bumptech/glide/disklrucache/DiskLruCache$Editor;
    //   25: astore_3
    //   26: aload_3
    //   27: ifnull +25 -> 52
    //   30: aload_2
    //   31: aload_3
    //   32: iconst_0
    //   33: invokevirtual 121	com/bumptech/glide/disklrucache/DiskLruCache$Editor:getFile	(I)Ljava/io/File;
    //   36: invokeinterface 127 2 0
    //   41: ifeq +7 -> 48
    //   44: aload_3
    //   45: invokevirtual 130	com/bumptech/glide/disklrucache/DiskLruCache$Editor:commit	()V
    //   48: aload_3
    //   49: invokevirtual 133	com/bumptech/glide/disklrucache/DiskLruCache$Editor:abortUnlessCommitted	()V
    //   52: aload_0
    //   53: getfield 39	com/bumptech/glide/load/engine/cache/DiskLruCacheWrapper:writeLocker	Lcom/bumptech/glide/load/engine/cache/DiskCacheWriteLocker;
    //   56: aload_1
    //   57: invokevirtual 136	com/bumptech/glide/load/engine/cache/DiskCacheWriteLocker:release	(Lcom/bumptech/glide/load/Key;)V
    //   60: return
    //   61: astore_2
    //   62: aload_3
    //   63: invokevirtual 133	com/bumptech/glide/disklrucache/DiskLruCache$Editor:abortUnlessCommitted	()V
    //   66: aload_2
    //   67: athrow
    //   68: astore_2
    //   69: ldc 13
    //   71: iconst_5
    //   72: invokestatic 80	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   75: ifeq +12 -> 87
    //   78: ldc 13
    //   80: ldc -118
    //   82: aload_2
    //   83: invokestatic 86	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   86: pop
    //   87: aload_0
    //   88: getfield 39	com/bumptech/glide/load/engine/cache/DiskLruCacheWrapper:writeLocker	Lcom/bumptech/glide/load/engine/cache/DiskCacheWriteLocker;
    //   91: aload_1
    //   92: invokevirtual 136	com/bumptech/glide/load/engine/cache/DiskCacheWriteLocker:release	(Lcom/bumptech/glide/load/Key;)V
    //   95: return
    //   96: astore_2
    //   97: aload_0
    //   98: getfield 39	com/bumptech/glide/load/engine/cache/DiskLruCacheWrapper:writeLocker	Lcom/bumptech/glide/load/engine/cache/DiskCacheWriteLocker;
    //   101: aload_1
    //   102: invokevirtual 136	com/bumptech/glide/load/engine/cache/DiskCacheWriteLocker:release	(Lcom/bumptech/glide/load/Key;)V
    //   105: aload_2
    //   106: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	107	0	this	DiskLruCacheWrapper
    //   0	107	1	paramKey	Key
    //   0	107	2	paramWriter	DiskCache.Writer
    //   8	55	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   30	48	61	finally
    //   17	26	68	java/io/IOException
    //   48	52	68	java/io/IOException
    //   62	68	68	java/io/IOException
    //   17	26	96	finally
    //   48	52	96	finally
    //   62	68	96	finally
    //   69	87	96	finally
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\engine\cache\DiskLruCacheWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */