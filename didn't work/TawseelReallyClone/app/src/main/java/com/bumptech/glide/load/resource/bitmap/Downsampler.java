package com.bumptech.glide.load.resource.bitmap;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import android.util.Log;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.ByteArrayPool;
import com.bumptech.glide.util.ExceptionCatchingInputStream;
import com.bumptech.glide.util.MarkEnforcingInputStream;
import com.bumptech.glide.util.Util;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.Queue;
import java.util.Set;

public abstract class Downsampler
  implements BitmapDecoder<InputStream>
{
  public static final Downsampler AT_LEAST = new Downsampler()
  {
    public String getId()
    {
      return "AT_LEAST.com.bumptech.glide.load.data.bitmap";
    }
    
    protected int getSampleSize(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4)
    {
      return Math.min(paramAnonymousInt2 / paramAnonymousInt4, paramAnonymousInt1 / paramAnonymousInt3);
    }
  };
  public static final Downsampler AT_MOST = new Downsampler()
  {
    public String getId()
    {
      return "AT_MOST.com.bumptech.glide.load.data.bitmap";
    }
    
    protected int getSampleSize(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4)
    {
      int i = 1;
      paramAnonymousInt1 = (int)Math.ceil(Math.max(paramAnonymousInt2 / paramAnonymousInt4, paramAnonymousInt1 / paramAnonymousInt3));
      paramAnonymousInt2 = Math.max(1, Integer.highestOneBit(paramAnonymousInt1));
      if (paramAnonymousInt2 < paramAnonymousInt1) {}
      for (paramAnonymousInt1 = i;; paramAnonymousInt1 = 0) {
        return paramAnonymousInt2 << paramAnonymousInt1;
      }
    }
  };
  private static final int MARK_POSITION = 5242880;
  public static final Downsampler NONE = new Downsampler()
  {
    public String getId()
    {
      return "NONE.com.bumptech.glide.load.data.bitmap";
    }
    
    protected int getSampleSize(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4)
    {
      return 0;
    }
  };
  private static final Queue<BitmapFactory.Options> OPTIONS_QUEUE;
  private static final String TAG = "Downsampler";
  private static final Set<ImageHeaderParser.ImageType> TYPES_THAT_USE_POOL = EnumSet.of(ImageHeaderParser.ImageType.JPEG, ImageHeaderParser.ImageType.PNG_A, ImageHeaderParser.ImageType.PNG);
  
  static
  {
    OPTIONS_QUEUE = Util.createQueue(0);
  }
  
  private static Bitmap decodeStream(MarkEnforcingInputStream paramMarkEnforcingInputStream, RecyclableBufferedInputStream paramRecyclableBufferedInputStream, BitmapFactory.Options paramOptions)
  {
    if (paramOptions.inJustDecodeBounds) {
      paramMarkEnforcingInputStream.mark(5242880);
    }
    for (;;)
    {
      paramRecyclableBufferedInputStream = BitmapFactory.decodeStream(paramMarkEnforcingInputStream, null, paramOptions);
      try
      {
        if (paramOptions.inJustDecodeBounds) {
          paramMarkEnforcingInputStream.reset();
        }
        return paramRecyclableBufferedInputStream;
        paramRecyclableBufferedInputStream.fixMarkLimit();
      }
      catch (IOException paramMarkEnforcingInputStream)
      {
        while (!Log.isLoggable("Downsampler", 6)) {}
        Log.e("Downsampler", "Exception loading inDecodeBounds=" + paramOptions.inJustDecodeBounds + " sample=" + paramOptions.inSampleSize, paramMarkEnforcingInputStream);
      }
    }
    return paramRecyclableBufferedInputStream;
  }
  
  private Bitmap downsampleWithSize(MarkEnforcingInputStream paramMarkEnforcingInputStream, RecyclableBufferedInputStream paramRecyclableBufferedInputStream, BitmapFactory.Options paramOptions, BitmapPool paramBitmapPool, int paramInt1, int paramInt2, int paramInt3, DecodeFormat paramDecodeFormat)
  {
    paramDecodeFormat = getConfig(paramMarkEnforcingInputStream, paramDecodeFormat);
    paramOptions.inSampleSize = paramInt3;
    paramOptions.inPreferredConfig = paramDecodeFormat;
    if (((paramOptions.inSampleSize == 1) || (19 <= Build.VERSION.SDK_INT)) && (shouldUsePool(paramMarkEnforcingInputStream))) {
      setInBitmap(paramOptions, paramBitmapPool.getDirty((int)Math.ceil(paramInt1 / paramInt3), (int)Math.ceil(paramInt2 / paramInt3), paramDecodeFormat));
    }
    return decodeStream(paramMarkEnforcingInputStream, paramRecyclableBufferedInputStream, paramOptions);
  }
  
  private static Bitmap.Config getConfig(InputStream paramInputStream, DecodeFormat paramDecodeFormat)
  {
    if ((paramDecodeFormat == DecodeFormat.ALWAYS_ARGB_8888) || (paramDecodeFormat == DecodeFormat.PREFER_ARGB_8888) || (Build.VERSION.SDK_INT == 16)) {
      return Bitmap.Config.ARGB_8888;
    }
    boolean bool2 = false;
    paramInputStream.mark(1024);
    for (;;)
    {
      try
      {
        bool1 = new ImageHeaderParser(paramInputStream).hasAlpha();
        bool2 = bool1;
      }
      catch (IOException localIOException)
      {
        if (!Log.isLoggable("Downsampler", 5)) {
          continue;
        }
        Log.w("Downsampler", "Cannot determine whether the image has alpha or not from header for format " + paramDecodeFormat, localIOException);
        try
        {
          paramInputStream.reset();
          bool1 = bool2;
        }
        catch (IOException paramInputStream)
        {
          bool1 = bool2;
        }
        if (!Log.isLoggable("Downsampler", 5)) {
          continue;
        }
        Log.w("Downsampler", "Cannot reset the input stream", paramInputStream);
        boolean bool1 = bool2;
        continue;
      }
      finally
      {
        try
        {
          paramInputStream.reset();
          throw paramDecodeFormat;
        }
        catch (IOException paramInputStream)
        {
          if (!Log.isLoggable("Downsampler", 5)) {
            continue;
          }
          Log.w("Downsampler", "Cannot reset the input stream", paramInputStream);
          continue;
        }
      }
      try
      {
        paramInputStream.reset();
        bool1 = bool2;
      }
      catch (IOException paramInputStream)
      {
        bool1 = bool2;
        if (!Log.isLoggable("Downsampler", 5)) {
          continue;
        }
        Log.w("Downsampler", "Cannot reset the input stream", paramInputStream);
        bool1 = bool2;
      }
    }
    if (bool1) {
      return Bitmap.Config.ARGB_8888;
    }
    return Bitmap.Config.RGB_565;
  }
  
  /* Error */
  @TargetApi(11)
  private static BitmapFactory.Options getDefaultOptions()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 60	com/bumptech/glide/load/resource/bitmap/Downsampler:OPTIONS_QUEUE	Ljava/util/Queue;
    //   6: astore_0
    //   7: aload_0
    //   8: monitorenter
    //   9: getstatic 60	com/bumptech/glide/load/resource/bitmap/Downsampler:OPTIONS_QUEUE	Ljava/util/Queue;
    //   12: invokeinterface 224 1 0
    //   17: checkcast 79	android/graphics/BitmapFactory$Options
    //   20: astore_1
    //   21: aload_0
    //   22: monitorexit
    //   23: aload_1
    //   24: astore_0
    //   25: aload_1
    //   26: ifnonnull +15 -> 41
    //   29: new 79	android/graphics/BitmapFactory$Options
    //   32: dup
    //   33: invokespecial 225	android/graphics/BitmapFactory$Options:<init>	()V
    //   36: astore_0
    //   37: aload_0
    //   38: invokestatic 229	com/bumptech/glide/load/resource/bitmap/Downsampler:resetOptions	(Landroid/graphics/BitmapFactory$Options;)V
    //   41: ldc 2
    //   43: monitorexit
    //   44: aload_0
    //   45: areturn
    //   46: astore_1
    //   47: aload_0
    //   48: monitorexit
    //   49: aload_1
    //   50: athrow
    //   51: astore_0
    //   52: ldc 2
    //   54: monitorexit
    //   55: aload_0
    //   56: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   51	5	0	localObject2	Object
    //   20	6	1	localOptions	BitmapFactory.Options
    //   46	4	1	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   9	23	46	finally
    //   47	49	46	finally
    //   3	9	51	finally
    //   29	41	51	finally
    //   49	51	51	finally
  }
  
  private int getRoundedSampleSize(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    if (paramInt5 == Integer.MIN_VALUE)
    {
      paramInt5 = paramInt3;
      if (paramInt4 != Integer.MIN_VALUE) {
        break label59;
      }
      paramInt4 = paramInt2;
      label20:
      if ((paramInt1 != 90) && (paramInt1 != 270)) {
        break label62;
      }
      paramInt1 = getSampleSize(paramInt3, paramInt2, paramInt4, paramInt5);
      label44:
      if (paramInt1 != 0) {
        break label76;
      }
    }
    label59:
    label62:
    label76:
    for (paramInt1 = 0;; paramInt1 = Integer.highestOneBit(paramInt1))
    {
      return Math.max(1, paramInt1);
      break;
      break label20;
      paramInt1 = getSampleSize(paramInt2, paramInt3, paramInt4, paramInt5);
      break label44;
    }
  }
  
  private static void releaseOptions(BitmapFactory.Options paramOptions)
  {
    resetOptions(paramOptions);
    synchronized (OPTIONS_QUEUE)
    {
      OPTIONS_QUEUE.offer(paramOptions);
      return;
    }
  }
  
  @TargetApi(11)
  private static void resetOptions(BitmapFactory.Options paramOptions)
  {
    paramOptions.inTempStorage = null;
    paramOptions.inDither = false;
    paramOptions.inScaled = false;
    paramOptions.inSampleSize = 1;
    paramOptions.inPreferredConfig = null;
    paramOptions.inJustDecodeBounds = false;
    paramOptions.outWidth = 0;
    paramOptions.outHeight = 0;
    paramOptions.outMimeType = null;
    if (11 <= Build.VERSION.SDK_INT)
    {
      paramOptions.inBitmap = null;
      paramOptions.inMutable = true;
    }
  }
  
  @TargetApi(11)
  private static void setInBitmap(BitmapFactory.Options paramOptions, Bitmap paramBitmap)
  {
    if (11 <= Build.VERSION.SDK_INT) {
      paramOptions.inBitmap = paramBitmap;
    }
  }
  
  private static boolean shouldUsePool(InputStream paramInputStream)
  {
    boolean bool1;
    if (19 <= Build.VERSION.SDK_INT) {
      bool1 = true;
    }
    for (;;)
    {
      return bool1;
      paramInputStream.mark(1024);
      try
      {
        ImageHeaderParser.ImageType localImageType = new ImageHeaderParser(paramInputStream).getType();
        boolean bool2 = TYPES_THAT_USE_POOL.contains(localImageType);
        try
        {
          paramInputStream.reset();
          return bool2;
        }
        catch (IOException paramInputStream)
        {
          bool1 = bool2;
        }
        if (Log.isLoggable("Downsampler", 5))
        {
          Log.w("Downsampler", "Cannot reset the input stream", paramInputStream);
          return bool2;
        }
      }
      catch (IOException localIOException)
      {
        localIOException = localIOException;
        if (Log.isLoggable("Downsampler", 5)) {
          Log.w("Downsampler", "Cannot determine the image type from header", localIOException);
        }
        try
        {
          paramInputStream.reset();
          return false;
        }
        catch (IOException paramInputStream)
        {
          for (;;)
          {
            if (Log.isLoggable("Downsampler", 5)) {
              Log.w("Downsampler", "Cannot reset the input stream", paramInputStream);
            }
          }
        }
      }
      finally {}
    }
    try
    {
      paramInputStream.reset();
      throw ((Throwable)localObject);
    }
    catch (IOException paramInputStream)
    {
      for (;;)
      {
        if (Log.isLoggable("Downsampler", 5)) {
          Log.w("Downsampler", "Cannot reset the input stream", paramInputStream);
        }
      }
    }
  }
  
  public Bitmap decode(InputStream paramInputStream, BitmapPool paramBitmapPool, int paramInt1, int paramInt2, DecodeFormat paramDecodeFormat)
  {
    ByteArrayPool localByteArrayPool = ByteArrayPool.get();
    byte[] arrayOfByte1 = localByteArrayPool.getBytes();
    byte[] arrayOfByte2 = localByteArrayPool.getBytes();
    BitmapFactory.Options localOptions = getDefaultOptions();
    paramInputStream = new RecyclableBufferedInputStream(paramInputStream, arrayOfByte2);
    ExceptionCatchingInputStream localExceptionCatchingInputStream = ExceptionCatchingInputStream.obtain(paramInputStream);
    Object localObject = new MarkEnforcingInputStream(localExceptionCatchingInputStream);
    int i;
    try
    {
      localExceptionCatchingInputStream.mark(5242880);
      j = 0;
    }
    finally
    {
      int[] arrayOfInt;
      int k;
      localByteArrayPool.releaseBytes(arrayOfByte1);
      localByteArrayPool.releaseBytes(arrayOfByte2);
      localExceptionCatchingInputStream.release();
      releaseOptions(localOptions);
    }
    for (;;)
    {
      try
      {
        i = new ImageHeaderParser(localExceptionCatchingInputStream).getOrientation();
        j = i;
      }
      catch (IOException localIOException2)
      {
        if (!Log.isLoggable("Downsampler", 5)) {
          continue;
        }
        Log.w("Downsampler", "Cannot determine the image orientation from header", localIOException2);
        try
        {
          localExceptionCatchingInputStream.reset();
          i = j;
        }
        catch (IOException localIOException3)
        {
          i = j;
        }
        if (!Log.isLoggable("Downsampler", 5)) {
          break;
        }
        Log.w("Downsampler", "Cannot reset the input stream", localIOException3);
        i = j;
        break;
      }
      finally
      {
        try
        {
          localExceptionCatchingInputStream.reset();
          throw paramInputStream;
        }
        catch (IOException paramBitmapPool)
        {
          if (!Log.isLoggable("Downsampler", 5)) {
            continue;
          }
          Log.w("Downsampler", "Cannot reset the input stream", paramBitmapPool);
          continue;
        }
        paramInputStream = null;
        if (localObject == null) {
          continue;
        }
        paramDecodeFormat = TransformationUtils.rotateImageExif((Bitmap)localObject, paramBitmapPool, i);
        paramInputStream = paramDecodeFormat;
        if (localObject.equals(paramDecodeFormat)) {
          continue;
        }
        paramInputStream = paramDecodeFormat;
        if (paramBitmapPool.put((Bitmap)localObject)) {
          continue;
        }
        ((Bitmap)localObject).recycle();
        paramInputStream = paramDecodeFormat;
        localByteArrayPool.releaseBytes(arrayOfByte1);
        localByteArrayPool.releaseBytes(arrayOfByte2);
        localExceptionCatchingInputStream.release();
        releaseOptions(localOptions);
      }
      try
      {
        localExceptionCatchingInputStream.reset();
        i = j;
      }
      catch (IOException localIOException1)
      {
        i = j;
        if (!Log.isLoggable("Downsampler", 5)) {
          break;
        }
        Log.w("Downsampler", "Cannot reset the input stream", localIOException1);
        i = j;
        break;
      }
    }
    localOptions.inTempStorage = arrayOfByte1;
    arrayOfInt = getDimensions((MarkEnforcingInputStream)localObject, paramInputStream, localOptions);
    int j = arrayOfInt[0];
    k = arrayOfInt[1];
    localObject = downsampleWithSize((MarkEnforcingInputStream)localObject, paramInputStream, localOptions, paramBitmapPool, j, k, getRoundedSampleSize(TransformationUtils.getExifOrientationDegrees(i), j, k, paramInt1, paramInt2), paramDecodeFormat);
    paramInputStream = localExceptionCatchingInputStream.getException();
    if (paramInputStream != null) {
      throw new RuntimeException(paramInputStream);
    }
    return paramInputStream;
  }
  
  public int[] getDimensions(MarkEnforcingInputStream paramMarkEnforcingInputStream, RecyclableBufferedInputStream paramRecyclableBufferedInputStream, BitmapFactory.Options paramOptions)
  {
    paramOptions.inJustDecodeBounds = true;
    decodeStream(paramMarkEnforcingInputStream, paramRecyclableBufferedInputStream, paramOptions);
    paramOptions.inJustDecodeBounds = false;
    return new int[] { paramOptions.outWidth, paramOptions.outHeight };
  }
  
  protected abstract int getSampleSize(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\bitmap\Downsampler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */