package com.google.i18n.phonenumbers;

import com.google.i18n.phonenumbers.nano.Phonemetadata.PhoneMetadata;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.CodedInputByteBufferNano;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

class MetadataManager
{
  private static final String ALTERNATE_FORMATS_FILE_PREFIX = "/com/google/i18n/phonenumbers/data/PhoneNumberAlternateFormatsProto";
  private static final int BUFFER_SIZE = 16384;
  private static final Logger LOGGER = Logger.getLogger(MetadataManager.class.getName());
  private static final String SHORT_NUMBER_METADATA_FILE_PREFIX = "/com/google/i18n/phonenumbers/data/ShortNumberMetadataProto";
  private static final Map<Integer, Phonemetadata.PhoneMetadata> callingCodeToAlternateFormatsMap = Collections.synchronizedMap(new HashMap());
  private static final Set<Integer> countryCodeSet = AlternateFormatsCountryCodeSet.getCountryCodeSet();
  private static final Set<String> regionCodeSet = ShortNumbersRegionCodeSet.getRegionCodeSet();
  private static final Map<String, Phonemetadata.PhoneMetadata> regionCodeToShortNumberMetadataMap = Collections.synchronizedMap(new HashMap());
  
  private static void close(InputStream paramInputStream)
  {
    if (paramInputStream != null) {}
    try
    {
      paramInputStream.close();
      return;
    }
    catch (IOException paramInputStream)
    {
      LOGGER.log(Level.WARNING, paramInputStream.toString());
    }
  }
  
  static CodedInputByteBufferNano convertStreamToByteBuffer(ObjectInputStream paramObjectInputStream, int paramInt)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    byte[] arrayOfByte = new byte[paramInt];
    for (;;)
    {
      int i = paramObjectInputStream.read(arrayOfByte, 0, paramInt);
      if (i == -1) {
        break;
      }
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    }
    localByteArrayOutputStream.flush();
    return CodedInputByteBufferNano.newInstance(localByteArrayOutputStream.toByteArray());
  }
  
  static Phonemetadata.PhoneMetadata getAlternateFormatsForCountry(int paramInt)
  {
    if (!countryCodeSet.contains(Integer.valueOf(paramInt))) {
      return null;
    }
    synchronized (callingCodeToAlternateFormatsMap)
    {
      if (!callingCodeToAlternateFormatsMap.containsKey(Integer.valueOf(paramInt))) {
        loadAlternateFormatsMetadataFromFile(paramInt);
      }
      return (Phonemetadata.PhoneMetadata)callingCodeToAlternateFormatsMap.get(Integer.valueOf(paramInt));
    }
  }
  
  static Phonemetadata.PhoneMetadata getShortNumberMetadataForRegion(String paramString)
  {
    if (!regionCodeSet.contains(paramString)) {
      return null;
    }
    synchronized (regionCodeToShortNumberMetadataMap)
    {
      if (!regionCodeToShortNumberMetadataMap.containsKey(paramString)) {
        loadShortNumberMetadataFromFile(paramString);
      }
      return (Phonemetadata.PhoneMetadata)regionCodeToShortNumberMetadataMap.get(paramString);
    }
  }
  
  static Set<String> getShortNumberMetadataSupportedRegions()
  {
    return regionCodeSet;
  }
  
  /* Error */
  private static void loadAlternateFormatsMetadataFromFile(int paramInt)
  {
    // Byte code:
    //   0: ldc -92
    //   2: invokestatic 169	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   5: invokestatic 169	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   8: astore_2
    //   9: ldc -85
    //   11: new 173	java/lang/StringBuilder
    //   14: dup
    //   15: aload_2
    //   16: invokevirtual 177	java/lang/String:length	()I
    //   19: bipush 11
    //   21: iadd
    //   22: invokespecial 179	java/lang/StringBuilder:<init>	(I)V
    //   25: aload_2
    //   26: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   29: iload_0
    //   30: invokevirtual 186	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   33: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   36: invokevirtual 191	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   39: astore_3
    //   40: aconst_null
    //   41: astore_2
    //   42: aconst_null
    //   43: astore 4
    //   45: new 102	java/io/ObjectInputStream
    //   48: dup
    //   49: aload_3
    //   50: invokespecial 193	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   53: astore_3
    //   54: aload_3
    //   55: sipush 16384
    //   58: invokestatic 195	com/google/i18n/phonenumbers/MetadataManager:convertStreamToByteBuffer	(Ljava/io/ObjectInputStream;I)Lcom/google/i18n/phonenumbers/repackaged/com/google/protobuf/nano/CodedInputByteBufferNano;
    //   61: astore_2
    //   62: new 197	com/google/i18n/phonenumbers/nano/Phonemetadata$PhoneMetadataCollection
    //   65: dup
    //   66: invokespecial 198	com/google/i18n/phonenumbers/nano/Phonemetadata$PhoneMetadataCollection:<init>	()V
    //   69: astore 4
    //   71: aload 4
    //   73: aload_2
    //   74: invokevirtual 202	com/google/i18n/phonenumbers/nano/Phonemetadata$PhoneMetadataCollection:mergeFrom	(Lcom/google/i18n/phonenumbers/repackaged/com/google/protobuf/nano/CodedInputByteBufferNano;)Lcom/google/i18n/phonenumbers/nano/Phonemetadata$PhoneMetadataCollection;
    //   77: pop
    //   78: aload 4
    //   80: getfield 206	com/google/i18n/phonenumbers/nano/Phonemetadata$PhoneMetadataCollection:metadata	[Lcom/google/i18n/phonenumbers/nano/Phonemetadata$PhoneMetadata;
    //   83: astore_2
    //   84: aload_2
    //   85: arraylength
    //   86: istore_1
    //   87: iconst_0
    //   88: istore_0
    //   89: iload_0
    //   90: iload_1
    //   91: if_icmpge +34 -> 125
    //   94: aload_2
    //   95: iload_0
    //   96: aaload
    //   97: astore 4
    //   99: getstatic 55	com/google/i18n/phonenumbers/MetadataManager:callingCodeToAlternateFormatsMap	Ljava/util/Map;
    //   102: aload 4
    //   104: getfield 209	com/google/i18n/phonenumbers/nano/Phonemetadata$PhoneMetadata:countryCode	I
    //   107: invokestatic 132	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   110: aload 4
    //   112: invokeinterface 213 3 0
    //   117: pop
    //   118: iload_0
    //   119: iconst_1
    //   120: iadd
    //   121: istore_0
    //   122: goto -33 -> 89
    //   125: aload_3
    //   126: invokestatic 215	com/google/i18n/phonenumbers/MetadataManager:close	(Ljava/io/InputStream;)V
    //   129: return
    //   130: astore_2
    //   131: aload 4
    //   133: astore_3
    //   134: aload_2
    //   135: astore 4
    //   137: aload_3
    //   138: astore_2
    //   139: getstatic 42	com/google/i18n/phonenumbers/MetadataManager:LOGGER	Ljava/util/logging/Logger;
    //   142: getstatic 88	java/util/logging/Level:WARNING	Ljava/util/logging/Level;
    //   145: aload 4
    //   147: invokevirtual 91	java/io/IOException:toString	()Ljava/lang/String;
    //   150: invokevirtual 95	java/util/logging/Logger:log	(Ljava/util/logging/Level;Ljava/lang/String;)V
    //   153: aload_3
    //   154: invokestatic 215	com/google/i18n/phonenumbers/MetadataManager:close	(Ljava/io/InputStream;)V
    //   157: return
    //   158: astore_3
    //   159: aload_2
    //   160: invokestatic 215	com/google/i18n/phonenumbers/MetadataManager:close	(Ljava/io/InputStream;)V
    //   163: aload_3
    //   164: athrow
    //   165: astore 4
    //   167: aload_3
    //   168: astore_2
    //   169: aload 4
    //   171: astore_3
    //   172: goto -13 -> 159
    //   175: astore 4
    //   177: goto -40 -> 137
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	180	0	paramInt	int
    //   86	6	1	i	int
    //   8	87	2	localObject1	Object
    //   130	5	2	localIOException1	IOException
    //   138	31	2	localObject2	Object
    //   39	115	3	localObject3	Object
    //   158	10	3	localObject4	Object
    //   171	1	3	localObject5	Object
    //   43	103	4	localObject6	Object
    //   165	5	4	localObject7	Object
    //   175	1	4	localIOException2	IOException
    // Exception table:
    //   from	to	target	type
    //   45	54	130	java/io/IOException
    //   45	54	158	finally
    //   139	153	158	finally
    //   54	87	165	finally
    //   99	118	165	finally
    //   54	87	175	java/io/IOException
    //   99	118	175	java/io/IOException
  }
  
  /* Error */
  private static void loadShortNumberMetadataFromFile(String paramString)
  {
    // Byte code:
    //   0: ldc -39
    //   2: invokestatic 169	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   5: astore_3
    //   6: aload_0
    //   7: invokestatic 169	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   10: astore 4
    //   12: aload 4
    //   14: invokevirtual 177	java/lang/String:length	()I
    //   17: ifeq +99 -> 116
    //   20: aload_3
    //   21: aload 4
    //   23: invokevirtual 221	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
    //   26: astore_3
    //   27: ldc -85
    //   29: aload_3
    //   30: invokevirtual 191	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   33: astore 4
    //   35: aconst_null
    //   36: astore_3
    //   37: aconst_null
    //   38: astore 5
    //   40: new 102	java/io/ObjectInputStream
    //   43: dup
    //   44: aload 4
    //   46: invokespecial 193	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   49: astore 4
    //   51: aload 4
    //   53: sipush 16384
    //   56: invokestatic 195	com/google/i18n/phonenumbers/MetadataManager:convertStreamToByteBuffer	(Ljava/io/ObjectInputStream;I)Lcom/google/i18n/phonenumbers/repackaged/com/google/protobuf/nano/CodedInputByteBufferNano;
    //   59: astore_3
    //   60: new 197	com/google/i18n/phonenumbers/nano/Phonemetadata$PhoneMetadataCollection
    //   63: dup
    //   64: invokespecial 198	com/google/i18n/phonenumbers/nano/Phonemetadata$PhoneMetadataCollection:<init>	()V
    //   67: astore 5
    //   69: aload 5
    //   71: aload_3
    //   72: invokevirtual 202	com/google/i18n/phonenumbers/nano/Phonemetadata$PhoneMetadataCollection:mergeFrom	(Lcom/google/i18n/phonenumbers/repackaged/com/google/protobuf/nano/CodedInputByteBufferNano;)Lcom/google/i18n/phonenumbers/nano/Phonemetadata$PhoneMetadataCollection;
    //   75: pop
    //   76: aload 5
    //   78: getfield 206	com/google/i18n/phonenumbers/nano/Phonemetadata$PhoneMetadataCollection:metadata	[Lcom/google/i18n/phonenumbers/nano/Phonemetadata$PhoneMetadata;
    //   81: astore_3
    //   82: aload_3
    //   83: arraylength
    //   84: istore_2
    //   85: iconst_0
    //   86: istore_1
    //   87: iload_1
    //   88: iload_2
    //   89: if_icmpge +39 -> 128
    //   92: aload_3
    //   93: iload_1
    //   94: aaload
    //   95: astore 5
    //   97: getstatic 57	com/google/i18n/phonenumbers/MetadataManager:regionCodeToShortNumberMetadataMap	Ljava/util/Map;
    //   100: aload_0
    //   101: aload 5
    //   103: invokeinterface 213 3 0
    //   108: pop
    //   109: iload_1
    //   110: iconst_1
    //   111: iadd
    //   112: istore_1
    //   113: goto -26 -> 87
    //   116: new 166	java/lang/String
    //   119: dup
    //   120: aload_3
    //   121: invokespecial 223	java/lang/String:<init>	(Ljava/lang/String;)V
    //   124: astore_3
    //   125: goto -98 -> 27
    //   128: aload 4
    //   130: invokestatic 215	com/google/i18n/phonenumbers/MetadataManager:close	(Ljava/io/InputStream;)V
    //   133: return
    //   134: astore 4
    //   136: aload 5
    //   138: astore_0
    //   139: aload_0
    //   140: astore_3
    //   141: getstatic 42	com/google/i18n/phonenumbers/MetadataManager:LOGGER	Ljava/util/logging/Logger;
    //   144: getstatic 88	java/util/logging/Level:WARNING	Ljava/util/logging/Level;
    //   147: aload 4
    //   149: invokevirtual 91	java/io/IOException:toString	()Ljava/lang/String;
    //   152: invokevirtual 95	java/util/logging/Logger:log	(Ljava/util/logging/Level;Ljava/lang/String;)V
    //   155: aload_0
    //   156: invokestatic 215	com/google/i18n/phonenumbers/MetadataManager:close	(Ljava/io/InputStream;)V
    //   159: return
    //   160: astore_0
    //   161: aload_3
    //   162: invokestatic 215	com/google/i18n/phonenumbers/MetadataManager:close	(Ljava/io/InputStream;)V
    //   165: aload_0
    //   166: athrow
    //   167: astore_0
    //   168: aload 4
    //   170: astore_3
    //   171: goto -10 -> 161
    //   174: astore_3
    //   175: aload 4
    //   177: astore_0
    //   178: aload_3
    //   179: astore 4
    //   181: goto -42 -> 139
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	184	0	paramString	String
    //   86	27	1	i	int
    //   84	6	2	j	int
    //   5	166	3	localObject1	Object
    //   174	5	3	localIOException1	IOException
    //   10	119	4	localObject2	Object
    //   134	42	4	localIOException2	IOException
    //   179	1	4	localIOException3	IOException
    //   38	99	5	localPhoneMetadataCollection	com.google.i18n.phonenumbers.nano.Phonemetadata.PhoneMetadataCollection
    // Exception table:
    //   from	to	target	type
    //   40	51	134	java/io/IOException
    //   40	51	160	finally
    //   141	155	160	finally
    //   51	85	167	finally
    //   97	109	167	finally
    //   51	85	174	java/io/IOException
    //   97	109	174	java/io/IOException
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\i18n\phonenumbers\MetadataManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */