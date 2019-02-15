package com.google.api.client.http;

import com.google.api.client.util.Beta;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sleeper;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public final class HttpRequest
{
  public static final int DEFAULT_NUMBER_OF_RETRIES = 10;
  public static final String USER_AGENT_SUFFIX = "Google-HTTP-Java-Client/1.22.0 (gzip)";
  public static final String VERSION = "1.22.0";
  @Deprecated
  @Beta
  private BackOffPolicy backOffPolicy;
  private int connectTimeout = 20000;
  private HttpContent content;
  private int contentLoggingLimit = 16384;
  private boolean curlLoggingEnabled = true;
  private HttpEncoding encoding;
  private HttpExecuteInterceptor executeInterceptor;
  private boolean followRedirects = true;
  private HttpHeaders headers = new HttpHeaders();
  @Beta
  private HttpIOExceptionHandler ioExceptionHandler;
  private boolean loggingEnabled = true;
  private int numRetries = 10;
  private ObjectParser objectParser;
  private int readTimeout = 20000;
  private String requestMethod;
  private HttpHeaders responseHeaders = new HttpHeaders();
  private HttpResponseInterceptor responseInterceptor;
  @Deprecated
  @Beta
  private boolean retryOnExecuteIOException = false;
  private Sleeper sleeper = Sleeper.DEFAULT;
  private boolean suppressUserAgentSuffix;
  private boolean throwExceptionOnExecuteError = true;
  private final HttpTransport transport;
  private HttpUnsuccessfulResponseHandler unsuccessfulResponseHandler;
  private GenericUrl url;
  
  HttpRequest(HttpTransport paramHttpTransport, String paramString)
  {
    this.transport = paramHttpTransport;
    setRequestMethod(paramString);
  }
  
  /* Error */
  public HttpResponse execute()
    throws java.io.IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 69	com/google/api/client/http/HttpRequest:numRetries	I
    //   4: iflt +926 -> 930
    //   7: iconst_1
    //   8: istore 4
    //   10: iload 4
    //   12: invokestatic 111	com/google/api/client/util/Preconditions:checkArgument	(Z)V
    //   15: aload_0
    //   16: getfield 69	com/google/api/client/http/HttpRequest:numRetries	I
    //   19: istore_1
    //   20: aload_0
    //   21: getfield 113	com/google/api/client/http/HttpRequest:backOffPolicy	Lcom/google/api/client/http/BackOffPolicy;
    //   24: ifnull +12 -> 36
    //   27: aload_0
    //   28: getfield 113	com/google/api/client/http/HttpRequest:backOffPolicy	Lcom/google/api/client/http/BackOffPolicy;
    //   31: invokeinterface 118 1 0
    //   36: aconst_null
    //   37: astore 11
    //   39: aload_0
    //   40: getfield 120	com/google/api/client/http/HttpRequest:requestMethod	Ljava/lang/String;
    //   43: invokestatic 124	com/google/api/client/util/Preconditions:checkNotNull	(Ljava/lang/Object;)Ljava/lang/Object;
    //   46: pop
    //   47: aload_0
    //   48: getfield 126	com/google/api/client/http/HttpRequest:url	Lcom/google/api/client/http/GenericUrl;
    //   51: invokestatic 124	com/google/api/client/util/Preconditions:checkNotNull	(Ljava/lang/Object;)Ljava/lang/Object;
    //   54: pop
    //   55: aload 11
    //   57: ifnull +8 -> 65
    //   60: aload 11
    //   62: invokevirtual 131	com/google/api/client/http/HttpResponse:ignore	()V
    //   65: aconst_null
    //   66: astore 13
    //   68: aconst_null
    //   69: astore 14
    //   71: aload_0
    //   72: getfield 133	com/google/api/client/http/HttpRequest:executeInterceptor	Lcom/google/api/client/http/HttpExecuteInterceptor;
    //   75: ifnull +13 -> 88
    //   78: aload_0
    //   79: getfield 133	com/google/api/client/http/HttpRequest:executeInterceptor	Lcom/google/api/client/http/HttpExecuteInterceptor;
    //   82: aload_0
    //   83: invokeinterface 139 2 0
    //   88: aload_0
    //   89: getfield 126	com/google/api/client/http/HttpRequest:url	Lcom/google/api/client/http/GenericUrl;
    //   92: invokevirtual 145	com/google/api/client/http/GenericUrl:build	()Ljava/lang/String;
    //   95: astore 16
    //   97: aload_0
    //   98: getfield 94	com/google/api/client/http/HttpRequest:transport	Lcom/google/api/client/http/HttpTransport;
    //   101: aload_0
    //   102: getfield 120	com/google/api/client/http/HttpRequest:requestMethod	Ljava/lang/String;
    //   105: aload 16
    //   107: invokevirtual 151	com/google/api/client/http/HttpTransport:buildRequest	(Ljava/lang/String;Ljava/lang/String;)Lcom/google/api/client/http/LowLevelHttpRequest;
    //   110: astore 17
    //   112: getstatic 155	com/google/api/client/http/HttpTransport:LOGGER	Ljava/util/logging/Logger;
    //   115: astore 15
    //   117: aload_0
    //   118: getfield 73	com/google/api/client/http/HttpRequest:loggingEnabled	Z
    //   121: ifeq +815 -> 936
    //   124: aload 15
    //   126: getstatic 161	java/util/logging/Level:CONFIG	Ljava/util/logging/Level;
    //   129: invokevirtual 167	java/util/logging/Logger:isLoggable	(Ljava/util/logging/Level;)Z
    //   132: ifeq +804 -> 936
    //   135: iconst_1
    //   136: istore_2
    //   137: aconst_null
    //   138: astore 11
    //   140: aconst_null
    //   141: astore 9
    //   143: aload 9
    //   145: astore 10
    //   147: iload_2
    //   148: ifeq +121 -> 269
    //   151: new 169	java/lang/StringBuilder
    //   154: dup
    //   155: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   158: astore 12
    //   160: aload 12
    //   162: ldc -84
    //   164: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   167: getstatic 181	com/google/api/client/util/StringUtils:LINE_SEPARATOR	Ljava/lang/String;
    //   170: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: pop
    //   174: aload 12
    //   176: aload_0
    //   177: getfield 120	com/google/api/client/http/HttpRequest:requestMethod	Ljava/lang/String;
    //   180: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: bipush 32
    //   185: invokevirtual 184	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   188: aload 16
    //   190: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   193: getstatic 181	com/google/api/client/util/StringUtils:LINE_SEPARATOR	Ljava/lang/String;
    //   196: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   199: pop
    //   200: aload 9
    //   202: astore 10
    //   204: aload 12
    //   206: astore 11
    //   208: aload_0
    //   209: getfield 75	com/google/api/client/http/HttpRequest:curlLoggingEnabled	Z
    //   212: ifeq +57 -> 269
    //   215: new 169	java/lang/StringBuilder
    //   218: dup
    //   219: ldc -70
    //   221: invokespecial 189	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   224: astore 9
    //   226: aload 9
    //   228: astore 10
    //   230: aload 12
    //   232: astore 11
    //   234: aload_0
    //   235: getfield 120	com/google/api/client/http/HttpRequest:requestMethod	Ljava/lang/String;
    //   238: ldc -65
    //   240: invokevirtual 197	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   243: ifne +26 -> 269
    //   246: aload 9
    //   248: ldc -57
    //   250: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   253: aload_0
    //   254: getfield 120	com/google/api/client/http/HttpRequest:requestMethod	Ljava/lang/String;
    //   257: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   260: pop
    //   261: aload 12
    //   263: astore 11
    //   265: aload 9
    //   267: astore 10
    //   269: aload_0
    //   270: getfield 65	com/google/api/client/http/HttpRequest:headers	Lcom/google/api/client/http/HttpHeaders;
    //   273: invokevirtual 202	com/google/api/client/http/HttpHeaders:getUserAgent	()Ljava/lang/String;
    //   276: astore 9
    //   278: aload_0
    //   279: getfield 204	com/google/api/client/http/HttpRequest:suppressUserAgentSuffix	Z
    //   282: ifne +18 -> 300
    //   285: aload 9
    //   287: ifnonnull +654 -> 941
    //   290: aload_0
    //   291: getfield 65	com/google/api/client/http/HttpRequest:headers	Lcom/google/api/client/http/HttpHeaders;
    //   294: ldc 13
    //   296: invokevirtual 208	com/google/api/client/http/HttpHeaders:setUserAgent	(Ljava/lang/String;)Lcom/google/api/client/http/HttpHeaders;
    //   299: pop
    //   300: aload_0
    //   301: getfield 65	com/google/api/client/http/HttpRequest:headers	Lcom/google/api/client/http/HttpHeaders;
    //   304: aload 11
    //   306: aload 10
    //   308: aload 15
    //   310: aload 17
    //   312: invokestatic 212	com/google/api/client/http/HttpHeaders:serializeHeaders	(Lcom/google/api/client/http/HttpHeaders;Ljava/lang/StringBuilder;Ljava/lang/StringBuilder;Ljava/util/logging/Logger;Lcom/google/api/client/http/LowLevelHttpRequest;)V
    //   315: aload_0
    //   316: getfield 204	com/google/api/client/http/HttpRequest:suppressUserAgentSuffix	Z
    //   319: ifne +13 -> 332
    //   322: aload_0
    //   323: getfield 65	com/google/api/client/http/HttpRequest:headers	Lcom/google/api/client/http/HttpHeaders;
    //   326: aload 9
    //   328: invokevirtual 208	com/google/api/client/http/HttpHeaders:setUserAgent	(Ljava/lang/String;)Lcom/google/api/client/http/HttpHeaders;
    //   331: pop
    //   332: aload_0
    //   333: getfield 214	com/google/api/client/http/HttpRequest:content	Lcom/google/api/client/http/HttpContent;
    //   336: astore 12
    //   338: aload 12
    //   340: ifnull +15 -> 355
    //   343: aload_0
    //   344: getfield 214	com/google/api/client/http/HttpRequest:content	Lcom/google/api/client/http/HttpContent;
    //   347: invokeinterface 220 1 0
    //   352: ifeq +626 -> 978
    //   355: iconst_1
    //   356: istore_3
    //   357: aload 12
    //   359: astore 9
    //   361: aload 12
    //   363: ifnull +302 -> 665
    //   366: aload_0
    //   367: getfield 214	com/google/api/client/http/HttpRequest:content	Lcom/google/api/client/http/HttpContent;
    //   370: invokeinterface 223 1 0
    //   375: astore 18
    //   377: aload 12
    //   379: astore 9
    //   381: iload_2
    //   382: ifeq +24 -> 406
    //   385: new 225	com/google/api/client/util/LoggingStreamingContent
    //   388: dup
    //   389: aload 12
    //   391: getstatic 155	com/google/api/client/http/HttpTransport:LOGGER	Ljava/util/logging/Logger;
    //   394: getstatic 161	java/util/logging/Level:CONFIG	Ljava/util/logging/Level;
    //   397: aload_0
    //   398: getfield 71	com/google/api/client/http/HttpRequest:contentLoggingLimit	I
    //   401: invokespecial 228	com/google/api/client/util/LoggingStreamingContent:<init>	(Lcom/google/api/client/util/StreamingContent;Ljava/util/logging/Logger;Ljava/util/logging/Level;I)V
    //   404: astore 9
    //   406: aload_0
    //   407: getfield 230	com/google/api/client/http/HttpRequest:encoding	Lcom/google/api/client/http/HttpEncoding;
    //   410: ifnonnull +573 -> 983
    //   413: aconst_null
    //   414: astore 12
    //   416: aload_0
    //   417: getfield 214	com/google/api/client/http/HttpRequest:content	Lcom/google/api/client/http/HttpContent;
    //   420: invokeinterface 234 1 0
    //   425: lstore 7
    //   427: iload_2
    //   428: ifeq +196 -> 624
    //   431: aload 18
    //   433: ifnull +75 -> 508
    //   436: new 169	java/lang/StringBuilder
    //   439: dup
    //   440: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   443: ldc -20
    //   445: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   448: aload 18
    //   450: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   453: invokevirtual 239	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   456: astore 19
    //   458: aload 11
    //   460: aload 19
    //   462: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   465: getstatic 181	com/google/api/client/util/StringUtils:LINE_SEPARATOR	Ljava/lang/String;
    //   468: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   471: pop
    //   472: aload 10
    //   474: ifnull +34 -> 508
    //   477: aload 10
    //   479: new 169	java/lang/StringBuilder
    //   482: dup
    //   483: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   486: ldc -15
    //   488: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   491: aload 19
    //   493: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   496: ldc -13
    //   498: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   501: invokevirtual 239	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   504: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   507: pop
    //   508: aload 12
    //   510: ifnull +75 -> 585
    //   513: new 169	java/lang/StringBuilder
    //   516: dup
    //   517: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   520: ldc -11
    //   522: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   525: aload 12
    //   527: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   530: invokevirtual 239	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   533: astore 19
    //   535: aload 11
    //   537: aload 19
    //   539: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   542: getstatic 181	com/google/api/client/util/StringUtils:LINE_SEPARATOR	Ljava/lang/String;
    //   545: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   548: pop
    //   549: aload 10
    //   551: ifnull +34 -> 585
    //   554: aload 10
    //   556: new 169	java/lang/StringBuilder
    //   559: dup
    //   560: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   563: ldc -15
    //   565: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   568: aload 19
    //   570: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   573: ldc -13
    //   575: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   578: invokevirtual 239	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   581: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   584: pop
    //   585: lload 7
    //   587: lconst_0
    //   588: lcmp
    //   589: iflt +35 -> 624
    //   592: aload 11
    //   594: new 169	java/lang/StringBuilder
    //   597: dup
    //   598: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   601: ldc -9
    //   603: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   606: lload 7
    //   608: invokevirtual 250	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   611: invokevirtual 239	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   614: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   617: getstatic 181	com/google/api/client/util/StringUtils:LINE_SEPARATOR	Ljava/lang/String;
    //   620: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   623: pop
    //   624: aload 10
    //   626: ifnull +11 -> 637
    //   629: aload 10
    //   631: ldc -4
    //   633: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   636: pop
    //   637: aload 17
    //   639: aload 18
    //   641: invokevirtual 257	com/google/api/client/http/LowLevelHttpRequest:setContentType	(Ljava/lang/String;)V
    //   644: aload 17
    //   646: aload 12
    //   648: invokevirtual 260	com/google/api/client/http/LowLevelHttpRequest:setContentEncoding	(Ljava/lang/String;)V
    //   651: aload 17
    //   653: lload 7
    //   655: invokevirtual 264	com/google/api/client/http/LowLevelHttpRequest:setContentLength	(J)V
    //   658: aload 17
    //   660: aload 9
    //   662: invokevirtual 268	com/google/api/client/http/LowLevelHttpRequest:setStreamingContent	(Lcom/google/api/client/util/StreamingContent;)V
    //   665: iload_2
    //   666: ifeq +75 -> 741
    //   669: aload 15
    //   671: aload 11
    //   673: invokevirtual 239	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   676: invokevirtual 271	java/util/logging/Logger:config	(Ljava/lang/String;)V
    //   679: aload 10
    //   681: ifnull +60 -> 741
    //   684: aload 10
    //   686: ldc_w 273
    //   689: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   692: pop
    //   693: aload 10
    //   695: aload 16
    //   697: ldc -13
    //   699: ldc_w 275
    //   702: invokevirtual 279	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   705: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   708: pop
    //   709: aload 10
    //   711: ldc -13
    //   713: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   716: pop
    //   717: aload 9
    //   719: ifnull +12 -> 731
    //   722: aload 10
    //   724: ldc_w 281
    //   727: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   730: pop
    //   731: aload 15
    //   733: aload 10
    //   735: invokevirtual 239	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   738: invokevirtual 271	java/util/logging/Logger:config	(Ljava/lang/String;)V
    //   741: iload_3
    //   742: ifeq +289 -> 1031
    //   745: iload_1
    //   746: ifle +285 -> 1031
    //   749: iconst_1
    //   750: istore 6
    //   752: aload 17
    //   754: aload_0
    //   755: getfield 77	com/google/api/client/http/HttpRequest:connectTimeout	I
    //   758: aload_0
    //   759: getfield 79	com/google/api/client/http/HttpRequest:readTimeout	I
    //   762: invokevirtual 285	com/google/api/client/http/LowLevelHttpRequest:setTimeout	(II)V
    //   765: aload 17
    //   767: invokevirtual 288	com/google/api/client/http/LowLevelHttpRequest:execute	()Lcom/google/api/client/http/LowLevelHttpResponse;
    //   770: astore 10
    //   772: new 128	com/google/api/client/http/HttpResponse
    //   775: dup
    //   776: aload_0
    //   777: aload 10
    //   779: invokespecial 291	com/google/api/client/http/HttpResponse:<init>	(Lcom/google/api/client/http/HttpRequest;Lcom/google/api/client/http/LowLevelHttpResponse;)V
    //   782: astore 9
    //   784: iconst_1
    //   785: ifne +20 -> 805
    //   788: aload 10
    //   790: invokevirtual 297	com/google/api/client/http/LowLevelHttpResponse:getContent	()Ljava/io/InputStream;
    //   793: astore 10
    //   795: aload 10
    //   797: ifnull +8 -> 805
    //   800: aload 10
    //   802: invokevirtual 302	java/io/InputStream:close	()V
    //   805: aload 14
    //   807: astore 10
    //   809: aload 9
    //   811: ifnull +396 -> 1207
    //   814: aload 9
    //   816: invokevirtual 305	com/google/api/client/http/HttpResponse:isSuccessStatusCode	()Z
    //   819: ifne +388 -> 1207
    //   822: iconst_0
    //   823: istore 4
    //   825: aload_0
    //   826: getfield 307	com/google/api/client/http/HttpRequest:unsuccessfulResponseHandler	Lcom/google/api/client/http/HttpUnsuccessfulResponseHandler;
    //   829: ifnull +19 -> 848
    //   832: aload_0
    //   833: getfield 307	com/google/api/client/http/HttpRequest:unsuccessfulResponseHandler	Lcom/google/api/client/http/HttpUnsuccessfulResponseHandler;
    //   836: aload_0
    //   837: aload 9
    //   839: iload 6
    //   841: invokeinterface 313 4 0
    //   846: istore 4
    //   848: iload 4
    //   850: istore 5
    //   852: iload 4
    //   854: ifne +23 -> 877
    //   857: aload_0
    //   858: aload 9
    //   860: invokevirtual 317	com/google/api/client/http/HttpResponse:getStatusCode	()I
    //   863: aload 9
    //   865: invokevirtual 321	com/google/api/client/http/HttpResponse:getHeaders	()Lcom/google/api/client/http/HttpHeaders;
    //   868: invokevirtual 325	com/google/api/client/http/HttpRequest:handleRedirect	(ILcom/google/api/client/http/HttpHeaders;)Z
    //   871: ifeq +254 -> 1125
    //   874: iconst_1
    //   875: istore 5
    //   877: iload 6
    //   879: iload 5
    //   881: iand
    //   882: istore_3
    //   883: iload_3
    //   884: istore_2
    //   885: iload_3
    //   886: ifeq +10 -> 896
    //   889: aload 9
    //   891: invokevirtual 131	com/google/api/client/http/HttpResponse:ignore	()V
    //   894: iload_3
    //   895: istore_2
    //   896: iload_1
    //   897: iconst_1
    //   898: isub
    //   899: istore_1
    //   900: aload 9
    //   902: ifnull +12 -> 914
    //   905: iconst_1
    //   906: ifne +8 -> 914
    //   909: aload 9
    //   911: invokevirtual 328	com/google/api/client/http/HttpResponse:disconnect	()V
    //   914: aload 9
    //   916: astore 11
    //   918: iload_2
    //   919: ifne -864 -> 55
    //   922: aload 9
    //   924: ifnonnull +322 -> 1246
    //   927: aload 10
    //   929: athrow
    //   930: iconst_0
    //   931: istore 4
    //   933: goto -923 -> 10
    //   936: iconst_0
    //   937: istore_2
    //   938: goto -801 -> 137
    //   941: aload_0
    //   942: getfield 65	com/google/api/client/http/HttpRequest:headers	Lcom/google/api/client/http/HttpHeaders;
    //   945: new 169	java/lang/StringBuilder
    //   948: dup
    //   949: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   952: aload 9
    //   954: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   957: ldc_w 330
    //   960: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   963: ldc 13
    //   965: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   968: invokevirtual 239	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   971: invokevirtual 208	com/google/api/client/http/HttpHeaders:setUserAgent	(Ljava/lang/String;)Lcom/google/api/client/http/HttpHeaders;
    //   974: pop
    //   975: goto -675 -> 300
    //   978: iconst_0
    //   979: istore_3
    //   980: goto -623 -> 357
    //   983: aload_0
    //   984: getfield 230	com/google/api/client/http/HttpRequest:encoding	Lcom/google/api/client/http/HttpEncoding;
    //   987: invokeinterface 335 1 0
    //   992: astore 12
    //   994: new 337	com/google/api/client/http/HttpEncodingStreamingContent
    //   997: dup
    //   998: aload 9
    //   1000: aload_0
    //   1001: getfield 230	com/google/api/client/http/HttpRequest:encoding	Lcom/google/api/client/http/HttpEncoding;
    //   1004: invokespecial 340	com/google/api/client/http/HttpEncodingStreamingContent:<init>	(Lcom/google/api/client/util/StreamingContent;Lcom/google/api/client/http/HttpEncoding;)V
    //   1007: astore 9
    //   1009: iload_3
    //   1010: ifeq +13 -> 1023
    //   1013: aload 9
    //   1015: invokestatic 346	com/google/api/client/util/IOUtils:computeLength	(Lcom/google/api/client/util/StreamingContent;)J
    //   1018: lstore 7
    //   1020: goto -593 -> 427
    //   1023: ldc2_w 347
    //   1026: lstore 7
    //   1028: goto -8 -> 1020
    //   1031: iconst_0
    //   1032: istore 6
    //   1034: goto -282 -> 752
    //   1037: astore 9
    //   1039: iconst_0
    //   1040: ifne +20 -> 1060
    //   1043: aload 10
    //   1045: invokevirtual 297	com/google/api/client/http/LowLevelHttpResponse:getContent	()Ljava/io/InputStream;
    //   1048: astore 10
    //   1050: aload 10
    //   1052: ifnull +8 -> 1060
    //   1055: aload 10
    //   1057: invokevirtual 302	java/io/InputStream:close	()V
    //   1060: aload 9
    //   1062: athrow
    //   1063: astore 10
    //   1065: aload 13
    //   1067: astore 9
    //   1069: aload_0
    //   1070: getfield 85	com/google/api/client/http/HttpRequest:retryOnExecuteIOException	Z
    //   1073: ifne +28 -> 1101
    //   1076: aload_0
    //   1077: getfield 350	com/google/api/client/http/HttpRequest:ioExceptionHandler	Lcom/google/api/client/http/HttpIOExceptionHandler;
    //   1080: ifnull +18 -> 1098
    //   1083: aload_0
    //   1084: getfield 350	com/google/api/client/http/HttpRequest:ioExceptionHandler	Lcom/google/api/client/http/HttpIOExceptionHandler;
    //   1087: aload_0
    //   1088: iload 6
    //   1090: invokeinterface 356 3 0
    //   1095: ifne +6 -> 1101
    //   1098: aload 10
    //   1100: athrow
    //   1101: aload 10
    //   1103: astore 11
    //   1105: aload 15
    //   1107: getstatic 359	java/util/logging/Level:WARNING	Ljava/util/logging/Level;
    //   1110: ldc_w 361
    //   1113: aload 10
    //   1115: invokevirtual 365	java/util/logging/Logger:log	(Ljava/util/logging/Level;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   1118: aload 11
    //   1120: astore 10
    //   1122: goto -313 -> 809
    //   1125: iload 4
    //   1127: istore 5
    //   1129: iload 6
    //   1131: ifeq -254 -> 877
    //   1134: iload 4
    //   1136: istore 5
    //   1138: aload_0
    //   1139: getfield 113	com/google/api/client/http/HttpRequest:backOffPolicy	Lcom/google/api/client/http/BackOffPolicy;
    //   1142: ifnull -265 -> 877
    //   1145: iload 4
    //   1147: istore 5
    //   1149: aload_0
    //   1150: getfield 113	com/google/api/client/http/HttpRequest:backOffPolicy	Lcom/google/api/client/http/BackOffPolicy;
    //   1153: aload 9
    //   1155: invokevirtual 317	com/google/api/client/http/HttpResponse:getStatusCode	()I
    //   1158: invokeinterface 369 2 0
    //   1163: ifeq -286 -> 877
    //   1166: aload_0
    //   1167: getfield 113	com/google/api/client/http/HttpRequest:backOffPolicy	Lcom/google/api/client/http/BackOffPolicy;
    //   1170: invokeinterface 372 1 0
    //   1175: lstore 7
    //   1177: iload 4
    //   1179: istore 5
    //   1181: lload 7
    //   1183: ldc2_w 347
    //   1186: lcmp
    //   1187: ifeq -310 -> 877
    //   1190: aload_0
    //   1191: getfield 92	com/google/api/client/http/HttpRequest:sleeper	Lcom/google/api/client/util/Sleeper;
    //   1194: lload 7
    //   1196: invokeinterface 375 3 0
    //   1201: iconst_1
    //   1202: istore 5
    //   1204: goto -327 -> 877
    //   1207: aload 9
    //   1209: ifnonnull +13 -> 1222
    //   1212: iconst_1
    //   1213: istore_2
    //   1214: iload 6
    //   1216: iload_2
    //   1217: iand
    //   1218: istore_2
    //   1219: goto -323 -> 896
    //   1222: iconst_0
    //   1223: istore_2
    //   1224: goto -10 -> 1214
    //   1227: astore 10
    //   1229: aload 9
    //   1231: ifnull +12 -> 1243
    //   1234: iconst_0
    //   1235: ifne +8 -> 1243
    //   1238: aload 9
    //   1240: invokevirtual 328	com/google/api/client/http/HttpResponse:disconnect	()V
    //   1243: aload 10
    //   1245: athrow
    //   1246: aload_0
    //   1247: getfield 377	com/google/api/client/http/HttpRequest:responseInterceptor	Lcom/google/api/client/http/HttpResponseInterceptor;
    //   1250: ifnull +14 -> 1264
    //   1253: aload_0
    //   1254: getfield 377	com/google/api/client/http/HttpRequest:responseInterceptor	Lcom/google/api/client/http/HttpResponseInterceptor;
    //   1257: aload 9
    //   1259: invokeinterface 383 2 0
    //   1264: aload_0
    //   1265: getfield 83	com/google/api/client/http/HttpRequest:throwExceptionOnExecuteError	Z
    //   1268: ifeq +31 -> 1299
    //   1271: aload 9
    //   1273: invokevirtual 305	com/google/api/client/http/HttpResponse:isSuccessStatusCode	()Z
    //   1276: ifne +23 -> 1299
    //   1279: new 385	com/google/api/client/http/HttpResponseException
    //   1282: dup
    //   1283: aload 9
    //   1285: invokespecial 387	com/google/api/client/http/HttpResponseException:<init>	(Lcom/google/api/client/http/HttpResponse;)V
    //   1288: athrow
    //   1289: astore 10
    //   1291: aload 9
    //   1293: invokevirtual 328	com/google/api/client/http/HttpResponse:disconnect	()V
    //   1296: aload 10
    //   1298: athrow
    //   1299: aload 9
    //   1301: areturn
    //   1302: astore 11
    //   1304: goto -103 -> 1201
    //   1307: astore 10
    //   1309: goto -240 -> 1069
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1312	0	this	HttpRequest
    //   19	881	1	i	int
    //   136	1088	2	bool1	boolean
    //   356	654	3	bool2	boolean
    //   8	1170	4	bool3	boolean
    //   850	353	5	bool4	boolean
    //   750	468	6	bool5	boolean
    //   425	770	7	l	long
    //   141	873	9	localObject1	Object
    //   1037	24	9	localObject2	Object
    //   1067	233	9	localObject3	Object
    //   145	911	10	localObject4	Object
    //   1063	51	10	localIOException1	java.io.IOException
    //   1120	1	10	localObject5	Object
    //   1227	17	10	localObject6	Object
    //   1289	8	10	localObject7	Object
    //   1307	1	10	localIOException2	java.io.IOException
    //   37	1082	11	localObject8	Object
    //   1302	1	11	localInterruptedException	InterruptedException
    //   158	835	12	localObject9	Object
    //   66	1000	13	localObject10	Object
    //   69	737	14	localObject11	Object
    //   115	991	15	localLogger	java.util.logging.Logger
    //   95	601	16	str1	String
    //   110	656	17	localLowLevelHttpRequest	LowLevelHttpRequest
    //   375	265	18	str2	String
    //   456	113	19	str3	String
    // Exception table:
    //   from	to	target	type
    //   772	784	1037	finally
    //   765	772	1063	java/io/IOException
    //   1043	1050	1063	java/io/IOException
    //   1055	1060	1063	java/io/IOException
    //   1060	1063	1063	java/io/IOException
    //   814	822	1227	finally
    //   825	848	1227	finally
    //   857	874	1227	finally
    //   889	894	1227	finally
    //   1138	1145	1227	finally
    //   1149	1177	1227	finally
    //   1190	1201	1227	finally
    //   1279	1289	1289	finally
    //   1190	1201	1302	java/lang/InterruptedException
    //   788	795	1307	java/io/IOException
    //   800	805	1307	java/io/IOException
  }
  
  @Beta
  public Future<HttpResponse> executeAsync()
  {
    return executeAsync(Executors.newSingleThreadExecutor());
  }
  
  @Beta
  public Future<HttpResponse> executeAsync(Executor paramExecutor)
  {
    FutureTask localFutureTask = new FutureTask(new Callable()
    {
      public HttpResponse call()
        throws Exception
      {
        return HttpRequest.this.execute();
      }
    });
    paramExecutor.execute(localFutureTask);
    return localFutureTask;
  }
  
  @Deprecated
  @Beta
  public BackOffPolicy getBackOffPolicy()
  {
    return this.backOffPolicy;
  }
  
  public int getConnectTimeout()
  {
    return this.connectTimeout;
  }
  
  public HttpContent getContent()
  {
    return this.content;
  }
  
  public int getContentLoggingLimit()
  {
    return this.contentLoggingLimit;
  }
  
  public HttpEncoding getEncoding()
  {
    return this.encoding;
  }
  
  public boolean getFollowRedirects()
  {
    return this.followRedirects;
  }
  
  public HttpHeaders getHeaders()
  {
    return this.headers;
  }
  
  @Beta
  public HttpIOExceptionHandler getIOExceptionHandler()
  {
    return this.ioExceptionHandler;
  }
  
  public HttpExecuteInterceptor getInterceptor()
  {
    return this.executeInterceptor;
  }
  
  public int getNumberOfRetries()
  {
    return this.numRetries;
  }
  
  public final ObjectParser getParser()
  {
    return this.objectParser;
  }
  
  public int getReadTimeout()
  {
    return this.readTimeout;
  }
  
  public String getRequestMethod()
  {
    return this.requestMethod;
  }
  
  public HttpHeaders getResponseHeaders()
  {
    return this.responseHeaders;
  }
  
  public HttpResponseInterceptor getResponseInterceptor()
  {
    return this.responseInterceptor;
  }
  
  @Deprecated
  @Beta
  public boolean getRetryOnExecuteIOException()
  {
    return this.retryOnExecuteIOException;
  }
  
  public Sleeper getSleeper()
  {
    return this.sleeper;
  }
  
  public boolean getSuppressUserAgentSuffix()
  {
    return this.suppressUserAgentSuffix;
  }
  
  public boolean getThrowExceptionOnExecuteError()
  {
    return this.throwExceptionOnExecuteError;
  }
  
  public HttpTransport getTransport()
  {
    return this.transport;
  }
  
  public HttpUnsuccessfulResponseHandler getUnsuccessfulResponseHandler()
  {
    return this.unsuccessfulResponseHandler;
  }
  
  public GenericUrl getUrl()
  {
    return this.url;
  }
  
  public boolean handleRedirect(int paramInt, HttpHeaders paramHttpHeaders)
  {
    paramHttpHeaders = paramHttpHeaders.getLocation();
    if ((getFollowRedirects()) && (HttpStatusCodes.isRedirect(paramInt)) && (paramHttpHeaders != null))
    {
      setUrl(new GenericUrl(this.url.toURL(paramHttpHeaders)));
      if (paramInt == 303)
      {
        setRequestMethod("GET");
        setContent(null);
      }
      this.headers.setAuthorization((String)null);
      this.headers.setIfMatch((String)null);
      this.headers.setIfNoneMatch((String)null);
      this.headers.setIfModifiedSince((String)null);
      this.headers.setIfUnmodifiedSince((String)null);
      this.headers.setIfRange((String)null);
      return true;
    }
    return false;
  }
  
  public boolean isCurlLoggingEnabled()
  {
    return this.curlLoggingEnabled;
  }
  
  public boolean isLoggingEnabled()
  {
    return this.loggingEnabled;
  }
  
  @Deprecated
  @Beta
  public HttpRequest setBackOffPolicy(BackOffPolicy paramBackOffPolicy)
  {
    this.backOffPolicy = paramBackOffPolicy;
    return this;
  }
  
  public HttpRequest setConnectTimeout(int paramInt)
  {
    if (paramInt >= 0) {}
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkArgument(bool);
      this.connectTimeout = paramInt;
      return this;
    }
  }
  
  public HttpRequest setContent(HttpContent paramHttpContent)
  {
    this.content = paramHttpContent;
    return this;
  }
  
  public HttpRequest setContentLoggingLimit(int paramInt)
  {
    if (paramInt >= 0) {}
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkArgument(bool, "The content logging limit must be non-negative.");
      this.contentLoggingLimit = paramInt;
      return this;
    }
  }
  
  public HttpRequest setCurlLoggingEnabled(boolean paramBoolean)
  {
    this.curlLoggingEnabled = paramBoolean;
    return this;
  }
  
  public HttpRequest setEncoding(HttpEncoding paramHttpEncoding)
  {
    this.encoding = paramHttpEncoding;
    return this;
  }
  
  public HttpRequest setFollowRedirects(boolean paramBoolean)
  {
    this.followRedirects = paramBoolean;
    return this;
  }
  
  public HttpRequest setHeaders(HttpHeaders paramHttpHeaders)
  {
    this.headers = ((HttpHeaders)Preconditions.checkNotNull(paramHttpHeaders));
    return this;
  }
  
  @Beta
  public HttpRequest setIOExceptionHandler(HttpIOExceptionHandler paramHttpIOExceptionHandler)
  {
    this.ioExceptionHandler = paramHttpIOExceptionHandler;
    return this;
  }
  
  public HttpRequest setInterceptor(HttpExecuteInterceptor paramHttpExecuteInterceptor)
  {
    this.executeInterceptor = paramHttpExecuteInterceptor;
    return this;
  }
  
  public HttpRequest setLoggingEnabled(boolean paramBoolean)
  {
    this.loggingEnabled = paramBoolean;
    return this;
  }
  
  public HttpRequest setNumberOfRetries(int paramInt)
  {
    if (paramInt >= 0) {}
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkArgument(bool);
      this.numRetries = paramInt;
      return this;
    }
  }
  
  public HttpRequest setParser(ObjectParser paramObjectParser)
  {
    this.objectParser = paramObjectParser;
    return this;
  }
  
  public HttpRequest setReadTimeout(int paramInt)
  {
    if (paramInt >= 0) {}
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkArgument(bool);
      this.readTimeout = paramInt;
      return this;
    }
  }
  
  public HttpRequest setRequestMethod(String paramString)
  {
    if ((paramString == null) || (HttpMediaType.matchesToken(paramString))) {}
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkArgument(bool);
      this.requestMethod = paramString;
      return this;
    }
  }
  
  public HttpRequest setResponseHeaders(HttpHeaders paramHttpHeaders)
  {
    this.responseHeaders = ((HttpHeaders)Preconditions.checkNotNull(paramHttpHeaders));
    return this;
  }
  
  public HttpRequest setResponseInterceptor(HttpResponseInterceptor paramHttpResponseInterceptor)
  {
    this.responseInterceptor = paramHttpResponseInterceptor;
    return this;
  }
  
  @Deprecated
  @Beta
  public HttpRequest setRetryOnExecuteIOException(boolean paramBoolean)
  {
    this.retryOnExecuteIOException = paramBoolean;
    return this;
  }
  
  public HttpRequest setSleeper(Sleeper paramSleeper)
  {
    this.sleeper = ((Sleeper)Preconditions.checkNotNull(paramSleeper));
    return this;
  }
  
  public HttpRequest setSuppressUserAgentSuffix(boolean paramBoolean)
  {
    this.suppressUserAgentSuffix = paramBoolean;
    return this;
  }
  
  public HttpRequest setThrowExceptionOnExecuteError(boolean paramBoolean)
  {
    this.throwExceptionOnExecuteError = paramBoolean;
    return this;
  }
  
  public HttpRequest setUnsuccessfulResponseHandler(HttpUnsuccessfulResponseHandler paramHttpUnsuccessfulResponseHandler)
  {
    this.unsuccessfulResponseHandler = paramHttpUnsuccessfulResponseHandler;
    return this;
  }
  
  public HttpRequest setUrl(GenericUrl paramGenericUrl)
  {
    this.url = ((GenericUrl)Preconditions.checkNotNull(paramGenericUrl));
    return this;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\HttpRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */