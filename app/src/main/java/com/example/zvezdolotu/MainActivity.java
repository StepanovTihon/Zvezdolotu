package com.example.zvezdolotu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    SensorManager sensorManager;
    Sensor sensor;
    float speedgo=1;
    float speedturn=1;
    int xv=0;
    int yv=-50;
    int xr=50;
    int yl=10;
    int xl=-50;
    int yr=10;
    int yp,xp;
    int xn=0;
    int yn=20;
    float ugol=0;
    int ugolb=0;
    int xdlina;
    int ydlina;
    int[] y = new int[10];
    int[] x = new int[10];
    int[][] zvezdu = new int [1000][4];
    int[][] monsru = new int [100][5];
    int xroc=240;
    int yroc=480;
    float ugol1,ugol2,ugol1s,ugol2s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(new DrawView(this));
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_GAME);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        xdlina = size.x;

        ydlina = size.y;
        for(int i=0;i<1000;i++){

            zvezdu[i][0] = (int) ((double) 6000 * Math.random())-3000;
            zvezdu[i][1] = (int) ((double) 6000 * Math.random())-3000;
            zvezdu[i][2] = (int) ((double) 5 * Math.random());




        }
        for(int i=0;i<100;i++){
            monsru[i][0] = (int) ((double) 6000 * Math.random())-3000;
            monsru[i][1] = (int) ((double) 6000 * Math.random())-3000;
            monsru[i][2] = (int) ((double) 7 * Math.random());
            monsru[i][3] = (int) ((double) 3 * Math.random())+2;
            monsru[i][4] = (int) ((double) 360 * Math.random());


        }
    }
    @Override
    protected void onPause() { super.onPause(); sensorManager.unregisterListener(listener, sensor); }

    SensorEventListener listener = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) { }

        @Override
        public void onSensorChanged(SensorEvent event) {
            ugol1=-1;
            ugol1 = event.values[0];
            ugol2=event.values[1];
            //y = (int)event.values[1];
        }
    };
    class DrawView extends View {

        Paint p;
        Rect rect;

        public DrawView(Context context) {
            super(context);
            p = new Paint();
            rect = new Rect();

        }

        @Override
        protected void onDraw(Canvas canvas) {
            p.setColor(Color.rgb(255,255,255));
            canvas.drawARGB(1000, 0, 0, 0);
            p.setStrokeWidth(3);
            p.setTextSize(50);
            canvas.drawText(""+(Math.abs((360-ugol)%360)), 300, 525, p);
            //canvas.drawText(zvezdu[0][0]+"", 150, 300, p);
            speedgo= (float) (((ugol2/5)+speedgo)*0.5+(1.0-0.5)*speedgo);
            xroc+=speedgo*Math.sin(Math.toRadians((-1*ugol)%360));
            yroc +=speedgo*Math.cos(Math.toRadians((-1*ugol)%360));
            if(xp>0){
                canvas.drawLine(xdlina/2, 0, xdlina/2,ydlina/2, p);

            }
            if(Math.abs(ugol1)>1){
                if(ugol1<0) {
                    canvas.drawLine(xv + (xdlina / 2), yv + (ydlina / 2), xr + (xdlina / 2) - Math.abs(ugol1*2), yr + (ydlina / 2) + Math.abs(ugol1*2), p);
                    canvas.drawLine(xv + (xdlina / 2), yv + (ydlina / 2), xl + (xdlina / 2) + Math.abs(ugol1*2), yl + (ydlina / 2) - Math.abs(ugol1*2), p);
                    canvas.drawLine(0 + (xdlina / 2), 0 + (ydlina / 2), xr + (xdlina / 2) - Math.abs(ugol1*2), yr + (ydlina / 2) + Math.abs(ugol1*2), p);
                    canvas.drawLine(0 + (xdlina / 2), 0 + (ydlina / 2), xl + (xdlina / 2) + Math.abs(ugol1*2), yl + (ydlina / 2) - Math.abs(ugol1*2), p);
                    canvas.drawLine(0 + (xdlina / 2), 0 + (ydlina / 2), xv + (xdlina / 2), yv + (ydlina / 2), p);
                    canvas.drawLine(xn + (xdlina / 2), yn + (ydlina / 2), xl + (xdlina / 2)+Math.abs(ugol1*2), yl + (ydlina / 2)-Math.abs(ugol1*2), p);
                    canvas.drawLine(xn + (xdlina / 2), yn + (ydlina / 2), xr + (xdlina / 2)-Math.abs(ugol1*2), yr + (ydlina / 2)+Math.abs(ugol1*2), p);

                }

                if(ugol1>0) {
                    canvas.drawLine(xv + (xdlina / 2), yv + (ydlina / 2), xr + (xdlina / 2) - Math.abs(ugol1*2), yr + (ydlina / 2) - Math.abs(ugol1*2), p);
                    canvas.drawLine(xv + (xdlina / 2), yv + (ydlina / 2), xl + (xdlina / 2) + Math.abs(ugol1*2), yl + (ydlina / 2) + Math.abs(ugol1*2), p);
                    canvas.drawLine(0 + (xdlina / 2), 0 + (ydlina / 2), xr + (xdlina / 2) - Math.abs(ugol1*2), yr + (ydlina / 2) - Math.abs(ugol1*2), p);
                    canvas.drawLine(0 + (xdlina / 2), 0 + (ydlina / 2), xl + (xdlina / 2) + Math.abs(ugol1*2), yl + (ydlina / 2) + Math.abs(ugol1*2), p);
                    canvas.drawLine(0 + (xdlina / 2), 0 + (ydlina / 2), xv + (xdlina / 2), yv + (ydlina / 2), p);
                    canvas.drawLine(xn + (xdlina / 2), yn + (ydlina / 2), xl + (xdlina / 2)+Math.abs(ugol1*2), yl + (ydlina / 2)+Math.abs(ugol1*2), p);
                    canvas.drawLine(xn + (xdlina / 2), yn + (ydlina / 2), xr + (xdlina / 2)-Math.abs(ugol1*2), yr + (ydlina / 2)-Math.abs(ugol1*2), p);

                }

            }
            speedturn= (float) ((ugol1+speedturn)*0.5+(1.0-0.5)*speedturn);
            ugol = -1*speedturn;

            for(int i=0;i<1000;i++){
                if(zvezdu[i][2]<1){
                    p.setColor(Color.rgb( 93, 207, 52));

                }else if(zvezdu[i][2]<2){
                    p.setColor(Color.rgb( 73, 88, 222));

                }else if(zvezdu[i][2]<3){
                    p.setColor(Color.rgb( 209, 21, 65));
                }else if(zvezdu[i][2]<4){
                    p.setColor(Color.rgb( 209, 99, 21));


                }else if(zvezdu[i][2]<5){
                    p.setColor(Color.rgb( 159, 209, 21));


                }

                if(Math.abs(zvezdu[i][0]-xroc)<1000 && Math.abs(zvezdu[i][1]-yroc)<1000) {
                    canvas.drawCircle((int) ((xdlina / 2) + (((zvezdu[i][0] - xroc) + (xdlina / 2)) * Math.cos(Math.toRadians(ugol)) + ((zvezdu[i][1] - yroc) + (ydlina / 2)) * Math.sin(Math.toRadians(ugol)))), (int) ((ydlina / 2) + (((zvezdu[i][1] - yroc) + (ydlina / 2)) * Math.cos(Math.toRadians(ugol)) - ((zvezdu[i][0] - xroc) + (xdlina / 2)) * Math.sin(Math.toRadians(ugol)))), zvezdu[i][2] + 5, p);
                }
                if(Math.abs(zvezdu[i][0]-xroc)>2000 || Math.abs(zvezdu[i][1]-yroc)>2000){
                    zvezdu[i][0] = (int) ((double) 3000 * Math.random()) -1500 + xroc;
                    zvezdu[i][1] = (int) ((double) 3000 * Math.random()) -1500+ yroc;
                    if(Math.abs(zvezdu[i][0]-xroc)<1000 && Math.abs(zvezdu[i][1]-yroc)<1000){
                        zvezdu[i][0] += (int) 1000;
                        zvezdu[i][1] += (int) 1000;
                    }


                }



            }
            p.setColor(Color.rgb(255,255,255));
            for(int i=0;i<100;i++){
                if(Math.random()*1000>988){
                    monsru[i][4]= (int) ((double)Math.random()*360);
                }
                monsru[i][0]+=monsru[i][3]*Math.sin(Math.toRadians((-1*monsru[i][4])%360));
                monsru[i][1] +=monsru[i][3]*Math.cos(Math.toRadians((-1*monsru[i][4])%360));
                if(Math.abs(monsru[i][0]-xroc)<1000 && Math.abs(monsru[i][1]-yroc)<1000) {
                    canvas.drawCircle((int) ((xdlina / 2) + (((monsru[i][0] - xroc) + (xdlina / 2)) * Math.cos(Math.toRadians(ugol)) + ((monsru[i][1] - yroc) + (ydlina / 2)) * Math.sin(Math.toRadians(ugol)))), (int) ((ydlina / 2) + (((monsru[i][1] - yroc) + (ydlina / 2)) * Math.cos(Math.toRadians(ugol)) - ((monsru[i][0] - xroc) + (xdlina / 2)) * Math.sin(Math.toRadians(ugol)))), monsru[i][2] + 5, p);
                }

                if(Math.abs(monsru[i][0]-xroc)>2000 || Math.abs(monsru[i][1]-yroc)>2000 || xp>0){
                    monsru[i][0] = (int) ((double) 3000 * Math.random()) -1500 + xroc;
                    monsru[i][1] = (int) ((double) 3000 * Math.random()) -1500+ yroc;
                    if(Math.abs(monsru[i][0]-xroc)<1000 && Math.abs(monsru[i][1]-yroc)<1000){
                        monsru[i][0] += (int) 1000;
                        monsru[i][1] += (int) 1000;
                    }


                }




            }

            if(Math.abs(ugol1)<1) {
                canvas.drawLine(xv + (xdlina / 2), yv + (ydlina / 2), xr + (xdlina / 2), yr + (ydlina / 2), p);
                canvas.drawLine(xv + (xdlina / 2), yv + (ydlina / 2), xl + (xdlina / 2), yl + (ydlina / 2), p);
                canvas.drawLine(0 + (xdlina / 2), 0 + (ydlina / 2), xr + (xdlina / 2), yr + (ydlina / 2), p);
                canvas.drawLine(0 + (xdlina / 2), 0 + (ydlina / 2), xl + (xdlina / 2), yl + (ydlina / 2), p);
                canvas.drawLine(0 + (xdlina / 2), 0 + (ydlina / 2), xv + (xdlina / 2), yv + (ydlina / 2), p);
                canvas.drawLine(xn + (xdlina / 2), yn + (ydlina / 2), xl + (xdlina / 2), yl + (ydlina / 2), p);
                canvas.drawLine(xn + (xdlina / 2), yn + (ydlina / 2), xr + (xdlina / 2), yr + (ydlina / 2), p);

            }
            if(ugol==359){
                ugol=0;
            }
            xp=0;
            yp=0;
            invalidate();
        }

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            xp = (int) event.getX();
            yp = (int) event.getY();
        } else if (action == MotionEvent.ACTION_MOVE) {
            xp = (int) event.getX();
            yp = (int) event.getY();
        }
        return true;
    }
}
