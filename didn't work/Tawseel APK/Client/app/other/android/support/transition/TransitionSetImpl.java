package android.support.transition;

abstract interface TransitionSetImpl
{
  public abstract TransitionSetImpl addTransition(TransitionImpl paramTransitionImpl);
  
  public abstract int getOrdering();
  
  public abstract TransitionSetImpl removeTransition(TransitionImpl paramTransitionImpl);
  
  public abstract TransitionSetImpl setOrdering(int paramInt);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\android\support\transition\TransitionSetImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */