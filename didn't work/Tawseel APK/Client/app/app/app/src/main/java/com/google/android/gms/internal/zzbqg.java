package com.google.android.gms.internal;

import android.util.Base64;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseReference.CompletionListener;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class zzbqg
{
  private static final char[] zzciB = "0123456789abcdef".toCharArray();
  
  public static int zzD(int paramInt1, int paramInt2)
  {
    if (paramInt1 < paramInt2) {
      return -1;
    }
    if (paramInt1 == paramInt2) {
      return 0;
    }
    return 1;
  }
  
  public static void zzaW(boolean paramBoolean)
  {
    zzb(paramBoolean, "");
  }
  
  public static zzbqd<Task<Void>, DatabaseReference.CompletionListener> zzb(DatabaseReference.CompletionListener paramCompletionListener)
  {
    if (paramCompletionListener == null)
    {
      paramCompletionListener = new TaskCompletionSource();
      DatabaseReference.CompletionListener local1 = new DatabaseReference.CompletionListener()
      {
        public void onComplete(DatabaseError paramAnonymousDatabaseError, DatabaseReference paramAnonymousDatabaseReference)
        {
          if (paramAnonymousDatabaseError != null)
          {
            zzbqg.this.setException(paramAnonymousDatabaseError.toException());
            return;
          }
          zzbqg.this.setResult(null);
        }
      };
      return new zzbqd(paramCompletionListener.getTask(), local1);
    }
    return new zzbqd(null, paramCompletionListener);
  }
  
  public static void zzb(boolean paramBoolean, String paramString)
  {
    if (!paramBoolean)
    {
      paramString = String.valueOf(paramString);
      if (paramString.length() != 0) {}
      for (paramString = "hardAssert failed: ".concat(paramString);; paramString = new String("hardAssert failed: ")) {
        throw new AssertionError(paramString);
      }
    }
  }
  
  public static int zzj(long paramLong1, long paramLong2)
  {
    if (paramLong1 < paramLong2) {
      return -1;
    }
    if (paramLong1 == paramLong2) {
      return 0;
    }
    return 1;
  }
  
  public static zzbqe zzjh(String paramString)
    throws DatabaseException
  {
    int i = 0;
    for (;;)
    {
      Object localObject1;
      try
      {
        j = paramString.indexOf("//");
        if (j == -1) {
          throw new URISyntaxException(paramString, "Invalid scheme specified");
        }
      }
      catch (URISyntaxException paramString)
      {
        throw new DatabaseException("Invalid Firebase Database url specified", paramString);
        int k = paramString.substring(j + 2).indexOf("/");
        if (k == -1) {
          break label388;
        }
        int j = j + 2 + k;
        Object localObject2 = paramString.substring(j).split("/");
        localObject1 = new StringBuilder();
        if (i < localObject2.length)
        {
          if (localObject2[i].equals("")) {
            break label391;
          }
          ((StringBuilder)localObject1).append("/");
          ((StringBuilder)localObject1).append(URLEncoder.encode(localObject2[i], "UTF-8"));
          break label391;
        }
        paramString = String.valueOf(paramString.substring(0, j));
        localObject1 = String.valueOf(((StringBuilder)localObject1).toString());
        if (((String)localObject1).length() != 0)
        {
          paramString = paramString.concat((String)localObject1);
          localObject2 = new URI(paramString);
          paramString = ((URI)localObject2).getPath().replace("+", " ");
          zzbqh.zzjn(paramString);
          paramString = new zzbmj(paramString);
          String str = ((URI)localObject2).getScheme();
          localObject1 = new zzbmm();
          ((zzbmm)localObject1).zzbZA = ((URI)localObject2).getHost().toLowerCase();
          i = ((URI)localObject2).getPort();
          if (i != -1)
          {
            ((zzbmm)localObject1).zzbZB = str.equals("https");
            localObject2 = String.valueOf(((zzbmm)localObject1).zzbZA);
            ((zzbmm)localObject1).zzbZA = (String.valueOf(localObject2).length() + 12 + (String)localObject2 + ":" + i);
            ((zzbmm)localObject1).zzaFs = localObject1.zzbZA.split("\\.")[0].toLowerCase();
            ((zzbmm)localObject1).zzcdm = ((zzbmm)localObject1).zzbZA;
            localObject2 = new zzbqe();
            ((zzbqe)localObject2).zzbXY = paramString;
            ((zzbqe)localObject2).zzbXP = ((zzbmm)localObject1);
            return (zzbqe)localObject2;
          }
        }
        else
        {
          paramString = new String(paramString);
          continue;
        }
      }
      catch (UnsupportedEncodingException paramString)
      {
        throw new DatabaseException("Failed to URLEncode the path", paramString);
      }
      ((zzbmm)localObject1).zzbZB = true;
      continue;
      label388:
      continue;
      label391:
      i += 1;
    }
  }
  
  public static String zzji(String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-1");
      localMessageDigest.update(paramString.getBytes("UTF-8"));
      paramString = Base64.encodeToString(localMessageDigest.digest(), 2);
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      throw new RuntimeException("Missing SHA-1 MessageDigest provider.", paramString);
    }
    catch (UnsupportedEncodingException paramString)
    {
      throw new RuntimeException("UTF-8 encoding is required for Firebase Database to run!");
    }
  }
  
  public static String zzjj(String paramString)
  {
    if (paramString.indexOf('\\') != -1) {}
    for (String str1 = paramString.replace("\\", "\\\\");; str1 = paramString)
    {
      String str2 = str1;
      if (paramString.indexOf('"') != -1) {
        str2 = str1.replace("\"", "\\\"");
      }
      return String.valueOf(str2).length() + 2 + "\"" + str2 + "\"";
    }
  }
  
  public static Integer zzjk(String paramString)
  {
    int j = 1;
    int i = 0;
    if ((paramString.length() > 11) || (paramString.length() == 0)) {
      return null;
    }
    if (paramString.charAt(0) == '-')
    {
      if (paramString.length() == 1) {
        return null;
      }
      i = 1;
    }
    for (;;)
    {
      long l = 0L;
      while (i < paramString.length())
      {
        int k = paramString.charAt(i);
        if ((k < 48) || (k > 57)) {
          return null;
        }
        l = l * 10L + (k - 48);
        i += 1;
      }
      if (j != 0)
      {
        if (-l < -2147483648L) {
          return null;
        }
        return Integer.valueOf((int)-l);
      }
      if (l > 2147483647L) {
        return null;
      }
      return Integer.valueOf((int)l);
      j = 0;
    }
  }
  
  public static String zzl(double paramDouble)
  {
    StringBuilder localStringBuilder = new StringBuilder(16);
    long l = Double.doubleToLongBits(paramDouble);
    int i = 7;
    while (i >= 0)
    {
      int j = (int)(l >>> i * 8 & 0xFF);
      localStringBuilder.append(zzciB[(j >> 4 & 0xF)]);
      localStringBuilder.append(zzciB[(j & 0xF)]);
      i -= 1;
    }
    return localStringBuilder.toString();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbqg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */