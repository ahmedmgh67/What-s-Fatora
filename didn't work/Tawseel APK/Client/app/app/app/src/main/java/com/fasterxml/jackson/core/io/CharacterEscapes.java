package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.SerializableString;
import java.io.Serializable;
import java.util.Arrays;

public abstract class CharacterEscapes
  implements Serializable
{
  public static final int ESCAPE_CUSTOM = -2;
  public static final int ESCAPE_NONE = 0;
  public static final int ESCAPE_STANDARD = -1;
  
  public static int[] standardAsciiEscapesForJSON()
  {
    int[] arrayOfInt = CharTypes.get7BitOutputEscapes();
    return Arrays.copyOf(arrayOfInt, arrayOfInt.length);
  }
  
  public abstract int[] getEscapeCodesForAscii();
  
  public abstract SerializableString getEscapeSequence(int paramInt);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\fasterxml\jackson\core\io\CharacterEscapes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */