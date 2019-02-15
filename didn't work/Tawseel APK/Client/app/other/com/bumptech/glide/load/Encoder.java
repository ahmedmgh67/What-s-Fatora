package com.bumptech.glide.load;

import java.io.OutputStream;

public abstract interface Encoder<T>
{
  public abstract boolean encode(T paramT, OutputStream paramOutputStream);
  
  public abstract String getId();
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\Encoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */