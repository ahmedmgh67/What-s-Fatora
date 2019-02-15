package com.google.android.gms.internal;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseReference.CompletionListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.Transaction.Handler;
import com.google.firebase.database.Transaction.Result;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.zza;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class zzbml
  implements zzblr.zza
{
  private final zzbmm zzbXP;
  private final zzblr zzcaY;
  private final zzbqc zzccf = new zzbqc(new zzbpz(), 0L);
  private zzbmq zzccg;
  private zzbmr zzcch;
  private zzbnu<List<zza>> zzcci;
  private boolean zzccj = false;
  private final zzboc zzcck;
  private final zzbmc zzccl;
  private final zzbop zzccm;
  private final zzbop zzccn;
  private final zzbop zzcco;
  public long zzccp = 0L;
  private long zzccq = 1L;
  private zzbmt zzccr;
  private zzbmt zzccs;
  private FirebaseDatabase zzcct;
  private boolean zzccu = false;
  private long zzccv = 0L;
  
  static
  {
    if (!zzbml.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  zzbml(zzbmm paramzzbmm, zzbmc paramzzbmc, FirebaseDatabase paramFirebaseDatabase)
  {
    this.zzbXP = paramzzbmm;
    this.zzccl = paramzzbmc;
    this.zzcct = paramFirebaseDatabase;
    this.zzccm = this.zzccl.zziW("RepoOperation");
    this.zzccn = this.zzccl.zziW("Transaction");
    this.zzcco = this.zzccl.zziW("DataOperation");
    this.zzcck = new zzboc(this.zzccl);
    this.zzcaY = paramzzbmc.zza(new zzblp(paramzzbmm.zzbZA, paramzzbmm.zzaFs, paramzzbmm.zzbZB), this);
    zzs(new Runnable()
    {
      public void run()
      {
        zzbml.zza(zzbml.this);
      }
    });
  }
  
  private void zzW(List<? extends zzboa> paramList)
  {
    if (!paramList.isEmpty()) {
      this.zzcck.zzY(paramList);
    }
  }
  
  private void zzXn()
  {
    this.zzccl.zzWT().zza(new zzbly.zzb()
    {
      public void zziV(String paramAnonymousString)
      {
        zzbml.zzb(zzbml.this).zzi("Auth token changed, triggering auth token refresh", new Object[0]);
        zzbml.zzc(zzbml.this).zziO(paramAnonymousString);
      }
    });
    this.zzcaY.initialize();
    zzbnn localzzbnn = this.zzccl.zziX(this.zzbXP.zzbZA);
    this.zzccg = new zzbmq();
    this.zzcch = new zzbmr();
    this.zzcci = new zzbnu();
    this.zzccr = new zzbmt(this.zzccl, new zzbnm(), new zzbmt.zzd()
    {
      public void zza(zzboe paramAnonymouszzboe, zzbmu paramAnonymouszzbmu) {}
      
      public void zza(final zzboe paramAnonymouszzboe, zzbmu paramAnonymouszzbmu, zzblq paramAnonymouszzblq, final zzbmt.zza paramAnonymouszza)
      {
        zzbml.this.zzs(new Runnable()
        {
          public void run()
          {
            Object localObject = zzbml.zzd(zzbml.this).zzq(paramAnonymouszzboe.zzVc());
            if (!((zzbpe)localObject).isEmpty())
            {
              localObject = zzbml.zze(zzbml.this).zzi(paramAnonymouszzboe.zzVc(), (zzbpe)localObject);
              zzbml.zza(zzbml.this, (List)localObject);
              paramAnonymouszza.zzb(null);
            }
          }
        });
      }
    });
    this.zzccs = new zzbmt(this.zzccl, localzzbnn, new zzbmt.zzd()
    {
      public void zza(zzboe paramAnonymouszzboe, zzbmu paramAnonymouszzbmu)
      {
        zzbml.zzc(zzbml.this).zza(paramAnonymouszzboe.zzVc().zzXh(), paramAnonymouszzboe.zzYG().zzYC());
      }
      
      public void zza(zzboe paramAnonymouszzboe, zzbmu paramAnonymouszzbmu, zzblq paramAnonymouszzblq, final zzbmt.zza paramAnonymouszza)
      {
        zzblr localzzblr = zzbml.zzc(zzbml.this);
        List localList = paramAnonymouszzboe.zzVc().zzXh();
        Map localMap = paramAnonymouszzboe.zzYG().zzYC();
        if (paramAnonymouszzbmu != null) {}
        for (paramAnonymouszzboe = Long.valueOf(paramAnonymouszzbmu.zzXB());; paramAnonymouszzboe = null)
        {
          localzzblr.zza(localList, localMap, paramAnonymouszzblq, paramAnonymouszzboe, new zzblu()
          {
            public void zzan(String paramAnonymous2String1, String paramAnonymous2String2)
            {
              paramAnonymous2String1 = zzbml.zzap(paramAnonymous2String1, paramAnonymous2String2);
              paramAnonymous2String1 = paramAnonymouszza.zzb(paramAnonymous2String1);
              zzbml.zza(zzbml.this, paramAnonymous2String1);
            }
          });
          return;
        }
      }
    });
    zza(localzzbnn);
    zzb(zzbmb.zzcbI, Boolean.valueOf(false));
    zzb(zzbmb.zzcbJ, Boolean.valueOf(false));
  }
  
  private long zzXr()
  {
    long l = this.zzccq;
    this.zzccq = (1L + l);
    return l;
  }
  
  private void zzXs()
  {
    Object localObject = zzbmp.zza(this.zzccf);
    localObject = zzbmp.zza(this.zzcch, (Map)localObject);
    final ArrayList localArrayList = new ArrayList();
    ((zzbmr)localObject).zza(zzbmj.zzXf(), new zzbmr.zzb()
    {
      public void zzf(zzbmj paramAnonymouszzbmj, zzbpe paramAnonymouszzbpe)
      {
        localArrayList.addAll(zzbml.zzg(zzbml.this).zzi(paramAnonymouszzbmj, paramAnonymouszzbpe));
        paramAnonymouszzbmj = zzbml.zza(zzbml.this, paramAnonymouszzbmj, -9);
        zzbml.zzb(zzbml.this, paramAnonymouszzbmj);
      }
    });
    this.zzcch = new zzbmr();
    zzW(localArrayList);
  }
  
  private void zzXt()
  {
    zzbnu localzzbnu = this.zzcci;
    zzb(localzzbnu);
    zza(localzzbnu);
  }
  
  private long zzXu()
  {
    long l = this.zzccv;
    this.zzccv = (1L + l);
    return l;
  }
  
  private zzbpe zza(zzbmj paramzzbmj, List<Long> paramList)
  {
    paramList = this.zzccs.zzc(paramzzbmj, paramList);
    paramzzbmj = paramList;
    if (paramList == null) {
      paramzzbmj = zzbox.zzZp();
    }
    return paramzzbmj;
  }
  
  private void zza(long paramLong, zzbmj paramzzbmj, DatabaseError paramDatabaseError)
  {
    if ((paramDatabaseError != null) && (paramDatabaseError.getCode() == -25)) {
      return;
    }
    int i;
    if (paramDatabaseError == null)
    {
      i = 1;
      paramDatabaseError = this.zzccs;
      if (i != 0) {
        break label82;
      }
    }
    label82:
    for (boolean bool = true;; bool = false)
    {
      paramDatabaseError = paramDatabaseError.zza(paramLong, bool, true, this.zzccf);
      if (paramDatabaseError.size() > 0) {
        zzo(paramzzbmj);
      }
      zzW(paramDatabaseError);
      return;
      i = 0;
      break;
    }
  }
  
  private void zza(zzbnn paramzzbnn)
  {
    Object localObject1 = paramzzbnn.zzVe();
    paramzzbnn = zzbmp.zza(this.zzccf);
    localObject1 = ((List)localObject1).iterator();
    long l1 = Long.MIN_VALUE;
    if (((Iterator)localObject1).hasNext())
    {
      final zzbmx localzzbmx = (zzbmx)((Iterator)localObject1).next();
      Object localObject2 = new zzblu()
      {
        public void zzan(String paramAnonymousString1, String paramAnonymousString2)
        {
          paramAnonymousString1 = zzbml.zzap(paramAnonymousString1, paramAnonymousString2);
          zzbml.zza(zzbml.this, "Persisted write", localzzbmx.zzVc(), paramAnonymousString1);
          zzbml.zza(zzbml.this, localzzbmx.zzXC(), localzzbmx.zzVc(), paramAnonymousString1);
        }
      };
      if (l1 >= localzzbmx.zzXC()) {
        throw new IllegalStateException("Write ids were not in order.");
      }
      l1 = localzzbmx.zzXC();
      this.zzccq = (localzzbmx.zzXC() + 1L);
      zzbop localzzbop;
      long l2;
      if (localzzbmx.zzXF())
      {
        if (this.zzccm.zzYT())
        {
          localzzbop = this.zzccm;
          l2 = localzzbmx.zzXC();
          localzzbop.zzi(48 + "Restoring overwrite with id " + l2, new Object[0]);
        }
        this.zzcaY.zza(localzzbmx.zzVc().zzXh(), localzzbmx.zzXD().getValue(true), (zzblu)localObject2);
        localObject2 = zzbmp.zza(localzzbmx.zzXD(), paramzzbnn);
        this.zzccs.zza(localzzbmx.zzVc(), localzzbmx.zzXD(), (zzbpe)localObject2, localzzbmx.zzXC(), true, false);
      }
      for (;;)
      {
        break;
        if (this.zzccm.zzYT())
        {
          localzzbop = this.zzccm;
          l2 = localzzbmx.zzXC();
          localzzbop.zzi(44 + "Restoring merge with id " + l2, new Object[0]);
        }
        this.zzcaY.zza(localzzbmx.zzVc().zzXh(), localzzbmx.zzXE().zzaZ(true), (zzblu)localObject2);
        localObject2 = zzbmp.zza(localzzbmx.zzXE(), paramzzbnn);
        this.zzccs.zza(localzzbmx.zzVc(), localzzbmx.zzXE(), (zzbma)localObject2, localzzbmx.zzXC(), false);
      }
    }
  }
  
  private void zza(zzbnu<List<zza>> paramzzbnu)
  {
    List localList;
    if ((List)paramzzbnu.getValue() != null)
    {
      localList = zzc(paramzzbnu);
      assert (localList.size() > 0);
      localObject = localList.iterator();
      do
      {
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
      } while (zza.zzd((zza)((Iterator)localObject).next()) == zzb.zzcdg);
    }
    for (Object localObject = Boolean.valueOf(false);; localObject = Boolean.valueOf(true))
    {
      if (((Boolean)localObject).booleanValue()) {
        zza(localList, paramzzbnu.zzVc());
      }
      do
      {
        return;
      } while (!paramzzbnu.hasChildren());
      paramzzbnu.zzb(new zzbnu.zzb()
      {
        public void zzd(zzbnu<List<zzbml.zza>> paramAnonymouszzbnu)
        {
          zzbml.zza(zzbml.this, paramAnonymouszzbnu);
        }
      });
      return;
    }
  }
  
  private void zza(zzbnu<List<zza>> paramzzbnu, int paramInt)
  {
    List localList = (List)paramzzbnu.getValue();
    ArrayList localArrayList1 = new ArrayList();
    if (localList != null)
    {
      ArrayList localArrayList2 = new ArrayList();
      final DatabaseError localDatabaseError;
      int j;
      int i;
      label51:
      final zza localzza;
      if (paramInt == -9)
      {
        localDatabaseError = DatabaseError.zziE("overriddenBySet");
        j = 0;
        i = -1;
        if (j >= localList.size()) {
          break label355;
        }
        localzza = (zza)localList.get(j);
        if (zza.zzd(localzza) != zzb.zzcdj) {
          break label149;
        }
      }
      for (;;)
      {
        j += 1;
        break label51;
        if (paramInt == -25) {}
        for (bool = true;; bool = false)
        {
          zzbqg.zzb(bool, 45 + "Unknown transaction abort reason: " + paramInt);
          localDatabaseError = DatabaseError.zzpI(-25);
          break;
        }
        label149:
        if (zza.zzd(localzza) == zzb.zzcdh)
        {
          assert (i == j - 1);
          zza.zza(localzza, zzb.zzcdj);
          zza.zza(localzza, localDatabaseError);
          i = j;
        }
        else
        {
          assert (zza.zzd(localzza) == zzb.zzcdg);
          zze(new zzbmz(this, zza.zzj(localzza), zzboe.zzN(zza.zzf(localzza))));
          if (paramInt != -9) {
            break label310;
          }
          localArrayList1.addAll(this.zzccs.zza(zza.zzc(localzza), true, false, this.zzccf));
          localArrayList2.add(new Runnable()
          {
            public void run()
            {
              zzbml.zza.zzi(localzza).onComplete(localDatabaseError, false, null);
            }
          });
        }
      }
      label310:
      if (paramInt == -25) {}
      for (boolean bool = true;; bool = false)
      {
        zzbqg.zzb(bool, 45 + "Unknown transaction abort reason: " + paramInt);
        break;
      }
      label355:
      if (i == -1) {
        paramzzbnu.setValue(null);
      }
      for (;;)
      {
        zzW(localArrayList1);
        paramzzbnu = localArrayList2.iterator();
        while (paramzzbnu.hasNext()) {
          zzq((Runnable)paramzzbnu.next());
        }
        paramzzbnu.setValue(localList.subList(0, i + 1));
      }
    }
  }
  
  private void zza(String paramString, zzbmj paramzzbmj, DatabaseError paramDatabaseError)
  {
    if ((paramDatabaseError != null) && (paramDatabaseError.getCode() != -1) && (paramDatabaseError.getCode() != -25))
    {
      zzbop localzzbop = this.zzccm;
      paramzzbmj = String.valueOf(paramzzbmj.toString());
      paramDatabaseError = String.valueOf(paramDatabaseError.toString());
      localzzbop.warn(String.valueOf(paramString).length() + 13 + String.valueOf(paramzzbmj).length() + String.valueOf(paramDatabaseError).length() + paramString + " at " + paramzzbmj + " failed: " + paramDatabaseError);
    }
  }
  
  private void zza(final List<zza> paramList, final zzbmj paramzzbmj)
  {
    Object localObject1 = new ArrayList();
    Object localObject2 = paramList.iterator();
    while (((Iterator)localObject2).hasNext()) {
      ((List)localObject1).add(Long.valueOf(zza.zzc((zza)((Iterator)localObject2).next())));
    }
    localObject1 = zza(paramzzbmj, (List)localObject1);
    localObject2 = ((zzbpe)localObject1).zzZc();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      zza localzza = (zza)localIterator.next();
      assert (zza.zzd(localzza) == zzb.zzcdg);
      zza.zza(localzza, zzb.zzcdh);
      zza.zze(localzza);
      localObject1 = ((zzbpe)localObject1).zzl(zzbmj.zza(paramzzbmj, zza.zzf(localzza)), zza.zzg(localzza));
    }
    localObject1 = ((zzbpe)localObject1).getValue(true);
    zzXr();
    this.zzcaY.zza(paramzzbmj.zzXh(), localObject1, (String)localObject2, new zzblu()
    {
      public void zzan(String paramAnonymousString1, String paramAnonymousString2)
      {
        int i = 0;
        paramAnonymousString2 = zzbml.zzap(paramAnonymousString1, paramAnonymousString2);
        zzbml.zza(zzbml.this, "Transaction", paramzzbmj, paramAnonymousString2);
        paramAnonymousString1 = new ArrayList();
        Object localObject;
        if (paramAnonymousString2 == null)
        {
          paramAnonymousString2 = new ArrayList();
          localObject = paramList.iterator();
          while (((Iterator)localObject).hasNext())
          {
            final zzbml.zza localzza = (zzbml.zza)((Iterator)localObject).next();
            zzbml.zza.zza(localzza, zzbml.zzb.zzcdi);
            paramAnonymousString1.addAll(zzbml.zzg(zzbml.this).zza(zzbml.zza.zzc(localzza), false, false, zzbml.zzh(zzbml.this)));
            zzbpe localzzbpe = zzbml.zza.zzh(localzza);
            paramAnonymousString2.add(new Runnable()
            {
              public void run()
              {
                zzbml.zza.zzi(localzza).onComplete(null, true, this.zzccC);
              }
            });
            zzbml.this.zze(new zzbmz(zzbml.this, zzbml.zza.zzj(localzza), zzboe.zzN(zzbml.zza.zzf(localzza))));
          }
          zzbml.zzb(zzbml.this, zzbml.zzi(zzbml.this).zzL(paramzzbmj));
          zzbml.zzj(zzbml.this);
          zzbml.zza(jdField_this, paramAnonymousString1);
          while (i < paramAnonymousString2.size())
          {
            zzbml.this.zzq((Runnable)paramAnonymousString2.get(i));
            i += 1;
          }
        }
        if (paramAnonymousString2.getCode() == -1)
        {
          paramAnonymousString1 = paramList.iterator();
          while (paramAnonymousString1.hasNext())
          {
            paramAnonymousString2 = (zzbml.zza)paramAnonymousString1.next();
            if (zzbml.zza.zzd(paramAnonymousString2) == zzbml.zzb.zzcdj) {
              zzbml.zza.zza(paramAnonymousString2, zzbml.zzb.zzcdk);
            } else {
              zzbml.zza.zza(paramAnonymousString2, zzbml.zzb.zzcdg);
            }
          }
        }
        paramAnonymousString1 = paramList.iterator();
        while (paramAnonymousString1.hasNext())
        {
          localObject = (zzbml.zza)paramAnonymousString1.next();
          zzbml.zza.zza((zzbml.zza)localObject, zzbml.zzb.zzcdk);
          zzbml.zza.zza((zzbml.zza)localObject, paramAnonymousString2);
        }
        zzbml.zzb(zzbml.this, paramzzbmj);
      }
    });
  }
  
  private void zza(final List<zza> paramList, zzbnu<List<zza>> paramzzbnu)
  {
    List localList = (List)paramzzbnu.getValue();
    if (localList != null) {
      paramList.addAll(localList);
    }
    paramzzbnu.zzb(new zzbnu.zzb()
    {
      public void zzd(zzbnu<List<zzbml.zza>> paramAnonymouszzbnu)
      {
        zzbml.zza(zzbml.this, paramList, paramAnonymouszzbnu);
      }
    });
  }
  
  private static DatabaseError zzao(String paramString1, String paramString2)
  {
    if (paramString1 != null) {
      return DatabaseError.zzal(paramString1, paramString2);
    }
    return null;
  }
  
  private zzbmj zzb(zzbmj paramzzbmj, final int paramInt)
  {
    zzbmj localzzbmj = zzp(paramzzbmj).zzVc();
    if (this.zzccn.zzYT())
    {
      zzbop localzzbop = this.zzccm;
      String str1 = String.valueOf(paramzzbmj);
      String str2 = String.valueOf(localzzbmj);
      localzzbop.zzi(String.valueOf(str1).length() + 44 + String.valueOf(str2).length() + "Aborting transactions for path: " + str1 + ". Affected: " + str2, new Object[0]);
    }
    paramzzbmj = this.zzcci.zzL(paramzzbmj);
    paramzzbmj.zza(new zzbnu.zza()
    {
      public boolean zze(zzbnu<List<zzbml.zza>> paramAnonymouszzbnu)
      {
        zzbml.zza(zzbml.this, paramAnonymouszzbnu, paramInt);
        return false;
      }
    });
    zza(paramzzbmj, paramInt);
    paramzzbmj.zza(new zzbnu.zzb()
    {
      public void zzd(zzbnu<List<zzbml.zza>> paramAnonymouszzbnu)
      {
        zzbml.zza(zzbml.this, paramAnonymouszzbnu, paramInt);
      }
    });
    return localzzbmj;
  }
  
  private void zzb(zzbnu<List<zza>> paramzzbnu)
  {
    List localList = (List)paramzzbnu.getValue();
    if (localList != null)
    {
      int i = 0;
      if (i < localList.size())
      {
        if (zza.zzd((zza)localList.get(i)) == zzb.zzcdi) {
          localList.remove(i);
        }
        for (;;)
        {
          break;
          i += 1;
        }
      }
      if (localList.size() <= 0) {
        break label88;
      }
      paramzzbnu.setValue(localList);
    }
    for (;;)
    {
      paramzzbnu.zzb(new zzbnu.zzb()
      {
        public void zzd(zzbnu<List<zzbml.zza>> paramAnonymouszzbnu)
        {
          zzbml.zzb(zzbml.this, paramAnonymouszzbnu);
        }
      });
      return;
      label88:
      paramzzbnu.setValue(null);
    }
  }
  
  private void zzb(zzbos paramzzbos, Object paramObject)
  {
    if (paramzzbos.equals(zzbmb.zzcbH)) {
      this.zzccf.zzaP(((Long)paramObject).longValue());
    }
    paramzzbos = new zzbmj(new zzbos[] { zzbmb.zzcbG, paramzzbos });
    try
    {
      paramObject = zzbpf.zzar(paramObject);
      this.zzccg.zzg(paramzzbos, (zzbpe)paramObject);
      zzW(this.zzccr.zzi(paramzzbos, (zzbpe)paramObject));
      return;
    }
    catch (DatabaseException paramzzbos)
    {
      this.zzccm.zzd("Failed to parse info update", paramzzbos);
    }
  }
  
  private void zzb(final List<zza> paramList, zzbmj paramzzbmj)
  {
    if (paramList.isEmpty()) {
      return;
    }
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    final Object localObject1 = paramList.iterator();
    while (((Iterator)localObject1).hasNext()) {
      localArrayList2.add(Long.valueOf(zza.zzc((zza)((Iterator)localObject1).next())));
    }
    Iterator localIterator = paramList.iterator();
    final zza localzza;
    ArrayList localArrayList3;
    int i;
    if (localIterator.hasNext())
    {
      localzza = (zza)localIterator.next();
      paramList = zzbmj.zza(paramzzbmj, zza.zzf(localzza));
      localArrayList3 = new ArrayList();
      assert (paramList != null);
      if (zza.zzd(localzza) == zzb.zzcdk)
      {
        int j = 1;
        localObject1 = zza.zzk(localzza);
        i = j;
        paramList = (List<zza>)localObject1;
        if (((DatabaseError)localObject1).getCode() != -25)
        {
          localArrayList3.addAll(this.zzccs.zza(zza.zzc(localzza), true, false, this.zzccf));
          paramList = (List<zza>)localObject1;
          i = j;
        }
      }
    }
    for (;;)
    {
      zzW(localArrayList3);
      if (i == 0) {
        break;
      }
      zza.zza(localzza, zzb.zzcdi);
      localObject1 = zza.zza(zza.zza(this, zza.zzf(localzza)), zzboz.zzn(zza.zzb(localzza)));
      zzs(new Runnable()
      {
        public void run()
        {
          zzbml.this.zze(new zzbmz(zzbml.this, zzbml.zza.zzj(localzza), zzboe.zzN(zzbml.zza.zzf(localzza))));
        }
      });
      localArrayList1.add(new Runnable()
      {
        public void run()
        {
          zzbml.zza.zzi(localzza).onComplete(paramList, false, localObject1);
        }
      });
      break;
      if (zza.zzd(localzza) == zzb.zzcdg)
      {
        if (zza.zzl(localzza) >= 25)
        {
          i = 1;
          paramList = DatabaseError.zziE("maxretries");
          localArrayList3.addAll(this.zzccs.zza(zza.zzc(localzza), true, false, this.zzccf));
        }
        else
        {
          paramList = zza(zza.zzf(localzza), localArrayList2);
          zza.zza(localzza, paramList);
          paramList = zza.zza(paramList);
          try
          {
            localObject1 = zza.zzi(localzza).doTransaction(paramList);
            paramList = null;
          }
          catch (Throwable paramList)
          {
            for (;;)
            {
              Object localObject2;
              paramList = DatabaseError.fromException(paramList);
              localObject1 = Transaction.abort();
            }
            i = 1;
            localArrayList3.addAll(this.zzccs.zza(zza.zzc(localzza), true, false, this.zzccf));
          }
          if (((Transaction.Result)localObject1).isSuccess())
          {
            paramList = Long.valueOf(zza.zzc(localzza));
            localObject2 = zzbmp.zza(this.zzccf);
            localObject1 = ((Transaction.Result)localObject1).zzUY();
            localObject2 = zzbmp.zza((zzbpe)localObject1, (Map)localObject2);
            zza.zzb(localzza, (zzbpe)localObject1);
            zza.zzc(localzza, (zzbpe)localObject2);
            zza.zza(localzza, zzXr());
            localArrayList2.remove(paramList);
            localArrayList3.addAll(this.zzccs.zza(zza.zzf(localzza), (zzbpe)localObject1, (zzbpe)localObject2, zza.zzc(localzza), zza.zzm(localzza), false));
            localArrayList3.addAll(this.zzccs.zza(paramList.longValue(), true, false, this.zzccf));
            paramList = null;
            i = 0;
          }
          else
          {
            continue;
            zzb(this.zzcci);
            i = 0;
            while (i < localArrayList1.size())
            {
              zzq((Runnable)localArrayList1.get(i));
              i += 1;
            }
            zzXt();
          }
        }
      }
      else
      {
        paramList = null;
        i = 0;
      }
    }
  }
  
  private List<zza> zzc(zzbnu<List<zza>> paramzzbnu)
  {
    ArrayList localArrayList = new ArrayList();
    zza(localArrayList, paramzzbnu);
    Collections.sort(localArrayList);
    return localArrayList;
  }
  
  private zzbpe zzn(zzbmj paramzzbmj)
  {
    return zza(paramzzbmj, new ArrayList());
  }
  
  private zzbmj zzo(zzbmj paramzzbmj)
  {
    paramzzbmj = zzp(paramzzbmj);
    zzbmj localzzbmj = paramzzbmj.zzVc();
    zzb(zzc(paramzzbmj), localzzbmj);
    return localzzbmj;
  }
  
  private zzbnu<List<zza>> zzp(zzbmj paramzzbmj)
  {
    zzbnu localzzbnu = this.zzcci;
    while ((!paramzzbmj.isEmpty()) && (localzzbnu.getValue() == null))
    {
      localzzbnu = localzzbnu.zzL(new zzbmj(new zzbos[] { paramzzbmj.zzXi() }));
      paramzzbmj = paramzzbmj.zzXj();
    }
    return localzzbnu;
  }
  
  public FirebaseDatabase getDatabase()
  {
    return this.zzcct;
  }
  
  void interrupt()
  {
    this.zzcaY.interrupt("repo_interrupt");
  }
  
  public void onDisconnect()
  {
    zza(zzbmb.zzcbJ, Boolean.valueOf(false));
    zzXs();
  }
  
  public void purgeOutstandingWrites()
  {
    if (this.zzccm.zzYT()) {
      this.zzccm.zzi("Purging writes", new Object[0]);
    }
    zzW(this.zzccs.zzXz());
    zzb(zzbmj.zzXf(), -25);
    this.zzcaY.purgeOutstandingWrites();
  }
  
  void resume()
  {
    this.zzcaY.resume("repo_interrupt");
  }
  
  public String toString()
  {
    return this.zzbXP.toString();
  }
  
  public void zzVP()
  {
    zza(zzbmb.zzcbJ, Boolean.valueOf(true));
  }
  
  public zzbmm zzXo()
  {
    return this.zzbXP;
  }
  
  public long zzXp()
  {
    return this.zzccf.zzZY();
  }
  
  boolean zzXq()
  {
    return (!this.zzccr.isEmpty()) || (!this.zzccs.isEmpty());
  }
  
  public void zza(final zzbmj paramzzbmj, zzbma paramzzbma, DatabaseReference.CompletionListener paramCompletionListener, Map<String, Object> paramMap)
  {
    String str1;
    if (this.zzccm.zzYT())
    {
      localObject = this.zzccm;
      str1 = String.valueOf(paramzzbmj);
      ((zzbop)localObject).zzi(String.valueOf(str1).length() + 8 + "update: " + str1, new Object[0]);
    }
    if (this.zzcco.zzYT())
    {
      localObject = this.zzcco;
      str1 = String.valueOf(paramzzbmj);
      String str2 = String.valueOf(paramMap);
      ((zzbop)localObject).zzi(String.valueOf(str1).length() + 9 + String.valueOf(str2).length() + "update: " + str1 + " " + str2, new Object[0]);
    }
    if (paramzzbma.isEmpty())
    {
      if (this.zzccm.zzYT()) {
        this.zzccm.zzi("update called with no changes. No-op", new Object[0]);
      }
      zza(paramCompletionListener, null, paramzzbmj);
      return;
    }
    Object localObject = zzbmp.zza(paramzzbma, zzbmp.zza(this.zzccf));
    final long l = zzXr();
    zzW(this.zzccs.zza(paramzzbmj, paramzzbma, (zzbma)localObject, l, true));
    this.zzcaY.zza(paramzzbmj.zzXh(), paramMap, new zzblu()
    {
      public void zzan(String paramAnonymousString1, String paramAnonymousString2)
      {
        paramAnonymousString1 = zzbml.zzap(paramAnonymousString1, paramAnonymousString2);
        zzbml.zza(zzbml.this, "updateChildren", paramzzbmj, paramAnonymousString1);
        zzbml.zza(zzbml.this, l, paramzzbmj, paramAnonymousString1);
        zzbml.this.zza(this.zzccz, paramAnonymousString1, paramzzbmj);
      }
    });
    zzo(zzb(paramzzbmj, -9));
  }
  
  public void zza(final zzbmj paramzzbmj, zzbpe paramzzbpe, DatabaseReference.CompletionListener paramCompletionListener)
  {
    String str1;
    if (this.zzccm.zzYT())
    {
      localObject = this.zzccm;
      str1 = String.valueOf(paramzzbmj);
      ((zzbop)localObject).zzi(String.valueOf(str1).length() + 5 + "set: " + str1, new Object[0]);
    }
    if (this.zzcco.zzYT())
    {
      localObject = this.zzcco;
      str1 = String.valueOf(paramzzbmj);
      String str2 = String.valueOf(paramzzbpe);
      ((zzbop)localObject).zzi(String.valueOf(str1).length() + 6 + String.valueOf(str2).length() + "set: " + str1 + " " + str2, new Object[0]);
    }
    Object localObject = zzbmp.zza(paramzzbpe, zzbmp.zza(this.zzccf));
    final long l = zzXr();
    zzW(this.zzccs.zza(paramzzbmj, paramzzbpe, (zzbpe)localObject, l, true, true));
    this.zzcaY.zza(paramzzbmj.zzXh(), paramzzbpe.getValue(true), new zzblu()
    {
      public void zzan(String paramAnonymousString1, String paramAnonymousString2)
      {
        paramAnonymousString1 = zzbml.zzap(paramAnonymousString1, paramAnonymousString2);
        zzbml.zza(zzbml.this, "setValue", paramzzbmj, paramAnonymousString1);
        zzbml.zza(zzbml.this, l, paramzzbmj, paramAnonymousString1);
        zzbml.this.zza(this.zzccz, paramAnonymousString1, paramzzbmj);
      }
    });
    zzo(zzb(paramzzbmj, -9));
  }
  
  public void zza(final zzbmj paramzzbmj, final DatabaseReference.CompletionListener paramCompletionListener)
  {
    this.zzcaY.zza(paramzzbmj.zzXh(), new zzblu()
    {
      public void zzan(String paramAnonymousString1, String paramAnonymousString2)
      {
        paramAnonymousString1 = zzbml.zzap(paramAnonymousString1, paramAnonymousString2);
        if (paramAnonymousString1 == null) {
          zzbml.zzf(zzbml.this).zzr(paramzzbmj);
        }
        zzbml.this.zza(paramCompletionListener, paramAnonymousString1, paramzzbmj);
      }
    });
  }
  
  public void zza(zzbmj paramzzbmj, final Transaction.Handler paramHandler, boolean paramBoolean)
  {
    final Object localObject3;
    if (this.zzccm.zzYT())
    {
      localObject1 = this.zzccm;
      localObject3 = String.valueOf(paramzzbmj);
      ((zzbop)localObject1).zzi(String.valueOf(localObject3).length() + 13 + "transaction: " + (String)localObject3, new Object[0]);
    }
    if (this.zzcco.zzYT())
    {
      localObject1 = this.zzccm;
      localObject3 = String.valueOf(paramzzbmj);
      ((zzbop)localObject1).zzi(String.valueOf(localObject3).length() + 13 + "transaction: " + (String)localObject3, new Object[0]);
    }
    if ((this.zzccl.zzVK()) && (!this.zzccu))
    {
      this.zzccu = true;
      this.zzccn.info("runTransaction() usage detected while persistence is enabled. Please be aware that transactions *will not* be persisted across database restarts.  See https://www.firebase.com/docs/android/guide/offline-capabilities.html#section-handling-transactions-offline for more details.");
    }
    Object localObject4 = zza.zza(this, paramzzbmj);
    Object localObject1 = new ValueEventListener()
    {
      public void onCancelled(DatabaseError paramAnonymousDatabaseError) {}
      
      public void onDataChange(DataSnapshot paramAnonymousDataSnapshot) {}
    };
    zzf(new zzbmz(this, (ValueEventListener)localObject1, ((DatabaseReference)localObject4).zzVd()));
    zza localzza = new zza(paramzzbmj, paramHandler, (ValueEventListener)localObject1, zzb.zzcdf, paramBoolean, zzXu(), null);
    localObject1 = zzn(paramzzbmj);
    zza.zza(localzza, (zzbpe)localObject1);
    localObject1 = zza.zza((zzbpe)localObject1);
    try
    {
      localObject1 = paramHandler.doTransaction((MutableData)localObject1);
      if (localObject1 == null) {
        throw new NullPointerException("Transaction returned null as result");
      }
    }
    catch (Throwable localThrowable)
    {
      localObject3 = DatabaseError.fromException(localThrowable);
      Object localObject2 = Transaction.abort();
      while (!((Transaction.Result)localObject2).isSuccess())
      {
        zza.zzb(localzza, null);
        zza.zzc(localzza, null);
        zzq(new Runnable()
        {
          public void run()
          {
            paramHandler.onComplete(localObject3, false, this.zzccC);
          }
        });
        return;
        localObject3 = null;
      }
      zza.zza(localzza, zzb.zzcdg);
      localObject4 = this.zzcci.zzL(paramzzbmj);
      localObject3 = (List)((zzbnu)localObject4).getValue();
      paramHandler = (Transaction.Handler)localObject3;
      if (localObject3 == null) {
        paramHandler = new ArrayList();
      }
      paramHandler.add(localzza);
      ((zzbnu)localObject4).setValue(paramHandler);
      paramHandler = zzbmp.zza(this.zzccf);
      localObject2 = ((Transaction.Result)localObject2).zzUY();
      paramHandler = zzbmp.zza((zzbpe)localObject2, paramHandler);
      zza.zzb(localzza, (zzbpe)localObject2);
      zza.zzc(localzza, paramHandler);
      zza.zza(localzza, zzXr());
      zzW(this.zzccs.zza(paramzzbmj, (zzbpe)localObject2, paramHandler, zza.zzc(localzza), paramBoolean, false));
      zzXt();
    }
  }
  
  public void zza(final zzbmj paramzzbmj, final Map<zzbmj, zzbpe> paramMap, final DatabaseReference.CompletionListener paramCompletionListener, Map<String, Object> paramMap1)
  {
    this.zzcaY.zzb(paramzzbmj.zzXh(), paramMap1, new zzblu()
    {
      public void zzan(String paramAnonymousString1, String paramAnonymousString2)
      {
        paramAnonymousString1 = zzbml.zzap(paramAnonymousString1, paramAnonymousString2);
        zzbml.zza(zzbml.this, "onDisconnect().updateChildren", paramzzbmj, paramAnonymousString1);
        if (paramAnonymousString1 == null)
        {
          paramAnonymousString2 = paramMap.entrySet().iterator();
          while (paramAnonymousString2.hasNext())
          {
            Map.Entry localEntry = (Map.Entry)paramAnonymousString2.next();
            zzbml.zzf(zzbml.this).zzh(paramzzbmj.zzh((zzbmj)localEntry.getKey()), (zzbpe)localEntry.getValue());
          }
        }
        zzbml.this.zza(paramCompletionListener, paramAnonymousString1, paramzzbmj);
      }
    });
  }
  
  public void zza(zzboe paramzzboe, boolean paramBoolean)
  {
    assert ((paramzzboe.zzVc().isEmpty()) || (!paramzzboe.zzVc().zzXi().equals(zzbmb.zzcbG)));
    this.zzccs.zza(paramzzboe, paramBoolean);
  }
  
  public void zza(zzbos paramzzbos, Object paramObject)
  {
    zzb(paramzzbos, paramObject);
  }
  
  void zza(final DatabaseReference.CompletionListener paramCompletionListener, final DatabaseError paramDatabaseError, final zzbmj paramzzbmj)
  {
    if (paramCompletionListener != null)
    {
      zzbos localzzbos = paramzzbmj.zzXl();
      if ((localzzbos == null) || (!localzzbos.zzZa())) {
        break label48;
      }
    }
    label48:
    for (paramzzbmj = zza.zza(this, paramzzbmj.zzXk());; paramzzbmj = zza.zza(this, paramzzbmj))
    {
      zzq(new Runnable()
      {
        public void run()
        {
          paramCompletionListener.onComplete(paramDatabaseError, paramzzbmj);
        }
      });
      return;
    }
  }
  
  public void zza(List<String> paramList, Object paramObject, boolean paramBoolean, Long paramLong)
  {
    zzbmj localzzbmj = new zzbmj(paramList);
    Object localObject1;
    if (this.zzccm.zzYT())
    {
      paramList = this.zzccm;
      localObject1 = String.valueOf(localzzbmj);
      paramList.zzi(String.valueOf(localObject1).length() + 14 + "onDataUpdate: " + (String)localObject1, new Object[0]);
    }
    Object localObject2;
    if (this.zzcco.zzYT())
    {
      paramList = this.zzccm;
      localObject1 = String.valueOf(localzzbmj);
      localObject2 = String.valueOf(paramObject);
      paramList.zzi(String.valueOf(localObject1).length() + 15 + String.valueOf(localObject2).length() + "onDataUpdate: " + (String)localObject1 + " " + (String)localObject2, new Object[0]);
    }
    this.zzccp += 1L;
    if (paramLong != null) {
      try
      {
        paramList = new zzbmu(paramLong.longValue());
        if (paramBoolean)
        {
          paramLong = new HashMap();
          paramObject = ((Map)paramObject).entrySet().iterator();
          while (((Iterator)paramObject).hasNext())
          {
            localObject1 = (Map.Entry)((Iterator)paramObject).next();
            localObject2 = zzbpf.zzar(((Map.Entry)localObject1).getValue());
            paramLong.put(new zzbmj((String)((Map.Entry)localObject1).getKey()), localObject2);
          }
          paramList = this.zzccs.zza(localzzbmj, paramLong, paramList);
        }
      }
      catch (DatabaseException paramList)
      {
        this.zzccm.zzd("FIREBASE INTERNAL ERROR", paramList);
        return;
      }
    }
    for (;;)
    {
      if (paramList.size() > 0) {
        zzo(localzzbmj);
      }
      zzW(paramList);
      return;
      paramObject = zzbpf.zzar(paramObject);
      paramList = this.zzccs.zza(localzzbmj, (zzbpe)paramObject, paramList);
      continue;
      if (paramBoolean)
      {
        paramList = new HashMap();
        paramObject = ((Map)paramObject).entrySet().iterator();
        while (((Iterator)paramObject).hasNext())
        {
          paramLong = (Map.Entry)((Iterator)paramObject).next();
          localObject1 = zzbpf.zzar(paramLong.getValue());
          paramList.put(new zzbmj((String)paramLong.getKey()), localObject1);
        }
        paramList = this.zzccs.zza(localzzbmj, paramList);
      }
      else
      {
        paramList = zzbpf.zzar(paramObject);
        paramList = this.zzccs.zzi(localzzbmj, paramList);
      }
    }
  }
  
  public void zza(List<String> paramList, List<zzblt> paramList1, Long paramLong)
  {
    zzbmj localzzbmj = new zzbmj(paramList);
    String str1;
    if (this.zzccm.zzYT())
    {
      paramList = this.zzccm;
      str1 = String.valueOf(localzzbmj);
      paramList.zzi(String.valueOf(str1).length() + 20 + "onRangeMergeUpdate: " + str1, new Object[0]);
    }
    if (this.zzcco.zzYT())
    {
      paramList = this.zzccm;
      str1 = String.valueOf(localzzbmj);
      String str2 = String.valueOf(paramList1);
      paramList.zzi(String.valueOf(str1).length() + 21 + String.valueOf(str2).length() + "onRangeMergeUpdate: " + str1 + " " + str2, new Object[0]);
    }
    this.zzccp += 1L;
    paramList = new ArrayList(paramList1.size());
    paramList1 = paramList1.iterator();
    while (paramList1.hasNext()) {
      paramList.add(new zzbpj((zzblt)paramList1.next()));
    }
    if (paramLong != null) {}
    for (paramList = this.zzccs.zza(localzzbmj, paramList, new zzbmu(paramLong.longValue()));; paramList = this.zzccs.zzb(localzzbmj, paramList))
    {
      if (paramList.size() > 0) {
        zzo(localzzbmj);
      }
      zzW(paramList);
      return;
    }
  }
  
  public void zzaX(boolean paramBoolean)
  {
    zza(zzbmb.zzcbI, Boolean.valueOf(paramBoolean));
  }
  
  public void zzaw(Map<String, Object> paramMap)
  {
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      zzb(zzbos.zzjb((String)localEntry.getKey()), localEntry.getValue());
    }
  }
  
  public void zzb(final zzbmj paramzzbmj, final zzbpe paramzzbpe, final DatabaseReference.CompletionListener paramCompletionListener)
  {
    this.zzcaY.zzb(paramzzbmj.zzXh(), paramzzbpe.getValue(true), new zzblu()
    {
      public void zzan(String paramAnonymousString1, String paramAnonymousString2)
      {
        paramAnonymousString1 = zzbml.zzap(paramAnonymousString1, paramAnonymousString2);
        zzbml.zza(zzbml.this, "onDisconnect().setValue", paramzzbmj, paramAnonymousString1);
        if (paramAnonymousString1 == null) {
          zzbml.zzf(zzbml.this).zzh(paramzzbmj, paramzzbpe);
        }
        zzbml.this.zza(paramCompletionListener, paramAnonymousString1, paramzzbmj);
      }
    });
  }
  
  public void zze(zzbme paramzzbme)
  {
    if (zzbmb.zzcbG.equals(paramzzbme.zzWD().zzVc().zzXi())) {}
    for (paramzzbme = this.zzccr.zzh(paramzzbme);; paramzzbme = this.zzccs.zzh(paramzzbme))
    {
      zzW(paramzzbme);
      return;
    }
  }
  
  public void zzf(zzbme paramzzbme)
  {
    zzbos localzzbos = paramzzbme.zzWD().zzVc().zzXi();
    if ((localzzbos != null) && (localzzbos.equals(zzbmb.zzcbG))) {}
    for (paramzzbme = this.zzccr.zzg(paramzzbme);; paramzzbme = this.zzccs.zzg(paramzzbme))
    {
      zzW(paramzzbme);
      return;
    }
  }
  
  public void zzq(Runnable paramRunnable)
  {
    this.zzccl.zzWK();
    this.zzccl.zzWQ().zzq(paramRunnable);
  }
  
  public void zzs(Runnable paramRunnable)
  {
    this.zzccl.zzWK();
    this.zzccl.zzWR().zzs(paramRunnable);
  }
  
  private static class zza
    implements Comparable<zza>
  {
    private int retryCount;
    private zzbmj zzbXY;
    private Transaction.Handler zzccV;
    private ValueEventListener zzccW;
    private zzbml.zzb zzccX;
    private long zzccY;
    private boolean zzccZ;
    private DatabaseError zzcda;
    private long zzcdb;
    private zzbpe zzcdc;
    private zzbpe zzcdd;
    private zzbpe zzcde;
    
    private zza(zzbmj paramzzbmj, Transaction.Handler paramHandler, ValueEventListener paramValueEventListener, zzbml.zzb paramzzb, boolean paramBoolean, long paramLong)
    {
      this.zzbXY = paramzzbmj;
      this.zzccV = paramHandler;
      this.zzccW = paramValueEventListener;
      this.zzccX = paramzzb;
      this.retryCount = 0;
      this.zzccZ = paramBoolean;
      this.zzccY = paramLong;
      this.zzcda = null;
      this.zzcdc = null;
      this.zzcdd = null;
      this.zzcde = null;
    }
    
    public int zza(zza paramzza)
    {
      if (this.zzccY < paramzza.zzccY) {
        return -1;
      }
      if (this.zzccY == paramzza.zzccY) {
        return 0;
      }
      return 1;
    }
  }
  
  private static enum zzb
  {
    private zzb() {}
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbml.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */