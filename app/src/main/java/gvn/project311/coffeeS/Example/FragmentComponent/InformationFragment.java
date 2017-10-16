package gvn.project311.coffeeS.Example.FragmentComponent;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import gvn.project311.coffeeS.BaseFragment;
import gvn.project311.coffeeS.Example.Interface.IInformation;
import gvn.project311.coffeeS.Example.Utils.UtilsView;
import gvn.project311.coffeeS.Example.ViewModel.Component.InformationViewModel;
import gvn.project311.coffeeS.Lifecycle;
import gvn.project311.coffeeS.Example.Utils.Constant;
import gvn.project311.coffeeS.Example.Utils.CustomWatcher;
import gvn.project311.coffeeS.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InformationFragment extends BaseFragment implements IInformation.View, View.OnClickListener {

    private IInformation.ViewModel informationViewModel;
    private View view;
    private EditText editTextDiaChi, editTextDienThoai, editTextMoCua, editTextDongCua;

    public InformationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        informationViewModel = new InformationViewModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_information, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        editTextDiaChi = (EditText) view.findViewById(R.id.editText_DiaChi);
        editTextDienThoai = (EditText) view.findViewById(R.id.editText_DienThoai);
        editTextMoCua = (EditText) view.findViewById(R.id.editText_MoCua);
        editTextDongCua = (EditText) view.findViewById(R.id.editText_DongCua);

        editTextMoCua.setOnClickListener(this);
        editTextMoCua.getBackground().clearColorFilter();

        editTextDongCua.setOnClickListener(this);
        editTextDongCua.getBackground().clearColorFilter();

        editTextDiaChi.addTextChangedListener(new CustomWatcher());
        editTextDiaChi.getBackground().clearColorFilter();

        editTextDienThoai.addTextChangedListener(new CustomWatcher());
        editTextDienThoai.setInputType(InputType.TYPE_CLASS_PHONE);
        editTextDienThoai.getBackground().clearColorFilter();
    }

    @Override
    protected Lifecycle.ViewModel getViewModel() {
        return informationViewModel;
    }

    @Override
    public void setEnableView(boolean enable) {

        if(enable)
            view.setAlpha(Constant.NO_TRANSPARENT);
        else
            view.setAlpha(Constant.TRANSPARENT);
        UtilsView.enableDisableViewGroup((ViewGroup) view,enable);
    }

    @Override
    public void onClick(View view) {

        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);

        switch (view.getId()) {
            case R.id.editText_DongCua:
                informationViewModel.handleTimePicker(editTextDongCua, getContext());
                break;
            case R.id.editText_MoCua:
                informationViewModel.handleTimePicker(editTextMoCua, getContext());
                break;
        }
    }

    public boolean checkEditText() {
        return UtilsView.validateEditText(new EditText[] {editTextDienThoai,editTextDiaChi,editTextDongCua,editTextMoCua});
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        editTextMoCua.setOnClickListener(null);
        editTextDongCua.setOnClickListener(null);

        editTextDiaChi.removeTextChangedListener(new CustomWatcher());
        editTextDienThoai.removeTextChangedListener(new CustomWatcher());

    }
}
