package com.google.firebase.storage;

import android.net.Uri;
import android.net.Uri.Builder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzbrb;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class FirebaseStorage
{
  private static final Map<FirebaseApp, FirebaseStorage> zzckq;
  private final FirebaseApp zzciR;
  private long zzckr = 600000L;
  private long zzcks = 600000L;
  private long zzckt = 120000L;
  
  static
  {
    if (!FirebaseStorage.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      zzckq = new HashMap();
      return;
    }
  }
  
  private FirebaseStorage(@NonNull FirebaseApp paramFirebaseApp)
  {
    this.zzciR = paramFirebaseApp;
  }
  
  @NonNull
  public static FirebaseStorage getInstance()
  {
    FirebaseApp localFirebaseApp = FirebaseApp.getInstance();
    if (localFirebaseApp != null) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zzb(bool, "You must call FirebaseApp.initialize() first.");
      if (($assertionsDisabled) || (localFirebaseApp != null)) {
        break;
      }
      throw new AssertionError();
    }
    return getInstance(localFirebaseApp);
  }
  
  @NonNull
  public static FirebaseStorage getInstance(@NonNull FirebaseApp paramFirebaseApp)
  {
    if (paramFirebaseApp != null) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zzb(bool, "Null is not a valid value for the FirebaseApp.");
      synchronized (zzckq)
      {
        FirebaseStorage localFirebaseStorage2 = (FirebaseStorage)zzckq.get(paramFirebaseApp);
        FirebaseStorage localFirebaseStorage1 = localFirebaseStorage2;
        if (localFirebaseStorage2 == null)
        {
          localFirebaseStorage1 = new FirebaseStorage(paramFirebaseApp);
          zzckq.put(paramFirebaseApp, localFirebaseStorage1);
        }
        return localFirebaseStorage1;
      }
    }
  }
  
  @Nullable
  private String zzaaL()
  {
    return this.zzciR.getOptions().getStorageBucket();
  }
  
  @NonNull
  private StorageReference zzz(@NonNull Uri paramUri)
  {
    zzac.zzb(paramUri, "uri must not be null");
    String str = zzaaL();
    if ((TextUtils.isEmpty(str)) || (paramUri.getAuthority().equalsIgnoreCase(str))) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zzb(bool, "The supplied bucketname is not available to this project.");
      return new StorageReference(paramUri, this);
    }
  }
  
  @NonNull
  public FirebaseApp getApp()
  {
    return this.zzciR;
  }
  
  public long getMaxDownloadRetryTimeMillis()
  {
    return this.zzcks;
  }
  
  public long getMaxOperationRetryTimeMillis()
  {
    return this.zzckt;
  }
  
  public long getMaxUploadRetryTimeMillis()
  {
    return this.zzckr;
  }
  
  @NonNull
  public StorageReference getReference()
  {
    if (TextUtils.isEmpty(zzaaL())) {
      throw new IllegalStateException("FirebaseApp was not initialized with a bucket name.");
    }
    return zzz(new Uri.Builder().scheme("gs").authority(zzaaL()).path("/").build());
  }
  
  @NonNull
  public StorageReference getReference(@NonNull String paramString)
  {
    if (!TextUtils.isEmpty(paramString)) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zzb(bool, "location must not be null or empty");
      String str = paramString.toLowerCase();
      if ((!str.startsWith("gs://")) && (!str.startsWith("https://")) && (!str.startsWith("http://"))) {
        break;
      }
      throw new IllegalArgumentException("location should not be a full URL.");
    }
    return getReference().child(paramString);
  }
  
  @NonNull
  public StorageReference getReferenceFromUrl(@NonNull String paramString)
  {
    boolean bool;
    if (!TextUtils.isEmpty(paramString))
    {
      bool = true;
      zzac.zzb(bool, "location must not be null or empty");
      Object localObject = paramString.toLowerCase();
      if ((!((String)localObject).startsWith("gs://")) && (!((String)localObject).startsWith("https://")) && (!((String)localObject).startsWith("http://"))) {
        break label134;
      }
      try
      {
        localObject = zzbrb.zzg(this.zzciR, paramString);
        if (localObject == null) {
          throw new IllegalArgumentException("The storage Uri could not be parsed.");
        }
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        paramString = String.valueOf(paramString);
        if (paramString.length() == 0) {}
      }
    }
    for (paramString = "Unable to parse location:".concat(paramString);; paramString = new String("Unable to parse location:"))
    {
      Log.e("FirebaseStorage", paramString, localUnsupportedEncodingException);
      throw new IllegalArgumentException("The storage Uri could not be parsed.");
      bool = false;
      break;
      StorageReference localStorageReference = zzz(localUnsupportedEncodingException);
      return localStorageReference;
    }
    label134:
    throw new IllegalArgumentException("The storage Uri could not be parsed.");
  }
  
  public void setMaxDownloadRetryTimeMillis(long paramLong)
  {
    this.zzcks = paramLong;
  }
  
  public void setMaxOperationRetryTimeMillis(long paramLong)
  {
    this.zzckt = paramLong;
  }
  
  public void setMaxUploadRetryTimeMillis(long paramLong)
  {
    this.zzckr = paramLong;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\storage\FirebaseStorage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */