package com.google.firebase.storage;

import android.support.annotation.NonNull;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class zzc
{
  private static final zzc zzclg = new zzc();
  private final Object mSyncObject = new Object();
  private final Map<String, WeakReference<StorageTask>> zzclh = new HashMap();
  
  static zzc zzaaV()
  {
    return zzclg;
  }
  
  public List<UploadTask> zza(@NonNull StorageReference paramStorageReference)
  {
    ArrayList localArrayList;
    synchronized (this.mSyncObject)
    {
      localArrayList = new ArrayList();
      paramStorageReference = paramStorageReference.toString();
      Iterator localIterator = this.zzclh.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Object localObject2 = (Map.Entry)localIterator.next();
        if (((String)((Map.Entry)localObject2).getKey()).startsWith(paramStorageReference))
        {
          localObject2 = (StorageTask)((WeakReference)((Map.Entry)localObject2).getValue()).get();
          if ((localObject2 instanceof UploadTask)) {
            localArrayList.add((UploadTask)localObject2);
          }
        }
      }
    }
    paramStorageReference = Collections.unmodifiableList(localArrayList);
    return paramStorageReference;
  }
  
  public List<FileDownloadTask> zzb(@NonNull StorageReference paramStorageReference)
  {
    ArrayList localArrayList;
    synchronized (this.mSyncObject)
    {
      localArrayList = new ArrayList();
      paramStorageReference = paramStorageReference.toString();
      Iterator localIterator = this.zzclh.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Object localObject2 = (Map.Entry)localIterator.next();
        if (((String)((Map.Entry)localObject2).getKey()).startsWith(paramStorageReference))
        {
          localObject2 = (StorageTask)((WeakReference)((Map.Entry)localObject2).getValue()).get();
          if ((localObject2 instanceof FileDownloadTask)) {
            localArrayList.add((FileDownloadTask)localObject2);
          }
        }
      }
    }
    paramStorageReference = Collections.unmodifiableList(localArrayList);
    return paramStorageReference;
  }
  
  public void zzb(StorageTask paramStorageTask)
  {
    synchronized (this.mSyncObject)
    {
      this.zzclh.put(paramStorageTask.getStorage().toString(), new WeakReference(paramStorageTask));
      return;
    }
  }
  
  public void zzc(StorageTask paramStorageTask)
  {
    Object localObject1;
    synchronized (this.mSyncObject)
    {
      String str = paramStorageTask.getStorage().toString();
      localObject1 = (WeakReference)this.zzclh.get(str);
      if (localObject1 != null)
      {
        localObject1 = (StorageTask)((WeakReference)localObject1).get();
        break label66;
        this.zzclh.remove(str);
        label58:
        return;
      }
    }
    label66:
    while (localObject1 != null)
    {
      if (localObject1 != paramStorageTask) {
        break label58;
      }
      break;
      localObject1 = null;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\storage\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */