package gvn.project311.coffeeS.Example.Utils;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import gvn.project311.coffeeS.Example.Fragment.TempFragment;
import gvn.project311.coffeeS.R;

/**
 * Created by admin on 10/9/17.
 */

public class PopUpHelper {


    public static void initPopUpHeler(String textNotice) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AndroidUtils.getCurrentActivity());
        View view = AndroidUtils.getCurrentActivity().getLayoutInflater().inflate(R.layout.pop_up_layout, null);

        TextView textView = (TextView) view.findViewById(R.id.text_error);
        textView.setText(textNotice.toString().trim());

        builder.setView(view);
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                Log.i("app", "setOnCancelListener");
            }
        });

        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

        Button button = (Button) view.findViewById(R.id.btn_complete_dialog);
        button.setText("Đóng");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

    public static void initPopUpHelper(ViewGroup view, String textButton, final FragmentTransaction transaction) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AndroidUtils.getCurrentActivity());
        builder.setView(view);

        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {

            }
        });

        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

        Button button = new Button(view.getContext());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //button.setLayoutParams(layoutParams);

        button.setGravity(Gravity.CENTER_HORIZONTAL);

        button.setText(textButton);
        button.setBackgroundColor(Color.parseColor("#59940a"));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                TempFragment fragment = new TempFragment();
                transaction
                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                        .replace(R.id.fragment_frame,fragment,fragment.getClass().getSimpleName())
                        //.addToBackStack(null)
                        .commit();
            }
        });
        view.addView(button);

    }

}
