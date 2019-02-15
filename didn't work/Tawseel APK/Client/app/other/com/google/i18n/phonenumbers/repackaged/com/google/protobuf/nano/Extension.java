package com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Extension<M extends ExtendableMessageNano<M>, T>
{
  public static final int TYPE_BOOL = 8;
  public static final int TYPE_BYTES = 12;
  public static final int TYPE_DOUBLE = 1;
  public static final int TYPE_ENUM = 14;
  public static final int TYPE_FIXED32 = 7;
  public static final int TYPE_FIXED64 = 6;
  public static final int TYPE_FLOAT = 2;
  public static final int TYPE_GROUP = 10;
  public static final int TYPE_INT32 = 5;
  public static final int TYPE_INT64 = 3;
  public static final int TYPE_MESSAGE = 11;
  public static final int TYPE_SFIXED32 = 15;
  public static final int TYPE_SFIXED64 = 16;
  public static final int TYPE_SINT32 = 17;
  public static final int TYPE_SINT64 = 18;
  public static final int TYPE_STRING = 9;
  public static final int TYPE_UINT32 = 13;
  public static final int TYPE_UINT64 = 4;
  protected final Class<T> clazz;
  protected final boolean repeated;
  public final int tag;
  protected final int type;
  
  private Extension(int paramInt1, Class<T> paramClass, int paramInt2, boolean paramBoolean)
  {
    this.type = paramInt1;
    this.clazz = paramClass;
    this.tag = paramInt2;
    this.repeated = paramBoolean;
  }
  
  @Deprecated
  public static <M extends ExtendableMessageNano<M>, T extends MessageNano> Extension<M, T> createMessageTyped(int paramInt1, Class<T> paramClass, int paramInt2)
  {
    return new Extension(paramInt1, paramClass, paramInt2, false);
  }
  
  public static <M extends ExtendableMessageNano<M>, T extends MessageNano> Extension<M, T> createMessageTyped(int paramInt, Class<T> paramClass, long paramLong)
  {
    return new Extension(paramInt, paramClass, (int)paramLong, false);
  }
  
  public static <M extends ExtendableMessageNano<M>, T> Extension<M, T> createPrimitiveTyped(int paramInt, Class<T> paramClass, long paramLong)
  {
    return new PrimitiveExtension(paramInt, paramClass, (int)paramLong, false, 0, 0);
  }
  
  public static <M extends ExtendableMessageNano<M>, T extends MessageNano> Extension<M, T[]> createRepeatedMessageTyped(int paramInt, Class<T[]> paramClass, long paramLong)
  {
    return new Extension(paramInt, paramClass, (int)paramLong, true);
  }
  
  public static <M extends ExtendableMessageNano<M>, T> Extension<M, T> createRepeatedPrimitiveTyped(int paramInt, Class<T> paramClass, long paramLong1, long paramLong2, long paramLong3)
  {
    return new PrimitiveExtension(paramInt, paramClass, (int)paramLong1, true, (int)paramLong2, (int)paramLong3);
  }
  
  private T getRepeatedValueFrom(List<UnknownFieldData> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramList.size())
    {
      localObject = (UnknownFieldData)paramList.get(i);
      if (((UnknownFieldData)localObject).bytes.length != 0) {
        readDataInto((UnknownFieldData)localObject, localArrayList);
      }
      i += 1;
    }
    int j = localArrayList.size();
    if (j == 0)
    {
      paramList = null;
      return paramList;
    }
    Object localObject = this.clazz.cast(Array.newInstance(this.clazz.getComponentType(), j));
    i = 0;
    for (;;)
    {
      paramList = (List<UnknownFieldData>)localObject;
      if (i >= j) {
        break;
      }
      Array.set(localObject, i, localArrayList.get(i));
      i += 1;
    }
  }
  
  private T getSingularValueFrom(List<UnknownFieldData> paramList)
  {
    if (paramList.isEmpty()) {
      return null;
    }
    paramList = (UnknownFieldData)paramList.get(paramList.size() - 1);
    return (T)this.clazz.cast(readData(CodedInputByteBufferNano.newInstance(paramList.bytes)));
  }
  
  protected int computeRepeatedSerializedSize(Object paramObject)
  {
    int j = 0;
    int m = Array.getLength(paramObject);
    int i = 0;
    while (i < m)
    {
      int k = j;
      if (Array.get(paramObject, i) != null) {
        k = j + computeSingularSerializedSize(Array.get(paramObject, i));
      }
      i += 1;
      j = k;
    }
    return j;
  }
  
  int computeSerializedSize(Object paramObject)
  {
    if (this.repeated) {
      return computeRepeatedSerializedSize(paramObject);
    }
    return computeSingularSerializedSize(paramObject);
  }
  
  protected int computeSingularSerializedSize(Object paramObject)
  {
    int i = WireFormatNano.getTagFieldNumber(this.tag);
    switch (this.type)
    {
    default: 
      i = this.type;
      throw new IllegalArgumentException(24 + "Unknown type " + i);
    case 10: 
      return CodedOutputByteBufferNano.computeGroupSize(i, (MessageNano)paramObject);
    }
    return CodedOutputByteBufferNano.computeMessageSize(i, (MessageNano)paramObject);
  }
  
  final T getValueFrom(List<UnknownFieldData> paramList)
  {
    if (paramList == null) {
      return null;
    }
    if (this.repeated) {
      return (T)getRepeatedValueFrom(paramList);
    }
    return (T)getSingularValueFrom(paramList);
  }
  
  protected Object readData(CodedInputByteBufferNano paramCodedInputByteBufferNano)
  {
    Object localObject;
    if (this.repeated) {
      localObject = this.clazz.getComponentType();
    }
    for (;;)
    {
      try
      {
        switch (this.type)
        {
        case 10: 
          int i = this.type;
          throw new IllegalArgumentException(24 + "Unknown type " + i);
        }
      }
      catch (InstantiationException paramCodedInputByteBufferNano)
      {
        localObject = String.valueOf(localObject);
        throw new IllegalArgumentException(String.valueOf(localObject).length() + 33 + "Error creating instance of class " + (String)localObject, paramCodedInputByteBufferNano);
        localObject = this.clazz;
        continue;
        MessageNano localMessageNano = (MessageNano)((Class)localObject).newInstance();
        paramCodedInputByteBufferNano.readGroup(localMessageNano, WireFormatNano.getTagFieldNumber(this.tag));
        return localMessageNano;
        localMessageNano = (MessageNano)((Class)localObject).newInstance();
        paramCodedInputByteBufferNano.readMessage(localMessageNano);
        return localMessageNano;
      }
      catch (IllegalAccessException paramCodedInputByteBufferNano)
      {
        localObject = String.valueOf(localObject);
        throw new IllegalArgumentException(String.valueOf(localObject).length() + 33 + "Error creating instance of class " + (String)localObject, paramCodedInputByteBufferNano);
      }
      catch (IOException paramCodedInputByteBufferNano)
      {
        throw new IllegalArgumentException("Error reading extension field", paramCodedInputByteBufferNano);
      }
    }
  }
  
  protected void readDataInto(UnknownFieldData paramUnknownFieldData, List<Object> paramList)
  {
    paramList.add(readData(CodedInputByteBufferNano.newInstance(paramUnknownFieldData.bytes)));
  }
  
  protected void writeRepeatedData(Object paramObject, CodedOutputByteBufferNano paramCodedOutputByteBufferNano)
  {
    int j = Array.getLength(paramObject);
    int i = 0;
    while (i < j)
    {
      Object localObject = Array.get(paramObject, i);
      if (localObject != null) {
        writeSingularData(localObject, paramCodedOutputByteBufferNano);
      }
      i += 1;
    }
  }
  
  protected void writeSingularData(Object paramObject, CodedOutputByteBufferNano paramCodedOutputByteBufferNano)
  {
    for (;;)
    {
      try
      {
        paramCodedOutputByteBufferNano.writeRawVarint32(this.tag);
        switch (this.type)
        {
        case 10: 
          i = this.type;
          throw new IllegalArgumentException(24 + "Unknown type " + i);
        }
      }
      catch (IOException paramObject)
      {
        throw new IllegalStateException((Throwable)paramObject);
      }
      paramObject = (MessageNano)paramObject;
      int i = WireFormatNano.getTagFieldNumber(this.tag);
      paramCodedOutputByteBufferNano.writeGroupNoTag((MessageNano)paramObject);
      paramCodedOutputByteBufferNano.writeTag(i, 4);
      return;
      paramCodedOutputByteBufferNano.writeMessageNoTag((MessageNano)paramObject);
      return;
    }
  }
  
  void writeTo(Object paramObject, CodedOutputByteBufferNano paramCodedOutputByteBufferNano)
    throws IOException
  {
    if (this.repeated)
    {
      writeRepeatedData(paramObject, paramCodedOutputByteBufferNano);
      return;
    }
    writeSingularData(paramObject, paramCodedOutputByteBufferNano);
  }
  
  private static class PrimitiveExtension<M extends ExtendableMessageNano<M>, T>
    extends Extension<M, T>
  {
    private final int nonPackedTag;
    private final int packedTag;
    
    public PrimitiveExtension(int paramInt1, Class<T> paramClass, int paramInt2, boolean paramBoolean, int paramInt3, int paramInt4)
    {
      super(paramClass, paramInt2, paramBoolean, null);
      this.nonPackedTag = paramInt3;
      this.packedTag = paramInt4;
    }
    
    private int computePackedDataSize(Object paramObject)
    {
      int i = 0;
      int n = 0;
      int i1 = 0;
      int i2 = 0;
      int i3 = 0;
      int i4 = 0;
      int j = 0;
      int m = Array.getLength(paramObject);
      switch (this.type)
      {
      case 9: 
      case 10: 
      case 11: 
      case 12: 
      default: 
        i = this.type;
        throw new IllegalArgumentException(40 + "Unexpected non-packable type " + i);
      case 8: 
        i = m;
        return i;
      case 2: 
      case 7: 
      case 15: 
        return m * 4;
      case 1: 
      case 6: 
      case 16: 
        return m * 8;
      case 5: 
        k = 0;
        for (;;)
        {
          i = j;
          if (k >= m) {
            break;
          }
          j += CodedOutputByteBufferNano.computeInt32SizeNoTag(Array.getInt(paramObject, k));
          k += 1;
        }
      case 17: 
        k = 0;
        j = i;
        for (;;)
        {
          i = j;
          if (k >= m) {
            break;
          }
          j += CodedOutputByteBufferNano.computeSInt32SizeNoTag(Array.getInt(paramObject, k));
          k += 1;
        }
      case 13: 
        k = 0;
        j = n;
        for (;;)
        {
          i = j;
          if (k >= m) {
            break;
          }
          j += CodedOutputByteBufferNano.computeUInt32SizeNoTag(Array.getInt(paramObject, k));
          k += 1;
        }
      case 3: 
        k = 0;
        j = i1;
        for (;;)
        {
          i = j;
          if (k >= m) {
            break;
          }
          j += CodedOutputByteBufferNano.computeInt64SizeNoTag(Array.getLong(paramObject, k));
          k += 1;
        }
      case 18: 
        k = 0;
        j = i2;
        for (;;)
        {
          i = j;
          if (k >= m) {
            break;
          }
          j += CodedOutputByteBufferNano.computeSInt64SizeNoTag(Array.getLong(paramObject, k));
          k += 1;
        }
      case 4: 
        k = 0;
        j = i3;
        for (;;)
        {
          i = j;
          if (k >= m) {
            break;
          }
          j += CodedOutputByteBufferNano.computeUInt64SizeNoTag(Array.getLong(paramObject, k));
          k += 1;
        }
      }
      int k = 0;
      j = i4;
      for (;;)
      {
        i = j;
        if (k >= m) {
          break;
        }
        j += CodedOutputByteBufferNano.computeEnumSizeNoTag(Array.getInt(paramObject, k));
        k += 1;
      }
    }
    
    protected int computeRepeatedSerializedSize(Object paramObject)
    {
      if (this.tag == this.nonPackedTag) {
        return super.computeRepeatedSerializedSize(paramObject);
      }
      if (this.tag == this.packedTag)
      {
        i = computePackedDataSize(paramObject);
        j = CodedOutputByteBufferNano.computeRawVarint32Size(i);
        return CodedOutputByteBufferNano.computeRawVarint32Size(this.tag) + (i + j);
      }
      int i = this.tag;
      int j = this.nonPackedTag;
      int k = this.packedTag;
      throw new IllegalArgumentException(124 + "Unexpected repeated extension tag " + i + ", unequal to both non-packed variant " + j + " and packed variant " + k);
    }
    
    protected final int computeSingularSerializedSize(Object paramObject)
    {
      int i = WireFormatNano.getTagFieldNumber(this.tag);
      switch (this.type)
      {
      case 10: 
      case 11: 
      default: 
        i = this.type;
        throw new IllegalArgumentException(24 + "Unknown type " + i);
      case 1: 
        return CodedOutputByteBufferNano.computeDoubleSize(i, ((Double)paramObject).doubleValue());
      case 2: 
        return CodedOutputByteBufferNano.computeFloatSize(i, ((Float)paramObject).floatValue());
      case 3: 
        return CodedOutputByteBufferNano.computeInt64Size(i, ((Long)paramObject).longValue());
      case 4: 
        return CodedOutputByteBufferNano.computeUInt64Size(i, ((Long)paramObject).longValue());
      case 5: 
        return CodedOutputByteBufferNano.computeInt32Size(i, ((Integer)paramObject).intValue());
      case 6: 
        return CodedOutputByteBufferNano.computeFixed64Size(i, ((Long)paramObject).longValue());
      case 7: 
        return CodedOutputByteBufferNano.computeFixed32Size(i, ((Integer)paramObject).intValue());
      case 8: 
        return CodedOutputByteBufferNano.computeBoolSize(i, ((Boolean)paramObject).booleanValue());
      case 9: 
        return CodedOutputByteBufferNano.computeStringSize(i, (String)paramObject);
      case 12: 
        return CodedOutputByteBufferNano.computeBytesSize(i, (byte[])paramObject);
      case 13: 
        return CodedOutputByteBufferNano.computeUInt32Size(i, ((Integer)paramObject).intValue());
      case 14: 
        return CodedOutputByteBufferNano.computeEnumSize(i, ((Integer)paramObject).intValue());
      case 15: 
        return CodedOutputByteBufferNano.computeSFixed32Size(i, ((Integer)paramObject).intValue());
      case 16: 
        return CodedOutputByteBufferNano.computeSFixed64Size(i, ((Long)paramObject).longValue());
      case 17: 
        return CodedOutputByteBufferNano.computeSInt32Size(i, ((Integer)paramObject).intValue());
      }
      return CodedOutputByteBufferNano.computeSInt64Size(i, ((Long)paramObject).longValue());
    }
    
    protected Object readData(CodedInputByteBufferNano paramCodedInputByteBufferNano)
    {
      for (;;)
      {
        try
        {
          switch (this.type)
          {
          case 10: 
          case 11: 
            int i = this.type;
            throw new IllegalArgumentException(24 + "Unknown type " + i);
          }
        }
        catch (IOException paramCodedInputByteBufferNano)
        {
          throw new IllegalArgumentException("Error reading extension field", paramCodedInputByteBufferNano);
        }
        return Double.valueOf(paramCodedInputByteBufferNano.readDouble());
        return Float.valueOf(paramCodedInputByteBufferNano.readFloat());
        return Long.valueOf(paramCodedInputByteBufferNano.readInt64());
        return Long.valueOf(paramCodedInputByteBufferNano.readUInt64());
        return Integer.valueOf(paramCodedInputByteBufferNano.readInt32());
        return Long.valueOf(paramCodedInputByteBufferNano.readFixed64());
        return Integer.valueOf(paramCodedInputByteBufferNano.readFixed32());
        return Boolean.valueOf(paramCodedInputByteBufferNano.readBool());
        return paramCodedInputByteBufferNano.readString();
        return paramCodedInputByteBufferNano.readBytes();
        return Integer.valueOf(paramCodedInputByteBufferNano.readUInt32());
        return Integer.valueOf(paramCodedInputByteBufferNano.readEnum());
        return Integer.valueOf(paramCodedInputByteBufferNano.readSFixed32());
        return Long.valueOf(paramCodedInputByteBufferNano.readSFixed64());
        return Integer.valueOf(paramCodedInputByteBufferNano.readSInt32());
        long l = paramCodedInputByteBufferNano.readSInt64();
        return Long.valueOf(l);
      }
    }
    
    /* Error */
    protected void readDataInto(UnknownFieldData paramUnknownFieldData, List<Object> paramList)
    {
      // Byte code:
      //   0: aload_1
      //   1: getfield 282	com/google/i18n/phonenumbers/repackaged/com/google/protobuf/nano/UnknownFieldData:tag	I
      //   4: aload_0
      //   5: getfield 16	com/google/i18n/phonenumbers/repackaged/com/google/protobuf/nano/Extension$PrimitiveExtension:nonPackedTag	I
      //   8: if_icmpne +22 -> 30
      //   11: aload_2
      //   12: aload_0
      //   13: aload_1
      //   14: getfield 285	com/google/i18n/phonenumbers/repackaged/com/google/protobuf/nano/UnknownFieldData:bytes	[B
      //   17: invokestatic 289	com/google/i18n/phonenumbers/repackaged/com/google/protobuf/nano/CodedInputByteBufferNano:newInstance	([B)Lcom/google/i18n/phonenumbers/repackaged/com/google/protobuf/nano/CodedInputByteBufferNano;
      //   20: invokevirtual 291	com/google/i18n/phonenumbers/repackaged/com/google/protobuf/nano/Extension$PrimitiveExtension:readData	(Lcom/google/i18n/phonenumbers/repackaged/com/google/protobuf/nano/CodedInputByteBufferNano;)Ljava/lang/Object;
      //   23: invokeinterface 297 2 0
      //   28: pop
      //   29: return
      //   30: aload_1
      //   31: getfield 285	com/google/i18n/phonenumbers/repackaged/com/google/protobuf/nano/UnknownFieldData:bytes	[B
      //   34: invokestatic 289	com/google/i18n/phonenumbers/repackaged/com/google/protobuf/nano/CodedInputByteBufferNano:newInstance	([B)Lcom/google/i18n/phonenumbers/repackaged/com/google/protobuf/nano/CodedInputByteBufferNano;
      //   37: astore_1
      //   38: aload_1
      //   39: aload_1
      //   40: invokevirtual 300	com/google/i18n/phonenumbers/repackaged/com/google/protobuf/nano/CodedInputByteBufferNano:readRawVarint32	()I
      //   43: invokevirtual 303	com/google/i18n/phonenumbers/repackaged/com/google/protobuf/nano/CodedInputByteBufferNano:pushLimit	(I)I
      //   46: pop
      //   47: aload_1
      //   48: invokevirtual 306	com/google/i18n/phonenumbers/repackaged/com/google/protobuf/nano/CodedInputByteBufferNano:isAtEnd	()Z
      //   51: ifne -22 -> 29
      //   54: aload_2
      //   55: aload_0
      //   56: aload_1
      //   57: invokevirtual 291	com/google/i18n/phonenumbers/repackaged/com/google/protobuf/nano/Extension$PrimitiveExtension:readData	(Lcom/google/i18n/phonenumbers/repackaged/com/google/protobuf/nano/CodedInputByteBufferNano;)Ljava/lang/Object;
      //   60: invokeinterface 297 2 0
      //   65: pop
      //   66: goto -19 -> 47
      //   69: astore_1
      //   70: new 33	java/lang/IllegalArgumentException
      //   73: dup
      //   74: ldc -49
      //   76: aload_1
      //   77: invokespecial 210	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
      //   80: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	81	0	this	PrimitiveExtension
      //   0	81	1	paramUnknownFieldData	UnknownFieldData
      //   0	81	2	paramList	List<Object>
      // Exception table:
      //   from	to	target	type
      //   38	47	69	java/io/IOException
    }
    
    protected void writeRepeatedData(Object paramObject, CodedOutputByteBufferNano paramCodedOutputByteBufferNano)
    {
      if (this.tag == this.nonPackedTag) {
        super.writeRepeatedData(paramObject, paramCodedOutputByteBufferNano);
      }
      label144:
      label213:
      label235:
      label257:
      label279:
      label301:
      label323:
      label345:
      label367:
      label389:
      label411:
      label433:
      label455:
      label631:
      for (;;)
      {
        return;
        int j;
        int i;
        if (this.tag == this.packedTag)
        {
          j = Array.getLength(paramObject);
          i = computePackedDataSize(paramObject);
          try
          {
            paramCodedOutputByteBufferNano.writeRawVarint32(this.tag);
            paramCodedOutputByteBufferNano.writeRawVarint32(i);
            switch (this.type)
            {
            case 9: 
            case 10: 
            case 11: 
            case 12: 
              i = this.type;
              throw new IllegalArgumentException(27 + "Unpackable type " + i);
            }
          }
          catch (IOException paramObject)
          {
            throw new IllegalStateException((Throwable)paramObject);
          }
          i = 0;
          while (i < j)
          {
            paramCodedOutputByteBufferNano.writeBoolNoTag(Array.getBoolean(paramObject, i));
            i += 1;
          }
          continue;
          while (i < j)
          {
            paramCodedOutputByteBufferNano.writeFixed32NoTag(Array.getInt(paramObject, i));
            i += 1;
            continue;
            while (i < j)
            {
              paramCodedOutputByteBufferNano.writeSFixed32NoTag(Array.getInt(paramObject, i));
              i += 1;
              continue;
              while (i < j)
              {
                paramCodedOutputByteBufferNano.writeFloatNoTag(Array.getFloat(paramObject, i));
                i += 1;
                continue;
                while (i < j)
                {
                  paramCodedOutputByteBufferNano.writeFixed64NoTag(Array.getLong(paramObject, i));
                  i += 1;
                  continue;
                  while (i < j)
                  {
                    paramCodedOutputByteBufferNano.writeSFixed64NoTag(Array.getLong(paramObject, i));
                    i += 1;
                    continue;
                    while (i < j)
                    {
                      paramCodedOutputByteBufferNano.writeDoubleNoTag(Array.getDouble(paramObject, i));
                      i += 1;
                      continue;
                      while (i < j)
                      {
                        paramCodedOutputByteBufferNano.writeInt32NoTag(Array.getInt(paramObject, i));
                        i += 1;
                        continue;
                        while (i < j)
                        {
                          paramCodedOutputByteBufferNano.writeSInt32NoTag(Array.getInt(paramObject, i));
                          i += 1;
                          continue;
                          while (i < j)
                          {
                            paramCodedOutputByteBufferNano.writeUInt32NoTag(Array.getInt(paramObject, i));
                            i += 1;
                            continue;
                            while (i < j)
                            {
                              paramCodedOutputByteBufferNano.writeInt64NoTag(Array.getLong(paramObject, i));
                              i += 1;
                              continue;
                              while (i < j)
                              {
                                paramCodedOutputByteBufferNano.writeSInt64NoTag(Array.getLong(paramObject, i));
                                i += 1;
                                continue;
                                while (i < j)
                                {
                                  paramCodedOutputByteBufferNano.writeUInt64NoTag(Array.getLong(paramObject, i));
                                  i += 1;
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
        else
        {
          for (;;)
          {
            if (i >= j) {
              break label631;
            }
            paramCodedOutputByteBufferNano.writeEnumNoTag(Array.getInt(paramObject, i));
            i += 1;
            continue;
            i = this.tag;
            j = this.nonPackedTag;
            int k = this.packedTag;
            throw new IllegalArgumentException(124 + "Unexpected repeated extension tag " + i + ", unequal to both non-packed variant " + j + " and packed variant " + k);
            break label144;
            i = 0;
            break label213;
            break;
            i = 0;
            break label235;
            break;
            i = 0;
            break label257;
            break;
            i = 0;
            break label279;
            break;
            i = 0;
            break label301;
            break;
            i = 0;
            break label323;
            break;
            i = 0;
            break label345;
            break;
            i = 0;
            break label367;
            break;
            i = 0;
            break label389;
            break;
            i = 0;
            break label411;
            break;
            i = 0;
            break label433;
            break;
            i = 0;
            break label455;
            break;
            i = 0;
          }
        }
      }
    }
    
    protected final void writeSingularData(Object paramObject, CodedOutputByteBufferNano paramCodedOutputByteBufferNano)
    {
      for (;;)
      {
        try
        {
          paramCodedOutputByteBufferNano.writeRawVarint32(this.tag);
          switch (this.type)
          {
          case 10: 
          case 11: 
            int i = this.type;
            throw new IllegalArgumentException(24 + "Unknown type " + i);
          }
        }
        catch (IOException paramObject)
        {
          throw new IllegalStateException((Throwable)paramObject);
        }
        paramCodedOutputByteBufferNano.writeDoubleNoTag(((Double)paramObject).doubleValue());
        return;
        paramCodedOutputByteBufferNano.writeFloatNoTag(((Float)paramObject).floatValue());
        return;
        paramCodedOutputByteBufferNano.writeInt64NoTag(((Long)paramObject).longValue());
        return;
        paramCodedOutputByteBufferNano.writeUInt64NoTag(((Long)paramObject).longValue());
        return;
        paramCodedOutputByteBufferNano.writeInt32NoTag(((Integer)paramObject).intValue());
        return;
        paramCodedOutputByteBufferNano.writeFixed64NoTag(((Long)paramObject).longValue());
        return;
        paramCodedOutputByteBufferNano.writeFixed32NoTag(((Integer)paramObject).intValue());
        return;
        paramCodedOutputByteBufferNano.writeBoolNoTag(((Boolean)paramObject).booleanValue());
        return;
        paramCodedOutputByteBufferNano.writeStringNoTag((String)paramObject);
        return;
        paramCodedOutputByteBufferNano.writeBytesNoTag((byte[])paramObject);
        return;
        paramCodedOutputByteBufferNano.writeUInt32NoTag(((Integer)paramObject).intValue());
        return;
        paramCodedOutputByteBufferNano.writeEnumNoTag(((Integer)paramObject).intValue());
        return;
        paramCodedOutputByteBufferNano.writeSFixed32NoTag(((Integer)paramObject).intValue());
        return;
        paramCodedOutputByteBufferNano.writeSFixed64NoTag(((Long)paramObject).longValue());
        return;
        paramCodedOutputByteBufferNano.writeSInt32NoTag(((Integer)paramObject).intValue());
        return;
        paramCodedOutputByteBufferNano.writeSInt64NoTag(((Long)paramObject).longValue());
        return;
      }
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\i18n\phonenumbers\repackaged\com\google\protobuf\nano\Extension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */