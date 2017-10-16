package gvn.project311.coffeeS.Example.Interface;

import gvn.project311.coffeeS.Lifecycle;

/**
 * Created by admin on 10/6/17.
 */

public interface IEmotion {
    interface View extends Lifecycle.View {
        void setEnableView(boolean enable);

    }
    interface ViewModel extends Lifecycle.ViewModel {

    }
}
