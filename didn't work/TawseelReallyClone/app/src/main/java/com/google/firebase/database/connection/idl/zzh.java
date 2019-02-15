package com.google.firebase.database.connection.idl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface zzh
  extends IInterface
{
  public abstract void zza(boolean paramBoolean, zzi paramzzi)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzh
  {
    public zza()
    {
      attachInterface(this, "com.google.firebase.database.connection.idl.IConnectionAuthTokenProvider");
    }
    
    public static zzh zzfG(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.firebase.database.connection.idl.IConnectionAuthTokenProvider");
      if ((localIInterface != null) && ((localIInterface instanceof zzh))) {
        return (zzh)localIInterface;
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
        paramParcel2.writeString("com.google.firebase.database.connection.idl.IConnectionAuthTokenProvider");
        return true;
      }
      paramParcel1.enforceInterface("com.google.firebase.database.connection.idl.IConnectionAuthTokenProvider");
      if (paramParcel1.readInt() != 0) {}
      for (boolean bool = true;; bool = false)
      {
        zza(bool, zzi.zza.zzfH(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      }
    }
    
    private static class zza
      implements zzh
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
      
      /* Error */
      public void zza(boolean paramBoolean, zzi paramzzi)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore_3
        //   2: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore 4
        //   7: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   10: astore 5
        //   12: aload 4
        //   14: ldc 32
        //   16: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   19: iload_1
        //   20: ifeq +58 -> 78
        //   23: aload 4
        //   25: iload_3
        //   26: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   29: aload_2
        //   30: ifnull +53 -> 83
        //   33: aload_2
        //   34: invokeinterface 44 1 0
        //   39: astore_2
        //   40: aload 4
        //   42: aload_2
        //   43: invokevirtual 47	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   46: aload_0
        //   47: getfield 18	com/google/firebase/database/connection/idl/zzh$zza$zza:zzrp	Landroid/os/IBinder;
        //   50: iconst_1
        //   51: aload 4
        //   53: aload 5
        //   55: iconst_0
        //   56: invokeinterface 53 5 0
        //   61: pop
        //   62: aload 5
        //   64: invokevirtual 56	android/os/Parcel:readException	()V
        //   67: aload 5
        //   69: invokevirtual 59	android/os/Parcel:recycle	()V
        //   72: aload 4
        //   74: invokevirtual 59	android/os/Parcel:recycle	()V
        //   77: return
        //   78: iconst_0
        //   79: istore_3
        //   80: goto -57 -> 23
        //   83: aconst_null
        //   84: astore_2
        //   85: goto -45 -> 40
        //   88: astore_2
        //   89: aload 5
        //   91: invokevirtual 59	android/os/Parcel:recycle	()V
        //   94: aload 4
        //   96: invokevirtual 59	android/os/Parcel:recycle	()V
        //   99: aload_2
        //   100: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	101	0	this	zza
        //   0	101	1	paramBoolean	boolean
        //   0	101	2	paramzzi	zzi
        //   1	79	3	i	int
        //   5	90	4	localParcel1	Parcel
        //   10	80	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   12	19	88	finally
        //   23	29	88	finally
        //   33	40	88	finally
        //   40	67	88	finally
      }
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\database\connection\idl\zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */