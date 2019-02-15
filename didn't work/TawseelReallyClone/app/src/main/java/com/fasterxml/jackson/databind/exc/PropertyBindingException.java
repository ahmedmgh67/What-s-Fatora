package com.fasterxml.jackson.databind.exc;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public abstract class PropertyBindingException
  extends JsonMappingException
{
  private static final int MAX_DESC_LENGTH = 1000;
  protected transient String _propertiesAsString;
  protected final Collection<Object> _propertyIds;
  protected final String _propertyName;
  protected final Class<?> _referringClass;
  
  protected PropertyBindingException(JsonParser paramJsonParser, String paramString1, JsonLocation paramJsonLocation, Class<?> paramClass, String paramString2, Collection<Object> paramCollection)
  {
    super(paramJsonParser, paramString1, paramJsonLocation);
    this._referringClass = paramClass;
    this._propertyName = paramString2;
    this._propertyIds = paramCollection;
  }
  
  @Deprecated
  protected PropertyBindingException(String paramString1, JsonLocation paramJsonLocation, Class<?> paramClass, String paramString2, Collection<Object> paramCollection)
  {
    this(null, paramString1, paramJsonLocation, paramClass, paramString2, paramCollection);
  }
  
  public Collection<Object> getKnownPropertyIds()
  {
    if (this._propertyIds == null) {
      return null;
    }
    return Collections.unmodifiableCollection(this._propertyIds);
  }
  
  public String getMessageSuffix()
  {
    Object localObject2 = this._propertiesAsString;
    Object localObject1 = localObject2;
    int i;
    if (localObject2 == null)
    {
      localObject1 = localObject2;
      if (this._propertyIds != null)
      {
        localObject1 = new StringBuilder(100);
        i = this._propertyIds.size();
        if (i != 1) {
          break label100;
        }
        ((StringBuilder)localObject1).append(" (one known property: \"");
        ((StringBuilder)localObject1).append(String.valueOf(this._propertyIds.iterator().next()));
        ((StringBuilder)localObject1).append('"');
      }
    }
    label100:
    label200:
    for (;;)
    {
      ((StringBuilder)localObject1).append("])");
      localObject1 = ((StringBuilder)localObject1).toString();
      this._propertiesAsString = ((String)localObject1);
      return (String)localObject1;
      ((StringBuilder)localObject1).append(" (").append(i).append(" known properties: ");
      localObject2 = this._propertyIds.iterator();
      for (;;)
      {
        if (!((Iterator)localObject2).hasNext()) {
          break label200;
        }
        ((StringBuilder)localObject1).append('"');
        ((StringBuilder)localObject1).append(String.valueOf(((Iterator)localObject2).next()));
        ((StringBuilder)localObject1).append('"');
        if (((StringBuilder)localObject1).length() > 1000)
        {
          ((StringBuilder)localObject1).append(" [truncated]");
          break;
        }
        if (((Iterator)localObject2).hasNext()) {
          ((StringBuilder)localObject1).append(", ");
        }
      }
    }
  }
  
  public String getPropertyName()
  {
    return this._propertyName;
  }
  
  public Class<?> getReferringClass()
  {
    return this._referringClass;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\exc\PropertyBindingException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */