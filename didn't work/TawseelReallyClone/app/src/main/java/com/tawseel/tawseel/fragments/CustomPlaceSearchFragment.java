package com.tawseel.tawseel.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResolvingResultCallbacks;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompleteFilter.Builder;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.GeoDataApi;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.SphericalUtil;
import com.tawseel.tawseel.activities.MainActivity;
import com.tawseel.tawseel.adapters.CustomPlaceSearchAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomPlaceSearchFragment
  extends BaseFragment
{
  private static final String CENTER_KEY = "CENTER_KEY";
  public static final int REQUEST_GOOGLE_PLACE = 1;
  public static final String SELECTED_PLACE = "SELECTED_PLACE";
  private static final String TAG = CustomPlaceSearchFragment.class.getSimpleName();
  private List<AutocompletePrediction> autocompletePredictionList;
  private LatLng centerLatLng;
  private CustomPlaceSearchAdapter customPlaceSearchAdapter;
  private GoogleApiClient mGoogleApiClient;
  private ProgressBar progressBar;
  
  public static CustomPlaceSearchFragment newInstance(LatLng paramLatLng)
  {
    Bundle localBundle = new Bundle();
    CustomPlaceSearchFragment localCustomPlaceSearchFragment = new CustomPlaceSearchFragment();
    localBundle.putParcelable("CENTER_KEY", paramLatLng);
    localCustomPlaceSearchFragment.setArguments(localBundle);
    return localCustomPlaceSearchFragment;
  }
  
  private void searchPlaces(String paramString)
  {
    if ((this.mGoogleApiClient != null) && (this.mGoogleApiClient.isConnected()))
    {
      this.progressBar.setVisibility(0);
      LatLngBounds localLatLngBounds = new LatLngBounds(SphericalUtil.computeOffset(this.centerLatLng, 100.0D, 225.0D), SphericalUtil.computeOffset(this.centerLatLng, 100.0D, 45.0D));
      AutocompleteFilter localAutocompleteFilter = new AutocompleteFilter.Builder().setTypeFilter(0).build();
      Places.GeoDataApi.getAutocompletePredictions(this.mGoogleApiClient, paramString, localLatLngBounds, localAutocompleteFilter).setResultCallback(new ResolvingResultCallbacks(getActivity(), 0)
      {
        public void onSuccess(@NonNull AutocompletePredictionBuffer paramAnonymousAutocompletePredictionBuffer)
        {
          CustomPlaceSearchFragment.this.autocompletePredictionList.clear();
          Log.d(CustomPlaceSearchFragment.TAG, "on success");
          paramAnonymousAutocompletePredictionBuffer = paramAnonymousAutocompletePredictionBuffer.iterator();
          while (paramAnonymousAutocompletePredictionBuffer.hasNext())
          {
            AutocompletePrediction localAutocompletePrediction = (AutocompletePrediction)paramAnonymousAutocompletePredictionBuffer.next();
            Log.d(CustomPlaceSearchFragment.TAG, "place> " + localAutocompletePrediction.getPrimaryText(null) + " \n\r" + localAutocompletePrediction.getSecondaryText(null));
            CustomPlaceSearchFragment.this.autocompletePredictionList.add(localAutocompletePrediction);
          }
          CustomPlaceSearchFragment.this.customPlaceSearchAdapter.notifyDataSetChanged();
          CustomPlaceSearchFragment.this.progressBar.setVisibility(4);
        }
        
        public void onUnresolvableFailure(@NonNull Status paramAnonymousStatus)
        {
          Log.d(CustomPlaceSearchFragment.TAG, "place> on failure > " + paramAnonymousStatus);
          CustomPlaceSearchFragment.this.progressBar.setVisibility(4);
        }
      });
    }
  }
  
  public void getPlace(String paramString)
  {
    if (getActivity() == null) {
      return;
    }
    final ProgressDialog localProgressDialog = new ProgressDialog(getActivity());
    localProgressDialog.setMessage(getString(2131099752));
    localProgressDialog.setCancelable(false);
    localProgressDialog.show();
    Places.GeoDataApi.getPlaceById(this.mGoogleApiClient, new String[] { paramString }).setResultCallback(new ResolvingResultCallbacks(getActivity(), 0)
    {
      public void onSuccess(@NonNull PlaceBuffer paramAnonymousPlaceBuffer)
      {
        localProgressDialog.hide();
        Log.d(CustomPlaceSearchFragment.TAG, "place> " + paramAnonymousPlaceBuffer.getStatus().isSuccess() + " ," + paramAnonymousPlaceBuffer.getCount() + " ," + paramAnonymousPlaceBuffer.get(0));
        MainActivity localMainActivity = CustomPlaceSearchFragment.this.getMainActivity();
        if ((paramAnonymousPlaceBuffer.getStatus().isSuccess()) && (paramAnonymousPlaceBuffer.getCount() > 0) && (localMainActivity != null))
        {
          Intent localIntent = new Intent();
          localIntent.putExtra("SELECTED_PLACE", paramAnonymousPlaceBuffer.get(0).getLatLng());
          CustomPlaceSearchFragment.this.getTargetFragment().onActivityResult(1, -1, localIntent);
          localMainActivity.onBackPressed();
        }
      }
      
      public void onUnresolvableFailure(@NonNull Status paramAnonymousStatus)
      {
        localProgressDialog.hide();
      }
    });
  }
  
  @Nullable
  public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2130903096, paramViewGroup, false);
    paramViewGroup = (SearchView)paramLayoutInflater.findViewById(2131493065);
    paramViewGroup.requestFocus();
    paramViewGroup.setOnQueryTextListener(new SearchView.OnQueryTextListener()
    {
      public boolean onQueryTextChange(String paramAnonymousString)
      {
        return false;
      }
      
      public boolean onQueryTextSubmit(String paramAnonymousString)
      {
        Log.d(CustomPlaceSearchFragment.TAG, "search text> " + paramAnonymousString);
        CustomPlaceSearchFragment.this.getMainActivity().hideKeyboardIfShown();
        CustomPlaceSearchFragment.this.searchPlaces(paramAnonymousString);
        return false;
      }
    });
    this.mGoogleApiClient = new GoogleApiClient.Builder(getActivity()).addApi(Places.GEO_DATA_API).build();
    this.centerLatLng = ((LatLng)getArguments().getParcelable("CENTER_KEY"));
    this.progressBar = ((ProgressBar)paramLayoutInflater.findViewById(2131493067));
    paramViewGroup = (RecyclerView)paramLayoutInflater.findViewById(2131493066);
    paramViewGroup.setLayoutManager(new LinearLayoutManager(getActivity()));
    this.autocompletePredictionList = new ArrayList();
    this.customPlaceSearchAdapter = new CustomPlaceSearchAdapter(this.autocompletePredictionList, new OnSearchPlaceSelectedListener()
    {
      public void onSearchPlaceSelected(String paramAnonymousString)
      {
        CustomPlaceSearchFragment.this.getPlace(paramAnonymousString);
      }
    });
    paramViewGroup.setAdapter(this.customPlaceSearchAdapter);
    paramLayoutInflater.findViewById(2131493064).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CustomPlaceSearchFragment.this.getActivity().onBackPressed();
      }
    });
    return paramLayoutInflater;
  }
  
  public void onStart()
  {
    super.onStart();
    if (this.mGoogleApiClient != null) {
      this.mGoogleApiClient.connect();
    }
    getMainActivity().getSupportActionBar().hide();
  }
  
  public void onStop()
  {
    super.onStop();
    if ((this.mGoogleApiClient != null) && (this.mGoogleApiClient.isConnected())) {
      this.mGoogleApiClient.disconnect();
    }
    getMainActivity().getSupportActionBar().show();
  }
  
  public static abstract interface OnSearchPlaceSelectedListener
  {
    public abstract void onSearchPlaceSelected(String paramString);
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\fragments\CustomPlaceSearchFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */