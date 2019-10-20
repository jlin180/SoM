package edu.qc.seclass.glm;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

//activity
public class ConnectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_connect);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
