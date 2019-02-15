package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.Key;

abstract interface EngineJobListener
{
  public abstract void onEngineJobCancelled(EngineJob paramEngineJob, Key paramKey);
  
  public abstract void onEngineJobComplete(Key paramKey, EngineResource<?> paramEngineResource);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\bumptech\glide\load\engine\EngineJobListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */