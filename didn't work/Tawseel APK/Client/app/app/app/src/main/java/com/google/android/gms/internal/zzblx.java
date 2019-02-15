package com.google.android.gms.internal;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.Iterator;
import java.util.List;

public class zzblx
  extends Reader
{
  private boolean closed = false;
  private List<String> zzcbs = null;
  private int zzcbt;
  private int zzcbu;
  private int zzcbv = this.zzcbt;
  private int zzcbw = this.zzcbu;
  private boolean zzcbx = false;
  
  private String zzWA()
  {
    if (this.zzcbu < this.zzcbs.size()) {
      return (String)this.zzcbs.get(this.zzcbu);
    }
    return null;
  }
  
  private int zzWB()
  {
    String str = zzWA();
    if (str == null) {
      return 0;
    }
    return str.length() - this.zzcbt;
  }
  
  private void zzWC()
    throws IOException
  {
    if (this.closed) {
      throw new IOException("Stream already closed");
    }
    if (!this.zzcbx) {
      throw new IOException("Reader needs to be frozen before read operations can be called");
    }
  }
  
  private long zzaK(long paramLong)
  {
    long l1 = 0L;
    while ((this.zzcbu < this.zzcbs.size()) && (l1 < paramLong))
    {
      int i = zzWB();
      long l2 = paramLong - l1;
      if (l2 < i)
      {
        this.zzcbt = ((int)(this.zzcbt + l2));
        l1 += l2;
      }
      else
      {
        l1 += i;
        this.zzcbt = 0;
        this.zzcbu += 1;
      }
    }
    return l1;
  }
  
  public void close()
    throws IOException
  {
    zzWC();
    this.closed = true;
  }
  
  public void mark(int paramInt)
    throws IOException
  {
    zzWC();
    this.zzcbv = this.zzcbt;
    this.zzcbw = this.zzcbu;
  }
  
  public boolean markSupported()
  {
    return true;
  }
  
  public int read()
    throws IOException
  {
    zzWC();
    String str = zzWA();
    if (str == null) {
      return -1;
    }
    int i = str.charAt(this.zzcbt);
    zzaK(1L);
    return i;
  }
  
  public int read(CharBuffer paramCharBuffer)
    throws IOException
  {
    zzWC();
    int j = paramCharBuffer.remaining();
    int i = 0;
    for (String str = zzWA(); (j > 0) && (str != null); str = zzWA())
    {
      int k = Math.min(str.length() - this.zzcbt, j);
      paramCharBuffer.put((String)this.zzcbs.get(this.zzcbu), this.zzcbt, this.zzcbt + k);
      j -= k;
      i += k;
      zzaK(k);
    }
    if ((i > 0) || (str != null)) {
      return i;
    }
    return -1;
  }
  
  public int read(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    zzWC();
    String str = zzWA();
    int i = 0;
    while ((str != null) && (i < paramInt2))
    {
      int j = Math.min(zzWB(), paramInt2 - i);
      str.getChars(this.zzcbt, this.zzcbt + j, paramArrayOfChar, paramInt1 + i);
      zzaK(j);
      str = zzWA();
      i += j;
    }
    if ((i > 0) || (str != null)) {
      return i;
    }
    return -1;
  }
  
  public boolean ready()
    throws IOException
  {
    zzWC();
    return true;
  }
  
  public void reset()
    throws IOException
  {
    this.zzcbt = this.zzcbv;
    this.zzcbu = this.zzcbw;
  }
  
  public long skip(long paramLong)
    throws IOException
  {
    zzWC();
    return zzaK(paramLong);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = this.zzcbs.iterator();
    while (localIterator.hasNext()) {
      localStringBuilder.append((String)localIterator.next());
    }
    return localStringBuilder.toString();
  }
  
  public void zzWz()
  {
    if (this.zzcbx) {
      throw new IllegalStateException("Trying to freeze frozen StringListReader");
    }
    this.zzcbx = true;
  }
  
  public void zziU(String paramString)
  {
    if (this.zzcbx) {
      throw new IllegalStateException("Trying to add string after reading");
    }
    if (paramString.length() > 0) {
      this.zzcbs.add(paramString);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzblx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */