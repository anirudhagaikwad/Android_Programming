package com.anirudha.activitylifecycle_intent;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class SecondActivity extends AppCompatActivity {
    ImageButton imageButton2,imageButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        imageButton2 = findViewById(R.id.imageButton2);
        imageButton3 = findViewById(R.id.imageButton3);
        startFirstActivity();
        startThiredActivity();
        Intent intentFromFirstActivity = getIntent();
        if (intentFromFirstActivity != null) {
            String messageFromFirstActivity = intentFromFirstActivity.getStringExtra("FROM_FIRST_ACTIVITY");
            if (messageFromFirstActivity != null) {
                Log.i("Msg from Activity1: ", messageFromFirstActivity);
            } else {
                Log.e("Error", "messageFromFirstActivity is null");
            }
        } else {
            Log.e("Error", "intentFromFirstActivity is null");
        }
    }

    private void startFirstActivity() {
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("FROM_SECOND_ACTIVITY", "Hello from SecondActivity");
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    private void startThiredActivity() {
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start ThiredActivity
                finish();
                startActivity(new Intent(getApplicationContext(), ThiredActivity.class));
            }
        });
    }
}
