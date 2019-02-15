package com.google.android.gms.location.places.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import com.google.android.gms.R.id;
import com.google.android.gms.R.layout;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLngBounds;

public class SupportPlaceAutocompleteFragment
  extends Fragment
{
  private View zzbmN;
  private View zzbmO;
  private EditText zzbmP;
  private boolean zzbmQ;
  @Nullable
  private LatLngBounds zzbmR;
  @Nullable
  private AutocompleteFilter zzbmS;
  @Nullable
  private PlaceSelectionListener zzbmT;
  
  private void zzIv()
  {
    int j = 0;
    View localView;
    if (!this.zzbmP.getText().toString().isEmpty())
    {
      i = 1;
      localView = this.zzbmO;
      if (i == 0) {
        break label42;
      }
    }
    label42:
    for (int i = j;; i = 8)
    {
      localView.setVisibility(i);
      return;
      i = 0;
      break;
    }
  }
  
  private void zzIw()
  {
    try
    {
      Intent localIntent = new PlaceAutocomplete.IntentBuilder(2).setBoundsBias(this.zzbmR).setFilter(this.zzbmS).zzfd(this.zzbmP.getText().toString()).zzkU(1).build(getActivity());
      this.zzbmQ = true;
      startActivityForResult(localIntent, 30421);
      i = -1;
    }
    catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException)
    {
      for (;;)
      {
        i = localGooglePlayServicesRepairableException.getConnectionStatusCode();
        Log.e("Places", "Could not open autocomplete activity", localGooglePlayServicesRepairableException);
      }
    }
    catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
    {
      for (;;)
      {
        int i = localGooglePlayServicesNotAvailableException.errorCode;
        Log.e("Places", "Could not open autocomplete activity", localGooglePlayServicesNotAvailableException);
      }
    }
    if (i != -1) {
      GoogleApiAvailability.getInstance().showErrorDialogFragment(getActivity(), i, 30422);
    }
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    this.zzbmQ = false;
    Object localObject;
    if (paramInt1 == 30421)
    {
      if (paramInt2 != -1) {
        break label69;
      }
      localObject = PlaceAutocomplete.getPlace(getActivity(), paramIntent);
      if (this.zzbmT != null) {
        this.zzbmT.onPlaceSelected((Place)localObject);
      }
      setText(((Place)localObject).getName().toString());
    }
    for (;;)
    {
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
      return;
      label69:
      if (paramInt2 == 2)
      {
        localObject = PlaceAutocomplete.getStatus(getActivity(), paramIntent);
        if (this.zzbmT != null) {
          this.zzbmT.onError((Status)localObject);
        }
      }
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(R.layout.place_autocomplete_fragment, paramViewGroup, false);
    this.zzbmN = paramLayoutInflater.findViewById(R.id.place_autocomplete_search_button);
    this.zzbmO = paramLayoutInflater.findViewById(R.id.place_autocomplete_clear_button);
    this.zzbmP = ((EditText)paramLayoutInflater.findViewById(R.id.place_autocomplete_search_input));
    paramViewGroup = new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!SupportPlaceAutocompleteFragment.zza(SupportPlaceAutocompleteFragment.this)) {
          SupportPlaceAutocompleteFragment.zzb(SupportPlaceAutocompleteFragment.this);
        }
      }
    };
    this.zzbmN.setOnClickListener(paramViewGroup);
    this.zzbmP.setOnClickListener(paramViewGroup);
    this.zzbmO.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SupportPlaceAutocompleteFragment.this.setText("");
      }
    });
    zzIv();
    return paramLayoutInflater;
  }
  
  public void onDestroyView()
  {
    this.zzbmN = null;
    this.zzbmO = null;
    this.zzbmP = null;
    super.onDestroyView();
  }
  
  public void setBoundsBias(@Nullable LatLngBounds paramLatLngBounds)
  {
    this.zzbmR = paramLatLngBounds;
  }
  
  public void setFilter(@Nullable AutocompleteFilter paramAutocompleteFilter)
  {
    this.zzbmS = paramAutocompleteFilter;
  }
  
  public void setHint(CharSequence paramCharSequence)
  {
    this.zzbmP.setHint(paramCharSequence);
    this.zzbmN.setContentDescription(paramCharSequence);
  }
  
  public void setOnPlaceSelectedListener(PlaceSelectionListener paramPlaceSelectionListener)
  {
    this.zzbmT = paramPlaceSelectionListener;
  }
  
  public void setText(CharSequence paramCharSequence)
  {
    this.zzbmP.setText(paramCharSequence);
    zzIv();
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\android\gms\location\places\ui\SupportPlaceAutocompleteFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */