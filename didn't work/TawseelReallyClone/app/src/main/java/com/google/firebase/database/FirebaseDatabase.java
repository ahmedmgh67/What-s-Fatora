package com.google.firebase.database;

import com.google.android.gms.internal.zzbmd;
import com.google.android.gms.internal.zzbmj;
import com.google.android.gms.internal.zzbml;
import com.google.android.gms.internal.zzbmm;
import com.google.android.gms.internal.zzbmn;
import com.google.android.gms.internal.zzbqe;
import com.google.android.gms.internal.zzbqg;
import com.google.android.gms.internal.zzbqh;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.util.HashMap;
import java.util.Map;

public class FirebaseDatabase
{
  private static final Map<String, FirebaseDatabase> zzbXN = new HashMap();
  private final FirebaseApp zzbXO;
  private final zzbmm zzbXP;
  private final zzbmd zzbXQ;
  private zzbml zzbXR;
  
  private FirebaseDatabase(FirebaseApp paramFirebaseApp, zzbmm paramzzbmm, zzbmd paramzzbmd)
  {
    this.zzbXO = paramFirebaseApp;
    this.zzbXP = paramzzbmm;
    this.zzbXQ = paramzzbmd;
  }
  
  public static FirebaseDatabase getInstance()
  {
    return getInstance(FirebaseApp.getInstance());
  }
  
  public static FirebaseDatabase getInstance(FirebaseApp paramFirebaseApp)
  {
    try
    {
      if (zzbXN.containsKey(paramFirebaseApp.getName())) {
        break label180;
      }
      localObject = paramFirebaseApp.getOptions().getDatabaseUrl();
      if (localObject == null) {
        throw new DatabaseException("Failed to get FirebaseDatabase instance: FirebaseApp object has no DatabaseURL in its FirebaseOptions object.");
      }
    }
    finally {}
    zzbqe localzzbqe = zzbqg.zzjh((String)localObject);
    if (!localzzbqe.zzbXY.isEmpty())
    {
      paramFirebaseApp = String.valueOf(localzzbqe.zzbXY.toString());
      throw new DatabaseException(String.valueOf(localObject).length() + 114 + String.valueOf(paramFirebaseApp).length() + "Configured Database URL '" + (String)localObject + "' is invalid. It should point to the root of a Firebase Database but it includes a path: " + paramFirebaseApp);
    }
    Object localObject = new zzbmd();
    if (!paramFirebaseApp.zzTt()) {
      ((zzbmd)localObject).zziZ(paramFirebaseApp.getName());
    }
    ((zzbmd)localObject).zzf(paramFirebaseApp);
    zzbXN.put(paramFirebaseApp.getName(), new FirebaseDatabase(paramFirebaseApp, localzzbqe.zzbXP, (zzbmd)localObject));
    label180:
    paramFirebaseApp = (FirebaseDatabase)zzbXN.get(paramFirebaseApp.getName());
    return paramFirebaseApp;
  }
  
  public static String getSdkVersion()
  {
    return "3.0.0";
  }
  
  private void zzUX()
  {
    try
    {
      if (this.zzbXR == null) {
        this.zzbXR = zzbmn.zza(this.zzbXQ, this.zzbXP, this);
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private void zziF(String paramString)
  {
    if (this.zzbXR != null) {
      throw new DatabaseException(String.valueOf(paramString).length() + 77 + "Calls to " + paramString + "() must be made before any other usage of FirebaseDatabase instance.");
    }
  }
  
  public FirebaseApp getApp()
  {
    return this.zzbXO;
  }
  
  public DatabaseReference getReference()
  {
    zzUX();
    return new DatabaseReference(this.zzbXR, zzbmj.zzXf());
  }
  
  public DatabaseReference getReference(String paramString)
  {
    zzUX();
    if (paramString == null) {
      throw new NullPointerException("Can't pass null for argument 'pathString' in FirebaseDatabase.getReference()");
    }
    zzbqh.zzjn(paramString);
    paramString = new zzbmj(paramString);
    return new DatabaseReference(this.zzbXR, paramString);
  }
  
  public DatabaseReference getReferenceFromUrl(String paramString)
  {
    zzUX();
    if (paramString == null) {
      throw new NullPointerException("Can't pass null for argument 'url' in FirebaseDatabase.getReferenceFromUrl()");
    }
    Object localObject = zzbqg.zzjh(paramString);
    if (!((zzbqe)localObject).zzbXP.zzbZA.equals(this.zzbXR.zzXo().zzbZA))
    {
      localObject = String.valueOf(getReference().toString());
      throw new DatabaseException(String.valueOf(paramString).length() + 93 + String.valueOf(localObject).length() + "Invalid URL (" + paramString + ") passed to getReference().  URL was expected to match configured Database URL: " + (String)localObject);
    }
    return new DatabaseReference(this.zzbXR, ((zzbqe)localObject).zzbXY);
  }
  
  public void goOffline()
  {
    zzUX();
    zzbmn.zzk(this.zzbXR);
  }
  
  public void goOnline()
  {
    zzUX();
    zzbmn.zzl(this.zzbXR);
  }
  
  public void purgeOutstandingWrites()
  {
    zzUX();
    this.zzbXR.zzs(new Runnable()
    {
      public void run()
      {
        FirebaseDatabase.zza(FirebaseDatabase.this).purgeOutstandingWrites();
      }
    });
  }
  
  public void setLogLevel(Logger.Level paramLevel)
  {
    try
    {
      zziF("setLogLevel");
      this.zzbXQ.setLogLevel(paramLevel);
      return;
    }
    finally
    {
      paramLevel = finally;
      throw paramLevel;
    }
  }
  
  public void setPersistenceEnabled(boolean paramBoolean)
  {
    try
    {
      zziF("setPersistenceEnabled");
      this.zzbXQ.setPersistenceEnabled(paramBoolean);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\database\FirebaseDatabase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */