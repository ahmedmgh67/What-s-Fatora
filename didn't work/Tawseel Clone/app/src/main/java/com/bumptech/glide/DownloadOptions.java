package com.bumptech.glide;

import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.Target;
import java.io.File;

abstract interface DownloadOptions
{
  public abstract FutureTarget<File> downloadOnly(int paramInt1, int paramInt2);
  
  public abstract <Y extends Target<File>> Y downloadOnly(Y paramY);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\DownloadOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */