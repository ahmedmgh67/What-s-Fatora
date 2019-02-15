package com.google.i18n.phonenumbers;

import com.google.i18n.phonenumbers.nano.Phonemetadata.NumberFormat;
import com.google.i18n.phonenumbers.nano.Phonemetadata.PhoneMetadata;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AsYouTypeFormatter
{
  private static final Pattern CHARACTER_CLASS_PATTERN;
  private static final Pattern DIGIT_PATTERN = Pattern.compile(" ");
  private static final String DIGIT_PLACEHOLDER = " ";
  private static final Pattern ELIGIBLE_FORMAT_PATTERN;
  private static final Phonemetadata.PhoneMetadata EMPTY_METADATA = new Phonemetadata.PhoneMetadata();
  private static final int MIN_LEADING_DIGITS_LENGTH = 3;
  private static final Pattern NATIONAL_PREFIX_SEPARATORS_PATTERN;
  private static final char SEPARATOR_BEFORE_NATIONAL_NUMBER = ' ';
  private static final Pattern STANDALONE_DIGIT_PATTERN;
  private boolean ableToFormat = true;
  private StringBuilder accruedInput = new StringBuilder();
  private StringBuilder accruedInputWithoutFormatting = new StringBuilder();
  private String currentFormattingPattern = "";
  private Phonemetadata.PhoneMetadata currentMetadata;
  private String currentOutput = "";
  private String defaultCountry;
  private Phonemetadata.PhoneMetadata defaultMetadata;
  private String extractedNationalPrefix = "";
  private StringBuilder formattingTemplate = new StringBuilder();
  private boolean inputHasFormatting = false;
  private boolean isCompleteNumber = false;
  private boolean isExpectingCountryCallingCode = false;
  private int lastMatchPosition = 0;
  private StringBuilder nationalNumber = new StringBuilder();
  private int originalPosition = 0;
  private final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
  private int positionToRemember = 0;
  private List<Phonemetadata.NumberFormat> possibleFormats = new ArrayList();
  private StringBuilder prefixBeforeNationalNumber = new StringBuilder();
  private RegexCache regexCache = new RegexCache(64);
  private boolean shouldAddSpaceAfterNationalPrefix = false;
  
  static
  {
    EMPTY_METADATA.internationalPrefix = "NA";
    CHARACTER_CLASS_PATTERN = Pattern.compile("\\[([^\\[\\]])*\\]");
    STANDALONE_DIGIT_PATTERN = Pattern.compile("\\d(?=[^,}][^,}])");
    ELIGIBLE_FORMAT_PATTERN = Pattern.compile("[-x‐-―−ー－-／  ­​⁠　()（）［］.\\[\\]/~⁓∼～]*(\\$\\d[-x‐-―−ー－-／  ­​⁠　()（）［］.\\[\\]/~⁓∼～]*)+");
    NATIONAL_PREFIX_SEPARATORS_PATTERN = Pattern.compile("[- ]");
  }
  
  AsYouTypeFormatter(String paramString)
  {
    this.defaultCountry = paramString;
    this.currentMetadata = getMetadataForRegion(this.defaultCountry);
    this.defaultMetadata = this.currentMetadata;
  }
  
  private boolean ableToExtractLongerNdd()
  {
    boolean bool = false;
    if (this.extractedNationalPrefix.length() > 0)
    {
      this.nationalNumber.insert(0, this.extractedNationalPrefix);
      int i = this.prefixBeforeNationalNumber.lastIndexOf(this.extractedNationalPrefix);
      this.prefixBeforeNationalNumber.setLength(i);
    }
    if (!this.extractedNationalPrefix.equals(removeNationalPrefixFromNationalNumber())) {
      bool = true;
    }
    return bool;
  }
  
  private String appendNationalNumber(String paramString)
  {
    int i = this.prefixBeforeNationalNumber.length();
    if ((this.shouldAddSpaceAfterNationalPrefix) && (i > 0) && (this.prefixBeforeNationalNumber.charAt(i - 1) != ' '))
    {
      str = String.valueOf(String.valueOf(new String(this.prefixBeforeNationalNumber)));
      paramString = String.valueOf(String.valueOf(paramString));
      return str.length() + 1 + paramString.length() + str + ' ' + paramString;
    }
    String str = String.valueOf(String.valueOf(this.prefixBeforeNationalNumber));
    paramString = String.valueOf(String.valueOf(paramString));
    return str.length() + 0 + paramString.length() + str + paramString;
  }
  
  private String attemptToChooseFormattingPattern()
  {
    if (this.nationalNumber.length() >= 3)
    {
      getAvailableFormats(this.nationalNumber.toString());
      String str = attemptToFormatAccruedDigits();
      if (str.length() > 0) {
        return str;
      }
      if (maybeCreateNewTemplate()) {}
      for (str = inputAccruedNationalNumber();; str = this.accruedInput.toString()) {
        return str;
      }
    }
    return appendNationalNumber(this.nationalNumber.toString());
  }
  
  private String attemptToChoosePatternWithPrefixExtracted()
  {
    this.ableToFormat = true;
    this.isExpectingCountryCallingCode = false;
    this.possibleFormats.clear();
    this.lastMatchPosition = 0;
    this.formattingTemplate.setLength(0);
    this.currentFormattingPattern = "";
    return attemptToChooseFormattingPattern();
  }
  
  private boolean attemptToExtractCountryCallingCode()
  {
    if (this.nationalNumber.length() == 0) {}
    int i;
    do
    {
      return false;
      localObject = new StringBuilder();
      i = this.phoneUtil.extractCountryCode(this.nationalNumber, (StringBuilder)localObject);
    } while (i == 0);
    this.nationalNumber.setLength(0);
    this.nationalNumber.append((CharSequence)localObject);
    Object localObject = this.phoneUtil.getRegionCodeForCountryCode(i);
    if ("001".equals(localObject)) {
      this.currentMetadata = this.phoneUtil.getMetadataForNonGeographicalRegion(i);
    }
    for (;;)
    {
      localObject = Integer.toString(i);
      this.prefixBeforeNationalNumber.append((String)localObject).append(' ');
      this.extractedNationalPrefix = "";
      return true;
      if (!((String)localObject).equals(this.defaultCountry)) {
        this.currentMetadata = getMetadataForRegion((String)localObject);
      }
    }
  }
  
  private boolean attemptToExtractIdd()
  {
    RegexCache localRegexCache = this.regexCache;
    Object localObject = String.valueOf("\\+|");
    String str = String.valueOf(this.currentMetadata.internationalPrefix);
    if (str.length() != 0) {}
    for (localObject = ((String)localObject).concat(str);; localObject = new String((String)localObject))
    {
      localObject = localRegexCache.getPatternForRegex((String)localObject).matcher(this.accruedInputWithoutFormatting);
      if (!((Matcher)localObject).lookingAt()) {
        break;
      }
      this.isCompleteNumber = true;
      int i = ((Matcher)localObject).end();
      this.nationalNumber.setLength(0);
      this.nationalNumber.append(this.accruedInputWithoutFormatting.substring(i));
      this.prefixBeforeNationalNumber.setLength(0);
      this.prefixBeforeNationalNumber.append(this.accruedInputWithoutFormatting.substring(0, i));
      if (this.accruedInputWithoutFormatting.charAt(0) != '+') {
        this.prefixBeforeNationalNumber.append(' ');
      }
      return true;
    }
    return false;
  }
  
  private boolean createFormattingTemplate(Phonemetadata.NumberFormat paramNumberFormat)
  {
    String str = paramNumberFormat.pattern;
    if (str.indexOf('|') != -1) {}
    do
    {
      return false;
      str = CHARACTER_CLASS_PATTERN.matcher(str).replaceAll("\\\\d");
      str = STANDALONE_DIGIT_PATTERN.matcher(str).replaceAll("\\\\d");
      this.formattingTemplate.setLength(0);
      paramNumberFormat = getFormattingTemplate(str, paramNumberFormat.format);
    } while (paramNumberFormat.length() <= 0);
    this.formattingTemplate.append(paramNumberFormat);
    return true;
  }
  
  private void getAvailableFormats(String paramString)
  {
    Phonemetadata.NumberFormat[] arrayOfNumberFormat;
    if ((this.isCompleteNumber) && (this.currentMetadata.intlNumberFormat.length > 0))
    {
      arrayOfNumberFormat = this.currentMetadata.intlNumberFormat;
      if (this.currentMetadata.nationalPrefix.equals("")) {
        break label136;
      }
    }
    label136:
    for (int i = 1;; i = 0)
    {
      int k = arrayOfNumberFormat.length;
      int j = 0;
      while (j < k)
      {
        Phonemetadata.NumberFormat localNumberFormat = arrayOfNumberFormat[j];
        if (((i == 0) || (this.isCompleteNumber) || (localNumberFormat.nationalPrefixOptionalWhenFormatting) || (PhoneNumberUtil.formattingRuleHasFirstGroupOnly(localNumberFormat.nationalPrefixFormattingRule))) && (isFormatEligible(localNumberFormat.format))) {
          this.possibleFormats.add(localNumberFormat);
        }
        j += 1;
      }
      arrayOfNumberFormat = this.currentMetadata.numberFormat;
      break;
    }
    narrowDownPossibleFormats(paramString);
  }
  
  private String getFormattingTemplate(String paramString1, String paramString2)
  {
    Object localObject = this.regexCache.getPatternForRegex(paramString1).matcher("999999999999999");
    ((Matcher)localObject).find();
    localObject = ((Matcher)localObject).group();
    if (((String)localObject).length() < this.nationalNumber.length()) {
      return "";
    }
    return ((String)localObject).replaceAll(paramString1, paramString2).replaceAll("9", " ");
  }
  
  private Phonemetadata.PhoneMetadata getMetadataForRegion(String paramString)
  {
    int i = this.phoneUtil.getCountryCodeForRegion(paramString);
    paramString = this.phoneUtil.getRegionCodeForCountryCode(i);
    paramString = this.phoneUtil.getMetadataForRegion(paramString);
    if (paramString != null) {
      return paramString;
    }
    return EMPTY_METADATA;
  }
  
  private String inputAccruedNationalNumber()
  {
    int j = this.nationalNumber.length();
    if (j > 0)
    {
      String str = "";
      int i = 0;
      while (i < j)
      {
        str = inputDigitHelper(this.nationalNumber.charAt(i));
        i += 1;
      }
      if (this.ableToFormat) {
        return appendNationalNumber(str);
      }
      return this.accruedInput.toString();
    }
    return this.prefixBeforeNationalNumber.toString();
  }
  
  private String inputDigitHelper(char paramChar)
  {
    Matcher localMatcher = DIGIT_PATTERN.matcher(this.formattingTemplate);
    if (localMatcher.find(this.lastMatchPosition))
    {
      String str = localMatcher.replaceFirst(Character.toString(paramChar));
      this.formattingTemplate.replace(0, str.length(), str);
      this.lastMatchPosition = localMatcher.start();
      return this.formattingTemplate.substring(0, this.lastMatchPosition + 1);
    }
    if (this.possibleFormats.size() == 1) {
      this.ableToFormat = false;
    }
    this.currentFormattingPattern = "";
    return this.accruedInput.toString();
  }
  
  private String inputDigitWithOptionToRememberPosition(char paramChar, boolean paramBoolean)
  {
    this.accruedInput.append(paramChar);
    if (paramBoolean) {
      this.originalPosition = this.accruedInput.length();
    }
    if (!isDigitOrLeadingPlusSign(paramChar))
    {
      this.ableToFormat = false;
      this.inputHasFormatting = true;
      if (this.ableToFormat) {
        break label125;
      }
      if (!this.inputHasFormatting) {
        break label76;
      }
      localObject = this.accruedInput.toString();
    }
    label76:
    label125:
    String str1;
    String str2;
    do
    {
      return (String)localObject;
      paramChar = normalizeAndAccrueDigitsAndPlusSign(paramChar, paramBoolean);
      break;
      if (attemptToExtractIdd())
      {
        if (attemptToExtractCountryCallingCode()) {
          return attemptToChoosePatternWithPrefixExtracted();
        }
      }
      else if (ableToExtractLongerNdd())
      {
        this.prefixBeforeNationalNumber.append(' ');
        return attemptToChoosePatternWithPrefixExtracted();
      }
      return this.accruedInput.toString();
      switch (this.accruedInputWithoutFormatting.length())
      {
      }
      while (this.isExpectingCountryCallingCode)
      {
        if (attemptToExtractCountryCallingCode()) {
          this.isExpectingCountryCallingCode = false;
        }
        localObject = String.valueOf(String.valueOf(this.prefixBeforeNationalNumber));
        str1 = String.valueOf(String.valueOf(this.nationalNumber.toString()));
        return ((String)localObject).length() + 0 + str1.length() + (String)localObject + str1;
        return this.accruedInput.toString();
        if (attemptToExtractIdd())
        {
          this.isExpectingCountryCallingCode = true;
        }
        else
        {
          this.extractedNationalPrefix = removeNationalPrefixFromNationalNumber();
          return attemptToChooseFormattingPattern();
        }
      }
      if (this.possibleFormats.size() <= 0) {
        break label363;
      }
      str2 = inputDigitHelper(paramChar);
      str1 = attemptToFormatAccruedDigits();
      localObject = str1;
    } while (str1.length() > 0);
    narrowDownPossibleFormats(this.nationalNumber.toString());
    if (maybeCreateNewTemplate()) {
      return inputAccruedNationalNumber();
    }
    if (this.ableToFormat) {}
    for (Object localObject = appendNationalNumber(str2);; localObject = this.accruedInput.toString()) {
      return (String)localObject;
    }
    label363:
    return attemptToChooseFormattingPattern();
  }
  
  private boolean isDigitOrLeadingPlusSign(char paramChar)
  {
    return (Character.isDigit(paramChar)) || ((this.accruedInput.length() == 1) && (PhoneNumberUtil.PLUS_CHARS_PATTERN.matcher(Character.toString(paramChar)).matches()));
  }
  
  private boolean isFormatEligible(String paramString)
  {
    return ELIGIBLE_FORMAT_PATTERN.matcher(paramString).matches();
  }
  
  private boolean isNanpaNumberWithNationalPrefix()
  {
    return (this.currentMetadata.countryCode == 1) && (this.nationalNumber.charAt(0) == '1') && (this.nationalNumber.charAt(1) != '0') && (this.nationalNumber.charAt(1) != '1');
  }
  
  private boolean maybeCreateNewTemplate()
  {
    Iterator localIterator = this.possibleFormats.iterator();
    while (localIterator.hasNext())
    {
      Phonemetadata.NumberFormat localNumberFormat = (Phonemetadata.NumberFormat)localIterator.next();
      String str = localNumberFormat.pattern;
      if (this.currentFormattingPattern.equals(str)) {
        return false;
      }
      if (createFormattingTemplate(localNumberFormat))
      {
        this.currentFormattingPattern = str;
        this.shouldAddSpaceAfterNationalPrefix = NATIONAL_PREFIX_SEPARATORS_PATTERN.matcher(localNumberFormat.nationalPrefixFormattingRule).find();
        this.lastMatchPosition = 0;
        return true;
      }
      localIterator.remove();
    }
    this.ableToFormat = false;
    return false;
  }
  
  private void narrowDownPossibleFormats(String paramString)
  {
    int i = paramString.length();
    Iterator localIterator = this.possibleFormats.iterator();
    while (localIterator.hasNext())
    {
      Phonemetadata.NumberFormat localNumberFormat = (Phonemetadata.NumberFormat)localIterator.next();
      if (localNumberFormat.leadingDigitsPattern.length != 0)
      {
        int j = Math.min(i - 3, localNumberFormat.leadingDigitsPattern.length - 1);
        if (!this.regexCache.getPatternForRegex(localNumberFormat.leadingDigitsPattern[j]).matcher(paramString).lookingAt()) {
          localIterator.remove();
        }
      }
    }
  }
  
  private char normalizeAndAccrueDigitsAndPlusSign(char paramChar, boolean paramBoolean)
  {
    char c;
    if (paramChar == '+')
    {
      c = paramChar;
      this.accruedInputWithoutFormatting.append(paramChar);
    }
    for (;;)
    {
      if (paramBoolean) {
        this.positionToRemember = this.accruedInputWithoutFormatting.length();
      }
      return c;
      c = Character.forDigit(Character.digit(paramChar, 10), 10);
      this.accruedInputWithoutFormatting.append(c);
      this.nationalNumber.append(c);
    }
  }
  
  private String removeNationalPrefixFromNationalNumber()
  {
    int j = 0;
    int i;
    if (isNanpaNumberWithNationalPrefix())
    {
      i = 1;
      this.prefixBeforeNationalNumber.append('1').append(' ');
      this.isCompleteNumber = true;
    }
    for (;;)
    {
      Object localObject = this.nationalNumber.substring(0, i);
      this.nationalNumber.delete(0, i);
      return (String)localObject;
      i = j;
      if (!this.currentMetadata.nationalPrefixForParsing.equals(""))
      {
        localObject = this.regexCache.getPatternForRegex(this.currentMetadata.nationalPrefixForParsing).matcher(this.nationalNumber);
        i = j;
        if (((Matcher)localObject).lookingAt())
        {
          i = j;
          if (((Matcher)localObject).end() > 0)
          {
            this.isCompleteNumber = true;
            i = ((Matcher)localObject).end();
            this.prefixBeforeNationalNumber.append(this.nationalNumber.substring(0, i));
          }
        }
      }
    }
  }
  
  String attemptToFormatAccruedDigits()
  {
    Iterator localIterator = this.possibleFormats.iterator();
    while (localIterator.hasNext())
    {
      Phonemetadata.NumberFormat localNumberFormat = (Phonemetadata.NumberFormat)localIterator.next();
      Matcher localMatcher = this.regexCache.getPatternForRegex(localNumberFormat.pattern).matcher(this.nationalNumber);
      if (localMatcher.matches())
      {
        this.shouldAddSpaceAfterNationalPrefix = NATIONAL_PREFIX_SEPARATORS_PATTERN.matcher(localNumberFormat.nationalPrefixFormattingRule).find();
        return appendNationalNumber(localMatcher.replaceAll(localNumberFormat.format));
      }
    }
    return "";
  }
  
  public void clear()
  {
    this.currentOutput = "";
    this.accruedInput.setLength(0);
    this.accruedInputWithoutFormatting.setLength(0);
    this.formattingTemplate.setLength(0);
    this.lastMatchPosition = 0;
    this.currentFormattingPattern = "";
    this.prefixBeforeNationalNumber.setLength(0);
    this.extractedNationalPrefix = "";
    this.nationalNumber.setLength(0);
    this.ableToFormat = true;
    this.inputHasFormatting = false;
    this.positionToRemember = 0;
    this.originalPosition = 0;
    this.isCompleteNumber = false;
    this.isExpectingCountryCallingCode = false;
    this.possibleFormats.clear();
    this.shouldAddSpaceAfterNationalPrefix = false;
    if (!this.currentMetadata.equals(this.defaultMetadata)) {
      this.currentMetadata = getMetadataForRegion(this.defaultCountry);
    }
  }
  
  String getExtractedNationalPrefix()
  {
    return this.extractedNationalPrefix;
  }
  
  public int getRememberedPosition()
  {
    int k;
    if (!this.ableToFormat)
    {
      k = this.originalPosition;
      return k;
    }
    int j = 0;
    int i = 0;
    for (;;)
    {
      k = i;
      if (j >= this.positionToRemember) {
        break;
      }
      k = i;
      if (i >= this.currentOutput.length()) {
        break;
      }
      k = j;
      if (this.accruedInputWithoutFormatting.charAt(j) == this.currentOutput.charAt(i)) {
        k = j + 1;
      }
      i += 1;
      j = k;
    }
  }
  
  public String inputDigit(char paramChar)
  {
    this.currentOutput = inputDigitWithOptionToRememberPosition(paramChar, false);
    return this.currentOutput;
  }
  
  public String inputDigitAndRememberPosition(char paramChar)
  {
    this.currentOutput = inputDigitWithOptionToRememberPosition(paramChar, true);
    return this.currentOutput;
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\com\google\i18n\phonenumbers\AsYouTypeFormatter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */