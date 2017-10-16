package gvn.project311.coffeeS.Example.Interface;

import android.widget.AdapterView;
import android.widget.BaseAdapter;

import java.util.Map;

import gvn.project311.coffeeS.Lifecycle;

/**
 * Created by admin on 10/10/17.
 */

public interface IGallery {
    interface View extends Lifecycle.View {
        void setUpGripView(BaseAdapter adapter);
        void refreshSwipeUp(boolean refreshing);
        void scrollTo(int child);
        boolean isSelectedMultipleItem();
    }

    interface ViewModel extends Lifecycle.ViewModel {
        void getData();
        void highlightSelectedItem(AdapterView<?> adapterView, android.view.View view, int position);
        void refreshGridView();
        Map getListItemSelected();
    }
}
