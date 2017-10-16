package gvn.project311.coffeeS.Example.FragmentComponent;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

import gvn.project311.coffeeS.Example.Utils.Constant;
import gvn.project311.coffeeS.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment {

    private String pathImage;

    public ImageFragment() {
        // Required empty public constructor
    }

    public static ImageFragment newInstance(String pathImage) {
        ImageFragment imageFragment = new ImageFragment();

        Bundle args = new Bundle();
        args.putString(Constant.FRAGMENT_IMAGE_PATH,pathImage);

        imageFragment.setArguments(args);
        return imageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pathImage = getArguments() != null ? getArguments().getString(Constant.FRAGMENT_IMAGE_PATH) : null;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        if(pathImage != null) {
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView_Fragment);
            Picasso.with(getContext()).load(new File(pathImage)).fit().into(imageView);
        }
        return view;
    }

}
