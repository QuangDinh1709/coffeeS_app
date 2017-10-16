package gvn.project311.coffeeS.Example.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;

import gvn.project311.coffeeS.Example.Utils.Constant;
import gvn.project311.coffeeS.Example.Utils.PermissionUtils;

public class PermissionActivity extends Activity implements PermissionUtils.PermissionAskListener {

    private final int STATUS_PRE_WAITING 		= 1;
    private final int STATUS_WAITING	 		= 2;
    private final int STATUS_COMPLETED	 		= 3;
    private int processStatus 					= STATUS_PRE_WAITING;

    private final String TAG = "permission";


    private String permissionType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();



        if (extras != null)
        {
            permissionType = extras.getString("permissionType");
            PermissionUtils.checkPermission(this,permissionType,this);

//            LogUtils.DBG(TAG,"onCreate set status to PRE_WAITING");
//            processStatus = STATUS_PRE_WAITING;
        }
        else
        {
            setResult(RESULT_CANCELED);
            finish();//finish the activity in onPause
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        processStatus = STATUS_COMPLETED;
        switch (requestCode)
        {
            case Constant.REQUEST_PERMISSION_NUMBER: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setResult(RESULT_OK);
                }
                else {
                    setResult(RESULT_CANCELED);
                }
                finish();
                return;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        LogUtils.DBG(TAG,"onresume Perm Activity");

        if(processStatus == STATUS_PRE_WAITING)
        {
//            LogUtils.DBG(TAG,"onResume PRE_WAITING, set status to WAITING");
            processStatus = STATUS_WAITING;
        }
        else
        {
//            LogUtils.DBG(TAG,"onResume after 500 MS "+processStatus);
            if(processStatus == STATUS_WAITING)
            {
//                LogUtils.DBG(TAG,"onResume return CANCEL result");
                setResult(RESULT_CANCELED);
                finish();//finish the activity in onPause
            }
        }
    }

    @Override
    public void onPermissionGranted() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onPermissionRequest() {
        ActivityCompat.requestPermissions(this,
                new String[]{permissionType},
                Constant.REQUEST_PERMISSION_NUMBER);
    }

    @Override
    public void onPermissionPreviouslyDenied() {

        new AlertDialog.Builder(this)
                .setTitle("Permission required")
                .setMessage("Storage is required for this application to work ! ")
                .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(PermissionActivity.this,
                                new String[]{permissionType},
                                Constant.REQUEST_PERMISSION_NUMBER);


                    }

                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        setResult(RESULT_CANCELED);
                        finish();
                    }
                })
                .show();

    }

    @Override
    public void onPermissionDisabled() {

        new AlertDialog.Builder(this)
                .setTitle("Permission Disabled")
                .setMessage("Please enable the permission in \n  Settings>CoffeeS>Permission \n and check 'storage' permission")
                .setPositiveButton("Go to settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_SETTINGS));
                    }

                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        setResult(RESULT_CANCELED);
                        finish();
                    }
                })
                .show();
    }
}
