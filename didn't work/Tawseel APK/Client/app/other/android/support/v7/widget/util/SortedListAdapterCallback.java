package android.support.v7.widget.util;

import android.support.v7.util.SortedList.Callback;
import android.support.v7.widget.RecyclerView.Adapter;

public abstract class SortedListAdapterCallback<T2>
  extends SortedList.Callback<T2>
{
  final RecyclerView.Adapter mAdapter;
  
  public SortedListAdapterCallback(RecyclerView.Adapter paramAdapter)
  {
    this.mAdapter = paramAdapter;
  }
  
  public void onChanged(int paramInt1, int paramInt2)
  {
    this.mAdapter.notifyItemRangeChanged(paramInt1, paramInt2);
  }
  
  public void onInserted(int paramInt1, int paramInt2)
  {
    this.mAdapter.notifyItemRangeInserted(paramInt1, paramInt2);
  }
  
  public void onMoved(int paramInt1, int paramInt2)
  {
    this.mAdapter.notifyItemMoved(paramInt1, paramInt2);
  }
  
  public void onRemoved(int paramInt1, int paramInt2)
  {
    this.mAdapter.notifyItemRangeRemoved(paramInt1, paramInt2);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\android\support\v7\widget\util\SortedListAdapterCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */