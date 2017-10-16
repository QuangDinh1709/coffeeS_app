package gvn.project311.coffeeS.Example.Interface;

import gvn.project311.coffeeS.Lifecycle;

/**
 * Created by admin on 10/6/17.
 */

public interface IMain {
    interface View extends Lifecycle.View {
        void setupView();
        void setUpViewNextSkip();
        void scrollTo(int childAt);
        void setUpTipView(int left, int top, int right, int bottom, int res);
        void destroyTipView();
        void destroyNextSkip();
    }

    interface ViewMode extends Lifecycle.ViewModel {
        void nextFragmentEnable();
    }
}
