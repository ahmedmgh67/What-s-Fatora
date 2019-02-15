package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.io.PrintStream;
import java.io.Serializable;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class OptionalHandlerFactory
  implements Serializable
{
  private static final Class<?> CLASS_DOM_DOCUMENT;
  private static final Class<?> CLASS_DOM_NODE = Node.class;
  private static final Class<?> CLASS_JAVA7_PATH;
  private static final String DESERIALIZERS_FOR_JAVAX_XML = "com.fasterxml.jackson.databind.ext.CoreXMLDeserializers";
  private static final String DESERIALIZER_FOR_DOM_DOCUMENT = "com.fasterxml.jackson.databind.ext.DOMDeserializer$DocumentDeserializer";
  private static final String DESERIALIZER_FOR_DOM_NODE = "com.fasterxml.jackson.databind.ext.DOMDeserializer$NodeDeserializer";
  private static final String DESERIALIZER_FOR_PATH = "com.fasterxml.jackson.databind.ext.PathDeserializer";
  private static final String PACKAGE_PREFIX_JAVAX_XML = "javax.xml.";
  private static final String SERIALIZERS_FOR_JAVAX_XML = "com.fasterxml.jackson.databind.ext.CoreXMLSerializers";
  private static final String SERIALIZER_FOR_DOM_NODE = "com.fasterxml.jackson.databind.ext.DOMSerializer";
  public static final OptionalHandlerFactory instance = new OptionalHandlerFactory();
  private static final long serialVersionUID = 1L;
  
  static
  {
    CLASS_DOM_DOCUMENT = Document.class;
    Object localObject = null;
    try
    {
      Class localClass = Class.forName("java.nio.file.Path");
      localObject = localClass;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        System.err.println("WARNING: could not load Java7 Path class");
      }
    }
    CLASS_JAVA7_PATH = (Class)localObject;
  }
  
  private boolean hasSuperClassStartingWith(Class<?> paramClass, String paramString)
  {
    for (paramClass = paramClass.getSuperclass();; paramClass = paramClass.getSuperclass())
    {
      if ((paramClass == null) || (paramClass == Object.class)) {
        return false;
      }
      if (paramClass.getName().startsWith(paramString)) {
        return true;
      }
    }
  }
  
  private Object instantiate(String paramString)
  {
    try
    {
      paramString = Class.forName(paramString).newInstance();
      return paramString;
    }
    catch (Exception paramString)
    {
      return null;
    }
    catch (LinkageError paramString)
    {
      for (;;) {}
    }
  }
  
  public JsonDeserializer<?> findDeserializer(JavaType paramJavaType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    Object localObject2 = null;
    Object localObject3 = paramJavaType.getRawClass();
    Object localObject1;
    if ((CLASS_JAVA7_PATH != null) && (CLASS_JAVA7_PATH.isAssignableFrom((Class)localObject3))) {
      localObject1 = (JsonDeserializer)instantiate("com.fasterxml.jackson.databind.ext.PathDeserializer");
    }
    do
    {
      do
      {
        return (JsonDeserializer<?>)localObject1;
        if ((CLASS_DOM_NODE != null) && (CLASS_DOM_NODE.isAssignableFrom((Class)localObject3))) {
          return (JsonDeserializer)instantiate("com.fasterxml.jackson.databind.ext.DOMDeserializer$NodeDeserializer");
        }
        if ((CLASS_DOM_DOCUMENT != null) && (CLASS_DOM_DOCUMENT.isAssignableFrom((Class)localObject3))) {
          return (JsonDeserializer)instantiate("com.fasterxml.jackson.databind.ext.DOMDeserializer$DocumentDeserializer");
        }
        if (((Class)localObject3).getName().startsWith("javax.xml.")) {
          break;
        }
        localObject1 = localObject2;
      } while (!hasSuperClassStartingWith((Class)localObject3, "javax.xml."));
      localObject3 = instantiate("com.fasterxml.jackson.databind.ext.CoreXMLDeserializers");
      localObject1 = localObject2;
    } while (localObject3 == null);
    return ((Deserializers)localObject3).findBeanDeserializer(paramJavaType, paramDeserializationConfig, paramBeanDescription);
  }
  
  public JsonSerializer<?> findSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType, BeanDescription paramBeanDescription)
  {
    Object localObject2 = null;
    Object localObject3 = paramJavaType.getRawClass();
    Object localObject1;
    if ((CLASS_JAVA7_PATH != null) && (CLASS_JAVA7_PATH.isAssignableFrom((Class)localObject3))) {
      localObject1 = ToStringSerializer.instance;
    }
    do
    {
      do
      {
        return (JsonSerializer<?>)localObject1;
        if ((CLASS_DOM_NODE != null) && (CLASS_DOM_NODE.isAssignableFrom((Class)localObject3))) {
          return (JsonSerializer)instantiate("com.fasterxml.jackson.databind.ext.DOMSerializer");
        }
        if (((Class)localObject3).getName().startsWith("javax.xml.")) {
          break;
        }
        localObject1 = localObject2;
      } while (!hasSuperClassStartingWith((Class)localObject3, "javax.xml."));
      localObject3 = instantiate("com.fasterxml.jackson.databind.ext.CoreXMLSerializers");
      localObject1 = localObject2;
    } while (localObject3 == null);
    return ((Serializers)localObject3).findSerializer(paramSerializationConfig, paramJavaType, paramBeanDescription);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\ext\OptionalHandlerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */