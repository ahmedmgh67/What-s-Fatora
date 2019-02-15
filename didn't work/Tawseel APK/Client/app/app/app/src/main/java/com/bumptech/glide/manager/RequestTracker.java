package com.bumptech.glide.manager;

import com.bumptech.glide.request.Request;
import com.bumptech.glide.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

public class RequestTracker
{
  private boolean isPaused;
  private final List<Request> pendingRequests = new ArrayList();
  private final Set<Request> requests = Collections.newSetFromMap(new WeakHashMap());
  
  void addRequest(Request paramRequest)
  {
    this.requests.add(paramRequest);
  }
  
  public void clearRequests()
  {
    Iterator localIterator = Util.getSnapshot(this.requests).iterator();
    while (localIterator.hasNext()) {
      ((Request)localIterator.next()).clear();
    }
    this.pendingRequests.clear();
  }
  
  public boolean isPaused()
  {
    return this.isPaused;
  }
  
  public void pauseRequests()
  {
    this.isPaused = true;
    Iterator localIterator = Util.getSnapshot(this.requests).iterator();
    while (localIterator.hasNext())
    {
      Request localRequest = (Request)localIterator.next();
      if (localRequest.isRunning())
      {
        localRequest.pause();
        this.pendingRequests.add(localRequest);
      }
    }
  }
  
  public void removeRequest(Request paramRequest)
  {
    this.requests.remove(paramRequest);
    this.pendingRequests.remove(paramRequest);
  }
  
  public void restartRequests()
  {
    Iterator localIterator = Util.getSnapshot(this.requests).iterator();
    while (localIterator.hasNext())
    {
      Request localRequest = (Request)localIterator.next();
      if ((!localRequest.isComplete()) && (!localRequest.isCancelled()))
      {
        localRequest.pause();
        if (!this.isPaused) {
          localRequest.begin();
        } else {
          this.pendingRequests.add(localRequest);
        }
      }
    }
  }
  
  public void resumeRequests()
  {
    this.isPaused = false;
    Iterator localIterator = Util.getSnapshot(this.requests).iterator();
    while (localIterator.hasNext())
    {
      Request localRequest = (Request)localIterator.next();
      if ((!localRequest.isComplete()) && (!localRequest.isCancelled()) && (!localRequest.isRunning())) {
        localRequest.begin();
      }
    }
    this.pendingRequests.clear();
  }
  
  public void runRequest(Request paramRequest)
  {
    this.requests.add(paramRequest);
    if (!this.isPaused)
    {
      paramRequest.begin();
      return;
    }
    this.pendingRequests.add(paramRequest);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\manager\RequestTracker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */