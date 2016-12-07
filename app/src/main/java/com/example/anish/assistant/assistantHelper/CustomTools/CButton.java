package com.example.anish.assistant.assistantHelper.CustomTools;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;

import com.example.anish.assistant.R;

/**
 * Created by anish on 07-12-2016.
 */

public class CButton extends Button {

    private final String TAG = getClass().getName();

    public CButton(Context context) {
        super(context);
        setFont(context, null);


    }


    public CButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont(context, attrs);

    }

    public CButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont(context, attrs);
    }

    private void setFont(Context context, AttributeSet attrs) {

        String font = "";
        TypedArray typedArray = null;
        try {
            typedArray = context.obtainStyledAttributes(attrs, R.styleable.CButton);
            font = typedArray.getString(R.styleable.CButton_font);
        } catch (Exception e) {
            // e.printStackTrace();
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }
        setTypeface(CVController.applyFont(context, font, isInEditMode()));
    }

}
