package gvn.project311.coffeeS.Example.Interface;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;

import java.util.List;

import gvn.project311.coffeeS.Lifecycle;

/**
 * Created by admin on 10/6/17.
 */

public interface ICover {
    interface View extends Lifecycle.View {
        void setEnableView(boolean enable);
        void setUpView();
        void setUpViewPager(PagerAdapter baseAdapter);
    }
    interface ViewModel extends Lifecycle.ViewModel {
        void showGalleryImage(FragmentTransaction fragmentTransaction);
        void setData(List<String> data);
    }
}
