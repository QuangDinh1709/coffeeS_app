package gvn.project311.coffeeS.Example.FragmentComponent;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import gvn.project311.coffeeS.BaseFragment;
import gvn.project311.coffeeS.Example.Interface.ICover;
import gvn.project311.coffeeS.Example.Utils.Constant;
import gvn.project311.coffeeS.Example.Utils.UtilsView;
import gvn.project311.coffeeS.Example.ViewModel.Component.CoverViewModel;
import gvn.project311.coffeeS.Lifecycle;
import gvn.project311.coffeeS.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CoverFragment extends BaseFragment implements ICover.View, View.OnClickListener {

    private ICover.ViewModel coverViewMode;
    private View view;

    private ViewPager viewPagerCover;
    private TabLayout tabLayoutCover;

    private boolean enable;

    public boolean isEnable() {
        return enable;
    }

    public CoverFragment() {

    }


    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        coverViewMode = new CoverViewModel(getFragmentManager());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cover, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        ImageView imageViewUpload = (ImageView) view.findViewById(R.id.imageViewUpload);
        imageViewUpload.setOnClickListener(this);

        viewPagerCover = (ViewPager) view.findViewById(R.id.viewPager_Cover);
        tabLayoutCover = (TabLayout) view.findViewById(R.id.tabLayout_Cover);
    }

    @Override
    protected Lifecycle.ViewModel getViewModel() {
        return coverViewMode;
    }


    @Override
    public void setEnableView(boolean enable) {
        if(enable)
            view.setAlpha(Constant.NO_TRANSPARENT);
        else
            view.setAlpha(Constant.TRANSPARENT);
        UtilsView.enableDisableViewGroup((ViewGroup) view,enable);
        this.enable = enable;
    }

    @Override
    public void setUpView() {
        int top = (int) UtilsView.pxFromDp(getContext(),160);
        int right = (int) UtilsView.pxFromDp(getContext(),50);

        Fragment fragmentStep = StepTutorialFragment.newInstance(0,top,right,0,R.drawable.ic_guide_cover);
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                .add(R.id.fragment_frame,fragmentStep,fragmentStep.getClass().getSimpleName())
                //.addToBackStack(null)
                .commit();
    }

    @Override
    public void setUpViewPager(PagerAdapter baseAdapter) {
        viewPagerCover.setAdapter(baseAdapter);
        tabLayoutCover.setupWithViewPager(viewPagerCover);
    }

    @Override
    public void onClick(View view) {
        Log.i("app", "onClick");

        Fragment fragmentStep = getFragmentManager().findFragmentByTag(StepTutorialFragment.class.getSimpleName());
        if (fragmentStep != null) {
            getFragmentManager().beginTransaction()
                    .remove(fragmentStep)
                    .commit();
        }

        coverViewMode.showGalleryImage(getFragmentManager().beginTransaction());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void setData(List<String> data) {
        coverViewMode.setData(data);
    }
}
