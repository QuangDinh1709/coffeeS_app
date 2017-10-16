package gvn.project311.coffeeS.Example.ViewModel.Component;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

import gvn.project311.coffeeS.Example.Adapter.PhotoAdapter;
import gvn.project311.coffeeS.Example.Fragment.GalleryImageFragment;
import gvn.project311.coffeeS.Example.Interface.ICallBack;
import gvn.project311.coffeeS.Example.Interface.IPhoto;
import gvn.project311.coffeeS.Lifecycle;
import gvn.project311.coffeeS.R;

/**
 * Created by admin on 10/9/17.
 */

public class PhotoViewModel implements IPhoto.ViewModel {

    private IPhoto.View viewCallBack;

    private ArrayList<String> urlImage = new ArrayList<>();
    private Context context;

    private boolean isFirstRun = true;

    private PhotoAdapter photoAdapter;


    public PhotoViewModel(Context context) {
        this.context = context;
        urlImage.add(null);
        photoAdapter = new PhotoAdapter(urlImage,context);
    }

    @Override
    public void onViewResumed() {
        if(isFirstRun)
        {
            viewCallBack.setAdapterForGrid(photoAdapter);
        }
    }

    @Override
    public void onViewAttached(@NonNull Lifecycle.View viewCallBack) {
        this.viewCallBack = (IPhoto.View) viewCallBack;
    }

    @Override
    public void onViewDetached() {

    }

    @Override
    public void addData(ArrayList data) {
        data.add(null);
        photoAdapter.addMoreData(data, new ICallBack.IRefreshCallBack() {
            @Override
            public void refreshCallBack() {

                int temp = 0;
                if(photoAdapter.getCount() % 3 != 0)
                    temp = 1;

                viewCallBack.modifyLayoutGrid((photoAdapter.getCount()/3 + temp));
            }
        });
    }

    @Override
    public void showGalleryImage(FragmentTransaction fragmentTransaction) {
        GalleryImageFragment galleryImageFragment = new GalleryImageFragment();
        fragmentTransaction
                .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                .add(R.id.fragment_frame,galleryImageFragment,galleryImageFragment.getClass().getSimpleName())
                //.addToBackStack(null)
                .commit();
    }

}
