package com.example.zvezdolotu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.hardware.Camera;
public class MainActivity extends AppCompatActivity {
    SensorManager sensorManager;
    Sensor sensor;
    float speedgo=1;
    float speedturn=1;
    int xv=0;
    int yv=-50;
    int xr=50;
    MediaPlayer mPlayer;
    MediaPlayer mPlayer2;
    int yl=10;
    int xl=-50;
    int yr=10;
    int yp,xp;
    int xn=0;
    Camera camera;
    int yn=20;
    Bitmap monstr,vzruv,z1,z2,z3;
    float ugol=0;
    int ugolb=0;
    int xdlina;
    int jizn=10;
    Vibrator vibrator;
    int time;
    int ydlina;
    float[] speedturnmons = new float[100];
    int[] y = new int[10];
    int[] x = new int[10];
    int[][] zvezdu = new int [1000][4];
    int[][] monsru = new int [50][8];
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
        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        xdlina = size.x;
        monstr = BitmapFactory.decodeResource(getResources(), R.drawable.a);
        vzruv = BitmapFactory.decodeResource(getResources(), R.drawable.b);
        z1 = BitmapFactory.decodeResource(getResources(), R.drawable.z1);
        z2 = BitmapFactory.decodeResource(getResources(), R.drawable.z2);
        z3 = BitmapFactory.decodeResource(getResources(), R.drawable.z3);

        ydlina = size.y;
        mPlayer= MediaPlayer.create(this, R.raw.a);
        mPlayer2= MediaPlayer.create(this, R.raw.b);
        for(int i=0;i<1000;i++){

            zvezdu[i][0] = (int) ((double) 6000 * Math.random())-3000;
            zvezdu[i][1] = (int) ((double) 6000 * Math.random())-3000;
            zvezdu[i][2] = (int) ((double) 5 * Math.random());




        }
        for(int i=0;i<50;i++){
            monsru[i][0] = (int) ((double) 6000 * Math.random())-3000;
            monsru[i][1] = (int) ((double) 6000 * Math.random())-3000;
            monsru[i][2] = (int) ((double) 7 * Math.random());
            monsru[i][3] = (int) ((double) 100 * Math.random()/20)+1;
            monsru[i][4] = (int) ((double) 360 * Math.random());
            monsru[i][5] = (int) ((double) 4 * Math.random())-2;
            monsru[i][6] = (int) ((double)1);
            monsru[i][7] = (int) ((double)0);
            speedturnmons[i] = (int) ((double)0);


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
            //canvas.drawText(""+speedgo, 300, 525, p);
            //canvas.drawText(zvezdu[0][0]+"", 150, 300, p);

            speedgo= (float) (((ugol2/5)+speedgo)*0.5+(1.0-0.5)*speedgo);
            if(speedgo>20){
                speedgo=19;
            }
            xroc+=speedgo*Math.sin(Math.toRadians((-1*ugol)%360));
            yroc +=speedgo*Math.cos(Math.toRadians((-1*ugol)%360));
            canvas.drawLine(100,0,100,100,p);
            canvas.drawLine(0,100,100,100,p);
            p.setColor(Color.rgb( 93, 207, 52));
            canvas.drawCircle((int) 50, 50,  5, p);
            canvas.drawRect(0, ydlina-100, (jizn)*(xdlina/10), ydlina, p);
            p.setColor(Color.rgb( 255, 255, 255));
            if(xp>0){
                canvas.drawLine(xdlina/2, 0, xdlina/2,ydlina/2, p);

            }
            if(Math.abs(ugol1)>1){
                if(ugol1<0) {
                    canvas.drawBitmap(z3, 205,390, p);
                }

                if(ugol1>0) {
                    canvas.drawBitmap(z2 ,205,390, p);
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
            for(int i=0;i<50;i++){
                canvas.drawCircle((int) (monsru[i][0]-xroc)/40+50, (int) (monsru[i][1]-yroc)/40+50,  1, p);



                if(xp>0){
                    mPlayer2.start();
                }

                //if(Math.random()*1000>980 && monsru[i][6]==1){
                //    monsru[i][5]*= -1 ;
                //    monsru[i][7]=0;
                //}

                if(monsru[i][6]==1) {
                    //monsru[i][4] += monsru[i][5];
                    float xDiff = (float) (((xdlina / 2) + (((monsru[i][0] - xroc) + (xdlina / 2)) * Math.cos(Math.toRadians(ugol)) + ((monsru[i][1] - yroc) + (ydlina / 2)) * Math.sin(Math.toRadians(ugol)))) - (xdlina/2));
                    float yDiff = (float) (((ydlina / 2) + (((monsru[i][1] - yroc) + (ydlina / 2)) * Math.cos(Math.toRadians(ugol)) - ((monsru[i][0] - xroc) + (xdlina / 2)) * Math.sin(Math.toRadians(ugol)))) - (ydlina/2));
                    //float xDiff=monsru[i][0]-xroc;
                    //float yDiff=monsru[i][0]-yroc;
                    monsru[i][4]= (int) ((monsru[i][4])*0.9 + 0.1*((float) Math.toDegrees(Math.atan2(yDiff, xDiff) * (180 / Math.PI))));

                    //monsru[i][4] = (int) ((int) (float) Math.toDegrees(Math.atan2(yDiff, xDiff) * (180 / Math.PI)));

                    monsru[i][0] += monsru[i][3] * Math.sin(Math.toRadians((-1 * monsru[i][4]) % 360));
                    monsru[i][1] += monsru[i][3] * Math.cos(Math.toRadians((-1 * monsru[i][4]) % 360));
                }
                float tmp1 = (float) (((xdlina / 2) + (((monsru[i][0] - xroc) + (xdlina / 2)) * Math.cos(Math.toRadians(ugol)) + ((monsru[i][1] - yroc) + (ydlina / 2)) * Math.sin(Math.toRadians(ugol)))) );
                float tmp2 = (float) (((ydlina / 2) + (((monsru[i][1] - yroc) + (ydlina / 2)) * Math.cos(Math.toRadians(ugol)) - ((monsru[i][0] - xroc) + (xdlina / 2)) * Math.sin(Math.toRadians(ugol)))) );

                if(monsru[i][6]>0 && tmp1>(xdlina / 2)-25 && tmp1<(xdlina / 2)+25 && tmp2>(ydlina / 2)-25 && tmp2<(ydlina / 2)+25){
                    monsru[i][6]=0;

                    mPlayer.start();
                    jizn-=1;


                    vibrator.vibrate(100);
                }


                if( Math.abs(monsru[i][0]-xroc)<1000 && Math.abs(monsru[i][1]-yroc)<1000 && monsru[i][6]==1) {
                    canvas.save();
                    canvas.rotate(monsru[i][4]-180-ugol,(int) ((xdlina / 2) + (((monsru[i][0] - xroc) + (xdlina / 2)) * Math.cos(Math.toRadians(ugol)) + ((monsru[i][1] - yroc) + (ydlina / 2)) * Math.sin(Math.toRadians(ugol)))),(int) ((ydlina / 2) + (((monsru[i][1] - yroc) + (ydlina / 2)) * Math.cos(Math.toRadians(ugol)) - ((monsru[i][0] - xroc) + (xdlina / 2)) * Math.sin(Math.toRadians(ugol)))));

                    canvas.drawBitmap(monstr, (int) ((xdlina / 2) + (((monsru[i][0] - xroc) + (xdlina / 2)) * Math.cos(Math.toRadians(ugol)) + ((monsru[i][1] - yroc) + (ydlina / 2)) * Math.sin(Math.toRadians(ugol))))-9, (float) (int) ((ydlina / 2) + (((monsru[i][1] - yroc) + (ydlina / 2)) * Math.cos(Math.toRadians(ugol)) - ((monsru[i][0] - xroc) + (xdlina / 2)) * Math.sin(Math.toRadians(ugol))))-8, p);
                    canvas.restore();
                }
                if(xp>0 && (int) ((xdlina / 2) + (((monsru[i][0] - xroc) + (xdlina / 2)) * Math.cos(Math.toRadians(ugol)) + ((monsru[i][1] - yroc) + (ydlina / 2)) * Math.sin(Math.toRadians(ugol))))-11<260 && (int) ((xdlina / 2) + (((monsru[i][0] - xroc) + (xdlina / 2)) * Math.cos(Math.toRadians(ugol)) + ((monsru[i][1] - yroc) + (ydlina / 2)) * Math.sin(Math.toRadians(ugol))))-11>220 && (int) ((ydlina / 2) + (((monsru[i][1] - yroc) + (ydlina / 2)) * Math.cos(Math.toRadians(ugol)) - ((monsru[i][0] - xroc) + (xdlina / 2)) * Math.sin(Math.toRadians(ugol))))-8<400 && (int) ((ydlina / 2) + (((monsru[i][1] - yroc) + (ydlina / 2)) * Math.cos(Math.toRadians(ugol)) - ((monsru[i][0] - xroc) + (xdlina / 2)) * Math.sin(Math.toRadians(ugol))))-8>0){
                    monsru[i][6]=0;

                    mPlayer.start();

                }
                if(monsru[i][6]==0 && monsru[i][7]<100){
                    canvas.save();
                    canvas.rotate(360-ugol,(int) ((xdlina / 2) + (((monsru[i][0] - xroc) + (xdlina / 2)) * Math.cos(Math.toRadians(ugol)) + ((monsru[i][1] - yroc) + (ydlina / 2)) * Math.sin(Math.toRadians(ugol)))),(int) ((ydlina / 2) + (((monsru[i][1] - yroc) + (ydlina / 2)) * Math.cos(Math.toRadians(ugol)) - ((monsru[i][0] - xroc) + (xdlina / 2)) * Math.sin(Math.toRadians(ugol)))));
                    canvas.drawBitmap(vzruv, (int) ((xdlina / 2) + (((monsru[i][0] - xroc) + (xdlina / 2)) * Math.cos(Math.toRadians(ugol)) + ((monsru[i][1] - yroc) + (ydlina / 2)) * Math.sin(Math.toRadians(ugol))))-25, (float) (int) ((ydlina / 2) + (((monsru[i][1] - yroc) + (ydlina / 2)) * Math.cos(Math.toRadians(ugol)) - ((monsru[i][0] - xroc) + (xdlina / 2)) * Math.sin(Math.toRadians(ugol))))-25, p);

                    canvas.restore();
                    monsru[i][7]+=1;
                } else if(monsru[i][7]>100 && monsru[i][6]==0){
                    //monsru[i][0] = (int) ((double) 3000 * Math.random()) -1500 + xroc;
                   //monsru[i][1] = (int) ((double) 3000 * Math.random()) -1500+ yroc;
                    //if(Math.abs(monsru[i][0]-xroc)<1000 && Math.abs(monsru[i][1]-yroc)<1000){
                        monsru[i][0] += (int) 10000;
                        monsru[i][1] += (int) 10000;
                    //}
                    //monsru[i][6]=1;
                    //monsru[i][7]=0;
                }

                if((Math.abs(monsru[i][0]-xroc)>2000 || Math.abs(monsru[i][1]-yroc)>2000)&& monsru[i][6]==1){
                    monsru[i][0] = (int) ((double) 3000 * Math.random()) -1500 + xroc;
                    monsru[i][1] = (int) ((double) 3000 * Math.random()) -1500+ yroc;
                    if(Math.abs(monsru[i][0]-xroc)<1000 && Math.abs(monsru[i][1]-yroc)<1000){
                        monsru[i][0] += (int) 1000;
                        monsru[i][1] += (int) 1000;
                    }


                }




            }

            if(Math.abs(ugol1)<1) {
                canvas.drawBitmap(z1, 205,390, p);

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
