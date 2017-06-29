package com.example.danielcauas.timequiz;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class GameActivity extends AppCompatActivity {

    private TextView time, score;
    private EditText input;
    private CountDownTimer timer;
    private boolean started = false;

    private Checker checker;
    private String filename;

    private void extractExtras(){
        Bundle b = getIntent().getExtras();

        filename = b.getString("filename");
        tempo = b.getInt("tempo");
        total = b.getInt("total");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        time = (TextView) findViewById(R.id.textView);
        score = (TextView) findViewById(R.id.score);
        input = (EditText) findViewById(R.id.editText);

        extractExtras();

        InputStream is = null;
        try {
            is = getAssets().open(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        checker = new Checker(is);
        time.setText(timeToString(tempo));
        score.setText(getScore());



        input.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                String str = GameActivity.this.checker.find(input.getText().toString());
                if(!str.equals("")) {
                    input.setText("");
                    acertos++;
                    score.setText(getScore());
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                GameActivity.this.start();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }


    private int tempo;
    private int acertos = 0;
    private int total;

    public void start(){
        if(started) return;
        started = true;
        input.setText("");
        timer = new CountDownTimer(tempo * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time.setText(timeToString(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                time.setText("Done !");
            }
        };

        timer.start();
    }

    public String timeToString(long time){
        return String.format("%02d:%02d", (time/60), (time%60));
    }

    public String getScore(){
        return acertos + "/" + total;
    }

    private void cancel(){
        if(timer != null){
            timer.cancel();
            timer = null;
        }
    }
}
