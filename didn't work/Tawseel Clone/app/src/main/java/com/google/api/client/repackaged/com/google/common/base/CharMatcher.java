package com.google.api.client.repackaged.com.google.common.base;

import com.google.api.client.repackaged.com.google.common.annotations.Beta;
import com.google.api.client.repackaged.com.google.common.annotations.GwtCompatible;
import com.google.api.client.repackaged.com.google.common.annotations.GwtIncompatible;
import java.util.Arrays;
import java.util.BitSet;
import javax.annotation.CheckReturnValue;

@Beta
@GwtCompatible(emulated=true)
public abstract class CharMatcher
  implements Predicate<Character>
{
  public static final CharMatcher ANY;
  public static final CharMatcher ASCII;
  public static final CharMatcher BREAKING_WHITESPACE = new CharMatcher()
  {
    public boolean matches(char paramAnonymousChar)
    {
      switch (paramAnonymousChar)
      {
      default: 
        if ((paramAnonymousChar < ' ') || (paramAnonymousChar > ' ')) {
          break;
        }
      case '\t': 
      case '\n': 
      case '\013': 
      case '\f': 
      case '\r': 
      case ' ': 
      case '': 
      case ' ': 
      case ' ': 
      case ' ': 
      case ' ': 
      case '　': 
        return true;
      case ' ': 
        return false;
      }
      return false;
    }
    
    public String toString()
    {
      return "CharMatcher.BREAKING_WHITESPACE";
    }
  };
  public static final CharMatcher DIGIT;
  private static final int DISTINCT_CHARS = 65536;
  public static final CharMatcher INVISIBLE;
  public static final CharMatcher JAVA_DIGIT;
  public static final CharMatcher JAVA_ISO_CONTROL;
  public static final CharMatcher JAVA_LETTER;
  public static final CharMatcher JAVA_LETTER_OR_DIGIT;
  public static final CharMatcher JAVA_LOWER_CASE;
  public static final CharMatcher JAVA_UPPER_CASE;
  private static final String NINES;
  public static final CharMatcher NONE;
  public static final CharMatcher SINGLE_WIDTH;
  public static final CharMatcher WHITESPACE = new FastMatcher("WHITESPACE")
  {
    public boolean matches(char paramAnonymousChar)
    {
      return " 　\r   　 \013　   　 \t     \f 　 　　 \n 　".charAt(1682554634 * paramAnonymousChar >>> WHITESPACE_SHIFT) == paramAnonymousChar;
    }
    
    @GwtIncompatible("java.util.BitSet")
    void setBits(BitSet paramAnonymousBitSet)
    {
      int i = 0;
      while (i < " 　\r   　 \013　   　 \t     \f 　 　　 \n 　".length())
      {
        paramAnonymousBitSet.set(" 　\r   　 \013　   　 \t     \f 　 　　 \n 　".charAt(i));
        i += 1;
      }
    }
  };
  static final int WHITESPACE_MULTIPLIER = 1682554634;
  static final int WHITESPACE_SHIFT;
  static final String WHITESPACE_TABLE = " 　\r   　 \013　   　 \t     \f 　 　　 \n 　";
  private static final String ZEROES = "0٠۰߀०০੦૦୦௦౦೦൦๐໐༠၀႐០᠐᥆᧐᭐᮰᱀᱐꘠꣐꤀꩐０";
  final String description;
  
  static
  {
    ASCII = inRange('\000', '', "CharMatcher.ASCII");
    StringBuilder localStringBuilder = new StringBuilder("0٠۰߀०০੦૦୦௦౦೦൦๐໐༠၀႐០᠐᥆᧐᭐᮰᱀᱐꘠꣐꤀꩐０".length());
    int i = 0;
    while (i < "0٠۰߀०০੦૦୦௦౦೦൦๐໐༠၀႐០᠐᥆᧐᭐᮰᱀᱐꘠꣐꤀꩐０".length())
    {
      localStringBuilder.append((char)("0٠۰߀०০੦૦୦௦౦೦൦๐໐༠၀႐០᠐᥆᧐᭐᮰᱀᱐꘠꣐꤀꩐０".charAt(i) + '\t'));
      i += 1;
    }
    NINES = localStringBuilder.toString();
    DIGIT = new RangesMatcher("CharMatcher.DIGIT", "0٠۰߀०০੦૦୦௦౦೦൦๐໐༠၀႐០᠐᥆᧐᭐᮰᱀᱐꘠꣐꤀꩐０".toCharArray(), NINES.toCharArray());
    JAVA_DIGIT = new CharMatcher("CharMatcher.JAVA_DIGIT")
    {
      public boolean matches(char paramAnonymousChar)
      {
        return Character.isDigit(paramAnonymousChar);
      }
    };
    JAVA_LETTER = new CharMatcher("CharMatcher.JAVA_LETTER")
    {
      public boolean matches(char paramAnonymousChar)
      {
        return Character.isLetter(paramAnonymousChar);
      }
    };
    JAVA_LETTER_OR_DIGIT = new CharMatcher("CharMatcher.JAVA_LETTER_OR_DIGIT")
    {
      public boolean matches(char paramAnonymousChar)
      {
        return Character.isLetterOrDigit(paramAnonymousChar);
      }
    };
    JAVA_UPPER_CASE = new CharMatcher("CharMatcher.JAVA_UPPER_CASE")
    {
      public boolean matches(char paramAnonymousChar)
      {
        return Character.isUpperCase(paramAnonymousChar);
      }
    };
    JAVA_LOWER_CASE = new CharMatcher("CharMatcher.JAVA_LOWER_CASE")
    {
      public boolean matches(char paramAnonymousChar)
      {
        return Character.isLowerCase(paramAnonymousChar);
      }
    };
    JAVA_ISO_CONTROL = inRange('\000', '\037').or(inRange('', '')).withToString("CharMatcher.JAVA_ISO_CONTROL");
    INVISIBLE = new RangesMatcher("CharMatcher.INVISIBLE", "\000­؀؜۝܏ ᠎   ⁦⁧⁨⁩⁪　?﻿￹￺".toCharArray(), "  ­؄؜۝܏ ᠎‏ ⁤⁦⁧⁨⁩⁯　﻿￹￻".toCharArray());
    SINGLE_WIDTH = new RangesMatcher("CharMatcher.SINGLE_WIDTH", "\000־א׳؀ݐ฀Ḁ℀ﭐﹰ｡".toCharArray(), "ӹ־ת״ۿݿ๿₯℺﷿﻿ￜ".toCharArray());
    ANY = new FastMatcher("CharMatcher.ANY")
    {
      public CharMatcher and(CharMatcher paramAnonymousCharMatcher)
      {
        return (CharMatcher)Preconditions.checkNotNull(paramAnonymousCharMatcher);
      }
      
      public String collapseFrom(CharSequence paramAnonymousCharSequence, char paramAnonymousChar)
      {
        if (paramAnonymousCharSequence.length() == 0) {
          return "";
        }
        return String.valueOf(paramAnonymousChar);
      }
      
      public int countIn(CharSequence paramAnonymousCharSequence)
      {
        return paramAnonymousCharSequence.length();
      }
      
      public int indexIn(CharSequence paramAnonymousCharSequence)
      {
        if (paramAnonymousCharSequence.length() == 0) {
          return -1;
        }
        return 0;
      }
      
      public int indexIn(CharSequence paramAnonymousCharSequence, int paramAnonymousInt)
      {
        int j = paramAnonymousCharSequence.length();
        Preconditions.checkPositionIndex(paramAnonymousInt, j);
        int i = paramAnonymousInt;
        if (paramAnonymousInt == j) {
          i = -1;
        }
        return i;
      }
      
      public int lastIndexIn(CharSequence paramAnonymousCharSequence)
      {
        return paramAnonymousCharSequence.length() - 1;
      }
      
      public boolean matches(char paramAnonymousChar)
      {
        return true;
      }
      
      public boolean matchesAllOf(CharSequence paramAnonymousCharSequence)
      {
        Preconditions.checkNotNull(paramAnonymousCharSequence);
        return true;
      }
      
      public boolean matchesNoneOf(CharSequence paramAnonymousCharSequence)
      {
        return paramAnonymousCharSequence.length() == 0;
      }
      
      public CharMatcher negate()
      {
        return NONE;
      }
      
      public CharMatcher or(CharMatcher paramAnonymousCharMatcher)
      {
        Preconditions.checkNotNull(paramAnonymousCharMatcher);
        return this;
      }
      
      public String removeFrom(CharSequence paramAnonymousCharSequence)
      {
        Preconditions.checkNotNull(paramAnonymousCharSequence);
        return "";
      }
      
      public String replaceFrom(CharSequence paramAnonymousCharSequence, char paramAnonymousChar)
      {
        paramAnonymousCharSequence = new char[paramAnonymousCharSequence.length()];
        Arrays.fill(paramAnonymousCharSequence, paramAnonymousChar);
        return new String(paramAnonymousCharSequence);
      }
      
      public String replaceFrom(CharSequence paramAnonymousCharSequence1, CharSequence paramAnonymousCharSequence2)
      {
        StringBuilder localStringBuilder = new StringBuilder(paramAnonymousCharSequence1.length() * paramAnonymousCharSequence2.length());
        int i = 0;
        while (i < paramAnonymousCharSequence1.length())
        {
          localStringBuilder.append(paramAnonymousCharSequence2);
          i += 1;
        }
        return localStringBuilder.toString();
      }
      
      public String trimFrom(CharSequence paramAnonymousCharSequence)
      {
        Preconditions.checkNotNull(paramAnonymousCharSequence);
        return "";
      }
    };
    NONE = new FastMatcher("CharMatcher.NONE")
    {
      public CharMatcher and(CharMatcher paramAnonymousCharMatcher)
      {
        Preconditions.checkNotNull(paramAnonymousCharMatcher);
        return this;
      }
      
      public String collapseFrom(CharSequence paramAnonymousCharSequence, char paramAnonymousChar)
      {
        return paramAnonymousCharSequence.toString();
      }
      
      public int countIn(CharSequence paramAnonymousCharSequence)
      {
        Preconditions.checkNotNull(paramAnonymousCharSequence);
        return 0;
      }
      
      public int indexIn(CharSequence paramAnonymousCharSequence)
      {
        Preconditions.checkNotNull(paramAnonymousCharSequence);
        return -1;
      }
      
      public int indexIn(CharSequence paramAnonymousCharSequence, int paramAnonymousInt)
      {
        Preconditions.checkPositionIndex(paramAnonymousInt, paramAnonymousCharSequence.length());
        return -1;
      }
      
      public int lastIndexIn(CharSequence paramAnonymousCharSequence)
      {
        Preconditions.checkNotNull(paramAnonymousCharSequence);
        return -1;
      }
      
      public boolean matches(char paramAnonymousChar)
      {
        return false;
      }
      
      public boolean matchesAllOf(CharSequence paramAnonymousCharSequence)
      {
        return paramAnonymousCharSequence.length() == 0;
      }
      
      public boolean matchesNoneOf(CharSequence paramAnonymousCharSequence)
      {
        Preconditions.checkNotNull(paramAnonymousCharSequence);
        return true;
      }
      
      public CharMatcher negate()
      {
        return ANY;
      }
      
      public CharMatcher or(CharMatcher paramAnonymousCharMatcher)
      {
        return (CharMatcher)Preconditions.checkNotNull(paramAnonymousCharMatcher);
      }
      
      public String removeFrom(CharSequence paramAnonymousCharSequence)
      {
        return paramAnonymousCharSequence.toString();
      }
      
      public String replaceFrom(CharSequence paramAnonymousCharSequence, char paramAnonymousChar)
      {
        return paramAnonymousCharSequence.toString();
      }
      
      public String replaceFrom(CharSequence paramAnonymousCharSequence1, CharSequence paramAnonymousCharSequence2)
      {
        Preconditions.checkNotNull(paramAnonymousCharSequence2);
        return paramAnonymousCharSequence1.toString();
      }
      
      public String trimFrom(CharSequence paramAnonymousCharSequence)
      {
        return paramAnonymousCharSequence.toString();
      }
      
      public String trimLeadingFrom(CharSequence paramAnonymousCharSequence)
      {
        return paramAnonymousCharSequence.toString();
      }
      
      public String trimTrailingFrom(CharSequence paramAnonymousCharSequence)
      {
        return paramAnonymousCharSequence.toString();
      }
    };
    WHITESPACE_SHIFT = Integer.numberOfLeadingZeros(" 　\r   　 \013　   　 \t     \f 　 　　 \n 　".length() - 1);
  }
  
  protected CharMatcher()
  {
    this.description = super.toString();
  }
  
  CharMatcher(String paramString)
  {
    this.description = paramString;
  }
  
  public static CharMatcher anyOf(final CharSequence paramCharSequence)
  {
    StringBuilder localStringBuilder;
    int j;
    int i;
    switch (paramCharSequence.length())
    {
    default: 
      paramCharSequence = paramCharSequence.toString().toCharArray();
      Arrays.sort(paramCharSequence);
      localStringBuilder = new StringBuilder("CharMatcher.anyOf(\"");
      j = paramCharSequence.length;
      i = 0;
    }
    while (i < j)
    {
      localStringBuilder.append(showCharacter(paramCharSequence[i]));
      i += 1;
      continue;
      return NONE;
      return is(paramCharSequence.charAt(0));
      return isEither(paramCharSequence.charAt(0), paramCharSequence.charAt(1));
    }
    localStringBuilder.append("\")");
    new CharMatcher(localStringBuilder.toString())
    {
      public boolean matches(char paramAnonymousChar)
      {
        return Arrays.binarySearch(paramCharSequence, paramAnonymousChar) >= 0;
      }
      
      @GwtIncompatible("java.util.BitSet")
      void setBits(BitSet paramAnonymousBitSet)
      {
        char[] arrayOfChar = paramCharSequence;
        int j = arrayOfChar.length;
        int i = 0;
        while (i < j)
        {
          paramAnonymousBitSet.set(arrayOfChar[i]);
          i += 1;
        }
      }
    };
  }
  
  private String finishCollapseFrom(CharSequence paramCharSequence, int paramInt1, int paramInt2, char paramChar, StringBuilder paramStringBuilder, boolean paramBoolean)
  {
    boolean bool = paramBoolean;
    if (paramInt1 < paramInt2)
    {
      char c = paramCharSequence.charAt(paramInt1);
      if (matches(c))
      {
        paramBoolean = bool;
        if (!bool) {
          paramStringBuilder.append(paramChar);
        }
      }
      for (paramBoolean = true;; paramBoolean = false)
      {
        paramInt1 += 1;
        bool = paramBoolean;
        break;
        paramStringBuilder.append(c);
      }
    }
    return paramStringBuilder.toString();
  }
  
  public static CharMatcher forPredicate(final Predicate<? super Character> paramPredicate)
  {
    Preconditions.checkNotNull(paramPredicate);
    if ((paramPredicate instanceof CharMatcher)) {
      return (CharMatcher)paramPredicate;
    }
    new CharMatcher("CharMatcher.forPredicate(" + paramPredicate + ")")
    {
      public boolean apply(Character paramAnonymousCharacter)
      {
        return paramPredicate.apply(Preconditions.checkNotNull(paramAnonymousCharacter));
      }
      
      public boolean matches(char paramAnonymousChar)
      {
        return paramPredicate.apply(Character.valueOf(paramAnonymousChar));
      }
    };
  }
  
  public static CharMatcher inRange(char paramChar1, char paramChar2)
  {
    if (paramChar2 >= paramChar1) {}
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkArgument(bool);
      return inRange(paramChar1, paramChar2, "CharMatcher.inRange('" + showCharacter(paramChar1) + "', '" + showCharacter(paramChar2) + "')");
    }
  }
  
  static CharMatcher inRange(final char paramChar1, final char paramChar2, String paramString)
  {
    new FastMatcher(paramString)
    {
      public boolean matches(char paramAnonymousChar)
      {
        return (paramChar1 <= paramAnonymousChar) && (paramAnonymousChar <= paramChar2);
      }
      
      @GwtIncompatible("java.util.BitSet")
      void setBits(BitSet paramAnonymousBitSet)
      {
        paramAnonymousBitSet.set(paramChar1, paramChar2 + '\001');
      }
    };
  }
  
  public static CharMatcher is(final char paramChar)
  {
    new FastMatcher("CharMatcher.is('" + showCharacter(paramChar) + "')")
    {
      public CharMatcher and(CharMatcher paramAnonymousCharMatcher)
      {
        if (paramAnonymousCharMatcher.matches(paramChar)) {
          return this;
        }
        return NONE;
      }
      
      public boolean matches(char paramAnonymousChar)
      {
        return paramAnonymousChar == paramChar;
      }
      
      public CharMatcher negate()
      {
        return isNot(paramChar);
      }
      
      public CharMatcher or(CharMatcher paramAnonymousCharMatcher)
      {
        if (paramAnonymousCharMatcher.matches(paramChar)) {
          return paramAnonymousCharMatcher;
        }
        return super.or(paramAnonymousCharMatcher);
      }
      
      public String replaceFrom(CharSequence paramAnonymousCharSequence, char paramAnonymousChar)
      {
        return paramAnonymousCharSequence.toString().replace(paramChar, paramAnonymousChar);
      }
      
      @GwtIncompatible("java.util.BitSet")
      void setBits(BitSet paramAnonymousBitSet)
      {
        paramAnonymousBitSet.set(paramChar);
      }
    };
  }
  
  private static CharMatcher isEither(final char paramChar1, final char paramChar2)
  {
    new FastMatcher("CharMatcher.anyOf(\"" + showCharacter(paramChar1) + showCharacter(paramChar2) + "\")")
    {
      public boolean matches(char paramAnonymousChar)
      {
        return (paramAnonymousChar == paramChar1) || (paramAnonymousChar == paramChar2);
      }
      
      @GwtIncompatible("java.util.BitSet")
      void setBits(BitSet paramAnonymousBitSet)
      {
        paramAnonymousBitSet.set(paramChar1);
        paramAnonymousBitSet.set(paramChar2);
      }
    };
  }
  
  public static CharMatcher isNot(final char paramChar)
  {
    new FastMatcher("CharMatcher.isNot('" + showCharacter(paramChar) + "')")
    {
      public CharMatcher and(CharMatcher paramAnonymousCharMatcher)
      {
        CharMatcher localCharMatcher = paramAnonymousCharMatcher;
        if (paramAnonymousCharMatcher.matches(paramChar)) {
          localCharMatcher = super.and(paramAnonymousCharMatcher);
        }
        return localCharMatcher;
      }
      
      public boolean matches(char paramAnonymousChar)
      {
        return paramAnonymousChar != paramChar;
      }
      
      public CharMatcher negate()
      {
        return is(paramChar);
      }
      
      public CharMatcher or(CharMatcher paramAnonymousCharMatcher)
      {
        Object localObject = this;
        if (paramAnonymousCharMatcher.matches(paramChar)) {
          localObject = ANY;
        }
        return (CharMatcher)localObject;
      }
      
      @GwtIncompatible("java.util.BitSet")
      void setBits(BitSet paramAnonymousBitSet)
      {
        paramAnonymousBitSet.set(0, paramChar);
        paramAnonymousBitSet.set(paramChar + '\001', 65536);
      }
    };
  }
  
  @GwtIncompatible("SmallCharMatcher")
  private static boolean isSmall(int paramInt1, int paramInt2)
  {
    return (paramInt1 <= 1023) && (paramInt2 > paramInt1 * 4 * 16);
  }
  
  public static CharMatcher noneOf(CharSequence paramCharSequence)
  {
    return anyOf(paramCharSequence).negate();
  }
  
  @GwtIncompatible("java.util.BitSet")
  private static CharMatcher precomputedPositive(int paramInt, BitSet paramBitSet, String paramString)
  {
    switch (paramInt)
    {
    default: 
      if (isSmall(paramInt, paramBitSet.length())) {
        return SmallCharMatcher.from(paramBitSet, paramString);
      }
      break;
    case 0: 
      return NONE;
    case 1: 
      return is((char)paramBitSet.nextSetBit(0));
    case 2: 
      char c = (char)paramBitSet.nextSetBit(0);
      return isEither(c, (char)paramBitSet.nextSetBit(c + '\001'));
    }
    return new BitSetMatcher(paramBitSet, paramString, null);
  }
  
  private static String showCharacter(char paramChar)
  {
    char[] arrayOfChar = new char[6];
    char[] tmp6_5 = arrayOfChar;
    tmp6_5[0] = 92;
    char[] tmp12_6 = tmp6_5;
    tmp12_6[1] = 117;
    char[] tmp18_12 = tmp12_6;
    tmp18_12[2] = 0;
    char[] tmp24_18 = tmp18_12;
    tmp24_18[3] = 0;
    char[] tmp30_24 = tmp24_18;
    tmp30_24[4] = 0;
    char[] tmp36_30 = tmp30_24;
    tmp36_30[5] = 0;
    tmp36_30;
    char c = '\000';
    int i = paramChar;
    paramChar = c;
    while (paramChar < '\004')
    {
      arrayOfChar[('\005' - paramChar)] = "0123456789ABCDEF".charAt(i & 0xF);
      i = (char)(i >> 4);
      paramChar += '\001';
    }
    return String.copyValueOf(arrayOfChar);
  }
  
  public CharMatcher and(CharMatcher paramCharMatcher)
  {
    return new And(this, (CharMatcher)Preconditions.checkNotNull(paramCharMatcher));
  }
  
  @Deprecated
  public boolean apply(Character paramCharacter)
  {
    return matches(paramCharacter.charValue());
  }
  
  @CheckReturnValue
  public String collapseFrom(CharSequence paramCharSequence, char paramChar)
  {
    int k = paramCharSequence.length();
    int i = 0;
    while (i < k)
    {
      char c = paramCharSequence.charAt(i);
      int j = i;
      if (matches(c))
      {
        if ((c == paramChar) && ((i == k - 1) || (!matches(paramCharSequence.charAt(i + 1))))) {
          j = i + 1;
        }
      }
      else
      {
        i = j + 1;
        continue;
      }
      return finishCollapseFrom(paramCharSequence, i + 1, k, paramChar, new StringBuilder(k).append(paramCharSequence.subSequence(0, i)).append(paramChar), true);
    }
    return paramCharSequence.toString();
  }
  
  public int countIn(CharSequence paramCharSequence)
  {
    int j = 0;
    int i = 0;
    while (i < paramCharSequence.length())
    {
      int k = j;
      if (matches(paramCharSequence.charAt(i))) {
        k = j + 1;
      }
      i += 1;
      j = k;
    }
    return j;
  }
  
  public int indexIn(CharSequence paramCharSequence)
  {
    int j = paramCharSequence.length();
    int i = 0;
    while (i < j)
    {
      if (matches(paramCharSequence.charAt(i))) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  public int indexIn(CharSequence paramCharSequence, int paramInt)
  {
    int i = paramCharSequence.length();
    Preconditions.checkPositionIndex(paramInt, i);
    while (paramInt < i)
    {
      if (matches(paramCharSequence.charAt(paramInt))) {
        return paramInt;
      }
      paramInt += 1;
    }
    return -1;
  }
  
  public int lastIndexIn(CharSequence paramCharSequence)
  {
    int i = paramCharSequence.length() - 1;
    while (i >= 0)
    {
      if (matches(paramCharSequence.charAt(i))) {
        return i;
      }
      i -= 1;
    }
    return -1;
  }
  
  public abstract boolean matches(char paramChar);
  
  public boolean matchesAllOf(CharSequence paramCharSequence)
  {
    int i = paramCharSequence.length() - 1;
    while (i >= 0)
    {
      if (!matches(paramCharSequence.charAt(i))) {
        return false;
      }
      i -= 1;
    }
    return true;
  }
  
  public boolean matchesAnyOf(CharSequence paramCharSequence)
  {
    return !matchesNoneOf(paramCharSequence);
  }
  
  public boolean matchesNoneOf(CharSequence paramCharSequence)
  {
    return indexIn(paramCharSequence) == -1;
  }
  
  public CharMatcher negate()
  {
    return new NegatedMatcher(this);
  }
  
  public CharMatcher or(CharMatcher paramCharMatcher)
  {
    return new Or(this, (CharMatcher)Preconditions.checkNotNull(paramCharMatcher));
  }
  
  public CharMatcher precomputed()
  {
    return Platform.precomputeCharMatcher(this);
  }
  
  @GwtIncompatible("java.util.BitSet")
  CharMatcher precomputedInternal()
  {
    BitSet localBitSet = new BitSet();
    setBits(localBitSet);
    int i = localBitSet.cardinality();
    if (i * 2 <= 65536) {
      return precomputedPositive(i, localBitSet, this.description);
    }
    localBitSet.flip(0, 65536);
    if (this.description.endsWith(".negate()")) {}
    for (String str = this.description.substring(0, this.description.length() - ".negate()".length());; str = this.description + ".negate()") {
      return new NegatedFastMatcher(toString(), precomputedPositive(65536 - i, localBitSet, str));
    }
  }
  
  @CheckReturnValue
  public String removeFrom(CharSequence paramCharSequence)
  {
    paramCharSequence = paramCharSequence.toString();
    int i = indexIn(paramCharSequence);
    if (i == -1) {
      return paramCharSequence;
    }
    paramCharSequence = paramCharSequence.toCharArray();
    int j = 1;
    i += 1;
    for (;;)
    {
      if (i == paramCharSequence.length) {
        return new String(paramCharSequence, 0, i - j);
      }
      if (matches(paramCharSequence[i]))
      {
        j += 1;
        break;
      }
      paramCharSequence[(i - j)] = paramCharSequence[i];
      i += 1;
    }
  }
  
  @CheckReturnValue
  public String replaceFrom(CharSequence paramCharSequence, char paramChar)
  {
    paramCharSequence = paramCharSequence.toString();
    int i = indexIn(paramCharSequence);
    if (i == -1) {
      return paramCharSequence;
    }
    paramCharSequence = paramCharSequence.toCharArray();
    paramCharSequence[i] = paramChar;
    i += 1;
    while (i < paramCharSequence.length)
    {
      if (matches(paramCharSequence[i])) {
        paramCharSequence[i] = paramChar;
      }
      i += 1;
    }
    return new String(paramCharSequence);
  }
  
  @CheckReturnValue
  public String replaceFrom(CharSequence paramCharSequence1, CharSequence paramCharSequence2)
  {
    int i = paramCharSequence2.length();
    if (i == 0) {
      paramCharSequence1 = removeFrom(paramCharSequence1);
    }
    String str;
    do
    {
      return paramCharSequence1;
      if (i == 1) {
        return replaceFrom(paramCharSequence1, paramCharSequence2.charAt(0));
      }
      str = paramCharSequence1.toString();
      i = indexIn(str);
      paramCharSequence1 = str;
    } while (i == -1);
    int n = str.length();
    paramCharSequence1 = new StringBuilder(n * 3 / 2 + 16);
    int j = 0;
    int k;
    int m;
    do
    {
      paramCharSequence1.append(str, j, i);
      paramCharSequence1.append(paramCharSequence2);
      k = i + 1;
      m = indexIn(str, k);
      j = k;
      i = m;
    } while (m != -1);
    paramCharSequence1.append(str, k, n);
    return paramCharSequence1.toString();
  }
  
  @CheckReturnValue
  public String retainFrom(CharSequence paramCharSequence)
  {
    return negate().removeFrom(paramCharSequence);
  }
  
  @GwtIncompatible("java.util.BitSet")
  void setBits(BitSet paramBitSet)
  {
    int i = 65535;
    while (i >= 0)
    {
      if (matches((char)i)) {
        paramBitSet.set(i);
      }
      i -= 1;
    }
  }
  
  public String toString()
  {
    return this.description;
  }
  
  @CheckReturnValue
  public String trimAndCollapseFrom(CharSequence paramCharSequence, char paramChar)
  {
    int k = paramCharSequence.length();
    int i = 0;
    while ((i < k) && (matches(paramCharSequence.charAt(i)))) {
      i += 1;
    }
    int j = k - 1;
    while ((j > i) && (matches(paramCharSequence.charAt(j)))) {
      j -= 1;
    }
    if ((i == 0) && (j == k - 1)) {
      return collapseFrom(paramCharSequence, paramChar);
    }
    return finishCollapseFrom(paramCharSequence, i, j + 1, paramChar, new StringBuilder(j + 1 - i), false);
  }
  
  @CheckReturnValue
  public String trimFrom(CharSequence paramCharSequence)
  {
    int j = paramCharSequence.length();
    int i = 0;
    if ((i >= j) || (!matches(paramCharSequence.charAt(i)))) {
      j -= 1;
    }
    for (;;)
    {
      if ((j <= i) || (!matches(paramCharSequence.charAt(j))))
      {
        return paramCharSequence.subSequence(i, j + 1).toString();
        i += 1;
        break;
      }
      j -= 1;
    }
  }
  
  @CheckReturnValue
  public String trimLeadingFrom(CharSequence paramCharSequence)
  {
    int j = paramCharSequence.length();
    int i = 0;
    while (i < j)
    {
      if (!matches(paramCharSequence.charAt(i))) {
        return paramCharSequence.subSequence(i, j).toString();
      }
      i += 1;
    }
    return "";
  }
  
  @CheckReturnValue
  public String trimTrailingFrom(CharSequence paramCharSequence)
  {
    int i = paramCharSequence.length() - 1;
    while (i >= 0)
    {
      if (!matches(paramCharSequence.charAt(i))) {
        return paramCharSequence.subSequence(0, i + 1).toString();
      }
      i -= 1;
    }
    return "";
  }
  
  CharMatcher withToString(String paramString)
  {
    throw new UnsupportedOperationException();
  }
  
  private static class And
    extends CharMatcher
  {
    final CharMatcher first;
    final CharMatcher second;
    
    And(CharMatcher paramCharMatcher1, CharMatcher paramCharMatcher2)
    {
      this(paramCharMatcher1, paramCharMatcher2, "CharMatcher.and(" + paramCharMatcher1 + ", " + paramCharMatcher2 + ")");
    }
    
    And(CharMatcher paramCharMatcher1, CharMatcher paramCharMatcher2, String paramString)
    {
      super();
      this.first = ((CharMatcher)Preconditions.checkNotNull(paramCharMatcher1));
      this.second = ((CharMatcher)Preconditions.checkNotNull(paramCharMatcher2));
    }
    
    public boolean matches(char paramChar)
    {
      return (this.first.matches(paramChar)) && (this.second.matches(paramChar));
    }
    
    @GwtIncompatible("java.util.BitSet")
    void setBits(BitSet paramBitSet)
    {
      BitSet localBitSet1 = new BitSet();
      this.first.setBits(localBitSet1);
      BitSet localBitSet2 = new BitSet();
      this.second.setBits(localBitSet2);
      localBitSet1.and(localBitSet2);
      paramBitSet.or(localBitSet1);
    }
    
    CharMatcher withToString(String paramString)
    {
      return new And(this.first, this.second, paramString);
    }
  }
  
  @GwtIncompatible("java.util.BitSet")
  private static class BitSetMatcher
    extends CharMatcher.FastMatcher
  {
    private final BitSet table;
    
    private BitSetMatcher(BitSet paramBitSet, String paramString)
    {
      super();
      paramString = paramBitSet;
      if (paramBitSet.length() + 64 < paramBitSet.size()) {
        paramString = (BitSet)paramBitSet.clone();
      }
      this.table = paramString;
    }
    
    public boolean matches(char paramChar)
    {
      return this.table.get(paramChar);
    }
    
    void setBits(BitSet paramBitSet)
    {
      paramBitSet.or(this.table);
    }
  }
  
  static abstract class FastMatcher
    extends CharMatcher
  {
    FastMatcher() {}
    
    FastMatcher(String paramString)
    {
      super();
    }
    
    public CharMatcher negate()
    {
      return new CharMatcher.NegatedFastMatcher(this);
    }
    
    public final CharMatcher precomputed()
    {
      return this;
    }
  }
  
  static final class NegatedFastMatcher
    extends CharMatcher.NegatedMatcher
  {
    NegatedFastMatcher(CharMatcher paramCharMatcher)
    {
      super();
    }
    
    NegatedFastMatcher(String paramString, CharMatcher paramCharMatcher)
    {
      super(paramCharMatcher);
    }
    
    public final CharMatcher precomputed()
    {
      return this;
    }
    
    CharMatcher withToString(String paramString)
    {
      return new NegatedFastMatcher(paramString, this.original);
    }
  }
  
  private static class NegatedMatcher
    extends CharMatcher
  {
    final CharMatcher original;
    
    NegatedMatcher(CharMatcher paramCharMatcher)
    {
      this(paramCharMatcher + ".negate()", paramCharMatcher);
    }
    
    NegatedMatcher(String paramString, CharMatcher paramCharMatcher)
    {
      super();
      this.original = paramCharMatcher;
    }
    
    public int countIn(CharSequence paramCharSequence)
    {
      return paramCharSequence.length() - this.original.countIn(paramCharSequence);
    }
    
    public boolean matches(char paramChar)
    {
      return !this.original.matches(paramChar);
    }
    
    public boolean matchesAllOf(CharSequence paramCharSequence)
    {
      return this.original.matchesNoneOf(paramCharSequence);
    }
    
    public boolean matchesNoneOf(CharSequence paramCharSequence)
    {
      return this.original.matchesAllOf(paramCharSequence);
    }
    
    public CharMatcher negate()
    {
      return this.original;
    }
    
    @GwtIncompatible("java.util.BitSet")
    void setBits(BitSet paramBitSet)
    {
      BitSet localBitSet = new BitSet();
      this.original.setBits(localBitSet);
      localBitSet.flip(0, 65536);
      paramBitSet.or(localBitSet);
    }
    
    CharMatcher withToString(String paramString)
    {
      return new NegatedMatcher(paramString, this.original);
    }
  }
  
  private static class Or
    extends CharMatcher
  {
    final CharMatcher first;
    final CharMatcher second;
    
    Or(CharMatcher paramCharMatcher1, CharMatcher paramCharMatcher2)
    {
      this(paramCharMatcher1, paramCharMatcher2, "CharMatcher.or(" + paramCharMatcher1 + ", " + paramCharMatcher2 + ")");
    }
    
    Or(CharMatcher paramCharMatcher1, CharMatcher paramCharMatcher2, String paramString)
    {
      super();
      this.first = ((CharMatcher)Preconditions.checkNotNull(paramCharMatcher1));
      this.second = ((CharMatcher)Preconditions.checkNotNull(paramCharMatcher2));
    }
    
    public boolean matches(char paramChar)
    {
      return (this.first.matches(paramChar)) || (this.second.matches(paramChar));
    }
    
    @GwtIncompatible("java.util.BitSet")
    void setBits(BitSet paramBitSet)
    {
      this.first.setBits(paramBitSet);
      this.second.setBits(paramBitSet);
    }
    
    CharMatcher withToString(String paramString)
    {
      return new Or(this.first, this.second, paramString);
    }
  }
  
  private static class RangesMatcher
    extends CharMatcher
  {
    private final char[] rangeEnds;
    private final char[] rangeStarts;
    
    RangesMatcher(String paramString, char[] paramArrayOfChar1, char[] paramArrayOfChar2)
    {
      super();
      this.rangeStarts = paramArrayOfChar1;
      this.rangeEnds = paramArrayOfChar2;
      int i;
      if (paramArrayOfChar1.length == paramArrayOfChar2.length)
      {
        bool = true;
        Preconditions.checkArgument(bool);
        i = 0;
        label33:
        if (i >= paramArrayOfChar1.length) {
          return;
        }
        if (paramArrayOfChar1[i] > paramArrayOfChar2[i]) {
          break label104;
        }
        bool = true;
        label54:
        Preconditions.checkArgument(bool);
        if (i + 1 < paramArrayOfChar1.length) {
          if (paramArrayOfChar2[i] >= paramArrayOfChar1[(i + 1)]) {
            break label110;
          }
        }
      }
      label104:
      label110:
      for (boolean bool = true;; bool = false)
      {
        Preconditions.checkArgument(bool);
        i += 1;
        break label33;
        bool = false;
        break;
        bool = false;
        break label54;
      }
    }
    
    public boolean matches(char paramChar)
    {
      int i = Arrays.binarySearch(this.rangeStarts, paramChar);
      if (i >= 0) {}
      do
      {
        return true;
        i = (i ^ 0xFFFFFFFF) - 1;
      } while ((i >= 0) && (paramChar <= this.rangeEnds[i]));
      return false;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\repackaged\com\google\common\base\CharMatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */