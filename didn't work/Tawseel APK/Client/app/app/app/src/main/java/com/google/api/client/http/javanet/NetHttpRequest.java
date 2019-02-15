package com.google.api.client.http.javanet;

import com.google.api.client.http.LowLevelHttpRequest;
import java.net.HttpURLConnection;

final class NetHttpRequest
  extends LowLevelHttpRequest
{
  private final HttpURLConnection connection;
  
  NetHttpRequest(HttpURLConnection paramHttpURLConnection)
  {
    this.connection = paramHttpURLConnection;
    paramHttpURLConnection.setInstanceFollowRedirects(false);
  }
  
  public void addHeader(String paramString1, String paramString2)
  {
    this.connection.addRequestProperty(paramString1, paramString2);
  }
  
  /* Error */
  public com.google.api.client.http.LowLevelHttpResponse execute()
    throws java.io.IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 13	com/google/api/client/http/javanet/NetHttpRequest:connection	Ljava/net/HttpURLConnection;
    //   4: astore 4
    //   6: aload_0
    //   7: invokevirtual 33	com/google/api/client/http/javanet/NetHttpRequest:getStreamingContent	()Lcom/google/api/client/util/StreamingContent;
    //   10: ifnull +139 -> 149
    //   13: aload_0
    //   14: invokevirtual 37	com/google/api/client/http/javanet/NetHttpRequest:getContentType	()Ljava/lang/String;
    //   17: astore 5
    //   19: aload 5
    //   21: ifnull +11 -> 32
    //   24: aload_0
    //   25: ldc 39
    //   27: aload 5
    //   29: invokevirtual 41	com/google/api/client/http/javanet/NetHttpRequest:addHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   32: aload_0
    //   33: invokevirtual 44	com/google/api/client/http/javanet/NetHttpRequest:getContentEncoding	()Ljava/lang/String;
    //   36: astore 5
    //   38: aload 5
    //   40: ifnull +11 -> 51
    //   43: aload_0
    //   44: ldc 46
    //   46: aload 5
    //   48: invokevirtual 41	com/google/api/client/http/javanet/NetHttpRequest:addHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   51: aload_0
    //   52: invokevirtual 50	com/google/api/client/http/javanet/NetHttpRequest:getContentLength	()J
    //   55: lstore_1
    //   56: lload_1
    //   57: lconst_0
    //   58: lcmp
    //   59: iflt +13 -> 72
    //   62: aload_0
    //   63: ldc 52
    //   65: lload_1
    //   66: invokestatic 58	java/lang/Long:toString	(J)Ljava/lang/String;
    //   69: invokevirtual 41	com/google/api/client/http/javanet/NetHttpRequest:addHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   72: aload 4
    //   74: invokevirtual 61	java/net/HttpURLConnection:getRequestMethod	()Ljava/lang/String;
    //   77: astore 5
    //   79: ldc 63
    //   81: aload 5
    //   83: invokevirtual 69	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   86: ifne +13 -> 99
    //   89: ldc 71
    //   91: aload 5
    //   93: invokevirtual 69	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   96: ifeq +100 -> 196
    //   99: aload 4
    //   101: iconst_1
    //   102: invokevirtual 74	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   105: lload_1
    //   106: lconst_0
    //   107: lcmp
    //   108: iflt +69 -> 177
    //   111: lload_1
    //   112: ldc2_w 75
    //   115: lcmp
    //   116: ifgt +61 -> 177
    //   119: aload 4
    //   121: lload_1
    //   122: l2i
    //   123: invokevirtual 80	java/net/HttpURLConnection:setFixedLengthStreamingMode	(I)V
    //   126: aload 4
    //   128: invokevirtual 84	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   131: astore 5
    //   133: aload_0
    //   134: invokevirtual 33	com/google/api/client/http/javanet/NetHttpRequest:getStreamingContent	()Lcom/google/api/client/util/StreamingContent;
    //   137: aload 5
    //   139: invokeinterface 90 2 0
    //   144: aload 5
    //   146: invokevirtual 95	java/io/OutputStream:close	()V
    //   149: aload 4
    //   151: invokevirtual 98	java/net/HttpURLConnection:connect	()V
    //   154: new 100	com/google/api/client/http/javanet/NetHttpResponse
    //   157: dup
    //   158: aload 4
    //   160: invokespecial 102	com/google/api/client/http/javanet/NetHttpResponse:<init>	(Ljava/net/HttpURLConnection;)V
    //   163: astore 5
    //   165: iconst_1
    //   166: ifne +8 -> 174
    //   169: aload 4
    //   171: invokevirtual 105	java/net/HttpURLConnection:disconnect	()V
    //   174: aload 5
    //   176: areturn
    //   177: aload 4
    //   179: iconst_0
    //   180: invokevirtual 108	java/net/HttpURLConnection:setChunkedStreamingMode	(I)V
    //   183: goto -57 -> 126
    //   186: astore 4
    //   188: aload 5
    //   190: invokevirtual 95	java/io/OutputStream:close	()V
    //   193: aload 4
    //   195: athrow
    //   196: lload_1
    //   197: lconst_0
    //   198: lcmp
    //   199: ifne +23 -> 222
    //   202: iconst_1
    //   203: istore_3
    //   204: iload_3
    //   205: ldc 110
    //   207: iconst_1
    //   208: anewarray 112	java/lang/Object
    //   211: dup
    //   212: iconst_0
    //   213: aload 5
    //   215: aastore
    //   216: invokestatic 118	com/google/api/client/util/Preconditions:checkArgument	(ZLjava/lang/String;[Ljava/lang/Object;)V
    //   219: goto -70 -> 149
    //   222: iconst_0
    //   223: istore_3
    //   224: goto -20 -> 204
    //   227: astore 5
    //   229: iconst_0
    //   230: ifne +8 -> 238
    //   233: aload 4
    //   235: invokevirtual 105	java/net/HttpURLConnection:disconnect	()V
    //   238: aload 5
    //   240: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	241	0	this	NetHttpRequest
    //   55	142	1	l	long
    //   203	21	3	bool	boolean
    //   4	174	4	localHttpURLConnection	HttpURLConnection
    //   186	48	4	localObject1	Object
    //   17	197	5	localObject2	Object
    //   227	12	5	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   133	144	186	finally
    //   149	165	227	finally
  }
  
  public void setTimeout(int paramInt1, int paramInt2)
  {
    this.connection.setReadTimeout(paramInt2);
    this.connection.setConnectTimeout(paramInt1);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\http\javanet\NetHttpRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */