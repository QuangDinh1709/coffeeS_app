package gvn.project311.coffeeS.Example.Interface;

import android.support.v4.app.FragmentTransaction;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import gvn.project311.coffeeS.Lifecycle;

/**
 * Created by admin on 10/9/17.
 */

public interface IPhoto {
    interface View extends Lifecycle.View {
        void setAdapterForGrid(BaseAdapter adapter);
        void modifyLayoutGrid(int size);
    }

    interface ViewModel extends Lifecycle.ViewModel {
        void addData(ArrayList data);
        void showGalleryImage(FragmentTransaction fragmentTransaction);
    }
}
