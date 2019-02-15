package com.google.android.gms.maps.model.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.Tile;

public abstract interface zzi
  extends IInterface
{
  public abstract Tile getTile(int paramInt1, int paramInt2, int paramInt3)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzi
  {
    public zza()
    {
      attachInterface(this, "com.google.android.gms.maps.model.internal.ITileProviderDelegate");
    }
    
    public static zzi zzeq(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.ITileProviderDelegate");
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
        paramParcel2.writeString("com.google.android.gms.maps.model.internal.ITileProviderDelegate");
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.maps.model.internal.ITileProviderDelegate");
      paramParcel1 = getTile(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
      paramParcel2.writeNoException();
      if (paramParcel1 != null)
      {
        paramParcel2.writeInt(1);
        paramParcel1.writeToParcel(paramParcel2, 1);
        return true;
      }
      paramParcel2.writeInt(0);
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
      
      /* Error */
      public Tile getTile(int paramInt1, int paramInt2, int paramInt3)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 6
        //   10: aload 5
        //   12: ldc 33
        //   14: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload 5
        //   19: iload_1
        //   20: invokevirtual 41	android/os/Parcel:writeInt	(I)V
        //   23: aload 5
        //   25: iload_2
        //   26: invokevirtual 41	android/os/Parcel:writeInt	(I)V
        //   29: aload 5
        //   31: iload_3
        //   32: invokevirtual 41	android/os/Parcel:writeInt	(I)V
        //   35: aload_0
        //   36: getfield 18	com/google/android/gms/maps/model/internal/zzi$zza$zza:zzrp	Landroid/os/IBinder;
        //   39: iconst_1
        //   40: aload 5
        //   42: aload 6
        //   44: iconst_0
        //   45: invokeinterface 47 5 0
        //   50: pop
        //   51: aload 6
        //   53: invokevirtual 50	android/os/Parcel:readException	()V
        //   56: aload 6
        //   58: invokevirtual 54	android/os/Parcel:readInt	()I
        //   61: ifeq +31 -> 92
        //   64: getstatic 60	com/google/android/gms/maps/model/Tile:CREATOR	Landroid/os/Parcelable$Creator;
        //   67: aload 6
        //   69: invokeinterface 66 2 0
        //   74: checkcast 56	com/google/android/gms/maps/model/Tile
        //   77: astore 4
        //   79: aload 6
        //   81: invokevirtual 69	android/os/Parcel:recycle	()V
        //   84: aload 5
        //   86: invokevirtual 69	android/os/Parcel:recycle	()V
        //   89: aload 4
        //   91: areturn
        //   92: aconst_null
        //   93: astore 4
        //   95: goto -16 -> 79
        //   98: astore 4
        //   100: aload 6
        //   102: invokevirtual 69	android/os/Parcel:recycle	()V
        //   105: aload 5
        //   107: invokevirtual 69	android/os/Parcel:recycle	()V
        //   110: aload 4
        //   112: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	113	0	this	zza
        //   0	113	1	paramInt1	int
        //   0	113	2	paramInt2	int
        //   0	113	3	paramInt3	int
        //   77	17	4	localTile	Tile
        //   98	13	4	localObject	Object
        //   3	103	5	localParcel1	Parcel
        //   8	93	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	79	98	finally
      }
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\maps\model\internal\zzi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */