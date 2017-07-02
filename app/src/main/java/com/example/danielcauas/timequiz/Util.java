package com.example.danielcauas.timequiz;

import android.app.Activity;
import android.content.Intent;

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

    /*
    public static int dist(String str1, String str2){

        int dif = Math.abs(str1.length() - str2.length());
        if(dif > 2) return 10;

        int len = Math.min(str1.length(), str2.length());
        int match = 0;
        for(int i = 0; i < len; i++){
            if(str1.charAt(i) != str2.charAt(i)) match++;
        }

        return match + dif;
    }

    public static int editDist(String str1 , String str2 , int m ,int n) {
        if (m == 0) return n;
        if (n == 0) return m;
        if (str1.charAt(m-1) == str2.charAt(n-1))
            return editDist(str1, str2, m-1, n-1);

        return 1 + min ( editDist(str1,  str2, m, n-1),
                editDist(str1,  str2, m-1, n),
                editDist(str1,  str2, m-1, n-1)
        );
    }

    static int min(int a, int b, int c){
        return Math.min(a, Math.min(b, c));
    }
    */
}
