package android.support.v4.print;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.RequiresApi;

@TargetApi(20)
@RequiresApi(20)
class PrintHelperApi20
  extends PrintHelperKitkat
{
  PrintHelperApi20(Context paramContext)
  {
    super(paramContext);
    this.mPrintActivityRespectsOrientation = false;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\android\support\v4\print\PrintHelperApi20.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */