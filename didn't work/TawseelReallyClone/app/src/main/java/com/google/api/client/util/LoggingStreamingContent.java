package com.google.api.client.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class LoggingStreamingContent
  implements StreamingContent
{
  private final StreamingContent content;
  private final int contentLoggingLimit;
  private final Logger logger;
  private final Level loggingLevel;
  
  public LoggingStreamingContent(StreamingContent paramStreamingContent, Logger paramLogger, Level paramLevel, int paramInt)
  {
    this.content = paramStreamingContent;
    this.logger = paramLogger;
    this.loggingLevel = paramLevel;
    this.contentLoggingLimit = paramInt;
  }
  
  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    LoggingOutputStream localLoggingOutputStream = new LoggingOutputStream(paramOutputStream, this.logger, this.loggingLevel, this.contentLoggingLimit);
    try
    {
      this.content.writeTo(localLoggingOutputStream);
      localLoggingOutputStream.getLogStream().close();
      paramOutputStream.flush();
      return;
    }
    finally
    {
      localLoggingOutputStream.getLogStream().close();
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\util\LoggingStreamingContent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */