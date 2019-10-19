package edu.qc.seclass.glm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//activity
public class HelplineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpline);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
