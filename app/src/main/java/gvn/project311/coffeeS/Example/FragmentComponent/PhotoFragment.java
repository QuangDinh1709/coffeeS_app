package gvn.project311.coffeeS.Example.FragmentComponent;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;

import java.util.ArrayList;

import gvn.project311.coffeeS.Example.Utils.AndroidUtils;
import gvn.project311.coffeeS.Example.ViewModel.Component.PhotoViewModel;
import gvn.project311.coffeeS.Lifecycle;
import gvn.project311.coffeeS.BaseFragment;
import gvn.project311.coffeeS.Example.Interface.ICallBack;
import gvn.project311.coffeeS.Example.Interface.IPhoto;
import gvn.project311.coffeeS.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoFragment extends BaseFragment implements IPhoto.View, AdapterView.OnItemClickListener {

    private IPhoto.ViewModel photoViewModel;
    private GridView gridView;

    private int heightGridView;

    public PhotoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        photoViewModel = new PhotoViewModel(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photo, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        gridView  = (GridView) view.findViewById(R.id.gridview_photo);
        gridView.setOnItemClickListener(this);
    }

    @Override
    protected Lifecycle.ViewModel getViewModel() {
        return photoViewModel;
    }

    @Override
    public void setAdapterForGrid(BaseAdapter adapter) {
        gridView.setAdapter(adapter);
        AndroidUtils.getHeightView(gridView, new ICallBack.IGetDataInteger() {
            @Override
            public void getData(int data) {
                heightGridView = data;
            }
        });
    }

    @Override
    public void modifyLayoutGrid(int size) {

        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) gridView.getLayoutParams();
        layoutParams.width = FrameLayout.LayoutParams.MATCH_PARENT;
        layoutParams.height = size * heightGridView;
        gridView.setLayoutParams(layoutParams);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        photoViewModel.showGalleryImage(getFragmentManager().beginTransaction());
    }

    public void setData(ArrayList<String> data) {
        photoViewModel.addData(data);
    }
}
