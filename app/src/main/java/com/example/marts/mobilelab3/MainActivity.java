package com.example.marts.mobilelab3;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    //Sensor
    private SensorManager sensorManager;
    private Sensor gravitySensor;

    //The ball to move around on the screen
    private ImageView ball;

    //The width of the frame
    private int frameWidth;
    //The height of the frame
    private int frameHeight;
    //Used to make the ball stay within the frame
    private int radius;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Gets the frame and the ball
        ImageView frame = findViewById(R.id.frame);
        ball = findViewById(R.id.ball);

        //Sets the height and with of the frame
        frameWidth = frame.getWidth();
        frameHeight = frame.getHeight();

        radius = 25;

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);    //Gets the system sensors
        assert sensorManager != null;
        gravitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);  //Selects the gravitySensor sensor

        sensorManager.registerListener(this, gravitySensor, SensorManager.SENSOR_DELAY_GAME); //Creates a listener on the sensor
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

        //moves the ball LEFT until it crashes with the border
        if((nextX - radius) >= frameWidth / 2){
            ball.setX(nextX);
        }else {
            onFrameHit();
            ball.setX(nextX + 35); //"Bounces" the ball of the frame
        }
        //moves the ball to the TOP until it crashes with the border
        if((nextY - radius) >= (frameHeight / 2) + 20){ //Added "+ 20" to make the ball bounce on the inner edge of the frame
            ball.setY(nextY);
        }else{
            onFrameHit();
            ball.setY(nextY + 35);
        }

        //Gets the screens height and width
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int devHeight = displayMetrics.heightPixels;
        int devWidth = displayMetrics.widthPixels;

        //moves the ball to the RIGHT until it crashes with the border
        //Ignored linter error as the log spam in the console was annoying
        if ((nextX + radius) > devWidth - (frameWidth / 2) - 60) { //Added "- 60" to make the ball bounce on the frame and not the edge of the screen
            onFrameHit();
            ball.setX(nextX - 35);
        }

        //moves the ball to the BOTTOM until it crashes with the border
        //Ignored linter error as the log spam in the console was annoying
        if ((nextY + radius) > devHeight - (frameHeight / 2) - 320) {  //Added "- 320" to make the ball bounce on the frame and off the edge of the screen
            onFrameHit();
            ball.setY(nextY - 35);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //NOT USED, but implemented as SensorEventListener needs it
    }

   @Override
    protected void onResume() {
        super.onResume();
       sensorManager.registerListener(this, gravitySensor, SensorManager.SENSOR_DELAY_GAME); //Creates a listener on the sensor
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this); //Removes the listener when the app is not in use
    }

    /**
     * Sets of multiple effects when the ball hits the edge of the frame
     */
    private void onFrameHit(){
        //Vibrator
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        assert vibrator != null;
        vibrator.vibrate(400);

        //Sound

    }
}
