package com.tawseel.tawseel;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class CustomAlertDialog
  extends AlertDialog
{
  Context context;
  
  public CustomAlertDialog(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
  }
  
  public static class Builder
    extends AlertDialog.Builder
  {
    Context builderContext;
    
    public Builder(Context paramContext)
    {
      super();
      this.builderContext = paramContext;
    }
    
    public AlertDialog show()
    {
      try
      {
        AlertDialog localAlertDialog = super.show();
        int i = (int)getContext().getResources().getDisplayMetrics().density * 6;
        Object localObject = localAlertDialog.getButton(-1);
        Button localButton = localAlertDialog.getButton(-2);
        if (localButton != null)
        {
          Log.d("Tawseel", "button neg");
          localButton.setBackgroundResource(2130837598);
          localButton.setTextColor(this.builderContext.getResources().getColor(2131427349));
          LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -2);
          if (localObject != null) {
            localLayoutParams.setMargins(5, 0, 5, 0);
          }
          localButton.setLayoutParams(localLayoutParams);
          localButton.setPadding(i, i, i, i);
        }
        if (localObject != null)
        {
          Log.d("Tawseel", "button pos");
          ((Button)localObject).setBackgroundResource(2130837598);
          ((Button)localObject).setTextColor(this.builderContext.getResources().getColor(2131427349));
          ((Button)localObject).setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
          ((Button)localObject).setPadding(i, i, i, i);
        }
        localObject = localAlertDialog.findViewById(this.builderContext.getResources().getIdentifier("alertMessage", "id", "android"));
        if (localObject != null)
        {
          ((TextView)localObject).setTextSize(16.0F);
          ((TextView)localObject).setTypeface(Typeface.createFromAsset(getContext().getAssets(), "DroidKufi-Bold.ttf"));
          return localAlertDialog;
        }
        Log.e("TITLE NULL", "TITLENULL");
        return localAlertDialog;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      return null;
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\CustomAlertDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */