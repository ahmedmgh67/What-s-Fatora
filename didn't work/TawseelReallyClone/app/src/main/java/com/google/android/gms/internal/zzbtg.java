package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.UUID;

public final class zzbtg
{
  public static final zzbsd<Class> zzcoA = new zzbsd()
  {
    public void zza(zzbtk paramAnonymouszzbtk, Class paramAnonymousClass)
      throws IOException
    {
      if (paramAnonymousClass == null)
      {
        paramAnonymouszzbtk.zzaca();
        return;
      }
      paramAnonymouszzbtk = String.valueOf(paramAnonymousClass.getName());
      throw new UnsupportedOperationException(String.valueOf(paramAnonymouszzbtk).length() + 76 + "Attempted to serialize java.lang.Class: " + paramAnonymouszzbtk + ". Forgot to register a type adapter?");
    }
    
    public Class zzo(zzbti paramAnonymouszzbti)
      throws IOException
    {
      if (paramAnonymouszzbti.zzabQ() == zzbtj.zzcqa)
      {
        paramAnonymouszzbti.nextNull();
        return null;
      }
      throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
    }
  };
  public static final zzbse zzcoB = zza(Class.class, zzcoA);
  public static final zzbsd<BitSet> zzcoC = new zzbsd()
  {
    public void zza(zzbtk paramAnonymouszzbtk, BitSet paramAnonymousBitSet)
      throws IOException
    {
      if (paramAnonymousBitSet == null)
      {
        paramAnonymouszzbtk.zzaca();
        return;
      }
      paramAnonymouszzbtk.zzabW();
      int i = 0;
      if (i < paramAnonymousBitSet.length())
      {
        if (paramAnonymousBitSet.get(i)) {}
        for (int j = 1;; j = 0)
        {
          paramAnonymouszzbtk.zzaU(j);
          i += 1;
          break;
        }
      }
      paramAnonymouszzbtk.zzabX();
    }
    
    public BitSet zzx(zzbti paramAnonymouszzbti)
      throws IOException
    {
      if (paramAnonymouszzbti.zzabQ() == zzbtj.zzcqa)
      {
        paramAnonymouszzbti.nextNull();
        return null;
      }
      BitSet localBitSet = new BitSet();
      paramAnonymouszzbti.beginArray();
      Object localObject = paramAnonymouszzbti.zzabQ();
      int i = 0;
      if (localObject != zzbtj.zzcpT)
      {
        boolean bool;
        switch (zzbtg.26.zzcon[localObject.ordinal()])
        {
        default: 
          paramAnonymouszzbti = String.valueOf(localObject);
          throw new zzbsa(String.valueOf(paramAnonymouszzbti).length() + 27 + "Invalid bitset value type: " + paramAnonymouszzbti);
        case 1: 
          if (paramAnonymouszzbti.nextInt() != 0) {
            bool = true;
          }
          break;
        }
        for (;;)
        {
          if (bool) {
            localBitSet.set(i);
          }
          i += 1;
          localObject = paramAnonymouszzbti.zzabQ();
          break;
          bool = false;
          continue;
          bool = paramAnonymouszzbti.nextBoolean();
          continue;
          localObject = paramAnonymouszzbti.nextString();
          try
          {
            int j = Integer.parseInt((String)localObject);
            if (j != 0) {
              bool = true;
            } else {
              bool = false;
            }
          }
          catch (NumberFormatException paramAnonymouszzbti)
          {
            paramAnonymouszzbti = String.valueOf(localObject);
            if (paramAnonymouszzbti.length() == 0) {}
          }
        }
        for (paramAnonymouszzbti = "Error: Expecting: bitset number value (1, 0), Found: ".concat(paramAnonymouszzbti);; paramAnonymouszzbti = new String("Error: Expecting: bitset number value (1, 0), Found: ")) {
          throw new zzbsa(paramAnonymouszzbti);
        }
      }
      paramAnonymouszzbti.endArray();
      return localBitSet;
    }
  };
  public static final zzbse zzcoD = zza(BitSet.class, zzcoC);
  public static final zzbsd<Boolean> zzcoE = new zzbsd()
  {
    public Boolean zzE(zzbti paramAnonymouszzbti)
      throws IOException
    {
      if (paramAnonymouszzbti.zzabQ() == zzbtj.zzcqa)
      {
        paramAnonymouszzbti.nextNull();
        return null;
      }
      if (paramAnonymouszzbti.zzabQ() == zzbtj.zzcpX) {
        return Boolean.valueOf(Boolean.parseBoolean(paramAnonymouszzbti.nextString()));
      }
      return Boolean.valueOf(paramAnonymouszzbti.nextBoolean());
    }
    
    public void zza(zzbtk paramAnonymouszzbtk, Boolean paramAnonymousBoolean)
      throws IOException
    {
      if (paramAnonymousBoolean == null)
      {
        paramAnonymouszzbtk.zzaca();
        return;
      }
      paramAnonymouszzbtk.zzbg(paramAnonymousBoolean.booleanValue());
    }
  };
  public static final zzbsd<Boolean> zzcoF = new zzbsd()
  {
    public Boolean zzE(zzbti paramAnonymouszzbti)
      throws IOException
    {
      if (paramAnonymouszzbti.zzabQ() == zzbtj.zzcqa)
      {
        paramAnonymouszzbti.nextNull();
        return null;
      }
      return Boolean.valueOf(paramAnonymouszzbti.nextString());
    }
    
    public void zza(zzbtk paramAnonymouszzbtk, Boolean paramAnonymousBoolean)
      throws IOException
    {
      if (paramAnonymousBoolean == null) {}
      for (paramAnonymousBoolean = "null";; paramAnonymousBoolean = paramAnonymousBoolean.toString())
      {
        paramAnonymouszzbtk.zzjX(paramAnonymousBoolean);
        return;
      }
    }
  };
  public static final zzbse zzcoG = zza(Boolean.TYPE, Boolean.class, zzcoE);
  public static final zzbsd<Number> zzcoH = new zzbsd()
  {
    public void zza(zzbtk paramAnonymouszzbtk, Number paramAnonymousNumber)
      throws IOException
    {
      paramAnonymouszzbtk.zza(paramAnonymousNumber);
    }
    
    public Number zzg(zzbti paramAnonymouszzbti)
      throws IOException
    {
      if (paramAnonymouszzbti.zzabQ() == zzbtj.zzcqa)
      {
        paramAnonymouszzbti.nextNull();
        return null;
      }
      try
      {
        byte b = (byte)paramAnonymouszzbti.nextInt();
        return Byte.valueOf(b);
      }
      catch (NumberFormatException paramAnonymouszzbti)
      {
        throw new zzbsa(paramAnonymouszzbti);
      }
    }
  };
  public static final zzbse zzcoI = zza(Byte.TYPE, Byte.class, zzcoH);
  public static final zzbsd<Number> zzcoJ = new zzbsd()
  {
    public void zza(zzbtk paramAnonymouszzbtk, Number paramAnonymousNumber)
      throws IOException
    {
      paramAnonymouszzbtk.zza(paramAnonymousNumber);
    }
    
    public Number zzg(zzbti paramAnonymouszzbti)
      throws IOException
    {
      if (paramAnonymouszzbti.zzabQ() == zzbtj.zzcqa)
      {
        paramAnonymouszzbti.nextNull();
        return null;
      }
      try
      {
        short s = (short)paramAnonymouszzbti.nextInt();
        return Short.valueOf(s);
      }
      catch (NumberFormatException paramAnonymouszzbti)
      {
        throw new zzbsa(paramAnonymouszzbti);
      }
    }
  };
  public static final zzbse zzcoK = zza(Short.TYPE, Short.class, zzcoJ);
  public static final zzbsd<Number> zzcoL = new zzbsd()
  {
    public void zza(zzbtk paramAnonymouszzbtk, Number paramAnonymousNumber)
      throws IOException
    {
      paramAnonymouszzbtk.zza(paramAnonymousNumber);
    }
    
    public Number zzg(zzbti paramAnonymouszzbti)
      throws IOException
    {
      if (paramAnonymouszzbti.zzabQ() == zzbtj.zzcqa)
      {
        paramAnonymouszzbti.nextNull();
        return null;
      }
      try
      {
        int i = paramAnonymouszzbti.nextInt();
        return Integer.valueOf(i);
      }
      catch (NumberFormatException paramAnonymouszzbti)
      {
        throw new zzbsa(paramAnonymouszzbti);
      }
    }
  };
  public static final zzbse zzcoM = zza(Integer.TYPE, Integer.class, zzcoL);
  public static final zzbsd<Number> zzcoN = new zzbsd()
  {
    public void zza(zzbtk paramAnonymouszzbtk, Number paramAnonymousNumber)
      throws IOException
    {
      paramAnonymouszzbtk.zza(paramAnonymousNumber);
    }
    
    public Number zzg(zzbti paramAnonymouszzbti)
      throws IOException
    {
      if (paramAnonymouszzbti.zzabQ() == zzbtj.zzcqa)
      {
        paramAnonymouszzbti.nextNull();
        return null;
      }
      try
      {
        long l = paramAnonymouszzbti.nextLong();
        return Long.valueOf(l);
      }
      catch (NumberFormatException paramAnonymouszzbti)
      {
        throw new zzbsa(paramAnonymouszzbti);
      }
    }
  };
  public static final zzbsd<Number> zzcoO = new zzbsd()
  {
    public void zza(zzbtk paramAnonymouszzbtk, Number paramAnonymousNumber)
      throws IOException
    {
      paramAnonymouszzbtk.zza(paramAnonymousNumber);
    }
    
    public Number zzg(zzbti paramAnonymouszzbti)
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
  public static final zzbsd<Number> zzcoP = new zzbsd()
  {
    public void zza(zzbtk paramAnonymouszzbtk, Number paramAnonymousNumber)
      throws IOException
    {
      paramAnonymouszzbtk.zza(paramAnonymousNumber);
    }
    
    public Number zzg(zzbti paramAnonymouszzbti)
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
  public static final zzbsd<Number> zzcoQ = new zzbsd()
  {
    public void zza(zzbtk paramAnonymouszzbtk, Number paramAnonymousNumber)
      throws IOException
    {
      paramAnonymouszzbtk.zza(paramAnonymousNumber);
    }
    
    public Number zzg(zzbti paramAnonymouszzbti)
      throws IOException
    {
      zzbtj localzzbtj = paramAnonymouszzbti.zzabQ();
      switch (zzbtg.26.zzcon[localzzbtj.ordinal()])
      {
      case 2: 
      case 3: 
      default: 
        paramAnonymouszzbti = String.valueOf(localzzbtj);
        throw new zzbsa(String.valueOf(paramAnonymouszzbti).length() + 23 + "Expecting number, got: " + paramAnonymouszzbti);
      case 4: 
        paramAnonymouszzbti.nextNull();
        return null;
      }
      return new zzbso(paramAnonymouszzbti.nextString());
    }
  };
  public static final zzbse zzcoR = zza(Number.class, zzcoQ);
  public static final zzbsd<Character> zzcoS = new zzbsd()
  {
    public void zza(zzbtk paramAnonymouszzbtk, Character paramAnonymousCharacter)
      throws IOException
    {
      if (paramAnonymousCharacter == null) {}
      for (paramAnonymousCharacter = null;; paramAnonymousCharacter = String.valueOf(paramAnonymousCharacter))
      {
        paramAnonymouszzbtk.zzjX(paramAnonymousCharacter);
        return;
      }
    }
    
    public Character zzp(zzbti paramAnonymouszzbti)
      throws IOException
    {
      if (paramAnonymouszzbti.zzabQ() == zzbtj.zzcqa)
      {
        paramAnonymouszzbti.nextNull();
        return null;
      }
      paramAnonymouszzbti = paramAnonymouszzbti.nextString();
      if (paramAnonymouszzbti.length() != 1)
      {
        paramAnonymouszzbti = String.valueOf(paramAnonymouszzbti);
        if (paramAnonymouszzbti.length() != 0) {}
        for (paramAnonymouszzbti = "Expecting character, got: ".concat(paramAnonymouszzbti);; paramAnonymouszzbti = new String("Expecting character, got: ")) {
          throw new zzbsa(paramAnonymouszzbti);
        }
      }
      return Character.valueOf(paramAnonymouszzbti.charAt(0));
    }
  };
  public static final zzbse zzcoT = zza(Character.TYPE, Character.class, zzcoS);
  public static final zzbsd<String> zzcoU = new zzbsd()
  {
    public void zza(zzbtk paramAnonymouszzbtk, String paramAnonymousString)
      throws IOException
    {
      paramAnonymouszzbtk.zzjX(paramAnonymousString);
    }
    
    public String zzq(zzbti paramAnonymouszzbti)
      throws IOException
    {
      zzbtj localzzbtj = paramAnonymouszzbti.zzabQ();
      if (localzzbtj == zzbtj.zzcqa)
      {
        paramAnonymouszzbti.nextNull();
        return null;
      }
      if (localzzbtj == zzbtj.zzcpZ) {
        return Boolean.toString(paramAnonymouszzbti.nextBoolean());
      }
      return paramAnonymouszzbti.nextString();
    }
  };
  public static final zzbsd<BigDecimal> zzcoV = new zzbsd()
  {
    public void zza(zzbtk paramAnonymouszzbtk, BigDecimal paramAnonymousBigDecimal)
      throws IOException
    {
      paramAnonymouszzbtk.zza(paramAnonymousBigDecimal);
    }
    
    public BigDecimal zzr(zzbti paramAnonymouszzbti)
      throws IOException
    {
      if (paramAnonymouszzbti.zzabQ() == zzbtj.zzcqa)
      {
        paramAnonymouszzbti.nextNull();
        return null;
      }
      try
      {
        paramAnonymouszzbti = new BigDecimal(paramAnonymouszzbti.nextString());
        return paramAnonymouszzbti;
      }
      catch (NumberFormatException paramAnonymouszzbti)
      {
        throw new zzbsa(paramAnonymouszzbti);
      }
    }
  };
  public static final zzbsd<BigInteger> zzcoW = new zzbsd()
  {
    public void zza(zzbtk paramAnonymouszzbtk, BigInteger paramAnonymousBigInteger)
      throws IOException
    {
      paramAnonymouszzbtk.zza(paramAnonymousBigInteger);
    }
    
    public BigInteger zzs(zzbti paramAnonymouszzbti)
      throws IOException
    {
      if (paramAnonymouszzbti.zzabQ() == zzbtj.zzcqa)
      {
        paramAnonymouszzbti.nextNull();
        return null;
      }
      try
      {
        paramAnonymouszzbti = new BigInteger(paramAnonymouszzbti.nextString());
        return paramAnonymouszzbti;
      }
      catch (NumberFormatException paramAnonymouszzbti)
      {
        throw new zzbsa(paramAnonymouszzbti);
      }
    }
  };
  public static final zzbse zzcoX = zza(String.class, zzcoU);
  public static final zzbsd<StringBuilder> zzcoY = new zzbsd()
  {
    public void zza(zzbtk paramAnonymouszzbtk, StringBuilder paramAnonymousStringBuilder)
      throws IOException
    {
      if (paramAnonymousStringBuilder == null) {}
      for (paramAnonymousStringBuilder = null;; paramAnonymousStringBuilder = paramAnonymousStringBuilder.toString())
      {
        paramAnonymouszzbtk.zzjX(paramAnonymousStringBuilder);
        return;
      }
    }
    
    public StringBuilder zzt(zzbti paramAnonymouszzbti)
      throws IOException
    {
      if (paramAnonymouszzbti.zzabQ() == zzbtj.zzcqa)
      {
        paramAnonymouszzbti.nextNull();
        return null;
      }
      return new StringBuilder(paramAnonymouszzbti.nextString());
    }
  };
  public static final zzbse zzcoZ = zza(StringBuilder.class, zzcoY);
  public static final zzbsd<StringBuffer> zzcpa = new zzbsd()
  {
    public void zza(zzbtk paramAnonymouszzbtk, StringBuffer paramAnonymousStringBuffer)
      throws IOException
    {
      if (paramAnonymousStringBuffer == null) {}
      for (paramAnonymousStringBuffer = null;; paramAnonymousStringBuffer = paramAnonymousStringBuffer.toString())
      {
        paramAnonymouszzbtk.zzjX(paramAnonymousStringBuffer);
        return;
      }
    }
    
    public StringBuffer zzu(zzbti paramAnonymouszzbti)
      throws IOException
    {
      if (paramAnonymouszzbti.zzabQ() == zzbtj.zzcqa)
      {
        paramAnonymouszzbti.nextNull();
        return null;
      }
      return new StringBuffer(paramAnonymouszzbti.nextString());
    }
  };
  public static final zzbse zzcpb = zza(StringBuffer.class, zzcpa);
  public static final zzbsd<URL> zzcpc = new zzbsd()
  {
    public void zza(zzbtk paramAnonymouszzbtk, URL paramAnonymousURL)
      throws IOException
    {
      if (paramAnonymousURL == null) {}
      for (paramAnonymousURL = null;; paramAnonymousURL = paramAnonymousURL.toExternalForm())
      {
        paramAnonymouszzbtk.zzjX(paramAnonymousURL);
        return;
      }
    }
    
    public URL zzv(zzbti paramAnonymouszzbti)
      throws IOException
    {
      if (paramAnonymouszzbti.zzabQ() == zzbtj.zzcqa) {
        paramAnonymouszzbti.nextNull();
      }
      do
      {
        return null;
        paramAnonymouszzbti = paramAnonymouszzbti.nextString();
      } while ("null".equals(paramAnonymouszzbti));
      return new URL(paramAnonymouszzbti);
    }
  };
  public static final zzbse zzcpd = zza(URL.class, zzcpc);
  public static final zzbsd<URI> zzcpe = new zzbsd()
  {
    public void zza(zzbtk paramAnonymouszzbtk, URI paramAnonymousURI)
      throws IOException
    {
      if (paramAnonymousURI == null) {}
      for (paramAnonymousURI = null;; paramAnonymousURI = paramAnonymousURI.toASCIIString())
      {
        paramAnonymouszzbtk.zzjX(paramAnonymousURI);
        return;
      }
    }
    
    public URI zzw(zzbti paramAnonymouszzbti)
      throws IOException
    {
      if (paramAnonymouszzbti.zzabQ() == zzbtj.zzcqa) {
        paramAnonymouszzbti.nextNull();
      }
      for (;;)
      {
        return null;
        try
        {
          paramAnonymouszzbti = paramAnonymouszzbti.nextString();
          if ("null".equals(paramAnonymouszzbti)) {
            continue;
          }
          paramAnonymouszzbti = new URI(paramAnonymouszzbti);
          return paramAnonymouszzbti;
        }
        catch (URISyntaxException paramAnonymouszzbti)
        {
          throw new zzbrs(paramAnonymouszzbti);
        }
      }
    }
  };
  public static final zzbse zzcpf = zza(URI.class, zzcpe);
  public static final zzbsd<InetAddress> zzcpg = new zzbsd()
  {
    public void zza(zzbtk paramAnonymouszzbtk, InetAddress paramAnonymousInetAddress)
      throws IOException
    {
      if (paramAnonymousInetAddress == null) {}
      for (paramAnonymousInetAddress = null;; paramAnonymousInetAddress = paramAnonymousInetAddress.getHostAddress())
      {
        paramAnonymouszzbtk.zzjX(paramAnonymousInetAddress);
        return;
      }
    }
    
    public InetAddress zzy(zzbti paramAnonymouszzbti)
      throws IOException
    {
      if (paramAnonymouszzbti.zzabQ() == zzbtj.zzcqa)
      {
        paramAnonymouszzbti.nextNull();
        return null;
      }
      return InetAddress.getByName(paramAnonymouszzbti.nextString());
    }
  };
  public static final zzbse zzcph = zzb(InetAddress.class, zzcpg);
  public static final zzbsd<UUID> zzcpi = new zzbsd()
  {
    public void zza(zzbtk paramAnonymouszzbtk, UUID paramAnonymousUUID)
      throws IOException
    {
      if (paramAnonymousUUID == null) {}
      for (paramAnonymousUUID = null;; paramAnonymousUUID = paramAnonymousUUID.toString())
      {
        paramAnonymouszzbtk.zzjX(paramAnonymousUUID);
        return;
      }
    }
    
    public UUID zzz(zzbti paramAnonymouszzbti)
      throws IOException
    {
      if (paramAnonymouszzbti.zzabQ() == zzbtj.zzcqa)
      {
        paramAnonymouszzbti.nextNull();
        return null;
      }
      return UUID.fromString(paramAnonymouszzbti.nextString());
    }
  };
  public static final zzbse zzcpj = zza(UUID.class, zzcpi);
  public static final zzbse zzcpk = new zzbse()
  {
    public <T> zzbsd<T> zza(zzbrl paramAnonymouszzbrl, zzbth<T> paramAnonymouszzbth)
    {
      if (paramAnonymouszzbth.zzacb() != Timestamp.class) {
        return null;
      }
      new zzbsd()
      {
        public Timestamp zzA(zzbti paramAnonymous2zzbti)
          throws IOException
        {
          paramAnonymous2zzbti = (Date)this.zzcps.zzb(paramAnonymous2zzbti);
          if (paramAnonymous2zzbti != null) {
            return new Timestamp(paramAnonymous2zzbti.getTime());
          }
          return null;
        }
        
        public void zza(zzbtk paramAnonymous2zzbtk, Timestamp paramAnonymous2Timestamp)
          throws IOException
        {
          this.zzcps.zza(paramAnonymous2zzbtk, paramAnonymous2Timestamp);
        }
      };
    }
  };
  public static final zzbsd<Calendar> zzcpl = new zzbsd()
  {
    public Calendar zzB(zzbti paramAnonymouszzbti)
      throws IOException
    {
      int j = 0;
      if (paramAnonymouszzbti.zzabQ() == zzbtj.zzcqa)
      {
        paramAnonymouszzbti.nextNull();
        return null;
      }
      paramAnonymouszzbti.beginObject();
      int k = 0;
      int m = 0;
      int n = 0;
      int i1 = 0;
      int i2 = 0;
      while (paramAnonymouszzbti.zzabQ() != zzbtj.zzcpV)
      {
        String str = paramAnonymouszzbti.nextName();
        int i = paramAnonymouszzbti.nextInt();
        if ("year".equals(str)) {
          i2 = i;
        } else if ("month".equals(str)) {
          i1 = i;
        } else if ("dayOfMonth".equals(str)) {
          n = i;
        } else if ("hourOfDay".equals(str)) {
          m = i;
        } else if ("minute".equals(str)) {
          k = i;
        } else if ("second".equals(str)) {
          j = i;
        }
      }
      paramAnonymouszzbti.endObject();
      return new GregorianCalendar(i2, i1, n, m, k, j);
    }
    
    public void zza(zzbtk paramAnonymouszzbtk, Calendar paramAnonymousCalendar)
      throws IOException
    {
      if (paramAnonymousCalendar == null)
      {
        paramAnonymouszzbtk.zzaca();
        return;
      }
      paramAnonymouszzbtk.zzabY();
      paramAnonymouszzbtk.zzjW("year");
      paramAnonymouszzbtk.zzaU(paramAnonymousCalendar.get(1));
      paramAnonymouszzbtk.zzjW("month");
      paramAnonymouszzbtk.zzaU(paramAnonymousCalendar.get(2));
      paramAnonymouszzbtk.zzjW("dayOfMonth");
      paramAnonymouszzbtk.zzaU(paramAnonymousCalendar.get(5));
      paramAnonymouszzbtk.zzjW("hourOfDay");
      paramAnonymouszzbtk.zzaU(paramAnonymousCalendar.get(11));
      paramAnonymouszzbtk.zzjW("minute");
      paramAnonymouszzbtk.zzaU(paramAnonymousCalendar.get(12));
      paramAnonymouszzbtk.zzjW("second");
      paramAnonymouszzbtk.zzaU(paramAnonymousCalendar.get(13));
      paramAnonymouszzbtk.zzabZ();
    }
  };
  public static final zzbse zzcpm = zzb(Calendar.class, GregorianCalendar.class, zzcpl);
  public static final zzbsd<Locale> zzcpn = new zzbsd()
  {
    public Locale zzC(zzbti paramAnonymouszzbti)
      throws IOException
    {
      if (paramAnonymouszzbti.zzabQ() == zzbtj.zzcqa)
      {
        paramAnonymouszzbti.nextNull();
        return null;
      }
      Object localObject = new StringTokenizer(paramAnonymouszzbti.nextString(), "_");
      if (((StringTokenizer)localObject).hasMoreElements()) {}
      for (paramAnonymouszzbti = ((StringTokenizer)localObject).nextToken();; paramAnonymouszzbti = null)
      {
        if (((StringTokenizer)localObject).hasMoreElements()) {}
        for (String str = ((StringTokenizer)localObject).nextToken();; str = null)
        {
          if (((StringTokenizer)localObject).hasMoreElements()) {}
          for (localObject = ((StringTokenizer)localObject).nextToken();; localObject = null)
          {
            if ((str == null) && (localObject == null)) {
              return new Locale(paramAnonymouszzbti);
            }
            if (localObject == null) {
              return new Locale(paramAnonymouszzbti, str);
            }
            return new Locale(paramAnonymouszzbti, str, (String)localObject);
          }
        }
      }
    }
    
    public void zza(zzbtk paramAnonymouszzbtk, Locale paramAnonymousLocale)
      throws IOException
    {
      if (paramAnonymousLocale == null) {}
      for (paramAnonymousLocale = null;; paramAnonymousLocale = paramAnonymousLocale.toString())
      {
        paramAnonymouszzbtk.zzjX(paramAnonymousLocale);
        return;
      }
    }
  };
  public static final zzbse zzcpo = zza(Locale.class, zzcpn);
  public static final zzbsd<zzbrr> zzcpp = new zzbsd()
  {
    public zzbrr zzD(zzbti paramAnonymouszzbti)
      throws IOException
    {
      switch (zzbtg.26.zzcon[paramAnonymouszzbti.zzabQ().ordinal()])
      {
      default: 
        throw new IllegalArgumentException();
      case 3: 
        return new zzbrx(paramAnonymouszzbti.nextString());
      case 1: 
        return new zzbrx(new zzbso(paramAnonymouszzbti.nextString()));
      case 2: 
        return new zzbrx(Boolean.valueOf(paramAnonymouszzbti.nextBoolean()));
      case 4: 
        paramAnonymouszzbti.nextNull();
        return zzbrt.zzcmL;
      case 5: 
        localObject = new zzbro();
        paramAnonymouszzbti.beginArray();
        while (paramAnonymouszzbti.hasNext()) {
          ((zzbro)localObject).zzc((zzbrr)zzb(paramAnonymouszzbti));
        }
        paramAnonymouszzbti.endArray();
        return (zzbrr)localObject;
      }
      Object localObject = new zzbru();
      paramAnonymouszzbti.beginObject();
      while (paramAnonymouszzbti.hasNext()) {
        ((zzbru)localObject).zza(paramAnonymouszzbti.nextName(), (zzbrr)zzb(paramAnonymouszzbti));
      }
      paramAnonymouszzbti.endObject();
      return (zzbrr)localObject;
    }
    
    public void zza(zzbtk paramAnonymouszzbtk, zzbrr paramAnonymouszzbrr)
      throws IOException
    {
      if ((paramAnonymouszzbrr == null) || (paramAnonymouszzbrr.zzaby()))
      {
        paramAnonymouszzbtk.zzaca();
        return;
      }
      if (paramAnonymouszzbrr.zzabx())
      {
        paramAnonymouszzbrr = paramAnonymouszzbrr.zzabB();
        if (paramAnonymouszzbrr.zzabE())
        {
          paramAnonymouszzbtk.zza(paramAnonymouszzbrr.zzabt());
          return;
        }
        if (paramAnonymouszzbrr.zzabD())
        {
          paramAnonymouszzbtk.zzbg(paramAnonymouszzbrr.getAsBoolean());
          return;
        }
        paramAnonymouszzbtk.zzjX(paramAnonymouszzbrr.zzabu());
        return;
      }
      if (paramAnonymouszzbrr.zzabv())
      {
        paramAnonymouszzbtk.zzabW();
        paramAnonymouszzbrr = paramAnonymouszzbrr.zzabA().iterator();
        while (paramAnonymouszzbrr.hasNext()) {
          zza(paramAnonymouszzbtk, (zzbrr)paramAnonymouszzbrr.next());
        }
        paramAnonymouszzbtk.zzabX();
        return;
      }
      if (paramAnonymouszzbrr.zzabw())
      {
        paramAnonymouszzbtk.zzabY();
        paramAnonymouszzbrr = paramAnonymouszzbrr.zzabz().entrySet().iterator();
        while (paramAnonymouszzbrr.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)paramAnonymouszzbrr.next();
          paramAnonymouszzbtk.zzjW((String)localEntry.getKey());
          zza(paramAnonymouszzbtk, (zzbrr)localEntry.getValue());
        }
        paramAnonymouszzbtk.zzabZ();
        return;
      }
      paramAnonymouszzbtk = String.valueOf(paramAnonymouszzbrr.getClass());
      throw new IllegalArgumentException(String.valueOf(paramAnonymouszzbtk).length() + 15 + "Couldn't write " + paramAnonymouszzbtk);
    }
  };
  public static final zzbse zzcpq = zzb(zzbrr.class, zzcpp);
  public static final zzbse zzcpr = new zzbse()
  {
    public <T> zzbsd<T> zza(zzbrl paramAnonymouszzbrl, zzbth<T> paramAnonymouszzbth)
    {
      paramAnonymouszzbth = paramAnonymouszzbth.zzacb();
      if ((!Enum.class.isAssignableFrom(paramAnonymouszzbth)) || (paramAnonymouszzbth == Enum.class)) {
        return null;
      }
      paramAnonymouszzbrl = paramAnonymouszzbth;
      if (!paramAnonymouszzbth.isEnum()) {
        paramAnonymouszzbrl = paramAnonymouszzbth.getSuperclass();
      }
      return new zzbtg.zza(paramAnonymouszzbrl);
    }
  };
  
  public static <TT> zzbse zza(zzbth<TT> paramzzbth, final zzbsd<TT> paramzzbsd)
  {
    new zzbse()
    {
      public <T> zzbsd<T> zza(zzbrl paramAnonymouszzbrl, zzbth<T> paramAnonymouszzbth)
      {
        if (paramAnonymouszzbth.equals(this.zzcnw)) {
          return paramzzbsd;
        }
        return null;
      }
    };
  }
  
  public static <TT> zzbse zza(Class<TT> paramClass, final zzbsd<TT> paramzzbsd)
  {
    new zzbse()
    {
      public String toString()
      {
        String str1 = String.valueOf(this.zzcpv.getName());
        String str2 = String.valueOf(paramzzbsd);
        return String.valueOf(str1).length() + 23 + String.valueOf(str2).length() + "Factory[type=" + str1 + ",adapter=" + str2 + "]";
      }
      
      public <T> zzbsd<T> zza(zzbrl paramAnonymouszzbrl, zzbth<T> paramAnonymouszzbth)
      {
        if (paramAnonymouszzbth.zzacb() == this.zzcpv) {
          return paramzzbsd;
        }
        return null;
      }
    };
  }
  
  public static <TT> zzbse zza(Class<TT> paramClass1, final Class<TT> paramClass2, final zzbsd<? super TT> paramzzbsd)
  {
    new zzbse()
    {
      public String toString()
      {
        String str1 = String.valueOf(paramClass2.getName());
        String str2 = String.valueOf(this.zzcpw.getName());
        String str3 = String.valueOf(paramzzbsd);
        return String.valueOf(str1).length() + 24 + String.valueOf(str2).length() + String.valueOf(str3).length() + "Factory[type=" + str1 + "+" + str2 + ",adapter=" + str3 + "]";
      }
      
      public <T> zzbsd<T> zza(zzbrl paramAnonymouszzbrl, zzbth<T> paramAnonymouszzbth)
      {
        paramAnonymouszzbrl = paramAnonymouszzbth.zzacb();
        if ((paramAnonymouszzbrl == this.zzcpw) || (paramAnonymouszzbrl == paramClass2)) {
          return paramzzbsd;
        }
        return null;
      }
    };
  }
  
  public static <TT> zzbse zzb(Class<TT> paramClass, final zzbsd<TT> paramzzbsd)
  {
    new zzbse()
    {
      public String toString()
      {
        String str1 = String.valueOf(this.zzcpA.getName());
        String str2 = String.valueOf(paramzzbsd);
        return String.valueOf(str1).length() + 32 + String.valueOf(str2).length() + "Factory[typeHierarchy=" + str1 + ",adapter=" + str2 + "]";
      }
      
      public <T> zzbsd<T> zza(zzbrl paramAnonymouszzbrl, zzbth<T> paramAnonymouszzbth)
      {
        if (this.zzcpA.isAssignableFrom(paramAnonymouszzbth.zzacb())) {
          return paramzzbsd;
        }
        return null;
      }
    };
  }
  
  public static <TT> zzbse zzb(Class<TT> paramClass, final Class<? extends TT> paramClass1, final zzbsd<? super TT> paramzzbsd)
  {
    new zzbse()
    {
      public String toString()
      {
        String str1 = String.valueOf(this.zzcpy.getName());
        String str2 = String.valueOf(paramClass1.getName());
        String str3 = String.valueOf(paramzzbsd);
        return String.valueOf(str1).length() + 24 + String.valueOf(str2).length() + String.valueOf(str3).length() + "Factory[type=" + str1 + "+" + str2 + ",adapter=" + str3 + "]";
      }
      
      public <T> zzbsd<T> zza(zzbrl paramAnonymouszzbrl, zzbth<T> paramAnonymouszzbth)
      {
        paramAnonymouszzbrl = paramAnonymouszzbth.zzacb();
        if ((paramAnonymouszzbrl == this.zzcpy) || (paramAnonymouszzbrl == paramClass1)) {
          return paramzzbsd;
        }
        return null;
      }
    };
  }
  
  private static final class zza<T extends Enum<T>>
    extends zzbsd<T>
  {
    private final Map<String, T> zzcpB = new HashMap();
    private final Map<T, String> zzcpC = new HashMap();
    
    public zza(Class<T> paramClass)
    {
      try
      {
        Enum[] arrayOfEnum = (Enum[])paramClass.getEnumConstants();
        int k = arrayOfEnum.length;
        int i = 0;
        while (i < k)
        {
          Enum localEnum = arrayOfEnum[i];
          Object localObject1 = localEnum.name();
          Object localObject2 = (zzbsg)paramClass.getField((String)localObject1).getAnnotation(zzbsg.class);
          if (localObject2 != null)
          {
            String str = ((zzbsg)localObject2).value();
            localObject2 = ((zzbsg)localObject2).zzabH();
            int m = localObject2.length;
            int j = 0;
            for (;;)
            {
              localObject1 = str;
              if (j >= m) {
                break;
              }
              localObject1 = localObject2[j];
              this.zzcpB.put(localObject1, localEnum);
              j += 1;
            }
          }
          this.zzcpB.put(localObject1, localEnum);
          this.zzcpC.put(localEnum, localObject1);
          i += 1;
        }
        return;
      }
      catch (NoSuchFieldException paramClass)
      {
        throw new AssertionError();
      }
    }
    
    public T zzF(zzbti paramzzbti)
      throws IOException
    {
      if (paramzzbti.zzabQ() == zzbtj.zzcqa)
      {
        paramzzbti.nextNull();
        return null;
      }
      return (Enum)this.zzcpB.get(paramzzbti.nextString());
    }
    
    public void zza(zzbtk paramzzbtk, T paramT)
      throws IOException
    {
      if (paramT == null) {}
      for (paramT = null;; paramT = (String)this.zzcpC.get(paramT))
      {
        paramzzbtk.zzjX(paramT);
        return;
      }
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbtg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */