package gvn.project311.coffeeS.Example.ViewModel.Component;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import gvn.project311.coffeeS.Example.Adapter.GalleryAdapter;
import gvn.project311.coffeeS.Example.Interface.ICallBack;
import gvn.project311.coffeeS.Example.Interface.IGallery;
import gvn.project311.coffeeS.Example.Utils.AndroidUtils;
import gvn.project311.coffeeS.Example.Utils.Constant;
import gvn.project311.coffeeS.Lifecycle;
import gvn.project311.coffeeS.R;

/**
 * Created by admin on 10/10/17.
 */

public class GalleryViewModel implements IGallery.ViewModel {

    private IGallery.View viewCallBack;
    private GalleryAdapter galleryAdapter;
    private ArrayList<String> listAllImage = new ArrayList<>();
    private Map<Integer,String> mapItemSelected = new HashMap<>();

    private Context context;

    private boolean isGetMoreData = true; //true is get more can

    private int indexMax = Constant.MAX_DATA;
    private int indexMin = Constant.MIN_DATA;

    private boolean isFirstRun = true;

    public GalleryViewModel(Context context) {
        this.context = context;
        listAllImage = AndroidUtils.getAllShownImagesPath(AndroidUtils.getCurrentActivity());

        if(indexMax > listAllImage.size()) {
            indexMax = listAllImage.size();
            isGetMoreData = false;
        }
    }

    @Override
    public void onViewResumed() {
        if(isFirstRun) {
            viewCallBack.refreshSwipeUp(true);
            createAdapter();
            isFirstRun = false;
        }
    }

    @Override
    public void onViewAttached(@NonNull Lifecycle.View viewCallBack) {
        this.viewCallBack = (IGallery.View) viewCallBack;
    }

    @Override
    public void onViewDetached() {

    }

    @Override
    public void getData() {
        if(isGetMoreData) {
            galleryAdapter.addDataToList(createDataShow(), new ICallBack.IRefreshCallBack() {
                @Override
                public void refreshCallBack() {
                    viewCallBack.refreshSwipeUp(false);
                    viewCallBack.scrollTo((indexMax - indexMin) / 2);
                }
            });
        }
    }

    @Override
    public void highlightSelectedItem(AdapterView<?> adapterView, View view, int position) {

        if(viewCallBack.isSelectedMultipleItem()) {

            ImageView imageViewCheckBox = (ImageView) view.findViewById(R.id.imageView_checkBox);

            if(view.getTag(view.getId()) != null){
                view.setAlpha(Constant.NO_TRANSPARENT);
                view.setTag(view.getId(), null);
                imageViewCheckBox.setVisibility(View.GONE);
                mapItemSelected.put(position,null);
            }
            else {
                view.setAlpha(Constant.TRANSPARENT);
                view.setTag(view.getId(),position);

                imageViewCheckBox.setVisibility(View.VISIBLE);
                imageViewCheckBox.setImageResource(R.drawable.ic_check_box);
                mapItemSelected.put(position,listAllImage.get(position));
            }

        }
        else {
            for(int i = 0; i < adapterView.getChildCount(); i++)
            {
                adapterView.getChildAt(i).setAlpha(Constant.NO_TRANSPARENT);
            }
            view.setAlpha(Constant.TRANSPARENT);
            Log.i("app","listAllImage.get(position) "+listAllImage.get(position));
            mapItemSelected.put(0,listAllImage.get(position));
        }
    }

    @Override
    public void refreshGridView() {
        galleryAdapter.notifyDataSetChanged();
        viewCallBack.setUpGripView(galleryAdapter);
        mapItemSelected.clear();
    }

    @Override
    public Map<Integer, String> getListItemSelected() {

        for(Iterator<Map.Entry<Integer, String>> it = mapItemSelected.entrySet().iterator(); it.hasNext();)
        {
            Map.Entry<Integer,String> entry = it.next();
            if(entry.getValue() == null)
                it.remove();
        }

        return mapItemSelected;
    }

    private void createAdapter() {

        if(!isGetMoreData)
        {
            galleryAdapter = new GalleryAdapter(listAllImage,context);
        }
        else {
            galleryAdapter = new GalleryAdapter(createDataShow(),context);
        }

        viewCallBack.setUpGripView(galleryAdapter);
    }

    private ArrayList<String> createDataShow() {

        ArrayList<String> listDataShow = new ArrayList<>(indexMax);
        for(int i = indexMin ; i < indexMax ; i++) {
            listDataShow.add(listAllImage.get(i));
        }

        if(indexMax == listAllImage.size())
        {
            isGetMoreData = false;
        }
        else {
            increaseIndex();
        }
        return listDataShow;
    }

    private void increaseIndex() {

        indexMin = indexMax;
        indexMax += Constant.MORE_DATA;
        if(indexMax >= listAllImage.size())
            indexMax = listAllImage.size();

    }

}
