package gvn.project311.coffeeS.Example.ViewHolder;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.io.File;

import gvn.project311.coffeeS.Example.Utils.AndroidUtils;
import gvn.project311.coffeeS.R;

/**
 * Created by admin on 10/11/17.
 */

public class ViewHolderPhotoAlbum {

    private ImageView imageView ;
    private RelativeLayout relativeLayoutImageMore;
    private Context context;

    public ViewHolderPhotoAlbum(Context context) {
        this.context = context;
    }

    public void initViewHolderPhoto(View view) {

        imageView = (ImageView) view.findViewById(R.id.imageView_photo_ablum);
        imageView.setLayoutParams(new FrameLayout.LayoutParams(AndroidUtils.getScreenWidth()/3,AndroidUtils.getScreenHeight()/5));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        relativeLayoutImageMore= (RelativeLayout) view.findViewById(R.id.imageView_more);
        relativeLayoutImageMore.setLayoutParams(new FrameLayout.LayoutParams(AndroidUtils.getScreenWidth()/3,AndroidUtils.getScreenHeight()/5));
    }

    public void fillData(String file) {
        if(file != null) {
            Log.i("app","abc");
            imageView.setVisibility(View.VISIBLE);
            relativeLayoutImageMore.setVisibility(View.GONE);
            Picasso.with(context).load(new File(file)).placeholder(R.color.colorPlaceHolder).centerInside().resize(AndroidUtils.getScreenWidth() / 3, AndroidUtils.getScreenHeight() / 5).into(imageView);
        }
        else {
            imageView.setVisibility(View.GONE);
            relativeLayoutImageMore.setVisibility(View.VISIBLE);
        }
    }
}
