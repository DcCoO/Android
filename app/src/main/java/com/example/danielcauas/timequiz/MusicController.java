package com.example.danielcauas.timequiz;

import android.content.Context;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by Daniel on 02/07/2017.
 */

public class MusicController {

    private static MusicController controller;
    public MediaPlayer player;
    public boolean paradoPorOpcao = false;

    public static MusicController getInstance(Context context){
        if(controller == null) controller = new MusicController(context);
        return controller;
    }

    private MusicController(Context context){
        player = MediaPlayer.create(context, R.raw.music44);
    }


    public void play(){
        player.start();
        player.setLooping(true);
    }


    public void stop(){
        player.pause();
    }

    public boolean playing(){
        return controller.player.isPlaying();
    }


}
