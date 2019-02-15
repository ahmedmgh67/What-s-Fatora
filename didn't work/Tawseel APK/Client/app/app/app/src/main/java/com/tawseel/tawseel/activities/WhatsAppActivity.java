package com.tawseel.tawseel.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WhatsAppActivity
  extends BaseActivity
{
  private EditText address;
  private String latStr = null;
  private String remainder = null;
  
  private Double convertDegreesToDecimal(String paramString)
  {
    double d1 = 0.0D;
    try
    {
      double d5 = Double.parseDouble(paramString.split("°")[0]);
      Log.d("Tawseel", "d " + d5);
      double d2 = Double.parseDouble(paramString.split("°")[1].split("'")[0]);
      Log.d("Tawseel", "m " + d2);
      double d3 = Double.parseDouble(paramString.split("°")[1].split("'")[1].split("\"")[0]);
      Log.d("Tawseel", "s " + d3);
      double d4 = Math.signum(d5);
      d5 = Math.abs(d5);
      d1 = d4 * (d5 + d2 / 60.0D + d3 / 3600.0D);
    }
    catch (NumberFormatException paramString)
    {
      for (;;)
      {
        showAlertWithMessage(getString(2131099735));
      }
    }
    return Double.valueOf(d1);
  }
  
  private boolean isAddressInDegrees(String paramString)
  {
    if ((paramString.contains("°")) && (paramString.contains("'")) && (paramString.contains("\"")))
    {
      if (paramString.contains("N "))
      {
        this.latStr = paramString.split("N ")[0];
        this.remainder = paramString.split("N ")[1];
        return true;
      }
      if (paramString.contains("N"))
      {
        this.latStr = paramString.split("N")[0];
        this.remainder = paramString.split("N")[1];
        return true;
      }
      if (paramString.contains(" "))
      {
        this.latStr = paramString.split(" ")[0];
        this.remainder = paramString.split(" ")[1];
        return true;
      }
    }
    return false;
  }
  
  private boolean validData()
  {
    if (this.address.getText().toString().isEmpty())
    {
      showAlertWithMessage(getString(2131099732));
      return false;
    }
    if (!isAddressInDegrees(this.address.getText().toString()))
    {
      showAlertWithMessage(getString(2131099735));
      return false;
    }
    return true;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903074);
    Object localObject = (Toolbar)findViewById(2131493018);
    paramBundle = (TextView)((Toolbar)localObject).findViewById(2131492950);
    localObject = (TextView)((Toolbar)localObject).findViewById(2131493020);
    paramBundle.setText(2131099789);
    ((TextView)localObject).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        WhatsAppActivity.this.onBackPressed();
      }
    });
    this.address = ((EditText)findViewById(2131493017));
    ((Button)findViewById(2131493016)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if ((WhatsAppActivity.this.validData()) && (WhatsAppActivity.this.convertDegreesToDecimal(WhatsAppActivity.this.latStr).doubleValue() != 0.0D) && (WhatsAppActivity.this.convertDegreesToDecimal(WhatsAppActivity.this.remainder.split("E")[0]).doubleValue() != 0.0D))
        {
          paramAnonymousView = new Intent();
          Log.d("Tawseel", "lat degrees" + WhatsAppActivity.this.latStr);
          Log.d("Tawseel", "lng degrees" + WhatsAppActivity.this.remainder.split("E")[0]);
          Log.d("Tawseel", "lat decimal" + WhatsAppActivity.this.convertDegreesToDecimal(WhatsAppActivity.this.latStr));
          Log.d("Tawseel", "lng decimal" + WhatsAppActivity.this.convertDegreesToDecimal(WhatsAppActivity.this.remainder.split("E")[0]));
          paramAnonymousView.putExtra("lat", WhatsAppActivity.this.convertDegreesToDecimal(WhatsAppActivity.this.latStr));
          paramAnonymousView.putExtra("lng", WhatsAppActivity.this.convertDegreesToDecimal(WhatsAppActivity.this.remainder.split("E")[0]));
          WhatsAppActivity.this.setResult(-1, paramAnonymousView);
          WhatsAppActivity.this.finish();
        }
      }
    });
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\activities\WhatsAppActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */