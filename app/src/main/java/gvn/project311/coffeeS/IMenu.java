package gvn.project311.coffeeS;

import android.view.ViewGroup;

/**
 * Created by admin on 10/5/17.
 */

public interface IMenu {
    interface View extends Lifecycle.View {
        void setUpView();
        void showProgessBar();
    }

    interface ViewModel extends Lifecycle.ViewModel {
        void addNewAMenu(ViewGroup parent);
        void addNewARowMenu(ViewGroup parent, int numberRow);
    }
}
