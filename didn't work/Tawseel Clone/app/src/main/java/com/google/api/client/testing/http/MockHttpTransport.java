package com.google.api.client.testing.http;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

@Beta
public class MockHttpTransport
  extends HttpTransport
{
  private MockLowLevelHttpRequest lowLevelHttpRequest;
  private MockLowLevelHttpResponse lowLevelHttpResponse;
  private Set<String> supportedMethods;
  
  public MockHttpTransport() {}
  
  protected MockHttpTransport(Builder paramBuilder)
  {
    this.supportedMethods = paramBuilder.supportedMethods;
    this.lowLevelHttpRequest = paramBuilder.lowLevelHttpRequest;
    this.lowLevelHttpResponse = paramBuilder.lowLevelHttpResponse;
  }
  
  @Deprecated
  public static Builder builder()
  {
    return new Builder();
  }
  
  public LowLevelHttpRequest buildRequest(String paramString1, String paramString2)
    throws IOException
  {
    Preconditions.checkArgument(supportsMethod(paramString1), "HTTP method %s not supported", new Object[] { paramString1 });
    if (this.lowLevelHttpRequest != null) {
      paramString1 = this.lowLevelHttpRequest;
    }
    do
    {
      return paramString1;
      paramString2 = new MockLowLevelHttpRequest(paramString2);
      paramString1 = paramString2;
    } while (this.lowLevelHttpResponse == null);
    paramString2.setResponse(this.lowLevelHttpResponse);
    return paramString2;
  }
  
  public final MockLowLevelHttpRequest getLowLevelHttpRequest()
  {
    return this.lowLevelHttpRequest;
  }
  
  public final Set<String> getSupportedMethods()
  {
    if (this.supportedMethods == null) {
      return null;
    }
    return Collections.unmodifiableSet(this.supportedMethods);
  }
  
  public boolean supportsMethod(String paramString)
    throws IOException
  {
    return (this.supportedMethods == null) || (this.supportedMethods.contains(paramString));
  }
  
  @Beta
  public static class Builder
  {
    MockLowLevelHttpRequest lowLevelHttpRequest;
    MockLowLevelHttpResponse lowLevelHttpResponse;
    Set<String> supportedMethods;
    
    public MockHttpTransport build()
    {
      return new MockHttpTransport(this);
    }
    
    public final MockLowLevelHttpRequest getLowLevelHttpRequest()
    {
      return this.lowLevelHttpRequest;
    }
    
    MockLowLevelHttpResponse getLowLevelHttpResponse()
    {
      return this.lowLevelHttpResponse;
    }
    
    public final Set<String> getSupportedMethods()
    {
      return this.supportedMethods;
    }
    
    public final Builder setLowLevelHttpRequest(MockLowLevelHttpRequest paramMockLowLevelHttpRequest)
    {
      if (this.lowLevelHttpResponse == null) {}
      for (boolean bool = true;; bool = false)
      {
        Preconditions.checkState(bool, "Cannnot set a low level HTTP request when a low level HTTP response has been set.");
        this.lowLevelHttpRequest = paramMockLowLevelHttpRequest;
        return this;
      }
    }
    
    public final Builder setLowLevelHttpResponse(MockLowLevelHttpResponse paramMockLowLevelHttpResponse)
    {
      if (this.lowLevelHttpRequest == null) {}
      for (boolean bool = true;; bool = false)
      {
        Preconditions.checkState(bool, "Cannot set a low level HTTP response when a low level HTTP request has been set.");
        this.lowLevelHttpResponse = paramMockLowLevelHttpResponse;
        return this;
      }
    }
    
    public final Builder setSupportedMethods(Set<String> paramSet)
    {
      this.supportedMethods = paramSet;
      return this;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\testing\http\MockHttpTransport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */