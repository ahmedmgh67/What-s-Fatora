package com.bumptech.glide.load.model;

import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.data.DataFetcher;
import java.io.InputStream;

public class ImageVideoModelLoader<A>
  implements ModelLoader<A, ImageVideoWrapper>
{
  private static final String TAG = "IVML";
  private final ModelLoader<A, ParcelFileDescriptor> fileDescriptorLoader;
  private final ModelLoader<A, InputStream> streamLoader;
  
  public ImageVideoModelLoader(ModelLoader<A, InputStream> paramModelLoader, ModelLoader<A, ParcelFileDescriptor> paramModelLoader1)
  {
    if ((paramModelLoader == null) && (paramModelLoader1 == null)) {
      throw new NullPointerException("At least one of streamLoader and fileDescriptorLoader must be non null");
    }
    this.streamLoader = paramModelLoader;
    this.fileDescriptorLoader = paramModelLoader1;
  }
  
  public DataFetcher<ImageVideoWrapper> getResourceFetcher(A paramA, int paramInt1, int paramInt2)
  {
    DataFetcher localDataFetcher1 = null;
    if (this.streamLoader != null) {
      localDataFetcher1 = this.streamLoader.getResourceFetcher(paramA, paramInt1, paramInt2);
    }
    DataFetcher localDataFetcher2 = null;
    if (this.fileDescriptorLoader != null) {
      localDataFetcher2 = this.fileDescriptorLoader.getResourceFetcher(paramA, paramInt1, paramInt2);
    }
    if ((localDataFetcher1 != null) || (localDataFetcher2 != null)) {
      return new ImageVideoFetcher(localDataFetcher1, localDataFetcher2);
    }
    return null;
  }
  
  static class ImageVideoFetcher
    implements DataFetcher<ImageVideoWrapper>
  {
    private final DataFetcher<ParcelFileDescriptor> fileDescriptorFetcher;
    private final DataFetcher<InputStream> streamFetcher;
    
    public ImageVideoFetcher(DataFetcher<InputStream> paramDataFetcher, DataFetcher<ParcelFileDescriptor> paramDataFetcher1)
    {
      this.streamFetcher = paramDataFetcher;
      this.fileDescriptorFetcher = paramDataFetcher1;
    }
    
    public void cancel()
    {
      if (this.streamFetcher != null) {
        this.streamFetcher.cancel();
      }
      if (this.fileDescriptorFetcher != null) {
        this.fileDescriptorFetcher.cancel();
      }
    }
    
    public void cleanup()
    {
      if (this.streamFetcher != null) {
        this.streamFetcher.cleanup();
      }
      if (this.fileDescriptorFetcher != null) {
        this.fileDescriptorFetcher.cleanup();
      }
    }
    
    public String getId()
    {
      if (this.streamFetcher != null) {
        return this.streamFetcher.getId();
      }
      return this.fileDescriptorFetcher.getId();
    }
    
    /* Error */
    public ImageVideoWrapper loadData(com.bumptech.glide.Priority paramPriority)
      throws java.lang.Exception
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore_3
      //   2: aload_3
      //   3: astore_2
      //   4: aload_0
      //   5: getfield 22	com/bumptech/glide/load/model/ImageVideoModelLoader$ImageVideoFetcher:streamFetcher	Lcom/bumptech/glide/load/data/DataFetcher;
      //   8: ifnull +17 -> 25
      //   11: aload_0
      //   12: getfield 22	com/bumptech/glide/load/model/ImageVideoModelLoader$ImageVideoFetcher:streamFetcher	Lcom/bumptech/glide/load/data/DataFetcher;
      //   15: aload_1
      //   16: invokeinterface 44 2 0
      //   21: checkcast 46	java/io/InputStream
      //   24: astore_2
      //   25: aconst_null
      //   26: astore 4
      //   28: aload 4
      //   30: astore_3
      //   31: aload_0
      //   32: getfield 24	com/bumptech/glide/load/model/ImageVideoModelLoader$ImageVideoFetcher:fileDescriptorFetcher	Lcom/bumptech/glide/load/data/DataFetcher;
      //   35: ifnull +17 -> 52
      //   38: aload_0
      //   39: getfield 24	com/bumptech/glide/load/model/ImageVideoModelLoader$ImageVideoFetcher:fileDescriptorFetcher	Lcom/bumptech/glide/load/data/DataFetcher;
      //   42: aload_1
      //   43: invokeinterface 44 2 0
      //   48: checkcast 48	android/os/ParcelFileDescriptor
      //   51: astore_3
      //   52: new 50	com/bumptech/glide/load/model/ImageVideoWrapper
      //   55: dup
      //   56: aload_2
      //   57: aload_3
      //   58: invokespecial 53	com/bumptech/glide/load/model/ImageVideoWrapper:<init>	(Ljava/io/InputStream;Landroid/os/ParcelFileDescriptor;)V
      //   61: areturn
      //   62: astore 4
      //   64: ldc 55
      //   66: iconst_2
      //   67: invokestatic 61	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
      //   70: ifeq +13 -> 83
      //   73: ldc 55
      //   75: ldc 63
      //   77: aload 4
      //   79: invokestatic 67	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   82: pop
      //   83: aload_3
      //   84: astore_2
      //   85: aload_0
      //   86: getfield 24	com/bumptech/glide/load/model/ImageVideoModelLoader$ImageVideoFetcher:fileDescriptorFetcher	Lcom/bumptech/glide/load/data/DataFetcher;
      //   89: ifnonnull -64 -> 25
      //   92: aload 4
      //   94: athrow
      //   95: astore_1
      //   96: ldc 55
      //   98: iconst_2
      //   99: invokestatic 61	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
      //   102: ifeq +12 -> 114
      //   105: ldc 55
      //   107: ldc 69
      //   109: aload_1
      //   110: invokestatic 67	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   113: pop
      //   114: aload 4
      //   116: astore_3
      //   117: aload_2
      //   118: ifnonnull -66 -> 52
      //   121: aload_1
      //   122: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	123	0	this	ImageVideoFetcher
      //   0	123	1	paramPriority	com.bumptech.glide.Priority
      //   3	115	2	localObject1	Object
      //   1	116	3	localObject2	Object
      //   26	3	4	localObject3	Object
      //   62	53	4	localException	Exception
      // Exception table:
      //   from	to	target	type
      //   11	25	62	java/lang/Exception
      //   38	52	95	java/lang/Exception
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\model\ImageVideoModelLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */