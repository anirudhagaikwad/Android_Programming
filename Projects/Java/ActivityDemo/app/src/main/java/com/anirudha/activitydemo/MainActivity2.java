package com.anirudha.activitydemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toast.makeText(this,"OnCreate()", Toast.LENGTH_SHORT).show();

        Button btn2=findViewById(R.id.button2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        });

        Button btn3=findViewById(R.id.btnclose);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
               onDestroy();

            }
        });


        // Receive the Intent that started this activity
        Intent intent = getIntent();

        // Retrieve the string using the key specified in the sending activity
        String message = intent.getStringExtra("MESSAGE_KEY");


    }

    @Override
    protected void onStart(){
        super.onStart();
        Toast.makeText(this,"OnStart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume(){
        super.onResume();
        Toast.makeText(this,"OnResume()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause(){
        super.onPause();
        Toast.makeText(this,"OnPause()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop(){
        super.onStop();
        Toast.makeText(this,"OnStop()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Toast.makeText(this,"OnRestart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy(){
        Toast.makeText(this,"OnDestroy()", Toast.LENGTH_SHORT).show();
        super.onDestroy();

    }
}