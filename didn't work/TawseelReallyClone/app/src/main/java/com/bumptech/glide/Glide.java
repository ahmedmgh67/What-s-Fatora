package com.bumptech.glide;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.prefill.BitmapPreFiller;
import com.bumptech.glide.load.engine.prefill.PreFillType.Builder;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ImageVideoWrapper;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.file_descriptor.FileDescriptorFileLoader.Factory;
import com.bumptech.glide.load.model.file_descriptor.FileDescriptorResourceLoader.Factory;
import com.bumptech.glide.load.model.file_descriptor.FileDescriptorStringLoader.Factory;
import com.bumptech.glide.load.model.file_descriptor.FileDescriptorUriLoader.Factory;
import com.bumptech.glide.load.model.stream.HttpUrlGlideUrlLoader.Factory;
import com.bumptech.glide.load.model.stream.StreamByteArrayLoader.Factory;
import com.bumptech.glide.load.model.stream.StreamFileLoader.Factory;
import com.bumptech.glide.load.model.stream.StreamResourceLoader.Factory;
import com.bumptech.glide.load.model.stream.StreamStringLoader.Factory;
import com.bumptech.glide.load.model.stream.StreamUriLoader.Factory;
import com.bumptech.glide.load.model.stream.StreamUrlLoader.Factory;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.FileDescriptorBitmapDataLoadProvider;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.bitmap.ImageVideoDataLoadProvider;
import com.bumptech.glide.load.resource.bitmap.StreamBitmapDataLoadProvider;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.file.StreamFileDataLoadProvider;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawableLoadProvider;
import com.bumptech.glide.load.resource.gifbitmap.GifBitmapWrapper;
import com.bumptech.glide.load.resource.gifbitmap.GifBitmapWrapperTransformation;
import com.bumptech.glide.load.resource.gifbitmap.ImageVideoGifDrawableLoadProvider;
import com.bumptech.glide.load.resource.transcode.GifBitmapWrapperDrawableTranscoder;
import com.bumptech.glide.load.resource.transcode.GlideBitmapDrawableTranscoder;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.load.resource.transcode.TranscoderRegistry;
import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.module.ManifestParser;
import com.bumptech.glide.provider.DataLoadProvider;
import com.bumptech.glide.provider.DataLoadProviderRegistry;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ImageViewTargetFactory;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.util.Util;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class Glide
{
  private static final String TAG = "Glide";
  private static volatile Glide glide;
  private final CenterCrop bitmapCenterCrop;
  private final FitCenter bitmapFitCenter;
  private final BitmapPool bitmapPool;
  private final BitmapPreFiller bitmapPreFiller;
  private final DataLoadProviderRegistry dataLoadProviderRegistry;
  private final DecodeFormat decodeFormat;
  private final GifBitmapWrapperTransformation drawableCenterCrop;
  private final GifBitmapWrapperTransformation drawableFitCenter;
  private final Engine engine;
  private final ImageViewTargetFactory imageViewTargetFactory = new ImageViewTargetFactory();
  private final GenericLoaderFactory loaderFactory;
  private final Handler mainHandler;
  private final MemoryCache memoryCache;
  private final TranscoderRegistry transcoderRegistry = new TranscoderRegistry();
  
  Glide(Engine paramEngine, MemoryCache paramMemoryCache, BitmapPool paramBitmapPool, Context paramContext, DecodeFormat paramDecodeFormat)
  {
    this.engine = paramEngine;
    this.bitmapPool = paramBitmapPool;
    this.memoryCache = paramMemoryCache;
    this.decodeFormat = paramDecodeFormat;
    this.loaderFactory = new GenericLoaderFactory(paramContext);
    this.mainHandler = new Handler(Looper.getMainLooper());
    this.bitmapPreFiller = new BitmapPreFiller(paramMemoryCache, paramBitmapPool, paramDecodeFormat);
    this.dataLoadProviderRegistry = new DataLoadProviderRegistry();
    paramEngine = new StreamBitmapDataLoadProvider(paramBitmapPool, paramDecodeFormat);
    this.dataLoadProviderRegistry.register(InputStream.class, Bitmap.class, paramEngine);
    paramMemoryCache = new FileDescriptorBitmapDataLoadProvider(paramBitmapPool, paramDecodeFormat);
    this.dataLoadProviderRegistry.register(ParcelFileDescriptor.class, Bitmap.class, paramMemoryCache);
    paramEngine = new ImageVideoDataLoadProvider(paramEngine, paramMemoryCache);
    this.dataLoadProviderRegistry.register(ImageVideoWrapper.class, Bitmap.class, paramEngine);
    paramMemoryCache = new GifDrawableLoadProvider(paramContext, paramBitmapPool);
    this.dataLoadProviderRegistry.register(InputStream.class, GifDrawable.class, paramMemoryCache);
    this.dataLoadProviderRegistry.register(ImageVideoWrapper.class, GifBitmapWrapper.class, new ImageVideoGifDrawableLoadProvider(paramEngine, paramMemoryCache, paramBitmapPool));
    this.dataLoadProviderRegistry.register(InputStream.class, File.class, new StreamFileDataLoadProvider());
    register(File.class, ParcelFileDescriptor.class, new FileDescriptorFileLoader.Factory());
    register(File.class, InputStream.class, new StreamFileLoader.Factory());
    register(Integer.TYPE, ParcelFileDescriptor.class, new FileDescriptorResourceLoader.Factory());
    register(Integer.TYPE, InputStream.class, new StreamResourceLoader.Factory());
    register(Integer.class, ParcelFileDescriptor.class, new FileDescriptorResourceLoader.Factory());
    register(Integer.class, InputStream.class, new StreamResourceLoader.Factory());
    register(String.class, ParcelFileDescriptor.class, new FileDescriptorStringLoader.Factory());
    register(String.class, InputStream.class, new StreamStringLoader.Factory());
    register(Uri.class, ParcelFileDescriptor.class, new FileDescriptorUriLoader.Factory());
    register(Uri.class, InputStream.class, new StreamUriLoader.Factory());
    register(URL.class, InputStream.class, new StreamUrlLoader.Factory());
    register(GlideUrl.class, InputStream.class, new HttpUrlGlideUrlLoader.Factory());
    register(byte[].class, InputStream.class, new StreamByteArrayLoader.Factory());
    this.transcoderRegistry.register(Bitmap.class, GlideBitmapDrawable.class, new GlideBitmapDrawableTranscoder(paramContext.getResources(), paramBitmapPool));
    this.transcoderRegistry.register(GifBitmapWrapper.class, GlideDrawable.class, new GifBitmapWrapperDrawableTranscoder(new GlideBitmapDrawableTranscoder(paramContext.getResources(), paramBitmapPool)));
    this.bitmapCenterCrop = new CenterCrop(paramBitmapPool);
    this.drawableCenterCrop = new GifBitmapWrapperTransformation(paramBitmapPool, this.bitmapCenterCrop);
    this.bitmapFitCenter = new FitCenter(paramBitmapPool);
    this.drawableFitCenter = new GifBitmapWrapperTransformation(paramBitmapPool, this.bitmapFitCenter);
  }
  
  public static <T> ModelLoader<T, ParcelFileDescriptor> buildFileDescriptorModelLoader(Class<T> paramClass, Context paramContext)
  {
    return buildModelLoader(paramClass, ParcelFileDescriptor.class, paramContext);
  }
  
  public static <T> ModelLoader<T, ParcelFileDescriptor> buildFileDescriptorModelLoader(T paramT, Context paramContext)
  {
    return buildModelLoader(paramT, ParcelFileDescriptor.class, paramContext);
  }
  
  public static <T, Y> ModelLoader<T, Y> buildModelLoader(Class<T> paramClass, Class<Y> paramClass1, Context paramContext)
  {
    if (paramClass == null)
    {
      if (Log.isLoggable("Glide", 3)) {
        Log.d("Glide", "Unable to load null model, setting placeholder only");
      }
      return null;
    }
    return get(paramContext).getLoaderFactory().buildModelLoader(paramClass, paramClass1);
  }
  
  public static <T, Y> ModelLoader<T, Y> buildModelLoader(T paramT, Class<Y> paramClass, Context paramContext)
  {
    if (paramT != null) {}
    for (paramT = paramT.getClass();; paramT = null) {
      return buildModelLoader(paramT, paramClass, paramContext);
    }
  }
  
  public static <T> ModelLoader<T, InputStream> buildStreamModelLoader(Class<T> paramClass, Context paramContext)
  {
    return buildModelLoader(paramClass, InputStream.class, paramContext);
  }
  
  public static <T> ModelLoader<T, InputStream> buildStreamModelLoader(T paramT, Context paramContext)
  {
    return buildModelLoader(paramT, InputStream.class, paramContext);
  }
  
  public static void clear(View paramView)
  {
    clear(new ClearTarget(paramView));
  }
  
  public static void clear(FutureTarget<?> paramFutureTarget)
  {
    paramFutureTarget.clear();
  }
  
  public static void clear(Target<?> paramTarget)
  {
    Util.assertMainThread();
    Request localRequest = paramTarget.getRequest();
    if (localRequest != null)
    {
      localRequest.clear();
      paramTarget.setRequest(null);
    }
  }
  
  public static Glide get(Context paramContext)
  {
    if (glide == null)
    {
      try
      {
        if (glide != null) {
          break label126;
        }
        paramContext = paramContext.getApplicationContext();
        localObject = new ManifestParser(paramContext).parse();
        GlideBuilder localGlideBuilder = new GlideBuilder(paramContext);
        Iterator localIterator = ((List)localObject).iterator();
        while (localIterator.hasNext()) {
          ((GlideModule)localIterator.next()).applyOptions(paramContext, localGlideBuilder);
        }
        glide = localGlideBuilder.createGlide();
      }
      finally {}
      Object localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        ((GlideModule)((Iterator)localObject).next()).registerComponents(paramContext, glide);
      }
    }
    label126:
    return glide;
  }
  
  private GenericLoaderFactory getLoaderFactory()
  {
    return this.loaderFactory;
  }
  
  public static File getPhotoCacheDir(Context paramContext)
  {
    return getPhotoCacheDir(paramContext, "image_manager_disk_cache");
  }
  
  public static File getPhotoCacheDir(Context paramContext, String paramString)
  {
    paramContext = paramContext.getCacheDir();
    if (paramContext != null)
    {
      paramString = new File(paramContext, paramString);
      paramContext = paramString;
      if (!paramString.mkdirs()) {
        if (paramString.exists())
        {
          paramContext = paramString;
          if (paramString.isDirectory()) {}
        }
        else
        {
          paramContext = null;
        }
      }
      return paramContext;
    }
    if (Log.isLoggable("Glide", 6)) {
      Log.e("Glide", "default disk cache dir is null");
    }
    return null;
  }
  
  @Deprecated
  public static boolean isSetup()
  {
    return glide != null;
  }
  
  @Deprecated
  public static void setup(GlideBuilder paramGlideBuilder)
  {
    if (isSetup()) {
      throw new IllegalArgumentException("Glide is already setup, check with isSetup() first");
    }
    glide = paramGlideBuilder.createGlide();
  }
  
  static void tearDown()
  {
    glide = null;
  }
  
  public static RequestManager with(Activity paramActivity)
  {
    return RequestManagerRetriever.get().get(paramActivity);
  }
  
  @TargetApi(11)
  public static RequestManager with(android.app.Fragment paramFragment)
  {
    return RequestManagerRetriever.get().get(paramFragment);
  }
  
  public static RequestManager with(Context paramContext)
  {
    return RequestManagerRetriever.get().get(paramContext);
  }
  
  public static RequestManager with(android.support.v4.app.Fragment paramFragment)
  {
    return RequestManagerRetriever.get().get(paramFragment);
  }
  
  public static RequestManager with(FragmentActivity paramFragmentActivity)
  {
    return RequestManagerRetriever.get().get(paramFragmentActivity);
  }
  
  <T, Z> DataLoadProvider<T, Z> buildDataProvider(Class<T> paramClass, Class<Z> paramClass1)
  {
    return this.dataLoadProviderRegistry.get(paramClass, paramClass1);
  }
  
  <R> Target<R> buildImageViewTarget(ImageView paramImageView, Class<R> paramClass)
  {
    return this.imageViewTargetFactory.buildTarget(paramImageView, paramClass);
  }
  
  <Z, R> ResourceTranscoder<Z, R> buildTranscoder(Class<Z> paramClass, Class<R> paramClass1)
  {
    return this.transcoderRegistry.get(paramClass, paramClass1);
  }
  
  public void clearDiskCache()
  {
    Util.assertBackgroundThread();
    getEngine().clearDiskCache();
  }
  
  public void clearMemory()
  {
    Util.assertMainThread();
    this.memoryCache.clearMemory();
    this.bitmapPool.clearMemory();
  }
  
  CenterCrop getBitmapCenterCrop()
  {
    return this.bitmapCenterCrop;
  }
  
  FitCenter getBitmapFitCenter()
  {
    return this.bitmapFitCenter;
  }
  
  public BitmapPool getBitmapPool()
  {
    return this.bitmapPool;
  }
  
  DecodeFormat getDecodeFormat()
  {
    return this.decodeFormat;
  }
  
  GifBitmapWrapperTransformation getDrawableCenterCrop()
  {
    return this.drawableCenterCrop;
  }
  
  GifBitmapWrapperTransformation getDrawableFitCenter()
  {
    return this.drawableFitCenter;
  }
  
  Engine getEngine()
  {
    return this.engine;
  }
  
  Handler getMainHandler()
  {
    return this.mainHandler;
  }
  
  public void preFillBitmapPool(PreFillType.Builder... paramVarArgs)
  {
    this.bitmapPreFiller.preFill(paramVarArgs);
  }
  
  public <T, Y> void register(Class<T> paramClass, Class<Y> paramClass1, ModelLoaderFactory<T, Y> paramModelLoaderFactory)
  {
    paramClass = this.loaderFactory.register(paramClass, paramClass1, paramModelLoaderFactory);
    if (paramClass != null) {
      paramClass.teardown();
    }
  }
  
  public void setMemoryCategory(MemoryCategory paramMemoryCategory)
  {
    Util.assertMainThread();
    this.memoryCache.setSizeMultiplier(paramMemoryCategory.getMultiplier());
    this.bitmapPool.setSizeMultiplier(paramMemoryCategory.getMultiplier());
  }
  
  public void trimMemory(int paramInt)
  {
    Util.assertMainThread();
    this.memoryCache.trimMemory(paramInt);
    this.bitmapPool.trimMemory(paramInt);
  }
  
  @Deprecated
  public <T, Y> void unregister(Class<T> paramClass, Class<Y> paramClass1)
  {
    paramClass = this.loaderFactory.unregister(paramClass, paramClass1);
    if (paramClass != null) {
      paramClass.teardown();
    }
  }
  
  private static class ClearTarget
    extends ViewTarget<View, Object>
  {
    public ClearTarget(View paramView)
    {
      super();
    }
    
    public void onLoadCleared(Drawable paramDrawable) {}
    
    public void onLoadFailed(Exception paramException, Drawable paramDrawable) {}
    
    public void onLoadStarted(Drawable paramDrawable) {}
    
    public void onResourceReady(Object paramObject, GlideAnimation<? super Object> paramGlideAnimation) {}
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\Glide.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */