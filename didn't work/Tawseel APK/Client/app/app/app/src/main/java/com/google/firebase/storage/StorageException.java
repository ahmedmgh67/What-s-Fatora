package com.google.firebase.storage;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzac;
import com.google.firebase.FirebaseException;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class StorageException
  extends FirebaseException
{
  public static final int ERROR_BUCKET_NOT_FOUND = -13011;
  public static final int ERROR_CANCELED = -13040;
  public static final int ERROR_INVALID_CHECKSUM = -13031;
  public static final int ERROR_NOT_AUTHENTICATED = -13020;
  public static final int ERROR_NOT_AUTHORIZED = -13021;
  public static final int ERROR_OBJECT_NOT_FOUND = -13010;
  public static final int ERROR_PROJECT_NOT_FOUND = -13012;
  public static final int ERROR_QUOTA_EXCEEDED = -13013;
  public static final int ERROR_RETRY_LIMIT_EXCEEDED = -13030;
  public static final int ERROR_UNKNOWN = -13000;
  static IOException zzckv;
  private final int zzPF;
  private final int zzckw;
  private String zzckx;
  private Throwable zzcky;
  
  static
  {
    if (!StorageException.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      zzckv = new IOException("The operation was canceled.");
      return;
    }
  }
  
  StorageException(int paramInt1, Throwable paramThrowable, int paramInt2)
  {
    this.zzckx = zzpY(paramInt1);
    this.zzcky = paramThrowable;
    this.zzPF = paramInt1;
    this.zzckw = paramInt2;
    paramThrowable = this.zzckx;
    String str1 = String.valueOf(Integer.toString(this.zzPF));
    String str2 = String.valueOf(Integer.toString(this.zzckw));
    Log.e("StorageException", String.valueOf(paramThrowable).length() + 52 + String.valueOf(str1).length() + String.valueOf(str2).length() + "StorageException has occurred.\n" + paramThrowable + "\n Code: " + str1 + " HttpResult: " + str2);
    if (this.zzcky != null) {
      Log.e("StorageException", this.zzcky.getMessage(), this.zzcky);
    }
  }
  
  StorageException(Status paramStatus)
  {
    this(zzcd(paramStatus), null, 0);
  }
  
  StorageException(@Nullable Throwable paramThrowable, int paramInt)
  {
    this(zza(paramThrowable, paramInt), paramThrowable, paramInt);
  }
  
  @NonNull
  public static StorageException fromErrorStatus(@NonNull Status paramStatus)
  {
    zzac.zzw(paramStatus);
    if (!paramStatus.isSuccess()) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zzas(bool);
      return new StorageException(paramStatus);
    }
  }
  
  @NonNull
  public static StorageException fromException(@NonNull Throwable paramThrowable)
  {
    paramThrowable = fromExceptionAndHttpCode(paramThrowable, 0);
    assert (paramThrowable != null);
    return paramThrowable;
  }
  
  @Nullable
  public static StorageException fromExceptionAndHttpCode(@Nullable Throwable paramThrowable, int paramInt)
  {
    if ((paramThrowable instanceof StorageException)) {
      return (StorageException)paramThrowable;
    }
    if ((zzpX(paramInt)) && (paramThrowable == null)) {
      return null;
    }
    return new StorageException(paramThrowable, paramInt);
  }
  
  private static int zza(@Nullable Throwable paramThrowable, int paramInt)
  {
    if (paramThrowable == zzckv) {
      return 52496;
    }
    switch (paramInt)
    {
    default: 
      return 52536;
    case -2: 
      return 52506;
    case 401: 
      return 52516;
    case 403: 
      return 52515;
    }
    return 52526;
  }
  
  private static int zzcd(Status paramStatus)
  {
    if (paramStatus.isCanceled()) {
      return 52496;
    }
    if (paramStatus == Status.zzayk) {
      return 52506;
    }
    return 52536;
  }
  
  private static boolean zzpX(int paramInt)
  {
    return (paramInt == 0) || ((paramInt >= 200) && (paramInt < 300));
  }
  
  static String zzpY(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "An unknown error occurred, please check the HTTP result code and inner exception for server response.";
    case -13000: 
      return "An unknown error occurred, please check the HTTP result code and inner exception for server response.";
    case -13010: 
      return "Object does not exist at location.";
    case -13011: 
      return "Bucket does not exist.";
    case -13012: 
      return "Project does not exist.";
    case -13013: 
      return "Quota for bucket exceeded, please view quota on www.firebase.google.com/storage.";
    case -13020: 
      return "User is not authenticated, please authenticate using Firebase Authentication and try again.";
    case -13021: 
      return "User does not have permission to access this object.";
    case -13030: 
      return "The operation retry limit has been exceeded.";
    case -13031: 
      return "Object has a checksum which does not match. Please retry the operation.";
    }
    return "The operation was cancelled.";
  }
  
  public Throwable getCause()
  {
    if (this.zzcky == this) {
      return null;
    }
    return this.zzcky;
  }
  
  public int getErrorCode()
  {
    return this.zzPF;
  }
  
  public int getHttpResultCode()
  {
    return this.zzckw;
  }
  
  public boolean getIsRecoverableException()
  {
    return getErrorCode() == 52506;
  }
  
  public String getMessage()
  {
    return this.zzckx;
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ErrorCode {}
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\storage\StorageException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */