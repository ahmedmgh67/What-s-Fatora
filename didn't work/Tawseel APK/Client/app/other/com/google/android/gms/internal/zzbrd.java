package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zzd.zza;

public abstract interface zzbrd
  extends IInterface
{
  public abstract String zzA(Uri paramUri)
    throws RemoteException;
  
  public abstract String zzB(Uri paramUri)
    throws RemoteException;
  
  public abstract zzbrc zza(Uri paramUri, zzd paramzzd)
    throws RemoteException;
  
  public abstract zzbrc zza(Uri paramUri, zzd paramzzd, long paramLong)
    throws RemoteException;
  
  public abstract zzbrc zza(Uri paramUri, zzd paramzzd1, zzd paramzzd2)
    throws RemoteException;
  
  public abstract zzbrc zza(Uri paramUri, zzd paramzzd1, zzd paramzzd2, String paramString)
    throws RemoteException;
  
  public abstract zzbrc zza(Uri paramUri, zzd paramzzd, String paramString)
    throws RemoteException;
  
  public abstract zzbrc zza(Uri paramUri, zzd paramzzd1, String paramString, zzd paramzzd2, long paramLong, int paramInt, boolean paramBoolean)
    throws RemoteException;
  
  public abstract String zzabp()
    throws RemoteException;
  
  public abstract zzbrc zzb(Uri paramUri, zzd paramzzd)
    throws RemoteException;
  
  public abstract zzbrc zzb(Uri paramUri, zzd paramzzd, String paramString)
    throws RemoteException;
  
  public abstract zzbrc zzc(Uri paramUri, zzd paramzzd, String paramString)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzbrd
  {
    public static zzbrd zzfM(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.firebase.storage.network.INetworkRequestFactory");
      if ((localIInterface != null) && ((localIInterface instanceof zzbrd))) {
        return (zzbrd)localIInterface;
      }
      return new zza(paramIBinder);
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      Uri localUri;
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.firebase.storage.network.INetworkRequestFactory");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.google.firebase.storage.network.INetworkRequestFactory");
        if (paramParcel1.readInt() != 0)
        {
          localUri = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);
          paramParcel1 = zza(localUri, zzd.zza.zzcd(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          if (paramParcel1 == null) {
            break label203;
          }
        }
        for (paramParcel1 = paramParcel1.asBinder();; paramParcel1 = null)
        {
          paramParcel2.writeStrongBinder(paramParcel1);
          return true;
          localUri = null;
          break;
        }
      case 2: 
        paramParcel1.enforceInterface("com.google.firebase.storage.network.INetworkRequestFactory");
        if (paramParcel1.readInt() != 0)
        {
          localUri = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);
          paramParcel1 = zzb(localUri, zzd.zza.zzcd(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          if (paramParcel1 == null) {
            break label277;
          }
        }
        for (paramParcel1 = paramParcel1.asBinder();; paramParcel1 = null)
        {
          paramParcel2.writeStrongBinder(paramParcel1);
          return true;
          localUri = null;
          break;
        }
      case 3: 
        paramParcel1.enforceInterface("com.google.firebase.storage.network.INetworkRequestFactory");
        if (paramParcel1.readInt() != 0)
        {
          localUri = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);
          paramParcel1 = zza(localUri, zzd.zza.zzcd(paramParcel1.readStrongBinder()), paramParcel1.readLong());
          paramParcel2.writeNoException();
          if (paramParcel1 == null) {
            break label355;
          }
        }
        for (paramParcel1 = paramParcel1.asBinder();; paramParcel1 = null)
        {
          paramParcel2.writeStrongBinder(paramParcel1);
          return true;
          localUri = null;
          break;
        }
      case 4: 
        paramParcel1.enforceInterface("com.google.firebase.storage.network.INetworkRequestFactory");
        if (paramParcel1.readInt() != 0)
        {
          localUri = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);
          paramParcel1 = zza(localUri, zzd.zza.zzcd(paramParcel1.readStrongBinder()), paramParcel1.readString());
          paramParcel2.writeNoException();
          if (paramParcel1 == null) {
            break label433;
          }
        }
        for (paramParcel1 = paramParcel1.asBinder();; paramParcel1 = null)
        {
          paramParcel2.writeStrongBinder(paramParcel1);
          return true;
          localUri = null;
          break;
        }
      case 5: 
        paramParcel1.enforceInterface("com.google.firebase.storage.network.INetworkRequestFactory");
        boolean bool;
        if (paramParcel1.readInt() != 0)
        {
          localUri = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);
          zzd localzzd1 = zzd.zza.zzcd(paramParcel1.readStrongBinder());
          String str = paramParcel1.readString();
          zzd localzzd2 = zzd.zza.zzcd(paramParcel1.readStrongBinder());
          long l = paramParcel1.readLong();
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() == 0) {
            break label556;
          }
          bool = true;
          paramParcel1 = zza(localUri, localzzd1, str, localzzd2, l, paramInt1, bool);
          paramParcel2.writeNoException();
          if (paramParcel1 == null) {
            break label562;
          }
        }
        for (paramParcel1 = paramParcel1.asBinder();; paramParcel1 = null)
        {
          paramParcel2.writeStrongBinder(paramParcel1);
          return true;
          localUri = null;
          break;
          bool = false;
          break label510;
        }
      case 6: 
        paramParcel1.enforceInterface("com.google.firebase.storage.network.INetworkRequestFactory");
        if (paramParcel1.readInt() != 0)
        {
          localUri = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);
          paramParcel1 = zzb(localUri, zzd.zza.zzcd(paramParcel1.readStrongBinder()), paramParcel1.readString());
          paramParcel2.writeNoException();
          if (paramParcel1 == null) {
            break label640;
          }
        }
        for (paramParcel1 = paramParcel1.asBinder();; paramParcel1 = null)
        {
          paramParcel2.writeStrongBinder(paramParcel1);
          return true;
          localUri = null;
          break;
        }
      case 7: 
        paramParcel1.enforceInterface("com.google.firebase.storage.network.INetworkRequestFactory");
        if (paramParcel1.readInt() != 0)
        {
          localUri = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);
          paramParcel1 = zzc(localUri, zzd.zza.zzcd(paramParcel1.readStrongBinder()), paramParcel1.readString());
          paramParcel2.writeNoException();
          if (paramParcel1 == null) {
            break label718;
          }
        }
        for (paramParcel1 = paramParcel1.asBinder();; paramParcel1 = null)
        {
          paramParcel2.writeStrongBinder(paramParcel1);
          return true;
          localUri = null;
          break;
        }
      case 8: 
        paramParcel1.enforceInterface("com.google.firebase.storage.network.INetworkRequestFactory");
        if (paramParcel1.readInt() != 0)
        {
          localUri = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);
          paramParcel1 = zza(localUri, zzd.zza.zzcd(paramParcel1.readStrongBinder()), zzd.zza.zzcd(paramParcel1.readStrongBinder()), paramParcel1.readString());
          paramParcel2.writeNoException();
          if (paramParcel1 == null) {
            break label803;
          }
        }
        for (paramParcel1 = paramParcel1.asBinder();; paramParcel1 = null)
        {
          paramParcel2.writeStrongBinder(paramParcel1);
          return true;
          localUri = null;
          break;
        }
      case 9: 
        paramParcel1.enforceInterface("com.google.firebase.storage.network.INetworkRequestFactory");
        if (paramParcel1.readInt() != 0)
        {
          localUri = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);
          paramParcel1 = zza(localUri, zzd.zza.zzcd(paramParcel1.readStrongBinder()), zzd.zza.zzcd(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          if (paramParcel1 == null) {
            break label884;
          }
        }
        for (paramParcel1 = paramParcel1.asBinder();; paramParcel1 = null)
        {
          paramParcel2.writeStrongBinder(paramParcel1);
          return true;
          localUri = null;
          break;
        }
      case 10: 
        paramParcel1.enforceInterface("com.google.firebase.storage.network.INetworkRequestFactory");
        paramParcel1 = zzabp();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        return true;
      case 11: 
        label203:
        label277:
        label355:
        label433:
        label510:
        label556:
        label562:
        label640:
        label718:
        label803:
        label884:
        paramParcel1.enforceInterface("com.google.firebase.storage.network.INetworkRequestFactory");
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          paramParcel1 = zzA(paramParcel1);
          paramParcel2.writeNoException();
          paramParcel2.writeString(paramParcel1);
          return true;
        }
      }
      paramParcel1.enforceInterface("com.google.firebase.storage.network.INetworkRequestFactory");
      if (paramParcel1.readInt() != 0) {}
      for (paramParcel1 = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
      {
        paramParcel1 = zzB(paramParcel1);
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        return true;
      }
    }
    
    private static class zza
      implements zzbrd
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
      public String zzA(Uri paramUri)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 33
        //   11: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +48 -> 63
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 41	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 47	android/net/Uri:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/internal/zzbrd$zza$zza:zzrp	Landroid/os/IBinder;
        //   33: bipush 11
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 53 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 56	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 60	android/os/Parcel:readString	()Ljava/lang/String;
        //   52: astore_1
        //   53: aload_3
        //   54: invokevirtual 63	android/os/Parcel:recycle	()V
        //   57: aload_2
        //   58: invokevirtual 63	android/os/Parcel:recycle	()V
        //   61: aload_1
        //   62: areturn
        //   63: aload_2
        //   64: iconst_0
        //   65: invokevirtual 41	android/os/Parcel:writeInt	(I)V
        //   68: goto -39 -> 29
        //   71: astore_1
        //   72: aload_3
        //   73: invokevirtual 63	android/os/Parcel:recycle	()V
        //   76: aload_2
        //   77: invokevirtual 63	android/os/Parcel:recycle	()V
        //   80: aload_1
        //   81: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	82	0	this	zza
        //   0	82	1	paramUri	Uri
        //   3	74	2	localParcel1	Parcel
        //   7	66	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	71	finally
        //   18	29	71	finally
        //   29	53	71	finally
        //   63	68	71	finally
      }
      
      /* Error */
      public String zzB(Uri paramUri)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 33
        //   11: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +48 -> 63
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 41	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 47	android/net/Uri:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/internal/zzbrd$zza$zza:zzrp	Landroid/os/IBinder;
        //   33: bipush 12
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 53 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 56	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 60	android/os/Parcel:readString	()Ljava/lang/String;
        //   52: astore_1
        //   53: aload_3
        //   54: invokevirtual 63	android/os/Parcel:recycle	()V
        //   57: aload_2
        //   58: invokevirtual 63	android/os/Parcel:recycle	()V
        //   61: aload_1
        //   62: areturn
        //   63: aload_2
        //   64: iconst_0
        //   65: invokevirtual 41	android/os/Parcel:writeInt	(I)V
        //   68: goto -39 -> 29
        //   71: astore_1
        //   72: aload_3
        //   73: invokevirtual 63	android/os/Parcel:recycle	()V
        //   76: aload_2
        //   77: invokevirtual 63	android/os/Parcel:recycle	()V
        //   80: aload_1
        //   81: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	82	0	this	zza
        //   0	82	1	paramUri	Uri
        //   3	74	2	localParcel1	Parcel
        //   7	66	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	71	finally
        //   18	29	71	finally
        //   29	53	71	finally
        //   63	68	71	finally
      }
      
      public zzbrc zza(Uri paramUri, zzd paramzzd)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.firebase.storage.network.INetworkRequestFactory");
            if (paramUri != null)
            {
              localParcel1.writeInt(1);
              paramUri.writeToParcel(localParcel1, 0);
              if (paramzzd != null)
              {
                paramUri = paramzzd.asBinder();
                localParcel1.writeStrongBinder(paramUri);
                this.zzrp.transact(1, localParcel1, localParcel2, 0);
                localParcel2.readException();
                paramUri = zzbrc.zza.zzfL(localParcel2.readStrongBinder());
                return paramUri;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramUri = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public zzbrc zza(Uri paramUri, zzd paramzzd, long paramLong)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.firebase.storage.network.INetworkRequestFactory");
            if (paramUri != null)
            {
              localParcel1.writeInt(1);
              paramUri.writeToParcel(localParcel1, 0);
              if (paramzzd != null)
              {
                paramUri = paramzzd.asBinder();
                localParcel1.writeStrongBinder(paramUri);
                localParcel1.writeLong(paramLong);
                this.zzrp.transact(3, localParcel1, localParcel2, 0);
                localParcel2.readException();
                paramUri = zzbrc.zza.zzfL(localParcel2.readStrongBinder());
                return paramUri;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramUri = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public zzbrc zza(Uri paramUri, zzd paramzzd1, zzd paramzzd2)
        throws RemoteException
      {
        Object localObject = null;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.firebase.storage.network.INetworkRequestFactory");
            if (paramUri != null)
            {
              localParcel1.writeInt(1);
              paramUri.writeToParcel(localParcel1, 0);
              if (paramzzd1 != null)
              {
                paramUri = paramzzd1.asBinder();
                localParcel1.writeStrongBinder(paramUri);
                paramUri = (Uri)localObject;
                if (paramzzd2 != null) {
                  paramUri = paramzzd2.asBinder();
                }
                localParcel1.writeStrongBinder(paramUri);
                this.zzrp.transact(9, localParcel1, localParcel2, 0);
                localParcel2.readException();
                paramUri = zzbrc.zza.zzfL(localParcel2.readStrongBinder());
                return paramUri;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramUri = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public zzbrc zza(Uri paramUri, zzd paramzzd1, zzd paramzzd2, String paramString)
        throws RemoteException
      {
        Object localObject = null;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.firebase.storage.network.INetworkRequestFactory");
            if (paramUri != null)
            {
              localParcel1.writeInt(1);
              paramUri.writeToParcel(localParcel1, 0);
              if (paramzzd1 != null)
              {
                paramUri = paramzzd1.asBinder();
                localParcel1.writeStrongBinder(paramUri);
                paramUri = (Uri)localObject;
                if (paramzzd2 != null) {
                  paramUri = paramzzd2.asBinder();
                }
                localParcel1.writeStrongBinder(paramUri);
                localParcel1.writeString(paramString);
                this.zzrp.transact(8, localParcel1, localParcel2, 0);
                localParcel2.readException();
                paramUri = zzbrc.zza.zzfL(localParcel2.readStrongBinder());
                return paramUri;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramUri = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public zzbrc zza(Uri paramUri, zzd paramzzd, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.firebase.storage.network.INetworkRequestFactory");
            if (paramUri != null)
            {
              localParcel1.writeInt(1);
              paramUri.writeToParcel(localParcel1, 0);
              if (paramzzd != null)
              {
                paramUri = paramzzd.asBinder();
                localParcel1.writeStrongBinder(paramUri);
                localParcel1.writeString(paramString);
                this.zzrp.transact(4, localParcel1, localParcel2, 0);
                localParcel2.readException();
                paramUri = zzbrc.zza.zzfL(localParcel2.readStrongBinder());
                return paramUri;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramUri = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public zzbrc zza(Uri paramUri, zzd paramzzd1, String paramString, zzd paramzzd2, long paramLong, int paramInt, boolean paramBoolean)
        throws RemoteException
      {
        Object localObject = null;
        int i = 1;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.firebase.storage.network.INetworkRequestFactory");
            if (paramUri != null)
            {
              localParcel1.writeInt(1);
              paramUri.writeToParcel(localParcel1, 0);
              if (paramzzd1 != null)
              {
                paramUri = paramzzd1.asBinder();
                localParcel1.writeStrongBinder(paramUri);
                localParcel1.writeString(paramString);
                paramUri = (Uri)localObject;
                if (paramzzd2 != null) {
                  paramUri = paramzzd2.asBinder();
                }
                localParcel1.writeStrongBinder(paramUri);
                localParcel1.writeLong(paramLong);
                localParcel1.writeInt(paramInt);
                if (!paramBoolean) {
                  break label184;
                }
                paramInt = i;
                localParcel1.writeInt(paramInt);
                this.zzrp.transact(5, localParcel1, localParcel2, 0);
                localParcel2.readException();
                paramUri = zzbrc.zza.zzfL(localParcel2.readStrongBinder());
                return paramUri;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramUri = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          continue;
          label184:
          paramInt = 0;
        }
      }
      
      public String zzabp()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.firebase.storage.network.INetworkRequestFactory");
          this.zzrp.transact(10, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public zzbrc zzb(Uri paramUri, zzd paramzzd)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.firebase.storage.network.INetworkRequestFactory");
            if (paramUri != null)
            {
              localParcel1.writeInt(1);
              paramUri.writeToParcel(localParcel1, 0);
              if (paramzzd != null)
              {
                paramUri = paramzzd.asBinder();
                localParcel1.writeStrongBinder(paramUri);
                this.zzrp.transact(2, localParcel1, localParcel2, 0);
                localParcel2.readException();
                paramUri = zzbrc.zza.zzfL(localParcel2.readStrongBinder());
                return paramUri;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramUri = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public zzbrc zzb(Uri paramUri, zzd paramzzd, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.firebase.storage.network.INetworkRequestFactory");
            if (paramUri != null)
            {
              localParcel1.writeInt(1);
              paramUri.writeToParcel(localParcel1, 0);
              if (paramzzd != null)
              {
                paramUri = paramzzd.asBinder();
                localParcel1.writeStrongBinder(paramUri);
                localParcel1.writeString(paramString);
                this.zzrp.transact(6, localParcel1, localParcel2, 0);
                localParcel2.readException();
                paramUri = zzbrc.zza.zzfL(localParcel2.readStrongBinder());
                return paramUri;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramUri = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public zzbrc zzc(Uri paramUri, zzd paramzzd, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.firebase.storage.network.INetworkRequestFactory");
            if (paramUri != null)
            {
              localParcel1.writeInt(1);
              paramUri.writeToParcel(localParcel1, 0);
              if (paramzzd != null)
              {
                paramUri = paramzzd.asBinder();
                localParcel1.writeStrongBinder(paramUri);
                localParcel1.writeString(paramString);
                this.zzrp.transact(7, localParcel1, localParcel2, 0);
                localParcel2.readException();
                paramUri = zzbrc.zza.zzfL(localParcel2.readStrongBinder());
                return paramUri;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramUri = null;
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


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\internal\zzbrd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */