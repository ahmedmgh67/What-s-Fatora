package android.support.transition;

abstract interface TransitionInterfaceListener<TransitionT extends TransitionInterface>
{
  public abstract void onTransitionCancel(TransitionT paramTransitionT);
  
  public abstract void onTransitionEnd(TransitionT paramTransitionT);
  
  public abstract void onTransitionPause(TransitionT paramTransitionT);
  
  public abstract void onTransitionResume(TransitionT paramTransitionT);
  
  public abstract void onTransitionStart(TransitionT paramTransitionT);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\android\support\transition\TransitionInterfaceListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */