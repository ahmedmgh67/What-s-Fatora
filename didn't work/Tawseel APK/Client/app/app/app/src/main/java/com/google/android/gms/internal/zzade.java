package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.DataHolder.zza;
import com.google.android.gms.common.data.zzd;
import com.google.firebase.iid.zzc;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class zzade
  implements zzacz
{
  private static final Charset UTF_8 = Charset.forName("UTF-8");
  private static final Pattern zzaHr = Pattern.compile("^(1|true|t|yes|y|on)$", 2);
  private static final Pattern zzaHs = Pattern.compile("^(0|false|f|no|n|off|)$", 2);
  
  private static HashMap<String, TreeMap<String, byte[]>> zza(zzadl paramzzadl)
  {
    if (paramzzadl == null) {}
    do
    {
      return null;
      localObject1 = paramzzadl.zzzb();
    } while (localObject1 == null);
    Object localObject1 = (zzadp)new zzd((DataHolder)localObject1, zzadp.CREATOR).get(0);
    paramzzadl.zzzc();
    paramzzadl = new HashMap();
    Iterator localIterator1 = ((zzadp)localObject1).zzzd().keySet().iterator();
    while (localIterator1.hasNext())
    {
      Object localObject2 = (String)localIterator1.next();
      TreeMap localTreeMap = new TreeMap();
      paramzzadl.put(localObject2, localTreeMap);
      localObject2 = ((zzadp)localObject1).zzzd().getBundle((String)localObject2);
      Iterator localIterator2 = ((Bundle)localObject2).keySet().iterator();
      while (localIterator2.hasNext())
      {
        String str = (String)localIterator2.next();
        localTreeMap.put(str, ((Bundle)localObject2).getByteArray(str));
      }
    }
    return paramzzadl;
  }
  
  private static Status zzdn(int paramInt)
  {
    return new Status(paramInt, zzada.getStatusCodeString(paramInt));
  }
  
  public PendingResult<zzacz.zzb> zza(GoogleApiClient paramGoogleApiClient, final zzacz.zza paramzza)
  {
    if ((paramGoogleApiClient == null) || (paramzza == null)) {
      return null;
    }
    paramGoogleApiClient.zza(new zzc(paramGoogleApiClient)
    {
      protected zzacz.zzb zzH(Status paramAnonymousStatus)
      {
        return new zzade.zzd(paramAnonymousStatus, new HashMap());
      }
      
      protected void zza(Context paramAnonymousContext, zzado paramAnonymouszzado)
        throws RemoteException
      {
        Object localObject1 = zzd.zzwC();
        Object localObject2 = paramzza.zzyR().entrySet().iterator();
        Object localObject3;
        while (((Iterator)localObject2).hasNext())
        {
          localObject3 = (Map.Entry)((Iterator)localObject2).next();
          zzd.zza((DataHolder.zza)localObject1, new zzadh((String)((Map.Entry)localObject3).getKey(), (String)((Map.Entry)localObject3).getValue()));
        }
        DataHolder localDataHolder = ((DataHolder.zza)localObject1).zzcE(0);
        if (zzaas.zzay(paramAnonymousContext) == Status.zzayh) {
          localObject2 = zzaas.zzwj();
        }
        for (;;)
        {
          try
          {
            localObject1 = zzc.zzaab().getId();
          }
          catch (IllegalStateException localIllegalStateException1)
          {
            List localList;
            localObject1 = null;
          }
          try
          {
            localObject3 = zzc.zzaab().getToken();
            localList = zzadd.zzaS(paramAnonymousContext);
            paramAnonymousContext = new zzadj(paramAnonymousContext.getPackageName(), paramzza.zzyQ(), localDataHolder, (String)localObject2, (String)localObject1, (String)localObject3, null, paramzza.zzyS(), localList);
            paramAnonymouszzado.zza(this.zzaHu, paramAnonymousContext);
            localDataHolder.close();
            return;
          }
          catch (IllegalStateException localIllegalStateException2)
          {
            Object localObject4;
            for (;;) {}
          }
          localObject2 = null;
          continue;
          if (Log.isLoggable("ConfigApiImpl", 3)) {
            Log.d("ConfigApiImpl", "Cannot retrieve instanceId or instanceIdToken.", localIllegalStateException1);
          }
          localObject4 = null;
        }
      }
    });
  }
  
  static abstract class zza
    extends zzadn.zza
  {
    public void zzI(Status paramStatus)
    {
      throw new UnsupportedOperationException();
    }
    
    public void zza(Status paramStatus, zzadl paramzzadl)
    {
      throw new UnsupportedOperationException();
    }
    
    public void zza(Status paramStatus, Map paramMap)
    {
      throw new UnsupportedOperationException();
    }
    
    public void zza(Status paramStatus, byte[] paramArrayOfByte)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  static abstract class zzb<R extends Result>
    extends zzzv.zza<R, zzadg>
  {
    public zzb(GoogleApiClient paramGoogleApiClient)
    {
      super(paramGoogleApiClient);
    }
    
    protected abstract void zza(Context paramContext, zzado paramzzado)
      throws RemoteException;
    
    protected final void zza(zzadg paramzzadg)
      throws RemoteException
    {
      zza(paramzzadg.getContext(), (zzado)paramzzadg.zzwW());
    }
  }
  
  static abstract class zzc
    extends zzade.zzb<zzacz.zzb>
  {
    protected zzadn zzaHu = new zzade.zza()
    {
      public void zza(Status paramAnonymousStatus, zzadl paramAnonymouszzadl)
      {
        if ((paramAnonymouszzadl.getStatusCode() == 6502) || (paramAnonymouszzadl.getStatusCode() == 6507))
        {
          zzade.zzc.this.zzb(new zzade.zzd(zzade.zzdo(paramAnonymouszzadl.getStatusCode()), zzade.zzb(paramAnonymouszzadl), paramAnonymouszzadl.getThrottleEndTimeMillis()));
          return;
        }
        zzade.zzc.this.zzb(new zzade.zzd(zzade.zzdo(paramAnonymouszzadl.getStatusCode()), zzade.zzb(paramAnonymouszzadl)));
      }
    };
    
    public zzc(GoogleApiClient paramGoogleApiClient)
    {
      super();
    }
  }
  
  public static class zzd
    implements zzacz.zzb
  {
    private final Map<String, TreeMap<String, byte[]>> zzaHw;
    private final long zzaHx;
    private final Status zzahq;
    
    public zzd(Status paramStatus, Map<String, TreeMap<String, byte[]>> paramMap)
    {
      this(paramStatus, paramMap, -1L);
    }
    
    public zzd(Status paramStatus, Map<String, TreeMap<String, byte[]>> paramMap, long paramLong)
    {
      this.zzahq = paramStatus;
      this.zzaHw = paramMap;
      this.zzaHx = paramLong;
    }
    
    public Status getStatus()
    {
      return this.zzahq;
    }
    
    public long getThrottleEndTimeMillis()
    {
      return this.zzaHx;
    }
    
    public boolean zzH(String paramString1, String paramString2)
    {
      if ((this.zzaHw == null) || (this.zzaHw.get(paramString2) == null)) {
        return false;
      }
      return ((TreeMap)this.zzaHw.get(paramString2)).get(paramString1) != null;
    }
    
    public byte[] zza(String paramString1, byte[] paramArrayOfByte, String paramString2)
    {
      if (zzH(paramString1, paramString2)) {
        return (byte[])((TreeMap)this.zzaHw.get(paramString2)).get(paramString1);
      }
      return paramArrayOfByte;
    }
    
    public Map<String, Set<String>> zzyU()
    {
      HashMap localHashMap = new HashMap();
      if (this.zzaHw != null)
      {
        Iterator localIterator = this.zzaHw.keySet().iterator();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          Map localMap = (Map)this.zzaHw.get(str);
          if (localMap != null) {
            localHashMap.put(str, localMap.keySet());
          }
        }
      }
      return localHashMap;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzade.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */