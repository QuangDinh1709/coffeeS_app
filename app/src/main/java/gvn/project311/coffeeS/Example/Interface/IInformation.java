package gvn.project311.coffeeS.Example.Interface;

import android.content.Context;
import android.widget.EditText;

import gvn.project311.coffeeS.Lifecycle;

/**
 * Created by admin on 10/6/17.
 */

public interface IInformation {
    interface View extends Lifecycle.View {
        void setEnableView(boolean enable);
    }
    interface ViewModel extends Lifecycle.ViewModel {
        void handleTimePicker(EditText editText, Context context);
    }
}
