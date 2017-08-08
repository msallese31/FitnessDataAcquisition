package com.projects.sallese.fitnessdataacquisition;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import org.json.JSONObject;

import java.util.concurrent.locks.Lock;

import static com.projects.sallese.fitnessdataacquisition.LogHelper.logSensorLevel;

public class DataCollectionService extends Service implements SensorEventListener {

    // Sensors
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Sensor mGyroscope;
    private JSONObject accSessionData;
    private JSONObject gyroSessionData;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mGyroscope = mSensorManager
                .getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        mSensorManager.registerListener(this, mAccelerometer,
                SensorManager.SENSOR_DELAY_NORMAL, new Handler());
        mSensorManager.registerListener(this, mGyroscope,
                SensorManager.SENSOR_DELAY_NORMAL, new Handler());
        // TODO: 8/5/17 Research more sensors to leverage

        return START_STICKY;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        logSensorLevel("Sensor: " + event.sensor.getName() + "\n" + "X: " + x + "Y: " + y + "Z:" + z + "\n");
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            logSensorLevel("Acclerometer should be a " + event.sensor.getName() + "\n");
            return;
        }
        logSensorLevel("Gyro should be a " + event.sensor.getName() + "\n");
    }

    @Override
    public void onDestroy() {
        // TODO: 8/5/17 Clean up after sensors (turn off, dave data, etc)
        this.mSensorManager.unregisterListener(this);
        super.onDestroy();

    }

}
