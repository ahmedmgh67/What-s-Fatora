package com.google.api.client.util;

import java.util.HashSet;
import java.util.TreeSet;

public final class Sets
{
  public static <E> HashSet<E> newHashSet()
  {
    return new HashSet();
  }
  
  public static <E extends Comparable<?>> TreeSet<E> newTreeSet()
  {
    return new TreeSet();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\Sets.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */