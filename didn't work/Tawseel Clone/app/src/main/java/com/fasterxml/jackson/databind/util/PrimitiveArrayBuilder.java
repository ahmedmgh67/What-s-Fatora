package com.fasterxml.jackson.databind.util;

public abstract class PrimitiveArrayBuilder<T>
{
  static final int INITIAL_CHUNK_SIZE = 12;
  static final int MAX_CHUNK_SIZE = 262144;
  static final int SMALL_CHUNK_SIZE = 16384;
  protected Node<T> _bufferHead;
  protected Node<T> _bufferTail;
  protected int _bufferedEntryCount;
  protected T _freeBuffer;
  
  protected abstract T _constructArray(int paramInt);
  
  protected void _reset()
  {
    if (this._bufferTail != null) {
      this._freeBuffer = this._bufferTail.getData();
    }
    this._bufferTail = null;
    this._bufferHead = null;
    this._bufferedEntryCount = 0;
  }
  
  public final T appendCompletedChunk(T paramT, int paramInt)
  {
    paramT = new Node(paramT, paramInt);
    if (this._bufferHead == null)
    {
      this._bufferTail = paramT;
      this._bufferHead = paramT;
      this._bufferedEntryCount += paramInt;
      if (paramInt >= 16384) {
        break label70;
      }
      paramInt += paramInt;
    }
    for (;;)
    {
      return (T)_constructArray(paramInt);
      this._bufferTail.linkNext(paramT);
      this._bufferTail = paramT;
      break;
      label70:
      paramInt += (paramInt >> 2);
    }
  }
  
  public int bufferedSize()
  {
    return this._bufferedEntryCount;
  }
  
  public T completeAndClearBuffer(T paramT, int paramInt)
  {
    int j = paramInt + this._bufferedEntryCount;
    Object localObject = _constructArray(j);
    int i = 0;
    for (Node localNode = this._bufferHead; localNode != null; localNode = localNode.next()) {
      i = localNode.copyData(localObject, i);
    }
    System.arraycopy(paramT, 0, localObject, i, paramInt);
    paramInt = i + paramInt;
    if (paramInt != j) {
      throw new IllegalStateException("Should have gotten " + j + " entries, got " + paramInt);
    }
    return (T)localObject;
  }
  
  public T resetAndStart()
  {
    _reset();
    if (this._freeBuffer == null) {
      return (T)_constructArray(12);
    }
    return (T)this._freeBuffer;
  }
  
  static final class Node<T>
  {
    final T _data;
    final int _dataLength;
    Node<T> _next;
    
    public Node(T paramT, int paramInt)
    {
      this._data = paramT;
      this._dataLength = paramInt;
    }
    
    public int copyData(T paramT, int paramInt)
    {
      System.arraycopy(this._data, 0, paramT, paramInt, this._dataLength);
      return paramInt + this._dataLength;
    }
    
    public T getData()
    {
      return (T)this._data;
    }
    
    public void linkNext(Node<T> paramNode)
    {
      if (this._next != null) {
        throw new IllegalStateException();
      }
      this._next = paramNode;
    }
    
    public Node<T> next()
    {
      return this._next;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\databind\util\PrimitiveArrayBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */