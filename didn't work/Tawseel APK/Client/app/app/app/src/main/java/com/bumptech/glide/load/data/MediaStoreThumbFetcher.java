package com.bumptech.glide.load.data;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Images.Thumbnails;
import android.provider.MediaStore.Video.Thumbnails;
import android.text.TextUtils;
import android.util.Log;
import com.bumptech.glide.Priority;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MediaStoreThumbFetcher
  implements DataFetcher<InputStream>
{
  private static final ThumbnailStreamOpenerFactory DEFAULT_FACTORY = new ThumbnailStreamOpenerFactory();
  private static final int MINI_HEIGHT = 384;
  private static final int MINI_WIDTH = 512;
  private static final String TAG = "MediaStoreThumbFetcher";
  private final Context context;
  private final DataFetcher<InputStream> defaultFetcher;
  private final ThumbnailStreamOpenerFactory factory;
  private final int height;
  private InputStream inputStream;
  private final Uri mediaStoreUri;
  private final int width;
  
  public MediaStoreThumbFetcher(Context paramContext, Uri paramUri, DataFetcher<InputStream> paramDataFetcher, int paramInt1, int paramInt2)
  {
    this(paramContext, paramUri, paramDataFetcher, paramInt1, paramInt2, DEFAULT_FACTORY);
  }
  
  MediaStoreThumbFetcher(Context paramContext, Uri paramUri, DataFetcher<InputStream> paramDataFetcher, int paramInt1, int paramInt2, ThumbnailStreamOpenerFactory paramThumbnailStreamOpenerFactory)
  {
    this.context = paramContext;
    this.mediaStoreUri = paramUri;
    this.defaultFetcher = paramDataFetcher;
    this.width = paramInt1;
    this.height = paramInt2;
    this.factory = paramThumbnailStreamOpenerFactory;
  }
  
  private static boolean isMediaStoreUri(Uri paramUri)
  {
    return (paramUri != null) && ("content".equals(paramUri.getScheme())) && ("media".equals(paramUri.getAuthority()));
  }
  
  private static boolean isMediaStoreVideo(Uri paramUri)
  {
    return (isMediaStoreUri(paramUri)) && (paramUri.getPathSegments().contains("video"));
  }
  
  private InputStream openThumbInputStream(ThumbnailStreamOpener paramThumbnailStreamOpener)
  {
    try
    {
      InputStream localInputStream1 = paramThumbnailStreamOpener.open(this.context, this.mediaStoreUri);
      int i = -1;
      if (localInputStream1 != null) {
        i = paramThumbnailStreamOpener.getOrientation(this.context, this.mediaStoreUri);
      }
      if (i != -1) {
        return new ExifOrientationStream(localInputStream1, i);
      }
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      InputStream localInputStream2;
      for (;;)
      {
        if (Log.isLoggable("MediaStoreThumbFetcher", 3)) {
          Log.d("MediaStoreThumbFetcher", "Failed to find thumbnail file", localFileNotFoundException);
        }
        localInputStream2 = null;
      }
      return localInputStream2;
    }
  }
  
  public void cancel() {}
  
  public void cleanup()
  {
    if (this.inputStream != null) {}
    try
    {
      this.inputStream.close();
      this.defaultFetcher.cleanup();
      return;
    }
    catch (IOException localIOException)
    {
      for (;;) {}
    }
  }
  
  public String getId()
  {
    return this.mediaStoreUri.toString();
  }
  
  public InputStream loadData(Priority paramPriority)
    throws Exception
  {
    ThumbnailStreamOpener localThumbnailStreamOpener = this.factory.build(this.mediaStoreUri, this.width, this.height);
    if (localThumbnailStreamOpener != null) {
      this.inputStream = openThumbInputStream(localThumbnailStreamOpener);
    }
    if (this.inputStream == null) {
      this.inputStream = ((InputStream)this.defaultFetcher.loadData(paramPriority));
    }
    return this.inputStream;
  }
  
  static class FileService
  {
    public boolean exists(File paramFile)
    {
      return paramFile.exists();
    }
    
    public File get(String paramString)
    {
      return new File(paramString);
    }
    
    public long length(File paramFile)
    {
      return paramFile.length();
    }
  }
  
  static class ImageThumbnailQuery
    implements MediaStoreThumbFetcher.ThumbnailQuery
  {
    private static final String[] PATH_PROJECTION = { "_data" };
    private static final String PATH_SELECTION = "kind = 1 AND image_id = ?";
    
    public Cursor queryPath(Context paramContext, Uri paramUri)
    {
      paramUri = paramUri.getLastPathSegment();
      return paramContext.getContentResolver().query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, PATH_PROJECTION, "kind = 1 AND image_id = ?", new String[] { paramUri }, null);
    }
  }
  
  static abstract interface ThumbnailQuery
  {
    public abstract Cursor queryPath(Context paramContext, Uri paramUri);
  }
  
  static class ThumbnailStreamOpener
  {
    private static final MediaStoreThumbFetcher.FileService DEFAULT_SERVICE = new MediaStoreThumbFetcher.FileService();
    private MediaStoreThumbFetcher.ThumbnailQuery query;
    private final MediaStoreThumbFetcher.FileService service;
    
    public ThumbnailStreamOpener(MediaStoreThumbFetcher.FileService paramFileService, MediaStoreThumbFetcher.ThumbnailQuery paramThumbnailQuery)
    {
      this.service = paramFileService;
      this.query = paramThumbnailQuery;
    }
    
    public ThumbnailStreamOpener(MediaStoreThumbFetcher.ThumbnailQuery paramThumbnailQuery)
    {
      this(DEFAULT_SERVICE, paramThumbnailQuery);
    }
    
    private Uri parseThumbUri(Cursor paramCursor)
    {
      Object localObject1 = null;
      Object localObject2 = paramCursor.getString(0);
      paramCursor = (Cursor)localObject1;
      if (!TextUtils.isEmpty((CharSequence)localObject2))
      {
        localObject2 = this.service.get((String)localObject2);
        paramCursor = (Cursor)localObject1;
        if (this.service.exists((File)localObject2))
        {
          paramCursor = (Cursor)localObject1;
          if (this.service.length((File)localObject2) > 0L) {
            paramCursor = Uri.fromFile((File)localObject2);
          }
        }
      }
      return paramCursor;
    }
    
    /* Error */
    public int getOrientation(Context paramContext, Uri paramUri)
    {
      // Byte code:
      //   0: iconst_m1
      //   1: istore 4
      //   3: aconst_null
      //   4: astore 6
      //   6: aconst_null
      //   7: astore 5
      //   9: aload_1
      //   10: invokevirtual 73	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
      //   13: aload_2
      //   14: invokevirtual 79	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
      //   17: astore_1
      //   18: aload_1
      //   19: astore 5
      //   21: aload_1
      //   22: astore 6
      //   24: new 81	com/bumptech/glide/load/resource/bitmap/ImageHeaderParser
      //   27: dup
      //   28: aload_1
      //   29: invokespecial 84	com/bumptech/glide/load/resource/bitmap/ImageHeaderParser:<init>	(Ljava/io/InputStream;)V
      //   32: invokevirtual 87	com/bumptech/glide/load/resource/bitmap/ImageHeaderParser:getOrientation	()I
      //   35: istore_3
      //   36: iload_3
      //   37: istore 4
      //   39: aload_1
      //   40: ifnull +10 -> 50
      //   43: aload_1
      //   44: invokevirtual 92	java/io/InputStream:close	()V
      //   47: iload_3
      //   48: istore 4
      //   50: iload 4
      //   52: ireturn
      //   53: astore_1
      //   54: aload 5
      //   56: astore 6
      //   58: ldc 94
      //   60: iconst_3
      //   61: invokestatic 100	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
      //   64: ifeq +33 -> 97
      //   67: aload 5
      //   69: astore 6
      //   71: ldc 94
      //   73: new 102	java/lang/StringBuilder
      //   76: dup
      //   77: invokespecial 103	java/lang/StringBuilder:<init>	()V
      //   80: ldc 105
      //   82: invokevirtual 109	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   85: aload_2
      //   86: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   89: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   92: aload_1
      //   93: invokestatic 120	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   96: pop
      //   97: aload 5
      //   99: ifnull -49 -> 50
      //   102: aload 5
      //   104: invokevirtual 92	java/io/InputStream:close	()V
      //   107: iconst_m1
      //   108: ireturn
      //   109: astore_1
      //   110: iconst_m1
      //   111: ireturn
      //   112: astore_1
      //   113: aload 6
      //   115: ifnull +8 -> 123
      //   118: aload 6
      //   120: invokevirtual 92	java/io/InputStream:close	()V
      //   123: aload_1
      //   124: athrow
      //   125: astore_1
      //   126: iload_3
      //   127: ireturn
      //   128: astore_2
      //   129: goto -6 -> 123
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	132	0	this	ThumbnailStreamOpener
      //   0	132	1	paramContext	Context
      //   0	132	2	paramUri	Uri
      //   35	92	3	i	int
      //   1	50	4	j	int
      //   7	96	5	localContext	Context
      //   4	115	6	localObject	Object
      // Exception table:
      //   from	to	target	type
      //   9	18	53	java/io/IOException
      //   24	36	53	java/io/IOException
      //   102	107	109	java/io/IOException
      //   9	18	112	finally
      //   24	36	112	finally
      //   58	67	112	finally
      //   71	97	112	finally
      //   43	47	125	java/io/IOException
      //   118	123	128	java/io/IOException
    }
    
    public InputStream open(Context paramContext, Uri paramUri)
      throws FileNotFoundException
    {
      Object localObject = null;
      InputStream localInputStream = null;
      Cursor localCursor = this.query.queryPath(paramContext, paramUri);
      paramUri = (Uri)localObject;
      if (localCursor != null) {
        paramUri = (Uri)localObject;
      }
      try
      {
        if (localCursor.moveToFirst()) {
          paramUri = parseThumbUri(localCursor);
        }
        if (localCursor != null) {
          localCursor.close();
        }
        if (paramUri != null) {
          localInputStream = paramContext.getContentResolver().openInputStream(paramUri);
        }
        return localInputStream;
      }
      finally
      {
        if (localCursor != null) {
          localCursor.close();
        }
      }
    }
  }
  
  static class ThumbnailStreamOpenerFactory
  {
    public MediaStoreThumbFetcher.ThumbnailStreamOpener build(Uri paramUri, int paramInt1, int paramInt2)
    {
      if ((!MediaStoreThumbFetcher.isMediaStoreUri(paramUri)) || (paramInt1 > 512) || (paramInt2 > 384)) {
        return null;
      }
      if (MediaStoreThumbFetcher.isMediaStoreVideo(paramUri)) {
        return new MediaStoreThumbFetcher.ThumbnailStreamOpener(new MediaStoreThumbFetcher.VideoThumbnailQuery());
      }
      return new MediaStoreThumbFetcher.ThumbnailStreamOpener(new MediaStoreThumbFetcher.ImageThumbnailQuery());
    }
  }
  
  static class VideoThumbnailQuery
    implements MediaStoreThumbFetcher.ThumbnailQuery
  {
    private static final String[] PATH_PROJECTION = { "_data" };
    private static final String PATH_SELECTION = "kind = 1 AND video_id = ?";
    
    public Cursor queryPath(Context paramContext, Uri paramUri)
    {
      paramUri = paramUri.getLastPathSegment();
      return paramContext.getContentResolver().query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, PATH_PROJECTION, "kind = 1 AND video_id = ?", new String[] { paramUri }, null);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\data\MediaStoreThumbFetcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */