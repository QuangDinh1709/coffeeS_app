package gvn.project311.coffeeS.Example.Application;

import android.app.Application;
import android.content.Context;

//import com.squareup.leakcanary.LeakCanary;
//import com.squareup.leakcanary.RefWatcher;

/**
 * Created by admin on 10/10/17.
 */

public class coffeeSApplication extends Application {
    private static Context context;
//    private RefWatcher refWatcher;

    @Override
    public void onCreate()
    {
        super.onCreate();
//        if(LeakCanary.isInAnalyzerProcess(this)) {
//            return;
//        }
//        refWatcher = LeakCanary.install(this);

        coffeeSApplication.context = getApplicationContext();
    }

//    public static RefWatcher getRefWatcher(Context context) {
//        coffeeSApplication application = (coffeeSApplication) context.getApplicationContext();
//        return application.refWatcher;
//    }

    public static Context getContext() {
        return coffeeSApplication.context;
    }
}
