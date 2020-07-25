package com.example.sharif.appointment;

import android.graphics.Bitmap;
import android.view.View;

/**
 * Created by SHARIF on 8/17/2018.
 */

 public class ScreenShot {
  public static  Bitmap takeScreenShoot(View v){
         v.setDrawingCacheEnabled(true);
         v.buildDrawingCache(true);
         Bitmap b=Bitmap.createBitmap(v.getDrawingCache());
         v.setDrawingCacheEnabled(false);
         return b;
     }
     public  static Bitmap takeScreenShotOfView(View v){
         return takeScreenShoot(v.getRootView());
     }

}
