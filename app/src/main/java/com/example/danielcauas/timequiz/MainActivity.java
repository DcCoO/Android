package com.example.danielcauas.timequiz;

import android.content.Intent;
import android.graphics.Typeface;
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
    }




    public void play(View v){
        //Toast.makeText(MainActivity.this, "This is my Toast message!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), ChooseGameActivity.class);
        startActivity(intent);
        finish();
    }
}
