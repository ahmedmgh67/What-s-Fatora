package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class CloudShellCredential
  extends GoogleCredential
{
  private static final int ACCESS_TOKEN_INDEX = 2;
  protected static final String GET_AUTH_TOKEN_REQUEST = "2\n[]";
  private static final int READ_TIMEOUT_MS = 5000;
  private final int authPort;
  private final JsonFactory jsonFactory;
  
  public CloudShellCredential(int paramInt, JsonFactory paramJsonFactory)
  {
    this.authPort = paramInt;
    this.jsonFactory = paramJsonFactory;
  }
  
  protected TokenResponse executeRefreshToken()
    throws IOException
  {
    Socket localSocket = new Socket("localhost", getAuthPort());
    localSocket.setSoTimeout(5000);
    TokenResponse localTokenResponse = new TokenResponse();
    try
    {
      new PrintWriter(localSocket.getOutputStream(), true).println("2\n[]");
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(localSocket.getInputStream()));
      localBufferedReader.readLine();
      localTokenResponse.setAccessToken(((List)this.jsonFactory.createJsonParser(localBufferedReader).parseArray(LinkedList.class, Object.class)).get(2).toString());
      return localTokenResponse;
    }
    finally
    {
      localSocket.close();
    }
  }
  
  protected int getAuthPort()
  {
    return this.authPort;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\auth\oauth2\CloudShellCredential.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */