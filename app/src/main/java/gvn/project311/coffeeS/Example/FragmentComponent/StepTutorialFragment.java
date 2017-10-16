package gvn.project311.coffeeS.Example.FragmentComponent;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import gvn.project311.coffeeS.Lifecycle;
import gvn.project311.coffeeS.BaseFragment;
import gvn.project311.coffeeS.Example.Interface.ITutorialStep;
import gvn.project311.coffeeS.Example.ViewModel.Component.StepTutorialViewModel;
import gvn.project311.coffeeS.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepTutorialFragment extends BaseFragment implements ITutorialStep.View {

    private ITutorialStep.ViewModel stepViewModel;
    private int left, top, right, bottom, imageResource;


    public static StepTutorialFragment newInstance(int left, int top, int right, int bottom, int imageResource) {
        StepTutorialFragment  stepTutorialFragment = new StepTutorialFragment();

        Bundle args = new Bundle();
        args.putInt("left",left);
        args.putInt("top",top);
        args.putInt("right",right);
        args.putInt("bottom",bottom);
        args.putInt("imageResource",imageResource);
        stepTutorialFragment.setArguments(args);
        return stepTutorialFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.left = getArguments() != null ? getArguments().getInt("left") : 0;
        this.top = getArguments() != null ? getArguments().getInt("top") : 0;
        this.right = getArguments() != null ? getArguments().getInt("right") : 0;
        this.bottom = getArguments() != null ? getArguments().getInt("bottom") : 0;
        this.imageResource = getArguments() != null ? getArguments().getInt("imageResource") : 0;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        stepViewModel = new StepTutorialViewModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_step_tutorial, container, false);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        params.setMargins(left,top,right,bottom);
        view.setLayoutParams(params);

        ImageView imageViewTutorial = (ImageView) view.findViewById(R.id.imageViewTutorial);
        imageViewTutorial.setImageResource(imageResource);

        return view;
    }

    @Override
    protected Lifecycle.ViewModel getViewModel() {
        return stepViewModel;
    }

    @Override
    public void setEnableView(boolean enable) {
    }
}
