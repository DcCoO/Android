package com.example.danielcauas.timequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }




    public void play(View v){
        //Toast.makeText(MainActivity.this, "This is my Toast message!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), ChooseGameActivity.class);
        startActivity(intent);
        finish();
    }
}
