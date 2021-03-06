package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;

@TargetApi(11)
public final class zzaay
  extends Fragment
  implements zzaax
{
  private static WeakHashMap<Activity, WeakReference<zzaay>> zzaBt = new WeakHashMap();
  private int zzJh = 0;
  private Map<String, zzaaw> zzaBu = new ArrayMap();
  private Bundle zzaBv;
  
  private void zzb(final String paramString, @NonNull final zzaaw paramzzaaw)
  {
    if (this.zzJh > 0) {
      new Handler(Looper.getMainLooper()).post(new Runnable()
      {
        public void run()
        {
          zzaaw localzzaaw;
          if (zzaay.zza(zzaay.this) >= 1)
          {
            localzzaaw = paramzzaaw;
            if (zzaay.zzb(zzaay.this) == null) {
              break label101;
            }
          }
          label101:
          for (Bundle localBundle = zzaay.zzb(zzaay.this).getBundle(paramString);; localBundle = null)
          {
            localzzaaw.onCreate(localBundle);
            if (zzaay.zza(zzaay.this) >= 2) {
              paramzzaaw.onStart();
            }
            if (zzaay.zza(zzaay.this) >= 3) {
              paramzzaaw.onStop();
            }
            if (zzaay.zza(zzaay.this) >= 4) {
              paramzzaaw.onDestroy();
            }
            return;
          }
        }
      });
    }
  }
  
  public static zzaay zzt(Activity paramActivity)
  {
    Object localObject = (WeakReference)zzaBt.get(paramActivity);
    if (localObject != null)
    {
      localObject = (zzaay)((WeakReference)localObject).get();
      if (localObject != null) {
        return (zzaay)localObject;
      }
    }
    try
    {
      zzaay localzzaay = (zzaay)paramActivity.getFragmentManager().findFragmentByTag("LifecycleFragmentImpl");
      if (localzzaay != null)
      {
        localObject = localzzaay;
        if (!localzzaay.isRemoving()) {}
      }
      else
      {
        localObject = new zzaay();
        paramActivity.getFragmentManager().beginTransaction().add((Fragment)localObject, "LifecycleFragmentImpl").commitAllowingStateLoss();
      }
      zzaBt.put(paramActivity, new WeakReference(localObject));
      return (zzaay)localObject;
    }
    catch (ClassCastException paramActivity)
    {
      throw new IllegalStateException("Fragment with tag LifecycleFragmentImpl is not a LifecycleFragmentImpl", paramActivity);
    }
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    super.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    Iterator localIterator = this.zzaBu.values().iterator();
    while (localIterator.hasNext()) {
      ((zzaaw)localIterator.next()).dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    }
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    Iterator localIterator = this.zzaBu.values().iterator();
    while (localIterator.hasNext()) {
      ((zzaaw)localIterator.next()).onActivityResult(paramInt1, paramInt2, paramIntent);
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.zzJh = 1;
    this.zzaBv = paramBundle;
    Iterator localIterator = this.zzaBu.entrySet().iterator();
    if (localIterator.hasNext())
    {
      Object localObject = (Map.Entry)localIterator.next();
      zzaaw localzzaaw = (zzaaw)((Map.Entry)localObject).getValue();
      if (paramBundle != null) {}
      for (localObject = paramBundle.getBundle((String)((Map.Entry)localObject).getKey());; localObject = null)
      {
        localzzaaw.onCreate((Bundle)localObject);
        break;
      }
    }
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    this.zzJh = 4;
    Iterator localIterator = this.zzaBu.values().iterator();
    while (localIterator.hasNext()) {
      ((zzaaw)localIterator.next()).onDestroy();
    }
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if (paramBundle == null) {}
    for (;;)
    {
      return;
      Iterator localIterator = this.zzaBu.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        Bundle localBundle = new Bundle();
        ((zzaaw)localEntry.getValue()).onSaveInstanceState(localBundle);
        paramBundle.putBundle((String)localEntry.getKey(), localBundle);
      }
    }
  }
  
  public void onStart()
  {
    super.onStart();
    this.zzJh = 2;
    Iterator localIterator = this.zzaBu.values().iterator();
    while (localIterator.hasNext()) {
      ((zzaaw)localIterator.next()).onStart();
    }
  }
  
  public void onStop()
  {
    super.onStop();
    this.zzJh = 3;
    Iterator localIterator = this.zzaBu.values().iterator();
    while (localIterator.hasNext()) {
      ((zzaaw)localIterator.next()).onStop();
    }
  }
  
  public <T extends zzaaw> T zza(String paramString, Class<T> paramClass)
  {
    return (zzaaw)paramClass.cast(this.zzaBu.get(paramString));
  }
  
  public void zza(String paramString, @NonNull zzaaw paramzzaaw)
  {
    if (!this.zzaBu.containsKey(paramString))
    {
      this.zzaBu.put(paramString, paramzzaaw);
      zzb(paramString, paramzzaaw);
      return;
    }
    throw new IllegalArgumentException(String.valueOf(paramString).length() + 59 + "LifecycleCallback with tag " + paramString + " already added to this fragment.");
  }
  
  public Activity zzwo()
  {
    return getActivity();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzaay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */