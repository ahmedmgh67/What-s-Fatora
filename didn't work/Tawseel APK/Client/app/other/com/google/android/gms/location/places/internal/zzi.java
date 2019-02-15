package com.google.android.gms.location.places.internal;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceReport;
import com.google.android.gms.location.places.zzf;
import com.google.android.gms.location.places.zzm;

public abstract interface zzi
  extends IInterface
{
  public abstract void zza(PlaceFilter paramPlaceFilter, zzz paramzzz, zzl paramzzl)
    throws RemoteException;
  
  public abstract void zza(PlaceReport paramPlaceReport, zzz paramzzz, zzl paramzzl)
    throws RemoteException;
  
  public abstract void zza(zzz paramzzz, PendingIntent paramPendingIntent, zzl paramzzl)
    throws RemoteException;
  
  public abstract void zza(zzf paramzzf, zzz paramzzz, PendingIntent paramPendingIntent, zzl paramzzl)
    throws RemoteException;
  
  public abstract void zza(zzm paramzzm, zzz paramzzz, PendingIntent paramPendingIntent, zzl paramzzl)
    throws RemoteException;
  
  public abstract void zzb(zzz paramzzz, PendingIntent paramPendingIntent, zzl paramzzl)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzi
  {
    public static zzi zzdl(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
      if ((localIInterface != null) && ((localIInterface instanceof zzi))) {
        return (zzi)localIInterface;
      }
      return new zza(paramIBinder);
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      Object localObject1;
      label134:
      PendingIntent localPendingIntent;
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
        return true;
      case 2: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = (zzm)zzm.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label184;
          }
          localObject2 = (zzz)zzz.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label190;
          }
        }
        for (localPendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; localPendingIntent = null)
        {
          zza((zzm)localObject1, (zzz)localObject2, localPendingIntent, zzl.zza.zzdo(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
          localObject1 = null;
          break;
          localObject2 = null;
          break label134;
        }
      case 3: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = (zzz)zzz.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label271;
          }
        }
        for (localObject2 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; localObject2 = null)
        {
          zza((zzz)localObject1, (PendingIntent)localObject2, zzl.zza.zzdo(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
          localObject1 = null;
          break;
        }
      case 4: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = (zzf)zzf.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label375;
          }
          localObject2 = (zzz)zzz.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label381;
          }
        }
        for (localPendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; localPendingIntent = null)
        {
          zza((zzf)localObject1, (zzz)localObject2, localPendingIntent, zzl.zza.zzdo(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
          localObject1 = null;
          break;
          localObject2 = null;
          break label325;
        }
      case 5: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = (zzz)zzz.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label462;
          }
        }
        for (localObject2 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; localObject2 = null)
        {
          zzb((zzz)localObject1, (PendingIntent)localObject2, zzl.zza.zzdo(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
          localObject1 = null;
          break;
        }
      case 6: 
        label184:
        label190:
        label271:
        label325:
        label375:
        label381:
        label462:
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = (PlaceFilter)PlaceFilter.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label543;
          }
        }
        label543:
        for (localObject2 = (zzz)zzz.CREATOR.createFromParcel(paramParcel1);; localObject2 = null)
        {
          zza((PlaceFilter)localObject1, (zzz)localObject2, zzl.zza.zzdo(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
          localObject1 = null;
          break;
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
      if (paramParcel1.readInt() != 0)
      {
        localObject1 = (PlaceReport)PlaceReport.CREATOR.createFromParcel(paramParcel1);
        if (paramParcel1.readInt() == 0) {
          break label624;
        }
      }
      label624:
      for (Object localObject2 = (zzz)zzz.CREATOR.createFromParcel(paramParcel1);; localObject2 = null)
      {
        zza((PlaceReport)localObject1, (zzz)localObject2, zzl.zza.zzdo(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
        localObject1 = null;
        break;
      }
    }
    
    private static class zza
      implements zzi
    {
      private IBinder zzrp;
      
      zza(IBinder paramIBinder)
      {
        this.zzrp = paramIBinder;
      }
      
      public IBinder asBinder()
      {
        return this.zzrp;
      }
      
      public void zza(PlaceFilter paramPlaceFilter, zzz paramzzz, zzl paramzzl)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
            if (paramPlaceFilter != null)
            {
              localParcel1.writeInt(1);
              paramPlaceFilter.writeToParcel(localParcel1, 0);
              if (paramzzz != null)
              {
                localParcel1.writeInt(1);
                paramzzz.writeToParcel(localParcel1, 0);
                if (paramzzl == null) {
                  break label132;
                }
                paramPlaceFilter = paramzzl.asBinder();
                localParcel1.writeStrongBinder(paramPlaceFilter);
                this.zzrp.transact(6, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          continue;
          label132:
          paramPlaceFilter = null;
        }
      }
      
      public void zza(PlaceReport paramPlaceReport, zzz paramzzz, zzl paramzzl)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
            if (paramPlaceReport != null)
            {
              localParcel1.writeInt(1);
              paramPlaceReport.writeToParcel(localParcel1, 0);
              if (paramzzz != null)
              {
                localParcel1.writeInt(1);
                paramzzz.writeToParcel(localParcel1, 0);
                if (paramzzl == null) {
                  break label132;
                }
                paramPlaceReport = paramzzl.asBinder();
                localParcel1.writeStrongBinder(paramPlaceReport);
                this.zzrp.transact(7, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          continue;
          label132:
          paramPlaceReport = null;
        }
      }
      
      public void zza(zzz paramzzz, PendingIntent paramPendingIntent, zzl paramzzl)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
            if (paramzzz != null)
            {
              localParcel1.writeInt(1);
              paramzzz.writeToParcel(localParcel1, 0);
              if (paramPendingIntent != null)
              {
                localParcel1.writeInt(1);
                paramPendingIntent.writeToParcel(localParcel1, 0);
                if (paramzzl == null) {
                  break label131;
                }
                paramzzz = paramzzl.asBinder();
                localParcel1.writeStrongBinder(paramzzz);
                this.zzrp.transact(3, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          continue;
          label131:
          paramzzz = null;
        }
      }
      
      public void zza(zzf paramzzf, zzz paramzzz, PendingIntent paramPendingIntent, zzl paramzzl)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
            if (paramzzf != null)
            {
              localParcel1.writeInt(1);
              paramzzf.writeToParcel(localParcel1, 0);
              if (paramzzz != null)
              {
                localParcel1.writeInt(1);
                paramzzz.writeToParcel(localParcel1, 0);
                if (paramPendingIntent == null) {
                  break label150;
                }
                localParcel1.writeInt(1);
                paramPendingIntent.writeToParcel(localParcel1, 0);
                if (paramzzl == null) {
                  break label159;
                }
                paramzzf = paramzzl.asBinder();
                localParcel1.writeStrongBinder(paramzzf);
                this.zzrp.transact(4, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          continue;
          label150:
          localParcel1.writeInt(0);
          continue;
          label159:
          paramzzf = null;
        }
      }
      
      public void zza(zzm paramzzm, zzz paramzzz, PendingIntent paramPendingIntent, zzl paramzzl)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
            if (paramzzm != null)
            {
              localParcel1.writeInt(1);
              paramzzm.writeToParcel(localParcel1, 0);
              if (paramzzz != null)
              {
                localParcel1.writeInt(1);
                paramzzz.writeToParcel(localParcel1, 0);
                if (paramPendingIntent == null) {
                  break label150;
                }
                localParcel1.writeInt(1);
                paramPendingIntent.writeToParcel(localParcel1, 0);
                if (paramzzl == null) {
                  break label159;
                }
                paramzzm = paramzzl.asBinder();
                localParcel1.writeStrongBinder(paramzzm);
                this.zzrp.transact(2, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          continue;
          label150:
          localParcel1.writeInt(0);
          continue;
          label159:
          paramzzm = null;
        }
      }
      
      public void zzb(zzz paramzzz, PendingIntent paramPendingIntent, zzl paramzzl)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
            if (paramzzz != null)
            {
              localParcel1.writeInt(1);
              paramzzz.writeToParcel(localParcel1, 0);
              if (paramPendingIntent != null)
              {
                localParcel1.writeInt(1);
                paramPendingIntent.writeToParcel(localParcel1, 0);
                if (paramzzl == null) {
                  break label131;
                }
                paramzzz = paramzzl.asBinder();
                localParcel1.writeStrongBinder(paramzzz);
                this.zzrp.transact(5, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          continue;
          label131:
          paramzzz = null;
        }
      }
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\internal\zzi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */