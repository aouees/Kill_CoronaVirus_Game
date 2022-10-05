package com.owis_alhammoud.killcoronavirus;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class CarClient {
    public boolean isGoingleft=false;
    public boolean isStop=false;
    public int toshoot=0;
    int x,y,width,hight;
    private  GameView gameView;
    Bitmap car,dead;
    CarClient(GameView gameView,int screenX,int screenY, Resources res)
    {
        this.gameView=gameView;

        car = BitmapFactory.decodeResource(res,R.drawable.tank);

       /* width=car.getWidth();
        hight=car.getHeight();

        width/=3.5;
        hight/=3.5;
        width*=(int)GameView.screenRatX;
        hight *=(int)GameView.screenRatY;*/
       width=screenX/4;
       hight=width+screenY/20;
        car=Bitmap.createScaledBitmap(car,width,hight,false);
        x=screenX/3;
       y= (int) ( screenY-hight);
       dead=car;

    }
    Bitmap getCar()
    {
        if(toshoot!=0)
        {

            toshoot--;
            gameView.newBullet();
            return  car;
        }
        return  car;
    }
    Rect getRect()
    {
        return new Rect(x,y,x+width,y+hight);
    }
    Bitmap getdead()
    {
        return dead;
    }
}
