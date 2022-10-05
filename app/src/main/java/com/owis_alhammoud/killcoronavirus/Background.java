package com.owis_alhammoud.killcoronavirus;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Background {
    int x=0,y=0,cnt=1;
    Bitmap bg1,bg2,bg3,bg4,bg5,bg6,bg7,bg8,bg9,bg10,bg11,bg12,bg13,bg14,bg15,bg16;


    Background(int screenX, int screenY, Resources res)
    {
        bg1 = BitmapFactory.decodeResource(res,R.drawable.road1);
        bg2 = BitmapFactory.decodeResource(res,R.drawable.road2);
        bg3 = BitmapFactory.decodeResource(res,R.drawable.road3);
        bg4 = BitmapFactory.decodeResource(res,R.drawable.road4);
        bg5 = BitmapFactory.decodeResource(res,R.drawable.road5);
        bg6 = BitmapFactory.decodeResource(res,R.drawable.road6);
        bg7 = BitmapFactory.decodeResource(res,R.drawable.road7);
        bg8 = BitmapFactory.decodeResource(res,R.drawable.road8);
        bg9 = BitmapFactory.decodeResource(res,R.drawable.road9);
        bg10 = BitmapFactory.decodeResource(res,R.drawable.road10);
        bg11 = BitmapFactory.decodeResource(res,R.drawable.road11);
        bg12 = BitmapFactory.decodeResource(res,R.drawable.road12);
        bg13 = BitmapFactory.decodeResource(res,R.drawable.road13);
        bg14 = BitmapFactory.decodeResource(res,R.drawable.road14);
        bg15 = BitmapFactory.decodeResource(res,R.drawable.road15);
        bg16 = BitmapFactory.decodeResource(res,R.drawable.road16);


        bg1 =Bitmap.createScaledBitmap(bg1,screenX,screenY,false);
        bg2 =Bitmap.createScaledBitmap(bg2,screenX,screenY,false);
        bg3 =Bitmap.createScaledBitmap(bg3,screenX,screenY,false);
        bg4 =Bitmap.createScaledBitmap(bg4,screenX,screenY,false);
        bg5 =Bitmap.createScaledBitmap(bg5,screenX,screenY,false);
        bg6 =Bitmap.createScaledBitmap(bg6,screenX,screenY,false);
        bg7 =Bitmap.createScaledBitmap(bg7,screenX,screenY,false);
        bg8 =Bitmap.createScaledBitmap(bg8,screenX,screenY,false);
        bg9 =Bitmap.createScaledBitmap(bg9,screenX,screenY,false);
        bg10 =Bitmap.createScaledBitmap(bg10,screenX,screenY,false);
        bg11 =Bitmap.createScaledBitmap(bg11,screenX,screenY,false);
        bg12 =Bitmap.createScaledBitmap(bg12,screenX,screenY,false);
        bg13 =Bitmap.createScaledBitmap(bg13,screenX,screenY,false);
        bg14 =Bitmap.createScaledBitmap(bg14,screenX,screenY,false);
        bg15 =Bitmap.createScaledBitmap(bg15,screenX,screenY,false);
        bg16 =Bitmap.createScaledBitmap(bg16,screenX,screenY,false);
    }
    Bitmap getbg()
    {
        switch (cnt)
        {
            case 1:
                cnt++;
                return bg1;

            case 2:
                cnt++;
                return bg2;

            case 3:
                cnt++;
                return bg3;

            case 4:
                cnt++;
                return bg4;

            case 5:
                cnt++;
                return bg5;

            case 6:
                cnt++;
                return bg6;
            case 7:
                cnt++;
                return bg7;

            case 8:
                cnt++;
                return bg8;
            case 9:
                cnt++;
                return bg9;
            case 10:
                cnt++;
                return bg10;
            case 11:
                cnt++;
                return bg11;
            case 12:
                cnt++;
                return bg12;
            case 13:
                cnt++;
                return bg13;
            case 14:
                cnt++;
                return bg14;
            case 15:
                cnt++;
                return bg15;
            case 16:
                cnt=1;
                return bg16;
        }

        return null;
    }
}
