package com.owis_alhammoud.killcoronavirus;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.WindowManager;

public class GameActivity extends AppCompatActivity {
    GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Point point=new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
    gameView = new GameView(this,point.x,point.y);
    setContentView(gameView);
    }
    @Override
    protected  void onPause()
    {
        super.onPause();
        gameView.pause();
    }
    @Override
    protected void  onResume()
    {
         super.onResume();
         gameView.resume();
    }
}
