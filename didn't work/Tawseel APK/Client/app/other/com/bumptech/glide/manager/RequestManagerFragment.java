package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Build.VERSION;
import com.bumptech.glide.RequestManager;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@TargetApi(11)
public class RequestManagerFragment
  extends Fragment
{
  private final HashSet<RequestManagerFragment> childRequestManagerFragments = new HashSet();
  private final ActivityFragmentLifecycle lifecycle;
  private RequestManager requestManager;
  private final RequestManagerTreeNode requestManagerTreeNode = new FragmentRequestManagerTreeNode(null);
  private RequestManagerFragment rootRequestManagerFragment;
  
  public RequestManagerFragment()
  {
    this(new ActivityFragmentLifecycle());
  }
  
  @SuppressLint({"ValidFragment"})
  RequestManagerFragment(ActivityFragmentLifecycle paramActivityFragmentLifecycle)
  {
    this.lifecycle = paramActivityFragmentLifecycle;
  }
  
  private void addChildRequestManagerFragment(RequestManagerFragment paramRequestManagerFragment)
  {
    this.childRequestManagerFragments.add(paramRequestManagerFragment);
  }
  
  @TargetApi(17)
  private boolean isDescendant(Fragment paramFragment)
  {
    Fragment localFragment = getParentFragment();
    while (paramFragment.getParentFragment() != null)
    {
      if (paramFragment.getParentFragment() == localFragment) {
        return true;
      }
      paramFragment = paramFragment.getParentFragment();
    }
    return false;
  }
  
  private void removeChildRequestManagerFragment(RequestManagerFragment paramRequestManagerFragment)
  {
    this.childRequestManagerFragments.remove(paramRequestManagerFragment);
  }
  
  @TargetApi(17)
  public Set<RequestManagerFragment> getDescendantRequestManagerFragments()
  {
    if (this.rootRequestManagerFragment == this) {
      return Collections.unmodifiableSet(this.childRequestManagerFragments);
    }
    if ((this.rootRequestManagerFragment == null) || (Build.VERSION.SDK_INT < 17)) {
      return Collections.emptySet();
    }
    HashSet localHashSet = new HashSet();
    Iterator localIterator = this.rootRequestManagerFragment.getDescendantRequestManagerFragments().iterator();
    while (localIterator.hasNext())
    {
      RequestManagerFragment localRequestManagerFragment = (RequestManagerFragment)localIterator.next();
      if (isDescendant(localRequestManagerFragment.getParentFragment())) {
        localHashSet.add(localRequestManagerFragment);
      }
    }
    return Collections.unmodifiableSet(localHashSet);
  }
  
  ActivityFragmentLifecycle getLifecycle()
  {
    return this.lifecycle;
  }
  
  public RequestManager getRequestManager()
  {
    return this.requestManager;
  }
  
  public RequestManagerTreeNode getRequestManagerTreeNode()
  {
    return this.requestManagerTreeNode;
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    this.rootRequestManagerFragment = RequestManagerRetriever.get().getRequestManagerFragment(getActivity().getFragmentManager());
    if (this.rootRequestManagerFragment != this) {
      this.rootRequestManagerFragment.addChildRequestManagerFragment(this);
    }
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    this.lifecycle.onDestroy();
  }
  
  public void onDetach()
  {
    super.onDetach();
    if (this.rootRequestManagerFragment != null)
    {
      this.rootRequestManagerFragment.removeChildRequestManagerFragment(this);
      this.rootRequestManagerFragment = null;
    }
  }
  
  public void onLowMemory()
  {
    if (this.requestManager != null) {
      this.requestManager.onLowMemory();
    }
  }
  
  public void onStart()
  {
    super.onStart();
    this.lifecycle.onStart();
  }
  
  public void onStop()
  {
    super.onStop();
    this.lifecycle.onStop();
  }
  
  public void onTrimMemory(int paramInt)
  {
    if (this.requestManager != null) {
      this.requestManager.onTrimMemory(paramInt);
    }
  }
  
  public void setRequestManager(RequestManager paramRequestManager)
  {
    this.requestManager = paramRequestManager;
  }
  
  private class FragmentRequestManagerTreeNode
    implements RequestManagerTreeNode
  {
    private FragmentRequestManagerTreeNode() {}
    
    public Set<RequestManager> getDescendants()
    {
      Object localObject = RequestManagerFragment.this.getDescendantRequestManagerFragments();
      HashSet localHashSet = new HashSet(((Set)localObject).size());
      localObject = ((Set)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        RequestManagerFragment localRequestManagerFragment = (RequestManagerFragment)((Iterator)localObject).next();
        if (localRequestManagerFragment.getRequestManager() != null) {
          localHashSet.add(localRequestManagerFragment.getRequestManager());
        }
      }
      return localHashSet;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\manager\RequestManagerFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */