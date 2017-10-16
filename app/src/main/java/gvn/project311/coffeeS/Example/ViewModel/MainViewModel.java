package gvn.project311.coffeeS.Example.ViewModel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import gvn.project311.coffeeS.Example.FragmentComponent.CoverFragment;
import gvn.project311.coffeeS.Lifecycle;
import gvn.project311.coffeeS.Example.FragmentComponent.InformationFragment;
import gvn.project311.coffeeS.Example.FragmentComponent.TabFragment;
import gvn.project311.coffeeS.Example.Interface.IMain;
import gvn.project311.coffeeS.Example.Utils.Constant;
import gvn.project311.coffeeS.Example.Utils.PopUpHelper;
import gvn.project311.coffeeS.R;

/**
 * Created by admin on 10/6/17.
 */

public class MainViewModel implements IMain.ViewMode {

    private IMain.View viewCallBack;
    private FragmentManager fragmentManager;
    private Constant.Tutorial step;
    private static boolean isFirstRun = true;

    private Context context;


    public MainViewModel(FragmentManager fragmentManager, Context context) {
        this.fragmentManager = fragmentManager;
        this.step = Constant.Tutorial.COVER;
        this.context = context;
    }

    @Override
    public void onViewResumed() {
        Log.i("app","Main onViewResumed");
        if(isFirstRun) {
            Log.i("app","Main onViewResumed again");
            viewCallBack.setupView();
            viewCallBack.scrollTo(0);
            isFirstRun = false;
        }
    }

    @Override
    public void onViewAttached(@NonNull Lifecycle.View viewCallBack) {
        this.viewCallBack = (IMain.View) viewCallBack;
    }

    @Override
    public void onViewDetached() {
    }

    @Override
    public void nextFragmentEnable() {
        switch (step) {
            case COVER: {
                handleCover();
                break;
            }
            case LIKE_DISLIKE_FOLLOW: {
                step = Constant.Tutorial.INFORMATION;
                break;
            }
            case INFORMATION: {
                handleInformationShop();
                break;
            }
            case TAB_DESC: {
                handleTabDesc();
                break;
            }
            case TAB_MENU: {
                handleTabMenu();
                break;
            }
            case TAB_PHOTO_ALBUM: {
                handleTabAlbum();
                break;
            }

            default:
                break;
        }
    }

    private void handleCover() {
        Log.i("app","Handle Cover");
        step = Constant.Tutorial.INFORMATION;
        InformationFragment informationFragment = (InformationFragment) fragmentManager.findFragmentByTag(InformationFragment.class.getSimpleName());
        if(informationFragment != null) {
            informationFragment.setEnableView(true);
//            informationFragment.setUpTipView();

            viewCallBack.setUpViewNextSkip();
            viewCallBack.scrollTo(2);
            viewCallBack.setUpTipView(0,20,0,0,R.drawable.ic_guide_information);

            CoverFragment coverFragment = (CoverFragment)fragmentManager.findFragmentByTag(CoverFragment.class.getSimpleName());
            coverFragment.setEnableView(false);
        }
    }

    private void handleInformationShop () {
        InformationFragment informationFragment = (InformationFragment) fragmentManager.findFragmentByTag(InformationFragment.class.getSimpleName());
        Log.i("app","check edit text "+informationFragment.checkEditText());
        if(informationFragment.checkEditText()) {

            informationFragment.setEnableView(false);
//            informationFragment.destroyTipView();
            viewCallBack.destroyNextSkip();
            step = Constant.Tutorial.TAB_DESC;

            TabFragment tabFragment = (TabFragment) fragmentManager.findFragmentByTag(TabFragment.class.getSimpleName());
            tabFragment.setEnableView(true);
            tabFragment.setEnableTab(0);

            viewCallBack.setUpTipView(0,80,85,0,R.drawable.ic_guide_desc);
            viewCallBack.scrollTo(3);
        }
        else {
            PopUpHelper.initPopUpHeler("Một số thông tin chưa điền !!!");
            viewCallBack.setUpViewNextSkip();
        }
    }

    private void handleTabDesc() {
        step = Constant.Tutorial.TAB_MENU;
        TabFragment tabFragment = (TabFragment) fragmentManager.findFragmentByTag(TabFragment.class.getSimpleName());
        tabFragment.setEnableTab(1);

        viewCallBack.setUpTipView(0,150,0,0,R.drawable.ic_guide_menu);
    }

    private void handleTabMenu() {
        step = Constant.Tutorial.TAB_PHOTO_ALBUM;
        TabFragment tabFragment = (TabFragment) fragmentManager.findFragmentByTag(TabFragment.class.getSimpleName());
        tabFragment.setEnableTab(2);
        viewCallBack.setUpTipView(0,200,0,0,R.drawable.ic_guide_photo);
    }

    private void handleTabAlbum() {

        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.complete_tutorial_notice,null);
        PopUpHelper.initPopUpHelper(viewGroup,"Hoàn Thành", fragmentManager.beginTransaction());
    }

}
