package edu.qc.seclass.glm;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

//activity
public class MoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
