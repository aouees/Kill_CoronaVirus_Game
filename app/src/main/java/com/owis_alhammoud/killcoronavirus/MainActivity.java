package com.owis_alhammoud.killcoronavirus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;

public class MainActivity extends AppCompatActivity {
    NotificationManager nm;
    Boolean isMute;
    MediaPlayer mediaPlayer;
    SharedPreferences sharedPreferences;
    TextView hightScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.bella);


        findViewById(R.id.go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sharedPreferences.getBoolean("isClicked", false)) {
                    mediaPlayer.stop();
                    startActivity(new Intent(MainActivity.this, GameActivity.class));
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isClicked", true);
                    editor.apply();
                }
            }
        });


        hightScore = findViewById(R.id.highsecortext);
        sharedPreferences = getSharedPreferences("game", MODE_PRIVATE);
        hightScore.setText("High Score : " + sharedPreferences.getInt("hightScore", 0));


        isMute = sharedPreferences.getBoolean("isMute", false);
        final ImageView CntrolV = findViewById(R.id.controlV);
        if (isMute) {
            CntrolV.setImageResource(R.drawable.ic_volume_off_black_24dp);
            mediaPlayer.pause();
        } else {
            mediaPlayer = MediaPlayer.create(this, R.raw.bella);
            CntrolV.setImageResource(R.drawable.ic_volume_up_black_24dp);
            mediaPlayer.start();
        }


        if (!sharedPreferences.getBoolean("done", false)) {
            Notific();
        }

        CntrolV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMute = !isMute;
                if (isMute) {
                    CntrolV.setImageResource(R.drawable.ic_volume_off_black_24dp);
                    mediaPlayer.stop();
                } else {
                    mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.bella);
                    CntrolV.setImageResource(R.drawable.ic_volume_up_black_24dp);
                    mediaPlayer.start();
                }

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isMute", isMute);
                editor.apply();
                /*
                Point point=new Point();
                WindowManager w=getWindowManager();
                Display d=w.getDefaultDisplay();
                getWindowManager().getDefaultDisplay().getSize(point);
                String s="(x="+point.x+" , y="+point.y+")";

                Point p=new Point();
                try {
                    Display.class.getMethod("getRealSize", Point.class).invoke(d,p);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                String  s1="(x="+p.x+" , y="+p.y+")";
                Toast.makeText(MainActivity.this,s+s1,Toast.LENGTH_LONG).show();*/
            }
        });


    }

    @Override
    protected void onPause() {


        super.onPause();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("done", true);
        editor.apply();
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("done", false);
        editor.putBoolean("isClicked", false);

        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        hightScore.setText("High Score : " + sharedPreferences.getInt("hightScore", 0));

    }

    private void Notific() {
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder nb;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            nm.createNotificationChannel(new NotificationChannel("1", "Calculator", nm.IMPORTANCE_DEFAULT));
            nb = new NotificationCompat.Builder(this, "1");
        } else {
            nb = new NotificationCompat.Builder(this);

        }

        nb.setSmallIcon(R.drawable.icon_corona)
                .setContentTitle("Kill CoronaVirus Game : Simple Education project")
                .setContentText("It's programmed by : Owis Al_hammoud")
                /*  .setContentIntent(
                          PendingIntent.getActivity(
                                  this,
                                  0,
                                  new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/aouees")),
                                  PendingIntent.FLAG_UPDATE_CURRENT))*/
                .addAction(
                        R.drawable.bullet, "Whatsapp",
                        PendingIntent.getActivity(
                                this,
                                0,
                                new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=+963936973634&text=Hello Owis , I have a message for you : ")),
                                PendingIntent.FLAG_UPDATE_CURRENT))
                .addAction(
                        R.drawable.bullet, "Facebook",
                        PendingIntent.getActivity(
                                this,
                                0,
                                new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/aouees")),
                                PendingIntent.FLAG_UPDATE_CURRENT))
                .addAction(
                        R.drawable.bullet, "Instagram",
                        PendingIntent.getActivity(
                                this,
                                0,
                                new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/aouees_ah/")),
                                PendingIntent.FLAG_UPDATE_CURRENT));

        nm.notify(1, nb.build());
    }
}
