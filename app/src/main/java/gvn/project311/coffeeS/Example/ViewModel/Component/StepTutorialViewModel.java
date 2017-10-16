package gvn.project311.coffeeS.Example.ViewModel.Component;

import android.support.annotation.NonNull;

import gvn.project311.coffeeS.Example.Interface.ITutorialStep;
import gvn.project311.coffeeS.Lifecycle;

/**
 * Created by admin on 10/6/17.
 */

public class StepTutorialViewModel implements ITutorialStep.ViewModel {
    private ITutorialStep.View viewCallBack;

    public StepTutorialViewModel() {
    }

    @Override
    public void onViewResumed() {
        //viewCallBack.setEnableView(false);
    }

    @Override
    public void onViewAttached(@NonNull Lifecycle.View viewCallBack) {
        this.viewCallBack = (ITutorialStep.View) viewCallBack;
    }

    @Override
    public void onViewDetached() {

    }
}
