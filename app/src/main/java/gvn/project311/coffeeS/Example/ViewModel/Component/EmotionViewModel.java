package gvn.project311.coffeeS.Example.ViewModel.Component;

import android.support.annotation.NonNull;

import gvn.project311.coffeeS.Example.Interface.IEmotion;
import gvn.project311.coffeeS.Lifecycle;

/**
 * Created by admin on 10/6/17.
 */

public class EmotionViewModel implements IEmotion.ViewModel {

    private IEmotion.View viewCallBack;
    private boolean isFirstRun = true;

    public EmotionViewModel() {
    }

    @Override
    public void onViewResumed() {
        if(isFirstRun) {
            viewCallBack.setEnableView(false);
            isFirstRun = false;
        }
    }

    @Override
    public void onViewAttached(@NonNull Lifecycle.View viewCallBack) {
        this.viewCallBack = (IEmotion.View) viewCallBack;
    }

    @Override
    public void onViewDetached() {

    }
}
