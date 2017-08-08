package com.projects.sallese.fitnessdataacquisition;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.projects.sallese.fitnessdataacquisition.LogHelper.logSensorLevel;

public class NameAndWeightActivity extends WearableActivity {

    private Button callDataCollectorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_and_weight);

        callDataCollectorButton = (Button) findViewById(R.id.callDataCollectorButton);

        // Enables Always-on
        setAmbientEnabled();
    }

    public void saveDataAndReturn (View v){
        logSensorLevel("Name and weight activity linked!");
        // TODO: 8/6/17 Save input and make sure fields are not empty
        setResult(RESULT_OK);
        finish();
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