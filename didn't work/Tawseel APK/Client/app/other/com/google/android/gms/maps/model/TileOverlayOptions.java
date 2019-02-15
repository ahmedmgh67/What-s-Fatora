package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.maps.model.internal.zzi;
import com.google.android.gms.maps.model.internal.zzi.zza;

public final class TileOverlayOptions
  extends zza
{
  public static final Parcelable.Creator<TileOverlayOptions> CREATOR = new zzp();
  private final int mVersionCode;
  private float zzboJ;
  private boolean zzboK = true;
  private float zzboR = 0.0F;
  private zzi zzbpq;
  private TileProvider zzbpr;
  private boolean zzbps = true;
  
  public TileOverlayOptions()
  {
    this.mVersionCode = 1;
  }
  
  TileOverlayOptions(int paramInt, IBinder paramIBinder, boolean paramBoolean1, float paramFloat1, boolean paramBoolean2, float paramFloat2)
  {
    this.mVersionCode = paramInt;
    this.zzbpq = zzi.zza.zzeq(paramIBinder);
    if (this.zzbpq == null) {}
    for (paramIBinder = null;; paramIBinder = new TileProvider()
        {
          private final zzi zzbpt = TileOverlayOptions.zza(TileOverlayOptions.this);
          
          public Tile getTile(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
          {
            try
            {
              Tile localTile = this.zzbpt.getTile(paramAnonymousInt1, paramAnonymousInt2, paramAnonymousInt3);
              return localTile;
            }
            catch (RemoteException localRemoteException) {}
            return null;
          }
        })
    {
      this.zzbpr = paramIBinder;
      this.zzboK = paramBoolean1;
      this.zzboJ = paramFloat1;
      this.zzbps = paramBoolean2;
      this.zzboR = paramFloat2;
      return;
    }
  }
  
  public TileOverlayOptions fadeIn(boolean paramBoolean)
  {
    this.zzbps = paramBoolean;
    return this;
  }
  
  public boolean getFadeIn()
  {
    return this.zzbps;
  }
  
  public TileProvider getTileProvider()
  {
    return this.zzbpr;
  }
  
  public float getTransparency()
  {
    return this.zzboR;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public float getZIndex()
  {
    return this.zzboJ;
  }
  
  public boolean isVisible()
  {
    return this.zzboK;
  }
  
  public TileOverlayOptions tileProvider(final TileProvider paramTileProvider)
  {
    this.zzbpr = paramTileProvider;
    if (this.zzbpr == null) {}
    for (paramTileProvider = null;; paramTileProvider = new zzi.zza()
        {
          public Tile getTile(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
          {
            return paramTileProvider.getTile(paramAnonymousInt1, paramAnonymousInt2, paramAnonymousInt3);
          }
        })
    {
      this.zzbpq = paramTileProvider;
      return this;
    }
  }
  
  public TileOverlayOptions transparency(float paramFloat)
  {
    if ((paramFloat >= 0.0F) && (paramFloat <= 1.0F)) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zzb(bool, "Transparency must be in the range [0..1]");
      this.zzboR = paramFloat;
      return this;
    }
  }
  
  public TileOverlayOptions visible(boolean paramBoolean)
  {
    this.zzboK = paramBoolean;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzp.zza(this, paramParcel, paramInt);
  }
  
  public TileOverlayOptions zIndex(float paramFloat)
  {
    this.zzboJ = paramFloat;
    return this;
  }
  
  IBinder zzIX()
  {
    return this.zzbpq.asBinder();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\maps\model\TileOverlayOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */