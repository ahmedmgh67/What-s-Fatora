package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract interface zzacz
{
  public abstract PendingResult<zzb> zza(GoogleApiClient paramGoogleApiClient, zza paramzza);
  
  public static class zza
  {
    private final long zzaHn;
    private final Map<String, String> zzaHo;
    private final int zzaHp;
    private final List<zzadb> zzaHq;
    
    private zza(zza paramzza)
    {
      this.zzaHn = zza.zza(paramzza);
      this.zzaHo = zza.zzb(paramzza);
      this.zzaHp = zza.zzc(paramzza);
      this.zzaHq = null;
    }
    
    public long zzyQ()
    {
      return this.zzaHn;
    }
    
    public Map<String, String> zzyR()
    {
      if (this.zzaHo == null) {
        return Collections.emptyMap();
      }
      return this.zzaHo;
    }
    
    public int zzyS()
    {
      return this.zzaHp;
    }
    
    public static class zza
    {
      private long zzaHn = 43200L;
      private Map<String, String> zzaHo;
      private int zzaHp;
      
      public zza zzG(String paramString1, String paramString2)
      {
        if (this.zzaHo == null) {
          this.zzaHo = new HashMap();
        }
        this.zzaHo.put(paramString1, paramString2);
        return this;
      }
      
      public zza zzJ(long paramLong)
      {
        this.zzaHn = paramLong;
        return this;
      }
      
      public zza zzdl(int paramInt)
      {
        this.zzaHp = paramInt;
        return this;
      }
      
      public zzacz.zza zzyT()
      {
        return new zzacz.zza(this, null);
      }
    }
  }
  
  public static abstract interface zzb
    extends Result
  {
    public abstract Status getStatus();
    
    public abstract long getThrottleEndTimeMillis();
    
    public abstract byte[] zza(String paramString1, byte[] paramArrayOfByte, String paramString2);
    
    public abstract Map<String, Set<String>> zzyU();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzacz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */