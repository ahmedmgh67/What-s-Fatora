package com.bumptech.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.animation.Animation;
import android.widget.ImageView;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.UnitTransformation;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.RequestTracker;
import com.bumptech.glide.provider.ChildLoadProvider;
import com.bumptech.glide.provider.LoadProvider;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.GenericRequest;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestCoordinator;
import com.bumptech.glide.request.RequestFutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.ThumbnailRequestCoordinator;
import com.bumptech.glide.request.animation.GlideAnimationFactory;
import com.bumptech.glide.request.animation.NoAnimation;
import com.bumptech.glide.request.animation.ViewAnimationFactory;
import com.bumptech.glide.request.animation.ViewPropertyAnimation.Animator;
import com.bumptech.glide.request.animation.ViewPropertyAnimationFactory;
import com.bumptech.glide.request.target.PreloadTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.EmptySignature;
import com.bumptech.glide.util.Util;
import java.io.File;

public class GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType>
  implements Cloneable
{
  private GlideAnimationFactory<TranscodeType> animationFactory = NoAnimation.getFactory();
  protected final Context context;
  private DiskCacheStrategy diskCacheStrategy = DiskCacheStrategy.RESULT;
  private int errorId;
  private Drawable errorPlaceholder;
  private Drawable fallbackDrawable;
  private int fallbackResource;
  protected final Glide glide;
  private boolean isCacheable = true;
  private boolean isModelSet;
  private boolean isThumbnailBuilt;
  private boolean isTransformationSet;
  protected final Lifecycle lifecycle;
  private ChildLoadProvider<ModelType, DataType, ResourceType, TranscodeType> loadProvider;
  private ModelType model;
  protected final Class<ModelType> modelClass;
  private int overrideHeight = -1;
  private int overrideWidth = -1;
  private Drawable placeholderDrawable;
  private int placeholderId;
  private Priority priority = null;
  private RequestListener<? super ModelType, TranscodeType> requestListener;
  protected final RequestTracker requestTracker;
  private Key signature = EmptySignature.obtain();
  private Float sizeMultiplier = Float.valueOf(1.0F);
  private Float thumbSizeMultiplier;
  private GenericRequestBuilder<?, ?, ?, TranscodeType> thumbnailRequestBuilder;
  protected final Class<TranscodeType> transcodeClass;
  private Transformation<ResourceType> transformation = UnitTransformation.get();
  
  GenericRequestBuilder(Context paramContext, Class<ModelType> paramClass, LoadProvider<ModelType, DataType, ResourceType, TranscodeType> paramLoadProvider, Class<TranscodeType> paramClass1, Glide paramGlide, RequestTracker paramRequestTracker, Lifecycle paramLifecycle)
  {
    this.context = paramContext;
    this.modelClass = paramClass;
    this.transcodeClass = paramClass1;
    this.glide = paramGlide;
    this.requestTracker = paramRequestTracker;
    this.lifecycle = paramLifecycle;
    paramClass1 = (Class<TranscodeType>)localObject;
    if (paramLoadProvider != null) {
      paramClass1 = new ChildLoadProvider(paramLoadProvider);
    }
    this.loadProvider = paramClass1;
    if (paramContext == null) {
      throw new NullPointerException("Context can't be null");
    }
    if ((paramClass != null) && (paramLoadProvider == null)) {
      throw new NullPointerException("LoadProvider must not be null");
    }
  }
  
  GenericRequestBuilder(LoadProvider<ModelType, DataType, ResourceType, TranscodeType> paramLoadProvider, Class<TranscodeType> paramClass, GenericRequestBuilder<ModelType, ?, ?, ?> paramGenericRequestBuilder)
  {
    this(paramGenericRequestBuilder.context, paramGenericRequestBuilder.modelClass, paramLoadProvider, paramClass, paramGenericRequestBuilder.glide, paramGenericRequestBuilder.requestTracker, paramGenericRequestBuilder.lifecycle);
    this.model = paramGenericRequestBuilder.model;
    this.isModelSet = paramGenericRequestBuilder.isModelSet;
    this.diskCacheStrategy = paramGenericRequestBuilder.diskCacheStrategy;
    this.isCacheable = paramGenericRequestBuilder.isCacheable;
  }
  
  private Request buildRequest(Target<TranscodeType> paramTarget)
  {
    if (this.priority == null) {
      this.priority = Priority.NORMAL;
    }
    return buildRequestRecursive(paramTarget, null);
  }
  
  private Request buildRequestRecursive(Target<TranscodeType> paramTarget, ThumbnailRequestCoordinator paramThumbnailRequestCoordinator)
  {
    if (this.thumbnailRequestBuilder != null)
    {
      if (this.isThumbnailBuilt) {
        throw new IllegalStateException("You cannot use a request as both the main request and a thumbnail, consider using clone() on the request(s) passed to thumbnail()");
      }
      if (this.thumbnailRequestBuilder.animationFactory.equals(NoAnimation.getFactory())) {
        this.thumbnailRequestBuilder.animationFactory = this.animationFactory;
      }
      if (this.thumbnailRequestBuilder.priority == null) {
        this.thumbnailRequestBuilder.priority = getThumbnailPriority();
      }
      if ((Util.isValidDimensions(this.overrideWidth, this.overrideHeight)) && (!Util.isValidDimensions(this.thumbnailRequestBuilder.overrideWidth, this.thumbnailRequestBuilder.overrideHeight))) {
        this.thumbnailRequestBuilder.override(this.overrideWidth, this.overrideHeight);
      }
      paramThumbnailRequestCoordinator = new ThumbnailRequestCoordinator(paramThumbnailRequestCoordinator);
      Request localRequest = obtainRequest(paramTarget, this.sizeMultiplier.floatValue(), this.priority, paramThumbnailRequestCoordinator);
      this.isThumbnailBuilt = true;
      paramTarget = this.thumbnailRequestBuilder.buildRequestRecursive(paramTarget, paramThumbnailRequestCoordinator);
      this.isThumbnailBuilt = false;
      paramThumbnailRequestCoordinator.setRequests(localRequest, paramTarget);
      return paramThumbnailRequestCoordinator;
    }
    if (this.thumbSizeMultiplier != null)
    {
      paramThumbnailRequestCoordinator = new ThumbnailRequestCoordinator(paramThumbnailRequestCoordinator);
      paramThumbnailRequestCoordinator.setRequests(obtainRequest(paramTarget, this.sizeMultiplier.floatValue(), this.priority, paramThumbnailRequestCoordinator), obtainRequest(paramTarget, this.thumbSizeMultiplier.floatValue(), getThumbnailPriority(), paramThumbnailRequestCoordinator));
      return paramThumbnailRequestCoordinator;
    }
    return obtainRequest(paramTarget, this.sizeMultiplier.floatValue(), this.priority, paramThumbnailRequestCoordinator);
  }
  
  private Priority getThumbnailPriority()
  {
    if (this.priority == Priority.LOW) {
      return Priority.NORMAL;
    }
    if (this.priority == Priority.NORMAL) {
      return Priority.HIGH;
    }
    return Priority.IMMEDIATE;
  }
  
  private Request obtainRequest(Target<TranscodeType> paramTarget, float paramFloat, Priority paramPriority, RequestCoordinator paramRequestCoordinator)
  {
    return GenericRequest.obtain(this.loadProvider, this.model, this.signature, this.context, paramPriority, paramTarget, paramFloat, this.placeholderDrawable, this.placeholderId, this.errorPlaceholder, this.errorId, this.fallbackDrawable, this.fallbackResource, this.requestListener, paramRequestCoordinator, this.glide.getEngine(), this.transformation, this.transcodeClass, this.isCacheable, this.animationFactory, this.overrideWidth, this.overrideHeight, this.diskCacheStrategy);
  }
  
  public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> animate(int paramInt)
  {
    return animate(new ViewAnimationFactory(this.context, paramInt));
  }
  
  @Deprecated
  public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> animate(Animation paramAnimation)
  {
    return animate(new ViewAnimationFactory(paramAnimation));
  }
  
  GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> animate(GlideAnimationFactory<TranscodeType> paramGlideAnimationFactory)
  {
    if (paramGlideAnimationFactory == null) {
      throw new NullPointerException("Animation factory must not be null!");
    }
    this.animationFactory = paramGlideAnimationFactory;
    return this;
  }
  
  public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> animate(ViewPropertyAnimation.Animator paramAnimator)
  {
    return animate(new ViewPropertyAnimationFactory(paramAnimator));
  }
  
  void applyCenterCrop() {}
  
  void applyFitCenter() {}
  
  public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> cacheDecoder(ResourceDecoder<File, ResourceType> paramResourceDecoder)
  {
    if (this.loadProvider != null) {
      this.loadProvider.setCacheDecoder(paramResourceDecoder);
    }
    return this;
  }
  
  /* Error */
  public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> clone()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 295	java/lang/Object:clone	()Ljava/lang/Object;
    //   4: checkcast 2	com/bumptech/glide/GenericRequestBuilder
    //   7: astore_2
    //   8: aload_0
    //   9: getfield 137	com/bumptech/glide/GenericRequestBuilder:loadProvider	Lcom/bumptech/glide/provider/ChildLoadProvider;
    //   12: ifnull +18 -> 30
    //   15: aload_0
    //   16: getfield 137	com/bumptech/glide/GenericRequestBuilder:loadProvider	Lcom/bumptech/glide/provider/ChildLoadProvider;
    //   19: invokevirtual 298	com/bumptech/glide/provider/ChildLoadProvider:clone	()Lcom/bumptech/glide/provider/ChildLoadProvider;
    //   22: astore_1
    //   23: aload_2
    //   24: aload_1
    //   25: putfield 137	com/bumptech/glide/GenericRequestBuilder:loadProvider	Lcom/bumptech/glide/provider/ChildLoadProvider;
    //   28: aload_2
    //   29: areturn
    //   30: aconst_null
    //   31: astore_1
    //   32: goto -9 -> 23
    //   35: astore_1
    //   36: new 300	java/lang/RuntimeException
    //   39: dup
    //   40: aload_1
    //   41: invokespecial 303	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   44: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	45	0	this	GenericRequestBuilder
    //   22	10	1	localChildLoadProvider	ChildLoadProvider
    //   35	6	1	localCloneNotSupportedException	CloneNotSupportedException
    //   7	22	2	localGenericRequestBuilder	GenericRequestBuilder
    // Exception table:
    //   from	to	target	type
    //   0	23	35	java/lang/CloneNotSupportedException
    //   23	28	35	java/lang/CloneNotSupportedException
  }
  
  public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> decoder(ResourceDecoder<DataType, ResourceType> paramResourceDecoder)
  {
    if (this.loadProvider != null) {
      this.loadProvider.setSourceDecoder(paramResourceDecoder);
    }
    return this;
  }
  
  public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> diskCacheStrategy(DiskCacheStrategy paramDiskCacheStrategy)
  {
    this.diskCacheStrategy = paramDiskCacheStrategy;
    return this;
  }
  
  public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> dontAnimate()
  {
    return animate(NoAnimation.getFactory());
  }
  
  public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> dontTransform()
  {
    return transform(new Transformation[] { UnitTransformation.get() });
  }
  
  public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> encoder(ResourceEncoder<ResourceType> paramResourceEncoder)
  {
    if (this.loadProvider != null) {
      this.loadProvider.setEncoder(paramResourceEncoder);
    }
    return this;
  }
  
  public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> error(int paramInt)
  {
    this.errorId = paramInt;
    return this;
  }
  
  public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> error(Drawable paramDrawable)
  {
    this.errorPlaceholder = paramDrawable;
    return this;
  }
  
  public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> fallback(int paramInt)
  {
    this.fallbackResource = paramInt;
    return this;
  }
  
  public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> fallback(Drawable paramDrawable)
  {
    this.fallbackDrawable = paramDrawable;
    return this;
  }
  
  public FutureTarget<TranscodeType> into(int paramInt1, int paramInt2)
  {
    final RequestFutureTarget localRequestFutureTarget = new RequestFutureTarget(this.glide.getMainHandler(), paramInt1, paramInt2);
    this.glide.getMainHandler().post(new Runnable()
    {
      public void run()
      {
        if (!localRequestFutureTarget.isCancelled()) {
          GenericRequestBuilder.this.into(localRequestFutureTarget);
        }
      }
    });
    return localRequestFutureTarget;
  }
  
  public Target<TranscodeType> into(ImageView paramImageView)
  {
    
    if (paramImageView == null) {
      throw new IllegalArgumentException("You must pass in a non null View");
    }
    if ((!this.isTransformationSet) && (paramImageView.getScaleType() != null)) {
      switch (paramImageView.getScaleType())
      {
      }
    }
    for (;;)
    {
      return into(this.glide.buildImageViewTarget(paramImageView, this.transcodeClass));
      applyCenterCrop();
      continue;
      applyFitCenter();
    }
  }
  
  public <Y extends Target<TranscodeType>> Y into(Y paramY)
  {
    
    if (paramY == null) {
      throw new IllegalArgumentException("You must pass in a non null Target");
    }
    if (!this.isModelSet) {
      throw new IllegalArgumentException("You must first set a model (try #load())");
    }
    Request localRequest = paramY.getRequest();
    if (localRequest != null)
    {
      localRequest.clear();
      this.requestTracker.removeRequest(localRequest);
      localRequest.recycle();
    }
    localRequest = buildRequest(paramY);
    paramY.setRequest(localRequest);
    this.lifecycle.addListener(paramY);
    this.requestTracker.runRequest(localRequest);
    return paramY;
  }
  
  public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> listener(RequestListener<? super ModelType, TranscodeType> paramRequestListener)
  {
    this.requestListener = paramRequestListener;
    return this;
  }
  
  public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> load(ModelType paramModelType)
  {
    this.model = paramModelType;
    this.isModelSet = true;
    return this;
  }
  
  public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> override(int paramInt1, int paramInt2)
  {
    if (!Util.isValidDimensions(paramInt1, paramInt2)) {
      throw new IllegalArgumentException("Width and height must be Target#SIZE_ORIGINAL or > 0");
    }
    this.overrideWidth = paramInt1;
    this.overrideHeight = paramInt2;
    return this;
  }
  
  public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> placeholder(int paramInt)
  {
    this.placeholderId = paramInt;
    return this;
  }
  
  public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> placeholder(Drawable paramDrawable)
  {
    this.placeholderDrawable = paramDrawable;
    return this;
  }
  
  public Target<TranscodeType> preload()
  {
    return preload(Integer.MIN_VALUE, Integer.MIN_VALUE);
  }
  
  public Target<TranscodeType> preload(int paramInt1, int paramInt2)
  {
    return into(PreloadTarget.obtain(paramInt1, paramInt2));
  }
  
  public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> priority(Priority paramPriority)
  {
    this.priority = paramPriority;
    return this;
  }
  
  public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> signature(Key paramKey)
  {
    if (paramKey == null) {
      throw new NullPointerException("Signature must not be null");
    }
    this.signature = paramKey;
    return this;
  }
  
  public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> sizeMultiplier(float paramFloat)
  {
    if ((paramFloat < 0.0F) || (paramFloat > 1.0F)) {
      throw new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
    }
    this.sizeMultiplier = Float.valueOf(paramFloat);
    return this;
  }
  
  public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> skipMemoryCache(boolean paramBoolean)
  {
    if (!paramBoolean) {}
    for (paramBoolean = true;; paramBoolean = false)
    {
      this.isCacheable = paramBoolean;
      return this;
    }
  }
  
  public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> sourceEncoder(Encoder<DataType> paramEncoder)
  {
    if (this.loadProvider != null) {
      this.loadProvider.setSourceEncoder(paramEncoder);
    }
    return this;
  }
  
  public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> thumbnail(float paramFloat)
  {
    if ((paramFloat < 0.0F) || (paramFloat > 1.0F)) {
      throw new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
    }
    this.thumbSizeMultiplier = Float.valueOf(paramFloat);
    return this;
  }
  
  public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> thumbnail(GenericRequestBuilder<?, ?, ?, TranscodeType> paramGenericRequestBuilder)
  {
    if (equals(paramGenericRequestBuilder)) {
      throw new IllegalArgumentException("You cannot set a request as a thumbnail for itself. Consider using clone() on the request you are passing to thumbnail()");
    }
    this.thumbnailRequestBuilder = paramGenericRequestBuilder;
    return this;
  }
  
  public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> transcoder(ResourceTranscoder<ResourceType, TranscodeType> paramResourceTranscoder)
  {
    if (this.loadProvider != null) {
      this.loadProvider.setTranscoder(paramResourceTranscoder);
    }
    return this;
  }
  
  public GenericRequestBuilder<ModelType, DataType, ResourceType, TranscodeType> transform(Transformation<ResourceType>... paramVarArgs)
  {
    this.isTransformationSet = true;
    if (paramVarArgs.length == 1)
    {
      this.transformation = paramVarArgs[0];
      return this;
    }
    this.transformation = new MultiTransformation(paramVarArgs);
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\GenericRequestBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */