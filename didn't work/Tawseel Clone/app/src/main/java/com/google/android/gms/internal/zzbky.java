package com.google.android.gms.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.google.firebase.database.DatabaseException;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
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

public class zzbky
  implements zzbno
{
  private static final Charset zzavy;
  private final SQLiteDatabase zzbYw;
  private final zzbop zzbYx;
  private boolean zzbYy;
  private long zzbYz = 0L;
  
  static
  {
    if (!zzbky.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      zzavy = Charset.forName("UTF-8");
      return;
    }
  }
  
  public zzbky(Context paramContext, zzbmc paramzzbmc, String paramString)
  {
    try
    {
      paramString = URLEncoder.encode(paramString, "utf-8");
      this.zzbYx = paramzzbmc.zziW("Persistence");
      this.zzbYw = zzJ(paramContext, paramString);
      return;
    }
    catch (IOException paramContext)
    {
      throw new RuntimeException(paramContext);
    }
  }
  
  private SQLiteDatabase zzJ(Context paramContext, String paramString)
  {
    paramContext = new zza(paramContext, paramString);
    try
    {
      paramContext = paramContext.getWritableDatabase();
      paramContext.rawQuery("PRAGMA locking_mode = EXCLUSIVE", null).close();
      paramContext.beginTransaction();
      paramContext.endTransaction();
      return paramContext;
    }
    catch (SQLiteException paramContext)
    {
      if ((paramContext instanceof SQLiteDatabaseLockedException)) {
        throw new DatabaseException("Failed to gain exclusive lock to Firebase Database's offline persistence. This generally means you are using Firebase Database from multiple processes in your app. Keep in mind that multi-process Android apps execute the code in your Application class in all processes, so you may need to avoid initializing FirebaseDatabase in your Application class. If you are intentionally using Firebase Database from multiple processes, you can only enable offline persistence (i.e. call setPersistenceEnabled(true)) in one of them.", paramContext);
      }
      throw paramContext;
    }
  }
  
  private byte[] zzS(List<byte[]> paramList)
  {
    Object localObject = paramList.iterator();
    for (int i = 0; ((Iterator)localObject).hasNext(); i = ((byte[])((Iterator)localObject).next()).length + i) {}
    localObject = new byte[i];
    paramList = paramList.iterator();
    byte[] arrayOfByte;
    for (i = 0; paramList.hasNext(); i = arrayOfByte.length + i)
    {
      arrayOfByte = (byte[])paramList.next();
      System.arraycopy(arrayOfByte, 0, localObject, i, arrayOfByte.length);
    }
    return (byte[])localObject;
  }
  
  private zzbpe zzT(byte[] paramArrayOfByte)
  {
    try
    {
      zzbpe localzzbpe = zzbpf.zzar(zzbpx.zzjg(new String(paramArrayOfByte, zzavy)));
      return localzzbpe;
    }
    catch (IOException localIOException)
    {
      paramArrayOfByte = String.valueOf(new String(paramArrayOfByte, zzavy));
      if (paramArrayOfByte.length() == 0) {}
    }
    for (paramArrayOfByte = "Could not deserialize node: ".concat(paramArrayOfByte);; paramArrayOfByte = new String("Could not deserialize node: ")) {
      throw new RuntimeException(paramArrayOfByte, localIOException);
    }
  }
  
  private void zzVi()
  {
    zzbqg.zzb(this.zzbYy, "Transaction expected to already be in progress.");
  }
  
  private int zza(zzbmj paramzzbmj, List<String> paramList, int paramInt)
  {
    int i = paramInt + 1;
    String str = zzc(paramzzbmj);
    if (!((String)paramList.get(paramInt)).startsWith(str)) {
      throw new IllegalStateException("Extracting split nodes needs to start with path prefix");
    }
    while ((i < paramList.size()) && (((String)paramList.get(i)).equals(zza(paramzzbmj, i - paramInt)))) {
      i += 1;
    }
    if (i < paramList.size())
    {
      paramList = (String)paramList.get(i);
      paramzzbmj = String.valueOf(str);
      str = String.valueOf(".part-");
      if (str.length() != 0) {}
      for (paramzzbmj = paramzzbmj.concat(str); paramList.startsWith(paramzzbmj); paramzzbmj = new String(paramzzbmj)) {
        throw new IllegalStateException("Run did not finish with all parts");
      }
    }
    return i - paramInt;
  }
  
  private int zza(String paramString, zzbmj paramzzbmj)
  {
    paramzzbmj = zzc(paramzzbmj);
    String str = zziH(paramzzbmj);
    return this.zzbYw.delete(paramString, "path >= ? AND path < ?", new String[] { paramzzbmj, str });
  }
  
  private Cursor zza(zzbmj paramzzbmj, String[] paramArrayOfString)
  {
    String str2 = zzc(paramzzbmj);
    String str3 = zziH(str2);
    String[] arrayOfString = new String[paramzzbmj.size() + 3];
    String str1 = String.valueOf(zzb(paramzzbmj, arrayOfString));
    String str4 = String.valueOf(" OR (path > ? AND path < ?)");
    if (str4.length() != 0) {}
    for (str1 = str1.concat(str4);; str1 = new String(str1))
    {
      arrayOfString[(paramzzbmj.size() + 1)] = str2;
      arrayOfString[(paramzzbmj.size() + 2)] = str3;
      return this.zzbYw.query("serverCache", paramArrayOfString, str1, arrayOfString, null, null, "path");
    }
  }
  
  private String zza(zzbmj paramzzbmj, int paramInt)
  {
    paramzzbmj = String.valueOf(zzc(paramzzbmj));
    String str = String.valueOf(String.format(".part-%04d", new Object[] { Integer.valueOf(paramInt) }));
    if (str.length() != 0) {
      return paramzzbmj.concat(str);
    }
    return new String(paramzzbmj);
  }
  
  private void zza(zzbmj paramzzbmj, long paramLong, String paramString, byte[] paramArrayOfByte)
  {
    zzVi();
    this.zzbYw.delete("writes", "id = ?", new String[] { String.valueOf(paramLong) });
    if (paramArrayOfByte.length >= 262144)
    {
      paramArrayOfByte = zzd(paramArrayOfByte, 262144);
      int i = 0;
      while (i < paramArrayOfByte.size())
      {
        localContentValues = new ContentValues();
        localContentValues.put("id", Long.valueOf(paramLong));
        localContentValues.put("path", zzc(paramzzbmj));
        localContentValues.put("type", paramString);
        localContentValues.put("part", Integer.valueOf(i));
        localContentValues.put("node", (byte[])paramArrayOfByte.get(i));
        this.zzbYw.insertWithOnConflict("writes", null, localContentValues, 5);
        i += 1;
      }
    }
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("id", Long.valueOf(paramLong));
    localContentValues.put("path", zzc(paramzzbmj));
    localContentValues.put("type", paramString);
    localContentValues.put("part", null);
    localContentValues.put("node", paramArrayOfByte);
    this.zzbYw.insertWithOnConflict("writes", null, localContentValues, 5);
  }
  
  private void zza(zzbmj paramzzbmj1, final zzbmj paramzzbmj2, zzbns<Long> paramzzbns1, final zzbns<Long> paramzzbns2, zzbnp paramzzbnp, final List<zzbqd<zzbmj, zzbpe>> paramList)
  {
    if (paramzzbns1.getValue() != null)
    {
      int i = ((Integer)paramzzbnp.zza(Integer.valueOf(0), new zzbns.zza()
      {
        public Integer zza(zzbmj paramAnonymouszzbmj, Void paramAnonymousVoid, Integer paramAnonymousInteger)
        {
          if (paramzzbns2.zzK(paramAnonymouszzbmj) == null) {}
          for (int i = paramAnonymousInteger.intValue() + 1;; i = paramAnonymousInteger.intValue()) {
            return Integer.valueOf(i);
          }
        }
      })).intValue();
      if (i > 0)
      {
        paramzzbmj1 = paramzzbmj1.zzh(paramzzbmj2);
        if (this.zzbYx.zzYT()) {
          this.zzbYx.zzi(String.format("Need to rewrite %d nodes below path %s", new Object[] { Integer.valueOf(i), paramzzbmj1 }), new Object[0]);
        }
        paramzzbnp.zza(null, new zzbns.zza()
        {
          public Void zza(zzbmj paramAnonymouszzbmj, Void paramAnonymousVoid1, Void paramAnonymousVoid2)
          {
            if (paramzzbns2.zzK(paramAnonymouszzbmj) == null) {
              paramList.add(new zzbqd(paramzzbmj2.zzh(paramAnonymouszzbmj), this.zzbYD.zzO(paramAnonymouszzbmj)));
            }
            return null;
          }
        });
      }
    }
    for (;;)
    {
      return;
      paramzzbns1 = paramzzbns1.zzYe().iterator();
      while (paramzzbns1.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramzzbns1.next();
        zzbos localzzbos = (zzbos)localEntry.getKey();
        zzbnp localzzbnp = paramzzbnp.zzd((zzbos)localEntry.getKey());
        zza(paramzzbmj1, paramzzbmj2.zza(localzzbos), (zzbns)localEntry.getValue(), paramzzbns2.zze(localzzbos), localzzbnp, paramList);
      }
    }
  }
  
  private void zza(zzbmj paramzzbmj, zzbpe paramzzbpe, boolean paramBoolean)
  {
    long l1 = System.currentTimeMillis();
    int k;
    int m;
    if (!paramBoolean)
    {
      k = zza("serverCache", paramzzbmj);
      m = zzc(paramzzbmj, paramzzbpe);
      long l2 = System.currentTimeMillis();
      if (this.zzbYx.zzYT()) {
        this.zzbYx.zzi(String.format("Persisted a total of %d rows and deleted %d rows for a set at %s in %dms", new Object[] { Integer.valueOf(m), Integer.valueOf(k), paramzzbmj.toString(), Long.valueOf(l2 - l1) }), new Object[0]);
      }
      return;
    }
    paramzzbpe = paramzzbpe.iterator();
    int i = 0;
    int j = 0;
    for (;;)
    {
      m = i;
      k = j;
      if (!paramzzbpe.hasNext()) {
        break;
      }
      zzbpd localzzbpd = (zzbpd)paramzzbpe.next();
      j += zza("serverCache", paramzzbmj.zza(localzzbpd.zzZz()));
      i = zzc(paramzzbmj.zza(localzzbpd.zzZz()), localzzbpd.zzUY()) + i;
    }
  }
  
  private byte[] zzad(Object paramObject)
  {
    try
    {
      paramObject = zzbpx.zzat(paramObject).getBytes(zzavy);
      return (byte[])paramObject;
    }
    catch (IOException paramObject)
    {
      throw new RuntimeException("Could not serialize leaf node", (Throwable)paramObject);
    }
  }
  
  private zzbpe zzb(zzbmj paramzzbmj)
  {
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList1 = new ArrayList();
    long l1 = System.currentTimeMillis();
    Object localObject1 = zza(paramzzbmj, new String[] { "path", "value" });
    long l2 = System.currentTimeMillis();
    long l3 = System.currentTimeMillis();
    try
    {
      while (((Cursor)localObject1).moveToNext())
      {
        localArrayList2.add(((Cursor)localObject1).getString(0));
        localArrayList1.add(((Cursor)localObject1).getBlob(1));
      }
      l4 = System.currentTimeMillis();
    }
    finally
    {
      ((Cursor)localObject1).close();
    }
    long l4;
    long l5 = System.currentTimeMillis();
    localObject1 = zzbox.zzZp();
    int i = 0;
    HashMap localHashMap = new HashMap();
    int j = 0;
    Object localObject3;
    if (j < localArrayList1.size())
    {
      Object localObject4;
      if (((String)localArrayList2.get(j)).endsWith(".part-0000"))
      {
        localObject2 = (String)localArrayList2.get(j);
        localObject2 = new zzbmj(((String)localObject2).substring(0, ((String)localObject2).length() - ".part-0000".length()));
        int k = zza((zzbmj)localObject2, localArrayList2, j);
        if (this.zzbYx.zzYT()) {
          this.zzbYx.zzi(42 + "Loading split node with " + k + " parts.", new Object[0]);
        }
        localObject4 = zzT(zzS(localArrayList1.subList(j, j + k)));
        j = j + k - 1;
        localObject3 = localObject2;
        label310:
        if ((((zzbmj)localObject3).zzXl() == null) || (!((zzbmj)localObject3).zzXl().zzZa())) {
          break label392;
        }
        localHashMap.put(localObject3, localObject4);
      }
      for (;;)
      {
        j += 1;
        break;
        localObject2 = zzT((byte[])localArrayList1.get(j));
        localObject3 = new zzbmj((String)localArrayList2.get(j));
        localObject4 = localObject2;
        break label310;
        label392:
        if (((zzbmj)localObject3).zzi(paramzzbmj))
        {
          if (i == 0) {}
          for (boolean bool = true;; bool = false)
          {
            zzbqg.zzb(bool, "Descendants of path must come after ancestors.");
            localObject1 = ((zzbpe)localObject4).zzO(zzbmj.zza((zzbmj)localObject3, paramzzbmj));
            break;
          }
        }
        if (!paramzzbmj.zzi((zzbmj)localObject3)) {
          break label471;
        }
        i = 1;
        localObject1 = ((zzbpe)localObject1).zzl(zzbmj.zza(paramzzbmj, (zzbmj)localObject3), (zzbpe)localObject4);
      }
      label471:
      throw new IllegalStateException(String.format("Loading an unrelated row with path %s for %s", new Object[] { localObject3, paramzzbmj }));
    }
    Object localObject2 = localHashMap.entrySet().iterator();
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (Map.Entry)((Iterator)localObject2).next();
      localObject1 = ((zzbpe)localObject1).zzl(zzbmj.zza(paramzzbmj, (zzbmj)((Map.Entry)localObject3).getKey()), (zzbpe)((Map.Entry)localObject3).getValue());
    }
    long l6 = System.currentTimeMillis();
    long l7 = System.currentTimeMillis();
    if (this.zzbYx.zzYT()) {
      this.zzbYx.zzi(String.format("Loaded a total of %d rows for a total of %d nodes at %s in %dms (Query: %dms, Loading: %dms, Serializing: %dms)", new Object[] { Integer.valueOf(localArrayList1.size()), Integer.valueOf(zzbqb.zzu((zzbpe)localObject1)), paramzzbmj, Long.valueOf(l7 - l1), Long.valueOf(l2 - l1), Long.valueOf(l4 - l3), Long.valueOf(l6 - l5) }), new Object[0]);
    }
    return (zzbpe)localObject1;
  }
  
  private static String zzb(zzbmj paramzzbmj, String[] paramArrayOfString)
  {
    assert (paramArrayOfString.length >= paramzzbmj.size() + 1);
    int i = 0;
    StringBuilder localStringBuilder = new StringBuilder("(");
    while (!paramzzbmj.isEmpty())
    {
      localStringBuilder.append("path");
      localStringBuilder.append(" = ? OR ");
      paramArrayOfString[i] = zzc(paramzzbmj);
      paramzzbmj = paramzzbmj.zzXk();
      i += 1;
    }
    localStringBuilder.append("path");
    localStringBuilder.append(" = ?)");
    paramArrayOfString[i] = zzc(zzbmj.zzXf());
    return localStringBuilder.toString();
  }
  
  private int zzc(zzbmj paramzzbmj, zzbpe paramzzbpe)
  {
    long l = zzbqb.zzt(paramzzbpe);
    if (((paramzzbpe instanceof zzbot)) && (l > 16384L))
    {
      if (this.zzbYx.zzYT()) {
        this.zzbYx.zzi(String.format("Node estimated serialized size at path %s of %d bytes exceeds limit of %d bytes. Splitting up.", new Object[] { paramzzbmj, Long.valueOf(l), Integer.valueOf(16384) }), new Object[0]);
      }
      Iterator localIterator = paramzzbpe.iterator();
      zzbpd localzzbpd;
      for (int i = 0; localIterator.hasNext(); i = zzc(paramzzbmj.zza(localzzbpd.zzZz()), localzzbpd.zzUY()) + i) {
        localzzbpd = (zzbpd)localIterator.next();
      }
      int j = i;
      if (!paramzzbpe.zzZe().isEmpty())
      {
        zzd(paramzzbmj.zza(zzbos.zzYY()), paramzzbpe.zzZe());
        j = i + 1;
      }
      zzd(paramzzbmj, zzbox.zzZp());
      return j + 1;
    }
    zzd(paramzzbmj, paramzzbpe);
    return 1;
  }
  
  private static String zzc(zzbmj paramzzbmj)
  {
    if (paramzzbmj.isEmpty()) {
      return "/";
    }
    return String.valueOf(paramzzbmj.toString()).concat("/");
  }
  
  private static List<byte[]> zzd(byte[] paramArrayOfByte, int paramInt)
  {
    int j = (paramArrayOfByte.length - 1) / paramInt + 1;
    ArrayList localArrayList = new ArrayList(j);
    int i = 0;
    while (i < j)
    {
      int k = Math.min(paramInt, paramArrayOfByte.length - i * paramInt);
      byte[] arrayOfByte = new byte[k];
      System.arraycopy(paramArrayOfByte, i * paramInt, arrayOfByte, 0, k);
      localArrayList.add(arrayOfByte);
      i += 1;
    }
    return localArrayList;
  }
  
  private void zzd(zzbmj paramzzbmj, zzbpe paramzzbpe)
  {
    paramzzbpe = zzad(paramzzbpe.getValue(true));
    if (paramzzbpe.length >= 262144)
    {
      paramzzbpe = zzd(paramzzbpe, 262144);
      if (this.zzbYx.zzYT())
      {
        localObject = this.zzbYx;
        i = paramzzbpe.size();
        ((zzbop)localObject).zzi(45 + "Saving huge leaf node with " + i + " parts.", new Object[0]);
      }
      int i = 0;
      while (i < paramzzbpe.size())
      {
        localObject = new ContentValues();
        ((ContentValues)localObject).put("path", zza(paramzzbmj, i));
        ((ContentValues)localObject).put("value", (byte[])paramzzbpe.get(i));
        this.zzbYw.insertWithOnConflict("serverCache", null, (ContentValues)localObject, 5);
        i += 1;
      }
    }
    Object localObject = new ContentValues();
    ((ContentValues)localObject).put("path", zzc(paramzzbmj));
    ((ContentValues)localObject).put("value", paramzzbpe);
    this.zzbYw.insertWithOnConflict("serverCache", null, (ContentValues)localObject, 5);
  }
  
  private static String zziH(String paramString)
  {
    assert (paramString.endsWith("/")) : "Path keys must end with a '/'";
    paramString = String.valueOf(paramString.substring(0, paramString.length() - 1));
    return String.valueOf(paramString).length() + 1 + paramString + '0';
  }
  
  private String zzp(Collection<Long> paramCollection)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramCollection = paramCollection.iterator();
    for (int i = 1; paramCollection.hasNext(); i = 0)
    {
      long l = ((Long)paramCollection.next()).longValue();
      if (i == 0) {
        localStringBuilder.append(",");
      }
      localStringBuilder.append(l);
    }
    return localStringBuilder.toString();
  }
  
  public void beginTransaction()
  {
    if (!this.zzbYy) {}
    for (boolean bool = true;; bool = false)
    {
      zzbqg.zzb(bool, "runInTransaction called when an existing transaction is already in progress.");
      if (this.zzbYx.zzYT()) {
        this.zzbYx.zzi("Starting transaction.", new Object[0]);
      }
      this.zzbYw.beginTransaction();
      this.zzbYy = true;
      this.zzbYz = System.currentTimeMillis();
      return;
    }
  }
  
  public void endTransaction()
  {
    this.zzbYw.endTransaction();
    this.zzbYy = false;
    long l1 = System.currentTimeMillis();
    long l2 = this.zzbYz;
    if (this.zzbYx.zzYT()) {
      this.zzbYx.zzi(String.format("Transaction completed. Elapsed: %dms", new Object[] { Long.valueOf(l1 - l2) }), new Object[0]);
    }
  }
  
  public void setTransactionSuccessful()
  {
    this.zzbYw.setTransactionSuccessful();
  }
  
  public List<zzbmx> zzVe()
  {
    long l1 = System.currentTimeMillis();
    Cursor localCursor = this.zzbYw.query("writes", new String[] { "id", "path", "type", "part", "node" }, null, null, null, null, "id, part");
    ArrayList localArrayList = new ArrayList();
    String str;
    for (;;)
    {
      zzbmj localzzbmj;
      try
      {
        if (!localCursor.moveToNext()) {
          break label369;
        }
        l2 = localCursor.getLong(0);
        localzzbmj = new zzbmj(localCursor.getString(1));
        str = localCursor.getString(2);
        if (localCursor.isNull(3))
        {
          Object localObject1 = localCursor.getBlob(4);
          localObject1 = zzbpx.zzjg(new String((byte[])localObject1, zzavy));
          if (!"o".equals(str)) {
            break label285;
          }
          localObject1 = new zzbmx(l2, localzzbmj, zzbpf.zzar(localObject1), true);
          localArrayList.add(localObject1);
          continue;
        }
        localObject3 = new ArrayList();
      }
      catch (IOException localIOException)
      {
        throw new RuntimeException("Failed to load writes", localIOException);
      }
      finally
      {
        localCursor.close();
      }
      do
      {
        ((List)localObject3).add(localCursor.getBlob(4));
      } while ((localCursor.moveToNext()) && (localCursor.getLong(0) == l2));
      localCursor.moveToPrevious();
      localObject3 = zzS((List)localObject3);
      continue;
      label285:
      if (!"m".equals(str)) {
        break;
      }
      localObject3 = new zzbmx(l2, localzzbmj, zzbma.zzaA((Map)localObject3));
    }
    Object localObject3 = String.valueOf(str);
    if (((String)localObject3).length() != 0) {}
    for (localObject3 = "Got invalid write type: ".concat((String)localObject3);; localObject3 = new String("Got invalid write type: ")) {
      throw new IllegalStateException((String)localObject3);
    }
    label369:
    long l2 = System.currentTimeMillis();
    if (this.zzbYx.zzYT()) {
      this.zzbYx.zzi(String.format("Loaded %d writes in %dms", new Object[] { Integer.valueOf(localArrayList.size()), Long.valueOf(l2 - l1) }), new Object[0]);
    }
    localCursor.close();
    return localArrayList;
  }
  
  public long zzVf()
  {
    Object localObject1 = String.format("SELECT sum(length(%s) + length(%s)) FROM %s", new Object[] { "value", "path", "serverCache" });
    localObject1 = this.zzbYw.rawQuery((String)localObject1, null);
    try
    {
      if (((Cursor)localObject1).moveToFirst())
      {
        long l = ((Cursor)localObject1).getLong(0);
        return l;
      }
      throw new IllegalStateException("Couldn't read database result!");
    }
    finally
    {
      ((Cursor)localObject1).close();
    }
  }
  
  public List<zzbnq> zzVg()
  {
    long l1 = System.currentTimeMillis();
    Cursor localCursor = this.zzbYw.query("trackedQueries", new String[] { "id", "path", "queryParams", "lastUse", "complete", "active" }, null, null, null, null, "id");
    ArrayList localArrayList = new ArrayList();
    for (;;)
    {
      try
      {
        if (localCursor.moveToNext())
        {
          l2 = localCursor.getLong(0);
          localObject2 = new zzbmj(localCursor.getString(1));
          localObject3 = localCursor.getString(2);
        }
      }
      finally
      {
        try
        {
          Object localObject3 = zzbpx.zzjf((String)localObject3);
          Object localObject2 = zzboe.zzb((zzbmj)localObject2, (Map)localObject3);
          long l3 = localCursor.getLong(3);
          if (localCursor.getInt(4) == 0) {
            break label290;
          }
          bool1 = true;
          if (localCursor.getInt(5) == 0) {
            break label296;
          }
          bool2 = true;
          localArrayList.add(new zzbnq(l2, (zzboe)localObject2, l3, bool1, bool2));
        }
        catch (IOException localIOException)
        {
          throw new RuntimeException(localIOException);
        }
        localObject1 = finally;
        localCursor.close();
      }
      long l2 = System.currentTimeMillis();
      if (this.zzbYx.zzYT()) {
        this.zzbYx.zzi(String.format("Loaded %d tracked queries in %dms", new Object[] { Integer.valueOf(localIOException.size()), Long.valueOf(l2 - l1) }), new Object[0]);
      }
      localCursor.close();
      return localIOException;
      label290:
      boolean bool1 = false;
      continue;
      label296:
      boolean bool2 = false;
    }
  }
  
  public void zzVh()
  {
    zzVi();
    long l1 = System.currentTimeMillis();
    int i = this.zzbYw.delete("writes", null, null);
    long l2 = System.currentTimeMillis();
    if (this.zzbYx.zzYT()) {
      this.zzbYx.zzi(String.format("Deleted %d (all) write(s) in %dms", new Object[] { Integer.valueOf(i), Long.valueOf(l2 - l1) }), new Object[0]);
    }
  }
  
  public zzbpe zza(zzbmj paramzzbmj)
  {
    return zzb(paramzzbmj);
  }
  
  public void zza(long paramLong, Set<zzbos> paramSet)
  {
    zzVi();
    long l1 = System.currentTimeMillis();
    this.zzbYw.delete("trackedKeys", "id = ?", new String[] { String.valueOf(paramLong) });
    Iterator localIterator = paramSet.iterator();
    while (localIterator.hasNext())
    {
      zzbos localzzbos = (zzbos)localIterator.next();
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("id", Long.valueOf(paramLong));
      localContentValues.put("key", localzzbos.asString());
      this.zzbYw.insertWithOnConflict("trackedKeys", null, localContentValues, 5);
    }
    long l2 = System.currentTimeMillis();
    if (this.zzbYx.zzYT()) {
      this.zzbYx.zzi(String.format("Set %d tracked query keys for tracked query %d in %dms", new Object[] { Integer.valueOf(paramSet.size()), Long.valueOf(paramLong), Long.valueOf(l2 - l1) }), new Object[0]);
    }
  }
  
  public void zza(long paramLong, Set<zzbos> paramSet1, Set<zzbos> paramSet2)
  {
    zzVi();
    long l1 = System.currentTimeMillis();
    Iterator localIterator = paramSet2.iterator();
    zzbos localzzbos;
    while (localIterator.hasNext())
    {
      localzzbos = (zzbos)localIterator.next();
      this.zzbYw.delete("trackedKeys", "id = ? AND key = ?", new String[] { String.valueOf(paramLong), localzzbos.asString() });
    }
    localIterator = paramSet1.iterator();
    while (localIterator.hasNext())
    {
      localzzbos = (zzbos)localIterator.next();
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("id", Long.valueOf(paramLong));
      localContentValues.put("key", localzzbos.asString());
      this.zzbYw.insertWithOnConflict("trackedKeys", null, localContentValues, 5);
    }
    long l2 = System.currentTimeMillis();
    if (this.zzbYx.zzYT()) {
      this.zzbYx.zzi(String.format("Updated tracked query keys (%d added, %d removed) for tracked query id %d in %dms", new Object[] { Integer.valueOf(paramSet1.size()), Integer.valueOf(paramSet2.size()), Long.valueOf(paramLong), Long.valueOf(l2 - l1) }), new Object[0]);
    }
  }
  
  public void zza(zzbmj paramzzbmj, zzbma paramzzbma)
  {
    zzVi();
    long l1 = System.currentTimeMillis();
    paramzzbma = paramzzbma.iterator();
    int j = 0;
    Map.Entry localEntry;
    for (int i = 0; paramzzbma.hasNext(); i = zzc(paramzzbmj.zzh((zzbmj)localEntry.getKey()), (zzbpe)localEntry.getValue()) + i)
    {
      localEntry = (Map.Entry)paramzzbma.next();
      j += zza("serverCache", paramzzbmj.zzh((zzbmj)localEntry.getKey()));
    }
    long l2 = System.currentTimeMillis();
    if (this.zzbYx.zzYT()) {
      this.zzbYx.zzi(String.format("Persisted a total of %d rows and deleted %d rows for a merge at %s in %dms", new Object[] { Integer.valueOf(i), Integer.valueOf(j), paramzzbmj.toString(), Long.valueOf(l2 - l1) }), new Object[0]);
    }
  }
  
  public void zza(zzbmj paramzzbmj, zzbma paramzzbma, long paramLong)
  {
    zzVi();
    long l = System.currentTimeMillis();
    zza(paramzzbmj, paramLong, "m", zzad(paramzzbma.zzaZ(true)));
    paramLong = System.currentTimeMillis();
    if (this.zzbYx.zzYT()) {
      this.zzbYx.zzi(String.format("Persisted user merge in %dms", new Object[] { Long.valueOf(paramLong - l) }), new Object[0]);
    }
  }
  
  public void zza(zzbmj paramzzbmj, zzbnp paramzzbnp)
  {
    if (!paramzzbnp.zzXY()) {}
    long l1;
    long l2;
    int j;
    int i;
    do
    {
      return;
      zzVi();
      l1 = System.currentTimeMillis();
      Object localObject3 = zza(paramzzbmj, new String[] { "rowid", "path" });
      Object localObject2 = new zzbns(null);
      Object localObject1 = new zzbns(null);
      while (((Cursor)localObject3).moveToNext())
      {
        l2 = ((Cursor)localObject3).getLong(0);
        Object localObject4 = new zzbmj(((Cursor)localObject3).getString(1));
        Object localObject5;
        String str;
        if (!paramzzbmj.zzi((zzbmj)localObject4))
        {
          localObject5 = this.zzbYx;
          str = String.valueOf(paramzzbmj);
          localObject4 = String.valueOf(localObject4);
          ((zzbop)localObject5).warn(String.valueOf(str).length() + 67 + String.valueOf(localObject4).length() + "We are pruning at " + str + " but we have data stored higher up at " + (String)localObject4 + ". Ignoring.");
        }
        else
        {
          localObject5 = zzbmj.zza(paramzzbmj, (zzbmj)localObject4);
          if (paramzzbnp.zzw((zzbmj)localObject5))
          {
            localObject2 = ((zzbns)localObject2).zzb((zzbmj)localObject5, Long.valueOf(l2));
          }
          else if (paramzzbnp.zzx((zzbmj)localObject5))
          {
            localObject1 = ((zzbns)localObject1).zzb((zzbmj)localObject5, Long.valueOf(l2));
          }
          else
          {
            localObject5 = this.zzbYx;
            str = String.valueOf(paramzzbmj);
            localObject4 = String.valueOf(localObject4);
            ((zzbop)localObject5).warn(String.valueOf(str).length() + 88 + String.valueOf(localObject4).length() + "We are pruning at " + str + " and have data at " + (String)localObject4 + " that isn't marked for pruning or keeping. Ignoring.");
          }
        }
      }
      j = 0;
      i = 0;
      if (!((zzbns)localObject2).isEmpty())
      {
        localObject3 = new ArrayList();
        zza(paramzzbmj, zzbmj.zzXf(), (zzbns)localObject2, (zzbns)localObject1, paramzzbnp, (List)localObject3);
        paramzzbnp = ((zzbns)localObject2).values();
        localObject1 = String.valueOf(zzp(paramzzbnp));
        localObject1 = String.valueOf(localObject1).length() + 11 + "rowid IN (" + (String)localObject1 + ")";
        this.zzbYw.delete("serverCache", (String)localObject1, null);
        localObject1 = ((List)localObject3).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (zzbqd)((Iterator)localObject1).next();
          zzc(paramzzbmj.zzh((zzbmj)((zzbqd)localObject2).getFirst()), (zzbpe)((zzbqd)localObject2).zzZZ());
        }
        j = paramzzbnp.size();
        i = ((List)localObject3).size();
      }
      l2 = System.currentTimeMillis();
    } while (!this.zzbYx.zzYT());
    this.zzbYx.zzi(String.format("Pruned %d rows with %d nodes resaved in %dms", new Object[] { Integer.valueOf(j), Integer.valueOf(i), Long.valueOf(l2 - l1) }), new Object[0]);
  }
  
  public void zza(zzbmj paramzzbmj, zzbpe paramzzbpe)
  {
    zzVi();
    zza(paramzzbmj, paramzzbpe, false);
  }
  
  public void zza(zzbmj paramzzbmj, zzbpe paramzzbpe, long paramLong)
  {
    zzVi();
    long l = System.currentTimeMillis();
    zza(paramzzbmj, paramLong, "o", zzad(paramzzbpe.getValue(true)));
    paramLong = System.currentTimeMillis();
    if (this.zzbYx.zzYT()) {
      this.zzbYx.zzi(String.format("Persisted user overwrite in %dms", new Object[] { Long.valueOf(paramLong - l) }), new Object[0]);
    }
  }
  
  public void zza(zzbnq paramzzbnq)
  {
    zzVi();
    long l1 = System.currentTimeMillis();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("id", Long.valueOf(paramzzbnq.id));
    localContentValues.put("path", zzc(paramzzbnq.zzcfe.zzVc()));
    localContentValues.put("queryParams", paramzzbnq.zzcfe.zzYG().zzYE());
    localContentValues.put("lastUse", Long.valueOf(paramzzbnq.zzcff));
    localContentValues.put("complete", Boolean.valueOf(paramzzbnq.zzcfg));
    localContentValues.put("active", Boolean.valueOf(paramzzbnq.zzcfh));
    this.zzbYw.insertWithOnConflict("trackedQueries", null, localContentValues, 5);
    long l2 = System.currentTimeMillis();
    if (this.zzbYx.zzYT()) {
      this.zzbYx.zzi(String.format("Saved new tracked query in %dms", new Object[] { Long.valueOf(l2 - l1) }), new Object[0]);
    }
  }
  
  public void zzaA(long paramLong)
  {
    zzVi();
    long l1 = System.currentTimeMillis();
    int i = this.zzbYw.delete("writes", "id = ?", new String[] { String.valueOf(paramLong) });
    long l2 = System.currentTimeMillis();
    if (this.zzbYx.zzYT()) {
      this.zzbYx.zzi(String.format("Deleted %d write(s) with writeId %d in %dms", new Object[] { Integer.valueOf(i), Long.valueOf(paramLong), Long.valueOf(l2 - l1) }), new Object[0]);
    }
  }
  
  public void zzaB(long paramLong)
  {
    zzVi();
    String str = String.valueOf(paramLong);
    this.zzbYw.delete("trackedQueries", "id = ?", new String[] { str });
    this.zzbYw.delete("trackedKeys", "id = ?", new String[] { str });
  }
  
  public void zzaC(long paramLong)
  {
    zzVi();
    long l = System.currentTimeMillis();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("active", Boolean.valueOf(false));
    localContentValues.put("lastUse", Long.valueOf(paramLong));
    this.zzbYw.updateWithOnConflict("trackedQueries", localContentValues, "active = 1", new String[0], 5);
    paramLong = System.currentTimeMillis();
    if (this.zzbYx.zzYT()) {
      this.zzbYx.zzi(String.format("Reset active tracked queries in %dms", new Object[] { Long.valueOf(paramLong - l) }), new Object[0]);
    }
  }
  
  public Set<zzbos> zzaD(long paramLong)
  {
    return zzg(Collections.singleton(Long.valueOf(paramLong)));
  }
  
  public void zzb(zzbmj paramzzbmj, zzbpe paramzzbpe)
  {
    zzVi();
    zza(paramzzbmj, paramzzbpe, true);
  }
  
  public Set<zzbos> zzg(Set<Long> paramSet)
  {
    long l1 = System.currentTimeMillis();
    Object localObject1 = String.valueOf("id IN (");
    Object localObject2 = String.valueOf(zzp(paramSet));
    localObject1 = String.valueOf(localObject1).length() + 1 + String.valueOf(localObject2).length() + (String)localObject1 + (String)localObject2 + ")";
    localObject1 = this.zzbYw.query(true, "trackedKeys", new String[] { "key" }, (String)localObject1, null, null, null, null, null);
    localObject2 = new HashSet();
    try
    {
      while (((Cursor)localObject1).moveToNext()) {
        ((Set)localObject2).add(zzbos.zzjb(((Cursor)localObject1).getString(0)));
      }
      l2 = System.currentTimeMillis();
    }
    finally
    {
      ((Cursor)localObject1).close();
    }
    long l2;
    if (this.zzbYx.zzYT()) {
      this.zzbYx.zzi(String.format("Loaded %d tracked queries keys for tracked queries %s in %dms", new Object[] { Integer.valueOf(((Set)localObject2).size()), paramSet.toString(), Long.valueOf(l2 - l1) }), new Object[0]);
    }
    ((Cursor)localObject1).close();
    return (Set<zzbos>)localObject2;
  }
  
  private static class zza
    extends SQLiteOpenHelper
  {
    static
    {
      if (!zzbky.class.desiredAssertionStatus()) {}
      for (boolean bool = true;; bool = false)
      {
        $assertionsDisabled = bool;
        return;
      }
    }
    
    public zza(Context paramContext, String paramString)
    {
      super(paramString, null, 2);
    }
    
    private void zzc(SQLiteDatabase paramSQLiteDatabase, String paramString)
    {
      paramString = String.valueOf(paramString);
      if (paramString.length() != 0) {}
      for (paramString = "DROP TABLE IF EXISTS ".concat(paramString);; paramString = new String("DROP TABLE IF EXISTS "))
      {
        paramSQLiteDatabase.execSQL(paramString);
        return;
      }
    }
    
    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("CREATE TABLE serverCache (path TEXT PRIMARY KEY, value BLOB);");
      paramSQLiteDatabase.execSQL("CREATE TABLE writes (id INTEGER, path TEXT, type TEXT, part INTEGER, node BLOB, UNIQUE (id, part));");
      paramSQLiteDatabase.execSQL("CREATE TABLE trackedQueries (id INTEGER PRIMARY KEY, path TEXT, queryParams TEXT, lastUse INTEGER, complete INTEGER, active INTEGER);");
      paramSQLiteDatabase.execSQL("CREATE TABLE trackedKeys (id INTEGER, key TEXT);");
    }
    
    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      assert (paramInt2 == 2) : "Why is onUpgrade() called with a different version?";
      if (paramInt1 <= 1)
      {
        zzc(paramSQLiteDatabase, "serverCache");
        paramSQLiteDatabase.execSQL("CREATE TABLE serverCache (path TEXT PRIMARY KEY, value BLOB);");
        zzc(paramSQLiteDatabase, "complete");
        paramSQLiteDatabase.execSQL("CREATE TABLE trackedKeys (id INTEGER, key TEXT);");
        paramSQLiteDatabase.execSQL("CREATE TABLE trackedQueries (id INTEGER PRIMARY KEY, path TEXT, queryParams TEXT, lastUse INTEGER, complete INTEGER, active INTEGER);");
        return;
      }
      throw new AssertionError(40 + "We don't handle upgrading to " + paramInt2);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbky.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */