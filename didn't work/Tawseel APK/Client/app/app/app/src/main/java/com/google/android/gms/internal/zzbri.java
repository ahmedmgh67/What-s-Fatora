package com.google.android.gms.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public final class zzbri
{
  private final Field zzcmk;
  
  public zzbri(Field paramField)
  {
    zzbsj.zzw(paramField);
    this.zzcmk = paramField;
  }
  
  public <T extends Annotation> T getAnnotation(Class<T> paramClass)
  {
    return this.zzcmk.getAnnotation(paramClass);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbri.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */