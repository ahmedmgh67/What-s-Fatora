package com.google.android.gms.internal;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

public class zzbti
  implements Closeable
{
  private static final char[] zzcpF = ")]}'\n".toCharArray();
  private final Reader in;
  private int limit = 0;
  private int pos = 0;
  private boolean zzcpG = false;
  private final char[] zzcpH = new char['Ѐ'];
  private int zzcpI = 0;
  private int zzcpJ = 0;
  private int zzcpK = 0;
  private long zzcpL;
  private int zzcpM;
  private String zzcpN;
  private int[] zzcpO = new int[32];
  private int zzcpP = 0;
  private String[] zzcpQ;
  private int[] zzcpR;
  
  static
  {
    zzbsn.zzcny = new zzbsn()
    {
      public void zzi(zzbti paramAnonymouszzbti)
        throws IOException
      {
        if ((paramAnonymouszzbti instanceof zzbsy))
        {
          ((zzbsy)paramAnonymouszzbti).zzabT();
          return;
        }
        int j = zzbti.zzG(paramAnonymouszzbti);
        int i = j;
        if (j == 0) {
          i = zzbti.zzH(paramAnonymouszzbti);
        }
        if (i == 13)
        {
          zzbti.zza(paramAnonymouszzbti, 9);
          return;
        }
        if (i == 12)
        {
          zzbti.zza(paramAnonymouszzbti, 8);
          return;
        }
        if (i == 14)
        {
          zzbti.zza(paramAnonymouszzbti, 10);
          return;
        }
        String str = String.valueOf(paramAnonymouszzbti.zzabQ());
        i = zzbti.zzI(paramAnonymouszzbti);
        j = zzbti.zzJ(paramAnonymouszzbti);
        paramAnonymouszzbti = paramAnonymouszzbti.getPath();
        throw new IllegalStateException(String.valueOf(str).length() + 70 + String.valueOf(paramAnonymouszzbti).length() + "Expected a name but was " + str + " " + " at line " + i + " column " + j + " path " + paramAnonymouszzbti);
      }
    };
  }
  
  public zzbti(Reader paramReader)
  {
    int[] arrayOfInt = this.zzcpO;
    int i = this.zzcpP;
    this.zzcpP = (i + 1);
    arrayOfInt[i] = 6;
    this.zzcpQ = new String[32];
    this.zzcpR = new int[32];
    if (paramReader == null) {
      throw new NullPointerException("in == null");
    }
    this.in = paramReader;
  }
  
  private int getColumnNumber()
  {
    return this.pos - this.zzcpJ + 1;
  }
  
  private int getLineNumber()
  {
    return this.zzcpI + 1;
  }
  
  private int zzacd()
    throws IOException
  {
    int i = this.zzcpO[(this.zzcpP - 1)];
    if (i == 1)
    {
      this.zzcpO[(this.zzcpP - 1)] = 2;
      switch (zzbh(true))
      {
      default: 
        this.pos -= 1;
        if (this.zzcpP == 1) {
          zzaci();
        }
        i = zzace();
        if (i == 0) {
          break;
        }
      }
    }
    int j;
    do
    {
      return i;
      if (i == 2)
      {
        switch (zzbh(true))
        {
        case 44: 
        default: 
          throw zzjZ("Unterminated array");
        case 93: 
          this.zzcpK = 4;
          return 4;
        }
        zzaci();
        break;
      }
      if ((i == 3) || (i == 5))
      {
        this.zzcpO[(this.zzcpP - 1)] = 4;
        if (i == 5) {
          switch (zzbh(true))
          {
          default: 
            throw zzjZ("Unterminated object");
          case 125: 
            this.zzcpK = 2;
            return 2;
          case 59: 
            zzaci();
          }
        }
        j = zzbh(true);
        switch (j)
        {
        default: 
          zzaci();
          this.pos -= 1;
          if (zzc((char)j))
          {
            this.zzcpK = 14;
            return 14;
          }
          break;
        case 34: 
          this.zzcpK = 13;
          return 13;
        case 39: 
          zzaci();
          this.zzcpK = 12;
          return 12;
        case 125: 
          if (i != 5)
          {
            this.zzcpK = 2;
            return 2;
          }
          throw zzjZ("Expected name");
        }
        throw zzjZ("Expected name");
      }
      if (i == 4)
      {
        this.zzcpO[(this.zzcpP - 1)] = 5;
        switch (zzbh(true))
        {
        case 58: 
        case 59: 
        case 60: 
        default: 
          throw zzjZ("Expected ':'");
        }
        zzaci();
        if (((this.pos >= this.limit) && (!zzqe(1))) || (this.zzcpH[this.pos] != '>')) {
          break;
        }
        this.pos += 1;
        break;
      }
      if (i == 6)
      {
        if (this.zzcpG) {
          zzacl();
        }
        this.zzcpO[(this.zzcpP - 1)] = 7;
        break;
      }
      if (i == 7)
      {
        if (zzbh(false) == -1)
        {
          this.zzcpK = 17;
          return 17;
        }
        zzaci();
        this.pos -= 1;
        break;
      }
      if (i != 8) {
        break;
      }
      throw new IllegalStateException("JsonReader is closed");
      if (i == 1)
      {
        this.zzcpK = 4;
        return 4;
      }
      if ((i == 1) || (i == 2))
      {
        zzaci();
        this.pos -= 1;
        this.zzcpK = 7;
        return 7;
      }
      throw zzjZ("Unexpected value");
      zzaci();
      this.zzcpK = 8;
      return 8;
      if (this.zzcpP == 1) {
        zzaci();
      }
      this.zzcpK = 9;
      return 9;
      this.zzcpK = 3;
      return 3;
      this.zzcpK = 1;
      return 1;
      j = zzacf();
      i = j;
    } while (j != 0);
    if (!zzc(this.zzcpH[this.pos])) {
      throw zzjZ("Expected value");
    }
    zzaci();
    this.zzcpK = 10;
    return 10;
  }
  
  private int zzace()
    throws IOException
  {
    int i = this.zzcpH[this.pos];
    String str2;
    String str1;
    int k;
    int j;
    if ((i == 116) || (i == 84))
    {
      str2 = "true";
      str1 = "TRUE";
      i = 5;
      k = str2.length();
      j = 1;
    }
    for (;;)
    {
      if (j >= k) {
        break label168;
      }
      if ((this.pos + j >= this.limit) && (!zzqe(j + 1)))
      {
        return 0;
        if ((i == 102) || (i == 70))
        {
          str2 = "false";
          str1 = "FALSE";
          i = 6;
          break;
        }
        if ((i == 110) || (i == 78))
        {
          str2 = "null";
          str1 = "NULL";
          i = 7;
          break;
        }
        return 0;
      }
      int m = this.zzcpH[(this.pos + j)];
      if ((m != str2.charAt(j)) && (m != str1.charAt(j))) {
        return 0;
      }
      j += 1;
    }
    label168:
    if (((this.pos + k < this.limit) || (zzqe(k + 1))) && (zzc(this.zzcpH[(this.pos + k)]))) {
      return 0;
    }
    this.pos += k;
    this.zzcpK = i;
    return i;
  }
  
  private int zzacf()
    throws IOException
  {
    char[] arrayOfChar = this.zzcpH;
    int i2 = this.pos;
    int n = this.limit;
    long l1 = 0L;
    int i = 0;
    int j = 1;
    int k = 0;
    int m = 0;
    int i3 = n;
    int i1 = i3;
    n = i2;
    if (i2 + m == i3)
    {
      if (m == arrayOfChar.length) {
        return 0;
      }
      if (zzqe(m + 1)) {}
    }
    label101:
    char c;
    for (;;)
    {
      if ((k == 2) && (j != 0) && ((l1 != Long.MIN_VALUE) || (i != 0))) {
        if (i != 0)
        {
          this.zzcpL = l1;
          this.pos += m;
          this.zzcpK = 15;
          return 15;
          n = this.pos;
          i1 = this.limit;
          c = arrayOfChar[(n + m)];
          switch (c)
          {
          default: 
            if ((c < '0') || (c > '9'))
            {
              if (!zzc(c)) {
                continue;
              }
              return 0;
            }
            break;
          case '-': 
            if (k == 0)
            {
              i = 1;
              k = 1;
            }
            break;
          }
        }
      }
    }
    for (;;)
    {
      int i4 = m + 1;
      m = k;
      i3 = i1;
      i2 = n;
      k = i;
      i = m;
      m = i4;
      break;
      if (k == 5)
      {
        i2 = 6;
        k = i;
        i = i2;
      }
      else
      {
        return 0;
        if (k == 5)
        {
          i2 = 6;
          k = i;
          i = i2;
        }
        else
        {
          return 0;
          if ((k == 2) || (k == 4))
          {
            i2 = 5;
            k = i;
            i = i2;
          }
          else
          {
            return 0;
            if (k == 2)
            {
              i2 = 3;
              k = i;
              i = i2;
            }
            else
            {
              return 0;
              if ((k == 1) || (k == 0))
              {
                l1 = -(c - '0');
                i2 = 2;
                k = i;
                i = i2;
              }
              else
              {
                if (k == 2)
                {
                  if (l1 == 0L) {
                    return 0;
                  }
                  long l2 = 10L * l1 - (c - '0');
                  if ((l1 > -922337203685477580L) || ((l1 == -922337203685477580L) && (l2 < l1))) {}
                  for (i3 = 1;; i3 = 0)
                  {
                    i2 = i;
                    l1 = l2;
                    j = i3 & j;
                    i = k;
                    k = i2;
                    break;
                  }
                }
                if (k == 3)
                {
                  i2 = 4;
                  k = i;
                  i = i2;
                }
                else
                {
                  if ((k == 5) || (k == 6))
                  {
                    i2 = 7;
                    k = i;
                    i = i2;
                    continue;
                    l1 = -l1;
                    break label101;
                    if ((k == 2) || (k == 4) || (k == 7))
                    {
                      this.zzcpM = m;
                      this.zzcpK = 16;
                      return 16;
                    }
                    return 0;
                  }
                  i2 = i;
                  i = k;
                  k = i2;
                }
              }
            }
          }
        }
      }
    }
  }
  
  private String zzacg()
    throws IOException
  {
    Object localObject1 = null;
    int i = 0;
    for (;;)
    {
      Object localObject2;
      int j;
      if (this.pos + i < this.limit)
      {
        localObject2 = localObject1;
        j = i;
        switch (this.zzcpH[(this.pos + i)])
        {
        default: 
          i += 1;
          break;
        case '#': 
        case '/': 
        case ';': 
        case '=': 
        case '\\': 
          zzaci();
          j = i;
          localObject2 = localObject1;
        case '\t': 
        case '\n': 
        case '\f': 
        case '\r': 
        case ' ': 
        case ',': 
        case ':': 
        case '[': 
        case ']': 
        case '{': 
        case '}': 
          label188:
          if (localObject2 != null) {}
          break;
        }
      }
      else
      {
        for (localObject1 = new String(this.zzcpH, this.pos, j);; localObject1 = ((StringBuilder)localObject2).toString())
        {
          this.pos = (j + this.pos);
          return (String)localObject1;
          if (i < this.zzcpH.length)
          {
            localObject2 = localObject1;
            j = i;
            if (!zzqe(i + 1)) {
              break label188;
            }
            break;
          }
          localObject2 = localObject1;
          if (localObject1 == null) {
            localObject2 = new StringBuilder();
          }
          ((StringBuilder)localObject2).append(this.zzcpH, this.pos, i);
          this.pos = (i + this.pos);
          if (zzqe(1)) {
            break label327;
          }
          j = 0;
          break label188;
          ((StringBuilder)localObject2).append(this.zzcpH, this.pos, j);
        }
        label327:
        i = 0;
        localObject1 = localObject2;
      }
    }
  }
  
  private void zzach()
    throws IOException
  {
    do
    {
      int i = 0;
      while (this.pos + i < this.limit) {
        switch (this.zzcpH[(this.pos + i)])
        {
        default: 
          i += 1;
          break;
        case '#': 
        case '/': 
        case ';': 
        case '=': 
        case '\\': 
          zzaci();
        case '\t': 
        case '\n': 
        case '\f': 
        case '\r': 
        case ' ': 
        case ',': 
        case ':': 
        case '[': 
        case ']': 
        case '{': 
        case '}': 
          this.pos = (i + this.pos);
          return;
        }
      }
      this.pos = (i + this.pos);
    } while (zzqe(1));
  }
  
  private void zzaci()
    throws IOException
  {
    if (!this.zzcpG) {
      throw zzjZ("Use JsonReader.setLenient(true) to accept malformed JSON");
    }
  }
  
  private void zzacj()
    throws IOException
  {
    int i;
    do
    {
      if ((this.pos < this.limit) || (zzqe(1)))
      {
        char[] arrayOfChar = this.zzcpH;
        i = this.pos;
        this.pos = (i + 1);
        i = arrayOfChar[i];
        if (i == 10)
        {
          this.zzcpI += 1;
          this.zzcpJ = this.pos;
        }
      }
      else
      {
        return;
      }
    } while (i != 13);
  }
  
  private char zzack()
    throws IOException
  {
    if ((this.pos == this.limit) && (!zzqe(1))) {
      throw zzjZ("Unterminated escape sequence");
    }
    Object localObject = this.zzcpH;
    int i = this.pos;
    this.pos = (i + 1);
    char c = localObject[i];
    switch (c)
    {
    default: 
      return c;
    case 'u': 
      if ((this.pos + 4 > this.limit) && (!zzqe(4))) {
        throw zzjZ("Unterminated escape sequence");
      }
      int j = this.pos;
      c = '\000';
      i = j;
      if (i < j + 4)
      {
        int k = this.zzcpH[i];
        int m = (char)(c << '\004');
        if ((k >= 48) && (k <= 57)) {
          c = (char)(m + (k - 48));
        }
        for (;;)
        {
          i += 1;
          break;
          if ((k >= 97) && (k <= 102))
          {
            c = (char)(m + (k - 97 + 10));
          }
          else
          {
            if ((k < 65) || (k > 70)) {
              break label267;
            }
            c = (char)(m + (k - 65 + 10));
          }
        }
        localObject = String.valueOf(new String(this.zzcpH, this.pos, 4));
        if (((String)localObject).length() != 0) {}
        for (localObject = "\\u".concat((String)localObject);; localObject = new String("\\u")) {
          throw new NumberFormatException((String)localObject);
        }
      }
      this.pos += 4;
      return c;
    case 't': 
      return '\t';
    case 'b': 
      return '\b';
    case 'n': 
      return '\n';
    case 'r': 
      return '\r';
    case 'f': 
      label267:
      return '\f';
    }
    this.zzcpI += 1;
    this.zzcpJ = this.pos;
    return c;
  }
  
  private void zzacl()
    throws IOException
  {
    zzbh(true);
    this.pos -= 1;
    if ((this.pos + zzcpF.length > this.limit) && (!zzqe(zzcpF.length))) {
      return;
    }
    int i = 0;
    for (;;)
    {
      if (i >= zzcpF.length) {
        break label80;
      }
      if (this.zzcpH[(this.pos + i)] != zzcpF[i]) {
        break;
      }
      i += 1;
    }
    label80:
    this.pos += zzcpF.length;
  }
  
  private int zzbh(boolean paramBoolean)
    throws IOException
  {
    Object localObject = this.zzcpH;
    int i = this.pos;
    int j = this.limit;
    for (;;)
    {
      int k = j;
      int m = i;
      if (i == j)
      {
        this.pos = i;
        if (!zzqe(1))
        {
          if (paramBoolean)
          {
            localObject = String.valueOf("End of input at line ");
            i = getLineNumber();
            j = getColumnNumber();
            throw new EOFException(String.valueOf(localObject).length() + 30 + (String)localObject + i + " column " + j);
          }
        }
        else
        {
          m = this.pos;
          k = this.limit;
        }
      }
      else
      {
        i = m + 1;
        j = localObject[m];
        if (j == 10)
        {
          this.zzcpI += 1;
          this.zzcpJ = i;
          j = k;
          continue;
        }
        if ((j == 32) || (j == 13)) {
          break label383;
        }
        if (j == 9)
        {
          j = k;
          continue;
        }
        if (j == 47)
        {
          this.pos = i;
          if (i == k)
          {
            this.pos -= 1;
            boolean bool = zzqe(2);
            this.pos += 1;
            if (!bool) {
              return j;
            }
          }
          zzaci();
          switch (localObject[this.pos])
          {
          default: 
            return j;
          case '*': 
            this.pos += 1;
            if (!zzjY("*/")) {
              throw zzjZ("Unterminated comment");
            }
            i = this.pos + 2;
            j = this.limit;
            break;
          case '/': 
            this.pos += 1;
            zzacj();
            i = this.pos;
            j = this.limit;
            break;
          }
        }
        if (j == 35)
        {
          this.pos = i;
          zzaci();
          zzacj();
          i = this.pos;
          j = this.limit;
          continue;
        }
        this.pos = i;
        return j;
      }
      return -1;
      label383:
      j = k;
    }
  }
  
  private boolean zzc(char paramChar)
    throws IOException
  {
    switch (paramChar)
    {
    default: 
      return true;
    case '#': 
    case '/': 
    case ';': 
    case '=': 
    case '\\': 
      zzaci();
    }
    return false;
  }
  
  private String zzd(char paramChar)
    throws IOException
  {
    char[] arrayOfChar = this.zzcpH;
    StringBuilder localStringBuilder = new StringBuilder();
    do
    {
      int k = this.pos;
      int j = this.limit;
      int i = k;
      if (i < j)
      {
        int i1 = i + 1;
        char c = arrayOfChar[i];
        if (c == paramChar)
        {
          this.pos = i1;
          localStringBuilder.append(arrayOfChar, k, i1 - k - 1);
          return localStringBuilder.toString();
        }
        int n;
        int m;
        if (c == '\\')
        {
          this.pos = i1;
          localStringBuilder.append(arrayOfChar, k, i1 - k - 1);
          localStringBuilder.append(zzack());
          n = this.pos;
          m = this.limit;
          i = n;
        }
        for (;;)
        {
          k = n;
          j = m;
          break;
          n = k;
          m = j;
          i = i1;
          if (c == '\n')
          {
            this.zzcpI += 1;
            this.zzcpJ = i1;
            n = k;
            m = j;
            i = i1;
          }
        }
      }
      localStringBuilder.append(arrayOfChar, k, i - k);
      this.pos = i;
    } while (zzqe(1));
    throw zzjZ("Unterminated string");
  }
  
  private void zze(char paramChar)
    throws IOException
  {
    char[] arrayOfChar = this.zzcpH;
    do
    {
      int i = this.pos;
      int j = this.limit;
      if (i < j)
      {
        int m = i + 1;
        char c = arrayOfChar[i];
        if (c == paramChar)
        {
          this.pos = m;
          return;
        }
        int k;
        if (c == '\\')
        {
          this.pos = m;
          zzack();
          i = this.pos;
          k = this.limit;
        }
        for (;;)
        {
          j = k;
          break;
          k = j;
          i = m;
          if (c == '\n')
          {
            this.zzcpI += 1;
            this.zzcpJ = m;
            k = j;
            i = m;
          }
        }
      }
      this.pos = i;
    } while (zzqe(1));
    throw zzjZ("Unterminated string");
  }
  
  private boolean zzjY(String paramString)
    throws IOException
  {
    boolean bool2 = false;
    for (;;)
    {
      if (this.pos + paramString.length() > this.limit)
      {
        bool1 = bool2;
        if (!zzqe(paramString.length())) {
          return bool1;
        }
      }
      if (this.zzcpH[this.pos] != '\n') {
        break;
      }
      this.zzcpI += 1;
      this.zzcpJ = (this.pos + 1);
      this.pos += 1;
    }
    int i = 0;
    for (;;)
    {
      if (i >= paramString.length()) {
        break label116;
      }
      if (this.zzcpH[(this.pos + i)] != paramString.charAt(i)) {
        break;
      }
      i += 1;
    }
    label116:
    boolean bool1 = true;
    return bool1;
  }
  
  private IOException zzjZ(String paramString)
    throws IOException
  {
    int i = getLineNumber();
    int j = getColumnNumber();
    String str = getPath();
    throw new zzbtl(String.valueOf(paramString).length() + 45 + String.valueOf(str).length() + paramString + " at line " + i + " column " + j + " path " + str);
  }
  
  private void zzqd(int paramInt)
  {
    if (this.zzcpP == this.zzcpO.length)
    {
      arrayOfInt1 = new int[this.zzcpP * 2];
      int[] arrayOfInt2 = new int[this.zzcpP * 2];
      String[] arrayOfString = new String[this.zzcpP * 2];
      System.arraycopy(this.zzcpO, 0, arrayOfInt1, 0, this.zzcpP);
      System.arraycopy(this.zzcpR, 0, arrayOfInt2, 0, this.zzcpP);
      System.arraycopy(this.zzcpQ, 0, arrayOfString, 0, this.zzcpP);
      this.zzcpO = arrayOfInt1;
      this.zzcpR = arrayOfInt2;
      this.zzcpQ = arrayOfString;
    }
    int[] arrayOfInt1 = this.zzcpO;
    int i = this.zzcpP;
    this.zzcpP = (i + 1);
    arrayOfInt1[i] = paramInt;
  }
  
  private boolean zzqe(int paramInt)
    throws IOException
  {
    boolean bool2 = false;
    char[] arrayOfChar = this.zzcpH;
    this.zzcpJ -= this.pos;
    if (this.limit != this.pos)
    {
      this.limit -= this.pos;
      System.arraycopy(arrayOfChar, this.pos, arrayOfChar, 0, this.limit);
    }
    for (;;)
    {
      this.pos = 0;
      int i;
      do
      {
        i = this.in.read(arrayOfChar, this.limit, arrayOfChar.length - this.limit);
        bool1 = bool2;
        if (i == -1) {
          break;
        }
        this.limit = (i + this.limit);
        i = paramInt;
        if (this.zzcpI == 0)
        {
          i = paramInt;
          if (this.zzcpJ == 0)
          {
            i = paramInt;
            if (this.limit > 0)
            {
              i = paramInt;
              if (arrayOfChar[0] == 65279)
              {
                this.pos += 1;
                this.zzcpJ += 1;
                i = paramInt + 1;
              }
            }
          }
        }
        paramInt = i;
      } while (this.limit < i);
      boolean bool1 = true;
      return bool1;
      this.limit = 0;
    }
  }
  
  public void beginArray()
    throws IOException
  {
    int j = this.zzcpK;
    int i = j;
    if (j == 0) {
      i = zzacd();
    }
    if (i == 3)
    {
      zzqd(1);
      this.zzcpR[(this.zzcpP - 1)] = 0;
      this.zzcpK = 0;
      return;
    }
    String str1 = String.valueOf(zzabQ());
    i = getLineNumber();
    j = getColumnNumber();
    String str2 = getPath();
    throw new IllegalStateException(String.valueOf(str1).length() + 74 + String.valueOf(str2).length() + "Expected BEGIN_ARRAY but was " + str1 + " at line " + i + " column " + j + " path " + str2);
  }
  
  public void beginObject()
    throws IOException
  {
    int j = this.zzcpK;
    int i = j;
    if (j == 0) {
      i = zzacd();
    }
    if (i == 1)
    {
      zzqd(3);
      this.zzcpK = 0;
      return;
    }
    String str1 = String.valueOf(zzabQ());
    i = getLineNumber();
    j = getColumnNumber();
    String str2 = getPath();
    throw new IllegalStateException(String.valueOf(str1).length() + 75 + String.valueOf(str2).length() + "Expected BEGIN_OBJECT but was " + str1 + " at line " + i + " column " + j + " path " + str2);
  }
  
  public void close()
    throws IOException
  {
    this.zzcpK = 0;
    this.zzcpO[0] = 8;
    this.zzcpP = 1;
    this.in.close();
  }
  
  public void endArray()
    throws IOException
  {
    int j = this.zzcpK;
    int i = j;
    if (j == 0) {
      i = zzacd();
    }
    if (i == 4)
    {
      this.zzcpP -= 1;
      localObject = this.zzcpR;
      i = this.zzcpP - 1;
      localObject[i] += 1;
      this.zzcpK = 0;
      return;
    }
    Object localObject = String.valueOf(zzabQ());
    i = getLineNumber();
    j = getColumnNumber();
    String str = getPath();
    throw new IllegalStateException(String.valueOf(localObject).length() + 72 + String.valueOf(str).length() + "Expected END_ARRAY but was " + (String)localObject + " at line " + i + " column " + j + " path " + str);
  }
  
  public void endObject()
    throws IOException
  {
    int j = this.zzcpK;
    int i = j;
    if (j == 0) {
      i = zzacd();
    }
    if (i == 2)
    {
      this.zzcpP -= 1;
      this.zzcpQ[this.zzcpP] = null;
      localObject = this.zzcpR;
      i = this.zzcpP - 1;
      localObject[i] += 1;
      this.zzcpK = 0;
      return;
    }
    Object localObject = String.valueOf(zzabQ());
    i = getLineNumber();
    j = getColumnNumber();
    String str = getPath();
    throw new IllegalStateException(String.valueOf(localObject).length() + 73 + String.valueOf(str).length() + "Expected END_OBJECT but was " + (String)localObject + " at line " + i + " column " + j + " path " + str);
  }
  
  public String getPath()
  {
    StringBuilder localStringBuilder = new StringBuilder().append('$');
    int i = 0;
    int j = this.zzcpP;
    if (i < j)
    {
      switch (this.zzcpO[i])
      {
      }
      for (;;)
      {
        i += 1;
        break;
        localStringBuilder.append('[').append(this.zzcpR[i]).append(']');
        continue;
        localStringBuilder.append('.');
        if (this.zzcpQ[i] != null) {
          localStringBuilder.append(this.zzcpQ[i]);
        }
      }
    }
    return localStringBuilder.toString();
  }
  
  public boolean hasNext()
    throws IOException
  {
    int j = this.zzcpK;
    int i = j;
    if (j == 0) {
      i = zzacd();
    }
    return (i != 2) && (i != 4);
  }
  
  public final boolean isLenient()
  {
    return this.zzcpG;
  }
  
  public boolean nextBoolean()
    throws IOException
  {
    int j = this.zzcpK;
    int i = j;
    if (j == 0) {
      i = zzacd();
    }
    if (i == 5)
    {
      this.zzcpK = 0;
      localObject = this.zzcpR;
      i = this.zzcpP - 1;
      localObject[i] += 1;
      return true;
    }
    if (i == 6)
    {
      this.zzcpK = 0;
      localObject = this.zzcpR;
      i = this.zzcpP - 1;
      localObject[i] += 1;
      return false;
    }
    Object localObject = String.valueOf(zzabQ());
    i = getLineNumber();
    j = getColumnNumber();
    String str = getPath();
    throw new IllegalStateException(String.valueOf(localObject).length() + 72 + String.valueOf(str).length() + "Expected a boolean but was " + (String)localObject + " at line " + i + " column " + j + " path " + str);
  }
  
  public double nextDouble()
    throws IOException
  {
    int j = this.zzcpK;
    int i = j;
    if (j == 0) {
      i = zzacd();
    }
    if (i == 15)
    {
      this.zzcpK = 0;
      localObject = this.zzcpR;
      i = this.zzcpP - 1;
      localObject[i] += 1;
      return this.zzcpL;
    }
    if (i == 16)
    {
      this.zzcpN = new String(this.zzcpH, this.pos, this.zzcpM);
      this.pos += this.zzcpM;
    }
    double d;
    do
    {
      for (;;)
      {
        this.zzcpK = 11;
        d = Double.parseDouble(this.zzcpN);
        if ((this.zzcpG) || ((!Double.isNaN(d)) && (!Double.isInfinite(d)))) {
          break label407;
        }
        i = getLineNumber();
        j = getColumnNumber();
        localObject = getPath();
        throw new zzbtl(String.valueOf(localObject).length() + 102 + "JSON forbids NaN and infinities: " + d + " at line " + i + " column " + j + " path " + (String)localObject);
        if ((i == 8) || (i == 9))
        {
          if (i == 8) {}
          for (char c = '\'';; c = '"')
          {
            this.zzcpN = zzd(c);
            break;
          }
        }
        if (i != 10) {
          break;
        }
        this.zzcpN = zzacg();
      }
    } while (i == 11);
    Object localObject = String.valueOf(zzabQ());
    i = getLineNumber();
    j = getColumnNumber();
    String str = getPath();
    throw new IllegalStateException(String.valueOf(localObject).length() + 71 + String.valueOf(str).length() + "Expected a double but was " + (String)localObject + " at line " + i + " column " + j + " path " + str);
    label407:
    this.zzcpN = null;
    this.zzcpK = 0;
    localObject = this.zzcpR;
    i = this.zzcpP - 1;
    localObject[i] += 1;
    return d;
  }
  
  public int nextInt()
    throws IOException
  {
    int j = this.zzcpK;
    int i = j;
    if (j == 0) {
      i = zzacd();
    }
    Object localObject1;
    if (i == 15)
    {
      i = (int)this.zzcpL;
      if (this.zzcpL != i)
      {
        long l = this.zzcpL;
        i = getLineNumber();
        j = getColumnNumber();
        localObject1 = getPath();
        throw new NumberFormatException(String.valueOf(localObject1).length() + 89 + "Expected an int but was " + l + " at line " + i + " column " + j + " path " + (String)localObject1);
      }
      this.zzcpK = 0;
      localObject1 = this.zzcpR;
      j = this.zzcpP - 1;
      localObject1[j] += 1;
      return i;
    }
    String str;
    if (i == 16)
    {
      this.zzcpN = new String(this.zzcpH, this.pos, this.zzcpM);
      this.pos += this.zzcpM;
      this.zzcpK = 11;
      double d = Double.parseDouble(this.zzcpN);
      i = (int)d;
      if (i != d)
      {
        localObject1 = this.zzcpN;
        i = getLineNumber();
        j = getColumnNumber();
        str = getPath();
        throw new NumberFormatException(String.valueOf(localObject1).length() + 69 + String.valueOf(str).length() + "Expected an int but was " + (String)localObject1 + " at line " + i + " column " + j + " path " + str);
      }
    }
    else
    {
      if ((i == 8) || (i == 9))
      {
        if (i == 8) {}
        for (char c = '\'';; c = '"')
        {
          this.zzcpN = zzd(c);
          try
          {
            i = Integer.parseInt(this.zzcpN);
            this.zzcpK = 0;
            localObject1 = this.zzcpR;
            j = this.zzcpP - 1;
            localObject1[j] += 1;
            return i;
          }
          catch (NumberFormatException localNumberFormatException) {}
          break;
        }
      }
      localObject2 = String.valueOf(zzabQ());
      i = getLineNumber();
      j = getColumnNumber();
      str = getPath();
      throw new IllegalStateException(String.valueOf(localObject2).length() + 69 + String.valueOf(str).length() + "Expected an int but was " + (String)localObject2 + " at line " + i + " column " + j + " path " + str);
    }
    this.zzcpN = null;
    this.zzcpK = 0;
    Object localObject2 = this.zzcpR;
    j = this.zzcpP - 1;
    localObject2[j] += 1;
    return i;
  }
  
  public long nextLong()
    throws IOException
  {
    int j = this.zzcpK;
    int i = j;
    if (j == 0) {
      i = zzacd();
    }
    Object localObject1;
    if (i == 15)
    {
      this.zzcpK = 0;
      localObject1 = this.zzcpR;
      i = this.zzcpP - 1;
      localObject1[i] += 1;
      return this.zzcpL;
    }
    long l;
    String str;
    if (i == 16)
    {
      this.zzcpN = new String(this.zzcpH, this.pos, this.zzcpM);
      this.pos += this.zzcpM;
      this.zzcpK = 11;
      double d = Double.parseDouble(this.zzcpN);
      l = d;
      if (l != d)
      {
        localObject1 = this.zzcpN;
        i = getLineNumber();
        j = getColumnNumber();
        str = getPath();
        throw new NumberFormatException(String.valueOf(localObject1).length() + 69 + String.valueOf(str).length() + "Expected a long but was " + (String)localObject1 + " at line " + i + " column " + j + " path " + str);
      }
    }
    else
    {
      if ((i == 8) || (i == 9))
      {
        if (i == 8) {}
        for (char c = '\'';; c = '"')
        {
          this.zzcpN = zzd(c);
          try
          {
            l = Long.parseLong(this.zzcpN);
            this.zzcpK = 0;
            localObject1 = this.zzcpR;
            i = this.zzcpP - 1;
            localObject1[i] += 1;
            return l;
          }
          catch (NumberFormatException localNumberFormatException) {}
          break;
        }
      }
      localObject2 = String.valueOf(zzabQ());
      i = getLineNumber();
      j = getColumnNumber();
      str = getPath();
      throw new IllegalStateException(String.valueOf(localObject2).length() + 69 + String.valueOf(str).length() + "Expected a long but was " + (String)localObject2 + " at line " + i + " column " + j + " path " + str);
    }
    this.zzcpN = null;
    this.zzcpK = 0;
    Object localObject2 = this.zzcpR;
    i = this.zzcpP - 1;
    localObject2[i] += 1;
    return l;
  }
  
  public String nextName()
    throws IOException
  {
    int j = this.zzcpK;
    int i = j;
    if (j == 0) {
      i = zzacd();
    }
    if (i == 14) {
      str1 = zzacg();
    }
    for (;;)
    {
      this.zzcpK = 0;
      this.zzcpQ[(this.zzcpP - 1)] = str1;
      return str1;
      if (i == 12)
      {
        str1 = zzd('\'');
      }
      else
      {
        if (i != 13) {
          break;
        }
        str1 = zzd('"');
      }
    }
    String str1 = String.valueOf(zzabQ());
    i = getLineNumber();
    j = getColumnNumber();
    String str2 = getPath();
    throw new IllegalStateException(String.valueOf(str1).length() + 69 + String.valueOf(str2).length() + "Expected a name but was " + str1 + " at line " + i + " column " + j + " path " + str2);
  }
  
  public void nextNull()
    throws IOException
  {
    int j = this.zzcpK;
    int i = j;
    if (j == 0) {
      i = zzacd();
    }
    if (i == 7)
    {
      this.zzcpK = 0;
      localObject = this.zzcpR;
      i = this.zzcpP - 1;
      localObject[i] += 1;
      return;
    }
    Object localObject = String.valueOf(zzabQ());
    i = getLineNumber();
    j = getColumnNumber();
    String str = getPath();
    throw new IllegalStateException(String.valueOf(localObject).length() + 67 + String.valueOf(str).length() + "Expected null but was " + (String)localObject + " at line " + i + " column " + j + " path " + str);
  }
  
  public String nextString()
    throws IOException
  {
    int j = this.zzcpK;
    int i = j;
    if (j == 0) {
      i = zzacd();
    }
    if (i == 10) {
      str = zzacg();
    }
    for (;;)
    {
      this.zzcpK = 0;
      localObject = this.zzcpR;
      i = this.zzcpP - 1;
      localObject[i] += 1;
      return str;
      if (i == 8)
      {
        str = zzd('\'');
      }
      else if (i == 9)
      {
        str = zzd('"');
      }
      else if (i == 11)
      {
        str = this.zzcpN;
        this.zzcpN = null;
      }
      else if (i == 15)
      {
        str = Long.toString(this.zzcpL);
      }
      else
      {
        if (i != 16) {
          break;
        }
        str = new String(this.zzcpH, this.pos, this.zzcpM);
        this.pos += this.zzcpM;
      }
    }
    String str = String.valueOf(zzabQ());
    i = getLineNumber();
    j = getColumnNumber();
    Object localObject = getPath();
    throw new IllegalStateException(String.valueOf(str).length() + 71 + String.valueOf(localObject).length() + "Expected a string but was " + str + " at line " + i + " column " + j + " path " + (String)localObject);
  }
  
  public final void setLenient(boolean paramBoolean)
  {
    this.zzcpG = paramBoolean;
  }
  
  public void skipValue()
    throws IOException
  {
    int j = 0;
    int i = this.zzcpK;
    int k = i;
    if (i == 0) {
      k = zzacd();
    }
    if (k == 3)
    {
      zzqd(1);
      i = j + 1;
    }
    for (;;)
    {
      this.zzcpK = 0;
      j = i;
      if (i != 0) {
        break;
      }
      int[] arrayOfInt = this.zzcpR;
      i = this.zzcpP - 1;
      arrayOfInt[i] += 1;
      this.zzcpQ[(this.zzcpP - 1)] = "null";
      return;
      if (k == 1)
      {
        zzqd(3);
        i = j + 1;
      }
      else if (k == 4)
      {
        this.zzcpP -= 1;
        i = j - 1;
      }
      else if (k == 2)
      {
        this.zzcpP -= 1;
        i = j - 1;
      }
      else if ((k == 14) || (k == 10))
      {
        zzach();
        i = j;
      }
      else if ((k == 8) || (k == 12))
      {
        zze('\'');
        i = j;
      }
      else if ((k == 9) || (k == 13))
      {
        zze('"');
        i = j;
      }
      else
      {
        i = j;
        if (k == 16)
        {
          this.pos += this.zzcpM;
          i = j;
        }
      }
    }
  }
  
  public String toString()
  {
    String str = String.valueOf(getClass().getSimpleName());
    int i = getLineNumber();
    int j = getColumnNumber();
    return String.valueOf(str).length() + 39 + str + " at line " + i + " column " + j;
  }
  
  public zzbtj zzabQ()
    throws IOException
  {
    int j = this.zzcpK;
    int i = j;
    if (j == 0) {
      i = zzacd();
    }
    switch (i)
    {
    default: 
      throw new AssertionError();
    case 1: 
      return zzbtj.zzcpU;
    case 2: 
      return zzbtj.zzcpV;
    case 3: 
      return zzbtj.zzcpS;
    case 4: 
      return zzbtj.zzcpT;
    case 12: 
    case 13: 
    case 14: 
      return zzbtj.zzcpW;
    case 5: 
    case 6: 
      return zzbtj.zzcpZ;
    case 7: 
      return zzbtj.zzcqa;
    case 8: 
    case 9: 
    case 10: 
    case 11: 
      return zzbtj.zzcpX;
    case 15: 
    case 16: 
      return zzbtj.zzcpY;
    }
    return zzbtj.zzcqb;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbti.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */