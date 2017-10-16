package gvn.project311.coffeeS.Example.Utils;

import android.app.Activity;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import java.util.ArrayList;

import gvn.project311.coffeeS.Example.Interface.ICallBack;

/**
 * Created by admin on 10/9/17.
 */

public class AndroidUtils {
    private static Activity currentActivity = null;

    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    public static void setCurrentActivity(Activity currentActivity) {
        AndroidUtils.currentActivity = currentActivity;
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static ArrayList<String> getAllShownImagesPath(Activity activity) {
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;
        ArrayList<String> listOfAllImages = new ArrayList<String>();

        String absolutePathOfImage = null;
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = { MediaStore.MediaColumns.DATA};

        cursor = activity.getContentResolver().query(uri, projection, null,
                null, MediaStore.Images.Media.DATE_TAKEN + " DESC ");

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);

        //column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);

        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);

            listOfAllImages.add(absolutePathOfImage);
        }
        return listOfAllImages;
    }

    public static void AddLayoutParams(final View view, final int size){
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                int width = view.getWidth();
                int height = view.getHeight();

                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN){
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }else{
                    view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }

                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.width = width;
                layoutParams.height = height * size;

                Log.i("coffeeS","height "+height);

                view.setLayoutParams(layoutParams);
            }
        });
    }

    public static void getHeightView(final View view, final ICallBack.IGetDataInteger getData) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                int height = view.getHeight();

                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN){
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }else{
                    view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }

                getData.getData(height);
            }
        });
    }

}
