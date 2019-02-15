package com.google.i18n.phonenumbers.nano;

import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.InternalNano;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.MessageNano;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public abstract interface Phonemetadata
{
  public static final class NumberFormat
    extends MessageNano
  {
    private static volatile NumberFormat[] _emptyArray;
    public String domesticCarrierCodeFormattingRule;
    public String format;
    public String[] leadingDigitsPattern;
    public String nationalPrefixFormattingRule;
    public boolean nationalPrefixOptionalWhenFormatting;
    public String pattern;
    
    public NumberFormat()
    {
      clear();
    }
    
    public static NumberFormat[] emptyArray()
    {
      if (_emptyArray == null) {}
      synchronized (InternalNano.LAZY_INIT_LOCK)
      {
        if (_emptyArray == null) {
          _emptyArray = new NumberFormat[0];
        }
        return _emptyArray;
      }
    }
    
    public static NumberFormat parseFrom(CodedInputByteBufferNano paramCodedInputByteBufferNano)
      throws IOException
    {
      return new NumberFormat().mergeFrom(paramCodedInputByteBufferNano);
    }
    
    public static NumberFormat parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferNanoException
    {
      return (NumberFormat)MessageNano.mergeFrom(new NumberFormat(), paramArrayOfByte);
    }
    
    public NumberFormat clear()
    {
      this.pattern = "";
      this.format = "";
      this.leadingDigitsPattern = WireFormatNano.EMPTY_STRING_ARRAY;
      this.nationalPrefixFormattingRule = "";
      this.nationalPrefixOptionalWhenFormatting = false;
      this.domesticCarrierCodeFormattingRule = "";
      this.cachedSize = -1;
      return this;
    }
    
    protected int computeSerializedSize()
    {
      int i1 = super.computeSerializedSize() + CodedOutputByteBufferNano.computeStringSize(1, this.pattern) + CodedOutputByteBufferNano.computeStringSize(2, this.format);
      int i = i1;
      if (this.leadingDigitsPattern != null)
      {
        i = i1;
        if (this.leadingDigitsPattern.length > 0)
        {
          int m = 0;
          j = 0;
          i = 0;
          while (i < this.leadingDigitsPattern.length)
          {
            String str = this.leadingDigitsPattern[i];
            int n = m;
            int k = j;
            if (str != null)
            {
              n = m + 1;
              k = j + CodedOutputByteBufferNano.computeStringSizeNoTag(str);
            }
            i += 1;
            m = n;
            j = k;
          }
          i = i1 + j + m * 1;
        }
      }
      int j = i;
      if (!this.nationalPrefixFormattingRule.equals("")) {
        j = i + CodedOutputByteBufferNano.computeStringSize(4, this.nationalPrefixFormattingRule);
      }
      i = j;
      if (!this.domesticCarrierCodeFormattingRule.equals("")) {
        i = j + CodedOutputByteBufferNano.computeStringSize(5, this.domesticCarrierCodeFormattingRule);
      }
      j = i;
      if (this.nationalPrefixOptionalWhenFormatting) {
        j = i + CodedOutputByteBufferNano.computeBoolSize(6, this.nationalPrefixOptionalWhenFormatting);
      }
      return j;
    }
    
    public NumberFormat mergeFrom(CodedInputByteBufferNano paramCodedInputByteBufferNano)
      throws IOException
    {
      for (;;)
      {
        int i = paramCodedInputByteBufferNano.readTag();
        switch (i)
        {
        default: 
          if (WireFormatNano.parseUnknownField(paramCodedInputByteBufferNano, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          this.pattern = paramCodedInputByteBufferNano.readString();
          break;
        case 18: 
          this.format = paramCodedInputByteBufferNano.readString();
          break;
        case 26: 
          int j = WireFormatNano.getRepeatedFieldArrayLength(paramCodedInputByteBufferNano, 26);
          if (this.leadingDigitsPattern == null) {}
          String[] arrayOfString;
          for (i = 0;; i = this.leadingDigitsPattern.length)
          {
            arrayOfString = new String[i + j];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.leadingDigitsPattern, 0, arrayOfString, 0, i);
              j = i;
            }
            while (j < arrayOfString.length - 1)
            {
              arrayOfString[j] = paramCodedInputByteBufferNano.readString();
              paramCodedInputByteBufferNano.readTag();
              j += 1;
            }
          }
          arrayOfString[j] = paramCodedInputByteBufferNano.readString();
          this.leadingDigitsPattern = arrayOfString;
          break;
        case 34: 
          this.nationalPrefixFormattingRule = paramCodedInputByteBufferNano.readString();
          break;
        case 42: 
          this.domesticCarrierCodeFormattingRule = paramCodedInputByteBufferNano.readString();
          break;
        case 48: 
          this.nationalPrefixOptionalWhenFormatting = paramCodedInputByteBufferNano.readBool();
        }
      }
    }
    
    public void writeTo(CodedOutputByteBufferNano paramCodedOutputByteBufferNano)
      throws IOException
    {
      paramCodedOutputByteBufferNano.writeString(1, this.pattern);
      paramCodedOutputByteBufferNano.writeString(2, this.format);
      if ((this.leadingDigitsPattern != null) && (this.leadingDigitsPattern.length > 0))
      {
        int i = 0;
        while (i < this.leadingDigitsPattern.length)
        {
          String str = this.leadingDigitsPattern[i];
          if (str != null) {
            paramCodedOutputByteBufferNano.writeString(3, str);
          }
          i += 1;
        }
      }
      if (!this.nationalPrefixFormattingRule.equals("")) {
        paramCodedOutputByteBufferNano.writeString(4, this.nationalPrefixFormattingRule);
      }
      if (!this.domesticCarrierCodeFormattingRule.equals("")) {
        paramCodedOutputByteBufferNano.writeString(5, this.domesticCarrierCodeFormattingRule);
      }
      if (this.nationalPrefixOptionalWhenFormatting) {
        paramCodedOutputByteBufferNano.writeBool(6, this.nationalPrefixOptionalWhenFormatting);
      }
      super.writeTo(paramCodedOutputByteBufferNano);
    }
  }
  
  public static final class PhoneMetadata
    extends MessageNano
  {
    private static volatile PhoneMetadata[] _emptyArray;
    public Phonemetadata.PhoneNumberDesc carrierSpecific;
    public int countryCode;
    public Phonemetadata.PhoneNumberDesc emergency;
    public Phonemetadata.PhoneNumberDesc fixedLine;
    public Phonemetadata.PhoneNumberDesc generalDesc;
    public String id;
    public String internationalPrefix;
    public Phonemetadata.NumberFormat[] intlNumberFormat;
    public String leadingDigits;
    public boolean leadingZeroPossible;
    public boolean mainCountryForCode;
    public Phonemetadata.PhoneNumberDesc mobile;
    public boolean mobileNumberPortableRegion;
    public String nationalPrefix;
    public String nationalPrefixForParsing;
    public String nationalPrefixTransformRule;
    public Phonemetadata.PhoneNumberDesc noInternationalDialling;
    public Phonemetadata.NumberFormat[] numberFormat;
    public Phonemetadata.PhoneNumberDesc pager;
    public Phonemetadata.PhoneNumberDesc personalNumber;
    public String preferredExtnPrefix;
    public String preferredInternationalPrefix;
    public Phonemetadata.PhoneNumberDesc premiumRate;
    public boolean sameMobileAndFixedLinePattern;
    public Phonemetadata.PhoneNumberDesc sharedCost;
    public Phonemetadata.PhoneNumberDesc shortCode;
    public Phonemetadata.PhoneNumberDesc standardRate;
    public Phonemetadata.PhoneNumberDesc tollFree;
    public Phonemetadata.PhoneNumberDesc uan;
    public Phonemetadata.PhoneNumberDesc voicemail;
    public Phonemetadata.PhoneNumberDesc voip;
    
    public PhoneMetadata()
    {
      clear();
    }
    
    public static PhoneMetadata[] emptyArray()
    {
      if (_emptyArray == null) {}
      synchronized (InternalNano.LAZY_INIT_LOCK)
      {
        if (_emptyArray == null) {
          _emptyArray = new PhoneMetadata[0];
        }
        return _emptyArray;
      }
    }
    
    public static PhoneMetadata parseFrom(CodedInputByteBufferNano paramCodedInputByteBufferNano)
      throws IOException
    {
      return new PhoneMetadata().mergeFrom(paramCodedInputByteBufferNano);
    }
    
    public static PhoneMetadata parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferNanoException
    {
      return (PhoneMetadata)MessageNano.mergeFrom(new PhoneMetadata(), paramArrayOfByte);
    }
    
    public PhoneMetadata clear()
    {
      this.generalDesc = null;
      this.fixedLine = null;
      this.mobile = null;
      this.tollFree = null;
      this.premiumRate = null;
      this.sharedCost = null;
      this.personalNumber = null;
      this.voip = null;
      this.pager = null;
      this.uan = null;
      this.emergency = null;
      this.voicemail = null;
      this.shortCode = null;
      this.standardRate = null;
      this.carrierSpecific = null;
      this.noInternationalDialling = null;
      this.id = "";
      this.countryCode = 0;
      this.internationalPrefix = "";
      this.preferredInternationalPrefix = "";
      this.nationalPrefix = "";
      this.preferredExtnPrefix = "";
      this.nationalPrefixForParsing = "";
      this.nationalPrefixTransformRule = "";
      this.sameMobileAndFixedLinePattern = false;
      this.numberFormat = Phonemetadata.NumberFormat.emptyArray();
      this.intlNumberFormat = Phonemetadata.NumberFormat.emptyArray();
      this.mainCountryForCode = false;
      this.leadingDigits = "";
      this.leadingZeroPossible = false;
      this.mobileNumberPortableRegion = false;
      this.cachedSize = -1;
      return this;
    }
    
    protected int computeSerializedSize()
    {
      int j = super.computeSerializedSize();
      int i = j;
      if (this.generalDesc != null) {
        i = j + CodedOutputByteBufferNano.computeMessageSize(1, this.generalDesc);
      }
      j = i;
      if (this.fixedLine != null) {
        j = i + CodedOutputByteBufferNano.computeMessageSize(2, this.fixedLine);
      }
      i = j;
      if (this.mobile != null) {
        i = j + CodedOutputByteBufferNano.computeMessageSize(3, this.mobile);
      }
      j = i;
      if (this.tollFree != null) {
        j = i + CodedOutputByteBufferNano.computeMessageSize(4, this.tollFree);
      }
      i = j;
      if (this.premiumRate != null) {
        i = j + CodedOutputByteBufferNano.computeMessageSize(5, this.premiumRate);
      }
      j = i;
      if (this.sharedCost != null) {
        j = i + CodedOutputByteBufferNano.computeMessageSize(6, this.sharedCost);
      }
      i = j;
      if (this.personalNumber != null) {
        i = j + CodedOutputByteBufferNano.computeMessageSize(7, this.personalNumber);
      }
      j = i;
      if (this.voip != null) {
        j = i + CodedOutputByteBufferNano.computeMessageSize(8, this.voip);
      }
      j += CodedOutputByteBufferNano.computeStringSize(9, this.id);
      i = j;
      if (this.countryCode != 0) {
        i = j + CodedOutputByteBufferNano.computeInt32Size(10, this.countryCode);
      }
      j = i;
      if (!this.internationalPrefix.equals("")) {
        j = i + CodedOutputByteBufferNano.computeStringSize(11, this.internationalPrefix);
      }
      i = j;
      if (!this.nationalPrefix.equals("")) {
        i = j + CodedOutputByteBufferNano.computeStringSize(12, this.nationalPrefix);
      }
      j = i;
      if (!this.preferredExtnPrefix.equals("")) {
        j = i + CodedOutputByteBufferNano.computeStringSize(13, this.preferredExtnPrefix);
      }
      i = j;
      if (!this.nationalPrefixForParsing.equals("")) {
        i = j + CodedOutputByteBufferNano.computeStringSize(15, this.nationalPrefixForParsing);
      }
      j = i;
      if (!this.nationalPrefixTransformRule.equals("")) {
        j = i + CodedOutputByteBufferNano.computeStringSize(16, this.nationalPrefixTransformRule);
      }
      i = j;
      if (!this.preferredInternationalPrefix.equals("")) {
        i = j + CodedOutputByteBufferNano.computeStringSize(17, this.preferredInternationalPrefix);
      }
      j = i;
      if (this.sameMobileAndFixedLinePattern) {
        j = i + CodedOutputByteBufferNano.computeBoolSize(18, this.sameMobileAndFixedLinePattern);
      }
      i = j;
      int k;
      Phonemetadata.NumberFormat localNumberFormat;
      if (this.numberFormat != null)
      {
        i = j;
        if (this.numberFormat.length > 0)
        {
          k = 0;
          for (;;)
          {
            i = j;
            if (k >= this.numberFormat.length) {
              break;
            }
            localNumberFormat = this.numberFormat[k];
            i = j;
            if (localNumberFormat != null) {
              i = j + CodedOutputByteBufferNano.computeMessageSize(19, localNumberFormat);
            }
            k += 1;
            j = i;
          }
        }
      }
      j = i;
      if (this.intlNumberFormat != null)
      {
        j = i;
        if (this.intlNumberFormat.length > 0)
        {
          k = 0;
          for (;;)
          {
            j = i;
            if (k >= this.intlNumberFormat.length) {
              break;
            }
            localNumberFormat = this.intlNumberFormat[k];
            j = i;
            if (localNumberFormat != null) {
              j = i + CodedOutputByteBufferNano.computeMessageSize(20, localNumberFormat);
            }
            k += 1;
            i = j;
          }
        }
      }
      i = j;
      if (this.pager != null) {
        i = j + CodedOutputByteBufferNano.computeMessageSize(21, this.pager);
      }
      j = i;
      if (this.mainCountryForCode) {
        j = i + CodedOutputByteBufferNano.computeBoolSize(22, this.mainCountryForCode);
      }
      i = j;
      if (!this.leadingDigits.equals("")) {
        i = j + CodedOutputByteBufferNano.computeStringSize(23, this.leadingDigits);
      }
      j = i;
      if (this.noInternationalDialling != null) {
        j = i + CodedOutputByteBufferNano.computeMessageSize(24, this.noInternationalDialling);
      }
      i = j;
      if (this.uan != null) {
        i = j + CodedOutputByteBufferNano.computeMessageSize(25, this.uan);
      }
      j = i;
      if (this.leadingZeroPossible) {
        j = i + CodedOutputByteBufferNano.computeBoolSize(26, this.leadingZeroPossible);
      }
      i = j;
      if (this.emergency != null) {
        i = j + CodedOutputByteBufferNano.computeMessageSize(27, this.emergency);
      }
      j = i;
      if (this.voicemail != null) {
        j = i + CodedOutputByteBufferNano.computeMessageSize(28, this.voicemail);
      }
      i = j;
      if (this.shortCode != null) {
        i = j + CodedOutputByteBufferNano.computeMessageSize(29, this.shortCode);
      }
      j = i;
      if (this.standardRate != null) {
        j = i + CodedOutputByteBufferNano.computeMessageSize(30, this.standardRate);
      }
      i = j;
      if (this.carrierSpecific != null) {
        i = j + CodedOutputByteBufferNano.computeMessageSize(31, this.carrierSpecific);
      }
      j = i;
      if (this.mobileNumberPortableRegion) {
        j = i + CodedOutputByteBufferNano.computeBoolSize(32, this.mobileNumberPortableRegion);
      }
      return j;
    }
    
    public PhoneMetadata mergeFrom(CodedInputByteBufferNano paramCodedInputByteBufferNano)
      throws IOException
    {
      for (;;)
      {
        int i = paramCodedInputByteBufferNano.readTag();
        int j;
        Phonemetadata.NumberFormat[] arrayOfNumberFormat;
        switch (i)
        {
        default: 
          if (WireFormatNano.parseUnknownField(paramCodedInputByteBufferNano, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          if (this.generalDesc == null) {
            this.generalDesc = new Phonemetadata.PhoneNumberDesc();
          }
          paramCodedInputByteBufferNano.readMessage(this.generalDesc);
          break;
        case 18: 
          if (this.fixedLine == null) {
            this.fixedLine = new Phonemetadata.PhoneNumberDesc();
          }
          paramCodedInputByteBufferNano.readMessage(this.fixedLine);
          break;
        case 26: 
          if (this.mobile == null) {
            this.mobile = new Phonemetadata.PhoneNumberDesc();
          }
          paramCodedInputByteBufferNano.readMessage(this.mobile);
          break;
        case 34: 
          if (this.tollFree == null) {
            this.tollFree = new Phonemetadata.PhoneNumberDesc();
          }
          paramCodedInputByteBufferNano.readMessage(this.tollFree);
          break;
        case 42: 
          if (this.premiumRate == null) {
            this.premiumRate = new Phonemetadata.PhoneNumberDesc();
          }
          paramCodedInputByteBufferNano.readMessage(this.premiumRate);
          break;
        case 50: 
          if (this.sharedCost == null) {
            this.sharedCost = new Phonemetadata.PhoneNumberDesc();
          }
          paramCodedInputByteBufferNano.readMessage(this.sharedCost);
          break;
        case 58: 
          if (this.personalNumber == null) {
            this.personalNumber = new Phonemetadata.PhoneNumberDesc();
          }
          paramCodedInputByteBufferNano.readMessage(this.personalNumber);
          break;
        case 66: 
          if (this.voip == null) {
            this.voip = new Phonemetadata.PhoneNumberDesc();
          }
          paramCodedInputByteBufferNano.readMessage(this.voip);
          break;
        case 74: 
          this.id = paramCodedInputByteBufferNano.readString();
          break;
        case 80: 
          this.countryCode = paramCodedInputByteBufferNano.readInt32();
          break;
        case 90: 
          this.internationalPrefix = paramCodedInputByteBufferNano.readString();
          break;
        case 98: 
          this.nationalPrefix = paramCodedInputByteBufferNano.readString();
          break;
        case 106: 
          this.preferredExtnPrefix = paramCodedInputByteBufferNano.readString();
          break;
        case 122: 
          this.nationalPrefixForParsing = paramCodedInputByteBufferNano.readString();
          break;
        case 130: 
          this.nationalPrefixTransformRule = paramCodedInputByteBufferNano.readString();
          break;
        case 138: 
          this.preferredInternationalPrefix = paramCodedInputByteBufferNano.readString();
          break;
        case 144: 
          this.sameMobileAndFixedLinePattern = paramCodedInputByteBufferNano.readBool();
          break;
        case 154: 
          j = WireFormatNano.getRepeatedFieldArrayLength(paramCodedInputByteBufferNano, 154);
          if (this.numberFormat == null) {}
          for (i = 0;; i = this.numberFormat.length)
          {
            arrayOfNumberFormat = new Phonemetadata.NumberFormat[i + j];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.numberFormat, 0, arrayOfNumberFormat, 0, i);
              j = i;
            }
            while (j < arrayOfNumberFormat.length - 1)
            {
              arrayOfNumberFormat[j] = new Phonemetadata.NumberFormat();
              paramCodedInputByteBufferNano.readMessage(arrayOfNumberFormat[j]);
              paramCodedInputByteBufferNano.readTag();
              j += 1;
            }
          }
          arrayOfNumberFormat[j] = new Phonemetadata.NumberFormat();
          paramCodedInputByteBufferNano.readMessage(arrayOfNumberFormat[j]);
          this.numberFormat = arrayOfNumberFormat;
          break;
        case 162: 
          j = WireFormatNano.getRepeatedFieldArrayLength(paramCodedInputByteBufferNano, 162);
          if (this.intlNumberFormat == null) {}
          for (i = 0;; i = this.intlNumberFormat.length)
          {
            arrayOfNumberFormat = new Phonemetadata.NumberFormat[i + j];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.intlNumberFormat, 0, arrayOfNumberFormat, 0, i);
              j = i;
            }
            while (j < arrayOfNumberFormat.length - 1)
            {
              arrayOfNumberFormat[j] = new Phonemetadata.NumberFormat();
              paramCodedInputByteBufferNano.readMessage(arrayOfNumberFormat[j]);
              paramCodedInputByteBufferNano.readTag();
              j += 1;
            }
          }
          arrayOfNumberFormat[j] = new Phonemetadata.NumberFormat();
          paramCodedInputByteBufferNano.readMessage(arrayOfNumberFormat[j]);
          this.intlNumberFormat = arrayOfNumberFormat;
          break;
        case 170: 
          if (this.pager == null) {
            this.pager = new Phonemetadata.PhoneNumberDesc();
          }
          paramCodedInputByteBufferNano.readMessage(this.pager);
          break;
        case 176: 
          this.mainCountryForCode = paramCodedInputByteBufferNano.readBool();
          break;
        case 186: 
          this.leadingDigits = paramCodedInputByteBufferNano.readString();
          break;
        case 194: 
          if (this.noInternationalDialling == null) {
            this.noInternationalDialling = new Phonemetadata.PhoneNumberDesc();
          }
          paramCodedInputByteBufferNano.readMessage(this.noInternationalDialling);
          break;
        case 202: 
          if (this.uan == null) {
            this.uan = new Phonemetadata.PhoneNumberDesc();
          }
          paramCodedInputByteBufferNano.readMessage(this.uan);
          break;
        case 208: 
          this.leadingZeroPossible = paramCodedInputByteBufferNano.readBool();
          break;
        case 218: 
          if (this.emergency == null) {
            this.emergency = new Phonemetadata.PhoneNumberDesc();
          }
          paramCodedInputByteBufferNano.readMessage(this.emergency);
          break;
        case 226: 
          if (this.voicemail == null) {
            this.voicemail = new Phonemetadata.PhoneNumberDesc();
          }
          paramCodedInputByteBufferNano.readMessage(this.voicemail);
          break;
        case 234: 
          if (this.shortCode == null) {
            this.shortCode = new Phonemetadata.PhoneNumberDesc();
          }
          paramCodedInputByteBufferNano.readMessage(this.shortCode);
          break;
        case 242: 
          if (this.standardRate == null) {
            this.standardRate = new Phonemetadata.PhoneNumberDesc();
          }
          paramCodedInputByteBufferNano.readMessage(this.standardRate);
          break;
        case 250: 
          if (this.carrierSpecific == null) {
            this.carrierSpecific = new Phonemetadata.PhoneNumberDesc();
          }
          paramCodedInputByteBufferNano.readMessage(this.carrierSpecific);
          break;
        case 256: 
          this.mobileNumberPortableRegion = paramCodedInputByteBufferNano.readBool();
        }
      }
    }
    
    public void writeTo(CodedOutputByteBufferNano paramCodedOutputByteBufferNano)
      throws IOException
    {
      if (this.generalDesc != null) {
        paramCodedOutputByteBufferNano.writeMessage(1, this.generalDesc);
      }
      if (this.fixedLine != null) {
        paramCodedOutputByteBufferNano.writeMessage(2, this.fixedLine);
      }
      if (this.mobile != null) {
        paramCodedOutputByteBufferNano.writeMessage(3, this.mobile);
      }
      if (this.tollFree != null) {
        paramCodedOutputByteBufferNano.writeMessage(4, this.tollFree);
      }
      if (this.premiumRate != null) {
        paramCodedOutputByteBufferNano.writeMessage(5, this.premiumRate);
      }
      if (this.sharedCost != null) {
        paramCodedOutputByteBufferNano.writeMessage(6, this.sharedCost);
      }
      if (this.personalNumber != null) {
        paramCodedOutputByteBufferNano.writeMessage(7, this.personalNumber);
      }
      if (this.voip != null) {
        paramCodedOutputByteBufferNano.writeMessage(8, this.voip);
      }
      paramCodedOutputByteBufferNano.writeString(9, this.id);
      if (this.countryCode != 0) {
        paramCodedOutputByteBufferNano.writeInt32(10, this.countryCode);
      }
      if (!this.internationalPrefix.equals("")) {
        paramCodedOutputByteBufferNano.writeString(11, this.internationalPrefix);
      }
      if (!this.nationalPrefix.equals("")) {
        paramCodedOutputByteBufferNano.writeString(12, this.nationalPrefix);
      }
      if (!this.preferredExtnPrefix.equals("")) {
        paramCodedOutputByteBufferNano.writeString(13, this.preferredExtnPrefix);
      }
      if (!this.nationalPrefixForParsing.equals("")) {
        paramCodedOutputByteBufferNano.writeString(15, this.nationalPrefixForParsing);
      }
      if (!this.nationalPrefixTransformRule.equals("")) {
        paramCodedOutputByteBufferNano.writeString(16, this.nationalPrefixTransformRule);
      }
      if (!this.preferredInternationalPrefix.equals("")) {
        paramCodedOutputByteBufferNano.writeString(17, this.preferredInternationalPrefix);
      }
      if (this.sameMobileAndFixedLinePattern) {
        paramCodedOutputByteBufferNano.writeBool(18, this.sameMobileAndFixedLinePattern);
      }
      int i;
      Phonemetadata.NumberFormat localNumberFormat;
      if ((this.numberFormat != null) && (this.numberFormat.length > 0))
      {
        i = 0;
        while (i < this.numberFormat.length)
        {
          localNumberFormat = this.numberFormat[i];
          if (localNumberFormat != null) {
            paramCodedOutputByteBufferNano.writeMessage(19, localNumberFormat);
          }
          i += 1;
        }
      }
      if ((this.intlNumberFormat != null) && (this.intlNumberFormat.length > 0))
      {
        i = 0;
        while (i < this.intlNumberFormat.length)
        {
          localNumberFormat = this.intlNumberFormat[i];
          if (localNumberFormat != null) {
            paramCodedOutputByteBufferNano.writeMessage(20, localNumberFormat);
          }
          i += 1;
        }
      }
      if (this.pager != null) {
        paramCodedOutputByteBufferNano.writeMessage(21, this.pager);
      }
      if (this.mainCountryForCode) {
        paramCodedOutputByteBufferNano.writeBool(22, this.mainCountryForCode);
      }
      if (!this.leadingDigits.equals("")) {
        paramCodedOutputByteBufferNano.writeString(23, this.leadingDigits);
      }
      if (this.noInternationalDialling != null) {
        paramCodedOutputByteBufferNano.writeMessage(24, this.noInternationalDialling);
      }
      if (this.uan != null) {
        paramCodedOutputByteBufferNano.writeMessage(25, this.uan);
      }
      if (this.leadingZeroPossible) {
        paramCodedOutputByteBufferNano.writeBool(26, this.leadingZeroPossible);
      }
      if (this.emergency != null) {
        paramCodedOutputByteBufferNano.writeMessage(27, this.emergency);
      }
      if (this.voicemail != null) {
        paramCodedOutputByteBufferNano.writeMessage(28, this.voicemail);
      }
      if (this.shortCode != null) {
        paramCodedOutputByteBufferNano.writeMessage(29, this.shortCode);
      }
      if (this.standardRate != null) {
        paramCodedOutputByteBufferNano.writeMessage(30, this.standardRate);
      }
      if (this.carrierSpecific != null) {
        paramCodedOutputByteBufferNano.writeMessage(31, this.carrierSpecific);
      }
      if (this.mobileNumberPortableRegion) {
        paramCodedOutputByteBufferNano.writeBool(32, this.mobileNumberPortableRegion);
      }
      super.writeTo(paramCodedOutputByteBufferNano);
    }
  }
  
  public static final class PhoneMetadataCollection
    extends MessageNano
  {
    private static volatile PhoneMetadataCollection[] _emptyArray;
    public Phonemetadata.PhoneMetadata[] metadata;
    
    public PhoneMetadataCollection()
    {
      clear();
    }
    
    public static PhoneMetadataCollection[] emptyArray()
    {
      if (_emptyArray == null) {}
      synchronized (InternalNano.LAZY_INIT_LOCK)
      {
        if (_emptyArray == null) {
          _emptyArray = new PhoneMetadataCollection[0];
        }
        return _emptyArray;
      }
    }
    
    public static PhoneMetadataCollection parseFrom(CodedInputByteBufferNano paramCodedInputByteBufferNano)
      throws IOException
    {
      return new PhoneMetadataCollection().mergeFrom(paramCodedInputByteBufferNano);
    }
    
    public static PhoneMetadataCollection parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferNanoException
    {
      return (PhoneMetadataCollection)MessageNano.mergeFrom(new PhoneMetadataCollection(), paramArrayOfByte);
    }
    
    public PhoneMetadataCollection clear()
    {
      this.metadata = Phonemetadata.PhoneMetadata.emptyArray();
      this.cachedSize = -1;
      return this;
    }
    
    protected int computeSerializedSize()
    {
      int i = super.computeSerializedSize();
      int k = i;
      if (this.metadata != null)
      {
        k = i;
        if (this.metadata.length > 0)
        {
          int j = 0;
          for (;;)
          {
            k = i;
            if (j >= this.metadata.length) {
              break;
            }
            Phonemetadata.PhoneMetadata localPhoneMetadata = this.metadata[j];
            k = i;
            if (localPhoneMetadata != null) {
              k = i + CodedOutputByteBufferNano.computeMessageSize(1, localPhoneMetadata);
            }
            j += 1;
            i = k;
          }
        }
      }
      return k;
    }
    
    public PhoneMetadataCollection mergeFrom(CodedInputByteBufferNano paramCodedInputByteBufferNano)
      throws IOException
    {
      for (;;)
      {
        int i = paramCodedInputByteBufferNano.readTag();
        switch (i)
        {
        default: 
          if (WireFormatNano.parseUnknownField(paramCodedInputByteBufferNano, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          int j = WireFormatNano.getRepeatedFieldArrayLength(paramCodedInputByteBufferNano, 10);
          if (this.metadata == null) {}
          Phonemetadata.PhoneMetadata[] arrayOfPhoneMetadata;
          for (i = 0;; i = this.metadata.length)
          {
            arrayOfPhoneMetadata = new Phonemetadata.PhoneMetadata[i + j];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.metadata, 0, arrayOfPhoneMetadata, 0, i);
              j = i;
            }
            while (j < arrayOfPhoneMetadata.length - 1)
            {
              arrayOfPhoneMetadata[j] = new Phonemetadata.PhoneMetadata();
              paramCodedInputByteBufferNano.readMessage(arrayOfPhoneMetadata[j]);
              paramCodedInputByteBufferNano.readTag();
              j += 1;
            }
          }
          arrayOfPhoneMetadata[j] = new Phonemetadata.PhoneMetadata();
          paramCodedInputByteBufferNano.readMessage(arrayOfPhoneMetadata[j]);
          this.metadata = arrayOfPhoneMetadata;
        }
      }
    }
    
    public void writeTo(CodedOutputByteBufferNano paramCodedOutputByteBufferNano)
      throws IOException
    {
      if ((this.metadata != null) && (this.metadata.length > 0))
      {
        int i = 0;
        while (i < this.metadata.length)
        {
          Phonemetadata.PhoneMetadata localPhoneMetadata = this.metadata[i];
          if (localPhoneMetadata != null) {
            paramCodedOutputByteBufferNano.writeMessage(1, localPhoneMetadata);
          }
          i += 1;
        }
      }
      super.writeTo(paramCodedOutputByteBufferNano);
    }
  }
  
  public static final class PhoneNumberDesc
    extends MessageNano
  {
    private static volatile PhoneNumberDesc[] _emptyArray;
    public String exampleNumber;
    public String nationalNumberPattern;
    public String possibleNumberPattern;
    
    public PhoneNumberDesc()
    {
      clear();
    }
    
    public static PhoneNumberDesc[] emptyArray()
    {
      if (_emptyArray == null) {}
      synchronized (InternalNano.LAZY_INIT_LOCK)
      {
        if (_emptyArray == null) {
          _emptyArray = new PhoneNumberDesc[0];
        }
        return _emptyArray;
      }
    }
    
    public static PhoneNumberDesc parseFrom(CodedInputByteBufferNano paramCodedInputByteBufferNano)
      throws IOException
    {
      return new PhoneNumberDesc().mergeFrom(paramCodedInputByteBufferNano);
    }
    
    public static PhoneNumberDesc parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferNanoException
    {
      return (PhoneNumberDesc)MessageNano.mergeFrom(new PhoneNumberDesc(), paramArrayOfByte);
    }
    
    public PhoneNumberDesc clear()
    {
      this.nationalNumberPattern = "";
      this.possibleNumberPattern = "";
      this.exampleNumber = "";
      this.cachedSize = -1;
      return this;
    }
    
    protected int computeSerializedSize()
    {
      int j = super.computeSerializedSize();
      int i = j;
      if (!this.nationalNumberPattern.equals("")) {
        i = j + CodedOutputByteBufferNano.computeStringSize(2, this.nationalNumberPattern);
      }
      j = i;
      if (!this.possibleNumberPattern.equals("")) {
        j = i + CodedOutputByteBufferNano.computeStringSize(3, this.possibleNumberPattern);
      }
      i = j;
      if (!this.exampleNumber.equals("")) {
        i = j + CodedOutputByteBufferNano.computeStringSize(6, this.exampleNumber);
      }
      return i;
    }
    
    public PhoneNumberDesc mergeFrom(CodedInputByteBufferNano paramCodedInputByteBufferNano)
      throws IOException
    {
      for (;;)
      {
        int i = paramCodedInputByteBufferNano.readTag();
        switch (i)
        {
        default: 
          if (WireFormatNano.parseUnknownField(paramCodedInputByteBufferNano, i)) {}
          break;
        case 0: 
          return this;
        case 18: 
          this.nationalNumberPattern = paramCodedInputByteBufferNano.readString();
          break;
        case 26: 
          this.possibleNumberPattern = paramCodedInputByteBufferNano.readString();
          break;
        case 50: 
          this.exampleNumber = paramCodedInputByteBufferNano.readString();
        }
      }
    }
    
    public void writeTo(CodedOutputByteBufferNano paramCodedOutputByteBufferNano)
      throws IOException
    {
      if (!this.nationalNumberPattern.equals("")) {
        paramCodedOutputByteBufferNano.writeString(2, this.nationalNumberPattern);
      }
      if (!this.possibleNumberPattern.equals("")) {
        paramCodedOutputByteBufferNano.writeString(3, this.possibleNumberPattern);
      }
      if (!this.exampleNumber.equals("")) {
        paramCodedOutputByteBufferNano.writeString(6, this.exampleNumber);
      }
      super.writeTo(paramCodedOutputByteBufferNano);
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\i18n\phonenumbers\nano\Phonemetadata.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */