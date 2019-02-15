package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class zzbmj
  implements Comparable<zzbmj>, Iterable<zzbos>
{
  private static final zzbmj zzccd;
  private final int end;
  private final int start;
  private final zzbos[] zzccc;
  
  static
  {
    if (!zzbmj.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      zzccd = new zzbmj("");
      return;
    }
  }
  
  public zzbmj(String paramString)
  {
    paramString = paramString.split("/");
    int m = paramString.length;
    int i = 0;
    int k;
    for (int j = 0; i < m; j = k)
    {
      k = j;
      if (paramString[i].length() > 0) {
        k = j + 1;
      }
      i += 1;
    }
    this.zzccc = new zzbos[j];
    m = paramString.length;
    j = 0;
    i = 0;
    if (j < m)
    {
      String str = paramString[j];
      if (str.length() <= 0) {
        break label132;
      }
      zzbos[] arrayOfzzbos = this.zzccc;
      k = i + 1;
      arrayOfzzbos[i] = zzbos.zzjb(str);
      i = k;
    }
    label132:
    for (;;)
    {
      j += 1;
      break;
      this.start = 0;
      this.end = this.zzccc.length;
      return;
    }
  }
  
  public zzbmj(List<String> paramList)
  {
    this.zzccc = new zzbos[paramList.size()];
    Iterator localIterator = paramList.iterator();
    int i = 0;
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      this.zzccc[i] = zzbos.zzjb(str);
      i += 1;
    }
    this.start = 0;
    this.end = paramList.size();
  }
  
  public zzbmj(zzbos... paramVarArgs)
  {
    this.zzccc = ((zzbos[])Arrays.copyOf(paramVarArgs, paramVarArgs.length));
    this.start = 0;
    this.end = paramVarArgs.length;
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      zzbos localzzbos = paramVarArgs[i];
      assert (localzzbos != null) : "Can't construct a path with a null value!";
      i += 1;
    }
  }
  
  private zzbmj(zzbos[] paramArrayOfzzbos, int paramInt1, int paramInt2)
  {
    this.zzccc = paramArrayOfzzbos;
    this.start = paramInt1;
    this.end = paramInt2;
  }
  
  public static zzbmj zzXf()
  {
    return zzccd;
  }
  
  public static zzbmj zza(zzbmj paramzzbmj1, zzbmj paramzzbmj2)
  {
    zzbos localzzbos1 = paramzzbmj1.zzXi();
    zzbos localzzbos2 = paramzzbmj2.zzXi();
    if (localzzbos1 == null) {
      return paramzzbmj2;
    }
    if (localzzbos1.equals(localzzbos2)) {
      return zza(paramzzbmj1.zzXj(), paramzzbmj2.zzXj());
    }
    paramzzbmj2 = String.valueOf(paramzzbmj2);
    paramzzbmj1 = String.valueOf(paramzzbmj1);
    throw new DatabaseException(String.valueOf(paramzzbmj2).length() + 37 + String.valueOf(paramzzbmj1).length() + "INTERNAL ERROR: " + paramzzbmj2 + " is not contained in " + paramzzbmj1);
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof zzbmj)) {
      return false;
    }
    if (this == paramObject) {
      return true;
    }
    paramObject = (zzbmj)paramObject;
    if (size() != ((zzbmj)paramObject).size()) {
      return false;
    }
    int j = this.start;
    int i = ((zzbmj)paramObject).start;
    while ((j < this.end) && (i < ((zzbmj)paramObject).end))
    {
      if (!this.zzccc[j].equals(paramObject.zzccc[i])) {
        return false;
      }
      j += 1;
      i += 1;
    }
    return true;
  }
  
  public int hashCode()
  {
    int j = 0;
    int i = this.start;
    while (i < this.end)
    {
      j = j * 37 + this.zzccc[i].hashCode();
      i += 1;
    }
    return j;
  }
  
  public boolean isEmpty()
  {
    return this.start >= this.end;
  }
  
  public Iterator<zzbos> iterator()
  {
    new Iterator()
    {
      int offset = zzbmj.zzk(zzbmj.this);
      
      public boolean hasNext()
      {
        return this.offset < zzbmj.zzl(zzbmj.this);
      }
      
      public void remove()
      {
        throw new UnsupportedOperationException("Can't remove component from immutable Path!");
      }
      
      public zzbos zzXm()
      {
        if (!hasNext()) {
          throw new NoSuchElementException("No more elements.");
        }
        zzbos localzzbos = zzbmj.zzm(zzbmj.this)[this.offset];
        this.offset += 1;
        return localzzbos;
      }
    };
  }
  
  public int size()
  {
    return this.end - this.start;
  }
  
  public String toString()
  {
    if (isEmpty()) {
      return "/";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = this.start;
    while (i < this.end)
    {
      localStringBuilder.append("/");
      localStringBuilder.append(this.zzccc[i].asString());
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public String zzXg()
  {
    if (isEmpty()) {
      return "/";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = this.start;
    while (i < this.end)
    {
      if (i > this.start) {
        localStringBuilder.append("/");
      }
      localStringBuilder.append(this.zzccc[i].asString());
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public List<String> zzXh()
  {
    ArrayList localArrayList = new ArrayList(size());
    Iterator localIterator = iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(((zzbos)localIterator.next()).asString());
    }
    return localArrayList;
  }
  
  public zzbos zzXi()
  {
    if (isEmpty()) {
      return null;
    }
    return this.zzccc[this.start];
  }
  
  public zzbmj zzXj()
  {
    int j = this.start;
    int i = j;
    if (!isEmpty()) {
      i = j + 1;
    }
    return new zzbmj(this.zzccc, i, this.end);
  }
  
  public zzbmj zzXk()
  {
    if (isEmpty()) {
      return null;
    }
    return new zzbmj(this.zzccc, this.start, this.end - 1);
  }
  
  public zzbos zzXl()
  {
    if (!isEmpty()) {
      return this.zzccc[(this.end - 1)];
    }
    return null;
  }
  
  public zzbmj zza(zzbos paramzzbos)
  {
    int i = size();
    zzbos[] arrayOfzzbos = new zzbos[i + 1];
    System.arraycopy(this.zzccc, this.start, arrayOfzzbos, 0, i);
    arrayOfzzbos[i] = paramzzbos;
    return new zzbmj(arrayOfzzbos, 0, i + 1);
  }
  
  public zzbmj zzh(zzbmj paramzzbmj)
  {
    int i = size() + paramzzbmj.size();
    zzbos[] arrayOfzzbos = new zzbos[i];
    System.arraycopy(this.zzccc, this.start, arrayOfzzbos, 0, size());
    System.arraycopy(paramzzbmj.zzccc, paramzzbmj.start, arrayOfzzbos, size(), paramzzbmj.size());
    return new zzbmj(arrayOfzzbos, 0, i);
  }
  
  public boolean zzi(zzbmj paramzzbmj)
  {
    if (size() > paramzzbmj.size()) {
      return false;
    }
    int j = this.start;
    int i = paramzzbmj.start;
    while (j < this.end)
    {
      if (!this.zzccc[j].equals(paramzzbmj.zzccc[i])) {
        return false;
      }
      j += 1;
      i += 1;
    }
    return true;
  }
  
  public int zzj(zzbmj paramzzbmj)
  {
    int j = this.start;
    int i = paramzzbmj.start;
    while ((j < this.end) && (i < paramzzbmj.end))
    {
      int k = this.zzccc[j].zzi(paramzzbmj.zzccc[i]);
      if (k != 0) {
        return k;
      }
      j += 1;
      i += 1;
    }
    if ((j == this.end) && (i == paramzzbmj.end)) {
      return 0;
    }
    if (j == this.end) {
      return -1;
    }
    return 1;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbmj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */