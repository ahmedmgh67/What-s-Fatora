package com.google.firebase.database;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class DatabaseError
{
  public static final int DATA_STALE = -1;
  public static final int DISCONNECTED = -4;
  public static final int EXPIRED_TOKEN = -6;
  public static final int INVALID_TOKEN = -7;
  public static final int MAX_RETRIES = -8;
  public static final int NETWORK_ERROR = -24;
  public static final int OPERATION_FAILED = -2;
  public static final int OVERRIDDEN_BY_SET = -9;
  public static final int PERMISSION_DENIED = -3;
  public static final int UNAVAILABLE = -10;
  public static final int UNKNOWN_ERROR = -999;
  public static final int USER_CODE_EXCEPTION = -11;
  public static final int WRITE_CANCELED = -25;
  private static final Map<Integer, String> zzbXA = new HashMap();
  private static final Map<String, Integer> zzbXB;
  private final String message;
  private final int zzbXC;
  private final String zzbXD;
  
  static
  {
    zzbXA.put(Integer.valueOf(-1), "The transaction needs to be run again with current data");
    zzbXA.put(Integer.valueOf(-2), "The server indicated that this operation failed");
    zzbXA.put(Integer.valueOf(-3), "This client does not have permission to perform this operation");
    zzbXA.put(Integer.valueOf(-4), "The operation had to be aborted due to a network disconnect");
    zzbXA.put(Integer.valueOf(-6), "The supplied auth token has expired");
    zzbXA.put(Integer.valueOf(-7), "The supplied auth token was invalid");
    zzbXA.put(Integer.valueOf(-8), "The transaction had too many retries");
    zzbXA.put(Integer.valueOf(-9), "The transaction was overridden by a subsequent set");
    zzbXA.put(Integer.valueOf(-10), "The service is unavailable");
    zzbXA.put(Integer.valueOf(-11), "User code called from the Firebase Database runloop threw an exception:\n");
    zzbXA.put(Integer.valueOf(-24), "The operation could not be performed due to a network error");
    zzbXA.put(Integer.valueOf(-25), "The write was canceled by the user.");
    zzbXA.put(Integer.valueOf(64537), "An unknown error occurred");
    zzbXB = new HashMap();
    zzbXB.put("datastale", Integer.valueOf(-1));
    zzbXB.put("failure", Integer.valueOf(-2));
    zzbXB.put("permission_denied", Integer.valueOf(-3));
    zzbXB.put("disconnected", Integer.valueOf(-4));
    zzbXB.put("expired_token", Integer.valueOf(-6));
    zzbXB.put("invalid_token", Integer.valueOf(-7));
    zzbXB.put("maxretries", Integer.valueOf(-8));
    zzbXB.put("overriddenbyset", Integer.valueOf(-9));
    zzbXB.put("unavailable", Integer.valueOf(-10));
    zzbXB.put("network_error", Integer.valueOf(-24));
    zzbXB.put("write_canceled", Integer.valueOf(-25));
  }
  
  private DatabaseError(int paramInt, String paramString)
  {
    this(paramInt, paramString, null);
  }
  
  private DatabaseError(int paramInt, String paramString1, String paramString2)
  {
    this.zzbXC = paramInt;
    this.message = paramString1;
    paramString1 = paramString2;
    if (paramString2 == null) {
      paramString1 = "";
    }
    this.zzbXD = paramString1;
  }
  
  public static DatabaseError fromException(Throwable paramThrowable)
  {
    Object localObject = new StringWriter();
    paramThrowable.printStackTrace(new PrintWriter((Writer)localObject));
    paramThrowable = String.valueOf((String)zzbXA.get(Integer.valueOf(-11)));
    localObject = String.valueOf(((StringWriter)localObject).toString());
    if (((String)localObject).length() != 0) {}
    for (paramThrowable = paramThrowable.concat((String)localObject);; paramThrowable = new String(paramThrowable)) {
      return new DatabaseError(-11, paramThrowable);
    }
  }
  
  public static DatabaseError zzal(String paramString1, String paramString2)
  {
    return zzo(paramString1, paramString2, null);
  }
  
  public static DatabaseError zziE(String paramString)
  {
    return zzal(paramString, null);
  }
  
  public static DatabaseError zzo(String paramString1, String paramString2, String paramString3)
  {
    paramString1 = (Integer)zzbXB.get(paramString1.toLowerCase());
    if (paramString1 == null) {
      paramString1 = Integer.valueOf(64537);
    }
    for (;;)
    {
      if (paramString2 == null) {
        paramString2 = (String)zzbXA.get(paramString1);
      }
      for (;;)
      {
        return new DatabaseError(paramString1.intValue(), paramString2, paramString3);
      }
    }
  }
  
  public static DatabaseError zzpI(int paramInt)
  {
    if (!zzbXA.containsKey(Integer.valueOf(paramInt))) {
      throw new IllegalArgumentException(49 + "Invalid Firebase Database error code: " + paramInt);
    }
    return new DatabaseError(paramInt, (String)zzbXA.get(Integer.valueOf(paramInt)), null);
  }
  
  public int getCode()
  {
    return this.zzbXC;
  }
  
  public String getDetails()
  {
    return this.zzbXD;
  }
  
  public String getMessage()
  {
    return this.message;
  }
  
  public DatabaseException toException()
  {
    String str = String.valueOf(this.message);
    if (str.length() != 0) {}
    for (str = "Firebase Database error: ".concat(str);; str = new String("Firebase Database error: ")) {
      return new DatabaseException(str);
    }
  }
  
  public String toString()
  {
    String str = String.valueOf(this.message);
    if (str.length() != 0) {
      return "DatabaseError: ".concat(str);
    }
    return new String("DatabaseError: ");
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\database\DatabaseError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */