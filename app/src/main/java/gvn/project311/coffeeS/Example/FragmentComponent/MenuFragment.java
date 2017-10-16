package gvn.project311.coffeeS.Example.FragmentComponent;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import gvn.project311.coffeeS.BaseFragment;
import gvn.project311.coffeeS.IMenu;
import gvn.project311.coffeeS.Lifecycle;
import gvn.project311.coffeeS.Example.ViewModel.Component.MenuViewModel;
import gvn.project311.coffeeS.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends BaseFragment implements View.OnClickListener, IMenu.View {

    private IMenu.ViewModel menuViewModel;

    private RelativeLayout layoutAddMenu ;
    private LinearLayout layoutAllMenu ;
    private ProgressBar progressBar;

    public MenuFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        menuViewModel = new MenuViewModel(getContext());
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        layoutAddMenu = (RelativeLayout) view.findViewById(R.id.layout_add_menu);
        layoutAddMenu.setOnClickListener(this);
        layoutAllMenu = (LinearLayout) view.findViewById(R.id.layout_all_menu);

        progressBar = (ProgressBar) view.findViewById(R.id.progessBar);
    }

    @Override
    public void onClick(View view) {
        Log.i("app","view id "+view.getId());
        switch (view.getId()) {
            case R.id.layout_add_menu:
            {
                menuViewModel.addNewAMenu(layoutAllMenu);
                break;
            }
        }
    }

    @Override
    protected Lifecycle.ViewModel getViewModel() {
        return menuViewModel;
    }


    @Override
    public void setUpView() {
        menuViewModel.addNewAMenu(layoutAllMenu);
    }

    @Override
    public void showProgessBar() {
        progressBar.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        };
        handler.postDelayed(runnable,500);
    }

}
