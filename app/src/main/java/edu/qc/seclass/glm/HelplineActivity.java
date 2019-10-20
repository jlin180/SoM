package edu.qc.seclass.glm;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

//activity
public class HelplineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_hotlines);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
