package com.google.api.client.googleapis.testing.auth.oauth2;

import com.google.api.client.googleapis.testing.TestUtils;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.webtoken.JsonWebSignature;
import com.google.api.client.json.webtoken.JsonWebToken.Payload;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.api.client.util.Beta;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Beta
public class MockTokenServerTransport
  extends MockHttpTransport
{
  static final String EXPECTED_GRANT_TYPE = "urn:ietf:params:oauth:grant-type:jwt-bearer";
  static final JsonFactory JSON_FACTORY = new JacksonFactory();
  Map<String, String> clients = new HashMap();
  Map<String, String> refreshTokens = new HashMap();
  Map<String, String> serviceAccounts = new HashMap();
  final String tokenServerUrl;
  
  public MockTokenServerTransport()
  {
    this("https://accounts.google.com/o/oauth2/token");
  }
  
  public MockTokenServerTransport(String paramString)
  {
    this.tokenServerUrl = paramString;
  }
  
  public void addClient(String paramString1, String paramString2)
  {
    this.clients.put(paramString1, paramString2);
  }
  
  public void addRefreshToken(String paramString1, String paramString2)
  {
    this.refreshTokens.put(paramString1, paramString2);
  }
  
  public void addServiceAccount(String paramString1, String paramString2)
  {
    this.serviceAccounts.put(paramString1, paramString2);
  }
  
  public LowLevelHttpRequest buildRequest(String paramString1, String paramString2)
    throws IOException
  {
    if (paramString2.equals(this.tokenServerUrl)) {
      new MockLowLevelHttpRequest(paramString2)
      {
        public LowLevelHttpResponse execute()
          throws IOException
        {
          Object localObject1 = TestUtils.parseQuery(getContentAsString());
          String str = (String)((Map)localObject1).get("client_id");
          Object localObject2;
          if (str != null)
          {
            if (!MockTokenServerTransport.this.clients.containsKey(str)) {
              throw new IOException("Client ID not found.");
            }
            localObject2 = (String)((Map)localObject1).get("client_secret");
            str = (String)MockTokenServerTransport.this.clients.get(str);
            if ((localObject2 == null) || (!((String)localObject2).equals(str))) {
              throw new IOException("Client secret not found.");
            }
            localObject1 = (String)((Map)localObject1).get("refresh_token");
            if (!MockTokenServerTransport.this.refreshTokens.containsKey(localObject1)) {
              throw new IOException("Refresh Token not found.");
            }
            localObject1 = (String)MockTokenServerTransport.this.refreshTokens.get(localObject1);
          }
          do
          {
            localObject2 = new GenericJson();
            ((GenericJson)localObject2).setFactory(MockTokenServerTransport.JSON_FACTORY);
            ((GenericJson)localObject2).put("access_token", localObject1);
            ((GenericJson)localObject2).put("expires_in", Integer.valueOf(3600000));
            ((GenericJson)localObject2).put("token_type", "Bearer");
            localObject1 = ((GenericJson)localObject2).toPrettyString();
            return new MockLowLevelHttpResponse().setContentType("application/json; charset=UTF-8").setContent((String)localObject1);
            if (!((Map)localObject1).containsKey("grant_type")) {
              break;
            }
            if (!"urn:ietf:params:oauth:grant-type:jwt-bearer".equals((String)((Map)localObject1).get("grant_type"))) {
              throw new IOException("Unexpected Grant Type.");
            }
            localObject1 = (String)((Map)localObject1).get("assertion");
            localObject2 = JsonWebSignature.parse(MockTokenServerTransport.JSON_FACTORY, (String)localObject1);
            localObject1 = ((JsonWebSignature)localObject2).getPayload().getIssuer();
            if (!MockTokenServerTransport.this.serviceAccounts.containsKey(localObject1)) {
              throw new IOException("Service Account Email not found as issuer.");
            }
            localObject1 = (String)MockTokenServerTransport.this.serviceAccounts.get(localObject1);
            localObject2 = (String)((JsonWebSignature)localObject2).getPayload().get("scope");
          } while ((localObject2 != null) && (((String)localObject2).length() != 0));
          throw new IOException("Scopes not found.");
          throw new IOException("Unknown token type.");
        }
      };
    }
    return super.buildRequest(paramString1, paramString2);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\testing\auth\oauth2\MockTokenServerTransport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */