package com.google.firebase.database;

import com.google.android.gms.internal.zzbmj;
import com.google.android.gms.internal.zzbmq;
import com.google.android.gms.internal.zzbmy;
import com.google.android.gms.internal.zzbos;
import com.google.android.gms.internal.zzboz;
import com.google.android.gms.internal.zzbpd;
import com.google.android.gms.internal.zzbpe;
import com.google.android.gms.internal.zzbpf;
import com.google.android.gms.internal.zzbpi;
import com.google.android.gms.internal.zzbqh;
import com.google.android.gms.internal.zzbqi;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MutableData
{
  private final zzbmq zzbXU;
  private final zzbmj zzbXV;
  
  private MutableData(zzbmq paramzzbmq, zzbmj paramzzbmj)
  {
    this.zzbXU = paramzzbmq;
    this.zzbXV = paramzzbmj;
    zzbmy.zza(this.zzbXV, getValue());
  }
  
  MutableData(zzbpe paramzzbpe)
  {
    this(new zzbmq(paramzzbpe), new zzbmj(""));
  }
  
  public MutableData child(String paramString)
  {
    zzbqh.zzjm(paramString);
    return new MutableData(this.zzbXU, this.zzbXV.zzh(new zzbmj(paramString)));
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof MutableData)) && (this.zzbXU.equals(((MutableData)paramObject).zzbXU)) && (this.zzbXV.equals(((MutableData)paramObject).zzbXV));
  }
  
  public Iterable<MutableData> getChildren()
  {
    zzbpe localzzbpe = zzUY();
    if ((localzzbpe.isEmpty()) || (localzzbpe.zzZd())) {
      new Iterable()
      {
        public Iterator<MutableData> iterator()
        {
          new Iterator()
          {
            public boolean hasNext()
            {
              return false;
            }
            
            public void remove()
            {
              throw new UnsupportedOperationException("remove called on immutable collection");
            }
            
            public MutableData zzUZ()
            {
              throw new NoSuchElementException();
            }
          };
        }
      };
    }
    new Iterable()
    {
      public Iterator<MutableData> iterator()
      {
        new Iterator()
        {
          public boolean hasNext()
          {
            return MutableData.2.this.zzbXx.hasNext();
          }
          
          public void remove()
          {
            throw new UnsupportedOperationException("remove called on immutable collection");
          }
          
          public MutableData zzUZ()
          {
            zzbpd localzzbpd = (zzbpd)MutableData.2.this.zzbXx.next();
            return new MutableData(MutableData.zza(MutableData.this), MutableData.zzb(MutableData.this).zza(localzzbpd.zzZz()), null);
          }
        };
      }
    };
  }
  
  public long getChildrenCount()
  {
    return zzUY().getChildCount();
  }
  
  public String getKey()
  {
    if (this.zzbXV.zzXl() != null) {
      return this.zzbXV.zzXl().asString();
    }
    return null;
  }
  
  public Object getPriority()
  {
    return zzUY().zzZe().getValue();
  }
  
  public Object getValue()
  {
    return zzUY().getValue();
  }
  
  public <T> T getValue(GenericTypeIndicator<T> paramGenericTypeIndicator)
  {
    return (T)zzbqi.zza(zzUY().getValue(), paramGenericTypeIndicator);
  }
  
  public <T> T getValue(Class<T> paramClass)
  {
    return (T)zzbqi.zza(zzUY().getValue(), paramClass);
  }
  
  public boolean hasChild(String paramString)
  {
    return !zzUY().zzO(new zzbmj(paramString)).isEmpty();
  }
  
  public boolean hasChildren()
  {
    zzbpe localzzbpe = zzUY();
    return (!localzzbpe.zzZd()) && (!localzzbpe.isEmpty());
  }
  
  public void setPriority(Object paramObject)
  {
    this.zzbXU.zzg(this.zzbXV, zzUY().zzg(zzbpi.zzas(paramObject)));
  }
  
  public void setValue(Object paramObject)
    throws DatabaseException
  {
    zzbmy.zza(this.zzbXV, paramObject);
    paramObject = zzbqi.zzaw(paramObject);
    zzbqh.zzav(paramObject);
    this.zzbXU.zzg(this.zzbXV, zzbpf.zzar(paramObject));
  }
  
  public String toString()
  {
    Object localObject = this.zzbXV.zzXi();
    if (localObject != null) {}
    for (localObject = ((zzbos)localObject).asString();; localObject = "<none>")
    {
      String str = String.valueOf(this.zzbXU.zzXv().getValue(true));
      return String.valueOf(localObject).length() + 32 + String.valueOf(str).length() + "MutableData { key = " + (String)localObject + ", value = " + str + " }";
    }
  }
  
  zzbpe zzUY()
  {
    return this.zzbXU.zzq(this.zzbXV);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\database\MutableData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */