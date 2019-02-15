package com.google.android.gms.internal;

public class zzbqd<T, U>
{
  private final T first;
  private final U second;
  
  public zzbqd(T paramT, U paramU)
  {
    this.first = paramT;
    this.second = paramU;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (getClass() != paramObject.getClass())) {
        return false;
      }
      paramObject = (zzbqd)paramObject;
      if (this.first != null)
      {
        if (this.first.equals(((zzbqd)paramObject).first)) {}
      }
      else {
        while (((zzbqd)paramObject).first != null) {
          return false;
        }
      }
      if (this.second == null) {
        break;
      }
    } while (this.second.equals(((zzbqd)paramObject).second));
    for (;;)
    {
      return false;
      if (((zzbqd)paramObject).second == null) {
        break;
      }
    }
  }
  
  public T getFirst()
  {
    return (T)this.first;
  }
  
  public int hashCode()
  {
    int j = 0;
    if (this.first != null) {}
    for (int i = this.first.hashCode();; i = 0)
    {
      if (this.second != null) {
        j = this.second.hashCode();
      }
      return i * 31 + j;
    }
  }
  
  public String toString()
  {
    String str1 = String.valueOf(this.first);
    String str2 = String.valueOf(this.second);
    return String.valueOf(str1).length() + 7 + String.valueOf(str2).length() + "Pair(" + str1 + "," + str2 + ")";
  }
  
  public U zzZZ()
  {
    return (U)this.second;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbqd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */