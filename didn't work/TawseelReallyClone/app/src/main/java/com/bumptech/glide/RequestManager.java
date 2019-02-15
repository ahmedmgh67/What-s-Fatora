package com.bumptech.glide;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.file_descriptor.FileDescriptorModelLoader;
import com.bumptech.glide.load.model.stream.MediaStoreStreamLoader;
import com.bumptech.glide.load.model.stream.StreamByteArrayLoader;
import com.bumptech.glide.load.model.stream.StreamModelLoader;
import com.bumptech.glide.manager.ConnectivityMonitor.ConnectivityListener;
import com.bumptech.glide.manager.ConnectivityMonitorFactory;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.LifecycleListener;
import com.bumptech.glide.manager.RequestManagerTreeNode;
import com.bumptech.glide.manager.RequestTracker;
import com.bumptech.glide.signature.ApplicationVersionSignature;
import com.bumptech.glide.signature.MediaStoreSignature;
import com.bumptech.glide.signature.StringSignature;
import com.bumptech.glide.util.Util;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

public class RequestManager
  implements LifecycleListener
{
  private final Context context;
  private final Glide glide;
  private final Lifecycle lifecycle;
  private DefaultOptions options;
  private final OptionsApplier optionsApplier;
  private final RequestTracker requestTracker;
  private final RequestManagerTreeNode treeNode;
  
  public RequestManager(Context paramContext, Lifecycle paramLifecycle, RequestManagerTreeNode paramRequestManagerTreeNode)
  {
    this(paramContext, paramLifecycle, paramRequestManagerTreeNode, new RequestTracker(), new ConnectivityMonitorFactory());
  }
  
  RequestManager(Context paramContext, final Lifecycle paramLifecycle, RequestManagerTreeNode paramRequestManagerTreeNode, RequestTracker paramRequestTracker, ConnectivityMonitorFactory paramConnectivityMonitorFactory)
  {
    this.context = paramContext.getApplicationContext();
    this.lifecycle = paramLifecycle;
    this.treeNode = paramRequestManagerTreeNode;
    this.requestTracker = paramRequestTracker;
    this.glide = Glide.get(paramContext);
    this.optionsApplier = new OptionsApplier();
    paramContext = paramConnectivityMonitorFactory.build(paramContext, new RequestManagerConnectivityListener(paramRequestTracker));
    if (Util.isOnBackgroundThread()) {
      new Handler(Looper.getMainLooper()).post(new Runnable()
      {
        public void run()
        {
          paramLifecycle.addListener(RequestManager.this);
        }
      });
    }
    for (;;)
    {
      paramLifecycle.addListener(paramContext);
      return;
      paramLifecycle.addListener(this);
    }
  }
  
  private static <T> Class<T> getSafeClass(T paramT)
  {
    if (paramT != null) {
      return paramT.getClass();
    }
    return null;
  }
  
  private <T> DrawableTypeRequest<T> loadGeneric(Class<T> paramClass)
  {
    ModelLoader localModelLoader1 = Glide.buildStreamModelLoader(paramClass, this.context);
    ModelLoader localModelLoader2 = Glide.buildFileDescriptorModelLoader(paramClass, this.context);
    if ((paramClass != null) && (localModelLoader1 == null) && (localModelLoader2 == null)) {
      throw new IllegalArgumentException("Unknown type " + paramClass + ". You must provide a Model of a type for" + " which there is a registered ModelLoader, if you are using a custom model, you must first call" + " Glide#register with a ModelLoaderFactory for your custom model class");
    }
    return (DrawableTypeRequest)this.optionsApplier.apply(new DrawableTypeRequest(paramClass, localModelLoader1, localModelLoader2, this.context, this.glide, this.requestTracker, this.lifecycle, this.optionsApplier));
  }
  
  public <T> DrawableTypeRequest<T> from(Class<T> paramClass)
  {
    return loadGeneric(paramClass);
  }
  
  public DrawableTypeRequest<byte[]> fromBytes()
  {
    return (DrawableTypeRequest)loadGeneric(byte[].class).signature(new StringSignature(UUID.randomUUID().toString())).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true);
  }
  
  public DrawableTypeRequest<File> fromFile()
  {
    return loadGeneric(File.class);
  }
  
  public DrawableTypeRequest<Uri> fromMediaStore()
  {
    Object localObject = Glide.buildStreamModelLoader(Uri.class, this.context);
    localObject = new MediaStoreStreamLoader(this.context, (ModelLoader)localObject);
    ModelLoader localModelLoader = Glide.buildFileDescriptorModelLoader(Uri.class, this.context);
    return (DrawableTypeRequest)this.optionsApplier.apply(new DrawableTypeRequest(Uri.class, (ModelLoader)localObject, localModelLoader, this.context, this.glide, this.requestTracker, this.lifecycle, this.optionsApplier));
  }
  
  public DrawableTypeRequest<Integer> fromResource()
  {
    return (DrawableTypeRequest)loadGeneric(Integer.class).signature(ApplicationVersionSignature.obtain(this.context));
  }
  
  public DrawableTypeRequest<String> fromString()
  {
    return loadGeneric(String.class);
  }
  
  public DrawableTypeRequest<Uri> fromUri()
  {
    return loadGeneric(Uri.class);
  }
  
  @Deprecated
  public DrawableTypeRequest<URL> fromUrl()
  {
    return loadGeneric(URL.class);
  }
  
  public boolean isPaused()
  {
    Util.assertMainThread();
    return this.requestTracker.isPaused();
  }
  
  public DrawableTypeRequest<Uri> load(Uri paramUri)
  {
    return (DrawableTypeRequest)fromUri().load(paramUri);
  }
  
  public DrawableTypeRequest<File> load(File paramFile)
  {
    return (DrawableTypeRequest)fromFile().load(paramFile);
  }
  
  public DrawableTypeRequest<Integer> load(Integer paramInteger)
  {
    return (DrawableTypeRequest)fromResource().load(paramInteger);
  }
  
  public <T> DrawableTypeRequest<T> load(T paramT)
  {
    return (DrawableTypeRequest)loadGeneric(getSafeClass(paramT)).load(paramT);
  }
  
  public DrawableTypeRequest<String> load(String paramString)
  {
    return (DrawableTypeRequest)fromString().load(paramString);
  }
  
  @Deprecated
  public DrawableTypeRequest<URL> load(URL paramURL)
  {
    return (DrawableTypeRequest)fromUrl().load(paramURL);
  }
  
  public DrawableTypeRequest<byte[]> load(byte[] paramArrayOfByte)
  {
    return (DrawableTypeRequest)fromBytes().load(paramArrayOfByte);
  }
  
  @Deprecated
  public DrawableTypeRequest<byte[]> load(byte[] paramArrayOfByte, String paramString)
  {
    return (DrawableTypeRequest)load(paramArrayOfByte).signature(new StringSignature(paramString));
  }
  
  public DrawableTypeRequest<Uri> loadFromMediaStore(Uri paramUri)
  {
    return (DrawableTypeRequest)fromMediaStore().load(paramUri);
  }
  
  @Deprecated
  public DrawableTypeRequest<Uri> loadFromMediaStore(Uri paramUri, String paramString, long paramLong, int paramInt)
  {
    paramString = new MediaStoreSignature(paramString, paramLong, paramInt);
    return (DrawableTypeRequest)loadFromMediaStore(paramUri).signature(paramString);
  }
  
  public void onDestroy()
  {
    this.requestTracker.clearRequests();
  }
  
  public void onLowMemory()
  {
    this.glide.clearMemory();
  }
  
  public void onStart()
  {
    resumeRequests();
  }
  
  public void onStop()
  {
    pauseRequests();
  }
  
  public void onTrimMemory(int paramInt)
  {
    this.glide.trimMemory(paramInt);
  }
  
  public void pauseRequests()
  {
    Util.assertMainThread();
    this.requestTracker.pauseRequests();
  }
  
  public void pauseRequestsRecursive()
  {
    Util.assertMainThread();
    pauseRequests();
    Iterator localIterator = this.treeNode.getDescendants().iterator();
    while (localIterator.hasNext()) {
      ((RequestManager)localIterator.next()).pauseRequests();
    }
  }
  
  public void resumeRequests()
  {
    Util.assertMainThread();
    this.requestTracker.resumeRequests();
  }
  
  public void resumeRequestsRecursive()
  {
    Util.assertMainThread();
    resumeRequests();
    Iterator localIterator = this.treeNode.getDescendants().iterator();
    while (localIterator.hasNext()) {
      ((RequestManager)localIterator.next()).resumeRequests();
    }
  }
  
  public void setDefaultOptions(DefaultOptions paramDefaultOptions)
  {
    this.options = paramDefaultOptions;
  }
  
  public <A, T> GenericModelRequest<A, T> using(ModelLoader<A, T> paramModelLoader, Class<T> paramClass)
  {
    return new GenericModelRequest(paramModelLoader, paramClass);
  }
  
  public ImageModelRequest<byte[]> using(StreamByteArrayLoader paramStreamByteArrayLoader)
  {
    return new ImageModelRequest(paramStreamByteArrayLoader);
  }
  
  public <T> ImageModelRequest<T> using(StreamModelLoader<T> paramStreamModelLoader)
  {
    return new ImageModelRequest(paramStreamModelLoader);
  }
  
  public <T> VideoModelRequest<T> using(FileDescriptorModelLoader<T> paramFileDescriptorModelLoader)
  {
    return new VideoModelRequest(paramFileDescriptorModelLoader);
  }
  
  public static abstract interface DefaultOptions
  {
    public abstract <T> void apply(GenericRequestBuilder<T, ?, ?, ?> paramGenericRequestBuilder);
  }
  
  public final class GenericModelRequest<A, T>
  {
    private final Class<T> dataClass;
    private final ModelLoader<A, T> modelLoader;
    
    GenericModelRequest(Class<T> paramClass)
    {
      this.modelLoader = paramClass;
      Class localClass;
      this.dataClass = localClass;
    }
    
    public GenericModelRequest<A, T>.GenericTypeRequest from(Class<A> paramClass)
    {
      return new GenericTypeRequest(paramClass);
    }
    
    public GenericModelRequest<A, T>.GenericTypeRequest load(A paramA)
    {
      return new GenericTypeRequest(paramA);
    }
    
    public final class GenericTypeRequest
    {
      private final A model;
      private final Class<A> modelClass;
      private final boolean providedModel;
      
      GenericTypeRequest()
      {
        this.providedModel = false;
        this.model = null;
        Class localClass;
        this.modelClass = localClass;
      }
      
      GenericTypeRequest()
      {
        this.providedModel = true;
        Object localObject;
        this.model = localObject;
        this.modelClass = RequestManager.getSafeClass(localObject);
      }
      
      public <Z> GenericTranscodeRequest<A, T, Z> as(Class<Z> paramClass)
      {
        paramClass = (GenericTranscodeRequest)RequestManager.this.optionsApplier.apply(new GenericTranscodeRequest(RequestManager.this.context, RequestManager.this.glide, this.modelClass, RequestManager.GenericModelRequest.this.modelLoader, RequestManager.GenericModelRequest.this.dataClass, paramClass, RequestManager.this.requestTracker, RequestManager.this.lifecycle, RequestManager.this.optionsApplier));
        if (this.providedModel) {
          paramClass.load(this.model);
        }
        return paramClass;
      }
    }
  }
  
  public final class ImageModelRequest<T>
  {
    private final ModelLoader<T, InputStream> loader;
    
    ImageModelRequest()
    {
      ModelLoader localModelLoader;
      this.loader = localModelLoader;
    }
    
    public DrawableTypeRequest<T> from(Class<T> paramClass)
    {
      return (DrawableTypeRequest)RequestManager.this.optionsApplier.apply(new DrawableTypeRequest(paramClass, this.loader, null, RequestManager.this.context, RequestManager.this.glide, RequestManager.this.requestTracker, RequestManager.this.lifecycle, RequestManager.this.optionsApplier));
    }
    
    public DrawableTypeRequest<T> load(T paramT)
    {
      return (DrawableTypeRequest)from(RequestManager.getSafeClass(paramT)).load(paramT);
    }
  }
  
  class OptionsApplier
  {
    OptionsApplier() {}
    
    public <A, X extends GenericRequestBuilder<A, ?, ?, ?>> X apply(X paramX)
    {
      if (RequestManager.this.options != null) {
        RequestManager.this.options.apply(paramX);
      }
      return paramX;
    }
  }
  
  private static class RequestManagerConnectivityListener
    implements ConnectivityMonitor.ConnectivityListener
  {
    private final RequestTracker requestTracker;
    
    public RequestManagerConnectivityListener(RequestTracker paramRequestTracker)
    {
      this.requestTracker = paramRequestTracker;
    }
    
    public void onConnectivityChanged(boolean paramBoolean)
    {
      if (paramBoolean) {
        this.requestTracker.restartRequests();
      }
    }
  }
  
  public final class VideoModelRequest<T>
  {
    private final ModelLoader<T, ParcelFileDescriptor> loader;
    
    VideoModelRequest()
    {
      ModelLoader localModelLoader;
      this.loader = localModelLoader;
    }
    
    public DrawableTypeRequest<T> load(T paramT)
    {
      return (DrawableTypeRequest)((DrawableTypeRequest)RequestManager.this.optionsApplier.apply(new DrawableTypeRequest(RequestManager.getSafeClass(paramT), null, this.loader, RequestManager.this.context, RequestManager.this.glide, RequestManager.this.requestTracker, RequestManager.this.lifecycle, RequestManager.this.optionsApplier))).load(paramT);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\RequestManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */