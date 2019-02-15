package com.bumptech.glide.gifdecoder;

import java.util.ArrayList;
import java.util.List;

public class GifHeader
{
  int bgColor;
  int bgIndex;
  GifFrame currentFrame;
  int frameCount = 0;
  List<GifFrame> frames = new ArrayList();
  int[] gct = null;
  boolean gctFlag;
  int gctSize;
  int height;
  int loopCount;
  int pixelAspect;
  int status = 0;
  int width;
  
  public int getHeight()
  {
    return this.height;
  }
  
  public int getNumFrames()
  {
    return this.frameCount;
  }
  
  public int getStatus()
  {
    return this.status;
  }
  
  public int getWidth()
  {
    return this.width;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\gifdecoder\GifHeader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */