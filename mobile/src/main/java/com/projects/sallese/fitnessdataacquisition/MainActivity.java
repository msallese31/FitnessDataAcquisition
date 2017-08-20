package com.projects.sallese.fitnessdataacquisition;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import static com.projects.sallese.fitnessdataacquisition.LogHelper.logSensorLevel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://app-0e822eae-6d3e-414b-9f65-20b369ca0a3b.cleverapps.io/";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        logSensorLevel("Response is: "+ response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                logSensorLevel("That didn't work!\nError: " + error);
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

}
