package gvn.project311.coffeeS.Example.Fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;

import java.util.ArrayList;

import gvn.project311.coffeeS.BaseFragment;
import gvn.project311.coffeeS.Example.FragmentComponent.NextSkipFragment;
import gvn.project311.coffeeS.Example.Interface.ICallBack;
import gvn.project311.coffeeS.Example.Interface.IGallery;
import gvn.project311.coffeeS.Lifecycle;
import gvn.project311.coffeeS.Example.Utils.Constant;
import gvn.project311.coffeeS.Example.ViewModel.Component.GalleryViewModel;
import gvn.project311.coffeeS.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryImageFragment extends BaseFragment implements IGallery.View, View.OnTouchListener, SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {

    private IGallery.ViewModel galleryViewModel;

    private SwipeRefreshLayout swipeRefreshLayout;
    private GridView gridView;

    private int isShowCrop;

    private boolean isSelectedMultiple = false;

    private ICallBack.IGetData mCallBack;

    public GalleryImageFragment() {
        // Required empty public constructor
    }

    public static GalleryImageFragment newInstance(int showCropImage) {
        GalleryImageFragment galleryImageFragment = new GalleryImageFragment();

        Bundle args = new Bundle();
        args.putInt(Constant.SHOW_CROP_IMAGE,showCropImage);

        galleryImageFragment.setArguments(args);
        return galleryImageFragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        galleryViewModel = new GalleryViewModel(context);

        Activity activity = context instanceof Activity ? (Activity) context : null;
        if(activity != null) {
            try {
                mCallBack = (ICallBack.IGetData) activity;
            }
            catch (ClassCastException e)
            {
                Log.i("app",activity.toString() + " must implement ICallBack.ICommunication");
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isShowCrop = getArguments() != null ? getArguments().getInt(Constant.SHOW_CROP_IMAGE) : View.GONE;
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(getFragmentManager().findFragmentByTag(NextSkipFragment.class.getSimpleName()) != null) {
            getFragmentManager().beginTransaction()
                    .remove(getFragmentManager().findFragmentByTag(NextSkipFragment.class.getSimpleName()))
                    .commit();
        } //remove next fragment

        return inflater.inflate(R.layout.fragment_gallery_image, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_gallery);
        swipeRefreshLayout.setOnRefreshListener(this);

        gridView = (GridView) view.findViewById(R.id.gridView_Gallery);
        gridView.setOnTouchListener(this);
        gridView.setOnItemClickListener(this);

        FrameLayout frameLayoutCropView = (FrameLayout) view.findViewById(R.id.frame_cropImage);
        frameLayoutCropView.setVisibility(isShowCrop);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {

        menuInflater.inflate(R.menu.menu,menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_multiple:
                isSelectedMultiple = !isSelectedMultiple;
                galleryViewModel.refreshGridView();
                if(isSelectedMultiple)
                    item.setIcon(R.drawable.ic_action_selected_single);
                else
                    item.setIcon(R.drawable.ic_action_collections);
                break;
            case R.id.menu_next:
                mCallBack.getData(new ArrayList<>(galleryViewModel.getListItemSelected().values()));
                //getFragmentManager().popBackStackImmediate();
                getFragmentManager().beginTransaction()
                        .remove(getFragmentManager().findFragmentByTag(GalleryImageFragment.class.getSimpleName()))
                        .commit();
                break;
        }
        return false;
    }


    @Override
    protected Lifecycle.ViewModel getViewModel() {
        return galleryViewModel;
    }

    @Override
    public void setUpGripView(BaseAdapter adapter) {

        gridView.setAdapter(adapter);

        if(swipeRefreshLayout.isRefreshing())
        {
            refreshSwipeUp(false);
        }
    }

    @Override
    public void refreshSwipeUp(boolean refreshing) {
        swipeRefreshLayout.setRefreshing(refreshing);
    }

    @Override
    public void scrollTo(int child) {
        /* Todo :
            Need check agian;
        * */
        //Log.i("app","gridView.getChildCount() " +gridView.getChildCount() );
        //gridView.smoothScrollBy(gridView.getChildAt(gridView.getChildCount() - 5).getTop(), 1000);
    }

    @Override
    public boolean isSelectedMultipleItem() {
        return isSelectedMultiple;
    }

    @Override
    public void onRefresh() {
        galleryViewModel.getData();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(motionEvent.getAction() == android.view.MotionEvent.ACTION_UP) {

            if(gridView.getLastVisiblePosition() == gridView.getAdapter().getCount() - 1 &&
               gridView.getChildAt(gridView.getChildCount() - 1).getBottom()   <=  gridView.getHeight()) {

                refreshSwipeUp(true);

                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        galleryViewModel.getData();
                    }
                },2000);
            }
        }
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        galleryViewModel.highlightSelectedItem(adapterView,view,position);
//        Log.i("gridView","" +position);
        //view.setAlpha(Constant.TRANSPARENT);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        gridView.setOnTouchListener(null);
        gridView.setOnItemClickListener(null);
    }
}
