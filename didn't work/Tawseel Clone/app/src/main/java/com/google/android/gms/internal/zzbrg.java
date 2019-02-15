package com.google.android.gms.internal;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

final class zzbrg
  implements zzbrq<java.util.Date>, zzbrz<java.util.Date>
{
  private final DateFormat zzcmh;
  private final DateFormat zzcmi;
  private final DateFormat zzcmj;
  
  zzbrg()
  {
    this(DateFormat.getDateTimeInstance(2, 2, Locale.US), DateFormat.getDateTimeInstance(2, 2));
  }
  
  public zzbrg(int paramInt1, int paramInt2)
  {
    this(DateFormat.getDateTimeInstance(paramInt1, paramInt2, Locale.US), DateFormat.getDateTimeInstance(paramInt1, paramInt2));
  }
  
  zzbrg(String paramString)
  {
    this(new SimpleDateFormat(paramString, Locale.US), new SimpleDateFormat(paramString));
  }
  
  zzbrg(DateFormat paramDateFormat1, DateFormat paramDateFormat2)
  {
    this.zzcmh = paramDateFormat1;
    this.zzcmi = paramDateFormat2;
    this.zzcmj = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
    this.zzcmj.setTimeZone(TimeZone.getTimeZone("UTC"));
  }
  
  private java.util.Date zza(zzbrr paramzzbrr)
  {
    java.util.Date localDate2;
    synchronized (this.zzcmi)
    {
      try
      {
        java.util.Date localDate1 = this.zzcmi.parse(paramzzbrr.zzabu());
        return localDate1;
      }
      catch (ParseException localParseException1) {}
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(zzbrg.class.getSimpleName());
    localStringBuilder.append('(').append(this.zzcmi.getClass().getSimpleName()).append(')');
    return localStringBuilder.toString();
  }
  
  public zzbrr zza(java.util.Date paramDate, Type arg2, zzbry paramzzbry)
  {
    synchronized (this.zzcmi)
    {
      paramDate = new zzbrx(this.zzcmh.format(paramDate));
      return paramDate;
    }
  }
  
  public java.util.Date zza(zzbrr paramzzbrr, Type paramType, zzbrp paramzzbrp)
    throws zzbrv
  {
    if (!(paramzzbrr instanceof zzbrx)) {
      throw new zzbrv("The date should be a string value");
    }
    paramzzbrr = zza(paramzzbrr);
    if (paramType == java.util.Date.class) {
      return paramzzbrr;
    }
    if (paramType == Timestamp.class) {
      return new Timestamp(paramzzbrr.getTime());
    }
    if (paramType == java.sql.Date.class) {
      return new java.sql.Date(paramzzbrr.getTime());
    }
    paramzzbrr = String.valueOf(getClass());
    paramType = String.valueOf(paramType);
    throw new IllegalArgumentException(String.valueOf(paramzzbrr).length() + 23 + String.valueOf(paramType).length() + paramzzbrr + " cannot deserialize to " + paramType);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbrg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */