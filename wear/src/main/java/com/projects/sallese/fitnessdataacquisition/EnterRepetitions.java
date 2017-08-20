package com.projects.sallese.fitnessdataacquisition;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

import static com.projects.sallese.fitnessdataacquisition.LogHelper.logSensorLevel;

public class EnterRepetitions extends WearableActivity {

    private NumberPicker repetitionsNumberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_repetitions);

//        repetitionsEditText = (TextView) findViewById(R.id.repetitionsEditText);
        repetitionsNumberPicker = (NumberPicker) findViewById(R.id.repetitionsNumberPicker);
        repetitionsNumberPicker.setMaxValue(100);
        repetitionsNumberPicker.setMinValue(0);

        // Enables Always-on
        setAmbientEnabled();
    }

    public void saveDataAndReturn (View v){
        logSensorLevel("Saving repetition data!");
        // TODO: 8/6/17 Save input and make sure fields are not empty
        validateData();
        Intent resultIntent = new Intent();
        resultIntent.putExtra("repetitions", repetitionsNumberPicker.getValue());
//        resultIntent.putExtra("repetitions","dummy");


        setResult(RESULT_OK, resultIntent);
        finish();
    }

    public void validateData(){
        logSensorLevel("IMPLEMENT VALIDATE DATA!!!");
    }
}
