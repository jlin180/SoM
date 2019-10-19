package edu.qc.seclass.glm;

<<<<<<< HEAD
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
=======

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
>>>>>>> b3a38e8cbc6d30a0ad16ff7bbcf571ea97b9f169

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
