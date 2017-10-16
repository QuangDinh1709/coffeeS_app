package gvn.project311.coffeeS.Example.ViewModel.Component;

import android.support.annotation.NonNull;

import gvn.project311.coffeeS.Example.Interface.ITab;
import gvn.project311.coffeeS.Lifecycle;

/**
 * Created by admin on 10/9/17.
 */

public class TabViewModel implements ITab.ViewModel {

    private ITab.View viewCallBack;
    private boolean isFirstRun = true;

    @Override
    public void onViewResumed() {

        if ((viewCallBack != null)) {
            if(isFirstRun) {
                viewCallBack.setEnableView(false);
                isFirstRun = false;
            }
        }
    }

    @Override
    public void onViewAttached(@NonNull Lifecycle.View viewCallBack) {
        this.viewCallBack = (ITab.View) viewCallBack;

    }

    @Override
    public void onViewDetached() {

    }
}
