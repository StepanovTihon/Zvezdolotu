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
    int yv=50;
    int xr=-50;
    int yl=-30;
    int xl=50;
    int yr=-30;
    int xvvrem=0;
    int yvvrem=50;
    int xrvrem=-50;
    int ylvrem=-30;
    int xlvrem=50;
    int yrvrem=-30;
    int ugol=0;
    int ugolb=0;
    int xdlina;
    int ydlina;
    int x=240;
    int y=427;
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
            canvas.drawText((xdlina/2)+"", 150, 525, p);
            canvas.drawText((ydlina/2)+"", 150, 300, p);


            ugol += (ugol1/3);

            xvvrem = (int) ((xv-x) * Math.cos(Math.toRadians(ugol)) + (yv-y) * Math.sin(Math.toRadians(ugol)));
            yvvrem = (int) ((yv-y) * Math.cos(Math.toRadians(ugol)) - (xv-x) * Math.sin(Math.toRadians(ugol)));
            xrvrem = (int) ((xr-x) * Math.cos(Math.toRadians(ugol)) + (yr-y) * Math.sin(Math.toRadians(ugol)));
            yrvrem = (int) ((yr-y) * Math.cos(Math.toRadians(ugol)) - (xr-x) * Math.sin(Math.toRadians(ugol)));
            xlvrem = (int) ((xl-x) * Math.cos(Math.toRadians(ugol)) + (yl-y) * Math.sin(Math.toRadians(ugol)));
            ylvrem = (int) ((yl-y) * Math.cos(Math.toRadians(ugol)) - (xl-x) * Math.sin(Math.toRadians(ugol)));
            canvas.drawLine(xvvrem+(xdlina/2), yvvrem+(ydlina/2), xrvrem+(xdlina/2), yrvrem+(ydlina/2), p);
            canvas.drawLine(xvvrem+(xdlina/2), yvvrem+(ydlina/2), xlvrem+(xdlina/2), ylvrem+(ydlina/2), p);
            canvas.drawLine(x+(xdlina/2), x+(ydlina/2), xrvrem+(xdlina/2), yrvrem+(ydlina/2), p);
            canvas.drawLine(y+(xdlina/2), y+(ydlina/2), xlvrem+(xdlina/2), ylvrem+(ydlina/2), p);
            if(ugol==359){
                ugol=0;
            }
            invalidate();
        }
    }
}
