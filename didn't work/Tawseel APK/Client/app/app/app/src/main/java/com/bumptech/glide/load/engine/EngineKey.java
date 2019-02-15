package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

class EngineKey
  implements Key
{
  private static final String EMPTY_LOG_STRING = "";
  private final ResourceDecoder cacheDecoder;
  private final ResourceDecoder decoder;
  private final ResourceEncoder encoder;
  private int hashCode;
  private final int height;
  private final String id;
  private Key originalKey;
  private final Key signature;
  private final Encoder sourceEncoder;
  private String stringKey;
  private final ResourceTranscoder transcoder;
  private final Transformation transformation;
  private final int width;
  
  public EngineKey(String paramString, Key paramKey, int paramInt1, int paramInt2, ResourceDecoder paramResourceDecoder1, ResourceDecoder paramResourceDecoder2, Transformation paramTransformation, ResourceEncoder paramResourceEncoder, ResourceTranscoder paramResourceTranscoder, Encoder paramEncoder)
  {
    this.id = paramString;
    this.signature = paramKey;
    this.width = paramInt1;
    this.height = paramInt2;
    this.cacheDecoder = paramResourceDecoder1;
    this.decoder = paramResourceDecoder2;
    this.transformation = paramTransformation;
    this.encoder = paramResourceEncoder;
    this.transcoder = paramResourceTranscoder;
    this.sourceEncoder = paramEncoder;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1;
    if (this == paramObject) {
      bool1 = true;
    }
    int i;
    label117:
    label126:
    label180:
    label189:
    label243:
    label252:
    label306:
    label315:
    label369:
    label378:
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    do
                    {
                      do
                      {
                        do
                        {
                          do
                          {
                            do
                            {
                              do
                              {
                                do
                                {
                                  do
                                  {
                                    return bool1;
                                    bool1 = bool2;
                                  } while (paramObject == null);
                                  bool1 = bool2;
                                } while (getClass() != paramObject.getClass());
                                paramObject = (EngineKey)paramObject;
                                bool1 = bool2;
                              } while (!this.id.equals(((EngineKey)paramObject).id));
                              bool1 = bool2;
                            } while (!this.signature.equals(((EngineKey)paramObject).signature));
                            bool1 = bool2;
                          } while (this.height != ((EngineKey)paramObject).height);
                          bool1 = bool2;
                        } while (this.width != ((EngineKey)paramObject).width);
                        if (this.transformation != null) {
                          break;
                        }
                        i = 1;
                        if (((EngineKey)paramObject).transformation != null) {
                          break label493;
                        }
                        j = 1;
                        bool1 = bool2;
                      } while ((i ^ j) != 0);
                      if (this.transformation == null) {
                        break;
                      }
                      bool1 = bool2;
                    } while (!this.transformation.getId().equals(((EngineKey)paramObject).transformation.getId()));
                    if (this.decoder != null) {
                      break label498;
                    }
                    i = 1;
                    if (((EngineKey)paramObject).decoder != null) {
                      break label503;
                    }
                    j = 1;
                    bool1 = bool2;
                  } while ((i ^ j) != 0);
                  if (this.decoder == null) {
                    break;
                  }
                  bool1 = bool2;
                } while (!this.decoder.getId().equals(((EngineKey)paramObject).decoder.getId()));
                if (this.cacheDecoder != null) {
                  break label508;
                }
                i = 1;
                if (((EngineKey)paramObject).cacheDecoder != null) {
                  break label513;
                }
                j = 1;
                bool1 = bool2;
              } while ((i ^ j) != 0);
              if (this.cacheDecoder == null) {
                break;
              }
              bool1 = bool2;
            } while (!this.cacheDecoder.getId().equals(((EngineKey)paramObject).cacheDecoder.getId()));
            if (this.encoder != null) {
              break label518;
            }
            i = 1;
            if (((EngineKey)paramObject).encoder != null) {
              break label523;
            }
            j = 1;
            bool1 = bool2;
          } while ((i ^ j) != 0);
          if (this.encoder == null) {
            break;
          }
          bool1 = bool2;
        } while (!this.encoder.getId().equals(((EngineKey)paramObject).encoder.getId()));
        if (this.transcoder != null) {
          break label528;
        }
        i = 1;
        if (((EngineKey)paramObject).transcoder != null) {
          break label533;
        }
        j = 1;
        bool1 = bool2;
      } while ((i ^ j) != 0);
      if (this.transcoder == null) {
        break;
      }
      bool1 = bool2;
    } while (!this.transcoder.getId().equals(((EngineKey)paramObject).transcoder.getId()));
    if (this.sourceEncoder == null)
    {
      i = 1;
      label432:
      if (((EngineKey)paramObject).sourceEncoder != null) {
        break label543;
      }
    }
    label493:
    label498:
    label503:
    label508:
    label513:
    label518:
    label523:
    label528:
    label533:
    label543:
    for (int j = 1;; j = 0)
    {
      bool1 = bool2;
      if ((i ^ j) != 0) {
        break;
      }
      if (this.sourceEncoder != null)
      {
        bool1 = bool2;
        if (!this.sourceEncoder.getId().equals(((EngineKey)paramObject).sourceEncoder.getId())) {
          break;
        }
      }
      return true;
      i = 0;
      break label117;
      j = 0;
      break label126;
      i = 0;
      break label180;
      j = 0;
      break label189;
      i = 0;
      break label243;
      j = 0;
      break label252;
      i = 0;
      break label306;
      j = 0;
      break label315;
      i = 0;
      break label369;
      j = 0;
      break label378;
      i = 0;
      break label432;
    }
  }
  
  public Key getOriginalKey()
  {
    if (this.originalKey == null) {
      this.originalKey = new OriginalKey(this.id, this.signature);
    }
    return this.originalKey;
  }
  
  public int hashCode()
  {
    int j = 0;
    int k;
    if (this.hashCode == 0)
    {
      this.hashCode = this.id.hashCode();
      this.hashCode = (this.hashCode * 31 + this.signature.hashCode());
      this.hashCode = (this.hashCode * 31 + this.width);
      this.hashCode = (this.hashCode * 31 + this.height);
      k = this.hashCode;
      if (this.cacheDecoder == null) {
        break label288;
      }
      i = this.cacheDecoder.getId().hashCode();
      this.hashCode = (i + k * 31);
      k = this.hashCode;
      if (this.decoder == null) {
        break label293;
      }
      i = this.decoder.getId().hashCode();
      label131:
      this.hashCode = (i + k * 31);
      k = this.hashCode;
      if (this.transformation == null) {
        break label298;
      }
      i = this.transformation.getId().hashCode();
      label166:
      this.hashCode = (i + k * 31);
      k = this.hashCode;
      if (this.encoder == null) {
        break label303;
      }
      i = this.encoder.getId().hashCode();
      label201:
      this.hashCode = (i + k * 31);
      k = this.hashCode;
      if (this.transcoder == null) {
        break label308;
      }
    }
    label288:
    label293:
    label298:
    label303:
    label308:
    for (int i = this.transcoder.getId().hashCode();; i = 0)
    {
      this.hashCode = (i + k * 31);
      k = this.hashCode;
      i = j;
      if (this.sourceEncoder != null) {
        i = this.sourceEncoder.getId().hashCode();
      }
      this.hashCode = (k * 31 + i);
      return this.hashCode;
      i = 0;
      break;
      i = 0;
      break label131;
      i = 0;
      break label166;
      i = 0;
      break label201;
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder;
    if (this.stringKey == null)
    {
      localStringBuilder = new StringBuilder().append("EngineKey{").append(this.id).append('+').append(this.signature).append("+[").append(this.width).append('x').append(this.height).append("]+").append('\'');
      if (this.cacheDecoder == null) {
        break label307;
      }
      str = this.cacheDecoder.getId();
      localStringBuilder = localStringBuilder.append(str).append('\'').append('+').append('\'');
      if (this.decoder == null) {
        break label313;
      }
      str = this.decoder.getId();
      label128:
      localStringBuilder = localStringBuilder.append(str).append('\'').append('+').append('\'');
      if (this.transformation == null) {
        break label319;
      }
      str = this.transformation.getId();
      label166:
      localStringBuilder = localStringBuilder.append(str).append('\'').append('+').append('\'');
      if (this.encoder == null) {
        break label325;
      }
      str = this.encoder.getId();
      label204:
      localStringBuilder = localStringBuilder.append(str).append('\'').append('+').append('\'');
      if (this.transcoder == null) {
        break label331;
      }
      str = this.transcoder.getId();
      label242:
      localStringBuilder = localStringBuilder.append(str).append('\'').append('+').append('\'');
      if (this.sourceEncoder == null) {
        break label337;
      }
    }
    label307:
    label313:
    label319:
    label325:
    label331:
    label337:
    for (String str = this.sourceEncoder.getId();; str = "")
    {
      this.stringKey = (str + '\'' + '}');
      return this.stringKey;
      str = "";
      break;
      str = "";
      break label128;
      str = "";
      break label166;
      str = "";
      break label204;
      str = "";
      break label242;
    }
  }
  
  public void updateDiskCacheKey(MessageDigest paramMessageDigest)
    throws UnsupportedEncodingException
  {
    Object localObject = ByteBuffer.allocate(8).putInt(this.width).putInt(this.height).array();
    this.signature.updateDiskCacheKey(paramMessageDigest);
    paramMessageDigest.update(this.id.getBytes("UTF-8"));
    paramMessageDigest.update((byte[])localObject);
    if (this.cacheDecoder != null)
    {
      localObject = this.cacheDecoder.getId();
      paramMessageDigest.update(((String)localObject).getBytes("UTF-8"));
      if (this.decoder == null) {
        break label193;
      }
      localObject = this.decoder.getId();
      label95:
      paramMessageDigest.update(((String)localObject).getBytes("UTF-8"));
      if (this.transformation == null) {
        break label199;
      }
      localObject = this.transformation.getId();
      label122:
      paramMessageDigest.update(((String)localObject).getBytes("UTF-8"));
      if (this.encoder == null) {
        break label205;
      }
      localObject = this.encoder.getId();
      label149:
      paramMessageDigest.update(((String)localObject).getBytes("UTF-8"));
      if (this.sourceEncoder == null) {
        break label211;
      }
    }
    label193:
    label199:
    label205:
    label211:
    for (localObject = this.sourceEncoder.getId();; localObject = "")
    {
      paramMessageDigest.update(((String)localObject).getBytes("UTF-8"));
      return;
      localObject = "";
      break;
      localObject = "";
      break label95;
      localObject = "";
      break label122;
      localObject = "";
      break label149;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\engine\EngineKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */