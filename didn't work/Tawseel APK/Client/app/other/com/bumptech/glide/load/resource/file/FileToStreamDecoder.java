package com.bumptech.glide.load.resource.file;

import com.bumptech.glide.load.ResourceDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileToStreamDecoder<T>
  implements ResourceDecoder<File, T>
{
  private static final FileOpener DEFAULT_FILE_OPENER = new FileOpener();
  private final FileOpener fileOpener;
  private ResourceDecoder<InputStream, T> streamDecoder;
  
  public FileToStreamDecoder(ResourceDecoder<InputStream, T> paramResourceDecoder)
  {
    this(paramResourceDecoder, DEFAULT_FILE_OPENER);
  }
  
  FileToStreamDecoder(ResourceDecoder<InputStream, T> paramResourceDecoder, FileOpener paramFileOpener)
  {
    this.streamDecoder = paramResourceDecoder;
    this.fileOpener = paramFileOpener;
  }
  
  /* Error */
  public com.bumptech.glide.load.engine.Resource<T> decode(File paramFile, int paramInt1, int paramInt2)
    throws java.io.IOException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: getfield 35	com/bumptech/glide/load/resource/file/FileToStreamDecoder:fileOpener	Lcom/bumptech/glide/load/resource/file/FileToStreamDecoder$FileOpener;
    //   7: aload_1
    //   8: invokevirtual 44	com/bumptech/glide/load/resource/file/FileToStreamDecoder$FileOpener:open	(Ljava/io/File;)Ljava/io/InputStream;
    //   11: astore_1
    //   12: aload_1
    //   13: astore 4
    //   15: aload_0
    //   16: getfield 33	com/bumptech/glide/load/resource/file/FileToStreamDecoder:streamDecoder	Lcom/bumptech/glide/load/ResourceDecoder;
    //   19: aload_1
    //   20: iload_2
    //   21: iload_3
    //   22: invokeinterface 47 4 0
    //   27: astore 5
    //   29: aload_1
    //   30: ifnull +7 -> 37
    //   33: aload_1
    //   34: invokevirtual 52	java/io/InputStream:close	()V
    //   37: aload 5
    //   39: areturn
    //   40: astore_1
    //   41: aload 4
    //   43: ifnull +8 -> 51
    //   46: aload 4
    //   48: invokevirtual 52	java/io/InputStream:close	()V
    //   51: aload_1
    //   52: athrow
    //   53: astore_1
    //   54: aload 5
    //   56: areturn
    //   57: astore 4
    //   59: goto -8 -> 51
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	62	0	this	FileToStreamDecoder
    //   0	62	1	paramFile	File
    //   0	62	2	paramInt1	int
    //   0	62	3	paramInt2	int
    //   1	46	4	localFile	File
    //   57	1	4	localIOException	java.io.IOException
    //   27	28	5	localResource	com.bumptech.glide.load.engine.Resource
    // Exception table:
    //   from	to	target	type
    //   3	12	40	finally
    //   15	29	40	finally
    //   33	37	53	java/io/IOException
    //   46	51	57	java/io/IOException
  }
  
  public String getId()
  {
    return "";
  }
  
  static class FileOpener
  {
    public InputStream open(File paramFile)
      throws FileNotFoundException
    {
      return new FileInputStream(paramFile);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\resource\file\FileToStreamDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */