package com.google.api.services.urlshortener;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class UrlshortenerScopes
{
  public static final String URLSHORTENER = "https://www.googleapis.com/auth/urlshortener";
  
  public static Set<String> all()
  {
    HashSet localHashSet = new HashSet();
    localHashSet.add("https://www.googleapis.com/auth/urlshortener");
    return Collections.unmodifiableSet(localHashSet);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\services\urlshortener\UrlshortenerScopes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */