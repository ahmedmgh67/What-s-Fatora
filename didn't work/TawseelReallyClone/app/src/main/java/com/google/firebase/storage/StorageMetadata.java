package com.google.firebase.storage;

import android.net.Uri;
import android.net.Uri.Builder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzbqx;
import com.google.android.gms.internal.zzbrb;
import com.google.android.gms.internal.zzbre;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class StorageMetadata
{
  private String mPath = null;
  private String zzand = null;
  private String zzckA = null;
  private String zzckB = null;
  private String zzckC = null;
  private String zzckD = null;
  private String zzckE = null;
  private long zzckF;
  private String zzckG = null;
  private String zzckH = null;
  private String zzckI = null;
  private String zzckJ = null;
  private String zzckK = null;
  private Map<String, String> zzckL = null;
  private String[] zzckM = null;
  private StorageReference zzcki = null;
  private FirebaseStorage zzckz = null;
  
  public StorageMetadata() {}
  
  private StorageMetadata(@NonNull StorageMetadata paramStorageMetadata, boolean paramBoolean)
  {
    zzac.zzw(paramStorageMetadata);
    this.mPath = paramStorageMetadata.mPath;
    this.zzckz = paramStorageMetadata.zzckz;
    this.zzcki = paramStorageMetadata.zzcki;
    this.zzckA = paramStorageMetadata.zzckA;
    this.zzand = paramStorageMetadata.zzand;
    this.zzckH = paramStorageMetadata.zzckH;
    this.zzckI = paramStorageMetadata.zzckI;
    this.zzckJ = paramStorageMetadata.zzckJ;
    this.zzckK = paramStorageMetadata.zzckK;
    if (paramStorageMetadata.zzckL != null) {
      this.zzckL = new HashMap(paramStorageMetadata.zzckL);
    }
    this.zzckM = paramStorageMetadata.zzckM;
    if (paramBoolean)
    {
      this.zzckG = paramStorageMetadata.zzckG;
      this.zzckF = paramStorageMetadata.zzckF;
      this.zzckE = paramStorageMetadata.zzckE;
      this.zzckD = paramStorageMetadata.zzckD;
      this.zzckC = paramStorageMetadata.zzckC;
      this.zzckB = paramStorageMetadata.zzckB;
    }
  }
  
  private void zzjI(@Nullable String paramString)
  {
    if (!TextUtils.isEmpty(paramString)) {
      this.zzckM = paramString.split(",");
    }
  }
  
  @Nullable
  public String getBucket()
  {
    return this.zzckA;
  }
  
  @Nullable
  public String getCacheControl()
  {
    return this.zzckH;
  }
  
  @Nullable
  public String getContentDisposition()
  {
    return this.zzckI;
  }
  
  @Nullable
  public String getContentEncoding()
  {
    return this.zzckJ;
  }
  
  @Nullable
  public String getContentLanguage()
  {
    return this.zzckK;
  }
  
  public String getContentType()
  {
    return this.zzand;
  }
  
  public long getCreationTimeMillis()
  {
    return zzbrb.zzjM(this.zzckD);
  }
  
  public String getCustomMetadata(@NonNull String paramString)
  {
    if ((this.zzckL == null) || (TextUtils.isEmpty(paramString))) {
      return null;
    }
    return (String)this.zzckL.get(paramString);
  }
  
  @NonNull
  public Set<String> getCustomMetadataKeys()
  {
    if (this.zzckL == null) {
      return Collections.emptySet();
    }
    return this.zzckL.keySet();
  }
  
  @Nullable
  public Uri getDownloadUrl()
  {
    List localList = getDownloadUrls();
    if ((localList != null) && (localList.size() > 0)) {
      return (Uri)localList.get(0);
    }
    return null;
  }
  
  @Nullable
  public List<Uri> getDownloadUrls()
  {
    ArrayList localArrayList = new ArrayList();
    if ((this.zzckM != null) && (this.zzcki != null)) {}
    for (;;)
    {
      int i;
      try
      {
        String str1 = this.zzcki.zzaaN().zzA(this.zzcki.zzaaO());
        if (!TextUtils.isEmpty(str1))
        {
          String[] arrayOfString = this.zzckM;
          int j = arrayOfString.length;
          i = 0;
          if (i < j)
          {
            String str2 = arrayOfString[i];
            if (TextUtils.isEmpty(str2)) {
              break label153;
            }
            localArrayList.add(Uri.parse(String.valueOf(str1).length() + 17 + String.valueOf(str2).length() + str1 + "?alt=media&token=" + str2));
          }
        }
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("StorageMetadata", "Unexpected error getting DownloadUrls.", localRemoteException);
      }
      return localArrayList;
      label153:
      i += 1;
    }
  }
  
  @Nullable
  public String getGeneration()
  {
    return this.zzckB;
  }
  
  @Nullable
  public String getMd5Hash()
  {
    return this.zzckG;
  }
  
  @Nullable
  public String getMetadataGeneration()
  {
    return this.zzckC;
  }
  
  @Nullable
  public String getName()
  {
    String str2 = getPath();
    String str1;
    if (TextUtils.isEmpty(str2)) {
      str1 = null;
    }
    int i;
    do
    {
      return str1;
      i = str2.lastIndexOf('/');
      str1 = str2;
    } while (i == -1);
    return str2.substring(i + 1);
  }
  
  @NonNull
  public String getPath()
  {
    if (this.mPath != null) {
      return this.mPath;
    }
    return "";
  }
  
  @Nullable
  public StorageReference getReference()
  {
    if ((this.zzcki == null) && (this.zzckz != null))
    {
      String str1 = getBucket();
      String str2 = getPath();
      if ((TextUtils.isEmpty(str1)) || (TextUtils.isEmpty(str2))) {
        return null;
      }
      try
      {
        Uri localUri = new Uri.Builder().scheme("gs").authority(str1).encodedPath(zzbqx.zzjJ(str2)).build();
        return new StorageReference(localUri, this.zzckz);
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        Log.e("StorageMetadata", String.valueOf(str1).length() + 38 + String.valueOf(str2).length() + "Unable to create a valid default Uri. " + str1 + str2, localUnsupportedEncodingException);
        throw new IllegalStateException(localUnsupportedEncodingException);
      }
    }
    return this.zzcki;
  }
  
  public long getSizeBytes()
  {
    return this.zzckF;
  }
  
  public long getUpdatedTimeMillis()
  {
    return zzbrb.zzjM(this.zzckE);
  }
  
  @NonNull
  JSONObject zzaaM()
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    if (getContentType() != null) {
      localJSONObject.put("contentType", getContentType());
    }
    if (this.zzckL != null) {
      localJSONObject.put("metadata", new JSONObject(this.zzckL));
    }
    if (getCacheControl() != null) {
      localJSONObject.put("cacheControl", getCacheControl());
    }
    if (getContentDisposition() != null) {
      localJSONObject.put("contentDisposition", getContentDisposition());
    }
    if (getContentEncoding() != null) {
      localJSONObject.put("'contentEncoding", getContentEncoding());
    }
    if (getContentLanguage() != null) {
      localJSONObject.put("'contentLanguage", getContentLanguage());
    }
    return localJSONObject;
  }
  
  public static class Builder
  {
    StorageMetadata zzckN;
    boolean zzckO;
    
    public Builder()
    {
      this.zzckN = new StorageMetadata();
    }
    
    public Builder(StorageMetadata paramStorageMetadata)
    {
      this.zzckN = new StorageMetadata(paramStorageMetadata, false, null);
    }
    
    Builder(JSONObject paramJSONObject)
      throws JSONException
    {
      this.zzckN = new StorageMetadata();
      if (paramJSONObject != null)
      {
        zzu(paramJSONObject);
        this.zzckO = true;
      }
    }
    
    Builder(JSONObject paramJSONObject, StorageReference paramStorageReference)
      throws JSONException
    {
      this(paramJSONObject);
      StorageMetadata.zza(this.zzckN, paramStorageReference);
    }
    
    private void zzu(JSONObject paramJSONObject)
      throws JSONException
    {
      StorageMetadata.zza(this.zzckN, paramJSONObject.optString("generation"));
      StorageMetadata.zzb(this.zzckN, paramJSONObject.optString("name"));
      StorageMetadata.zzc(this.zzckN, paramJSONObject.optString("bucket"));
      StorageMetadata.zzd(this.zzckN, paramJSONObject.optString("metageneration"));
      StorageMetadata.zze(this.zzckN, paramJSONObject.optString("timeCreated"));
      StorageMetadata.zzf(this.zzckN, paramJSONObject.optString("updated"));
      StorageMetadata.zza(this.zzckN, paramJSONObject.optLong("size"));
      StorageMetadata.zzg(this.zzckN, paramJSONObject.optString("md5Hash"));
      StorageMetadata.zzh(this.zzckN, paramJSONObject.optString("downloadTokens"));
      setContentType(paramJSONObject.optString("contentType"));
      if (paramJSONObject.has("metadata"))
      {
        JSONObject localJSONObject = paramJSONObject.getJSONObject("metadata");
        Iterator localIterator = localJSONObject.keys();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          setCustomMetadata(str, localJSONObject.getString(str));
        }
      }
      setCacheControl(paramJSONObject.optString("cacheControl"));
      setContentDisposition(paramJSONObject.optString("contentDisposition"));
      setContentEncoding(paramJSONObject.optString("'contentEncoding"));
      setContentLanguage(paramJSONObject.optString("'contentLanguage"));
    }
    
    public StorageMetadata build()
    {
      return new StorageMetadata(this.zzckN, this.zzckO, null);
    }
    
    public Builder setCacheControl(String paramString)
    {
      StorageMetadata.zzl(this.zzckN, paramString);
      return this;
    }
    
    public Builder setContentDisposition(String paramString)
    {
      StorageMetadata.zzk(this.zzckN, paramString);
      return this;
    }
    
    public Builder setContentEncoding(String paramString)
    {
      StorageMetadata.zzj(this.zzckN, paramString);
      return this;
    }
    
    public Builder setContentLanguage(String paramString)
    {
      StorageMetadata.zzi(this.zzckN, paramString);
      return this;
    }
    
    public Builder setContentType(String paramString)
    {
      StorageMetadata.zzm(this.zzckN, paramString);
      return this;
    }
    
    public Builder setCustomMetadata(String paramString1, String paramString2)
    {
      if (StorageMetadata.zza(this.zzckN) == null) {
        StorageMetadata.zza(this.zzckN, new HashMap());
      }
      StorageMetadata.zza(this.zzckN).put(paramString1, paramString2);
      return this;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\storage\StorageMetadata.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */