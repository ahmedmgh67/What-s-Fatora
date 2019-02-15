package com.tawseel.tawseel.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.tawseel.tawseel.CustomApplication;
import com.tawseel.tawseel.GenericCallback;
import com.tawseel.tawseel.Preferences;
import com.tawseel.tawseel.Security;

public class MessageAndActionActivity
  extends BaseActivity
{
  private Button btnAction;
  private Button btnRetry;
  String key;
  private ProgressDialog progress;
  TextView tvMessage;
  
  private void actionWhenBlocked()
  {
    Security.getInstance(this).logout(new GenericCallback()
    {
      public void onError(Object paramAnonymousObject, String paramAnonymousString) {}
      
      public void onSuccess(Object paramAnonymousObject, String paramAnonymousString)
      {
        Preferences.getInstance(CustomApplication.getAppContext()).removeUserData();
        paramAnonymousObject = new Intent(MessageAndActionActivity.this, SplashActivity.class);
        MessageAndActionActivity.this.startActivity((Intent)paramAnonymousObject);
        MessageAndActionActivity.this.finish();
      }
    });
  }
  
  private void setActionOnClickListener()
  {
    this.btnAction.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = MessageAndActionActivity.this.key;
        int i = -1;
        switch (paramAnonymousView.hashCode())
        {
        }
        for (;;)
        {
          switch (i)
          {
          default: 
            return;
            if (paramAnonymousView.equals("blocked"))
            {
              i = 0;
              continue;
              if (paramAnonymousView.equals("connection")) {
                i = 1;
              }
            }
            break;
          }
        }
        MessageAndActionActivity.this.actionWhenBlocked();
      }
    });
  }
  
  private void setRetryOnClickListener()
  {
    this.btnRetry.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MessageAndActionActivity.access$002(MessageAndActionActivity.this, MessageAndActionActivity.this.startProgress());
        paramAnonymousView = MessageAndActionActivity.this.key;
        int i = -1;
        switch (paramAnonymousView.hashCode())
        {
        }
        for (;;)
        {
          switch (i)
          {
          }
          return;
          if (paramAnonymousView.equals("blocked"))
          {
            i = 0;
            continue;
            if (paramAnonymousView.equals("connection")) {
              i = 1;
            }
          }
        }
      }
    });
  }
  
  private ProgressDialog startProgress()
  {
    ProgressDialog localProgressDialog = new ProgressDialog(this);
    localProgressDialog.setMessage(getString(2131099752));
    localProgressDialog.setCancelable(false);
    localProgressDialog.show();
    return localProgressDialog;
  }
  
  public void onBackPressed() {}
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903071);
    this.key = getIntent().getStringExtra("key");
    if (this.key == null) {
      return;
    }
    this.btnRetry = ((Button)findViewById(2131493009));
    this.btnAction = ((Button)findViewById(2131493010));
    this.tvMessage = ((TextView)findViewById(2131493008));
    paramBundle = this.key;
    int i = -1;
    switch (paramBundle.hashCode())
    {
    default: 
      switch (i)
      {
      }
      break;
    }
    for (;;)
    {
      setActionOnClickListener();
      setRetryOnClickListener();
      return;
      if (!paramBundle.equals("blocked")) {
        break;
      }
      i = 0;
      break;
      if (!paramBundle.equals("connection")) {
        break;
      }
      i = 1;
      break;
      this.tvMessage.setText(getString(2131099690));
      this.btnRetry.setVisibility(4);
      this.btnAction.setText(getString(2131099694));
    }
  }
  
  protected void onPause()
  {
    super.onPause();
  }
  
  protected void onResume()
  {
    super.onResume();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\activities\MessageAndActionActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */