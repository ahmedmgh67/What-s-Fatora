package android.support.design.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

public class TextInputEditText
  extends AppCompatEditText
{
  public TextInputEditText(Context paramContext)
  {
    super(paramContext);
  }
  
  public TextInputEditText(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public TextInputEditText(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public InputConnection onCreateInputConnection(EditorInfo paramEditorInfo)
  {
    InputConnection localInputConnection = super.onCreateInputConnection(paramEditorInfo);
    if ((localInputConnection != null) && (paramEditorInfo.hintText == null)) {}
    for (ViewParent localViewParent = getParent();; localViewParent = localViewParent.getParent()) {
      if ((localViewParent instanceof View))
      {
        if ((localViewParent instanceof TextInputLayout)) {
          paramEditorInfo.hintText = ((TextInputLayout)localViewParent).getHint();
        }
      }
      else {
        return localInputConnection;
      }
    }
  }
}


/* Location:              H:\As A Bussines Man\confedince\App Dev Department\What's Fatora\Tawseel APK\Client\dex2jar-2.0\t-dex2jar.jar!\android\support\design\widget\TextInputEditText.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */