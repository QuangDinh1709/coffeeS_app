package gvn.project311.coffeeS.Example.Utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by admin on 10/10/17.
 */

public class PermissionUtils {
    private static final String TAG = "PermissionUtils";

    private static final String PREFS_FILE_NAME = "preference_permission";
    private static final String PREFS_FIRST_TIME_KEY = "is_app_launched_first_time";

    public interface PermissionAskListener {
        void onPermissionGranted();
        void onPermissionRequest();
        void onPermissionPreviouslyDenied();
        void onPermissionDisabled();
    }

    private static boolean getApplicationLaunchedFirstTime() {
        return AndroidSharedPreferences.getInstance().RetrievingDataBoolean(PREFS_FIRST_TIME_KEY);
    }

    private static void setApplicationLaunchedFirstTime() {
        AndroidSharedPreferences.getInstance().StoringDataBoolean(PREFS_FIRST_TIME_KEY,false);
    }

    private static boolean isRuntimePermissionRequired() {
        return (Build.VERSION.SDK_INT >= 23);
    }

    public static void checkPermission(Activity activity, String permission, PermissionAskListener permissionAskListener) {
//        LogUtils.DBG(TAG,"checkPermission");

        if(!isRuntimePermissionRequired()) {
            permissionAskListener.onPermissionGranted();
        }
        else {
            if(ContextCompat.checkSelfPermission(activity,permission) != PackageManager.PERMISSION_GRANTED){
                if(ActivityCompat.shouldShowRequestPermissionRationale(activity,permission))
                {
                    permissionAskListener.onPermissionPreviouslyDenied();
                }
                else {
                    if(getApplicationLaunchedFirstTime()) {
//                        LogUtils.DBG(TAG, "ApplicationLaunchedFirstTime");
                        setApplicationLaunchedFirstTime();
                        permissionAskListener.onPermissionRequest();
                    }
                    else {
//                        LogUtils.DBG(TAG, "onPermissionDisabled");
                        permissionAskListener.onPermissionDisabled();
                    }
                }
            }
            else {
//                LogUtils.DBG(TAG, "Permission already granted");
                permissionAskListener.onPermissionGranted();
            }
        }
    }
}
