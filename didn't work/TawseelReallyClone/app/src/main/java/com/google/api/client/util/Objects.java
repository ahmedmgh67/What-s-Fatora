package com.google.api.client.util;

public final class Objects
{
  public static boolean equal(Object paramObject1, Object paramObject2)
  {
    return com.google.api.client.repackaged.com.google.common.base.Objects.equal(paramObject1, paramObject2);
  }
  
  public static ToStringHelper toStringHelper(Object paramObject)
  {
    return new ToStringHelper(paramObject.getClass().getSimpleName());
  }
  
  public static final class ToStringHelper
  {
    private final String className;
    private ValueHolder holderHead = new ValueHolder(null);
    private ValueHolder holderTail = this.holderHead;
    private boolean omitNullValues;
    
    ToStringHelper(String paramString)
    {
      this.className = paramString;
    }
    
    private ValueHolder addHolder()
    {
      ValueHolder localValueHolder = new ValueHolder(null);
      this.holderTail.next = localValueHolder;
      this.holderTail = localValueHolder;
      return localValueHolder;
    }
    
    private ToStringHelper addHolder(String paramString, Object paramObject)
    {
      ValueHolder localValueHolder = addHolder();
      localValueHolder.value = paramObject;
      localValueHolder.name = ((String)Preconditions.checkNotNull(paramString));
      return this;
    }
    
    public ToStringHelper add(String paramString, Object paramObject)
    {
      return addHolder(paramString, paramObject);
    }
    
    public ToStringHelper omitNullValues()
    {
      this.omitNullValues = true;
      return this;
    }
    
    public String toString()
    {
      boolean bool = this.omitNullValues;
      Object localObject1 = "";
      StringBuilder localStringBuilder = new StringBuilder(32).append(this.className).append('{');
      ValueHolder localValueHolder = this.holderHead.next;
      while (localValueHolder != null)
      {
        Object localObject2;
        if (bool)
        {
          localObject2 = localObject1;
          if (localValueHolder.value == null) {}
        }
        else
        {
          localStringBuilder.append((String)localObject1);
          localObject2 = ", ";
          if (localValueHolder.name != null) {
            localStringBuilder.append(localValueHolder.name).append('=');
          }
          localStringBuilder.append(localValueHolder.value);
        }
        localValueHolder = localValueHolder.next;
        localObject1 = localObject2;
      }
      return '}';
    }
    
    private static final class ValueHolder
    {
      String name;
      ValueHolder next;
      Object value;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\Objects.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */