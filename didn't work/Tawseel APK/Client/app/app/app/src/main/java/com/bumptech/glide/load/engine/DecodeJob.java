package com.bumptech.glide.load.engine;

import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskCache.Writer;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.provider.DataLoadProvider;
import com.bumptech.glide.util.LogTime;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class DecodeJob<A, T, Z>
{
  private static final FileOpener DEFAULT_FILE_OPENER = new FileOpener();
  private static final String TAG = "DecodeJob";
  private final DiskCacheProvider diskCacheProvider;
  private final DiskCacheStrategy diskCacheStrategy;
  private final DataFetcher<A> fetcher;
  private final FileOpener fileOpener;
  private final int height;
  private volatile boolean isCancelled;
  private final DataLoadProvider<A, T> loadProvider;
  private final Priority priority;
  private final EngineKey resultKey;
  private final ResourceTranscoder<T, Z> transcoder;
  private final Transformation<T> transformation;
  private final int width;
  
  public DecodeJob(EngineKey paramEngineKey, int paramInt1, int paramInt2, DataFetcher<A> paramDataFetcher, DataLoadProvider<A, T> paramDataLoadProvider, Transformation<T> paramTransformation, ResourceTranscoder<T, Z> paramResourceTranscoder, DiskCacheProvider paramDiskCacheProvider, DiskCacheStrategy paramDiskCacheStrategy, Priority paramPriority)
  {
    this(paramEngineKey, paramInt1, paramInt2, paramDataFetcher, paramDataLoadProvider, paramTransformation, paramResourceTranscoder, paramDiskCacheProvider, paramDiskCacheStrategy, paramPriority, DEFAULT_FILE_OPENER);
  }
  
  DecodeJob(EngineKey paramEngineKey, int paramInt1, int paramInt2, DataFetcher<A> paramDataFetcher, DataLoadProvider<A, T> paramDataLoadProvider, Transformation<T> paramTransformation, ResourceTranscoder<T, Z> paramResourceTranscoder, DiskCacheProvider paramDiskCacheProvider, DiskCacheStrategy paramDiskCacheStrategy, Priority paramPriority, FileOpener paramFileOpener)
  {
    this.resultKey = paramEngineKey;
    this.width = paramInt1;
    this.height = paramInt2;
    this.fetcher = paramDataFetcher;
    this.loadProvider = paramDataLoadProvider;
    this.transformation = paramTransformation;
    this.transcoder = paramResourceTranscoder;
    this.diskCacheProvider = paramDiskCacheProvider;
    this.diskCacheStrategy = paramDiskCacheStrategy;
    this.priority = paramPriority;
    this.fileOpener = paramFileOpener;
  }
  
  private Resource<T> cacheAndDecodeSourceData(A paramA)
    throws IOException
  {
    long l = LogTime.getLogTime();
    paramA = new SourceWriter(this.loadProvider.getSourceEncoder(), paramA);
    this.diskCacheProvider.getDiskCache().put(this.resultKey.getOriginalKey(), paramA);
    if (Log.isLoggable("DecodeJob", 2)) {
      logWithTimeAndKey("Wrote source to cache", l);
    }
    l = LogTime.getLogTime();
    paramA = loadFromCache(this.resultKey.getOriginalKey());
    if ((Log.isLoggable("DecodeJob", 2)) && (paramA != null)) {
      logWithTimeAndKey("Decoded source from cache", l);
    }
    return paramA;
  }
  
  private Resource<T> decodeFromSourceData(A paramA)
    throws IOException
  {
    if (this.diskCacheStrategy.cacheSource()) {
      paramA = cacheAndDecodeSourceData(paramA);
    }
    long l;
    Resource localResource;
    do
    {
      return paramA;
      l = LogTime.getLogTime();
      localResource = this.loadProvider.getSourceDecoder().decode(paramA, this.width, this.height);
      paramA = localResource;
    } while (!Log.isLoggable("DecodeJob", 2));
    logWithTimeAndKey("Decoded from source", l);
    return localResource;
  }
  
  private Resource<T> decodeSource()
    throws Exception
  {
    try
    {
      long l = LogTime.getLogTime();
      Object localObject1 = this.fetcher.loadData(this.priority);
      if (Log.isLoggable("DecodeJob", 2)) {
        logWithTimeAndKey("Fetched data", l);
      }
      boolean bool = this.isCancelled;
      if (bool) {
        return null;
      }
      localObject1 = decodeFromSourceData(localObject1);
      return (Resource<T>)localObject1;
    }
    finally
    {
      this.fetcher.cleanup();
    }
  }
  
  private Resource<T> loadFromCache(Key paramKey)
    throws IOException
  {
    Object localObject1 = this.diskCacheProvider.getDiskCache().get(paramKey);
    if (localObject1 == null) {
      localObject1 = null;
    }
    for (;;)
    {
      return (Resource<T>)localObject1;
      try
      {
        Resource localResource = this.loadProvider.getCacheDecoder().decode(localObject1, this.width, this.height);
        localObject1 = localResource;
        if (localResource != null) {
          continue;
        }
        this.diskCacheProvider.getDiskCache().delete(paramKey);
        return localResource;
      }
      finally
      {
        if (0 == 0) {
          this.diskCacheProvider.getDiskCache().delete(paramKey);
        }
      }
    }
  }
  
  private void logWithTimeAndKey(String paramString, long paramLong)
  {
    Log.v("DecodeJob", paramString + " in " + LogTime.getElapsedMillis(paramLong) + ", key: " + this.resultKey);
  }
  
  private Resource<Z> transcode(Resource<T> paramResource)
  {
    if (paramResource == null) {
      return null;
    }
    return this.transcoder.transcode(paramResource);
  }
  
  private Resource<T> transform(Resource<T> paramResource)
  {
    Object localObject;
    if (paramResource == null) {
      localObject = null;
    }
    Resource localResource;
    do
    {
      return (Resource<T>)localObject;
      localResource = this.transformation.transform(paramResource, this.width, this.height);
      localObject = localResource;
    } while (paramResource.equals(localResource));
    paramResource.recycle();
    return localResource;
  }
  
  private Resource<Z> transformEncodeAndTranscode(Resource<T> paramResource)
  {
    long l = LogTime.getLogTime();
    paramResource = transform(paramResource);
    if (Log.isLoggable("DecodeJob", 2)) {
      logWithTimeAndKey("Transformed resource from source", l);
    }
    writeTransformedToCache(paramResource);
    l = LogTime.getLogTime();
    paramResource = transcode(paramResource);
    if (Log.isLoggable("DecodeJob", 2)) {
      logWithTimeAndKey("Transcoded transformed from source", l);
    }
    return paramResource;
  }
  
  private void writeTransformedToCache(Resource<T> paramResource)
  {
    if ((paramResource == null) || (!this.diskCacheStrategy.cacheResult())) {}
    long l;
    do
    {
      return;
      l = LogTime.getLogTime();
      paramResource = new SourceWriter(this.loadProvider.getEncoder(), paramResource);
      this.diskCacheProvider.getDiskCache().put(this.resultKey, paramResource);
    } while (!Log.isLoggable("DecodeJob", 2));
    logWithTimeAndKey("Wrote transformed from source to cache", l);
  }
  
  public void cancel()
  {
    this.isCancelled = true;
    this.fetcher.cancel();
  }
  
  public Resource<Z> decodeFromSource()
    throws Exception
  {
    return transformEncodeAndTranscode(decodeSource());
  }
  
  public Resource<Z> decodeResultFromCache()
    throws Exception
  {
    Object localObject;
    if (!this.diskCacheStrategy.cacheResult()) {
      localObject = null;
    }
    long l;
    Resource localResource;
    do
    {
      return (Resource<Z>)localObject;
      l = LogTime.getLogTime();
      localObject = loadFromCache(this.resultKey);
      if (Log.isLoggable("DecodeJob", 2)) {
        logWithTimeAndKey("Decoded transformed from cache", l);
      }
      l = LogTime.getLogTime();
      localResource = transcode((Resource)localObject);
      localObject = localResource;
    } while (!Log.isLoggable("DecodeJob", 2));
    logWithTimeAndKey("Transcoded transformed from cache", l);
    return localResource;
  }
  
  public Resource<Z> decodeSourceFromCache()
    throws Exception
  {
    if (!this.diskCacheStrategy.cacheSource()) {
      return null;
    }
    long l = LogTime.getLogTime();
    Resource localResource = loadFromCache(this.resultKey.getOriginalKey());
    if (Log.isLoggable("DecodeJob", 2)) {
      logWithTimeAndKey("Decoded source from cache", l);
    }
    return transformEncodeAndTranscode(localResource);
  }
  
  static abstract interface DiskCacheProvider
  {
    public abstract DiskCache getDiskCache();
  }
  
  static class FileOpener
  {
    public OutputStream open(File paramFile)
      throws FileNotFoundException
    {
      return new BufferedOutputStream(new FileOutputStream(paramFile));
    }
  }
  
  class SourceWriter<DataType>
    implements DiskCache.Writer
  {
    private final DataType data;
    private final Encoder<DataType> encoder;
    
    public SourceWriter(DataType paramDataType)
    {
      this.encoder = paramDataType;
      Object localObject;
      this.data = localObject;
    }
    
    /* Error */
    public boolean write(File paramFile)
    {
      // Byte code:
      //   0: iconst_0
      //   1: istore_3
      //   2: aconst_null
      //   3: astore 5
      //   5: aconst_null
      //   6: astore 4
      //   8: aload_0
      //   9: getfield 22	com/bumptech/glide/load/engine/DecodeJob$SourceWriter:this$0	Lcom/bumptech/glide/load/engine/DecodeJob;
      //   12: invokestatic 42	com/bumptech/glide/load/engine/DecodeJob:access$000	(Lcom/bumptech/glide/load/engine/DecodeJob;)Lcom/bumptech/glide/load/engine/DecodeJob$FileOpener;
      //   15: aload_1
      //   16: invokevirtual 48	com/bumptech/glide/load/engine/DecodeJob$FileOpener:open	(Ljava/io/File;)Ljava/io/OutputStream;
      //   19: astore_1
      //   20: aload_1
      //   21: astore 4
      //   23: aload_1
      //   24: astore 5
      //   26: aload_0
      //   27: getfield 27	com/bumptech/glide/load/engine/DecodeJob$SourceWriter:encoder	Lcom/bumptech/glide/load/Encoder;
      //   30: aload_0
      //   31: getfield 29	com/bumptech/glide/load/engine/DecodeJob$SourceWriter:data	Ljava/lang/Object;
      //   34: aload_1
      //   35: invokeinterface 54 3 0
      //   40: istore_2
      //   41: iload_2
      //   42: istore_3
      //   43: aload_1
      //   44: ifnull +9 -> 53
      //   47: aload_1
      //   48: invokevirtual 59	java/io/OutputStream:close	()V
      //   51: iload_2
      //   52: istore_3
      //   53: iload_3
      //   54: ireturn
      //   55: astore_1
      //   56: aload 4
      //   58: astore 5
      //   60: ldc 61
      //   62: iconst_3
      //   63: invokestatic 67	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
      //   66: ifeq +16 -> 82
      //   69: aload 4
      //   71: astore 5
      //   73: ldc 61
      //   75: ldc 69
      //   77: aload_1
      //   78: invokestatic 73	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   81: pop
      //   82: aload 4
      //   84: ifnull -31 -> 53
      //   87: aload 4
      //   89: invokevirtual 59	java/io/OutputStream:close	()V
      //   92: iconst_0
      //   93: ireturn
      //   94: astore_1
      //   95: iconst_0
      //   96: ireturn
      //   97: astore_1
      //   98: aload 5
      //   100: ifnull +8 -> 108
      //   103: aload 5
      //   105: invokevirtual 59	java/io/OutputStream:close	()V
      //   108: aload_1
      //   109: athrow
      //   110: astore_1
      //   111: iload_2
      //   112: ireturn
      //   113: astore 4
      //   115: goto -7 -> 108
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	118	0	this	SourceWriter
      //   0	118	1	paramFile	File
      //   40	72	2	bool1	boolean
      //   1	53	3	bool2	boolean
      //   6	82	4	localFile	File
      //   113	1	4	localIOException	IOException
      //   3	101	5	localObject	Object
      // Exception table:
      //   from	to	target	type
      //   8	20	55	java/io/FileNotFoundException
      //   26	41	55	java/io/FileNotFoundException
      //   87	92	94	java/io/IOException
      //   8	20	97	finally
      //   26	41	97	finally
      //   60	69	97	finally
      //   73	82	97	finally
      //   47	51	110	java/io/IOException
      //   103	108	113	java/io/IOException
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\engine\DecodeJob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */