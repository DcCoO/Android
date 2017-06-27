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

    private TextView time;
    private EditText input;
    private CountDownTimer timer;
    private boolean started;

    private Checker checker;

    private ListView lv1, lv2;
    private boolean par = false;
    private ArrayList<String> listImpar, listPar;
    private ArrayAdapter<String> adapterImpar, adapterPar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        started = false;
        listImpar = new ArrayList<String>(); listPar = new ArrayList<String>();
        adapterImpar = new ArrayAdapter<String>(this, R.layout.game_list_view_item, listImpar);
        adapterPar = new ArrayAdapter<String>(this, R.layout.game_list_view_item, listPar);

        lv1 = (ListView) findViewById(R.id.listView3); lv1.setAdapter(adapterImpar);
        lv2 = (ListView) findViewById(R.id.listView4); lv2.setAdapter(adapterPar);

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
                if(!str.equals("")) {
                    if(par){
                        listPar.add(str);
                        adapterPar.notifyDataSetChanged();
                    }
                    else{
                        listImpar.add(str);
                        adapterImpar.notifyDataSetChanged();
                    }
                    input.setText("");
                    par = !par;
                }
                else System.out.println("NAO ACHOU NADA");
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
