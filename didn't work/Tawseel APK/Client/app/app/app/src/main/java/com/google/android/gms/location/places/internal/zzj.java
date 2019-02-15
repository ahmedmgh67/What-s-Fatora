package com.google.android.gms.location.places.internal;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.internal.zzash;
import com.google.android.gms.internal.zzash.zza;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceReport;
import com.google.android.gms.location.places.zzf;
import com.google.android.gms.location.places.zzm;
import com.google.android.gms.location.places.zzp;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.List;

public abstract interface zzj
  extends IInterface
{
  public abstract void zza(AddPlaceRequest paramAddPlaceRequest, zzz paramzzz, zzl paramzzl)
    throws RemoteException;
  
  public abstract void zza(PlaceReport paramPlaceReport, zzz paramzzz)
    throws RemoteException;
  
  public abstract void zza(zzz paramzzz, PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract void zza(zzz paramzzz, zzash paramzzash)
    throws RemoteException;
  
  public abstract void zza(zzz paramzzz, zzl paramzzl)
    throws RemoteException;
  
  public abstract void zza(zzf paramzzf, zzz paramzzz, PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract void zza(zzm paramzzm, zzz paramzzz, PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract void zza(zzp paramzzp, LatLngBounds paramLatLngBounds, List<String> paramList, zzz paramzzz, zzl paramzzl)
    throws RemoteException;
  
  public abstract void zza(LatLng paramLatLng, PlaceFilter paramPlaceFilter, zzz paramzzz, zzl paramzzl)
    throws RemoteException;
  
  public abstract void zza(LatLng paramLatLng, zzz paramzzz, zzl paramzzl)
    throws RemoteException;
  
  public abstract void zza(LatLngBounds paramLatLngBounds, int paramInt, String paramString, PlaceFilter paramPlaceFilter, zzz paramzzz, zzl paramzzl)
    throws RemoteException;
  
  public abstract void zza(String paramString, int paramInt1, int paramInt2, int paramInt3, zzz paramzzz, zzk paramzzk)
    throws RemoteException;
  
  public abstract void zza(String paramString, int paramInt, zzz paramzzz, zzl paramzzl)
    throws RemoteException;
  
  public abstract void zza(String paramString, zzz paramzzz, zzk paramzzk)
    throws RemoteException;
  
  public abstract void zza(String paramString, zzz paramzzz, zzl paramzzl)
    throws RemoteException;
  
  public abstract void zza(String paramString, LatLngBounds paramLatLngBounds, AutocompleteFilter paramAutocompleteFilter, zzz paramzzz, zzl paramzzl)
    throws RemoteException;
  
  public abstract void zza(String paramString1, String paramString2, zzz paramzzz, zzash paramzzash)
    throws RemoteException;
  
  public abstract void zza(String paramString1, String paramString2, String paramString3, zzz paramzzz, zzash paramzzash)
    throws RemoteException;
  
  public abstract void zza(List<String> paramList, zzz paramzzz, zzl paramzzl)
    throws RemoteException;
  
  public abstract void zzb(PlaceFilter paramPlaceFilter, zzz paramzzz, zzl paramzzl)
    throws RemoteException;
  
  public abstract void zzb(zzz paramzzz, PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract void zzb(zzz paramzzz, zzash paramzzash)
    throws RemoteException;
  
  public abstract void zzb(List<String> paramList, zzz paramzzz, zzl paramzzl)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzj
  {
    public static zzj zzdm(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
      if ((localIInterface != null) && ((localIInterface instanceof zzj))) {
        return (zzj)localIInterface;
      }
      return new zza(paramIBinder);
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      Object localObject1 = null;
      Object localObject3 = null;
      Object localObject4;
      Object localObject2;
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.android.gms.location.places.internal.IGooglePlacesService");
        return true;
      case 2: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = (LatLngBounds)LatLngBounds.CREATOR.createFromParcel(paramParcel1);
          paramInt1 = paramParcel1.readInt();
          localObject4 = paramParcel1.readString();
          if (paramParcel1.readInt() == 0) {
            break label338;
          }
        }
        for (localObject2 = (PlaceFilter)PlaceFilter.CREATOR.createFromParcel(paramParcel1);; localObject2 = null)
        {
          if (paramParcel1.readInt() != 0) {
            localObject3 = (zzz)zzz.CREATOR.createFromParcel(paramParcel1);
          }
          zza((LatLngBounds)localObject1, paramInt1, (String)localObject4, (PlaceFilter)localObject2, (zzz)localObject3, zzl.zza.zzdo(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
          localObject1 = null;
          break;
        }
      case 4: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = (LatLng)LatLng.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label442;
          }
          localObject2 = (PlaceFilter)PlaceFilter.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label448;
          }
        }
        for (localObject3 = (zzz)zzz.CREATOR.createFromParcel(paramParcel1);; localObject3 = null)
        {
          zza((LatLng)localObject1, (PlaceFilter)localObject2, (zzz)localObject3, zzl.zza.zzdo(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
          localObject1 = null;
          break;
          localObject2 = null;
          break label392;
        }
      case 5: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = (PlaceFilter)PlaceFilter.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label529;
          }
        }
        for (localObject2 = (zzz)zzz.CREATOR.createFromParcel(paramParcel1);; localObject2 = null)
        {
          zzb((PlaceFilter)localObject1, (zzz)localObject2, zzl.zza.zzdo(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
          localObject1 = null;
          break;
        }
      case 6: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        localObject2 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0) {}
        for (localObject1 = (zzz)zzz.CREATOR.createFromParcel(paramParcel1);; localObject1 = null)
        {
          zza((String)localObject2, (zzz)localObject1, zzl.zza.zzdo(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        }
      case 7: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        localObject2 = paramParcel1.createStringArrayList();
        if (paramParcel1.readInt() != 0) {}
        for (localObject1 = (zzz)zzz.CREATOR.createFromParcel(paramParcel1);; localObject1 = null)
        {
          zza((List)localObject2, (zzz)localObject1, zzl.zza.zzdo(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        }
      case 17: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        localObject2 = paramParcel1.createStringArrayList();
        if (paramParcel1.readInt() != 0) {}
        for (localObject1 = (zzz)zzz.CREATOR.createFromParcel(paramParcel1);; localObject1 = null)
        {
          zzb((List)localObject2, (zzz)localObject1, zzl.zza.zzdo(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        }
      case 8: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = (zzp)zzp.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label821;
          }
          localObject2 = (LatLngBounds)LatLngBounds.CREATOR.createFromParcel(paramParcel1);
          localObject4 = paramParcel1.createStringArrayList();
          if (paramParcel1.readInt() == 0) {
            break label827;
          }
        }
        for (localObject3 = (zzz)zzz.CREATOR.createFromParcel(paramParcel1);; localObject3 = null)
        {
          zza((zzp)localObject1, (LatLngBounds)localObject2, (List)localObject4, (zzz)localObject3, zzl.zza.zzdo(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
          localObject1 = null;
          break;
          localObject2 = null;
          break label763;
        }
      case 9: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = (zzm)zzm.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label922;
          }
          localObject2 = (zzz)zzz.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label928;
          }
        }
        for (paramParcel1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          zza((zzm)localObject1, (zzz)localObject2, paramParcel1);
          paramParcel2.writeNoException();
          return true;
          localObject1 = null;
          break;
          localObject2 = null;
          break label881;
        }
      case 10: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = (zzz)zzz.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label999;
          }
        }
        for (paramParcel1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          zza((zzz)localObject1, paramParcel1);
          paramParcel2.writeNoException();
          return true;
          localObject1 = null;
          break;
        }
      case 11: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = (zzf)zzf.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label1093;
          }
          localObject2 = (zzz)zzz.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label1099;
          }
        }
        for (paramParcel1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          zza((zzf)localObject1, (zzz)localObject2, paramParcel1);
          paramParcel2.writeNoException();
          return true;
          localObject1 = null;
          break;
          localObject2 = null;
          break label1052;
        }
      case 12: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = (zzz)zzz.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label1170;
          }
        }
        for (paramParcel1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          zzb((zzz)localObject1, paramParcel1);
          paramParcel2.writeNoException();
          return true;
          localObject1 = null;
          break;
        }
      case 13: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        localObject4 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = (LatLngBounds)LatLngBounds.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label1281;
          }
          localObject2 = (AutocompleteFilter)AutocompleteFilter.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label1287;
          }
        }
        for (localObject3 = (zzz)zzz.CREATOR.createFromParcel(paramParcel1);; localObject3 = null)
        {
          zza((String)localObject4, (LatLngBounds)localObject1, (AutocompleteFilter)localObject2, (zzz)localObject3, zzl.zza.zzdo(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
          localObject1 = null;
          break;
          localObject2 = null;
          break label1229;
        }
      case 14: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = (AddPlaceRequest)AddPlaceRequest.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label1368;
          }
        }
        for (localObject2 = (zzz)zzz.CREATOR.createFromParcel(paramParcel1);; localObject2 = null)
        {
          zza((AddPlaceRequest)localObject1, (zzz)localObject2, zzl.zza.zzdo(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
          localObject1 = null;
          break;
        }
      case 15: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = (PlaceReport)PlaceReport.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label1440;
          }
        }
        for (paramParcel1 = (zzz)zzz.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          zza((PlaceReport)localObject1, paramParcel1);
          paramParcel2.writeNoException();
          return true;
          localObject1 = null;
          break;
        }
      case 16: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        localObject2 = paramParcel1.readString();
        localObject3 = paramParcel1.readString();
        localObject4 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0) {}
        for (localObject1 = (zzz)zzz.CREATOR.createFromParcel(paramParcel1);; localObject1 = null)
        {
          zza((String)localObject2, (String)localObject3, (String)localObject4, (zzz)localObject1, zzash.zza.zzdq(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        }
      case 21: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        localObject2 = paramParcel1.readString();
        localObject3 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0) {}
        for (localObject1 = (zzz)zzz.CREATOR.createFromParcel(paramParcel1);; localObject1 = null)
        {
          zza((String)localObject2, (String)localObject3, (zzz)localObject1, zzash.zza.zzdq(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        }
      case 18: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        localObject2 = paramParcel1.readString();
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0) {}
        for (localObject1 = (zzz)zzz.CREATOR.createFromParcel(paramParcel1);; localObject1 = null)
        {
          zza((String)localObject2, paramInt1, (zzz)localObject1, zzl.zza.zzdo(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        }
      case 19: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        localObject2 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0) {}
        for (localObject1 = (zzz)zzz.CREATOR.createFromParcel(paramParcel1);; localObject1 = null)
        {
          zza((String)localObject2, (zzz)localObject1, zzk.zza.zzdn(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        }
      case 20: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        localObject2 = paramParcel1.readString();
        paramInt1 = paramParcel1.readInt();
        paramInt2 = paramParcel1.readInt();
        int i = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0) {
          localObject1 = (zzz)zzz.CREATOR.createFromParcel(paramParcel1);
        }
        zza((String)localObject2, paramInt1, paramInt2, i, (zzz)localObject1, zzk.zza.zzdn(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 22: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = (LatLng)LatLng.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label1866;
          }
        }
        for (localObject2 = (zzz)zzz.CREATOR.createFromParcel(paramParcel1);; localObject2 = null)
        {
          zza((LatLng)localObject1, (zzz)localObject2, zzl.zza.zzdo(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
          localObject1 = null;
          break;
        }
      case 23: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        if (paramParcel1.readInt() != 0) {}
        for (localObject1 = (zzz)zzz.CREATOR.createFromParcel(paramParcel1);; localObject1 = null)
        {
          zza((zzz)localObject1, zzl.zza.zzdo(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        }
      case 24: 
        label338:
        label392:
        label442:
        label448:
        label529:
        label763:
        label821:
        label827:
        label881:
        label922:
        label928:
        label999:
        label1052:
        label1093:
        label1099:
        label1170:
        label1229:
        label1281:
        label1287:
        label1368:
        label1440:
        label1866:
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        if (paramParcel1.readInt() != 0) {}
        for (localObject1 = (zzz)zzz.CREATOR.createFromParcel(paramParcel1);; localObject1 = null)
        {
          zza((zzz)localObject1, zzash.zza.zzdq(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
      if (paramParcel1.readInt() != 0) {}
      for (localObject1 = (zzz)zzz.CREATOR.createFromParcel(paramParcel1);; localObject1 = null)
      {
        zzb((zzz)localObject1, zzash.zza.zzdq(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      }
    }
    
    private static class zza
      implements zzj
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
      
      public void zza(AddPlaceRequest paramAddPlaceRequest, zzz paramzzz, zzl paramzzl)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramAddPlaceRequest != null)
            {
              localParcel1.writeInt(1);
              paramAddPlaceRequest.writeToParcel(localParcel1, 0);
              if (paramzzz != null)
              {
                localParcel1.writeInt(1);
                paramzzz.writeToParcel(localParcel1, 0);
                if (paramzzl == null) {
                  break label132;
                }
                paramAddPlaceRequest = paramzzl.asBinder();
                localParcel1.writeStrongBinder(paramAddPlaceRequest);
                this.zzrp.transact(14, localParcel1, localParcel2, 0);
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
          paramAddPlaceRequest = null;
        }
      }
      
      public void zza(PlaceReport paramPlaceReport, zzz paramzzz)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramPlaceReport != null)
            {
              localParcel1.writeInt(1);
              paramPlaceReport.writeToParcel(localParcel1, 0);
              if (paramzzz != null)
              {
                localParcel1.writeInt(1);
                paramzzz.writeToParcel(localParcel1, 0);
                this.zzrp.transact(15, localParcel1, localParcel2, 0);
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
        }
      }
      
      public void zza(zzz paramzzz, PendingIntent paramPendingIntent)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramzzz != null)
            {
              localParcel1.writeInt(1);
              paramzzz.writeToParcel(localParcel1, 0);
              if (paramPendingIntent != null)
              {
                localParcel1.writeInt(1);
                paramPendingIntent.writeToParcel(localParcel1, 0);
                this.zzrp.transact(10, localParcel1, localParcel2, 0);
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
        }
      }
      
      public void zza(zzz paramzzz, zzash paramzzash)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramzzz != null)
            {
              localParcel1.writeInt(1);
              paramzzz.writeToParcel(localParcel1, 0);
              if (paramzzash != null)
              {
                paramzzz = paramzzash.asBinder();
                localParcel1.writeStrongBinder(paramzzz);
                this.zzrp.transact(24, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramzzz = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(zzz paramzzz, zzl paramzzl)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramzzz != null)
            {
              localParcel1.writeInt(1);
              paramzzz.writeToParcel(localParcel1, 0);
              if (paramzzl != null)
              {
                paramzzz = paramzzl.asBinder();
                localParcel1.writeStrongBinder(paramzzz);
                this.zzrp.transact(23, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramzzz = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(zzf paramzzf, zzz paramzzz, PendingIntent paramPendingIntent)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramzzf != null)
            {
              localParcel1.writeInt(1);
              paramzzf.writeToParcel(localParcel1, 0);
              if (paramzzz != null)
              {
                localParcel1.writeInt(1);
                paramzzz.writeToParcel(localParcel1, 0);
                if (paramPendingIntent == null) {
                  break label132;
                }
                localParcel1.writeInt(1);
                paramPendingIntent.writeToParcel(localParcel1, 0);
                this.zzrp.transact(11, localParcel1, localParcel2, 0);
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
          localParcel1.writeInt(0);
        }
      }
      
      public void zza(zzm paramzzm, zzz paramzzz, PendingIntent paramPendingIntent)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramzzm != null)
            {
              localParcel1.writeInt(1);
              paramzzm.writeToParcel(localParcel1, 0);
              if (paramzzz != null)
              {
                localParcel1.writeInt(1);
                paramzzz.writeToParcel(localParcel1, 0);
                if (paramPendingIntent == null) {
                  break label132;
                }
                localParcel1.writeInt(1);
                paramPendingIntent.writeToParcel(localParcel1, 0);
                this.zzrp.transact(9, localParcel1, localParcel2, 0);
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
          localParcel1.writeInt(0);
        }
      }
      
      public void zza(zzp paramzzp, LatLngBounds paramLatLngBounds, List<String> paramList, zzz paramzzz, zzl paramzzl)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramzzp != null)
            {
              localParcel1.writeInt(1);
              paramzzp.writeToParcel(localParcel1, 0);
              if (paramLatLngBounds != null)
              {
                localParcel1.writeInt(1);
                paramLatLngBounds.writeToParcel(localParcel1, 0);
                localParcel1.writeStringList(paramList);
                if (paramzzz == null) {
                  break label159;
                }
                localParcel1.writeInt(1);
                paramzzz.writeToParcel(localParcel1, 0);
                if (paramzzl == null) {
                  break label168;
                }
                paramzzp = paramzzl.asBinder();
                localParcel1.writeStrongBinder(paramzzp);
                this.zzrp.transact(8, localParcel1, localParcel2, 0);
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
          label159:
          localParcel1.writeInt(0);
          continue;
          label168:
          paramzzp = null;
        }
      }
      
      public void zza(LatLng paramLatLng, PlaceFilter paramPlaceFilter, zzz paramzzz, zzl paramzzl)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramLatLng != null)
            {
              localParcel1.writeInt(1);
              paramLatLng.writeToParcel(localParcel1, 0);
              if (paramPlaceFilter != null)
              {
                localParcel1.writeInt(1);
                paramPlaceFilter.writeToParcel(localParcel1, 0);
                if (paramzzz == null) {
                  break label150;
                }
                localParcel1.writeInt(1);
                paramzzz.writeToParcel(localParcel1, 0);
                if (paramzzl == null) {
                  break label159;
                }
                paramLatLng = paramzzl.asBinder();
                localParcel1.writeStrongBinder(paramLatLng);
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
          paramLatLng = null;
        }
      }
      
      public void zza(LatLng paramLatLng, zzz paramzzz, zzl paramzzl)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramLatLng != null)
            {
              localParcel1.writeInt(1);
              paramLatLng.writeToParcel(localParcel1, 0);
              if (paramzzz != null)
              {
                localParcel1.writeInt(1);
                paramzzz.writeToParcel(localParcel1, 0);
                if (paramzzl == null) {
                  break label132;
                }
                paramLatLng = paramzzl.asBinder();
                localParcel1.writeStrongBinder(paramLatLng);
                this.zzrp.transact(22, localParcel1, localParcel2, 0);
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
          paramLatLng = null;
        }
      }
      
      public void zza(LatLngBounds paramLatLngBounds, int paramInt, String paramString, PlaceFilter paramPlaceFilter, zzz paramzzz, zzl paramzzl)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramLatLngBounds != null)
            {
              localParcel1.writeInt(1);
              paramLatLngBounds.writeToParcel(localParcel1, 0);
              localParcel1.writeInt(paramInt);
              localParcel1.writeString(paramString);
              if (paramPlaceFilter != null)
              {
                localParcel1.writeInt(1);
                paramPlaceFilter.writeToParcel(localParcel1, 0);
                if (paramzzz == null) {
                  break label166;
                }
                localParcel1.writeInt(1);
                paramzzz.writeToParcel(localParcel1, 0);
                if (paramzzl == null) {
                  break label175;
                }
                paramLatLngBounds = paramzzl.asBinder();
                localParcel1.writeStrongBinder(paramLatLngBounds);
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
          label166:
          localParcel1.writeInt(0);
          continue;
          label175:
          paramLatLngBounds = null;
        }
      }
      
      public void zza(String paramString, int paramInt1, int paramInt2, int paramInt3, zzz paramzzz, zzk paramzzk)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            localParcel1.writeString(paramString);
            localParcel1.writeInt(paramInt1);
            localParcel1.writeInt(paramInt2);
            localParcel1.writeInt(paramInt3);
            if (paramzzz != null)
            {
              localParcel1.writeInt(1);
              paramzzz.writeToParcel(localParcel1, 0);
              if (paramzzk != null)
              {
                paramString = paramzzk.asBinder();
                localParcel1.writeStrongBinder(paramString);
                this.zzrp.transact(20, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramString = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(String paramString, int paramInt, zzz paramzzz, zzl paramzzl)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            localParcel1.writeString(paramString);
            localParcel1.writeInt(paramInt);
            if (paramzzz != null)
            {
              localParcel1.writeInt(1);
              paramzzz.writeToParcel(localParcel1, 0);
              if (paramzzl != null)
              {
                paramString = paramzzl.asBinder();
                localParcel1.writeStrongBinder(paramString);
                this.zzrp.transact(18, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramString = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(String paramString, zzz paramzzz, zzk paramzzk)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            localParcel1.writeString(paramString);
            if (paramzzz != null)
            {
              localParcel1.writeInt(1);
              paramzzz.writeToParcel(localParcel1, 0);
              if (paramzzk != null)
              {
                paramString = paramzzk.asBinder();
                localParcel1.writeStrongBinder(paramString);
                this.zzrp.transact(19, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramString = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(String paramString, zzz paramzzz, zzl paramzzl)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            localParcel1.writeString(paramString);
            if (paramzzz != null)
            {
              localParcel1.writeInt(1);
              paramzzz.writeToParcel(localParcel1, 0);
              if (paramzzl != null)
              {
                paramString = paramzzl.asBinder();
                localParcel1.writeStrongBinder(paramString);
                this.zzrp.transact(6, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramString = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(String paramString, LatLngBounds paramLatLngBounds, AutocompleteFilter paramAutocompleteFilter, zzz paramzzz, zzl paramzzl)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            localParcel1.writeString(paramString);
            if (paramLatLngBounds != null)
            {
              localParcel1.writeInt(1);
              paramLatLngBounds.writeToParcel(localParcel1, 0);
              if (paramAutocompleteFilter != null)
              {
                localParcel1.writeInt(1);
                paramAutocompleteFilter.writeToParcel(localParcel1, 0);
                if (paramzzz == null) {
                  break label159;
                }
                localParcel1.writeInt(1);
                paramzzz.writeToParcel(localParcel1, 0);
                if (paramzzl == null) {
                  break label168;
                }
                paramString = paramzzl.asBinder();
                localParcel1.writeStrongBinder(paramString);
                this.zzrp.transact(13, localParcel1, localParcel2, 0);
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
          label159:
          localParcel1.writeInt(0);
          continue;
          label168:
          paramString = null;
        }
      }
      
      public void zza(String paramString1, String paramString2, zzz paramzzz, zzash paramzzash)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            localParcel1.writeString(paramString1);
            localParcel1.writeString(paramString2);
            if (paramzzz != null)
            {
              localParcel1.writeInt(1);
              paramzzz.writeToParcel(localParcel1, 0);
              if (paramzzash != null)
              {
                paramString1 = paramzzash.asBinder();
                localParcel1.writeStrongBinder(paramString1);
                this.zzrp.transact(21, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramString1 = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(String paramString1, String paramString2, String paramString3, zzz paramzzz, zzash paramzzash)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            localParcel1.writeString(paramString1);
            localParcel1.writeString(paramString2);
            localParcel1.writeString(paramString3);
            if (paramzzz != null)
            {
              localParcel1.writeInt(1);
              paramzzz.writeToParcel(localParcel1, 0);
              if (paramzzash != null)
              {
                paramString1 = paramzzash.asBinder();
                localParcel1.writeStrongBinder(paramString1);
                this.zzrp.transact(16, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramString1 = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(List<String> paramList, zzz paramzzz, zzl paramzzl)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            localParcel1.writeStringList(paramList);
            if (paramzzz != null)
            {
              localParcel1.writeInt(1);
              paramzzz.writeToParcel(localParcel1, 0);
              if (paramzzl != null)
              {
                paramList = paramzzl.asBinder();
                localParcel1.writeStrongBinder(paramList);
                this.zzrp.transact(7, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramList = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zzb(PlaceFilter paramPlaceFilter, zzz paramzzz, zzl paramzzl)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramPlaceFilter != null)
            {
              localParcel1.writeInt(1);
              paramPlaceFilter.writeToParcel(localParcel1, 0);
              if (paramzzz != null)
              {
                localParcel1.writeInt(1);
                paramzzz.writeToParcel(localParcel1, 0);
                if (paramzzl == null) {
                  break label131;
                }
                paramPlaceFilter = paramzzl.asBinder();
                localParcel1.writeStrongBinder(paramPlaceFilter);
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
          paramPlaceFilter = null;
        }
      }
      
      public void zzb(zzz paramzzz, PendingIntent paramPendingIntent)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramzzz != null)
            {
              localParcel1.writeInt(1);
              paramzzz.writeToParcel(localParcel1, 0);
              if (paramPendingIntent != null)
              {
                localParcel1.writeInt(1);
                paramPendingIntent.writeToParcel(localParcel1, 0);
                this.zzrp.transact(12, localParcel1, localParcel2, 0);
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
        }
      }
      
      public void zzb(zzz paramzzz, zzash paramzzash)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramzzz != null)
            {
              localParcel1.writeInt(1);
              paramzzz.writeToParcel(localParcel1, 0);
              if (paramzzash != null)
              {
                paramzzz = paramzzash.asBinder();
                localParcel1.writeStrongBinder(paramzzz);
                this.zzrp.transact(25, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramzzz = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zzb(List<String> paramList, zzz paramzzz, zzl paramzzl)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            localParcel1.writeStringList(paramList);
            if (paramzzz != null)
            {
              localParcel1.writeInt(1);
              paramzzz.writeToParcel(localParcel1, 0);
              if (paramzzl != null)
              {
                paramList = paramzzl.asBinder();
                localParcel1.writeStrongBinder(paramList);
                this.zzrp.transact(17, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramList = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\internal\zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */