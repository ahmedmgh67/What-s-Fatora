package com.google.firebase.database;

import com.google.android.gms.internal.zzblz;
import com.google.android.gms.internal.zzbme;
import com.google.android.gms.internal.zzbmj;
import com.google.android.gms.internal.zzbml;
import com.google.android.gms.internal.zzbmz;
import com.google.android.gms.internal.zzbnc;
import com.google.android.gms.internal.zzbod;
import com.google.android.gms.internal.zzboe;
import com.google.android.gms.internal.zzbor;
import com.google.android.gms.internal.zzbos;
import com.google.android.gms.internal.zzbow;
import com.google.android.gms.internal.zzbox;
import com.google.android.gms.internal.zzbpa;
import com.google.android.gms.internal.zzbpe;
import com.google.android.gms.internal.zzbpg;
import com.google.android.gms.internal.zzbph;
import com.google.android.gms.internal.zzbpi;
import com.google.android.gms.internal.zzbpk;
import com.google.android.gms.internal.zzbpl;
import com.google.android.gms.internal.zzbqg;
import com.google.android.gms.internal.zzbqh;

public class Query
{
  protected final zzbml zzbXR;
  protected final zzbmj zzbXY;
  protected final zzbod zzbYc;
  private final boolean zzbYd;
  
  static
  {
    if (!Query.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  Query(zzbml paramzzbml, zzbmj paramzzbmj)
  {
    this.zzbXR = paramzzbml;
    this.zzbXY = paramzzbmj;
    this.zzbYc = zzbod.zzcfX;
    this.zzbYd = false;
  }
  
  Query(zzbml paramzzbml, zzbmj paramzzbmj, zzbod paramzzbod, boolean paramBoolean)
    throws DatabaseException
  {
    this.zzbXR = paramzzbml;
    this.zzbXY = paramzzbmj;
    this.zzbYc = paramzzbod;
    this.zzbYd = paramBoolean;
    zzbqg.zzb(paramzzbod.isValid(), "Validation of queries failed.");
  }
  
  private void zzVa()
  {
    if (this.zzbYc.zzYr()) {
      throw new IllegalArgumentException("Can't call equalTo() and startAt() combined");
    }
    if (this.zzbYc.zzYu()) {
      throw new IllegalArgumentException("Can't call equalTo() and endAt() combined");
    }
  }
  
  private void zzVb()
  {
    if (this.zzbYd) {
      throw new IllegalArgumentException("You can't combine multiple orderBy calls!");
    }
  }
  
  private Query zza(zzbpe paramzzbpe, String paramString)
  {
    zzbqh.zzjq(paramString);
    if ((!paramzzbpe.zzZd()) && (!paramzzbpe.isEmpty())) {
      throw new IllegalArgumentException("Can only use simple values for startAt()");
    }
    if (this.zzbYc.zzYr()) {
      throw new IllegalArgumentException("Can't call startAt() or equalTo() multiple times");
    }
    if (paramString != null) {}
    for (paramString = zzbos.zzjb(paramString);; paramString = null)
    {
      paramzzbpe = this.zzbYc.zza(paramzzbpe, paramString);
      zzb(paramzzbpe);
      zza(paramzzbpe);
      if (($assertionsDisabled) || (paramzzbpe.isValid())) {
        break;
      }
      throw new AssertionError();
    }
    return new Query(this.zzbXR, this.zzbXY, paramzzbpe, this.zzbYd);
  }
  
  private void zza(final zzbme paramzzbme)
  {
    zzbnc.zzXK().zzk(paramzzbme);
    this.zzbXR.zzs(new Runnable()
    {
      public void run()
      {
        Query.this.zzbXR.zze(paramzzbme);
      }
    });
  }
  
  private void zza(zzbod paramzzbod)
  {
    if (paramzzbod.zzYz().equals(zzbpa.zzZw()))
    {
      zzbpe localzzbpe;
      if (paramzzbod.zzYr())
      {
        localzzbpe = paramzzbod.zzYs();
        if ((paramzzbod.zzYt() != zzbos.zzYW()) || (!(localzzbpe instanceof zzbpk))) {
          throw new IllegalArgumentException("You must use startAt(String value), endAt(String value) or equalTo(String value) in combination with orderByKey(). Other type of values or using the version with 2 parameters is not supported");
        }
      }
      if (paramzzbod.zzYu())
      {
        localzzbpe = paramzzbod.zzYv();
        if ((paramzzbod.zzYw() != zzbos.zzYX()) || (!(localzzbpe instanceof zzbpk))) {
          throw new IllegalArgumentException("You must use startAt(String value), endAt(String value) or equalTo(String value) in combination with orderByKey(). Other type of values or using the version with 2 parameters is not supported");
        }
      }
    }
    else if ((paramzzbod.zzYz().equals(zzbph.zzZA())) && (((paramzzbod.zzYr()) && (!zzbpi.zzq(paramzzbod.zzYs()))) || ((paramzzbod.zzYu()) && (!zzbpi.zzq(paramzzbod.zzYv())))))
    {
      throw new IllegalArgumentException("When using orderByPriority(), values provided to startAt(), endAt(), or equalTo() must be valid priorities.");
    }
  }
  
  private Query zzb(zzbpe paramzzbpe, String paramString)
  {
    zzbqh.zzjq(paramString);
    if ((!paramzzbpe.zzZd()) && (!paramzzbpe.isEmpty())) {
      throw new IllegalArgumentException("Can only use simple values for endAt()");
    }
    if (paramString != null) {}
    for (paramString = zzbos.zzjb(paramString); this.zzbYc.zzYu(); paramString = null) {
      throw new IllegalArgumentException("Can't call endAt() or equalTo() multiple times");
    }
    paramzzbpe = this.zzbYc.zzb(paramzzbpe, paramString);
    zzb(paramzzbpe);
    zza(paramzzbpe);
    assert (paramzzbpe.isValid());
    return new Query(this.zzbXR, this.zzbXY, paramzzbpe, this.zzbYd);
  }
  
  private void zzb(final zzbme paramzzbme)
  {
    zzbnc.zzXK().zzi(paramzzbme);
    this.zzbXR.zzs(new Runnable()
    {
      public void run()
      {
        Query.this.zzbXR.zzf(paramzzbme);
      }
    });
  }
  
  private void zzb(zzbod paramzzbod)
  {
    if ((paramzzbod.zzYr()) && (paramzzbod.zzYu()) && (paramzzbod.zzYx()) && (!paramzzbod.zzYy())) {
      throw new IllegalArgumentException("Can't combine startAt(), endAt() and limit(). Use limitToFirst() or limitToLast() instead");
    }
  }
  
  public ChildEventListener addChildEventListener(ChildEventListener paramChildEventListener)
  {
    zzb(new zzblz(this.zzbXR, paramChildEventListener, zzVd()));
    return paramChildEventListener;
  }
  
  public void addListenerForSingleValueEvent(final ValueEventListener paramValueEventListener)
  {
    zzb(new zzbmz(this.zzbXR, new ValueEventListener()
    {
      public void onCancelled(DatabaseError paramAnonymousDatabaseError)
      {
        paramValueEventListener.onCancelled(paramAnonymousDatabaseError);
      }
      
      public void onDataChange(DataSnapshot paramAnonymousDataSnapshot)
      {
        Query.this.removeEventListener(this);
        paramValueEventListener.onDataChange(paramAnonymousDataSnapshot);
      }
    }, zzVd()));
  }
  
  public ValueEventListener addValueEventListener(ValueEventListener paramValueEventListener)
  {
    zzb(new zzbmz(this.zzbXR, paramValueEventListener, zzVd()));
    return paramValueEventListener;
  }
  
  public Query endAt(double paramDouble)
  {
    return endAt(paramDouble, null);
  }
  
  public Query endAt(double paramDouble, String paramString)
  {
    return zzb(new zzbow(Double.valueOf(paramDouble), zzbpi.zzZB()), paramString);
  }
  
  public Query endAt(String paramString)
  {
    return endAt(paramString, null);
  }
  
  public Query endAt(String paramString1, String paramString2)
  {
    if (paramString1 != null) {}
    for (paramString1 = new zzbpk(paramString1, zzbpi.zzZB());; paramString1 = zzbox.zzZp()) {
      return zzb(paramString1, paramString2);
    }
  }
  
  public Query endAt(boolean paramBoolean)
  {
    return endAt(paramBoolean, null);
  }
  
  public Query endAt(boolean paramBoolean, String paramString)
  {
    return zzb(new zzbor(Boolean.valueOf(paramBoolean), zzbpi.zzZB()), paramString);
  }
  
  public Query equalTo(double paramDouble)
  {
    zzVa();
    return startAt(paramDouble).endAt(paramDouble);
  }
  
  public Query equalTo(double paramDouble, String paramString)
  {
    zzVa();
    return startAt(paramDouble, paramString).endAt(paramDouble, paramString);
  }
  
  public Query equalTo(String paramString)
  {
    zzVa();
    return startAt(paramString).endAt(paramString);
  }
  
  public Query equalTo(String paramString1, String paramString2)
  {
    zzVa();
    return startAt(paramString1, paramString2).endAt(paramString1, paramString2);
  }
  
  public Query equalTo(boolean paramBoolean)
  {
    zzVa();
    return startAt(paramBoolean).endAt(paramBoolean);
  }
  
  public Query equalTo(boolean paramBoolean, String paramString)
  {
    zzVa();
    return startAt(paramBoolean, paramString).endAt(paramBoolean, paramString);
  }
  
  public DatabaseReference getRef()
  {
    return new DatabaseReference(this.zzbXR, zzVc());
  }
  
  public void keepSynced(final boolean paramBoolean)
  {
    if ((!this.zzbXY.isEmpty()) && (this.zzbXY.zzXi().equals(zzbos.zzYZ()))) {
      throw new DatabaseException("Can't call keepSynced() on .info paths.");
    }
    this.zzbXR.zzs(new Runnable()
    {
      public void run()
      {
        Query.this.zzbXR.zza(Query.this.zzVd(), paramBoolean);
      }
    });
  }
  
  public Query limitToFirst(int paramInt)
  {
    if (paramInt <= 0) {
      throw new IllegalArgumentException("Limit must be a positive integer!");
    }
    if (this.zzbYc.zzYx()) {
      throw new IllegalArgumentException("Can't call limitToLast on query with previously set limit!");
    }
    return new Query(this.zzbXR, this.zzbXY, this.zzbYc.zzpO(paramInt), this.zzbYd);
  }
  
  public Query limitToLast(int paramInt)
  {
    if (paramInt <= 0) {
      throw new IllegalArgumentException("Limit must be a positive integer!");
    }
    if (this.zzbYc.zzYx()) {
      throw new IllegalArgumentException("Can't call limitToLast on query with previously set limit!");
    }
    return new Query(this.zzbXR, this.zzbXY, this.zzbYc.zzpP(paramInt), this.zzbYd);
  }
  
  public Query orderByChild(String paramString)
  {
    if (paramString == null) {
      throw new NullPointerException("Key can't be null");
    }
    if ((paramString.equals("$key")) || (paramString.equals(".key"))) {
      throw new IllegalArgumentException(String.valueOf(paramString).length() + 54 + "Can't use '" + paramString + "' as path, please use orderByKey() instead!");
    }
    if ((paramString.equals("$priority")) || (paramString.equals(".priority"))) {
      throw new IllegalArgumentException(String.valueOf(paramString).length() + 59 + "Can't use '" + paramString + "' as path, please use orderByPriority() instead!");
    }
    if ((paramString.equals("$value")) || (paramString.equals(".value"))) {
      throw new IllegalArgumentException(String.valueOf(paramString).length() + 56 + "Can't use '" + paramString + "' as path, please use orderByValue() instead!");
    }
    zzbqh.zzjm(paramString);
    zzVb();
    paramString = new zzbmj(paramString);
    if (paramString.size() == 0) {
      throw new IllegalArgumentException("Can't use empty path, use orderByValue() instead!");
    }
    paramString = new zzbpg(paramString);
    return new Query(this.zzbXR, this.zzbXY, this.zzbYc.zza(paramString), true);
  }
  
  public Query orderByKey()
  {
    zzVb();
    zzbod localzzbod = this.zzbYc.zza(zzbpa.zzZw());
    zza(localzzbod);
    return new Query(this.zzbXR, this.zzbXY, localzzbod, true);
  }
  
  public Query orderByPriority()
  {
    zzVb();
    zzbod localzzbod = this.zzbYc.zza(zzbph.zzZA());
    zza(localzzbod);
    return new Query(this.zzbXR, this.zzbXY, localzzbod, true);
  }
  
  public Query orderByValue()
  {
    zzVb();
    return new Query(this.zzbXR, this.zzbXY, this.zzbYc.zza(zzbpl.zzZC()), true);
  }
  
  public void removeEventListener(ChildEventListener paramChildEventListener)
  {
    if (paramChildEventListener == null) {
      throw new NullPointerException("listener must not be null");
    }
    zza(new zzblz(this.zzbXR, paramChildEventListener, zzVd()));
  }
  
  public void removeEventListener(ValueEventListener paramValueEventListener)
  {
    if (paramValueEventListener == null) {
      throw new NullPointerException("listener must not be null");
    }
    zza(new zzbmz(this.zzbXR, paramValueEventListener, zzVd()));
  }
  
  public Query startAt(double paramDouble)
  {
    return startAt(paramDouble, null);
  }
  
  public Query startAt(double paramDouble, String paramString)
  {
    return zza(new zzbow(Double.valueOf(paramDouble), zzbpi.zzZB()), paramString);
  }
  
  public Query startAt(String paramString)
  {
    return startAt(paramString, null);
  }
  
  public Query startAt(String paramString1, String paramString2)
  {
    if (paramString1 != null) {}
    for (paramString1 = new zzbpk(paramString1, zzbpi.zzZB());; paramString1 = zzbox.zzZp()) {
      return zza(paramString1, paramString2);
    }
  }
  
  public Query startAt(boolean paramBoolean)
  {
    return startAt(paramBoolean, null);
  }
  
  public Query startAt(boolean paramBoolean, String paramString)
  {
    return zza(new zzbor(Boolean.valueOf(paramBoolean), zzbpi.zzZB()), paramString);
  }
  
  public zzbmj zzVc()
  {
    return this.zzbXY;
  }
  
  public zzboe zzVd()
  {
    return new zzboe(this.zzbXY, this.zzbYc);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\database\Query.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */