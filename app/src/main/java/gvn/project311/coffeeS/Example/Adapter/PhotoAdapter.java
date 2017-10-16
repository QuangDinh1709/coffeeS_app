package gvn.project311.coffeeS.Example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import gvn.project311.coffeeS.Example.Interface.ICallBack;
import gvn.project311.coffeeS.Example.ViewHolder.ViewHolderPhotoAlbum;
import gvn.project311.coffeeS.R;

/**
 * Created by admin on 10/11/17.
 */

public class PhotoAdapter extends BaseAdapter {
    private ArrayList<String> listPhoto;
    private Context context;

    public PhotoAdapter(ArrayList<String> listPhoto, Context context) {
        this.listPhoto = listPhoto;
        this.context = context;
    }

    public void addMoreData(ArrayList<String> moreData, ICallBack.IRefreshCallBack callBack) {
        listPhoto.remove(listPhoto.size() - 1);
        listPhoto.addAll(moreData);
        notifyDataSetChanged();
        callBack.refreshCallBack();
    }

    @Override
    public int getCount() {
        return listPhoto.size();
    }

    @Override
    public Object getItem(int i) {
        return listPhoto.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        ViewHolderPhotoAlbum viewHolderPhoto = new ViewHolderPhotoAlbum(context);

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = (View) inflater.inflate(R.layout.row_photo_album_layout, null);
            viewHolderPhoto.initViewHolderPhoto(convertView);
            convertView.setTag(viewHolderPhoto);
        }
        else {
            viewHolderPhoto = (ViewHolderPhotoAlbum) convertView.getTag();
        }

        viewHolderPhoto.fillData(listPhoto.get(i));
        return convertView;
    }
}
