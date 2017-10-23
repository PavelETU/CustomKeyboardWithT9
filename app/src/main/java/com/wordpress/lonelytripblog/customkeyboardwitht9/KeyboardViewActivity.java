package com.wordpress.lonelytripblog.customkeyboardwitht9;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
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
import com.wordpress.lonelytripblog.customkeyboardwitht9.utils.CustomArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class KeyboardViewActivity extends AppCompatActivity implements Contract.View {

    private KeyboardView keyboardView;
    private ArrayAdapter<Contact> mArrayAdapter;
    private Contract.Presenter mPresenter;
    private static final int MY_PERMISSION_READ_CONTACTS = 115;

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
        requestPermission();
        Keyboard keyboard = new Keyboard(this, R.xml.custom_keyboard);
        ListView contactsList = (ListView) findViewById(R.id.contacts_list);
        mArrayAdapter = new CustomArrayAdapter(this, new ArrayList<Contact>());
        contactsList.setAdapter(mArrayAdapter);
        keyboardView = (KeyboardView) findViewById(R.id.keyboard_view);
        keyboardView.setKeyboard(keyboard);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        final EditText userInput = (EditText) findViewById(R.id.input);
        userInput.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!(keyboardView.getVisibility() == View.VISIBLE)) {
                    openCustomKeyboard(view);
                }
                return true;
            }
        });
        userInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    openCustomKeyboard(view);
                    requestContacts(userInput.getText().toString());
                } else {
                    hideCustomKeyboard();
                }
            }
        });
        keyboardView.setOnKeyboardActionListener(new KeyboardView.OnKeyboardActionListener() {
            @Override
            public void onPress(int i) {
            }

            @Override
            public void onRelease(int i) {
            }

            @Override
            public void onKey(int primaryCode, int[] keyCodes) {
                View focusCurrent = getWindow().getCurrentFocus();
                if ((focusCurrent == null) || focusCurrent.getClass() != AppCompatEditText.class)
                    return;
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
        });
        keyboardView.setPreviewEnabled(false);
        ImageButton clearButton = (ImageButton) findViewById(R.id.clear_btn);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Editable editable = userInput.getText();
                int start = userInput.getSelectionStart();
                if (start > 0) {
                    editable.replace(start - 1, start, "");
                }
            }
        });
        clearButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                userInput.getText().clear();
                return true;
            }
        });
        userInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                requestContacts(editable.toString());
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_READ_CONTACTS:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted
                    makePresenter();
                } else {
                    makeSnackBar();
                }
                break;
        }
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && ContextCompat.checkSelfPermission
                (this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale
                    (this, Manifest.permission.READ_CONTACTS)) {
                makeSnackBar();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSION_READ_CONTACTS);
            }
        } else {
            makePresenter();
        }
    }

    private void makePresenter() {
        mPresenter = new InputHandler(Injection.provideContacts(getApplicationContext()), this);
    }

    private void requestContacts(String query) {
        if (mPresenter != null) {
            mPresenter.requestContacts(query);
        } else {
            requestPermission();
        }
    }

    private void makeSnackBar() {
        Snackbar.make(findViewById(R.id.list_coordinator),
                R.string.grant_permission_explanation,
                BaseTransientBottomBar.LENGTH_INDEFINITE)
                .setAction(R.string.settings, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toAppSettings();
                    }
                }).show();
    }

    private void toAppSettings() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", getPackageName(), null));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
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
            ((InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
