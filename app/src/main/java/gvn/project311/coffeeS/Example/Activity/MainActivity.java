package gvn.project311.coffeeS.Example.Activity;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import gvn.project311.coffeeS.Example.Fragment.MainFragment;
import gvn.project311.coffeeS.Example.FragmentComponent.CoverFragment;
import gvn.project311.coffeeS.Example.FragmentComponent.PhotoFragment;
import gvn.project311.coffeeS.Example.Interface.ICallBack;
import gvn.project311.coffeeS.Example.Utils.AndroidUtils;
import gvn.project311.coffeeS.Example.Utils.Constant;
import gvn.project311.coffeeS.Example.Utils.PopUpHelper;
import gvn.project311.coffeeS.Example.Fragment.LogoFragment;
import gvn.project311.coffeeS.Example.FragmentComponent.TabFragment;
import gvn.project311.coffeeS.R;


public class MainActivity extends AppCompatActivity implements ICallBack.ICommunication, ICallBack.IGetData {

    /*
    * The only required method (that is, it needs to be overwritten by the class that extends Activity)
    * defines what the layout of that activity is
    * (with the "setContentView" method)
    * and where the XML components are mapped to the code.
    * */

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidUtils.setCurrentActivity(this);

        Intent intent = new Intent(this,PermissionActivity.class);
        intent.putExtra("permissionType", Manifest.permission.WRITE_EXTERNAL_STORAGE);
        startActivityForResult(intent, Constant.REQUEST_PERMISSION_NUMBER);

        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
    }

    /*
    * This method is always executed right after the onCreate
    * and whenever the activity returns from background to foreground through onRestart.
    * */

    @Override
    protected void  onStart() {
        super.onStart();
    }

    /*
    *  It always runs before the application appears to the user.
    *  At that moment this activity is already at the top of the activity stack and is visible to the user.
    * */

    public void onResume() {
        super.onResume();  // Always call the superclass method first
    }

    /*
    * Called when the system is about to start resuming another activity.
    * This method is generally used to confirm unsaved data changes,
    * stop animations among other things that may be consuming CPU, and so on.
    * */

    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first
    }

    /*
    * Called when the activity is no longer visible to the user.
    * This can happen because it is being destroyed
    * or is going to background so that another activity takes its place at the top of the stack and is visible to the user.
    * */
    @Override
    protected void onStop() {
        super.onStop(); // call the superclass method first
    }

    /*
    * Called before destroying the activity.
    * This is the last call that activity will take when it is leaving the activity stack - that is, it is being destroyed
    * - it is called because the activity is terminating (someone called finish ()),
    * or because the system is temporarily destroying this activity instance to save space.
    * */

    @Override
    protected void onDestroy() {
        AndroidUtils.setCurrentActivity(null);
        super.onDestroy();
    }

    @Override
    public void deliverNextStepTutorial() {

        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag(MainFragment.class.getSimpleName());
        if(mainFragment != null) {
            mainFragment.nextStepTutorial();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        AndroidUtils.setCurrentActivity(this);
        switch (requestCode) {
            case Constant.REQUEST_PERMISSION_NUMBER: {
                if (resultCode == RESULT_CANCELED) {
                    finish();
                }
                else {
                    loadMainFragment();
                }
                break;
            }
        }
    }

    private void loadMainFragment() {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Fragment logoFragment = new LogoFragment();

                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                        .replace(R.id.fragment_frame, logoFragment, logoFragment.getClass().getSimpleName())
                        //.addToBackStack(null)
                        .commit();
            }
        };
        if(runnable != null)
            handler.post(runnable);
    }

    @Override
    public void getData(ArrayList data) {
/*
* Todo : distinction cover and photo
* */
        CoverFragment coverFragment = (CoverFragment) getSupportFragmentManager().findFragmentByTag(CoverFragment.class.getSimpleName());
        PhotoFragment photoFragment = (PhotoFragment) getSupportFragmentManager().findFragmentByTag(PhotoFragment.class.getSimpleName());
        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag(MainFragment.class.getSimpleName());

        if(coverFragment != null) {
            if(coverFragment.isEnable()) {
                coverFragment.setData(data);

                if(mainFragment != null) {
                    mainFragment.setUpViewNextSkip();
                    mainFragment.destroyTipView();
                }
            }
        }

        if(photoFragment != null) {
            if(!coverFragment.isEnable()) {
                photoFragment.setData(data);
                TabFragment tabFragment = (TabFragment) getSupportFragmentManager().findFragmentByTag(TabFragment.class.getSimpleName());
                tabFragment.setUpView();
                if(mainFragment != null)
                    mainFragment.destroyTipView();
            }

        }
    }

    @Override
    public void onBackPressed(){
        PopUpHelper.initPopUpHeler("Feature not implemented yet !!!, If you would like exit app, please force stop it");
    }
}
