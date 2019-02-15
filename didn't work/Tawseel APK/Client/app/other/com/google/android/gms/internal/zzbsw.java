package com.google.android.gms.internal;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public final class zzbsw
  extends zzbsd<Date>
{
  public static final zzbse zzcnX = new zzbse()
  {
    public <T> zzbsd<T> zza(zzbrl paramAnonymouszzbrl, zzbth<T> paramAnonymouszzbth)
    {
      if (paramAnonymouszzbth.zzacb() == Date.class) {
        return new zzbsw();
      }
      return null;
    }
  };
  private final DateFormat zzcmh = DateFormat.getDateTimeInstance(2, 2, Locale.US);
  private final DateFormat zzcmi = DateFormat.getDateTimeInstance(2, 2);
  private final DateFormat zzcmj = zzabP();
  
  private static DateFormat zzabP()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
    localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    return localSimpleDateFormat;
  }
  
  private Date zzjV(String paramString)
  {
    try
    {
      Date localDate1 = this.zzcmi.parse(paramString);
      paramString = localDate1;
    }
    catch (ParseException localParseException1)
    {
      try
      {
        Date localDate2 = this.zzcmh.parse(paramString);
        paramString = localDate2;
      }
      catch (ParseException localParseException2)
      {
        try
        {
          Date localDate3 = this.zzcmj.parse(paramString);
          paramString = localDate3;
        }
        catch (ParseException localParseException3)
        {
          throw new zzbsa(paramString, localParseException3);
        }
      }
    }
    finally {}
    return paramString;
  }
  
  public void zza(zzbtk paramzzbtk, Date paramDate)
    throws IOException
  {
    if (paramDate == null) {}
    for (;;)
    {
      try
      {
        paramzzbtk.zzaca();
        return;
      }
      finally {}
      paramzzbtk.zzjX(this.zzcmh.format(paramDate));
    }
  }
  
  public Date zzk(zzbti paramzzbti)
    throws IOException
  {
    if (paramzzbti.zzabQ() == zzbtj.zzcqa)
    {
      paramzzbti.nextNull();
      return null;
    }
    return zzjV(paramzzbti.nextString());
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbsw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */