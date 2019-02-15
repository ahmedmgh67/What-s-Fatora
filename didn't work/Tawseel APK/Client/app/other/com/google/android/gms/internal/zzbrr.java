package com.google.android.gms.internal;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public abstract class zzbrr
{
  public boolean getAsBoolean()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }
  
  public double getAsDouble()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }
  
  public int getAsInt()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }
  
  public long getAsLong()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }
  
  public String toString()
  {
    try
    {
      Object localObject = new StringWriter();
      zzbtk localzzbtk = new zzbtk((Writer)localObject);
      localzzbtk.setLenient(true);
      zzbss.zzb(this, localzzbtk);
      localObject = ((StringWriter)localObject).toString();
      return (String)localObject;
    }
    catch (IOException localIOException)
    {
      throw new AssertionError(localIOException);
    }
  }
  
  public zzbro zzabA()
  {
    if (zzabv()) {
      return (zzbro)this;
    }
    throw new IllegalStateException("This is not a JSON Array.");
  }
  
  public zzbrx zzabB()
  {
    if (zzabx()) {
      return (zzbrx)this;
    }
    throw new IllegalStateException("This is not a JSON Primitive.");
  }
  
  Boolean zzabC()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }
  
  public Number zzabt()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }
  
  public String zzabu()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }
  
  public boolean zzabv()
  {
    return this instanceof zzbro;
  }
  
  public boolean zzabw()
  {
    return this instanceof zzbru;
  }
  
  public boolean zzabx()
  {
    return this instanceof zzbrx;
  }
  
  public boolean zzaby()
  {
    return this instanceof zzbrt;
  }
  
  public zzbru zzabz()
  {
    if (zzabw()) {
      return (zzbru)this;
    }
    String str = String.valueOf(this);
    throw new IllegalStateException(String.valueOf(str).length() + 19 + "Not a JSON Object: " + str);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbrr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */