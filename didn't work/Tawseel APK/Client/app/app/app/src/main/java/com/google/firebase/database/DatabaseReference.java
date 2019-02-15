package com.google.firebase.database;

import com.google.android.gms.internal.zzbma;
import com.google.android.gms.internal.zzbmd;
import com.google.android.gms.internal.zzbmj;
import com.google.android.gms.internal.zzbml;
import com.google.android.gms.internal.zzbmn;
import com.google.android.gms.internal.zzbmy;
import com.google.android.gms.internal.zzbos;
import com.google.android.gms.internal.zzbpe;
import com.google.android.gms.internal.zzbpf;
import com.google.android.gms.internal.zzbpi;
import com.google.android.gms.internal.zzbqd;
import com.google.android.gms.internal.zzbqf;
import com.google.android.gms.internal.zzbqg;
import com.google.android.gms.internal.zzbqh;
import com.google.android.gms.internal.zzbqi;
import com.google.android.gms.tasks.Task;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class DatabaseReference
  extends Query
{
  private static zzbmd zzbXE;
  
  DatabaseReference(zzbml paramzzbml, zzbmj paramzzbmj)
  {
    super(paramzzbml, paramzzbmj);
  }
  
  public static void goOffline()
  {
    zza(zzUW());
  }
  
  public static void goOnline()
  {
    zzb(zzUW());
  }
  
  private static zzbmd zzUW()
  {
    try
    {
      if (zzbXE == null) {
        zzbXE = new zzbmd();
      }
      zzbmd localzzbmd = zzbXE;
      return localzzbmd;
    }
    finally {}
  }
  
  private Task<Void> zza(final zzbpe paramzzbpe, final CompletionListener paramCompletionListener)
  {
    zzbqh.zzQ(zzVc());
    paramCompletionListener = zzbqg.zzb(paramCompletionListener);
    this.zzbXR.zzs(new Runnable()
    {
      public void run()
      {
        DatabaseReference.this.zzbXR.zza(DatabaseReference.this.zzVc().zza(zzbos.zzYY()), paramzzbpe, (DatabaseReference.CompletionListener)paramCompletionListener.zzZZ());
      }
    });
    return (Task)paramCompletionListener.getFirst();
  }
  
  private Task<Void> zza(final Object paramObject, final zzbpe paramzzbpe, CompletionListener paramCompletionListener)
  {
    zzbqh.zzQ(zzVc());
    zzbmy.zza(zzVc(), paramObject);
    paramObject = zzbqi.zzaw(paramObject);
    zzbqh.zzav(paramObject);
    paramObject = zzbpf.zza(paramObject, paramzzbpe);
    paramzzbpe = zzbqg.zzb(paramCompletionListener);
    this.zzbXR.zzs(new Runnable()
    {
      public void run()
      {
        DatabaseReference.this.zzbXR.zza(DatabaseReference.this.zzVc(), paramObject, (DatabaseReference.CompletionListener)paramzzbpe.zzZZ());
      }
    });
    return (Task)paramzzbpe.getFirst();
  }
  
  private Task<Void> zza(final Map<String, Object> paramMap, final CompletionListener paramCompletionListener)
  {
    if (paramMap == null) {
      throw new NullPointerException("Can't pass null for argument 'update' in updateChildren()");
    }
    paramMap = zzbqi.zzaF(paramMap);
    final zzbma localzzbma = zzbma.zzaB(zzbqh.zzc(zzVc(), paramMap));
    paramCompletionListener = zzbqg.zzb(paramCompletionListener);
    this.zzbXR.zzs(new Runnable()
    {
      public void run()
      {
        DatabaseReference.this.zzbXR.zza(DatabaseReference.this.zzVc(), localzzbma, (DatabaseReference.CompletionListener)paramCompletionListener.zzZZ(), paramMap);
      }
    });
    return (Task)paramCompletionListener.getFirst();
  }
  
  static void zza(zzbmd paramzzbmd)
  {
    zzbmn.zzd(paramzzbmd);
  }
  
  static void zzb(zzbmd paramzzbmd)
  {
    zzbmn.zze(paramzzbmd);
  }
  
  public DatabaseReference child(String paramString)
  {
    if (paramString == null) {
      throw new NullPointerException("Can't pass null for argument 'pathString' in child()");
    }
    if (zzVc().isEmpty()) {
      zzbqh.zzjn(paramString);
    }
    for (;;)
    {
      paramString = zzVc().zzh(new zzbmj(paramString));
      return new DatabaseReference(this.zzbXR, paramString);
      zzbqh.zzjm(paramString);
    }
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof DatabaseReference)) && (toString().equals(paramObject.toString()));
  }
  
  public FirebaseDatabase getDatabase()
  {
    return this.zzbXR.getDatabase();
  }
  
  public String getKey()
  {
    if (zzVc().isEmpty()) {
      return null;
    }
    return zzVc().zzXl().asString();
  }
  
  public DatabaseReference getParent()
  {
    zzbmj localzzbmj = zzVc().zzXk();
    if (localzzbmj != null) {
      return new DatabaseReference(this.zzbXR, localzzbmj);
    }
    return null;
  }
  
  public DatabaseReference getRoot()
  {
    return new DatabaseReference(this.zzbXR, new zzbmj(""));
  }
  
  public int hashCode()
  {
    return toString().hashCode();
  }
  
  public OnDisconnect onDisconnect()
  {
    zzbqh.zzQ(zzVc());
    return new OnDisconnect(this.zzbXR, zzVc());
  }
  
  public DatabaseReference push()
  {
    zzbos localzzbos = zzbos.zzjb(zzbqf.zzaQ(this.zzbXR.zzXp()));
    return new DatabaseReference(this.zzbXR, zzVc().zza(localzzbos));
  }
  
  public Task<Void> removeValue()
  {
    return setValue(null);
  }
  
  public void removeValue(CompletionListener paramCompletionListener)
  {
    setValue(null, paramCompletionListener);
  }
  
  public void runTransaction(Transaction.Handler paramHandler)
  {
    runTransaction(paramHandler, true);
  }
  
  public void runTransaction(final Transaction.Handler paramHandler, final boolean paramBoolean)
  {
    if (paramHandler == null) {
      throw new NullPointerException("Can't pass null for argument 'handler' in runTransaction()");
    }
    zzbqh.zzQ(zzVc());
    this.zzbXR.zzs(new Runnable()
    {
      public void run()
      {
        DatabaseReference.this.zzbXR.zza(DatabaseReference.this.zzVc(), paramHandler, paramBoolean);
      }
    });
  }
  
  public Task<Void> setPriority(Object paramObject)
  {
    return zza(zzbpi.zzas(paramObject), null);
  }
  
  public void setPriority(Object paramObject, CompletionListener paramCompletionListener)
  {
    zza(zzbpi.zzas(paramObject), paramCompletionListener);
  }
  
  public Task<Void> setValue(Object paramObject)
  {
    return zza(paramObject, zzbpi.zzas(null), null);
  }
  
  public Task<Void> setValue(Object paramObject1, Object paramObject2)
  {
    return zza(paramObject1, zzbpi.zzas(paramObject2), null);
  }
  
  public void setValue(Object paramObject, CompletionListener paramCompletionListener)
  {
    zza(paramObject, zzbpi.zzas(null), paramCompletionListener);
  }
  
  public void setValue(Object paramObject1, Object paramObject2, CompletionListener paramCompletionListener)
  {
    zza(paramObject1, zzbpi.zzas(paramObject2), paramCompletionListener);
  }
  
  public String toString()
  {
    Object localObject = getParent();
    if (localObject == null) {
      return this.zzbXR.toString();
    }
    try
    {
      localObject = String.valueOf(((DatabaseReference)localObject).toString());
      String str = String.valueOf(URLEncoder.encode(getKey(), "UTF-8").replace("+", "%20"));
      localObject = String.valueOf(localObject).length() + 1 + String.valueOf(str).length() + (String)localObject + "/" + str;
      return (String)localObject;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      localObject = String.valueOf(getKey());
      if (((String)localObject).length() == 0) {}
    }
    for (localObject = "Failed to URLEncode key: ".concat((String)localObject);; localObject = new String("Failed to URLEncode key: ")) {
      throw new DatabaseException((String)localObject, localUnsupportedEncodingException);
    }
  }
  
  public Task<Void> updateChildren(Map<String, Object> paramMap)
  {
    return zza(paramMap, null);
  }
  
  public void updateChildren(Map<String, Object> paramMap, CompletionListener paramCompletionListener)
  {
    zza(paramMap, paramCompletionListener);
  }
  
  public static abstract interface CompletionListener
  {
    public abstract void onComplete(DatabaseError paramDatabaseError, DatabaseReference paramDatabaseReference);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\database\DatabaseReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */