package com.google.firebase.database;

public abstract interface ChildEventListener
{
  public abstract void onCancelled(DatabaseError paramDatabaseError);
  
  public abstract void onChildAdded(DataSnapshot paramDataSnapshot, String paramString);
  
  public abstract void onChildChanged(DataSnapshot paramDataSnapshot, String paramString);
  
  public abstract void onChildMoved(DataSnapshot paramDataSnapshot, String paramString);
  
  public abstract void onChildRemoved(DataSnapshot paramDataSnapshot);
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\database\ChildEventListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */