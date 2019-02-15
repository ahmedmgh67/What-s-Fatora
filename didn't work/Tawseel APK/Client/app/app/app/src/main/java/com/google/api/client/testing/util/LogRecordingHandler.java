package com.google.api.client.testing.util;

import com.google.api.client.util.Beta;
import com.google.api.client.util.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

@Beta
public class LogRecordingHandler
  extends Handler
{
  private final List<LogRecord> records = Lists.newArrayList();
  
  public void close()
    throws SecurityException
  {}
  
  public void flush() {}
  
  public List<String> messages()
  {
    ArrayList localArrayList = Lists.newArrayList();
    Iterator localIterator = this.records.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(((LogRecord)localIterator.next()).getMessage());
    }
    return localArrayList;
  }
  
  public void publish(LogRecord paramLogRecord)
  {
    this.records.add(paramLogRecord);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\api\client\testing\util\LogRecordingHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */