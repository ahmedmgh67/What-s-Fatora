package com.crashlytics.android.answers;

public class RatingEvent
  extends PredefinedEvent<RatingEvent>
{
  static final String CONTENT_ID_ATTRIBUTE = "contentId";
  static final String CONTENT_NAME_ATTRIBUTE = "contentName";
  static final String CONTENT_TYPE_ATTRIBUTE = "contentType";
  static final String RATING_ATTRIBUTE = "rating";
  static final String TYPE = "rating";
  
  String getPredefinedType()
  {
    return "rating";
  }
  
  public RatingEvent putContentId(String paramString)
  {
    this.predefinedAttributes.put("contentId", paramString);
    return this;
  }
  
  public RatingEvent putContentName(String paramString)
  {
    this.predefinedAttributes.put("contentName", paramString);
    return this;
  }
  
  public RatingEvent putContentType(String paramString)
  {
    this.predefinedAttributes.put("contentType", paramString);
    return this;
  }
  
  public RatingEvent putRating(int paramInt)
  {
    this.predefinedAttributes.put("rating", Integer.valueOf(paramInt));
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\crashlytics\android\answers\RatingEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */