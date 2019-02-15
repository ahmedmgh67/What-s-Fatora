package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class zzbls
  implements zzbll.zza, zzblr
{
  private static long zzbZj;
  private final ScheduledExecutorService zzbYl;
  private final zzbop zzbYx;
  private final zzblr.zza zzbZC;
  private String zzbZD;
  private HashSet<String> zzbZE = new HashSet();
  private boolean zzbZF = true;
  private long zzbZG;
  private zzbll zzbZH;
  private zzb zzbZI = zzb.zzcai;
  private long zzbZJ = 0L;
  private long zzbZK = 0L;
  private Map<Long, zza> zzbZL;
  private List<zzd> zzbZM;
  private Map<Long, zzf> zzbZN;
  private Map<zzc, zze> zzbZO;
  private String zzbZP;
  private boolean zzbZQ;
  private final zzbln zzbZR;
  private final zzblw zzbZS;
  private String zzbZT;
  private long zzbZU = 0L;
  private int zzbZV = 0;
  private ScheduledFuture<?> zzbZW = null;
  private long zzbZX;
  private boolean zzbZY;
  private final zzblp zzbZk;
  private final zzblm zzbZv;
  
  static
  {
    if (!zzbls.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      zzbZj = 0L;
      return;
    }
  }
  
  public zzbls(zzbln paramzzbln, zzblp paramzzblp, zzblr.zza paramzza)
  {
    this.zzbZC = paramzza;
    this.zzbZR = paramzzbln;
    this.zzbYl = paramzzbln.zzVJ();
    this.zzbZv = paramzzbln.zzVI();
    this.zzbZk = paramzzblp;
    this.zzbZO = new HashMap();
    this.zzbZL = new HashMap();
    this.zzbZN = new HashMap();
    this.zzbZM = new ArrayList();
    this.zzbZS = new zzblw.zza(this.zzbYl, paramzzbln.zzVH(), "ConnectionRetryHelper").zzaI(1000L).zzj(1.3D).zzaJ(30000L).zzk(0.7D).zzWy();
    long l = zzbZj;
    zzbZj = 1L + l;
    this.zzbYx = new zzbop(paramzzbln.zzVH(), "PersistentConnection", 23 + "pc_" + l);
    this.zzbZT = null;
    zzWc();
  }
  
  private boolean isIdle()
  {
    return (this.zzbZO.isEmpty()) && (this.zzbZL.isEmpty()) && (!this.zzbZY) && (this.zzbZN.isEmpty());
  }
  
  private Collection<zze> zzU(List<String> paramList)
  {
    if (this.zzbYx.zzYT())
    {
      localObject1 = this.zzbYx;
      localObject2 = String.valueOf(paramList);
      ((zzbop)localObject1).zzi(String.valueOf(localObject2).length() + 29 + "removing all listens at path " + (String)localObject2, new Object[0]);
    }
    Object localObject1 = new ArrayList();
    Object localObject2 = this.zzbZO.entrySet().iterator();
    while (((Iterator)localObject2).hasNext())
    {
      Object localObject3 = (Map.Entry)((Iterator)localObject2).next();
      zzc localzzc = (zzc)((Map.Entry)localObject3).getKey();
      localObject3 = (zze)((Map.Entry)localObject3).getValue();
      if (zzc.zzb(localzzc).equals(paramList)) {
        ((List)localObject1).add(localObject3);
      }
    }
    paramList = ((List)localObject1).iterator();
    while (paramList.hasNext())
    {
      localObject2 = (zze)paramList.next();
      this.zzbZO.remove(((zze)localObject2).zzWg());
    }
    zzWc();
    return (Collection<zze>)localObject1;
  }
  
  private void zzV(List<String> paramList)
  {
    paramList = zzU(paramList).iterator();
    while (paramList.hasNext()) {
      zze.zzd((zze)paramList.next()).zzan("permission_denied", null);
    }
  }
  
  private boolean zzVQ()
  {
    return (this.zzbZI == zzb.zzcal) || (this.zzbZI == zzb.zzcam);
  }
  
  private boolean zzVR()
  {
    return this.zzbZI == zzb.zzcam;
  }
  
  private void zzVT()
  {
    if (zzVS()) {
      if (this.zzbZI != zzb.zzcai) {
        break label78;
      }
    }
    label78:
    for (final boolean bool = true;; bool = false)
    {
      zzblo.zzc(bool, "Not in disconnected state: %s", new Object[] { this.zzbZI });
      bool = this.zzbZQ;
      this.zzbYx.zzi("Scheduling connection attempt", new Object[0]);
      this.zzbZQ = false;
      this.zzbZS.zzr(new Runnable()
      {
        public void run()
        {
          zzbls.zza(zzbls.this).zzi("Trying to fetch auth token", new Object[0]);
          if (zzbls.zzb(zzbls.this) == zzbls.zzb.zzcai) {}
          for (boolean bool = true;; bool = false)
          {
            zzblo.zzc(bool, "Not in disconnected state: %s", new Object[] { zzbls.zzb(zzbls.this) });
            zzbls.zza(zzbls.this, zzbls.zzb.zzcaj);
            zzbls.zzc(zzbls.this);
            final long l = zzbls.zzd(zzbls.this);
            zzbls.zzf(zzbls.this).zza(bool, new zzblm.zza()
            {
              public void onError(String paramAnonymous2String)
              {
                if (l == zzbls.zzd(zzbls.this))
                {
                  zzbls.zza(zzbls.this, zzbls.zzb.zzcai);
                  zzbop localzzbop = zzbls.zza(zzbls.this);
                  paramAnonymous2String = String.valueOf(paramAnonymous2String);
                  if (paramAnonymous2String.length() != 0) {}
                  for (paramAnonymous2String = "Error fetching token: ".concat(paramAnonymous2String);; paramAnonymous2String = new String("Error fetching token: "))
                  {
                    localzzbop.zzi(paramAnonymous2String, new Object[0]);
                    zzbls.zze(zzbls.this);
                    return;
                  }
                }
                zzbls.zza(zzbls.this).zzi("Ignoring getToken error, because this was not the latest attempt.", new Object[0]);
              }
              
              public void zziM(String paramAnonymous2String)
              {
                if (l == zzbls.zzd(zzbls.this))
                {
                  if (zzbls.zzb(zzbls.this) == zzbls.zzb.zzcaj)
                  {
                    zzbls.zza(zzbls.this).zzi("Successfully fetched token, opening connection", new Object[0]);
                    zzbls.this.zziP(paramAnonymous2String);
                    return;
                  }
                  if (zzbls.zzb(zzbls.this) == zzbls.zzb.zzcai) {}
                  for (boolean bool = true;; bool = false)
                  {
                    zzblo.zzc(bool, "Expected connection state disconnected, but was %s", new Object[] { zzbls.zzb(zzbls.this) });
                    zzbls.zza(zzbls.this).zzi("Not opening connection after token refresh, because connection was set to disconnected", new Object[0]);
                    return;
                  }
                }
                zzbls.zza(zzbls.this).zzi("Ignoring getToken result, because this was not the latest attempt.", new Object[0]);
              }
            });
            return;
          }
        }
      });
      return;
    }
  }
  
  private void zzVU()
  {
    Iterator localIterator = this.zzbZN.entrySet().iterator();
    while (localIterator.hasNext())
    {
      zzf localzzf = (zzf)((Map.Entry)localIterator.next()).getValue();
      if ((localzzf.zzWj().containsKey("h")) && (localzzf.zzWl()))
      {
        localzzf.zzWf().zzan("disconnected", null);
        localIterator.remove();
      }
    }
  }
  
  private void zzVV()
  {
    zzaY(false);
  }
  
  private void zzVW()
  {
    zzaY(true);
  }
  
  private void zzVX()
  {
    zzblo.zzc(zzVQ(), "Must be connected to send unauth.", new Object[0]);
    if (this.zzbZP == null) {}
    for (boolean bool = true;; bool = false)
    {
      zzblo.zzc(bool, "Auth token must not be set.", new Object[0]);
      zza("unauth", Collections.emptyMap(), null);
      return;
    }
  }
  
  private void zzVY()
  {
    if (this.zzbYx.zzYT()) {
      this.zzbYx.zzi("calling restore state", new Object[0]);
    }
    if (this.zzbZI == zzb.zzcak) {}
    for (boolean bool = true;; bool = false)
    {
      zzblo.zzc(bool, "Wanted to restore auth, but was in wrong state: %s", new Object[] { this.zzbZI });
      if (this.zzbZP != null) {
        break;
      }
      if (this.zzbYx.zzYT()) {
        this.zzbYx.zzi("Not restoring auth because token is null.", new Object[0]);
      }
      this.zzbZI = zzb.zzcam;
      zzVZ();
      return;
    }
    if (this.zzbYx.zzYT()) {
      this.zzbYx.zzi("Restoring auth.", new Object[0]);
    }
    this.zzbZI = zzb.zzcal;
    zzVW();
  }
  
  private void zzVZ()
  {
    if (this.zzbZI == zzb.zzcam) {}
    Object localObject2;
    for (boolean bool = true;; bool = false)
    {
      zzblo.zzc(bool, "Should be connected if we're restoring state, but we are: %s", new Object[] { this.zzbZI });
      if (this.zzbYx.zzYT()) {
        this.zzbYx.zzi("Restoring outstanding listens", new Object[0]);
      }
      localObject1 = this.zzbZO.values().iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (zze)((Iterator)localObject1).next();
        if (this.zzbYx.zzYT())
        {
          zzbop localzzbop = this.zzbYx;
          String str = String.valueOf(((zze)localObject2).zzWg());
          localzzbop.zzi(String.valueOf(str).length() + 17 + "Restoring listen " + str, new Object[0]);
        }
        zzb((zze)localObject2);
      }
    }
    if (this.zzbYx.zzYT()) {
      this.zzbYx.zzi("Restoring writes.", new Object[0]);
    }
    Object localObject1 = new ArrayList(this.zzbZN.keySet());
    Collections.sort((List)localObject1);
    localObject1 = ((ArrayList)localObject1).iterator();
    while (((Iterator)localObject1).hasNext()) {
      zzaF(((Long)((Iterator)localObject1).next()).longValue());
    }
    localObject1 = this.zzbZM.iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (zzd)((Iterator)localObject1).next();
      zza(((zzd)localObject2).getAction(), ((zzd)localObject2).zzWe(), ((zzd)localObject2).getData(), ((zzd)localObject2).zzWf());
    }
    this.zzbZM.clear();
  }
  
  private void zzWa()
  {
    HashMap localHashMap = new HashMap();
    if (zzbpv.zzZW())
    {
      if (this.zzbZR.zzVK()) {
        localHashMap.put("persistence.android.enabled", Integer.valueOf(1));
      }
      str = String.valueOf(this.zzbZR.zzVL().replace('.', '-'));
      if (str.length() != 0) {}
      for (str = "sdk.android.".concat(str);; str = new String("sdk.android."))
      {
        localHashMap.put(str, Integer.valueOf(1));
        if (this.zzbYx.zzYT()) {
          this.zzbYx.zzi("Sending first connection stats", new Object[0]);
        }
        zzay(localHashMap);
        return;
      }
    }
    assert (!this.zzbZR.zzVK()) : "Stats for persistence on JVM missing (persistence not yet supported)";
    String str = String.valueOf(this.zzbZR.zzVL().replace('.', '-'));
    if (str.length() != 0) {}
    for (str = "sdk.java.".concat(str);; str = new String("sdk.java."))
    {
      localHashMap.put(str, Integer.valueOf(1));
      break;
    }
  }
  
  private long zzWb()
  {
    long l = this.zzbZK;
    this.zzbZK = (1L + l);
    return l;
  }
  
  private void zzWc()
  {
    boolean bool = false;
    if (isIdle())
    {
      if (this.zzbZW != null) {
        this.zzbZW.cancel(false);
      }
      this.zzbZW = this.zzbYl.schedule(new Runnable()
      {
        public void run()
        {
          zzbls.zza(zzbls.this, null);
          if (zzbls.zzp(zzbls.this))
          {
            zzbls.this.interrupt("connection_idle");
            return;
          }
          zzbls.zzn(zzbls.this);
        }
      }, 60000L, TimeUnit.MILLISECONDS);
    }
    while (!isInterrupted("connection_idle")) {
      return;
    }
    if (!isIdle()) {
      bool = true;
    }
    zzblo.zzaW(bool);
    resume("connection_idle");
  }
  
  private boolean zzWd()
  {
    long l = System.currentTimeMillis();
    return (isIdle()) && (l > this.zzbZX + 60000L);
  }
  
  private zze zza(zzc paramzzc)
  {
    if (this.zzbYx.zzYT())
    {
      localObject = this.zzbYx;
      String str = String.valueOf(paramzzc);
      ((zzbop)localObject).zzi(String.valueOf(str).length() + 15 + "removing query " + str, new Object[0]);
    }
    if (!this.zzbZO.containsKey(paramzzc))
    {
      if (this.zzbYx.zzYT())
      {
        localObject = this.zzbYx;
        paramzzc = String.valueOf(paramzzc);
        ((zzbop)localObject).zzi(String.valueOf(paramzzc).length() + 64 + "Trying to remove listener for QuerySpec " + paramzzc + " but no listener exists.", new Object[0]);
      }
      return null;
    }
    Object localObject = (zze)this.zzbZO.get(paramzzc);
    this.zzbZO.remove(paramzzc);
    zzWc();
    return (zze)localObject;
  }
  
  private Map<String, Object> zza(List<String> paramList, Object paramObject, String paramString)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("p", zzblo.zzT(paramList));
    localHashMap.put("d", paramObject);
    if (paramString != null) {
      localHashMap.put("h", paramString);
    }
    return localHashMap;
  }
  
  private void zza(zze paramzze)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("p", zzblo.zzT(zzc.zzb(zze.zzc(paramzze))));
    Long localLong = paramzze.zzWh();
    if (localLong != null)
    {
      localHashMap.put("q", zzc.zzc(paramzze.zzWg()));
      localHashMap.put("t", localLong);
    }
    zza("n", localHashMap, null);
  }
  
  private void zza(String paramString, List<String> paramList, Object paramObject, final zzblu paramzzblu)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("p", zzblo.zzT(paramList));
    localHashMap.put("d", paramObject);
    zza(paramString, localHashMap, new zza()
    {
      public void zzaz(Map<String, Object> paramAnonymousMap)
      {
        String str1 = null;
        String str2 = (String)paramAnonymousMap.get("s");
        if (!str2.equals("ok")) {
          str1 = (String)paramAnonymousMap.get("d");
        }
        for (paramAnonymousMap = str2;; paramAnonymousMap = null)
        {
          if (paramzzblu != null) {
            paramzzblu.zzan(paramAnonymousMap, str1);
          }
          return;
        }
      }
    });
  }
  
  private void zza(String paramString1, List<String> paramList, Object paramObject, String paramString2, zzblu paramzzblu)
  {
    paramList = zza(paramList, paramObject, paramString2);
    long l = this.zzbZJ;
    this.zzbZJ = (1L + l);
    this.zzbZN.put(Long.valueOf(l), new zzf(paramString1, paramList, paramzzblu, null));
    if (zzVR()) {
      zzaF(l);
    }
    this.zzbZX = System.currentTimeMillis();
    zzWc();
  }
  
  private void zza(String paramString, Map<String, Object> paramMap, zza paramzza)
  {
    zza(paramString, false, paramMap, paramzza);
  }
  
  private void zza(String paramString, boolean paramBoolean, Map<String, Object> paramMap, zza paramzza)
  {
    long l = zzWb();
    HashMap localHashMap = new HashMap();
    localHashMap.put("r", Long.valueOf(l));
    localHashMap.put("a", paramString);
    localHashMap.put("b", paramMap);
    this.zzbZH.zza(localHashMap, paramBoolean);
    this.zzbZL.put(Long.valueOf(l), paramzza);
  }
  
  private void zza(List<String> paramList, zzc paramzzc)
  {
    if (paramList.contains("no_index"))
    {
      paramList = String.valueOf(zzc.zzc(paramzzc).get("i"));
      paramList = String.valueOf(paramList).length() + 14 + "\".indexOn\": \"" + paramList + "\"";
      zzbop localzzbop = this.zzbYx;
      paramzzc = String.valueOf(zzblo.zzT(zzc.zzb(paramzzc)));
      localzzbop.warn(String.valueOf(paramList).length() + 118 + String.valueOf(paramzzc).length() + "Using an unspecified index. Consider adding '" + paramList + "' at " + paramzzc + " to your security and Firebase Database rules for better performance");
    }
  }
  
  private void zzaE(long paramLong)
  {
    if (this.zzbYx.zzYT()) {
      this.zzbYx.zzi("handling timestamp", new Object[0]);
    }
    long l = System.currentTimeMillis();
    HashMap localHashMap = new HashMap();
    localHashMap.put("serverTimeOffset", Long.valueOf(paramLong - l));
    this.zzbZC.zzaw(localHashMap);
  }
  
  private void zzaF(final long paramLong)
  {
    assert (zzVR()) : "sendPut called when we can't send writes (we're disconnected or writes are paused).";
    zzf localzzf = (zzf)this.zzbZN.get(Long.valueOf(paramLong));
    final zzblu localzzblu = localzzf.zzWf();
    final String str = localzzf.getAction();
    localzzf.zzWk();
    zza(str, localzzf.zzWj(), new zza()
    {
      public void zzaz(Map<String, Object> paramAnonymousMap)
      {
        Object localObject;
        if (zzbls.zza(zzbls.this).zzYT())
        {
          localObject = zzbls.zza(zzbls.this);
          String str1 = str;
          String str2 = String.valueOf(paramAnonymousMap);
          ((zzbop)localObject).zzi(String.valueOf(str1).length() + 11 + String.valueOf(str2).length() + str1 + " response: " + str2, new Object[0]);
        }
        if ((zzbls.zzf)zzbls.zzm(zzbls.this).get(Long.valueOf(paramLong)) == localzzblu)
        {
          zzbls.zzm(zzbls.this).remove(Long.valueOf(paramLong));
          if (this.zzcad != null)
          {
            localObject = (String)paramAnonymousMap.get("s");
            if (!((String)localObject).equals("ok")) {
              break label186;
            }
            this.zzcad.zzan(null, null);
          }
        }
        for (;;)
        {
          zzbls.zzn(zzbls.this);
          return;
          label186:
          paramAnonymousMap = (String)paramAnonymousMap.get("d");
          this.zzcad.zzan((String)localObject, paramAnonymousMap);
          continue;
          if (zzbls.zza(zzbls.this).zzYT())
          {
            paramAnonymousMap = zzbls.zza(zzbls.this);
            long l = paramLong;
            paramAnonymousMap.zzi(81 + "Ignoring on complete for put " + l + " because it was removed already.", new Object[0]);
          }
        }
      }
    });
  }
  
  private void zzaY(final boolean paramBoolean)
  {
    zzblo.zzc(zzVQ(), "Must be connected to send auth, but was: %s", new Object[] { this.zzbZI });
    if (this.zzbZP != null) {}
    zza local3;
    HashMap localHashMap;
    for (boolean bool = true;; bool = false)
    {
      zzblo.zzc(bool, "Auth token must be set to authenticate!", new Object[0]);
      local3 = new zza()
      {
        public void zzaz(Map<String, Object> paramAnonymousMap)
        {
          zzbls.zza(zzbls.this, zzbls.zzb.zzcam);
          String str = (String)paramAnonymousMap.get("s");
          if (str.equals("ok"))
          {
            zzbls.zza(zzbls.this, 0);
            zzbls.zzg(zzbls.this).zzaX(true);
            if (paramBoolean) {
              zzbls.zzh(zzbls.this);
            }
          }
          do
          {
            do
            {
              return;
              zzbls.zza(zzbls.this, null);
              zzbls.zza(zzbls.this, true);
              zzbls.zzg(zzbls.this).zzaX(false);
              paramAnonymousMap = (String)paramAnonymousMap.get("d");
              zzbls.zza(zzbls.this).zzi(String.valueOf(str).length() + 26 + String.valueOf(paramAnonymousMap).length() + "Authentication failed: " + str + " (" + paramAnonymousMap + ")", new Object[0]);
              zzbls.zzi(zzbls.this).close();
            } while (!str.equals("invalid_token"));
            zzbls.zzj(zzbls.this);
          } while (zzbls.zzk(zzbls.this) < 3L);
          zzbls.zzl(zzbls.this).zzWx();
          zzbls.zza(zzbls.this).warn("Provided authentication credentials are invalid. This usually indicates your FirebaseApp instance was not initialized correctly. Make sure your google-services.json file has the correct firebase_url and api_key. You can re-download google-services.json from https://console.firebase.google.com/.");
        }
      };
      localHashMap = new HashMap();
      zzbpw localzzbpw = zzbpw.zzje(this.zzbZP);
      if (localzzbpw == null) {
        break;
      }
      localHashMap.put("cred", localzzbpw.getToken());
      if (localzzbpw.zzZX() != null) {
        localHashMap.put("authvar", localzzbpw.zzZX());
      }
      zza("gauth", true, localHashMap, local3);
      return;
    }
    localHashMap.put("cred", this.zzbZP);
    zza("auth", true, localHashMap, local3);
  }
  
  private void zzam(String paramString1, String paramString2)
  {
    this.zzbYx.warn(String.valueOf(paramString1).length() + 23 + String.valueOf(paramString2).length() + "Auth token revoked: " + paramString1 + " (" + paramString2 + ")");
    this.zzbZP = null;
    this.zzbZQ = true;
    this.zzbZC.zzaX(false);
    this.zzbZH.close();
  }
  
  private void zzax(Map<String, Object> paramMap)
  {
    this.zzbYx.info((String)paramMap.get("msg"));
  }
  
  private void zzay(Map<String, Integer> paramMap)
  {
    if (!paramMap.isEmpty())
    {
      localHashMap = new HashMap();
      localHashMap.put("c", paramMap);
      zza("s", localHashMap, new zza()
      {
        public void zzaz(Map<String, Object> paramAnonymousMap)
        {
          String str = (String)paramAnonymousMap.get("s");
          if (!str.equals("ok"))
          {
            paramAnonymousMap = (String)paramAnonymousMap.get("d");
            if (zzbls.zza(zzbls.this).zzYT()) {
              zzbls.zza(zzbls.this).zzi(String.valueOf(str).length() + 34 + String.valueOf(paramAnonymousMap).length() + "Failed to send stats: " + str + " (message: " + paramAnonymousMap + ")", new Object[0]);
            }
          }
        }
      });
    }
    while (!this.zzbYx.zzYT())
    {
      HashMap localHashMap;
      return;
    }
    this.zzbYx.zzi("Not sending stats because stats are empty", new Object[0]);
  }
  
  private void zzb(final zze paramzze)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("p", zzblo.zzT(zzc.zzb(paramzze.zzWg())));
    Object localObject1 = paramzze.zzWh();
    if (localObject1 != null)
    {
      localHashMap.put("q", zzc.zzc(zze.zzc(paramzze)));
      localHashMap.put("t", localObject1);
    }
    localObject1 = paramzze.zzWi();
    localHashMap.put("h", ((zzblq)localObject1).zzVM());
    if (((zzblq)localObject1).zzVN())
    {
      localObject1 = ((zzblq)localObject1).zzVO();
      ArrayList localArrayList = new ArrayList();
      Object localObject2 = ((zzblk)localObject1).zzVF().iterator();
      while (((Iterator)localObject2).hasNext()) {
        localArrayList.add(zzblo.zzT((List)((Iterator)localObject2).next()));
      }
      localObject2 = new HashMap();
      ((Map)localObject2).put("hs", ((zzblk)localObject1).zzVG());
      ((Map)localObject2).put("ps", localArrayList);
      localHashMap.put("ch", localObject2);
    }
    zza("q", localHashMap, new zza()
    {
      public void zzaz(Map<String, Object> paramAnonymousMap)
      {
        String str = (String)paramAnonymousMap.get("s");
        if (str.equals("ok"))
        {
          Object localObject = (Map)paramAnonymousMap.get("d");
          if (((Map)localObject).containsKey("w"))
          {
            localObject = (List)((Map)localObject).get("w");
            zzbls.zza(zzbls.this, (List)localObject, zzbls.zze.zzc(paramzze));
          }
        }
        if ((zzbls.zze)zzbls.zzo(zzbls.this).get(paramzze.zzWg()) == paramzze)
        {
          if (!str.equals("ok"))
          {
            zzbls.zza(zzbls.this, paramzze.zzWg());
            paramAnonymousMap = (String)paramAnonymousMap.get("d");
            zzbls.zze.zzd(paramzze).zzan(str, paramAnonymousMap);
          }
        }
        else {
          return;
        }
        zzbls.zze.zzd(paramzze).zzan(null, null);
      }
    });
  }
  
  private void zzk(String paramString, Map<String, Object> paramMap)
  {
    Object localObject1;
    Object localObject2;
    if (this.zzbYx.zzYT())
    {
      localObject1 = this.zzbYx;
      localObject2 = String.valueOf(paramMap);
      ((zzbop)localObject1).zzi(String.valueOf(paramString).length() + 22 + String.valueOf(localObject2).length() + "handleServerMessage: " + paramString + " " + (String)localObject2, new Object[0]);
    }
    boolean bool;
    if ((paramString.equals("d")) || (paramString.equals("m")))
    {
      bool = paramString.equals("m");
      paramString = (String)paramMap.get("p");
      localObject1 = paramMap.get("d");
      paramMap = zzblo.zzao(paramMap.get("t"));
      if ((bool) && ((localObject1 instanceof Map)) && (((Map)localObject1).size() == 0)) {
        if (this.zzbYx.zzYT())
        {
          paramMap = this.zzbYx;
          paramString = String.valueOf(paramString);
          if (paramString.length() == 0) {
            break label216;
          }
          paramString = "ignoring empty merge for path ".concat(paramString);
          paramMap.zzi(paramString, new Object[0]);
        }
      }
    }
    label216:
    label386:
    label430:
    label505:
    label521:
    do
    {
      Long localLong;
      ArrayList localArrayList;
      do
      {
        return;
        paramString = new String("ignoring empty merge for path ");
        break;
        paramString = zzblo.zziN(paramString);
        this.zzbZC.zza(paramString, localObject1, bool, paramMap);
        return;
        if (!paramString.equals("rm")) {
          break label521;
        }
        localObject1 = (String)paramMap.get("p");
        localObject2 = zzblo.zziN((String)localObject1);
        paramString = paramMap.get("d");
        localLong = zzblo.zzao(paramMap.get("t"));
        paramString = (List)paramString;
        localArrayList = new ArrayList();
        Iterator localIterator = paramString.iterator();
        if (localIterator.hasNext())
        {
          Map localMap = (Map)localIterator.next();
          paramString = (String)localMap.get("s");
          paramMap = (String)localMap.get("e");
          if (paramString != null)
          {
            paramString = zzblo.zziN(paramString);
            if (paramMap == null) {
              break label430;
            }
          }
          for (paramMap = zzblo.zziN(paramMap);; paramMap = null)
          {
            localArrayList.add(new zzblt(paramString, paramMap, localMap.get("m")));
            break;
            paramString = null;
            break label386;
          }
        }
        if (!localArrayList.isEmpty()) {
          break label505;
        }
      } while (!this.zzbYx.zzYT());
      paramMap = this.zzbYx;
      paramString = String.valueOf(localObject1);
      if (paramString.length() != 0) {}
      for (paramString = "Ignoring empty range merge for path ".concat(paramString);; paramString = new String("Ignoring empty range merge for path "))
      {
        paramMap.zzi(paramString, new Object[0]);
        return;
      }
      this.zzbZC.zza((List)localObject2, localArrayList, localLong);
      return;
      if (paramString.equals("c"))
      {
        zzV(zzblo.zziN((String)paramMap.get("p")));
        return;
      }
      if (paramString.equals("ac"))
      {
        zzam((String)paramMap.get("s"), (String)paramMap.get("d"));
        return;
      }
      if (paramString.equals("sd"))
      {
        zzax(paramMap);
        return;
      }
    } while (!this.zzbYx.zzYT());
    paramMap = this.zzbYx;
    paramString = String.valueOf(paramString);
    if (paramString.length() != 0) {}
    for (paramString = "Unrecognized action from server: ".concat(paramString);; paramString = new String("Unrecognized action from server: "))
    {
      paramMap.zzi(paramString, new Object[0]);
      return;
    }
  }
  
  public void initialize()
  {
    zzVT();
  }
  
  public void interrupt(String paramString)
  {
    String str;
    if (this.zzbYx.zzYT())
    {
      zzbop localzzbop = this.zzbYx;
      str = String.valueOf(paramString);
      if (str.length() != 0)
      {
        str = "Connection interrupted for: ".concat(str);
        localzzbop.zzi(str, new Object[0]);
      }
    }
    else
    {
      this.zzbZE.add(paramString);
      if (this.zzbZH == null) {
        break label94;
      }
      this.zzbZH.close();
      this.zzbZH = null;
    }
    for (;;)
    {
      this.zzbZS.zzUk();
      return;
      str = new String("Connection interrupted for: ");
      break;
      label94:
      this.zzbZS.cancel();
      this.zzbZI = zzb.zzcai;
    }
  }
  
  public boolean isInterrupted(String paramString)
  {
    return this.zzbZE.contains(paramString);
  }
  
  public void purgeOutstandingWrites()
  {
    Iterator localIterator = this.zzbZN.values().iterator();
    Object localObject;
    while (localIterator.hasNext())
    {
      localObject = (zzf)localIterator.next();
      if (zzf.zza((zzf)localObject) != null) {
        zzf.zza((zzf)localObject).zzan("write_canceled", null);
      }
    }
    localIterator = this.zzbZM.iterator();
    while (localIterator.hasNext())
    {
      localObject = (zzd)localIterator.next();
      if (zzd.zza((zzd)localObject) != null) {
        zzd.zza((zzd)localObject).zzan("write_canceled", null);
      }
    }
    this.zzbZN.clear();
    this.zzbZM.clear();
    if (!zzVQ()) {
      this.zzbZY = false;
    }
    zzWc();
  }
  
  public void refreshAuthToken()
  {
    this.zzbYx.zzi("Auth token refresh requested", new Object[0]);
    interrupt("token_refresh");
    resume("token_refresh");
  }
  
  public void resume(String paramString)
  {
    zzbop localzzbop;
    if (this.zzbYx.zzYT())
    {
      localzzbop = this.zzbYx;
      str = String.valueOf(paramString);
      if (str.length() == 0) {
        break label75;
      }
    }
    label75:
    for (String str = "Connection no longer interrupted for: ".concat(str);; str = new String("Connection no longer interrupted for: "))
    {
      localzzbop.zzi(str, new Object[0]);
      this.zzbZE.remove(paramString);
      if ((zzVS()) && (this.zzbZI == zzb.zzcai)) {
        zzVT();
      }
      return;
    }
  }
  
  public void shutdown()
  {
    interrupt("shutdown");
  }
  
  boolean zzVS()
  {
    return this.zzbZE.size() == 0;
  }
  
  public void zza(List<String> paramList, zzblu paramzzblu)
  {
    if (zzVR()) {
      zza("oc", paramList, null, paramzzblu);
    }
    for (;;)
    {
      zzWc();
      return;
      this.zzbZM.add(new zzd("oc", paramList, null, paramzzblu, null));
    }
  }
  
  public void zza(List<String> paramList, Object paramObject, zzblu paramzzblu)
  {
    zza("p", paramList, paramObject, null, paramzzblu);
  }
  
  public void zza(List<String> paramList, Object paramObject, String paramString, zzblu paramzzblu)
  {
    zza("p", paramList, paramObject, paramString, paramzzblu);
  }
  
  public void zza(List<String> paramList, Map<String, Object> paramMap)
  {
    paramList = new zzc(paramList, paramMap);
    if (this.zzbYx.zzYT())
    {
      paramMap = this.zzbYx;
      String str = String.valueOf(paramList);
      paramMap.zzi(String.valueOf(str).length() + 15 + "unlistening on " + str, new Object[0]);
    }
    paramList = zza(paramList);
    if ((paramList != null) && (zzVQ())) {
      zza(paramList);
    }
    zzWc();
  }
  
  public void zza(List<String> paramList, Map<String, Object> paramMap, zzblq paramzzblq, Long paramLong, zzblu paramzzblu)
  {
    paramList = new zzc(paramList, paramMap);
    String str;
    if (this.zzbYx.zzYT())
    {
      paramMap = this.zzbYx;
      str = String.valueOf(paramList);
      paramMap.zzi(String.valueOf(str).length() + 13 + "Listening on " + str, new Object[0]);
    }
    if (!this.zzbZO.containsKey(paramList)) {}
    for (boolean bool = true;; bool = false)
    {
      zzblo.zzc(bool, "listen() called twice for same QuerySpec.", new Object[0]);
      if (this.zzbYx.zzYT())
      {
        paramMap = this.zzbYx;
        str = String.valueOf(paramList);
        paramMap.zzi(String.valueOf(str).length() + 21 + "Adding listen query: " + str, new Object[0]);
      }
      paramMap = new zze(paramzzblu, paramList, paramLong, paramzzblq, null);
      this.zzbZO.put(paramList, paramMap);
      if (zzVQ()) {
        zzb(paramMap);
      }
      zzWc();
      return;
    }
  }
  
  public void zza(List<String> paramList, Map<String, Object> paramMap, zzblu paramzzblu)
  {
    zza("m", paramList, paramMap, null, paramzzblu);
  }
  
  public void zzat(Map<String, Object> paramMap)
  {
    if (paramMap.containsKey("r"))
    {
      long l = ((Integer)paramMap.get("r")).intValue();
      localObject = (zza)this.zzbZL.remove(Long.valueOf(l));
      if (localObject != null) {
        ((zza)localObject).zzaz((Map)paramMap.get("b"));
      }
    }
    do
    {
      do
      {
        return;
      } while (paramMap.containsKey("error"));
      if (paramMap.containsKey("a"))
      {
        zzk((String)paramMap.get("a"), (Map)paramMap.get("b"));
        return;
      }
    } while (!this.zzbYx.zzYT());
    Object localObject = this.zzbYx;
    paramMap = String.valueOf(paramMap);
    ((zzbop)localObject).zzi(String.valueOf(paramMap).length() + 26 + "Ignoring unknown message: " + paramMap, new Object[0]);
  }
  
  public void zzb(zzbll.zzb paramzzb)
  {
    String str;
    int i;
    if (this.zzbYx.zzYT())
    {
      zzbop localzzbop = this.zzbYx;
      str = String.valueOf(paramzzb.name());
      if (str.length() != 0)
      {
        str = "Got on disconnect due to ".concat(str);
        localzzbop.zzi(str, new Object[0]);
      }
    }
    else
    {
      this.zzbZI = zzb.zzcai;
      this.zzbZH = null;
      this.zzbZY = false;
      this.zzbZL.clear();
      zzVU();
      if (zzVS())
      {
        long l1 = System.currentTimeMillis();
        long l2 = this.zzbZG;
        if (this.zzbZG <= 0L) {
          break label180;
        }
        if (l1 - l2 <= 30000L) {
          break label175;
        }
        i = 1;
      }
    }
    for (;;)
    {
      if ((paramzzb == zzbll.zzb.zzbZo) || (i != 0)) {
        this.zzbZS.zzUk();
      }
      zzVT();
      this.zzbZG = 0L;
      this.zzbZC.onDisconnect();
      return;
      str = new String("Got on disconnect due to ");
      break;
      label175:
      i = 0;
      continue;
      label180:
      i = 0;
    }
  }
  
  public void zzb(List<String> paramList, Object paramObject, zzblu paramzzblu)
  {
    this.zzbZY = true;
    if (zzVR()) {
      zza("o", paramList, paramObject, paramzzblu);
    }
    for (;;)
    {
      zzWc();
      return;
      this.zzbZM.add(new zzd("o", paramList, paramObject, paramzzblu, null));
    }
  }
  
  public void zzb(List<String> paramList, Map<String, Object> paramMap, zzblu paramzzblu)
  {
    this.zzbZY = true;
    if (zzVR()) {
      zza("om", paramList, paramMap, paramzzblu);
    }
    for (;;)
    {
      zzWc();
      return;
      this.zzbZM.add(new zzd("om", paramList, paramMap, paramzzblu, null));
    }
  }
  
  public void zziK(String paramString)
  {
    this.zzbZD = paramString;
  }
  
  public void zziL(String paramString)
  {
    zzbop localzzbop;
    if (this.zzbYx.zzYT())
    {
      localzzbop = this.zzbYx;
      paramString = String.valueOf(paramString);
      if (paramString.length() == 0) {
        break label52;
      }
    }
    label52:
    for (paramString = "Firebase Database connection was forcefully killed by the server. Will not attempt reconnect. Reason: ".concat(paramString);; paramString = new String("Firebase Database connection was forcefully killed by the server. Will not attempt reconnect. Reason: "))
    {
      localzzbop.zzi(paramString, new Object[0]);
      interrupt("server_kill");
      return;
    }
  }
  
  public void zziO(String paramString)
  {
    this.zzbYx.zzi("Auth token refreshed.", new Object[0]);
    this.zzbZP = paramString;
    if (zzVQ())
    {
      if (paramString != null) {
        zzVV();
      }
    }
    else {
      return;
    }
    zzVX();
  }
  
  public void zziP(String paramString)
  {
    if (this.zzbZI == zzb.zzcaj) {}
    for (boolean bool = true;; bool = false)
    {
      zzblo.zzc(bool, "Trying to open network connection while in the wrong state: %s", new Object[] { this.zzbZI });
      if (paramString == null) {
        this.zzbZC.zzaX(false);
      }
      this.zzbZP = paramString;
      this.zzbZI = zzb.zzcak;
      this.zzbZH = new zzbll(this.zzbZR, this.zzbZk, this.zzbZD, this, this.zzbZT);
      this.zzbZH.open();
      return;
    }
  }
  
  public void zzj(long paramLong, String paramString)
  {
    if (this.zzbYx.zzYT()) {
      this.zzbYx.zzi("onReady", new Object[0]);
    }
    this.zzbZG = System.currentTimeMillis();
    zzaE(paramLong);
    if (this.zzbZF) {
      zzWa();
    }
    zzVY();
    this.zzbZF = false;
    this.zzbZT = paramString;
    this.zzbZC.zzVP();
  }
  
  private static abstract interface zza
  {
    public abstract void zzaz(Map<String, Object> paramMap);
  }
  
  private static enum zzb
  {
    private zzb() {}
  }
  
  private static class zzc
  {
    private final List<String> zzcao;
    private final Map<String, Object> zzcap;
    
    public zzc(List<String> paramList, Map<String, Object> paramMap)
    {
      this.zzcao = paramList;
      this.zzcap = paramMap;
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1;
      if (this == paramObject) {
        bool1 = true;
      }
      do
      {
        do
        {
          return bool1;
          bool1 = bool2;
        } while (!(paramObject instanceof zzc));
        paramObject = (zzc)paramObject;
        bool1 = bool2;
      } while (!this.zzcao.equals(((zzc)paramObject).zzcao));
      return this.zzcap.equals(((zzc)paramObject).zzcap);
    }
    
    public int hashCode()
    {
      return this.zzcao.hashCode() * 31 + this.zzcap.hashCode();
    }
    
    public String toString()
    {
      String str1 = String.valueOf(zzblo.zzT(this.zzcao));
      String str2 = String.valueOf(this.zzcap);
      return String.valueOf(str1).length() + 11 + String.valueOf(str2).length() + str1 + " (params: " + str2 + ")";
    }
  }
  
  private static class zzd
  {
    private final Object data;
    private final List<String> zzcao;
    private final String zzcaq;
    private final zzblu zzcar;
    
    private zzd(String paramString, List<String> paramList, Object paramObject, zzblu paramzzblu)
    {
      this.zzcaq = paramString;
      this.zzcao = paramList;
      this.data = paramObject;
      this.zzcar = paramzzblu;
    }
    
    public String getAction()
    {
      return this.zzcaq;
    }
    
    public Object getData()
    {
      return this.data;
    }
    
    public List<String> zzWe()
    {
      return this.zzcao;
    }
    
    public zzblu zzWf()
    {
      return this.zzcar;
    }
  }
  
  private static class zze
  {
    private final zzblu zzcas;
    private final zzbls.zzc zzcat;
    private final zzblq zzcau;
    private final Long zzcav;
    
    private zze(zzblu paramzzblu, zzbls.zzc paramzzc, Long paramLong, zzblq paramzzblq)
    {
      this.zzcas = paramzzblu;
      this.zzcat = paramzzc;
      this.zzcau = paramzzblq;
      this.zzcav = paramLong;
    }
    
    public String toString()
    {
      String str1 = String.valueOf(this.zzcat.toString());
      String str2 = String.valueOf(this.zzcav);
      return String.valueOf(str1).length() + 8 + String.valueOf(str2).length() + str1 + " (Tag: " + str2 + ")";
    }
    
    public zzbls.zzc zzWg()
    {
      return this.zzcat;
    }
    
    public Long zzWh()
    {
      return this.zzcav;
    }
    
    public zzblq zzWi()
    {
      return this.zzcau;
    }
  }
  
  private static class zzf
  {
    private String zzcaq;
    private zzblu zzcar;
    private Map<String, Object> zzcaw;
    private boolean zzcax;
    
    private zzf(String paramString, Map<String, Object> paramMap, zzblu paramzzblu)
    {
      this.zzcaq = paramString;
      this.zzcaw = paramMap;
      this.zzcar = paramzzblu;
    }
    
    public String getAction()
    {
      return this.zzcaq;
    }
    
    public zzblu zzWf()
    {
      return this.zzcar;
    }
    
    public Map<String, Object> zzWj()
    {
      return this.zzcaw;
    }
    
    public void zzWk()
    {
      this.zzcax = true;
    }
    
    public boolean zzWl()
    {
      return this.zzcax;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbls.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */