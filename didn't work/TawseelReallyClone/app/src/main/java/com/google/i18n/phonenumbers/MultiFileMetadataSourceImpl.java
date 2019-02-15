package com.google.i18n.phonenumbers;

import com.google.i18n.phonenumbers.nano.Phonemetadata.PhoneMetadata;
import com.google.i18n.phonenumbers.nano.Phonemetadata.PhoneMetadataCollection;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

final class MultiFileMetadataSourceImpl
  implements MetadataSource
{
  private static final String META_DATA_FILE_PREFIX = "/com/google/i18n/phonenumbers/data/PhoneNumberMetadataProto";
  private static final Logger logger = Logger.getLogger(MultiFileMetadataSourceImpl.class.getName());
  private final Map<Integer, Phonemetadata.PhoneMetadata> countryCodeToNonGeographicalMetadataMap = Collections.synchronizedMap(new HashMap());
  private final String filePrefix;
  private final MetadataLoader metadataLoader;
  private final Map<String, Phonemetadata.PhoneMetadata> regionToMetadataMap = Collections.synchronizedMap(new HashMap());
  
  public MultiFileMetadataSourceImpl(MetadataLoader paramMetadataLoader)
  {
    this("/com/google/i18n/phonenumbers/data/PhoneNumberMetadataProto", paramMetadataLoader);
  }
  
  public MultiFileMetadataSourceImpl(String paramString, MetadataLoader paramMetadataLoader)
  {
    this.filePrefix = paramString;
    this.metadataLoader = paramMetadataLoader;
  }
  
  private static Phonemetadata.PhoneMetadataCollection loadMetadataAndCloseInput(ObjectInputStream paramObjectInputStream)
  {
    Phonemetadata.PhoneMetadataCollection localPhoneMetadataCollection = new Phonemetadata.PhoneMetadataCollection();
    try
    {
      localPhoneMetadataCollection.mergeFrom(MetadataManager.convertStreamToByteBuffer(paramObjectInputStream, 16384));
      try
      {
        paramObjectInputStream.close();
        return localPhoneMetadataCollection;
      }
      catch (IOException paramObjectInputStream)
      {
        logger.log(Level.WARNING, "error closing input stream (ignored)", paramObjectInputStream);
        return localPhoneMetadataCollection;
      }
      try
      {
        paramObjectInputStream.close();
        throw ((Throwable)localObject);
      }
      catch (IOException paramObjectInputStream)
      {
        for (;;)
        {
          logger.log(Level.WARNING, "error closing input stream (ignored)", paramObjectInputStream);
        }
      }
    }
    catch (IOException localIOException)
    {
      localIOException = localIOException;
      logger.log(Level.WARNING, "error reading input (ignored)", localIOException);
      try
      {
        paramObjectInputStream.close();
        return localPhoneMetadataCollection;
      }
      catch (IOException paramObjectInputStream)
      {
        logger.log(Level.WARNING, "error closing input stream (ignored)", paramObjectInputStream);
        return localPhoneMetadataCollection;
      }
    }
    finally {}
  }
  
  public Phonemetadata.PhoneMetadata getMetadataForNonGeographicalRegion(int paramInt)
  {
    synchronized (this.countryCodeToNonGeographicalMetadataMap)
    {
      if (!this.countryCodeToNonGeographicalMetadataMap.containsKey(Integer.valueOf(paramInt)))
      {
        List localList = (List)CountryCodeToRegionCodeMap.getCountryCodeToRegionCodeMap().get(Integer.valueOf(paramInt));
        if ((localList.size() == 1) && ("001".equals(localList.get(0)))) {
          loadMetadataFromFile("001", paramInt);
        }
      }
      return (Phonemetadata.PhoneMetadata)this.countryCodeToNonGeographicalMetadataMap.get(Integer.valueOf(paramInt));
    }
  }
  
  public Phonemetadata.PhoneMetadata getMetadataForRegion(String paramString)
  {
    synchronized (this.regionToMetadataMap)
    {
      if (!this.regionToMetadataMap.containsKey(paramString)) {
        loadMetadataFromFile(paramString, 0);
      }
      return (Phonemetadata.PhoneMetadata)this.regionToMetadataMap.get(paramString);
    }
  }
  
  void loadMetadataFromFile(String paramString, int paramInt)
  {
    boolean bool = "001".equals(paramString);
    String str2 = String.valueOf(String.valueOf(this.filePrefix));
    Object localObject1;
    Object localObject2;
    if (bool)
    {
      localObject1 = String.valueOf(paramInt);
      localObject1 = String.valueOf(String.valueOf(localObject1));
      str2 = str2.length() + 1 + ((String)localObject1).length() + str2 + "_" + (String)localObject1;
      localObject1 = this.metadataLoader.loadMetadata(str2);
      if (localObject1 != null) {
        break label196;
      }
      localObject1 = logger;
      localObject2 = Level.SEVERE;
      paramString = String.valueOf(str2);
      if (paramString.length() == 0) {
        break label170;
      }
      paramString = "missing metadata: ".concat(paramString);
      label127:
      ((Logger)localObject1).log((Level)localObject2, paramString);
      paramString = String.valueOf(str2);
      if (paramString.length() == 0) {
        break label183;
      }
    }
    label170:
    label183:
    for (paramString = "missing metadata: ".concat(paramString);; paramString = new String("missing metadata: "))
    {
      throw new IllegalStateException(paramString);
      localObject1 = paramString;
      break;
      paramString = new String("missing metadata: ");
      break label127;
    }
    label196:
    label277:
    Object localObject3;
    try
    {
      localObject2 = loadMetadataAndCloseInput(new ObjectInputStream((InputStream)localObject1)).metadata;
      if (localObject2.length != 0) {
        break label385;
      }
      localObject1 = logger;
      localObject2 = Level.SEVERE;
      paramString = String.valueOf(str2);
      if (paramString.length() == 0) {
        break label359;
      }
      paramString = "empty metadata: ".concat(paramString);
      ((Logger)localObject1).log((Level)localObject2, paramString);
      paramString = String.valueOf(str2);
      if (paramString.length() == 0) {
        break label372;
      }
      paramString = "empty metadata: ".concat(paramString);
      throw new IllegalStateException(paramString);
    }
    catch (IOException localIOException)
    {
      localObject2 = logger;
      localObject3 = Level.SEVERE;
      paramString = String.valueOf(str2);
      if (paramString.length() == 0) {
        break label490;
      }
    }
    paramString = "cannot load/parse metadata: ".concat(paramString);
    label318:
    ((Logger)localObject2).log((Level)localObject3, paramString, localIOException);
    paramString = String.valueOf(str2);
    if (paramString.length() != 0) {}
    for (paramString = "cannot load/parse metadata: ".concat(paramString);; paramString = new String("cannot load/parse metadata: "))
    {
      throw new RuntimeException(paramString, localIOException);
      label359:
      paramString = new String("empty metadata: ");
      break;
      label372:
      paramString = new String("empty metadata: ");
      break label277;
      label385:
      Level localLevel;
      if (localObject2.length > 1)
      {
        localObject3 = logger;
        localLevel = Level.WARNING;
        str1 = String.valueOf(str2);
        if (str1.length() == 0) {
          break label462;
        }
      }
      label462:
      for (String str1 = "invalid metadata (too many entries): ".concat(str1);; str1 = new String("invalid metadata (too many entries): "))
      {
        ((Logger)localObject3).log(localLevel, str1);
        str1 = localObject2[0];
        if (!bool) {
          break;
        }
        this.countryCodeToNonGeographicalMetadataMap.put(Integer.valueOf(paramInt), str1);
        return;
      }
      this.regionToMetadataMap.put(paramString, str1);
      return;
      label490:
      paramString = new String("cannot load/parse metadata: ");
      break label318;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\i18n\phonenumbers\MultiFileMetadataSourceImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */