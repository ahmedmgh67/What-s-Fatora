package android.support.transition;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.LongSparseArray;
import android.util.SparseArray;
import android.view.View;

@TargetApi(14)
@RequiresApi(14)
class TransitionValuesMaps
{
  public SparseArray<TransitionValues> idValues = new SparseArray();
  public LongSparseArray<TransitionValues> itemIdValues = new LongSparseArray();
  public ArrayMap<View, TransitionValues> viewValues = new ArrayMap();
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\android\support\transition\TransitionValuesMaps.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */