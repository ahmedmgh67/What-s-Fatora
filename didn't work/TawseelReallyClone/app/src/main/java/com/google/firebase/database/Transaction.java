package com.google.firebase.database;

import com.google.android.gms.internal.zzbpe;

public class Transaction
{
  public static Result abort()
  {
    return new Result(false, null, null);
  }
  
  public static Result success(MutableData paramMutableData)
  {
    return new Result(true, paramMutableData.zzUY(), null);
  }
  
  public static abstract interface Handler
  {
    public abstract Transaction.Result doTransaction(MutableData paramMutableData);
    
    public abstract void onComplete(DatabaseError paramDatabaseError, boolean paramBoolean, DataSnapshot paramDataSnapshot);
  }
  
  public static class Result
  {
    private boolean zzbYj;
    private zzbpe zzbYk;
    
    private Result(boolean paramBoolean, zzbpe paramzzbpe)
    {
      this.zzbYj = paramBoolean;
      this.zzbYk = paramzzbpe;
    }
    
    public boolean isSuccess()
    {
      return this.zzbYj;
    }
    
    public zzbpe zzUY()
    {
      return this.zzbYk;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\firebase\database\Transaction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */