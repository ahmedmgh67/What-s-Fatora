package com.google.firebase.database.connection.idl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface zzi
  extends IInterface
{
  public abstract void onError(String paramString)
    throws RemoteException;
  
  public abstract void zziM(String paramString)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzi
  {
    public zza()
    {
      attachInterface(this, "com.google.firebase.database.connection.idl.IGetTokenCallback");
    }
    
    public static zzi zzfH(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.firebase.database.connection.idl.IGetTokenCallback");
      if ((localIInterface != null) && ((localIInterface instanceof zzi))) {
        return (zzi)localIInterface;
      }
      return new zza(paramIBinder);
    }
    
    public IBinder asBinder()
    {
      return this;
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.firebase.database.connection.idl.IGetTokenCallback");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.google.firebase.database.connection.idl.IGetTokenCallback");
        zziM(paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      }
      paramParcel1.enforceInterface("com.google.firebase.database.connection.idl.IGetTokenCallback");
      onError(paramParcel1.readString());
      paramParcel2.writeNoException();
      return true;
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
      
      public void onError(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.firebase.database.connection.idl.IGetTokenCallback");
          localParcel1.writeString(paramString);
          this.zzrp.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void zziM(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.firebase.database.connection.idl.IGetTokenCallback");
          localParcel1.writeString(paramString);
          this.zzrp.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
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


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\database\connection\idl\zzi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */