package com.example.danielcauas.timequiz;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;


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
        tipo = b.getString("tipo");
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

        final MediaPlayer scoreSound = MediaPlayer.create(GameActivity.this, R.raw.scorepoint);

        input.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                String str = GameActivity.this.checker.find(input.getText().toString());
                if(!str.equals("")) {
                    input.setText("");
                    acertos++;
                    score.setText(getScore());

                    if(acertos == total){
                        MediaPlayer winSound = MediaPlayer.create(GameActivity.this, R.raw.victory);
                        winSound.start();

                        timer.cancel();
                        time.setText("Venceu!");
                        input.setEnabled(false); input.setInputType(InputType.TYPE_NULL);
                        PersistenceController.save(GameActivity.this, GameActivity.this.tipo);
                        PersistenceController.prepareToast(GameActivity.this.tipo);
                    }
                    else{
                        if(!scoreSound.isPlaying()) scoreSound.start();
                        else scoreSound.seekTo(0);

                    }
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

    public boolean leaving = false;

    @Override
    public void onBackPressed(){
        leaving = true;
        Util.navigate(this, ChooseGameActivity.class);
    }

    public void musicButtonPress(View view){
        if(MusicController.getInstance(this).playing()){
            MusicController.getInstance(this).stop();
        }
        else{
            MusicController.getInstance(this).play();
        }
    }


    private int tempo;
    private int acertos = 0;
    private int total;
    private String tipo;

    public void start(){
        if(started) return;
        started = true;
        timer = new CountDownTimer(tempo * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time.setText(timeToString(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                time.setText("Perdeu!");
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
