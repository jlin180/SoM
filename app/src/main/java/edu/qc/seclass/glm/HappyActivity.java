package edu.qc.seclass.glm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
//activity
public class HappyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button btnHappy = findViewById(R.id.button3);

        btnHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent ( HappyActivity.this, ConnectActivity.class);
                startActivity(i);
            }
        });

    }


}
