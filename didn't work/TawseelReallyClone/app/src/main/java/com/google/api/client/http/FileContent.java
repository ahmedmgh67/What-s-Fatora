package com.google.api.client.http;

import com.google.api.client.util.Preconditions;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public final class FileContent
  extends AbstractInputStreamContent
{
  private final File file;
  
  public FileContent(String paramString, File paramFile)
  {
    super(paramString);
    this.file = ((File)Preconditions.checkNotNull(paramFile));
  }
  
  public File getFile()
  {
    return this.file;
  }
  
  public InputStream getInputStream()
    throws FileNotFoundException
  {
    return new FileInputStream(this.file);
  }
  
  public long getLength()
  {
    return this.file.length();
  }
  
  public boolean retrySupported()
  {
    return true;
  }
  
  public FileContent setCloseInputStream(boolean paramBoolean)
  {
    return (FileContent)super.setCloseInputStream(paramBoolean);
  }
  
  public FileContent setType(String paramString)
  {
    return (FileContent)super.setType(paramString);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\FileContent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */