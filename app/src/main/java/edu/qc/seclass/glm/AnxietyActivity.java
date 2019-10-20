package edu.qc.seclass.glm;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

//activity
public class AnxietyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anxiety);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button chatButton = findViewById(R.id.button2);
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent ( AnxietyActivity.this, ChatbotActivity.class);
                startActivity(i);
            }
        });
    }
}
