package gvn.project311.coffeeS.Example.ViewModel.Component;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gvn.project311.coffeeS.ICallBack;
import gvn.project311.coffeeS.IMenu;
import gvn.project311.coffeeS.Lifecycle;
import gvn.project311.coffeeS.MenuModel;
import gvn.project311.coffeeS.R;

/**
 * Created by admin on 10/5/17.
 */

public class MenuViewModel implements IMenu.ViewModel {

    private LayoutInflater inflater;
    private  IMenu.View viewCallBack;
    private Context context;

    private Map<Integer,List<MenuModel>> mapMenus;
    private List<MenuModel> menuModels;

    private boolean isFristRun = true;

    private int order = -1;

    public MenuViewModel(Context context) {
        this.context = context;
        mapMenus = new HashMap<>();
    }


    @Override
    public void onViewResumed() {
        Log.i("app","onViewResumed ");
        if(this.viewCallBack != null) {
            if(isFristRun) {
                this.viewCallBack.setUpView();
                isFristRun = false;
            }
        }
    }

    @Override
    public void onViewAttached(@NonNull Lifecycle.View viewCallBack) {

        this.viewCallBack = (IMenu.View) viewCallBack;
        this.inflater = LayoutInflater.from(context);


    }

    @Override
    public void onViewDetached() {
        Log.i("app","stop menu list");
        for(Map.Entry<Integer,List<MenuModel>> entry : mapMenus.entrySet()) {
            List<MenuModel> menuModels = entry.getValue();
            for(MenuModel menuModel : menuModels) {
                Log.i("app"," order id " + entry.getKey() + " special " + menuModel.isSpecial()+ " name "+ menuModel.getName()+" price "+menuModel.getPrice());
            }
        }
    }

    @Override
    public void addNewAMenu(ViewGroup parent) {
        LinearLayout child = (LinearLayout) inflater.inflate(R.layout.layout_menu,null,false);
        if(child != null) {
            order ++; //New a menu
            menuModels = new ArrayList<>();

            parent.addView(child);
            final LinearLayout linearLayoutParent = (LinearLayout) child.findViewById(R.id.layout_menu);

            addNewARowMenu(linearLayoutParent,3);

            LinearLayout linearLayoutAdd = (LinearLayout) child.findViewById(R.id.add_new_menu);
            linearLayoutAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addNewARowMenu(linearLayoutParent,1);
                }
            });
        }
    }

    @Override
    public void addNewARowMenu(final ViewGroup parent, int num) {
        for(int i = 0 ;i < num; i++) {
            TableLayout child = (TableLayout) inflater.inflate(R.layout.menu, null, false);
            if (child != null) {
                final MenuModel menuModel = new MenuModel();
                parent.addView(child);
                //parent.ge
                HandleRowMenu handleRowMenu = new HandleRowMenu(menuModel, child, new ICallBack() {
                    @Override
                    public void removeChild(View child, int index) {
                        //Log.i("app","indexs "+parent.getChildCount() +" index delete " +index);

                        if(parent != null) {
                            parent.removeView(child);
                            //parent.removeViewAt(index);
                            menuModel.setIs_vaild(false);
                            viewCallBack.showProgessBar();
                        }
                    }
                }, parent.getChildCount() - 1);

                handleRowMenu.setEventForView();
                menuModels.add(menuModel);
            }
        }
        mapMenus.put(order,menuModels);
    }

    class HandleRowMenu implements View.OnClickListener, TextWatcher {

        private ImageView imageViewSpecial;
        private EditText  editTextName;
        private EditText  editTextPrice;
        private ImageView imageViewDelete;
        private MenuModel menuModel;

        private Handler handler = new Handler();
        private Runnable runnable;

        private View view;
        private ICallBack iCallBack;

        public HandleRowMenu(MenuModel menuModel, View view, ICallBack iCallBack, int indexView) {
            this.menuModel = menuModel;
            this.imageViewSpecial = (ImageView)view.findViewById(R.id.image_view_is_special);
            this.editTextName = (EditText) view.findViewById(R.id.edit_text_name_menu);
            this.editTextPrice = (EditText) view.findViewById(R.id.edit_text_price_menu);
            this.imageViewDelete = (ImageView) view.findViewById(R.id.image_view_delete);
            this.iCallBack = iCallBack;
            this.view = view;
            //Log.i("app","indexView "+ this.indexView);
        }

        public void setEventForView() {
            imageViewSpecial.setOnClickListener(this);
            imageViewDelete.setOnClickListener(this);
            editTextName.addTextChangedListener(this);
            editTextPrice.addTextChangedListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.image_view_is_special: {
                    boolean isSpecial = menuModel.isSpecial();
                    menuModel.setSpecial(!isSpecial);
                    break;
                }
                case R.id.image_view_delete: {
                    Log.i("app","remove a menu");

                    iCallBack.removeChild(this.view, 0);
                    break;
                }
            }
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(final Editable editable) {
            Log.i("app","afterTextChanged editable "+editable.toString());

                runnable = new Runnable() {
                    @Override
                    public void run() {
                        if (editTextName.getEditableText() ==  editable ) {
                            Log.i("app", "afterTextChanged Name " + editable.toString());
                            menuModel.setName(editable.toString());
                        } else if (editTextPrice.getEditableText() == editable) {
                            Log.i("app", "afterTextChanged price " + editable.toString());
                            menuModel.setPrice(editable.toString());
                        }
                    }
                };
                handler.postDelayed(runnable,500);
        }
    }
}
