package com.google.firebase.database.connection.idl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zzd.zza;
import java.util.List;

public abstract interface zzk
  extends IInterface
{
  public abstract void compareAndPut(List<String> paramList, zzd paramzzd, String paramString, zzm paramzzm)
    throws RemoteException;
  
  public abstract void initialize()
    throws RemoteException;
  
  public abstract void interrupt(String paramString)
    throws RemoteException;
  
  public abstract boolean isInterrupted(String paramString)
    throws RemoteException;
  
  public abstract void listen(List<String> paramList, zzd paramzzd, zzj paramzzj, long paramLong, zzm paramzzm)
    throws RemoteException;
  
  public abstract void merge(List<String> paramList, zzd paramzzd, zzm paramzzm)
    throws RemoteException;
  
  public abstract void onDisconnectCancel(List<String> paramList, zzm paramzzm)
    throws RemoteException;
  
  public abstract void onDisconnectMerge(List<String> paramList, zzd paramzzd, zzm paramzzm)
    throws RemoteException;
  
  public abstract void onDisconnectPut(List<String> paramList, zzd paramzzd, zzm paramzzm)
    throws RemoteException;
  
  public abstract void purgeOutstandingWrites()
    throws RemoteException;
  
  public abstract void put(List<String> paramList, zzd paramzzd, zzm paramzzm)
    throws RemoteException;
  
  public abstract void refreshAuthToken()
    throws RemoteException;
  
  public abstract void refreshAuthToken2(String paramString)
    throws RemoteException;
  
  public abstract void resume(String paramString)
    throws RemoteException;
  
  public abstract void setup(zzc paramzzc, zzh paramzzh, zzd paramzzd, zzl paramzzl)
    throws RemoteException;
  
  public abstract void shutdown()
    throws RemoteException;
  
  public abstract void unlisten(List<String> paramList, zzd paramzzd)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzk
  {
    public zza()
    {
      attachInterface(this, "com.google.firebase.database.connection.idl.IPersistentConnection");
    }
    
    public static zzk asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
      if ((localIInterface != null) && ((localIInterface instanceof zzk))) {
        return (zzk)localIInterface;
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
        paramParcel2.writeString("com.google.firebase.database.connection.idl.IPersistentConnection");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
        if (paramParcel1.readInt() != 0) {}
        for (zzc localzzc = (zzc)zzc.CREATOR.createFromParcel(paramParcel1);; localzzc = null)
        {
          setup(localzzc, zzh.zza.zzfG(paramParcel1.readStrongBinder()), zzd.zza.zzcd(paramParcel1.readStrongBinder()), zzl.zza.zzfJ(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        }
      case 2: 
        paramParcel1.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
        initialize();
        paramParcel2.writeNoException();
        return true;
      case 3: 
        paramParcel1.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
        shutdown();
        paramParcel2.writeNoException();
        return true;
      case 4: 
        paramParcel1.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
        refreshAuthToken();
        paramParcel2.writeNoException();
        return true;
      case 17: 
        paramParcel1.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
        refreshAuthToken2(paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5: 
        paramParcel1.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
        listen(paramParcel1.createStringArrayList(), zzd.zza.zzcd(paramParcel1.readStrongBinder()), zzj.zza.zzfI(paramParcel1.readStrongBinder()), paramParcel1.readLong(), zzm.zza.zzfK(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 6: 
        paramParcel1.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
        unlisten(paramParcel1.createStringArrayList(), zzd.zza.zzcd(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 7: 
        paramParcel1.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
        purgeOutstandingWrites();
        paramParcel2.writeNoException();
        return true;
      case 8: 
        paramParcel1.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
        put(paramParcel1.createStringArrayList(), zzd.zza.zzcd(paramParcel1.readStrongBinder()), zzm.zza.zzfK(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 9: 
        paramParcel1.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
        compareAndPut(paramParcel1.createStringArrayList(), zzd.zza.zzcd(paramParcel1.readStrongBinder()), paramParcel1.readString(), zzm.zza.zzfK(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 10: 
        paramParcel1.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
        merge(paramParcel1.createStringArrayList(), zzd.zza.zzcd(paramParcel1.readStrongBinder()), zzm.zza.zzfK(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 11: 
        paramParcel1.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
        onDisconnectPut(paramParcel1.createStringArrayList(), zzd.zza.zzcd(paramParcel1.readStrongBinder()), zzm.zza.zzfK(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 12: 
        paramParcel1.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
        onDisconnectMerge(paramParcel1.createStringArrayList(), zzd.zza.zzcd(paramParcel1.readStrongBinder()), zzm.zza.zzfK(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 13: 
        paramParcel1.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
        onDisconnectCancel(paramParcel1.createStringArrayList(), zzm.zza.zzfK(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 14: 
        paramParcel1.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
        interrupt(paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 15: 
        paramParcel1.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
        resume(paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      }
      paramParcel1.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
      boolean bool = isInterrupted(paramParcel1.readString());
      paramParcel2.writeNoException();
      if (bool) {}
      for (paramInt1 = 1;; paramInt1 = 0)
      {
        paramParcel2.writeInt(paramInt1);
        return true;
      }
    }
    
    private static class zza
      implements zzk
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
      public void compareAndPut(List<String> paramList, zzd paramzzd, String paramString, zzm paramzzm)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 5
        //   3: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 6
        //   8: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 7
        //   13: aload 6
        //   15: ldc 33
        //   17: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload 6
        //   22: aload_1
        //   23: invokevirtual 41	android/os/Parcel:writeStringList	(Ljava/util/List;)V
        //   26: aload_2
        //   27: ifnull +77 -> 104
        //   30: aload_2
        //   31: invokeinterface 45 1 0
        //   36: astore_1
        //   37: aload 6
        //   39: aload_1
        //   40: invokevirtual 48	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   43: aload 6
        //   45: aload_3
        //   46: invokevirtual 51	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   49: aload 5
        //   51: astore_1
        //   52: aload 4
        //   54: ifnull +11 -> 65
        //   57: aload 4
        //   59: invokeinterface 54 1 0
        //   64: astore_1
        //   65: aload 6
        //   67: aload_1
        //   68: invokevirtual 48	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   71: aload_0
        //   72: getfield 18	com/google/firebase/database/connection/idl/zzk$zza$zza:zzrp	Landroid/os/IBinder;
        //   75: bipush 9
        //   77: aload 6
        //   79: aload 7
        //   81: iconst_0
        //   82: invokeinterface 60 5 0
        //   87: pop
        //   88: aload 7
        //   90: invokevirtual 63	android/os/Parcel:readException	()V
        //   93: aload 7
        //   95: invokevirtual 66	android/os/Parcel:recycle	()V
        //   98: aload 6
        //   100: invokevirtual 66	android/os/Parcel:recycle	()V
        //   103: return
        //   104: aconst_null
        //   105: astore_1
        //   106: goto -69 -> 37
        //   109: astore_1
        //   110: aload 7
        //   112: invokevirtual 66	android/os/Parcel:recycle	()V
        //   115: aload 6
        //   117: invokevirtual 66	android/os/Parcel:recycle	()V
        //   120: aload_1
        //   121: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	122	0	this	zza
        //   0	122	1	paramList	List<String>
        //   0	122	2	paramzzd	zzd
        //   0	122	3	paramString	String
        //   0	122	4	paramzzm	zzm
        //   1	49	5	localObject	Object
        //   6	110	6	localParcel1	Parcel
        //   11	100	7	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	26	109	finally
        //   30	37	109	finally
        //   37	49	109	finally
        //   57	65	109	finally
        //   65	93	109	finally
      }
      
      public void initialize()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnection");
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
      
      public void interrupt(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnection");
          localParcel1.writeString(paramString);
          this.zzrp.transact(14, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public boolean isInterrupted(String paramString)
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnection");
          localParcel1.writeString(paramString);
          this.zzrp.transact(16, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (i != 0) {
            bool = true;
          }
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public void listen(List<String> paramList, zzd paramzzd, zzj paramzzj, long paramLong, zzm paramzzm)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 7
        //   3: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 8
        //   8: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 9
        //   13: aload 8
        //   15: ldc 33
        //   17: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload 8
        //   22: aload_1
        //   23: invokevirtual 41	android/os/Parcel:writeStringList	(Ljava/util/List;)V
        //   26: aload_2
        //   27: ifnull +94 -> 121
        //   30: aload_2
        //   31: invokeinterface 45 1 0
        //   36: astore_1
        //   37: aload 8
        //   39: aload_1
        //   40: invokevirtual 48	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   43: aload_3
        //   44: ifnull +82 -> 126
        //   47: aload_3
        //   48: invokeinterface 82 1 0
        //   53: astore_1
        //   54: aload 8
        //   56: aload_1
        //   57: invokevirtual 48	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   60: aload 8
        //   62: lload 4
        //   64: invokevirtual 86	android/os/Parcel:writeLong	(J)V
        //   67: aload 7
        //   69: astore_1
        //   70: aload 6
        //   72: ifnull +11 -> 83
        //   75: aload 6
        //   77: invokeinterface 54 1 0
        //   82: astore_1
        //   83: aload 8
        //   85: aload_1
        //   86: invokevirtual 48	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   89: aload_0
        //   90: getfield 18	com/google/firebase/database/connection/idl/zzk$zza$zza:zzrp	Landroid/os/IBinder;
        //   93: iconst_5
        //   94: aload 8
        //   96: aload 9
        //   98: iconst_0
        //   99: invokeinterface 60 5 0
        //   104: pop
        //   105: aload 9
        //   107: invokevirtual 63	android/os/Parcel:readException	()V
        //   110: aload 9
        //   112: invokevirtual 66	android/os/Parcel:recycle	()V
        //   115: aload 8
        //   117: invokevirtual 66	android/os/Parcel:recycle	()V
        //   120: return
        //   121: aconst_null
        //   122: astore_1
        //   123: goto -86 -> 37
        //   126: aconst_null
        //   127: astore_1
        //   128: goto -74 -> 54
        //   131: astore_1
        //   132: aload 9
        //   134: invokevirtual 66	android/os/Parcel:recycle	()V
        //   137: aload 8
        //   139: invokevirtual 66	android/os/Parcel:recycle	()V
        //   142: aload_1
        //   143: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	144	0	this	zza
        //   0	144	1	paramList	List<String>
        //   0	144	2	paramzzd	zzd
        //   0	144	3	paramzzj	zzj
        //   0	144	4	paramLong	long
        //   0	144	6	paramzzm	zzm
        //   1	67	7	localObject	Object
        //   6	132	8	localParcel1	Parcel
        //   11	122	9	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	26	131	finally
        //   30	37	131	finally
        //   37	43	131	finally
        //   47	54	131	finally
        //   54	67	131	finally
        //   75	83	131	finally
        //   83	110	131	finally
      }
      
      /* Error */
      public void merge(List<String> paramList, zzd paramzzd, zzm paramzzm)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 4
        //   3: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 6
        //   13: aload 5
        //   15: ldc 33
        //   17: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload 5
        //   22: aload_1
        //   23: invokevirtual 41	android/os/Parcel:writeStringList	(Ljava/util/List;)V
        //   26: aload_2
        //   27: ifnull +69 -> 96
        //   30: aload_2
        //   31: invokeinterface 45 1 0
        //   36: astore_1
        //   37: aload 5
        //   39: aload_1
        //   40: invokevirtual 48	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   43: aload 4
        //   45: astore_1
        //   46: aload_3
        //   47: ifnull +10 -> 57
        //   50: aload_3
        //   51: invokeinterface 54 1 0
        //   56: astore_1
        //   57: aload 5
        //   59: aload_1
        //   60: invokevirtual 48	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   63: aload_0
        //   64: getfield 18	com/google/firebase/database/connection/idl/zzk$zza$zza:zzrp	Landroid/os/IBinder;
        //   67: bipush 10
        //   69: aload 5
        //   71: aload 6
        //   73: iconst_0
        //   74: invokeinterface 60 5 0
        //   79: pop
        //   80: aload 6
        //   82: invokevirtual 63	android/os/Parcel:readException	()V
        //   85: aload 6
        //   87: invokevirtual 66	android/os/Parcel:recycle	()V
        //   90: aload 5
        //   92: invokevirtual 66	android/os/Parcel:recycle	()V
        //   95: return
        //   96: aconst_null
        //   97: astore_1
        //   98: goto -61 -> 37
        //   101: astore_1
        //   102: aload 6
        //   104: invokevirtual 66	android/os/Parcel:recycle	()V
        //   107: aload 5
        //   109: invokevirtual 66	android/os/Parcel:recycle	()V
        //   112: aload_1
        //   113: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	114	0	this	zza
        //   0	114	1	paramList	List<String>
        //   0	114	2	paramzzd	zzd
        //   0	114	3	paramzzm	zzm
        //   1	43	4	localObject	Object
        //   6	102	5	localParcel1	Parcel
        //   11	92	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	26	101	finally
        //   30	37	101	finally
        //   37	43	101	finally
        //   50	57	101	finally
        //   57	85	101	finally
      }
      
      /* Error */
      public void onDisconnectCancel(List<String> paramList, zzm paramzzm)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore 4
        //   9: aload_3
        //   10: ldc 33
        //   12: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_3
        //   16: aload_1
        //   17: invokevirtual 41	android/os/Parcel:writeStringList	(Ljava/util/List;)V
        //   20: aload_2
        //   21: ifnull +46 -> 67
        //   24: aload_2
        //   25: invokeinterface 54 1 0
        //   30: astore_1
        //   31: aload_3
        //   32: aload_1
        //   33: invokevirtual 48	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload_0
        //   37: getfield 18	com/google/firebase/database/connection/idl/zzk$zza$zza:zzrp	Landroid/os/IBinder;
        //   40: bipush 13
        //   42: aload_3
        //   43: aload 4
        //   45: iconst_0
        //   46: invokeinterface 60 5 0
        //   51: pop
        //   52: aload 4
        //   54: invokevirtual 63	android/os/Parcel:readException	()V
        //   57: aload 4
        //   59: invokevirtual 66	android/os/Parcel:recycle	()V
        //   62: aload_3
        //   63: invokevirtual 66	android/os/Parcel:recycle	()V
        //   66: return
        //   67: aconst_null
        //   68: astore_1
        //   69: goto -38 -> 31
        //   72: astore_1
        //   73: aload 4
        //   75: invokevirtual 66	android/os/Parcel:recycle	()V
        //   78: aload_3
        //   79: invokevirtual 66	android/os/Parcel:recycle	()V
        //   82: aload_1
        //   83: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	84	0	this	zza
        //   0	84	1	paramList	List<String>
        //   0	84	2	paramzzm	zzm
        //   3	76	3	localParcel1	Parcel
        //   7	67	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	20	72	finally
        //   24	31	72	finally
        //   31	57	72	finally
      }
      
      /* Error */
      public void onDisconnectMerge(List<String> paramList, zzd paramzzd, zzm paramzzm)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 4
        //   3: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 6
        //   13: aload 5
        //   15: ldc 33
        //   17: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload 5
        //   22: aload_1
        //   23: invokevirtual 41	android/os/Parcel:writeStringList	(Ljava/util/List;)V
        //   26: aload_2
        //   27: ifnull +69 -> 96
        //   30: aload_2
        //   31: invokeinterface 45 1 0
        //   36: astore_1
        //   37: aload 5
        //   39: aload_1
        //   40: invokevirtual 48	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   43: aload 4
        //   45: astore_1
        //   46: aload_3
        //   47: ifnull +10 -> 57
        //   50: aload_3
        //   51: invokeinterface 54 1 0
        //   56: astore_1
        //   57: aload 5
        //   59: aload_1
        //   60: invokevirtual 48	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   63: aload_0
        //   64: getfield 18	com/google/firebase/database/connection/idl/zzk$zza$zza:zzrp	Landroid/os/IBinder;
        //   67: bipush 12
        //   69: aload 5
        //   71: aload 6
        //   73: iconst_0
        //   74: invokeinterface 60 5 0
        //   79: pop
        //   80: aload 6
        //   82: invokevirtual 63	android/os/Parcel:readException	()V
        //   85: aload 6
        //   87: invokevirtual 66	android/os/Parcel:recycle	()V
        //   90: aload 5
        //   92: invokevirtual 66	android/os/Parcel:recycle	()V
        //   95: return
        //   96: aconst_null
        //   97: astore_1
        //   98: goto -61 -> 37
        //   101: astore_1
        //   102: aload 6
        //   104: invokevirtual 66	android/os/Parcel:recycle	()V
        //   107: aload 5
        //   109: invokevirtual 66	android/os/Parcel:recycle	()V
        //   112: aload_1
        //   113: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	114	0	this	zza
        //   0	114	1	paramList	List<String>
        //   0	114	2	paramzzd	zzd
        //   0	114	3	paramzzm	zzm
        //   1	43	4	localObject	Object
        //   6	102	5	localParcel1	Parcel
        //   11	92	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	26	101	finally
        //   30	37	101	finally
        //   37	43	101	finally
        //   50	57	101	finally
        //   57	85	101	finally
      }
      
      /* Error */
      public void onDisconnectPut(List<String> paramList, zzd paramzzd, zzm paramzzm)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 4
        //   3: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 6
        //   13: aload 5
        //   15: ldc 33
        //   17: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload 5
        //   22: aload_1
        //   23: invokevirtual 41	android/os/Parcel:writeStringList	(Ljava/util/List;)V
        //   26: aload_2
        //   27: ifnull +69 -> 96
        //   30: aload_2
        //   31: invokeinterface 45 1 0
        //   36: astore_1
        //   37: aload 5
        //   39: aload_1
        //   40: invokevirtual 48	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   43: aload 4
        //   45: astore_1
        //   46: aload_3
        //   47: ifnull +10 -> 57
        //   50: aload_3
        //   51: invokeinterface 54 1 0
        //   56: astore_1
        //   57: aload 5
        //   59: aload_1
        //   60: invokevirtual 48	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   63: aload_0
        //   64: getfield 18	com/google/firebase/database/connection/idl/zzk$zza$zza:zzrp	Landroid/os/IBinder;
        //   67: bipush 11
        //   69: aload 5
        //   71: aload 6
        //   73: iconst_0
        //   74: invokeinterface 60 5 0
        //   79: pop
        //   80: aload 6
        //   82: invokevirtual 63	android/os/Parcel:readException	()V
        //   85: aload 6
        //   87: invokevirtual 66	android/os/Parcel:recycle	()V
        //   90: aload 5
        //   92: invokevirtual 66	android/os/Parcel:recycle	()V
        //   95: return
        //   96: aconst_null
        //   97: astore_1
        //   98: goto -61 -> 37
        //   101: astore_1
        //   102: aload 6
        //   104: invokevirtual 66	android/os/Parcel:recycle	()V
        //   107: aload 5
        //   109: invokevirtual 66	android/os/Parcel:recycle	()V
        //   112: aload_1
        //   113: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	114	0	this	zza
        //   0	114	1	paramList	List<String>
        //   0	114	2	paramzzd	zzd
        //   0	114	3	paramzzm	zzm
        //   1	43	4	localObject	Object
        //   6	102	5	localParcel1	Parcel
        //   11	92	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	26	101	finally
        //   30	37	101	finally
        //   37	43	101	finally
        //   50	57	101	finally
        //   57	85	101	finally
      }
      
      public void purgeOutstandingWrites()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnection");
          this.zzrp.transact(7, localParcel1, localParcel2, 0);
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
      public void put(List<String> paramList, zzd paramzzd, zzm paramzzm)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 4
        //   3: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 6
        //   13: aload 5
        //   15: ldc 33
        //   17: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload 5
        //   22: aload_1
        //   23: invokevirtual 41	android/os/Parcel:writeStringList	(Ljava/util/List;)V
        //   26: aload_2
        //   27: ifnull +69 -> 96
        //   30: aload_2
        //   31: invokeinterface 45 1 0
        //   36: astore_1
        //   37: aload 5
        //   39: aload_1
        //   40: invokevirtual 48	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   43: aload 4
        //   45: astore_1
        //   46: aload_3
        //   47: ifnull +10 -> 57
        //   50: aload_3
        //   51: invokeinterface 54 1 0
        //   56: astore_1
        //   57: aload 5
        //   59: aload_1
        //   60: invokevirtual 48	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   63: aload_0
        //   64: getfield 18	com/google/firebase/database/connection/idl/zzk$zza$zza:zzrp	Landroid/os/IBinder;
        //   67: bipush 8
        //   69: aload 5
        //   71: aload 6
        //   73: iconst_0
        //   74: invokeinterface 60 5 0
        //   79: pop
        //   80: aload 6
        //   82: invokevirtual 63	android/os/Parcel:readException	()V
        //   85: aload 6
        //   87: invokevirtual 66	android/os/Parcel:recycle	()V
        //   90: aload 5
        //   92: invokevirtual 66	android/os/Parcel:recycle	()V
        //   95: return
        //   96: aconst_null
        //   97: astore_1
        //   98: goto -61 -> 37
        //   101: astore_1
        //   102: aload 6
        //   104: invokevirtual 66	android/os/Parcel:recycle	()V
        //   107: aload 5
        //   109: invokevirtual 66	android/os/Parcel:recycle	()V
        //   112: aload_1
        //   113: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	114	0	this	zza
        //   0	114	1	paramList	List<String>
        //   0	114	2	paramzzd	zzd
        //   0	114	3	paramzzm	zzm
        //   1	43	4	localObject	Object
        //   6	102	5	localParcel1	Parcel
        //   11	92	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   13	26	101	finally
        //   30	37	101	finally
        //   37	43	101	finally
        //   50	57	101	finally
        //   57	85	101	finally
      }
      
      public void refreshAuthToken()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnection");
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
      
      public void refreshAuthToken2(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnection");
          localParcel1.writeString(paramString);
          this.zzrp.transact(17, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void resume(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnection");
          localParcel1.writeString(paramString);
          this.zzrp.transact(15, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void setup(zzc paramzzc, zzh paramzzh, zzd paramzzd, zzl paramzzl)
        throws RemoteException
      {
        Object localObject = null;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnection");
            if (paramzzc != null)
            {
              localParcel1.writeInt(1);
              paramzzc.writeToParcel(localParcel1, 0);
              if (paramzzh != null)
              {
                paramzzc = paramzzh.asBinder();
                localParcel1.writeStrongBinder(paramzzc);
                if (paramzzd == null) {
                  break label152;
                }
                paramzzc = paramzzd.asBinder();
                localParcel1.writeStrongBinder(paramzzc);
                paramzzc = (zzc)localObject;
                if (paramzzl != null) {
                  paramzzc = paramzzl.asBinder();
                }
                localParcel1.writeStrongBinder(paramzzc);
                this.zzrp.transact(1, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramzzc = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          continue;
          label152:
          paramzzc = null;
        }
      }
      
      public void shutdown()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnection");
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
      public void unlisten(List<String> paramList, zzd paramzzd)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore 4
        //   9: aload_3
        //   10: ldc 33
        //   12: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_3
        //   16: aload_1
        //   17: invokevirtual 41	android/os/Parcel:writeStringList	(Ljava/util/List;)V
        //   20: aload_2
        //   21: ifnull +46 -> 67
        //   24: aload_2
        //   25: invokeinterface 45 1 0
        //   30: astore_1
        //   31: aload_3
        //   32: aload_1
        //   33: invokevirtual 48	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload_0
        //   37: getfield 18	com/google/firebase/database/connection/idl/zzk$zza$zza:zzrp	Landroid/os/IBinder;
        //   40: bipush 6
        //   42: aload_3
        //   43: aload 4
        //   45: iconst_0
        //   46: invokeinterface 60 5 0
        //   51: pop
        //   52: aload 4
        //   54: invokevirtual 63	android/os/Parcel:readException	()V
        //   57: aload 4
        //   59: invokevirtual 66	android/os/Parcel:recycle	()V
        //   62: aload_3
        //   63: invokevirtual 66	android/os/Parcel:recycle	()V
        //   66: return
        //   67: aconst_null
        //   68: astore_1
        //   69: goto -38 -> 31
        //   72: astore_1
        //   73: aload 4
        //   75: invokevirtual 66	android/os/Parcel:recycle	()V
        //   78: aload_3
        //   79: invokevirtual 66	android/os/Parcel:recycle	()V
        //   82: aload_1
        //   83: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	84	0	this	zza
        //   0	84	1	paramList	List<String>
        //   0	84	2	paramzzd	zzd
        //   3	76	3	localParcel1	Parcel
        //   7	67	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	20	72	finally
        //   24	31	72	finally
        //   31	57	72	finally
      }
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\database\connection\idl\zzk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */