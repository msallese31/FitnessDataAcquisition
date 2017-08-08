package com.projects.sallese.fitnessdataacquisition;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.projects.sallese.fitnessdataacquisition.LogHelper.logSensorLevel;

public class StartStopActivity extends WearableActivity {

    private Button startStopButton;
    private String activityType;
    public final static int REQUEST_CODE_NAME_AND_WEIGHT = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_stop);

        activityType = (String) getIntent().getSerializableExtra("activityType");

        startStopButton = (Button) findViewById(R.id.startStopButton);
        startStopButton.setText("Start " + activityType);

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
                        startRecordingData();
                        break;
                    case RESULT_CANCELED:
                        //you just got back from activity C - deal with resultCode
                        logSensorLevel("Something bad happened");
                        break;
                }
                break;
            // TODO: 8/6/17 Handle Shot 
            case RESULT_CANCELED:
                //you just got back from activity C - deal with resultCode
                break;
        }
    }

    public void stopExercise(){
        stopRecordingData();
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
}
