package android.support.v4.app;

import android.app.Activity;
import android.support.annotation.RestrictTo;
import android.support.v4.util.SimpleArrayMap;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class SupportActivity
  extends Activity
{
  private SimpleArrayMap<Class<? extends ExtraData>, ExtraData> mExtraDataMap = new SimpleArrayMap();
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public <T extends ExtraData> T getExtraData(Class<T> paramClass)
  {
    return (ExtraData)this.mExtraDataMap.get(paramClass);
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void putExtraData(ExtraData paramExtraData)
  {
    this.mExtraDataMap.put(paramExtraData.getClass(), paramExtraData);
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static class ExtraData {}
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\android\support\v4\app\SupportActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */