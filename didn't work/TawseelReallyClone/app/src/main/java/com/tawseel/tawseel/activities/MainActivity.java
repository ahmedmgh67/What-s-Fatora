package com.tawseel.tawseel.activities;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ValueEventListener;
import com.tawseel.tawseel.Constants.ClientPresenceStatus;
import com.tawseel.tawseel.Constants.TripStatus;
import com.tawseel.tawseel.CustomApplication;
import com.tawseel.tawseel.GenericCallback;
import com.tawseel.tawseel.Preferences;
import com.tawseel.tawseel.Security;
import com.tawseel.tawseel.Settings;
import com.tawseel.tawseel.fragments.AboutUsFragment;
import com.tawseel.tawseel.fragments.CompletedTripOrderReviewFragment;
import com.tawseel.tawseel.fragments.CustomPlaceSearchFragment;
import com.tawseel.tawseel.fragments.EnRouteTripFragment;
import com.tawseel.tawseel.fragments.HistoryFragment;
import com.tawseel.tawseel.fragments.InProgressFragment;
import com.tawseel.tawseel.fragments.IntroFragment;
import com.tawseel.tawseel.fragments.OnTripOrderReviewFragment;
import com.tawseel.tawseel.fragments.PickupLocationFragment;
import com.tawseel.tawseel.fragments.ProfileFragment;
import com.tawseel.tawseel.fragments.RecipientDetailsFragment;
import com.tawseel.tawseel.fragments.RecipientLocationFragment;
import com.tawseel.tawseel.fragments.SenderDetailsFragment;
import com.tawseel.tawseel.fragments.ShareFragment;
import com.tawseel.tawseel.helpers.FirebaseHelper;
import com.tawseel.tawseel.helpers.HelperMethods;
import com.tawseel.tawseel.helpers.InvitationListener;
import com.tawseel.tawseel.models.Order;
import com.tawseel.tawseel.network.APIConnector;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import sa.tawseel.tawseelcommon.utils.PhoneUtils;

public class MainActivity
  extends BaseActivity
  implements NavigationView.OnNavigationItemSelectedListener
{
  private static final int CONTACT_PICKER_RESULT = 0;
  private static final int DIAL_PHONE = 4;
  public static final String FCM_TOKEN_UPDATED_FILTER = "FCM_TOKEN_UPDATED_FILTER";
  public static final String NAVIGATION_TAG = MainActivity.class.getSimpleName();
  private static final int REQUEST_IMAGE_CAPTURE = 2;
  private static final int SELECT_PICTURE_FILE = 1;
  private static final String TAG = MainActivity.class.getSimpleName();
  private static final int WHATS_APP_CODE = 5;
  private LinearLayout addressLayout;
  private TextView back;
  private TextView decline;
  private AlertDialog driverAcceptedDialog;
  private BroadcastReceiver fcmTokenUpdatedReciever;
  private String filePath = null;
  private String imageFilePath;
  private boolean isGPSEnabled;
  private LocationManager lm;
  private Location location;
  private FirebaseAuth mAuth;
  private FirebaseAuth.AuthStateListener mAuthListener;
  private ImageView mapTypeButton;
  private RelativeLayout mapTypeParentLayout;
  private Order order;
  private ValueEventListener orderStatusValueEventListener;
  private PopupWindow popupWindow;
  private TextView title;
  
  private void checkFCMTokenUpdated()
  {
    final Preferences localPreferences = Preferences.getInstance(this);
    if ((localPreferences.getCashedBoolean("FCM_DEVICE_TOKEN_UPDATED")) && (!localPreferences.getUserPhone().isEmpty())) {
      FirebaseHelper.getInstance().saveFCMToken(localPreferences.getUserPhone(), localPreferences.getCashedString("FCM_DEVICE_TOKEN"), new GenericCallback()
      {
        public void onError(Object paramAnonymousObject, String paramAnonymousString)
        {
          Log.d(MainActivity.TAG, "Error in saving FCM token");
        }
        
        public void onSuccess(Object paramAnonymousObject, String paramAnonymousString)
        {
          Log.d(MainActivity.TAG, "FCM token saved successfully");
          localPreferences.cacheBooleanValue("FCM_DEVICE_TOKEN_UPDATED", false);
        }
      }, this);
    }
  }
  
  private static String getDataColumn(Context paramContext, Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    Context localContext = null;
    try
    {
      paramContext = paramContext.getContentResolver().query(paramUri, new String[] { "_data" }, paramString, paramArrayOfString, null);
      if (paramContext != null)
      {
        localContext = paramContext;
        if (paramContext.moveToFirst())
        {
          localContext = paramContext;
          paramUri = paramContext.getString(paramContext.getColumnIndexOrThrow("_data"));
          return paramUri;
        }
      }
      return null;
    }
    finally
    {
      if (localContext != null) {
        localContext.close();
      }
    }
  }
  
  private Uri getImageUri(Context paramContext, Bitmap paramBitmap)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramBitmap.compress(Bitmap.CompressFormat.JPEG, 100, localByteArrayOutputStream);
    return Uri.parse(MediaStore.Images.Media.insertImage(paramContext.getContentResolver(), paramBitmap, "Title", null));
  }
  
  private static String getPath(Context paramContext, Uri paramUri)
  {
    Object localObject1 = null;
    if (Build.VERSION.SDK_INT >= 19) {
      if (DocumentsContract.isDocumentUri(paramContext, paramUri)) {
        if (isExternalStorageDocument(paramUri))
        {
          paramContext = DocumentsContract.getDocumentId(paramUri).split(":");
          if ("primary".equalsIgnoreCase(paramContext[0])) {
            localObject1 = Environment.getExternalStorageDirectory() + "/" + paramContext[1];
          }
        }
      }
    }
    do
    {
      do
      {
        do
        {
          return (String)localObject1;
          if (isDownloadsDocument(paramUri))
          {
            paramUri = DocumentsContract.getDocumentId(paramUri);
            return getDataColumn(paramContext, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(paramUri).longValue()), null, null);
          }
        } while (!isMediaDocument(paramUri));
        localObject1 = DocumentsContract.getDocumentId(paramUri).split(":");
        Object localObject2 = localObject1[0];
        paramUri = null;
        if ("image".equals(localObject2)) {
          paramUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }
        for (;;)
        {
          return getDataColumn(paramContext, paramUri, "_id=?", new String[] { localObject1[1] });
          if ("video".equals(localObject2)) {
            paramUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
          } else if ("audio".equals(localObject2)) {
            paramUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
          }
        }
        if ("content".equalsIgnoreCase(paramUri.getScheme())) {
          return getDataColumn(paramContext, paramUri, null, null);
        }
      } while (!"file".equalsIgnoreCase(paramUri.getScheme()));
      return paramUri.getPath();
      if ("content".equalsIgnoreCase(paramUri.getScheme())) {
        return getDataColumn(paramContext, paramUri, null, null);
      }
    } while (!"file".equalsIgnoreCase(paramUri.getScheme()));
    return paramUri.getPath();
  }
  
  private static boolean isDownloadsDocument(Uri paramUri)
  {
    return "com.android.providers.downloads.documents".equals(paramUri.getAuthority());
  }
  
  private static boolean isExternalStorageDocument(Uri paramUri)
  {
    return "com.android.externalstorage.documents".equals(paramUri.getAuthority());
  }
  
  private static boolean isMediaDocument(Uri paramUri)
  {
    return "com.android.providers.media.documents".equals(paramUri.getAuthority());
  }
  
  private void listenOnOrder()
  {
    if ((this.order != null) && (this.order.orderID != null) && (!this.order.orderID.isEmpty())) {
      this.orderStatusValueEventListener = FirebaseHelper.getInstance().listenOnOrderStatus(this.order.orderID, new GenericCallback()
      {
        public void onError(Object paramAnonymousObject, String paramAnonymousString) {}
        
        public void onSuccess(Object paramAnonymousObject, String paramAnonymousString)
        {
          if ((paramAnonymousObject != null) && (MainActivity.this.order != null)) {
            MainActivity.this.order.status = paramAnonymousObject.toString();
          }
          Log.d(MainActivity.TAG, "order status listner : " + paramAnonymousObject);
        }
      });
    }
  }
  
  private void removeOrderListener()
  {
    if ((this.order != null) && (this.order.orderID != null) && (!this.order.orderID.isEmpty()) && (this.orderStatusValueEventListener != null)) {
      FirebaseHelper.getInstance().removeOrderStatusListener(this.order.orderID, this.orderStatusValueEventListener);
    }
  }
  
  public void cancelNewTrip()
  {
    if ((getOrder() != null) && (getOrder().orderID != null) && (!getOrder().orderID.isEmpty()) && (this.order.status != null) && (!this.order.status.isEmpty()) && (this.order.status.equals(Constants.TripStatus.NEW.getValue())))
    {
      new CustomAlertDialog.Builder(this).setTitle(getResources().getString(2131099713)).setMessage(getResources().getString(2131099708)).setPositiveButton(getResources().getString(2131099866), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface = new ArrayList();
          paramAnonymousDialogInterface.add(Constants.TripStatus.NEW.getValue());
          FirebaseHelper.getInstance().cancelTrip(MainActivity.this.getOrder().orderID, null, paramAnonymousDialogInterface, Constants.TripStatus.NEW_CANCELED.getValue(), MainActivity.this.getLocation(), new GenericCallback()
          {
            public void onError(Object paramAnonymous2Object, String paramAnonymous2String) {}
            
            public void onSuccess(Object paramAnonymous2Object, String paramAnonymous2String) {}
          }, MainActivity.this);
          MainActivity.this.goToIntroFragment();
        }
      }).setNegativeButton(getResources().getString(2131099771), null).setIcon(17301543).show();
      return;
    }
    goToIntroFragment();
  }
  
  public void checkNavigationViewItem(int paramInt)
  {
    this.navigationView.setCheckedItem(paramInt);
  }
  
  public void doLaunchContactPicker()
  {
    startActivityForResult(new Intent("android.intent.action.PICK", ContactsContract.Contacts.CONTENT_URI), 0);
  }
  
  public void doLaunchGallery()
  {
    startActivityForResult(new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI), 1);
  }
  
  public void doLaunchPictureCapture()
  {
    Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
    if (localIntent.resolveActivity(getPackageManager()) != null) {
      startActivityForResult(localIntent, 2);
    }
  }
  
  public void doLaunchPlaceAutocomplete(LatLng paramLatLng, Fragment paramFragment)
  {
    paramLatLng = CustomPlaceSearchFragment.newInstance(paramLatLng);
    paramLatLng.setTargetFragment(paramFragment, 1);
    replaceFragment(paramLatLng, true);
  }
  
  public TextView getBack()
  {
    return this.back;
  }
  
  public TextView getDeclineTextView()
  {
    return this.decline;
  }
  
  public AlertDialog getDriverAcceptedDialog()
  {
    return this.driverAcceptedDialog;
  }
  
  public Location getLocation()
  {
    for (;;)
    {
      try
      {
        this.lm = ((LocationManager)getSystemService("location"));
        this.isGPSEnabled = this.lm.isProviderEnabled("gps");
        bool1 = this.lm.isProviderEnabled("network");
        boolean bool2 = this.isGPSEnabled;
        if ((bool2) || (bool1)) {
          continue;
        }
      }
      catch (Exception localException)
      {
        boolean bool1;
        localException.printStackTrace();
        continue;
      }
      return this.location;
      if (bool1)
      {
        Log.d("Network", "Network Enabled");
        if (this.lm != null) {
          this.location = this.lm.getLastKnownLocation("network");
        }
      }
      if ((this.isGPSEnabled) && (this.location == null))
      {
        Log.d("GPS", "GPS Enabled");
        if (this.lm != null) {
          this.location = this.lm.getLastKnownLocation("gps");
        }
      }
    }
  }
  
  public Order getOrder()
  {
    return this.order;
  }
  
  public String getPackageImageFilePath()
  {
    return this.imageFilePath;
  }
  
  public String getTypeID(String paramString)
  {
    if ((paramString == null) || (paramString.equals(getResources().getString(2131099800)))) {}
    do
    {
      return "1";
      if (paramString.equals(getResources().getString(2131099801))) {
        return "2";
      }
      if (paramString.equals(getResources().getString(2131099802))) {
        return "3";
      }
    } while (!paramString.equals(getResources().getString(2131099804)));
    return "4";
  }
  
  public String getTypeString(String paramString)
  {
    String str = getResources().getString(2131099800);
    if ((paramString == null) || (paramString.equals("1"))) {
      str = getResources().getString(2131099800);
    }
    do
    {
      return str;
      if (paramString.equals("2")) {
        return getResources().getString(2131099801);
      }
      if (paramString.equals("3")) {
        return getResources().getString(2131099802);
      }
    } while (!paramString.equals("4"));
    return getResources().getString(2131099804);
  }
  
  public String getTypeStringForOrderReview(String paramString)
  {
    String str = getResources().getString(2131099800);
    if ((paramString == null) || (paramString == "1")) {
      str = getResources().getString(2131099800);
    }
    do
    {
      return str;
      if (paramString == "2") {
        return getResources().getString(2131099801);
      }
      if (paramString == "3") {
        return getResources().getString(2131099803);
      }
    } while (paramString != "4");
    return getResources().getString(2131099805);
  }
  
  public void goToCallActivity(String paramString)
  {
    paramString = PhoneUtils.validatePhoneNo(paramString);
    if (!paramString.isEmpty()) {}
    try
    {
      paramString = "+" + paramString;
      paramString = "tel:" + paramString.trim();
      Intent localIntent = new Intent("android.intent.action.DIAL");
      localIntent.setData(Uri.parse(paramString));
      startActivityForResult(localIntent, 4);
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public void goToCompletedTripOrderReview(Bundle paramBundle)
  {
    CompletedTripOrderReviewFragment localCompletedTripOrderReviewFragment = new CompletedTripOrderReviewFragment();
    if (paramBundle != null) {
      localCompletedTripOrderReviewFragment.setArguments(paramBundle);
    }
    replaceFragment(localCompletedTripOrderReviewFragment, false);
  }
  
  public void goToHistoryFromIntro()
  {
    replaceFragment(new HistoryFragment(), NAVIGATION_TAG);
  }
  
  public void goToInProgressFromHistory(Bundle paramBundle)
  {
    InProgressFragment localInProgressFragment = new InProgressFragment();
    localInProgressFragment.setArguments(paramBundle);
    replaceFragment(localInProgressFragment, false);
  }
  
  public void goToOnTripOrderReview(Bundle paramBundle)
  {
    OnTripOrderReviewFragment localOnTripOrderReviewFragment = new OnTripOrderReviewFragment();
    if (paramBundle != null) {
      localOnTripOrderReviewFragment.setArguments(paramBundle);
    }
    replaceFragment(localOnTripOrderReviewFragment, true);
  }
  
  public void goToOngoingFromHistory(Bundle paramBundle)
  {
    EnRouteTripFragment localEnRouteTripFragment = new EnRouteTripFragment();
    localEnRouteTripFragment.setArguments(paramBundle);
    replaceFragment(localEnRouteTripFragment, false);
  }
  
  public void newOrder()
  {
    this.order = null;
    replaceFragment(new IntroFragment(), false);
    this.decline.setVisibility(8);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    Fragment localFragment = getSupportFragmentManager().findFragmentById(2131493024);
    Log.d("Tawseel", "resultCode " + paramInt2);
    Log.d("Tawseel", "requestCode " + paramInt1);
    if (paramInt2 == -1) {
      switch (paramInt1)
      {
      }
    }
    label510:
    do
    {
      do
      {
        for (;;)
        {
          return;
          Log.d("Tawseel", "data" + paramIntent.getDataString());
          paramIntent = paramIntent.getData().getLastPathSegment();
          paramIntent = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, "contact_id=?", new String[] { paramIntent }, null);
          paramInt1 = paramIntent.getColumnIndex("data1");
          paramInt2 = paramIntent.getColumnIndex("display_name");
          String str;
          if (paramIntent.moveToFirst())
          {
            localObject = paramIntent.getString(paramInt1);
            str = paramIntent.getString(paramInt2);
            if ((localFragment instanceof SenderDetailsFragment))
            {
              ((SenderDetailsFragment)localFragment).setPhoneNumber((String)localObject);
              ((SenderDetailsFragment)localFragment).setName(str);
              Log.v("Tawseel", "Got number: " + (String)localObject);
            }
          }
          for (;;)
          {
            paramIntent.close();
            return;
            if (!(localFragment instanceof RecipientDetailsFragment)) {
              break;
            }
            ((RecipientDetailsFragment)localFragment).setPhoneNumber((String)localObject);
            ((RecipientDetailsFragment)localFragment).setName(str);
            break;
            Log.w("Tawseel", "No results");
          }
          Object localObject = paramIntent.getData();
          paramIntent = new String[1];
          paramIntent[0] = "_data";
          localObject = getContentResolver().query((Uri)localObject, paramIntent, null, null, null);
          ((Cursor)localObject).moveToFirst();
          this.filePath = ((Cursor)localObject).getString(((Cursor)localObject).getColumnIndex(paramIntent[0]));
          ((Cursor)localObject).close();
          Log.d("Tawseel", "file path " + this.filePath);
          if ((localFragment instanceof SenderDetailsFragment)) {
            ((SenderDetailsFragment)localFragment).setImageFilePath(this.filePath);
          }
          ((Cursor)localObject).close();
          return;
          if ((paramIntent != null) && (paramIntent.getData() != null))
          {
            paramIntent = paramIntent.getData();
            Log.d("Tawseel", "data " + paramIntent);
          }
          for (this.filePath = getPath(this, paramIntent); (localFragment instanceof SenderDetailsFragment); this.filePath = getPath(this, paramIntent))
          {
            ((SenderDetailsFragment)localFragment).setImageFilePath(this.filePath);
            return;
            if ((paramIntent == null) || (paramIntent.getExtras() == null) || (paramIntent.getExtras().get("data") == null)) {
              break label510;
            }
            paramIntent = (Bitmap)paramIntent.getExtras().get("data");
            paramIntent = getImageUri(getApplicationContext(), paramIntent);
            Log.d("Tawseel", "data " + paramIntent);
          }
        }
      } while ((paramIntent == null) || (paramIntent.getExtras() == null));
      if ((localFragment instanceof RecipientLocationFragment))
      {
        ((RecipientLocationFragment)localFragment).setPlace(paramIntent.getDoubleExtra("lat", 0.0D), paramIntent.getDoubleExtra("lng", 0.0D));
        return;
      }
    } while (!(localFragment instanceof PickupLocationFragment));
    ((PickupLocationFragment)localFragment).setPlace(paramIntent.getDoubleExtra("lat", 0.0D), paramIntent.getDoubleExtra("lng", 0.0D));
  }
  
  public void onBackPressed()
  {
    Log.d("Tawseel", "onBackPressedMainActivity");
    if ((this.popupWindow != null) && (this.popupWindow.isShowing())) {}
    try
    {
      this.popupWindow.dismiss();
      if ((this.addressLayout != null) && (this.addressLayout.getVisibility() == 0))
      {
        this.addressLayout.setVisibility(8);
        this.mapTypeParentLayout.setVisibility(0);
        this.mapTypeButton.setVisibility(0);
        return;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
      Fragment localFragment = getSupportFragmentManager().findFragmentById(2131493024);
      if (((localFragment instanceof AboutUsFragment)) || ((localFragment instanceof ProfileFragment)) || ((localFragment instanceof ShareFragment)) || ((localFragment instanceof HistoryFragment)))
      {
        getSupportFragmentManager().popBackStack(NAVIGATION_TAG, 1);
        return;
      }
      if ((!(localFragment instanceof EnRouteTripFragment)) && (!(localFragment instanceof InProgressFragment)) && ((localFragment instanceof SenderDetailsFragment)))
      {
        cancelNewTrip();
        return;
      }
      if (getSupportFragmentManager().getBackStackEntryCount() < 1)
      {
        moveTaskToBack(true);
        return;
      }
      super.onBackPressed();
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903070);
    this.lm = ((LocationManager)getSystemService("location"));
    Object localObject = (Toolbar)findViewById(2131493018);
    setSupportActionBar((Toolbar)localObject);
    getSupportActionBar().setTitle(null);
    getSupportActionBar().setBackgroundDrawable(null);
    this.title = ((TextView)((Toolbar)localObject).findViewById(2131492950));
    this.decline = ((TextView)((Toolbar)localObject).findViewById(2131493019));
    this.back = ((TextView)((Toolbar)localObject).findViewById(2131493020));
    this.back.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MainActivity.this.onBackPressed();
      }
    });
    this.mAuth = FirebaseAuth.getInstance();
    this.mAuthListener = new FirebaseAuth.AuthStateListener()
    {
      public void onAuthStateChanged(@NonNull FirebaseAuth paramAnonymousFirebaseAuth)
      {
        paramAnonymousFirebaseAuth = paramAnonymousFirebaseAuth.getCurrentUser();
        if (paramAnonymousFirebaseAuth != null)
        {
          Log.e("Tawseel", "onAuthStateChanged:signed_in:" + paramAnonymousFirebaseAuth.getUid());
          return;
        }
        if ((CustomApplication.getCurrentUser() != null) && (!CustomApplication.getCurrentUser().accountStatus.equals(Constants.ClientPresenceStatus.BLOCKED.getValue()))) {
          MainActivity.this.authenticateToFirebase(new GenericCallback()
          {
            public void onError(Object paramAnonymous2Object, String paramAnonymous2String) {}
            
            public void onSuccess(Object paramAnonymous2Object, String paramAnonymous2String) {}
          });
        }
        Log.d("Tawseel", "onAuthStateChanged:signed_out");
      }
    };
    paramBundle = (DrawerLayout)findViewById(2131493006);
    localObject = new ActionBarDrawerToggle(this, paramBundle, (Toolbar)localObject, 2131099768, 2131099767);
    paramBundle.setDrawerListener((DrawerLayout.DrawerListener)localObject);
    ((ActionBarDrawerToggle)localObject).syncState();
    this.navigationView = ((NavigationView)findViewById(2131493007));
    this.navigationView.setNavigationItemSelectedListener(this);
    this.navigationView.setCheckedItem(this.navigationView.getMenu().getItem(0).getItemId());
    this.fcmTokenUpdatedReciever = new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        MainActivity.this.checkFCMTokenUpdated();
      }
    };
    LocalBroadcastManager.getInstance(this).registerReceiver(this.fcmTokenUpdatedReciever, new IntentFilter("FCM_TOKEN_UPDATED_FILTER"));
    InvitationListener.getInstance(this).listenForInvitation(new GenericCallback()
    {
      public void onError(Object paramAnonymousObject, String paramAnonymousString) {}
      
      public void onSuccess(Object paramAnonymousObject, final String paramAnonymousString)
      {
        paramAnonymousString = new ProgressDialog(MainActivity.this);
        paramAnonymousString.setMessage(MainActivity.this.getString(2131099752));
        paramAnonymousString.setCancelable(false);
        paramAnonymousString.show();
        APIConnector.getInstance(MainActivity.this).addReferredPromotion(paramAnonymousObject.toString(), new GenericCallback()
        {
          public void onError(Object paramAnonymous2Object, String paramAnonymous2String)
          {
            Log.e(MainActivity.TAG, "referred error: " + paramAnonymous2String);
            HelperMethods.dismissDialog(paramAnonymousString);
          }
          
          public void onSuccess(Object paramAnonymous2Object, String paramAnonymous2String)
          {
            HelperMethods.dismissDialog(paramAnonymousString);
            paramAnonymous2Object = String.format(MainActivity.this.getString(2131099747), new Object[] { Settings.referredByDiscountPercentage + "%" }) + " 🚙 ";
            Log.d(MainActivity.TAG, "referred text: " + (String)paramAnonymous2Object);
            new CustomAlertDialog.Builder(MainActivity.this).setMessage((CharSequence)paramAnonymous2Object).setPositiveButton(MainActivity.this.getString(2131099720), new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int)
              {
                try
                {
                  paramAnonymous3DialogInterface.dismiss();
                  return;
                }
                catch (Exception paramAnonymous3DialogInterface)
                {
                  paramAnonymous3DialogInterface.printStackTrace();
                }
              }
            }).show();
          }
        });
      }
    });
    replaceFragment(new IntroFragment(), false);
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    try
    {
      LocalBroadcastManager.getInstance(this).unregisterReceiver(this.fcmTokenUpdatedReciever);
      return;
    }
    catch (Exception localException)
    {
      Log.e("Tawseel", localException.getMessage());
    }
  }
  
  public boolean onNavigationItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    }
    for (;;)
    {
      hideKeyboardIfShown();
      ((DrawerLayout)findViewById(2131493006)).closeDrawer(8388611);
      return true;
      replaceFragment(new IntroFragment(), false);
      continue;
      replaceFragment(new HistoryFragment(), NAVIGATION_TAG);
      continue;
      replaceFragment(new ShareFragment(), NAVIGATION_TAG);
      continue;
      Security.getInstance(this).logout(new GenericCallback()
      {
        public void onError(Object paramAnonymousObject, String paramAnonymousString) {}
        
        public void onSuccess(Object paramAnonymousObject, String paramAnonymousString)
        {
          paramAnonymousObject = new Intent(MainActivity.this, LoginSMSActivity.class);
          MainActivity.this.startActivity((Intent)paramAnonymousObject);
          MainActivity.this.finish();
        }
      });
      continue;
      replaceFragment(new ProfileFragment(), NAVIGATION_TAG);
      continue;
      goToCallActivity(Settings.Contact_Us_Number);
      continue;
      replaceFragment(new AboutUsFragment(), NAVIGATION_TAG);
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    checkFCMTokenUpdated();
  }
  
  protected void onStart()
  {
    super.onStart();
    this.mAuth.addAuthStateListener(this.mAuthListener);
  }
  
  protected void onStop()
  {
    super.onStop();
    if (this.mAuthListener != null) {
      this.mAuth.removeAuthStateListener(this.mAuthListener);
    }
  }
  
  public void restartOrder(Order paramOrder)
  {
    this.order = paramOrder;
    replaceFragment(new SenderDetailsFragment(), false);
    this.decline.setVisibility(8);
  }
  
  public void setAddressLayout(LinearLayout paramLinearLayout)
  {
    this.addressLayout = paramLinearLayout;
  }
  
  public void setCustomTitle(int paramInt)
  {
    if ((this.title != null) && (getResources() != null)) {
      this.title.setText(getResources().getText(paramInt));
    }
  }
  
  public void setCustomTitle(String paramString)
  {
    if ((this.title != null) && (paramString != null)) {
      this.title.setText(paramString);
    }
  }
  
  public void setDriverAcceptedDialog(AlertDialog paramAlertDialog)
  {
    this.driverAcceptedDialog = paramAlertDialog;
  }
  
  public void setMapTypeButton(ImageView paramImageView)
  {
    this.mapTypeButton = paramImageView;
  }
  
  public void setMapTypeParentLayout(RelativeLayout paramRelativeLayout)
  {
    this.mapTypeParentLayout = paramRelativeLayout;
  }
  
  public void setOrder(Order paramOrder)
  {
    removeOrderListener();
    this.order = paramOrder;
    if ((paramOrder != null) && (paramOrder.orderID != null)) {
      Crashlytics.setString("ORDERID", paramOrder.orderID + "");
    }
    listenOnOrder();
  }
  
  public void setPackageImageFilePath(String paramString)
  {
    this.imageFilePath = paramString;
  }
  
  public void setPopupWindow(PopupWindow paramPopupWindow)
  {
    this.popupWindow = paramPopupWindow;
  }
  
  public void showGapsAlertDialogIfNotEnable()
  {
    if (!this.isGPSEnabled) {
      new CustomAlertDialog.Builder(this).setTitle(getString(2131099754)).setMessage(getString(2131099730)).setPositiveButton(getString(2131099838), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
          MainActivity.this.startActivity(paramAnonymousDialogInterface);
        }
      }).setNegativeButton(getString(2131099707), null).setCancelable(true).show();
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\activities\MainActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */