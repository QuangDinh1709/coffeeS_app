package gvn.project311.coffeeS.Example.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import gvn.project311.coffeeS.Example.Interface.ICallBack;
import gvn.project311.coffeeS.Example.ViewHolder.ViewHolderPhoto;
import gvn.project311.coffeeS.R;

/**
 * Created by admin on 10/10/17.
 */

public class GalleryAdapter extends BaseAdapter {
    private ArrayList<String> listUrlShow;
    private Context context;

    public GalleryAdapter(ArrayList<String> listUrlShow, Context context) {
        this.listUrlShow = listUrlShow;
        this.context = context;
    }

    public void addDataToList(ArrayList<String> listMore, ICallBack.IRefreshCallBack refresh ) {
        listUrlShow.addAll(listMore);
        notifyDataSetChanged();
        refresh.refreshCallBack();
    }

    @Override
    public int getCount() {
        return listUrlShow.size();
    }

    @Override
    public Object getItem(int i) {
        return listUrlShow.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        ViewHolderPhoto viewHolderPhoto = new ViewHolderPhoto(context);
        if(convertView == null) {
            Log.i("gridView","Create view position "+i);
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = (View) inflater.inflate(R.layout.row_photo_layout,null);
            viewHolderPhoto.initViewHolderPhoto(convertView);
            convertView.setTag(viewHolderPhoto);
        }
        else {
            Log.i("gridView","Reused view position "+i);
            viewHolderPhoto = (ViewHolderPhoto) convertView.getTag();
        }

        viewHolderPhoto.fillData(listUrlShow.get(i));
        return convertView;
    }
}
