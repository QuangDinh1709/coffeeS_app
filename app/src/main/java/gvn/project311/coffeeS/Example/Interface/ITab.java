package gvn.project311.coffeeS.Example.Interface;

import gvn.project311.coffeeS.Lifecycle;

/**
 * Created by admin on 10/9/17.
 */

public interface ITab {
    interface View extends Lifecycle.View {
        void setEnableView(boolean enable);
        void setEnableTab(int tabId);
    }
    interface ViewModel extends Lifecycle.ViewModel {
    }
}
