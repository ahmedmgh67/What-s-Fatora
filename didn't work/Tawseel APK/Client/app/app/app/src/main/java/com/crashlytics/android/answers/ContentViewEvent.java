package com.crashlytics.android.answers;

public class ContentViewEvent
  extends PredefinedEvent<ContentViewEvent>
{
  static final String CONTENT_ID_ATTRIBUTE = "contentId";
  static final String CONTENT_NAME_ATTRIBUTE = "contentName";
  static final String CONTENT_TYPE_ATTRIBUTE = "contentType";
  static final String TYPE = "contentView";
  
  String getPredefinedType()
  {
    return "contentView";
  }
  
  public ContentViewEvent putContentId(String paramString)
  {
    this.predefinedAttributes.put("contentId", paramString);
    return this;
  }
  
  public ContentViewEvent putContentName(String paramString)
  {
    this.predefinedAttributes.put("contentName", paramString);
    return this;
  }
  
  public ContentViewEvent putContentType(String paramString)
  {
    this.predefinedAttributes.put("contentType", paramString);
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\crashlytics\android\answers\ContentViewEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */