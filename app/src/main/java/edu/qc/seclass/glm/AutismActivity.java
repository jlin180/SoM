package edu.qc.seclass.glm;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

//activity
public class AutismActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autism);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
