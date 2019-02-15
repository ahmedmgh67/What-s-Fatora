package com.tawseel.tawseel;

import android.content.Context;
import android.content.res.Resources;

public class LabelsRepository
{
  private String detailsPlaceHolder = "";
  private String detailsValidationText = "";
  private String determineSenderLocation = "";
  private String nameValidationText = "";
  private String packageDetailsLabel = "";
  private String packageImageLabel = "";
  private String senderLocationTitle = "";
  private String senderNameLabel = "";
  private String senderNamePlaceHolder = "";
  private String senderType = "";
  
  public LabelsRepository(Context paramContext, String paramString)
  {
    this.senderType = paramString;
    if ((paramContext != null) && (paramString != null))
    {
      if (!paramString.equals(paramContext.getResources().getString(2131099800))) {
        break label286;
      }
      this.senderNameLabel = paramContext.getResources().getString(2131099836);
      this.senderNamePlaceHolder = (paramContext.getResources().getString(2131099731) + " " + this.senderNameLabel);
      this.packageDetailsLabel = paramContext.getResources().getString(2131099787);
      this.detailsPlaceHolder = paramContext.getResources().getString(2131099834);
      this.packageImageLabel = paramContext.getResources().getString(2131099788);
      this.nameValidationText = (paramContext.getResources().getString(2131099862) + " " + this.senderNameLabel);
      this.detailsValidationText = (paramContext.getResources().getString(2131099862) + " " + this.packageDetailsLabel);
      this.determineSenderLocation = paramContext.getResources().getString(2131099835);
      this.senderLocationTitle = paramContext.getResources().getString(2131099823);
    }
    label286:
    do
    {
      return;
      if (paramString.equals(paramContext.getResources().getString(2131099801)))
      {
        this.senderNameLabel = paramContext.getResources().getString(2131099836);
        this.senderNamePlaceHolder = (paramContext.getResources().getString(2131099731) + " " + this.senderNameLabel);
        this.packageDetailsLabel = paramContext.getResources().getString(2131099787);
        this.detailsPlaceHolder = paramContext.getResources().getString(2131099701);
        this.packageImageLabel = paramContext.getResources().getString(2131099788);
        this.nameValidationText = (paramContext.getResources().getString(2131099862) + " " + this.senderNameLabel);
        this.detailsValidationText = (paramContext.getResources().getString(2131099862) + " " + this.packageDetailsLabel);
        this.determineSenderLocation = paramContext.getResources().getString(2131099835);
        this.senderLocationTitle = paramContext.getResources().getString(2131099823);
        return;
      }
      if (paramString.equals(paramContext.getResources().getString(2131099802)))
      {
        this.senderNameLabel = paramContext.getResources().getString(2131099815);
        this.senderNamePlaceHolder = (paramContext.getResources().getString(2131099731) + " " + this.senderNameLabel);
        this.packageDetailsLabel = paramContext.getResources().getString(2131099783);
        this.detailsPlaceHolder = paramContext.getResources().getString(2131099813);
        this.packageImageLabel = paramContext.getResources().getString(2131099784);
        this.nameValidationText = (paramContext.getResources().getString(2131099862) + " " + this.senderNameLabel);
        this.detailsValidationText = (paramContext.getResources().getString(2131099862) + " " + this.packageDetailsLabel);
        this.determineSenderLocation = paramContext.getResources().getString(2131099716);
        this.senderLocationTitle = paramContext.getResources().getString(2131099814);
        return;
      }
    } while (!paramString.equals(paramContext.getResources().getString(2131099804)));
    this.senderNameLabel = paramContext.getResources().getString(2131099844);
    this.senderNamePlaceHolder = (paramContext.getResources().getString(2131099731) + " " + this.senderNameLabel);
    this.packageDetailsLabel = paramContext.getResources().getString(2131099783);
    this.detailsPlaceHolder = paramContext.getResources().getString(2131099842);
    this.packageImageLabel = paramContext.getResources().getString(2131099784);
    this.nameValidationText = (paramContext.getResources().getString(2131099862) + " " + this.senderNameLabel);
    this.detailsValidationText = (paramContext.getResources().getString(2131099862) + " " + this.packageDetailsLabel);
    this.determineSenderLocation = paramContext.getResources().getString(2131099717);
    this.senderLocationTitle = paramContext.getResources().getString(2131099843);
  }
  
  public String getDetailsPlaceHolder()
  {
    return this.detailsPlaceHolder;
  }
  
  public String getDetailsValidationText()
  {
    return this.detailsValidationText;
  }
  
  public String getDetermineSenderLocation()
  {
    return this.determineSenderLocation;
  }
  
  public String getNameValidationText()
  {
    return this.nameValidationText;
  }
  
  public String getPackageDetailsLabel()
  {
    return this.packageDetailsLabel;
  }
  
  public String getPackageImageLabel()
  {
    return this.packageImageLabel;
  }
  
  public String getSenderLocationTitle()
  {
    return this.senderLocationTitle;
  }
  
  public String getSenderNameLabel()
  {
    return this.senderNameLabel;
  }
  
  public String getSenderNamePlaceHolder()
  {
    return this.senderNamePlaceHolder;
  }
  
  public String getSenderType()
  {
    return this.senderType;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\tawseel\tawseel\LabelsRepository.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */