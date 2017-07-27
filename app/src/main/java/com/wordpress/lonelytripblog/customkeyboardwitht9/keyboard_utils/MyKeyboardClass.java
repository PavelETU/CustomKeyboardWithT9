package com.wordpress.lonelytripblog.customkeyboardwitht9.keyboard_utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.SparseIntArray;

import com.wordpress.lonelytripblog.customkeyboardwitht9.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Custom Keyboard class.
 */

public class MyKeyboardClass extends KeyboardView {
    public MyKeyboardClass(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onDraw(Canvas canvas) {
        List<Keyboard.Key> keys = getKeyboard().getKeys();
        for (Keyboard.Key key : keys) {
            // If key is not pressed - just draw icon, otherwise - draw pressed icon
            if (!key.pressed) {
                key.icon.setBounds(key.x,
                        key.y,
                        key.x  + key.width,
                        key.y +  key.height);
                key.icon.draw(canvas);
            } else {
                // Switch to pressed icon (if it exists), draw it and switch to original icon
                Drawable prevDrawable = key.icon;
                int drawable = getPressedDrawable(key.codes[0]);
                if (drawable != 0) {
                    key.icon = ContextCompat.getDrawable(getContext(), drawable);
                }
                key.icon.setBounds(key.x,
                        key.y,
                        key.x  + key.width,
                        key.y +  key.height);
                key.icon.draw(canvas);
                if (drawable != 0) {
                    key.icon = prevDrawable;
                }
            }
        }
    }

    // Based on the key code returns pressed drawable
    private int getPressedDrawable(int code) {
        SparseIntArray codeDrawableRelation = new SparseIntArray() {
            {
                append(49, R.drawable.key1_p);
                append(50, R.drawable.key2_p);
                append(51, R.drawable.key3_p);
                append(52, R.drawable.key4_p);
                append(53, R.drawable.key5_p);
                append(54, R.drawable.key6_p);
                append(55, R.drawable.key7_p);
                append(56, R.drawable.key8_p);
                append(57, R.drawable.key9_p);
                append(42, R.drawable.key_a_p);
                append(48, R.drawable.key0_p);
                append(35, R.drawable.key_d_p);
            }
        };
        return codeDrawableRelation.get(code);
    }
}
