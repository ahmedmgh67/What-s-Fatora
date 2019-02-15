package com.crashlytics.android.core;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.common.ResponseParser;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class DefaultCreateReportSpiCall
  extends AbstractSpiCall
  implements CreateReportSpiCall
{
  static final String FILE_CONTENT_TYPE = "application/octet-stream";
  static final String FILE_PARAM = "report[file]";
  static final String IDENTIFIER_PARAM = "report[identifier]";
  static final String MULTI_FILE_PARAM = "report[file";
  
  public DefaultCreateReportSpiCall(Kit paramKit, String paramString1, String paramString2, HttpRequestFactory paramHttpRequestFactory)
  {
    super(paramKit, paramString1, paramString2, paramHttpRequestFactory, HttpMethod.POST);
  }
  
  DefaultCreateReportSpiCall(Kit paramKit, String paramString1, String paramString2, HttpRequestFactory paramHttpRequestFactory, HttpMethod paramHttpMethod)
  {
    super(paramKit, paramString1, paramString2, paramHttpRequestFactory, paramHttpMethod);
  }
  
  private HttpRequest applyHeadersTo(HttpRequest paramHttpRequest, CreateReportRequest paramCreateReportRequest)
  {
    paramHttpRequest = paramHttpRequest.header("X-CRASHLYTICS-API-KEY", paramCreateReportRequest.apiKey).header("X-CRASHLYTICS-API-CLIENT-TYPE", "android").header("X-CRASHLYTICS-API-CLIENT-VERSION", this.kit.getVersion());
    paramCreateReportRequest = paramCreateReportRequest.report.getCustomHeaders().entrySet().iterator();
    while (paramCreateReportRequest.hasNext()) {
      paramHttpRequest = paramHttpRequest.header((Map.Entry)paramCreateReportRequest.next());
    }
    return paramHttpRequest;
  }
  
  private HttpRequest applyMultipartDataTo(HttpRequest paramHttpRequest, Report paramReport)
  {
    paramHttpRequest.part("report[identifier]", paramReport.getIdentifier());
    HttpRequest localHttpRequest;
    if (paramReport.getFiles().length == 1)
    {
      Fabric.getLogger().d("CrashlyticsCore", "Adding single file " + paramReport.getFileName() + " to report " + paramReport.getIdentifier());
      localHttpRequest = paramHttpRequest.part("report[file]", paramReport.getFileName(), "application/octet-stream", paramReport.getFile());
      return localHttpRequest;
    }
    int j = 0;
    File[] arrayOfFile = paramReport.getFiles();
    int k = arrayOfFile.length;
    int i = 0;
    for (;;)
    {
      localHttpRequest = paramHttpRequest;
      if (i >= k) {
        break;
      }
      localHttpRequest = arrayOfFile[i];
      Fabric.getLogger().d("CrashlyticsCore", "Adding file " + localHttpRequest.getName() + " to report " + paramReport.getIdentifier());
      paramHttpRequest.part("report[file" + j + "]", localHttpRequest.getName(), "application/octet-stream", localHttpRequest);
      j += 1;
      i += 1;
    }
  }
  
  public boolean invoke(CreateReportRequest paramCreateReportRequest)
  {
    paramCreateReportRequest = applyMultipartDataTo(applyHeadersTo(getHttpRequest(), paramCreateReportRequest), paramCreateReportRequest.report);
    Fabric.getLogger().d("CrashlyticsCore", "Sending report to: " + getUrl());
    int i = paramCreateReportRequest.code();
    Fabric.getLogger().d("CrashlyticsCore", "Create report request ID: " + paramCreateReportRequest.header("X-REQUEST-ID"));
    Fabric.getLogger().d("CrashlyticsCore", "Result was: " + i);
    return ResponseParser.parse(i) == 0;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\crashlytics\android\core\DefaultCreateReportSpiCall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */