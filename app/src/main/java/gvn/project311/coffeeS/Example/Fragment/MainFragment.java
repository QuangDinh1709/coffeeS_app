package gvn.project311.coffeeS.Example.Fragment;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

import gvn.project311.coffeeS.Example.FragmentComponent.CoverFragment;
import gvn.project311.coffeeS.Example.FragmentComponent.NextSkipFragment;
import gvn.project311.coffeeS.Example.FragmentComponent.StepTutorialFragment;
import gvn.project311.coffeeS.Example.Utils.UtilsView;
import gvn.project311.coffeeS.Example.ViewModel.MainViewModel;
import gvn.project311.coffeeS.Lifecycle;
import gvn.project311.coffeeS.BaseFragment;
import gvn.project311.coffeeS.Example.FragmentComponent.EmotionFragment;
import gvn.project311.coffeeS.Example.FragmentComponent.InformationFragment;
import gvn.project311.coffeeS.Example.FragmentComponent.TabFragment;
import gvn.project311.coffeeS.Example.Interface.IMain;
import gvn.project311.coffeeS.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment implements IMain.View {

    private IMain.ViewMode mainViewModel;

    private ScrollView scrollViewMain;
    private boolean isLockedScrollView = false;


    public MainFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainViewModel = new MainViewModel(getFragmentManager(), getContext());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        scrollViewMain = (ScrollView) view.findViewById(R.id.scrollView_MainFragment);
        scrollViewMain.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return isLockedScrollView;
            }
        });
        return view;
    }

    @Override
    protected Lifecycle.ViewModel getViewModel() {
        return mainViewModel;
    }

    @Override
    public void setupView() {
        Log.i("app","setupView main");
        Fragment fragmentCover = new CoverFragment();
        Fragment fragmentEmotion = new EmotionFragment();
        Fragment fragmentInformation = new InformationFragment();
        Fragment fragmentTab = new TabFragment();

        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                .add(R.id.frame_cover, fragmentCover, fragmentCover.getClass().getSimpleName())
                .add(R.id.frame_dislike_like, fragmentEmotion, fragmentEmotion.getClass().getSimpleName())
                .add(R.id.frame_information, fragmentInformation, fragmentInformation.getClass().getSimpleName())
                .add(R.id.frame_tab, fragmentTab, fragmentTab.getClass().getSimpleName())
                //.addToBackStack(null)
                .commit();
    }

    @Override
    public void setUpViewNextSkip() {

        destroyNextSkip();

        Fragment fragmentNextSkip = NextSkipFragment.newInstance(0);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction
                .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                .add(R.id.fragment_frame,fragmentNextSkip,fragmentNextSkip.getClass().getSimpleName())
                //.addToBackStack(null)
                .commit();
    }

    @Override
    public void scrollTo(int childAt) {
        Log.i("app","scrollTo "+childAt);
        ViewGroup viewGroup = (ViewGroup) scrollViewMain.getChildAt(0);
        final View view = viewGroup.getChildAt(childAt);
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                final int top     = view.getTop();
                final int bottom  = view.getBottom();
                Log.i("app","top "+top+ " bottom "+bottom);
                scrollViewMain.setSmoothScrollingEnabled(true);
                scrollViewMain.scrollTo(0,top);

//                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN){
//                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                }else{
//                    view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                }
//                scrollViewMain.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.i("app","top "+top);
//                        scrollViewMain.setSmoothScrollingEnabled(true);
//                        scrollViewMain.scrollTo(0,top);
//                    }
//                });
            }
        });
    }

    @Override
    public void setUpTipView(int left, int top, int right, int bottom, int res) {

        destroyTipView();

        top = (int) UtilsView.pxFromDp(getContext(),top);
        right = (int) UtilsView.pxFromDp(getContext(),right);
        Fragment fragmentStep = StepTutorialFragment.newInstance(0,top,right,0,res);
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                .add(R.id.fragment_frame,fragmentStep,fragmentStep.getClass().getSimpleName())
                //.addToBackStack(null)
                .commit();
    }

    @Override
    public void destroyTipView() {
        StepTutorialFragment fragment = (StepTutorialFragment) getFragmentManager().findFragmentByTag(StepTutorialFragment.class.getSimpleName());
        if(fragment != null) {
            getFragmentManager().beginTransaction()
                    .remove(fragment)
                    .commit();
        }
    }

    @Override
    public void destroyNextSkip() {

        if(getFragmentManager().findFragmentByTag(NextSkipFragment.class.getSimpleName()) != null) {
            getFragmentManager().beginTransaction()
                    .remove(getFragmentManager().findFragmentByTag(NextSkipFragment.class.getSimpleName()))
                    .commit();
        }
    }

    public void nextStepTutorial() {
        Log.i("app","nextStep");
        mainViewModel.nextFragmentEnable();
    }
}
