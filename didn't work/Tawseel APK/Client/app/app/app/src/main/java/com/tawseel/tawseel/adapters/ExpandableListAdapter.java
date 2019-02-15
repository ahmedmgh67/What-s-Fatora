package com.tawseel.tawseel.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tawseel.tawseel.GenericCallback;
import com.tawseel.tawseel.activities.MainActivity;
import com.tawseel.tawseel.helpers.FirebaseHelper;
import com.tawseel.tawseel.models.Row;
import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter
  extends BaseExpandableListAdapter
{
  private Context context;
  LayoutInflater infalInflater;
  private HashMap<String, List<Row>> listDataChild;
  private List<String> listDataHeader;
  
  public ExpandableListAdapter(Context paramContext, List<String> paramList, HashMap<String, List<Row>> paramHashMap)
  {
    this.context = paramContext;
    this.listDataHeader = paramList;
    this.listDataChild = paramHashMap;
  }
  
  public Object getChild(int paramInt1, int paramInt2)
  {
    return ((List)this.listDataChild.get(this.listDataHeader.get(paramInt1))).get(paramInt2);
  }
  
  public long getChildId(int paramInt1, int paramInt2)
  {
    return paramInt2;
  }
  
  public View getChildView(int paramInt1, int paramInt2, boolean paramBoolean, final View paramView, ViewGroup paramViewGroup)
  {
    final Row localRow = (Row)getChild(paramInt1, paramInt2);
    paramViewGroup = paramView;
    if (paramView == null)
    {
      this.infalInflater = ((LayoutInflater)this.context.getSystemService("layout_inflater"));
      paramViewGroup = this.infalInflater.inflate(2130903112, null);
    }
    paramView = (ImageView)paramViewGroup.findViewById(2131492946);
    paramView.setVisibility(8);
    TextView localTextView1 = (TextView)paramViewGroup.findViewById(2131493159);
    TextView localTextView2 = (TextView)paramViewGroup.findViewById(2131493162);
    RelativeLayout localRelativeLayout = (RelativeLayout)paramViewGroup.findViewById(2131493160);
    TextView localTextView3 = (TextView)paramViewGroup.findViewById(2131493101);
    localRelativeLayout.setVisibility(0);
    localTextView3.setVisibility(8);
    LinearLayout localLinearLayout = (LinearLayout)paramViewGroup.findViewById(2131493161);
    ImageView localImageView = (ImageView)paramViewGroup.findViewById(2131493080);
    localLinearLayout.setVisibility(8);
    View localView = paramViewGroup.findViewById(2131493163);
    localView.setVisibility(0);
    if (paramInt2 == getChildrenCount(paramInt1) - 1) {
      localView.setVisibility(8);
    }
    if ((paramInt2 == 5) && (paramInt1 == 1) && (getChildrenCount(1) == 7))
    {
      paramView.setVisibility(0);
      localRelativeLayout.setVisibility(8);
      if ((localRow.value != null) && (!localRow.value.isEmpty())) {
        FirebaseHelper.getInstance().getPackageImageFromStorage(localRow.value, new GenericCallback()
        {
          public void onError(Object paramAnonymousObject, String paramAnonymousString) {}
          
          public void onSuccess(final Object paramAnonymousObject, String paramAnonymousString)
          {
            paramAnonymousObject = (byte[])paramAnonymousObject;
            paramAnonymousObject = BitmapFactory.decodeByteArray((byte[])paramAnonymousObject, 0, paramAnonymousObject.length);
            paramView.setImageBitmap((Bitmap)paramAnonymousObject);
            paramView.setOnClickListener(new View.OnClickListener()
            {
              public void onClick(View paramAnonymous2View)
              {
                paramAnonymous2View = ExpandableListAdapter.this.infalInflater.inflate(2130903113, null);
                final PopupWindow localPopupWindow = new PopupWindow(paramAnonymous2View, -1, -1);
                ((MainActivity)ExpandableListAdapter.this.context).setPopupWindow(localPopupWindow);
                ImageView localImageView = (ImageView)paramAnonymous2View.findViewById(2131493165);
                localImageView.setImageBitmap(paramAnonymousObject);
                ((RelativeLayout)paramAnonymous2View.findViewById(2131493164)).setOnClickListener(new View.OnClickListener()
                {
                  public void onClick(View paramAnonymous3View)
                  {
                    if (localPopupWindow.isShowing()) {}
                    try
                    {
                      localPopupWindow.dismiss();
                      return;
                    }
                    catch (Exception paramAnonymous3View)
                    {
                      paramAnonymous3View.printStackTrace();
                    }
                  }
                });
                localPopupWindow.setAnimationStyle(16973824);
                localPopupWindow.showAtLocation(localImageView, 17, 0, 0);
              }
            });
          }
        });
      }
    }
    for (;;)
    {
      localTextView1.setText(localRow.title);
      localTextView2.setText(localRow.value);
      return paramViewGroup;
      if ((paramInt2 == 2) && ((paramInt1 == 1) || (paramInt1 == 2)))
      {
        localLinearLayout.setVisibility(0);
        if ((localRow.value != null) && (!localRow.value.isEmpty())) {
          localImageView.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              ((MainActivity)ExpandableListAdapter.this.context).goToCallActivity(localRow.value);
            }
          });
        }
      }
      else if ((paramInt2 == 2) && (paramInt1 == 0) && (localRow.newValue != null))
      {
        localTextView2.setPaintFlags(localTextView2.getPaintFlags() | 0x10);
        localTextView2.setTextColor(-65536);
        localTextView3.setVisibility(0);
        localTextView3.setText(localRow.newValue);
      }
    }
  }
  
  public int getChildrenCount(int paramInt)
  {
    return ((List)this.listDataChild.get(this.listDataHeader.get(paramInt))).size();
  }
  
  public Object getGroup(int paramInt)
  {
    return this.listDataHeader.get(paramInt);
  }
  
  public int getGroupCount()
  {
    return this.listDataHeader.size();
  }
  
  public long getGroupId(int paramInt)
  {
    return paramInt;
  }
  
  public View getGroupView(int paramInt, boolean paramBoolean, View paramView, ViewGroup paramViewGroup)
  {
    String str = (String)getGroup(paramInt);
    paramViewGroup = paramView;
    if (paramView == null) {
      paramViewGroup = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130903111, null);
    }
    ((TextView)paramViewGroup.findViewById(2131493158)).setText(str);
    return paramViewGroup;
  }
  
  public boolean hasStableIds()
  {
    return false;
  }
  
  public boolean isChildSelectable(int paramInt1, int paramInt2)
  {
    return false;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\adapters\ExpandableListAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */