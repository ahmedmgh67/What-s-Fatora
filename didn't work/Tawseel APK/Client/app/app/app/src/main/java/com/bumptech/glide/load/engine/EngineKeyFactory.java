package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;

class EngineKeyFactory
{
  public EngineKey buildKey(String paramString, Key paramKey, int paramInt1, int paramInt2, ResourceDecoder paramResourceDecoder1, ResourceDecoder paramResourceDecoder2, Transformation paramTransformation, ResourceEncoder paramResourceEncoder, ResourceTranscoder paramResourceTranscoder, Encoder paramEncoder)
  {
    return new EngineKey(paramString, paramKey, paramInt1, paramInt2, paramResourceDecoder1, paramResourceDecoder2, paramTransformation, paramResourceEncoder, paramResourceTranscoder, paramEncoder);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\engine\EngineKeyFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */