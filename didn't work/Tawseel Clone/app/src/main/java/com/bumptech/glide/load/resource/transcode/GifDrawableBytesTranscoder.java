package com.bumptech.glide.load.resource.transcode;

import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.bytes.BytesResource;
import com.bumptech.glide.load.resource.gif.GifDrawable;

public class GifDrawableBytesTranscoder
  implements ResourceTranscoder<GifDrawable, byte[]>
{
  public String getId()
  {
    return "GifDrawableBytesTranscoder.com.bumptech.glide.load.resource.transcode";
  }
  
  public Resource<byte[]> transcode(Resource<GifDrawable> paramResource)
  {
    return new BytesResource(((GifDrawable)paramResource.get()).getData());
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\transcode\GifDrawableBytesTranscoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */