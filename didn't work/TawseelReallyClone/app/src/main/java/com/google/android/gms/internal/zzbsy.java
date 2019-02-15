package com.google.android.gms.internal;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public final class zzbsy
  extends zzbti
{
  private static final Reader zzcoc = new Reader()
  {
    public void close()
      throws IOException
    {
      throw new AssertionError();
    }
    
    public int read(char[] paramAnonymousArrayOfChar, int paramAnonymousInt1, int paramAnonymousInt2)
      throws IOException
    {
      throw new AssertionError();
    }
  };
  private static final Object zzcod = new Object();
  private final List<Object> zzcoe = new ArrayList();
  
  public zzbsy(zzbrr paramzzbrr)
  {
    super(zzcoc);
    this.zzcoe.add(paramzzbrr);
  }
  
  private void zza(zzbtj paramzzbtj)
    throws IOException
  {
    if (zzabQ() != paramzzbtj)
    {
      paramzzbtj = String.valueOf(paramzzbtj);
      String str = String.valueOf(zzabQ());
      throw new IllegalStateException(String.valueOf(paramzzbtj).length() + 18 + String.valueOf(str).length() + "Expected " + paramzzbtj + " but was " + str);
    }
  }
  
  private Object zzabR()
  {
    return this.zzcoe.get(this.zzcoe.size() - 1);
  }
  
  private Object zzabS()
  {
    return this.zzcoe.remove(this.zzcoe.size() - 1);
  }
  
  public void beginArray()
    throws IOException
  {
    zza(zzbtj.zzcpS);
    zzbro localzzbro = (zzbro)zzabR();
    this.zzcoe.add(localzzbro.iterator());
  }
  
  public void beginObject()
    throws IOException
  {
    zza(zzbtj.zzcpU);
    zzbru localzzbru = (zzbru)zzabR();
    this.zzcoe.add(localzzbru.entrySet().iterator());
  }
  
  public void close()
    throws IOException
  {
    this.zzcoe.clear();
    this.zzcoe.add(zzcod);
  }
  
  public void endArray()
    throws IOException
  {
    zza(zzbtj.zzcpT);
    zzabS();
    zzabS();
  }
  
  public void endObject()
    throws IOException
  {
    zza(zzbtj.zzcpV);
    zzabS();
    zzabS();
  }
  
  public boolean hasNext()
    throws IOException
  {
    zzbtj localzzbtj = zzabQ();
    return (localzzbtj != zzbtj.zzcpV) && (localzzbtj != zzbtj.zzcpT);
  }
  
  public boolean nextBoolean()
    throws IOException
  {
    zza(zzbtj.zzcpZ);
    return ((zzbrx)zzabS()).getAsBoolean();
  }
  
  public double nextDouble()
    throws IOException
  {
    Object localObject = zzabQ();
    if ((localObject != zzbtj.zzcpY) && (localObject != zzbtj.zzcpX))
    {
      String str = String.valueOf(zzbtj.zzcpY);
      localObject = String.valueOf(localObject);
      throw new IllegalStateException(String.valueOf(str).length() + 18 + String.valueOf(localObject).length() + "Expected " + str + " but was " + (String)localObject);
    }
    double d = ((zzbrx)zzabR()).getAsDouble();
    if ((!isLenient()) && ((Double.isNaN(d)) || (Double.isInfinite(d)))) {
      throw new NumberFormatException(57 + "JSON forbids NaN and infinities: " + d);
    }
    zzabS();
    return d;
  }
  
  public int nextInt()
    throws IOException
  {
    Object localObject = zzabQ();
    if ((localObject != zzbtj.zzcpY) && (localObject != zzbtj.zzcpX))
    {
      String str = String.valueOf(zzbtj.zzcpY);
      localObject = String.valueOf(localObject);
      throw new IllegalStateException(String.valueOf(str).length() + 18 + String.valueOf(localObject).length() + "Expected " + str + " but was " + (String)localObject);
    }
    int i = ((zzbrx)zzabR()).getAsInt();
    zzabS();
    return i;
  }
  
  public long nextLong()
    throws IOException
  {
    Object localObject = zzabQ();
    if ((localObject != zzbtj.zzcpY) && (localObject != zzbtj.zzcpX))
    {
      String str = String.valueOf(zzbtj.zzcpY);
      localObject = String.valueOf(localObject);
      throw new IllegalStateException(String.valueOf(str).length() + 18 + String.valueOf(localObject).length() + "Expected " + str + " but was " + (String)localObject);
    }
    long l = ((zzbrx)zzabR()).getAsLong();
    zzabS();
    return l;
  }
  
  public String nextName()
    throws IOException
  {
    zza(zzbtj.zzcpW);
    Map.Entry localEntry = (Map.Entry)((Iterator)zzabR()).next();
    this.zzcoe.add(localEntry.getValue());
    return (String)localEntry.getKey();
  }
  
  public void nextNull()
    throws IOException
  {
    zza(zzbtj.zzcqa);
    zzabS();
  }
  
  public String nextString()
    throws IOException
  {
    Object localObject = zzabQ();
    if ((localObject != zzbtj.zzcpX) && (localObject != zzbtj.zzcpY))
    {
      String str = String.valueOf(zzbtj.zzcpX);
      localObject = String.valueOf(localObject);
      throw new IllegalStateException(String.valueOf(str).length() + 18 + String.valueOf(localObject).length() + "Expected " + str + " but was " + (String)localObject);
    }
    return ((zzbrx)zzabS()).zzabu();
  }
  
  public void skipValue()
    throws IOException
  {
    if (zzabQ() == zzbtj.zzcpW)
    {
      nextName();
      return;
    }
    zzabS();
  }
  
  public String toString()
  {
    return getClass().getSimpleName();
  }
  
  public zzbtj zzabQ()
    throws IOException
  {
    if (this.zzcoe.isEmpty()) {
      return zzbtj.zzcqb;
    }
    Object localObject = zzabR();
    if ((localObject instanceof Iterator))
    {
      boolean bool = this.zzcoe.get(this.zzcoe.size() - 2) instanceof zzbru;
      localObject = (Iterator)localObject;
      if (((Iterator)localObject).hasNext())
      {
        if (bool) {
          return zzbtj.zzcpW;
        }
        this.zzcoe.add(((Iterator)localObject).next());
        return zzabQ();
      }
      if (bool) {
        return zzbtj.zzcpV;
      }
      return zzbtj.zzcpT;
    }
    if ((localObject instanceof zzbru)) {
      return zzbtj.zzcpU;
    }
    if ((localObject instanceof zzbro)) {
      return zzbtj.zzcpS;
    }
    if ((localObject instanceof zzbrx))
    {
      localObject = (zzbrx)localObject;
      if (((zzbrx)localObject).zzabF()) {
        return zzbtj.zzcpX;
      }
      if (((zzbrx)localObject).zzabD()) {
        return zzbtj.zzcpZ;
      }
      if (((zzbrx)localObject).zzabE()) {
        return zzbtj.zzcpY;
      }
      throw new AssertionError();
    }
    if ((localObject instanceof zzbrt)) {
      return zzbtj.zzcqa;
    }
    if (localObject == zzcod) {
      throw new IllegalStateException("JsonReader is closed");
    }
    throw new AssertionError();
  }
  
  public void zzabT()
    throws IOException
  {
    zza(zzbtj.zzcpW);
    Map.Entry localEntry = (Map.Entry)((Iterator)zzabR()).next();
    this.zzcoe.add(localEntry.getValue());
    this.zzcoe.add(new zzbrx((String)localEntry.getKey()));
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbsy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */