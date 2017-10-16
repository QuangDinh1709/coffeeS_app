package gvn.project311.coffeeS.Example.FragmentComponent;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import gvn.project311.coffeeS.BaseFragment;
import gvn.project311.coffeeS.Example.Interface.ICallBack;
import gvn.project311.coffeeS.Example.Interface.INextSkip;
import gvn.project311.coffeeS.Example.ViewModel.Component.NextSkipViewModel;
import gvn.project311.coffeeS.Lifecycle;
import gvn.project311.coffeeS.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NextSkipFragment extends BaseFragment implements INextSkip.View, View.OnClickListener {

    private INextSkip.ViewModel nextViewModel;
    private int step;

    private ICallBack.ICommunication mCallBack;


    public NextSkipFragment() {

    }

    public static NextSkipFragment newInstance(int step) {
        NextSkipFragment nextSkipFragment = new NextSkipFragment();

        Bundle args = new Bundle();
        args.putInt("step",step);

        nextSkipFragment.setArguments(args);
        return nextSkipFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        step = getArguments() != null ? getArguments().getInt("step") : 0;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        nextViewModel = new NextSkipViewModel();

        Activity activity = context instanceof Activity ? (Activity) context : null;
        if(activity != null) {
            try {
                mCallBack = (ICallBack.ICommunication) activity;
            }
            catch (ClassCastException e)
            {
                Log.i("app",activity.toString() + " must implement ICallBack.ICommunication");
            }
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_next_skip, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        ImageView relativeLayoutNext = (ImageView) view.findViewById(R.id.layout_next_button);
        ImageView relativeLayoutSkip = (ImageView) view.findViewById(R.id.layout_skip_button);

        switch (step) {
            case 0:
                relativeLayoutNext.setVisibility(View.VISIBLE);
                relativeLayoutSkip.setVisibility(View.VISIBLE);

                relativeLayoutNext.setOnClickListener(this);
                relativeLayoutSkip.setOnClickListener(this);
                break;
            case 1:
                relativeLayoutNext.setVisibility(View.VISIBLE);
                relativeLayoutNext.setOnClickListener(this);
                break;
            case 2:
                relativeLayoutSkip.setVisibility(View.VISIBLE);
                relativeLayoutSkip.setOnClickListener(this);
                break;
        }

    }

    @Override
    protected Lifecycle.ViewModel getViewModel() {
        return nextViewModel;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_next_button: {
                Log.i("app", "Next button");
                getFragmentManager().popBackStackImmediate();
                mCallBack.deliverNextStepTutorial();
                break;
            }
            case R.id.layout_skip_button: {
                Log.i("app", "Skip button");
                getFragmentManager().popBackStackImmediate();
                mCallBack.deliverNextStepTutorial();
                break;
            }
        }
    }
}
