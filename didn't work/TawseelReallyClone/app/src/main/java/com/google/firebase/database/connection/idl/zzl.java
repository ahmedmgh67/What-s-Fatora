package com.google.firebase.database.connection.idl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zzd.zza;
import java.util.ArrayList;
import java.util.List;

public abstract interface zzl
  extends IInterface
{
  public abstract void onDisconnect()
    throws RemoteException;
  
  public abstract void zzP(zzd paramzzd)
    throws RemoteException;
  
  public abstract void zzVP()
    throws RemoteException;
  
  public abstract void zza(List<String> paramList, zzd paramzzd, boolean paramBoolean, long paramLong)
    throws RemoteException;
  
  public abstract void zza(List<String> paramList, List<zzn> paramList1, zzd paramzzd, long paramLong)
    throws RemoteException;
  
  public abstract void zzaX(boolean paramBoolean)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzl
  {
    public zza()
    {
      attachInterface(this, "com.google.firebase.database.connection.idl.IPersistentConnectionDelegate");
    }
    
    public static zzl zzfJ(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.firebase.database.connection.idl.IPersistentConnectionDelegate");
      if ((localIInterface != null) && ((localIInterface instanceof zzl))) {
        return (zzl)localIInterface;
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
      boolean bool2 = false;
      boolean bool1 = false;
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.firebase.database.connection.idl.IPersistentConnectionDelegate");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnectionDelegate");
        ArrayList localArrayList = paramParcel1.createStringArrayList();
        zzd localzzd = zzd.zza.zzcd(paramParcel1.readStrongBinder());
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        zza(localArrayList, localzzd, bool1, paramParcel1.readLong());
        paramParcel2.writeNoException();
        return true;
      case 2: 
        paramParcel1.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnectionDelegate");
        zza(paramParcel1.createStringArrayList(), paramParcel1.createTypedArrayList(zzn.CREATOR), zzd.zza.zzcd(paramParcel1.readStrongBinder()), paramParcel1.readLong());
        paramParcel2.writeNoException();
        return true;
      case 3: 
        paramParcel1.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnectionDelegate");
        zzVP();
        paramParcel2.writeNoException();
        return true;
      case 4: 
        paramParcel1.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnectionDelegate");
        onDisconnect();
        paramParcel2.writeNoException();
        return true;
      case 5: 
        paramParcel1.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnectionDelegate");
        bool1 = bool2;
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        zzaX(bool1);
        paramParcel2.writeNoException();
        return true;
      }
      paramParcel1.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnectionDelegate");
      zzP(zzd.zza.zzcd(paramParcel1.readStrongBinder()));
      paramParcel2.writeNoException();
      return true;
    }
    
    private static class zza
      implements zzl
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
      
      public void onDisconnect()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnectionDelegate");
          this.zzrp.transact(4, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public void zzP(zzd paramzzd)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 32
        //   11: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +43 -> 58
        //   18: aload_1
        //   19: invokeinterface 55 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 58	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/firebase/database/connection/idl/zzl$zza$zza:zzrp	Landroid/os/IBinder;
        //   34: bipush 6
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 42 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 45	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 48	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 48	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 48	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 48	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	zza
        //   0	74	1	paramzzd	zzd
        //   3	66	2	localParcel1	Parcel
        //   7	58	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      public void zzVP()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnectionDelegate");
          this.zzrp.transact(3, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public void zza(List<String> paramList, zzd paramzzd, boolean paramBoolean, long paramLong)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 6
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 7
        //   8: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 8
        //   13: aload 7
        //   15: ldc 32
        //   17: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload 7
        //   22: aload_1
        //   23: invokevirtual 64	android/os/Parcel:writeStringList	(Ljava/util/List;)V
        //   26: aload_2
        //   27: ifnull +66 -> 93
        //   30: aload_2
        //   31: invokeinterface 55 1 0
        //   36: astore_1
        //   37: aload 7
        //   39: aload_1
        //   40: invokevirtual 58	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   43: iload_3
        //   44: ifeq +54 -> 98
        //   47: aload 7
        //   49: iload 6
        //   51: invokevirtual 68	android/os/Parcel:writeInt	(I)V
        //   54: aload 7
        //   56: lload 4
        //   58: invokevirtual 72	android/os/Parcel:writeLong	(J)V
        //   61: aload_0
        //   62: getfield 18	com/google/firebase/database/connection/idl/zzl$zza$zza:zzrp	Landroid/os/IBinder;
        //   65: iconst_1
        //   66: aload 7
        //   68: aload 8
        //   70: iconst_0
        //   71: invokeinterface 42 5 0
        //   76: pop
        //   77: aload 8
        //   79: invokevirtual 45	android/os/Parcel:readException	()V
        //   82: aload 8
        //   84: invokevirtual 48	android/os/Parcel:recycle	()V
        //   87: aload 7
        //   89: invokevirtual 48	android/os/Parcel:recycle	()V
        //   92: return
        //   93: aconst_null
        //   94: astore_1
        //   95: goto -58 -> 37
        //   98: iconst_0
        //   99: istore 6
        //   101: goto -54 -> 47
        //   104: astore_1
        //   105: aload 8
        //   107: invokevirtual 48	android/os/Parcel:recycle	()V
        //   110: aload 7
        //   112: invokevirtual 48	android/os/Parcel:recycle	()V
        //   115: aload_1
        //   116: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	117	0	this	zza
        //   0	117	1	paramList	List<String>
        //   0	117	2	paramzzd	zzd
        //   0	117	3	paramBoolean	boolean
        //   0	117	4	paramLong	long
        //   1	99	6	i	int
        //   6	105	7	localParcel1	Parcel
        //   11	95	8	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	26	104	finally
        //   30	37	104	finally
        //   37	43	104	finally
        //   47	82	104	finally
      }
      
      /* Error */
      public void zza(List<String> paramList, List<zzn> paramList1, zzd paramzzd, long paramLong)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 6
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 7
        //   10: aload 6
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload 6
        //   19: aload_1
        //   20: invokevirtual 64	android/os/Parcel:writeStringList	(Ljava/util/List;)V
        //   23: aload 6
        //   25: aload_2
        //   26: invokevirtual 78	android/os/Parcel:writeTypedList	(Ljava/util/List;)V
        //   29: aload_3
        //   30: ifnull +55 -> 85
        //   33: aload_3
        //   34: invokeinterface 55 1 0
        //   39: astore_1
        //   40: aload 6
        //   42: aload_1
        //   43: invokevirtual 58	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   46: aload 6
        //   48: lload 4
        //   50: invokevirtual 72	android/os/Parcel:writeLong	(J)V
        //   53: aload_0
        //   54: getfield 18	com/google/firebase/database/connection/idl/zzl$zza$zza:zzrp	Landroid/os/IBinder;
        //   57: iconst_2
        //   58: aload 6
        //   60: aload 7
        //   62: iconst_0
        //   63: invokeinterface 42 5 0
        //   68: pop
        //   69: aload 7
        //   71: invokevirtual 45	android/os/Parcel:readException	()V
        //   74: aload 7
        //   76: invokevirtual 48	android/os/Parcel:recycle	()V
        //   79: aload 6
        //   81: invokevirtual 48	android/os/Parcel:recycle	()V
        //   84: return
        //   85: aconst_null
        //   86: astore_1
        //   87: goto -47 -> 40
        //   90: astore_1
        //   91: aload 7
        //   93: invokevirtual 48	android/os/Parcel:recycle	()V
        //   96: aload 6
        //   98: invokevirtual 48	android/os/Parcel:recycle	()V
        //   101: aload_1
        //   102: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	103	0	this	zza
        //   0	103	1	paramList	List<String>
        //   0	103	2	paramList1	List<zzn>
        //   0	103	3	paramzzd	zzd
        //   0	103	4	paramLong	long
        //   3	94	6	localParcel1	Parcel
        //   8	84	7	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	29	90	finally
        //   33	40	90	finally
        //   40	74	90	finally
      }
      
      public void zzaX(boolean paramBoolean)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnectionDelegate");
          if (paramBoolean) {
            i = 1;
          }
          localParcel1.writeInt(i);
          this.zzrp.transact(5, localParcel1, localParcel2, 0);
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


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\database\connection\idl\zzl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */