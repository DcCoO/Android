package com.example.danielcauas.timequiz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.View;

/**
 * Created by Daniel on 24/06/2017.
 */

public class Util {

    public static boolean mDist(String str1, String str2){
        str1 = str1.toLowerCase();
        return str1.equals(str2);
    }

    public static void navigate(Activity current, Class destiny){
        Intent i = new Intent(current, destiny);
        current.startActivity(i);
        current.finish();
    }

    public static void changeShapeColor(View view, int color1, int color2){
        int[] colors = { color1, color2 };
        GradientDrawable gradientDrawable = (GradientDrawable) view.getBackground();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            gradientDrawable.setColors(colors);
        }
        view.setBackgroundDrawable(gradientDrawable);
    }
}
