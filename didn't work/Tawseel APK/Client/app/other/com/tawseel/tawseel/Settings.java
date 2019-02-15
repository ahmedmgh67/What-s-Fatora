package com.tawseel.tawseel;

public class Settings
{
  public static String AndroidRiderAppURL;
  public static String Contact_Us_Number;
  public static double DriversSearchRadius;
  public static int MinDistance;
  public static String PhoneCountryCode;
  public static String TermsUrl;
  public static int cacheExpiration;
  public static double cancellationPenaltyPeriodInSeconds;
  public static String cloudFunctionsURL;
  public static long delay;
  public static double farePerKMInSAR;
  public static double farePerMinInSAR;
  public static double freeKMs;
  public static boolean isFetched = false;
  public static double maximumConcurrentRequestsPerOrder;
  public static double maximumPercentageOfEstimatedFare;
  public static double maximumRequestRetriesPerOrder;
  public static long minimumBuildNumber;
  public static double minimumFareInSAR;
  public static int referredByDiscountPercentage;
  public static long remainingTimeSyncIntervalInSeconds = 420L;
  public static long remainingTimeUpdateIntervalInSeconds = 60L;
  public static int requestTimeOut = 20;
  
  static
  {
    AndroidRiderAppURL = "http://www.enozom.com";
    MinDistance = 5;
    TermsUrl = "http://tawseel.sa/privacy/";
    PhoneCountryCode = "SA";
    Contact_Us_Number = "";
    DriversSearchRadius = 5.0D;
    delay = 10000L;
    minimumFareInSAR = 15.0D;
    farePerKMInSAR = 0.7D;
    farePerMinInSAR = 0.5D;
    freeKMs = 5.0D;
    maximumPercentageOfEstimatedFare = 25.0D;
    maximumConcurrentRequestsPerOrder = 2.0D;
    maximumRequestRetriesPerOrder = 6.0D;
    cancellationPenaltyPeriodInSeconds = 30.0D;
    cacheExpiration = 43200;
    cloudFunctionsURL = "https://us-central1-tawseel-production.cloudfunctions.net";
    minimumBuildNumber = 0L;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\Settings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */