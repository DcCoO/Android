package com.example.danielcauas.timequiz;

import android.os.Debug;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Daniel on 24/06/2017.
 */

public class Checker {

    private ArrayList<String> list = new ArrayList<>();

    public Checker(InputStream is){

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String data = "";
        if(is != null) {
            try {
                while((data = br.readLine()) != null) list.add(data);
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public String find(String s1) {
        for (String s2 : list) {
            if(Util.mDist(s1, s2)){
                return s2;
            }
        }
        return "";
    }
}
