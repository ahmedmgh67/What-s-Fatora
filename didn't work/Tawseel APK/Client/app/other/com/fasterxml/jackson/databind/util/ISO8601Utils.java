package com.fasterxml.jackson.databind.util;

import java.io.PrintStream;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class ISO8601Utils
{
  @Deprecated
  private static final String GMT_ID = "GMT";
  @Deprecated
  private static final TimeZone TIMEZONE_GMT = TimeZone.getTimeZone("GMT");
  private static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone("UTC");
  private static final TimeZone TIMEZONE_Z = TIMEZONE_UTC;
  private static final String UTC_ID = "UTC";
  
  private static boolean checkOffset(String paramString, int paramInt, char paramChar)
  {
    return (paramInt < paramString.length()) && (paramString.charAt(paramInt) == paramChar);
  }
  
  public static String format(Date paramDate)
  {
    return format(paramDate, false, TIMEZONE_UTC);
  }
  
  public static String format(Date paramDate, boolean paramBoolean)
  {
    return format(paramDate, paramBoolean, TIMEZONE_UTC);
  }
  
  public static String format(Date paramDate, boolean paramBoolean, TimeZone paramTimeZone)
  {
    GregorianCalendar localGregorianCalendar = new GregorianCalendar(paramTimeZone, Locale.US);
    localGregorianCalendar.setTime(paramDate);
    int k = "yyyy-MM-ddThh:mm:ss".length();
    int i;
    int j;
    label51:
    char c;
    if (paramBoolean)
    {
      i = ".sss".length();
      if (paramTimeZone.getRawOffset() != 0) {
        break label320;
      }
      j = "Z".length();
      paramDate = new StringBuilder(k + i + j);
      padInt(paramDate, localGregorianCalendar.get(1), "yyyy".length());
      paramDate.append('-');
      padInt(paramDate, localGregorianCalendar.get(2) + 1, "MM".length());
      paramDate.append('-');
      padInt(paramDate, localGregorianCalendar.get(5), "dd".length());
      paramDate.append('T');
      padInt(paramDate, localGregorianCalendar.get(11), "hh".length());
      paramDate.append(':');
      padInt(paramDate, localGregorianCalendar.get(12), "mm".length());
      paramDate.append(':');
      padInt(paramDate, localGregorianCalendar.get(13), "ss".length());
      if (paramBoolean)
      {
        paramDate.append('.');
        padInt(paramDate, localGregorianCalendar.get(14), "sss".length());
      }
      i = paramTimeZone.getOffset(localGregorianCalendar.getTimeInMillis());
      if (i == 0) {
        break label336;
      }
      j = Math.abs(i / 60000 / 60);
      k = Math.abs(i / 60000 % 60);
      if (i >= 0) {
        break label330;
      }
      c = '-';
      label274:
      paramDate.append(c);
      padInt(paramDate, j, "hh".length());
      paramDate.append(':');
      padInt(paramDate, k, "mm".length());
    }
    for (;;)
    {
      return paramDate.toString();
      i = 0;
      break;
      label320:
      j = "+hh:mm".length();
      break label51;
      label330:
      c = '+';
      break label274;
      label336:
      paramDate.append('Z');
    }
  }
  
  private static int indexOfNonDigit(String paramString, int paramInt)
  {
    while (paramInt < paramString.length())
    {
      int i = paramString.charAt(paramInt);
      if ((i < 48) || (i > 57)) {
        return paramInt;
      }
      paramInt += 1;
    }
    return paramString.length();
  }
  
  public static void main(String[] paramArrayOfString)
  {
    for (;;)
    {
      long l1 = System.currentTimeMillis();
      int i = test1(250000, 3);
      long l2 = System.currentTimeMillis();
      System.out.println("Pow (" + i + ") -> " + (l2 - l1) + " ms");
      l1 = System.currentTimeMillis();
      i = test2(250000, 3);
      l2 = System.currentTimeMillis();
      System.out.println("Iter (" + i + ") -> " + (l2 - l1) + " ms");
    }
  }
  
  private static void padInt(StringBuilder paramStringBuilder, int paramInt1, int paramInt2)
  {
    String str = Integer.toString(paramInt1);
    paramInt1 = paramInt2 - str.length();
    while (paramInt1 > 0)
    {
      paramStringBuilder.append('0');
      paramInt1 -= 1;
    }
    paramStringBuilder.append(str);
  }
  
  public static Date parse(String paramString, ParsePosition paramParsePosition)
    throws ParseException
  {
    try
    {
      i = paramParsePosition.getIndex();
      j = i + 4;
      i6 = parseInt(paramString, i, j);
      i = j;
      if (checkOffset(paramString, j, '-')) {
        i = j + 1;
      }
      j = i + 2;
      i7 = parseInt(paramString, i, j);
      if (!checkOffset(paramString, j, '-')) {
        break label920;
      }
      i = j + 1;
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
      int i6;
      int i7;
      int i8;
      boolean bool;
      GregorianCalendar localGregorianCalendar;
      int i5;
      if (paramString != null) {
        break label866;
      }
      paramString = null;
      String str1 = localIndexOutOfBoundsException.getMessage();
      if (str1 == null) {
        break label446;
      }
      Object localObject2 = str1;
      if (!str1.isEmpty()) {
        break label479;
      }
      localObject2 = "(" + localIndexOutOfBoundsException.getClass().getName() + ")";
      paramString = new ParseException("Failed to parse date [" + paramString + "]: " + (String)localObject2, paramParsePosition.getIndex());
      paramString.initCause(localIndexOutOfBoundsException);
      throw paramString;
      i *= 10;
      break label926;
      i *= 100;
      break label926;
      c = paramString.charAt(i);
      if (c != 'Z') {
        break label947;
      }
      Object localObject1 = TIMEZONE_Z;
      i += 1;
      String str2;
      do
      {
        do
        {
          for (;;)
          {
            localObject1 = new GregorianCalendar((TimeZone)localObject1);
            ((Calendar)localObject1).setLenient(false);
            ((Calendar)localObject1).set(1, i6);
            ((Calendar)localObject1).set(2, i7 - 1);
            ((Calendar)localObject1).set(5, i8);
            ((Calendar)localObject1).set(11, k);
            ((Calendar)localObject1).set(12, n);
            ((Calendar)localObject1).set(13, i1);
            ((Calendar)localObject1).set(14, m);
            paramParsePosition.setIndex(i);
            return ((Calendar)localObject1).getTime();
            localObject1 = paramString.substring(i);
            j = i + ((String)localObject1).length();
            if ((!"+0000".equals(localObject1)) && (!"+00:00".equals(localObject1))) {
              break;
            }
            localObject1 = TIMEZONE_Z;
            i = j;
          }
          str1 = "GMT" + (String)localObject1;
          localObject2 = TimeZone.getTimeZone(str1);
          str2 = ((TimeZone)localObject2).getID();
          i = j;
          localObject1 = localObject2;
        } while (str2.equals(str1));
        i = j;
        localObject1 = localObject2;
      } while (str2.replace(":", "").equals(str1));
      throw new IndexOutOfBoundsException("Mismatching time zone indicator: " + str1 + " given, resolves to " + ((TimeZone)localObject2).getID());
      throw new IndexOutOfBoundsException("Invalid time zone indicator '" + c + "'");
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;)
      {
        int j;
        int i2;
        int i4;
        int i3;
        char c;
        continue;
        paramString = '"' + paramString + "'";
        continue;
        int k = i2;
        int m = i4;
        int n = i3;
        int i1 = j;
        continue;
        int i = k;
        continue;
        i = j;
        continue;
        k = i2;
        m = i;
        n = i3;
        i = i1;
        i1 = j;
        continue;
        if (c != '+') {
          if (c != '-') {}
        }
      }
    }
    catch (NumberFormatException localNumberFormatException)
    {
      label446:
      label479:
      label866:
      label895:
      label914:
      label920:
      label926:
      label947:
      for (;;) {}
    }
    i2 = i + 2;
    i8 = parseInt(paramString, i, i2);
    k = 0;
    n = 0;
    j = 0;
    i4 = 0;
    bool = checkOffset(paramString, i2, 'T');
    if ((!bool) && (paramString.length() <= i2))
    {
      localGregorianCalendar = new GregorianCalendar(i6, i7 - 1, i8);
      paramParsePosition.setIndex(i2);
      return localGregorianCalendar.getTime();
    }
    m = i4;
    i = i2;
    i1 = j;
    if (bool)
    {
      i = i2 + 1;
      k = i + 2;
      i2 = parseInt(paramString, i, k);
      i = k;
      if (checkOffset(paramString, k, ':')) {
        i = k + 1;
      }
      k = i + 2;
      i3 = parseInt(paramString, i, k);
      if (!checkOffset(paramString, k, ':')) {
        break label914;
      }
      i = k + 1;
      if (paramString.length() <= i) {
        break label895;
      }
      k = paramString.charAt(i);
      if ((k == 90) || (k == 43) || (k == 45)) {
        break label895;
      }
      i5 = i + 2;
      i = parseInt(paramString, i, i5);
      j = i;
      if (i > 59)
      {
        j = i;
        if (i < 63) {
          j = 59;
        }
      }
      k = i2;
      m = i4;
      n = i3;
      i = i5;
      i1 = j;
      if (checkOffset(paramString, i5, '.'))
      {
        k = i5 + 1;
        i1 = indexOfNonDigit(paramString, k + 1);
        m = Math.min(i1, k + 3);
        i = parseInt(paramString, k, m);
      }
    }
    switch (m - k)
    {
    case 2: 
      if (paramString.length() <= i) {
        throw new IllegalArgumentException("No time zone indicator");
      }
      break;
    }
  }
  
  private static int parseInt(String paramString, int paramInt1, int paramInt2)
    throws NumberFormatException
  {
    if ((paramInt1 < 0) || (paramInt2 > paramString.length()) || (paramInt1 > paramInt2)) {
      throw new NumberFormatException(paramString);
    }
    int j = 0;
    int i;
    if (paramInt1 < paramInt2)
    {
      i = paramInt1 + 1;
      j = Character.digit(paramString.charAt(paramInt1), 10);
      if (j < 0) {
        throw new NumberFormatException("Invalid number: " + paramString.substring(paramInt1, paramInt2));
      }
      j = -j;
    }
    for (;;)
    {
      if (i < paramInt2)
      {
        int k = Character.digit(paramString.charAt(i), 10);
        if (k < 0) {
          throw new NumberFormatException("Invalid number: " + paramString.substring(paramInt1, paramInt2));
        }
        j = j * 10 - k;
        i += 1;
      }
      else
      {
        return -j;
        i = paramInt1;
      }
    }
  }
  
  static int test1(int paramInt1, int paramInt2)
  {
    int j = 3;
    int i = paramInt1;
    for (paramInt1 = j;; paramInt1 = (int)Math.pow(10.0D, paramInt2))
    {
      i -= 1;
      if (i < 0) {
        break;
      }
    }
    return paramInt1;
  }
  
  static int test2(int paramInt1, int paramInt2)
  {
    int j = 3;
    int i = paramInt1;
    int k = i - 1;
    if (k >= 0)
    {
      paramInt1 = 10;
      int m;
      for (i = paramInt2;; i = m)
      {
        m = i - 1;
        j = paramInt1;
        i = k;
        if (m <= 0) {
          break;
        }
        paramInt1 *= 10;
      }
    }
    return j;
  }
  
  @Deprecated
  public static TimeZone timeZoneGMT()
  {
    return TIMEZONE_GMT;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\util\ISO8601Utils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */