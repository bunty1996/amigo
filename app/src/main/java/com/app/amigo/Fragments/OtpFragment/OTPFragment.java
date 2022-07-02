package com.app.amigo.Fragments.OtpFragment;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.app.amigo.Fragments.OtpFragment.Presenter.OtpPresenter;
import com.app.amigo.Fragments.OtpFragment.View.OtpView;
import com.app.amigo.R;
import com.app.amigo.Utils.Utils;


public class OTPFragment extends Fragment implements View.OnClickListener, OtpView {

    private Activity activity;
    private View view;
    private EditText et_code1, et_code2, et_code3, et_code4;
    private EditText[] editTexts;
    private String code;
    private ImageView img_next;
    private OtpPresenter otpPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_o_t_p, container, false);
        activity = getActivity();
        init();
        listeners();

        otpPresenter = new OtpPresenter(activity, this);

        editTexts = new EditText[]{et_code1, et_code2, et_code3, et_code4};

        et_code1.addTextChangedListener(new PinTextWatcher(0));
        et_code2.addTextChangedListener(new PinTextWatcher(1));
        et_code3.addTextChangedListener(new PinTextWatcher(2));
        et_code4.addTextChangedListener(new PinTextWatcher(3));

        et_code1.setOnKeyListener(new PinOnKeyListener(0));
        et_code2.setOnKeyListener(new PinOnKeyListener(1));
        et_code3.setOnKeyListener(new PinOnKeyListener(2));
        et_code4.setOnKeyListener(new PinOnKeyListener(3));

        return view;
    }


    private void init() {
        img_next = view.findViewById(R.id.img_next);
        et_code1 = view.findViewById(R.id.et_code1);
        et_code2 = view.findViewById(R.id.et_code2);
        et_code3 = view.findViewById(R.id.et_code3);
        et_code4 = view.findViewById(R.id.et_code4);

    }

    private void listeners() {
        img_next.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == img_next) {

            code = et_code1.getText().toString().trim() + et_code2.getText().toString().trim() + et_code3.getText().toString().trim() + et_code4.getText().toString().trim();

            if (!(code.equalsIgnoreCase(""))) {
                otpPresenter.otpmethod(code);
            }


        }
    }

    @Override
    public void showDialog(Activity activity) {
        Utils.showDialogMethod(activity, activity.getFragmentManager());


    }

    @Override
    public void hideDialog() {
        Utils.hideDialog();

    }

    @Override
    public void showMessage(Activity activity, String msg) {
        Utils.showMessage(activity, msg);


    }

    public class PinTextWatcher implements TextWatcher {

        private int currentIndex;
        private boolean isFirst = false, isLast = false;
        private String newTypedString = "";

        PinTextWatcher(int currentIndex) {
            this.currentIndex = currentIndex;

            if (currentIndex == 0)
                this.isFirst = true;
            else if (currentIndex == editTexts.length - 1)
                this.isLast = true;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            newTypedString = s.subSequence(start, start + count).toString().trim();
        }

        @Override
        public void afterTextChanged(Editable s) {

            String text = newTypedString;

            /* Detect paste event and set first char */
            if (text.length() > 1)
                text = String.valueOf(text.charAt(0)); // TODO: We can fill out other EditTexts

            editTexts[currentIndex].removeTextChangedListener(this);
            editTexts[currentIndex].setText(text);
            editTexts[currentIndex].setSelection(text.length());
            editTexts[currentIndex].addTextChangedListener(this);

            if (text.length() == 1)
                moveToNext();
            else if (text.length() == 0)
                moveToPrevious();
        }

        private void moveToNext() {
            if (!isLast)
                editTexts[currentIndex + 1].requestFocus();

            if (isAllEditTextsFilled() && isLast) { // isLast is optional
                editTexts[currentIndex].clearFocus();
                hideKeyboard();
            }
        }

        private void moveToPrevious() {
            if (!isFirst)
                editTexts[currentIndex - 1].requestFocus();
        }

        private boolean isAllEditTextsFilled() {
            for (EditText editText : editTexts)
                if (editText.getText().toString().trim().length() == 0)
                    return false;
            return true;
        }

        private void hideKeyboard() {
            if (getActivity().getCurrentFocus() != null) {
                InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
            }
        }

    }

    public class PinOnKeyListener implements View.OnKeyListener {

        private int currentIndex;

        PinOnKeyListener(int currentIndex) {
            this.currentIndex = currentIndex;
        }

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                if (editTexts[currentIndex].getText().toString().isEmpty() && currentIndex != 0)
                    editTexts[currentIndex - 1].requestFocus();
            }
            return false;
        }
    }
}