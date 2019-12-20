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
import android.view.View;

public class MainActivity extends AppCompatActivity {
    SensorManager sensorManager;
    Sensor sensor;
    int xv=0;
    int yv=-50;
    int xr=50;
    int yl=10;
    int xl=-50;
    int yr=10;
    int xn=0;
    int yn=20;
    int ugol=0;
    int ugolb=0;
    int xdlina;
    int ydlina;
    int[] y = new int[10];
    int[] x = new int[10];

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
        for(int i=0;i<10;i++){
            x[i]=(int) ((double) 480* Math.random());
            y[i]=(int) ((double) 800* Math.random());
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
            p.setColor(Color.BLACK);
            p.setStrokeWidth(3);
            p.setTextSize(50);
            //canvas.drawText((xdlina/2)+"", 150, 525, p);
            canvas.drawText(x[0]+"", 150, 300, p);
            if(Math.abs(ugol2)>1){
                xroc+=ugol2*Math.sin(Math.toRadians((-1*ugol)%360));
                yroc +=ugol2*Math.cos(Math.toRadians((-1*ugol)%360));
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
                ugol -= (ugol1/3);
            }
            for(int i=0;i<10;i++){
                canvas.drawCircle((int) ( (xdlina/2)+(((x[i]-xroc)+(xdlina/2)) * Math.cos(Math.toRadians(ugol)) + ((y[i]-yroc)+(ydlina/2)) * Math.sin(Math.toRadians(ugol)))), (int) ( (ydlina/2)+(((y[i]-yroc)+(ydlina/2)) * Math.cos(Math.toRadians(ugol)) - ((x[i]-xroc)+(xdlina/2)) * Math.sin(Math.toRadians(ugol)))),15, p);



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
            invalidate();
        }
    }
}
