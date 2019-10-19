package edu.qc.seclass.glm;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

//activity
public class SadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sad);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void DoSomething(View view) {
        Intent i = new Intent ( this, HelplineActivity.class);
        startActivity(i);
    }
}
