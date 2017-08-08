package com.projects.sallese.fitnessdataacquisition;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.TextView;

import static com.projects.sallese.fitnessdataacquisition.LogHelper.logSensorLevel;

public class BeginSessionActivity extends WearableActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin_session);

        mTextView = (TextView) findViewById(R.id.text);

        // Enables Always-on
        setAmbientEnabled();
    }

    public void shoot(View v){
        logSensorLevel("Shoot!");
        callRecordData("shoot");
    }

    public void exercise(View v){
        logSensorLevel("Exercise button!");
        callRecordData("exercise");
    }

    private void callRecordData(String activityType){
        Bundle b = new Bundle();
        b.putString("activityType",activityType);
        Intent startStopIntent = new Intent(this, StartStopActivity.class);
        startStopIntent.putExtras(b);
        startActivity(startStopIntent);
    }
}


