package com.anirudha.activitydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"OnCreate()", Toast.LENGTH_SHORT).show();
        Button btn1=findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                // Get the string to be passed
                String message = "Hello, SecondActivity!";

                // Create an Intent to start SecondActivity
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                // Put the string into the Intent using a key-value pair
                intent.putExtra("MESSAGE_KEY", message);

                // Start the SecondActivity
               // startActivity(intent);
                startActivity(new Intent(getApplicationContext(), MainActivity2.class));

            }
        });



    }
}