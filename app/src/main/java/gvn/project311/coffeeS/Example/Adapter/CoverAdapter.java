package gvn.project311.coffeeS.Example.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by admin on 10/12/17.
 */

public class CoverAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;

    public CoverAdapter(List<Fragment> fragments,FragmentManager fm) {
        super(fm);
        fragmentList = fragments;
    }

    public void setData(List<Fragment> fragments) {
        fragmentList = fragments;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
