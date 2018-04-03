package com.example.marts.mobilelab3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.PowerManager;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private String TAG = "TAG"; //To be replaced later

    private SensorManager sensorManager;
    private Sensor accelerometer;

    private ImageView frame;
    private ImageView ball;

    private int frameWidth;
    private int frameHeight;
    private int radius;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Gets the frame and the ball
        frame = findViewById(R.id.frame);
        ball = findViewById(R.id.ball);

        //Sets the height and with of the frame
        frameWidth = frame.getWidth();
        frameHeight = frame.getHeight();

        radius = 25;

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);    //Gets the system sensors
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);  //Selects the accelerometer sensor

        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME); //Creates a listener on the sensor
        //Set the delay to GAME as that created a less "laggy" experience

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //Current coordinates of the ball
        float X = ball.getX();
        float Y = ball.getY();

        //The coordinates the ball moves to
        float nextX = X + sensorEvent.values[1];
        float nextY = Y + sensorEvent.values[0];

        //LinearLayout.LayoutParams frameMargin = (LinearLayout.LayoutParams) frame.getLayoutParams();
        ConstraintLayout.LayoutParams frameMargin = (ConstraintLayout.LayoutParams) frame.getLayoutParams();

        float width = frameMargin.width;
        float height = frameMargin.height;


        //moves the ball LEFT until it crashes with the border
        if((nextX - radius) >= frameWidth / 2){
            ball.setX(nextX);
        }else {
            onFrameHit();
            ball.setX(nextX + 35); //"Bounces" the ball of the frame
        }
        //moves the ball to the TOP until it crashes with the border
        if((nextY - radius) >= frameHeight / 2){
            ball.setY(nextY);
        }else {
            onFrameHit();
            ball.setY(nextY + 35);
        }

        /*DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int dispHeight = displayMetrics.heightPixels;
        int dispWidth = displayMetrics.widthPixels;

        if((nextX + radius) < dispWidth - frameWidth){
            ball.setX(nextX);
        }else {
            onFrameHit();
        }
        /*if((nextY) <= radius ){
            ball.setY(nextY);
        }else {
            onFrameHit();
        }

        /*

        if(nextX >= width / 2 - radius){
            ball.setX(nextX);
        }else {
            onFrameHit();
        }



        if(nextY >= frameHeight / 2 - radius){
            ball.setY(nextY);
        }else {
            onFrameHit();
        }



        /*
        if(!onFrameHit()){
            //Inspired by http://prod3.imt.hig.no/maciejp/imt3673-lab3
            ball.setY(Y + sensorEvent.values[0]); //sensor value [0] = Y [1] = X
            ball.setX(X + sensorEvent.values[1]);
        }*/

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

   @Override
    protected void onResume() {
        super.onResume();
        //sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL); //Sets a listener on the accelerometer
       sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME); //Creates a listener on the sensor
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this); //Removes the listener when the app is not in use
    }

    private void onFrameHit(){

        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(500);

        int soundId;
        SoundPool soundPool;
        //AudioAttributes audioAttributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).build();
        soundPool = new SoundPool.Builder().setMaxStreams(1).build();
        soundId = soundPool.load(this, R.raw.bing, 1);
        soundPool.play(soundId,1,1,1, 0, 1);

    }
}
