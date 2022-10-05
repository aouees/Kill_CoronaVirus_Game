package com.owis_alhammoud.killcoronavirus;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Bullet {
    int x,y,width,hight    ;
    Bitmap bullet;
    Bullet(Resources res)
    {
        bullet = BitmapFactory.decodeResource(res,R.drawable.bullet);
         width=bullet.getWidth();
         hight =bullet.getHeight();

        width/=4;
        hight/=4;

        width*=(int) GameView.screenRatX;
        hight *= (int)GameView.screenRatY;

        bullet =Bitmap.createScaledBitmap(bullet,width,hight,false);
    }
    Rect getRect()
    {
        return new Rect(x,y,x+width,y+hight);
    }
}
