package com.google.firebase.database;

import com.google.android.gms.internal.zzbmj;
import com.google.android.gms.internal.zzbos;
import com.google.android.gms.internal.zzboz;
import com.google.android.gms.internal.zzbpd;
import com.google.android.gms.internal.zzbpe;
import com.google.android.gms.internal.zzbqh;
import com.google.android.gms.internal.zzbqi;
import java.util.Iterator;

public class DataSnapshot
{
  private final zzboz zzbXv;
  private final DatabaseReference zzbXw;
  
  DataSnapshot(DatabaseReference paramDatabaseReference, zzboz paramzzboz)
  {
    this.zzbXv = paramzzboz;
    this.zzbXw = paramDatabaseReference;
  }
  
  public DataSnapshot child(String paramString)
  {
    return new DataSnapshot(this.zzbXw.child(paramString), zzboz.zzn(this.zzbXv.zzUY().zzO(new zzbmj(paramString))));
  }
  
  public boolean exists()
  {
    return !this.zzbXv.zzUY().isEmpty();
  }
  
  public Iterable<DataSnapshot> getChildren()
  {
    new Iterable()
    {
      public Iterator<DataSnapshot> iterator()
      {
        new Iterator()
        {
          public boolean hasNext()
          {
            return DataSnapshot.1.this.zzbXx.hasNext();
          }
          
          public void remove()
          {
            throw new UnsupportedOperationException("remove called on immutable collection");
          }
          
          public DataSnapshot zzUV()
          {
            zzbpd localzzbpd = (zzbpd)DataSnapshot.1.this.zzbXx.next();
            return new DataSnapshot(DataSnapshot.zza(DataSnapshot.this).child(localzzbpd.zzZz().asString()), zzboz.zzn(localzzbpd.zzUY()));
          }
        };
      }
    };
  }
  
  public long getChildrenCount()
  {
    return this.zzbXv.zzUY().getChildCount();
  }
  
  public String getKey()
  {
    return this.zzbXw.getKey();
  }
  
  public Object getPriority()
  {
    Object localObject2 = this.zzbXv.zzUY().zzZe().getValue();
    Object localObject1 = localObject2;
    if ((localObject2 instanceof Long)) {
      localObject1 = Double.valueOf(((Long)localObject2).longValue());
    }
    return localObject1;
  }
  
  public DatabaseReference getRef()
  {
    return this.zzbXw;
  }
  
  public Object getValue()
  {
    return this.zzbXv.zzUY().getValue();
  }
  
  public <T> T getValue(GenericTypeIndicator<T> paramGenericTypeIndicator)
  {
    return (T)zzbqi.zza(this.zzbXv.zzUY().getValue(), paramGenericTypeIndicator);
  }
  
  public <T> T getValue(Class<T> paramClass)
  {
    return (T)zzbqi.zza(this.zzbXv.zzUY().getValue(), paramClass);
  }
  
  public Object getValue(boolean paramBoolean)
  {
    return this.zzbXv.zzUY().getValue(paramBoolean);
  }
  
  public boolean hasChild(String paramString)
  {
    if (this.zzbXw.getParent() == null) {
      zzbqh.zzjn(paramString);
    }
    while (!this.zzbXv.zzUY().zzO(new zzbmj(paramString)).isEmpty())
    {
      return true;
      zzbqh.zzjm(paramString);
    }
    return false;
  }
  
  public boolean hasChildren()
  {
    return this.zzbXv.zzUY().getChildCount() > 0;
  }
  
  public String toString()
  {
    String str1 = String.valueOf(this.zzbXw.getKey());
    String str2 = String.valueOf(this.zzbXv.zzUY().getValue(true));
    return String.valueOf(str1).length() + 33 + String.valueOf(str2).length() + "DataSnapshot { key = " + str1 + ", value = " + str2 + " }";
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\database\DataSnapshot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */