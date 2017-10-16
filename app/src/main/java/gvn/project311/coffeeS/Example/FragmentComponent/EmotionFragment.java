package gvn.project311.coffeeS.Example.FragmentComponent;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gvn.project311.coffeeS.BaseFragment;
import gvn.project311.coffeeS.Example.Interface.IEmotion;
import gvn.project311.coffeeS.Example.Utils.Constant;
import gvn.project311.coffeeS.Example.Utils.UtilsView;
import gvn.project311.coffeeS.Example.ViewModel.Component.EmotionViewModel;
import gvn.project311.coffeeS.Lifecycle;
import gvn.project311.coffeeS.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmotionFragment extends BaseFragment implements IEmotion.View {

    private IEmotion.ViewModel emotionViewModel;
    private View view;

    public EmotionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        emotionViewModel = new EmotionViewModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_emotion, container, false);
        return view;
    }

    @Override
    protected Lifecycle.ViewModel getViewModel() {
        return emotionViewModel;
    }

    @Override
    public void setEnableView(boolean enable) {
        if(enable)
            view.setAlpha(Constant.NO_TRANSPARENT);
        else
            view.setAlpha(Constant.TRANSPARENT);
        UtilsView.enableDisableViewGroup((ViewGroup) view,enable);
    }
}
