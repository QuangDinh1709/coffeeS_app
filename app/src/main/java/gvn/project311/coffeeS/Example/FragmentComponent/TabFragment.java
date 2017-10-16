package gvn.project311.coffeeS.Example.FragmentComponent;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import gvn.project311.coffeeS.BaseFragment;
import gvn.project311.coffeeS.Example.Utils.Constant;
import gvn.project311.coffeeS.Example.Utils.UtilsView;
import gvn.project311.coffeeS.Example.ViewModel.Component.TabViewModel;
import gvn.project311.coffeeS.Lifecycle;
import gvn.project311.coffeeS.Example.Interface.ITab;
import gvn.project311.coffeeS.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment extends BaseFragment implements ITab.View {

    private ITab.ViewModel tabViewModel;
    private View view;

    private FragmentTabHost fragmentTabHost;

    public TabFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        tabViewModel = new TabViewModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        fragmentTabHost = (FragmentTabHost) view.findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(getActivity(),getFragmentManager(), R.id.tab_content);

        View viewTabSpecDesc = LayoutInflater.from(getContext()).inflate(R.layout.tab_spec,null);
        TextView textViewDesc = (TextView) viewTabSpecDesc.findViewById(R.id.text_specTab);
        textViewDesc.setText("Giới Thiệu");

        View viewTabSpecMenu = LayoutInflater.from(getContext()).inflate(R.layout.tab_spec,null);
        TextView textViewMenu = (TextView) viewTabSpecMenu.findViewById(R.id.text_specTab);
        textViewMenu.setText("Thực Đơn");

        View viewTabSpecAlbum = LayoutInflater.from(getContext()).inflate(R.layout.tab_spec,null);
        TextView textViewAlbum = (TextView) viewTabSpecAlbum.findViewById(R.id.text_specTab);
        textViewAlbum.setText("Hình Ảnh");

        fragmentTabHost.addTab(fragmentTabHost.newTabSpec(DescriptionFragment.class.getSimpleName()).setIndicator(viewTabSpecDesc), DescriptionFragment.class, null);
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec(MenuFragment.class.getSimpleName()).setIndicator(viewTabSpecMenu), MenuFragment.class, null);
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec(PhotoFragment.class.getSimpleName()).setIndicator(viewTabSpecAlbum), PhotoFragment.class, null);
    }

    @Override
    protected Lifecycle.ViewModel getViewModel() {
        return tabViewModel;
    }

    @Override
    public void setEnableView(boolean enable) {

        if(enable)
            view.setAlpha(Constant.NO_TRANSPARENT);
        else
            view.setAlpha(Constant.TRANSPARENT);
        UtilsView.enableDisableViewGroup((ViewGroup) view,enable);
    }

    @Override
    public void setEnableTab(int tabId) {
        fragmentTabHost.setCurrentTab(tabId);
        fragmentTabHost.getTabWidget().setEnabled(false);
        setUpView();
    }

    public void setUpView() {

        if(getFragmentManager().findFragmentByTag(NextSkipFragment.class.getSimpleName()) !=  null) {
            getFragmentManager().beginTransaction()
                    .remove(getFragmentManager().findFragmentByTag(NextSkipFragment.class.getSimpleName()))
                    .commit();
        }

        Fragment fragmentNextSkip = NextSkipFragment.newInstance(0);
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                .add(R.id.frame_tab_over,fragmentNextSkip,fragmentNextSkip.getClass().getSimpleName())
                //.addToBackStack(null)
                .commit();
    }
}
