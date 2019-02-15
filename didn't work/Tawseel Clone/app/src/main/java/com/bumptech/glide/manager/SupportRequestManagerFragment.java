package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import com.bumptech.glide.RequestManager;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SupportRequestManagerFragment
  extends Fragment
{
  private final HashSet<SupportRequestManagerFragment> childRequestManagerFragments = new HashSet();
  private final ActivityFragmentLifecycle lifecycle;
  private RequestManager requestManager;
  private final RequestManagerTreeNode requestManagerTreeNode = new SupportFragmentRequestManagerTreeNode(null);
  private SupportRequestManagerFragment rootRequestManagerFragment;
  
  public SupportRequestManagerFragment()
  {
    this(new ActivityFragmentLifecycle());
  }
  
  @SuppressLint({"ValidFragment"})
  public SupportRequestManagerFragment(ActivityFragmentLifecycle paramActivityFragmentLifecycle)
  {
    this.lifecycle = paramActivityFragmentLifecycle;
  }
  
  private void addChildRequestManagerFragment(SupportRequestManagerFragment paramSupportRequestManagerFragment)
  {
    this.childRequestManagerFragments.add(paramSupportRequestManagerFragment);
  }
  
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
  
  private void removeChildRequestManagerFragment(SupportRequestManagerFragment paramSupportRequestManagerFragment)
  {
    this.childRequestManagerFragments.remove(paramSupportRequestManagerFragment);
  }
  
  public Set<SupportRequestManagerFragment> getDescendantRequestManagerFragments()
  {
    if (this.rootRequestManagerFragment == null) {
      return Collections.emptySet();
    }
    if (this.rootRequestManagerFragment == this) {
      return Collections.unmodifiableSet(this.childRequestManagerFragments);
    }
    HashSet localHashSet = new HashSet();
    Iterator localIterator = this.rootRequestManagerFragment.getDescendantRequestManagerFragments().iterator();
    while (localIterator.hasNext())
    {
      SupportRequestManagerFragment localSupportRequestManagerFragment = (SupportRequestManagerFragment)localIterator.next();
      if (isDescendant(localSupportRequestManagerFragment.getParentFragment())) {
        localHashSet.add(localSupportRequestManagerFragment);
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
    this.rootRequestManagerFragment = RequestManagerRetriever.get().getSupportRequestManagerFragment(getActivity().getSupportFragmentManager());
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
    super.onLowMemory();
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
  
  public void setRequestManager(RequestManager paramRequestManager)
  {
    this.requestManager = paramRequestManager;
  }
  
  private class SupportFragmentRequestManagerTreeNode
    implements RequestManagerTreeNode
  {
    private SupportFragmentRequestManagerTreeNode() {}
    
    public Set<RequestManager> getDescendants()
    {
      Object localObject = SupportRequestManagerFragment.this.getDescendantRequestManagerFragments();
      HashSet localHashSet = new HashSet(((Set)localObject).size());
      localObject = ((Set)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        SupportRequestManagerFragment localSupportRequestManagerFragment = (SupportRequestManagerFragment)((Iterator)localObject).next();
        if (localSupportRequestManagerFragment.getRequestManager() != null) {
          localHashSet.add(localSupportRequestManagerFragment.getRequestManager());
        }
      }
      return localHashSet;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\manager\SupportRequestManagerFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */