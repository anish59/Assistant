package com.example.anish.assistant.assistantHelper.CustomTools;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import com.example.anish.assistant.R;

/**
 * Created by anish on 07-12-2016.
 */

public class CEditText extends EditText {

    private final String TAG = getClass().getName();

    public CEditText(Context context) {
        super(context);
        setFont(context, null);

    }


    public CEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont(context, attrs);

    }


    public CEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont(context, attrs);

    }

    private void setFont(Context context, AttributeSet attrs) {

        String font = "";
        TypedArray typedArray = null;
        try {
            typedArray = context.obtainStyledAttributes(attrs, R.styleable.CEditText);
            font = typedArray.getString(R.styleable.CEditText_font);
        } catch (Exception e) {
            //  e.printStackTrace();
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }
        setTypeface(CVController.applyFont(context, font, isInEditMode()));
    }

}
