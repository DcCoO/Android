package com.example.danielcauas.timequiz;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/arlrbdb.ttf");
        button.setTypeface(font);
        if( !MusicController.getInstance(this).paradoPorOpcao )
            MusicController.getInstance(this).play();
    }

    @Override
    public void onPause(){
        super.onPause();
        if(MusicController.getInstance(this).playing() && !leaving){
            MusicController.getInstance(this).stop();
        }
    }

    @Override
    public void onResume(){
        if(!MusicController.getInstance(this).playing() && !MusicController.getInstance(this).paradoPorOpcao){
            MusicController.getInstance(this).play();
        }
        super.onResume();
    }

    private boolean leaving = false;

    public void play(View v){
        leaving = true;
        Util.navigate(this, ChooseGameActivity.class);
        //Toast.makeText(MainActivity.this, "This is my Toast message!", Toast.LENGTH_LONG).show();
    }

    public void musicButtonPress(View view){
        if(MusicController.getInstance(this).playing()){
            MusicController.getInstance(this).stop();
            MusicController.getInstance(this).paradoPorOpcao = true;
        }
        else{
            MusicController.getInstance(this).play();
            MusicController.getInstance(this).paradoPorOpcao = false;
        }
    }
}
