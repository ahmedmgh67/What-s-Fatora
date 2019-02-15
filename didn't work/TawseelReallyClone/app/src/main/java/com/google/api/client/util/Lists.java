package com.google.api.client.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public final class Lists
{
  public static <E> ArrayList<E> newArrayList()
  {
    return new ArrayList();
  }
  
  public static <E> ArrayList<E> newArrayList(Iterable<? extends E> paramIterable)
  {
    if ((paramIterable instanceof Collection)) {
      return new ArrayList(Collections2.cast(paramIterable));
    }
    return newArrayList(paramIterable.iterator());
  }
  
  public static <E> ArrayList<E> newArrayList(Iterator<? extends E> paramIterator)
  {
    ArrayList localArrayList = newArrayList();
    while (paramIterator.hasNext()) {
      localArrayList.add(paramIterator.next());
    }
    return localArrayList;
  }
  
  public static <E> ArrayList<E> newArrayListWithCapacity(int paramInt)
  {
    return new ArrayList(paramInt);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\Lists.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */