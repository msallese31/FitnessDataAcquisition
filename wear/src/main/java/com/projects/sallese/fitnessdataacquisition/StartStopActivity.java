package com.projects.sallese.fitnessdataacquisition;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.projects.sallese.fitnessdataacquisition.LogHelper.logSensorLevel;

public class StartStopActivity extends WearableActivity {

    private Button startStopButton;
    private String activityType;
    public final static int REQUEST_CODE_NAME_AND_WEIGHT = 1;
    public final static int REQUEST_CODE_REPETITIONS = 2;

    private int numberOfReps;
    private String exerciseName;
    private int weightUsed;

    WorkoutObject workoutObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_stop);

        activityType = (String) getIntent().getSerializableExtra("activityType");

        startStopButton = (Button) findViewById(R.id.startStopButton);
        startStopButton.setText("Start " + activityType);

        LocalBroadcastManager.getInstance(this).registerReceiver(serviceDataReceiver,
                new IntentFilter("send-service-data"));

        // Enables Always-on
        setAmbientEnabled();
    }

    public void startStop (View v){
        if (startStopButton.getText().equals("Start " + activityType)){
            startStopButton.setText("Stop");
            callActivityStart();
            return;
        }
        callActivityStop();
        startStopButton.setText("Start " + activityType);
    }

    public void startExercise (){
        workoutObject = new WorkoutObject();
        Intent nameAndWeightIntent = new Intent(this, NameAndWeightActivity.class);
        startActivityForResult(nameAndWeightIntent, REQUEST_CODE_NAME_AND_WEIGHT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        logSensorLevel("Got to activity result");
        switch(requestCode) {
            case REQUEST_CODE_NAME_AND_WEIGHT:
                switch(resultCode) {
                    case RESULT_OK:
                        logSensorLevel("Got back from name and weight ok!");
                        setNameAndWeight(data);
                        startRecordingData();
                        break;
                    case RESULT_CANCELED:
                        //you just got back from activity C - deal with resultCode
                        logSensorLevel("Something bad happened in name and weight");
                        break;
                }
                break;
            case REQUEST_CODE_REPETITIONS:
                switch(resultCode) {
                    case RESULT_OK:
                        logSensorLevel("Got back from repetitions ok!!");
                        setRepetitions(data);
                        break;
                    case RESULT_CANCELED:
                        //you just got back from activity C - deal with resultCode
                        logSensorLevel("Something bad happened in reps");
                        break;
                }
                break;
            // TODO: 8/6/17 Handle Shot
        }
    }

    private void setNameAndWeight(Intent data){
        workoutObject.setExerciseName(data.getStringExtra("exercise_name"));
        workoutObject.setWeightUsed(data.getIntExtra("weight", -1));
        // TODO: 8/7/17 Check values returned
        logSensorLevel("Set name and weight!\nName: " + workoutObject.getExerciseName() +"\nWeight: " + workoutObject.getWeightUsed());
    }

    private void setRepetitions(Intent data){
        workoutObject.setNumberOfReps(data.getIntExtra("repetitions", -1));
        // TODO: 8/7/17 Check values returned
        logSensorLevel("Set repetitions!\nReps: " + workoutObject.getNumberOfReps() +"\n");
        logSensorLevel("Name: " + workoutObject.getExerciseName() +"\nWeight: " + workoutObject.getWeightUsed());
        saveDataToJson();
    }

    public void stopExercise(){
        stopRecordingData();
        Intent enterRepsIntent = new Intent(this, EnterRepetitions.class);
        startActivityForResult(enterRepsIntent, REQUEST_CODE_REPETITIONS);
    }

    private BroadcastReceiver serviceDataReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String message = intent.getStringExtra("message");
            logSensorLevel("Got message: " + message);
            ArrayList<Float> xAcc = (ArrayList<Float>) intent.getSerializableExtra("xAcc");
            ArrayList<Float> yAcc = (ArrayList<Float>) intent.getSerializableExtra("yAcc");
            ArrayList<Float> zAcc = (ArrayList<Float>) intent.getSerializableExtra("zAcc");
            workoutObject.setAccel(xAcc, yAcc, zAcc);
        }
    };

    private void saveDataToJson() {
        try {
            JSONObject workoutJsonObject = new JSONObject();
            String exerciseName = workoutObject.getExerciseName();
            String repetitions = Integer.toString(workoutObject.getNumberOfReps());
            String weight = Integer.toString(workoutObject.getWeightUsed());
            ArrayList<Float> xAcc = workoutObject.getXAcc();
            ArrayList<Float> yAcc = workoutObject.getYAcc();
            ArrayList<Float> zAcc = workoutObject.getZAcc();

            JSONArray xAccJson = new JSONArray(xAcc);
            JSONArray yAccJson = new JSONArray(yAcc);
            JSONArray zAccJson = new JSONArray(zAcc);

            workoutJsonObject.put("exercise_name", exerciseName);
            workoutJsonObject.put("repetitions", repetitions);
            workoutJsonObject.put("weight", weight);
            workoutJsonObject.put("xAcc", xAccJson);
            workoutJsonObject.put("yAcc", yAccJson);
            workoutJsonObject.put("zAcc", zAccJson);

            logSensorLevel(workoutJsonObject.toString());

        }catch (JSONException e){
            logSensorLevel("Json Exception!" + e);
        }
    }

    public void startShoot (){
        // TODO: 8/6/17 Figure out what activity to call for shoot
    }

    public void stopShoot(){

    }

    public void startRecordingData(){
        logSensorLevel("Starting to record data");
        // TODO: 8/5/17 Pass activityType???
        startService(new Intent(this, DataCollectionService.class));
    }

    private void stopRecordingData(){
        logSensorLevel("Finished recording data");
        stopService(new Intent(this, DataCollectionService.class));
        // TODO: 8/5/17 Call activity to record details (reps, exercise, weight)... or shot info.. will need activityType
    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        // TODO: 8/4/17 Handle ambient properly
        super.onEnterAmbient(ambientDetails);
//        startStopButton.getPaint().setAntiAlias(false);
        System.out.println("test");
    }

    @Override
    public void onExitAmbient() {
        super.onExitAmbient();
        startStopButton.getPaint().setAntiAlias(true);
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
    }

    public void callActivityStart(){
        if (activityType.equals("exercise")){
            startExercise();
        }
        else if (activityType.equals("shoot")){
            startShoot();
        }
    }

    public void callActivityStop(){
        if (activityType.equals("exercise")){
            stopExercise();
        }
        else if (activityType.equals("shoot")){
            stopShoot();
        }
    }

    @Override
    public void onDestroy(){
        LocalBroadcastManager.getInstance(this).unregisterReceiver(serviceDataReceiver);
        super.onDestroy();
    }
}
