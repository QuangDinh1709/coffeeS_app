package gvn.project311.coffeeS.Example.ViewModel.Component;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

import gvn.project311.coffeeS.Lifecycle;
import gvn.project311.coffeeS.Example.Interface.IInformation;

/**
 * Created by admin on 10/6/17.
 */

public class InformationViewModel implements IInformation.ViewModel {

    private IInformation.View viewCallBack;
    private boolean isFirstRun = true;

    public InformationViewModel() {
    }

    @Override
    public void onViewResumed() {
        if(isFirstRun) {
            viewCallBack.setEnableView(false);
            isFirstRun = false;
        }
    }

    @Override
    public void onViewAttached(@NonNull Lifecycle.View viewCallBack) {
        this.viewCallBack = (IInformation.View) viewCallBack;
    }

    @Override
    public void onViewDetached() {

    }

    @Override
    public void handleTimePicker(final EditText editText, Context context) {
        Calendar mCurrentTime = Calendar.getInstance();
        int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mCurrentTime.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog;

        timePickerDialog = new TimePickerDialog(context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                editText.setText(hour + ":" + minute);
            }
        }, hour, minute, true);
        timePickerDialog.show();
    }
}
