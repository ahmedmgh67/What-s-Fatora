package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

import java.util.NoSuchElementException;

class BoundedByteString
  extends LiteralByteString
{
  private final int bytesLength;
  private final int bytesOffset;
  
  BoundedByteString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    super(paramArrayOfByte);
    if (paramInt1 < 0) {
      throw new IllegalArgumentException(29 + "Offset too small: " + paramInt1);
    }
    if (paramInt2 < 0) {
      throw new IllegalArgumentException(29 + "Length too small: " + paramInt1);
    }
    if (paramInt1 + paramInt2 > paramArrayOfByte.length) {
      throw new IllegalArgumentException(48 + "Offset+Length too large: " + paramInt1 + "+" + paramInt2);
    }
    this.bytesOffset = paramInt1;
    this.bytesLength = paramInt2;
  }
  
  public byte byteAt(int paramInt)
  {
    if (paramInt < 0) {
      throw new ArrayIndexOutOfBoundsException(28 + "Index too small: " + paramInt);
    }
    if (paramInt >= size())
    {
      int i = size();
      throw new ArrayIndexOutOfBoundsException(41 + "Index too large: " + paramInt + ", " + i);
    }
    return this.bytes[(this.bytesOffset + paramInt)];
  }
  
  protected void copyToInternal(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
  {
    System.arraycopy(this.bytes, getOffsetIntoBytes() + paramInt1, paramArrayOfByte, paramInt2, paramInt3);
  }
  
  protected int getOffsetIntoBytes()
  {
    return this.bytesOffset;
  }
  
  public ByteString.ByteIterator iterator()
  {
    return new BoundedByteIterator(null);
  }
  
  public int size()
  {
    return this.bytesLength;
  }
  
  private class BoundedByteIterator
    implements ByteString.ByteIterator
  {
    private final int limit = this.position + BoundedByteString.this.size();
    private int position = BoundedByteString.this.getOffsetIntoBytes();
    
    private BoundedByteIterator() {}
    
    public boolean hasNext()
    {
      return this.position < this.limit;
    }
    
    public Byte next()
    {
      return Byte.valueOf(nextByte());
    }
    
    public byte nextByte()
    {
      if (this.position >= this.limit) {
        throw new NoSuchElementException();
      }
      byte[] arrayOfByte = BoundedByteString.this.bytes;
      int i = this.position;
      this.position = (i + 1);
      return arrayOfByte[i];
    }
    
    public void remove()
    {
      throw new UnsupportedOperationException();
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\i18n\phonenumbers\repackaged\com\google\protobuf\BoundedByteString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */