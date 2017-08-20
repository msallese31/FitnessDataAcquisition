package com.projects.sallese.fitnessdataacquisition;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;

import static com.projects.sallese.fitnessdataacquisition.LogHelper.logSensorLevel;

public class NameAndWeightActivity extends WearableActivity {

    private Button callDataCollectorButton;
    private Spinner exerciseNameSpinner;
    private NumberPicker weightNumberPicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_and_weight);
// TODO: 8/19/17 Improve layout!!!
        callDataCollectorButton = (Button) findViewById(R.id.callDataCollectorButton);
        exerciseNameSpinner = (Spinner) findViewById(R.id.exerciseSpinner);
        weightNumberPicker = (NumberPicker) findViewById(R.id.weightNumberPicker);
        weightNumberPicker.setMinValue(0);
        weightNumberPicker.setMaxValue(200);
        // Enables Always-on
        setAmbientEnabled();
    }

    public void saveDataAndReturn (View v){
        logSensorLevel("Name and weight activity linked!");
        // TODO: 8/6/17 Validate data
        validateData();
        Intent resultIntent = new Intent();
        resultIntent.putExtra("exercise_name", exerciseNameSpinner.getSelectedItem().toString());
        resultIntent.putExtra("weight", weightNumberPicker.getValue());

        setResult(RESULT_OK, resultIntent);
        finish();
    }

    public void validateData(){
        logSensorLevel("IMPLEMENT VALIDATE DATA");
    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        // TODO: 8/4/17 Handle ambient properly
        super.onEnterAmbient(ambientDetails);
//        startStopButton.getPaint().setAntiAlias(false);
    }

    @Override
    public void onExitAmbient() {
        super.onExitAmbient();
        callDataCollectorButton.getPaint().setAntiAlias(true);
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
    }

}