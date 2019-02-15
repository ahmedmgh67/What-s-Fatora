package com.google.android.gms.internal;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public final class zzbsz
  extends zzbtk
{
  private static final Writer zzcof = new Writer()
  {
    public void close()
      throws IOException
    {
      throw new AssertionError();
    }
    
    public void flush()
      throws IOException
    {
      throw new AssertionError();
    }
    
    public void write(char[] paramAnonymousArrayOfChar, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      throw new AssertionError();
    }
  };
  private static final zzbrx zzcog = new zzbrx("closed");
  private final List<zzbrr> zzcoe = new ArrayList();
  private String zzcoh;
  private zzbrr zzcoi = zzbrt.zzcmL;
  
  public zzbsz()
  {
    super(zzcof);
  }
  
  private zzbrr zzabV()
  {
    return (zzbrr)this.zzcoe.get(this.zzcoe.size() - 1);
  }
  
  private void zzd(zzbrr paramzzbrr)
  {
    if (this.zzcoh != null)
    {
      if ((!paramzzbrr.zzaby()) || (zzacn())) {
        ((zzbru)zzabV()).zza(this.zzcoh, paramzzbrr);
      }
      this.zzcoh = null;
      return;
    }
    if (this.zzcoe.isEmpty())
    {
      this.zzcoi = paramzzbrr;
      return;
    }
    zzbrr localzzbrr = zzabV();
    if ((localzzbrr instanceof zzbro))
    {
      ((zzbro)localzzbrr).zzc(paramzzbrr);
      return;
    }
    throw new IllegalStateException();
  }
  
  public void close()
    throws IOException
  {
    if (!this.zzcoe.isEmpty()) {
      throw new IOException("Incomplete document");
    }
    this.zzcoe.add(zzcog);
  }
  
  public void flush()
    throws IOException
  {}
  
  public zzbtk zza(Number paramNumber)
    throws IOException
  {
    if (paramNumber == null) {
      return zzaca();
    }
    if (!isLenient())
    {
      double d = paramNumber.doubleValue();
      if ((Double.isNaN(d)) || (Double.isInfinite(d)))
      {
        paramNumber = String.valueOf(paramNumber);
        throw new IllegalArgumentException(String.valueOf(paramNumber).length() + 33 + "JSON forbids NaN and infinities: " + paramNumber);
      }
    }
    zzd(new zzbrx(paramNumber));
    return this;
  }
  
  public zzbtk zzaU(long paramLong)
    throws IOException
  {
    zzd(new zzbrx(Long.valueOf(paramLong)));
    return this;
  }
  
  public zzbrr zzabU()
  {
    if (!this.zzcoe.isEmpty())
    {
      String str = String.valueOf(this.zzcoe);
      throw new IllegalStateException(String.valueOf(str).length() + 34 + "Expected one JSON element but was " + str);
    }
    return this.zzcoi;
  }
  
  public zzbtk zzabW()
    throws IOException
  {
    zzbro localzzbro = new zzbro();
    zzd(localzzbro);
    this.zzcoe.add(localzzbro);
    return this;
  }
  
  public zzbtk zzabX()
    throws IOException
  {
    if ((this.zzcoe.isEmpty()) || (this.zzcoh != null)) {
      throw new IllegalStateException();
    }
    if ((zzabV() instanceof zzbro))
    {
      this.zzcoe.remove(this.zzcoe.size() - 1);
      return this;
    }
    throw new IllegalStateException();
  }
  
  public zzbtk zzabY()
    throws IOException
  {
    zzbru localzzbru = new zzbru();
    zzd(localzzbru);
    this.zzcoe.add(localzzbru);
    return this;
  }
  
  public zzbtk zzabZ()
    throws IOException
  {
    if ((this.zzcoe.isEmpty()) || (this.zzcoh != null)) {
      throw new IllegalStateException();
    }
    if ((zzabV() instanceof zzbru))
    {
      this.zzcoe.remove(this.zzcoe.size() - 1);
      return this;
    }
    throw new IllegalStateException();
  }
  
  public zzbtk zzaca()
    throws IOException
  {
    zzd(zzbrt.zzcmL);
    return this;
  }
  
  public zzbtk zzbg(boolean paramBoolean)
    throws IOException
  {
    zzd(new zzbrx(Boolean.valueOf(paramBoolean)));
    return this;
  }
  
  public zzbtk zzjW(String paramString)
    throws IOException
  {
    if ((this.zzcoe.isEmpty()) || (this.zzcoh != null)) {
      throw new IllegalStateException();
    }
    if ((zzabV() instanceof zzbru))
    {
      this.zzcoh = paramString;
      return this;
    }
    throw new IllegalStateException();
  }
  
  public zzbtk zzjX(String paramString)
    throws IOException
  {
    if (paramString == null) {
      return zzaca();
    }
    zzd(new zzbrx(paramString));
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbsz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */