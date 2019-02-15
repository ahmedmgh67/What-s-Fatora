package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

public class SingleFieldBuilder<MType extends GeneratedMessage, BType extends GeneratedMessage.Builder, IType extends MessageOrBuilder>
  implements GeneratedMessage.BuilderParent
{
  private BType builder;
  private boolean isClean;
  private MType message;
  private GeneratedMessage.BuilderParent parent;
  
  public SingleFieldBuilder(MType paramMType, GeneratedMessage.BuilderParent paramBuilderParent, boolean paramBoolean)
  {
    if (paramMType == null) {
      throw new NullPointerException();
    }
    this.message = paramMType;
    this.parent = paramBuilderParent;
    this.isClean = paramBoolean;
  }
  
  private void onChanged()
  {
    if (this.builder != null) {
      this.message = null;
    }
    if ((this.isClean) && (this.parent != null))
    {
      this.parent.markDirty();
      this.isClean = false;
    }
  }
  
  public MType build()
  {
    this.isClean = true;
    return getMessage();
  }
  
  public SingleFieldBuilder<MType, BType, IType> clear()
  {
    if (this.message != null) {}
    for (Message localMessage = this.message.getDefaultInstanceForType();; localMessage = this.builder.getDefaultInstanceForType())
    {
      this.message = ((GeneratedMessage)localMessage);
      if (this.builder != null)
      {
        this.builder.dispose();
        this.builder = null;
      }
      onChanged();
      return this;
    }
  }
  
  public void dispose()
  {
    this.parent = null;
  }
  
  public BType getBuilder()
  {
    if (this.builder == null)
    {
      this.builder = ((GeneratedMessage.Builder)this.message.newBuilderForType(this));
      this.builder.mergeFrom(this.message);
      this.builder.markClean();
    }
    return this.builder;
  }
  
  public MType getMessage()
  {
    if (this.message == null) {
      this.message = ((GeneratedMessage)this.builder.buildPartial());
    }
    return this.message;
  }
  
  public IType getMessageOrBuilder()
  {
    if (this.builder != null) {
      return this.builder;
    }
    return this.message;
  }
  
  public void markDirty()
  {
    onChanged();
  }
  
  public SingleFieldBuilder<MType, BType, IType> mergeFrom(MType paramMType)
  {
    if ((this.builder == null) && (this.message == this.message.getDefaultInstanceForType())) {
      this.message = paramMType;
    }
    for (;;)
    {
      onChanged();
      return this;
      getBuilder().mergeFrom(paramMType);
    }
  }
  
  public SingleFieldBuilder<MType, BType, IType> setMessage(MType paramMType)
  {
    if (paramMType == null) {
      throw new NullPointerException();
    }
    this.message = paramMType;
    if (this.builder != null)
    {
      this.builder.dispose();
      this.builder = null;
    }
    onChanged();
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\i18n\phonenumbers\repackaged\com\google\protobuf\SingleFieldBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */