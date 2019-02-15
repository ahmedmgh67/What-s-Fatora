package com.crashlytics.android.answers;

public class LevelStartEvent
  extends PredefinedEvent<LevelStartEvent>
{
  static final String LEVEL_NAME_ATTRIBUTE = "levelName";
  static final String TYPE = "levelStart";
  
  String getPredefinedType()
  {
    return "levelStart";
  }
  
  public LevelStartEvent putLevelName(String paramString)
  {
    this.predefinedAttributes.put("levelName", paramString);
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\crashlytics\android\answers\LevelStartEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */