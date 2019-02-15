package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;

public class zzblk
{
  private final List<List<String>> zzbZh;
  private final List<String> zzbZi;
  
  public zzblk(List<List<String>> paramList, List<String> paramList1)
  {
    if (paramList.size() != paramList1.size() - 1) {
      throw new IllegalArgumentException("Number of posts need to be n-1 for n hashes in CompoundHash");
    }
    this.zzbZh = paramList;
    this.zzbZi = paramList1;
  }
  
  public List<List<String>> zzVF()
  {
    return Collections.unmodifiableList(this.zzbZh);
  }
  
  public List<String> zzVG()
  {
    return Collections.unmodifiableList(this.zzbZi);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzblk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */