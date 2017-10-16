package gvn.project311.coffeeS.Example.ViewModel.Component;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import gvn.project311.coffeeS.Example.Adapter.CoverAdapter;
import gvn.project311.coffeeS.Example.Fragment.GalleryImageFragment;
import gvn.project311.coffeeS.Example.Interface.ICover;
import gvn.project311.coffeeS.Lifecycle;
import gvn.project311.coffeeS.Example.FragmentComponent.ImageFragment;
import gvn.project311.coffeeS.R;

/**
 * Created by admin on 10/6/17.
 */

public class CoverViewModel implements ICover.ViewModel{

    private ICover.View viewCallBack;
    private boolean isFirstRun = true;
    private CoverAdapter coverAdapter;
    private List<Fragment> fragments;
    private FragmentManager fragmentManager;

    public CoverViewModel(FragmentManager fragmentManager) {
        fragments = new ArrayList<>();
        this.fragmentManager = fragmentManager;
        coverAdapter = new CoverAdapter(fragments,this.fragmentManager);
    }

    @Override
    public void onViewResumed() {
        if(isFirstRun) {

            viewCallBack.setEnableView(true);
            viewCallBack.setUpView();
            isFirstRun = false;
            viewCallBack.setUpViewPager(coverAdapter);

        }

    }

    @Override
    public void onViewAttached(@NonNull Lifecycle.View viewCallBack) {
        this.viewCallBack = (ICover.View) viewCallBack;
    }

    @Override
    public void onViewDetached() {
    }

    @Override
    public void showGalleryImage(FragmentTransaction fragmentTransaction) {

        GalleryImageFragment galleryImageFragment = GalleryImageFragment.newInstance(View.VISIBLE);
        fragmentTransaction
                .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                .add(R.id.fragment_frame,galleryImageFragment,galleryImageFragment.getClass().getSimpleName())
                //.addToBackStack(null)
                .commit();
    }

    @Override
    public void setData(List<String> data) {
        for(String path : data) {
            fragments.add(ImageFragment.newInstance(path));
        }
        coverAdapter.setData(fragments);
    }

}
