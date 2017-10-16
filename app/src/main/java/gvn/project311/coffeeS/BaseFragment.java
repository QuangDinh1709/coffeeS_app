package gvn.project311.coffeeS;

import android.support.v4.app.Fragment;

//import com.squareup.leakcanary.RefWatcher;


/**
 * Created by admin on 10/5/17.
 */

public abstract class BaseFragment extends Fragment implements Lifecycle.View {

    protected abstract Lifecycle.ViewModel getViewModel();

    @Override
    public void onResume()
    {
        super.onResume();
        getViewModel().onViewResumed();
    }

    @Override
    public void onStart()
    {
        super.onStart();
        getViewModel().onViewAttached(this);
    }

    @Override
    public void onStop()
    {
        super.onStop();
        getViewModel().onViewDetached();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        RefWatcher refWatcher = coffeeSApplication.getRefWatcher(getActivity());
//        refWatcher.watch(this);
    }
}
