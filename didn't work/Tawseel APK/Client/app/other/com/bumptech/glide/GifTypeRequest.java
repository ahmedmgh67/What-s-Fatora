package com.bumptech.glide;

import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.load.resource.transcode.GifDrawableBytesTranscoder;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.provider.FixedLoadProvider;
import java.io.InputStream;

public class GifTypeRequest<ModelType>
  extends GifRequestBuilder<ModelType>
{
  private final RequestManager.OptionsApplier optionsApplier;
  private final ModelLoader<ModelType, InputStream> streamModelLoader;
  
  GifTypeRequest(GenericRequestBuilder<ModelType, ?, ?, ?> paramGenericRequestBuilder, ModelLoader<ModelType, InputStream> paramModelLoader, RequestManager.OptionsApplier paramOptionsApplier)
  {
    super(buildProvider(paramGenericRequestBuilder.glide, paramModelLoader, GifDrawable.class, null), GifDrawable.class, paramGenericRequestBuilder);
    this.streamModelLoader = paramModelLoader;
    this.optionsApplier = paramOptionsApplier;
    crossFade();
  }
  
  private static <A, R> FixedLoadProvider<A, InputStream, GifDrawable, R> buildProvider(Glide paramGlide, ModelLoader<A, InputStream> paramModelLoader, Class<R> paramClass, ResourceTranscoder<GifDrawable, R> paramResourceTranscoder)
  {
    if (paramModelLoader == null) {
      return null;
    }
    Object localObject = paramResourceTranscoder;
    if (paramResourceTranscoder == null) {
      localObject = paramGlide.buildTranscoder(GifDrawable.class, paramClass);
    }
    return new FixedLoadProvider(paramModelLoader, (ResourceTranscoder)localObject, paramGlide.buildDataProvider(InputStream.class, GifDrawable.class));
  }
  
  public GenericRequestBuilder<ModelType, InputStream, GifDrawable, byte[]> toBytes()
  {
    return transcode(new GifDrawableBytesTranscoder(), byte[].class);
  }
  
  public <R> GenericRequestBuilder<ModelType, InputStream, GifDrawable, R> transcode(ResourceTranscoder<GifDrawable, R> paramResourceTranscoder, Class<R> paramClass)
  {
    paramResourceTranscoder = buildProvider(this.glide, this.streamModelLoader, paramClass, paramResourceTranscoder);
    return this.optionsApplier.apply(new GenericRequestBuilder(paramResourceTranscoder, paramClass, this));
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\GifTypeRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */