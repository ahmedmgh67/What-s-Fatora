package com.bumptech.glide.load.resource.gifbitmap;

import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.model.ImageVideoWrapper;
import java.io.IOException;
import java.io.InputStream;

public class GifBitmapWrapperStreamResourceDecoder
  implements ResourceDecoder<InputStream, GifBitmapWrapper>
{
  private final ResourceDecoder<ImageVideoWrapper, GifBitmapWrapper> gifBitmapDecoder;
  
  public GifBitmapWrapperStreamResourceDecoder(ResourceDecoder<ImageVideoWrapper, GifBitmapWrapper> paramResourceDecoder)
  {
    this.gifBitmapDecoder = paramResourceDecoder;
  }
  
  public Resource<GifBitmapWrapper> decode(InputStream paramInputStream, int paramInt1, int paramInt2)
    throws IOException
  {
    return this.gifBitmapDecoder.decode(new ImageVideoWrapper(paramInputStream, null), paramInt1, paramInt2);
  }
  
  public String getId()
  {
    return this.gifBitmapDecoder.getId();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\gifbitmap\GifBitmapWrapperStreamResourceDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */