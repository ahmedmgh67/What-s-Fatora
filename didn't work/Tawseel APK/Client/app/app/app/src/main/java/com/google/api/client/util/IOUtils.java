package com.google.api.client.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class IOUtils
{
  public static long computeLength(StreamingContent paramStreamingContent)
    throws IOException
  {
    ByteCountingOutputStream localByteCountingOutputStream = new ByteCountingOutputStream();
    try
    {
      paramStreamingContent.writeTo(localByteCountingOutputStream);
      return localByteCountingOutputStream.count;
    }
    finally
    {
      localByteCountingOutputStream.close();
    }
  }
  
  public static void copy(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    copy(paramInputStream, paramOutputStream, true);
  }
  
  public static void copy(InputStream paramInputStream, OutputStream paramOutputStream, boolean paramBoolean)
    throws IOException
  {
    try
    {
      ByteStreams.copy(paramInputStream, paramOutputStream);
      return;
    }
    finally
    {
      if (paramBoolean) {
        paramInputStream.close();
      }
    }
  }
  
  public static <S extends Serializable> S deserialize(InputStream paramInputStream)
    throws IOException
  {
    try
    {
      Serializable localSerializable = (Serializable)new ObjectInputStream(paramInputStream).readObject();
      return localSerializable;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      IOException localIOException = new IOException("Failed to deserialize object");
      localIOException.initCause(localClassNotFoundException);
      throw localIOException;
    }
    finally
    {
      paramInputStream.close();
    }
  }
  
  public static <S extends Serializable> S deserialize(byte[] paramArrayOfByte)
    throws IOException
  {
    if (paramArrayOfByte == null) {
      return null;
    }
    return deserialize(new ByteArrayInputStream(paramArrayOfByte));
  }
  
  public static boolean isSymbolicLink(File paramFile)
    throws IOException
  {
    bool1 = false;
    try
    {
      Class localClass1 = Class.forName("java.nio.file.Files");
      Class localClass2 = Class.forName("java.nio.file.Path");
      Object localObject = File.class.getMethod("toPath", new Class[0]).invoke(paramFile, new Object[0]);
      boolean bool2 = ((Boolean)localClass1.getMethod("isSymbolicLink", new Class[] { localClass2 }).invoke(null, new Object[] { localObject })).booleanValue();
      bool1 = bool2;
    }
    catch (InvocationTargetException paramFile)
    {
      paramFile = paramFile.getCause();
      Throwables.propagateIfPossible(paramFile, IOException.class);
      throw new RuntimeException(paramFile);
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      while (File.separatorChar == '\\') {}
      File localFile = paramFile;
      if (paramFile.getParent() == null) {
        break label133;
      }
      localFile = new File(paramFile.getParentFile().getCanonicalFile(), paramFile.getName());
      if (localFile.getCanonicalFile().equals(localFile.getAbsoluteFile())) {
        break label151;
      }
      for (bool1 = true;; bool1 = false) {
        return bool1;
      }
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      for (;;) {}
    }
    catch (SecurityException localSecurityException)
    {
      for (;;) {}
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;) {}
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;) {}
    }
    return bool1;
  }
  
  public static void serialize(Object paramObject, OutputStream paramOutputStream)
    throws IOException
  {
    try
    {
      new ObjectOutputStream(paramOutputStream).writeObject(paramObject);
      return;
    }
    finally
    {
      paramOutputStream.close();
    }
  }
  
  public static byte[] serialize(Object paramObject)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    serialize(paramObject, localByteArrayOutputStream);
    return localByteArrayOutputStream.toByteArray();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\IOUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */