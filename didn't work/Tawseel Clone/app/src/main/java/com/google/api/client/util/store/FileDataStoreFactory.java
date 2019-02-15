package com.google.api.client.util.store;

import com.google.api.client.util.IOUtils;
import com.google.api.client.util.Maps;
import com.google.api.client.util.Throwables;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.logging.Logger;

public class FileDataStoreFactory
  extends AbstractDataStoreFactory
{
  private static final Logger LOGGER = Logger.getLogger(FileDataStoreFactory.class.getName());
  private final File dataDirectory;
  
  public FileDataStoreFactory(File paramFile)
    throws IOException
  {
    paramFile = paramFile.getCanonicalFile();
    this.dataDirectory = paramFile;
    if (IOUtils.isSymbolicLink(paramFile)) {
      throw new IOException("unable to use a symbolic link: " + paramFile);
    }
    if ((!paramFile.exists()) && (!paramFile.mkdirs())) {
      throw new IOException("unable to create directory: " + paramFile);
    }
    setPermissionsToOwnerOnly(paramFile);
  }
  
  static void setPermissionsToOwnerOnly(File paramFile)
    throws IOException
  {
    try
    {
      Method localMethod1 = File.class.getMethod("setReadable", new Class[] { Boolean.TYPE, Boolean.TYPE });
      Method localMethod2 = File.class.getMethod("setWritable", new Class[] { Boolean.TYPE, Boolean.TYPE });
      Method localMethod3 = File.class.getMethod("setExecutable", new Class[] { Boolean.TYPE, Boolean.TYPE });
      if (((Boolean)localMethod1.invoke(paramFile, new Object[] { Boolean.valueOf(false), Boolean.valueOf(false) })).booleanValue()) {
        if (((Boolean)localMethod2.invoke(paramFile, new Object[] { Boolean.valueOf(false), Boolean.valueOf(false) })).booleanValue()) {
          if (((Boolean)localMethod3.invoke(paramFile, new Object[] { Boolean.valueOf(false), Boolean.valueOf(false) })).booleanValue()) {
            break label193;
          }
        }
      }
      LOGGER.warning("unable to change permissions for everybody: " + paramFile);
      label193:
      if (((Boolean)localMethod1.invoke(paramFile, new Object[] { Boolean.valueOf(true), Boolean.valueOf(true) })).booleanValue()) {
        if (((Boolean)localMethod2.invoke(paramFile, new Object[] { Boolean.valueOf(true), Boolean.valueOf(true) })).booleanValue()) {
          if (((Boolean)localMethod3.invoke(paramFile, new Object[] { Boolean.valueOf(true), Boolean.valueOf(true) })).booleanValue()) {
            break label314;
          }
        }
      }
      LOGGER.warning("unable to change permissions for owner: " + paramFile);
      label314:
      return;
    }
    catch (InvocationTargetException paramFile)
    {
      paramFile = paramFile.getCause();
      Throwables.propagateIfPossible(paramFile, IOException.class);
      throw new RuntimeException(paramFile);
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      LOGGER.warning("Unable to set permissions for " + paramFile + ", likely because you are running a version of Java prior to 1.6");
      return;
    }
    catch (IllegalArgumentException paramFile) {}catch (IllegalAccessException paramFile) {}catch (SecurityException paramFile) {}
  }
  
  protected <V extends Serializable> DataStore<V> createDataStore(String paramString)
    throws IOException
  {
    return new FileDataStore(this, this.dataDirectory, paramString);
  }
  
  public final File getDataDirectory()
  {
    return this.dataDirectory;
  }
  
  static class FileDataStore<V extends Serializable>
    extends AbstractMemoryDataStore<V>
  {
    private final File dataFile;
    
    FileDataStore(FileDataStoreFactory paramFileDataStoreFactory, File paramFile, String paramString)
      throws IOException
    {
      super(paramString);
      this.dataFile = new File(paramFile, paramString);
      if (IOUtils.isSymbolicLink(this.dataFile)) {
        throw new IOException("unable to use a symbolic link: " + this.dataFile);
      }
      if (this.dataFile.createNewFile())
      {
        this.keyValueMap = Maps.newHashMap();
        save();
        return;
      }
      this.keyValueMap = ((HashMap)IOUtils.deserialize(new FileInputStream(this.dataFile)));
    }
    
    public FileDataStoreFactory getDataStoreFactory()
    {
      return (FileDataStoreFactory)super.getDataStoreFactory();
    }
    
    void save()
      throws IOException
    {
      IOUtils.serialize(this.keyValueMap, new FileOutputStream(this.dataFile));
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\store\FileDataStoreFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */