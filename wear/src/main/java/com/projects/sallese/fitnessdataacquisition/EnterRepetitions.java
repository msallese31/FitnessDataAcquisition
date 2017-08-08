package com.projects.sallese.fitnessdataacquisition;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.TextView;

import static com.projects.sallese.fitnessdataacquisition.LogHelper.logSensorLevel;

public class EnterRepetitions extends WearableActivity {

    private TextView repetitionsEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_repetitions);

        repetitionsEditText = (TextView) findViewById(R.id.repetitionsEditText);

        // Enables Always-on
        setAmbientEnabled();
    }

    public void saveDataAndReturn (View v){
        logSensorLevel("Saving repetition data!");
        // TODO: 8/6/17 Save input and make sure fields are not empty
        validateData();
        Intent resultIntent = new Intent();
        resultIntent.putExtra("repetitions", Integer.parseInt(repetitionsEditText.getText().toString()));

        setResult(RESULT_OK, resultIntent);
        finish();
    }

    public void validateData(){
        logSensorLevel("IMPLEMENT VALIDATE DATA!!!");
    }
}
