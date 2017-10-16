package gvn.project311.coffeeS.Example.Utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by admin on 10/6/17.
 */

public class UtilsView {
    public static void enableDisableViewGroup(ViewGroup viewGroup, boolean enable) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = viewGroup.getChildAt(i);
            view.setEnabled(enable);
            if (view instanceof ViewGroup) {
                enableDisableViewGroup((ViewGroup) view, enable);
            }
        }
    }

    public static float dpFromPx(final Context context, final float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public static boolean validateEditText(EditText ... editTexts) {
        boolean result = true;
        for(EditText editText : editTexts) {
            if(editText.getText().toString().isEmpty()) {
               return false;
            }
        }
        return result;
    }
}
