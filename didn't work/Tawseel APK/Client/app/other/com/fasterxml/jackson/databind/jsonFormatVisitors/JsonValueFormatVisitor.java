package com.fasterxml.jackson.databind.jsonFormatVisitors;

import java.util.Set;

public abstract interface JsonValueFormatVisitor
{
  public abstract void enumTypes(Set<String> paramSet);
  
  public abstract void format(JsonValueFormat paramJsonValueFormat);
  
  public static class Base
    implements JsonValueFormatVisitor
  {
    public void enumTypes(Set<String> paramSet) {}
    
    public void format(JsonValueFormat paramJsonValueFormat) {}
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\jsonFormatVisitors\JsonValueFormatVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */