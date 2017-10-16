package gvn.project311.coffeeS.Example.ViewHolder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.io.File;

import gvn.project311.coffeeS.Example.Utils.AndroidUtils;
import gvn.project311.coffeeS.R;

/**
 * Created by admin on 10/10/17.
 */

public class ViewHolderPhoto {

    private ImageView imageView ;
    private Context context;

    public ViewHolderPhoto(Context context) {
        this.context = context;
    }

    public void initViewHolderPhoto(View view) {
        imageView = (ImageView) view.findViewById(R.id.imageView_photo);
        imageView.setVisibility(View.VISIBLE);
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(AndroidUtils.getScreenWidth()/3,AndroidUtils.getScreenHeight()/5));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    public void fillData(String file) {
        Picasso.with(context).load(new File(file)).placeholder(R.color.colorPlaceHolder).centerInside().resize(AndroidUtils.getScreenWidth()/3,AndroidUtils.getScreenHeight()/5).into(imageView);
    }

}
