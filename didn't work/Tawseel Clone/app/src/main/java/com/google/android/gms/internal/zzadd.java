package com.google.android.gms.internal;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class zzadd
{
  static AppMeasurement zzaR(Context paramContext)
  {
    try
    {
      paramContext = AppMeasurement.getInstance(paramContext);
      return paramContext;
    }
    catch (NoClassDefFoundError paramContext) {}
    return null;
  }
  
  public static List<zzadb> zzaS(Context paramContext)
  {
    paramContext = zzaR(paramContext);
    if (paramContext == null) {
      if (Log.isLoggable("FRCAnalytics", 3)) {
        Log.d("FRCAnalytics", "Unable to get user properties: analytics library is missing.");
      }
    }
    ArrayList localArrayList;
    for (;;)
    {
      return null;
      try
      {
        paramContext = paramContext.zzaE(false);
        if (paramContext != null)
        {
          localArrayList = new ArrayList();
          paramContext = paramContext.entrySet().iterator();
          while (paramContext.hasNext())
          {
            Map.Entry localEntry = (Map.Entry)paramContext.next();
            if (localEntry.getValue() != null) {
              localArrayList.add(new zzadb((String)localEntry.getKey(), localEntry.getValue().toString()));
            }
          }
        }
      }
      catch (NullPointerException paramContext)
      {
        for (;;)
        {
          if (Log.isLoggable("FRCAnalytics", 3)) {
            Log.d("FRCAnalytics", "Unable to get user properties.", paramContext);
          }
          paramContext = null;
        }
      }
    }
    return localArrayList;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzadd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */