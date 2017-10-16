package gvn.project311.coffeeS.Example.Interface;

import java.util.ArrayList;

/**
 * Created by admin on 10/9/17.
 */

public interface ICallBack {
    interface ICommunication {
        void deliverNextStepTutorial();
    }

    interface IRefreshCallBack {
        void refreshCallBack();
    }

    interface IGetData {
        void getData(ArrayList data);
    }

    interface IGetDataInteger{
        void getData(int data);
    }
}
