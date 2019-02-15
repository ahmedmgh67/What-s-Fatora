package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

import java.util.Collection;
import java.util.List;

public abstract interface LazyStringList
  extends ProtocolStringList
{
  public abstract void add(ByteString paramByteString);
  
  public abstract void add(byte[] paramArrayOfByte);
  
  public abstract boolean addAllByteArray(Collection<byte[]> paramCollection);
  
  public abstract boolean addAllByteString(Collection<? extends ByteString> paramCollection);
  
  public abstract List<byte[]> asByteArrayList();
  
  public abstract byte[] getByteArray(int paramInt);
  
  public abstract ByteString getByteString(int paramInt);
  
  public abstract List<?> getUnderlyingElements();
  
  public abstract LazyStringList getUnmodifiableView();
  
  public abstract void mergeFrom(LazyStringList paramLazyStringList);
  
  public abstract void set(int paramInt, ByteString paramByteString);
  
  public abstract void set(int paramInt, byte[] paramArrayOfByte);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\i18n\phonenumbers\repackaged\com\google\protobuf\LazyStringList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */