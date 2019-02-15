package com.crashlytics.android.core;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;

final class Utils
{
  private static final FilenameFilter ALL_FILES_FILTER = new FilenameFilter()
  {
    public boolean accept(File paramAnonymousFile, String paramAnonymousString)
    {
      return true;
    }
  };
  
  static int capFileCount(File paramFile, int paramInt, Comparator<File> paramComparator)
  {
    return capFileCount(paramFile, ALL_FILES_FILTER, paramInt, paramComparator);
  }
  
  static int capFileCount(File paramFile, FilenameFilter paramFilenameFilter, int paramInt, Comparator<File> paramComparator)
  {
    int j = 0;
    paramFile = paramFile.listFiles(paramFilenameFilter);
    int k;
    if (paramFile == null)
    {
      k = 0;
      return k;
    }
    int i = paramFile.length;
    Arrays.sort(paramFile, paramComparator);
    int m = paramFile.length;
    for (;;)
    {
      k = i;
      if (j >= m) {
        break;
      }
      paramFilenameFilter = paramFile[j];
      k = i;
      if (i <= paramInt) {
        break;
      }
      paramFilenameFilter.delete();
      i -= 1;
      j += 1;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\crashlytics\android\core\Utils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */