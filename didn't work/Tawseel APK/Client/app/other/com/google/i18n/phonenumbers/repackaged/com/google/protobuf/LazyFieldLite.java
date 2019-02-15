package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

import java.io.IOException;

public class LazyFieldLite
{
  private ByteString bytes;
  private ExtensionRegistryLite extensionRegistry;
  private volatile boolean isDirty = false;
  protected volatile MessageLite value;
  
  public LazyFieldLite() {}
  
  public LazyFieldLite(ExtensionRegistryLite paramExtensionRegistryLite, ByteString paramByteString)
  {
    this.extensionRegistry = paramExtensionRegistryLite;
    this.bytes = paramByteString;
  }
  
  public static LazyFieldLite fromValue(MessageLite paramMessageLite)
  {
    LazyFieldLite localLazyFieldLite = new LazyFieldLite();
    localLazyFieldLite.setValue(paramMessageLite);
    return localLazyFieldLite;
  }
  
  public void clear()
  {
    this.bytes = null;
    this.value = null;
    this.extensionRegistry = null;
    this.isDirty = true;
  }
  
  public boolean containsDefaultInstance()
  {
    return (this.value == null) && (this.bytes == null);
  }
  
  protected void ensureInitialized(MessageLite paramMessageLite)
  {
    if (this.value != null) {
      return;
    }
    try
    {
      if (this.value != null) {
        return;
      }
    }
    finally {}
    try
    {
      if (this.bytes != null) {}
      for (this.value = ((MessageLite)paramMessageLite.getParserForType().parseFrom(this.bytes, this.extensionRegistry));; this.value = paramMessageLite) {
        return;
      }
    }
    catch (IOException paramMessageLite)
    {
      for (;;) {}
    }
  }
  
  public ExtensionRegistryLite getExtensionRegistry()
  {
    return this.extensionRegistry;
  }
  
  public int getSerializedSize()
  {
    if (this.isDirty) {
      return this.value.getSerializedSize();
    }
    return this.bytes.size();
  }
  
  public MessageLite getValue(MessageLite paramMessageLite)
  {
    ensureInitialized(paramMessageLite);
    return this.value;
  }
  
  public void merge(LazyFieldLite paramLazyFieldLite)
  {
    if (paramLazyFieldLite.containsDefaultInstance()) {
      return;
    }
    if (this.bytes == null) {
      this.bytes = paramLazyFieldLite.bytes;
    }
    for (;;)
    {
      this.isDirty = false;
      return;
      this.bytes.concat(paramLazyFieldLite.toByteString());
    }
  }
  
  public void setByteString(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
  {
    this.bytes = paramByteString;
    this.extensionRegistry = paramExtensionRegistryLite;
    this.isDirty = false;
  }
  
  public MessageLite setValue(MessageLite paramMessageLite)
  {
    MessageLite localMessageLite = this.value;
    this.value = paramMessageLite;
    this.bytes = null;
    this.isDirty = true;
    return localMessageLite;
  }
  
  public ByteString toByteString()
  {
    if (!this.isDirty) {
      return this.bytes;
    }
    try
    {
      if (!this.isDirty)
      {
        ByteString localByteString1 = this.bytes;
        return localByteString1;
      }
    }
    finally {}
    if (this.value == null) {}
    for (this.bytes = ByteString.EMPTY;; this.bytes = this.value.toByteString())
    {
      this.isDirty = false;
      ByteString localByteString2 = this.bytes;
      return localByteString2;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\i18n\phonenumbers\repackaged\com\google\protobuf\LazyFieldLite.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */