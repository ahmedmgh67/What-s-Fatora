package com.google.android.gms.internal;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

public class zzbtk
  implements Closeable, Flushable
{
  private static final String[] zzcqd = new String[''];
  private static final String[] zzcqe;
  private final Writer out;
  private String separator;
  private boolean zzcmv;
  private boolean zzcmw;
  private boolean zzcpG;
  private int[] zzcpO = new int[32];
  private int zzcpP = 0;
  private String zzcqf;
  private String zzcqg;
  
  static
  {
    int i = 0;
    while (i <= 31)
    {
      zzcqd[i] = String.format("\\u%04x", new Object[] { Integer.valueOf(i) });
      i += 1;
    }
    zzcqd[34] = "\\\"";
    zzcqd[92] = "\\\\";
    zzcqd[9] = "\\t";
    zzcqd[8] = "\\b";
    zzcqd[10] = "\\n";
    zzcqd[13] = "\\r";
    zzcqd[12] = "\\f";
    zzcqe = (String[])zzcqd.clone();
    zzcqe[60] = "\\u003c";
    zzcqe[62] = "\\u003e";
    zzcqe[38] = "\\u0026";
    zzcqe[61] = "\\u003d";
    zzcqe[39] = "\\u0027";
  }
  
  public zzbtk(Writer paramWriter)
  {
    zzqd(6);
    this.separator = ":";
    this.zzcmv = true;
    if (paramWriter == null) {
      throw new NullPointerException("out == null");
    }
    this.out = paramWriter;
  }
  
  private int zzaco()
  {
    if (this.zzcpP == 0) {
      throw new IllegalStateException("JsonWriter is closed.");
    }
    return this.zzcpO[(this.zzcpP - 1)];
  }
  
  private void zzacp()
    throws IOException
  {
    if (this.zzcqg != null)
    {
      zzacr();
      zzka(this.zzcqg);
      this.zzcqg = null;
    }
  }
  
  private void zzacq()
    throws IOException
  {
    if (this.zzcqf == null) {}
    for (;;)
    {
      return;
      this.out.write("\n");
      int i = 1;
      int j = this.zzcpP;
      while (i < j)
      {
        this.out.write(this.zzcqf);
        i += 1;
      }
    }
  }
  
  private void zzacr()
    throws IOException
  {
    int i = zzaco();
    if (i == 5) {
      this.out.write(44);
    }
    while (i == 3)
    {
      zzacq();
      zzqf(4);
      return;
    }
    throw new IllegalStateException("Nesting problem.");
  }
  
  private void zzbk(boolean paramBoolean)
    throws IOException
  {
    switch (zzaco())
    {
    case 3: 
    case 5: 
    default: 
      throw new IllegalStateException("Nesting problem.");
    case 7: 
      if (!this.zzcpG) {
        throw new IllegalStateException("JSON must have only one top-level value.");
      }
    case 6: 
      if ((!this.zzcpG) && (!paramBoolean)) {
        throw new IllegalStateException("JSON must start with an array or an object.");
      }
      zzqf(7);
      return;
    case 1: 
      zzqf(2);
      zzacq();
      return;
    case 2: 
      this.out.append(',');
      zzacq();
      return;
    }
    this.out.append(this.separator);
    zzqf(5);
  }
  
  private zzbtk zzc(int paramInt1, int paramInt2, String paramString)
    throws IOException
  {
    int i = zzaco();
    if ((i != paramInt2) && (i != paramInt1)) {
      throw new IllegalStateException("Nesting problem.");
    }
    if (this.zzcqg != null)
    {
      paramString = String.valueOf(this.zzcqg);
      if (paramString.length() != 0) {}
      for (paramString = "Dangling name: ".concat(paramString);; paramString = new String("Dangling name: ")) {
        throw new IllegalStateException(paramString);
      }
    }
    this.zzcpP -= 1;
    if (i == paramInt2) {
      zzacq();
    }
    this.out.write(paramString);
    return this;
  }
  
  private void zzka(String paramString)
    throws IOException
  {
    int j = 0;
    if (this.zzcmw) {}
    int m;
    int i;
    int n;
    int k;
    for (String[] arrayOfString = zzcqe;; arrayOfString = zzcqd)
    {
      this.out.write("\"");
      m = paramString.length();
      i = 0;
      for (;;)
      {
        if (i >= m) {
          break label153;
        }
        n = paramString.charAt(i);
        if (n >= 128) {
          break;
        }
        String str2 = arrayOfString[n];
        str1 = str2;
        if (str2 != null) {
          break label101;
        }
        k = j;
        i += 1;
        j = k;
      }
    }
    if (n == 8232) {}
    for (String str1 = "\\u2028";; str1 = "\\u2029")
    {
      label101:
      if (j < i) {
        this.out.write(paramString, j, i - j);
      }
      this.out.write(str1);
      k = i + 1;
      break;
      k = j;
      if (n != 8233) {
        break;
      }
    }
    label153:
    if (j < m) {
      this.out.write(paramString, j, m - j);
    }
    this.out.write("\"");
  }
  
  private zzbtk zzp(int paramInt, String paramString)
    throws IOException
  {
    zzbk(true);
    zzqd(paramInt);
    this.out.write(paramString);
    return this;
  }
  
  private void zzqd(int paramInt)
  {
    if (this.zzcpP == this.zzcpO.length)
    {
      arrayOfInt = new int[this.zzcpP * 2];
      System.arraycopy(this.zzcpO, 0, arrayOfInt, 0, this.zzcpP);
      this.zzcpO = arrayOfInt;
    }
    int[] arrayOfInt = this.zzcpO;
    int i = this.zzcpP;
    this.zzcpP = (i + 1);
    arrayOfInt[i] = paramInt;
  }
  
  private void zzqf(int paramInt)
  {
    this.zzcpO[(this.zzcpP - 1)] = paramInt;
  }
  
  public void close()
    throws IOException
  {
    this.out.close();
    int i = this.zzcpP;
    if ((i > 1) || ((i == 1) && (this.zzcpO[(i - 1)] != 7))) {
      throw new IOException("Incomplete document");
    }
    this.zzcpP = 0;
  }
  
  public void flush()
    throws IOException
  {
    if (this.zzcpP == 0) {
      throw new IllegalStateException("JsonWriter is closed.");
    }
    this.out.flush();
  }
  
  public boolean isLenient()
  {
    return this.zzcpG;
  }
  
  public final void setIndent(String paramString)
  {
    if (paramString.length() == 0)
    {
      this.zzcqf = null;
      this.separator = ":";
      return;
    }
    this.zzcqf = paramString;
    this.separator = ": ";
  }
  
  public final void setLenient(boolean paramBoolean)
  {
    this.zzcpG = paramBoolean;
  }
  
  public zzbtk zza(Number paramNumber)
    throws IOException
  {
    if (paramNumber == null) {
      return zzaca();
    }
    zzacp();
    String str = paramNumber.toString();
    if ((!this.zzcpG) && ((str.equals("-Infinity")) || (str.equals("Infinity")) || (str.equals("NaN"))))
    {
      paramNumber = String.valueOf(paramNumber);
      throw new IllegalArgumentException(String.valueOf(paramNumber).length() + 39 + "Numeric values must be finite, but was " + paramNumber);
    }
    zzbk(false);
    this.out.append(str);
    return this;
  }
  
  public zzbtk zzaU(long paramLong)
    throws IOException
  {
    zzacp();
    zzbk(false);
    this.out.write(Long.toString(paramLong));
    return this;
  }
  
  public zzbtk zzabW()
    throws IOException
  {
    zzacp();
    return zzp(1, "[");
  }
  
  public zzbtk zzabX()
    throws IOException
  {
    return zzc(1, 2, "]");
  }
  
  public zzbtk zzabY()
    throws IOException
  {
    zzacp();
    return zzp(3, "{");
  }
  
  public zzbtk zzabZ()
    throws IOException
  {
    return zzc(3, 5, "}");
  }
  
  public zzbtk zzaca()
    throws IOException
  {
    if (this.zzcqg != null)
    {
      if (this.zzcmv) {
        zzacp();
      }
    }
    else
    {
      zzbk(false);
      this.out.write("null");
      return this;
    }
    this.zzcqg = null;
    return this;
  }
  
  public final boolean zzacm()
  {
    return this.zzcmw;
  }
  
  public final boolean zzacn()
  {
    return this.zzcmv;
  }
  
  public zzbtk zzbg(boolean paramBoolean)
    throws IOException
  {
    zzacp();
    zzbk(false);
    Writer localWriter = this.out;
    if (paramBoolean) {}
    for (String str = "true";; str = "false")
    {
      localWriter.write(str);
      return this;
    }
  }
  
  public final void zzbi(boolean paramBoolean)
  {
    this.zzcmw = paramBoolean;
  }
  
  public final void zzbj(boolean paramBoolean)
  {
    this.zzcmv = paramBoolean;
  }
  
  public zzbtk zzjW(String paramString)
    throws IOException
  {
    if (paramString == null) {
      throw new NullPointerException("name == null");
    }
    if (this.zzcqg != null) {
      throw new IllegalStateException();
    }
    if (this.zzcpP == 0) {
      throw new IllegalStateException("JsonWriter is closed.");
    }
    this.zzcqg = paramString;
    return this;
  }
  
  public zzbtk zzjX(String paramString)
    throws IOException
  {
    if (paramString == null) {
      return zzaca();
    }
    zzacp();
    zzbk(false);
    zzka(paramString);
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbtk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */