package com.wordpress.lonelytripblog.customkeyboardwitht9;

import android.app.Activity;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.wordpress.lonelytripblog.customkeyboardwitht9.data.Contact;

import java.util.List;

public class KeyboardViewActivity extends AppCompatActivity implements Contract.View, KeyboardView.OnKeyboardActionListener {

    private KeyboardView keyboardView;
    private ArrayAdapter<Contact> mArrayAdapter;
    private Contract.Presenter mPresenter;
    private EditText editText;

    @Override
    public void onBackPressed() {
        if (keyboardView.getVisibility() == View.VISIBLE) {
            hideCustomKeyboard();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard_view);
        mPresenter = new InputHandler(this);
        Keyboard keyboard = new Keyboard(this, R.xml.custom_keyboard);
        ListView contactsList = (ListView) findViewById(R.id.contacts_list);
        mArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        contactsList.setAdapter(mArrayAdapter);
        keyboardView = (KeyboardView) findViewById(R.id.keyboard_view);
        keyboardView.setKeyboard(keyboard);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        editText = (EditText) findViewById(R.id.input);
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!(keyboardView.getVisibility() == View.VISIBLE)) {
                    openCustomKeyboard(view);
                }
                return true;
            }
        });
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    openCustomKeyboard(view);
                    mPresenter.requestContacts(editText.getText().toString());
                } else {
                    hideCustomKeyboard();
                }
            }
        });
        keyboardView.setOnKeyboardActionListener(this);
        keyboardView.setPreviewEnabled(false);
        ImageButton clearButton = (ImageButton) findViewById(R.id.clear_btn);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Editable editable = editText.getText();
                int start = editText.getSelectionStart();
                if (start > 0) {
                    editable.replace(start - 1, start, "");
                }
            }
        });
        clearButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                editText.getText().clear();
                return true;
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mPresenter.requestContacts(editable.toString());
            }
        });
    }



    @Override
    public void displayRequestedContacts(List<Contact> data) {
        mArrayAdapter.clear();
        mArrayAdapter.addAll(data);
    }

    @Override
    public void displayEmptyView() {
        mArrayAdapter.clear();
    }

    public void hideCustomKeyboard() {
        if (keyboardView != null) {
            keyboardView.setVisibility(View.GONE);
            keyboardView.setEnabled(false);
        }
    }

    public void openCustomKeyboard(View view) {
        keyboardView.setVisibility(View.VISIBLE);
        keyboardView.setEnabled(true);
        if (view != null) {
            ( (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE) ).
                    hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onPress(int i) {

    }

    @Override
    public void onRelease(int i) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        View focusCurrent = getWindow().getCurrentFocus();
        if( (focusCurrent == null) || focusCurrent.getClass()!=AppCompatEditText.class ) return;
        EditText editText = (EditText) focusCurrent;
        Editable editable = editText.getText();
        int start = editText.getSelectionStart();
        editable.insert(start, Character.toString((char) primaryCode));
    }

    @Override
    public void onText(CharSequence charSequence) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}
