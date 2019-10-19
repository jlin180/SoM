package edu.qc.seclass.glm;

<<<<<<< HEAD

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
=======
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
>>>>>>> 6af644496b7d621b5a44db694c4fa1b7e1fe1981

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
