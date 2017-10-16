package gvn.project311.coffeeS.Example.Fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import gvn.project311.coffeeS.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LogoFragment extends Fragment {


    public LogoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_logo, container, false);
        final ProgressBar progressBar = (ProgressBar)view.findViewById(R.id.progessBar_Logo);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);

                MainFragment mainFragment = new MainFragment();
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                        .replace(R.id.fragment_frame, mainFragment, mainFragment.getClass().getSimpleName())
                        //.addToBackStack(null)
                        .commit();
            }
        },3000);
        return view;
    }

}
