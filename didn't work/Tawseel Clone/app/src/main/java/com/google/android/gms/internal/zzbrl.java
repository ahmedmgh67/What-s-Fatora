package com.google.android.gms.internal;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class zzbrl
{
  final zzbry zzcmA = new zzbry() {};
  private final ThreadLocal<Map<zzbth<?>, zza<?>>> zzcmr = new ThreadLocal();
  private final Map<zzbth<?>, zzbsd<?>> zzcms = Collections.synchronizedMap(new HashMap());
  private final List<zzbse> zzcmt;
  private final zzbsl zzcmu;
  private final boolean zzcmv;
  private final boolean zzcmw;
  private final boolean zzcmx;
  private final boolean zzcmy;
  final zzbrp zzcmz = new zzbrp() {};
  
  public zzbrl()
  {
    this(zzbsm.zzcnn, zzbrj.zzcml, Collections.emptyMap(), false, false, false, true, false, false, zzbsb.zzcmO, Collections.emptyList());
  }
  
  zzbrl(zzbsm paramzzbsm, zzbrk paramzzbrk, Map<Type, zzbrn<?>> paramMap, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, boolean paramBoolean6, zzbsb paramzzbsb, List<zzbse> paramList)
  {
    this.zzcmu = new zzbsl(paramMap);
    this.zzcmv = paramBoolean1;
    this.zzcmx = paramBoolean3;
    this.zzcmw = paramBoolean4;
    this.zzcmy = paramBoolean5;
    paramMap = new ArrayList();
    paramMap.add(zzbtg.zzcpq);
    paramMap.add(zzbtb.zzcnX);
    paramMap.add(paramzzbsm);
    paramMap.addAll(paramList);
    paramMap.add(zzbtg.zzcoX);
    paramMap.add(zzbtg.zzcoM);
    paramMap.add(zzbtg.zzcoG);
    paramMap.add(zzbtg.zzcoI);
    paramMap.add(zzbtg.zzcoK);
    paramMap.add(zzbtg.zza(Long.TYPE, Long.class, zza(paramzzbsb)));
    paramMap.add(zzbtg.zza(Double.TYPE, Double.class, zzbe(paramBoolean6)));
    paramMap.add(zzbtg.zza(Float.TYPE, Float.class, zzbf(paramBoolean6)));
    paramMap.add(zzbtg.zzcoR);
    paramMap.add(zzbtg.zzcoT);
    paramMap.add(zzbtg.zzcoZ);
    paramMap.add(zzbtg.zzcpb);
    paramMap.add(zzbtg.zza(BigDecimal.class, zzbtg.zzcoV));
    paramMap.add(zzbtg.zza(BigInteger.class, zzbtg.zzcoW));
    paramMap.add(zzbtg.zzcpd);
    paramMap.add(zzbtg.zzcpf);
    paramMap.add(zzbtg.zzcpj);
    paramMap.add(zzbtg.zzcpo);
    paramMap.add(zzbtg.zzcph);
    paramMap.add(zzbtg.zzcoD);
    paramMap.add(zzbsw.zzcnX);
    paramMap.add(zzbtg.zzcpm);
    paramMap.add(zzbte.zzcnX);
    paramMap.add(zzbtd.zzcnX);
    paramMap.add(zzbtg.zzcpk);
    paramMap.add(zzbsu.zzcnX);
    paramMap.add(zzbtg.zzcoB);
    paramMap.add(new zzbsv(this.zzcmu));
    paramMap.add(new zzbta(this.zzcmu, paramBoolean2));
    paramMap.add(new zzbsx(this.zzcmu));
    paramMap.add(zzbtg.zzcpr);
    paramMap.add(new zzbtc(this.zzcmu, paramzzbrk, paramzzbsm));
    this.zzcmt = Collections.unmodifiableList(paramMap);
  }
  
  private zzbsd<Number> zza(zzbsb paramzzbsb)
  {
    if (paramzzbsb == zzbsb.zzcmO) {
      return zzbtg.zzcoN;
    }
    new zzbsd()
    {
      public void zza(zzbtk paramAnonymouszzbtk, Number paramAnonymousNumber)
        throws IOException
      {
        if (paramAnonymousNumber == null)
        {
          paramAnonymouszzbtk.zzaca();
          return;
        }
        paramAnonymouszzbtk.zzjX(paramAnonymousNumber.toString());
      }
      
      public Number zzg(zzbti paramAnonymouszzbti)
        throws IOException
      {
        if (paramAnonymouszzbti.zzabQ() == zzbtj.zzcqa)
        {
          paramAnonymouszzbti.nextNull();
          return null;
        }
        return Long.valueOf(paramAnonymouszzbti.nextLong());
      }
    };
  }
  
  private static void zza(Object paramObject, zzbti paramzzbti)
  {
    if (paramObject != null) {
      try
      {
        if (paramzzbti.zzabQ() != zzbtj.zzcqb) {
          throw new zzbrs("JSON document was not fully consumed.");
        }
      }
      catch (zzbtl paramObject)
      {
        throw new zzbsa((Throwable)paramObject);
      }
      catch (IOException paramObject)
      {
        throw new zzbrs((Throwable)paramObject);
      }
    }
  }
  
  private zzbsd<Number> zzbe(boolean paramBoolean)
  {
    if (paramBoolean) {
      return zzbtg.zzcoP;
    }
    new zzbsd()
    {
      public void zza(zzbtk paramAnonymouszzbtk, Number paramAnonymousNumber)
        throws IOException
      {
        if (paramAnonymousNumber == null)
        {
          paramAnonymouszzbtk.zzaca();
          return;
        }
        double d = paramAnonymousNumber.doubleValue();
        zzbrl.zza(zzbrl.this, d);
        paramAnonymouszzbtk.zza(paramAnonymousNumber);
      }
      
      public Double zze(zzbti paramAnonymouszzbti)
        throws IOException
      {
        if (paramAnonymouszzbti.zzabQ() == zzbtj.zzcqa)
        {
          paramAnonymouszzbti.nextNull();
          return null;
        }
        return Double.valueOf(paramAnonymouszzbti.nextDouble());
      }
    };
  }
  
  private zzbsd<Number> zzbf(boolean paramBoolean)
  {
    if (paramBoolean) {
      return zzbtg.zzcoO;
    }
    new zzbsd()
    {
      public void zza(zzbtk paramAnonymouszzbtk, Number paramAnonymousNumber)
        throws IOException
      {
        if (paramAnonymousNumber == null)
        {
          paramAnonymouszzbtk.zzaca();
          return;
        }
        float f = paramAnonymousNumber.floatValue();
        zzbrl.zza(zzbrl.this, f);
        paramAnonymouszzbtk.zza(paramAnonymousNumber);
      }
      
      public Float zzf(zzbti paramAnonymouszzbti)
        throws IOException
      {
        if (paramAnonymouszzbti.zzabQ() == zzbtj.zzcqa)
        {
          paramAnonymouszzbti.nextNull();
          return null;
        }
        return Float.valueOf((float)paramAnonymouszzbti.nextDouble());
      }
    };
  }
  
  private void zzm(double paramDouble)
  {
    if ((Double.isNaN(paramDouble)) || (Double.isInfinite(paramDouble))) {
      throw new IllegalArgumentException(168 + paramDouble + " is not a valid double value as per JSON specification. To override this" + " behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
    }
  }
  
  public String toString()
  {
    return "{serializeNulls:" + this.zzcmv + "factories:" + this.zzcmt + ",instanceCreators:" + this.zzcmu + "}";
  }
  
  public <T> zzbsd<T> zza(zzbse paramzzbse, zzbth<T> paramzzbth)
  {
    int i = 0;
    if (!this.zzcmt.contains(paramzzbse)) {
      i = 1;
    }
    Iterator localIterator = this.zzcmt.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (zzbse)localIterator.next();
      if (i == 0)
      {
        if (localObject == paramzzbse) {
          i = 1;
        }
      }
      else
      {
        localObject = ((zzbse)localObject).zza(this, paramzzbth);
        if (localObject != null) {
          return (zzbsd<T>)localObject;
        }
      }
    }
    paramzzbse = String.valueOf(paramzzbth);
    throw new IllegalArgumentException(String.valueOf(paramzzbse).length() + 22 + "GSON cannot serialize " + paramzzbse);
  }
  
  public <T> zzbsd<T> zza(zzbth<T> paramzzbth)
  {
    Object localObject1 = (zzbsd)this.zzcms.get(paramzzbth);
    if (localObject1 != null) {
      return (zzbsd<T>)localObject1;
    }
    Object localObject3 = (Map)this.zzcmr.get();
    int i = 0;
    if (localObject3 == null)
    {
      localObject3 = new HashMap();
      this.zzcmr.set(localObject3);
      i = 1;
    }
    for (;;)
    {
      Object localObject4 = (zza)((Map)localObject3).get(paramzzbth);
      localObject1 = localObject4;
      if (localObject4 != null) {
        break;
      }
      try
      {
        localObject1 = new zza();
        ((Map)localObject3).put(paramzzbth, localObject1);
        Iterator localIterator = this.zzcmt.iterator();
        for (;;)
        {
          if (localIterator.hasNext())
          {
            localObject4 = ((zzbse)localIterator.next()).zza(this, paramzzbth);
            if (localObject4 != null)
            {
              ((zza)localObject1).zza((zzbsd)localObject4);
              this.zzcms.put(paramzzbth, localObject4);
              ((Map)localObject3).remove(paramzzbth);
              localObject1 = localObject4;
              if (i == 0) {
                break;
              }
              this.zzcmr.remove();
              return (zzbsd<T>)localObject4;
            }
          }
        }
        localObject1 = String.valueOf(paramzzbth);
        throw new IllegalArgumentException(String.valueOf(localObject1).length() + 19 + "GSON cannot handle " + (String)localObject1);
      }
      finally
      {
        ((Map)localObject3).remove(paramzzbth);
        if (i != 0) {
          this.zzcmr.remove();
        }
      }
    }
  }
  
  public zzbtk zza(Writer paramWriter)
    throws IOException
  {
    if (this.zzcmx) {
      paramWriter.write(")]}'\n");
    }
    paramWriter = new zzbtk(paramWriter);
    if (this.zzcmy) {
      paramWriter.setIndent("  ");
    }
    paramWriter.zzbj(this.zzcmv);
    return paramWriter;
  }
  
  public <T> T zza(zzbrr paramzzbrr, Class<T> paramClass)
    throws zzbsa
  {
    paramzzbrr = zza(paramzzbrr, paramClass);
    return (T)zzbsr.zzo(paramClass).cast(paramzzbrr);
  }
  
  public <T> T zza(zzbrr paramzzbrr, Type paramType)
    throws zzbsa
  {
    if (paramzzbrr == null) {
      return null;
    }
    return (T)zza(new zzbsy(paramzzbrr), paramType);
  }
  
  /* Error */
  public <T> T zza(zzbti paramzzbti, Type paramType)
    throws zzbrs, zzbsa
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_3
    //   2: aload_1
    //   3: invokevirtual 484	com/google/android/gms/internal/zzbti:isLenient	()Z
    //   6: istore 4
    //   8: aload_1
    //   9: iconst_1
    //   10: invokevirtual 487	com/google/android/gms/internal/zzbti:setLenient	(Z)V
    //   13: aload_1
    //   14: invokevirtual 289	com/google/android/gms/internal/zzbti:zzabQ	()Lcom/google/android/gms/internal/zzbtj;
    //   17: pop
    //   18: iconst_0
    //   19: istore_3
    //   20: aload_0
    //   21: aload_2
    //   22: invokestatic 493	com/google/android/gms/internal/zzbth:zzl	(Ljava/lang/reflect/Type;)Lcom/google/android/gms/internal/zzbth;
    //   25: invokevirtual 495	com/google/android/gms/internal/zzbrl:zza	(Lcom/google/android/gms/internal/zzbth;)Lcom/google/android/gms/internal/zzbsd;
    //   28: aload_1
    //   29: invokevirtual 499	com/google/android/gms/internal/zzbsd:zzb	(Lcom/google/android/gms/internal/zzbti;)Ljava/lang/Object;
    //   32: astore_2
    //   33: aload_1
    //   34: iload 4
    //   36: invokevirtual 487	com/google/android/gms/internal/zzbti:setLenient	(Z)V
    //   39: aload_2
    //   40: areturn
    //   41: astore_2
    //   42: iload_3
    //   43: ifeq +11 -> 54
    //   46: aload_1
    //   47: iload 4
    //   49: invokevirtual 487	com/google/android/gms/internal/zzbti:setLenient	(Z)V
    //   52: aconst_null
    //   53: areturn
    //   54: new 304	com/google/android/gms/internal/zzbsa
    //   57: dup
    //   58: aload_2
    //   59: invokespecial 307	com/google/android/gms/internal/zzbsa:<init>	(Ljava/lang/Throwable;)V
    //   62: athrow
    //   63: astore_2
    //   64: aload_1
    //   65: iload 4
    //   67: invokevirtual 487	com/google/android/gms/internal/zzbti:setLenient	(Z)V
    //   70: aload_2
    //   71: athrow
    //   72: astore_2
    //   73: new 304	com/google/android/gms/internal/zzbsa
    //   76: dup
    //   77: aload_2
    //   78: invokespecial 307	com/google/android/gms/internal/zzbsa:<init>	(Ljava/lang/Throwable;)V
    //   81: athrow
    //   82: astore_2
    //   83: new 304	com/google/android/gms/internal/zzbsa
    //   86: dup
    //   87: aload_2
    //   88: invokespecial 307	com/google/android/gms/internal/zzbsa:<init>	(Ljava/lang/Throwable;)V
    //   91: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	92	0	this	zzbrl
    //   0	92	1	paramzzbti	zzbti
    //   0	92	2	paramType	Type
    //   1	42	3	i	int
    //   6	60	4	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   13	18	41	java/io/EOFException
    //   20	33	41	java/io/EOFException
    //   13	18	63	finally
    //   20	33	63	finally
    //   54	63	63	finally
    //   73	82	63	finally
    //   83	92	63	finally
    //   13	18	72	java/lang/IllegalStateException
    //   20	33	72	java/lang/IllegalStateException
    //   13	18	82	java/io/IOException
    //   20	33	82	java/io/IOException
  }
  
  public <T> T zza(Reader paramReader, Type paramType)
    throws zzbrs, zzbsa
  {
    paramReader = new zzbti(paramReader);
    paramType = zza(paramReader, paramType);
    zza(paramType, paramReader);
    return paramType;
  }
  
  public <T> T zza(String paramString, Type paramType)
    throws zzbsa
  {
    if (paramString == null) {
      return null;
    }
    return (T)zza(new StringReader(paramString), paramType);
  }
  
  public void zza(zzbrr paramzzbrr, zzbtk paramzzbtk)
    throws zzbrs
  {
    boolean bool1 = paramzzbtk.isLenient();
    paramzzbtk.setLenient(true);
    boolean bool2 = paramzzbtk.zzacm();
    paramzzbtk.zzbi(this.zzcmw);
    boolean bool3 = paramzzbtk.zzacn();
    paramzzbtk.zzbj(this.zzcmv);
    try
    {
      zzbss.zzb(paramzzbrr, paramzzbtk);
      return;
    }
    catch (IOException paramzzbrr)
    {
      throw new zzbrs(paramzzbrr);
    }
    finally
    {
      paramzzbtk.setLenient(bool1);
      paramzzbtk.zzbi(bool2);
      paramzzbtk.zzbj(bool3);
    }
  }
  
  public void zza(zzbrr paramzzbrr, Appendable paramAppendable)
    throws zzbrs
  {
    try
    {
      zza(paramzzbrr, zza(zzbss.zza(paramAppendable)));
      return;
    }
    catch (IOException paramzzbrr)
    {
      throw new RuntimeException(paramzzbrr);
    }
  }
  
  public void zza(Object paramObject, Type paramType, zzbtk paramzzbtk)
    throws zzbrs
  {
    paramType = zza(zzbth.zzl(paramType));
    boolean bool1 = paramzzbtk.isLenient();
    paramzzbtk.setLenient(true);
    boolean bool2 = paramzzbtk.zzacm();
    paramzzbtk.zzbi(this.zzcmw);
    boolean bool3 = paramzzbtk.zzacn();
    paramzzbtk.zzbj(this.zzcmv);
    try
    {
      paramType.zza(paramzzbtk, paramObject);
      return;
    }
    catch (IOException paramObject)
    {
      throw new zzbrs((Throwable)paramObject);
    }
    finally
    {
      paramzzbtk.setLenient(bool1);
      paramzzbtk.zzbi(bool2);
      paramzzbtk.zzbj(bool3);
    }
  }
  
  public void zza(Object paramObject, Type paramType, Appendable paramAppendable)
    throws zzbrs
  {
    try
    {
      zza(paramObject, paramType, zza(zzbss.zza(paramAppendable)));
      return;
    }
    catch (IOException paramObject)
    {
      throw new zzbrs((Throwable)paramObject);
    }
  }
  
  public String zzaI(Object paramObject)
  {
    if (paramObject == null) {
      return zzb(zzbrt.zzcmL);
    }
    return zzc(paramObject, paramObject.getClass());
  }
  
  public String zzb(zzbrr paramzzbrr)
  {
    StringWriter localStringWriter = new StringWriter();
    zza(paramzzbrr, localStringWriter);
    return localStringWriter.toString();
  }
  
  public String zzc(Object paramObject, Type paramType)
  {
    StringWriter localStringWriter = new StringWriter();
    zza(paramObject, paramType, localStringWriter);
    return localStringWriter.toString();
  }
  
  public <T> T zzf(String paramString, Class<T> paramClass)
    throws zzbsa
  {
    paramString = zza(paramString, paramClass);
    return (T)zzbsr.zzo(paramClass).cast(paramString);
  }
  
  public <T> zzbsd<T> zzj(Class<T> paramClass)
  {
    return zza(zzbth.zzq(paramClass));
  }
  
  static class zza<T>
    extends zzbsd<T>
  {
    private zzbsd<T> zzcmC;
    
    public void zza(zzbsd<T> paramzzbsd)
    {
      if (this.zzcmC != null) {
        throw new AssertionError();
      }
      this.zzcmC = paramzzbsd;
    }
    
    public void zza(zzbtk paramzzbtk, T paramT)
      throws IOException
    {
      if (this.zzcmC == null) {
        throw new IllegalStateException();
      }
      this.zzcmC.zza(paramzzbtk, paramT);
    }
    
    public T zzb(zzbti paramzzbti)
      throws IOException
    {
      if (this.zzcmC == null) {
        throw new IllegalStateException();
      }
      return (T)this.zzcmC.zzb(paramzzbti);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbrl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */