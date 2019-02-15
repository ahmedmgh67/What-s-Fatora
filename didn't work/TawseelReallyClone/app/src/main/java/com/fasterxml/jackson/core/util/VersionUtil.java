package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import java.io.Closeable;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import java.util.regex.Pattern;

public class VersionUtil
{
  private static final Pattern V_SEP = Pattern.compile("[-_./;:]");
  private final Version _v;
  
  protected VersionUtil()
  {
    Object localObject1 = null;
    try
    {
      localObject2 = versionFor(getClass());
      localObject1 = localObject2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject2;
        System.err.println("ERROR: Failed to load Version information from " + getClass());
      }
    }
    localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = Version.unknownVersion();
    }
    this._v = ((Version)localObject2);
  }
  
  private static final void _close(Closeable paramCloseable)
  {
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException paramCloseable) {}
  }
  
  @Deprecated
  public static Version mavenVersionFor(ClassLoader paramClassLoader, String paramString1, String paramString2)
  {
    paramClassLoader = paramClassLoader.getResourceAsStream("META-INF/maven/" + paramString1.replaceAll("\\.", "/") + "/" + paramString2 + "/pom.properties");
    if (paramClassLoader != null) {}
    try
    {
      paramString1 = new Properties();
      paramString1.load(paramClassLoader);
      paramString2 = paramString1.getProperty("version");
      String str = paramString1.getProperty("artifactId");
      paramString1 = parseVersion(paramString2, paramString1.getProperty("groupId"), str);
      _close(paramClassLoader);
      return paramString1;
    }
    catch (IOException paramString1)
    {
      paramString1 = paramString1;
      _close(paramClassLoader);
      return Version.unknownVersion();
    }
    finally
    {
      paramString1 = finally;
      _close(paramClassLoader);
      throw paramString1;
    }
  }
  
  public static Version packageVersionFor(Class<?> paramClass)
  {
    localObject = null;
    for (;;)
    {
      try
      {
        localClass = Class.forName(paramClass.getPackage().getName() + ".PackageVersion", true, paramClass.getClassLoader());
      }
      catch (Exception paramClass)
      {
        Class localClass;
        paramClass = (Class<?>)localObject;
        continue;
      }
      try
      {
        paramClass = ((Versioned)localClass.newInstance()).version();
        localObject = paramClass;
        if (paramClass == null) {
          localObject = Version.unknownVersion();
        }
        return (Version)localObject;
      }
      catch (Exception paramClass)
      {
        throw new IllegalArgumentException("Failed to get Versioned out of " + localClass);
      }
    }
  }
  
  public static Version parseVersion(String paramString1, String paramString2, String paramString3)
  {
    int j = 0;
    if (paramString1 != null)
    {
      paramString1 = paramString1.trim();
      if (paramString1.length() > 0)
      {
        paramString1 = V_SEP.split(paramString1);
        int k = parseVersionPart(paramString1[0]);
        int i;
        if (paramString1.length > 1)
        {
          i = parseVersionPart(paramString1[1]);
          if (paramString1.length > 2) {
            j = parseVersionPart(paramString1[2]);
          }
          if (paramString1.length <= 3) {
            break label93;
          }
        }
        label93:
        for (paramString1 = paramString1[3];; paramString1 = null)
        {
          return new Version(k, i, j, paramString1, paramString2, paramString3);
          i = 0;
          break;
        }
      }
    }
    return Version.unknownVersion();
  }
  
  protected static int parseVersionPart(String paramString)
  {
    int j = 0;
    int i = 0;
    int k = paramString.length();
    for (;;)
    {
      int m;
      if (i < k)
      {
        m = paramString.charAt(i);
        if ((m <= 57) && (m >= 48)) {}
      }
      else
      {
        return j;
      }
      j = j * 10 + (m - 48);
      i += 1;
    }
  }
  
  public static final void throwInternal()
  {
    throw new RuntimeException("Internal error: this code path should never get executed");
  }
  
  public static Version versionFor(Class<?> paramClass)
  {
    Version localVersion = packageVersionFor(paramClass);
    paramClass = localVersion;
    if (localVersion == null) {
      paramClass = Version.unknownVersion();
    }
    return paramClass;
  }
  
  public Version version()
  {
    return this._v;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\core\util\VersionUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */