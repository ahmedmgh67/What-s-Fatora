package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.io.NumberInput;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class StdDateFormat
  extends DateFormat
{
  protected static final String[] ALL_FORMATS = { "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy-MM-dd" };
  protected static final DateFormat DATE_FORMAT_ISO8601;
  protected static final DateFormat DATE_FORMAT_ISO8601_Z;
  protected static final DateFormat DATE_FORMAT_PLAIN;
  protected static final DateFormat DATE_FORMAT_RFC1123;
  public static final String DATE_FORMAT_STR_ISO8601 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
  protected static final String DATE_FORMAT_STR_ISO8601_Z = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
  protected static final String DATE_FORMAT_STR_PLAIN = "yyyy-MM-dd";
  protected static final String DATE_FORMAT_STR_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";
  private static final Locale DEFAULT_LOCALE;
  private static final TimeZone DEFAULT_TIMEZONE = TimeZone.getTimeZone("UTC");
  public static final StdDateFormat instance = new StdDateFormat();
  protected transient DateFormat _formatISO8601;
  protected transient DateFormat _formatISO8601_z;
  protected transient DateFormat _formatPlain;
  protected transient DateFormat _formatRFC1123;
  protected Boolean _lenient;
  protected final Locale _locale;
  protected transient TimeZone _timezone;
  
  static
  {
    DEFAULT_LOCALE = Locale.US;
    DATE_FORMAT_RFC1123 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", DEFAULT_LOCALE);
    DATE_FORMAT_RFC1123.setTimeZone(DEFAULT_TIMEZONE);
    DATE_FORMAT_ISO8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", DEFAULT_LOCALE);
    DATE_FORMAT_ISO8601.setTimeZone(DEFAULT_TIMEZONE);
    DATE_FORMAT_ISO8601_Z = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", DEFAULT_LOCALE);
    DATE_FORMAT_ISO8601_Z.setTimeZone(DEFAULT_TIMEZONE);
    DATE_FORMAT_PLAIN = new SimpleDateFormat("yyyy-MM-dd", DEFAULT_LOCALE);
    DATE_FORMAT_PLAIN.setTimeZone(DEFAULT_TIMEZONE);
  }
  
  public StdDateFormat()
  {
    this._locale = DEFAULT_LOCALE;
  }
  
  @Deprecated
  public StdDateFormat(TimeZone paramTimeZone, Locale paramLocale)
  {
    this._timezone = paramTimeZone;
    this._locale = paramLocale;
  }
  
  protected StdDateFormat(TimeZone paramTimeZone, Locale paramLocale, Boolean paramBoolean)
  {
    this._timezone = paramTimeZone;
    this._locale = paramLocale;
    this._lenient = paramBoolean;
  }
  
  private static final DateFormat _cloneFormat(DateFormat paramDateFormat, String paramString, TimeZone paramTimeZone, Locale paramLocale, Boolean paramBoolean)
  {
    if (!paramLocale.equals(DEFAULT_LOCALE))
    {
      paramString = new SimpleDateFormat(paramString, paramLocale);
      paramDateFormat = paramTimeZone;
      if (paramTimeZone == null) {
        paramDateFormat = DEFAULT_TIMEZONE;
      }
      paramString.setTimeZone(paramDateFormat);
      paramDateFormat = paramString;
    }
    for (;;)
    {
      if (paramBoolean != null) {
        paramDateFormat.setLenient(paramBoolean.booleanValue());
      }
      return paramDateFormat;
      paramString = (DateFormat)paramDateFormat.clone();
      paramDateFormat = paramString;
      if (paramTimeZone != null)
      {
        paramString.setTimeZone(paramTimeZone);
        paramDateFormat = paramString;
      }
    }
  }
  
  public static TimeZone getDefaultTimeZone()
  {
    return DEFAULT_TIMEZONE;
  }
  
  @Deprecated
  public static DateFormat getISO8601Format(TimeZone paramTimeZone)
  {
    return getISO8601Format(paramTimeZone, DEFAULT_LOCALE);
  }
  
  public static DateFormat getISO8601Format(TimeZone paramTimeZone, Locale paramLocale)
  {
    return _cloneFormat(DATE_FORMAT_ISO8601, "yyyy-MM-dd'T'HH:mm:ss.SSSZ", paramTimeZone, paramLocale, null);
  }
  
  @Deprecated
  public static DateFormat getRFC1123Format(TimeZone paramTimeZone)
  {
    return getRFC1123Format(paramTimeZone, DEFAULT_LOCALE);
  }
  
  public static DateFormat getRFC1123Format(TimeZone paramTimeZone, Locale paramLocale)
  {
    return _cloneFormat(DATE_FORMAT_RFC1123, "EEE, dd MMM yyyy HH:mm:ss zzz", paramTimeZone, paramLocale, null);
  }
  
  private static final boolean hasTimeZone(String paramString)
  {
    int i = paramString.length();
    if (i >= 6)
    {
      int j = paramString.charAt(i - 6);
      if ((j == 43) || (j == 45)) {}
      do
      {
        do
        {
          return true;
          j = paramString.charAt(i - 5);
        } while ((j == 43) || (j == 45));
        i = paramString.charAt(i - 3);
      } while ((i == 43) || (i == 45));
    }
    return false;
  }
  
  protected void _clearFormats()
  {
    this._formatRFC1123 = null;
    this._formatISO8601 = null;
    this._formatISO8601_z = null;
    this._formatPlain = null;
  }
  
  public StdDateFormat clone()
  {
    return new StdDateFormat(this._timezone, this._locale, this._lenient);
  }
  
  public boolean equals(Object paramObject)
  {
    return paramObject == this;
  }
  
  public StringBuffer format(Date paramDate, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition)
  {
    if (this._formatISO8601 == null) {
      this._formatISO8601 = _cloneFormat(DATE_FORMAT_ISO8601, "yyyy-MM-dd'T'HH:mm:ss.SSSZ", this._timezone, this._locale, this._lenient);
    }
    return this._formatISO8601.format(paramDate, paramStringBuffer, paramFieldPosition);
  }
  
  public TimeZone getTimeZone()
  {
    return this._timezone;
  }
  
  public int hashCode()
  {
    return System.identityHashCode(this);
  }
  
  public boolean isLenient()
  {
    if (this._lenient == null) {
      return true;
    }
    return this._lenient.booleanValue();
  }
  
  protected boolean looksLikeISO8601(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramString.length() >= 5)
    {
      bool1 = bool2;
      if (Character.isDigit(paramString.charAt(0)))
      {
        bool1 = bool2;
        if (Character.isDigit(paramString.charAt(3)))
        {
          bool1 = bool2;
          if (paramString.charAt(4) == '-') {
            bool1 = true;
          }
        }
      }
    }
    return bool1;
  }
  
  public Date parse(String paramString)
    throws ParseException
  {
    String str1 = paramString.trim();
    ParsePosition localParsePosition = new ParsePosition(0);
    if (looksLikeISO8601(str1)) {
      paramString = parseAsISO8601(str1, localParsePosition, true);
    }
    while (paramString != null)
    {
      return paramString;
      i = str1.length();
      int k;
      do
      {
        do
        {
          j = i - 1;
          if (j < 0) {
            break label92;
          }
          k = str1.charAt(j);
          if (k < 48) {
            break;
          }
          i = j;
        } while (k <= 57);
        if (j > 0) {
          break;
        }
        i = j;
      } while (k == 45);
      label92:
      if ((j < 0) && ((str1.charAt(0) == '-') || (NumberInput.inLongRange(str1, false)))) {
        paramString = new Date(Long.parseLong(str1));
      } else {
        paramString = parseAsRFC1123(str1, localParsePosition);
      }
    }
    paramString = new StringBuilder();
    String[] arrayOfString = ALL_FORMATS;
    int j = arrayOfString.length;
    int i = 0;
    if (i < j)
    {
      String str2 = arrayOfString[i];
      if (paramString.length() > 0) {
        paramString.append("\", \"");
      }
      for (;;)
      {
        paramString.append(str2);
        i += 1;
        break;
        paramString.append('"');
      }
    }
    paramString.append('"');
    throw new ParseException(String.format("Can not parse date \"%s\": not compatible with any of standard forms (%s)", new Object[] { str1, paramString.toString() }), localParsePosition.getErrorIndex());
  }
  
  public Date parse(String paramString, ParsePosition paramParsePosition)
  {
    if (looksLikeISO8601(paramString)) {
      try
      {
        paramString = parseAsISO8601(paramString, paramParsePosition, false);
        return paramString;
      }
      catch (ParseException paramString)
      {
        return null;
      }
    }
    int i = paramString.length();
    int j;
    int k;
    do
    {
      do
      {
        j = i - 1;
        if (j < 0) {
          break label76;
        }
        k = paramString.charAt(j);
        if (k < 48) {
          break;
        }
        i = j;
      } while (k <= 57);
      if (j > 0) {
        break;
      }
      i = j;
    } while (k == 45);
    label76:
    if ((j < 0) && ((paramString.charAt(0) == '-') || (NumberInput.inLongRange(paramString, false)))) {
      return new Date(Long.parseLong(paramString));
    }
    return parseAsRFC1123(paramString, paramParsePosition);
  }
  
  protected Date parseAsISO8601(String paramString, ParsePosition paramParsePosition, boolean paramBoolean)
    throws ParseException
  {
    int i = paramString.length();
    char c = paramString.charAt(i - 1);
    Object localObject4;
    Object localObject3;
    Object localObject2;
    String str;
    if ((i <= 10) && (Character.isDigit(c)))
    {
      localObject4 = this._formatPlain;
      localObject3 = "yyyy-MM-dd";
      localObject1 = localObject4;
      localObject2 = localObject3;
      str = paramString;
      if (localObject4 == null)
      {
        localObject1 = _cloneFormat(DATE_FORMAT_PLAIN, "yyyy-MM-dd", this._timezone, this._locale, this._lenient);
        this._formatPlain = ((DateFormat)localObject1);
        str = paramString;
        localObject2 = localObject3;
      }
    }
    for (;;)
    {
      paramString = ((DateFormat)localObject1).parse(str, paramParsePosition);
      if (paramString != null) {
        return paramString;
      }
      throw new ParseException(String.format("Can not parse date \"%s\": while it seems to fit format '%s', parsing fails (leniency? %s)", new Object[] { str, localObject2, this._lenient }), paramParsePosition.getErrorIndex());
      if (c != 'Z') {
        break;
      }
      localObject1 = this._formatISO8601_z;
      localObject4 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
      localObject3 = localObject1;
      if (localObject1 == null)
      {
        localObject3 = _cloneFormat(DATE_FORMAT_ISO8601_Z, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", this._timezone, this._locale, this._lenient);
        this._formatISO8601_z = ((DateFormat)localObject3);
      }
      localObject1 = localObject3;
      localObject2 = localObject4;
      str = paramString;
      if (paramString.charAt(i - 4) == ':')
      {
        paramString = new StringBuilder(paramString);
        paramString.insert(i - 1, ".000");
        str = paramString.toString();
        localObject1 = localObject3;
        localObject2 = localObject4;
      }
    }
    if (hasTimeZone(paramString))
    {
      int j = paramString.charAt(i - 3);
      if (j == 58)
      {
        paramString = new StringBuilder(paramString);
        paramString.delete(i - 3, i - 2);
        localObject1 = paramString.toString();
        label310:
        j = ((String)localObject1).length();
        i = j - ((String)localObject1).lastIndexOf('T') - 6;
        paramString = (String)localObject1;
        if (i < 12)
        {
          j -= 5;
          paramString = new StringBuilder((String)localObject1);
          switch (i)
          {
          }
        }
      }
      for (;;)
      {
        paramString = paramString.toString();
        localObject1 = this._formatISO8601;
        localObject3 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
        localObject2 = localObject3;
        str = paramString;
        if (this._formatISO8601 != null) {
          break;
        }
        localObject1 = _cloneFormat(DATE_FORMAT_ISO8601, "yyyy-MM-dd'T'HH:mm:ss.SSSZ", this._timezone, this._locale, this._lenient);
        this._formatISO8601 = ((DateFormat)localObject1);
        localObject2 = localObject3;
        str = paramString;
        break;
        if (j != 43)
        {
          localObject1 = paramString;
          if (j != 45) {
            break label310;
          }
        }
        localObject1 = paramString + "00";
        break label310;
        paramString.insert(j, '0');
        continue;
        paramString.insert(j, "00");
        continue;
        paramString.insert(j, "000");
        continue;
        paramString.insert(j, ".000");
        continue;
        paramString.insert(j, "00.000");
        paramString.insert(j, ":00.000");
      }
    }
    Object localObject1 = new StringBuilder(paramString);
    i = i - paramString.lastIndexOf('T') - 1;
    if (i < 12) {
      switch (i)
      {
      default: 
        ((StringBuilder)localObject1).append(".000");
      }
    }
    for (;;)
    {
      ((StringBuilder)localObject1).append('Z');
      paramString = ((StringBuilder)localObject1).toString();
      localObject4 = this._formatISO8601_z;
      localObject3 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
      localObject1 = localObject4;
      localObject2 = localObject3;
      str = paramString;
      if (localObject4 != null) {
        break;
      }
      localObject1 = _cloneFormat(DATE_FORMAT_ISO8601_Z, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", this._timezone, this._locale, this._lenient);
      this._formatISO8601_z = ((DateFormat)localObject1);
      localObject2 = localObject3;
      str = paramString;
      break;
      ((StringBuilder)localObject1).append('0');
      ((StringBuilder)localObject1).append('0');
      ((StringBuilder)localObject1).append('0');
    }
    return paramString;
  }
  
  protected Date parseAsRFC1123(String paramString, ParsePosition paramParsePosition)
  {
    if (this._formatRFC1123 == null) {
      this._formatRFC1123 = _cloneFormat(DATE_FORMAT_RFC1123, "EEE, dd MMM yyyy HH:mm:ss zzz", this._timezone, this._locale, this._lenient);
    }
    return this._formatRFC1123.parse(paramString, paramParsePosition);
  }
  
  public void setLenient(boolean paramBoolean)
  {
    Boolean localBoolean = Boolean.valueOf(paramBoolean);
    if (this._lenient != localBoolean)
    {
      this._lenient = localBoolean;
      _clearFormats();
    }
  }
  
  public void setTimeZone(TimeZone paramTimeZone)
  {
    if (!paramTimeZone.equals(this._timezone))
    {
      _clearFormats();
      this._timezone = paramTimeZone;
    }
  }
  
  public String toString()
  {
    String str2 = "DateFormat " + getClass().getName();
    TimeZone localTimeZone = this._timezone;
    String str1 = str2;
    if (localTimeZone != null) {
      str1 = str2 + " (timezone: " + localTimeZone + ")";
    }
    return str1 + "(locale: " + this._locale + ")";
  }
  
  public StdDateFormat withLocale(Locale paramLocale)
  {
    if (paramLocale.equals(this._locale)) {
      return this;
    }
    return new StdDateFormat(this._timezone, paramLocale, this._lenient);
  }
  
  public StdDateFormat withTimeZone(TimeZone paramTimeZone)
  {
    TimeZone localTimeZone = paramTimeZone;
    if (paramTimeZone == null) {
      localTimeZone = DEFAULT_TIMEZONE;
    }
    if ((localTimeZone == this._timezone) || (localTimeZone.equals(this._timezone))) {
      return this;
    }
    return new StdDateFormat(localTimeZone, this._locale, this._lenient);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\util\StdDateFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */