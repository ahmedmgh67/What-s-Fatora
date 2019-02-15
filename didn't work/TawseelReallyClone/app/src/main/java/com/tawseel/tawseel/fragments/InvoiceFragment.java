package com.tawseel.tawseel.fragments;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.tawseel.tawseel.CustomApplication;
import com.tawseel.tawseel.GenericCallback;
import com.tawseel.tawseel.activities.MainActivity;
import com.tawseel.tawseel.helpers.FirebaseHelper;
import com.tawseel.tawseel.helpers.Formatter;
import com.tawseel.tawseel.helpers.HelperMethods;
import com.tawseel.tawseel.models.Driver;
import com.tawseel.tawseel.models.Order;
import com.tawseel.tawseel.models.Promotion;
import de.hdodenhof.circleimageview.CircleImageView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class InvoiceFragment
  extends BaseFragment
  implements View.OnClickListener
{
  private Boolean driverHasImage;
  private String driverNameValue;
  private TextView mActualDurationTextView;
  private TextView mActualFareTextView;
  private TextView mDestinationTextView;
  private TextView mDistanceTextView;
  private Button mDoneButton;
  private CircleImageView mDriverImage;
  private TextView mDriverNameTextView;
  private TextView mSourceTextView;
  private TextView mStartDateTextView;
  private TextView mTotalFareDiscountTextView;
  private TextView mTotalFareTextView;
  private Order order;
  private String orderId;
  private ProgressDialog progressDialog;
  private SimpleDateFormat sdf;
  
  private void getOrderByIdAndBindData()
  {
    this.progressDialog = startProgress();
    FirebaseHelper.getInstance().getOrderById(this.orderId, new GenericCallback()
    {
      public void onError(Object paramAnonymousObject, String paramAnonymousString)
      {
        HelperMethods.dismissDialog(InvoiceFragment.this.progressDialog);
        if (InvoiceFragment.this.getContext() != null) {
          Toast.makeText(InvoiceFragment.this.getContext(), "Error: " + paramAnonymousString, 1).show();
        }
      }
      
      public void onSuccess(Object paramAnonymousObject, String paramAnonymousString)
      {
        InvoiceFragment.access$002(InvoiceFragment.this, (Order)paramAnonymousObject);
        FirebaseHelper.getInstance().getDriverData(InvoiceFragment.this.order.driverID, new GenericCallback()
        {
          public void onError(Object paramAnonymous2Object, String paramAnonymous2String)
          {
            HelperMethods.dismissDialog(InvoiceFragment.this.progressDialog);
          }
          
          public void onSuccess(Object paramAnonymous2Object, String paramAnonymous2String)
          {
            paramAnonymous2Object = (Driver)paramAnonymous2Object;
            for (;;)
            {
              try
              {
                InvoiceFragment.this.mSourceTextView.setText(InvoiceFragment.this.order.pickupAddress);
                InvoiceFragment.this.mDestinationTextView.setText(InvoiceFragment.this.order.dropOffAddress);
                if (InvoiceFragment.this.order.acceptedAt.doubleValue() == 0.0D) {
                  continue;
                }
                paramAnonymous2String = new Date(InvoiceFragment.this.order.acceptedAt.longValue());
                Log.e("Tawseel", "date1" + InvoiceFragment.this.order.acceptedAt.longValue());
                InvoiceFragment.this.mStartDateTextView.setText(InvoiceFragment.this.sdf.format(paramAnonymous2String));
                if ((InvoiceFragment.this.order.startTime.doubleValue() == 0.0D) || (InvoiceFragment.this.order.endTime.doubleValue() == 0.0D)) {
                  continue;
                }
                paramAnonymous2String = new Date(InvoiceFragment.this.order.startTime.longValue());
                Date localDate = new Date(InvoiceFragment.this.order.endTime.longValue());
                Log.e("Tawseel", "date2" + InvoiceFragment.this.order.endTime.longValue());
                long l = Formatter.getInstance(CustomApplication.getAppContext()).getDateDiff(paramAnonymous2String, localDate, TimeUnit.MINUTES);
                InvoiceFragment.this.mActualDurationTextView.setText(Formatter.getInstance(CustomApplication.getAppContext()).formatTime(Long.valueOf(l).intValue()));
              }
              catch (Exception paramAnonymous2Object)
              {
                double d1;
                double d2;
                if (((Exception)paramAnonymous2Object).getMessage() == null) {
                  continue;
                }
                Log.e("Exception ", ((Exception)paramAnonymous2Object).getMessage());
                continue;
                InvoiceFragment.this.mActualDurationTextView.setText("-");
                continue;
                InvoiceFragment.this.mTotalFareTextView.setText(Formatter.getInstance(CustomApplication.getAppContext()).formatCurrency(InvoiceFragment.this.order.fare.doubleValue()));
                continue;
              }
              InvoiceFragment.this.mActualFareTextView.setText(Formatter.getInstance(CustomApplication.getAppContext()).formatCurrency(InvoiceFragment.this.order.fare.doubleValue()));
              if ((InvoiceFragment.this.order.promotion == null) || (InvoiceFragment.this.order.promotion.getDiscountAmount() <= 0.0D)) {
                continue;
              }
              InvoiceFragment.this.mTotalFareDiscountTextView.setVisibility(0);
              d1 = InvoiceFragment.this.order.fare.doubleValue();
              d2 = InvoiceFragment.this.order.promotion.getDiscountAmount();
              InvoiceFragment.this.mTotalFareTextView.setText(Formatter.getInstance(InvoiceFragment.this.getActivity()).formatCurrency(d1 + d2));
              InvoiceFragment.this.mTotalFareTextView.setPaintFlags(InvoiceFragment.this.mTotalFareTextView.getPaintFlags() | 0x10);
              InvoiceFragment.this.mTotalFareDiscountTextView.setText(Formatter.getInstance(CustomApplication.getAppContext()).formatCurrency(InvoiceFragment.this.order.fare.doubleValue()));
              InvoiceFragment.access$902(InvoiceFragment.this, ((Driver)paramAnonymous2Object).name);
              InvoiceFragment.access$1002(InvoiceFragment.this, Boolean.valueOf(((Driver)paramAnonymous2Object).hasProfile));
              InvoiceFragment.this.mDriverNameTextView.setText(((Driver)paramAnonymous2Object).name);
              InvoiceFragment.this.mDistanceTextView.setText(Formatter.getInstance(CustomApplication.getAppContext()).formatDistance(InvoiceFragment.this.order.distance.doubleValue()));
              if (((Driver)paramAnonymous2Object).hasProfile) {
                FirebaseHelper.getInstance().getDriverImageFromStorage(InvoiceFragment.this.order.driverID, new GenericCallback()
                {
                  public void onError(Object paramAnonymous3Object, String paramAnonymous3String) {}
                  
                  public void onSuccess(Object paramAnonymous3Object, String paramAnonymous3String)
                  {
                    paramAnonymous3Object = (byte[])paramAnonymous3Object;
                    paramAnonymous3Object = BitmapFactory.decodeByteArray((byte[])paramAnonymous3Object, 0, paramAnonymous3Object.length);
                    InvoiceFragment.this.mDriverImage.setImageBitmap((Bitmap)paramAnonymous3Object);
                  }
                });
              }
              HelperMethods.dismissDialog(InvoiceFragment.this.progressDialog);
              return;
              InvoiceFragment.this.mStartDateTextView.setText("-");
            }
          }
        });
      }
    });
  }
  
  public static InvoiceFragment newInstance(String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    InvoiceFragment localInvoiceFragment = new InvoiceFragment();
    Bundle localBundle = new Bundle();
    localBundle.putString("orderId", paramString);
    localBundle.putBoolean("hideNextButton", paramBoolean1);
    localBundle.putBoolean("fromHistory", paramBoolean2);
    localInvoiceFragment.setArguments(localBundle);
    return localInvoiceFragment;
  }
  
  private ProgressDialog startProgress()
  {
    ProgressDialog localProgressDialog = new ProgressDialog(getActivity());
    localProgressDialog.setMessage(getResources().getString(2131099752));
    localProgressDialog.setIndeterminate(true);
    localProgressDialog.show();
    return localProgressDialog;
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    }
    do
    {
      do
      {
        return;
        if (!this.mDoneButton.getText().equals(getString(2131099720))) {
          break;
        }
      } while ((this.order == null) || (this.driverNameValue == null));
      paramView = new Bundle();
      paramView.putParcelable("order", this.order);
      paramView.putString("driverNameValue", this.driverNameValue);
      paramView.putBoolean("driverHasImage", this.driverHasImage.booleanValue());
      try
      {
        ((MainActivity)getActivity()).replaceFragment(paramView);
        return;
      }
      catch (Exception paramView)
      {
        paramView.printStackTrace();
        return;
      }
    } while (this.order == null);
    paramView = new Bundle();
    paramView.putParcelable("order", this.order);
    ((MainActivity)getActivity()).goToCompletedTripOrderReview(paramView);
  }
  
  public void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = ((MainActivity)getActivity()).getDeclineTextView();
    if ((paramBundle != null) && (paramBundle.getVisibility() == 0)) {
      paramBundle.setVisibility(8);
    }
    if ((getArguments() != null) && (!getArguments().getBoolean("fromHistory")))
    {
      paramBundle = ((MainActivity)getActivity()).getBack();
      if (paramBundle != null) {
        paramBundle.setVisibility(0);
      }
    }
    do
    {
      return;
      paramBundle = ((MainActivity)getActivity()).getBack();
    } while (paramBundle == null);
    paramBundle.setVisibility(8);
  }
  
  @Nullable
  public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    setActionBarTitle(2131099830);
    if (getArguments() != null) {
      this.orderId = getArguments().getString("orderId");
    }
    paramLayoutInflater = paramLayoutInflater.inflate(2130903101, paramViewGroup, false);
    this.mActualFareTextView = ((TextView)paramLayoutInflater.findViewById(2131493087));
    this.mActualDurationTextView = ((TextView)paramLayoutInflater.findViewById(2131493097));
    this.mDriverNameTextView = ((TextView)paramLayoutInflater.findViewById(2131493083));
    this.mStartDateTextView = ((TextView)paramLayoutInflater.findViewById(2131493093));
    this.mDistanceTextView = ((TextView)paramLayoutInflater.findViewById(2131493095));
    this.mTotalFareTextView = ((TextView)paramLayoutInflater.findViewById(2131493100));
    this.mTotalFareDiscountTextView = ((TextView)paramLayoutInflater.findViewById(2131493101));
    this.mSourceTextView = ((TextView)paramLayoutInflater.findViewById(2131493089));
    this.mDestinationTextView = ((TextView)paramLayoutInflater.findViewById(2131493091));
    this.mDriverImage = ((CircleImageView)paramLayoutInflater.findViewById(2131493051));
    this.mDoneButton = ((Button)paramLayoutInflater.findViewById(2131493102));
    this.sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    if ((getArguments() != null) && (getArguments().getBoolean("fromHistory")))
    {
      this.mDoneButton.setVisibility(0);
      this.mDoneButton.setText(getString(2131099822));
    }
    for (;;)
    {
      if (this.orderId != null) {
        getOrderByIdAndBindData();
      }
      this.mDoneButton.setOnClickListener(this);
      return paramLayoutInflater;
      if ((getArguments() != null) && (getArguments().getBoolean("hideNextButton"))) {
        this.mDoneButton.setVisibility(8);
      } else {
        this.mDoneButton.setVisibility(0);
      }
    }
  }
  
  public void onPause()
  {
    super.onPause();
    HelperMethods.dismissDialog(((MainActivity)getActivity()).getDriverAcceptedDialog());
  }
  
  public void onResume()
  {
    super.onResume();
    setActionBarTitle(2131099830);
    TextView localTextView = ((MainActivity)getActivity()).getDeclineTextView();
    if ((localTextView != null) && (localTextView.getVisibility() == 0)) {
      localTextView.setVisibility(8);
    }
    if ((getArguments() != null) && (getArguments().getBoolean("fromHistory")))
    {
      localTextView = ((MainActivity)getActivity()).getBack();
      if (localTextView != null) {
        localTextView.setVisibility(0);
      }
    }
    do
    {
      return;
      localTextView = ((MainActivity)getActivity()).getBack();
    } while (localTextView == null);
    localTextView.setVisibility(8);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\fragments\InvoiceFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */