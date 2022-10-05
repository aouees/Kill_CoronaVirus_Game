package com.owis_alhammoud.killcoronavirus;

import android.content.Context;









import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.view.MotionEvent;
import android.view.SurfaceView;











import java.util.ArrayList;
import java.util.List;
import java.util.Random;









public class GameView extends SurfaceView implements Runnable
{





    private Thread thread;

    boolean isPlaying;

    Paint paint;

    public  static int screenY,screenX,score=0;

    private List<Bullet> bullets;

    private virus []viruses;

    private SharedPreferences sharedPreferences;

    private SoundPool soundPool;

    private  int sound;

    private Random random;

    CarClient carClient;

    public  static   float screenRatX,screenRatY;

    private Background background;

    private boolean isGameOver=false;

    private  GameActivity activity;

    public GameView(GameActivity activity,int screenX,int screenY) {
        super(activity);
        this.activity=activity;
        this.screenX=screenX;
        this.screenY=screenY;
        screenRatX=1;
        screenRatY=1;

        sharedPreferences =activity.getSharedPreferences("game",Context.MODE_PRIVATE);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            AudioAttributes audioAttributes=new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .build();
            soundPool =new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .build();
        }
        else
        {
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);

        }

        sound =soundPool.load(activity,R.raw.shoot,1);
        background =new Background(screenX,screenY,getResources());


        carClient =new CarClient(this,screenX,screenY,getResources());

        bullets=new ArrayList<>();

        viruses=new virus[4];
        for (int i=0;i<4;i++)
        {
            viruses[i]=new virus(getResources());
        }
        random =new Random();
        paint =new Paint();
        paint.setTextSize(128);
        paint.setColor(Color.WHITE);



        if(!sharedPreferences.getBoolean("done",false))
        {
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putBoolean("done",true);
            editor.apply();
        }
    }

    @Override
    public void run() {
        while (isPlaying)
        {
            update();
            draw();
            sleep();
        }
    }
    private   void update()
    {
     /*   //******************************
        //Backgrond
        //due to not solved problem move background from top to down
        //******************************
        background1.y+=10*screenRatY;
        background2.y+=10*screenRatY;
        if( background1.background.getHeight()==background1.y)
        {
            background1.y=-screenY;
        }
        if( background2.background.getHeight()==background2.y)
        {
            background2.y=screenY;
        }
*/
        //******************************
        ///Car Client
        //******************************

        if(carClient.isGoingleft && !carClient.isStop )
        {
            carClient.x-=30*screenRatX;
        }
        else if(!carClient.isGoingleft &&  !carClient.isStop)
        {
            carClient.x+=30*screenRatX;
        }
        if(carClient.x<0)
            carClient.x=0;
        if (carClient.x>screenX-carClient.width)
        {
            carClient.x=screenX-carClient.width;
        }
        //******************************
        //Bullets
        //******************************
        List<Bullet>trash_Bullet=new ArrayList<>();
        for (Bullet bullet:bullets) {
            if(bullet.y<screenY/20)
                trash_Bullet.add(bullet);

            bullet.y-=50*screenRatY;

            for(virus virus: viruses)
            {
                if(Rect.intersects(virus.getRect(),bullet.getRect()))
                {
                    score++;
                    virus.y=-500;
                    virus.x=random.nextInt(screenX-virus.width);
                    bullet.y=-screenY;
                    virus.wasShoot=true;
                }
            }
        }
        for (Bullet bullet:trash_Bullet)
            bullets.remove(bullet);

        //******************************
        //Corona Virus
        //******************************

        for(virus virus : viruses)
        {
            virus.y+=virus.speed;
            if(virus.y+virus.height>0)
            {
                if(!virus.wasShoot || virus.height+virus.y>screenY)
                {
                    isGameOver=true;
                    return;
                }
            //    int bound= (int) (30 * screenRatY);
             //   virus.speed=random.nextInt(bound);
             /*   if(virus.speed<5*screenRatY)
                {
                    virus.speed= (int) (10*screenRatY);
                }*/
                virus.y+=virus.speed;
                int r=random.nextInt(screenX-virus.width);
                if(r%2==0)
                    virus.x+=screenX/36;
                else
                    virus.x-=screenX/36;
                if(virus.x<0)
                    virus.x=0;
                if(virus.x>screenX-virus.width)
                    virus.x=screenX-virus.width;
              //  virus.wasShoot=false;
            }
            if(Rect.intersects(virus.getRect(),carClient.getRect()))
            {
                isGameOver=true;
                return;
            }
        }
    }
    private   void draw()
    {
        if(getHolder().getSurface().isValid())
        {
            Canvas  canvas =getHolder().lockCanvas();

            //draw background
            canvas.drawBitmap(background.getbg(),background.x,background.y,paint);


            //draw Tank
            if(isGameOver)
            {
                isPlaying=false;
                paint.setTextSize((float) (screenX/4.6));
                paint.setColor(Color.RED);
                canvas.drawText("GameOver",0,screenY/2f,paint);
                canvas.drawBitmap(carClient.getdead(),carClient.x,carClient.y,paint);
                for(virus virus : viruses)
                {
                    canvas.drawBitmap(virus.virus2,virus.x,virus.y,paint);
                }
                getHolder().unlockCanvasAndPost(canvas);
                saveIfHightScor();
                waitBeforeExiting();
                return;
            }
            canvas.drawBitmap(carClient.getCar(),carClient.x,carClient.y,paint);

            //draw score
            canvas.drawText(score+"",(screenX/3f),screenY/18,paint);



            //draw bulltes
            for(Bullet bullet : bullets)
            {
                canvas.drawBitmap(bullet.bullet,bullet.x,bullet.y,paint);
            }
            //draw CoronaVirus
            for(virus virus : viruses)
            {
                canvas.drawBitmap(virus.getVirus(),virus.x,virus.y,paint);
            }

            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    private void waitBeforeExiting() {
        try {
            Thread.sleep(3000);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("done", true);
            editor.putBoolean("isClicked", false);
            editor.apply();



           // activity.startActivity(new Intent(activity,MainActivity.class));
            activity.finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void saveIfHightScor() {
        if(sharedPreferences.getInt("hightScore",0)<score)
        {
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putInt("hightScore",score);
            editor.apply();
        }
        score=0;
    }

    private   void sleep()
    {
        try {
            thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public  void resume(){
        isPlaying=true;
        thread =new Thread(this);
        thread.start();

    }

   public  void pause()
   {
       try {
           isPlaying=false;
           SharedPreferences.Editor editor = sharedPreferences.edit();
           editor.putBoolean("done", true);
           editor.putBoolean("isClicked", false);
           editor.apply();



           // activity.startActivity(new Intent(activity,MainActivity.class));
           activity.finish();
           thread.join();
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
   }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                if(event.getX()>carClient.x && event.getX()<carClient.x+carClient.width && event.getY()>carClient.y && event.getY()<carClient.y+carClient.hight)
                {
                    carClient.isStop=true;
                    carClient.toshoot++;
                }
                else
                {
                    if(event.getX()<(screenX/2)) {
                        carClient.isGoingleft=true;
                        carClient.isStop=false;
                    }
                    else{
                        carClient.isStop=false;
                        carClient.isGoingleft=false;
                    }
                }
                break;
            case  MotionEvent.ACTION_UP:
             //   carClient.isGoingleft=false;
                carClient.toshoot=0;
                carClient.isStop=true;
                break;
        }
        return  true;
    }


    public void newBullet() {
     if(!sharedPreferences.getBoolean("isMute",false))
        {
            soundPool.play(sound,1,1,0,0,1);
        }
        Bullet bullet=new Bullet(getResources());
        bullet.x=carClient.x+(carClient.width/2);
        bullet.y=carClient.y;
        bullets.add(bullet);
    }
}
