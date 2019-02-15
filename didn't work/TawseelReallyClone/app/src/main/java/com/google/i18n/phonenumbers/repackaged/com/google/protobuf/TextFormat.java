package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TextFormat
{
  private static final Printer DEFAULT_PRINTER;
  private static final Parser PARSER = Parser.newBuilder().build();
  private static final Printer SINGLE_LINE_PRINTER;
  private static final Printer UNICODE_PRINTER;
  private static final Logger logger = Logger.getLogger(TextFormat.class.getName());
  
  static
  {
    DEFAULT_PRINTER = new Printer(null);
    SINGLE_LINE_PRINTER = new Printer(null).setSingleLineMode(true);
    UNICODE_PRINTER = new Printer(null).setEscapeNonAscii(false);
  }
  
  private static int digitValue(byte paramByte)
  {
    if ((48 <= paramByte) && (paramByte <= 57)) {
      return paramByte - 48;
    }
    if ((97 <= paramByte) && (paramByte <= 122)) {
      return paramByte - 97 + 10;
    }
    return paramByte - 65 + 10;
  }
  
  static String escapeBytes(ByteString paramByteString)
  {
    escapeBytes(new ByteSequence()
    {
      public byte byteAt(int paramAnonymousInt)
      {
        return this.val$input.byteAt(paramAnonymousInt);
      }
      
      public int size()
      {
        return this.val$input.size();
      }
    });
  }
  
  private static String escapeBytes(ByteSequence paramByteSequence)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramByteSequence.size());
    int i = 0;
    if (i < paramByteSequence.size())
    {
      int j = paramByteSequence.byteAt(i);
      switch (j)
      {
      default: 
        if (j >= 32) {
          localStringBuilder.append((char)j);
        }
        break;
      }
      for (;;)
      {
        i += 1;
        break;
        localStringBuilder.append("\\a");
        continue;
        localStringBuilder.append("\\b");
        continue;
        localStringBuilder.append("\\f");
        continue;
        localStringBuilder.append("\\n");
        continue;
        localStringBuilder.append("\\r");
        continue;
        localStringBuilder.append("\\t");
        continue;
        localStringBuilder.append("\\v");
        continue;
        localStringBuilder.append("\\\\");
        continue;
        localStringBuilder.append("\\'");
        continue;
        localStringBuilder.append("\\\"");
        continue;
        localStringBuilder.append('\\');
        localStringBuilder.append((char)((j >>> 6 & 0x3) + 48));
        localStringBuilder.append((char)((j >>> 3 & 0x7) + 48));
        localStringBuilder.append((char)((j & 0x7) + 48));
      }
    }
    return localStringBuilder.toString();
  }
  
  static String escapeBytes(byte[] paramArrayOfByte)
  {
    escapeBytes(new ByteSequence()
    {
      public byte byteAt(int paramAnonymousInt)
      {
        return this.val$input[paramAnonymousInt];
      }
      
      public int size()
      {
        return this.val$input.length;
      }
    });
  }
  
  public static String escapeDoubleQuotesAndBackslashes(String paramString)
  {
    return paramString.replace("\\", "\\\\").replace("\"", "\\\"");
  }
  
  static String escapeText(String paramString)
  {
    return escapeBytes(ByteString.copyFromUtf8(paramString));
  }
  
  public static Parser getParser()
  {
    return PARSER;
  }
  
  private static boolean isHex(byte paramByte)
  {
    return ((48 <= paramByte) && (paramByte <= 57)) || ((97 <= paramByte) && (paramByte <= 102)) || ((65 <= paramByte) && (paramByte <= 70));
  }
  
  private static boolean isOctal(byte paramByte)
  {
    return (48 <= paramByte) && (paramByte <= 55);
  }
  
  public static void merge(CharSequence paramCharSequence, ExtensionRegistry paramExtensionRegistry, Message.Builder paramBuilder)
    throws TextFormat.ParseException
  {
    PARSER.merge(paramCharSequence, paramExtensionRegistry, paramBuilder);
  }
  
  public static void merge(CharSequence paramCharSequence, Message.Builder paramBuilder)
    throws TextFormat.ParseException
  {
    PARSER.merge(paramCharSequence, paramBuilder);
  }
  
  public static void merge(Readable paramReadable, ExtensionRegistry paramExtensionRegistry, Message.Builder paramBuilder)
    throws IOException
  {
    PARSER.merge(paramReadable, paramExtensionRegistry, paramBuilder);
  }
  
  public static void merge(Readable paramReadable, Message.Builder paramBuilder)
    throws IOException
  {
    PARSER.merge(paramReadable, paramBuilder);
  }
  
  static int parseInt32(String paramString)
    throws NumberFormatException
  {
    return (int)parseInteger(paramString, true, false);
  }
  
  static long parseInt64(String paramString)
    throws NumberFormatException
  {
    return parseInteger(paramString, true, true);
  }
  
  private static long parseInteger(String paramString, boolean paramBoolean1, boolean paramBoolean2)
    throws NumberFormatException
  {
    int i = 0;
    int k = 0;
    if (paramString.startsWith("-", 0))
    {
      if (!paramBoolean1)
      {
        paramString = String.valueOf(paramString);
        if (paramString.length() != 0) {}
        for (paramString = "Number must be positive: ".concat(paramString);; paramString = new String("Number must be positive: ")) {
          throw new NumberFormatException(paramString);
        }
      }
      i = 0 + 1;
      k = 1;
    }
    int j = 10;
    int m;
    Object localObject;
    long l2;
    long l1;
    if (paramString.startsWith("0x", i))
    {
      m = i + 2;
      j = 16;
      localObject = paramString.substring(m);
      if (((String)localObject).length() >= 16) {
        break label290;
      }
      l2 = Long.parseLong((String)localObject, j);
      l1 = l2;
      if (k != 0) {
        l1 = -l2;
      }
      l2 = l1;
      if (paramBoolean2) {
        break label546;
      }
      if (!paramBoolean1) {
        break label229;
      }
      if (l1 <= 2147483647L)
      {
        l2 = l1;
        if (l1 >= -2147483648L) {
          break label546;
        }
      }
      paramString = String.valueOf(paramString);
      if (paramString.length() == 0) {
        break label216;
      }
    }
    label216:
    for (paramString = "Number out of range for 32-bit signed integer: ".concat(paramString);; paramString = new String("Number out of range for 32-bit signed integer: "))
    {
      throw new NumberFormatException(paramString);
      m = i;
      if (!paramString.startsWith("0", i)) {
        break;
      }
      j = 8;
      m = i;
      break;
    }
    label229:
    if (l1 < 4294967296L)
    {
      l2 = l1;
      if (l1 >= 0L) {}
    }
    else
    {
      paramString = String.valueOf(paramString);
      if (paramString.length() != 0) {}
      for (paramString = "Number out of range for 32-bit unsigned integer: ".concat(paramString);; paramString = new String("Number out of range for 32-bit unsigned integer: ")) {
        throw new NumberFormatException(paramString);
      }
      label290:
      BigInteger localBigInteger = new BigInteger((String)localObject, j);
      localObject = localBigInteger;
      if (k != 0) {
        localObject = localBigInteger.negate();
      }
      if (!paramBoolean2)
      {
        if (paramBoolean1)
        {
          if (((BigInteger)localObject).bitLength() > 31)
          {
            paramString = String.valueOf(paramString);
            if (paramString.length() != 0) {}
            for (paramString = "Number out of range for 32-bit signed integer: ".concat(paramString);; paramString = new String("Number out of range for 32-bit signed integer: ")) {
              throw new NumberFormatException(paramString);
            }
          }
        }
        else if (((BigInteger)localObject).bitLength() > 32)
        {
          paramString = String.valueOf(paramString);
          if (paramString.length() != 0) {}
          for (paramString = "Number out of range for 32-bit unsigned integer: ".concat(paramString);; paramString = new String("Number out of range for 32-bit unsigned integer: ")) {
            throw new NumberFormatException(paramString);
          }
        }
      }
      else if (paramBoolean1)
      {
        if (((BigInteger)localObject).bitLength() > 63)
        {
          paramString = String.valueOf(paramString);
          if (paramString.length() != 0) {}
          for (paramString = "Number out of range for 64-bit signed integer: ".concat(paramString);; paramString = new String("Number out of range for 64-bit signed integer: ")) {
            throw new NumberFormatException(paramString);
          }
        }
      }
      else if (((BigInteger)localObject).bitLength() > 64)
      {
        paramString = String.valueOf(paramString);
        if (paramString.length() != 0) {}
        for (paramString = "Number out of range for 64-bit unsigned integer: ".concat(paramString);; paramString = new String("Number out of range for 64-bit unsigned integer: ")) {
          throw new NumberFormatException(paramString);
        }
      }
      l2 = ((BigInteger)localObject).longValue();
    }
    label546:
    return l2;
  }
  
  static int parseUInt32(String paramString)
    throws NumberFormatException
  {
    return (int)parseInteger(paramString, false, false);
  }
  
  static long parseUInt64(String paramString)
    throws NumberFormatException
  {
    return parseInteger(paramString, false, true);
  }
  
  public static void print(MessageOrBuilder paramMessageOrBuilder, Appendable paramAppendable)
    throws IOException
  {
    DEFAULT_PRINTER.print(paramMessageOrBuilder, new TextGenerator(paramAppendable, null));
  }
  
  public static void print(UnknownFieldSet paramUnknownFieldSet, Appendable paramAppendable)
    throws IOException
  {
    DEFAULT_PRINTER.printUnknownFields(paramUnknownFieldSet, new TextGenerator(paramAppendable, null));
  }
  
  public static void printField(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject, Appendable paramAppendable)
    throws IOException
  {
    DEFAULT_PRINTER.printField(paramFieldDescriptor, paramObject, new TextGenerator(paramAppendable, null));
  }
  
  public static String printFieldToString(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      printField(paramFieldDescriptor, paramObject, localStringBuilder);
      paramFieldDescriptor = localStringBuilder.toString();
      return paramFieldDescriptor;
    }
    catch (IOException paramFieldDescriptor)
    {
      throw new IllegalStateException(paramFieldDescriptor);
    }
  }
  
  public static void printFieldValue(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject, Appendable paramAppendable)
    throws IOException
  {
    DEFAULT_PRINTER.printFieldValue(paramFieldDescriptor, paramObject, new TextGenerator(paramAppendable, null));
  }
  
  public static String printToString(MessageOrBuilder paramMessageOrBuilder)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      print(paramMessageOrBuilder, localStringBuilder);
      paramMessageOrBuilder = localStringBuilder.toString();
      return paramMessageOrBuilder;
    }
    catch (IOException paramMessageOrBuilder)
    {
      throw new IllegalStateException(paramMessageOrBuilder);
    }
  }
  
  public static String printToString(UnknownFieldSet paramUnknownFieldSet)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      print(paramUnknownFieldSet, localStringBuilder);
      paramUnknownFieldSet = localStringBuilder.toString();
      return paramUnknownFieldSet;
    }
    catch (IOException paramUnknownFieldSet)
    {
      throw new IllegalStateException(paramUnknownFieldSet);
    }
  }
  
  public static String printToUnicodeString(MessageOrBuilder paramMessageOrBuilder)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      UNICODE_PRINTER.print(paramMessageOrBuilder, new TextGenerator(localStringBuilder, null));
      paramMessageOrBuilder = localStringBuilder.toString();
      return paramMessageOrBuilder;
    }
    catch (IOException paramMessageOrBuilder)
    {
      throw new IllegalStateException(paramMessageOrBuilder);
    }
  }
  
  public static String printToUnicodeString(UnknownFieldSet paramUnknownFieldSet)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      UNICODE_PRINTER.printUnknownFields(paramUnknownFieldSet, new TextGenerator(localStringBuilder, null));
      paramUnknownFieldSet = localStringBuilder.toString();
      return paramUnknownFieldSet;
    }
    catch (IOException paramUnknownFieldSet)
    {
      throw new IllegalStateException(paramUnknownFieldSet);
    }
  }
  
  public static void printUnicode(MessageOrBuilder paramMessageOrBuilder, Appendable paramAppendable)
    throws IOException
  {
    UNICODE_PRINTER.print(paramMessageOrBuilder, new TextGenerator(paramAppendable, null));
  }
  
  public static void printUnicode(UnknownFieldSet paramUnknownFieldSet, Appendable paramAppendable)
    throws IOException
  {
    UNICODE_PRINTER.printUnknownFields(paramUnknownFieldSet, new TextGenerator(paramAppendable, null));
  }
  
  private static void printUnknownFieldValue(int paramInt, Object paramObject, TextGenerator paramTextGenerator)
    throws IOException
  {
    switch (WireFormat.getTagWireType(paramInt))
    {
    case 4: 
    default: 
      throw new IllegalArgumentException(20 + "Bad tag: " + paramInt);
    case 0: 
      paramTextGenerator.print(unsignedToString(((Long)paramObject).longValue()));
      return;
    case 5: 
      paramTextGenerator.print(String.format((Locale)null, "0x%08x", new Object[] { (Integer)paramObject }));
      return;
    case 1: 
      paramTextGenerator.print(String.format((Locale)null, "0x%016x", new Object[] { (Long)paramObject }));
      return;
    case 2: 
      paramTextGenerator.print("\"");
      paramTextGenerator.print(escapeBytes((ByteString)paramObject));
      paramTextGenerator.print("\"");
      return;
    }
    DEFAULT_PRINTER.printUnknownFields((UnknownFieldSet)paramObject, paramTextGenerator);
  }
  
  public static void printUnknownFieldValue(int paramInt, Object paramObject, Appendable paramAppendable)
    throws IOException
  {
    printUnknownFieldValue(paramInt, paramObject, new TextGenerator(paramAppendable, null));
  }
  
  public static String shortDebugString(MessageOrBuilder paramMessageOrBuilder)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      SINGLE_LINE_PRINTER.print(paramMessageOrBuilder, new TextGenerator(localStringBuilder, null));
      paramMessageOrBuilder = localStringBuilder.toString().trim();
      return paramMessageOrBuilder;
    }
    catch (IOException paramMessageOrBuilder)
    {
      throw new IllegalStateException(paramMessageOrBuilder);
    }
  }
  
  public static String shortDebugString(UnknownFieldSet paramUnknownFieldSet)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      SINGLE_LINE_PRINTER.printUnknownFields(paramUnknownFieldSet, new TextGenerator(localStringBuilder, null));
      paramUnknownFieldSet = localStringBuilder.toString().trim();
      return paramUnknownFieldSet;
    }
    catch (IOException paramUnknownFieldSet)
    {
      throw new IllegalStateException(paramUnknownFieldSet);
    }
  }
  
  static ByteString unescapeBytes(CharSequence paramCharSequence)
    throws TextFormat.InvalidEscapeSequenceException
  {
    paramCharSequence = ByteString.copyFromUtf8(paramCharSequence.toString());
    byte[] arrayOfByte = new byte[paramCharSequence.size()];
    int m = 0;
    int j = 0;
    if (j < paramCharSequence.size())
    {
      byte b = paramCharSequence.byteAt(j);
      int k;
      int n;
      int i;
      if (b == 92) {
        if (j + 1 < paramCharSequence.size())
        {
          k = j + 1;
          b = paramCharSequence.byteAt(k);
          if (isOctal(b))
          {
            n = digitValue(b);
            i = n;
            j = k;
            if (k + 1 < paramCharSequence.size())
            {
              i = n;
              j = k;
              if (isOctal(paramCharSequence.byteAt(k + 1)))
              {
                j = k + 1;
                i = n * 8 + digitValue(paramCharSequence.byteAt(j));
              }
            }
            n = i;
            k = j;
            if (j + 1 < paramCharSequence.size())
            {
              n = i;
              k = j;
              if (isOctal(paramCharSequence.byteAt(j + 1)))
              {
                k = j + 1;
                n = i * 8 + digitValue(paramCharSequence.byteAt(k));
              }
            }
            arrayOfByte[m] = ((byte)n);
            i = m + 1;
            j = k;
          }
        }
      }
      for (;;)
      {
        j += 1;
        m = i;
        break;
        switch (b)
        {
        default: 
          char c = (char)b;
          throw new InvalidEscapeSequenceException(29 + "Invalid escape sequence: '\\" + c + "'");
        case 97: 
          arrayOfByte[m] = 7;
          i = m + 1;
          j = k;
          break;
        case 98: 
          arrayOfByte[m] = 8;
          i = m + 1;
          j = k;
          break;
        case 102: 
          arrayOfByte[m] = 12;
          i = m + 1;
          j = k;
          break;
        case 110: 
          arrayOfByte[m] = 10;
          i = m + 1;
          j = k;
          break;
        case 114: 
          arrayOfByte[m] = 13;
          i = m + 1;
          j = k;
          break;
        case 116: 
          arrayOfByte[m] = 9;
          i = m + 1;
          j = k;
          break;
        case 118: 
          arrayOfByte[m] = 11;
          i = m + 1;
          j = k;
          break;
        case 92: 
          arrayOfByte[m] = 92;
          i = m + 1;
          j = k;
          break;
        case 39: 
          arrayOfByte[m] = 39;
          i = m + 1;
          j = k;
          break;
        case 34: 
          arrayOfByte[m] = 34;
          i = m + 1;
          j = k;
          break;
        case 120: 
          if ((k + 1 < paramCharSequence.size()) && (isHex(paramCharSequence.byteAt(k + 1))))
          {
            n = k + 1;
            k = digitValue(paramCharSequence.byteAt(n));
            i = k;
            j = n;
            if (n + 1 < paramCharSequence.size())
            {
              i = k;
              j = n;
              if (isHex(paramCharSequence.byteAt(n + 1)))
              {
                j = n + 1;
                i = k * 16 + digitValue(paramCharSequence.byteAt(j));
              }
            }
            arrayOfByte[m] = ((byte)i);
            i = m + 1;
          }
          else
          {
            throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\x' with no digits");
            throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\' at end of string.");
            arrayOfByte[m] = b;
            i = m + 1;
          }
          break;
        }
      }
    }
    return ByteString.copyFrom(arrayOfByte, 0, m);
  }
  
  static String unescapeText(String paramString)
    throws TextFormat.InvalidEscapeSequenceException
  {
    return unescapeBytes(paramString).toStringUtf8();
  }
  
  public static String unsignedToString(int paramInt)
  {
    if (paramInt >= 0) {
      return Integer.toString(paramInt);
    }
    return Long.toString(paramInt & 0xFFFFFFFF);
  }
  
  public static String unsignedToString(long paramLong)
  {
    if (paramLong >= 0L) {
      return Long.toString(paramLong);
    }
    return BigInteger.valueOf(0x7FFFFFFFFFFFFFFF & paramLong).setBit(63).toString();
  }
  
  private static abstract interface ByteSequence
  {
    public abstract byte byteAt(int paramInt);
    
    public abstract int size();
  }
  
  static class InvalidEscapeSequenceException
    extends IOException
  {
    private static final long serialVersionUID = -8164033650142593304L;
    
    InvalidEscapeSequenceException(String paramString)
    {
      super();
    }
  }
  
  public static class ParseException
    extends IOException
  {
    private static final long serialVersionUID = 3196188060225107702L;
    private final int column;
    private final int line;
    
    public ParseException(int paramInt1, int paramInt2, String paramString)
    {
      super();
      this.line = paramInt1;
      this.column = paramInt2;
    }
    
    public ParseException(String paramString)
    {
      this(-1, -1, paramString);
    }
    
    public int getColumn()
    {
      return this.column;
    }
    
    public int getLine()
    {
      return this.line;
    }
  }
  
  public static class Parser
  {
    private static final int BUFFER_SIZE = 4096;
    private final boolean allowUnknownFields;
    private final SingularOverwritePolicy singularOverwritePolicy;
    
    private Parser(boolean paramBoolean, SingularOverwritePolicy paramSingularOverwritePolicy)
    {
      this.allowUnknownFields = paramBoolean;
      this.singularOverwritePolicy = paramSingularOverwritePolicy;
    }
    
    private void consumeFieldValue(TextFormat.Tokenizer paramTokenizer, ExtensionRegistry paramExtensionRegistry, MessageReflection.MergeTarget paramMergeTarget, Descriptors.FieldDescriptor paramFieldDescriptor, ExtensionRegistry.ExtensionInfo paramExtensionInfo)
      throws TextFormat.ParseException
    {
      Object localObject = null;
      if (paramFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
      {
        if (paramTokenizer.tryConsume("<"))
        {
          localObject = ">";
          if (paramExtensionInfo != null) {
            break label117;
          }
          paramExtensionInfo = null;
          label35:
          paramExtensionInfo = paramMergeTarget.newMergeTargetForField(paramFieldDescriptor, paramExtensionInfo);
        }
        for (;;)
        {
          if (paramTokenizer.tryConsume((String)localObject)) {
            break label138;
          }
          if (paramTokenizer.atEnd())
          {
            throw paramTokenizer.parseException(String.valueOf(localObject).length() + 12 + "Expected \"" + (String)localObject + "\".");
            paramTokenizer.consume("{");
            localObject = "}";
            break;
            label117:
            paramExtensionInfo = paramExtensionInfo.defaultInstance;
            break label35;
          }
          mergeField(paramTokenizer, paramExtensionRegistry, paramExtensionInfo);
        }
        label138:
        paramExtensionRegistry = paramExtensionInfo.finish();
      }
      while (paramFieldDescriptor.isRepeated())
      {
        paramMergeTarget.addRepeatedField(paramFieldDescriptor, paramExtensionRegistry);
        return;
        switch (TextFormat.3.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[paramFieldDescriptor.getType().ordinal()])
        {
        default: 
          paramExtensionRegistry = (ExtensionRegistry)localObject;
          break;
        case 1: 
        case 2: 
        case 3: 
          paramExtensionRegistry = Integer.valueOf(paramTokenizer.consumeInt32());
          break;
        case 4: 
        case 5: 
        case 6: 
          paramExtensionRegistry = Long.valueOf(paramTokenizer.consumeInt64());
          break;
        case 10: 
        case 11: 
          paramExtensionRegistry = Integer.valueOf(paramTokenizer.consumeUInt32());
          break;
        case 12: 
        case 13: 
          paramExtensionRegistry = Long.valueOf(paramTokenizer.consumeUInt64());
          break;
        case 8: 
          paramExtensionRegistry = Float.valueOf(paramTokenizer.consumeFloat());
          break;
        case 9: 
          paramExtensionRegistry = Double.valueOf(paramTokenizer.consumeDouble());
          break;
        case 7: 
          paramExtensionRegistry = Boolean.valueOf(paramTokenizer.consumeBoolean());
          break;
        case 14: 
          paramExtensionRegistry = paramTokenizer.consumeString();
          break;
        case 15: 
          paramExtensionRegistry = paramTokenizer.consumeByteString();
          break;
        case 16: 
          localObject = paramFieldDescriptor.getEnumType();
          if (paramTokenizer.lookingAtInteger())
          {
            int i = paramTokenizer.consumeInt32();
            paramExtensionInfo = ((Descriptors.EnumDescriptor)localObject).findValueByNumber(i);
            paramExtensionRegistry = paramExtensionInfo;
            if (paramExtensionInfo == null)
            {
              paramExtensionRegistry = ((Descriptors.EnumDescriptor)localObject).getFullName();
              throw paramTokenizer.parseExceptionPreviousToken(String.valueOf(paramExtensionRegistry).length() + 50 + "Enum type \"" + paramExtensionRegistry + "\" has no value with number " + i + ".");
            }
          }
          else
          {
            String str = paramTokenizer.consumeIdentifier();
            paramExtensionInfo = ((Descriptors.EnumDescriptor)localObject).findValueByName(str);
            paramExtensionRegistry = paramExtensionInfo;
            if (paramExtensionInfo == null)
            {
              paramExtensionRegistry = ((Descriptors.EnumDescriptor)localObject).getFullName();
              throw paramTokenizer.parseExceptionPreviousToken(String.valueOf(paramExtensionRegistry).length() + 35 + String.valueOf(str).length() + "Enum type \"" + paramExtensionRegistry + "\" has no value named \"" + str + "\".");
            }
          }
          break;
        case 17: 
        case 18: 
          throw new RuntimeException("Can't get here.");
        }
      }
      if ((this.singularOverwritePolicy == SingularOverwritePolicy.FORBID_SINGULAR_OVERWRITES) && (paramMergeTarget.hasField(paramFieldDescriptor)))
      {
        paramExtensionRegistry = paramFieldDescriptor.getFullName();
        throw paramTokenizer.parseExceptionPreviousToken(String.valueOf(paramExtensionRegistry).length() + 44 + "Non-repeated field \"" + paramExtensionRegistry + "\" cannot be overwritten.");
      }
      if ((this.singularOverwritePolicy == SingularOverwritePolicy.FORBID_SINGULAR_OVERWRITES) && (paramFieldDescriptor.getContainingOneof() != null) && (paramMergeTarget.hasOneof(paramFieldDescriptor.getContainingOneof())))
      {
        paramExtensionRegistry = paramFieldDescriptor.getContainingOneof();
        paramFieldDescriptor = paramFieldDescriptor.getFullName();
        paramMergeTarget = paramMergeTarget.getOneofFieldDescriptor(paramExtensionRegistry).getFullName();
        paramExtensionRegistry = paramExtensionRegistry.getName();
        throw paramTokenizer.parseExceptionPreviousToken(String.valueOf(paramFieldDescriptor).length() + 70 + String.valueOf(paramMergeTarget).length() + String.valueOf(paramExtensionRegistry).length() + "Field \"" + paramFieldDescriptor + "\" is specified along with field \"" + paramMergeTarget + "\", another member of oneof \"" + paramExtensionRegistry + "\".");
      }
      paramMergeTarget.setField(paramFieldDescriptor, paramExtensionRegistry);
    }
    
    private void mergeField(TextFormat.Tokenizer paramTokenizer, ExtensionRegistry paramExtensionRegistry, MessageReflection.MergeTarget paramMergeTarget)
      throws TextFormat.ParseException
    {
      Object localObject1 = null;
      Descriptors.Descriptor localDescriptor = paramMergeTarget.getDescriptorForType();
      Logger localLogger = null;
      Object localObject2;
      Object localObject3;
      if (paramTokenizer.tryConsume("["))
      {
        localObject2 = new StringBuilder(paramTokenizer.consumeIdentifier());
        while (paramTokenizer.tryConsume("."))
        {
          ((StringBuilder)localObject2).append('.');
          ((StringBuilder)localObject2).append(paramTokenizer.consumeIdentifier());
        }
        localObject3 = paramMergeTarget.findExtensionByName(paramExtensionRegistry, ((StringBuilder)localObject2).toString());
        if (localObject3 == null)
        {
          if (!this.allowUnknownFields)
          {
            paramExtensionRegistry = String.valueOf(localObject2);
            throw paramTokenizer.parseExceptionPreviousToken(String.valueOf(paramExtensionRegistry).length() + 48 + "Extension \"" + paramExtensionRegistry + "\" not found in the ExtensionRegistry.");
          }
          localLogger = TextFormat.logger;
          localObject2 = String.valueOf(localObject2);
          localLogger.warning(String.valueOf(localObject2).length() + 48 + "Extension \"" + (String)localObject2 + "\" not found in the ExtensionRegistry.");
          paramTokenizer.consume("]");
        }
      }
      for (;;)
      {
        if (localObject1 == null)
        {
          if ((paramTokenizer.tryConsume(":")) && (!paramTokenizer.lookingAt("{")) && (!paramTokenizer.lookingAt("<")))
          {
            skipFieldValue(paramTokenizer);
            return;
            if (((ExtensionRegistry.ExtensionInfo)localObject3).descriptor.getContainingType() != localDescriptor)
            {
              paramExtensionRegistry = String.valueOf(localObject2);
              paramMergeTarget = localDescriptor.getFullName();
              throw paramTokenizer.parseExceptionPreviousToken(String.valueOf(paramExtensionRegistry).length() + 45 + String.valueOf(paramMergeTarget).length() + "Extension \"" + paramExtensionRegistry + "\" does not extend message type \"" + paramMergeTarget + "\".");
            }
            localObject1 = ((ExtensionRegistry.ExtensionInfo)localObject3).descriptor;
            break;
            String str = paramTokenizer.consumeIdentifier();
            localObject2 = localDescriptor.findFieldByName(str);
            localObject1 = localObject2;
            if (localObject2 == null)
            {
              localObject2 = localDescriptor.findFieldByName(str.toLowerCase(Locale.US));
              localObject1 = localObject2;
              if (localObject2 != null)
              {
                localObject1 = localObject2;
                if (((Descriptors.FieldDescriptor)localObject2).getType() != Descriptors.FieldDescriptor.Type.GROUP) {
                  localObject1 = null;
                }
              }
            }
            localObject2 = localObject1;
            if (localObject1 != null)
            {
              localObject2 = localObject1;
              if (((Descriptors.FieldDescriptor)localObject1).getType() == Descriptors.FieldDescriptor.Type.GROUP)
              {
                localObject2 = localObject1;
                if (!((Descriptors.FieldDescriptor)localObject1).getMessageType().getName().equals(str)) {
                  localObject2 = null;
                }
              }
            }
            localObject1 = localObject2;
            localObject3 = localLogger;
            if (localObject2 != null) {
              continue;
            }
            if (!this.allowUnknownFields)
            {
              paramExtensionRegistry = localDescriptor.getFullName();
              throw paramTokenizer.parseExceptionPreviousToken(String.valueOf(paramExtensionRegistry).length() + 38 + String.valueOf(str).length() + "Message type \"" + paramExtensionRegistry + "\" has no field named \"" + str + "\".");
            }
            localObject1 = TextFormat.logger;
            localObject3 = localDescriptor.getFullName();
            ((Logger)localObject1).warning(String.valueOf(localObject3).length() + 38 + String.valueOf(str).length() + "Message type \"" + (String)localObject3 + "\" has no field named \"" + str + "\".");
            localObject1 = localObject2;
            localObject3 = localLogger;
            continue;
          }
          skipFieldMessage(paramTokenizer);
          return;
        }
      }
      if (((Descriptors.FieldDescriptor)localObject1).getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
        paramTokenizer.tryConsume(":");
      }
      for (;;)
      {
        if ((!((Descriptors.FieldDescriptor)localObject1).isRepeated()) || (!paramTokenizer.tryConsume("["))) {
          break label702;
        }
        for (;;)
        {
          consumeFieldValue(paramTokenizer, paramExtensionRegistry, paramMergeTarget, (Descriptors.FieldDescriptor)localObject1, (ExtensionRegistry.ExtensionInfo)localObject3);
          if (paramTokenizer.tryConsume("]")) {
            break;
          }
          paramTokenizer.consume(",");
        }
        paramTokenizer.consume(":");
      }
      label702:
      consumeFieldValue(paramTokenizer, paramExtensionRegistry, paramMergeTarget, (Descriptors.FieldDescriptor)localObject1, (ExtensionRegistry.ExtensionInfo)localObject3);
    }
    
    public static Builder newBuilder()
    {
      return new Builder();
    }
    
    private void skipField(TextFormat.Tokenizer paramTokenizer)
      throws TextFormat.ParseException
    {
      if (paramTokenizer.tryConsume("["))
      {
        do
        {
          paramTokenizer.consumeIdentifier();
        } while (paramTokenizer.tryConsume("."));
        paramTokenizer.consume("]");
        if ((!paramTokenizer.tryConsume(":")) || (paramTokenizer.lookingAt("<")) || (paramTokenizer.lookingAt("{"))) {
          break label91;
        }
        skipFieldValue(paramTokenizer);
      }
      for (;;)
      {
        if (!paramTokenizer.tryConsume(";")) {
          paramTokenizer.tryConsume(",");
        }
        return;
        paramTokenizer.consumeIdentifier();
        break;
        label91:
        skipFieldMessage(paramTokenizer);
      }
    }
    
    private void skipFieldMessage(TextFormat.Tokenizer paramTokenizer)
      throws TextFormat.ParseException
    {
      String str;
      if (paramTokenizer.tryConsume("<")) {
        str = ">";
      }
      while ((!paramTokenizer.lookingAt(">")) && (!paramTokenizer.lookingAt("}")))
      {
        skipField(paramTokenizer);
        continue;
        paramTokenizer.consume("{");
        str = "}";
      }
      paramTokenizer.consume(str);
    }
    
    private void skipFieldValue(TextFormat.Tokenizer paramTokenizer)
      throws TextFormat.ParseException
    {
      if (paramTokenizer.tryConsumeString()) {
        while (paramTokenizer.tryConsumeString()) {}
      }
      while ((paramTokenizer.tryConsumeIdentifier()) || (paramTokenizer.tryConsumeInt64()) || (paramTokenizer.tryConsumeUInt64()) || (paramTokenizer.tryConsumeDouble()) || (paramTokenizer.tryConsumeFloat())) {
        return;
      }
      String str = String.valueOf(TextFormat.Tokenizer.access$1200(paramTokenizer));
      if (str.length() != 0) {}
      for (str = "Invalid field value: ".concat(str);; str = new String("Invalid field value: ")) {
        throw paramTokenizer.parseException(str);
      }
    }
    
    private static StringBuilder toStringBuilder(Readable paramReadable)
      throws IOException
    {
      StringBuilder localStringBuilder = new StringBuilder();
      CharBuffer localCharBuffer = CharBuffer.allocate(4096);
      for (;;)
      {
        int i = paramReadable.read(localCharBuffer);
        if (i == -1) {
          return localStringBuilder;
        }
        localCharBuffer.flip();
        localStringBuilder.append(localCharBuffer, 0, i);
      }
    }
    
    public void merge(CharSequence paramCharSequence, ExtensionRegistry paramExtensionRegistry, Message.Builder paramBuilder)
      throws TextFormat.ParseException
    {
      paramCharSequence = new TextFormat.Tokenizer(paramCharSequence, null);
      paramBuilder = new MessageReflection.BuilderAdapter(paramBuilder);
      while (!paramCharSequence.atEnd()) {
        mergeField(paramCharSequence, paramExtensionRegistry, paramBuilder);
      }
    }
    
    public void merge(CharSequence paramCharSequence, Message.Builder paramBuilder)
      throws TextFormat.ParseException
    {
      merge(paramCharSequence, ExtensionRegistry.getEmptyRegistry(), paramBuilder);
    }
    
    public void merge(Readable paramReadable, ExtensionRegistry paramExtensionRegistry, Message.Builder paramBuilder)
      throws IOException
    {
      merge(toStringBuilder(paramReadable), paramExtensionRegistry, paramBuilder);
    }
    
    public void merge(Readable paramReadable, Message.Builder paramBuilder)
      throws IOException
    {
      merge(paramReadable, ExtensionRegistry.getEmptyRegistry(), paramBuilder);
    }
    
    public static class Builder
    {
      private boolean allowUnknownFields = false;
      private TextFormat.Parser.SingularOverwritePolicy singularOverwritePolicy = TextFormat.Parser.SingularOverwritePolicy.ALLOW_SINGULAR_OVERWRITES;
      
      public TextFormat.Parser build()
      {
        return new TextFormat.Parser(this.allowUnknownFields, this.singularOverwritePolicy, null);
      }
      
      public Builder setSingularOverwritePolicy(TextFormat.Parser.SingularOverwritePolicy paramSingularOverwritePolicy)
      {
        this.singularOverwritePolicy = paramSingularOverwritePolicy;
        return this;
      }
    }
    
    public static enum SingularOverwritePolicy
    {
      ALLOW_SINGULAR_OVERWRITES,  FORBID_SINGULAR_OVERWRITES;
      
      private SingularOverwritePolicy() {}
    }
  }
  
  private static final class Printer
  {
    boolean escapeNonAscii = true;
    boolean singleLineMode = false;
    
    private void print(MessageOrBuilder paramMessageOrBuilder, TextFormat.TextGenerator paramTextGenerator)
      throws IOException
    {
      Iterator localIterator = paramMessageOrBuilder.getAllFields().entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        printField((Descriptors.FieldDescriptor)localEntry.getKey(), localEntry.getValue(), paramTextGenerator);
      }
      printUnknownFields(paramMessageOrBuilder.getUnknownFields(), paramTextGenerator);
    }
    
    private void printField(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject, TextFormat.TextGenerator paramTextGenerator)
      throws IOException
    {
      if (paramFieldDescriptor.isRepeated())
      {
        paramObject = ((List)paramObject).iterator();
        while (((Iterator)paramObject).hasNext()) {
          printSingleField(paramFieldDescriptor, ((Iterator)paramObject).next(), paramTextGenerator);
        }
      }
      printSingleField(paramFieldDescriptor, paramObject, paramTextGenerator);
    }
    
    private void printFieldValue(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject, TextFormat.TextGenerator paramTextGenerator)
      throws IOException
    {
      switch (TextFormat.3.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[paramFieldDescriptor.getType().ordinal()])
      {
      default: 
        return;
      case 1: 
      case 2: 
      case 3: 
        paramTextGenerator.print(((Integer)paramObject).toString());
        return;
      case 4: 
      case 5: 
      case 6: 
        paramTextGenerator.print(((Long)paramObject).toString());
        return;
      case 7: 
        paramTextGenerator.print(((Boolean)paramObject).toString());
        return;
      case 8: 
        paramTextGenerator.print(((Float)paramObject).toString());
        return;
      case 9: 
        paramTextGenerator.print(((Double)paramObject).toString());
        return;
      case 10: 
      case 11: 
        paramTextGenerator.print(TextFormat.unsignedToString(((Integer)paramObject).intValue()));
        return;
      case 12: 
      case 13: 
        paramTextGenerator.print(TextFormat.unsignedToString(((Long)paramObject).longValue()));
        return;
      case 14: 
        paramTextGenerator.print("\"");
        if (this.escapeNonAscii) {}
        for (paramFieldDescriptor = TextFormat.escapeText((String)paramObject);; paramFieldDescriptor = TextFormat.escapeDoubleQuotesAndBackslashes((String)paramObject))
        {
          paramTextGenerator.print(paramFieldDescriptor);
          paramTextGenerator.print("\"");
          return;
        }
      case 15: 
        paramTextGenerator.print("\"");
        if ((paramObject instanceof ByteString)) {
          paramTextGenerator.print(TextFormat.escapeBytes((ByteString)paramObject));
        }
        for (;;)
        {
          paramTextGenerator.print("\"");
          return;
          paramTextGenerator.print(TextFormat.escapeBytes((byte[])paramObject));
        }
      case 16: 
        paramTextGenerator.print(((Descriptors.EnumValueDescriptor)paramObject).getName());
        return;
      }
      print((Message)paramObject, paramTextGenerator);
    }
    
    private void printSingleField(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject, TextFormat.TextGenerator paramTextGenerator)
      throws IOException
    {
      if (paramFieldDescriptor.isExtension())
      {
        paramTextGenerator.print("[");
        if ((paramFieldDescriptor.getContainingType().getOptions().getMessageSetWireFormat()) && (paramFieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.MESSAGE) && (paramFieldDescriptor.isOptional()) && (paramFieldDescriptor.getExtensionScope() == paramFieldDescriptor.getMessageType()))
        {
          paramTextGenerator.print(paramFieldDescriptor.getMessageType().getFullName());
          paramTextGenerator.print("]");
          label71:
          if (paramFieldDescriptor.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            break label184;
          }
          if (!this.singleLineMode) {
            break label171;
          }
          paramTextGenerator.print(" { ");
        }
      }
      for (;;)
      {
        printFieldValue(paramFieldDescriptor, paramObject, paramTextGenerator);
        if (paramFieldDescriptor.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
          break label205;
        }
        if (!this.singleLineMode) {
          break label193;
        }
        paramTextGenerator.print("} ");
        return;
        paramTextGenerator.print(paramFieldDescriptor.getFullName());
        break;
        if (paramFieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.GROUP)
        {
          paramTextGenerator.print(paramFieldDescriptor.getMessageType().getName());
          break label71;
        }
        paramTextGenerator.print(paramFieldDescriptor.getName());
        break label71;
        label171:
        paramTextGenerator.print(" {\n");
        paramTextGenerator.indent();
        continue;
        label184:
        paramTextGenerator.print(": ");
      }
      label193:
      paramTextGenerator.outdent();
      paramTextGenerator.print("}\n");
      return;
      label205:
      if (this.singleLineMode)
      {
        paramTextGenerator.print(" ");
        return;
      }
      paramTextGenerator.print("\n");
    }
    
    private void printUnknownField(int paramInt1, int paramInt2, List<?> paramList, TextFormat.TextGenerator paramTextGenerator)
      throws IOException
    {
      Iterator localIterator = paramList.iterator();
      if (localIterator.hasNext())
      {
        paramList = localIterator.next();
        paramTextGenerator.print(String.valueOf(paramInt1));
        paramTextGenerator.print(": ");
        TextFormat.printUnknownFieldValue(paramInt2, paramList, paramTextGenerator);
        if (this.singleLineMode) {}
        for (paramList = " ";; paramList = "\n")
        {
          paramTextGenerator.print(paramList);
          break;
        }
      }
    }
    
    private void printUnknownFields(UnknownFieldSet paramUnknownFieldSet, TextFormat.TextGenerator paramTextGenerator)
      throws IOException
    {
      paramUnknownFieldSet = paramUnknownFieldSet.asMap().entrySet().iterator();
      while (paramUnknownFieldSet.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramUnknownFieldSet.next();
        int i = ((Integer)localEntry.getKey()).intValue();
        Object localObject = (UnknownFieldSet.Field)localEntry.getValue();
        printUnknownField(i, 0, ((UnknownFieldSet.Field)localObject).getVarintList(), paramTextGenerator);
        printUnknownField(i, 5, ((UnknownFieldSet.Field)localObject).getFixed32List(), paramTextGenerator);
        printUnknownField(i, 1, ((UnknownFieldSet.Field)localObject).getFixed64List(), paramTextGenerator);
        printUnknownField(i, 2, ((UnknownFieldSet.Field)localObject).getLengthDelimitedList(), paramTextGenerator);
        localObject = ((UnknownFieldSet.Field)localObject).getGroupList().iterator();
        while (((Iterator)localObject).hasNext())
        {
          UnknownFieldSet localUnknownFieldSet = (UnknownFieldSet)((Iterator)localObject).next();
          paramTextGenerator.print(((Integer)localEntry.getKey()).toString());
          if (this.singleLineMode) {
            paramTextGenerator.print(" { ");
          }
          for (;;)
          {
            printUnknownFields(localUnknownFieldSet, paramTextGenerator);
            if (!this.singleLineMode) {
              break label209;
            }
            paramTextGenerator.print("} ");
            break;
            paramTextGenerator.print(" {\n");
            paramTextGenerator.indent();
          }
          label209:
          paramTextGenerator.outdent();
          paramTextGenerator.print("}\n");
        }
      }
    }
    
    private Printer setEscapeNonAscii(boolean paramBoolean)
    {
      this.escapeNonAscii = paramBoolean;
      return this;
    }
    
    private Printer setSingleLineMode(boolean paramBoolean)
    {
      this.singleLineMode = paramBoolean;
      return this;
    }
  }
  
  private static final class TextGenerator
  {
    private boolean atStartOfLine = true;
    private final StringBuilder indent = new StringBuilder();
    private final Appendable output;
    
    private TextGenerator(Appendable paramAppendable)
    {
      this.output = paramAppendable;
    }
    
    private void write(CharSequence paramCharSequence)
      throws IOException
    {
      if (paramCharSequence.length() == 0) {
        return;
      }
      if (this.atStartOfLine)
      {
        this.atStartOfLine = false;
        this.output.append(this.indent);
      }
      this.output.append(paramCharSequence);
    }
    
    public void indent()
    {
      this.indent.append("  ");
    }
    
    public void outdent()
    {
      int i = this.indent.length();
      if (i == 0) {
        throw new IllegalArgumentException(" Outdent() without matching Indent().");
      }
      this.indent.delete(i - 2, i);
    }
    
    public void print(CharSequence paramCharSequence)
      throws IOException
    {
      int m = paramCharSequence.length();
      int j = 0;
      int i = 0;
      while (i < m)
      {
        int k = j;
        if (paramCharSequence.charAt(i) == '\n')
        {
          write(paramCharSequence.subSequence(j, i + 1));
          k = i + 1;
          this.atStartOfLine = true;
        }
        i += 1;
        j = k;
      }
      write(paramCharSequence.subSequence(j, m));
    }
  }
  
  private static final class Tokenizer
  {
    private static final Pattern DOUBLE_INFINITY = Pattern.compile("-?inf(inity)?", 2);
    private static final Pattern FLOAT_INFINITY = Pattern.compile("-?inf(inity)?f?", 2);
    private static final Pattern FLOAT_NAN = Pattern.compile("nanf?", 2);
    private static final Pattern TOKEN;
    private static final Pattern WHITESPACE = Pattern.compile("(\\s|(#.*$))++", 8);
    private int column = 0;
    private String currentToken;
    private int line = 0;
    private final Matcher matcher;
    private int pos = 0;
    private int previousColumn = 0;
    private int previousLine = 0;
    private final CharSequence text;
    
    static
    {
      TOKEN = Pattern.compile("[a-zA-Z_][0-9a-zA-Z_+-]*+|[.]?[0-9+-][0-9a-zA-Z_.+-]*+|\"([^\"\n\\\\]|\\\\.)*+(\"|\\\\?$)|'([^'\n\\\\]|\\\\.)*+('|\\\\?$)", 8);
    }
    
    private Tokenizer(CharSequence paramCharSequence)
    {
      this.text = paramCharSequence;
      this.matcher = WHITESPACE.matcher(paramCharSequence);
      skipWhitespace();
      nextToken();
    }
    
    private void consumeByteString(List<ByteString> paramList)
      throws TextFormat.ParseException
    {
      int i = 0;
      if (this.currentToken.length() > 0) {
        i = this.currentToken.charAt(0);
      }
      if ((i != 34) && (i != 39)) {
        throw parseException("Expected string.");
      }
      if ((this.currentToken.length() < 2) || (this.currentToken.charAt(this.currentToken.length() - 1) != i)) {
        throw parseException("String missing ending quote.");
      }
      try
      {
        ByteString localByteString = TextFormat.unescapeBytes(this.currentToken.substring(1, this.currentToken.length() - 1));
        nextToken();
        paramList.add(localByteString);
        return;
      }
      catch (TextFormat.InvalidEscapeSequenceException paramList)
      {
        throw parseException(paramList.getMessage());
      }
    }
    
    private TextFormat.ParseException floatParseException(NumberFormatException paramNumberFormatException)
    {
      paramNumberFormatException = String.valueOf(paramNumberFormatException.getMessage());
      if (paramNumberFormatException.length() != 0) {}
      for (paramNumberFormatException = "Couldn't parse number: ".concat(paramNumberFormatException);; paramNumberFormatException = new String("Couldn't parse number: ")) {
        return parseException(paramNumberFormatException);
      }
    }
    
    private TextFormat.ParseException integerParseException(NumberFormatException paramNumberFormatException)
    {
      paramNumberFormatException = String.valueOf(paramNumberFormatException.getMessage());
      if (paramNumberFormatException.length() != 0) {}
      for (paramNumberFormatException = "Couldn't parse integer: ".concat(paramNumberFormatException);; paramNumberFormatException = new String("Couldn't parse integer: ")) {
        return parseException(paramNumberFormatException);
      }
    }
    
    private void skipWhitespace()
    {
      this.matcher.usePattern(WHITESPACE);
      if (this.matcher.lookingAt()) {
        this.matcher.region(this.matcher.end(), this.matcher.regionEnd());
      }
    }
    
    public boolean atEnd()
    {
      return this.currentToken.length() == 0;
    }
    
    public void consume(String paramString)
      throws TextFormat.ParseException
    {
      if (!tryConsume(paramString)) {
        throw parseException(String.valueOf(paramString).length() + 12 + "Expected \"" + paramString + "\".");
      }
    }
    
    public boolean consumeBoolean()
      throws TextFormat.ParseException
    {
      if ((this.currentToken.equals("true")) || (this.currentToken.equals("t")) || (this.currentToken.equals("1")))
      {
        nextToken();
        return true;
      }
      if ((this.currentToken.equals("false")) || (this.currentToken.equals("f")) || (this.currentToken.equals("0")))
      {
        nextToken();
        return false;
      }
      throw parseException("Expected \"true\" or \"false\".");
    }
    
    public ByteString consumeByteString()
      throws TextFormat.ParseException
    {
      ArrayList localArrayList = new ArrayList();
      consumeByteString(localArrayList);
      while ((this.currentToken.startsWith("'")) || (this.currentToken.startsWith("\""))) {
        consumeByteString(localArrayList);
      }
      return ByteString.copyFrom(localArrayList);
    }
    
    public double consumeDouble()
      throws TextFormat.ParseException
    {
      if (DOUBLE_INFINITY.matcher(this.currentToken).matches())
      {
        boolean bool = this.currentToken.startsWith("-");
        nextToken();
        if (bool) {
          return Double.NEGATIVE_INFINITY;
        }
        return Double.POSITIVE_INFINITY;
      }
      if (this.currentToken.equalsIgnoreCase("nan"))
      {
        nextToken();
        return NaN.0D;
      }
      try
      {
        double d = Double.parseDouble(this.currentToken);
        nextToken();
        return d;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw floatParseException(localNumberFormatException);
      }
    }
    
    public float consumeFloat()
      throws TextFormat.ParseException
    {
      if (FLOAT_INFINITY.matcher(this.currentToken).matches())
      {
        boolean bool = this.currentToken.startsWith("-");
        nextToken();
        if (bool) {
          return Float.NEGATIVE_INFINITY;
        }
        return Float.POSITIVE_INFINITY;
      }
      if (FLOAT_NAN.matcher(this.currentToken).matches())
      {
        nextToken();
        return NaN.0F;
      }
      try
      {
        float f = Float.parseFloat(this.currentToken);
        nextToken();
        return f;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw floatParseException(localNumberFormatException);
      }
    }
    
    public String consumeIdentifier()
      throws TextFormat.ParseException
    {
      int i = 0;
      while (i < this.currentToken.length())
      {
        int j = this.currentToken.charAt(i);
        if (((97 <= j) && (j <= 122)) || ((65 <= j) && (j <= 90)) || ((48 <= j) && (j <= 57)) || (j == 95) || (j == 46))
        {
          i += 1;
        }
        else
        {
          str = this.currentToken;
          throw parseException(String.valueOf(str).length() + 29 + "Expected identifier. Found '" + str + "'");
        }
      }
      String str = this.currentToken;
      nextToken();
      return str;
    }
    
    public int consumeInt32()
      throws TextFormat.ParseException
    {
      try
      {
        int i = TextFormat.parseInt32(this.currentToken);
        nextToken();
        return i;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw integerParseException(localNumberFormatException);
      }
    }
    
    public long consumeInt64()
      throws TextFormat.ParseException
    {
      try
      {
        long l = TextFormat.parseInt64(this.currentToken);
        nextToken();
        return l;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw integerParseException(localNumberFormatException);
      }
    }
    
    public String consumeString()
      throws TextFormat.ParseException
    {
      return consumeByteString().toStringUtf8();
    }
    
    public int consumeUInt32()
      throws TextFormat.ParseException
    {
      try
      {
        int i = TextFormat.parseUInt32(this.currentToken);
        nextToken();
        return i;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw integerParseException(localNumberFormatException);
      }
    }
    
    public long consumeUInt64()
      throws TextFormat.ParseException
    {
      try
      {
        long l = TextFormat.parseUInt64(this.currentToken);
        nextToken();
        return l;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw integerParseException(localNumberFormatException);
      }
    }
    
    public boolean lookingAt(String paramString)
    {
      return this.currentToken.equals(paramString);
    }
    
    public boolean lookingAtInteger()
    {
      if (this.currentToken.length() == 0) {}
      int i;
      do
      {
        return false;
        i = this.currentToken.charAt(0);
      } while (((48 > i) || (i > 57)) && (i != 45) && (i != 43));
      return true;
    }
    
    public void nextToken()
    {
      this.previousLine = this.line;
      this.previousColumn = this.column;
      if (this.pos < this.matcher.regionStart())
      {
        if (this.text.charAt(this.pos) == '\n') {
          this.line += 1;
        }
        for (this.column = 0;; this.column += 1)
        {
          this.pos += 1;
          break;
        }
      }
      if (this.matcher.regionStart() == this.matcher.regionEnd())
      {
        this.currentToken = "";
        return;
      }
      this.matcher.usePattern(TOKEN);
      if (this.matcher.lookingAt())
      {
        this.currentToken = this.matcher.group();
        this.matcher.region(this.matcher.end(), this.matcher.regionEnd());
      }
      for (;;)
      {
        skipWhitespace();
        return;
        this.currentToken = String.valueOf(this.text.charAt(this.pos));
        this.matcher.region(this.pos + 1, this.matcher.regionEnd());
      }
    }
    
    public TextFormat.ParseException parseException(String paramString)
    {
      return new TextFormat.ParseException(this.line + 1, this.column + 1, paramString);
    }
    
    public TextFormat.ParseException parseExceptionPreviousToken(String paramString)
    {
      return new TextFormat.ParseException(this.previousLine + 1, this.previousColumn + 1, paramString);
    }
    
    public boolean tryConsume(String paramString)
    {
      if (this.currentToken.equals(paramString))
      {
        nextToken();
        return true;
      }
      return false;
    }
    
    public boolean tryConsumeDouble()
    {
      try
      {
        consumeDouble();
        return true;
      }
      catch (TextFormat.ParseException localParseException) {}
      return false;
    }
    
    public boolean tryConsumeFloat()
    {
      try
      {
        consumeFloat();
        return true;
      }
      catch (TextFormat.ParseException localParseException) {}
      return false;
    }
    
    public boolean tryConsumeIdentifier()
    {
      try
      {
        consumeIdentifier();
        return true;
      }
      catch (TextFormat.ParseException localParseException) {}
      return false;
    }
    
    public boolean tryConsumeInt64()
    {
      try
      {
        consumeInt64();
        return true;
      }
      catch (TextFormat.ParseException localParseException) {}
      return false;
    }
    
    public boolean tryConsumeString()
    {
      try
      {
        consumeString();
        return true;
      }
      catch (TextFormat.ParseException localParseException) {}
      return false;
    }
    
    public boolean tryConsumeUInt64()
    {
      try
      {
        consumeUInt64();
        return true;
      }
      catch (TextFormat.ParseException localParseException) {}
      return false;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\i18n\phonenumbers\repackaged\com\google\protobuf\TextFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */