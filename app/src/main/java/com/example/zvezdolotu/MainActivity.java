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
    MediaPlayer mPlayer3;
    int yl=10;
    int xl=-50;
    int yr=10;
    int yp,xp;
    int pul=30;
    int xn=0;
    float speedbots= (float) 2;
    int timelevel=0;
    int level=1;
    int bots=2;
    int ostal=2;
    int time_drob=0;
    int zarad=1;
    Camera camera;
    int yn=20;
    Bitmap monstr,vzruv,z1,z2,z3;
    float ugol=0;
    int ugolb=0;
    int xdlina;
    int jizn=100;
    Vibrator vibrator;
    int time;
    int ydlina;
    float[] speedturnmons = new float[30];
    int[] y = new int[10];
    int[] x = new int[10];
    int[][] zvezdu = new int [1000][4];
    int[][] puli = new int [100][5];
    int[][] monsru = new int [50000][8];
    int xroc=240;
    float xcof;
    int yroc=422;
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
        xcof= (float) (xdlina/480.0);

        mPlayer= MediaPlayer.create(this, R.raw.a);
        mPlayer2= MediaPlayer.create(this, R.raw.b);
        mPlayer3= MediaPlayer.create(this, R.raw.c);
        for(int i=0;i<1000;i++){

            zvezdu[i][0] = (int) ((int) ((double) 6000 * Math.random())-3000);
            zvezdu[i][1] = (int) ((int) ((double) 6000 * Math.random())-3000);
            zvezdu[i][2] = (int) (xcof*(double) 5 * Math.random());




        }
        for(int i=0;i<bots;i++){
            monsru[i][0] = (int) ((int) ((double) 6000 * Math.random())-3000);
            monsru[i][1] = (int) ((int) ((double) 6000 * Math.random())-3000);
            monsru[i][2] = (int) ((double) 7 * Math.random());
            monsru[i][3] = (int) (xcof*((double) 100 * Math.random()/20)+speedbots);
            monsru[i][4] = (int) ((double) 360 * Math.random());
            monsru[i][5] = (int) ((double) 4 * Math.random())-2;
            monsru[i][6] = (int) ((double)1);



        }
        for(int i=0;i<30;i++){
            puli[i][0] = (int) -30000;
            puli[i][1] = (int) -30000;
            puli[i][2] = (int) 0;
            puli[i][3] = (int) 0;




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
            if(Math.random()*100>95){
                pul+=1;
            }
            time_drob+=1;
            p.setColor(Color.rgb(255,255,255));
            canvas.drawARGB(1000, 0, 0, 0);
            p.setStrokeWidth(xcof*1);
            p.setTextSize(xcof*20);
            canvas.drawText("level "+(puli[0][0])+" "+xroc, xcof*150, 50, p);
            canvas.drawText("осталось "+(puli[0][1])+" "+yroc, 0, xcof*150, p);
            canvas.drawText("пуль "+pul, 0, xcof*180, p);
            canvas.drawText("взрывов "+zarad, 0, xcof*210, p);


            speedgo= (float) (((ugol2/5)+speedgo)*0.5+(1.0-0.5)*speedgo);
            if(speedgo>20){
                speedgo=19;
            }
            xroc+=speedgo*Math.sin(Math.toRadians((-1*ugol)%360));
            yroc +=speedgo*Math.cos(Math.toRadians((-1*ugol)%360));
            canvas.drawLine(xcof*100,0,xcof*100,xcof*100,p);
            canvas.drawLine(0,xcof*100,xcof*100,xcof*100,p);
            p.setColor(Color.rgb( 93, 207, 52));
            canvas.drawCircle( xcof*((240/40)+50), xcof*((422/40)+50),  xcof*5, p);
            canvas.drawRect(0, (ydlina-(xcof*50)), (jizn)*(xdlina/100), (ydlina-(xcof*100)+(xcof*100)), p);
            p.setColor(Color.rgb( 255, 255, 255));

            if(xp>240 && yp>480 && pul>0){
                for(int o=0;o<100;o++){
                    if(puli[o][3]==0){
                        puli[o][3]=1;
                        puli[o][2]= (int) (ugol+180)%360;
                        puli[o][4]= (int) (40+(speedgo*-1));
                        pul-=1;
                        mPlayer3.start();
                        break;
                    }

                }

            }

            if(xp<240 && yp>480 && pul>6 && time_drob>100){
                time_drob=0;
                mPlayer.start();
                for(int o=0;o<100;o++){
                    if(puli[o][3]==0){
                            puli[o][3]=1;
                            puli[o][2]= (int) (ugol+180)%360;
                            puli[o][4]= (int) (40+(speedgo*-1));
                        for(int i=0;i<30;i++){
                            if(puli[i][3]==0) {
                                puli[i][3] = 1;
                                puli[i][2] = (int) (ugol + 180) % 360;
                                puli[i][4] = (int) (40 + (speedgo * -1));
                                pul-=1;
                                break;
                            }

                        }
                        for(int i=0;i<30;i++){
                            if(puli[i][3]==0) {
                                puli[i][3] = 1;
                                puli[i][2] = (int) (ugol + 180)-5 % 360;
                                puli[i][4] = (int) (40 + (speedgo * -1));
                                pul-=1;
                                break;
                            }

                        }
                        for(int i=0;i<30;i++){
                            if(puli[i][3]==0) {
                                puli[i][3] = 1;
                                puli[i][2] = (int) (ugol + 180)-10 % 360;
                                puli[i][4] = (int) (40 + (speedgo * -1));
                                pul-=1;
                                break;
                            }

                        }
                        for(int i=0;i<30;i++){
                            if(puli[i][3]==0) {
                                puli[i][3] = 1;
                                puli[i][2] = (int) (ugol + 180)-15 % 360;
                                puli[i][4] = (int) (40 + (speedgo * -1));
                                pul-=1;
                                break;
                            }

                        }
                        for(int i=0;i<30;i++){
                            if(puli[i][3]==0) {
                                puli[i][3] = 1;
                                puli[i][2] = (int) (ugol + 180)+5 % 360;
                                puli[i][4] = (int) (40 + (speedgo * -1));
                                pul-=1;
                                break;
                            }

                        }
                        for(int i=0;i<30;i++){
                            if(puli[i][3]==0) {
                                puli[i][3] = 1;
                                puli[i][2] = (int) (ugol + 180)+10 % 360;
                                puli[i][4] = (int) (40 + (speedgo * -1));
                                pul-=1;
                                break;
                            }

                        }
                        for(int i=0;i<30;i++){
                            if(puli[i][3]==0) {
                                puli[i][3] = 1;
                                puli[i][2] = (int) (ugol + 180)+15 % 360;
                                puli[i][4] = (int) (40 + (speedgo * -1));
                                pul-=1;
                                break;
                            }

                        }
                        break;
                    }

                }

            }
            if(Math.abs(ugol1)>1){
                if(ugol1<0) {
                    canvas.drawBitmap(z3, (xdlina/2)-xcof*32,(ydlina/2), p);
                }

                if(ugol1>0) {
                    canvas.drawBitmap(z2 ,(xdlina/2)-xcof*32,(ydlina/2), p);
                }

            }
            speedturn= (float) ((ugol1+speedturn)*0.5+(1.0-0.5)*speedturn);
            ugol = -1*speedturn;

            for(int i=0;i<1000;i++){
                if(zvezdu[i][2]<xcof*1){
                    p.setColor(Color.rgb( 93, 207, 52));

                }else if(zvezdu[i][2]<xcof*2){
                    p.setColor(Color.rgb( 73, 88, 222));

                }else if(zvezdu[i][2]<xcof*3){
                    p.setColor(Color.rgb( 209, 21, 65));
                }else if(zvezdu[i][2]<xcof*4){
                    p.setColor(Color.rgb( 209, 99, 21));


                }else if(zvezdu[i][2]<xcof*5){
                    p.setColor(Color.rgb( 159, 209, 21));


                }

                if(Math.abs(zvezdu[i][0]-xroc)<3000.0 && Math.abs(zvezdu[i][1]-yroc)<3000.0) {
                    canvas.drawCircle((int) ((xdlina / 2) + (((zvezdu[i][0] - xroc) + (xdlina / 2)) * Math.cos(Math.toRadians(ugol)) + ((zvezdu[i][1] - yroc) + (ydlina / 2)) * Math.sin(Math.toRadians(ugol)))), (int) ((ydlina / 2) + (((zvezdu[i][1] - yroc) + (ydlina / 2)) * Math.cos(Math.toRadians(ugol)) - ((zvezdu[i][0] - xroc) + (xdlina / 2)) * Math.sin(Math.toRadians(ugol)))), zvezdu[i][2] + xcof*5, p);

                }
                if(Math.abs(zvezdu[i][0]-xroc)>3000.0 || Math.abs(zvezdu[i][1]-yroc)>3000.0){
                    zvezdu[i][0] = (int) ((int) ((double) 6000.0 * Math.random()) -3000.0 + xroc);
                    zvezdu[i][1] = (int) ((int) ((double) 6000.0 * Math.random()) -3000.0+ yroc);
                    if(Math.abs(zvezdu[i][0]-xroc)<2000.0 && Math.abs(zvezdu[i][1]-yroc)<2000.0){
                        zvezdu[i][0] += (int) 2000.0;
                        zvezdu[i][1] += (int) 2000.0;
                    }


                }



            }
            p.setColor(Color.rgb(255,255,255));
            for(int i=0;i<bots;i++){
                float tmp1,tmp2;
                if(monsru[i][6]==1) {
                    tmp1 = (float) (((xdlina / 2) + (((monsru[i][0] - xroc) + (xdlina / 2)) * Math.cos(Math.toRadians(ugol)) + ((monsru[i][1] - yroc) + (ydlina / 2)) * Math.sin(Math.toRadians(ugol)))));
                    tmp2 = (float) (((ydlina / 2) + (((monsru[i][1] - yroc) + (ydlina / 2)) * Math.cos(Math.toRadians(ugol)) - ((monsru[i][0] - xroc) + (xdlina / 2)) * Math.sin(Math.toRadians(ugol)))));
                    canvas.drawCircle((int) (tmp1) / (40) + (xcof*50), (int) (tmp2) / (40) + (xcof*50), 1, p);
                }
                if(xp>240 && yp<480 && zarad>0){
                    for(int u=0;u<bots;u++) {
                        if (monsru[u][6] == 1 && Math.abs(monsru[u][0] - xroc) < xcof * 1500 && Math.abs(monsru[u][1] - yroc) < xcof * 1500) {
                            monsru[u][6] = 0;
                            ostal -= 1;
                            mPlayer.start();
                            if(Math.random()*100>90){
                                monsru[u][6]=-1;
                            }
                            if(Math.random()*100<30){
                                monsru[u][6]=-2;
                            }
                            pul+=10;




                        }
                    }
                    zarad -= 1;
                    vibrator.vibrate(1000);
                    xp = 0;
                    yp = 0;


                }

                if(xp>0 && xp<400){
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
                    monsru[i][4]= (int) ((int) ((monsru[i][4])*0.2 + 0.1*((float) Math.toDegrees(Math.atan2((monsru[i][1]-(yroc-422)), (monsru[i][0]-(xroc-240))) * (180 / Math.PI)))));

                    //monsru[i][4] = (int) ((int) (float) Math.toDegrees(Math.atan2(yDiff, xDiff) * (180 / Math.PI)));

                    monsru[i][0] += monsru[i][3] * Math.sin(Math.toRadians((-1 * monsru[i][4]) % 360));
                    monsru[i][1] += monsru[i][3] * Math.cos(Math.toRadians((-1 * monsru[i][4]) % 360));
                }
                tmp1 = (float) (((xdlina / 2) + (((monsru[i][0] - xroc) + (xdlina / 2)) * Math.cos(Math.toRadians(ugol)) + ((monsru[i][1] - yroc) + (ydlina / 2)) * Math.sin(Math.toRadians(ugol)))) );
                tmp2 = (float) (((ydlina / 2) + (((monsru[i][1] - yroc) + (ydlina / 2)) * Math.cos(Math.toRadians(ugol)) - ((monsru[i][0] - xroc) + (xdlina / 2)) * Math.sin(Math.toRadians(ugol)))) );

                if(monsru[i][6]>0 && tmp1>(xdlina / 2)-40 && tmp1<(xdlina / 2)+40 && tmp2>(ydlina / 2)-40 && tmp2<(ydlina / 2)+40){
                    monsru[i][6]=0;
                    ostal-=1;
                    mPlayer.start();
                    jizn-=monsru[i][3];


                    vibrator.vibrate(100);
                }
                if(monsru[i][6]==-1 && tmp1>(xdlina / 2)-60 && tmp1<(xdlina / 2)+60 && tmp2>(ydlina / 2)-60 && tmp2<(ydlina / 2)+60){
                    monsru[i][6]=0;
                    monsru[i][0] += (int) xcof*10000;
                    monsru[i][1] += (int) xcof*10000;
                    zarad+=1;


                    vibrator.vibrate(500);
                }
                if(monsru[i][6]==-2 && tmp1>(xdlina / 2)-60 && tmp1<(xdlina / 2)+60 && tmp2>(ydlina / 2)-60 && tmp2<(ydlina / 2)+60){
                    monsru[i][6]=0;
                    monsru[i][0] += (int) xcof*10000;
                    monsru[i][1] += (int) xcof*10000;
                    jizn+=25;


                    vibrator.vibrate(500);
                }


                if( Math.abs(monsru[i][0]-xroc)<xcof*1000 && Math.abs(monsru[i][1]-yroc)<xcof*1000 && monsru[i][6]==1) {
                    canvas.save();
                    canvas.rotate(monsru[i][4]-180-ugol,(int) ((xdlina / 2) + (((monsru[i][0] - xroc) + (xdlina / 2)) * Math.cos(Math.toRadians(ugol)) + ((monsru[i][1] - yroc) + (ydlina / 2)) * Math.sin(Math.toRadians(ugol)))),(int) ((ydlina / 2) + (((monsru[i][1] - yroc) + (ydlina / 2)) * Math.cos(Math.toRadians(ugol)) - ((monsru[i][0] - xroc) + (xdlina / 2)) * Math.sin(Math.toRadians(ugol)))));

                    canvas.drawBitmap(monstr, (int) ((xdlina / 2) + (((monsru[i][0] - xroc) + (xdlina / 2)) * Math.cos(Math.toRadians(ugol)) + ((monsru[i][1] - yroc) + (ydlina / 2)) * Math.sin(Math.toRadians(ugol))))-22, (float) (int) ((ydlina / 2) + (((monsru[i][1] - yroc) + (ydlina / 2)) * Math.cos(Math.toRadians(ugol)) - ((monsru[i][0] - xroc) + (xdlina / 2)) * Math.sin(Math.toRadians(ugol))))-34, p);
                    canvas.restore();
                }
                //if(monsru[i][6]==1 && xp>0 && xp<400 && (int) ((xdlina / 2) + (((monsru[i][0] - xroc) + (xdlina / 2)) * Math.cos(Math.toRadians(ugol)) + ((monsru[i][1] - yroc) + (ydlina / 2)) * Math.sin(Math.toRadians(ugol))))-xcof*11<xcof*260 && (int) ((xdlina / 2) + (((monsru[i][0] - xroc) + (xdlina / 2)) * Math.cos(Math.toRadians(ugol)) + ((monsru[i][1] - yroc) + (ydlina / 2)) * Math.sin(Math.toRadians(ugol))))-xcof*11>xcof*220 && (int) ((ydlina / 2) + (((monsru[i][1] - yroc) + (ydlina / 2)) * Math.cos(Math.toRadians(ugol)) - ((monsru[i][0] - xroc) + (xdlina / 2)) * Math.sin(Math.toRadians(ugol))))-xcof*8<xcof*400 && (int) ((ydlina / 2) + (((monsru[i][1] - yroc) + (ydlina / 2)) * Math.cos(Math.toRadians(ugol)) - ((monsru[i][0] - xroc) + (xdlina / 2)) * Math.sin(Math.toRadians(ugol))))-xcof*8>xcof*0){
                //    monsru[i][6]=0;
                //    ostal-=1;
                //    mPlayer.start();

                //}
                if(monsru[i][6]==-2 && monsru[i][7]<1000){
                    canvas.save();
                    canvas.rotate(360-ugol,(int) ((xdlina / 2) + (((monsru[i][0] - xroc) + (xdlina / 2)) * Math.cos(Math.toRadians(ugol)) + ((monsru[i][1] - yroc) + (ydlina / 2)) * Math.sin(Math.toRadians(ugol)))),(int) ((ydlina / 2) + (((monsru[i][1] - yroc) + (ydlina / 2)) * Math.cos(Math.toRadians(ugol)) - ((monsru[i][0] - xroc) + (xdlina / 2)) * Math.sin(Math.toRadians(ugol)))));
                    canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.l), (int) ((xdlina / 2) + (((monsru[i][0] - xroc) + (xdlina / 2)) * Math.cos(Math.toRadians(ugol)) + ((monsru[i][1] - yroc) + (ydlina / 2)) * Math.sin(Math.toRadians(ugol))))-25, (float) (int) ((ydlina / 2) + (((monsru[i][1] - yroc) + (ydlina / 2)) * Math.cos(Math.toRadians(ugol)) - ((monsru[i][0] - xroc) + (xdlina / 2)) * Math.sin(Math.toRadians(ugol))))-25, p);
                    canvas.restore();
                    monsru[i][7]+=1;
                } else if(monsru[i][7]>1000 && monsru[i][6]==-2){
                    monsru[i][0] += (int) xcof*10000;
                    monsru[i][1] += (int) xcof*10000;
                }
                if(monsru[i][6]==-1 && monsru[i][7]<1000){
                    canvas.save();
                    canvas.rotate(360-ugol,(int) ((xdlina / 2) + (((monsru[i][0] - xroc) + (xdlina / 2)) * Math.cos(Math.toRadians(ugol)) + ((monsru[i][1] - yroc) + (ydlina / 2)) * Math.sin(Math.toRadians(ugol)))),(int) ((ydlina / 2) + (((monsru[i][1] - yroc) + (ydlina / 2)) * Math.cos(Math.toRadians(ugol)) - ((monsru[i][0] - xroc) + (xdlina / 2)) * Math.sin(Math.toRadians(ugol)))));
                    canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.i), (int) ((xdlina / 2) + (((monsru[i][0] - xroc) + (xdlina / 2)) * Math.cos(Math.toRadians(ugol)) + ((monsru[i][1] - yroc) + (ydlina / 2)) * Math.sin(Math.toRadians(ugol))))-25, (float) (int) ((ydlina / 2) + (((monsru[i][1] - yroc) + (ydlina / 2)) * Math.cos(Math.toRadians(ugol)) - ((monsru[i][0] - xroc) + (xdlina / 2)) * Math.sin(Math.toRadians(ugol))))-25, p);
                    canvas.restore();

                    monsru[i][7]+=1;
                } else if(monsru[i][7]>1000 && monsru[i][6]==-1){
                    //monsru[i][0] = (int) ((double) 3000 * Math.random()) -1500 + xroc;
                    //monsru[i][1] = (int) ((double) 3000 * Math.random()) -1500+ yroc;
                    //if(Math.abs(monsru[i][0]-xroc)<1000 && Math.abs(monsru[i][1]-yroc)<1000){
                    monsru[i][0] += (int) xcof*10000;
                    monsru[i][1] += (int) xcof*10000;


                    //}

                    //monsru[i][6]=1;
                    //monsru[i][7]=0;
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
                        monsru[i][0] += (int) xcof*10000;
                        monsru[i][1] += (int) xcof*10000;


                    //}

                    //monsru[i][6]=1;
                    //monsru[i][7]=0;
                }
                if(jizn<=0){
                    finish();
                }
                if(ostal==0){
                    level+=1;
                    //timelevel=0;
                    speedbots= (float) (speedbots+0.7);
                    ostal=bots*2;
                    bots=ostal;
                    for(int h=0;h<bots;h++) {
                        monsru[h][0] = (int) (xcof*(double) 6000 * Math.random()) - 3000;
                        monsru[h][1] = (int) (xcof*(double) 6000 * Math.random()) - 3000;
                        monsru[h][2] = (int) ((double) 7 * Math.random());
                        monsru[h][3] = (int) ((int) xcof*((double) 100 * Math.random() / 20) + speedbots);
                        monsru[h][4] = (int) ((double) 360 * Math.random());
                        monsru[h][5] = (int) ((double) 4 * Math.random()) - 2;
                        monsru[h][6] = (int) ((double) 1);
                        monsru[h][7] = (int) ((double) 0);
                    }

                }
                if((Math.abs(monsru[i][0]-xroc)>xcof*2000 || Math.abs(monsru[i][1]-yroc )>xcof*2000 || Math.abs((tmp1/40)+50)>xcof*100 || (Math.abs((tmp2/40)+50)>xcof*100))&& monsru[i][6]==1){
                    monsru[i][0] = (int) ((double) 3000 * Math.random()) -1500 + xroc;
                    monsru[i][1] = (int) ((double) 3000 * Math.random()) -1500+ yroc;
                    if(Math.abs(monsru[i][0]-xroc)<xcof*1000 && Math.abs(monsru[i][1]-yroc)<xcof*1000){
                        monsru[i][0] += (int) xcof*1000;
                        monsru[i][1] += (int) xcof*1000;
                    }


                }




            }
            for(int i=0;i<30;i++){
                if(puli[i][3]==1){
                    canvas.save();
                    canvas.rotate(puli[i][2]-180-ugol,(int) ((xdlina / 2) + (((puli[i][0] - xroc) + (xdlina / 2)) * Math.cos(Math.toRadians(ugol)) + ((puli[i][1] - yroc) + (ydlina / 2)) * Math.sin(Math.toRadians(ugol)))),(int) ((ydlina / 2) + (((puli[i][1] - yroc) + (ydlina / 2)) * Math.cos(Math.toRadians(ugol)) - ((puli[i][0] - xroc) + (xdlina / 2)) * Math.sin(Math.toRadians(ugol)))));

                    canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.p), (int) ((xdlina / 2) + (((puli[i][0] - xroc) + (xdlina / 2)) * Math.cos(Math.toRadians(ugol)) + ((puli[i][1] - yroc) + (ydlina / 2)) * Math.sin(Math.toRadians(ugol))))-3, (float) (int) ((ydlina / 2) + (((puli[i][1] - yroc) + (ydlina / 2)) * Math.cos(Math.toRadians(ugol)) - ((puli[i][0] - xroc) + (xdlina / 2)) * Math.sin(Math.toRadians(ugol))))-3, p);
                    canvas.restore();
                    puli[i][0] += puli[i][4] * Math.sin(Math.toRadians((-1 * puli[i][2]) % 360));
                    puli[i][1] += puli[i][4] * Math.cos(Math.toRadians((-1 * puli[i][2]) % 360));


                }
                else{
                    puli[i][0]=(xroc)-240;
                    puli[i][1]=(yroc)-422;
                }
                for(int m=0;m<100;m++) {
                    if (monsru[m][6]==1 && Math.abs(puli[i][0] - monsru[m][0]) < xcof * 20 && Math.abs(puli[i][1] - monsru[m][1]) < xcof * 20) {

                        monsru[m][6]=0;
                        if(Math.random()*100>70){
                            monsru[m][6]=-1;
                        }
                        if(Math.random()*100<30){
                            monsru[m][6]=-2;
                        }
                        ostal-=1;
                        mPlayer.start();
                        puli[i][3]=0;
                        pul+=10;
                    }
                }
                if( puli[i][3]==1 && (int) ((xdlina / 2) + (((puli[i][0] - xroc) + (xdlina / 2)) * Math.cos(Math.toRadians(ugol)) + ((puli[i][1] - yroc) + (ydlina / 2)) * Math.sin(Math.toRadians(ugol))))>xcof*490 || (int) ((xdlina / 2) + (((puli[i][0] - xroc) + (xdlina / 2)) * Math.cos(Math.toRadians(ugol)) + ((puli[i][1] - yroc) + (ydlina / 2)) * Math.sin(Math.toRadians(ugol))))<0 || (float) (int) ((ydlina / 2) + (((puli[i][1] - yroc) + (ydlina / 2)) * Math.cos(Math.toRadians(ugol)) - ((puli[i][0] - xroc) + (xdlina / 2)) * Math.sin(Math.toRadians(ugol))))>xcof*854 || (float) (int) ((ydlina / 2) + (((puli[i][1] - yroc) + (ydlina / 2)) * Math.cos(Math.toRadians(ugol)) - ((puli[i][0] - xroc) + (xdlina / 2)) * Math.sin(Math.toRadians(ugol))))<0){
                    puli[i][3]=0;


                }







            }
            if(Math.abs(ugol1)<1) {
                canvas.drawBitmap(z1, (xdlina/2)-xcof*32,(ydlina/2), p);

            }
            if(ugol==359){
                ugol=0;
            }
            //canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.p4),440, 800, p);


            invalidate();
        }

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {
            xp = (int) event.getX();
            yp = (int) event.getY();
        } else if (action == MotionEvent.ACTION_UP) {
            xp = (int) 0;
            yp = (int) 0;
        }
        return true;
    }
}
