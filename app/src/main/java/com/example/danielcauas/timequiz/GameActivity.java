package com.example.danielcauas.timequiz;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;


public class GameActivity extends AppCompatActivity {

    private TextView time;
    private EditText input;
    private CountDownTimer timer;
    private boolean started;

    private Checker checker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        started = false;

        InputStream is = null;
        try {
            is = getAssets().open("Country.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        checker = new Checker(is);
        checker.find("");

        Bundle b = getIntent().getExtras();
        String tipo = b.getString("tipo");

        time = (TextView) findViewById(R.id.textView);
        input = (EditText) findViewById(R.id.editText);

        input.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                String str = GameActivity.this.checker.find(input.getText().toString());
                System.out.println("Saiu da busca por " + input.getText().toString() + ", achou: " + str);
                if(!str.equals("")) System.out.print("ACERTOU MIZERAVI");
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                GameActivity.this.start();
            }
        });
    }

    public void start(){
        if(started) return;
        started = true;
        time.setText("15");
        timer = new CountDownTimer(15 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time.setText("" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                time.setText("Done !");
            }
        };

        timer.start();
    }

    private void cancel(){
        if(timer != null){
            timer.cancel();
            timer = null;
        }
    }








}
