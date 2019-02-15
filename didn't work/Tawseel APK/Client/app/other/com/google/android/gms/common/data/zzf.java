package com.google.android.gms.common.data;

import java.util.ArrayList;

public abstract class zzf<T>
  extends AbstractDataBuffer<T>
{
  private boolean zzaCD = false;
  private ArrayList<Integer> zzaCE;
  
  protected zzf(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
  }
  
  private void zzwH()
  {
    for (;;)
    {
      int i;
      String str2;
      try
      {
        if (this.zzaCD) {
          break label204;
        }
        int j = this.zzazI.getCount();
        this.zzaCE = new ArrayList();
        if (j <= 0) {
          break label199;
        }
        this.zzaCE.add(Integer.valueOf(0));
        String str3 = zzwG();
        i = this.zzazI.zzcC(0);
        String str1 = this.zzazI.zzd(str3, 0, i);
        i = 1;
        if (i >= j) {
          break label199;
        }
        int k = this.zzazI.zzcC(i);
        str2 = this.zzazI.zzd(str3, i, k);
        if (str2 == null) {
          throw new NullPointerException(String.valueOf(str3).length() + 78 + "Missing value for markerColumn: " + str3 + ", at row: " + i + ", for window: " + k);
        }
      }
      finally {}
      if (!str2.equals(localObject1))
      {
        this.zzaCE.add(Integer.valueOf(i));
        Object localObject2 = str2;
        break label207;
        label199:
        this.zzaCD = true;
        label204:
        return;
      }
      label207:
      i += 1;
    }
  }
  
  public final T get(int paramInt)
  {
    zzwH();
    return (T)zzn(zzcG(paramInt), zzcH(paramInt));
  }
  
  public int getCount()
  {
    zzwH();
    return this.zzaCE.size();
  }
  
  int zzcG(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.zzaCE.size())) {
      throw new IllegalArgumentException(53 + "Position " + paramInt + " is out of bounds for this buffer");
    }
    return ((Integer)this.zzaCE.get(paramInt)).intValue();
  }
  
  protected int zzcH(int paramInt)
  {
    int j;
    if ((paramInt < 0) || (paramInt == this.zzaCE.size()))
    {
      j = 0;
      return j;
    }
    if (paramInt == this.zzaCE.size() - 1) {}
    for (int i = this.zzazI.getCount() - ((Integer)this.zzaCE.get(paramInt)).intValue();; i = ((Integer)this.zzaCE.get(paramInt + 1)).intValue() - ((Integer)this.zzaCE.get(paramInt)).intValue())
    {
      j = i;
      if (i != 1) {
        break;
      }
      paramInt = zzcG(paramInt);
      int k = this.zzazI.zzcC(paramInt);
      String str = zzwI();
      j = i;
      if (str == null) {
        break;
      }
      j = i;
      if (this.zzazI.zzd(str, paramInt, k) != null) {
        break;
      }
      return 0;
    }
  }
  
  protected abstract T zzn(int paramInt1, int paramInt2);
  
  protected abstract String zzwG();
  
  protected String zzwI()
  {
    return null;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\common\data\zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */