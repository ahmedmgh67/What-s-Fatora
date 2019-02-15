package com.google.api.client.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DateTime
  implements Serializable
{
  private static final TimeZone GMT = TimeZone.getTimeZone("GMT");
  private static final Pattern RFC3339_PATTERN = Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{2})([Tt](\\d{2}):(\\d{2}):(\\d{2})(\\.\\d+)?)?([Zz]|([+-])(\\d{2}):(\\d{2}))?");
  private static final long serialVersionUID = 1L;
  private final boolean dateOnly;
  private final int tzShift;
  private final long value;
  
  public DateTime(long paramLong)
  {
    this(false, paramLong, null);
  }
  
  public DateTime(long paramLong, int paramInt)
  {
    this(false, paramLong, Integer.valueOf(paramInt));
  }
  
  public DateTime(String paramString)
  {
    paramString = parseRfc3339(paramString);
    this.dateOnly = paramString.dateOnly;
    this.value = paramString.value;
    this.tzShift = paramString.tzShift;
  }
  
  public DateTime(Date paramDate)
  {
    this(paramDate.getTime());
  }
  
  public DateTime(Date paramDate, TimeZone paramTimeZone) {}
  
  public DateTime(boolean paramBoolean, long paramLong, Integer paramInteger)
  {
    this.dateOnly = paramBoolean;
    this.value = paramLong;
    int i;
    if (paramBoolean) {
      i = 0;
    }
    for (;;)
    {
      this.tzShift = i;
      return;
      if (paramInteger == null) {
        i = TimeZone.getDefault().getOffset(paramLong) / 60000;
      } else {
        i = paramInteger.intValue();
      }
    }
  }
  
  private static void appendInt(StringBuilder paramStringBuilder, int paramInt1, int paramInt2)
  {
    int i = paramInt1;
    if (paramInt1 < 0)
    {
      paramStringBuilder.append('-');
      i = -paramInt1;
    }
    paramInt1 = i;
    while (paramInt1 > 0)
    {
      paramInt1 /= 10;
      paramInt2 -= 1;
    }
    paramInt1 = 0;
    while (paramInt1 < paramInt2)
    {
      paramStringBuilder.append('0');
      paramInt1 += 1;
    }
    if (i != 0) {
      paramStringBuilder.append(i);
    }
  }
  
  public static DateTime parseRfc3339(String paramString)
    throws NumberFormatException
  {
    Matcher localMatcher = RFC3339_PATTERN.matcher(paramString);
    if (!localMatcher.matches()) {
      throw new NumberFormatException("Invalid date/time format: " + paramString);
    }
    int i6 = Integer.parseInt(localMatcher.group(1));
    int i7 = Integer.parseInt(localMatcher.group(2));
    int i8 = Integer.parseInt(localMatcher.group(3));
    int j;
    String str;
    if (localMatcher.group(4) != null)
    {
      j = 1;
      str = localMatcher.group(9);
      if (str == null) {
        break label158;
      }
    }
    int k;
    int m;
    int n;
    int i2;
    Object localObject;
    label158:
    for (int i = 1;; i = 0)
    {
      k = 0;
      m = 0;
      n = 0;
      i2 = 0;
      localObject = null;
      if ((i == 0) || (j != 0)) {
        break label163;
      }
      throw new NumberFormatException("Invalid date/time format, cannot specify time zone shift without specifying time: " + paramString);
      j = 0;
      break;
    }
    label163:
    int i1 = i2;
    if (j != 0)
    {
      int i3 = Integer.parseInt(localMatcher.group(5));
      int i4 = Integer.parseInt(localMatcher.group(6));
      int i5 = Integer.parseInt(localMatcher.group(7));
      k = i3;
      m = i4;
      n = i5;
      i1 = i2;
      if (localMatcher.group(8) != null)
      {
        k = Integer.parseInt(localMatcher.group(8).substring(1));
        m = localMatcher.group(8).substring(1).length();
        i1 = (int)(k / Math.pow(10.0D, m - 3));
        n = i5;
        m = i4;
        k = i3;
      }
    }
    paramString = new GregorianCalendar(GMT);
    paramString.set(i6, i7 - 1, i8, k, m, n);
    paramString.set(14, i1);
    long l1 = paramString.getTimeInMillis();
    paramString = (String)localObject;
    long l2 = l1;
    if (j != 0)
    {
      paramString = (String)localObject;
      l2 = l1;
      if (i != 0)
      {
        if (Character.toUpperCase(str.charAt(0)) != 'Z') {
          break label400;
        }
        i = 0;
        paramString = Integer.valueOf(i);
        l2 = l1;
      }
    }
    if (j == 0) {}
    for (boolean bool = true;; bool = false)
    {
      return new DateTime(bool, l2, paramString);
      label400:
      k = Integer.parseInt(localMatcher.group(11)) * 60 + Integer.parseInt(localMatcher.group(12));
      i = k;
      if (localMatcher.group(10).charAt(0) == '-') {
        i = -k;
      }
      l1 -= i * 60000L;
      break;
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (!(paramObject instanceof DateTime)) {
        return false;
      }
      paramObject = (DateTime)paramObject;
    } while ((this.dateOnly == ((DateTime)paramObject).dateOnly) && (this.value == ((DateTime)paramObject).value) && (this.tzShift == ((DateTime)paramObject).tzShift));
    return false;
  }
  
  public int getTimeZoneShift()
  {
    return this.tzShift;
  }
  
  public long getValue()
  {
    return this.value;
  }
  
  public int hashCode()
  {
    long l2 = this.value;
    if (this.dateOnly) {}
    for (long l1 = 1L;; l1 = 0L) {
      return Arrays.hashCode(new long[] { l2, l1, this.tzShift });
    }
  }
  
  public boolean isDateOnly()
  {
    return this.dateOnly;
  }
  
  public String toString()
  {
    return toStringRfc3339();
  }
  
  public String toStringRfc3339()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    GregorianCalendar localGregorianCalendar = new GregorianCalendar(GMT);
    localGregorianCalendar.setTimeInMillis(this.value + this.tzShift * 60000L);
    appendInt(localStringBuilder, localGregorianCalendar.get(1), 4);
    localStringBuilder.append('-');
    appendInt(localStringBuilder, localGregorianCalendar.get(2) + 1, 2);
    localStringBuilder.append('-');
    appendInt(localStringBuilder, localGregorianCalendar.get(5), 2);
    if (!this.dateOnly)
    {
      localStringBuilder.append('T');
      appendInt(localStringBuilder, localGregorianCalendar.get(11), 2);
      localStringBuilder.append(':');
      appendInt(localStringBuilder, localGregorianCalendar.get(12), 2);
      localStringBuilder.append(':');
      appendInt(localStringBuilder, localGregorianCalendar.get(13), 2);
      if (localGregorianCalendar.isSet(14))
      {
        localStringBuilder.append('.');
        appendInt(localStringBuilder, localGregorianCalendar.get(14), 3);
      }
      if (this.tzShift == 0) {
        localStringBuilder.append('Z');
      }
    }
    else
    {
      return localStringBuilder.toString();
    }
    int i = this.tzShift;
    if (this.tzShift > 0) {
      localStringBuilder.append('+');
    }
    for (;;)
    {
      appendInt(localStringBuilder, i / 60, 2);
      localStringBuilder.append(':');
      appendInt(localStringBuilder, i % 60, 2);
      break;
      localStringBuilder.append('-');
      i = -i;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\DateTime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */