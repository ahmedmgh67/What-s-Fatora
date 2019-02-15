package com.google.api.client.googleapis.testing.compute;

import com.google.api.client.googleapis.auth.oauth2.OAuth2Utils;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.api.client.util.Beta;
import java.io.IOException;

@Beta
public class MockMetadataServerTransport
  extends MockHttpTransport
{
  static final JsonFactory JSON_FACTORY = new JacksonFactory();
  private static final String METADATA_SERVER_URL = ;
  private static final String METADATA_TOKEN_SERVER_URL = METADATA_SERVER_URL + "/computeMetadata/v1/instance/service-accounts/default/token";
  String accessToken;
  Integer tokenRequestStatusCode;
  
  public MockMetadataServerTransport(String paramString)
  {
    this.accessToken = paramString;
  }
  
  public LowLevelHttpRequest buildRequest(String paramString1, String paramString2)
    throws IOException
  {
    if (paramString2.equals(METADATA_TOKEN_SERVER_URL)) {
      new MockLowLevelHttpRequest(paramString2)
      {
        public LowLevelHttpResponse execute()
          throws IOException
        {
          if (MockMetadataServerTransport.this.tokenRequestStatusCode != null) {
            return new MockLowLevelHttpResponse().setStatusCode(MockMetadataServerTransport.this.tokenRequestStatusCode.intValue()).setContent("Token Fetch Error");
          }
          if (!"Google".equals(getFirstHeaderValue("Metadata-Flavor"))) {
            throw new IOException("Metadata request header not found.");
          }
          Object localObject = new GenericJson();
          ((GenericJson)localObject).setFactory(MockMetadataServerTransport.JSON_FACTORY);
          ((GenericJson)localObject).put("access_token", MockMetadataServerTransport.this.accessToken);
          ((GenericJson)localObject).put("expires_in", Integer.valueOf(3600000));
          ((GenericJson)localObject).put("token_type", "Bearer");
          localObject = ((GenericJson)localObject).toPrettyString();
          return new MockLowLevelHttpResponse().setContentType("application/json; charset=UTF-8").setContent((String)localObject);
        }
      };
    }
    if (paramString2.equals(METADATA_SERVER_URL)) {
      new MockLowLevelHttpRequest(paramString2)
      {
        public LowLevelHttpResponse execute()
        {
          MockLowLevelHttpResponse localMockLowLevelHttpResponse = new MockLowLevelHttpResponse();
          localMockLowLevelHttpResponse.addHeader("Metadata-Flavor", "Google");
          return localMockLowLevelHttpResponse;
        }
      };
    }
    return super.buildRequest(paramString1, paramString2);
  }
  
  public void setTokenRequestStatusCode(Integer paramInteger)
  {
    this.tokenRequestStatusCode = paramInteger;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\googleapis\testing\compute\MockMetadataServerTransport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */