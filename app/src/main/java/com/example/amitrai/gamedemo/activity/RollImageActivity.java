package com.example.amitrai.gamedemo.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

public class RollImageActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private AnimatedView animatedView = null;
    private ShapeDrawable mDrawable = new ShapeDrawable();
    public static int x, x_value;
    public static int y, y_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        animatedView = new AnimatedView(this);

        setContentView(animatedView);
    }

    // This method will update the UI on new sensor events
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            x_value = (int) sensorEvent.values[0];
            y_value = (int) sensorEvent.values[1];

            x = x - x_value;
            y = y + y_value;

            Log.e("x is:", String.valueOf(x));
            Log.e("y is:", String.valueOf(y));
        }
    }

    public void onAccuracyChanged(Sensor arg0, int arg1) {
        // TODO Auto-generated method stub
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Registering this class as a listener for the accelerometer sensor
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onStop() {
        // Un-registering the listener
        sensorManager.unregisterListener(this);
        super.onStop();
    }

    public class AnimatedView extends ImageView {
        static final int width = 150;
        static final int height = 150;

        public AnimatedView(Context context) {
            super(context);
            // TODO Auto-generated constructor stub

            mDrawable = new ShapeDrawable(new OvalShape());
            mDrawable.getPaint().setColor(0xffffAC23);
            mDrawable.setBounds(200, 200, 200, 200);
        }

        @Override
        protected void onDraw(Canvas canvas) {

            mDrawable.setBounds(2 * x, 2 * y, 2 * x + width, 2 * y + height);
            mDrawable.draw(canvas);
            invalidate();
        }
    }
}
