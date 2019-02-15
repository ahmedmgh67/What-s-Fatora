package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class zzboc
{
  private final zzbop zzbYx;
  private final zzbmg zzcbK;
  
  public zzboc(zzbmc paramzzbmc)
  {
    this.zzcbK = paramzzbmc.zzWQ();
    this.zzbYx = paramzzbmc.zziW("EventRaiser");
  }
  
  public void zzY(final List<? extends zzboa> paramList)
  {
    if (this.zzbYx.zzYT())
    {
      zzbop localzzbop = this.zzbYx;
      int i = paramList.size();
      localzzbop.zzi(28 + "Raising " + i + " event(s)", new Object[0]);
    }
    paramList = new ArrayList(paramList);
    this.zzcbK.zzq(new Runnable()
    {
      public void run()
      {
        Iterator localIterator = paramList.iterator();
        if (localIterator.hasNext())
        {
          zzboa localzzboa = (zzboa)localIterator.next();
          zzbop localzzbop;
          if (zzboc.zza(zzboc.this).zzYT())
          {
            localzzbop = zzboc.zza(zzboc.this);
            str = String.valueOf(localzzboa.toString());
            if (str.length() == 0) {
              break label92;
            }
          }
          label92:
          for (String str = "Raising ".concat(str);; str = new String("Raising "))
          {
            localzzbop.zzi(str, new Object[0]);
            localzzboa.zzYj();
            break;
          }
        }
      }
    });
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzboc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */