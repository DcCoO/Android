package com.example.danielcauas.timequiz;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

/**
 * Created by Daniel on 02/07/2017.
 */

public class PersistenceController {

    private static boolean canToast = false;
    private static String level;

    public static void save(Context context, String level){
        SharedPreferences sharedPreferences = context.getSharedPreferences("levelInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(level, true);
        editor.apply();
    }

    public static boolean isLevelCompleted(Context context, String level){
        SharedPreferences sharedPreferences = context.getSharedPreferences("levelInfo", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(level, false);
    }

    public static void prepareToast(String s){
        canToast = true;
        level = s;
    }

    public static void showToast(Context context){
        if(!canToast) return;
        Toast.makeText(context, "Voce completou o nivel " + level + " !", Toast.LENGTH_LONG).show();
        canToast = false;
    }

    public static void clearPrefs(Context context){
        context.getSharedPreferences("levelInfo", 0).edit().clear().commit();
    }

}
