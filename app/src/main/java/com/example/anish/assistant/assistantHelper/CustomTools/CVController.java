package com.example.anish.assistant.assistantHelper.CustomTools;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by raghavthakkar on 22-11-2016.
 */

public class CVController {

    public static Typeface applyFont(Context context, String font, boolean isInEditMode) {
        Typeface typeface = null;
        if (isInEditMode) {
            return typeface;
        }
        try {
            typeface = Typeface.createFromAsset(context.getAssets(), font);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return typeface;
    }
}
