package com.google.api.client.util.escape;

final class Platform
{
  private static final ThreadLocal<char[]> DEST_TL = new ThreadLocal()
  {
    protected char[] initialValue()
    {
      return new char['Ѐ'];
    }
  };
  
  static char[] charBufferFromThreadLocal()
  {
    return (char[])DEST_TL.get();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\escape\Platform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */