package com.bumptech.glide.util;

public class MultiClassKey
{
  private Class<?> first;
  private Class<?> second;
  
  public MultiClassKey() {}
  
  public MultiClassKey(Class<?> paramClass1, Class<?> paramClass2)
  {
    set(paramClass1, paramClass2);
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
      paramObject = (MultiClassKey)paramObject;
      if (!this.first.equals(((MultiClassKey)paramObject).first)) {
        return false;
      }
    } while (this.second.equals(((MultiClassKey)paramObject).second));
    return false;
  }
  
  public int hashCode()
  {
    return this.first.hashCode() * 31 + this.second.hashCode();
  }
  
  public void set(Class<?> paramClass1, Class<?> paramClass2)
  {
    this.first = paramClass1;
    this.second = paramClass2;
  }
  
  public String toString()
  {
    return "MultiClassKey{first=" + this.first + ", second=" + this.second + '}';
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\util\MultiClassKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */