package gvn.project311.coffeeS;

import android.support.annotation.NonNull;

/**
 * Created by admin on 10/5/17.
 */

public interface Lifecycle {
    interface View {
    }

    /*
    * The View is going to call
    *  + Lifecycle.ViewModel#onViewResumed() in its onResume() method.
    *  + Lifecycle.ViewModel#onViewAttached(this) in the onStart()
    *  + Lifecycle.ViewModel#onViewDetached() in onDestroy()
    * */

    interface ViewModel {
        void onViewResumed();
        void onViewAttached(@NonNull Lifecycle.View viewCallBack);
        void onViewDetached();
    }
}
