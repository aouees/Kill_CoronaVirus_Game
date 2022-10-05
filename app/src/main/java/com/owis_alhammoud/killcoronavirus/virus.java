package com.owis_alhammoud.killcoronavirus;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;

public class virus {
    public int speed= 4;
    public boolean wasShoot=true;
    Random random;
    int x,y=0,width,height,cnt=0;
    Bitmap virus1,virus2;
    virus( Resources res)
    {
        virus1= BitmapFactory.decodeResource(res,R.drawable.coronaa);
        virus2=BitmapFactory.decodeResource(res,R.drawable.coronaa1);

      /*  width=virus1.getWidth();
        height=virus2.getHeight();

        width/=6;
        height/=6;


        width*=(int)GameView.screenRatX;
        height*=(int)GameView.screenRatY;
*/
      width=GameView.screenX/5;
      height=width;
        random =new Random();
        virus1=Bitmap.createScaledBitmap(virus1,width,height,false);
        virus2=Bitmap.createScaledBitmap(virus2,width,height,false);

        x=getnumber();
    }
    int getnumber()
    {
       /* if(cnt==0)
        {
            cnt++;
            return 0;
        }
        if(cnt==1)
        {
            cnt++;
            return (int) (width+GameView.screenRatX);
        }
        if(cnt==2)
        {
            cnt ++;
            return (int) ((2*(width+GameView.screenRatX)));
        }

        cnt =0;
        return (int) ((3*(width+GameView.screenRatX)));*/
       return random.nextInt(GameView.screenX-width);

    }
    Bitmap getVirus()
    {
        return virus1;
      /*  if(cnt==0)
        {
            cnt++;
            return  virus1;
        }
        else {
            cnt--;
            return  virus2;
        }*/
    }
    Rect getRect()
    {
        return new Rect(x,y,x+width,y+height);
    }
}
